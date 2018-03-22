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

public class NumbersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

        // Create a list of words
        ArrayList<Word> numberWords = new ArrayList<>();
        numberWords.add(new Word("one", "lutti"));
        numberWords.add(new Word("two", "otiiko"));
        numberWords.add(new Word("three", "tolookosu"));
        numberWords.add(new Word("four", "oyyisa"));
        numberWords.add(new Word("five", "massokka"));
        numberWords.add(new Word("six", "temmokka"));
        numberWords.add(new Word("seven", "kenekaku"));
        numberWords.add(new Word("eight", "kawinta"));
        numberWords.add(new Word("nine", "wo’e"));
        numberWords.add(new Word("ten", "na’aacha"));

//        LinearLayout linearLayout = findViewById(R.id.numbers_rootview);
//        TextView textView;
//        for (int i = 0; i < numberWords.size(); i++) {
//            textView = new TextView(this);
//            textView.setText(numberWords.get(i));
//            linearLayout.addView(textView);
//        }

        // Create an {@link WordAdapter}, whose data source is a list of {@link Word}s. The
        // adapter knows how to create list items for each item in the list.
        WordAdapter adapter = new WordAdapter(this, numberWords);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // activity_numbers.xml layout file.
        ListView listView = findViewById(R.id.numbers_list_view);

        // Make the {@link ListView} use the {@link WordAdapter} we created above, so that the
        // {@link ListView} will display list items for each {@link Word} in the list.
        listView.setAdapter(adapter);
    }
}
