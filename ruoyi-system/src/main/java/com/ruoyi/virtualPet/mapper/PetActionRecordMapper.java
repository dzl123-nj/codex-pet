package com.ruoyi.virtualPet.mapper;

import java.util.List;
import com.ruoyi.virtualPet.domain.PetActionRecord;

/**
 * 宠物动作记录Mapper接口
 *
 * @author codex
 * @date 2026-05-27
 */
public interface PetActionRecordMapper
{
    public PetActionRecord selectPetActionRecordById(Long id);

    public List<PetActionRecord> selectPetActionRecordList(PetActionRecord petActionRecord);

    public int insertPetActionRecord(PetActionRecord petActionRecord);

    public int deletePetActionRecordById(Long id);

    public int deletePetActionRecordByIds(Long[] ids);
}
