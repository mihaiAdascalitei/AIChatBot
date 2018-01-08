package com.project.mihai.aichatproject.gui.adapters;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.project.mihai.aichatproject.R;
import com.project.mihai.aichatproject.gui.chat.presenter.ChatAdapterClickImplementation;
import com.project.mihai.aichatproject.gui.chat.presenter.ChatAdapterClickPresenter;
import com.project.mihai.aichatproject.gui.chat.view.ChatAdapterView;
import com.project.mihai.aichatproject.model.ChatMessageModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by adasc on 12/13/2017.
 */

public class ChatBotAdapter extends RecyclerView.Adapter<ChatBotAdapter.ChatBotHolder> {

    Context context;
    List<ChatMessageModel> data;

    public ChatBotAdapter(Context context, List<ChatMessageModel> data) {
        this.data = data;
        this.context = context;
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

    public class ChatBotHolder extends RecyclerView.ViewHolder implements ChatAdapterView {
        @BindView(R.id.message)
        TextView message;

        @BindView(R.id.iv_microphone)
        ImageView ivMicrophone;

        private TextToSpeech textToSpeech;
        private ChatAdapterClickPresenter presenter;

        private ChatBotHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            adaptViews();
            initInstances();
        }

        private void adaptViews() {
            ivMicrophone.setColorFilter(ContextCompat.getColor(context, R.color.white));
        }

        private void initInstances() {
            textToSpeech = new TextToSpeech(context, null);
            textToSpeech.setLanguage(Locale.US);
            presenter = new ChatAdapterClickImplementation(this);

        }

        @OnClick(R.id.iv_microphone)
        public void microphoneClickAction() {
            presenter.onMicrophoneClick(message.getText().toString().trim(), data.get(getAdapterPosition()).getType());
        }


        @Override
        public void speak(String message) {
            textToSpeech.speak(message, TextToSpeech.QUEUE_FLUSH, null);

        }
    }
}
