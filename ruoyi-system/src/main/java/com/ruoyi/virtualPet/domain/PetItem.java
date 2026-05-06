package com.ruoyi.virtualPet.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 宠物道具对象 pet_item
 * 
 * @author dzl
 * @date 2026-04-24
 */
public class PetItem extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 道具ID */
    private Long id;

    /** 道具名称 */
    @Excel(name = "道具名称")
    private String itemName;

    /** 道具类型（食物、清洁、玩具、药品） */
    @Excel(name = "道具类型", readConverterExp = "食=物、清洁、玩具、药品")
    private String itemType;

    /** 道具描述 */
    @Excel(name = "道具描述")
    private String itemDesc;

    /** 使用后饥饿值变化 */
    @Excel(name = "使用后饥饿值变化")
    private Long hungerChange;

    /** 使用后心情值变化 */
    @Excel(name = "使用后心情值变化")
    private Long happinessChange;

    /** 使用后精力值变化 */
    @Excel(name = "使用后精力值变化")
    private Long energyChange;

    /** 使用后清洁度变化 */
    @Excel(name = "使用后清洁度变化")
    private Long cleanlinessChange;

    /** 使用后健康值变化 */
    @Excel(name = "使用后健康值变化")
    private Long healthChange;

    /** 使用后奖励经验 */
    @Excel(name = "使用后奖励经验")
    private Long experienceReward;

    /** 道具价格（虚拟货币） */
    @Excel(name = "道具价格", readConverterExp = "虚=拟货币")
    private Long price;

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

    public void setItemName(String itemName) 
    {
        this.itemName = itemName;
    }

    public String getItemName() 
    {
        return itemName;
    }

    public void setItemType(String itemType) 
    {
        this.itemType = itemType;
    }

    public String getItemType() 
    {
        return itemType;
    }

    public void setItemDesc(String itemDesc) 
    {
        this.itemDesc = itemDesc;
    }

    public String getItemDesc() 
    {
        return itemDesc;
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

    public void setPrice(Long price) 
    {
        this.price = price;
    }

    public Long getPrice() 
    {
        return price;
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
            .append("itemName", getItemName())
            .append("itemType", getItemType())
            .append("itemDesc", getItemDesc())
            .append("hungerChange", getHungerChange())
            .append("happinessChange", getHappinessChange())
            .append("energyChange", getEnergyChange())
            .append("cleanlinessChange", getCleanlinessChange())
            .append("healthChange", getHealthChange())
            .append("experienceReward", getExperienceReward())
            .append("price", getPrice())
            .append("deleted", getDeleted())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
