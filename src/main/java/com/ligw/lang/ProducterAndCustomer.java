/**
 * Company
 * Copyright (C) 2004-2018 All Rights Reserved.
 */
package com.ligw.lang;

/**
 * 生产者和消费者模式
 * @author ligw
 * @version $Id ProducterAndCustomer.java, v 0.1 2018-03-30 13:32 ligw Exp $$
 */
public class ProducterAndCustomer {
    public static void main(String[] args) {
        Food food = new Food();
        Producter p = new Producter(food);
        Customer c = new Customer(food);
        Thread tp = new Thread(p);
        Thread tc = new Thread(c);
        tp.start();
        tc.start();
    }
}
//生产者
class Producter implements Runnable {
    private Food food;
    public Producter(Food food) {
        this.food = food;
    }
    @Override
    public void run() {
        for(int i = 0; i < 50; i++) {
            if(i % 2 == 0) {
                //偶数，生产xx菜
                food.set("红烧肉", "好吃");
            } else {
                //奇数，生产xx
                food.set("脆皮鸡", "香脆");
            }
        }
    }
}

class Customer implements Runnable {
    private Food food;
    public Customer(Food food) {
        this.food = food;
    }
    @Override
    public void run() {
        for(int i = 0; i < 50; i++){
            food.get();
        }
    }
}

class Food {
    private String name;//菜名
    private String efficasy;//功效
    private boolean flag = true;
    public synchronized void set(String name, String efficasy) {
        if(!flag) {//1， 消费，不能生产
            try {
                this.wait();//当前线程进入等待状态，并让出CPU，释放监视器的锁
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("shengchan " + name + " " + efficasy);
        this.setName(name);
        this.setEfficasy(efficasy);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        flag = false; //开始消费
        this.notify();
    }
    public synchronized void get() {
        if(flag) {//true是生产
            try {
                this.wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        System.out.println(this.getName() + "xiaofei-->" + this.getEfficasy());
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        flag = true; //开始生产
        this.notify();
    }
    public Food() {}

    public Food(String name, String efficasy) {
        super();
        this.name = name;
        this.efficasy = efficasy;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEfficasy() {
        return efficasy;
    }

    public void setEfficasy(String efficasy) {
        this.efficasy = efficasy;
    }
}
