package com.example.lab1.Lab1_3;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lab1.R;

public class MainActivityLab1_3 extends AppCompatActivity implements View.OnClickListener, Listener {

    public Button btnLoad;
    private ImageView imgAndroid;
    private TextView txtMessage;
    private static final String IMAGE_URL = "https://nhadepso.com/wp-content/uploads/2023/02/tang-ban-50-hinh-nen-iphone-x-full-hd-dep-sang-chanh-nhat-1.jpg";

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
        if (v.getId() == R.id.btnLoad){
            new LoadImageTask(this, this).execute(IMAGE_URL);
        }


    }

    public void onImageLoaded(Bitmap bitmap){
        imgAndroid.setImageBitmap(bitmap);
        txtMessage.setText("Image Downloaded");
    }
     public void onError(){
        txtMessage.setText("Error download image");
     }

}