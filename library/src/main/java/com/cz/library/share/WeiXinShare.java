package com.cz.library.share;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.cz.library.util.ShareConstants;
import com.cz.library.util.Util;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * ClassName：WeiXinShare
 * Description：TODO<微信分享工具类>
 * Author：zihao
 * Date：2016/4/18 16:58
 * Email：crazy.zihao@gmail.com
 * Version：v3.0 [对该工具类进行重新架构梳理 edit by zihao 2018/4/24]
 */
class WeiXinShare {
    private static IWXAPI api;

    static WeiXinShare getInstance(Context context, String appId) {
        api = WXAPIFactory.createWXAPI(context, appId);
        api.registerApp(appId);
        return ShareHolders.mInstance;
    }

    /**
     * 分享内容给好友
     *
     * @param context     Context
     * @param shareBundle Bundle
     */
    void shareToSession(Context context, Bundle shareBundle) {
        // 初始化一个WXWebpageObject对象，填写url
        WXWebpageObject webpage = new WXWebpageObject();
        webpage.webpageUrl = shareBundle.getString(ShareConstants.SHARE_TAGURL, "");

        // 用WXWebpageObject对象初始化一个WXMediaMessage对象，填写标题、描述
        WXMediaMessage mediaMessage = new WXMediaMessage(webpage);
        mediaMessage.title = shareBundle.getString(ShareConstants.SHARE_TITLE, "");
        mediaMessage.description = shareBundle.getString(ShareConstants.SHARE_SUMMARY, "");
        Bitmap bmp = BitmapFactory.decodeResource(context.getResources(),
                shareBundle.getInt(ShareConstants.LOCAL_LOGO_ID, 0));
        mediaMessage.thumbData = Util.bmpToByteArray(bmp, true);

        // 构造一个Req
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("webpage");// transaction字段用于唯一标识一个请求
        req.message = mediaMessage;
        req.scene = SendMessageToWX.Req.WXSceneSession;// WXSceneTimeline是朋友圈，WXSceneSession是好友
        api.sendReq(req);
    }

    /**
     * 分享内容至朋友圈
     *
     * @param context     Context
     * @param shareBundle Bundle
     */
    void shareToTimeLine(Context context, Bundle shareBundle) {
        // 初始化一个WXWebpageObject对象，填写url
        WXWebpageObject webpage = new WXWebpageObject();
        webpage.webpageUrl = shareBundle.getString(ShareConstants.SHARE_TAGURL, "");
        webpage.extInfo = "";

        // 用WXWebpageObject对象初始化一个WXMediaMessage对象，填写标题、描述
        WXMediaMessage mediaMessage = new WXMediaMessage(webpage);
        mediaMessage.title = shareBundle.getString(ShareConstants.SHARE_TITLE, "");
        mediaMessage.description = shareBundle.getString(ShareConstants.SHARE_SUMMARY, "");
        Bitmap bmp = BitmapFactory.decodeResource(context.getResources(),
                shareBundle.getInt(ShareConstants.LOCAL_LOGO_ID, 0));
        mediaMessage.thumbData = Util.bmpToByteArray(bmp, true);

        // 构造一个Req
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("webpage");// transaction字段用于唯一标识一个请求
        req.message = mediaMessage;
        req.scene = SendMessageToWX.Req.WXSceneTimeline;// WXSceneTimeline是朋友圈，WXSceneSession是好友
        api.sendReq(req);
    }

    private String buildTransaction(String type) {
        return type + System.currentTimeMillis();
    }

    static class ShareHolders {
        static WeiXinShare mInstance = new WeiXinShare();
    }
}