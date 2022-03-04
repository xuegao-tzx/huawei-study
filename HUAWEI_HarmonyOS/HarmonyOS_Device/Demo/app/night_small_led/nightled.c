#include <stdio.h>

#include <unistd.h>

#include "ohos_init.h"
#include "cmsis_os2.h"
#include "iot_gpio.h"
//由于自OH2.0起，就没有厂商封装的函数了，故只能调用系统底层hi_的头文件
#include "hi_io.h" 
#include "hi_adc.h"
#include "hi_errno.h"
#include "hi_gpio.h"
#include "hi_pwm.h"
static void *GPIOTask(const char *arg){
    (void)arg;
    hi_u16 data;

    while(1){
    if(hi_adc_read(HI_ADC_CHANNEL_4, &data, HI_ADC_EQU_MODEL_4, HI_ADC_CUR_BAIS_DEFAULT, 0) != HI_ERR_SUCCESS){
            printf("ADC read error!\n");//光敏传感器读取异常时
        }else{
            //通过pwm控制当环境光强度增大时就减弱白色灯亮度
            hi_pwm_start(HI_PWM_PORT_PWM1, 65400*18/(1900-(unsigned int)data),65400);
            hi_pwm_start(HI_PWM_PORT_PWM2,  65400*18/(1900-(unsigned int)data),65400);
            hi_pwm_start(HI_PWM_PORT_PWM3,  65400*18/(1900-(unsigned int)data),65400);
            printf("%d",(unsigned int)data);
            osDelay(10);
            hi_pwm_stop(HI_PWM_PORT_PWM1);
            hi_pwm_stop(HI_PWM_PORT_PWM2);
            hi_pwm_stop(HI_PWM_PORT_PWM3);
        }
        //一下为人体红外传感器的使用
        /*if(hi_adc_read(HI_ADC_CHANNEL_3, &value, HI_ADC_EQU_MODEL_4, HI_ADC_CUR_BAIS_DEFAULT, 0) != HI_ERR_SUCCESS){
            printf("[ERROR] PEO read error!\n");
        }else{
            if((unsigned int)value>=1700){
                printf("[WARN] People is comming!\n");
                IoTGpioSetOutputVal(10,IOT_GPIO_VALUE1);//当人靠近时亮红灯
            }else{
                //否则亮青灯
                IoTGpioSetOutputVal(10,IOT_GPIO_VALUE0);
                IoTGpioSetOutputVal(11,IOT_GPIO_VALUE1);
                IoTGpioSetOutputVal(12,IOT_GPIO_VALUE1);
            }
        }*/
    }
}


static void GPIOEntry(void){
    printf("Led Test!\n");
    osThreadAttr_t attr;

    IoTGpioInit(12);
    IoTGpioSetDir(12, IOT_GPIO_DIR_OUT);
    IoTGpioInit(11);
    IoTGpioSetDir(11, IOT_GPIO_DIR_OUT);
    IoTGpioInit(10);
    IoTGpioSetDir(10, IOT_GPIO_DIR_OUT);
    //初始化底层gpio引脚
    hi_gpio_init();
    hi_io_set_func(HI_IO_NAME_GPIO_7, HI_IO_FUNC_GPIO_7_GPIO);//引脚复用
    hi_io_set_func(HI_IO_NAME_GPIO_9, HI_IO_FUNC_GPIO_9_GPIO);
    //初始化引脚及引脚复用，建议参考hi_io.h文件
    hi_gpio_set_dir(HI_IO_NAME_GPIO_9, IOT_GPIO_DIR_IN);
    hi_gpio_set_dir(HI_IO_NAME_GPIO_7, IOT_GPIO_DIR_IN);
    hi_io_set_func(10, HI_IO_FUNC_GPIO_10_PWM1_OUT);
    hi_io_set_func(11, HI_IO_FUNC_GPIO_11_PWM2_OUT);
    hi_io_set_func(12, HI_IO_FUNC_GPIO_12_PWM3_OUT);
    //初始化灯的引脚对应的PWM控制器
    hi_pwm_init(HI_PWM_PORT_PWM1);
    hi_pwm_init(HI_PWM_PORT_PWM2);
    hi_pwm_init(HI_PWM_PORT_PWM3);
    //线程相关
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



