package com.murraycole.appusagesample;

/**
 * Created by jlammwe on 3/11/17.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.content.Context;
import android.app.usage.UsageStatsManager;
import android.app.usage.UsageStats;
import java.util.Calendar;
import java.util.List;
import android.graphics.Color;
import android.widget.TextView;




import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.ValueDependentColor;


import java.util.Map;


public class DisplayAppUsage extends Activity{

    List<UsageStats> prayerList;
    Button backToCat;
    GraphView barGraph;
    long time0,time1,time2,time3,maxValue=0;
    int i;
    String catName;
    UStats prayer;
    TextView instagram, facebook, twitter, snapchat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_app_usage);

        Bundle extras = getIntent().getExtras();
        catName = extras.getString("name");

        instagram = (TextView) findViewById(R.id.legend_instagram);
        facebook = (TextView) findViewById(R.id.legend_facebook);
        twitter = (TextView) findViewById(R.id.legend_twitter);
        snapchat = (TextView) findViewById(R.id.legend_snapchat);
        instagram.setText("Bar 1 = Instagram");
        facebook.setText("Bar 2 = Facebook");
        twitter.setText("Bar 3 = Twitter");
        snapchat.setText("Bar 4 = Snapchat");

        backToCat = (Button) findViewById(R.id.back_to_cat_btn);
        barGraph = (GraphView) findViewById(R.id.bar_graph);
        if (prayer.getUsageStatsList(this).isEmpty()) {
            Intent intent = new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS);
            startActivity(intent);
        }

        prayerList = prayer.getUsageStatsList(this);
        prayer.printUsageStats(prayerList);

        for(i=0 ; i<prayerList.size() ; i++) {
//            if (prayerList.get(i).getPackageName().equals("com.android.chrome")) {
//                time0 = prayerList.get(i).getTotalTimeInForeground();
//                if (time0 > maxValue) maxValue = time0;
//            }
//            if (prayerList.get(i).getPackageName().equals("com.android.settings")) {
//                time1 = prayerList.get(i).getTotalTimeInForeground();
//                if (time1 > maxValue) maxValue = time1;

            if(prayerList.get(i).getPackageName().equals("com.instagram.android"))
            {
              time0 = prayerList.get(i).getTotalTimeInForeground();
              if (time0 > maxValue) maxValue = time0;
            }
            if(prayerList.get(i).getPackageName().equals("com.facebook.android"))
            {
              time1 = prayerList.get(i).getTotalTimeInForeground();
              if (time1 > maxValue) maxValue = time1;
            }
            if(prayerList.get(i).getPackageName().equals("com.twitter.android"))
            {
                time2 = prayerList.get(i).getTotalTimeInForeground();
                if(time2>maxValue) maxValue=time2;
            }
            if(prayerList.get(i).getPackageName().equals("com.snapchat.android"))
            {
                time3 = prayerList.get(i).getTotalTimeInForeground();
                if(time3>maxValue) maxValue=time2;
            }



        }

        BarGraphSeries<DataPoint> series = new BarGraphSeries<>(new DataPoint[]{
                new DataPoint(1, time0/1000),
                new DataPoint(2, time1/1000),
                new DataPoint(3, time2/1000),
                new DataPoint(4, time3/1000)
        });


        barGraph.addSeries(series);
        barGraph.getViewport().setYAxisBoundsManual(true);
        barGraph.getViewport().setMinY(0);
        barGraph.getViewport().setMaxY(20);
        barGraph.getViewport().setXAxisBoundsManual(true);
        barGraph.getViewport().setMinX(0);
        barGraph.getViewport().setMaxX(5);
        barGraph.getViewport().setBackgroundColor(Color.BLACK);
        barGraph.setTitle("Time Usage in Seconds");

        // styling
        series.setValueDependentColor(new ValueDependentColor<DataPoint>() {
            @Override
            public int get(DataPoint data) {
                return Color.rgb((int) data.getX()*255/4, (int) Math.abs(data.getY()*255/6), 100);
            }
        });

        series.setSpacing(50);

// draw values on top
        series.setDrawValuesOnTop(true);
        series.setValuesOnTopColor(Color.RED);
        series.setValuesOnTopSize(50);

    }

        public void exit_window(View view) {

            Intent intent = new Intent(this, CatActivity.class);
            intent.putExtra("name", catName);

            startActivity(intent);


    }



}

