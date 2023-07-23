package com.example.cmd.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.cmd.R;
import com.example.cmd.animation.LetterSpacingTextView;
import com.example.cmd.animation.ScalableTextView;

public class SplashActivity extends AppCompatActivity {

    private LetterSpacingTextView textViewKDA;
    private ScalableTextView textViewDescription1;
    private ScalableTextView textViewDescription2;
    private ScalableTextView textViewDescription3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        textViewKDA = findViewById(R.id.textview_KDA);
        textViewDescription1 = findViewById(R.id.textview_KDA_description1);
        textViewDescription2 = findViewById(R.id.textview_KDA_description2);
        textViewDescription3 = findViewById(R.id.textview_KDA_description3);

        // KDA 글씨를 크게 보이도록 애니메이션 설정
        //Animation fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in);

        //textViewKDA.startAnimation(fadeInAnimation);

        textViewKDA.animateLetterSpacing(1.4f,1000);

        // 일정 시간 후에 KDA 글씨를 보여주는 애니메이션 완료 후, 나머지 텍스트 표시
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                textViewKDA.setVisibility(View.VISIBLE);

                // "Korea Daedeok Assistance" 텍스트를 보여주도록 애니메이션 설정
                Animation slideUpAnimation = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.slide);
                textViewDescription1.startAnimation(slideUpAnimation);
                textViewDescription2.startAnimation(slideUpAnimation);
                textViewDescription3.startAnimation(slideUpAnimation);

                slideUpAnimation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }
        }, 1000); // 1초 후에 텍스트 표시 애니메이션 실행
    }
}