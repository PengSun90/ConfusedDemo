package com.example.office.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.example.office.app.BaseApplication;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by shanqiu on 2016/5/2.
 */
public class PreferenceUtils {
    private static PreferenceUtils mInstance;
    private SharedPreferences mSp;
    private static final String DAILY_CHECK_PREFIX = "daily_check_prefix_";
    private static final String FIRST_TIME_PRFIX = "first_time_prefix_";
    private static final String PREFERENCE_NAME = "pdd_config";
    /**
     * a in-memory cache for frequently used key-values
     */
    private Map<String, String> cachedValues = new HashMap<String, String>();

    private PreferenceUtils() {
    }

    public static synchronized PreferenceUtils shareInstance() {
        if (null == mInstance) {
            mInstance = new PreferenceUtils();
            mInstance.mSp = BaseApplication.getContext().getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        }
        return mInstance;
    }

    // 1 alipay 2 wxpay
    public int readLastPayType() {
        return this.mSp.getInt(Key.payTypeLastest, 2);
    }

    public void writeLastPaytype(int payType) {
        SharedPreferences.Editor editor = this.mSp.edit();
        editor.putInt(Key.payTypeLastest, payType);
        editor.commit();
    }

    public void writeAppInfo(String app_info) {
        writeString(Key.app_info, app_info);
    }

    public String readAppInfo() {
        return readString(Key.app_info, "");
    }

    public String readUUID() {
        return mSp.getString(Key.uuid, "");
    }

    public void writeUUID(String muuid) {
        SharedPreferences.Editor editor = mSp.edit();
        editor.putString(Key.uuid, muuid);
        editor.commit();
    }

    public long readTotalMemorySize() {
        return mSp.getLong(Key.deviceTotalMemorySize, -1L);
    }

    public void writeTotalMemorySize(long tms) {
        SharedPreferences.Editor editor = mSp.edit();
        editor.putLong(Key.deviceTotalMemorySize, tms);
        editor.commit();
    }

    public int readWebviewWidth() {
        return mSp.getInt(Key.webviewWidth, -1);
    }

    public int readWebviewHeight() {
        return mSp.getInt(Key.webviewHeight, -1);
    }

    public void writeWebviewMetrics(int width, int height) {
        SharedPreferences.Editor editor = mSp.edit();
        editor.putInt(Key.webviewWidth, width);
        editor.putInt(Key.webviewHeight, height);
        editor.commit();
    }


    public String readDeviceId() {
        return mSp.getString(Key.deviceId, "");
    }

    public void writeDeviceId(String deviceId) {
        SharedPreferences.Editor editor = mSp.edit();
        editor.putString(Key.deviceId, deviceId);
        editor.commit();
    }

    public String readMacAddress() {
        return mSp.getString(Key.macAddress, "");
    }

    public void writeMacAddress(String macAddress) {
        SharedPreferences.Editor editor = mSp.edit();
        editor.putString(Key.macAddress, macAddress);
        editor.commit();
    }

    public String readSimSerialNumber() {
        return mSp.getString(Key.simSerialNumber, "");
    }

    public void writeSimSerialNumber(String simSerialNumber) {
        SharedPreferences.Editor editor = mSp.edit();
        editor.putString(Key.simSerialNumber, simSerialNumber);
        editor.commit();
    }

    public String readSerialNumber() {
        return mSp.getString(Key.serialNumber, "");
    }

    public void writeSerialNumber(String serialNumber) {
        SharedPreferences.Editor editor = mSp.edit();
        editor.putString(Key.serialNumber, serialNumber);
        editor.commit();
    }

    public String readAndroidId() {
        return mSp.getString(Key.androidId, "");
    }

    public void writeAndroidId(String androidId) {
        SharedPreferences.Editor editor = mSp.edit();
        editor.putString(Key.androidId, androidId);
        editor.commit();
    }

    public String readJSCommonParams(String key) {
        String value = cachedValues.get(key);
        if (TextUtils.isEmpty(value)) {
            value = mSp.getString(key, "");
        }
        return value;
    }

    public void writeJSCommonParams(String jsKey, String jsValue) {
        cachedValues.put(jsKey, jsValue);
        SharedPreferences.Editor editor = mSp.edit();
        editor.putString(jsKey, jsValue);
        editor.commit();
    }

