package com.ntec.pokelearn;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.ValueAnimator;
import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityPlanetarium extends AppCompatActivity {

    private TextView textMercury,textVenus,textEarth,textMars,textJupiter,textSaturn,textUranus,textNeptune;
    private TextView targetMercury,targetVenus,targetEarth,targetMars,targetJupiter,targetSaturn,targetUranus,targetNeptune;
    private ImageView imageMercury,imageVenus,imageEarth,imageMars,imageJupiter,imageSaturn,imageUranus,imageNeptune,imageSun, imageQuiz;
    private SingleToast toast = new SingleToast();
    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planetarium);

        final ImageView backgroundOne = (ImageView) findViewById(R.id.background_one);
        final ImageView backgroundTwo = (ImageView) findViewById(R.id.background_two);

        //set moving background
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

        textMercury = findViewById(R.id.textMercury);
        textVenus = findViewById(R.id.textVenus);
        textEarth = findViewById(R.id.textEarth);
        textMars = findViewById(R.id.textMars);
        textJupiter = findViewById(R.id.textJupiter);
        textSaturn = findViewById(R.id.textSaturn);
        textUranus = findViewById(R.id.textUranus);
        textNeptune = findViewById(R.id.textNeptune);


        targetMercury = findViewById(R.id.targetMercury);
        targetVenus = findViewById(R.id.targetVenus);
        targetEarth = findViewById(R.id.targetEarth);
        targetMars = findViewById(R.id.targetMars);
        targetJupiter = findViewById(R.id.targetJupiter);
        targetSaturn = findViewById(R.id.targetSaturn);
        targetUranus = findViewById(R.id.targetUranus);
        targetNeptune = findViewById(R.id.targetNeptune);

        textMercury.setOnLongClickListener(longClickListener);
        textVenus.setOnLongClickListener(longClickListener);
        textEarth.setOnLongClickListener(longClickListener);
        textMars.setOnLongClickListener(longClickListener);
        textJupiter.setOnLongClickListener(longClickListener);
        textSaturn.setOnLongClickListener(longClickListener);
        textUranus.setOnLongClickListener(longClickListener);
        textNeptune.setOnLongClickListener(longClickListener);

        targetMercury.setOnDragListener(dragListener);
        targetVenus.setOnDragListener(dragListener);
        targetEarth.setOnDragListener(dragListener);
        targetMars.setOnDragListener(dragListener);
        targetJupiter.setOnDragListener(dragListener);
        targetSaturn.setOnDragListener(dragListener);
        targetUranus.setOnDragListener(dragListener);
        targetNeptune.setOnDragListener(dragListener);

        imageMercury = findViewById(R.id.imageMercury);
        imageVenus = findViewById(R.id.imageVenus);
        imageEarth = findViewById(R.id.imageEarth);
        imageMars = findViewById(R.id.imageMars);
        imageJupiter = findViewById(R.id.imageJupiter);
        imageSaturn = findViewById(R.id.imageSaturn);
        imageUranus = findViewById(R.id.imageUranus);
        imageNeptune = findViewById(R.id.imageNeptune);
        imageSun = findViewById(R.id.imageSun);


        //checks if all items are matched correctly
        //allow player to go to another activity
        imageMercury.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count==8) {
                    startActivity(new Intent(ActivityPlanetarium.this, ActivityMercury.class));
                }
                else{
                    toast.show(getApplication(),"Match the planet names correctly and then check back again! ",Toast.LENGTH_SHORT);
                }
            }
        });
        imageVenus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count==8) {
                    startActivity(new Intent(ActivityPlanetarium.this, ActivityVenus.class));
                }
                else{
                    toast.show(getApplication(),"Match the planet names correctly and then check back again! ",Toast.LENGTH_SHORT);
                }
            }
        });
        imageEarth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count==8) {
                    startActivity(new Intent(ActivityPlanetarium.this, ActivityEarth.class));
                }
                else{
                    toast.show(getApplication(),"Match the planet names correctly and then check back again! ",Toast.LENGTH_SHORT);
                }
            }
        });
        imageMars.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(count==8) {
                    startActivity(new Intent(ActivityPlanetarium.this, ActivityMars.class));
                }
                else{
                    toast.show(getApplication(),"Match the planet names correctly and then check back again! ",Toast.LENGTH_SHORT);
                }
            }
        });
        imageJupiter.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(count==8) {
                    startActivity(new Intent(ActivityPlanetarium.this, ActivityJupiter.class));
                }
                else{
                    toast.show(getApplication(),"Match the planet names correctly and then check back again! ",Toast.LENGTH_SHORT);
                }
            }
        });
        imageSaturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count==8) {
                    startActivity(new Intent(ActivityPlanetarium.this, ActivitySaturn.class));
                }
                else{
                    toast.show(getApplication(),"Match the planet names correctly and then check back again! ",Toast.LENGTH_SHORT);
                }
            }
        });
        imageUranus.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(count==8) {
                    startActivity(new Intent(ActivityPlanetarium.this, ActivityUranus.class));
                }
                else{
                    toast.show(getApplication(),"Match the planet names correctly and then check back again! ",Toast.LENGTH_SHORT);
                }
            }
        });
        imageNeptune.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(count==8) {
                    startActivity(new Intent(ActivityPlanetarium.this, ActivityNeptune.class));
                }
                else{
                    toast.show(getApplication(),"Match the planet names correctly and then check back again! ",Toast.LENGTH_SHORT);
                }
            }
        });
        imageSun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count==8) {
                    startActivity(new Intent(ActivityPlanetarium.this, ActivitySun.class));
                }
                else{
                    toast.show(getApplication(),"Match the planet names correctly and then check back again! ",Toast.LENGTH_SHORT);
                }
            }
        });
    }

    //allow user to drag and drop the planet names in the game
    View.OnLongClickListener longClickListener = new View.OnLongClickListener(){
        @Override
        public boolean onLongClick(View v) {
            ClipData data = ClipData.newPlainText("","");
            View.DragShadowBuilder myShadowBuilder = new View.DragShadowBuilder(v);
            v.startDrag(data,myShadowBuilder,v,0);
            return true;
        }
    };

    View.OnDragListener dragListener = new View.OnDragListener() {
        @Override
        public boolean onDrag(View v, DragEvent event) {

            int dragEvent = event.getAction();

            //switch case implemented to check if the element match correctly with the planet
            switch (dragEvent){
                case DragEvent.ACTION_DROP:
                    final View view = (View) event.getLocalState();

                    if (view.getId() == R.id.textMercury && v.getId() == R.id.targetMercury) {
                        textMercury.setVisibility(View.GONE);
                        targetMercury.setText("Mercury");
                        targetMercury.setTextColor(Color.parseColor("#ffff00"));
                        targetMercury.setBackgroundColor(Color.parseColor("#E5565656"));
                        count++;
                    }
                    else if (view.getId() == R.id.textVenus && v.getId() == R.id.targetVenus){
                        textVenus.setVisibility(View.GONE);
                        targetVenus.setText("Venus");
                        targetVenus.setTextColor(Color.parseColor("#ffff00"));
                        targetVenus.setBackgroundColor(Color.parseColor("#E5565656"));
                        count++;
                    }
                    else if (view.getId() == R.id.textEarth && v.getId() == R.id.targetEarth) {
                        textEarth.setVisibility(View.GONE);
                        targetEarth.setText("Earth");
                        targetEarth.setTextColor(Color.parseColor("#ffff00"));
                        targetEarth.setBackgroundColor(Color.parseColor("#E5565656"));
                        count++;
                    }
                    else if (view.getId() == R.id.textMars && v.getId() == R.id.targetMars){
                        textMars.setVisibility(View.GONE);
                        targetMars.setText("Mars");
                        targetMars.setTextColor(Color.parseColor("#ffff00"));
                        targetMars.setBackgroundColor(Color.parseColor("#E5565656"));
                        count++;
                    }
                    else if (view.getId() == R.id.textJupiter && v.getId() == R.id.targetJupiter) {
                        textJupiter.setVisibility(View.GONE);
                        targetJupiter.setText("Jupiter");
                        targetJupiter.setTextColor(Color.parseColor("#ffff00"));
                        targetJupiter.setBackgroundColor(Color.parseColor("#E5565656"));
                        count++;
                    }
                    else if (view.getId() == R.id.textSaturn && v.getId() == R.id.targetSaturn){
                        textSaturn.setVisibility(View.GONE);
                        targetSaturn.setText("Saturn");
                        targetSaturn.setTextColor(Color.parseColor("#ffff00"));
                        targetSaturn.setBackgroundColor(Color.parseColor("#E5565656"));
                        count++;
                    }
                    else if (view.getId() == R.id.textUranus && v.getId() == R.id.targetUranus) {
                        textUranus.setVisibility(View.GONE);
                        targetUranus.setText("Uranus");
                        targetUranus.setTextColor(Color.parseColor("#ffff00"));
                        targetUranus.setBackgroundColor(Color.parseColor("#E5565656"));
                        count++;
                    }
                    else if (view.getId() == R.id.textNeptune && v.getId() == R.id.targetNeptune){
                        textNeptune.setVisibility(View.GONE);
                        targetNeptune.setText("Neptune");
                        targetNeptune.setTextColor(Color.parseColor("#ffff00"));
                        targetNeptune.setBackgroundColor(Color.parseColor("#E5565656"));
                        count++;
                    }
                    else{
                        MediaPlayer wrongSound = MediaPlayer.create(ActivityPlanetarium.this,R.raw.invulnerable);
                        wrongSound.start();
                    }
                    break;

            }
            return true;
        }
    };

    //back button press confirmation
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to leave?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        ActivityPlanetarium.super.onBackPressed();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

}
