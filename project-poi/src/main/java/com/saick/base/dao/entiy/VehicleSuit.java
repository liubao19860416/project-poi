package com.saick.base.dao.entiy;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

/**
 * 生成大众保养套餐数据实体类
 * 
 * @author Liubao
 * @2015年3月9日
 * 
 */
@Alias(value = "vehicleSuit")
public class VehicleSuit implements Serializable {

    private static final long serialVersionUID = 3869221403827141183L;
    private String sortIndex;
    private String name;
    private String type;
    private String fitToSuit;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public String getSortIndex() {
        return sortIndex;
    }

    public void setSortIndex(String sortIndex) {
        this.sortIndex = sortIndex;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFitToSuit() {
        return fitToSuit;
    }

    public void setFitToSuit(String fitToSuit) {
        this.fitToSuit = fitToSuit;
    }

    @Override
    public String toString() {
        return "VehicleSuit [sortIndex=" + sortIndex + ", name=" + name
                + ", type=" + type + ", fitToSuit=" + fitToSuit + "]";
    }

}