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



import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

import java.util.Map;


public class DisplayAppUsage extends Activity{

    List<UsageStats> prayerList;
    Button backToCat;
    GraphView barGraph;
    UsageStats data;
    long time0,time1,time2,time3;
    int i;
    String catName;
    UStats prayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_app_usage);

        Bundle extras = getIntent().getExtras();
        catName = extras.getString("name");

        backToCat = (Button) findViewById(R.id.back_to_cat_btn);
        barGraph = (GraphView) findViewById(R.id.bar_graph);
        if (prayer.getUsageStatsList(this).isEmpty()) {
            Intent intent = new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS);
            startActivity(intent);
        }

        prayerList = prayer.getUsageStatsList(this);
        prayer.printUsageStats(prayerList);

        for(i=0 ; i<prayerList.size() ; i++)
        {
            if(prayerList.get(i).getPackageName().equals("com.android.chrome"))
                time0 = prayerList.get(i).getTotalTimeInForeground();
            if(prayerList.get(i).getPackageName().equals("com.android.settings"))
                time1 = prayerList.get(i).getTotalTimeInForeground();



        }
        /*
        for(i=0 ; i<prayerList.size() || prayerList.get(i).getPackageName() == "com.instagram.android" ; i++)
        {}
        time1 = prayerList.get(i).getTotalTimeInForeground();
        for(i=0 ; i<prayerList.size() || prayerList.get(i).getPackageName() == "com.facebook.android" ; i++)
        {}
        time2 = prayerList.get(i).getTotalTimeInForeground();
        for(i=0 ; i<prayerList.size() || prayerList.get(i).getPackageName() == "com.twitter.android" ; i++)
        {}
        time3 = prayerList.get(i).getTotalTimeInForeground();
        */
        BarGraphSeries<DataPoint> series = new BarGraphSeries<>(new DataPoint[]{
                new DataPoint(1, time0),
                new DataPoint(2, 0),
                new DataPoint(3, time1),
                new DataPoint(4, 0),
                new DataPoint(5, 3),
                new DataPoint(6, 0),
                new DataPoint(7, 4)
        });
        barGraph.addSeries(series);
        barGraph.getViewport().setYAxisBoundsManual(true);
        barGraph.getViewport().setMinY(0);
        barGraph.getViewport().setMaxY(8000);
        barGraph.getViewport().setXAxisBoundsManual(true);
        barGraph.getViewport().setMinX(0);
        barGraph.getViewport().setMaxX(8);
//        backToCat.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(v.getContext(), CatActivity.class);
//                startActivity(intent);
//            }
//
//        });
    }

        public void exit_window(View view) {

            Intent intent = new Intent(this, CatActivity.class);
            intent.putExtra("name", catName);

            startActivity(intent);


    }



}