    public void writeWebviewUserAgent(String userAgentString) {
        SharedPreferences.Editor editor = mSp.edit();
        editor.putString(Key.userAgentString, userAgentString);
        editor.commit();
    }

    public String readWebviewUserAgent() {
        return mSp.getString(Key.userAgentString, "");
    }

    public void writeUmengRegId(String registrationId) {
        SharedPreferences.Editor editor = mSp.edit();
        editor.putString(Key.umeng_reg_id, registrationId);
        editor.commit();
    }

    public String readUmengRegId() {
        return mSp.getString(Key.umeng_reg_id, "");
    }

    public void writeCurPDDVersion(String key, String version) {
        SharedPreferences.Editor editor = mSp.edit();
        editor.putString(key, version);
        editor.commit();
    }

    public String readCurPDDVersion(String key) {
        return mSp.getString(key, "");
    }

    public void writeDailyCheckValue(String key, long dateLong) {
        SharedPreferences.Editor editor = mSp.edit();
        editor.putLong(DAILY_CHECK_PREFIX + key, dateLong);
        editor.commit();
    }

    public Long readDailyCheckValue(String key) {
        return mSp.getLong(DAILY_CHECK_PREFIX + key, 0);
    }

    public void writeFirstTimeValue(String key) {
        SharedPreferences.Editor editor = mSp.edit();
        editor.putBoolean(FIRST_TIME_PRFIX + key, false);
        editor.commit();
    }

    public boolean readFirstTimeValue(String key) {
        return mSp.getBoolean(FIRST_TIME_PRFIX + key, true);
    }

    public void writeConversationsData(String data, String accessToken) {
        SharedPreferences.Editor editor = this.mSp.edit();
        editor.putString(Key.conversationsAccessToken, accessToken);
        editor.putString(Key.conversationsCache, data);
        editor.commit();
    }

    public String readConversationsData(String accessToken) {
        if (TextUtils.isEmpty(accessToken)) {
            return "";
        }
        String mAccessToken = mSp.getString(Key.conversationsAccessToken, "");
        if (TextUtils.isEmpty(mAccessToken) || !mAccessToken.equals(accessToken)) {
            return "";
        } else {
            return mSp.getString(Key.conversationsCache, "");
        }
    }

    public void writeHaitaoMarker(long lastModifyTime) {
        writeLong(Key.haitaoMarker, lastModifyTime);
    }

    public long readHaitaoMarker() {
        return readLong(Key.haitaoMarker, 0L);
    }

    public void writeTempPhotoPath(String path) {
        writeString(Key.tempPhotoPath, path);
    }

    public String readTempPhotoPath() {
        return readString(Key.tempPhotoPath, "");
    }

    public void writeLastCheckUpdateTime(long timeInMs) {
        writeLong(Key.lastCheckUpdateTime, timeInMs);
    }

    public long readLastCheckUpdateTime() {
        return readLong(Key.lastCheckUpdateTime, -1);
    }

    public void writeIgnoreVersion(int version) {
        writeInt(Key.ignoreVersion, version);
    }

    public int readIgnoreVersion() {
        return readInt(Key.ignoreVersion, -1);
    }

    public void writeUpgradeText(String text) {
        writeString(Key.upgradeText, text);
    }

    public String readUpgradeText() {
        return readString(Key.upgradeText, "");
    }

    public void writeChannel(String channel) {
        writeString(Key.channel, channel);
    }

    public String readChannel() {
        return readString(Key.channel, "official");
    }

    public boolean isWebpSupport() {
        return readBoolean(Key.isWebpSupport, false);
    }

    public void setWebpSupport(boolean support) {
        writeBoolean(Key.isWebpSupport, support);
    }

    public void addWebpRetryCnt() {
        int retryCnt = getWebpRetryCnt();
        retryCnt++;
        writeInt(Key.webpRetryCnt, retryCnt);
    }

    public int getWebpRetryCnt() {
        return readInt(Key.webpRetryCnt, 0);
    }

