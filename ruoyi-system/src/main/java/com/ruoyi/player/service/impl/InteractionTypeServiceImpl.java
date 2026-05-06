package com.ruoyi.player.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.player.mapper.InteractionTypeMapper;
import com.ruoyi.player.domain.InteractionType;
import com.ruoyi.player.service.IInteractionTypeService;

/**
 * 互动类型Service业务层处理
 * 
 * @author dzl
 * @date 2026-04-22
 */
@Service
public class InteractionTypeServiceImpl implements IInteractionTypeService 
{
    @Autowired
    private InteractionTypeMapper interactionTypeMapper;

    /**
     * 查询互动类型
     * 
     * @param id 互动类型主键
     * @return 互动类型
     */
    @Override
    public InteractionType selectInteractionTypeById(Long id)
    {
        return interactionTypeMapper.selectInteractionTypeById(id);
    }

    /**
     * 查询互动类型列表
     * 
     * @param interactionType 互动类型
     * @return 互动类型
     */
    @Override
    public List<InteractionType> selectInteractionTypeList(InteractionType interactionType)
    {
        return interactionTypeMapper.selectInteractionTypeList(interactionType);
    }

    /**
     * 新增互动类型
     * 
     * @param interactionType 互动类型
     * @return 结果
     */
    @Override
    public int insertInteractionType(InteractionType interactionType)
    {
        interactionType.setCreateTime(DateUtils.getNowDate());
        return interactionTypeMapper.insertInteractionType(interactionType);
    }

    /**
     * 修改互动类型
     * 
     * @param interactionType 互动类型
     * @return 结果
     */
    @Override
    public int updateInteractionType(InteractionType interactionType)
    {
        interactionType.setUpdateTime(DateUtils.getNowDate());
        return interactionTypeMapper.updateInteractionType(interactionType);
    }

    /**
     * 批量删除互动类型
     * 
     * @param ids 需要删除的互动类型主键
     * @return 结果
     */
    @Override
    public int deleteInteractionTypeByIds(Long[] ids)
    {
        return interactionTypeMapper.deleteInteractionTypeByIds(ids);
    }

    /**
     * 删除互动类型信息
     * 
     * @param id 互动类型主键
     * @return 结果
     */
    @Override
    public int deleteInteractionTypeById(Long id)
    {
        return interactionTypeMapper.deleteInteractionTypeById(id);
    }
}
