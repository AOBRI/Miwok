package com.example.android.miwok2.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
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
public class NumbersFragment extends Fragment {

    AudioPlaybackManager mAudioPlaybackManager;


    public NumbersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list, container, false);

        mAudioPlaybackManager = new AudioPlaybackManager(getActivity());
        mAudioPlaybackManager.initialize();

        // Create a list of words
        final ArrayList<Word> numberWords = new ArrayList<>();
        numberWords.add(new Word("one", "lutti", R.drawable.number_one, R.raw.number_one));
        numberWords.add(new Word("two", "otiiko", R.drawable.number_two, R.raw.number_two));
        numberWords.add(new Word("three", "tolookosu", R.drawable.number_three, R.raw.number_three));
        numberWords.add(new Word("four", "oyyisa", R.drawable.number_four, R.raw.number_four));
        numberWords.add(new Word("five", "massokka", R.drawable.number_five, R.raw.number_five));
        numberWords.add(new Word("six", "temmokka", R.drawable.number_six, R.raw.number_six));
        numberWords.add(new Word("seven", "kenekaku", R.drawable.number_seven, R.raw.number_seven));
        numberWords.add(new Word("eight", "kawinta", R.drawable.number_eight, R.raw.number_eight));
        numberWords.add(new Word("nine", "wo’e", R.drawable.number_nine, R.raw.number_nine));
        numberWords.add(new Word("ten", "na’aacha", R.drawable.number_ten, R.raw.number_ten));

        WordAdapter adapter = new WordAdapter(getActivity(), numberWords);
        ListView listView = rootView.findViewById(R.id.words_list_view);
        listView.setBackgroundResource(R.color.category_numbers);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mAudioPlaybackManager.playAudio(numberWords.get(i).getAudioResourceId());
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
