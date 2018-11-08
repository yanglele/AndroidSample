package com.example.yangl.androidsample.contentObserver;

import android.content.Context;
import android.database.ContentObserver;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.Toast;


/**
 * Desc:
 * Author:yl
 * Email:yangle06@anjuke.com
 * data:2018/1/12
 * version:
 * update:
 */

public class ContentObserverFragment extends Fragment {


    private SettingsContentObserver observer;
    private Handler mHandler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            if(!isAdded() || getActivity() == null)
                return;
            switch (msg.what){
                case 1 :
                    Toast.makeText(getActivity(),"fragment_un_mute",Toast.LENGTH_SHORT).show();
                    break;
                case 2 :
                    Toast.makeText(getActivity(),"fragment_mute",Toast.LENGTH_SHORT).show();
                    break;
                default:break;
            }
        }
    };


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        observer = new SettingsContentObserver(getActivity(),mHandler);
        getActivity().getApplicationContext().getContentResolver().registerContentObserver(Settings.System.CONTENT_URI,true,observer);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().getApplicationContext().getContentResolver().unregisterContentObserver(observer);
    }

    class SettingsContentObserver extends ContentObserver {

        private static final String TAG = "SettingsContentObserver";
        private Context context;
        private Handler handler;
        private int preVolume;
        /**
         * Creates a content observer.
         *
         * @param handler The handler to run {@link #onChange} on, or null if none.
         */
        public SettingsContentObserver(Context context , Handler handler) {
            super(handler);
            this.context = context;
            this.handler = handler;
            AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
            preVolume = audioManager.getStreamVolume(AudioManager.STREAM_SYSTEM);
        }

        @Override
        public boolean deliverSelfNotifications() {
            return super.deliverSelfNotifications();
        }

        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
            AudioManager audio = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
            int curVolume = audio.getStreamVolume(AudioManager.STREAM_SYSTEM);

            Message message = new Message();

            //此处是系统配置写入后调用
            //notice:we should use curVolume >= 1,because if we press volume key continue,
            //it may not call onchange() continue,it may be called when you release the key.eg.curVolume = 5
            if(preVolume == 0 && curVolume >= 1){
                //开启声音
                message.what = 1;
                handler.sendMessage(message);
            }else if(preVolume >= 1 && curVolume == 0){
                //静音
                message.what = 2;
                handler.sendMessage(message);
            }
            preVolume = curVolume;

        }
    }
}
