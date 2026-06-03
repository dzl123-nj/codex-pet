package com.ruoyi.virtualPet.service;

import java.util.List;
import com.ruoyi.virtualPet.domain.PetSpriteConfig;

/**
 * 宠物精灵图表配置Service接口
 *
 * @author dzl
 * @date 2026-06-02
 */
public interface IPetSpriteConfigService
{
    public PetSpriteConfig selectPetSpriteConfigById(Long id);

    public PetSpriteConfig selectByPetTypeId(Long petTypeId);

    public List<PetSpriteConfig> selectPetSpriteConfigList(PetSpriteConfig petSpriteConfig);

    public int insertPetSpriteConfig(PetSpriteConfig petSpriteConfig);

    public int updatePetSpriteConfig(PetSpriteConfig petSpriteConfig);

    public int deletePetSpriteConfigByIds(Long[] ids);

    public int deletePetSpriteConfigById(Long id);
}
