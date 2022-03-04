## 简介

WIFI\_IOT\_APP组件，提供了第一个简单的串口输出信息示例代码。

## 目录

```
applications/sample/wifi-iot/         # sample模块目录
└── app
    ├── BUILD.gn                      # 模块构建脚本
    └── my_first_app                  # 串口信息输出操作示例代码
        ├── BUILD.gn                  # APP构建脚本
        ├── README_zh.md              # 中文文档
        └── hello_world.c             # 简单的hello word示例代码
```

## 涉及仓

applications\_sample\_wifi-iot

## 模块构建脚本写法
```
features = [
        "my_first_app:myapp1",
    ]
```