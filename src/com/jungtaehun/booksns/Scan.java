package com.jungtaehun.booksns;

import com.jungtaehun.booksns.R;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Scan extends Activity implements OnClickListener{
	Intent intent;
	String contents, format;
	String isbn = "";
	String iIsbn = "";
	EditText isbncd;
	private ProgressDialog progressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		intent = getIntent();
		Intent intent1 = new Intent("com.google.zxing.client.android.SCAN");
		startActivityForResult(intent1, 0);
		
	}
	
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		if (requestCode == 0) {
			if (resultCode == RESULT_OK) {
				contents = intent.getStringExtra("SCAN_RESULT");
				format = intent.getStringExtra("SCAN_RESULT_FORMAT");
				setContentView(R.layout.activity_scan);
				isbncd = (EditText)findViewById(R.id.isbncd);
				Button btn = (Button)findViewById(R.id.isbnbtn);
				isbncd.setText(contents);
				btn.setOnClickListener(this);
			} else if (requestCode == RESULT_CANCELED) {
				
			}
		}
	}

	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {

		case R.id.isbnbtn :
			iIsbn = isbncd.getText().toString();
			
			if(iIsbn == "")
			{
				Toast.makeText(getApplicationContext(), "다시 검색해주세요.", Toast.LENGTH_SHORT).show();
			}else{
				progressDialog = ProgressDialog.show(Scan.this, "", "검색 중입니다.");
				
				Intent intent1=new Intent(Scan.this, LoadNaver.class);
				Bundle data=new Bundle();
				data.putString("isbn1", iIsbn);
				intent1.putExtras(data);
				startActivity(intent1);
				finish();
			}
			
			
	}
}
	


}
