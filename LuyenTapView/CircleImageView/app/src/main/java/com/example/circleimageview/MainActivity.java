package com.example.circleimageview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    private CircleImageView mcircleImageView;
    private Button btnStartAnimation;
    private Button btnStopAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mcircleImageView = findViewById(R.id.profile_image);
        btnStartAnimation = findViewById(R.id.btn_Start_Animation);
        btnStopAnimation = findViewById(R.id.btn_Stop_Animation);

        btnStartAnimation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAnimation();
            }
        });

        btnStopAnimation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopAnimation();
            }
        });
    }

    private  void  startAnimation(){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                mcircleImageView.animate().rotation(360).withEndAction(this).setDuration(10000)
                        .setInterpolator(new LinearInterpolator()).start();
            }
        };
        mcircleImageView.animate().rotation(360).withEndAction(runnable).setDuration(10000)
                .setInterpolator(new LinearInterpolator()).start();
    }

    private  void  stopAnimation(){
        mcircleImageView.animate().cancel();
    }
}