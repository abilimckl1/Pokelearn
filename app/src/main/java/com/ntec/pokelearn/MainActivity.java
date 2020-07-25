package com.ntec.pokelearn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button mFlipmath, mElectivire, mMrmime, mDiglett, mDeoxys;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFlipmath = findViewById(R.id.game_flipmath);
        mElectivire = findViewById(R.id.game_electivire);
        mMrmime = findViewById(R.id.game_mrmime);
        mDiglett = findViewById(R.id.game_diglett);
        mDeoxys = findViewById(R.id.game_deoxys);

        mFlipmath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FlipMathActivity.class));
            }
        });

        mElectivire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Electivire.class));
            }
        });

        mMrmime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MrMime.class));
            }
        });

        mDiglett.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Diglett.class));
            }
        });

        mDeoxys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ActivityMainMenuDeoxys.class));
            }
        });
    }
}
