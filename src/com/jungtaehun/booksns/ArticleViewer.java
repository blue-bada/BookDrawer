package com.jungtaehun.booksns;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.jungtaehun.booksns.R;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ArticleViewer extends Activity{
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.spec);
		
		TextView tvTitle = (TextView)findViewById(R.id.etitle);
		TextView tvWriter = (TextView)findViewById(R.id.ewriter);
		TextView tvContent = (TextView)findViewById(R.id.econtent);
		TextView tvWriteDate = (TextView)findViewById(R.id.ewritetime);
		
		ImageView tvImage = (ImageView)findViewById(R.id.eimage);
		
		String articleNumber = getIntent().getExtras().getString("ArticleNumber");
		
		Dao dao = new Dao( getApplicationContext() );
		
		Article article = dao.getArticleByArticleNumber( Integer.parseInt(articleNumber) );
		
		tvTitle.setText(article.getTitle());
		tvWriter.setText(article.getWriter());
		tvContent.setText(article.getContent());
		tvWriteDate.setText(article.getWriteDate());
		
		try{
		    URL ulrn = new URL(article.getImgName());
		    HttpURLConnection con = (HttpURLConnection)ulrn.openConnection();
		    InputStream is = con.getInputStream();
		    Bitmap bmp = BitmapFactory.decodeStream(is);
		    tvImage.setImageBitmap(bmp);

		} catch(Exception e) {
		}
		
		/**try {
			InputStream ims = getApplicationContext().getAssets().open(article.getImgName());
			Drawable d = Drawable.createFromStream(ims, null);
			tvImage.setImageDrawable(d);
		}catch(IOException e){
			Log.e("ERROR", "ERROR:" + e);
		}**/
		
		//String img_path = getApplicationContext().getFilesDir().getPath() + "/" + article.getImgName();
		//File img_load_path = new File(img_path);
		
		
		
	}

}
