package com.funplus.sdk.device;

import android.app.Application;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * The `UnityBridge` class is a wrapper layer that exposes native APIs to Unity layer.
 */

public class DeviceUtilsUnityBridge {

    private static final String LOG_TAG = "DeviceUtilsUnityBridge";

    /**
     * Get device's Android ID.
     *
     * @param application   The current application.
     * @return              The device's Android ID.
     */
    @NonNull
    public static String getAndroidId(@NonNull Application application) {
        return DeviceUtils.getAndroidId(application);
    }

    /**
     * Get Google Play Advertising ID.
     *
     * @param application   The current application.
     * @return              The Google Play Advertising ID.
     */
    @Nullable
    public static String getPlayAdId(@NonNull Application application) {
        return DeviceUtils.getPlayAdId(application);
    }

    /**
     * Get device type.
     *
     * @param application   The current application.
     * @return              Device type: phone/tablet/unknown.
     */
    @NonNull
    public static String getDeviceType(@NonNull Application application) {
        return DeviceUtils.getDeviceType(application);
    }

    /**
     * Get device model name.
     *
     * @return              The device model name.
     */
    @NonNull
    public static String getModelName() {
        return DeviceUtils.getModelName();
    }

    /**
     * Get device manufacturer.
     *
     * @return              The device manufacturer.
     */
    @NonNull
    public static String getManufacturer() {
        return DeviceUtils.getManufacturer();
    }

    /**
     * Get the system name.
     *
     * @return              The system name.
     */
    @NonNull
    public static String getSystemName() {
        return DeviceUtils.getSystemName();
    }

    /**
     * Get the system version.
     *
     * @return              The system version.
     */
    @NonNull
    public static String getSystemVersion() {
        return DeviceUtils.getSystemVersion();
    }

    /**
     * Get the API level in a string format.
     *
     * @return              The API level in a string format.
     */
    @NonNull
    public static String getApiLevel() {
        return DeviceUtils.getApiLevel();
    }

    /**
     * Get app name.
     *
     * @param application   The current application.
     * @return              The app name.
     */
    @NonNull
    public static String getAppName(@NonNull Application application) {
        return DeviceUtils.getAppName(application);
    }

    /**
     * Get app version.
     *
     * @param application       The current application.
     * @return                  The app version.
     */
    @NonNull
    public static String getAppVersion(@NonNull Application application) {
        return DeviceUtils.getAppVersion(application);
    }

    /**
     * Get app language.
     *
     * @return                  The app language.
     */
    @NonNull
    public static String getAppLanguage() {
        return DeviceUtils.getAppLanguage();
    }

    /**
     * Get country.
     *
     * @return                  The country.
     */
    @NonNull
    public static String getCountry() {
        return DeviceUtils.getCountry();
    }

    /**
     * Get the network carrier name.
     *
     * @param application       The current application.
     * @return                  The network carrier name.
     */
    @NonNull
    public static String getNetworkCarrierName(@NonNull Application application) {
        return DeviceUtils.getNetworkCarrierName(application);
    }

    /**
     * Set screen brightness.
     *
     * @param application       The current application.
     * @param brightness        The value of brightness to be set, between 0 and 255 inclusively.
     * @return                  Status of this operation.
     */
    public static boolean setScreenBrightness(@NonNull Application application, int brightness) {
        return DeviceUtils.setScreenBrightness(application, brightness);
    }
}
