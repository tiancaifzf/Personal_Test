package fzf.ipc_message;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by root on 16-9-12.
 */
public class MessengerService extends Service {
    private static final String TAG="MessengerService";
    private static class MessengerHandler extends Handler{
        @Override
        public void handleMessage(Message msg){

                if(msg.what==1)
                {
                    Log.i(TAG,"RECEIVE MSG FROM CLIENT!!"+msg.getData().getString("msg"));
                    Messenger client=msg.replyTo;
                    Message replayMessage=Message.obtain(null,1);
                    Bundle bundle=new Bundle();
                    bundle.putString("reply","Yes I receive your Message ! ");
                    replayMessage.setData(bundle);
                    replayMessage.what=3;
                    try {
                        client.send(replayMessage);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                else
                {
                    Log.i(TAG,"Something wrong");
                }

        }
    }
    private final Messenger mMessemger=new Messenger(new MessengerHandler());

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mMessemger.getBinder();
    }
}
