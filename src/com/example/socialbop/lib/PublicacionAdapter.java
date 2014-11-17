package com.example.socialbop.lib;

import java.util.List;
import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.socialbop.Publicacion;
import com.example.socialbop.R;

public class PublicacionAdapter extends ArrayAdapter<Publicacion> {
	private Context context;
	private List<Publicacion> objects;
	
	public PublicacionAdapter(Context context, int textViewResourceId, 
			List<Publicacion> objects) {
		super(context, textViewResourceId, objects);
		// TODO Auto-generated constructor stub
		this.context=context;
		this.objects=objects;
	}
	
	@Override
	public View getView(int position, View converView, ViewGroup parent) {
		// TODO Auto-generated method stub
		Publicacion publ = objects.get(position);
		
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.detallepublicacion_item, null);
		
		TextView tvNombre = (TextView) view.findViewById(R.id.tvDPINombre);
		tvNombre.setText(publ.getNombre());
		
		TextView tvRaza = (TextView) view.findViewById(R.id.tvDPIRaza);
		tvRaza.setText(publ.getRaza());
		
		//TextView tvUsuario = (TextView) view.findViewById(R.id.tvDPIUsuario);
		//tvUsuario.setText("usuario");
		
		TextView tvRecompensa = (TextView) view.findViewById(R.id.tvDPIRecompensa);
		tvRecompensa.setText(publ.getRecompensa());
		
		TextView tvComuna = (TextView) view.findViewById(R.id.tvDPIComuna);
		tvComuna.setText(publ.getLugar());
		
		TextView tvFecha = (TextView) view.findViewById(R.id.tvDPIFecha);
		tvFecha.setText(publ.getFecha());
		
		ImageView ivFoto = (ImageView) view.findViewById(R.id.ivDPI);
		ivFoto.setImageBitmap(BitmapFactory.decodeByteArray(publ.getFoto(), 0,
                publ.getFoto().length));
	
		return view;	
	}
}