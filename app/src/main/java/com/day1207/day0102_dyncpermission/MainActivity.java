package com.day1207.day0102_dyncpermission;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final int  MY_PERMISSIONS_REQUEST_READ_CONTACTS = 100;
    private static final String PACKAGE_URL_SCHEME = "package:"; // 方案
    private String[] permissionAry= {Manifest.permission.READ_CALENDAR};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.tv_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                intent.setData(Uri.parse(PACKAGE_URL_SCHEME + getPackageName()));
                startActivity(intent);
            }
        });
        /**
         * 1.如果应用具有此权限，方法返回PackageManager.PERMISSION_GRANTED,并且应用可以继续操作
         * 2.如果不具有操作权限，方法将返回PERMISSION_DENIED(-1),且应用必须声明声明向用户要求权限
         */
//        int permissionCheck = ContextCompat.checkSelfPermission(MainActivity.this,
//                Manifest.permission.CALL_PHONE);//在日历中写入权限
//        Log.i("TAG", "======permissionCheck====-1===" + permissionCheck);
//        Toast.makeText(MainActivity.this, "permissionCheck=====" + permissionCheck, Toast.LENGTH_SHORT).show();

        /**
         * 1.为了帮助查找用户可能需要解释的情形,Android提供了一个试用程序方法，
         * 即shouldShowRequestPermissionRationale().如果应用请求过此权限但用户拒绝了请求，
         * 此方法将返回true
         * 2.如果用户在过去拒绝了权限请求，并在权限请求时系统对话框中选择了Don't ask again选项，此方法将返回false.
         * 如果设备规范禁止应用具有该权限，此方法也会返回false
         */

//        if(lacksPermissions(permissionAry)) {//如果返回为true,代表缺少权限
////            Toast.makeText(this, "1111111"+ lacksPermissions(permissionAry), Toast.LENGTH_SHORT).show();
//            boolean rationaleBoolean = ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.READ_CALENDAR);
//            Toast.makeText(this, "2222222"+ rationaleBoolean, Toast.LENGTH_SHORT).show();
//
//            if(rationaleBoolean) {
//                Toast.makeText(MainActivity.this, "您已禁止了该权限，需要重新申请====", Toast.LENGTH_SHORT).show();
//                ActivityCompat.requestPermissions(MainActivity.this,
//                        permissionAry,
//                        MY_PERMISSIONS_REQUEST_READ_CONTACTS
//                );
//            }else {
//                //当您的应用调用requestPermissions()时，系统将向用户展示一个标准的对话框。您的应用无法配置或更改此对话框。
//                //如果您需要为用户提供任何信息或解释，您应用在调用requestPermission()之前进行，
//                //如：解释应用为什么需要权限中所述
//                Toast.makeText(MainActivity.this, "rationaleBoolean==false=false" + rationaleBoolean, Toast.LENGTH_SHORT).show();
//
//                ActivityCompat.requestPermissions(MainActivity.this,
//                        permissionAry,
//                        MY_PERMISSIONS_REQUEST_READ_CONTACTS
//                );
//            }
//        }
//        else {
//            Toast.makeText(MainActivity.this, "有权限====", Toast.LENGTH_SHORT).show();
//        }



        //请求您所需要的权限
        /**
         *如果应用尚无所需的权限，则应用必须调用一个reuestPermission()方法，以请求适当的权限。
         * 应用将传递其所需的权限，
         * 以及您指定用于识别此权限请求的整形请求码。                 此方法异步执行:它会立即返回，
         * 并且在用户响应对话框之后，系统会使用结果调用应用的回掉方法，将应用传递的相同请求代码传递到requestPermissions().
         */
        //一下代码可以检查应用是否具备读取用户联系人的权限，并根据需要请求该权限
        int permissionCheck = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE);
        Log.i("TAG", "======permissionCheck=======" + permissionCheck);
        if(permissionCheck != PackageManager.PERMISSION_GRANTED
                ) {
            // Should we show an explanation?
            boolean rationaleBoolean = ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,Manifest.permission.CALL_PHONE);
            Log.i("TAG", "======rationaleBoolean=======" + rationaleBoolean);
            Toast.makeText(MainActivity.this, "======rationaleBoolean=======" + rationaleBoolean, Toast.LENGTH_SHORT).show();

            if(    rationaleBoolean   //如果应用之前请求过次权限，但拒绝了请求，次方法将返回为true
                    ) {//如果用户在过去拒绝了权限请求，并在权限请求系统对话框中选择了Don't ask again选项，此方法将返回false.如果设备规范禁止应用具有该权限，此方法也会返回 false。
                Toast.makeText(MainActivity.this, "您已禁止了该权限，需要重新申请====", Toast.LENGTH_SHORT).show();
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.CALL_PHONE},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS
                );
            } else {
                //当您的应用调用requestPermissions()时，系统将向用户展示一个标准的对话框。您的应用无法配置或更改此对话框。
                //如果您需要为用户提供任何信息或解释，您应用在调用requestPermission()之前进行，
                //如：解释应用为什么需要权限中所述
                Toast.makeText(MainActivity.this, "rationaleBoolean==false=false" + rationaleBoolean, Toast.LENGTH_SHORT).show();

                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.CALL_PHONE},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS
                );
            }
        } else {
            Toast.makeText(MainActivity.this, "有权限===========" , Toast.LENGTH_SHORT).show();
        }
    }


    //处理权限请求响应
    /**
     * 当应用请求权限时，系统将向用户显示一个对话框。当用户响应时，系统将调用应用的onRequestPermissionsResult()方法，
     * 向其传递用户响应。您的应用必须替换该方法，以了解是否已获得相应权限，
     * 回调会将您传递的相同请求代码传递给requestPermissions()。例如应用请求CALL_PHONE访问权限，则它可能采用一下回调
    */
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            // If request is cancelled, the result arrays are empty.
            //如果请求被取消，这result数组是空的
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS:
                if(grantResults.length > 0 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    Toast.makeText(MainActivity.this, "权限允许==", Toast.LENGTH_SHORT).show();

                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.

                    if(!ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.READ_CALENDAR)) {
                        Toast.makeText(MainActivity.this, "权限被拒绝===Don't ask again", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "权限被拒绝===", Toast.LENGTH_SHORT).show();
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
        return ContextCompat.checkSelfPermission(MainActivity.this, permission)
                == PackageManager.PERMISSION_DENIED;
    }
}

