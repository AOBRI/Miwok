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
public class PhrasesFragment extends Fragment {


    AudioPlaybackManager mAudioPlaybackManager;

    public PhrasesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list, container, false);

        mAudioPlaybackManager = new AudioPlaybackManager(getActivity());
        mAudioPlaybackManager.initialize();

        // Create a list of words
        final ArrayList<Word> phraseWords = new ArrayList<>();
        phraseWords.add(new Word("Where are you going?", "minto wuksus", R.raw.phrase_where_are_you_going));
        phraseWords.add(new Word("What is your name?", "tinnә oyaase'nә", R.raw.phrase_what_is_your_name));
        phraseWords.add(new Word("My name is...", "oyaaset...", R.raw.phrase_my_name_is));
        phraseWords.add(new Word("How are you feeling?", "michәksәs?", R.raw.phrase_how_are_you_feeling));
        phraseWords.add(new Word("I’m feeling good.", "kuchi achit", R.raw.phrase_im_feeling_good));
        phraseWords.add(new Word("Are you coming?", "әәnәs'aa?", R.raw.phrase_are_you_coming));
        phraseWords.add(new Word("Yes, I’m coming.", "hәә’ әәnәm", R.raw.phrase_yes_im_coming));
        phraseWords.add(new Word("I’m coming.", "әәnәm", R.raw.phrase_im_coming));
        phraseWords.add(new Word("Let’s go.", "yoowutis", R.raw.phrase_lets_go));
        phraseWords.add(new Word("Come here.", "әnni'nem", R.raw.phrase_come_here));

        WordAdapter adapter = new WordAdapter(getActivity(), phraseWords);
        ListView listView = rootView.findViewById(R.id.words_list_view);
        listView.setAdapter(adapter);
        listView.setBackgroundResource(R.color.category_phrases);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mAudioPlaybackManager.playAudio(phraseWords.get(i).getAudioResourceId());
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
