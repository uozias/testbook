package com.webthreeapp.testbook;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends Activity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//起動時にコンテンツを表示
		showContent();

		//ボタンを押したらコンテンツを表示
		Button showButton = (Button) findViewById(R.id.showBook);
		showButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showContent();
			}
		});

		//全広告にタッチイベントを登録
		LinearLayout linearLayout = (LinearLayout) findViewById(R.id.adList);
		ImageView tmpView = null;
		for (int i = 0; i < linearLayout.getChildCount(); i++){
			tmpView = (ImageView) linearLayout.getChildAt(i);
			tmpView.setOnTouchListener(new AdTouchListener());
		}
	}

	//コンテンツ表示関数
	public void showContent(){
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



	//広告タッチイベントをもらう独自リスナー
	private class AdTouchListener implements OnTouchListener{

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			Intent adIntent = new Intent();
			adIntent.setAction(Intent.ACTION_VIEW);
			adIntent.setData(Uri.parse("http://www.saizo-aoyagi.jp/"));
			startActivity(adIntent);
			return false;
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		//getMenuInflater().inflate(R.menu.main, menu);
		return false;
	}


}
