package com.ruoyi.virtualPet.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 宠物类型对象 pet_type
 * 
 * @author dzl
 * @date 2026-04-24
 */
public class PetType extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 宠物类型ID */
    private Long id;

    /** 宠物类型名称（如：猫、狗、兔子） */
    @Excel(name = "宠物类型名称", readConverterExp = "如=：猫、狗、兔子")
    private String typeName;

    /** 类型描述 */
    @Excel(name = "类型描述")
    private String typeDesc;

    /** 默认头像URL */
    @Excel(name = "默认头像URL")
    private String defaultAvatar;

    /** 逻辑删除：0-未删除，1-已删除 */
    @Excel(name = "逻辑删除：0-未删除，1-已删除")
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

    public void setDefaultAvatar(String defaultAvatar) 
    {
        this.defaultAvatar = defaultAvatar;
    }

    public String getDefaultAvatar() 
    {
        return defaultAvatar;
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
            .append("defaultAvatar", getDefaultAvatar())
            .append("deleted", getDeleted())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
