package fzf.ipc_message;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent=new Intent(this,MessengerService.class);
        bindService(intent,MessengerClient.serviceConnection,BIND_AUTO_CREATE);
    }
}
