package com.ntec.pokelearn;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;

public class Diglett extends AppCompatActivity {
    ImageView holes[];
    TextView values[], score, question;
    private Handler tickHandler = new Handler();
    int emerge = -1; //variable that tracks which mole is emerged from the hole if -1: no mole is emerged
    int curScore = 0;
    int oprd1, oprd2, answer;
    boolean oprt;
    TickThread tickThread = new TickThread();
    boolean exit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diglett);

        initVal(); //initialize variables

        startTickThread(); //starts the game cycle
    }

    public void startTickThread() //starts the game cycle
    {
        tickThread.start();
    }

    private void initVal()
    {
        holes = new ImageView[4]; //puts the hole imageviews into an array
        values = new TextView[4]; //puts the mole imageviews into an array

        holes[0] = (ImageView)findViewById(R.id.hole1);
        holes[1] = (ImageView)findViewById(R.id.hole2);
        holes[2] = (ImageView)findViewById(R.id.hole3);
        holes[3] = (ImageView)findViewById(R.id.hole4);

        values[0] = (TextView)findViewById(R.id.value1);
        values[1] = (TextView)findViewById(R.id.value2);
        values[2] = (TextView)findViewById(R.id.value3);
        values[3] = (TextView)findViewById(R.id.value4);




        score = (TextView)findViewById(R.id.score);
        question = (TextView)findViewById(R.id.question);

        generateQuestion(); //generates an equation at the start of the game

        for(int i=0; i<4; i++)
        {
            values[i].setVisibility(View.INVISIBLE); //values for each mole will only be visible when they emerge
        }

        holes[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(emerge == 0) //checks to see if the hole click has a mole emerged from it
                {
                    if(Integer.valueOf(values[0].getText().toString()) == answer) //if the mole clicked has the right answer
                    {
                        values[0].setText("✓");
                        curScore++;
                        score.setText(curScore + "");
                        holes[0].setImageDrawable(getResources().getDrawable(R.drawable.molehapi));
                        emerge = -1;
                        generateQuestion();
                    }
                    else //when the mole has wrong answer
                    {
                        values[0].setText("X");
                        holes[0].setImageDrawable(getResources().getDrawable(R.drawable.moleded));
                        emerge = -1;
                    }

                }
            }
        });

        holes[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(emerge == 1)
                {
                    if(Integer.valueOf(values[1].getText().toString()) == answer)
                    {
                        values[1].setText("✓");
                        curScore++;
                        score.setText(curScore + "");
                        holes[1].setImageDrawable(getResources().getDrawable(R.drawable.molehapi));
                        emerge = -1;
                        generateQuestion();
                    }
                    else
                    {
                        values[1].setText("X");
                        holes[1].setImageDrawable(getResources().getDrawable(R.drawable.moleded));
                        emerge = -1;
                    }
                }
            }
        });

        holes[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(emerge == 2)
                {
                    if(Integer.valueOf(values[2].getText().toString()) == answer)
                    {
                        values[2].setText("✓");
                        curScore++;
                        score.setText(curScore + "");
                        holes[2].setImageDrawable(getResources().getDrawable(R.drawable.molehapi));
                        emerge = -1;
                        generateQuestion();
                    }
                    else
                    {
                        values[2].setText("X");
                        holes[2].setImageDrawable(getResources().getDrawable(R.drawable.moleded));
                        emerge = -1;
                    }
                }

            }
        });

        holes[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(emerge == 3)
                {
                    if(Integer.valueOf(values[3].getText().toString()) == answer)
                    {
                        values[3].setText("✓");
                        curScore++;
                        score.setText(curScore + "");
                        holes[3].setImageDrawable(getResources().getDrawable(R.drawable.molehapi));
                        emerge = -1;
                        generateQuestion();
                    }
                    else
                    {
                        values[3].setText("X");
                        holes[3].setImageDrawable(getResources().getDrawable(R.drawable.moleded));
                        emerge = -1;
                    }
                }
            }
        });
    }

    private void generateQuestion()
    {
        Random rndOprd1 = new Random(); //generates first random operand
        Random rndOprd2 = new Random(); //generates second random operand
        Random rndOprt = new Random(); //generates random operator (+ or -)
        oprd1 = rndOprd1.nextInt(50);
        oprd2 = rndOprd2.nextInt(oprd1);
        oprt = rndOprt.nextBoolean();

        if(oprt) // + operator
        {
            question.setText(oprd1 + " + " + oprd2);
            answer = oprd1 + oprd2;
        }
        else // - operator
        {
            question.setText(oprd1 + " - " + oprd2);
            answer = oprd1 - oprd2;
        }

    }

    public void back_clicked(View view)
    {
        tickThread.kill();
        this.finish();
    }

    class TickThread extends Thread //the thread classes that focuses on running the "tick/cycle" in the game and spawns moles into game
    {
        @Override
        public void run()
        {
            int seconds=0;

            while(!exit)
            {
                Log.w("Seconds passed", seconds + " seconds passed");


                if(seconds % 2 == 0)
                {
                    tickHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            Random randHole = new Random(); //mole emerges from 1 random hole
                            Random randVal = new Random(); //each mole gets assigned a random value
                            Random choice = new Random();

                            int val = 0;
                            int n = randHole.nextInt(4);

                            int ansOrNot = choice.nextInt(10); //sets "mole with answer value" spawn rate to 0.33

                            if(ansOrNot < 4)
                            {
                                val = answer;
                            }

                            else
                            {
                                val = randVal.nextInt(((answer+5) - (answer-5)) + 1) + (answer-5); //random value of +-5 spawns in with 0.66 chance
                            }


                            holes[n].setImageDrawable(getResources().getDrawable(R.drawable.mole));
                            emerge = n;
                            values[n].setVisibility(View.VISIBLE);
                            values[n].setText(val + "");
                        }
                    });
                }
                try
                {
                    Thread.sleep(1000); //each tick/cycle = 1 second
                    seconds++;
                }
                catch(InterruptedException e)
                {
                    e.printStackTrace();
                    Toast.makeText(Diglett.this, "Error", Toast.LENGTH_SHORT).show();
                }

                tickHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        for(int c=0; c<4; c++)
                        {
                            holes[c].setImageDrawable(getResources().getDrawable(R.drawable.hole));
                            values[c].setVisibility(View.INVISIBLE);
                            emerge = -1;
                        }
                    }
                });
            }
        }

        public void kill()
        {
            exit = true;
        }
    }
}
