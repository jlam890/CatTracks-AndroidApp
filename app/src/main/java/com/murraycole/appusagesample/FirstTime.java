package com.murraycole.appusagesample;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FirstTime extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_time);
        //savebtnlistener();
    }
    public void savebtnlistener(View view) {
        /*final Button save_btn = (Button) findViewById(R.id.btn_save);
        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {*/
                EditText catname = (EditText) findViewById(R.id.name_text);
                String name = catname.getText().toString();
                Intent intent = new Intent(this, CatActivity.class);
                intent.putExtra("name", name);
                startActivity(intent);
           /* }
        });*/
    }
}
