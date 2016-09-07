package fzf.hellotest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Entity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.http.HttpResponseCache;
import android.os.Handler;
import android.os.Message;
import android.os.Process;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    Context context;
    ImageView imageView;
    public static int DOWNLOAD_IMG=1;
    String imgPath="http://b.zol-img.com.cn/sjbizhi/images/9/320x510/147186530835.jpg";
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message message){
            byte[] data=(byte[])message.obj;
            Bitmap bitmap= BitmapFactory.decodeByteArray(data,0,data.length);
            imageView.setImageBitmap(bitmap);
            if(message.what==DOWNLOAD_IMG)
            {

            Toast.makeText(context,"Download Successful!",Toast.LENGTH_SHORT).show();
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context=this.getApplicationContext();
        imageView=(ImageView) findViewById(R.id.imageView);
        Button dl_button=(Button)findViewById(R.id.button);
        dl_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            new Thread(new MyThread()).start();
            }
        });
    }
 public class MyThread implements Runnable{

     @Override
     public void run() {
         HttpClient httpClient=new DefaultHttpClient();
         HttpGet httpGet=new HttpGet(imgPath);
         HttpResponse httpResponse=null;
         try{
             httpResponse=httpClient.execute(httpGet);
             if(httpResponse.getStatusLine().getStatusCode()==200){
                byte[] data= EntityUtils.toByteArray(httpResponse.getEntity());
                 Message message=Message.obtain();
                 message.obj=data;
                 message.what=DOWNLOAD_IMG;
                 handler.sendMessage(message);
             }
         } catch (ClientProtocolException e) {
             e.printStackTrace();
         } catch (IOException e) {
             e.printStackTrace();
         }
     }
 }
}
