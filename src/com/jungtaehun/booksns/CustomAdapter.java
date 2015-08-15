package com.jungtaehun.booksns;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import com.jungtaehun.booksns.R;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class CustomAdapter extends ArrayAdapter<Article> {
	private Context context;
	private int layoutResourceId;
	private ArrayList<Article> listData;
	
	
	
	public CustomAdapter(Context context, int layoutResourceId, ArrayList<Article> listData) {
		super(context, layoutResourceId, listData);
		this.context = context;
		this.layoutResourceId = layoutResourceId;
		this.listData = listData;
	}
	
	public View getView(int position, View convertView, ViewGroup parent) {
		
		View row = convertView;
		
		if(row == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			
			row = inflater.inflate(layoutResourceId, parent, false);
		}
		
		TextView tvTitle = (TextView) row.findViewById(R.id.ct1);
		TextView tvContent = (TextView) row.findViewById(R.id.ct2);
		ImageView imageView = (ImageView) row.findViewById(R.id.cimage);
		
		tvTitle.setText(listData.get(position).getTitle());
		tvContent.setText(listData.get(position).getContent());
		
		try{
		    URL ulrn = new URL(listData.get(position).getImgName());
		    HttpURLConnection con = (HttpURLConnection)ulrn.openConnection();
		    InputStream is = con.getInputStream();
		    Bitmap bmp = BitmapFactory.decodeStream(is);
		    imageView.setImageBitmap(bmp);

		} catch(Exception e) {
		}
		
		//String img_path = context.getFilesDir().getPath() + "/" + listData.get(position).getImgName();
		//File img_load_path = new File(img_path);
		
		/**try {
			InputStream is = context.getAssets().open(listData.get(position).getImgName());
			Drawable d = Drawable.createFromStream(is, null);
			imageView.setImageDrawable(d);
		} catch (IOException e) {
			Log.e("ERROR", "ERROR : " + e);
		}**/
		
		//if (img_load_path.exists()) {
			
		//}
		
		return row;
	}

}
