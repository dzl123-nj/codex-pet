package com.ruoyi.virtualPet.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 宠物属性记录对象 pet_attribute_record
 * 
 * @author dzl
 * @date 2026-04-24
 */
public class PetAttributeRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 记录ID */
    private Long id;

    /** 宠物ID */
    @Excel(name = "宠物ID")
    private Long petId;

    /** 属性类型（hunger/happiness/energy/cleanliness/health） */
    @Excel(name = "属性类型", readConverterExp = "h=unger/happiness/energy/cleanliness/health")
    private String attributeType;

    /** 修改前值 */
    @Excel(name = "修改前值")
    private Long oldValue;

    /** 修改后值 */
    @Excel(name = "修改后值")
    private Long newValue;

    /** 变化量（正数增加，负数减少） */
    @Excel(name = "变化量", readConverterExp = "正=数增加，负数减少")
    private Long changeAmount;

    /** 变化原因（如：喂食、互动、自然衰减） */
    @Excel(name = "变化原因", readConverterExp = "如=：喂食、互动、自然衰减")
    private String changeReason;

    /** 变化时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "变化时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date changeTime;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setPetId(Long petId) 
    {
        this.petId = petId;
    }

    public Long getPetId() 
    {
        return petId;
    }

    public void setAttributeType(String attributeType) 
    {
        this.attributeType = attributeType;
    }

    public String getAttributeType() 
    {
        return attributeType;
    }

    public void setOldValue(Long oldValue) 
    {
        this.oldValue = oldValue;
    }

    public Long getOldValue() 
    {
        return oldValue;
    }

    public void setNewValue(Long newValue) 
    {
        this.newValue = newValue;
    }

    public Long getNewValue() 
    {
        return newValue;
    }

    public void setChangeAmount(Long changeAmount) 
    {
        this.changeAmount = changeAmount;
    }

    public Long getChangeAmount() 
    {
        return changeAmount;
    }

    public void setChangeReason(String changeReason) 
    {
        this.changeReason = changeReason;
    }

    public String getChangeReason() 
    {
        return changeReason;
    }

    public void setChangeTime(Date changeTime) 
    {
        this.changeTime = changeTime;
    }

    public Date getChangeTime() 
    {
        return changeTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("petId", getPetId())
            .append("attributeType", getAttributeType())
            .append("oldValue", getOldValue())
            .append("newValue", getNewValue())
            .append("changeAmount", getChangeAmount())
            .append("changeReason", getChangeReason())
            .append("changeTime", getChangeTime())
            .append("createTime", getCreateTime())
            .toString();
    }
}
