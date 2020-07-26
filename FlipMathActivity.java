package com.ntec.pokelearn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import java.util.Random;

public class FlipMathActivity extends AppCompatActivity {

    private TextView mCountdownText, mCard1,mCard2, mCardAns, mStreakCount, mDialogResult,mDialogPrevResult,mCloseDialog,
            mDialogCountdownTxt,mDialogStartPrevResult;
    private ImageView mAnsCheckShow;
    private ImageButton mBtnInfo, mBtnExit;
    private CountDownTimer mCountDownTimer;
    private Button mDialogBtnExit, mDialogBtnQuit, mDialogBtnStart;
    private long mTimeLeftInMilliseconds = 300000, mCountDownMilliseconds = 4000;
    private Dialog mDialog_instruction, mDialog_gameOver, mDialog_countdown, mDialog_start;
    Handler handlerCountown, handler;
    ArrayList<String> mALCardNum;
    String[] arr = {"1","2","3","4","5","6","7","8","9","10","11","12"};
    RecyclerView mRecyclerView;
    RecyclerViewCardAdapter mRecyclerViewCardAdapter;
    SharedPreferencesCollection mPreferencesCollection;
    MediaPlayer correctSound, falseSound;
    Random randAns;

    Boolean cardFold, winStreak = false,mTimerRunning;
    String cardNum;
    int  minAns = 3, maxAns = 18,cardValue1,cardValue2, streakCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flipmath);
        statusBar();

        mRecyclerView  = findViewById(R.id.flipmath_rv);
        mCountdownText = findViewById(R.id.flipmath_timer);
        mBtnInfo       = findViewById(R.id.flipmath_instruction);
        mBtnExit       = findViewById(R.id.flipmath_exit);
        mStreakCount   = findViewById(R.id.flipmath_streak_count);
        mAnsCheckShow  = findViewById(R.id.flipmath_ansCheckShow);
        mCard1         = findViewById(R.id.flipmath_card1);
        mCard2         = findViewById(R.id.flipmath_card2);
        mCardAns       = findViewById(R.id.flipmath_ans);

        mDialog_start          = new Dialog(this);
        mDialog_countdown      = new Dialog(this);
        mDialog_instruction    = new Dialog(this);
        mDialog_gameOver       = new Dialog(this);
        handler                = new Handler();
        handlerCountown        = new Handler();
        mALCardNum             = new ArrayList<>(Arrays.asList(arr));
        randAns                = new Random();
        mPreferencesCollection = new SharedPreferencesCollection(this);
        correctSound = MediaPlayer.create(FlipMathActivity.this,R.raw.pickupgold_sound);
        falseSound = MediaPlayer.create(FlipMathActivity.this,R.raw.invulnerable);

        //Receive from adapter
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,
                new IntentFilter("flippedValue"));

        //Shuffle the number and pass to adapter
        Collections.shuffle(mALCardNum);
        mRecyclerViewCardAdapter = new RecyclerViewCardAdapter(this, mALCardNum);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        mRecyclerView.setAdapter(mRecyclerViewCardAdapter);

        //If instruction button clicked
        mBtnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startStop();
                ShowDialogInstruction();
            }
        });

        //if exit button clicked
        mBtnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FlipMathActivity.this, MainActivity.class));
            }
        });

        //check whether is there a record of previous game
        if( mPreferencesCollection.checkPrefsIsEmpty("flipMathScore")){
            mPreferencesCollection.setStr("flipMathScore", "0");
        }
        mCardAns.setText(String.valueOf(randAns.nextInt(maxAns - minAns + 1) + minAns));
        ShowDialogStart();
    }

    //Receive the flipped card number from adapter
    public BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            cardNum = intent.getStringExtra("cardNum");
            cardFold = intent.getBooleanExtra("cardFold", false);

            //if both card is fold then reset the slots
            if(cardFold){
                mCard1.setText("");
                mCard2.setText("");
                cardFold = false;
                mAnsCheckShow.setImageResource(R.drawable.ic_question);
                //if get the correct answer
                if(winStreak){
                    //update the equation and shuffle the position of card
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            randAns = new Random();
                            mCardAns.setText(String.valueOf(randAns.nextInt(maxAns - minAns + 1) + minAns));
                            Collections.shuffle(mALCardNum);
                            mRecyclerViewCardAdapter.notifyDataSetChanged();
                            winStreak = false;
                        }
                    }, 280); //0.15 sec
                }
                //check whether slot 1 is empty, if so set card number
            } else if(mCard1.getText().length() == 0){
                mCard1.setText(cardNum);
                cardValue1 = Integer.valueOf(cardNum);
                //if slot 1 is filled, check whether slot 2 is empty, if so set card number
            } else if(mCard2.getText().length() == 0 ){
                mCard2.setText(cardNum);
                cardValue2 = Integer.valueOf(cardNum);

                int cardAns = cardValue1 + cardValue2;
                //check whether the equation is correct
                if(Integer.parseInt(mCardAns.getText().toString()) == cardAns){
                    //if correct, set correct icon and produce correct sound and add streak count
                    mAnsCheckShow.setImageResource(R.drawable.ic_correct);
                    correctSound.start();
                    correctSound.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            correctSound.pause();
                        }
                    });
                    streakCount++;
                    mStreakCount.setText("" + streakCount);
                    winStreak =true;
                    //if false, set false icon and produce incorrect sound
                } else {
                    mAnsCheckShow.setImageResource(R.drawable.ic_false);
                    falseSound.start();
                    falseSound.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            falseSound.pause();
                        }

                    });
                }
            }
        }
    };

    //show dialog to let user to choose to play or leave the game
    public void ShowDialogStart() {
        mDialog_start.setContentView(R.layout.flipmath_dialog_start);
        mDialogBtnQuit = mDialog_start.findViewById(R.id.flipmath_dialog_quit);
        mDialogBtnStart = mDialog_start.findViewById(R.id.flipmath_dialog_start);
        mDialogStartPrevResult = mDialog_start.findViewById(R.id.flipmath_dialog_start_prevResult);

        mDialogStartPrevResult.setText(mPreferencesCollection.getStr("flipMathScore"));

        mDialogBtnQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FlipMathActivity.this, MainActivity.class));
            }
        });

        mDialogBtnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog_start.dismiss();
                ShowDialogCountdown();
            }
        });
        Objects.requireNonNull(mDialog_start.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mDialog_start.setCanceledOnTouchOutside(false);
        mDialog_start.show();
    }

    //countdown for preparing to play the game
    public void ShowDialogCountdown() {
        mDialog_countdown.setContentView(R.layout.flipmath_dialog_countdown);
        mDialogCountdownTxt = mDialog_countdown.findViewById(R.id.flipmath_dialog_countdown);

        Objects.requireNonNull(mDialog_countdown.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mDialog_countdown.setCanceledOnTouchOutside(false);
        mDialog_countdown.show();

        mCountDownTimer = new CountDownTimer(mCountDownMilliseconds, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mCountDownMilliseconds = millisUntilFinished;
                int seconds = (int) mCountDownMilliseconds % 60000 / 1000;

                mDialogCountdownTxt.setText(""+ seconds);
            }

            @Override
            public void onFinish() {
                mDialogCountdownTxt.setText("GO");
                handlerCountown.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mDialog_countdown.dismiss();
                        startTimer();
                    }
                }, 1000); //3 sec
            }
        }.start();
    }

    //instruction dialog to show how to play
    public void ShowDialogInstruction() {
        mDialog_instruction.setContentView(R.layout.flipmath_dialog_instruction);
        mCloseDialog = mDialog_instruction.findViewById(R.id.custom_pop_up_random_close);

        mCloseDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startStop();
                mDialog_instruction.dismiss();
            }
        });

        Objects.requireNonNull(mDialog_instruction.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mDialog_instruction.setCanceledOnTouchOutside(false);
        mDialog_instruction.show();
    }

    //Show score after countdown finished
    public void ShowDialogGameOver() {
        mDialog_gameOver.setContentView(R.layout.flipmath_dialog_gameover);
        mDialogBtnExit = mDialog_gameOver.findViewById(R.id.flipmath_dialog_exit);
        mDialogResult = mDialog_gameOver.findViewById(R.id.flipmath_dialog_result);
        mDialogPrevResult = mDialog_gameOver.findViewById(R.id.flipmath_prevResult);

        //Show Score and previous high score
        mDialogResult.setText(mStreakCount.getText());
        mDialogPrevResult.setText(mPreferencesCollection.getStr("flipMathScore"));

        //replace the high score if get higher score
        if (Integer.parseInt(mPreferencesCollection.getStr("flipMathScore")) < Integer.parseInt(mStreakCount.getText().toString())) {
            mPreferencesCollection.setStr("flipMathScore", mStreakCount.getText().toString());
        }
        //clicked to exit game
        mDialogBtnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FlipMathActivity.this, MainActivity.class));
            }
        });
        Objects.requireNonNull(mDialog_gameOver.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mDialog_gameOver.setCanceledOnTouchOutside(false);
        mDialog_gameOver.show();
    }

    //check whether time is running
    public void startStop(){
        if(mTimerRunning) { stopTimer(); }
        else { startTimer(); }
    }

    //update the timer
    public void startTimer(){
        mCountDownTimer = new CountDownTimer(mTimeLeftInMilliseconds, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMilliseconds = millisUntilFinished;
                updateTimer();

                if(mCountdownText.getText().equals("0:00")){
                    ShowDialogGameOver();
                }
            }
            @Override
            public void onFinish() {
            }
        }.start();

        mTimerRunning = true;
    }

    //pause the timer
    public void stopTimer(){
        mCountDownTimer.cancel();
        mTimerRunning = false;
    }

    //update the timer
    public void updateTimer(){
        int minutes = (int) mTimeLeftInMilliseconds / 60000;
        int seconds = (int) mTimeLeftInMilliseconds % 60000 / 1000;

        String timeLeftText;

        timeLeftText = "" + minutes;
        timeLeftText += ":";
        if (seconds < 10){
            timeLeftText += "0";
        }
        timeLeftText += seconds;

        mCountdownText.setText(timeLeftText);
    }

    //make status bar transparent
    public void statusBar(){
        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true);
        }
        if (Build.VERSION.SDK_INT >= 19) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
            getWindow().setStatusBarColor(Color.TRANSPARENT);}
    }

    public static void setWindowFlag(Activity activity, final int bits, boolean on) {
        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }
}

