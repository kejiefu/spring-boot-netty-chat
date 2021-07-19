package com.mountain.user.service.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author kejiefu
 * @Description TODO
 * @Date 2020/12/30 15:40
 * @Created by kejiefu
 */
public abstract class BaseEntity implements Serializable {

    @ApiModelProperty(hidden = true)
    @TableField(value = "is_delete", fill = FieldFill.INSERT)
    private Integer isDelete;

    @ApiModelProperty(hidden = true)
    @TableField(fill = FieldFill.INSERT)
    private Long createTime;

    @ApiModelProperty(hidden = true)
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateTime;


    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

}
