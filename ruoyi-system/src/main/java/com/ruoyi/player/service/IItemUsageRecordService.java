package com.ruoyi.player.service;

import java.util.List;
import com.ruoyi.player.domain.ItemUsageRecord;

/**
 * 道具使用记录Service接口
 * 
 * @author dzl
 * @date 2026-04-24
 */
public interface IItemUsageRecordService 
{
    /**
     * 查询道具使用记录
     * 
     * @param id 道具使用记录主键
     * @return 道具使用记录
     */
    public ItemUsageRecord selectItemUsageRecordById(Long id);

    /**
     * 查询道具使用记录列表
     * 
     * @param itemUsageRecord 道具使用记录
     * @return 道具使用记录集合
     */
    public List<ItemUsageRecord> selectItemUsageRecordList(ItemUsageRecord itemUsageRecord);

    /**
     * 新增道具使用记录
     * 
     * @param itemUsageRecord 道具使用记录
     * @return 结果
     */
    public int insertItemUsageRecord(ItemUsageRecord itemUsageRecord);

    /**
     * 修改道具使用记录
     * 
     * @param itemUsageRecord 道具使用记录
     * @return 结果
     */
    public int updateItemUsageRecord(ItemUsageRecord itemUsageRecord);

    /**
     * 批量删除道具使用记录
     * 
     * @param ids 需要删除的道具使用记录主键集合
     * @return 结果
     */
    public int deleteItemUsageRecordByIds(Long[] ids);

    /**
     * 删除道具使用记录信息
     * 
     * @param id 道具使用记录主键
     * @return 结果
     */
    public int deleteItemUsageRecordById(Long id);
}
