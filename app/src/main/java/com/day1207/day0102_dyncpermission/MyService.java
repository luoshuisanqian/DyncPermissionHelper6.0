package com.day1207.day0102_dyncpermission;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class MyService extends Service {
    private boolean serviceRunning = false;//判断service是否运行，默认是非运行状态的

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
//        throw new UnsupportedOperationException("Not yet implemented");
        return new Binder();//当使用绑定服务时，需要返回一个Binder()对象，否则会崩溃
    }

    @Override
    public void onCreate() {
        super.onCreate();
        serviceRunning = true;
        System.out.println("Service Create===");
                new Thread(){
            @Override
            public void run() {
                super.run();
                while(serviceRunning){
                    System.out.println("服务正在运行.......");
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }.start();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("Service Destory=====");
        serviceRunning = false;
    }

    /**
     * 只要你在外界执行了startService，在内部就行执行onStartCommand
     * @param intent
     * @param flags
     * @param startId
     * @return
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {//当调用sartService时会触发此方法
        System.out.println("onStartCommand=====");
//        new Thread(){
//            @Override
//            public void run() {
//                super.run();
//                System.out.println("服务正在运行.......");
//                try {
//                    sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }.start();
        return super.onStartCommand(intent, flags, startId);
    }
}
