# FunPlus SDK Device utils

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

Add the `funplus-android-sdk-<version>.jar` file to your app and set as dependency.

### Add Google Play Services

FunPlus SDK uses the [Google Advertising ID](https://support.google.com/googleplay/android-developer/answer/6048248?hl=en) to uniquely identify devices. To allow the SDK to use the Google Advertising ID, you must integrate the [Google Play Services](http://developer.android.com/google/play-services/setup.html). If you haven't done this yet, please open the `build.gradle` file of your app and add the following line to the `dependencies` block:

```groovy
compile 'com.google.android.gms:play-services-analytics:9.4.0'
```

### Add Permissions

Add the following permission declaration before the `application` tag in your `AndroidManifest.xml` if they're not present already.

```xml
<uses-permission android:name="android.permission.WRITE_SETTINGS"/>
```

## Usage

### Get the Play Advertising ID

```java
String playAdId = DeviceUtils.getPlayAdId(context);
```

The returning value might be null.

### Get the Android ID

```java
String androidId = DeviceUtils.getAndroidId(context);
```

### Get the Device Type

```java
String deviceType = DeviceUtils.getDeviceType(context);
```

The returning value is one of the following strings:

* phone
* tablet
* unknown

### Get Device's Model Name

```java
String modelName = DeviceUtils.getModelName();
```

### Get Device's Manufacturer

```java
String manufacturer = DeviceUtils.getManufacturer();
```

### Get the System Name

```java
String systemName = DeviceUtils.getSystemName();
```

### Get the System Version

```java
String systemVersion = DeviceUtils.getSystemVersion();
```

### Get the API Level

```java
String apiLevel = DeviceUtils.getApiLevel();
```

### Get App's Name

```java
String appName = DeviceUtils.getAppName();
```

### Get App's Version

```java
String appVersion = DeviceUtils.getAppVersion();
```

### Get App's Language

```java
String appLanguage = DeviceUtils.getAppLanguage();
```

### Set the Screen Brightness

```java
int brightness = 160;
DeviceUtils.setScreenBrightness(brightness);
```

Value of the `brightness` parameter should be between 0 and 255. values less than 0 will be treat as 0, and values greater than 255 will be treat as 255.