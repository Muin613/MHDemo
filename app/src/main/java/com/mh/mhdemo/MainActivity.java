package com.mh.mhdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt = (TextView) findViewById(R.id.test);
        final MHProxy<MainActivity> mhProxy = new MHProxy<>(this);
        mhProxy.newProxyInstance(new Class[]{MHProxyTag.class});
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                method();
            }
        });

    }

    @MHProxyTag
    void method() {
        StringBuilder builder=new StringBuilder("1");
        builder.append("nimabo")
                .append("1111111113")
                .append("++++++++++")
                .append("shenmegui  1");
        txt.setText(builder);
    }
}
