package com.webthreeapp.testbook;

import java.io.FileNotFoundException;
import java.io.IOException;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

public class AssetProvider extends ContentProvider {

	public static final String AUTHORITY = "com.example.testbook.assetfile";
	public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY);
	public static final String TAG = "AssetProvider";


	@Override
	public AssetFileDescriptor openAssetFile(Uri uri, String mode)
			throws FileNotFoundException {
		AssetManager assetManager = getContext().getAssets();
		Log.i(TAG, "open Asset file");
		try {
			return assetManager.openFd("book.mp3");
			//return assetManager.openFd("book.pdf");
		} catch (IOException e) {
			e.printStackTrace();
			Log.e(TAG, "ERROR: " + e);
			throw new FileNotFoundException(e.getMessage());
		}
	}


	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {

		return 0;
	}

	@Override
	public String getType(Uri uri) {

		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {

		return null;
	}

	@Override
	public boolean onCreate() {

		return false;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        return null;

	}

	@Override
	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

		return 0;
	}



}
