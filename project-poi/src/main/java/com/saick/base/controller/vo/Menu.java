package com.saick.base.controller.vo;

import java.io.Serializable;
import java.util.List;

/**
 * 权限菜单模型类
 */
public class Menu implements Serializable {

    private static final long serialVersionUID = -8604179560635237921L;

    private String menuId;// 菜单id、模块id
    private String icon;// 图标
    private String menuName;// 菜单名称/模块名称
    private String url;// 菜单链接

    private List<Menu> menuList;// 二级菜单
    private List<Operation> operationList;// 菜单下的各各操作链接为了权限链接

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Menu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<Menu> menuList) {
        this.menuList = menuList;
    }

    public List<Operation> getOperationList() {
        return operationList;
    }

    public void setOperationList(List<Operation> operationList) {
        this.operationList = operationList;
    }

    @Override
    public String toString() {
        return "Menu [menuId=" + menuId + ", icon=" + icon + ", menuName="
                + menuName + ", url=" + url + ", menuList=" + menuList
                + ", operationList=" + operationList + "]";
    }

}
