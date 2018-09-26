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
package com.example.android.miwok2.helper;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.android.miwok2.fragment.ColorsFragment;
import com.example.android.miwok2.fragment.FamilyFragment;
import com.example.android.miwok2.fragment.NumbersFragment;
import com.example.android.miwok2.fragment.PhrasesFragment;

/**
 * Provides the appropriate {@link Fragment} for a view pager.
 */
public class CategoryFragmentAdapter extends FragmentPagerAdapter {

    public CategoryFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new NumbersFragment();
        } else if (position == 1) {
            return new FamilyFragment();
        } else if (position == 2) {
            return new ColorsFragment();
        } else if (position == 3) {
            return new PhrasesFragment();
        } else {
            return new NumbersFragment();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
