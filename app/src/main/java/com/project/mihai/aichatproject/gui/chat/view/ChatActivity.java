package com.project.mihai.aichatproject.gui.chat.view;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.ImageView;

import com.project.mihai.aichatproject.R;
import com.project.mihai.aichatproject.gui.adapters.ChatBotAdapter;
import com.project.mihai.aichatproject.gui.chat.presenter.ChatPresenter;
import com.project.mihai.aichatproject.gui.chat.presenter.ChatPresenterImplementation;
import com.project.mihai.aichatproject.model.ChatMessageModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChatActivity extends AppCompatActivity implements ChatView {

    @BindView(R.id.iv_post_message)
    ImageView ivSendMessage;

    @BindView(R.id.et_wrote_message)
    EditText etGetMessage;

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    private ChatBotAdapter adapter;
    private List<ChatMessageModel> data;
    private ChatPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);
        adaptViews();
        initInstances();
        initAdapters();
    }


    private void adaptViews() {
        ivSendMessage.setColorFilter(ContextCompat.getColor(ChatActivity.this, R.color.blue_gray));
    }

    private void initInstances() {
        data = new ArrayList<>();
        presenter = new ChatPresenterImplementation(this);
    }


    private void initAdapters() {
        adapter = new ChatBotAdapter(this, data);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    @OnClick(R.id.iv_post_message)
    public void postMessageClickAction() {
        presenter.onPostMessage(etGetMessage.getText().toString().trim(), data);
    }

    @Override
    public void onAdapterViewUpdate() {
        recyclerView.smoothScrollToPosition(data.size());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClearChatMessage() {
        etGetMessage.setText("");
    }
}
