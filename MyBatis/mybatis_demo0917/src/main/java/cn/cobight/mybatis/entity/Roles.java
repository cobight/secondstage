package cn.cobight.mybatis.entity;

/**
 * fileName:Roles
 * description:
 * author:cobight
 * createTime:2020/9/17 20:01
 * version:1.0.0
 */
public class Roles {
    private Integer roleId;
    private String roleName;
    private String describe;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
