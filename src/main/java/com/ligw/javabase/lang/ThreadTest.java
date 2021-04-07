/**
 * Company
 * Copyright (C) 2004-2018 All Rights Reserved.
 */
package com.ligw.javabase.lang;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ligw
 * @version $Id ThreadTest.java, v 0.1 2018-03-29 23:57 ligw Exp $$
 */
public class ThreadTest {

//    参考：http://www.cnblogs.com/IUbanana/p/7110297.html

    /**

    Java线程具有五中基本状态:
    新建状态（New）：当线程对象对创建后，即进入了新建状态，如：Thread t = new MyThread();

    就绪状态（Runnable）：当调用线程对象的start()方法（t.start();），线程即进入就绪状态。处于就绪状态的线程，只是说明此线程已经做好了准备，
     随时等待CPU调度执行，并不是说执行了t.start()此线程立即就会执行；

    运行状态（Running）：当CPU开始调度处于就绪状态的线程时，此时线程才得以真正执行，即进入到运行状态。注：就绪状态是进入到运行状态的唯一入口，
     也就是说，线程要想进入运行状态执行，首先必须处于就绪状态中；

    阻塞状态（Blocked）：处于运行状态中的线程由于某种原因，暂时放弃对CPU的使用权，停止执行，此时进入阻塞状态，直到其进入到就绪状态，才 有机会
     再次被CPU调用以进入到运行状态。根据阻塞产生的原因不同，阻塞状态又可以分为三种：

        等待阻塞 -- 运行状态中的线程执行wait()方法，使本线程进入到等待阻塞状态；
        同步阻塞 -- 线程在获取synchronized同步锁失败(因为锁被其它线程所占用)，它会进入同步阻塞状态；
        其他阻塞 -- 通过调用线程的sleep()或join()或发出了I/O请求时，线程会进入到阻塞状态。当sleep()状态超时、join()等待线程终止或者超时、或者
        I/O处理完毕时，线程重新转入就绪状态。

    死亡状态（Dead）：线程执行完了或者因异常退出了run()方法，该线程结束生命周期。
     */

