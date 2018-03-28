/*
 *
 */
package com.example.android.miwok.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.android.miwok.R;
import com.example.android.miwok.helper.AudioPlaybackManager;
import com.example.android.miwok.helper.WordAdapter;
import com.example.android.miwok.model.Word;

import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity {

    private AudioPlaybackManager mAudioPlaybackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_list);

        mAudioPlaybackManager = new AudioPlaybackManager(this);
        mAudioPlaybackManager.initialize();

        // Create a list of words
        final ArrayList<Word> familyWords = new ArrayList<>();
        familyWords.add(new Word("Father", "әpә", R.drawable.family_father, R.raw.family_father));
        familyWords.add(new Word("Mother", "әṭa", R.drawable.family_mother, R.raw.family_mother));
        familyWords.add(new Word("Son", "angsi", R.drawable.family_son, R.raw.family_son));
        familyWords.add(new Word("Daughter", "tune", R.drawable.family_daughter, R.raw.family_daughter));
        familyWords.add(new Word("Older brother", "taachi", R.drawable.family_older_brother, R.raw.family_older_brother));
        familyWords.add(new Word("Younger brother", "chalitti", R.drawable.family_younger_brother, R.raw.family_younger_brother));
        familyWords.add(new Word("Older sister", "teṭe", R.drawable.family_older_sister, R.raw.family_older_sister));
        familyWords.add(new Word("Younger sister", "kolliti", R.drawable.family_younger_sister, R.raw.family_younger_sister));
        familyWords.add(new Word("Grandmother", "ama", R.drawable.family_grandmother, R.raw.family_grandmother));
        familyWords.add(new Word("Grandfather", "paapa", R.drawable.family_grandfather, R.raw.family_grandfather));

        WordAdapter adapter = new WordAdapter(this, familyWords);
        ListView listView = findViewById(R.id.words_list_view);
        listView.setAdapter(adapter);
        listView.setBackgroundResource(R.color.category_family);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mAudioPlaybackManager.playAudio(familyWords.get(i).getAudioResourceId());
            }
        });

    }

    @Override
    protected void onStop() {
        super.onStop();
        mAudioPlaybackManager.releaseMediaPlayer();
    }
}
