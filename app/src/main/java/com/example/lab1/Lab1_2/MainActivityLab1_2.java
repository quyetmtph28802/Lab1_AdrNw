package com.example.lab1.Lab1_2;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lab1.R;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivityLab1_2 extends AppCompatActivity implements View.OnClickListener {

    private Button btnLoad;
    private ImageView imgAndroid;
    private TextView txtMessage;
    private ProgressDialog progressDialog;
    private String url = "https://teky.edu.vn/blog/wp-content/uploads/2022/03/nhieu-nguoi-thich-tai-hinh-nen-nay.jpg";
    private Bitmap bitmap = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtMessage = findViewById(R.id.txtMessage);
        imgAndroid = findViewById(R.id.imgAndroid);
        btnLoad = findViewById(R.id.btnLoad);
        btnLoad.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        progressDialog = ProgressDialog.show(MainActivityLab1_2.this,"","Downloading...");
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                bitmap = downloadBitmap(url);
                Message msg = messageHandler.obtainMessage();
                Bundle bundle = new Bundle();
                String threadMessage = "Image downloaded";
                bundle.putString("message", threadMessage);
                msg.setData(bundle);
                messageHandler.sendMessage(msg);
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    private Bitmap downloadBitmap(String link){
        try {
            URL url = new URL(link);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            Bitmap bitmap1 = BitmapFactory.decodeStream(inputStream);
            return bitmap1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Handler messageHandler = new Handler(){
        public void handleMessage(Message msg){
            super.handleMessage(msg);
            Bundle bundle = msg.getData();
            String message = bundle.getString("message");
            txtMessage.setText(message);
            imgAndroid.setImageBitmap(bitmap);
            progressDialog.dismiss();
        }
    };
}