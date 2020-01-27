package com.odm.fight_2019ncov.widget;

/**
 * @description: 像素密度工具类
 * @author: ODM
 * @date: 2020/1/28
 */
import android.content.Context;

public class DensityUtil {

    public static float dip2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return dpValue * scale;
    }
}