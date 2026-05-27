package com.ruoyi.virtualPet.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 宠物动作记录对象 pet_action_record
 *
 * @author codex
 * @date 2026-05-27
 */
public class PetActionRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 记录ID */
    private Long id;

    /** 宠物ID */
    @Excel(name = "宠物ID")
    private Long petId;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 动作编码 */
    @Excel(name = "动作编码")
    private String actionCode;

    /** 动作名称 */
    @Excel(name = "动作名称")
    private String actionName;

    /** 触发来源 */
    @Excel(name = "触发来源")
    private String triggerSource;

    /** 动作前快照 */
    private String beforeSnapshot;

    /** 动作后快照 */
    private String afterSnapshot;

    /** 奖励经验 */
    @Excel(name = "奖励经验")
    private Long rewardExp;

    /** 气泡文案 */
    @Excel(name = "气泡文案")
    private String bubbleText;

    /** 动作时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "动作时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date actionTime;

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

    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getUserId()
    {
        return userId;
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

    public void setTriggerSource(String triggerSource)
    {
        this.triggerSource = triggerSource;
    }

    public String getTriggerSource()
    {
        return triggerSource;
    }

    public void setBeforeSnapshot(String beforeSnapshot)
    {
        this.beforeSnapshot = beforeSnapshot;
    }

    public String getBeforeSnapshot()
    {
        return beforeSnapshot;
    }

    public void setAfterSnapshot(String afterSnapshot)
    {
        this.afterSnapshot = afterSnapshot;
    }

    public String getAfterSnapshot()
    {
        return afterSnapshot;
    }

    public void setRewardExp(Long rewardExp)
    {
        this.rewardExp = rewardExp;
    }

    public Long getRewardExp()
    {
        return rewardExp;
    }

    public void setBubbleText(String bubbleText)
    {
        this.bubbleText = bubbleText;
    }

    public String getBubbleText()
    {
        return bubbleText;
    }

    public void setActionTime(Date actionTime)
    {
        this.actionTime = actionTime;
    }

    public Date getActionTime()
    {
        return actionTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("petId", getPetId())
            .append("userId", getUserId())
            .append("actionCode", getActionCode())
            .append("actionName", getActionName())
            .append("triggerSource", getTriggerSource())
            .append("beforeSnapshot", getBeforeSnapshot())
            .append("afterSnapshot", getAfterSnapshot())
            .append("rewardExp", getRewardExp())
            .append("bubbleText", getBubbleText())
            .append("actionTime", getActionTime())
            .append("createTime", getCreateTime())
            .toString();
    }
}
