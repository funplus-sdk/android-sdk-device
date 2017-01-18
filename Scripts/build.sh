#!/usr/bin/env bash

# go to project root.
if [[ $(pwd) == *Scripts ]]; then
    cd ..
fi

ver=$(grep "VERSION = " DeviceUtils/src/main/java/com/funplus/sdk/device/DeviceUtils.java | sed "s/public static final String VERSION = //g" | tr -d ' ;"')
out=$(echo Release/funplus-android-sdk-device-utils-$ver.jar | tr -d ' ')

echo SDK version: $ver
echo Output destination: $out

# check output directory.
if [ -f $out ]; then
    read -p 'Jar file exists. This action will erase the existing file, are you sure? [yN] ' yn
    if [[ $yn != 'y' && $yn != 'Y' ]]; then
        echo exit
        exit
    else
        rm -rf $out
    fi
fi

echo

gradle jar

build_dir=DeviceUtils/build/libs
cp $build_dir/funplus-android-sdk-device-utils.jar $out
