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
public class ColorsFragment extends Fragment {

    AudioPlaybackManager mAudioPlaybackManager;


    public ColorsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.word_list, container, false);

        mAudioPlaybackManager = new AudioPlaybackManager(getActivity());
        mAudioPlaybackManager.initialize();

        // Create a list of words
        final ArrayList<Word> colorWords = new ArrayList<>();
        colorWords.add(new Word("Brown", "ṭakaakki", R.drawable.color_brown, R.raw.color_brown));
        colorWords.add(new Word("Red", "weṭeṭṭi", R.drawable.color_red, R.raw.color_red));
        colorWords.add(new Word("Green", "chokokki", R.drawable.color_green, R.raw.color_green));
        colorWords.add(new Word("Mustard Yellow", "chiwiiṭә", R.drawable.color_mustard_yellow, R.raw.color_mustard_yellow));
        colorWords.add(new Word("Dusty Yellow ", "ṭopiisә", R.drawable.color_dusty_yellow, R.raw.color_dusty_yellow));
        colorWords.add(new Word("White", "kelelli", R.drawable.color_white, R.raw.color_white));
        colorWords.add(new Word("Gray", "ṭopoppi", R.drawable.color_gray, R.raw.color_gray));
        colorWords.add(new Word("Black", "kululli", R.drawable.color_black, R.raw.color_black));

        WordAdapter adapter = new WordAdapter(getActivity(), colorWords);
        ListView listView = rootView.findViewById(R.id.words_list_view);
        listView.setAdapter(adapter);
        listView.setBackgroundResource(R.color.category_colors);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mAudioPlaybackManager.playAudio(colorWords.get(i).getAudioResourceId());
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
