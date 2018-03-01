package com.day1207.day0102_dyncpermission;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {
    private final int CALL_PHONE_CODE = 1;
    private static final String PACKAGE_URL_SCHEME = "package:"; // 方案
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }
//    @Override
//    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        switch (requestCode) {
//            //If request is canclled, the result arrays are empty
//            case CALL_PHONE_CODE:
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    //permission was granted, yay! Do the
//                    //contacts-related task you need to do
//                    Toast.makeText(BaseActivity.this, "onRequestPermission=====2222=====", Toast.LENGTH_SHORT).show();
//                } else {
//                    //permission denied, boo! Disable the
//                    //functionality that depends on this permission
//                    if (ActivityCompat.shouldShowRequestPermissionRationale(BaseActivity.this, permissions[0])) {
//                        Toast.makeText(BaseActivity.this, "权限被拒绝=====", Toast.LENGTH_SHORT).show();
//                    } else {
//                        Toast.makeText(BaseActivity.this, "权限被拒绝====Don't ask again===", Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
//                        intent.setData(Uri.parse(PACKAGE_URL_SCHEME + getPackageName()));
//                        startActivity(intent);
//                    }
//                }
//                return;
//        }
//    }
}
