package com.example.yangl.androidsample.binderservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

public class BinderService extends Service {

    private String TAG = BinderService.class.getSimpleName();

    public static final int REPORT_CODE = 0;

    private Reporter mReporter;

    public BinderService() {
        mReporter = new Reporter();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mReporter;
    }

    public interface IReport {
        int report(String values,int type);
    }

    public final class Reporter extends Binder implements IReport{

        @Override
        protected boolean onTransact(int code, @NonNull Parcel data, @Nullable Parcel reply, int flags) throws RemoteException {
            switch (code){
                case REPORT_CODE:
                    data.enforceInterface("reporter");
                    String values = data.readString();
                    Log.d(TAG, "onTransact: data is "+values+" ");
                    int type = data.readInt();
                    int result = report(values,type);
                    reply.writeInterfaceToken("reporter");
                    reply.writeInt(result);
                    return true;
            }
            return super.onTransact(code, data, reply, flags);
        }

        @Override
        public int report(String values, int type) {
            return type+100;
        }
    }


}
