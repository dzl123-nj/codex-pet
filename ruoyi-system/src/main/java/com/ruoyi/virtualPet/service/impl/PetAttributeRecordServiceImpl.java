package com.ruoyi.virtualPet.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.virtualPet.mapper.PetAttributeRecordMapper;
import com.ruoyi.virtualPet.domain.PetAttributeRecord;
import com.ruoyi.virtualPet.service.IPetAttributeRecordService;

/**
 * 宠物属性记录Service业务层处理
 * 
 * @author dzl
 * @date 2026-04-24
 */
@Service
public class PetAttributeRecordServiceImpl implements IPetAttributeRecordService 
{
    @Autowired
    private PetAttributeRecordMapper petAttributeRecordMapper;

    /**
     * 查询宠物属性记录
     * 
     * @param id 宠物属性记录主键
     * @return 宠物属性记录
     */
    @Override
    public PetAttributeRecord selectPetAttributeRecordById(Long id)
    {
        return petAttributeRecordMapper.selectPetAttributeRecordById(id);
    }

    /**
     * 查询宠物属性记录列表
     * 
     * @param petAttributeRecord 宠物属性记录
     * @return 宠物属性记录
     */
    @Override
    public List<PetAttributeRecord> selectPetAttributeRecordList(PetAttributeRecord petAttributeRecord)
    {
        return petAttributeRecordMapper.selectPetAttributeRecordList(petAttributeRecord);
    }

    /**
     * 新增宠物属性记录
     * 
     * @param petAttributeRecord 宠物属性记录
     * @return 结果
     */
    @Override
    public int insertPetAttributeRecord(PetAttributeRecord petAttributeRecord)
    {
        petAttributeRecord.setCreateTime(DateUtils.getNowDate());
        return petAttributeRecordMapper.insertPetAttributeRecord(petAttributeRecord);
    }

    /**
     * 修改宠物属性记录
     * 
     * @param petAttributeRecord 宠物属性记录
     * @return 结果
     */
    @Override
    public int updatePetAttributeRecord(PetAttributeRecord petAttributeRecord)
    {
        return petAttributeRecordMapper.updatePetAttributeRecord(petAttributeRecord);
    }

    /**
     * 批量删除宠物属性记录
     * 
     * @param ids 需要删除的宠物属性记录主键
     * @return 结果
     */
    @Override
    public int deletePetAttributeRecordByIds(Long[] ids)
    {
        return petAttributeRecordMapper.deletePetAttributeRecordByIds(ids);
    }

    /**
     * 删除宠物属性记录信息
     * 
     * @param id 宠物属性记录主键
     * @return 结果
     */
    @Override
    public int deletePetAttributeRecordById(Long id)
    {
        return petAttributeRecordMapper.deletePetAttributeRecordById(id);
    }
}
