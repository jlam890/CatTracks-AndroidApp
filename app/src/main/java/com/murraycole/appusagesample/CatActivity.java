package com.murraycole.appusagesample;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class CatActivity extends Activity {

    private String catName;
    private int daysAlive;
    private int status;

    private TextView catNametv;
    private TextView daysAlivetv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat);

        catName="MEOW";
        daysAlive = 1;

        catNametv = (TextView)findViewById(R.id.cat_name);
        catNametv.setText(catName);

        String days = Integer.toString(daysAlive) +" days alive";
        daysAlivetv = (TextView)findViewById(R.id.cat_days);
        daysAlivetv.setText(days);

    }


    public void cat_goStats(View view){
//        Intent intent = new Intent(this, DisplayAppUsage.class);
//        startActivity(intent);
    }

}
