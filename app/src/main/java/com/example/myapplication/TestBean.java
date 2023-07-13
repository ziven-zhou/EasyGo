package com.example.myapplication;

import com.ziven.easygo.annotation.BeanType;
import com.ziven.easygo.annotation.EasyGoBean;

/**
 * @author Administrator
 */
public class TestBean {

    @EasyGoBean(type = BeanType.STRING, name = "strParam")
    @EasyGoBean(list = BeanType.FLOAT, name = "listParam")
    @EasyGoBean(set = BeanType.DOUBLE, name = "setParam")
    @EasyGoBean(map = {BeanType.STRING, BeanType.LONG}, name = "mapParam")
    @EasyGoBean(more = {"java.util.Map", BeanType.STRING, "com.example.myapplication.TestBean"}, name = "mapParam2")
    @EasyGoBean(name = BeanType.IMPLEMENTS, more = { BeanType.SERIALIZABLE })
    @EasyGoBean(name = BeanType.EXTENDS, type = "com.example.myapplication.TestBean.BaseBean")
    public int paramFieldBean;

    @EasyGoBean(type = BeanType.INT, name = "intParam")
    @EasyGoBean(type = BeanType.STRING, name = "strParam")
    @EasyGoBean(name = BeanType.NO_TO_STRING)
    public void paramMethodBean() {}

    public static class BaseBean {}
}
