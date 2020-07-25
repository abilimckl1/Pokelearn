package com.ntec.pokelearn;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityEarth extends AppCompatActivity {

    private static final String KEY_INDEX= "index";
    private Button mNextButton;
    private Button mPreviousButton;
    private TextView mFactText;
    private int mCurrentIndex= 0;
    private ImageView imageBack2Main;

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earth);

        final ImageView backgroundOne = (ImageView) findViewById(R.id.background_one);
        final ImageView backgroundTwo = (ImageView) findViewById(R.id.background_two);
        final ValueAnimator animator = ValueAnimator.ofFloat(1.0f, 0.0f);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.setDuration(10000L);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                final float progress = (float) animation.getAnimatedValue();
                final float height = backgroundOne.getHeight();
                final float translationY = height * progress;
                backgroundOne.setTranslationY(translationY);
                backgroundTwo.setTranslationY(translationY - height);
            }
        });
        animator.start();

        mFactText = findViewById(R.id.textViewFact);
        mNextButton = findViewById(R.id.buttonNext);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mFact.length;
                updateFact();
            }
        });

        mPreviousButton = findViewById(R.id.buttonPrevious);
        mPreviousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mCurrentIndex == 0)
                    mCurrentIndex = 12;
                else if(mCurrentIndex!= 0)
                    mCurrentIndex = (mCurrentIndex-1);

                updateFact();
            }
        });

        if(savedInstanceState != null){
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }
        updateFact();

        imageBack2Main = findViewById(R.id.imageBackMain);

        imageBack2Main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityEarth.super.onBackPressed();
            }
        });
    }

    private Fact[] mFact = new Fact[]{
            new Fact(R.string.earthFact1),
            new Fact(R.string.earthFact2),
            new Fact(R.string.earthFact3),
            new Fact(R.string.earthFact4),
            new Fact(R.string.earthFact5),
            new Fact(R.string.earthFact6),
            new Fact(R.string.earthFact7),
            new Fact(R.string.earthFact8),
            new Fact(R.string.earthFact9),
            new Fact(R.string.earthFact10),
            new Fact(R.string.earthFact11),
            new Fact(R.string.earthFact12),
            new Fact(R.string.earthFact13),
    };

    private void updateFact(){
        int fact = mFact[mCurrentIndex].getTextResId();
        mFactText.setText(fact);
    }


}
