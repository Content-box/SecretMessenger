package com.kohb.secretmessenger;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.kohb.secretmessenger.adapter.MessageAdapter;
import com.kohb.secretmessenger.adapter.TabsFragmentAdapter;
import com.kohb.secretmessenger.dto.MessageDTO;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;


public class MainActivity extends AppCompatActivity {

    private static final int LAYOUT = R.layout.activity_main;

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ProgressBar progressBar;
    private ViewPager viewPager;



    private RecyclerView messagesRecyclerView;
    private MessageAdapter adapter;
    private ArrayList<MessageDTO> messagesList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppDefault);
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);

        initToolbar();
        initNavigationView();
        initTabs();
    }

    private void initTabs() {
//        progressBar = findViewById(R.id.progressbar);
//
//        messagesRecyclerView = findViewById(R.id.messages_recycler);
//        messagesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        adapter = new MessageAdapter(this, messagesList);
//        messagesRecyclerView.setAdapter(adapter);
//
//        new MessageTask().execute();
        viewPager = findViewById(R.id.view_pager);
        TabsFragmentAdapter adapter = new TabsFragmentAdapter(getApplicationContext(), getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void initToolbar() {
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                return false;
            }
        });

        toolbar.inflateMenu(R.menu.menu);
    }

    private void initNavigationView() {

        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.view_navigation_open,R.string.view_navigation_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.navigation);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                drawerLayout.closeDrawers();
                return true;
            }
        });

    }

    private class MessageTask extends AsyncTask<Void, Void, MessageDTO[]>{

        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected MessageDTO[] doInBackground(Void... voids) {
            RestTemplate template = new RestTemplate();
            template.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

            return template.getForObject(Constants.URL.GET_ALL_MESSAGES, MessageDTO[].class);
        }

        @Override
        protected void onPostExecute(MessageDTO[] messageDTO) {
            progressBar.setVisibility(View.GONE);

            messagesList.addAll(Arrays.asList(messageDTO));
            adapter.setMessages(messagesList);
            refreshData();
        }
    }


    
    public void refreshData(){
        adapter.notifyDataSetChanged();
    }
}
