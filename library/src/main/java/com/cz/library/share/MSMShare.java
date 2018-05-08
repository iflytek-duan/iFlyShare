package com.cz.library.share;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.cz.library.util.ShareConstants;

/**
 * ClassName：MSMShare
 * Description：TODO<短信分享工具类>
 * Author：zihao
 * Date：2016/4/18 15:40
 * Email：crazy.zihao@gmail.com
 * Version：v3.0 [对该工具类进行重新架构梳理 edit by zihao 2018/4/24]
 */
public class MSMShare {

    public static MSMShare getInstance() {
        return ShareHolders.mInstance;
    }

    /**
     * 以短信的方式分享
     *
     * @param activity     Activity
     * @param shareBundle Bundle
     */
    public static void sendMessageWayShare(Activity activity, Bundle shareBundle) {
        try {
            Uri smsToUri = Uri.parse("smsto:");
            Intent intent = new Intent(Intent.ACTION_SENDTO, smsToUri);
            if (shareBundle != null)
                intent.putExtra("sms_body",
                        shareBundle.getString(ShareConstants.SHARE_SUMMARY, ""));
            else {
                intent.putExtra("sms_body", "");
            }
            activity.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static class ShareHolders {
        static MSMShare mInstance = new MSMShare();
    }
}