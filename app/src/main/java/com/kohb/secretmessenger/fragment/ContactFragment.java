package com.kohb.secretmessenger.fragment;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kohb.secretmessenger.Constants;
import com.kohb.secretmessenger.R;
import com.kohb.secretmessenger.adapter.ContactAdapter;
import com.kohb.secretmessenger.dto.UserDTO;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ContactFragment extends AbstractTabFragment {

    private static final int LAYOUT = R.layout.fragment_contact;

    private RecyclerView recyclerView;

    private ContactAdapter contactAdapter;
    private List<UserDTO> users;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(LAYOUT, container, false);

        recyclerView = view.findViewById(R.id.recycler_view_contact);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        users = new ArrayList<>();

        new ContactTask().execute();

        return view;
    }


    private class ContactTask extends AsyncTask<Void, Void, UserDTO[]> {

        @Override
        protected UserDTO[] doInBackground(Void... voids) {
            RestTemplate template = new RestTemplate();
            template.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

            return template.getForObject(Constants.URL.GET_ALL_USERS, UserDTO[].class);
        }

        @Override
        protected void onPostExecute(UserDTO[] userDTO) {

            users.addAll(Arrays.asList(userDTO));
            contactAdapter = new ContactAdapter(getContext(), users);
            recyclerView.setAdapter(contactAdapter);
            refreshData();
        }
    }

    public void refreshData(){
        contactAdapter.notifyDataSetChanged();
    }


    public ContactFragment() {
    }

    public static ContactFragment getInstance(Context context){
        Bundle args = new Bundle();
        ContactFragment fragment = new ContactFragment();
        fragment.setArguments(args);
        fragment.setContext(context);
        fragment.setTitle(context.getString(R.string.tab_item_contacts));

        return fragment;
    }

    public void setContext(Context context) {
        this.context = context;
    }

}
