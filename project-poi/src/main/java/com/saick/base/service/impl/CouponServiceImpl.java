package com.saick.base.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saick.base.dao.entiy.Coupon;
import com.saick.base.dao.entiy.CouponExample;
import com.saick.base.dao.entiy.CouponExample.Criteria;
import com.saick.base.dao.mapper.CouponMapper;
import com.saick.base.service.api.CouponService;

/**
 * CouponService实现类型
 * 
 * @author Liubao
 * @2014年12月14日
 * 
 */
@Service
public class CouponServiceImpl implements CouponService {

    @Autowired
    CouponMapper couponMapper;

    @Override
    public List<Coupon> getAll() {
        CouponExample example = new CouponExample();
        Criteria criteria = example.createCriteria();
        criteria.andActivedIsNotNull();
        List<Coupon> list = couponMapper.selectByExample(example);
        return list;
    }

}
