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
import com.ruoyi.virtualPet.domain.PetItem;
import com.ruoyi.virtualPet.service.IPetItemService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 宠物道具Controller
 * 
 * @author dzl
 * @date 2026-04-24
 */
@RestController
@RequestMapping("/pet/item")
public class PetItemController extends BaseController
{
    @Autowired
    private IPetItemService petItemService;

    /**
     * 查询宠物道具列表
     */
    @PreAuthorize("@ss.hasPermi('pet:item:list')")
    @GetMapping("/list")
    public TableDataInfo list(PetItem petItem)
    {
        startPage();
        List<PetItem> list = petItemService.selectPetItemList(petItem);
        return getDataTable(list);
    }

    /**
     * 导出宠物道具列表
     */
    @PreAuthorize("@ss.hasPermi('pet:item:export')")
    @Log(title = "宠物道具", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PetItem petItem)
    {
        List<PetItem> list = petItemService.selectPetItemList(petItem);
        ExcelUtil<PetItem> util = new ExcelUtil<PetItem>(PetItem.class);
        util.exportExcel(response, list, "宠物道具数据");
    }

    /**
     * 获取宠物道具详细信息
     */
    @PreAuthorize("@ss.hasPermi('pet:item:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(petItemService.selectPetItemById(id));
    }

    /**
     * 新增宠物道具
     */
    @PreAuthorize("@ss.hasPermi('pet:item:add')")
    @Log(title = "宠物道具", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PetItem petItem)
    {
        return toAjax(petItemService.insertPetItem(petItem));
    }

    /**
     * 修改宠物道具
     */
    @PreAuthorize("@ss.hasPermi('pet:item:edit')")
    @Log(title = "宠物道具", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PetItem petItem)
    {
        return toAjax(petItemService.updatePetItem(petItem));
    }

    /**
     * 删除宠物道具
     */
    @PreAuthorize("@ss.hasPermi('pet:item:remove')")
    @Log(title = "宠物道具", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(petItemService.deletePetItemByIds(ids));
    }
}
