package com.cz.library.share;

import android.app.Activity;
import android.os.Bundle;

import com.cz.library.util.ShareConstants;
import com.tencent.connect.share.QQShare;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

/**
 * ClassName：TencentQQShare
 * Description：TODO<QQ分享工具类>
 * Author：zihao
 * Date：2016/4/18 15:40
 * Email：crazy.zihao@gmail.com
 * Version：v3.0 [对该工具类进行重新架构梳理 edit by zihao 2018/4/24]
 */
class TencentQQShare {
    private int shareType = com.tencent.connect.share.QQShare.SHARE_TO_QQ_TYPE_DEFAULT;
    private static Tencent mTencent;
    private static IUiListener qqShareListener = null;

    /**
     * getInstance
     *
     * @param activity Activity
     * @param APP_ID   APP_ID
     * @param listener IUiListener
     * @return TencentQQShare
     */
    static TencentQQShare getInstance(Activity activity, String APP_ID, IUiListener listener) {
        mTencent = Tencent.createInstance(APP_ID, activity.getApplicationContext());
        qqShareListener = listener;
        return ShareHolders.mInstance;
    }

    /**
     * QQ分享--带有网络图片
     *
     * @param activity        Activity
     * @param shareInfoBundle Bundle
     */
    void QQShareImage(Activity activity, Bundle shareInfoBundle) {
        Bundle params = new Bundle();
        params.putString(QQShare.SHARE_TO_QQ_TITLE,
                shareInfoBundle.getString(ShareConstants.SHARE_TITLE, ""));
        params.putString(QQShare.SHARE_TO_QQ_SUMMARY,
                shareInfoBundle.getString(ShareConstants.SHARE_SUMMARY, ""));
        params.putString(QQShare.SHARE_TO_QQ_TARGET_URL,
                shareInfoBundle.getString(ShareConstants.SHARE_TAGURL, ""));
        params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, shareType);
        params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL,
                shareInfoBundle.getString(ShareConstants.SHARE_IMAGE, ""));
        params.putString(QQShare.SHARE_TO_QQ_APP_NAME,
                shareInfoBundle.getString(ShareConstants.APP_NAME, ""));

        doShareToQQ(activity, params);
    }

    /**
     * QQ分享--带有本地图片
     *
     * @param activity        Activity
     * @param shareInfoBundle Bundle
     */
    void QQShareLocalImage(Activity activity, Bundle shareInfoBundle) {
        Bundle params = new Bundle();
        params.putString(QQShare.SHARE_TO_QQ_TITLE,
                shareInfoBundle.getString(ShareConstants.SHARE_TITLE, ""));
        params.putString(QQShare.SHARE_TO_QQ_SUMMARY,
                shareInfoBundle.getString(ShareConstants.SHARE_SUMMARY, ""));
        params.putString(QQShare.SHARE_TO_QQ_TARGET_URL,
                shareInfoBundle.getString(ShareConstants.SHARE_TAGURL, ""));
        params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, shareType);
        params.putString(QQShare.SHARE_TO_QQ_IMAGE_LOCAL_URL,
                shareInfoBundle.getString(ShareConstants.LOCAL_LOGO_IMG, ""));

        doShareToQQ(activity, params);
    }

    private void doShareToQQ(Activity activity, Bundle params) {
        if (qqShareListener == null) {
            qqShareListener = new IUiListener() {
                @Override
                public void onComplete(Object response) {

                }

                @Override
                public void onError(UiError uiError) {

                }

                @Override
                public void onCancel() {

                }
            };
        }
        mTencent.shareToQQ(activity, params, qqShareListener);
    }

    static class ShareHolders {
        static TencentQQShare mInstance = new TencentQQShare();
    }
}