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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Search extends Activity implements OnClickListener{
	EditText STitle, SWriter, SIsbn;
	Button SButton;
	String iTitle = "", iWriter = "", iIsbn = "";
	//private ProgressDialog progressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		STitle = (EditText)findViewById(R.id.stet);
		SWriter = (EditText)findViewById(R.id.swet);
		SIsbn = (EditText)findViewById(R.id.siet);
		SButton = (Button)findViewById(R.id.sbtn);
		
		SButton.setOnClickListener(this);
		
		
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {

		case R.id.sbtn :
			iTitle = STitle.getText().toString();
			iWriter = SWriter.getText().toString();
			iIsbn = SIsbn.getText().toString();
			
			if(iTitle == "")
			{
				Toast.makeText(getApplicationContext(), "책 제목을 적어주시기 바랍니다.", Toast.LENGTH_SHORT).show();
			}else{
				//progressDialog = ProgressDialog.show(Search.this, "", "검색 중입니다.");
				
				Intent intent1=new Intent(Search.this, LoadNaver.class);
				Bundle data=new Bundle();
				data.putString("title2", iTitle);
				data.putString("author2", iWriter);
				data.putString("isbn2", iIsbn);
				intent1.putExtras(data);
				startActivity(intent1);
				finish();
			}
			
			
	}
}
}
