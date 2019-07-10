package com.sogou.teemo.watch.demo;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("a","1");
                startActivityForResult(intent, 111);
                Log.e("MainActivity","after startActivity");
//                startActivity(new Intent(android.provider.Settings.ACTION_SETTINGS));
//                Intent aliIntent = new Intent();
//                ComponentName componentName = new ComponentName("com.eg.android.AlipayGphone", "com.alipay.watch.ui.MainActivity");
//                aliIntent.setComponent(componentName);
//                aliIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(aliIntent);
//                ComponentName componentName = new ComponentName("com.eg.android.AlipayGphone", "com.alipay.watch.ui.MainActivity");
//                final Intent intent = new Intent();
//                intent.setComponent(componentName);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("MainActivity", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("MainActivity", "onPause");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("MainActivity", "onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("MainActivity", "onStop");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("MainActivity","onActivityResult requestCode="+requestCode+",resultCode="+resultCode);
        if (requestCode == 111 && resultCode == RESULT_OK) {

        }
    }
}
