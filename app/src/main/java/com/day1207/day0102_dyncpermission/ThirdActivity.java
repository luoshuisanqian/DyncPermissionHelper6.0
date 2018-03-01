package com.day1207.day0102_dyncpermission;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

public class ThirdActivity extends AppCompatActivity {
    private String[] permissionAry= {Manifest.permission.CALL_PHONE, Manifest.permission.CAMERA};
    private final int CALL_PHONE_CODE = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);


        if(lacksPermissions(permissionAry)) {//如果返回为true,代表缺少权限，
            Log.i("TAG", "===lacksPermission(permissionAry)=======" + lacksPermissions(permissionAry));
            boolean rationaleBoolean = ActivityCompat.shouldShowRequestPermissionRationale(ThirdActivity.this, Manifest.permission.CALL_PHONE);
            boolean rationalBoolean2 = ActivityCompat.shouldShowRequestPermissionRationale(ThirdActivity.this, Manifest.permission.CAMERA);
            if(rationaleBoolean && rationalBoolean2) {//如果用户请求过此权限，但用户拒绝了，返回为true
                Toast.makeText(ThirdActivity.this, "您已禁止了该权限，需要重新申请=======", Toast.LENGTH_SHORT).show();
                ActivityCompat.requestPermissions(ThirdActivity.this, permissionAry, CALL_PHONE_CODE);

            } else {
                Toast.makeText(ThirdActivity.this, "====一启动应用=====false========="+rationaleBoolean, Toast.LENGTH_SHORT).show();
                //申请权限
                    ActivityCompat.requestPermissions(ThirdActivity.this, permissionAry, CALL_PHONE_CODE);
            }
        } else {
            Toast.makeText(ThirdActivity.this, "有权限====3333", Toast.LENGTH_SHORT).show();
        }

    }

    //处理权限请求
    /**1.当应用请求权限时，系统将向用户显示一个对话框。当系统相应时，系统将调用应用的onRequestPermissionResult方法，
     * 向其传递用户相应。
     * 2.会调会将您传递的相同请求码传递给requestPermissions()。例如应用请求CALL_PHONE访问权限，则它可能采用一下回调
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case CALL_PHONE_CODE:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    //permission was granted, yay! Do the
                    //contacts-related task you need to do
                    Toast.makeText(ThirdActivity.this, "onRequestPermission=====2222=====", Toast.LENGTH_SHORT).show();
                } else {
                    //permission denied, boo! Disable the
                    //functionality that depends on this permission
                    if(ActivityCompat.shouldShowRequestPermissionRationale(ThirdActivity.this, Manifest.permission.CALL_PHONE)) {
                        Toast.makeText(ThirdActivity.this, "权限被拒绝=====", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ThirdActivity.this, "权限被拒绝====Don't ask again===", Toast.LENGTH_SHORT).show();
                    }
                }
                return;
        }
    }

    /**
     * 判断权限集合
     */
    public boolean lacksPermissions(String... permissions) {
        for(String permission : permissions) {
            if(lacksPermission(permission)) {
                return true;
            }
        }
        return false;
    }
    /**
     * 判断是否缺少权限
     */
    private boolean lacksPermission(String permission) {
        return ContextCompat.checkSelfPermission(ThirdActivity.this, permission) == PackageManager.PERMISSION_DENIED;
    }
}
