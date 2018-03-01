package com.day1207.day0102_dyncpermission;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;

public class FourActivity extends BaseActivity {
    private String[] permissionAry= {Manifest.permission.CALL_PHONE};
    private static final String PACKAGE_URL_SCHEME = "package:"; // 方案
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fjour);
        findViewById(R.id.tv_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                intent.setData(Uri.parse(PACKAGE_URL_SCHEME + getPackageName()));
                startActivity(intent);
            }
        });
        new CheckPermissionHelper(FourActivity.this, permissionAry).apply();
    }

    @Override
    protected void onResume() {
        super.onResume();
//        new CheckPermissionHelper(FourActivity.this, permissionAry).apply();
    }
}
