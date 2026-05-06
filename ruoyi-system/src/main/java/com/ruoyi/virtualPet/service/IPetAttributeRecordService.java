package com.ruoyi.virtualPet.service;

import java.util.List;
import com.ruoyi.virtualPet.domain.PetAttributeRecord;

/**
 * 宠物属性记录Service接口
 * 
 * @author dzl
 * @date 2026-04-24
 */
public interface IPetAttributeRecordService 
{
    /**
     * 查询宠物属性记录
     * 
     * @param id 宠物属性记录主键
     * @return 宠物属性记录
     */
    public PetAttributeRecord selectPetAttributeRecordById(Long id);

    /**
     * 查询宠物属性记录列表
     * 
     * @param petAttributeRecord 宠物属性记录
     * @return 宠物属性记录集合
     */
    public List<PetAttributeRecord> selectPetAttributeRecordList(PetAttributeRecord petAttributeRecord);

    /**
     * 新增宠物属性记录
     * 
     * @param petAttributeRecord 宠物属性记录
     * @return 结果
     */
    public int insertPetAttributeRecord(PetAttributeRecord petAttributeRecord);

    /**
     * 修改宠物属性记录
     * 
     * @param petAttributeRecord 宠物属性记录
     * @return 结果
     */
    public int updatePetAttributeRecord(PetAttributeRecord petAttributeRecord);

    /**
     * 批量删除宠物属性记录
     * 
     * @param ids 需要删除的宠物属性记录主键集合
     * @return 结果
     */
    public int deletePetAttributeRecordByIds(Long[] ids);

    /**
     * 删除宠物属性记录信息
     * 
     * @param id 宠物属性记录主键
     * @return 结果
     */
    public int deletePetAttributeRecordById(Long id);
}
