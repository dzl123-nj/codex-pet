package com.ruoyi.player.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 道具使用记录对象 item_usage_record
 * 
 * @author dzl
 * @date 2026-04-24
 */
public class ItemUsageRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 使用记录ID */
    private Long id;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 宠物ID */
    @Excel(name = "宠物ID")
    private Long petId;

    /** 道具ID */
    @Excel(name = "道具ID")
    private Long itemId;

    /** 使用数量 */
    @Excel(name = "使用数量")
    private Long usageQuantity;

    /** 使用时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "使用时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date usageTime;

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

    public void setPetId(Long petId) 
    {
        this.petId = petId;
    }

    public Long getPetId() 
    {
        return petId;
    }

    public void setItemId(Long itemId) 
    {
        this.itemId = itemId;
    }

    public Long getItemId() 
    {
        return itemId;
    }

    public void setUsageQuantity(Long usageQuantity) 
    {
        this.usageQuantity = usageQuantity;
    }

    public Long getUsageQuantity() 
    {
        return usageQuantity;
    }

    public void setUsageTime(Date usageTime) 
    {
        this.usageTime = usageTime;
    }

    public Date getUsageTime() 
    {
        return usageTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("petId", getPetId())
            .append("itemId", getItemId())
            .append("usageQuantity", getUsageQuantity())
            .append("usageTime", getUsageTime())
            .append("createTime", getCreateTime())
            .toString();
    }
}
