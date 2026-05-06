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
import com.ruoyi.virtualPet.domain.VirtualPet;
import com.ruoyi.virtualPet.service.IVirtualPetService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 虚拟宠物Controller
 * 
 * @author dzl
 * @date 2026-04-24
 */
@RestController
@RequestMapping("/pet/pname")
public class VirtualPetController extends BaseController
{
    @Autowired
    private IVirtualPetService virtualPetService;

    /**
     * 查询虚拟宠物列表
     */
    @PreAuthorize("@ss.hasPermi('pet:pname:list')")
    @GetMapping("/list")
    public TableDataInfo list(VirtualPet virtualPet)
    {
        startPage();
        List<VirtualPet> list = virtualPetService.selectVirtualPetList(virtualPet);
        return getDataTable(list);
    }

    /**
     * 导出虚拟宠物列表
     */
    @PreAuthorize("@ss.hasPermi('pet:pname:export')")
    @Log(title = "虚拟宠物", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, VirtualPet virtualPet)
    {
        List<VirtualPet> list = virtualPetService.selectVirtualPetList(virtualPet);
        ExcelUtil<VirtualPet> util = new ExcelUtil<VirtualPet>(VirtualPet.class);
        util.exportExcel(response, list, "虚拟宠物数据");
    }

    /**
     * 获取虚拟宠物详细信息
     */
    @PreAuthorize("@ss.hasPermi('pet:pname:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(virtualPetService.selectVirtualPetById(id));
    }

    /**
     * 新增虚拟宠物
     */
    @PreAuthorize("@ss.hasPermi('pet:pname:add')")
    @Log(title = "虚拟宠物", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody VirtualPet virtualPet)
    {
        return toAjax(virtualPetService.insertVirtualPet(virtualPet));
    }

    /**
     * 修改虚拟宠物
     */
    @PreAuthorize("@ss.hasPermi('pet:pname:edit')")
    @Log(title = "虚拟宠物", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody VirtualPet virtualPet)
    {
        return toAjax(virtualPetService.updateVirtualPet(virtualPet));
    }

    /**
     * 删除虚拟宠物
     */
    @PreAuthorize("@ss.hasPermi('pet:pname:remove')")
    @Log(title = "虚拟宠物", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(virtualPetService.deleteVirtualPetByIds(ids));
    }
}
