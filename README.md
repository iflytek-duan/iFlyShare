# iFlyShare

**更新日志**
- v3.0 2018/05/08 全新QQ、微信分享功能集成。
---

**使用说明**
- 支持分享到QQ功能（网络图片、本地图片）
- 支持分享到微信会话、朋友圈功能
---

**使用方式：**

- 在根目录的build.gradle中添加jitPack配置：
```java
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

- 在module的build.gradle中执行implementation依赖添加操作：
```java
implementation 'com.github.iflytek-duan:iFlyShare:3.1.0'
```

- 在app的AndroidManifest.xml中添加以下内容
  - 添加权限
  ```java
    <uses-permission android:name="android.permission.INTERNET"/>
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
    <uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/>
    <uses-permission android:name="android.permission.MOUNT_UNMOUNT_FILESYSTEMS"/>
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
    <uses-permission android:name="android.permission.CAMERA"/>
    <uses-permission android:name="android.permission.RECORD_AUDIO"/>
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
  ```
  - 添加注册信息(QQ分享需要)
  ```java
  <!-- 腾讯QQ分享相关配置 -->
  <activity
    android:name="com.tencent.tauth.AuthActivity"
    android:launchMode="singleTask"
    android:noHistory="true">
      <intent-filter>
        <action android:name="android.intent.action.VIEW"/>

        <category android:name="android.intent.category.DEFAULT"/>
        <category android:name="android.intent.category.BROWSABLE"/>

        <!-- 这里应该填入tencent+您的appId，如appId为123456，则应该填入tencent123456 -->
        <data android:scheme="tencent123456"/>
      </intent-filter>
    </activity>
    <activity
      android:name="com.tencent.connect.common.AssistActivity"
      android:screenOrientation="portrait"
      android:theme="@android:style/Theme.Translucent.NoTitleBar"/>
  ```
    ** 注意：这里要填入正确的tencent+appId。**
- 在QQ分享相关Activity中添加以下代码
  ```java
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
  ```
