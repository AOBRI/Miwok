package com.example.android.miwok;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Ahmed on 022 22-03-2018.
 * Handles a list of Word objects into the appropriate layout.
 */

public class WordAdapter extends ArrayAdapter<Word> {

    private static final String LOG_TAG = WordAdapter.class.getSimpleName();
    private int mColorResourceId = R.color.tan_background;

    /**
     * This is our own custom constructor (it doesn't mirror a superclass constructor).
     * The context is used to inflate the layout file, and the list is the data we want
     * to populate into the lists.
     *
     * @param context   The current context. Used to inflate the layout file.
     * @param wordsList A List of AndroidFlavor objects to display in a list
     */
    public WordAdapter(@NonNull Context context, @NonNull List<Word> wordsList, int colorResourceId) {
        //the adapter is not going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, wordsList);
        mColorResourceId = colorResourceId;

    }

    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position    The position in the list of data that should be displayed in the
     *                    list item view.
     * @param convertView The recycled view to populate.
     * @param parent      The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the {@link Word} object located at this position in the list
        final Word currentWord = getItem(position);

        ImageView iconView = listItemView.findViewById(R.id.helping_image_view);
        if (currentWord != null && currentWord.hasImage()) {
            // if the word contains an image resource id, set the image and make it visible.
            // this is true for Numbers, Family and Colors activities.
            iconView.setImageResource(currentWord.getImageResourceId());
            iconView.setVisibility(View.VISIBLE);
        } else {
            // in other cases ( when no the word does not have an image resource, make the iconView gone
            // (not visible and does not take space).
            iconView.setVisibility(View.GONE);
        }

        // set the background color of the Item Layout within each item to the corresponding
        // category color that the word belongs to.
//        listItemView.setBackgroundColor(ContextCompat.getColor(getContext(), mColorResourceId));
        LinearLayout wordLinearLayout = listItemView.findViewById(R.id.word_parent_layout);
        wordLinearLayout.setBackgroundColor(ContextCompat.getColor(getContext(), mColorResourceId));

        TextView numberTextView = listItemView.findViewById(R.id.miwok_text_view);
        numberTextView.setText(currentWord != null ? currentWord.getMiwokTranslation() : "No Miwok word!");

        TextView englishTextView = listItemView.findViewById(R.id.english_text_view);
        englishTextView.setText(currentWord != null ? currentWord.getDefaultTranslation() : "No English word!");

        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;
    }
}
