package com.ruoyi.player.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.player.mapper.UserItemInventoryMapper;
import com.ruoyi.player.domain.UserItemInventory;
import com.ruoyi.player.service.IUserItemInventoryService;

/**
 * 用户道具库存Service业务层处理
 * 
 * @author dzl
 * @date 2026-04-24
 */
@Service
public class UserItemInventoryServiceImpl implements IUserItemInventoryService 
{
    @Autowired
    private UserItemInventoryMapper userItemInventoryMapper;

    /**
     * 查询用户道具库存
     * 
     * @param id 用户道具库存主键
     * @return 用户道具库存
     */
    @Override
    public UserItemInventory selectUserItemInventoryById(Long id)
    {
        return userItemInventoryMapper.selectUserItemInventoryById(id);
    }

    /**
     * 查询用户道具库存列表
     * 
     * @param userItemInventory 用户道具库存
     * @return 用户道具库存
     */
    @Override
    public List<UserItemInventory> selectUserItemInventoryList(UserItemInventory userItemInventory)
    {
        return userItemInventoryMapper.selectUserItemInventoryList(userItemInventory);
    }

    /**
     * 新增用户道具库存
     * 
     * @param userItemInventory 用户道具库存
     * @return 结果
     */
    @Override
    public int insertUserItemInventory(UserItemInventory userItemInventory)
    {
        userItemInventory.setCreateTime(DateUtils.getNowDate());
        return userItemInventoryMapper.insertUserItemInventory(userItemInventory);
    }

    /**
     * 修改用户道具库存
     * 
     * @param userItemInventory 用户道具库存
     * @return 结果
     */
    @Override
    public int updateUserItemInventory(UserItemInventory userItemInventory)
    {
        userItemInventory.setUpdateTime(DateUtils.getNowDate());
        return userItemInventoryMapper.updateUserItemInventory(userItemInventory);
    }

    /**
     * 批量删除用户道具库存
     * 
     * @param ids 需要删除的用户道具库存主键
     * @return 结果
     */
    @Override
    public int deleteUserItemInventoryByIds(Long[] ids)
    {
        return userItemInventoryMapper.deleteUserItemInventoryByIds(ids);
    }

    /**
     * 删除用户道具库存信息
     * 
     * @param id 用户道具库存主键
     * @return 结果
     */
    @Override
    public int deleteUserItemInventoryById(Long id)
    {
        return userItemInventoryMapper.deleteUserItemInventoryById(id);
    }

    /**
     * 查询用户仓库（关联道具详情）
     * 
     * @param userId 用户ID
     * @return 用户道具库存集合（含道具详情）
     */
    @Override
    public List<UserItemInventory> selectMyWarehouse(Long userId)
    {
        return userItemInventoryMapper.selectMyWarehouse(userId);
    }
}
