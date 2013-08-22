package com.example.testbook;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;

public class MainActivity extends Activity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);


		Intent intent = new Intent(Intent.ACTION_VIEW);
		Uri uri = Uri.parse("content://com.example.testbook.assetfile/book.pdf");
		//intent.setPackage("com.example.sreader");
		intent.setDataAndType(uri,"application/pdf");

		startActivity(intent);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
