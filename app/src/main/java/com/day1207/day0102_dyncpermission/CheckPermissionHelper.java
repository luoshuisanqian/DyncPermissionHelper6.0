package com.day1207.day0102_dyncpermission;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

/**
 * Created by hm on 2017/1/9.
 */

public class CheckPermissionHelper extends Activity {
    private Activity context;
    private String[] permissionAry;
    private final int CALL_PHONE_CODE = 1;
    private static final String PACKAGE_URL_SCHEME = "package:"; // 方案

    public CheckPermissionHelper(Activity context, String[] permissionAry) {
        this.context = context;
        this.permissionAry = permissionAry;
    }

    public void apply(){
        if(lackPermissions(permissionAry)) {//如果返回为true,代表缺少权限
            boolean rationaleBoolean = ActivityCompat.shouldShowRequestPermissionRationale(context, permissionAry[0]);
            if(rationaleBoolean) {
                Toast.makeText(context, "您已禁止了该权限，需要重新申请=======", Toast.LENGTH_SHORT).show();
                ActivityCompat.requestPermissions(context, permissionAry, CALL_PHONE_CODE);
            }else {
                Toast.makeText(context, "====一启动应用=====false========="+rationaleBoolean, Toast.LENGTH_SHORT).show();
                //申请权限
                ActivityCompat.requestPermissions(context, permissionAry, CALL_PHONE_CODE);
            }
        } else {
            Toast.makeText(context, "有权限====3333", Toast.LENGTH_SHORT).show();
        }
    }



    /**
     * 判断权限集合
     */
    public boolean lackPermissions(String... permissions) {
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
        return ContextCompat.checkSelfPermission(context, permission) ==
                PackageManager.PERMISSION_DENIED;
    }
}
