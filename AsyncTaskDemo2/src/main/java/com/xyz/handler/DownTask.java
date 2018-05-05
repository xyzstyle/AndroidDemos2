package com.xyz.handler;

import android.app.ProgressDialog;
import android.os.AsyncTask;


public class DownTask extends AsyncTask<Integer, Integer, String> {
	private ProgressDialog progressDialog;

	public DownTask(ProgressDialog progressDialog) {

		this.progressDialog=progressDialog;
	}

	@Override
	protected String doInBackground(Integer... param) {
		int total = param[1];
		int current = 0;

		while (current <= total) {
			try {
				Thread.sleep(param[0]); // 可以在此处完成具体的任务
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			publishProgress(current, total);
			current += 5;
		}
		return "下载完毕";
	}

	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
		progressDialog.dismiss();
	}

	@Override
	protected void onPreExecute() {
		progressDialog.show();
		super.onPreExecute();
	}
	@Override
	protected void onProgressUpdate(Integer... values) {
		super.onProgressUpdate(values);
		progressDialog.setProgress(values[0]*100/values[1]);
	}
}
