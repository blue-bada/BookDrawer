package com.jungtaehun.booksns;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.http.Header;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.provider.Settings.Secure;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jungtaehun.booksns.R;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class EditActivity extends Activity {
	
	private EditText etContent;
	private String ititle, iwriter, iimage, wititle, wiwriter, wides, ides;
	private String filepath="";
	TextView tvTitle, tvWriter, tvDes;
	Intent intent;
	


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit);
		intent = getIntent();
		
		//etWriter = (EditText)findViewById(R.id.sswriter);
		//etTitle = (EditText)findViewById(R.id.sstitle);
		etContent = (EditText)findViewById(R.id.sscontent);
		tvTitle = (TextView)findViewById(R.id.textTitle);
		tvWriter = (TextView)findViewById(R.id.textWriter);
		tvDes = (TextView)findViewById(R.id.textdes);
		
		//ibPhoto = (ImageButton)findViewById(R.id.ssimgbtn);
		//nbtn = (Button)findViewById(R.id.nbtn);

		
		//ibPhoto.setOnClickListener(this);
		//nbtn.setOnClickListener(this);
		
		ititle = intent.getExtras().getString("titletest");
		iwriter = intent.getExtras().getString("writertest");
		iimage = intent.getExtras().getString("imgtest");
		ides = intent.getExtras().getString("des");
		intent.getExtras().getString("des");
		
		wititle = ititle.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "");
	    wiwriter = iwriter.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "");
		wides = ides.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "");
	     
		tvTitle.setText(wititle);
		tvWriter.setText(wiwriter);
		tvDes.setText(wides);
		
		
		

		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit, menu);
		return true;
	}
	

	





	
	//private String filePath;
	//private String fileName;

	//private static final int REQUEST_PHOTO_ALBUM = 1;
	
	/**protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		try {
			if(requestCode == REQUEST_PHOTO_ALBUM) {
				Uri uri = getRealPathUri( data.getData() );
				filePath = uri.toString();
				fileName = uri.getLastPathSegment();
				
				
			}
		} catch(Exception e) {
			Log.e("test", "onActivityResult ERROR : " + e);
		}
		
	}
	
	private Uri getRealPathUri(Uri uri) {
		Uri filePathUri = uri;
		if (uri.getScheme().toString().compareTo("content") == 0) {
			Cursor cursor = getApplicationContext().getContentResolver().query(uri, null, null, null, null);
			if (cursor.moveToFirst()) {
				int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
				filePathUri = Uri.parse(cursor.getString(column_index));
			}
		}
		return filePathUri;
	}**/
	private ProgressDialog progressDialog;
	
	/**@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		
		switch (view.getId()) {
		case R.id.ssimgbtn :-
		Intent intent = new Intent(Intent.ACTION_PICK);
		
		intent.setType(Images.Media.CONTENT_TYPE);
		intent.setData(Images.Media.EXTERNAL_CONTENT_URI);
		startActivityForResult(intent, REQUEST_PHOTO_ALBUM);
		
		break;
		case R.id.uploadbtn :
			//new Thread() {
				//public void run() {
					
				//}
			//}.start();
		//break;
			
		
		}
	}**/
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		//int id = item.getItemId();
		//if (id == R.id.sendbtn) {



			
		//}
		switch (item.getItemId()) {
		case R.id.sendbtn :
			/**new Thread() {
				public void run() {
					String ID = Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
					String DATE = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.KOREA).format( new Date() );
					
					Article article = new Article(
							0,
							etTitle.getText().toString(),
							etWriter.getText().toString(),
							ID,
							etContent.getText().toString(),
							DATE,
							fileName);
					
					ProxyUP proxyUP = new ProxyUP();
					proxyUP.uploadArticle(article, filePath);
				}
			}.start();
		
		break;**/
			String ID = Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
			String DATE = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.KOREA).format( new Date() );
			Article article1 = new Article(0, wititle.toString(), wiwriter.toString(), ID, etContent.getText().toString(), DATE, iimage.toString());
			progressDialog = ProgressDialog.show(EditActivity.this, "", "업로드 중입니다.");
			//ProxyUP proxyUP = new ProxyUP();
			ProxyUP.uploadArticle(article1, filepath, new AsyncHttpResponseHandler() {
				@Override
				public void onFailure(int statusCode, Header[] headers,
						byte[] responseBody, Throwable error) {
					Log.e("test", "up onFailure:" + statusCode);
					progressDialog.cancel();
					Toast.makeText(getApplicationContext(), "onFailure", Toast.LENGTH_SHORT).show();
					finish();
				}
				@Override
				public void onSuccess(int statusCode, Header[] headers,
						byte[] responseBody) {
					Log.i("test", "up onSuccess:" + statusCode);
					progressDialog.cancel();
					Toast.makeText(getApplicationContext(), "onSuccess", Toast.LENGTH_SHORT).show();
					finish();
				}
			});
		
			break;
		
		}
		return false;
	}
}
		
