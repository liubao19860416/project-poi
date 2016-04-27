package com.saick.base.service.impl;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.saick.base.BaseEnvironment;

/**
 * DAO层基础测试类，加载junit+spring环境 OK
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test/test-*.xml" })
public class BaseServiceTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testBaseENV() throws Exception {
        Assert.assertEquals("LOCAL", BaseEnvironment.SYS_ENV);
    }
}
