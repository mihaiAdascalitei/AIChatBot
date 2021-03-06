package com.project.mihai.aichatproject.gui.adapters;

import android.content.Context;
import android.provider.MediaStore;
import android.speech.tts.TextToSpeech;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
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

    private Context context;
    private List<ChatMessageModel> data;
    private TextToSpeech textToSpeech;


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

    public void onDestroyTextToSpeech() {
        if (textToSpeech != null) {
            textToSpeech.shutdown();
            textToSpeech.stop();
        }
    }


    public class ChatBotHolder extends RecyclerView.ViewHolder implements ChatAdapterView, TextToSpeech.OnInitListener {
        @BindView(R.id.message)
        TextView message;

        private ChatAdapterClickPresenter presenter;

        private ChatBotHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            initInstances();

        }


        private void initInstances() {
            textToSpeech = new TextToSpeech(context, this);
            presenter = new ChatAdapterClickImplementation(this);

        }

        @OnClick(R.id.message)
        public void messageClickAction() {
            presenter.onMicrophoneClick(message.getText().toString().trim(), data.get(getAdapterPosition()).getType());

        }

        @Override
        public void speak(String message) {
            textToSpeech.speak(message, TextToSpeech.QUEUE_FLUSH, null);

        }

        @Override
        public void muteMicrophone() {
            if (textToSpeech.isSpeaking()) {
                textToSpeech.stop();
            }
        }

        @Override
        public boolean isSpeaking() {
            return textToSpeech.isSpeaking();

        }

        @Override
        public void onInit(int i) {
            //the closest to romanian
            textToSpeech.setLanguage(new Locale("it"));

        }
    }

}
