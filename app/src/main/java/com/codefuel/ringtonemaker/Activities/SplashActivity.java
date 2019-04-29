package com.codefuel.ringtonemaker.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.codefuel.ringtonemaker.R;

public class SplashActivity extends AppCompatActivity {

    private ImageView mRToneImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
        mRToneImageView = (ImageView) findViewById(R.id.image_view_bell);
        mRToneImageView.startAnimation(shake);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mRToneImageView.clearAnimation();
                Intent i = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        }, 1000);
    }
}
