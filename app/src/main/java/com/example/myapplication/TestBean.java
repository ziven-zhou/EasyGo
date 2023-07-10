package com.example.myapplication;

import com.ziven.easygo.annotation.BeanType;
import com.ziven.easygo.annotation.EasyGoBean;

/**
 * @author Administrator
 */
public class TestBean {

    @EasyGoBean(type = BeanType.STRING, name = "strParam")
    @EasyGoBean(type = BeanType.INT, name = "intParam")
    @EasyGoBean(list = BeanType.STRING, name = "listParam")
    @EasyGoBean(map = {BeanType.STRING, BeanType.INT}, name = "mapParam")
    @EasyGoBean(set = BeanType.STRING, name = "setParam")
    @EasyGoBean(more = {"java.util.Map", BeanType.STRING, "com.example.myapplication.TestBean"}, name = "setParam2")
    @EasyGoBean(name = BeanType.HAS_SERIALIZABLE)
    public int paramFieldBean;

    @EasyGoBean(type = BeanType.INT, name = "intParam")
    @EasyGoBean(type = BeanType.STRING, name = "strParam")
    public void paramMethodBean() {}
}
