package com.saick.base.dao.mapper;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.saick.base.dao.entiy.Coupon;
import com.saick.base.dao.entiy.CouponExample;
import com.saick.base.dao.entiy.CouponExample.Criteria;
import com.saick.base.dao.mapper.CouponMapper;

public class CouponMapperTest extends BaseDAOTest {

    @Autowired
    private CouponMapper couponMapper;

    @Test
    public void testSelectCoupon() throws Exception {
        Long id=9l;
        Coupon coupon = couponMapper.selectByPrimaryKey(id);
        Assert.assertNotNull(coupon);
    }
    
    @Test
    public void testSelectAll() throws Exception {
        CouponExample example=new CouponExample();
        Criteria criteria = example.createCriteria();
        criteria.andActivedIsNotNull();
        List<Coupon> list = couponMapper.selectByExample(example);
        Assert.assertEquals(11, list.size());
    }
    
    
    
    
}
