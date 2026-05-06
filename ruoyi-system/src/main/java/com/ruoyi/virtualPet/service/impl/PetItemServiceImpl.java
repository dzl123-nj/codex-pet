package com.ruoyi.virtualPet.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.virtualPet.mapper.PetItemMapper;
import com.ruoyi.virtualPet.domain.PetItem;
import com.ruoyi.virtualPet.service.IPetItemService;

/**
 * 宠物道具Service业务层处理
 * 
 * @author dzl
 * @date 2026-04-24
 */
@Service
public class PetItemServiceImpl implements IPetItemService 
{
    @Autowired
    private PetItemMapper petItemMapper;

    /**
     * 查询宠物道具
     * 
     * @param id 宠物道具主键
     * @return 宠物道具
     */
    @Override
    public PetItem selectPetItemById(Long id)
    {
        return petItemMapper.selectPetItemById(id);
    }

    /**
     * 查询宠物道具列表
     * 
     * @param petItem 宠物道具
     * @return 宠物道具
     */
    @Override
    public List<PetItem> selectPetItemList(PetItem petItem)
    {
        return petItemMapper.selectPetItemList(petItem);
    }

    /**
     * 新增宠物道具
     * 
     * @param petItem 宠物道具
     * @return 结果
     */
    @Override
    public int insertPetItem(PetItem petItem)
    {
        petItem.setCreateTime(DateUtils.getNowDate());
        return petItemMapper.insertPetItem(petItem);
    }

    /**
     * 修改宠物道具
     * 
     * @param petItem 宠物道具
     * @return 结果
     */
    @Override
    public int updatePetItem(PetItem petItem)
    {
        petItem.setUpdateTime(DateUtils.getNowDate());
        return petItemMapper.updatePetItem(petItem);
    }

    /**
     * 批量删除宠物道具
     * 
     * @param ids 需要删除的宠物道具主键
     * @return 结果
     */
    @Override
    public int deletePetItemByIds(Long[] ids)
    {
        return petItemMapper.deletePetItemByIds(ids);
    }

    /**
     * 删除宠物道具信息
     * 
     * @param id 宠物道具主键
     * @return 结果
     */
    @Override
    public int deletePetItemById(Long id)
    {
        return petItemMapper.deletePetItemById(id);
    }
}
