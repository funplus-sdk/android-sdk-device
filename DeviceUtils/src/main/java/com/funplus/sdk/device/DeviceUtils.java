package com.funplus.sdk.device;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;

/**
 * Created by yuankun on 17/01/2017.
 */

public class DeviceUtils {

    @NonNull
    public static String getApplicationName(@NonNull Context context) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        int stringId = applicationInfo.labelRes;
        return stringId == 0 ? applicationInfo.nonLocalizedLabel.toString() : context.getString(stringId);
    }

    /**
     * Get app's version.
     *
     * @param context       The current context.
     * @return              App's version.
     */
    @NonNull
    public static String getAppVersion(@NonNull Context context) {
        try {
            PackageManager pm = context.getPackageManager();
            String name = context.getPackageName();
            PackageInfo info = pm.getPackageInfo(name, 0);
            if (info.versionName != null) {
                return info.versionName + "." + info.versionCode;
            } else {
                return "unknown";
            }
        } catch (PackageManager.NameNotFoundException e) {
            return "unknown";
        }
    }
}
