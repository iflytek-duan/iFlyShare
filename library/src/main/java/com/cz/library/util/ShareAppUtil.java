package com.cz.library.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName：ShareAppUtil
 * Description：TODO<app分享工具类>
 * Author：zihao
 * Date：2016/4/18 17:17
 * Email：crazy.zihao@gmail.com
 * Version：v3.0 [对该工具类进行重新架构梳理 edit by zihao 2018/4/24]
 */
public class ShareAppUtil {

    /**
     * 检查是否存在指定的App
     *
     * @param context     context
     * @param packageName 要进行校验的包
     * @return true：存在；false：不存在。
     */
    public static boolean monitorAPP(Context context, String packageName) {
        PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> packageInfoList = packageManager.getInstalledPackages(0);
        List<String> packageNames = new ArrayList<>();
        if (packageInfoList != null) {
            for (PackageInfo packageInfo
                    : packageInfoList) {
                String packName = packageInfo.packageName;
                packageNames.add(packName);
            }
        }
        return packageNames.contains(packageName);
    }

    /**
     * 以发送消息的方式进行分享
     *
     * @param activity Activity
     * @param SMSBody  消息内容
     */
    public static void sendMessageWayShare(Activity activity, String SMSBody) {
        try {
            Uri smsToUri = Uri.parse("smsto:");
            Intent intent = new Intent(Intent.ACTION_SENDTO, smsToUri);
            if (SMSBody != null)
                intent.putExtra("sms_body", SMSBody);
            else {
                intent.putExtra("sms_body", "");
            }
            activity.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}