    public static void main(String[] args) {
        /**
         Java多线程的创建及启动
         继承Thread类，重写该类的run()方法； 如：MyThread
         实现Runnable接口，并重写该接口的run()方法；
         使用Callable和Future接口创建线程。
         */
        Thread thread1 = new MyThread();
        Thread thread2 = new MyThread();
//        thread1.start();
//        thread2.start();
        Runnable runnable = new MyRunnable();
        Thread thread3 = new Thread(runnable);
        Thread thread4 = new Thread(runnable);
//        thread3.start();
//        thread4.start();
        /**
         * Thread类也实现了Runnable接口
         * 也就是说，当执行到Thread类中的run()方法时，会首先判断target是否存在，存在则执行target中的run()方法，也就是实现了Runnable接口
         * 并重写了run()方法的类中的run()方法。但是上述给到的列子中，由于多态的存在，根本就没有执行到Thread类中的run()方法，而是直接先执行
         * 了运行时类型即MyThread类中的run()方法
         */
//        使用Callable和Future接口创建线程。具体是创建Callable接口的实现类，并实现clall()方法。并使用FutureTask类来包装Callable实现类
        // 的对象，且以此FutureTask对象作为Thread对象的target来创建线程。
        Callable<Integer> myCallable = new MyCallable();
        FutureTask futureTask = new FutureTask<Integer>(myCallable);
        for (int i = 0; i < 200; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
            if (i == 30) {
                Thread thread = new Thread(futureTask);   //FutureTask对象作为Thread对象的target创建新的线程
                thread.start();  //线程进入到就绪状态
            }
        }
        System.out.println("主线程for循环执行完毕..");
        try {
            //取得新创建的新线程中的call()方法返回的结果
            int sum = (Integer) futureTask.get();
            System.out.println("sum = " + sum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e){
            e.printStackTrace();
        }
        /**
        我们发现FutureTask类实际上是同时实现了Runnable和Future接口，由此才使得其具有Future和Runnable双重特性。通过Runnable特性，可以
         作为Thread对象的target，而Future特性，使得其可以取得新创建线程中的call()方法的返回值。

        执行下此程序，我们发现sum = 4950永远都是最后输出的。而“主线程for循环执行完毕..”则很可能是在子线程循环中间输出。由CPU的线程调度机制
         ，我们知道，“主线程for循环执行完毕..”的输出时机是没有任何问题的，那么为什么sum =4950会永远最后输出呢？原因在于通过ft.get()方法获
         取子线程call()方法的返回值时，当子线程此方法还未执行完毕，ft.get()方法会一直阻塞，直到call()方法执行完毕才能取到返回值。
         */

        /**
        上述主要讲解了三种常见的线程创建方式，对于线程的启动而言，都是调用线程对象的start()方法，需要特别注意的是：不能对同一线程对象两次调用
         start()方法。
        */

        /**
        Java多线程的阻塞状态与线程控制
        1.join()
        join —— 让一个线程等待另一个线程完成才继续执行。如A线程线程执行体中调用B线程的join()方法，则A线程被阻塞，直到B线程执行完为止，A才能
         得以继续执行。

        为什么要用join()方法
        在很多情况下，主线程生成并启动了子线程，如果子线程里要进行大量的耗时的运算，主线程往往将于子线程之前结束，但是如果主线程处理完其他的事
         务后，需要用到子线程的处理结果，也就是主线程需要等待子线程执行完成之后再结束，这个时候就要用到join()方法了。

        2.sleep()
        sleep —— 让当前的正在执行的线程暂停指定的时间，并进入阻塞状态。在其睡眠的时间段内，该线程由于不是处于就绪状态，因此不会得到执行的机会
         。即使此时系统中没有任何其他可执行的线程，出于sleep()中的线程也不会执行。因此sleep()方法常用来暂停线程执行。

        前面有讲到，当调用了新建的线程的start()方法后，线程进入到就绪状态，可能会在接下来的某个时间获取CPU时间片得以执行，如果希望这个新线程
         必然性的立即执行，直接调用原来线程的sleep(1)即可。
        注：睡一个毫秒级够了，因为CPU不会空闲，会切换到新建的线程。

        3.后台线程（Daemon Thread）
        概念/目的：后台线程主要是为其他线程（相对可以称之为前台线程）提供服务，或“守护线程”。如JVM中的垃圾回收线程。
        生命周期：后台线程的生命周期与前台线程生命周期有一定关联。主要体现在：当所有的前台线程都进入死亡状态时，后台线程会自动死亡(其实这个也
         很好理解，因为后台线程存在的目的在于为前台线程服务的，既然所有的前台线程都死亡了，那它自己还留着有什么用...伟大啊 ! !)。
        设置后台线程：调用Thread对象的setDaemon(true)方法可以将指定的线程设置为后台线程。
        判断线程是否是后台线程：调用thread对象的isDeamon()方法。
        注：main线程默认是前台线程，前台线程创建中创建的子线程默认是前台线程，后台线程中创建的线程默认是后台线程。调用setDeamon(true)方法将
         前台线程设置为后台线程时，需要在start()方法调用之前。前天线程都死亡后，JVM通知后台线程死亡，但从接收指令到作出响应，需要一定的时间。

        4.改变线程的优先级/setPriority()：
        每个线程在执行时都具有一定的优先级，优先级高的线程具有较多的执行机会。每个线程默认的优先级都与创建它的线程的优先级相同。main线程默认具
         有普通优先级。

        设置线程优先级：setPriority(int priorityLevel)。参数priorityLevel范围在1-10之间，常用的有如下三个静态常量值：
        MAX_PRIORITY:10
        MIN_PRIORITY:1
        NORM_PRIORITY:5
        获取线程优先级：getPriority()。

        注：具有较高线程优先级的线程对象仅表示此线程具有较多的执行机会，而非优先执行。

        5.yield()线程让步
        yield()的基本作用：暂停当前正在执行的线程对象让出CPU资源，将当前线程从运行状态转换到就绪状态并执行其他线程。同时，yield()方法还与线
         程优先级有关，当某个线程调用yiled()方法从运行状态转换到就绪状态后，CPU从就绪状态线程队列中只会选择与该线程优先级相同或优先级更高的
         线程去执行。使用yield()的目的是让相同优先级的线程之间能适当的轮转执行。但是，实际中无法保证yield()达到让步目的，因为让步的线程还有
         可能被线程调度程序再次选中。

        注意：yield()从未导致线程转到等待/睡眠/阻塞状态。在大多数情况下，yield()将导致线程从运行状态转到可运行（就绪）状态，但有可能没有效果。
        */

        //.join()测试
        Thread thread_j = new MyThread();
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
            if (i == 30) {
                thread_j.start();
                try {
                    thread_j.join();    // main线程需要等待thread线程执行完后才能继续执行

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        //.sleep()测试
        Thread thread_s = new Thread(runnable);
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
            if (i == 30) {
                thread_s.start();
                try {
                    Thread.sleep(1);   // mainx线程转入阻塞1ms 使得thread必然能够马上得以执行
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        //.setDaemon()测试
        Thread thread_d = new MyThread();
        for (int i = 0; i < 100; i++) {
            System.out.println("main thread i = " + i);
            if (i == 20) {
                thread_d.setDaemon(true);   //设置为后台守护线程
                thread_d.start();
            }
        }

        //.yield()测试
        Thread thread_y1 = new MyThread();
        Thread thread_y2 = new Thread(runnable);
        thread_y1.setPriority(Thread.MAX_PRIORITY);
        thread_y2.setPriority(Thread.MIN_PRIORITY);
        for (int i = 0; i < 100; i++) {
            System.out.println("main thread i = " + i);
            if (i == 20) {
                thread_y1.start();
                thread_y2.start();
                Thread.yield();//主线程阻塞
            }
        }

        //线程同步 http://www.cnblogs.com/IUbanana/p/7112296.html


    }


    public synchronized Integer setnum(Integer num){
        return num ++;
    }

    private final Lock lock = new ReentrantLock();
    public void lockTest(){
        lock.lock();
        //需要进行线程安全的代码块
        // ...
        lock.unlock();
    }


}

/**
 * 继承Thread类创建线程
 */
class MyThread extends Thread{
    private int i=0 ;
    @Override public void run() {
        for (;i<= 127;i++){
            System.out.println(Thread.currentThread().getName()+":"+(char)i+"|");
        }
    }
}

/**
 * 实现Runnable接口创建线程
 */
class MyRunnable implements Runnable{
// 由于实际的业务需要，常常会遇到需要在特定时机终止某一线程的运行，使其进入到死亡状态。目前最通用的做法是设置一boolean型的变量，当条件满足
// 时，使线程执行体快速执行完毕。如：
//    for (int i = 0; i < 100; i++) {
//        System.out.println(Thread.currentThread().getName() + " " + i);
//        if (i == 30) {
//            thread.start();
//        }
//        if(i == 40){
//            myRunnable.stopThread();
//        }
//    }

    private boolean stop;
    private int i = 0;
    @Override public void run() {
        for (;i<= 127 && !stop;i++){
            System.out.println(Thread.currentThread().getName()+":"+i+",");
        }
    }
    public void stopThread(){
        this.stop = true;
    }
}

class  MyCallable implements Callable<Integer>{

    private int i = 0;

    @Override public Integer call() throws Exception {
        int sum = 0;
        for (; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + ":" + i);
            sum += i;
        }
        return sum;
    }
}
