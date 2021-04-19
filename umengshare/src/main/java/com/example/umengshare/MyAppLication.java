package com.example.umengshare;

import android.app.Application;

import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;

public class MyAppLication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        UMConfigure.init(this,"6079299c5844f15425d8abfb"
                ,"umeng",UMConfigure.DEVICE_TYPE_PHONE,"");

        PlatformConfig.setWeixin("wxdc1e388c3822c80b","3baf1193c85774b3fd9d18447d76cab0");
        PlatformConfig.setWXFileProvider("com.tencent.sample2.fileprovider");

        PlatformConfig.setQQZone("101830139","5d63ae8858f1caab67715ccd6c18d7a5");
        PlatformConfig.setQQFileProvider("com.tencent.sample2.fileprovider");

        PlatformConfig.setSinaWeibo("3921700954","04b48b094faeb16683c32669824ebdad","http://sns.whalecloud.com");
        PlatformConfig.setSinaFileProvider("com.example.umengshare.fileprovider");
    }
}
