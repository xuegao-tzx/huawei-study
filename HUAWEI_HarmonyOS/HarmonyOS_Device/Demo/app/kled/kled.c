#include <stdio.h>

#include <unistd.h>

#include "ohos_init.h"
#include "cmsis_os2.h"
#include "iot_gpio.h"
#include "hi_io.h" // OH3.0中由于没有厂商封装，故只能调用源码底层


static void *GPIOTask(const char *arg){
    (void)arg;
    int i=1;
    while (1)
    {
        IoTGpioSetOutputVal(12, IOT_GPIO_VALUE0);//IOT_GPIO_VALUE0代表关闭，12是引脚号
        IoTGpioSetOutputVal(11, IOT_GPIO_VALUE0);
        IoTGpioSetOutputVal(10,IOT_GPIO_VALUE0);
        IoTGpioSetOutputVal(12, IOT_GPIO_VALUE1);
        sleep(6);/*yellow*/
        IoTGpioSetOutputVal(12, IOT_GPIO_VALUE0);
        IoTGpioSetOutputVal(11, IOT_GPIO_VALUE1);
        sleep(30);/*green*/
        IoTGpioSetOutputVal(11, IOT_GPIO_VALUE0);
        IoTGpioSetOutputVal(10,IOT_GPIO_VALUE1);//IOT_GPIO_VALUE1代表开启，10是引脚号
        sleep(25);/*red*/
        IoTGpioSetOutputVal(10,IOT_GPIO_VALUE0);
        printf("[NOTICE] This is the: %d\n",i);
        if(i%10==0){
            //每10次同时亮一次
            IoTGpioSetOutputVal(12, IOT_GPIO_VALUE0);
            IoTGpioSetOutputVal(11, IOT_GPIO_VALUE0);
            IoTGpioSetOutputVal(10,IOT_GPIO_VALUE0);
            IoTGpioSetOutputVal(12, IOT_GPIO_VALUE1);
            IoTGpioSetOutputVal(11, IOT_GPIO_VALUE1);
            IoTGpioSetOutputVal(10,IOT_GPIO_VALUE1);
            //如果是炫彩灯板为白光，交通灯板为3个灯全亮
            printf("[NOTICE]This is the:10th,throw!\n");
            sleep(10);
        }
        i++;
    }
}


static void GPIOEntry(void){
    printf("Led Test!\n");
    osThreadAttr_t attr;
    //引脚初始化及定义
    IoTGpioInit(12);
    IoTGpioSetDir(12, IOT_GPIO_DIR_OUT);
    IoTGpioInit(11);
    IoTGpioSetDir(11, IOT_GPIO_DIR_OUT);
    IoTGpioInit(10);
    IoTGpioSetDir(10, IOT_GPIO_DIR_OUT);
    //进程相关
    attr.name = "GPIOTask";
    attr.attr_bits = 0U;
    attr.cb_mem = NULL;
    attr.cb_size = 0U;
    attr.stack_mem = NULL;
    attr.stack_size = 1024;
    attr.priority = 25;

    if (osThreadNew((osThreadFunc_t)GPIOTask, NULL, &attr) == NULL) {
        printf("[LedExample] Falied to create LedTask!\n");
    }
}

SYS_RUN(GPIOEntry);



