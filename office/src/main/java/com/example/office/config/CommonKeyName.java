package com.example.office.config;

/**
 * Created by YCY on 16/3/20.
 */
public interface CommonKeyName {
    /**
     * 阿里支付密钥
     */
    String mAlipayRsaPrivate = "mAlipayRsaPrivate";
    /**
     * qq appid
     */
    String mQQAppId = "mQQAppId";
    /**
     * 默认新浪授权回调地址
     */
    String mSinaCallbackUrl = "mSinaCallbackUrl";
    /**
     * build version
     */
    String mBuildVersion = "mBuildVersion";

    /**
     * 微信appid
     */
    String WX_APP_ID = "wx_app_id";
    /**
     * 新浪appkey
     */
    String SINA_APP_KEY = "sina_app_key";
    /**
     * 新浪scope
     */
    String SINA_SCOPE = "sina_scope";
    /**
     * 默认分享团描述
     */
    String shareGroupContent = "share_group_content";
    /**
     * 默认分享商品描述
     */
    String shareGoodsContent = "share_goods_content";
    /**
     * API domain
     */
    String apiDomain = "apiDomain";
    /**
     * meta API domain
     */
    String apiMetaDomain = "apiMetaDomain";
    /**
     * appId，拼好货换区时用到
     */
    String defaultAppId = "defaultAppId";
    /**
     * userAgent，webview userAgent
     */
    String userAgent = "userAgent";

    /**
     * sharedPreferences name
     */
    String spName = "spName";
    /**
     * App Domain
     */
    String appDomain = "appDomain";

    /**
     * API delay desc
     * default "多多粉们太热情了，服务器需要休息一下，请稍后重试~"
     */
    String apiDelayDesc = "apiDelayDesc";
}
