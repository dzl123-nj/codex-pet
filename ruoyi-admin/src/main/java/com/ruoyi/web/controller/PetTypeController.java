package com.ruoyi.web.controller;

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
import com.ruoyi.virtualPet.domain.PetType;
import com.ruoyi.virtualPet.service.IPetTypeService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 宠物类型Controller
 * 
 * @author dzl
 * @date 2026-04-24
 */
@RestController
@RequestMapping("/pet/type")
public class PetTypeController extends BaseController
{
    @Autowired
    private IPetTypeService petTypeService;

    /**
     * 查询宠物类型列表
     */
    @PreAuthorize("@ss.hasPermi('pet:type:list')")
    @GetMapping("/list")
    public TableDataInfo list(PetType petType)
    {
        startPage();
        List<PetType> list = petTypeService.selectPetTypeList(petType);
        return getDataTable(list);
    }

    /**
     * 获取所有宠物类型（下拉列表用，无需权限）
     */
    @GetMapping("/listAll")
    public AjaxResult listAll()
    {
        List<PetType> list = petTypeService.selectPetTypeList(new PetType());
        return success(list);
    }

    /**
     * 导出宠物类型列表
     */
    @PreAuthorize("@ss.hasPermi('pet:type:export')")
    @Log(title = "宠物类型", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PetType petType)
    {
        List<PetType> list = petTypeService.selectPetTypeList(petType);
        ExcelUtil<PetType> util = new ExcelUtil<PetType>(PetType.class);
        util.exportExcel(response, list, "宠物类型数据");
    }

    /**
     * 获取宠物类型详细信息
     */
    @PreAuthorize("@ss.hasPermi('pet:type:query')")
    @GetMapping(value = "/{id:\\d+}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(petTypeService.selectPetTypeById(id));
    }

    /**
     * 新增宠物类型
     */
    @PreAuthorize("@ss.hasPermi('pet:type:add')")
    @Log(title = "宠物类型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PetType petType)
    {
        return toAjax(petTypeService.insertPetType(petType));
    }

    /**
     * 修改宠物类型
     */
    @PreAuthorize("@ss.hasPermi('pet:type:edit')")
    @Log(title = "宠物类型", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PetType petType)
    {
        return toAjax(petTypeService.updatePetType(petType));
    }

    /**
     * 删除宠物类型
     */
    @PreAuthorize("@ss.hasPermi('pet:type:remove')")
    @Log(title = "宠物类型", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(petTypeService.deletePetTypeByIds(ids));
    }
}
