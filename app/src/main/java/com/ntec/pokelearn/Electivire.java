package com.ntec.pokelearn;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class Electivire extends AppCompatActivity {

    ImageView bulb1, bulb2, holder, item1, item2, item3, item4, item5, emptyBox;
    int rand1, rand2, rand3, rand4, rand5, randPos1, randPos2, randPos3, randPos4, randPos5, score = 0;
    TextView scoreView;
    Dialog myDialog;
    String conductivity;
    int[] itemCon = { R.drawable.aluminium, R.drawable.battery, R.drawable.coin, R.drawable.copper,  R.drawable.fork, R.drawable.gold, R.drawable.knife,
            R.drawable.key, R.drawable.nail, R.drawable.nickel,  R.drawable.padlock,  R.drawable.paperclip, R.drawable.platinum,  R.drawable.spoon,
            R.drawable.scissors, R.drawable.water};

    int[] itemIns = {R.drawable.apple, R.drawable.candle, R.drawable.cloth, R.drawable.cork, R.drawable.crayon, R.drawable.eraser, R.drawable.pencil, R.drawable.oil,
            R.drawable.wood, R.drawable.paper, R.drawable.plastic, R.drawable.rubberduck, R.drawable.ruler, R.drawable.sponge};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.electivire_activity);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        bulb1 = (ImageView) findViewById(R.id.bulbx1);
        bulb2 = (ImageView) findViewById(R.id.bulbx2);
        bulb1.setVisibility(View.INVISIBLE);
        bulb2.setVisibility(View.INVISIBLE);
        holder = (ImageView) findViewById(R.id.imageHolder);
        holder.setVisibility(View.INVISIBLE);

        emptyBox = (ImageView) findViewById(R.id.imageBox);
        emptyBox.setOnDragListener(new ChoiceDragListener());

        item1 = (ImageView) findViewById(R.id.item1);
        item1.setOnTouchListener(new ChoiceTouchListener());
        item1.setOnDragListener(new ChoiceDragListener());

        item2 = (ImageView) findViewById(R.id.item2);
        item2.setOnTouchListener(new ChoiceTouchListener());
        item2.setOnDragListener(new ChoiceDragListener());

        item3 = (ImageView) findViewById(R.id.item3);
        item3.setOnTouchListener(new ChoiceTouchListener());
        item3.setOnDragListener(new ChoiceDragListener());

        item4 = (ImageView) findViewById(R.id.item4);
        item4.setOnTouchListener(new ChoiceTouchListener());
        item4.setOnDragListener(new ChoiceDragListener());

        item5 = (ImageView) findViewById(R.id.item5);
        item5.setOnTouchListener(new ChoiceTouchListener());
        item5.setOnDragListener(new ChoiceDragListener());

        scoreView = (TextView) findViewById(R.id.textScore);
        scoreView.setText("Scores: " + String.valueOf(score));

        randomList();
    }

    public void returnClicked(View view) {
        startActivity(new Intent(Electivire.this, MainActivity.class));
    }

    private final class ChoiceTouchListener implements View.OnTouchListener {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN && ((ImageView)v).getDrawable() != null){
                ClipData data = ClipData.newPlainText("","");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                v.startDrag(data, shadowBuilder, v,0);
                return true;
            }  else {
                return false;
            }
        }
    }

    private class ChoiceDragListener implements View.OnDragListener {

        @Override
        public boolean onDrag(View v, DragEvent event) {

            switch (event.getAction()) {

                case DragEvent.ACTION_DROP:
                    ImageView view = (ImageView) event.getLocalState();

                    if (view == item1) {
                        ((ImageView) v).setImageDrawable(item1.getDrawable());
                    }

                    else if (view == item2) {
                        ((ImageView) v).setImageDrawable(item2.getDrawable());
                    }

                    else if (view == item3) {
                        ((ImageView) v).setImageDrawable(item3.getDrawable());
                    }

                    else if (view == item4) {
                        ((ImageView) v).setImageDrawable(item4.getDrawable());
                    }

                    else if (view == item5) {
                        ((ImageView) v).setImageDrawable(item5.getDrawable());
                    }

                    setHolder();
                    checkLight();
                    updateScore();
                    break;
            }

            return true;
        }
    }

    public void randomList(){
        Random rand = new Random();

        rand1 = itemCon[rand.nextInt(itemCon.length)];
        rand2 = itemIns[rand.nextInt(itemIns.length)];
        while (rand2 == rand1)
            rand2 = itemIns[rand.nextInt(itemIns.length)];
        rand3 = itemIns[rand.nextInt(itemIns.length)];
        while (rand3 == rand1 || rand3 == rand2)
            rand3 = itemIns[rand.nextInt(itemIns.length)];
        rand4 = itemIns[rand.nextInt(itemIns.length)];
        while (rand4 == rand1 || rand4 == rand2 || rand4 == rand3)
            rand4 = itemIns[rand.nextInt(itemIns.length)];
        rand5 = itemIns[rand.nextInt(itemIns.length)];
        while (rand5 == rand1 || rand5 == rand2 || rand5 == rand3 || rand5 == rand4)
            rand5 = itemIns[rand.nextInt(itemIns.length)];

        int itemPos[] = {rand1, rand2, rand3, rand4, rand5};
        randPos1 = itemPos[rand.nextInt(itemPos.length)];
        randPos2 = itemPos[rand.nextInt(itemPos.length)];
        while (randPos2 == randPos1)
            randPos2 = itemPos[rand.nextInt(itemPos.length)];
        randPos3 = itemPos[rand.nextInt(itemPos.length)];
        while (randPos3 == randPos1 || randPos3 == randPos2)
            randPos3 = itemPos[rand.nextInt(itemPos.length)];
        randPos4 = itemPos[rand.nextInt(itemPos.length)];
        while (randPos4 == randPos1 || randPos4 == randPos2 || randPos4 == randPos3)
            randPos4 = itemPos[rand.nextInt(itemPos.length)];
        randPos5 = itemPos[rand.nextInt(itemPos.length)];
        while (randPos5 == randPos1 || randPos5 == randPos2 || randPos5 == randPos3 || randPos5 == randPos4)
            randPos5 = itemPos[rand.nextInt(itemPos.length)];

        item1.setImageResource(randPos1);
        item2.setImageResource(randPos2);
        item3.setImageResource(randPos3);
        item4.setImageResource(randPos4);
        item5.setImageResource(randPos5);
    }

    public void checkLight(){

        if (emptyBox.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.battery).getConstantState())){
            bulb1.setVisibility(View.INVISIBLE);
            bulb2.setVisibility(View.VISIBLE);
            conductivity = "best";
        }

        else if (emptyBox.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.aluminium).getConstantState()) ||
                emptyBox.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.coin).getConstantState()) ||
                emptyBox.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.copper).getConstantState()) ||
                emptyBox.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.fork).getConstantState()) ||
                emptyBox.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.nickel).getConstantState()) ||
                emptyBox.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.padlock).getConstantState()) ||
                emptyBox.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.gold).getConstantState()) ||
                emptyBox.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.knife).getConstantState()) ||
                emptyBox.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.key).getConstantState())||
                emptyBox.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.nail).getConstantState()) ||
                emptyBox.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.paperclip).getConstantState()) ||
                emptyBox.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.platinum).getConstantState()) ||
                emptyBox.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.water).getConstantState()) ||
                emptyBox.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.scissors).getConstantState()) ||
                emptyBox.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.spoon).getConstantState())) {
            bulb1.setVisibility(View.VISIBLE);
            bulb2.setVisibility(View.INVISIBLE);
            conductivity = "conductor";
        }

        else if (emptyBox.getDrawable().getConstantState().equals((getResources().getDrawable(R.drawable.transparantbox).getConstantState()))){
            conductivity = "empty";
        }

        else {
            bulb1.setVisibility(View.INVISIBLE);
            bulb2.setVisibility(View.INVISIBLE);
            conductivity = "insulator";
        }
    }

    public void checkConductivity(){

        if (conductivity.equals("conductor")) {
            AlertDialog.Builder builder = new AlertDialog.Builder(Electivire.this);
            builder.setTitle("Explanation")
                    .setMessage("This is a conductor. Thus, it can conduct electricity and makes the light bulb lights up.")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
            builder.create().show();
        }

        else if (conductivity.equals("best")) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Explanation")
                    .setMessage("This is a battery. It can increase the electricity and makes the light bulb lights brighter.")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
            builder.create().show();
        }

        else if (conductivity.equals("insulator")) {
            AlertDialog.Builder builder = new AlertDialog.Builder(Electivire.this);
            builder.setTitle("Explanation")
                    .setMessage("This is an insulator. Thus, it cannot conduct electricity and cannot makes the light bulb lights up.")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
            builder.create().show();
        }

        else if (conductivity.equals("empty")) {
            AlertDialog.Builder builder = new AlertDialog.Builder(Electivire.this);
            builder.setTitle("Error")
                    .setMessage("There is nothing in the circuit. Please complete the circuit by filling in any item.")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
            builder.create().show();
        }
    }

    public void updateScore(){
        if (conductivity.equals("conductor") || conductivity.equals("best")) {
            score ++;
            scoreView.setText("Scores: " + String.valueOf(score));
        }
    }

    public void setHolder(){
        if (!(emptyBox.getDrawable().getConstantState().equals((getResources().getDrawable(R.drawable.transparantbox).getConstantState())))) {
            holder.setVisibility(View.VISIBLE);
        }

        else
            holder.setVisibility(View.INVISIBLE);
    }

    public void explainClicked(View view) {
        checkLight();
        checkConductivity();
    }

    public void helpClicked(View view) {
        myDialog = new Dialog(this);
        myDialog.setContentView(R.layout.pop_window);
        myDialog.show();
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    public void resetClicked(View view) {
        randomList();
    }
}