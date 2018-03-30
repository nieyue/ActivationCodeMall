package com.nieyue.bean;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 权限
 * @author yy
 *
 */
@ApiModel(value="权限",description="权限")
public class Permission implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     * 权限id
     */
    @ApiModelProperty(value="权限id",example="权限id")
    private Integer permissionId;

    /**
     * 权限类型，默认0开放，1鉴权
     */
    @ApiModelProperty(value="权限类型，默认0开放，1鉴权",example="权限类型，默认0开放，1鉴权")
    private Integer type;
    /**
     * 权限管理名
     */
    @ApiModelProperty(value="权限管理名",example="权限管理名")
    private String managerName;
    /**
     * 权限名
     */
    @ApiModelProperty(value="权限名",example="权限名")
    private String name;
    /**
     * 权限路由
     */
    @ApiModelProperty(value="权限路由",example="权限路由")
    private String route;
    /**
     * 权限更新时间
     */
    @ApiModelProperty(value="权限更新时间",example="权限更新时间")
    private Date updateDate;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
