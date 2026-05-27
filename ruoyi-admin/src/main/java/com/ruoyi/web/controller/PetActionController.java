package com.ruoyi.web.controller;

import java.util.List;
import java.util.ArrayList;
import javax.servlet.http.HttpServletResponse;
import org.springframework.dao.DataAccessException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.virtualPet.domain.PetAction;
import com.ruoyi.virtualPet.service.IPetActionService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 宠物动作定义Controller
 *
 * @author codex
 * @date 2026-05-27
 */
@RestController
@RequestMapping("/pet/action")
public class PetActionController extends BaseController
{
    @Autowired
    private IPetActionService petActionService;

    /**
     * 桌面宠物获取已启用动作配置
     */
    @GetMapping("/enabled")
    public AjaxResult enabled()
    {
        try
        {
            return success(petActionService.selectEnabledPetActionList());
        }
        catch (DataAccessException e)
        {
            return success(defaultActions());
        }
    }

    private List<PetAction> defaultActions()
    {
        List<PetAction> actions = new ArrayList<>();
        actions.add(createAction("idle", "待机", "我在这里陪着你。", "breathe", 10L));
        actions.add(createAction("walk", "散步", "去屏幕边上溜达一下。", "walk", 20L));
        actions.add(createAction("talk", "冒泡说话", "工作辛苦啦，记得喝水。", "talk", 30L));
        actions.add(createAction("drag", "拖拽", "搬家完成。", "drag", 40L));
        actions.add(createAction("feed", "喂食", "吃饱了，精神一点点回来了。", "eat", 50L));
        actions.add(createAction("clean", "清洁", "香香软软，清爽完成。", "bath", 60L));
        actions.add(createAction("play", "玩耍", "玩得好开心。", "play", 70L));
        actions.add(createAction("sleep", "睡觉", "我先睡一小会儿。", "sleep", 80L));
        actions.add(createAction("wake", "叫醒", "醒啦，伸个懒腰。", "wake", 90L));
        actions.add(createAction("sick", "生病", "有点不舒服，需要照顾。", "sick", 100L));
        return actions;
    }

    private PetAction createAction(String code, String name, String bubbleText, String animationName, Long sortOrder)
    {
        PetAction action = new PetAction();
        action.setActionCode(code);
        action.setActionName(name);
        action.setBubbleText(bubbleText);
        action.setAnimationName(animationName);
        action.setSortOrder(sortOrder);
        action.setEnabled(1L);
        action.setDeleted(0L);
        return action;
    }

    /**
     * 查询宠物动作定义列表
     */
    @PreAuthorize("@ss.hasPermi('pet:action:list')")
    @GetMapping("/list")
    public TableDataInfo list(PetAction petAction)
    {
        startPage();
        List<PetAction> list = petActionService.selectPetActionList(petAction);
        return getDataTable(list);
    }

    /**
     * 导出宠物动作定义列表
     */
    @PreAuthorize("@ss.hasPermi('pet:action:export')")
    @Log(title = "宠物动作定义", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PetAction petAction)
    {
        List<PetAction> list = petActionService.selectPetActionList(petAction);
        ExcelUtil<PetAction> util = new ExcelUtil<PetAction>(PetAction.class);
        util.exportExcel(response, list, "宠物动作定义数据");
    }

    /**
     * 获取宠物动作定义详细信息
     */
    @PreAuthorize("@ss.hasPermi('pet:action:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(petActionService.selectPetActionById(id));
    }

    /**
     * 新增宠物动作定义
     */
    @PreAuthorize("@ss.hasPermi('pet:action:add')")
    @Log(title = "宠物动作定义", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PetAction petAction)
    {
        return toAjax(petActionService.insertPetAction(petAction));
    }

    /**
     * 修改宠物动作定义
     */
    @PreAuthorize("@ss.hasPermi('pet:action:edit')")
    @Log(title = "宠物动作定义", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PetAction petAction)
    {
        return toAjax(petActionService.updatePetAction(petAction));
    }

    /**
     * 删除宠物动作定义
     */
    @PreAuthorize("@ss.hasPermi('pet:action:remove')")
    @Log(title = "宠物动作定义", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(petActionService.deletePetActionByIds(ids));
    }
}
