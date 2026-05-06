package com.ruoyi.player.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 互动类型对象 interaction_type
 * 
 * @author dzl
 * @date 2026-04-22
 */
public class InteractionType extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 互动类型ID */
    private Long id;

    /** 互动名称（如：喂食、抚摸、玩耍、洗澡） */
    @Excel(name = "互动名称", readConverterExp = "如=：喂食、抚摸、玩耍、洗澡")
    private String typeName;

    /** 互动描述 */
    @Excel(name = "互动描述")
    private String typeDesc;

    /** 对饥饿值的影响 */
    @Excel(name = "对饥饿值的影响")
    private Long hungerChange;

    /** 对心情值的影响 */
    @Excel(name = "对心情值的影响")
    private Long happinessChange;

    /** 对精力值的影响 */
    @Excel(name = "对精力值的影响")
    private Long energyChange;

    /** 对清洁度的影响 */
    @Excel(name = "对清洁度的影响")
    private Long cleanlinessChange;

    /** 对健康值的影响 */
    @Excel(name = "对健康值的影响")
    private Long healthChange;

    /** 奖励经验值 */
    @Excel(name = "奖励经验值")
    private Long experienceReward;

    /** 逻辑删除：0-未删除，1-已删除 */
    private Long deleted;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setTypeName(String typeName) 
    {
        this.typeName = typeName;
    }

    public String getTypeName() 
    {
        return typeName;
    }

    public void setTypeDesc(String typeDesc) 
    {
        this.typeDesc = typeDesc;
    }

    public String getTypeDesc() 
    {
        return typeDesc;
    }

    public void setHungerChange(Long hungerChange) 
    {
        this.hungerChange = hungerChange;
    }

    public Long getHungerChange() 
    {
        return hungerChange;
    }

    public void setHappinessChange(Long happinessChange) 
    {
        this.happinessChange = happinessChange;
    }

    public Long getHappinessChange() 
    {
        return happinessChange;
    }

    public void setEnergyChange(Long energyChange) 
    {
        this.energyChange = energyChange;
    }

    public Long getEnergyChange() 
    {
        return energyChange;
    }

    public void setCleanlinessChange(Long cleanlinessChange) 
    {
        this.cleanlinessChange = cleanlinessChange;
    }

    public Long getCleanlinessChange() 
    {
        return cleanlinessChange;
    }

    public void setHealthChange(Long healthChange) 
    {
        this.healthChange = healthChange;
    }

    public Long getHealthChange() 
    {
        return healthChange;
    }

    public void setExperienceReward(Long experienceReward) 
    {
        this.experienceReward = experienceReward;
    }

    public Long getExperienceReward() 
    {
        return experienceReward;
    }

    public void setDeleted(Long deleted) 
    {
        this.deleted = deleted;
    }

    public Long getDeleted() 
    {
        return deleted;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("typeName", getTypeName())
            .append("typeDesc", getTypeDesc())
            .append("hungerChange", getHungerChange())
            .append("happinessChange", getHappinessChange())
            .append("energyChange", getEnergyChange())
            .append("cleanlinessChange", getCleanlinessChange())
            .append("healthChange", getHealthChange())
            .append("experienceReward", getExperienceReward())
            .append("deleted", getDeleted())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
