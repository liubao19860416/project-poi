package com.saick.base.service.api;

import java.util.List;

import com.saick.base.dao.entiy.Coupon;

/**
 * Coupon的服务层接口
 * 
 * @author Liubao
 * @2014年12月14日
 * 
 */
public interface CouponService {
    
    public abstract List<Coupon> getAll();
}
