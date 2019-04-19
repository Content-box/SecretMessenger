package com.kohb.secretmessenger.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kohb.secretmessenger.R;
import com.kohb.secretmessenger.dto.MessageDTO;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    private static final int MSG_TYPE_LEFT = 0;
    private static final int MSG_TYPE_RIGHT = 1;

    private List<MessageDTO> messages;
    private LayoutInflater inflater;
    //private String imageURL;
    //Допилить

    public MessageAdapter(Context context, List<MessageDTO> messages) {
        this.messages = messages;
        this.inflater = LayoutInflater.from(context);
    }



    @NonNull
    @Override
    public MessageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == MSG_TYPE_RIGHT) {
            View view = inflater.inflate(R.layout.chat_item_right, viewGroup, false);
            return new ViewHolder(view);
        } else {
            View view = inflater.inflate(R.layout.chat_item_left, viewGroup, false);
            return new ViewHolder(view);
        }
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        MessageDTO message = messages.get(i);

//        viewHolder.textViewMessage.setText(message.getMessage());
//        viewHolder.textViewAuhtor.setText(Integer.toString(message.getSenderId()));
//        viewHolder.textViewTime.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)", message.getDate()));
        viewHolder.textViewMessage.setText(message.getMessage());
        //viewHolder.textViewTime.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)", message.getDate()));


    }

    @Override
    public int getItemCount() {
        return messages.size();
    }


    @Override
    public int getItemViewType(int position) {
        //получить id текущего пользователя
        int CURRENT_SENDER_ID = 0;
        if (messages.get(position).getSenderId() == CURRENT_SENDER_ID) {
            return MSG_TYPE_RIGHT;
        } else {
            return MSG_TYPE_LEFT;
        }
    }

    public void setMessages(List<MessageDTO> messages) {
        this.messages = messages;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

//        TextView textViewMessage;
//        TextView textViewAuhtor;
//        TextView textViewTime;

        TextView textViewMessage;
        ImageView profileImage;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
//            textViewMessage = itemView.findViewById(R.id.message_text);
//            textViewAuhtor = itemView.findViewById(R.id.author_text);
//            textViewTime = itemView.findViewById(R.id.time_text);

            textViewMessage = itemView.findViewById(R.id.show_message);
            profileImage = itemView.findViewById(R.id.profile_image);


        }
    }
}
