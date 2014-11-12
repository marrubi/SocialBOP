package com.example.socialbop;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
 





import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


public class Activity7 extends Activity{
	public Activity2 x = new Activity2();
	protected static ImageView iv1;
	private static final int SELECT_PICTURE = 1;
	private String selectedImagePath;
	
	EditText etA1Usuario;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activity7);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		iv1 = (ImageView) findViewById(R.id.ivDPIFoto);
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity7, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		switch (id) {
			case android.R.id.home:
				finish();
				break;
			case R.id.action_save:
				Intent i = new Intent(Activity7.this, Activity5.class);
				x.fa.finish();
				finish();
				startActivity(i);
			default:
				break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void loadPhotoNP7(View v){
		Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Seleccionar desde"),SELECT_PICTURE);
	}
	
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                Uri selectedImageUri = data.getData();
                selectedImagePath = getPath(selectedImageUri);
                System.out.println("Image Path : " + selectedImagePath);
                iv1.setVisibility(View.VISIBLE);
                iv1.setImageURI(selectedImageUri);
            }
        }
    }
	
    public String getPath(Uri uri) {
		String res = null;
	    String[] proj = { MediaStore.Images.Media.DATA };
	    Cursor cursor = getContentResolver().query(uri, proj, null, null, null);
	    if(cursor.moveToFirst()){;
	       int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
	       res = cursor.getString(column_index);
	    }
	    cursor.close();
	    return res;
    }
}
