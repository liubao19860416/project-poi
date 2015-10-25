package com.saick.base.controller.vo;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

/**
 * 用户身份信息，包括了该用户相关的权限信息
 */
public class ActiveUser implements Serializable {

    private static final long serialVersionUID = -8406545446638996090L;

    private String userId;// 用户账号
    private String userName;// 用户名称
    private String grouPid;// 用户类型
    private String groupName;// 用户类型名称

    private String loginType;// 用户登录类型
    private Menu menu;// 操作菜单
    private List<Operation> operationList;// 操作权限，包括用户点击菜单及操作菜单功能所有链接权限

    /**
     * 获取当前请求的URL信息
     */
    public Operation getRequestUrl(String actionUrl) {
        if (operationList != null) {
            for (Iterator<Operation> it = operationList.iterator(); it.hasNext();) {
                Operation operation = (Operation) it.next();
                if (actionUrl.indexOf(operation.getRequestUrl()) > 0) {
                    return operation;
                }
            }
        }
        return null;
    }
    
    public ActiveUser() {
        super();
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGrouPid() {
        return grouPid;
    }

    public void setGrouPid(String grouPid) {
        this.grouPid = grouPid;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    public List<Operation> getOperationList() {
        return operationList;
    }

    public void setOperationList(List<Operation> operationList) {
        this.operationList = operationList;
    }

    @Override
    public String toString() {
        return "ActiveUser [userId=" + userId + ", userName=" + userName
                + ", grouPid=" + grouPid + ", groupName=" + groupName
                + ", loginType=" + loginType + ", menu=" + menu
                + ", operationList=" + operationList + "]";
    }
    

}
