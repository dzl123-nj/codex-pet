package com.ruoyi.virtualPet.controller;

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
import com.ruoyi.virtualPet.domain.PetAttributeRecord;
import com.ruoyi.virtualPet.service.IPetAttributeRecordService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 宠物属性记录Controller
 * 
 * @author dzl
 * @date 2026-04-24
 */
@RestController
@RequestMapping("/pet/precord")
public class PetAttributeRecordController extends BaseController
{
    @Autowired
    private IPetAttributeRecordService petAttributeRecordService;

    /**
     * 查询宠物属性记录列表
     */
    @PreAuthorize("@ss.hasPermi('pet:precord:list')")
    @GetMapping("/list")
    public TableDataInfo list(PetAttributeRecord petAttributeRecord)
    {
        startPage();
        List<PetAttributeRecord> list = petAttributeRecordService.selectPetAttributeRecordList(petAttributeRecord);
        return getDataTable(list);
    }

    /**
     * 导出宠物属性记录列表
     */
    @PreAuthorize("@ss.hasPermi('pet:precord:export')")
    @Log(title = "宠物属性记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PetAttributeRecord petAttributeRecord)
    {
        List<PetAttributeRecord> list = petAttributeRecordService.selectPetAttributeRecordList(petAttributeRecord);
        ExcelUtil<PetAttributeRecord> util = new ExcelUtil<PetAttributeRecord>(PetAttributeRecord.class);
        util.exportExcel(response, list, "宠物属性记录数据");
    }

    /**
     * 获取宠物属性记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('pet:precord:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(petAttributeRecordService.selectPetAttributeRecordById(id));
    }

    /**
     * 新增宠物属性记录
     */
    @PreAuthorize("@ss.hasPermi('pet:precord:add')")
    @Log(title = "宠物属性记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PetAttributeRecord petAttributeRecord)
    {
        return toAjax(petAttributeRecordService.insertPetAttributeRecord(petAttributeRecord));
    }

    /**
     * 修改宠物属性记录
     */
    @PreAuthorize("@ss.hasPermi('pet:precord:edit')")
    @Log(title = "宠物属性记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PetAttributeRecord petAttributeRecord)
    {
        return toAjax(petAttributeRecordService.updatePetAttributeRecord(petAttributeRecord));
    }

    /**
     * 删除宠物属性记录
     */
    @PreAuthorize("@ss.hasPermi('pet:precord:remove')")
    @Log(title = "宠物属性记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(petAttributeRecordService.deletePetAttributeRecordByIds(ids));
    }
}
