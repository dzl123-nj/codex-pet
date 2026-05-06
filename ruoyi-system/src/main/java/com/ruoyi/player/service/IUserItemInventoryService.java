package com.ruoyi.player.service;

import java.util.List;
import com.ruoyi.player.domain.UserItemInventory;

/**
 * 用户道具库存Service接口
 * 
 * @author dzl
 * @date 2026-04-24
 */
public interface IUserItemInventoryService 
{
    /**
     * 查询用户道具库存
     * 
     * @param id 用户道具库存主键
     * @return 用户道具库存
     */
    public UserItemInventory selectUserItemInventoryById(Long id);

    /**
     * 查询用户道具库存列表
     * 
     * @param userItemInventory 用户道具库存
     * @return 用户道具库存集合
     */
    public List<UserItemInventory> selectUserItemInventoryList(UserItemInventory userItemInventory);

    /**
     * 新增用户道具库存
     * 
     * @param userItemInventory 用户道具库存
     * @return 结果
     */
    public int insertUserItemInventory(UserItemInventory userItemInventory);

    /**
     * 修改用户道具库存
     * 
     * @param userItemInventory 用户道具库存
     * @return 结果
     */
    public int updateUserItemInventory(UserItemInventory userItemInventory);

    /**
     * 批量删除用户道具库存
     * 
     * @param ids 需要删除的用户道具库存主键集合
     * @return 结果
     */
    public int deleteUserItemInventoryByIds(Long[] ids);

    /**
     * 删除用户道具库存信息
     * 
     * @param id 用户道具库存主键
     * @return 结果
     */
    public int deleteUserItemInventoryById(Long id);
}
