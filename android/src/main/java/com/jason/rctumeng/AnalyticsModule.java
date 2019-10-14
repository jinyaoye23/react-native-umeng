package com.jason.rctumeng;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.ReadableNativeMap;
import com.facebook.react.bridge.ReadableType;
import com.umeng.analytics.MobclickAgent;

/**
 * Created by wangfei on 17/8/28.
 */

public class AnalyticsModule extends ReactContextBaseJavaModule {
    private ReactApplicationContext context;

    public static final String E_UM_NORMAL = "E_UM_NORMAL";
    final static String E_UM_GAME = "E_UM_GAME";
    public AnalyticsModule(ReactApplicationContext reactContext) {
        super(reactContext);
        context = reactContext;
    }

    @Override
    public String getName() {
        return "UMAnalyticsModule";
    }

    /**
     * add by shizhenzhen at 2019-10-12 begin
     * 二次封装setSessionContinueMillis、setScenarioType方法
     * @param num
     */
    public static void setSessionContinueMillis(int num){
        MobclickAgent.setSessionContinueMillis(num);
    }

    public static void setScenarioType(Context context, String scenarioTypeStr){
        MobclickAgent.EScenarioType scenarioType = null;
        if(scenarioTypeStr.equals(E_UM_NORMAL)){
            scenarioType = MobclickAgent.EScenarioType.E_UM_NORMAL;
        }else if(scenarioTypeStr.equals(E_UM_GAME)){
            scenarioType = MobclickAgent.EScenarioType.E_UM_GAME;
        }
        MobclickAgent.setScenarioType(context, scenarioType);
    }

    public static void onPause(Context context){
        MobclickAgent.onPause(context);

    }
    public static void onResume(Context context){
        MobclickAgent.onResume(context);

    }
    /* add by shizhenzhen at 2019-10-12 end */

    /********************************U-App统计*********************************/
    @ReactMethod
    public void onPageStart(String mPageName) {

        MobclickAgent.onPageStart(mPageName);

    }

    @ReactMethod
    public void onPageEnd(String mPageName) {

        MobclickAgent.onPageEnd(mPageName);

    }
    @ReactMethod
    public void onEvent(String eventId) {
        MobclickAgent.onEvent(context, eventId);
    }
    @ReactMethod
    public void onEventWithLabel(String eventId,String eventLabel) {
        MobclickAgent.onEvent(context, eventId, eventLabel);
    }
    @ReactMethod
    public void onEventWithMap(String eventId,ReadableMap map) {
        Map<String, String> rMap = new HashMap<String, String>();
        ReadableMapKeySetIterator iterator = map.keySetIterator();
        while (iterator.hasNextKey()) {
            String key = iterator.nextKey();
            if (ReadableType.Array == map.getType(key)) {
                rMap.put(key, map.getArray(key).toString());
            } else if (ReadableType.Boolean == map.getType(key)) {
                rMap.put(key, String.valueOf(map.getBoolean(key)));
            } else if (ReadableType.Number == map.getType(key)) {
                rMap.put(key, String.valueOf(map.getInt(key)));
            } else if (ReadableType.String == map.getType(key)) {
                rMap.put(key, map.getString(key));
            } else if (ReadableType.Map == map.getType(key)) {
                rMap.put(key, map.getMap(key).toString());
            }
        }
        MobclickAgent.onEvent(context, eventId, rMap);
    }
    @ReactMethod
    public void onEventWithMapAndCount(String eventId,ReadableMap map,int value) {
        Map<String, String> rMap = new HashMap();
        ReadableMapKeySetIterator iterator = map.keySetIterator();
        while (iterator.hasNextKey()) {
            String key = iterator.nextKey();
            if (ReadableType.Array == map.getType(key)) {
                rMap.put(key, map.getArray(key).toString());
            } else if (ReadableType.Boolean == map.getType(key)) {
                rMap.put(key, String.valueOf(map.getBoolean(key)));
            } else if (ReadableType.Number == map.getType(key)) {
                rMap.put(key, String.valueOf(map.getInt(key)));
            } else if (ReadableType.String == map.getType(key)) {
                rMap.put(key, map.getString(key));
            } else if (ReadableType.Map == map.getType(key)) {
                rMap.put(key, map.getMap(key).toString());
            }
        }
        MobclickAgent.onEventValue(context, eventId, rMap, value);
    }

}
