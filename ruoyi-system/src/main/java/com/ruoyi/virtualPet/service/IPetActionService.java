package com.ruoyi.virtualPet.service;

import java.util.List;
import com.ruoyi.virtualPet.domain.PetAction;

/**
 * 宠物动作定义Service接口
 *
 * @author codex
 * @date 2026-05-27
 */
public interface IPetActionService
{
    public PetAction selectPetActionById(Long id);

    public PetAction selectPetActionByCode(String actionCode);

    public List<PetAction> selectPetActionList(PetAction petAction);

    public List<PetAction> selectEnabledPetActionList();

    public int insertPetAction(PetAction petAction);

    public int updatePetAction(PetAction petAction);

    public int deletePetActionByIds(Long[] ids);

    public int deletePetActionById(Long id);
}
