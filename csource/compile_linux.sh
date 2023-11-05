#!/bin/bash

set -e
set -x

DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"
cd "$DIR"

ARCH="$1"
PLATFORM="linux"
CEF_DIR="$2"

clang++ \
    -I "$CEF_DIR" \
    -L "$CEF_DIR/Release" \
    -lcef \
    -std=c++17 \
    -DHELPER \
    "-Wl,-rpath,"'$ORIGIN' \
    -o "ceflib Helper" \
    thirdparty/backupsignalhandlers/signal_restore_posix.cpp \
    cef_linux.cpp


#dylib

clang++ \
    -fPIC \
    -I "$CEF_DIR" \
    -L "$CEF_DIR/Release" \
    -lcef \
    -std=c++17 \
    "-Wl,-rpath,"'$ORIGIN' \
    -shared \
    -o libcljcef.so \
    thirdparty/backupsignalhandlers/signal_restore_posix.cpp \
    cef_linux.cpp


cp libcljcef.so ../resources/extract/linux-x86-64
cp "ceflib Helper" ../resources/extract/linux-x86-64

cp libcljcef.so /tmp/com.phronemophobic.cef/
cp "ceflib Helper" /tmp/com.phronemophobic.cef/

BUILD_DIR=build-"$PLATFORM"-"$ARCH"

mkdir -p "$BUILD_DIR"
cp libcljcef.so "$BUILD_DIR"
cp 'ceflib Helper' "$BUILD_DIR"

echo "done"
