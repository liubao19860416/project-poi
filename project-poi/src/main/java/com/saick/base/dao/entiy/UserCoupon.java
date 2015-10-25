package com.saick.base.dao.entiy;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

/**
 * 生成保养券json数据实体类
 * 
 * @author Liubao
 * @2015年3月9日
 * 
 */
@Alias(value = "userCoupon")
public class UserCoupon implements Serializable {

    private static final long serialVersionUID = 3869221403827141173L;
    private String userCode;
    // private String userName;
    private String userMobilePhone;
    private String verifyCode;
    private String userVehiclePlateNumber;

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserMobilePhone() {
        return userMobilePhone;
    }

    public void setUserMobilePhone(String userMobilePhone) {
        this.userMobilePhone = userMobilePhone;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public String getUserVehiclePlateNumber() {
        return userVehiclePlateNumber;
    }

    public void setUserVehiclePlateNumber(String userVehiclePlateNumber) {
        this.userVehiclePlateNumber = userVehiclePlateNumber;
    }

    @Override
    public String toString() {
        return "UserCoupon [userCode=" + userCode + ", userMobilePhone="
                + userMobilePhone + ", verifyCode=" + verifyCode
                + ", userVehiclePlateNumber=" + userVehiclePlateNumber + "]";
    }

}