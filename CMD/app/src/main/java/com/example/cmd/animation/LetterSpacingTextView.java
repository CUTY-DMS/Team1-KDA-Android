package com.example.cmd.animation;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

public class LetterSpacingTextView extends androidx.appcompat.widget.AppCompatTextView {

    private float originalLetterSpacing;

    public LetterSpacingTextView(Context context) {
        super(context);
        init();
    }

    public LetterSpacingTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LetterSpacingTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        originalLetterSpacing = getLetterSpacing();

        setMaxLines(1);
    }

    // 글자 간격을 애니메이션으로 변경하는 메서드
    public void animateLetterSpacing(float targetLetterSpacing, long duration) {
        ValueAnimator animator = ValueAnimator.ofFloat(originalLetterSpacing, targetLetterSpacing);
        animator.setDuration(duration);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float animatedValue = (float) animation.getAnimatedValue();
                setLetterSpacing(animatedValue);
            }
        });
        animator.start();
    }

    // 원래의 글자 간격으로 되돌리는 메서드
    public void resetLetterSpacing() {
        setLetterSpacing(originalLetterSpacing);
    }
}
