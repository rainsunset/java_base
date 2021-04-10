package com.ligw.javabase.designpattern.proxy;

import com.ligw.javabase.designpattern.proxy.dynamic.JDKDynamicProxyTry;
import com.ligw.javabase.designpattern.proxy.staticproxy.*;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;

/**
 * @Description: TODO
 * @Author: Amo
 * @CreateDate: 2021/4/11
 */
public class ProxyTest {

    // 实现 Daoa，Daob都有query功能;Daoa要附加打印入参功能;Daoa和Daob要附加统计耗时功能

    public static void main(String[] args) {
//        staticProxyByExt();
//        staticProxyByImpl();


        // 自己实现JDK动态代理
        try {
            DaoI o = (DaoI) JDKDynamicProxyTry.newImplProxyInstance(new Daoa());
            o.query("dida");
            // Daoa query by:dida
            // print cost time:0
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }

    /**
     * 接口(聚合)方法实现静态代理
     */
    private static void staticProxyByImpl() {
        DaoI daoa = new Daoa();
        DaoI daoaWithPrintParam = new PrintParamProxy(daoa);
        DaoI daoaWithPrintParqamAndCost = new TimeCostProxy(daoaWithPrintParam);
        daoaWithPrintParqamAndCost.query("lucy");
        // print arges:lucy
        // Daoa query by:lucy
        // print cost time:0

        DaoI daob = new Daob();
        DaoI daobWithPringParam = new PrintParamProxy(daob);
        daobWithPringParam.query("linda");
        // print arges:linda
        // Daob query by:linda
    }

    /**
     * 继承方法实现静态代理
     *
     */
    private static void staticProxyByExt() {
        Daoa daoa = new DaoaProxyByExt();
        daoa.query("lucy");
        // print arges:lucy
        // Daoa query by:lucy
        // print cost time:0

        Daob daob = new DaobProxyByExt();
        daob.query("linda");
        // Daob query by:linda
        // print cost time:0
    }

}
