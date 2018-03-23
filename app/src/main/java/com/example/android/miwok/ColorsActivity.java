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
        colorWords.add(new Word("Red", "weṭeṭṭi"));
        colorWords.add(new Word("Green", "chokokki"));
        colorWords.add(new Word("Brown", "ṭakaakki"));
        colorWords.add(new Word("Gray", "ṭopoppi"));
        colorWords.add(new Word("Black", "kululli"));
        colorWords.add(new Word("White", "kelelli"));
        colorWords.add(new Word("Dusty Yellow ", "ṭopiisә"));
        colorWords.add(new Word("Mustard Yellow", "chiwiiṭә"));

        WordAdapter adapter = new WordAdapter(this, colorWords);
        ListView listView = findViewById(R.id.words_list_view);
        listView.setAdapter(adapter);
    }
}
