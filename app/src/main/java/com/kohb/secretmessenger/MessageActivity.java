package com.kohb.secretmessenger;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.kohb.secretmessenger.adapter.MessageAdapter;
import com.kohb.secretmessenger.dto.MessageDTO;

import org.springframework.web.client.RestTemplate;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MessageActivity extends AppCompatActivity {

    CircleImageView profile_image;
    TextView username;

    ImageButton btn_send;
    EditText text_send;

    Intent intent;

    MessageAdapter messageAdapter;
    List<MessageDTO> messages;

    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        profile_image = findViewById(R.id.profile_image);
        username = findViewById(R.id.username);
        btn_send = findViewById(R.id.btn_send_message);
        text_send = findViewById(R.id.text_send_message);

        intent = getIntent();
        String userId = intent.getStringExtra("userName");

        username.setText(userId);
        profile_image.setImageResource(R.mipmap.ic_launcher);

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });

    }

    private void sendMessage() {

        String messageText = text_send.getText().toString();

        MessageDTO message = new MessageDTO();
        //message.setId(228);
        message.setChatId(228);
        message.setSenderId(228);
        message.setMessage(messageText);
        //message.setDate(228);

        if (!messageText.equals("")){
            new SendMessageAsync().execute(message);
        } else {
            Toast.makeText(getApplicationContext(),"Enter your message",Toast.LENGTH_SHORT).show();
        }

        text_send.setText("");
    }


    private class SendMessageAsync extends AsyncTask<MessageDTO, Void, Void> {

        @Override
        protected Void doInBackground(MessageDTO... messageDTOS) {
            RestTemplate template = new RestTemplate();
            MessageDTO messageBack = template.postForObject(Constants.URL.SAVE_MESSAGE, messageDTOS[0], MessageDTO.class);
            return null;
        }

    }
}
