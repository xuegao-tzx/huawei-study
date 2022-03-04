package com.xcl.jianjia.provider;

import com.example.jianjia.ResourceTable;
import com.xcl.jianjia.bean.Article;
import com.xcl.jianjia.util.TextUtils;
import ohos.agp.components.*;
import ohos.app.Context;
import org.apache.commons.lang.StringEscapeUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author 裴云飞
 * @date 2021/1/26
 */

public class HomeArticleProvider extends BaseItemProvider {

    private List<Article.Data.Datas> mDatas;
    private final Context mContext;

    public HomeArticleProvider(Context context, List<Article.Data.Datas> datas) {
        mContext = context;
        mDatas = datas;
        if (mDatas == null) {
            mDatas = new ArrayList<>();
        }
    }

    @Override
    public int getCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return Optional.of(mDatas.get(position));
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Component getComponent(int position, Component componentP, ComponentContainer componentContainer) {
        Component component = componentP;
        ComponentHolder componentHolder = null;
        if (component == null) {
            component = LayoutScatter.getInstance(mContext).parse(ResourceTable.Layout_item, null, false);
            if (componentHolder == null) {
                componentHolder = new ComponentHolder(component);
                component.setTag(componentHolder);
            }
        } else {
            componentHolder = (ComponentHolder) component.getTag();
        }
        Article.Data.Datas data = mDatas.get(position);
        if (componentHolder != null && data != null ) {
            if (!TextUtils.isEmpty(data.title)) {
                // json里面有一些特殊符号，特殊符号会被gson转义，
                // StringEscapeUtils可以对转义的字符串进行反转义
                // 调用StringEscapeUtils的unescapeHtml方法，如果字符串中没有转义字符，
                // unescapeHtml方法会直接返回原字符串，否则会对字符串进行反转义。
                String title = StringEscapeUtils.unescapeHtml(data.title);
                componentHolder.title.setText(title);
            }
            if (!TextUtils.isEmpty(data.author)) {
                componentHolder.author.setText(data.author);
            }
            if (!TextUtils.isEmpty(data.niceDate)) {
                componentHolder.time.setText(data.niceDate);
            }
            if (!TextUtils.isEmpty(data.superChapterName)) {
                componentHolder.category.setText(data.superChapterName);
            }
        }
        return component;
    }

    private static class ComponentHolder {
        Text title;
        Text author;
        Text category;
        Text time;

        public ComponentHolder(Component component) {
            title = (Text) component.findComponentById(ResourceTable.Id_title);
            title.setTruncationMode(Text.TruncationMode.ELLIPSIS_AT_END);
            author = (Text) component.findComponentById(ResourceTable.Id_author_name);
            category = (Text) component.findComponentById(ResourceTable.Id_category_name);
            time = (Text) component.findComponentById(ResourceTable.Id_time_name);
        }
    }
}