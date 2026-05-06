package com.ruoyi.player.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.player.mapper.ItemUsageRecordMapper;
import com.ruoyi.player.domain.ItemUsageRecord;
import com.ruoyi.player.service.IItemUsageRecordService;

/**
 * 道具使用记录Service业务层处理
 * 
 * @author dzl
 * @date 2026-04-24
 */
@Service
public class ItemUsageRecordServiceImpl implements IItemUsageRecordService 
{
    @Autowired
    private ItemUsageRecordMapper itemUsageRecordMapper;

    /**
     * 查询道具使用记录
     * 
     * @param id 道具使用记录主键
     * @return 道具使用记录
     */
    @Override
    public ItemUsageRecord selectItemUsageRecordById(Long id)
    {
        return itemUsageRecordMapper.selectItemUsageRecordById(id);
    }

    /**
     * 查询道具使用记录列表
     * 
     * @param itemUsageRecord 道具使用记录
     * @return 道具使用记录
     */
    @Override
    public List<ItemUsageRecord> selectItemUsageRecordList(ItemUsageRecord itemUsageRecord)
    {
        return itemUsageRecordMapper.selectItemUsageRecordList(itemUsageRecord);
    }

    /**
     * 新增道具使用记录
     * 
     * @param itemUsageRecord 道具使用记录
     * @return 结果
     */
    @Override
    public int insertItemUsageRecord(ItemUsageRecord itemUsageRecord)
    {
        itemUsageRecord.setCreateTime(DateUtils.getNowDate());
        return itemUsageRecordMapper.insertItemUsageRecord(itemUsageRecord);
    }

    /**
     * 修改道具使用记录
     * 
     * @param itemUsageRecord 道具使用记录
     * @return 结果
     */
    @Override
    public int updateItemUsageRecord(ItemUsageRecord itemUsageRecord)
    {
        return itemUsageRecordMapper.updateItemUsageRecord(itemUsageRecord);
    }

    /**
     * 批量删除道具使用记录
     * 
     * @param ids 需要删除的道具使用记录主键
     * @return 结果
     */
    @Override
    public int deleteItemUsageRecordByIds(Long[] ids)
    {
        return itemUsageRecordMapper.deleteItemUsageRecordByIds(ids);
    }

    /**
     * 删除道具使用记录信息
     * 
     * @param id 道具使用记录主键
     * @return 结果
     */
    @Override
    public int deleteItemUsageRecordById(Long id)
    {
        return itemUsageRecordMapper.deleteItemUsageRecordById(id);
    }
}
