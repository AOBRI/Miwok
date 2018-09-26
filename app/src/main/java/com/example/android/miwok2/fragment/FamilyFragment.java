package com.example.android.miwok2.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.android.miwok2.R;
import com.example.android.miwok2.helper.AudioPlaybackManager;
import com.example.android.miwok2.helper.WordAdapter;
import com.example.android.miwok2.model.Word;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FamilyFragment extends Fragment {


    AudioPlaybackManager mAudioPlaybackManager;

    public FamilyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list, container, false);

        mAudioPlaybackManager = new AudioPlaybackManager(getActivity());
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

        WordAdapter adapter = new WordAdapter(getActivity(), familyWords);
        ListView listView = rootView.findViewById(R.id.words_list_view);
        listView.setAdapter(adapter);
        listView.setBackgroundResource(R.color.category_family);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mAudioPlaybackManager.playAudio(familyWords.get(i).getAudioResourceId());
            }
        });

        return rootView;
    }

    @Override
    public void onStop() {
        super.onStop();
        mAudioPlaybackManager.releaseMediaPlayer();
    }
}
