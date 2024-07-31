package com.brouken.player;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Timer;
import java.util.TimerTask;

public class GPUUsageDisplay extends Activity {

    private TextView gpuUsageTextView;
    private Handler handler = new Handler();
    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gpu_usage_display);

        gpuUsageTextView = findViewById(R.id.gpuUsageTextView);

        // Start a timer to update GPU usage periodically
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                new RetrieveGPUUsageTask().execute();
            }
        }, 0, 1000); // Update every second
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
        }
    }

    private class RetrieveGPUUsageTask extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... voids) {
            return getGPUUsage();
        }

        @Override
        protected void onPostExecute(String result) {
            gpuUsageTextView.setText(result);
        }
    }

    private String getGPUUsage() {
        StringBuilder gpuUsage = new StringBuilder();
        try {
            Process process = Runtime.getRuntime().exec("dumpsys gfxinfo");
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                gpuUsage.append(line).append("\n");
            }
            reader.close();
            process.waitFor();
        } catch (Exception e) {
            gpuUsage.append("Error: ").append(e.getMessage());
        }
        return gpuUsage.toString();
    }
}
