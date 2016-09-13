package fzf.ipc_message;

import android.app.Activity;
import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by root on 16-9-12.
 */
public class MessengerClient extends Activity{
    private  static final String TAG="MessengerActivity";

    public static ServiceConnection serviceConnection=new ServiceConnection() {
        private Messenger mService;
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder service) {
            mService=new Messenger(service);
            Message msg=Message.obtain(null,1);
            Bundle data=new Bundle();
            data.putString("msg","Hello,I come from Client");
            msg.setData(data);
             Messenger mGetReplyMessenger = new Messenger(new MessengerHandler());//当客户端发送消息的时候，需要把接收服务端回复的messenger通过message的replyto参数传递给服务端
            msg.replyTo=mGetReplyMessenger;
            try{
                mService.send(msg);
            } catch (RemoteException e) {
                Log.i(TAG,"Some thing wrong in send msg");
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };
    private static class MessengerHandler extends Handler{
        @Override
        public void handleMessage(Message msg){
            if(msg.what==3)
            {
                Log.i(TAG,"From Server:"+msg.getData().getString("reply"));
            }
            else
            {
                super.handleMessage(msg);
            }
        }
    }
}
