package com.funplus.sdk.device;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.telephony.TelephonyManager;

import java.util.Locale;

/**
 * The <code>DeviceUtils</code> class is used to retrieve device and system information, and to
 * set device or system related attributes.
 */
public class DeviceUtils {

    private static final String PLAY_AD_ID_SAVED_KEY = "com.funplus.sdk.device.PlayAdId";

    /**
     * Get device's Android ID.
     *
     * @param context   The current context.
     * @return          The device's Android ID.
     */
    @NonNull
    public static String getAndroidId(@NonNull Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    /**
     * Get Google Play Advertising ID.
     *
     * @param context   The current context.
     * @return          The Google Play Advertising ID.
     */
    @Nullable
    public static String getPlayAdId(@NonNull Context context) {
        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String playAdId = preferences.getString(PLAY_AD_ID_SAVED_KEY, null);

        if (playAdId == null) {
            PlayServicesHelper.getPlayAdId(context, new PlayServicesHelper.PlayAdIdReadListener() {
                @Override
                public void onPlayAdIdRead(String playAdId) {
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString(PLAY_AD_ID_SAVED_KEY, playAdId);
                    editor.apply();
                }
            });
        }

        return playAdId;
    }

    /**
     * Get device type.
     *
     * @param context   The current context.
     * @return          Device type: phone/tablet/unknown.
     */
    @NonNull
    public static String getDeviceType(@NonNull Context context) {
        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();
        int screenLayout = configuration.screenLayout;
        int screenSize = screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK;

        switch (screenSize) {
            case Configuration.SCREENLAYOUT_SIZE_SMALL:
            case Configuration.SCREENLAYOUT_SIZE_NORMAL:
                return "phone";
            case Configuration.SCREENLAYOUT_SIZE_LARGE:
            case Configuration.SCREENLAYOUT_SIZE_XLARGE:
                return "tablet";
            default:
                return "unknown";
        }
    }

    /**
     * Get device model name.
     *
     * @return          The device model name.
     */
    @NonNull
    public static String getModelName() {
        return Build.MODEL;
    }

    /**
     * Get device manufacturer.
     *
     * @return          The device manufacturer.
     */
    @NonNull
    public static String getManufacturer() {
        return Build.MANUFACTURER;
    }

    /**
     * Get the system name.
     *
     * @return          The system name.
     */
    @NonNull
    public static String getSystemName() {
        return "android";
    }

    /**
     * Get the system version.
     *
     * @return          The system version.
     */
    @NonNull
    public static String getSystemVersion() {
        return Build.VERSION.RELEASE;
    }

    /**
     * Get the API level in a string format.
     *
     * @return          The API level in a string format.
     */
    @NonNull
    public static String getApiLevel() {
        return "" + Build.VERSION.SDK_INT;
    }

    /**
     * Get app name.
     *
     * @param context   The current context.
     * @return          The app name.
     */
    @NonNull
    public static String getAppName(@NonNull Context context) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        int stringId = applicationInfo.labelRes;
        return stringId == 0 ? applicationInfo.nonLocalizedLabel.toString() : context.getString(stringId);
    }

    /**
     * Get app version.
     *
     * @param context       The current context.
     * @return              The app version.
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

    /**
     * Get app language.
     *
     * @return              The app language.
     */
    @NonNull
    public static String getAppLanguage() {
        return Locale.getDefault().getDisplayLanguage();
    }

    /**
     * Get country.
     *
     * @return              The country.
     */
    @NonNull
    public static String getCountry() {
        return Locale.getDefault().getCountry();
    }

    /**
     * Get the network carrier name.
     *
     * @param context       The current context.
     * @return              The network carrier name.
     */
    @NonNull
    public static String getNetworkCarrierName(@NonNull Context context) {
        TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String carrierName = manager.getNetworkOperatorName();
        return carrierName == null || carrierName.isEmpty() ? "UNKNOWN" : carrierName;
    }

    /**
     * Set screen brightness.
     *
     * @param context       The current context.
     * @param brightness    The value of brightness to be set, between 0 and 255 inclusively.
     * @return              Status of this operation.
     */
    public static boolean setScreenBrightness(@NonNull Context context, int brightness) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.System.canWrite(context)) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
                intent.setData(Uri.parse("package:" + context.getPackageName()));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
                return false;
            }
        }

        if (brightness < 0) {
            brightness = 0;
        } else if (brightness > 255) {
            brightness = 255;
        }

        ContentResolver contentResolver = context.getContentResolver();
        Settings.System.putInt(contentResolver, Settings.System.SCREEN_BRIGHTNESS, brightness);

        return true;
    }
}
