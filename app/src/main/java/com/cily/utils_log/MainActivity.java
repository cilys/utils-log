package com.cily.utils_log;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.cily.utils.log.DbUtils;
import com.cily.utils.log.L;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PackageManager pm = getPackageManager();
        boolean permission = (PackageManager.PERMISSION_GRANTED ==
                pm.checkPermission(Manifest.permission_group.STORAGE, getPackageName()));
        L.i("TAG", "permission = " + permission);

        boolean read = (PackageManager.PERMISSION_GRANTED ==
                pm.checkPermission(Manifest.permission.READ_EXTERNAL_STORAGE, getPackageName()));
        L.i("TAG", "read = " + read);

        boolean write = (PackageManager.PERMISSION_GRANTED ==
                pm.checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, getPackageName()));
        L.i("TAG", "write = " + write);

        boolean mount = (PackageManager.PERMISSION_GRANTED ==
                pm.checkPermission(Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS, getPackageName()));
        L.i("TAG", "mount = " + mount);

        DbUtils.init(this, true, true);
        L.i("TAG", "******************111111");
    }
}
