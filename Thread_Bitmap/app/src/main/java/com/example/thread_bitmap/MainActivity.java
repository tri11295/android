package com.example.thread_bitmap;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button button ;
    TextView textView;
    ImageView imageView;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        textView = findViewById(R.id.textView);
        imageView = findViewById(R.id.imageView);
        editText = findViewById(R.id.edt_text);

        button.setOnClickListener(this);
    }

    // su dung thread
    @Override
    public void onClick(View v) {
        final Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                // https://cdn.tgdd.vn/Products/Images/42/230867/samsung-galaxy-note-20-ultra-5g-trang-600x600-2-400x400.jpg
                // https://cdn.tgdd.vn/Products/Images/42/190325/iphone-xr-hopmoi-den-600x600-400x400.jpg
                // https://cdn.tgdd.vn/Products/Images/42/206176/TimerThumb/samsung-galaxy-note-10-plus-(24).jpg
//                String url = editText.getText().toString().trim();
                final Bitmap bitmap = loadIMG("https://cdn.tgdd.vn/Products/Images/42/206176/TimerThumb/samsung-galaxy-note-10-plus-(24).jpg");
                imageView.post(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText("Download thanh cong");
                        imageView.setImageBitmap(bitmap);
                    }
                });
            }
        });
        thread.start();
    }

    private Bitmap loadIMG(String link){
        URL url;
        Bitmap bitmap = null;
        try {
            url = new URL(link);
            bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return bitmap;
    }
}