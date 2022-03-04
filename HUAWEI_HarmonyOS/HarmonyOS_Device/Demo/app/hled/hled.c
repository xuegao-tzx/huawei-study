#include <stdio.h>

#include <unistd.h>

#include "ohos_init.h"
#include "cmsis_os2.h"
#include "iot_gpio.h"

#define LED_TEST_GPIO 9 //根据HiSpark WIFI IOT套件上Hi3861电路图，可知核心板上灯为9号引脚
enum LedState {
    LED_ON = 0,
    LED_OFF,
    LED_SPARK,
};

enum LedState g_ledState = LED_SPARK;

static void *LedTask(const char *arg)
{
    (void)arg;
    while (1) {
        switch (g_ledState) {
            case LED_ON:
                IoTGpioSetOutputVal(LED_TEST_GPIO, 1);
                usleep(300000);//也可用sleep函数和osDelay函数
                break;
            case LED_OFF:
                IoTGpioSetOutputVal(LED_TEST_GPIO, 0);
                usleep(300000);
                break;
            case LED_SPARK:
                IoTGpioSetOutputVal(LED_TEST_GPIO, 0);
                usleep(300000);
                IoTGpioSetOutputVal(LED_TEST_GPIO, 1);
                usleep(300000);
                break;
            default:
                usleep(300000);
                break;
        }
    }

    return NULL;
}

static void LedExampleEntry(void)
{
    osThreadAttr_t attr;

    IoTGpioInit(LED_TEST_GPIO);//初始化引脚
    IoTGpioSetDir(LED_TEST_GPIO, IOT_GPIO_DIR_OUT);//设置引脚输出
    //线程有关
    attr.name = "LedTask";
    attr.attr_bits = 0U;
    attr.cb_mem = NULL;
    attr.cb_size = 0U;
    attr.stack_mem = NULL;
    attr.stack_size = 512;
    attr.priority = 25;

    if (osThreadNew((osThreadFunc_t)LedTask, NULL, &attr) == NULL) {
        printf("[LedExample] Falied to create LedTask!\n");
    }
}

SYS_RUN(LedExampleEntry);
