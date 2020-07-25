package com.ntec.pokelearn;

import android.animation.ValueAnimator;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class ActivityTrivia extends AppCompatActivity {

    private static final String KEY_INDEX= "index";
    private Button answerBtn1, answerBtn2, answerBtn3, answerBtn4;
    private TextView countLabel;
    private TextView mQuizText;
    private String rightAnswer;
    private int rightAnswerCount= 0;
    private int spreeCount = 0;
    private int highestSpreeCount = 0;
    private double score = 0;
    private double scoreWeight = 1;
    private int quizCount = 1;
    private boolean foundEaster = false;
    static final private int QUIZ_COUNT = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia);

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

        countLabel = findViewById(R.id.triviaTitle);
        mQuizText = findViewById(R.id.triviaQuestion);
        answerBtn1 = findViewById(R.id.buttonAns1);
        answerBtn2 = findViewById(R.id.buttonAns2);
        answerBtn3 = findViewById(R.id.buttonAns3);
        answerBtn4 = findViewById(R.id.buttonAns4);

        for(int i = 0; i < quizData.length; i++){
            ArrayList<String> tmpArray = new ArrayList<>();
            tmpArray.add(quizData[i][0]);
            tmpArray.add(quizData[i][1]);
            tmpArray.add(quizData[i][2]);
            tmpArray.add(quizData[i][3]);
            tmpArray.add(quizData[i][4]);

            quizArray.add(tmpArray);
        }

        showNextQuiz();
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Progress will be lost. Are you sure you want to leave?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        ActivityTrivia.super.onBackPressed();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    public void showNextQuiz(){
        countLabel.setText("Trivia " + quizCount);

        Random random = new Random();
        int randomNum = random.nextInt(quizArray.size());

        ArrayList<String> quiz = quizArray.get(randomNum);

        //Set question and right answer
        //Array format -> {"Q", "Right", "Choice1", "Choice2", "Choice3"}
        mQuizText.setText(quiz.get(0));
        rightAnswer = quiz.get(1);

        //Remove "Q" from quiz and shuffle choices
        quiz.remove(0);
        Collections.shuffle(quiz);

        answerBtn1.setText(quiz.get(0));
        answerBtn2.setText(quiz.get(1));
        answerBtn3.setText(quiz.get(2));
        answerBtn4.setText(quiz.get(3));

        quizArray.remove(randomNum);
    }

    public void checkAnswer(View view) {
        Button rightChoice;
        Button btnSelected = findViewById(view.getId());
        String btnSelectedText = btnSelected.getText().toString();
        TextView triviaQuestion = findViewById(R.id.triviaQuestion);
        String triviaQuestionString = triviaQuestion.getText().toString();
        TextView currentSpree = findViewById(R.id.textCurrentSpree);



        if (answerBtn1.getText().toString().equals(rightAnswer))
            rightChoice = answerBtn1;
        else if (answerBtn2.getText().toString().equals(rightAnswer))
            rightChoice = answerBtn2;
        else if (answerBtn3.getText().toString().equals(rightAnswer))
            rightChoice = answerBtn3;
        else
            rightChoice = answerBtn4;


        if (quizCount != QUIZ_COUNT) {
            if (btnSelectedText.equals(rightAnswer)) {
                spreeCount++;
                if(spreeCount>highestSpreeCount)
                    highestSpreeCount = spreeCount;
                if(triviaQuestionString.contains("EASTER EGG: "))
                    foundEaster = true;
                rightAnswerCount++;
                scoreWeight = scoreWeight + 1.5;
                score = score + 10 + (1*scoreWeight);
                currentSpree.setText(triviaSpree(spreeCount));
                btnSelected.setTextColor(Color.parseColor("#90EE90"));
                MediaPlayer correctSound = MediaPlayer.create(ActivityTrivia.this, R.raw.pickupgold_sound);
                correctSound.start();
            }

            else {
                spreeCount= 0;
                scoreWeight = scoreWeight - 0.5;
                currentSpree.setText(triviaSpree(spreeCount));
                btnSelected.setTextColor(Color.parseColor("#FF0000"));
                rightChoice.setTextColor(Color.parseColor("#90EE90"));
                MediaPlayer wrongSound = MediaPlayer.create(ActivityTrivia.this, R.raw.invulnerable);
                wrongSound.start();
            }
            buttonFreeze();
            quizCount++;
        }
        else {
            if (btnSelectedText.equals(rightAnswer)) {
                spreeCount++;
                if(spreeCount>highestSpreeCount)
                    highestSpreeCount = spreeCount;
                if(triviaQuestionString.contains("EASTER EGG: "))
                    foundEaster = true;
                rightAnswerCount++;
                btnSelected.setTextColor(Color.parseColor("#90EE90"));
                scoreWeight = scoreWeight + 1.5;
                score = score + 10 + (1*scoreWeight);
                MediaPlayer correctSound = MediaPlayer.create(ActivityTrivia.this, R.raw.pickupgold_sound);
                correctSound.start();

            }
            else{
                spreeCount= 0;
                scoreWeight = scoreWeight - 0.5;
                currentSpree.setText(triviaSpree(spreeCount));
                btnSelected.setTextColor(Color.parseColor("#FF0000"));
                rightChoice.setTextColor(Color.parseColor("#90EE90"));
                MediaPlayer wrongSound = MediaPlayer.create(ActivityTrivia.this, R.raw.invulnerable);
                wrongSound.start();
            }
            answerBtn1.setClickable(false);
            answerBtn2.setClickable(false);
            answerBtn3.setClickable(false);
            answerBtn4.setClickable(false);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(getApplicationContext(), ActivityTriviaResult.class);
                    intent.putExtra("GAME_SCORE", score);
                    intent.putExtra("GAME_HIGHEST_SPREE", highestSpreeCount);
                    intent.putExtra("GAME_RIGHT_ANSWER_COUNT", rightAnswerCount);
                    intent.putExtra("GAME_FOUND_EASTER_EGG", foundEaster);
                    startActivity(intent);
                }
            }, 2500);
        }
    }

    public void buttonFreeze(){
        answerBtn1.setClickable(false);
        answerBtn2.setClickable(false);
        answerBtn3.setClickable(false);
        answerBtn4.setClickable(false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                answerBtn1.setClickable(true);
                answerBtn2.setClickable(true);
                answerBtn3.setClickable(true);
                answerBtn4.setClickable(true);
                answerBtn1.setTextColor(Color.parseColor("#000000"));
                answerBtn2.setTextColor(Color.parseColor("#000000"));
                answerBtn3.setTextColor(Color.parseColor("#000000"));
                answerBtn4.setTextColor(Color.parseColor("#000000"));
                showNextQuiz();
            }
        }, 2000);
    }

    public String triviaSpree(int i){
        String[] spree = new String[14];

        spree[0] = "Nice try!";
        spree[1] = "There we go!";
        spree[2] = "Nice!";
        spree[3] = "Cool!";
        spree[4] = "Great!";
        spree[5] = "Well Done!";
        spree[6] = "Awesome!";
        spree[7] = "Genius!";
        spree[8] = "Einstein!";
        spree[9] = "Blimey!";
        spree[10] = "Amazing!";
        spree[11] = "Superb!";
        spree[12] = "Marvelous!";
        spree[13] = "Dominating!";

        return spree[i];
    }

    ArrayList<ArrayList<String>> quizArray = new ArrayList<>();
    private String quizData[][] = {
            //{"Q","Right Answer", "Choice1", "Choice2", "Choice3"}
            {"Which planet does not have moon or rings?", "Venus", "Jupiter", "Earth", "Uranus"},
            {"Which planet hits the coldest temperatures of any planet?", "Uranus", "Mercury", "Earth", "Neptune"},
            {"Which is the star of this solar system?", "The Sun", "Moon", "Proxima Centauri", "Vega"},
            {"Which planet is the most distant planet from the Sun.", "Neptune", "Uranus", "Jupiter", "Mars"},
            {"Which of these best describes the atmosphere surrounding Venus?", "Hot and Poisonous", "Bright and Sunny", "Cold and Wet", "Cold and Snowy"},
            {"How many moons does Mars have?", "2", "21", "1", "50"},
            {"What is sun mainly made of?", "Gas", "Molten iron", "Liquid lava", "Rock"},
            {"What is the Great Red Spot on Jupiter?", "A storm", "A volcano", "A lake", "A crater"},
            {"Which of the planet is the smallest?", "Mercury", "Earth", "Uranus", "Mars"},
            {"Where is the asteroid belt?", "Between Mars and Jupiter", "Between Mars and Earth", "Between Earth and Venus", "Between Jupiter and Saturn"},
            {"Which planet do the moons Oberon and Titania belong to?", "Uranus", "Venus", "Earth", "Jupiter"},
            {"How fast does light travel from the Sun to each of the planets?", "8 Minutes", "3 Minutes", "1 Day", "Instantaneously"},
            {"It takes the Sun 225-250 million years to do one revolution of the Milky Way Galaxy. How fast does the Sun travel?", "220km per second", "220km per minute", "220 km per hour", "220km in a year"},
            {"How old is the solar system?", "5 billion years", "500 billion years", "2000 years", "5000 years"},
            {"How many planets are there in our solar system?", "Eight", "Nine", "Ten", "Twelve"},
            {"Which planet is the fastest planet?", "Mercury", "Earth", "Jupiter", "Venus"},
            {"Which planet is the most likely suitable for future human habitat?", "Mars", "Venus", "Uranus", "Saturn"},
            {"How many years does it take for Uranus to orbit once around the Sun?", "84 Years", "12 Years", "91 Years", "46 Years"},
            {"How many years does it take for Neptune to orbit once around the sun?", "165 Years", "470 Years", "120 Years", "54 Years"},
            {"Which spacecraft is currently observing Jupiter?", "Juno", "Opportunity", "Voyager 1", "Perseverance"},
            {"Which is the second largest moon in our solar system?", "Titan, moon of Saturn", "Europa, moon of Jupiter ", "Titan, moon of Saturn", "Triton, moon of Neptune"},
            {"What is the name of the highest peak on Mars?", "Olympus Mons", "Caloris Montes", "Skadi Mons", "Mauna Loa"},
            {"Which planet is covered largely by water?", "Earth", "Uranus", "Mars", "Venus"},
            {"Which planet is known for the Great Red Spot?", "Jupiter", "Moon", "Saturn", "Venus"},
            {"Which planet is referred to as the 'Red Planet'?", "Mars", "Mercury", "Uranus", "Earth"},
            {"Which is the largest planet of the solar system?", "Jupiter", "Neptune", "Earth", "Saturn"},
            {"The first four planets in the solar system are called... ", "Inner planets", "Gas giants", "Outer planets", "Milky Way"},
            {"The inner planets are made up of... ", "Rocks", "Water", "Clouds", "Gas"},
            {"The outer planets are called ...", "Gas giants", "Large planets", "Terrestrial planets", "Ringed planets"},
            {"Which planet is known for its many rings?", "Saturn", "Jupiter", "Sun", "Uranus"},
            {"Which planet is in between the last rocky planet and the second gas giant?", "", "", "", ""},
            {"Where does the great divide take place? in the solar system?", "After the Mars", "After the Earth", "After the Neptune", "After the Sun"},
            {"What is the great divide in the solar system called?", "Asteroid Belt", "Orion's Belt", "Kuiper belt Objects", "Hilda asteroids"},
            {"The last inner planet is called... ", "Mars", "Earth", "Uranus", "Mercury"},
            {"How many moon does Earth have?", "One", "Two", "Three", "Four"},
            {"Earth's 24 hours a day is caused by its... ", "Revolution around the Sun", "Moon's orbiting around it", "Rotation on its plane", "Eclipsing of the sun"},
            {"What is the space rock that burns up in Earth's mesosphere?", "Meteor", "Meteoroid", "Meteorite", "Comet"},
            {"Which planet rotates backwards?", "Venus", "Saturn", "Jupiter", "Neptune"},
            {"How did our solar system most likely form?", "From an exploding star", "From a shrinking planet", "From a rotating nebula", "From an expanding galaxy"},
            {"Who was the first astronomer to use telescope to observe the night sky?", "Galileo Galilei", "Isaac Newton", "Johannes Kepler", "Nicolaus Copernicus"},
            {"What is the outer atmosphere of a comet called?", "Coma", "Nucleus", "Ion Tail", "Dust Tail"},
            {"Which planet in the solar system has the highest gravity of all?", "Jupiter", "Neptune", "Earth", "Uranus"},
            {"Which formed first after big bang?", "The universe", "The planets", "The solar system", "The galaxy"},
            {"What is a light year used to measure?", "The distance of objects in space", "The time for star to shine", "The mass of galaxy", "The diameter of star"},
            {"EASTER EGG: How many episodes have Rick And Morty travelled to outer space up to Season 4?", "21 episodes", "11 episodes", "24 episodes", "34 episodes"},
            {"EASTER EGG: What was the first space video game invented?", "Spacewar!", "Space Invaders", "Half Life", "Elder Book V Spacerim"},
    };
}
