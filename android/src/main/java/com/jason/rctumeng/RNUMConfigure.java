package com.jason.rctumeng;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build.VERSION_CODES;

import com.umeng.commonsdk.UMConfigure;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by wangfei on 17/9/14.
 */

public class RNUMConfigure {

    public static final int DEVICE_TYPE_PHONE = UMConfigure.DEVICE_TYPE_PHONE;
    public static final int DEVICE_TYPE_BOX = UMConfigure.DEVICE_TYPE_BOX;

    // SDK预初始化函数
    // preInit预初始化函数耗时极少，不会影响App首次冷启动用户体验
    public static void preInit(Context context,String appkey,String channel){
        UMConfigure.preInit(context, appkey, channel);
    }

    public static void init(Context context, String appkey, String channel, int type, String secret){
        initRN("react-native","1.0");
        UMConfigure.init(context, appkey, channel, type, secret);
    }
    @TargetApi(VERSION_CODES.KITKAT)
    private static void initRN(String v, String t){
        Method method = null;
        try {
            Class<?> config = Class.forName("com.umeng.commonsdk.UMConfigure");
            method = config.getDeclaredMethod("setWraperType", String.class, String.class);
            method.setAccessible(true);
            method.invoke(null, v,t);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void setLogEnabled(Boolean logEnabled) {
        UMConfigure.setLogEnabled(logEnabled);
    }
}
