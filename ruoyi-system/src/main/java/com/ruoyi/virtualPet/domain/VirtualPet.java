package com.ruoyi.virtualPet.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 虚拟宠物对象 virtual_pet
 * 
 * @author dzl
 * @date 2026-04-24
 */
public class VirtualPet extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 宠物ID */
    private Long id;

    /** 所属用户ID（关联sys_user.user_id） */
    @Excel(name = "所属用户ID", readConverterExp = "关=联sys_user.user_id")
    private Long userId;

    /** 宠物类型ID */
    @Excel(name = "宠物类型ID")
    private Long petTypeId;

    /** 宠物昵称 */
    @Excel(name = "宠物昵称")
    private String petName;

    /** 宠物头像URL */
    private String petAvatar;

    /** 创建/出生时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建/出生时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date birthTime;

    /** 宠物等级 */
    @Excel(name = "宠物等级")
    private Long level;

    /** 当前经验值 */
    @Excel(name = "当前经验值")
    private Long experience;

    /** 饥饿值（0-100） */
    @Excel(name = "饥饿值", readConverterExp = "0=-100")
    private Long hunger;

    /** 心情值（0-100） */
    @Excel(name = "心情值", readConverterExp = "0=-100")
    private Long happiness;

    /** 精力值（0-100） */
    @Excel(name = "精力值", readConverterExp = "0=-100")
    private Long energy;

    /** 清洁度（0-100） */
    @Excel(name = "清洁度", readConverterExp = "0=-100")
    private Long cleanliness;

    /** 健康值（0-100） */
    @Excel(name = "健康值", readConverterExp = "0=-100")
    private Long health;

    /** 状态：0-休眠，1-活跃，2-生病，3-死亡 */
    @Excel(name = "状态：0-休眠，1-活跃，2-生病，3-死亡")
    private Long status;

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

    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }

    public void setPetTypeId(Long petTypeId) 
    {
        this.petTypeId = petTypeId;
    }

    public Long getPetTypeId() 
    {
        return petTypeId;
    }

    public void setPetName(String petName) 
    {
        this.petName = petName;
    }

    public String getPetName() 
    {
        return petName;
    }

    public void setPetAvatar(String petAvatar) 
    {
        this.petAvatar = petAvatar;
    }

    public String getPetAvatar() 
    {
        return petAvatar;
    }

    public void setBirthTime(Date birthTime) 
    {
        this.birthTime = birthTime;
    }

    public Date getBirthTime() 
    {
        return birthTime;
    }

    public void setLevel(Long level) 
    {
        this.level = level;
    }

    public Long getLevel() 
    {
        return level;
    }

    public void setExperience(Long experience) 
    {
        this.experience = experience;
    }

    public Long getExperience() 
    {
        return experience;
    }

    public void setHunger(Long hunger) 
    {
        this.hunger = hunger;
    }

    public Long getHunger() 
    {
        return hunger;
    }

    public void setHappiness(Long happiness) 
    {
        this.happiness = happiness;
    }

    public Long getHappiness() 
    {
        return happiness;
    }

    public void setEnergy(Long energy) 
    {
        this.energy = energy;
    }

    public Long getEnergy() 
    {
        return energy;
    }

    public void setCleanliness(Long cleanliness) 
    {
        this.cleanliness = cleanliness;
    }

    public Long getCleanliness() 
    {
        return cleanliness;
    }

    public void setHealth(Long health) 
    {
        this.health = health;
    }

    public Long getHealth() 
    {
        return health;
    }

    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
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
            .append("userId", getUserId())
            .append("petTypeId", getPetTypeId())
            .append("petName", getPetName())
            .append("petAvatar", getPetAvatar())
            .append("birthTime", getBirthTime())
            .append("level", getLevel())
            .append("experience", getExperience())
            .append("hunger", getHunger())
            .append("happiness", getHappiness())
            .append("energy", getEnergy())
            .append("cleanliness", getCleanliness())
            .append("health", getHealth())
            .append("status", getStatus())
            .append("deleted", getDeleted())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
