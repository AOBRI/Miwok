/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.miwok.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;

import com.example.android.miwok.R;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.activity_main);

        // Find the TextView that shows the category name and set its click listener
        findViewById(R.id.numbers).setOnClickListener(this);
        findViewById(R.id.family).setOnClickListener(this);
        findViewById(R.id.colors).setOnClickListener(this);
        findViewById(R.id.phrases).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.numbers:
                startNumbersActivity();
                break;
            case R.id.family:
                startFamilyActivity();
                break;
            case R.id.colors:
                startColorsActivity();
                break;
            case R.id.phrases:
                startPhrasesActivity();
                break;
        }
    }

    private void startNumbersActivity() {
        Intent numbersIntent = new Intent(MainActivity.this, NumbersActivity.class);
        startActivity(numbersIntent);
    }

    private void startFamilyActivity() {
        Intent familyIntent = new Intent(MainActivity.this, FamilyActivity.class);
        startActivity(familyIntent);
    }

    private void startColorsActivity() {
        Intent colorsIntent = new Intent(MainActivity.this, ColorsActivity.class);
        startActivity(colorsIntent);
    }

    private void startPhrasesActivity() {
        Intent phrasesIntent = new Intent(MainActivity.this, PhrasesActivity.class);
        startActivity(phrasesIntent);
    }
}
