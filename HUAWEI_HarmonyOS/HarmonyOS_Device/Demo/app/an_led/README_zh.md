## 简介

WIFI\_IOT\_APP组件，提供了扩展板上LED点亮示例代码。

## 目录

```
applications/sample/wifi-iot/         # sample模块目录
└── app
    ├── BUILD.gn                      # 模块构建脚本
    └── kled                          # 扩展板LED操作示例代码
        ├── BUILD.gn                  # APP构建脚本
        ├── README_zh.md              # 中文文档
        └── kled.c                    # 扩展板LED示例代码
```

## 涉及仓

applications\_sample\_wifi-iot

## 模块构建脚本写法
```
features = [
        "kled:kled",
    ]
```