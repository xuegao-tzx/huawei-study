package com.xcl.jianjia;

import com.net.jianjia.gson.GsonConverterFactory;
import com.xcl.jianjia.net.Wan;
import ohos.aafwk.ability.AbilityPackage;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import com.net.jianjia.JianJia;

public class MyApplication extends AbilityPackage {

    private static MyApplication instance;
    private JianJia mJianJia;
    private Wan mWan;

    public static MyApplication getInstance() {
        return instance;
    }

    /**
     * 获取全局的蒹葭对象
     *
     * @return 全局的蒹葭对象
     */
    public JianJia getJianJia() {
        return mJianJia;
    }

    /**
     * 获取全局的接口实例对象
     *
     * @return 全局的接口实例对象
     */
    public Wan getWan() {
        return mWan;
    }

    @Override
    public void onInitialize() {
        super.onInitialize();
        instance = this;
        // 创建日志拦截器
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        // 为OKHTTP添加日志拦截器
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();
        // 创建全局的蒹葭对象
        mJianJia = new JianJia.Builder()
                // 使用自定义的okHttpClient对象
                .callFactory(okHttpClient)
                .baseUrl("https://www.wanandroid.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mWan = mJianJia.create(Wan.class);
    }
}
