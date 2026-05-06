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
import com.ruoyi.player.domain.ItemUsageRecord;
import com.ruoyi.player.service.IItemUsageRecordService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 道具使用记录Controller
 * 
 * @author dzl
 * @date 2026-04-24
 */
@RestController
@RequestMapping("/pUser/record")
public class ItemUsageRecordController extends BaseController
{
    @Autowired
    private IItemUsageRecordService itemUsageRecordService;

    /**
     * 查询道具使用记录列表
     */
    @PreAuthorize("@ss.hasPermi('pUser:record:list')")
    @GetMapping("/list")
    public TableDataInfo list(ItemUsageRecord itemUsageRecord)
    {
        startPage();
        List<ItemUsageRecord> list = itemUsageRecordService.selectItemUsageRecordList(itemUsageRecord);
        return getDataTable(list);
    }

    /**
     * 导出道具使用记录列表
     */
    @PreAuthorize("@ss.hasPermi('pUser:record:export')")
    @Log(title = "道具使用记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ItemUsageRecord itemUsageRecord)
    {
        List<ItemUsageRecord> list = itemUsageRecordService.selectItemUsageRecordList(itemUsageRecord);
        ExcelUtil<ItemUsageRecord> util = new ExcelUtil<ItemUsageRecord>(ItemUsageRecord.class);
        util.exportExcel(response, list, "道具使用记录数据");
    }

    /**
     * 获取道具使用记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('pUser:record:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(itemUsageRecordService.selectItemUsageRecordById(id));
    }

    /**
     * 新增道具使用记录
     */
    @PreAuthorize("@ss.hasPermi('pUser:record:add')")
    @Log(title = "道具使用记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ItemUsageRecord itemUsageRecord)
    {
        return toAjax(itemUsageRecordService.insertItemUsageRecord(itemUsageRecord));
    }

    /**
     * 修改道具使用记录
     */
    @PreAuthorize("@ss.hasPermi('pUser:record:edit')")
    @Log(title = "道具使用记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ItemUsageRecord itemUsageRecord)
    {
        return toAjax(itemUsageRecordService.updateItemUsageRecord(itemUsageRecord));
    }

    /**
     * 删除道具使用记录
     */
    @PreAuthorize("@ss.hasPermi('pUser:record:remove')")
    @Log(title = "道具使用记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(itemUsageRecordService.deleteItemUsageRecordByIds(ids));
    }
}
