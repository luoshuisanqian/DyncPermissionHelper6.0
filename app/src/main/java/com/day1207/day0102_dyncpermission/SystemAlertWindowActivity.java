package com.day1207.day0102_dyncpermission;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class SystemAlertWindowActivity extends AppCompatActivity {
    private int OVERLAY_PERMISSION_REQ_CODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_alert_window);
        if(Settings.canDrawOverlays(this)) {//有悬浮窗权限
            System.out.println("=====有悬浮窗权限==========");
        } else {
            System.out.println("=====没悬浮窗权限==========");
//            try{
//                Intent intent=new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
//                startActivityForResult(intent, OVERLAY_PERMISSION_REQ_CODE);
//            }catch (Exception e)
//            {
//                e.printStackTrace();
//            }
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == OVERLAY_PERMISSION_REQ_CODE) {
            if(Build.VERSION.SDK_INT>=23) {
                if (!Settings.canDrawOverlays(this)) {
                    Toast.makeText(this, "权限授予失败，无法开启悬浮窗", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "权限授予成功！", Toast.LENGTH_SHORT).show();
                    //有悬浮窗权限开启服务绑定 绑定权限
//                    Intent intent = new Intent(MainActivity.this, FBService.class);
//                    startService(intent);
                }
            }
        }
    }
}
