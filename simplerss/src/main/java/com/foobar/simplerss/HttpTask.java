package com.foobar.simplerss;

import android.app.Activity;
import android.app.Fragment;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Created by tom on 5/22/14.
 */
public class HttpTask extends AsyncTask<String, Void, String>
{

    public static final int MODE_VALIDATE = 1;
    public static final int MODE_GET = 0;

    private ProgressBar progressBar;

    private String mUrl;

    private NavigationDrawerFragment mFragment;
    private int mMode = MODE_GET;

    HttpTask(NavigationDrawerFragment fragment, int mode)
    {
        mMode = mode;
        mFragment = fragment;
    }

    @Override
    protected void onPreExecute()
    {
        progressBar = (ProgressBar) mFragment.getActivity().findViewById(R.id.progressBar);

        progressBar.setVisibility(View.VISIBLE);

    }

    @Override
    protected String doInBackground(String... params)
    {
        URL url = null;
        BufferedReader reader;
        StringBuilder stringBuilder;

        mUrl = params[0];

        try
        {
            url = new URL(mUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setReadTimeout(5 * 1000);
            connection.connect();

            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            stringBuilder = new StringBuilder();

            String line = null;
            while ((line = reader.readLine()) != null)
            {
                stringBuilder.append(line + "\n");
            }

            return stringBuilder.toString();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return "";
        }

    }

    @Override
    protected void onProgressUpdate(Void... unused)
    {

    }

    @Override
    protected void onPostExecute(String result)
    {
        progressBar = (ProgressBar) mFragment.getActivity().findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);
        if (mMode == MODE_VALIDATE)
        {
            mFragment.onValidateComplete(mUrl, result);
        }
        else
        {
            mFragment.onFetchComplete(mUrl, result);
        }
        Toast.makeText(mFragment.getActivity(), "AsyncTask finished, returned: " + result, Toast.LENGTH_SHORT).show();
    }
}

