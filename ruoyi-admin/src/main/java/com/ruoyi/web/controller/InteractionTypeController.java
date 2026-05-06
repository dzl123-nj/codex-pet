package com.ruoyi.player.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
import com.ruoyi.player.domain.InteractionType;
import com.ruoyi.player.service.IInteractionTypeService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 互动类型Controller
 * 
 * @author dzl
 * @date 2026-04-22
 */
@RestController
@RequestMapping("/pUser/type")
public class InteractionTypeController extends BaseController
{
    @Autowired
    private IInteractionTypeService interactionTypeService;

    /**
     * 查询互动类型列表
     */
    @PreAuthorize("@ss.hasPermi('pUser:type:list')")
    @GetMapping("/list")
    public TableDataInfo list(InteractionType interactionType)
    {
        startPage();
        List<InteractionType> list = interactionTypeService.selectInteractionTypeList(interactionType);
        return getDataTable(list);
    }

    /**
     * 导出互动类型列表
     */
    @PreAuthorize("@ss.hasPermi('pUser:type:export')")
    @Log(title = "互动类型", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, InteractionType interactionType)
    {
        List<InteractionType> list = interactionTypeService.selectInteractionTypeList(interactionType);
        ExcelUtil<InteractionType> util = new ExcelUtil<InteractionType>(InteractionType.class);
        util.exportExcel(response, list, "互动类型数据");
    }

    /**
     * 获取互动类型详细信息
     */
    @PreAuthorize("@ss.hasPermi('pUser:type:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(interactionTypeService.selectInteractionTypeById(id));
    }

    /**
     * 新增互动类型
     */
    @PreAuthorize("@ss.hasPermi('pUser:type:add')")
    @Log(title = "互动类型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody InteractionType interactionType)
    {
        return toAjax(interactionTypeService.insertInteractionType(interactionType));
    }

    /**
     * 修改互动类型
     */
    @PreAuthorize("@ss.hasPermi('pUser:type:edit')")
    @Log(title = "互动类型", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody InteractionType interactionType)
    {
        return toAjax(interactionTypeService.updateInteractionType(interactionType));
    }

    /**
     * 删除互动类型
     */
    @PreAuthorize("@ss.hasPermi('pUser:type:remove')")
    @Log(title = "互动类型", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(interactionTypeService.deleteInteractionTypeByIds(ids));
    }
}
