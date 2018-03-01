package com.day1207.day0102_dyncpermission;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;

import java.io.File;

public class SDCardActivity extends AppCompatActivity {

    private boolean exist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sdcard);
        getUpdateApkPath();
    }

    //是否存在SD卡
    public  boolean isSdCard() {
        exist = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
        return exist;
    }

    //下载apk存放路劲
    public  String getUpdateApkPath() {
        String sdDir = null;
        if(isSdCard()) {
            sdDir = Environment.getExternalStorageDirectory().getAbsolutePath();//      /storage/emulated/0
        } else {
            sdDir = Environment.getRootDirectory().getAbsolutePath();//                 /system
        }
        System.out.println("file===================" + (sdDir + "/" + "zhengda" + "/apk"));
        File file = new File(sdDir + "/" + "zhengda" + "/apk");
        if(!file.exists()) {
            file.mkdirs();
        }
        System.out.println("file.getAbsolutePath()================="+ file.getAbsolutePath());
        return file.getAbsolutePath();
    }
}