    public void writeString(String key, String value) {
        SharedPreferences.Editor editor = mSp.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public String readString(String key, String def) {
        return mSp.getString(key, def);
    }

    public void writeInt(String key, int value) {
        SharedPreferences.Editor editor = mSp.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public int readInt(String key, int def) {
        return mSp.getInt(key, def);
    }

    public void writeLong(String key, long value) {
        SharedPreferences.Editor editor = mSp.edit();
        editor.putLong(key, value);
        editor.commit();
    }

    public long readLong(String key, long def) {
        return mSp.getLong(key, def);
    }

    private void writeBoolean(String key, boolean value) {
        SharedPreferences.Editor editor = mSp.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    private boolean readBoolean(String key, boolean def) {
        return mSp.getBoolean(key, def);
    }

    public void remove(String key) {
        mSp.edit().remove(key).commit();
    }

    public void writeApiUID(String apiUID) {
        SharedPreferences.Editor editor = mSp.edit();
        editor.putString(Key.apiUID, apiUID);
        editor.commit();
    }

    public String readApiUID() {
        return mSp.getString(Key.apiUID, "");
    }

    public void writeFirstInstalled(boolean isFirstInstalled) {
        SharedPreferences.Editor editor = mSp.edit();
        editor.putBoolean(Key.isFirstInstalled, isFirstInstalled);
        editor.commit();
    }

    /**
     * 默认true
     *
     * @return
     */
    public boolean readFirstInstalled() {
        return mSp.getBoolean(Key.isFirstInstalled, true);
    }

    public void writeFirstBoot(boolean isFirstBoot) {
        writeBoolean(Key.isFirstBoot, isFirstBoot);
    }

    /**
     * 默认true
     *
     * @return
     */
    public boolean readFirstBoot() {
        boolean result = mSp.getBoolean(Key.isFirstBoot, true);
        writeFirstBoot(false);
        return result;
    }

    public void writeFirstLogin(boolean isFirstLogin) {
        writeBoolean(Key.isFirstLogin, isFirstLogin);
    }

    /**
     * 默认true
     *
     * @return
     */
    public boolean readFirstLogin() {
        boolean result = mSp.getBoolean(Key.isFirstLogin, true);
        writeFirstLogin(false);
        return result;
    }

    public boolean readIsPushEnabled() {
        return mSp.getBoolean(Key.isPushEnabled, false);
    }

    public void writeIsPushEnabled(boolean isEnable) {
        SharedPreferences.Editor editor = mSp.edit();
        editor.putBoolean(Key.isPushEnabled, isEnable);
        editor.commit();
    }

    public void writeDebugAppDomain(String appDomain) {
        SharedPreferences.Editor editor = mSp.edit();
        editor.putString(Key.debugAppDomain, appDomain);
        editor.commit();
    }

    public String readDebugAppDomain() {
        return mSp.getString(Key.debugAppDomain, "");
    }

    public interface Key {
        String payTypeLastest = "pay_type_lastest";

        String app_info = "app_info";
        String uuid = "device_uuid";
        String channel = "channel";

        String webviewWidth = "webviewWidth";
        String webviewHeight = "webviewHeight";
        String deviceId = "deviceId";
        String macAddress = "macAddress";
        String simSerialNumber = "simSerialNumber";
        String serialNumber = "serialNumber";
        String androidId = "androidId";
        String deviceTotalMemorySize = "device_total_memory_size";

        String jsCommonPrefix = "jsCommonKey_";
        String jsSecurePrefix = "jsSecureKey_";
        String userAgentString = "userAgentString";
        String curPDDVersion = "curePDDVersion";
        String curPDDVersionBoot = "curePDDVersionBoot";

        String umeng_reg_id = "umeng_reg_id";

        //chat list
        String conversationsCache = "conversationsCache";
        String conversationsAccessToken = "conversationsAccessToken";

        String haitaoMarker = "haitaoMarker";
        String tempPhotoPath = "tempPhotoPath";

        String apiUID = "cookie_api_uid";
        String isFirstInstalled = "isFirstInstalled";
        String isFirstBoot = "isFirstBoot";
        String isFirstLogin = "isFirstLogin";
        String isPushEnabled = "isPushEnabled";
        // version update
        String lastCheckUpdateTime = "lastcheckupdatetime";
        String ignoreVersion = "ignoreversion";
        String upgradeText = "upgradetext";

        String debugAppDomain = "debugAppDomain";

        String isWebpSupport = "isWebpSupport";
        String webpRetryCnt = "webpRetryCnt";
    }
}
