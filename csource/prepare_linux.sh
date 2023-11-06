#!/bin/bash

set -e
set -x

DEBIAN_FRONTEND=noninteractive apt -y install clang xvfb libatk1.0-dev libatk-bridge2.0-dev libxkbcommon-dev libxcomposite-dev libxrandr-dev libgbm-dev libxdamage-dev libva2 libva-x11-2 libva-drm2
