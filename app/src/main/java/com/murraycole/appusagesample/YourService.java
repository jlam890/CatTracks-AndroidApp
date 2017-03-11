package com.murraycole.appusagesample; /**
 * Created by kuran on 3/10/2017.
 */

import android.app.Activity;
import android.app.IntentService;
import android.app.usage.UsageStats;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

public class YourService extends IntentService {

    private int result = Activity.RESULT_CANCELED;
    public static final String URL = "urlpath";
    public static final String FILENAME = "somerhiht";
    public static final String UPDATECAT = "updateCat";
    public static final String FILEPATH = "filepath";
    public static final String RESULT = "result";
    public static final String NOTIFICATION = "com.vogella.android.service.receiver";

    public YourService() {
        super("YourService");
    }
    public void onCreate(){
        super.onCreate();
        final Handler h = new Handler();
        h.postDelayed(new Runnable()
        {
            private long time = 0;

            @Override
            public void run()
            {
                // do stuff then
                // can call h again after work!


                time += 1000;
                System.out.println("sadf asdf adsf ");
                Log.d("TimerExample", "Going for... " + time);
                h.postDelayed(this, 1000);
                publishResults(time + "", 1, (int) time);
            }
        }, 1000); // 1 second delay (takes millis)

    }
    // will be called asynchronously by Android
    @Override
    protected void onHandleIntent(Intent intent) {





        String urlPath = intent.getStringExtra(URL);
        String fileName = intent.getStringExtra(FILENAME);
        int i = 0;
        while(i < 100) {

            //System.out.println("stillThere");
        }
        File output = new File(Environment.getExternalStorageDirectory(),
                fileName);
        if (output.exists()) {
            output.delete();
        }

        InputStream stream = null;
        FileOutputStream fos = null;
        System.out.println("I got to service");
        try {

            java.net.URL url = new URL(urlPath);
            stream = url.openConnection().getInputStream();
            InputStreamReader reader = new InputStreamReader(stream);
            fos = new FileOutputStream(output.getPath());
            int next = -1;
            while ((next = reader.read()) != -1) {
                fos.write(next);
            }
            // successfully finished
            result = Activity.RESULT_OK;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //publishResults(output.getAbsolutePath(), result);


    }

    private void publishResults(String outputPath, int result, int updateCat) {
        Intent intent = new Intent(NOTIFICATION);
        intent.putExtra(FILEPATH, outputPath);
        intent.putExtra(RESULT, result);
        intent.putExtra(UPDATECAT, updateCat);
        sendBroadcast(intent);
    }
}
