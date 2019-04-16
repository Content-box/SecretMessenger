package com.kohb.secretmessenger.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.kohb.secretmessenger.fragment.AbstractTabFragment;
import com.kohb.secretmessenger.fragment.ContactFragment;
import com.kohb.secretmessenger.fragment.DialogFragment;

import java.util.HashMap;
import java.util.Map;

public class TabsFragmentAdapter extends FragmentPagerAdapter {

    private Map<Integer, AbstractTabFragment> tabs;
    private Context context;

    public TabsFragmentAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
        initTabsMap(context);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabs.get(position).getTitle();
    }

    @Override
    public Fragment getItem(int i) {
        return tabs.get(i);
    }

    @Override
    public int getCount() {
        return tabs.size();
    }

    private void initTabsMap(Context context) {
        tabs = new HashMap<>();
        tabs.put(0, DialogFragment.getInstance(context));
        tabs.put(1, ContactFragment.getInstance(context));
    }
}
