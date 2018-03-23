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

public class FamilyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_list);

        // Create a list of words
        ArrayList<Word> familyWords = new ArrayList<>();
        familyWords.add(new Word("Father", "әpә"));
        familyWords.add(new Word("Mother", "әṭa"));
        familyWords.add(new Word("Son", "angsi"));
        familyWords.add(new Word("Daughter", "tune"));
        familyWords.add(new Word("Older brother", "taachi"));
        familyWords.add(new Word("Younger brother", "chalitti"));
        familyWords.add(new Word("Older sister", "teṭe"));
        familyWords.add(new Word("Younger sister", "kolliti"));
        familyWords.add(new Word("Grandmother", "ama"));
        familyWords.add(new Word("Grandfather", "paapa"));

        WordAdapter adapter = new WordAdapter(this, familyWords);
        ListView listView = findViewById(R.id.words_list_view);
        listView.setAdapter(adapter);
    }
}
