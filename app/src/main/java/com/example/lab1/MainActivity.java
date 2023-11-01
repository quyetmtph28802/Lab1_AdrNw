package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;

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

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

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
        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //download ảnh
                        final  Bitmap bitmap = getPicture("https://didongviet.vn/dchannel/wp-content/uploads/2023/08/hinh-nen-3d-hinh-nen-iphone-dep-3d-didongviet@2x-576x1024.jpg");
                        // đưa lên imgView
                        imgAndroid.post(new Runnable() {
                            @Override
                            public void run() {
                                imgAndroid.setImageBitmap(bitmap);
                                txtMessage.setText("Download thành công");
                            }
                        });

                    }
                });
                thread.start();
            }
        });

    }

    private Bitmap getPicture (String link){
        URL url;
        Bitmap bitmap = null;
        try {
            url = new URL(link); // lấy về đường dẫn ảnh
            bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return bitmap;
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