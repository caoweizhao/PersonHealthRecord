package com.example.administrator.personhealthrecord.mvp.chat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.personhealthrecord.R;
import com.example.administrator.personhealthrecord.adapter.ChatListVIewAdapter;
import com.example.administrator.personhealthrecord.adapter.ChatRecycleviewAdapter;
import com.example.administrator.personhealthrecord.base.BaseActivity;
import com.example.administrator.personhealthrecord.bean.ChatMessageBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChatActivity extends IChatVIew {

    private List<ChatMessageBean> list;
    @BindView(R.id.chat_list_Listview)
    ListView listView;
    @BindView(R.id.chat_send)
    ImageView buttonSend;
    @BindView(R.id.chat_input_text)
    EditText editText;
    private  ChatListVIewAdapter adapter;
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_chat;
    }

    public void Test()
    {
        ChatMessageBean bean1=new ChatMessageBean("hello!",ChatMessageBean.TYPE_RECEIVED);
        ChatMessageBean bean2=new ChatMessageBean("hi!",ChatMessageBean.TYPE_SEND);
        ChatMessageBean bean3=new ChatMessageBean("How are you!",ChatMessageBean.TYPE_RECEIVED);
        ChatMessageBean bean4=new ChatMessageBean("I`m find!",ChatMessageBean.TYPE_SEND);
        ChatMessageBean bean5=new ChatMessageBean("吃屎啦你 就你他妈话最多!",ChatMessageBean.TYPE_RECEIVED);
        ChatMessageBean bean6=new ChatMessageBean("you too!",ChatMessageBean.TYPE_SEND);

        list.add(bean1);
        list.add(bean2);
        list.add(bean3);
        list.add(bean4);
        list.add(bean5);
        list.add(bean6);
    }

    @Override
    protected void initData() {
        super.initData();
        list=new ArrayList<>();
//        Test();
        adapter=new ChatListVIewAdapter(this,R.layout.chat_item,list);
        listView.setAdapter(adapter);
        initToolbar("聊天",true,null);
        getHelp();
    }

    @Override
    protected void initEvents() {
        super.initEvents();
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChatMessageBean bean=new ChatMessageBean(editText.getText().toString(),ChatMessageBean.TYPE_SEND);
                list.add(bean);
                getRespone(editText.getText().toString());
                editText.setText("");
                adapter.notifyDataSetChanged();
                listView.smoothScrollToPosition(list.size());
            }
        });

       listView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
           @Override
           public void onGlobalLayout() {
               listView.smoothScrollToPosition(list.size());

           }
       });
    }

    @Override
    public IChatPresenter createPresenter() {
        return new ChatPresentImpl();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void getHelp() {
        mPresenter.getHelpe();
    }

    @Override
    public void getRespone(String question) {
        mPresenter.getResone(question);
    }

    @Override
    public void OnHelpMessageReady(String message) {
        ChatMessageBean bean=new ChatMessageBean(message,ChatMessageBean.TYPE_RECEIVED);
        list.add(bean);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void OnSimpleMessageReady(String message) {
        ChatMessageBean bean=new ChatMessageBean(message,ChatMessageBean.TYPE_RECEIVED);
        list.add(bean);
        adapter.notifyDataSetChanged();
    }
}
