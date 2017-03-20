package com.example.office.config;


import com.example.office.util.Log.LogUtils;

import java.util.HashMap;

/**
 * Created by YCY on 16/3/20.
 */
public class CommonKeyValue {
    private String mAlipayRsaPrivate;
    private String WX_APP_ID;
    private String mQQAppId;
    private String mSinaCallbackUrl;
    private String SINA_SCOPE;
    private int mBuildVersion;
    private String SINA_APP_KEY;
    private String shareGoodsContent;
    private String shareGroupContent;
    private String apiDomain;
    private String apiMetaDomain;
    private static CommonKeyValue commonKeyValue = null;
    private String defaultAppId;
    private String userAgent;
    private String spName;
    private String appDomain;
    private String apiDelayDesc;

    private CommonKeyValue() {
    }

    public static CommonKeyValue getInstance() {
        if (commonKeyValue == null) {
            synchronized (CommonKeyValue.class) {
                if (commonKeyValue == null) {
                    commonKeyValue = new CommonKeyValue();
                }
            }
        }
        return commonKeyValue;
    }

    /**
     * 参数配置初始化
     *
     * @param param
     */
    public void initParms(HashMap<String, Object> param) {
        try {
            if (param.containsKey(CommonKeyName.mAlipayRsaPrivate)) {
                mAlipayRsaPrivate = (String) param.get(CommonKeyName.mAlipayRsaPrivate);
            }
            if (param.containsKey(CommonKeyName.mQQAppId)) {
                mQQAppId = (String) param.get(CommonKeyName.mQQAppId);
            }
            if (param.containsKey(CommonKeyName.WX_APP_ID)) {
                WX_APP_ID = (String) param.get(CommonKeyName.WX_APP_ID);
            }
            if (param.containsKey(CommonKeyName.mBuildVersion)) {
                mBuildVersion = (int) param.get(CommonKeyName.mBuildVersion);
            }
            if (param.containsKey(CommonKeyName.mSinaCallbackUrl)) {
                mSinaCallbackUrl = (String) param.get(CommonKeyName.mSinaCallbackUrl);
            }
            if (param.containsKey(CommonKeyName.SINA_SCOPE)) {
                SINA_SCOPE = (String) param.get(CommonKeyName.SINA_SCOPE);
            }
            if (param.containsKey(CommonKeyName.SINA_APP_KEY)) {
                SINA_APP_KEY = (String) param.get(CommonKeyName.SINA_APP_KEY);
            }
            if (param.containsKey(CommonKeyName.shareGroupContent)) {
                shareGroupContent = (String) param.get(CommonKeyName.shareGroupContent);
            }
            if (param.containsKey(CommonKeyName.shareGoodsContent)) {
                shareGoodsContent = (String) param.get(CommonKeyName.shareGoodsContent);
            }

            if (param.containsKey(CommonKeyName.apiDomain)) {
                apiDomain = (String) param.get(CommonKeyName.apiDomain);
            }
            if (param.containsKey(CommonKeyName.apiMetaDomain)) {
                apiMetaDomain = (String) param.get(CommonKeyName.apiMetaDomain);
            }
            if (param.containsKey(CommonKeyName.defaultAppId)) {
                defaultAppId = (String) param.get(CommonKeyName.defaultAppId);
            }
            if (param.containsKey(CommonKeyName.userAgent)) {
                userAgent = (String) param.get(CommonKeyName.userAgent);
            }
            if (param.containsKey(CommonKeyName.spName)) {
                spName = (String) param.get(CommonKeyName.spName);
            }
            if (param.containsKey(CommonKeyName.appDomain)) {
                appDomain = (String) param.get(CommonKeyName.appDomain);
            }
            if(param.containsKey(CommonKeyName.apiDelayDesc)){
                apiDelayDesc = (String)param.get(CommonKeyName.apiDelayDesc);
            }
        } catch (Exception e) {
            LogUtils.d("初始化常量异常 " + e.toString());
            e.printStackTrace();
        }
    }

    public String getmAlipayRsaPrivate() {
        return mAlipayRsaPrivate;
    }

    public String getWX_APP_ID() {
        return WX_APP_ID;
    }

    public String getmQQAppId() {
        return mQQAppId;
    }

    public String getmSinaCallbackUrl() {
        return mSinaCallbackUrl;
    }

    public String getSINA_SCOPE() {
        return SINA_SCOPE;
    }

    public int getmBuildVersion() {
        return mBuildVersion;
    }

    public String getSINA_APP_KEY() {
        return SINA_APP_KEY;
    }

    public String getShareGoodsContent() {
        return shareGoodsContent;
    }

    public String getShareGroupContent() {
        return shareGroupContent;
    }

    public String getApiDomain() {
        return apiDomain;
    }

    public String getApiMetaDomain() {
        return apiMetaDomain;
    }

    public String getDefaultAppId() {
        return defaultAppId;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public String getSpName() {
        return spName;
    }

    public String getAppDomain() {
        return appDomain;
    }

    public String getApiDelayDesc() {
        return apiDelayDesc;
    }

}
