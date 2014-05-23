package com.foobar.simplerss;

import android.app.Activity;
import android.app.Fragment;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;


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
        mUrl = params[0];
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

