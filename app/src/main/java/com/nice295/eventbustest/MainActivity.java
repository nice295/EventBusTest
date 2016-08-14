package com.nice295.eventbustest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mButton;
    private TextView mTvText;

    // khlee
    // This evnet called when other activities or fragments call a post with data
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onStringEvent(String event) {
        Toast.makeText(this, "onStringEvent: "  + event, Toast.LENGTH_SHORT).show();
        mTvText.setText(event);
    }

    // khlee
    // This evnet called when other activities or fragments call a post with data
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        Toast.makeText(this, "onMessageEvent: "  + event.message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this); //khlee: needed to register if this activity has a event function.

        mButton = (Button)findViewById(R.id.button);
        mButton.setOnClickListener(this);
        mTvText = (TextView)findViewById(R.id.textView);
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this); //khlee: after this destroyed, event is freeed.
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(this, DetailActivity.class));
    }
}

