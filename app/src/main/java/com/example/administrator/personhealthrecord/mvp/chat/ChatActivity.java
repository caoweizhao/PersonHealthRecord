package com.example.administrator.personhealthrecord.mvp.chat;

import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.administrator.personhealthrecord.R;
import com.example.administrator.personhealthrecord.adapter.ChatListVIewAdapter;
import com.example.administrator.personhealthrecord.bean.ChatMessageBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

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
                if(editText.getText().toString().matches("\\s*"))
                    return;
                ChatMessageBean bean=new ChatMessageBean(editText.getText().toString(),ChatMessageBean.TYPE_SEND);
                list.add(bean);
                getResponse(editText.getText().toString());
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
        mPresenter.getHelp();
    }

    @Override
    public void getResponse(String question) {
        mPresenter.getResponse(question);
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
