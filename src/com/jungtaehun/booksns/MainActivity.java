package com.jungtaehun.booksns;

import java.util.ArrayList;

import com.jungtaehun.booksns.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnItemClickListener{
	
	private ListView mainListView1;
	private ArrayList<Article> articleList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mainListView1 = (ListView)findViewById(R.id.listView1);
		if (isNetwork() == false) {
			Toast.makeText(getApplicationContext(), "인터넷이 연결되지 않았습니다. 인터넷을 확인하신 후 다시 연결해주시기 바랍니다.", Toast.LENGTH_SHORT).show();
			finish();
		}else{
			refreshData();
			
			listView();
		}
		
		
		

		
		
	}
	
	/**private void listView() {
		// TODO Auto-generated method stub
		Dao dao = new Dao( getApplicationContext() );
		articleList = dao.getArticleList();
		CustomAdapter customAdapter = new CustomAdapter(this, R.layout.custom_list_row, articleList);
		mainListView1.setAdapter(customAdapter);
		mainListView1.setOnItemClickListener(this);
	}**/
	private void listView(){
		
		
		//ListView listView = (ListView)findViewById(R.id.custom_list_listView);
		Dao dao = new Dao(getApplicationContext());
		articleList = dao.getArticleList();
		
		Log.i("test", "articleList count:" + articleList.size());
		CustomAdapter customAdapter = new CustomAdapter(this, R.layout.custom_list_row, articleList);
		/* 
		 * context를 넘기는 자리에 activity의 this를 넘길 수 있는 이유는 
		 * activity가 context를 상속받았기 때문.
		 */
		mainListView1.setAdapter(customAdapter);
		mainListView1.setOnItemClickListener(this);

	}

	
	private final Handler handler = new Handler();
	/**private void refreshData() {
		// TODO Auto-generated method stub
		new Thread() {
			public void run() {
				Proxy proxy = new Proxy();
				String jsonData = proxy.getJSON();
				
				
				Dao dao = new Dao( getApplicationContext() );
				
				//String testJsonData = dao.getJsonTestData();
				
				dao.insertJsonData( jsonData );
				
				handler.post(new Runnable(){
					public void run() {
						listView();
					}
				});
				
			}
		}.start();
	}**/
	private void refreshData(){
		new Thread(){
			public void run(){
				Proxy proxy = new Proxy();
				Log.i("test", "json get!!!");
				String jsonData = proxy.getJSON();
				Log.i("test", "json get2!!!" +  jsonData);
				
				/* 
				 * Dao생성시 해당 context에 DB 생성
				 * DB 연동 Json데이터를 getJsonData()로 불러 String 변수에 지정
				 * 지정한 변수를 insertJsonData()로 레코드 생성 
				 */
				Dao dao = new Dao(getApplicationContext());
				//String testJsonData = dao.getJsonTestData();
				//dao.insertJsonData(testJsonData);
				dao.insertJsonData(jsonData);
				
				handler.post(new Runnable(){
					public void run(){
						
						Log.i("test", "handler ON!!!");
						listView();
					}
				});
			}
		}.start();
	}
	
	

	public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
		Intent intent = new Intent(this, ArticleViewer.class);
		
		intent.putExtra("ArticleNumber", articleList.get(position).getArticleNumber() + "" );
		startActivity(intent);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);

		return super.onCreateOptionsMenu(menu);
	}
	
	private boolean isNetwork() {
		ConnectivityManager manager = (ConnectivityManager) this
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo mobile = manager
				.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		NetworkInfo wifi = manager
				.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

		if (wifi.isConnected() || mobile.isConnected())
			return true;
		else
			return false;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.sync :
			refreshData();
			break;
		case R.id.add :
			//Intent intent = new Intent(this, Search.class);
			//startActivity(intent);
			//break;
			//Intent intent1 = new Intent("com.google.zxing.client.android.SCAN");
			//startActivityForResult(intent1, 0);
			//Intent intent = new Intent(MainActivity.this, Search.class);
			//startActivity(intent);
			//break;
			Intent intent1 = new Intent(MainActivity.this, Scan.class);
			startActivity(intent1);
			break;
		//case R.id.barcode :
			//Intent intent1 = new Intent(MainActivity.this, Scan.class);
			//startActivity(intent1);
			//break;
		}

		return super.onOptionsItemSelected(item);
	}

	
}
