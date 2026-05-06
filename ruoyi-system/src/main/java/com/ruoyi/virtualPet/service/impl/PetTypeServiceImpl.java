package com.ruoyi.virtualPet.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.virtualPet.mapper.PetTypeMapper;
import com.ruoyi.virtualPet.domain.PetType;
import com.ruoyi.virtualPet.service.IPetTypeService;

/**
 * 宠物类型Service业务层处理
 * 
 * @author dzl
 * @date 2026-04-24
 */
@Service
public class PetTypeServiceImpl implements IPetTypeService 
{
    @Autowired
    private PetTypeMapper petTypeMapper;

    /**
     * 查询宠物类型
     * 
     * @param id 宠物类型主键
     * @return 宠物类型
     */
    @Override
    public PetType selectPetTypeById(Long id)
    {
        return petTypeMapper.selectPetTypeById(id);
    }

    /**
     * 查询宠物类型列表
     * 
     * @param petType 宠物类型
     * @return 宠物类型
     */
    @Override
    public List<PetType> selectPetTypeList(PetType petType)
    {
        return petTypeMapper.selectPetTypeList(petType);
    }

    /**
     * 新增宠物类型
     * 
     * @param petType 宠物类型
     * @return 结果
     */
    @Override
    public int insertPetType(PetType petType)
    {
        petType.setCreateTime(DateUtils.getNowDate());
        return petTypeMapper.insertPetType(petType);
    }

    /**
     * 修改宠物类型
     * 
     * @param petType 宠物类型
     * @return 结果
     */
    @Override
    public int updatePetType(PetType petType)
    {
        petType.setUpdateTime(DateUtils.getNowDate());
        return petTypeMapper.updatePetType(petType);
    }

    /**
     * 批量删除宠物类型
     * 
     * @param ids 需要删除的宠物类型主键
     * @return 结果
     */
    @Override
    public int deletePetTypeByIds(Long[] ids)
    {
        return petTypeMapper.deletePetTypeByIds(ids);
    }

    /**
     * 删除宠物类型信息
     * 
     * @param id 宠物类型主键
     * @return 结果
     */
    @Override
    public int deletePetTypeById(Long id)
    {
        return petTypeMapper.deletePetTypeById(id);
    }
}
