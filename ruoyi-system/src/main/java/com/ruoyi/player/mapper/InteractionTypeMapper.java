package com.ruoyi.player.mapper;

import java.util.List;
import com.ruoyi.player.domain.InteractionType;

/**
 * 互动类型Mapper接口
 * 
 * @author dzl
 * @date 2026-04-22
 */
public interface InteractionTypeMapper 
{
    /**
     * 查询互动类型
     * 
     * @param id 互动类型主键
     * @return 互动类型
     */
    public InteractionType selectInteractionTypeById(Long id);

    /**
     * 查询互动类型列表
     * 
     * @param interactionType 互动类型
     * @return 互动类型集合
     */
    public List<InteractionType> selectInteractionTypeList(InteractionType interactionType);

    /**
     * 新增互动类型
     * 
     * @param interactionType 互动类型
     * @return 结果
     */
    public int insertInteractionType(InteractionType interactionType);

    /**
     * 修改互动类型
     * 
     * @param interactionType 互动类型
     * @return 结果
     */
    public int updateInteractionType(InteractionType interactionType);

    /**
     * 删除互动类型
     * 
     * @param id 互动类型主键
     * @return 结果
     */
    public int deleteInteractionTypeById(Long id);

    /**
     * 批量删除互动类型
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteInteractionTypeByIds(Long[] ids);
}
