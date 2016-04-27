package com.saick.base.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.saick.base.controller.vo.CouponViewObject;
import com.saick.base.dao.entiy.Coupon;
import com.saick.base.service.api.CouponService;

/**
 * 系统首页
 * 
 * localhost:8080//login/ruijin1.htm
 */
@Controller
public class IndexController {
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CouponService couponService;
    
    /**
     * 测试用Controller,请求可以携带很多种参数
     */
    @RequestMapping("/login/test/coupon")
    public @ResponseBody List<CouponViewObject> testCouponService(
           @RequestBody Map<String, Object> params,
           @RequestHeader Map<String, String> paramsHeader,
            ModelAndView modelAndView, Model model, 
            HttpServletResponse response,
            HttpServletRequest request, 
            HttpSession session)
            throws Exception {
        List<Coupon> list = couponService.getAll();
        List<CouponViewObject> viewObjects=new ArrayList<CouponViewObject>();
        for (Coupon coupon : list) {
            CouponViewObject viewObject=new CouponViewObject();
            org.springframework.beans.BeanUtils.copyProperties(coupon, viewObject);
            viewObjects.add(viewObject);
        }
        params.get("");
        paramsHeader.get("");
        logger.info("OK....");
        return viewObjects;
    }
    
    
    @RequestMapping("/login/test")
    public String first(Model model) throws Exception {
        System.out.println("OK....");
        return "test";
    }
    
    
    @RequestMapping("/login/ruijin1")
    public String ruijin1(Model model) throws Exception {
        System.out.println("OK....");
        return "ruijin/ruijin1";
    }
    
    @RequestMapping("/login/ruijin2")
    public String ruijin2(Model model) throws Exception {
        System.out.println("OK....");
        return "ruijin/ruijin2";
    }
    
    @RequestMapping("/login/ruijin3")
    public String ruijin3(Model model) throws Exception {
        System.out.println("OK....");
        return "ruijin/ruijin3";
    }

}
