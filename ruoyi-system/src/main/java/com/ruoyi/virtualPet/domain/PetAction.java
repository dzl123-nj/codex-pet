package com.ruoyi.virtualPet.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 宠物动作定义对象 pet_action
 *
 * @author codex
 * @date 2026-05-27
 */
public class PetAction extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 动作ID */
    private Long id;

    /** 动作编码 */
    @Excel(name = "动作编码")
    private String actionCode;

    /** 动作名称 */
    @Excel(name = "动作名称")
    private String actionName;

    /** 触发类型：auto/user/status/timed */
    @Excel(name = "触发类型")
    private String triggerType;

    /** 动作持续时长，毫秒 */
    @Excel(name = "动作持续时长")
    private Long duration;

    /** 冷却时间，秒 */
    @Excel(name = "冷却时间")
    private Long cooldown;

    /** 饱食变化 */
    @Excel(name = "饱食变化")
    private Long hungerChange;

    /** 心情变化 */
    @Excel(name = "心情变化")
    private Long happinessChange;

    /** 精力变化 */
    @Excel(name = "精力变化")
    private Long energyChange;

    /** 清洁变化 */
    @Excel(name = "清洁变化")
    private Long cleanlinessChange;

    /** 健康变化 */
    @Excel(name = "健康变化")
    private Long healthChange;

    /** 亲密度变化 */
    @Excel(name = "亲密度变化")
    private Long intimacyChange;

    /** 奖励经验 */
    @Excel(name = "奖励经验")
    private Long experienceReward;

    /** 气泡文案 */
    @Excel(name = "气泡文案")
    private String bubbleText;

    /** 前端动画名称 */
    @Excel(name = "前端动画名称")
    private String animationName;

    /** 排序 */
    @Excel(name = "排序")
    private Long sortOrder;

    /** 是否启用：0-停用，1-启用 */
    @Excel(name = "是否启用")
    private Long enabled;

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

    public void setActionCode(String actionCode)
    {
        this.actionCode = actionCode;
    }

    public String getActionCode()
    {
        return actionCode;
    }

    public void setActionName(String actionName)
    {
        this.actionName = actionName;
    }

    public String getActionName()
    {
        return actionName;
    }

    public void setTriggerType(String triggerType)
    {
        this.triggerType = triggerType;
    }

    public String getTriggerType()
    {
        return triggerType;
    }

    public void setDuration(Long duration)
    {
        this.duration = duration;
    }

    public Long getDuration()
    {
        return duration;
    }

    public void setCooldown(Long cooldown)
    {
        this.cooldown = cooldown;
    }

    public Long getCooldown()
    {
        return cooldown;
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

    public void setIntimacyChange(Long intimacyChange)
    {
        this.intimacyChange = intimacyChange;
    }

    public Long getIntimacyChange()
    {
        return intimacyChange;
    }

    public void setExperienceReward(Long experienceReward)
    {
        this.experienceReward = experienceReward;
    }

    public Long getExperienceReward()
    {
        return experienceReward;
    }

    public void setBubbleText(String bubbleText)
    {
        this.bubbleText = bubbleText;
    }

    public String getBubbleText()
    {
        return bubbleText;
    }

    public void setAnimationName(String animationName)
    {
        this.animationName = animationName;
    }

    public String getAnimationName()
    {
        return animationName;
    }

    public void setSortOrder(Long sortOrder)
    {
        this.sortOrder = sortOrder;
    }

    public Long getSortOrder()
    {
        return sortOrder;
    }

    public void setEnabled(Long enabled)
    {
        this.enabled = enabled;
    }

    public Long getEnabled()
    {
        return enabled;
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
            .append("actionCode", getActionCode())
            .append("actionName", getActionName())
            .append("triggerType", getTriggerType())
            .append("duration", getDuration())
            .append("cooldown", getCooldown())
            .append("hungerChange", getHungerChange())
            .append("happinessChange", getHappinessChange())
            .append("energyChange", getEnergyChange())
            .append("cleanlinessChange", getCleanlinessChange())
            .append("healthChange", getHealthChange())
            .append("intimacyChange", getIntimacyChange())
            .append("experienceReward", getExperienceReward())
            .append("bubbleText", getBubbleText())
            .append("animationName", getAnimationName())
            .append("sortOrder", getSortOrder())
            .append("enabled", getEnabled())
            .append("deleted", getDeleted())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
