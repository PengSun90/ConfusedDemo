package com.example.office.common;

/**
 * Created by YCY on 15/7/3.
 */
public class ApiErrorCode {
    public static int JSErrorCodeOK = 30000;
    public static int JSErrorCodeLoginFailed = 30001;
    public static int JSErrorCodePayFailed = 30100;
    public static int JSErrorCodeGetLocation = 30010;
    public static int JSErrorCodeNoApp = 30400;
    public static int JSErrorCodeShareFailedUserCancel = 30801;//用户取消
    public static int JSErrorCodeShareFailedAuthDenied = 30802;//授权失败
    public static int JSErrorCodeShareFailedNoApp = 30803;//没装app
    public static int JSErrorCodeShareFailed = 30804;//其他失败情况

    public static int NoError = 0;//无错误
    public static int NomalError = 60000;//一般错误
    public static int ModuleInexistence = 60001;//模块不存在
    public static int MethodInexistence = 60002;//方法不存在
    public static int IllegalParameter = 60003;//非法参数
    public static int LastRequessIsProcessing = 60004;//正在处理上一个请求，用于同一时间只能处理一个的请求，例如AMLogin.authorize
    public static int UserRefused = 60005;//用户拒绝
    public static int UserCancel = 60006;//用户取消
    public static int NotInstalledWeChat = 60100;//未安装微信客户端
    public static int NotInstalledSina = 60110;//未安装微博客户端
    public static int NotInstalledQQ = 60120;//未安装手机QQ
    public static int ACCESSTOKEN_ERROR = 40001;//非法会话，或已过期
    public static int API_COUNTDOWN = 40002;//api倒数
}
