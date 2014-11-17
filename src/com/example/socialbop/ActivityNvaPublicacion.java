package com.example.socialbop;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.example.socialbop.db.DBOpenHelper;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.Media;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class ActivityNvaPublicacion extends Activity {
	private static final int GALLERY_KITKAT_INTENT_CALLED = 1002;
	private static final int SELECT_PICTURE = 1;
	private String selectedImagePath;
	DBOpenHelper helper;	
	String nombremascota, raza, generomascota, lugar, fecha, recompensa, detalles, userpublicacion, estadopubl;
	RadioGroup RGSexo;
	RadioButton rbA3F, rbA3M;
	EditText etA1Nombrepet,etA1recompensa,etA3detalles;
	Spinner spinner1,spinner2;
	DatePicker datePicker1;
	ImageButton button;
	ImageButton button2;
	private ImageView iv1;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nvapubl);
		getActionBar().setDisplayHomeAsUpEnabled(true);
			
		iv1 = (ImageView) findViewById(R.id.ivNP8Foto);
		etA1Nombrepet = (EditText) findViewById(R.id.etA1Nombrepet);
		etA1recompensa = (EditText) findViewById(R.id.etA1recompensa);
		etA3detalles = (EditText) findViewById(R.id.etA3detalles);
		RGSexo = (RadioGroup) findViewById(R.id.RGSexo);
		rbA3F = (RadioButton) findViewById(R.id.rbA3F);
		rbA3M = (RadioButton) findViewById(R.id.rbA3M);
		datePicker1 = (DatePicker) findViewById(R.id.datePicker1);
		spinner1 = (Spinner) findViewById(R.id.spinner1);
		spinner2 = (Spinner) findViewById(R.id.spinner2);
		button = (ImageButton) findViewById(R.id.cargarFotoNP8);
		button2 = (ImageButton) findViewById(R.id.borrarFotoNP8);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity8, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		byte[] byteImage1 = null;
		switch (id) {
			case android.R.id.home:
				finish();
				break;
			case R.id.action_guardar:
				if(validarCampos()){
					helper = new DBOpenHelper(this);
					Publicacion publ = new Publicacion();
					iv1.buildDrawingCache();
					Bitmap bmp = iv1.getDrawingCache();
					ByteArrayOutputStream stream = new ByteArrayOutputStream();
					bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
					byteImage1 = stream.toByteArray();
					
					publ.setNombre(nombremascota);
					publ.setRaza(raza);
					publ.setGenero(generomascota);
					publ.setLugar(lugar);
					publ.setFecha(fecha);
					publ.setRecompensa(recompensa);
					publ.setDetalles(detalles);
					publ.setEstadopubl(estadopubl);
					publ.setUsuariopubl(userpublicacion);
					publ.setFoto(byteImage1);
					
					helper.insertarPublicacion(publ);
					
					Toast.makeText(this, "Publicacion Agregada", Toast.LENGTH_SHORT).show();
					setResult(1);
					finish();	
				}
				break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@SuppressLint("SimpleDateFormat")
	public boolean validarCampos(){
		
		Intent intent = getIntent();
		String nombreuser = intent.getStringExtra(ActivityPublicaciones.COPIAR_TEXTO);
		
        Integer dobYear = datePicker1.getYear();
        Integer dobMonth = datePicker1.getMonth();
        Integer dobDate = datePicker1.getDayOfMonth();
        StringBuilder sb=new StringBuilder();
        sb.append(dobDate.toString()).append("-").append(dobMonth.toString()).append("-").append(dobYear.toString());
        String dobStr = sb.toString();
        
        Date date1 = new Date();
        DateFormat simpleDate2 = new SimpleDateFormat("dd-MM-yyyy");
        
        try {
			Date date2 = simpleDate2.parse(dobStr);
			if(date2.after(date1)){
				Toast.makeText(this, "Fecha ingresada no debe ser mayor a la fecha actual", Toast.LENGTH_SHORT).show();
				return false;
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        switch (RGSexo.getCheckedRadioButtonId()){
        	case R.id.rbA3F:{
        		generomascota = "Hembra";
        }
        break;

        	case R.id.rbA3M:{
        		generomascota = "Macho";
        	}
        break;
        }
        		
		nombremascota = etA1Nombrepet.getText().toString();
		raza = String.valueOf(spinner1.getSelectedItem());
		lugar = String.valueOf(spinner2.getSelectedItem());
		fecha = dobStr;
		recompensa = etA1recompensa.getText().toString(); 
		detalles = etA3detalles.getText().toString();
		userpublicacion = nombreuser;
		estadopubl = "up";
		
		if (!rbA3F.isChecked() && !rbA3M.isChecked()) {
			Toast.makeText(this, "Debe seleccionar género", Toast.LENGTH_SHORT).show();
			return false;
		}
		if(nombremascota.trim().length() == 0){
			Toast.makeText(this, "Debe escribir nombre de mascota", Toast.LENGTH_SHORT).show();
			return false;
		}
		if(recompensa.trim().length() == 0){
			Toast.makeText(this, "Debe ingresar recompensa", Toast.LENGTH_SHORT).show();
			return false;
		}
		if(detalles.trim().length() == 0){
			Toast.makeText(this, "Debe ingresar detalles", Toast.LENGTH_SHORT).show();
			return false;
		}
		
		if(iv1.getDrawable().getConstantState().equals
            (getResources().getDrawable(R.drawable.ic_launcher).getConstantState())){
			Toast.makeText(this, "Debe agregar una imagen", Toast.LENGTH_SHORT).show();
			return false;
		}
		return true;
	}
	
	public void cleanPhoto(View v){
		iv1.setImageResource(R.drawable.ic_launcher);
		iv1.setVisibility(View.GONE);
		button2.setVisibility(View.GONE);
		button.setVisibility(View.VISIBLE);
	}
	
	public void loadPhoto(View v){
		if (Build.VERSION.SDK_INT <19){
		    Intent intent = new Intent(); 
		    intent.setType("image/*");
		    intent.setAction(Intent.ACTION_GET_CONTENT);
		    startActivityForResult(Intent.createChooser(intent, "Seleccionar desde"),SELECT_PICTURE);
		} else {
			Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
		    intent.addCategory(Intent.CATEGORY_OPENABLE);
		    intent.setType("image/*");
			startActivityForResult(intent, GALLERY_KITKAT_INTENT_CALLED);
		}
		Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
       
	}
	
	@SuppressLint("NewApi")
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                Uri selectedImageUri = data.getData();
                selectedImagePath = getPath(selectedImageUri);
                Bitmap bitmap = decodeSampledBitmapFromResource(getResources(),selectedImagePath, 150, 150);
                iv1.setVisibility(View.VISIBLE);
                iv1.setImageBitmap(bitmap);
            }
            if(requestCode == GALLERY_KITKAT_INTENT_CALLED){
            	Uri uri = data.getData();
                final int takeFlags = data.getFlags()
                        & (Intent.FLAG_GRANT_READ_URI_PERMISSION
                        | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                // Check for the freshest data.
                getContentResolver().takePersistableUriPermission(uri, takeFlags);
            	try {
					Bitmap bitmap = Media.getBitmap(this.getContentResolver(), uri);
					ByteArrayOutputStream stream = new ByteArrayOutputStream();
					bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
					byte[] byteArray = stream.toByteArray();
					bitmap = decodeSampledBitmapFromResource(getResources(), byteArray, 150, 150);
					iv1.setVisibility(View.VISIBLE);
	                iv1.setImageBitmap(bitmap);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
            if(!iv1.getDrawable().getConstantState().equals
                (getResources().getDrawable(R.drawable.ic_launcher).getConstantState())){
            	button.setVisibility(View.GONE);
            	button2.setVisibility(View.VISIBLE);
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
    
    public static Bitmap decodeSampledBitmapFromResource(Resources res, byte[] byyte,
            int reqWidth, int reqHeight) {
    	
        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        //BitmapFactory.decodeResource(res, resId, options);
        BitmapFactory.decodeByteArray(byyte, 0, byyte.length, options);
        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeByteArray(byyte, 0, byyte.length, options);
    }
    
    public static Bitmap decodeSampledBitmapFromResource(Resources res, String  pathName,
            int reqWidth, int reqHeight) {
    	
        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        //BitmapFactory.decodeResource(res, resId, options);
        BitmapFactory.decodeFile(pathName, options);
        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(pathName, options);
    }
    
    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
	    // Raw height and width of image
	    final int height = options.outHeight;
	    final int width = options.outWidth;
	    int inSampleSize = 1;
	
	    if (height > reqHeight || width > reqWidth) {
	
	        final int halfHeight = height / 2;
	        final int halfWidth = width / 2;
	
	        // Calculate the largest inSampleSize value that is a power of 2 and keeps both
	        // height and width larger than the requested height and width.
	        while ((halfHeight / inSampleSize) > reqHeight
	                && (halfWidth / inSampleSize) > reqWidth) {
	            inSampleSize *= 2;
	        }
	    }
	    return inSampleSize;
    }
}
