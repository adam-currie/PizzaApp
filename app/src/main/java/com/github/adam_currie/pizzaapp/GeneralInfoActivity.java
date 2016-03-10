package com.github.adam_currie.pizzaapp;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.content.Intent;

public class GeneralInfoActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int CHOOSE_MENU_SAVE_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_general_info);
        findViewById(R.id.nextButton).setOnClickListener(this);
        findViewById(R.id.downloadMenuButton).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == findViewById(R.id.nextButton)) {
            startActivity(new Intent(this, OrderInfoActivity.class));
        }else if(v == findViewById(R.id.downloadMenuButton)){
            Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("application/pdf");
            intent.putExtra(Intent.EXTRA_TITLE, "menu.pdf");

            startActivityForResult(intent, CHOOSE_MENU_SAVE_REQUEST);
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent){
        if(requestCode == CHOOSE_MENU_SAVE_REQUEST){
            if(resultCode == RESULT_OK){
                Log.i("debug", "saving menu: " + intent.getDataString());

                Uri uri = Uri.parse(intent.getDataString());
                new DownloadFileTask(uri, getApplicationContext()).execute(
                        "http://pizzahut.com.ph/document/dine-in.pdf"
                );
            }
        }
    }
}
