package com.xcl.jianjia.slice;

import com.example.jianjia.ResourceTable;
import com.xcl.jianjia.MyApplication;
import com.xcl.jianjia.bean.Article;
import com.xcl.jianjia.provider.HomeArticleProvider;
import com.xcl.jianjia.bean.Symbol;
import com.xcl.jianjia.util.LogUtils;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.ListContainer;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import com.net.jianjia.Call;
import com.net.jianjia.Callback;
import com.net.jianjia.Response;

import java.io.File;
import java.io.InputStream;
import java.util.*;

public class MainAbilitySlice extends AbilitySlice {
    private static final HiLogLabel label = new HiLogLabel(HiLog.LOG_APP, 0x00234, "MainAbilitySlice");
    private ListContainer mListContainer;
    private HomeArticleProvider mHomeArticleProvider;
    List<Article.Data.Datas> mDatas;

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_main);
        mListContainer = (ListContainer) findComponentById(ResourceTable.Id_list);
        mDatas = new ArrayList<>();
        mHomeArticleProvider = new HomeArticleProvider(this, mDatas);
        mListContainer.setItemProvider(mHomeArticleProvider);
        // 从服务端获取数据
        getHomeArticle();
        GetMethod();
    }
    void GetMethod() {
        MyApplication.getInstance().getWan().getsymbols().enqueue(new Callback<Symbol>() {
            @Override
            public void onResponse(Call<Symbol> call, Response<Symbol> response) {
                if (response.isSuccessful()) {
                    // 请求成功
                    Iterator<Map.Entry<String, Symbol.Symbols>> iter = response.body().symbols.entrySet().iterator();
                    while (iter.hasNext()) {
                        Map.Entry<String, Symbol.Symbols> entry1 = iter.next();
                        HiLog.warn(label, "tzx xx=["+entry1.getKey()+"]");
                    }
                    /*
                    stringTitle = (String[]) stringTitle0.toArray(new String[]{});
                    getUITaskDispatcher().asyncDispatch(new Runnable() {
                        @Override
                        public void run() {
                            picker.setDisplayedData(stringTitle);
                            picker.setMinValue(0);
                            picker.setValueChangedListener(new Picker.ValueChangedListener() {
                                @Override
                                public void onValueChanged(Picker picker, int i, int i1) {
                                    if (i1 >= 0 && i1 < stringTitle.length) {
                                        picker.setMinValue(0);
                                        picker.setDisplayedData(stringTitle);
                                        picker.setMaxValue(stringTitle.length - 1);
                                    }
                                    DWZH6AbilitySlice.this.dw = i1;
                                }
                            });
                        }
                    });*/
                } else {
                    HiLog.warn(label, "tzx 接口故障，错误代码:" + response.code());
                }
            }

            @Override
            public void onFailure(Call<Symbol> call, Throwable t) {
                // 请求失败
                HiLog.warn(label, "tzx 网络错误，无法获取货币信息列表!");
                /*getUITaskDispatcher().asyncDispatch(new Runnable() {
                    @Override
                    public void run() {
                        String[] stringTitle = {
                                "网络错误",
                                "网络错误"
                        };
                        picker.setDisplayedData(stringTitle);
                        picker.setMinValue(0);
                        picker.setValueChangedListener(new Picker.ValueChangedListener() {
                            @Override
                            public void onValueChanged(Picker picker, int i, int i1) {
                                if (i1 >= 0 && i1 < stringTitle.length) {
                                    picker.setMinValue(0);
                                    picker.setDisplayedData(stringTitle);
                                    picker.setMaxValue(stringTitle.length - 1);
                                }
                                DWZH6AbilitySlice.this.dw = i1;
                            }
                        });
                    }
                });
                AGConnectCrash.getInstance().recordException(t);*/
                HiLog.error(label, t.getMessage());
            }
        });
    }
    /**
     * 下载文件，注意：下载文件的接口并不存在，开发者可以将示例中的接口替换成真实的下载文件接口
     */
    private void download() {
        MyApplication.getInstance().getWan().download().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                // onResponse方法在子线程中执行
                // 得到文件输入流后，就可以写文件了
                InputStream inputStream = response.body().byteStream();

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {

            }
        });
    }

    /**
     * 上传多个文件，注意：上传文件的接口并不存在，开发者可以将示例中的接口替换成真实的上传文件接口
     */
    private void uploadMultiFile() {
        File photoFile = new File(getExternalCacheDir(), "photo.png");
        RequestBody photoBody = RequestBody.create(MediaType.parse("image/png"), photoFile);

        File avatarFile = new File(getExternalCacheDir(), "avatar.png");
        RequestBody avatarBody = RequestBody.create(MediaType.parse("image/png"), avatarFile);

        Map<String, RequestBody> photos = new HashMap<>();
        photos.put("photo", photoBody);
        photos.put("avatar", avatarBody);
        MyApplication.getInstance().getWan().upload(photos).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                // 上传成功
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {
                // 上传失败
            }
        });
    }

    /**
     * 上传单个文件，注意：上传文件的接口并不存在，开发者可以将示例中的接口替换成真实的上传文件接口
     */
    private void uploadFile() {
        // 文件路径
        File file = new File(getExternalCacheDir(), "icon.png");
        // 创建请求体对象
        RequestBody photoBody = RequestBody.create(MediaType.parse("image/png"), file);
        MultipartBody.Part photo = MultipartBody.Part.createFormData("photos", "icon.png", photoBody);
        MyApplication.getInstance().getWan().upload(photo).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                // 上传成功
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {
                // 上传失败
            }
        });
    }

    /**
     * 从服务端获取数据
     */
    public void getHomeArticle() {
        MyApplication.getInstance().getWan().getHomeArticle(0).enqueue(new Callback<Article>() {
            @Override
            public void onResponse(Call<Article> call, Response<Article> response) {
                if (response.isSuccessful()) {
                    // 请求成功
                    setHomeArticle(response.body());
                }
            }

            @Override
            public void onFailure(Call<Article> call, Throwable t) {
                // 请求失败
                LogUtils.info("yunfei", t.getMessage());
            }
        });
    }

    @Override
    public void onActive() {
        super.onActive();
    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
    }

    public void setHomeArticle(Article article) {
        if (article == null || article.data == null || article.data.datas == null) {
            return;
        }
        mDatas.addAll(article.data.datas);
        // 刷新列表
        mHomeArticleProvider.notifyDataChanged();
    }

}
