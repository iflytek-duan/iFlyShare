package com.cz.library.share;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.cz.library.util.ShareConstants;
import com.tencent.tauth.IUiListener;

/**
 * ClassName：ShareUtils
 * Description：TODO<分享工具类>
 * Author：zihao
 * Date：2018/4/26 09:37
 * Email：crazy.zihao@gmail.com
 * Version：v1.0
 */
public class ShareUtils {

    /**
     * 分享至QQ（网络图片）
     *
     * @param activity Activity
     * @param appId    应用申请的APP_ID
     * @param title    分享内容标题
     * @param summary  分享摘要
     * @param tagUrl   标记内容地址
     * @param imgUrl   图片url（网络图片）
     * @param appName  应用名称
     * @param listener 回调
     */
    public static void shareToQQ(@NonNull Activity activity, @NonNull String appId, String title, String summary,
                                 String tagUrl, String imgUrl, String appName, @NonNull IUiListener listener) {
        Bundle bundle = new Bundle();
        bundle.putString(ShareConstants.SHARE_TITLE, title);
        bundle.putString(ShareConstants.SHARE_SUMMARY, summary);
        bundle.putString(ShareConstants.SHARE_TAGURL, tagUrl);
        bundle.putString(ShareConstants.SHARE_IMAGE, imgUrl);
        bundle.putString(ShareConstants.APP_NAME, appName);
        TencentQQShare.getInstance(activity, appId, listener).QQShareImage(activity, bundle);
    }

    /**
     * 分享至QQ（本地图片）
     *
     * @param activity Activity
     * @param appId    应用申请的APP_ID
     * @param title    分享内容标题
     * @param summary  分享摘要
     * @param tagUrl   标记内容地址
     * @param imgUrl   localImgUrl
     * @param appName  应用名称
     * @param listener 回调
     */
    public static void shareToQQLocalImage(@NonNull Activity activity, @NonNull String appId, String title, String summary,
                                           String tagUrl, String imgUrl, String appName, @NonNull IUiListener listener) {
        Bundle bundle = new Bundle();
        bundle.putString(ShareConstants.SHARE_TITLE, title);
        bundle.putString(ShareConstants.SHARE_SUMMARY, summary);
        bundle.putString(ShareConstants.SHARE_TAGURL, tagUrl);
        bundle.putString(ShareConstants.SHARE_LOACL_IMAGE, imgUrl);
        bundle.putString(ShareConstants.APP_NAME, appName);
        TencentQQShare.getInstance(activity, appId, listener).QQShareLocalImage(activity, bundle);
    }

    /**
     * 分享至微信好友
     *
     * @param context  Context
     * @param appId    应用申请的APP_ID
     * @param tagUrl   标记内容地址
     * @param title    分享内容标题
     * @param summary  分享摘要
     * @param imgResId 应用logo ResId
     */
    public static void shareToWXSenceSession(@NonNull Context context, @NonNull String appId,
                                             String tagUrl, String title, String summary, int imgResId) {
        Bundle bundle = new Bundle();
        bundle.putString(ShareConstants.SHARE_TITLE, title);
        bundle.putString(ShareConstants.SHARE_SUMMARY, summary);
        bundle.putString(ShareConstants.SHARE_TAGURL, tagUrl);
        bundle.putInt(ShareConstants.LOCAL_LOGO_ID, imgResId);
        WeiXinShare.getInstance(context, appId).shareToSession(context.getApplicationContext(),
                bundle);
    }

    /**
     * 分享至微信朋友圈
     *
     * @param context  Context
     * @param appId    应用申请的APP_ID
     * @param tagUrl   标记内容地址
     * @param title    分享内容标题
     * @param summary  分享摘要
     * @param imgResId 应用logo ResId
     */
    public static void shareToWXSenceTimeLine(@NonNull Context context, @NonNull String appId,
                                              String tagUrl, String title, String summary, int imgResId) {
        Bundle bundle = new Bundle();
        bundle.putString(ShareConstants.SHARE_TITLE, title);
        bundle.putString(ShareConstants.SHARE_SUMMARY, summary);
        bundle.putString(ShareConstants.SHARE_TAGURL, tagUrl);
        bundle.putInt(ShareConstants.LOCAL_LOGO_ID, imgResId);
        WeiXinShare.getInstance(context, appId).shareToTimeLine(context.getApplicationContext(),
                bundle);
    }

}
