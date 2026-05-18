package com.ruoyi.player.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 用户道具库存对象 user_item_inventory
 * 
 * @author dzl
 * @date 2026-04-24
 */
public class UserItemInventory extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 库存ID */
    private Long id;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 道具ID */
    @Excel(name = "道具ID")
    private Long itemId;

    /** 道具数量 */
    @Excel(name = "道具数量")
    private Long quantity;

    /** 道具名称（非数据库字段，JOIN查询时填充） */
    private String itemName;

    /** 道具类型（非数据库字段，JOIN查询时填充） */
    private String itemType;

    /** 道具描述（非数据库字段，JOIN查询时填充） */
    private String itemDesc;

    /** 饥饿值变化（非数据库字段，JOIN查询时填充） */
    private Long hungerChange;

    /** 心情值变化（非数据库字段，JOIN查询时填充） */
    private Long happinessChange;

    /** 精力值变化（非数据库字段，JOIN查询时填充） */
    private Long energyChange;

    /** 清洁度变化（非数据库字段，JOIN查询时填充） */
    private Long cleanlinessChange;

    /** 健康值变化（非数据库字段，JOIN查询时填充） */
    private Long healthChange;

    /** 经验奖励（非数据库字段，JOIN查询时填充） */
    private Long experienceReward;

    /** 道具价格（非数据库字段，JOIN查询时填充） */
    private Long price;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }

    public void setItemId(Long itemId) 
    {
        this.itemId = itemId;
    }

    public Long getItemId() 
    {
        return itemId;
    }

    public void setQuantity(Long quantity) 
    {
        this.quantity = quantity;
    }

    public Long getQuantity() 
    {
        return quantity;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("itemId", getItemId())
            .append("quantity", getQuantity())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
