package com.foobar.simplerss;

import android.app.Activity;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;


/**
 * Created by tom on 5/22/14.
 */
public class HttpTask extends AsyncTask<String, Void, String>
{
    private ProgressBar progressBar;

    private Activity mActivity;

    HttpTask(Activity activity)
    {
        mActivity = activity;
    }

    @Override
    protected void onPreExecute()
    {
        progressBar = (ProgressBar) mActivity.findViewById(R.id.progressBar);

        progressBar.setVisibility(View.VISIBLE);

    }

    @Override
    protected String doInBackground(String... params)
    {
        try
        {
            Thread.sleep(2000);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        return "foobar";
    }

    @Override
    protected void onProgressUpdate(Void... unused)
    {

    }

    @Override
    protected void onPostExecute(String result)
    {
        Toast.makeText(mActivity, "AsyncTask finished, returned: " + result, Toast.LENGTH_SHORT).show();
    }
}

