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



import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

import java.util.Map;


public class DisplayAppUsage extends Activity{

    Button backToCat;
    GraphView barGraph;
    Map dataStorage;
    UsageStats temp;
    UsageStatsManager data;
    Calendar calendar;
    int time0,time1,time2,time3;
    long start;
    long end;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_app_usage);
        backToCat = (Button) findViewById(R.id.back_to_cat_btn);
        barGraph = (GraphView) findViewById(R.id.bar_graph);

        data=UStats.getUsageStatsManager(this);
        calendar = Calendar.getInstance();
        end = calendar.getTimeInMillis();
        calendar.add(Calendar.YEAR, -1);
        start = calendar.getTimeInMillis();
        dataStorage=data.queryAndAggregateUsageStats(start, end);

        temp = (UsageStats) dataStorage.get("com.android.instagram");
        time0 = (int) temp.getTotalTimeInForeground();
        temp = (UsageStats) dataStorage.get("com.android.snapchat");
        time1 = (int) temp.getTotalTimeInForeground();
        temp = (UsageStats) dataStorage.get("com.android.facebook");
        time2 = (int) temp.getTotalTimeInForeground();
        temp = (UsageStats) dataStorage.get("com.android.twitter");
        time3 = (int) temp.getTotalTimeInForeground();
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                    new DataPoint(0 , time0),
                    new DataPoint(1 , time1),
                    new DataPoint(2 , time2),
                    new DataPoint(3 , time3)
        });
        barGraph.addSeries(series);

    backToCat.setOnClickListener(new View.OnClickListener() {
        @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), CatActivity.class);
                    startActivity(intent);
        }

    });


    }



}

