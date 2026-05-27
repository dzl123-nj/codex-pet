package com.ruoyi.virtualPet.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.virtualPet.mapper.PetActionRecordMapper;
import com.ruoyi.virtualPet.domain.PetActionRecord;
import com.ruoyi.virtualPet.service.IPetActionRecordService;

/**
 * 宠物动作记录Service业务层处理
 *
 * @author codex
 * @date 2026-05-27
 */
@Service
public class PetActionRecordServiceImpl implements IPetActionRecordService
{
    @Autowired
    private PetActionRecordMapper petActionRecordMapper;

    @Override
    public PetActionRecord selectPetActionRecordById(Long id)
    {
        return petActionRecordMapper.selectPetActionRecordById(id);
    }

    @Override
    public List<PetActionRecord> selectPetActionRecordList(PetActionRecord petActionRecord)
    {
        return petActionRecordMapper.selectPetActionRecordList(petActionRecord);
    }

    @Override
    public int insertPetActionRecord(PetActionRecord petActionRecord)
    {
        petActionRecord.setCreateTime(DateUtils.getNowDate());
        if (petActionRecord.getActionTime() == null)
        {
            petActionRecord.setActionTime(DateUtils.getNowDate());
        }
        return petActionRecordMapper.insertPetActionRecord(petActionRecord);
    }

    @Override
    public int deletePetActionRecordByIds(Long[] ids)
    {
        return petActionRecordMapper.deletePetActionRecordByIds(ids);
    }

    @Override
    public int deletePetActionRecordById(Long id)
    {
        return petActionRecordMapper.deletePetActionRecordById(id);
    }
}
