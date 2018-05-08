package com.cz.library.share;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.cz.library.util.ShareConstants;

import java.util.ArrayList;

/**
 * ClassName：EmailShare
 * Description：TODO<Email分享工具类>
 * Author：zihao
 * Date：2016/4/18 15:37
 * Email：crazy.zihao@gmail.com
 * Version：v3.0 [对该工具类进行重新架构梳理 edit by zihao 2018/4/24]
 */
public class EmailShare {

    public static EmailShare getInstance() {
        return ShareHolders.mInstance;
    }

    /**
     * 发送带有附件的Email
     *
     * @param ac         Activity
     * @param shareInfoBundle Bundle
     */
    public void emailShareImage(Activity ac, Bundle shareInfoBundle) {
        Intent intent = new Intent(Intent.ACTION_SEND_MULTIPLE);
        intent.putExtra(Intent.EXTRA_TEXT,
                shareInfoBundle.getString(ShareConstants.SHARE_SUMMARY, ""));
        intent.putExtra(Intent.EXTRA_SUBJECT,
                shareInfoBundle.getString(ShareConstants.SHARE_TITLE, ""));
        ArrayList<Uri> imageUris = new ArrayList<Uri>();
        String path = shareInfoBundle.getString(
                ShareConstants.SHARE_LOACL_IMAGE, "");

        imageUris.add(Uri.parse(path));
        intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, imageUris);
        intent.setType("image/*");
        intent.setType("message/rfc882");
        Intent.createChooser(intent, "Choose Email Client");
        ac.startActivity(intent);
    }

    static class ShareHolders {
        static EmailShare mInstance = new EmailShare();
    }
}