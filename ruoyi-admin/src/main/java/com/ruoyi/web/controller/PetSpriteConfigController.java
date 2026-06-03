package com.ruoyi.web.controller;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.virtualPet.domain.PetSpriteConfig;
import com.ruoyi.virtualPet.service.IPetSpriteConfigService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;

/**
 * 宠物精灵图表配置Controller
 *
 * @author dzl
 * @date 2026-06-02
 */
@RestController
@RequestMapping("/pet/sprite")
public class PetSpriteConfigController extends BaseController
{
    @Autowired
    private IPetSpriteConfigService petSpriteConfigService;

    /** 查询列表 */
    @PreAuthorize("@ss.hasPermi('pet:sprite:list')")
    @GetMapping("/list")
    public TableDataInfo list(PetSpriteConfig petSpriteConfig)
    {
        startPage();
        List<PetSpriteConfig> list = petSpriteConfigService.selectPetSpriteConfigList(petSpriteConfig);
        return getDataTable(list);
    }

    /** 导出 */
    @PreAuthorize("@ss.hasPermi('pet:sprite:export')")
    @Log(title = "宠物精灵图配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PetSpriteConfig petSpriteConfig)
    {
        List<PetSpriteConfig> list = petSpriteConfigService.selectPetSpriteConfigList(petSpriteConfig);
        ExcelUtil<PetSpriteConfig> util = new ExcelUtil<PetSpriteConfig>(PetSpriteConfig.class);
        util.exportExcel(response, list, "宠物精灵图配置数据");
    }

    /** 根据ID获取详细信息 */
    @PreAuthorize("@ss.hasPermi('pet:sprite:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable Long id)
    {
        return success(petSpriteConfigService.selectPetSpriteConfigById(id));
    }

    /** 按宠物类型获取配置（供桌面宠物使用，不需要管理权限） */
    @GetMapping("/config/{petTypeId}")
    public AjaxResult getByPetTypeId(@PathVariable Long petTypeId)
    {
        PetSpriteConfig config = petSpriteConfigService.selectByPetTypeId(petTypeId);
        if (config == null)
        {
            return success(null);
        }
        // 解析 actions JSON 为前端可用格式
        JSONObject result = new JSONObject();
        result.put("cellW", config.getCellW());
        result.put("cellH", config.getCellH());
        result.put("fps", config.getFps());
        result.put("spriteImage", config.getSpriteImage());
        try {
            result.put("actions", JSON.parse(config.getActions()));
        } catch (Exception e) {
            result.put("actions", new JSONArray());
        }
        return success(result);
    }

    /** 新增 */
    @PreAuthorize("@ss.hasPermi('pet:sprite:add')")
    @Log(title = "宠物精灵图配置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PetSpriteConfig petSpriteConfig)
    {
        return toAjax(petSpriteConfigService.insertPetSpriteConfig(petSpriteConfig));
    }

    /** 修改 */
    @PreAuthorize("@ss.hasPermi('pet:sprite:edit')")
    @Log(title = "宠物精灵图配置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PetSpriteConfig petSpriteConfig)
    {
        return toAjax(petSpriteConfigService.updatePetSpriteConfig(petSpriteConfig));
    }

    /** 删除 */
    @PreAuthorize("@ss.hasPermi('pet:sprite:remove')")
    @Log(title = "宠物精灵图配置", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(petSpriteConfigService.deletePetSpriteConfigByIds(ids));
    }

    /** 导入 hatch-pet validation.json，自动生成配置 */
    @PreAuthorize("@ss.hasPermi('pet:sprite:add')")
    @Log(title = "导入hatch-pet配置", businessType = BusinessType.INSERT)
    @PostMapping("/import")
    public AjaxResult importHatchPet(@RequestBody JSONObject body)
    {
        Long petTypeId = body.getLong("petTypeId");
        String spriteImage = body.getString("spriteImage");
        JSONObject validation = body.getJSONObject("validation");

        if (petTypeId == null || validation == null)
        {
            return error("缺少必要参数");
        }

        Integer totalWidth = validation.getInteger("width");
        Integer totalHeight = validation.getInteger("height");
        if (totalWidth == null || totalHeight == null)
        {
            return error("validation.json 缺少 width/height");
        }

        // 兼容两种格式：cells（扁平）和 rows（嵌套）
        JSONArray cells = validation.getJSONArray("cells");
        JSONArray rows = validation.getJSONArray("rows");

        JSONArray actions = new JSONArray();
        int maxCols = 0;
        int totalRows = 0;

        if (cells != null && !cells.isEmpty())
        {
            // cells 格式：扁平数组，按 row+column 分组
            // 先找出最大列数和行数
            java.util.Map<Integer, java.util.Map<Integer, JSONObject>> grid = new java.util.LinkedHashMap<>();
            for (int i = 0; i < cells.size(); i++)
            {
                JSONObject cell = cells.getJSONObject(i);
                Integer row = cell.getInteger("row");
                Integer col = cell.getInteger("column");
                Boolean used = cell.getBoolean("used");
                if (row == null || col == null) continue;
                grid.computeIfAbsent(row, k -> new java.util.LinkedHashMap<>()).put(col, cell);
                if (Boolean.TRUE.equals(used))
                {
                    if (col + 1 > maxCols) maxCols = col + 1;
                    if (row + 1 > totalRows) totalRows = row + 1;
                }
            }

            // 按行生成 actions
            for (java.util.Map.Entry<Integer, java.util.Map<Integer, JSONObject>> entry : grid.entrySet())
            {
                int rowNum = entry.getKey();
                java.util.Map<Integer, JSONObject> rowCells = entry.getValue();
                // 计算该行 used=true 的帧数
                int frameCount = 0;
                String state = null;
                for (java.util.Map.Entry<Integer, JSONObject> cellEntry : rowCells.entrySet())
                {
                    JSONObject cell = cellEntry.getValue();
                    if (Boolean.TRUE.equals(cell.getBoolean("used")))
                    {
                        frameCount++;
                    }
                    if (state == null) state = cell.getString("state");
                }
                if (state == null || frameCount == 0) continue;

                JSONObject action = new JSONObject();
                action.put("key", state);
                action.put("row", rowNum);
                action.put("frames", frameCount);
                boolean loop = state.contains("idle") || state.contains("wait")
                    || state.contains("running") || state.contains("walk");
                action.put("loop", loop);
                actions.add(action);
            }
        }
        else if (rows != null && !rows.isEmpty())
        {
            // rows 格式：嵌套数组
            totalRows = rows.size();
            for (int i = 0; i < rows.size(); i++)
            {
                JSONObject row = rows.getJSONObject(i);
                String state = row.getString("state");
                JSONArray frames = row.getJSONArray("frames");
                if (state == null || frames == null) continue;
                if (frames.size() > maxCols) maxCols = frames.size();

                JSONObject action = new JSONObject();
                action.put("key", state);
                action.put("row", i);
                action.put("frames", frames.size());
                boolean loop = state.contains("idle") || state.contains("wait")
                    || state.contains("running") || state.contains("walk");
                action.put("loop", loop);
                actions.add(action);
            }
        }
        else
        {
            return error("validation.json 格式不正确：缺少 cells 或 rows");
        }

        if (actions.isEmpty())
        {
            return error("未找到任何动作帧");
        }

        int cellW = totalWidth / maxCols;
        int cellH = totalHeight / totalRows;

        // 创建配置记录
        PetSpriteConfig config = new PetSpriteConfig();
        config.setPetTypeId(petTypeId);
        config.setSpriteImage(spriteImage);
        config.setCellW(cellW);
        config.setCellH(cellH);
        config.setFps(8);
        config.setActions(actions.toJSONString());
        config.setStatus("0");

        return toAjax(petSpriteConfigService.insertPetSpriteConfig(config));
    }
}
