package com.ntec.pokelearn;

import android.animation.ValueAnimator;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class ActivityTriviaResult extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia_result);
       
		//set the background animation
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

        TextView resultLabel = findViewById(R.id.resultLabel);
        TextView totalScoreLabel = findViewById(R.id.totalScore);
        TextView highestSpreeLabel = findViewById(R.id.highestSpreeLabel);
        TextView behaviorScore = findViewById(R.id.behaviorScore);
        TextView easterEggLabel = findViewById(R.id.textEaster);
        ImageView imageBehavior = findViewById(R.id.imageBehavior);
        Button btnLeaveGame = findViewById(R.id.btnLeavegame);
        Button btnRewardEasterEgg = findViewById(R.id.btnRewardEasterEgg);

		//retrieve the data needed from ActivityTrivia.class to be used
        int gameRightAnswerCount = getIntent().getIntExtra("GAME_RIGHT_ANSWER_COUNT", 0);
        int gameHighestSpree = getIntent().getIntExtra("GAME_HIGHEST_SPREE", 0);
        boolean gameFoundEasterEgg = getIntent().getBooleanExtra("GAME_FOUND_EASTER_EGG", false);
        Double gameScore = getIntent().getDoubleExtra("GAME_SCORE", 0);

		//setting the display text accordingly to the data retrieved
        resultLabel.setText("You've got " + gameRightAnswerCount + "/10 !");
        totalScoreLabel.setText("Score: " + Math.round(gameScore));
        highestSpreeLabel.setText("Highest Spree: " + gameHighestSpree);

		//determines player's behavior score based on the final score
        if(Math.round(gameScore) < 30){
            imageBehavior.setImageResource(R.drawable.caveman);
            behaviorScore.setText("Your intelligence is equivalent to a caveman");
        }
        else if(Math.round(gameScore) < 60){
            imageBehavior.setImageResource(R.drawable.monkey);
            behaviorScore.setText("Your intelligence is equivalent to a chimpanzee");
        }
        else if(Math.round(gameScore) < 90){
            imageBehavior.setImageResource(R.drawable.owl);
            behaviorScore.setText("Your intelligence is equivalent to an owl");
        }
        else if(Math.round(gameScore) < 120){
            imageBehavior.setImageResource(R.drawable.parrots);
            behaviorScore.setText("Your intelligence is equivalent to a parrot");
        }
        else if(Math.round(gameScore) < 150){
            imageBehavior.setImageResource(R.drawable.robot);
            behaviorScore.setText("Your intelligence is equivalent to a robot");
        }
        else if(Math.round(gameScore) > 170) {
            imageBehavior.setImageResource(R.drawable.einstein);
            behaviorScore.setText("Congrats! Your intelligence is equivalent to Einstein's!");
        }

		//check if easter egg trivia question were present in the game
		//if yes then show easter egg rewards at the end of the game
        if(gameFoundEasterEgg){
            easterEggLabel.setVisibility(View.VISIBLE);
            btnRewardEasterEgg.setVisibility(View.VISIBLE);
            btnRewardEasterEgg.setClickable(false);
        }
        else{
            easterEggLabel.setVisibility(View.INVISIBLE);
            btnRewardEasterEgg.setVisibility(View.INVISIBLE);
            btnRewardEasterEgg.setClickable(true);
        }

		//set the reward button to bring player to the reward layout
        btnRewardEasterEgg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityTriviaResult.this,ActivityDeoxysEasterEgg.class));
            }
        });

		//set the leave button to bring user to the deoxys main menu
        btnLeaveGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(ActivityTriviaResult.this, ActivityMainMenuDeoxys.class));
            }
        });
    }

	//make sure back button doesnt redirect player back to the previous layout
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to go back to main menu?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        startActivity(new Intent(ActivityTriviaResult.this, ActivityMainMenuDeoxys.class));
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }
}
