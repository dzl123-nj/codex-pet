package com.ruoyi.virtualPet.service;

import java.util.List;
import com.ruoyi.virtualPet.domain.PetType;

/**
 * 宠物类型Service接口
 * 
 * @author dzl
 * @date 2026-04-24
 */
public interface IPetTypeService 
{
    /**
     * 查询宠物类型
     * 
     * @param id 宠物类型主键
     * @return 宠物类型
     */
    public PetType selectPetTypeById(Long id);

    /**
     * 查询宠物类型列表
     * 
     * @param petType 宠物类型
     * @return 宠物类型集合
     */
    public List<PetType> selectPetTypeList(PetType petType);

    /**
     * 新增宠物类型
     * 
     * @param petType 宠物类型
     * @return 结果
     */
    public int insertPetType(PetType petType);

    /**
     * 修改宠物类型
     * 
     * @param petType 宠物类型
     * @return 结果
     */
    public int updatePetType(PetType petType);

    /**
     * 批量删除宠物类型
     * 
     * @param ids 需要删除的宠物类型主键集合
     * @return 结果
     */
    public int deletePetTypeByIds(Long[] ids);

    /**
     * 删除宠物类型信息
     * 
     * @param id 宠物类型主键
     * @return 结果
     */
    public int deletePetTypeById(Long id);
}
