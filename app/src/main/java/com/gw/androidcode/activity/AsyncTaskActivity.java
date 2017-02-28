package com.gw.androidcode.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * Created by GongWen on 17/2/28.
 */

public class AsyncTaskActivity extends AppCompatActivity {
    private final String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        testThreadPool();
        testFutureTask();
    }

    private void testAsyncTask() {
        AsyncTask<Integer, Long, String> mAsyncTask = new AsyncTask<Integer, Long, String>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected String doInBackground(Integer... params) {
                //publishProgress();
                return null;
            }

            @Override
            protected void onProgressUpdate(Long... values) {
                super.onProgressUpdate(values);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
            }
        };
        mAsyncTask.execute();
    }

    private void testThreadPool() {
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.i(TAG, "Thread-" + Thread.currentThread().getName());
            }
        };

        /*ExecutorService fixedService = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 10; i++) {
            fixedService.execute(runnable);
        }

        ExecutorService cachedService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            cachedService.execute(runnable);
        }

        ScheduledExecutorService scheduledService = Executors.newScheduledThreadPool(3);
        for (int i = 0; i < 10; i++) {
            //scheduledService.schedule(runnable, 0, TimeUnit.MILLISECONDS);
            scheduledService.scheduleAtFixedRate(runnable,0,5000, TimeUnit.MILLISECONDS);
        }*/

        ExecutorService singleService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            singleService.execute(runnable);
        }
    }

    private void testFutureTask() {
        Callable<String> worker = new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "this is my WorkerRunnable";
            }
        };
        FutureTask<String> mFutureTask = new FutureTask<String>(worker) {
            @Override
            protected void done() {
                try {
                    String result = get();
                    Log.i(TAG, "The result is " + result);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        };
        ExecutorService fixedService = Executors.newFixedThreadPool(3);
        fixedService.execute(mFutureTask);
    }
}
