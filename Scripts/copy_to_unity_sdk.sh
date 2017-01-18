#!/usr/bin/env bash

# go to project root.
if [[ $(pwd) == *Scripts ]]; then
    cd ..
fi

ver=$(grep "VERSION = " DeviceUtils/src/main/java/com/funplus/sdk/device/DeviceUtils.java | sed "s/public static final String VERSION = //g" | tr -d ' ;"')
f=funplus-android-sdk-device-utils-$ver.jar

src=Release/$f
target_dir=../../unity/sdk-device-utils/Assets/FunPlus/DeviceUtils/Plugins/Android

if [ -f $target_dir/funplus-android-sdk-device-utils*.jar ]; then
    rm $target_dir/funplus-android-sdk-device-utils*
fi

dst=$target_dir/funplus-android-sdk-device-utils.jar

cp $src $dst

echo Copied $src to $dst
