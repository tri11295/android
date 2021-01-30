package com.example.thread_bitmap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class TestHandler_2 extends AppCompatActivity implements View.OnClickListener {

    private Button btnLoad;
    private String url = "https://cdn.tgdd.vn/Products/Images/42/230867/samsung-galaxy-note-20-ultra-5g-trang-600x600-2-400x400.jpg";
    private Bitmap bitmap = null;
    private TextView tvMessage;
    private ImageView imageView1;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_handler_2);

        imageView1 = findViewById(R.id.imageView23);
        btnLoad = findViewById(R.id.button23);
        tvMessage = findViewById(R.id.textView23);

        btnLoad.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        progressDialog = ProgressDialog.show(TestHandler_2.this,"Dang download","Dang DownLoad");
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                bitmap = downLoadBimap(url);
                Message msg = messageHandler.obtainMessage();
                Bundle bundle = new Bundle();
                String thMessage = "Da download xong";
                bundle.putString("message",thMessage);
                msg.setData(bundle);
                messageHandler.sendMessage(msg);
            }
        };
        Thread th = new Thread(runnable);
        th.start();
    }


    private Handler messageHandler = new Handler(Looper.getMainLooper())
    {
        public void handlerMessage(Message msg)
        {
            super.handleMessage(msg);
            Bundle bundle = msg.getData();
            String message = bundle.getString("message");
            tvMessage.setText(message);
            imageView1.setImageBitmap(bitmap);

            progressDialog.dismiss();
        }
    };

    private Bitmap downLoadBimap(String link){
        try {
            URL url = new URL(link);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            Bitmap bitmap1 = BitmapFactory.decodeStream(inputStream);
            return bitmap1;
        }
        catch (MalformedURLException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}