package com.ruoyi.virtualPet.service;

import java.util.List;
import com.ruoyi.virtualPet.domain.VirtualPet;

/**
 * 虚拟宠物Service接口
 * 
 * @author dzl
 * @date 2026-04-24
 */
public interface IVirtualPetService 
{
    /**
     * 查询虚拟宠物
     * 
     * @param id 虚拟宠物主键
     * @return 虚拟宠物
     */
    public VirtualPet selectVirtualPetById(Long id);

    /**
     * 查询虚拟宠物列表
     * 
     * @param virtualPet 虚拟宠物
     * @return 虚拟宠物集合
     */
    public List<VirtualPet> selectVirtualPetList(VirtualPet virtualPet);

    /**
     * 新增虚拟宠物
     * 
     * @param virtualPet 虚拟宠物
     * @return 结果
     */
    public int insertVirtualPet(VirtualPet virtualPet);

    /**
     * 修改虚拟宠物
     * 
     * @param virtualPet 虚拟宠物
     * @return 结果
     */
    public int updateVirtualPet(VirtualPet virtualPet);

    /**
     * 批量删除虚拟宠物
     * 
     * @param ids 需要删除的虚拟宠物主键集合
     * @return 结果
     */
    public int deleteVirtualPetByIds(Long[] ids);

    /**
     * 删除虚拟宠物信息
     * 
     * @param id 虚拟宠物主键
     * @return 结果
     */
    public int deleteVirtualPetById(Long id);
}
