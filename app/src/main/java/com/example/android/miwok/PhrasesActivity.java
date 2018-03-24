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

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {

    /**
     * Member variables used for managing audio focus for this activity.
     */
    private AudioManager mAudioManager;
    private AudioAttributes mAudioPlaybackAttributes;
    private AudioFocusRequest mAudioFocusRequest;
    private Handler mAudioFocusHandler = new Handler();
    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener;
    /**
     * Member varaibles necessary for word translation audio playback.
     */
    private MediaPlayer mMediaPlayer;
    private MediaPlayer.OnCompletionListener mOnCompletionListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_list);

        //request the AudioManager service from the android system.
        mAudioManager = (AudioManager) getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
        // this is required setup! initialization of private variables and listeners used in the AudioManager and MediaPlayer setup.
        prepareAudioConfigurations();

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

        WordAdapter adapter = new WordAdapter(this, phraseWords, R.color.category_phrases);
        ListView listView = findViewById(R.id.words_list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // release the media player to stop any previous playbacks.
                releaseMediaPlayer();

                // request audio focus from the AudioManager and store the system response in audioFocusResult to be handled later.
                int audioFocusResult = requestAudioFocus();

                // handle the audio focus request result
                switch (audioFocusResult) {
                    case AudioManager.AUDIOFOCUS_REQUEST_GRANTED:
                        // if focus is granted create a media player with the clicked word's audio resource ID as input.
                        mMediaPlayer = MediaPlayer.create(PhrasesActivity.this, phraseWords.get(i).getAudioResourceId());
                        // set the OnCompletionListener to automatically handle what is done after playback is finished.
                        mMediaPlayer.setOnCompletionListener(mOnCompletionListener);
                        // start audio playback
                        mMediaPlayer.start();
                        break;
                    // other cases to be handled if needed.
                    case AudioManager.AUDIOFOCUS_REQUEST_FAILED:
                        break;
                    case AudioManager.AUDIOFOCUS_REQUEST_DELAYED:
                        break;
                }
            }
        });

    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    /**
     * Prepares the global variables used for audio playback and audio focus requests.
     * These listeners and objects are necessary for handling focus and playback and must be declared and prepared.
     * Specifically, mOnAudioFocusChangeListener , mOnCompletionListener in all android versions.
     * In OREO (API 26 ) and above mAudioPlaybackAttributes and mAudioFocusRequest are also prepared.
     */
    private void prepareAudioConfigurations() {

        // Initialization of OnAudioFocusChangeListener.
        mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
            @Override
            public void onAudioFocusChange(int i) {
                switch (i) {
                    case AudioManager.AUDIOFOCUS_GAIN:
                        // if audio focus is gained after a request, start audio/media playback.
                        mMediaPlayer.start();
                        break;
                    case AudioManager.AUDIOFOCUS_LOSS:
                        // if audio focus is lost release the media player and abandon focus.
                        releaseMediaPlayer();
                        break;
                    case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                    case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                        // if audio focus is lost temporarily (when changing to other apps),
                        // pause and restart the audio playback without releasing media player.
                        // in case of DUCKing, volume should be lowered instead but not required in this app.
                        mMediaPlayer.pause();
                        mMediaPlayer.seekTo(0);
                        break;
                }
            }
        };

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            mAudioPlaybackAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
                    .build();

            mAudioFocusRequest = new AudioFocusRequest.Builder(AudioManager.AUDIOFOCUS_GAIN)
                    .setAudioAttributes(mAudioPlaybackAttributes)
                    .setAcceptsDelayedFocusGain(true)
                    .setOnAudioFocusChangeListener(mOnAudioFocusChangeListener, mAudioFocusHandler)
                    .build();

        }

        mOnCompletionListener = new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                releaseMediaPlayer();
            }
        };
    }

    /**
     * Request transient audio focus from the android system depending on the API used
     * for OREO or above the new AudioFocusRequest object is used
     * for older versions OnAudioFocusChangeListener is used directly.
     *
     * @return result the result of the audio focus request(used to handle audio playback on list item click).
     */
    private int requestAudioFocus() {
        // the result of audio focus request.
        int result = -1;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // in OREO or above use new AudioFocusRequest (which supersedes the older version) to prepare for requests
            result = mAudioManager.requestAudioFocus(mAudioFocusRequest);

        } else if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.N_MR1) {
            // in other android SDK versions use the old audio focus request method.
            result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                    AudioManager.STREAM_MUSIC,
                    AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
        }
        return result;
    }

    /**
     * Tells the system that the app no longer needs audio focus, and focus is abandoned.
     * implementation takes new OREO changes into account.
     * for OREO or above the new AudioFocusRequest object is used
     * for older versions OnAudioFocusChangeListener is used directly.
     */
    private void abandonAudioFocus() {

        // if android build SDK version is OREO or above
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // tell the AudioManager service to abandon audio focus using the previously prepared
            // in onCreate method , mAudioFocusRequest object.
            mAudioManager.abandonAudioFocusRequest(mAudioFocusRequest);

            // if android build SDK is older versions
        } else if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.N_MR1) {
            // tell the AudioManager service to abandon audio focus using the previously prepared
            // in onCreate method , mOnAudioFocusChangeListener object.
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }

    /**
     * Clean up the media player by releasing its resources and abandoning audio focus.
     * This is important to help freeing up resources for other apps and should be called whenever
     * media player resources are not needed.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;
            // since the media player is release audio focus should be abandoned
            abandonAudioFocus();

        }
    }
}
