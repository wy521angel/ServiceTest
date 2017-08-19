package com.example.wy521angel.intentservicetest;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by wy521angel on 2017/5/9.
 */

public class UploadImgService extends IntentService {

    private static final String ACTION_UPLOAD_IMG = "com.example.wy521angel.intentservicetest.action.UPLOAD_IMAGE";
    public static final String EXTRA_IMG_PATH = "com.example.wy521angel.intentservicetest.extra.IMG_PATH";


    public static void startUploadImg(Context context, String path) {
        Intent intent = new Intent(context, UploadImgService.class);
        intent.setAction(ACTION_UPLOAD_IMG);
        intent.putExtra(EXTRA_IMG_PATH, path);
        context.startService(intent);
    }

    public UploadImgService() {
        super("UploadImgService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_UPLOAD_IMG.equals(action)) {
                final String path = intent.getStringExtra(EXTRA_IMG_PATH);
                handleUploadImg(path);
            }
        }
    }

    private void handleUploadImg(String path) {
        try {
            //模拟上传耗时
            Thread.sleep(3000);
//            Intent intent = new Intent(MainActivity.UPLOAD_RESULT);
//            intent.putExtra(EXTRA_IMG_PATH, path);
//            sendBroadcast(intent);

            //使用EventBus替代广播
            ImgEvent event = new ImgEvent();
            event.setPath(path);
            EventBus.getDefault().post(event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("TAG", "onCreate");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("TAG", "onDestroy");

    }
}






















