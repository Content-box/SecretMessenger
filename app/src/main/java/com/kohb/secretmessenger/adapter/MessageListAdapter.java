package com.kohb.secretmessenger.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kohb.secretmessenger.R;
import com.kohb.secretmessenger.dto.MessageDTO;

import java.util.List;

public class MessageListAdapter extends RecyclerView.Adapter<ViewHolder> {

    private List<MessageDTO> messages;
    private LayoutInflater inflater;


    public MessageListAdapter(Context context, List<MessageDTO> messages) {
        this.messages = messages;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.item_message, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        MessageDTO message = messages.get(i);

        viewHolder.textViewMessage.setText(message.getMessage());
        viewHolder.textViewAutor.setText(Integer.toString(message.getSenderId()));
        viewHolder.textViewTime.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)", message.getDate()));

    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public void setMessages(List<MessageDTO> messages) {
        this.messages = messages;

    }
}
