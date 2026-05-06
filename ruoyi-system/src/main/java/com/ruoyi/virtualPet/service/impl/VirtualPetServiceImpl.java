package com.ruoyi.virtualPet.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.virtualPet.mapper.VirtualPetMapper;
import com.ruoyi.virtualPet.domain.VirtualPet;
import com.ruoyi.virtualPet.service.IVirtualPetService;

/**
 * 虚拟宠物Service业务层处理
 * 
 * @author dzl
 * @date 2026-04-24
 */
@Service
public class VirtualPetServiceImpl implements IVirtualPetService 
{
    @Autowired
    private VirtualPetMapper virtualPetMapper;

    /**
     * 查询虚拟宠物
     * 
     * @param id 虚拟宠物主键
     * @return 虚拟宠物
     */
    @Override
    public VirtualPet selectVirtualPetById(Long id)
    {
        return virtualPetMapper.selectVirtualPetById(id);
    }

    /**
     * 查询虚拟宠物列表
     * 
     * @param virtualPet 虚拟宠物
     * @return 虚拟宠物
     */
    @Override
    public List<VirtualPet> selectVirtualPetList(VirtualPet virtualPet)
    {
        return virtualPetMapper.selectVirtualPetList(virtualPet);
    }

    /**
     * 新增虚拟宠物
     * 
     * @param virtualPet 虚拟宠物
     * @return 结果
     */
    @Override
    public int insertVirtualPet(VirtualPet virtualPet)
    {
        virtualPet.setCreateTime(DateUtils.getNowDate());
        return virtualPetMapper.insertVirtualPet(virtualPet);
    }

    /**
     * 修改虚拟宠物
     * 
     * @param virtualPet 虚拟宠物
     * @return 结果
     */
    @Override
    public int updateVirtualPet(VirtualPet virtualPet)
    {
        virtualPet.setUpdateTime(DateUtils.getNowDate());
        return virtualPetMapper.updateVirtualPet(virtualPet);
    }

    /**
     * 批量删除虚拟宠物
     * 
     * @param ids 需要删除的虚拟宠物主键
     * @return 结果
     */
    @Override
    public int deleteVirtualPetByIds(Long[] ids)
    {
        return virtualPetMapper.deleteVirtualPetByIds(ids);
    }

    /**
     * 删除虚拟宠物信息
     * 
     * @param id 虚拟宠物主键
     * @return 结果
     */
    @Override
    public int deleteVirtualPetById(Long id)
    {
        return virtualPetMapper.deleteVirtualPetById(id);
    }
}
