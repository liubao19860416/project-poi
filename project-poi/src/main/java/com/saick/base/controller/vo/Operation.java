package com.saick.base.controller.vo;

import java.io.Serializable;

/**
 * 菜单下的操作模型类，配置菜单下各各操作链接是为了进行用户权限拦截
 * 
 */
public class Operation implements Serializable {

    private static final long serialVersionUID = -5432351755716171044L;

    private String requestUrl;// 请求的url的地址
    private String icon;// 操作的图标，暂时不用
    private String operationId;// 操作的id，唯一标识，暂时不用
    private String operationName;// 操作的名称

    public Operation(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public Operation() {
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((requestUrl == null) ? 0 : requestUrl.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Operation other = (Operation) obj;
        if (requestUrl == null) {
            if (other.requestUrl != null)
                return false;
        } else if (requestUrl.indexOf(other.requestUrl) < 0
                && other.requestUrl.indexOf(requestUrl) < 0)
            return false;
        return true;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getOperationId() {
        return operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

}
