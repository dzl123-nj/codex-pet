package com.ruoyi.web.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.virtualPet.domain.PetActionRecord;
import com.ruoyi.virtualPet.service.IPetActionRecordService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 宠物动作记录Controller
 *
 * @author codex
 * @date 2026-05-27
 */
@RestController
@RequestMapping("/pet/actionRecord")
public class PetActionRecordController extends BaseController
{
    @Autowired
    private IPetActionRecordService petActionRecordService;

    /**
     * 查询宠物动作记录列表
     */
    @PreAuthorize("@ss.hasPermi('pet:actionRecord:list')")
    @GetMapping("/list")
    public TableDataInfo list(PetActionRecord petActionRecord)
    {
        startPage();
        List<PetActionRecord> list = petActionRecordService.selectPetActionRecordList(petActionRecord);
        return getDataTable(list);
    }

    /**
     * 导出宠物动作记录列表
     */
    @PreAuthorize("@ss.hasPermi('pet:actionRecord:export')")
    @Log(title = "宠物动作记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PetActionRecord petActionRecord)
    {
        List<PetActionRecord> list = petActionRecordService.selectPetActionRecordList(petActionRecord);
        ExcelUtil<PetActionRecord> util = new ExcelUtil<PetActionRecord>(PetActionRecord.class);
        util.exportExcel(response, list, "宠物动作记录数据");
    }

    /**
     * 获取宠物动作记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('pet:actionRecord:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(petActionRecordService.selectPetActionRecordById(id));
    }

    /**
     * 新增宠物动作记录
     */
    @PostMapping
    public AjaxResult add(@RequestBody PetActionRecord petActionRecord)
    {
        return toAjax(petActionRecordService.insertPetActionRecord(petActionRecord));
    }

    /**
     * 删除宠物动作记录
     */
    @PreAuthorize("@ss.hasPermi('pet:actionRecord:remove')")
    @Log(title = "宠物动作记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(petActionRecordService.deletePetActionRecordByIds(ids));
    }
}
