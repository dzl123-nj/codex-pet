package com.ruoyi.virtualPet.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.virtualPet.mapper.PetActionMapper;
import com.ruoyi.virtualPet.domain.PetAction;
import com.ruoyi.virtualPet.service.IPetActionService;

/**
 * 宠物动作定义Service业务层处理
 *
 * @author codex
 * @date 2026-05-27
 */
@Service
public class PetActionServiceImpl implements IPetActionService
{
    @Autowired
    private PetActionMapper petActionMapper;

    @Override
    public PetAction selectPetActionById(Long id)
    {
        return petActionMapper.selectPetActionById(id);
    }

    @Override
    public PetAction selectPetActionByCode(String actionCode)
    {
        return petActionMapper.selectPetActionByCode(actionCode);
    }

    @Override
    public List<PetAction> selectPetActionList(PetAction petAction)
    {
        return petActionMapper.selectPetActionList(petAction);
    }

    @Override
    public List<PetAction> selectEnabledPetActionList()
    {
        return petActionMapper.selectEnabledPetActionList();
    }

    @Override
    public int insertPetAction(PetAction petAction)
    {
        petAction.setCreateTime(DateUtils.getNowDate());
        if (petAction.getEnabled() == null)
        {
            petAction.setEnabled(1L);
        }
        if (petAction.getDeleted() == null)
        {
            petAction.setDeleted(0L);
        }
        return petActionMapper.insertPetAction(petAction);
    }

    @Override
    public int updatePetAction(PetAction petAction)
    {
        petAction.setUpdateTime(DateUtils.getNowDate());
        return petActionMapper.updatePetAction(petAction);
    }

    @Override
    public int deletePetActionByIds(Long[] ids)
    {
        return petActionMapper.deletePetActionByIds(ids);
    }

    @Override
    public int deletePetActionById(Long id)
    {
        return petActionMapper.deletePetActionById(id);
    }
}
