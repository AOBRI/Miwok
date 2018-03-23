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
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_list);

        // Create a list of words
        ArrayList<Word> colorWords = new ArrayList<>();
        colorWords.add(new Word("Brown", "ṭakaakki", R.drawable.color_brown, R.raw.color_brown));
        colorWords.add(new Word("Red", "weṭeṭṭi", R.drawable.color_red, R.raw.color_red));
        colorWords.add(new Word("Green", "chokokki", R.drawable.color_green, R.raw.color_green));
        colorWords.add(new Word("Mustard Yellow", "chiwiiṭә", R.drawable.color_mustard_yellow, R.raw.color_mustard_yellow));
        colorWords.add(new Word("Dusty Yellow ", "ṭopiisә", R.drawable.color_dusty_yellow, R.raw.color_dusty_yellow));
        colorWords.add(new Word("White", "kelelli", R.drawable.color_white, R.raw.color_white));
        colorWords.add(new Word("Gray", "ṭopoppi", R.drawable.color_gray, R.raw.color_gray));
        colorWords.add(new Word("Black", "kululli", R.drawable.color_black, R.raw.color_black));

        WordAdapter adapter = new WordAdapter(this, colorWords, R.color.category_colors);
        ListView listView = findViewById(R.id.words_list_view);
        listView.setAdapter(adapter);
    }
}
