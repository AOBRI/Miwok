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
package com.example.android.miwok;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

        ArrayList<String> numberWords = new ArrayList<>();
        numberWords.add("One");
        numberWords.add("Two");
        numberWords.add("Three");
        numberWords.add("Four");
        numberWords.add("Five");
        numberWords.add("Six");
        numberWords.add("Seven");
        numberWords.add("Eight");
        numberWords.add("Nine");
        numberWords.add("Ten");

        LinearLayout linearLayout = findViewById(R.id.numbers_rootview);
        TextView textView;

        for (int i = 0; i < 10; i++) {
            textView = new TextView(this);
            textView.setText(numberWords.get(i));
            linearLayout.addView(textView);
        }
    }
}
