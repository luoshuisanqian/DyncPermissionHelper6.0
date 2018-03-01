package com.day1207.day0102_dyncpermission;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;

public class ServiceActivity extends BaseActivity implements ServiceConnection {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        /**如果你启动了服务并且同时绑定了服务，   那你必须同是解除绑定服务和停止服务这两个同时执行之后，
         * 这个服务才会停止掉
         */
        /**重复的启动一个服务，它的onCreate只会执行一次，onStartCommand会重复的执行
         *
         */
        /**
         * 点击停止服务会执行Service的onDestory()方法
         * 点击解除绑定服务也会执行Service的onDestory()方法
         */
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(ServiceActivity.this, MyService.class));
            }
        });
        findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(ServiceActivity.this, MyService.class));
            }
        });
/**
 *
 */
        findViewById(R.id.btn3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bindService(new Intent(ServiceActivity.this, MyService.class), ServiceActivity.this, Context.BIND_AUTO_CREATE);
            }
        });
        findViewById(R.id.btn4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unbindService(ServiceActivity.this);
            }
        });
    }

    /**当服务绑定成功之后会执行此方法
     * @param name
     * @param service
     */
    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        System.out.println("服务链接成功了");

    }

    /**
     * 当服务所在的进程崩溃或者说被杀掉的时候，会执行
     * @param name
     */
    @Override
    public void onServiceDisconnected(ComponentName name) {

    }
}
