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

public class SecondActivity extends AppCompatActivity {
    private final int CALL_PHONE_CODE = 1;
    private static final String PACKAGE_URL_SCHEME = "package:"; // 方案
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        /**1.如果应用具有此权限，方法返回PackageManager.PERMISSION_GRANTED,并且应用可以继续操作
         * 2.如果不具有此权限，方法将返回PERMISSION_DENIED(-1),且应用必须声明向用户要求此权限
         */
        int permissionCheck = ContextCompat.checkSelfPermission(SecondActivity.this,
        Manifest.permission.CALL_PHONE);
                Log.i("TAG", "======permissionCheck====-1===" + permissionCheck);
        Toast.makeText(SecondActivity.this, "permissionCheck=====" + permissionCheck, Toast.LENGTH_SHORT).show();

        /**1.为了帮助查找用户可能需要解释的情形，Android提供了一个试用程序方法，
         * 即，shouldShowRequestPermissionRationale(),如果用户请求过此权限但用户拒绝了请求，此方法将返回为true,
         * 2.如果用户在过去拒绝了请求，并在权限请求时系统对话框中选择了Dont't ask again选项，此方法将返回为false
         * 3.如果设备规范禁止应用具有该权限，此方法也会返回为false
         */
        if(permissionCheck != PackageManager.PERMISSION_GRANTED) {
            //should we show an explantion
            boolean rationaleBoolean = ActivityCompat.shouldShowRequestPermissionRationale(SecondActivity.this, Manifest.permission.CALL_PHONE);
            Log.i("TAG", "======permissionCheck=======" + permissionCheck);
            if(rationaleBoolean) {//1.如果应用之前请求过此权限，但拒绝了，此方法返回为true
                    Toast.makeText(SecondActivity.this, "您已禁止了该权限，需要重新申请=======", Toast.LENGTH_SHORT).show();
                ActivityCompat.requestPermissions(SecondActivity.this, new String[]{Manifest.permission.CALL_PHONE}, CALL_PHONE_CODE);

            } else {//
                //当您的应用调用requestPermissions()时，系统将向用户展示一个标准的对话框。您的应用无法配置或更改此对话框
                //如果您需要为用户提供任何信息或解释，您应用在调用requestPermission()之前进行
                //如:解释应用为什么需要权限所诉
                Log.i("TAG", "rationaleBoolean====11111=====false====" + rationaleBoolean);
                ActivityCompat.requestPermissions(SecondActivity.this, new String[]{Manifest.permission.CALL_PHONE}, CALL_PHONE_CODE);
            }
        } else {
            Toast.makeText(SecondActivity.this, "有权限==============", Toast.LENGTH_SHORT).show();
        }
    }

    //处理权限请求响应
    /**1.当应用请求权限时，系统将向用户显示一个对话框。当系统响应时，系统将调用应用的onRequestPermissionResult()方法，
     * 向其传递用户响应。您的应用必须替换该方法，以了解是否已获得相应权限。
     * 2.回调会将您传递的相同请求码传递给requestPermissions()。例如应用请求CALL_PHONE访问权限，则它可能采用一下回调
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            //If request is canclled, the result arrays are empty
            case CALL_PHONE_CODE:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //permission was granted, yay! Do the
                    //contacts-related task you need to do
                    Toast.makeText(SecondActivity.this, "onRequestPermission=====2222=====", Toast.LENGTH_SHORT).show();
                } else {
                    //permission denied, boo! Disable the
                    //functionality that depends on this permission
                    if(ActivityCompat.shouldShowRequestPermissionRationale(SecondActivity.this, Manifest.permission.CALL_PHONE)) {
                        Toast.makeText(SecondActivity.this, "权限被拒绝=====", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(SecondActivity.this, "权限被拒绝====Don't ask again===", Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
//                        intent.setData(Uri.parse(PACKAGE_URL_SCHEME + getPackageName()));
//                        startActivity(intent);
                    }
                }
                return;
        }
    }
}
