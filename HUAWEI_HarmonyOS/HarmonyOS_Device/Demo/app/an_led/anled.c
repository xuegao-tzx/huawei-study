#include <stdio.h>

#include <unistd.h>

#include "ohos_init.h"
#include "cmsis_os2.h"
#include "iot_gpio.h"
#include "hi_io.h"
static volatile IotGpioValue g_ledPinValue = IOT_GPIO_VALUE1;

//中断函数
static void OnButtonPressed(char* arg) {

    (void)arg;
    g_ledPinValue = !g_ledPinValue;
}

//Gpio线程函数
static void* GpioTask(const char* arg) {

    (void)arg;

    unsigned int rst = 0;
    //注册中断
    rst = IoTGpioRegisterIsrFunc(8, IOT_INT_TYPE_EDGE, IOT_GPIO_EDGE_FALL_LEVEL_LOW, OnButtonPressed, NULL);
    printf("GpioTask is already running and IotGpioRegisterIsrFunc return: %d\n", rst);

    while (1)
    {

        IoTGpioSetOutputVal(12, g_ledPinValue);
        osDelay(10);
    }

}


static void GPIOEntry(void) {

    printf("Led Test!\n");
    osThreadAttr_t attr;

    IoTGpioInit(12);
    IoTGpioSetDir(12, IOT_GPIO_DIR_OUT);
    IoTGpioInit(8);
    IoTGpioSetDir(8, IOT_GPIO_DIR_IN);
    hi_io_set_pull(HI_IO_NAME_GPIO_8, HI_IO_PULL_UP);

    attr.name = "GPIOTask";
    attr.attr_bits = 0U;
    attr.cb_mem = NULL;
    attr.cb_size = 0U;
    attr.stack_mem = NULL;
    attr.stack_size = 1024;
    attr.priority = 25;

    if (osThreadNew((osThreadFunc_t)GpioTask, NULL, &attr) == NULL) {

        printf("[GpioExample] Falied to create GpioTask!\n");
    }
}

SYS_RUN(GPIOEntry);
