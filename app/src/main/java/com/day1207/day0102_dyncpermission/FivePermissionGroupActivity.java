package com.day1207.day0102_dyncpermission;

import android.Manifest;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class FivePermissionGroupActivity extends AppCompatActivity {
    private String[] permissionAry= {Manifest.permission_group.STORAGE};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_five_permission_group);
        new CheckPermissionHelper(FivePermissionGroupActivity.this, permissionAry).apply();
    }
}
