package com.nieyue.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * 角色权限
 * @author yy
 *
 */
@ApiModel(value="角色权限",description="角色权限")
public class RolePermission implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     * 角色权限id
     */
    @ApiModelProperty(value="角色权限id",example="角色权限id")
    private Integer rolePermissionId;

    /**
     * 范围，1公共，2自身
     */
    @ApiModelProperty(value="范围，1公共，2自身",example="范围，1公共，2自身")
    private Integer region;
    /**
     * 角色id
     */
    @ApiModelProperty(value="角色id",example="角色id")
    private Integer roleId;
    /**
     * 权限id
     */
    @ApiModelProperty(value="权限id",example="权限id")
    private Integer permissionId;
    /**
     * 角色权限更新时间
     */
    @ApiModelProperty(value="角色权限更新时间",example="角色权限更新时间")
    private Date updateDate;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getRolePermissionId() {
        return rolePermissionId;
    }

    public void setRolePermissionId(Integer rolePermissionId) {
        this.rolePermissionId = rolePermissionId;
    }

    public Integer getRegion() {
        return region;
    }

    public void setRegion(Integer region) {
        this.region = region;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
