package com.cz.iflyshare;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.cz.library.share.ShareUtils;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

/**
 * ClassName：SimpleActivity
 * Description：TODO<示例Activity>
 * Author：zihao
 * Date：2018/4/24 17:17
 * Email：crazy.zihao@gmail.com
 * Version：v1.0
 */
public class SimpleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);

        findViewById(R.id.btn_share).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ShareUtils.shareToQQ(SimpleActivity.this, tencent_appId, "分享测试内容",
//                        "百度一下，你就知道~", "http://www.baidu.com",
//                        "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec" +
//                                "=1524579808389&di=3e43a01ab953ccb1d8fdbbe58b258943&imgtype=0&src=" +
//                                "http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2F72f082025aafa" +
//                                "40f58182f40a164034f79f01954.jpg",
//                        App名称, mIUiListener);

                ShareUtils.shareToWXSenceSession(SimpleActivity.this, "wx_appId",
                        "http://www.baidu.com/", "分享内容测试",
                        "百度一下，你就知道", R.mipmap.ic_launcher);
            }
        });
    }

    private IUiListener mIUiListener = new IUiListener() {
        @Override
        public void onComplete(Object o) {

        }

        @Override
        public void onError(UiError uiError) {

        }

        @Override
        public void onCancel() {

        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Tencent.onActivityResultData(requestCode, resultCode, data, mIUiListener);
        if (requestCode == Constants.REQUEST_API) {
            if (resultCode == Constants.REQUEST_QQ_SHARE
                    || resultCode == Constants.REQUEST_QZONE_SHARE
                    || resultCode == Constants.REQUEST_OLD_SHARE) {
                Tencent.handleResultData(data, mIUiListener);
            }
        }
    }
}
