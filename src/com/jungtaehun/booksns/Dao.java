package com.jungtaehun.booksns;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class Dao {
	private Context context;
	private SQLiteDatabase database;
	
	public Dao(Context context) {
		this.context = context;
		
		
		database = context.openOrCreateDatabase("bookData.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
		
		try{
			String sql = "CREATE TABLE IF NOT EXISTS Articles(ID integer primary key autoincrement,"
					+ " ArticleNumber integer UNIQUE not null,"
					+ " Title text not null, "
					+ "WriterName text not null,"
					+ " WriterID text not null,"
					+ " Content text not null, "
					+ "WriteDate text not null,"
					+ "ImgName text UNIQUE not null);";
			database.execSQL(sql);
		} catch (Exception e) {
			Log.e("test", "CREATE TABLE FAILED! - " + e);
			e.printStackTrace();
		}
	}
	
	
	

	public void insertJsonData(String jsonData) {
		int articleNumber;
		String title;
		String writer;
		String id;
		String content;
		String writeDate;
		String imgName;
		
		//FileDownloader fileDownloader = new FileDownloader(context);
		
		try{
			JSONArray jArr = new JSONArray(jsonData);
			
			for(int i=0; i <jArr.length(); ++i){
				JSONObject jObj = jArr.getJSONObject(i);
				
				articleNumber = jObj.getInt("ArticleNumber");
				title =jObj.getString("Title");
				writer = jObj.getString("Writer");
				id = jObj.getString("Id");
				content = jObj.getString("Content");
				writeDate = jObj.getString("WriteDate");
				imgName = jObj.getString("ImgName");
				
				Log.i("test", "ArticleNumber: " + articleNumber + ", Title: " + title);
					
				String sql = "INSERT INTO Articles(ArticleNumber, Title, WriterName, WriterID, Content, WriteDate, ImgName)"
						+ "VALUES(" + articleNumber + ",'" + title + "','" + writer + "','" + id + "','"
						+ content + "','" + writeDate + "','" + imgName + "');";
				
				try{
					database.execSQL(sql);
				}catch(Exception e){
					//Log.e("test", "DB error! - "+e);
					//e.printStackTrace();
				}
				
				//fileDownloader.downFile(imgName, imgName);
				
			}
		}catch(JSONException e){
			Log.e("test", "JSON ERROR! - " + e);
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("finally")
	public ArrayList<Article> getArticleList() {
		
		ArrayList<Article> articleList = new ArrayList<Article>();
		
		int articleNumber;
		String title;
		String writer;
		String id;
		String content;
		String writeDate;
		String imgName;
		
		String sql = "SELECT * FROM Articles;";
		Cursor cursor = database.rawQuery(sql, null);
		try{
			while (cursor.moveToNext()) {
				articleNumber = cursor.getInt(1);
				title = cursor.getString(2);
				writer = cursor.getString(3);
				id = cursor.getString(4);
				content = cursor.getString(5);
				writeDate = cursor.getString(6);
				imgName = cursor.getString(7);
				
				articleList.add( new Article(articleNumber, title, writer, id, content, writeDate, imgName) );
				
			}
		}catch(Exception e){
			Log.e("test", "cursor error! - "+e);
			e.printStackTrace();
		}finally {
		
		
		
		
		cursor.close();
		
		return articleList;
		}
	}
	
public Article getArticleByArticleNumber( int articleNumber ) {
		
		Article article = null;
		
		String title;
		String writer;
		String id;
		String content;
		String writeDate;
		String imgName;
		
		String sql = "SELECT * FROM Articles WHERE ArticleNumber = " + articleNumber + ";";;
		Cursor cursor = database.rawQuery(sql, null);
		
		cursor.moveToNext();
		
			articleNumber = cursor.getInt(1);
			title = cursor.getString(2);
			writer = cursor.getString(3);
			id = cursor.getString(4);
			content = cursor.getString(5);
			writeDate = cursor.getString(6);
			imgName = cursor.getString(7);
			
			article = new Article(articleNumber, title, writer, id, content, writeDate, imgName);

		
		cursor.close();
		
		return article;
	}
	
/**public String getJsonTestData() {
		
		StringBuilder sb = new StringBuilder();
		sb.append("");
		
		sb.append("[");
		
		sb.append("	 {");
		sb.append("	    'ArticleNumber':'1',");
		sb.append("	    'Title':'���õ� ���� �Ϸ�',");
		sb.append("	    'Writer':'�л�1',");
		sb.append("	    'Id':'6613d02f3e2153283f23bf621145f877',");
		sb.append("	    'Content':'������ �� �⸻�����...',");
		sb.append("	    'WriteDate':'2013-09-23-10-10',");
		sb.append("	    'ImgName':'photo1.jpg'");
		sb.append("	 },");
		sb.append("	 {");
		sb.append("	    'ArticleNumber':'2',");
		sb.append("	    'Title':'���� �ְ� 3000����',");
		sb.append("	    'Writer':'��̿� ����',");
		sb.append("	    'Id':'6326d02f3e2153266f23bf621145f734',");
		sb.append("	    'Content':'��̿������Դϴ�. ���Բ����� ���������� �ְ� 3000�������� 30�� �̳� �����Աݰ����մϴ�.',");
		sb.append("	    'WriteDate':'2013-09-24-11-22',");
		sb.append("	    'ImgName':'photo2.jpg'");
		sb.append("	 },");
		sb.append("	 {");
		sb.append("	    'ArticleNumber':'3',");
		sb.append("	    'Title':'MAC��Ͻ�û',");
		sb.append("	    'Writer':'�л�2',");
		sb.append("	    'Id':'8426d02f3e2153283246bf6211454262',");
		sb.append("	    'Content':'1a:2b:3c:4d:5e:6f',");
		sb.append("	    'WriteDate':'2013-09-25-12-33',");
		sb.append("	    'ImgName':'photo3.jpg'");
		sb.append("	 }");
		sb.append("]");
		 
		 return sb.toString();
	}**/
}
