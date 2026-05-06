package com.ruoyi.virtualPet.mapper;

import java.util.List;
import com.ruoyi.virtualPet.domain.PetItem;

/**
 * 宠物道具Mapper接口
 * 
 * @author dzl
 * @date 2026-04-24
 */
public interface PetItemMapper 
{
    /**
     * 查询宠物道具
     * 
     * @param id 宠物道具主键
     * @return 宠物道具
     */
    public PetItem selectPetItemById(Long id);

    /**
     * 查询宠物道具列表
     * 
     * @param petItem 宠物道具
     * @return 宠物道具集合
     */
    public List<PetItem> selectPetItemList(PetItem petItem);

    /**
     * 新增宠物道具
     * 
     * @param petItem 宠物道具
     * @return 结果
     */
    public int insertPetItem(PetItem petItem);

    /**
     * 修改宠物道具
     * 
     * @param petItem 宠物道具
     * @return 结果
     */
    public int updatePetItem(PetItem petItem);

    /**
     * 删除宠物道具
     * 
     * @param id 宠物道具主键
     * @return 结果
     */
    public int deletePetItemById(Long id);

    /**
     * 批量删除宠物道具
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePetItemByIds(Long[] ids);
}
