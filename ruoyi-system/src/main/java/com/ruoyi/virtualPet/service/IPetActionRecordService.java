package com.ruoyi.virtualPet.service;

import java.util.List;
import com.ruoyi.virtualPet.domain.PetActionRecord;

/**
 * 宠物动作记录Service接口
 *
 * @author codex
 * @date 2026-05-27
 */
public interface IPetActionRecordService
{
    public PetActionRecord selectPetActionRecordById(Long id);

    public List<PetActionRecord> selectPetActionRecordList(PetActionRecord petActionRecord);

    public int insertPetActionRecord(PetActionRecord petActionRecord);

    public int deletePetActionRecordByIds(Long[] ids);

    public int deletePetActionRecordById(Long id);
}
