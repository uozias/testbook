package com.webthreeapp.testbook;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;

public class MainActivity extends Activity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);


		Intent intent = new Intent();
		intent.setAction("com.webthreeapp.book.VIEW");
		Uri uri = Uri.parse("content://com.webthreeapp.testbook.assetfile/book.pdf");
		intent.setClassName("com.webthreeapp.sreader", "com.artifex.mupdfdemo.MuPDFActivity");
		intent.setDataAndType(uri,"application/pdf");
		try{
			 startActivity(intent);
		}catch (ActivityNotFoundException e){
			//playstoreへ
			Uri uri2 = Uri.parse("market://details?id=" + "com.webthreeapp.sreader");
			Intent it = new Intent(Intent.ACTION_VIEW, uri2);
			startActivity(it);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
