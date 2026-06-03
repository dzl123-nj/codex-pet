package com.ruoyi.virtualPet.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 宠物精灵图表配置对象 pet_sprite_config
 *
 * @author dzl
 * @date 2026-06-02
 */
public class PetSpriteConfig extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 配置ID */
    private Long id;

    /** 关联宠物类型ID */
    @Excel(name = "宠物类型ID")
    private Long petTypeId;

    /** sprite 图片路径 */
    @Excel(name = "sprite图片路径")
    private String spriteImage;

    /** 每格宽度(px) */
    @Excel(name = "格子宽度")
    private Integer cellW;

    /** 每格高度(px) */
    @Excel(name = "格子高度")
    private Integer cellH;

    /** 动画帧率 */
    @Excel(name = "帧率")
    private Integer fps;

    /** 动作定义 JSON */
    private String actions;

    /** 状态：0=启用，1=停用 */
    @Excel(name = "状态", readConverterExp = "0=启用,1=停用")
    private String status;

    /** 非DB字段：宠物类型名称 */
    private String petTypeName;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getPetTypeId() { return petTypeId; }
    public void setPetTypeId(Long petTypeId) { this.petTypeId = petTypeId; }

    public String getSpriteImage() { return spriteImage; }
    public void setSpriteImage(String spriteImage) { this.spriteImage = spriteImage; }

    public Integer getCellW() { return cellW; }
    public void setCellW(Integer cellW) { this.cellW = cellW; }

    public Integer getCellH() { return cellH; }
    public void setCellH(Integer cellH) { this.cellH = cellH; }

    public Integer getFps() { return fps; }
    public void setFps(Integer fps) { this.fps = fps; }

    public String getActions() { return actions; }
    public void setActions(String actions) { this.actions = actions; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getPetTypeName() { return petTypeName; }
    public void setPetTypeName(String petTypeName) { this.petTypeName = petTypeName; }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("petTypeId", getPetTypeId())
            .append("spriteImage", getSpriteImage())
            .append("cellW", getCellW())
            .append("cellH", getCellH())
            .append("fps", getFps())
            .append("actions", getActions())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
