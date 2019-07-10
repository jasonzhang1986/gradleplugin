package com.sogou.teemo.watch.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class SecondActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("SecondActivity","onCreate");
        setContentView(R.layout.activity_sec);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("pkgName", "abc");
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
