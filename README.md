# Device Utils for Android

## Requirements

- Android Studio 2.2+
- Android API level 16+
- Gradle 2.3+

## Table of Contents

- [Integration](#integration)
  - [Add the SDK to Your Project](#add-the-sdk-to-your-project)
  - [Add Google Play Services](#add-google-play-services)
- [Usage](#usage)

## Integration

### Add the SDK to Your Project

Add the `funplus-android-sdk-device-utils-<version>.jar` file to your app and set as dependency.

### Add Google Play Services

FunPlus SDK uses the [Google Advertising ID](https://support.google.com/googleplay/android-developer/answer/6048248?hl=en) to uniquely identify devices. To allow the SDK to use the Google Advertising ID, you must integrate the [Google Play Services](http://developer.android.com/google/play-services/setup.html). If you haven't done this yet, please open the `build.gradle` file of your app and add the following line to the `dependencies` block:

```groovy
compile 'com.google.android.gms:play-services-analytics:9.4.0'
```

## Usage

Before using the APIs, import the package to your source files.

```java
import com.funplus.sdk.device.DeviceUtils;
```

### Get Play Advertising ID

This method might return `null`.

```java
String playAdId = DeviceUtils.getPlayAdId(context);
```

### Get Android ID

```java
String androidId = DeviceUtils.getAndroidId(context);
```

### Get Device Type

This method returns one of the following strings:

- phone
- tablet
- unknown

```java
String deviceType = DeviceUtils.getDeviceType(context);
```

### Get Device Model Name

```java
String modelName = DeviceUtils.getModelName();
```

### Get Device Manufacturer

```java
String manufacturer = DeviceUtils.getManufacturer();
```

### Get System Name

```java
String systemName = DeviceUtils.getSystemName();
```

### Get System Version

```java
String systemVersion = DeviceUtils.getSystemVersion();
```

### Get API Level

```java
String apiLevel = DeviceUtils.getApiLevel();
```

### Get App Name

```java
String appName = DeviceUtils.getAppName();
```

### Get App Version

```java
String appVersion = DeviceUtils.getAppVersion();
```

### Get App Display Language

This method gets the display language for the application, it might be different from the device language.

```java
String appLanguage = DeviceUtils.getAppLanguage();
```

### Get Network Carrier Name

```java
String carrier = DeviceUtils.getNetworkCarrierName(context);
```

### Set Screen Brightness

To use this API, add the following permission declaration before the `application` tag in your `AndroidManifest.xml` if they're not present already.

```xml
<uses-permission android:name="android.permission.WRITE_SETTINGS"/>
```

Value of the `brightness` parameter should be between 0 and 255. values less than 0 will be treat as 0, and values greater than 255 will be treat as 255.

Note that on Android 6.0+, it will return `false` and lead the user to a window that requests the `WRITE_SETTINGS` permission for the first time this method is called.

```java
int brightness = 160;
boolean isSuccess = DeviceUtils.setScreenBrightness(context, brightness);
```
