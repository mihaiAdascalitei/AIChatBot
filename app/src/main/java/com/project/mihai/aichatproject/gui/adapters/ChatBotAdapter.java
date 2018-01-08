package com.project.mihai.aichatproject.gui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.project.mihai.aichatproject.R;
import com.project.mihai.aichatproject.model.ChatMessageModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by adasc on 12/13/2017.
 */

public class ChatBotAdapter extends RecyclerView.Adapter<ChatBotAdapter.ChatBotHolder> {

    List<ChatMessageModel> data;

    public ChatBotAdapter(List<ChatMessageModel> data) {
        this.data = data;
    }

    @Override
    public int getItemViewType(int position) {
        return this.data.get(position).getType();
    }

    @Override
    public ChatBotHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case 1:
                View userView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.user_chat, parent, false);
                return new ChatBotHolder(userView);
            case 2:
                View botView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.bot_chat, parent, false);
                return new ChatBotHolder(botView);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(ChatBotHolder holder, int position) {
        holder.message.setText(data.get(position).getMessage());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ChatBotHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.message)
        TextView message;

        public ChatBotHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}
