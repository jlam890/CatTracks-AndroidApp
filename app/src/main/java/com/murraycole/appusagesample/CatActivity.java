package com.murraycole.appusagesample;

import android.app.Activity;
import android.app.usage.UsageStats;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
//import android.support.v7.app.ActionBarActivity;
import android.content.IntentFilter;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Map;

public class CatActivity extends Activity {

    private String catName;
    private int daysAlive;
    private int status;

    private TextView catNametv;
    private TextView daysAlivetv;
    private ImageView imv;
    private int[] imageArray;

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
                    Bundle bundle = intent.getExtras();
                    if (bundle != null) {


                        if (UStats.getUsageStatsList(CatActivity.this).isEmpty()){
                            Intent uintent = new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS);
                            startActivity(uintent);
                        }


                        String string = bundle.getString(YourService.FILEPATH);
                        int updateCat = bundle.getInt(YourService.UPDATECAT);
                        int asd = updateCat / 5000;



                        //imv.setImageDrawable(imageArray[asd]);
                        imv.setImageResource(imageArray[asd]);
                        Toast.makeText(CatActivity.this,
                                string,
                        Toast.LENGTH_LONG).show();

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat);

//        Intent intent = getIntent();
//        catName = intent.getExtras().getParcelable("name");
//        catName = intent.getExtras("name");
        Bundle extras = getIntent().getExtras();
        catName = extras.getString("name");
//        catName="MEOW";
        daysAlive = 0;

        catNametv = (TextView)findViewById(R.id.cat_name);
        catNametv.setText(catName);

        String days = Integer.toString(daysAlive) +" days alive";
        daysAlivetv = (TextView)findViewById(R.id.cat_days);
        daysAlivetv.setText(days);

        //In oncreate
        imageArray = new int[6];
        imageArray[0] =R.drawable.happiercat;
        imageArray[1] =R.drawable.happycat;
        imageArray[2] =R.drawable.damagedcat;
        imageArray[3] =R.drawable.abusedcat;
        imageArray[4] = R.drawable.deadcat;
        imageArray[5] =R.drawable.ghostcat;

        imv = (ImageView) findViewById(R.id.imageView);

//Starts Service

        Intent intent = new Intent(this, YourService.class);
// add infos for the service which file to download and where to store
        intent.putExtra(YourService.FILENAME, "index.html");
        intent.putExtra(YourService.URL,
                "http://www.vogella.com/index.html");
        Toast.makeText(CatActivity.this,
                "Service Started",
                Toast.LENGTH_LONG).show();
        startService(intent);

    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(receiver, new IntentFilter(
                YourService.NOTIFICATION));
    }
    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }
    public void cat_goStats(View view){
        Intent intent = new Intent(this, DisplayAppUsage.class);
        intent.putExtra("name", catName);
        startActivity(intent);
    }

}
