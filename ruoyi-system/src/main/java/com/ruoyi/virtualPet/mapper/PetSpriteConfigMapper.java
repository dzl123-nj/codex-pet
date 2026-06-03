package com.ruoyi.virtualPet.mapper;

import java.util.List;
import com.ruoyi.virtualPet.domain.PetSpriteConfig;

/**
 * 宠物精灵图表配置Mapper接口
 *
 * @author dzl
 * @date 2026-06-02
 */
public interface PetSpriteConfigMapper
{
    public PetSpriteConfig selectPetSpriteConfigById(Long id);

    public PetSpriteConfig selectByPetTypeId(Long petTypeId);

    public List<PetSpriteConfig> selectPetSpriteConfigList(PetSpriteConfig petSpriteConfig);

    public int insertPetSpriteConfig(PetSpriteConfig petSpriteConfig);

    public int updatePetSpriteConfig(PetSpriteConfig petSpriteConfig);

    public int deletePetSpriteConfigById(Long id);

    public int deletePetSpriteConfigByIds(Long[] ids);
}
