## 简介

WIFI\_IOT\_APP组件，提供了炫彩灯板上LED及传感器控制示例代码。

## 目录

```
applications/sample/wifi-iot/         # sample模块目录
└── app
    ├── BUILD.gn                      # 模块构建脚本
    └── night_small_led               # 核心板LED操作示例代码
        ├── BUILD.gn                  # APP构建脚本
        ├── README_zh.md              # 中文文档
        └── nightled.c                # 核心板LED示例代码
```

## 涉及仓

applications\_sample\_wifi-iot

## 模块构建脚本写法
```
features = [
        "night_small_led:nightled",
    ]
```