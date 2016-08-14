package com.nice295.eventbustest;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mButton;
    private EditText mEtText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail);

        mButton = (Button)findViewById(R.id.button);
        mButton.setOnClickListener(this);
        mEtText = (EditText)findViewById(R.id.editText);

        //khlee: Test to send a message with custom class
        EventBus.getDefault().post( new MessageEvent("Entered in DetailActivity()") );
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        EventBus.getDefault().post( new String( mEtText.getText().toString()) ); //khee: send post with normal string
        Toast.makeText(getApplication(), "Sending message..." + mEtText.getText().toString(), Toast.LENGTH_SHORT).show();
        finish();
    }
}
