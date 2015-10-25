package com.saick.base.dao.entiy;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

/**
 * 保养券实体类-dop平台
 * 
 * @author Liubao
 * @Version 2.0
 */
@Alias("userCouponDOP")
public class UserCouponDOP implements Serializable {

    private static final long serialVersionUID = 569944897964392818L;

    private String customer_id;
    private String coupon_id;
    private String user_name;
    private String store_name;
    private String grape_order_id;
    private String mobile;
    private String verify_code;
    private String plate_number;

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getCoupon_id() {
        return coupon_id;
    }

    public void setCoupon_id(String coupon_id) {
        this.coupon_id = coupon_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public String getGrape_order_id() {
        return grape_order_id;
    }

    public void setGrape_order_id(String grape_order_id) {
        this.grape_order_id = grape_order_id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getVerify_code() {
        return verify_code;
    }

    public void setVerify_code(String verify_code) {
        this.verify_code = verify_code;
    }

    public String getPlate_number() {
        return plate_number;
    }

    public void setPlate_number(String plate_number) {
        this.plate_number = plate_number;
    }

    @Override
    public String toString() {
        return "UserCouponDOP [customer_id=" + customer_id + ", coupon_id="
                + coupon_id + ", user_name=" + user_name + ", store_name="
                + store_name + ", grape_order_id=" + grape_order_id
                + ", mobile=" + mobile + ", verify_code=" + verify_code
                + ", plate_number=" + plate_number + "]";
    }

}
