package com.kohb.secretmessenger.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kohb.secretmessenger.R;

public class DialogFragment extends AbstractTabFragment {

    private static final int LAYOUT = R.layout.fragment_dialog;

    public DialogFragment() {
    }

    public static DialogFragment getInstance(Context context){
        Bundle args = new Bundle();
        DialogFragment fragment = new DialogFragment();
        fragment.setArguments(args);
        fragment.setContext(context);
        fragment.setTitle(context.getString(R.string.tab_item_dialogs));

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT, container, false);

        return view;
    }

    public void setContext(Context context) {
        this.context = context;
    }

}