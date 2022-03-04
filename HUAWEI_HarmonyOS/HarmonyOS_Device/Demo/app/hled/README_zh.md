## 简介

WIFI\_IOT\_APP组件，提供了核心板上LED点亮示例代码。

## 目录

```
applications/sample/wifi-iot/         # sample模块目录
└── app
    ├── BUILD.gn                      # 模块构建脚本
    └── hled                          # 核心板LED操作示例代码
        ├── BUILD.gn                  # APP构建脚本
        ├── README_zh.md              # 中文文档
        └── hled.c                    # 核心板LED示例代码
```

## 涉及仓

applications\_sample\_wifi-iot

## 模块构建脚本写法
```
features = [
        "hled:hled",
    ]
```