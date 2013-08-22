package com.example.testbook;

import java.io.FileNotFoundException;
import java.io.IOException;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.provider.BaseColumns;
import android.util.Log;

public class AssetProvider extends ContentProvider {

	public static final String AUTHORITY = "com.example.testbook.assetfile";
	public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY);
	public static final String TAG = "AssetProvider";

	DatabaseHelper databaseHelper;

	@Override
	public AssetFileDescriptor openAssetFile(Uri uri, String mode)
			throws FileNotFoundException {
		Log.i(TAG, "open Asset file");
		try {
			return getContext().getAssets().openFd("book.pdf");
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
		databaseHelper = new DatabaseHelper(getContext());
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

		SQLiteDatabase mDb = databaseHelper.getReadableDatabase();
        SQLiteQueryBuilder qBuilder = new SQLiteQueryBuilder();
        qBuilder.setTables("dummy");
        Cursor c = qBuilder.query(mDb,
        		projection,
                null,
                null,
                null,
                null,
                null);
        c.setNotificationUri(getContext().getContentResolver(), uri);
        return c;

	}

	@Override
	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

		return 0;
	}

	//helper for dummy table
	private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, "test.db", null, 1);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
          db.execSQL("CREATE TABLE dummy ("
                    + BaseColumns._ID + " INTEGER PRIMARY KEY,"
                    + "_data TEXT"
                    + ");");
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        	db.execSQL("DROP TABLE IF EXISTS dummy");
        	onCreate(db);
        }
    }

}
