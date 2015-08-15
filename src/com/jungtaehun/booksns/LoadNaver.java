package com.jungtaehun.booksns;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;



public class LoadNaver extends Activity{
	String key1 = "cc05b9a8af25a012d7baed854e8ae6a9";
	
	String atitle = "";
	String aauthor = "";
	String image = "";
	String aisbn = "";
	String contents = "";
	String empty = "";
	
	String ttitle = "", tauthor = "", tdes ="";
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Intent intent = getIntent();
		
		if(android.os.Build.VERSION.SDK_INT > 9) {

		    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

		        StrictMode.setThreadPolicy(policy);

		}

		
		try {
			getImageUrl(intent.getExtras().getString("isbn1"));
			if(ttitle == "") {
				Toast.makeText(getApplicationContext(), "검색결과가 없습니다. 코드가 잘못되었거나, 인식이 잘 못 되었는지 확인하신 후\n다시 연결해주세요.", Toast.LENGTH_SHORT).show();
				finish();
			}else{
				if(tdes == ""){
					tdes = "설명이 검색되지 않습니다.";
				}
				Intent intent1=new Intent(LoadNaver.this, EditActivity.class);
				Bundle data=new Bundle();
				data.putString("imgtest", image);
				data.putString("titletest", ttitle);
				data.putString("writertest", tauthor);
				data.putString("des", tdes);
				intent1.putExtras(data);
				startActivity(intent1);
				finish();
				
			}
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
		
		
	}
	


	public void getImageUrl(String isbn) throws UnsupportedEncodingException {
		aisbn= URLEncoder.encode(isbn, "UTF-8");
		
		if(atitle == ""){
			try{
				URL text= new URL(
						//"http://openapi.naver.com/search?key=" + key1 + "&query=" + "&target=book_adv&d_titl=" + atitle + "&d_auth=" + aauthor + "&d_isbn=" + contents);
						"http://openapi.naver.com/search?key=" + key1 + "&query=\"\"" + "&target=book_adv" + "&d_isbn=" + aisbn);
				
				XmlPullParserFactory parserCreator = XmlPullParserFactory.newInstance();
				XmlPullParser parser = parserCreator.newPullParser();
				
				parser.setInput(text.openStream(), null);
				
				Log.v("NET", "Parsing...");
				
				int parseEvent = parser.getEventType();
				while(parseEvent != XmlPullParser.END_DOCUMENT){
					
					switch(parseEvent){
					case XmlPullParser.START_TAG:
						String tag=parser.getName();
						
						if(tag.equals("title"))
						{
							String titlesrc=parser.nextText();
							ttitle = titlesrc;
						}
						if(tag.equals("image"))
						{
							String imagesrc=parser.nextText();
							image=imagesrc;
						}
						if(tag.equals("author"))
						{
							String authorsrc=parser.nextText();
							tauthor=authorsrc;
						}
						if(tag.equals("description"))
						{
							String dessrc=parser.nextText();
							tdes=dessrc;
						}
						break;
					}
					parseEvent=parser.next();
					

				}
				Log.v("NET","END...");
			} catch(Exception e)
			{
				Log.v("NET","Parsing fail" + e);
			}
		
		
		
		
		
	}
	

	

}
}