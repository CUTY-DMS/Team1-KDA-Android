package com.example.cmd.animation;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

public class ScalableTextView extends androidx.appcompat.widget.AppCompatTextView {

    public ScalableTextView(Context context) {
        super(context);
    }

    public ScalableTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScalableTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    // 텍스트 크기를 설정하는 메서드
    public void setTextSize(float textSize) {
        setTextSize(textSize, true);
    }

    // 텍스트 크기를 설정하고 크기 변경 애니메이션을 적용하는 메서드
    public void setTextSize(float textSize, boolean animate) {
        if (animate) {
            ValueAnimator animator = ValueAnimator.ofFloat(getTextSize(), textSize);
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    float animatedValue = (float) animation.getAnimatedValue();
                    ScalableTextView.super.setTextSize(animatedValue);
                }
            });
            animator.start();
        } else {
            super.setTextSize(textSize);
        }
    }
}
