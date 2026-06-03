package com.ruoyi.virtualPet.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.virtualPet.mapper.PetSpriteConfigMapper;
import com.ruoyi.virtualPet.domain.PetSpriteConfig;
import com.ruoyi.virtualPet.service.IPetSpriteConfigService;

/**
 * 宠物精灵图表配置Service业务层处理
 *
 * @author dzl
 * @date 2026-06-02
 */
@Service
public class PetSpriteConfigServiceImpl implements IPetSpriteConfigService
{
    @Autowired
    private PetSpriteConfigMapper petSpriteConfigMapper;

    @Override
    public PetSpriteConfig selectPetSpriteConfigById(Long id)
    {
        return petSpriteConfigMapper.selectPetSpriteConfigById(id);
    }

    @Override
    public PetSpriteConfig selectByPetTypeId(Long petTypeId)
    {
        return petSpriteConfigMapper.selectByPetTypeId(petTypeId);
    }

    @Override
    public List<PetSpriteConfig> selectPetSpriteConfigList(PetSpriteConfig petSpriteConfig)
    {
        return petSpriteConfigMapper.selectPetSpriteConfigList(petSpriteConfig);
    }

    @Override
    public int insertPetSpriteConfig(PetSpriteConfig petSpriteConfig)
    {
        petSpriteConfig.setCreateTime(DateUtils.getNowDate());
        return petSpriteConfigMapper.insertPetSpriteConfig(petSpriteConfig);
    }

    @Override
    public int updatePetSpriteConfig(PetSpriteConfig petSpriteConfig)
    {
        petSpriteConfig.setUpdateTime(DateUtils.getNowDate());
        return petSpriteConfigMapper.updatePetSpriteConfig(petSpriteConfig);
    }

    @Override
    public int deletePetSpriteConfigByIds(Long[] ids)
    {
        return petSpriteConfigMapper.deletePetSpriteConfigByIds(ids);
    }

    @Override
    public int deletePetSpriteConfigById(Long id)
    {
        return petSpriteConfigMapper.deletePetSpriteConfigById(id);
    }
}
