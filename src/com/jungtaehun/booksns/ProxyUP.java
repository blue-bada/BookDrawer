package com.jungtaehun.booksns;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
 
public class ProxyUP {
        
        private static AsyncHttpClient client = new AsyncHttpClient(); 
    	
    	public static void uploadArticle(Article article, String filePath, 
    			AsyncHttpResponseHandler responseHandler) { 
    		RequestParams params = new RequestParams();
    		params.put("title", article.getTitle());
    		params.put("writer", article.getWriter());
    		params.put("id", article.getId());
    		params.put("content",article.getContent());
    		params.put("writeDate",article.getWriteDate());
    		params.put("imgName",article.getImgName());
    		
    		params.put("uploadedfile", filePath );

    		
    		//client.post("http://www.jungtaehundb.esy.es/upload", params, responseHandler); 
    		client.post("http://jungtaehundb.woobi.co.kr/xe/upload.php", params, responseHandler);
    	  } 

    	
        /**public String uploadArticle(Article article, String filePath) {
               
               
                try {                       
                       
                        //URL url = new URL("http://www.jungtaehundb.esy.es/upload");
                	URL url = new URL("http://www.jungtaehundb.woobi.co.kr/xe/upload.php");
                       
                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                       
                        conn.setRequestProperty("Accept-Charset", "UTF-8");
                       
                        conn.setRequestMethod("POST");
                        conn.setRequestProperty("Cache-Control", "no-cache");
                        conn.setRequestProperty("Connection", "Keep-Alive");
                       
                        //응답 데이터가 많을 때에는 일정양 씩 묶어서 보내겠다는 의미
                        conn.setRequestProperty("Transfer-Encoding", "chunked");
                       
                        conn.setDoOutput(true);
                        conn.setDoInput(true);
 
                        //Content는 multipart형식이고 데이터의 끝을 boundary(*****)로 표시를 하겠다.(content-length이 없으므로...)
                        conn.setRequestProperty("Content-Type","multipart/form-data;boundary=" + boundary);
 
                        // write data
                        DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
                       
                       
                        dos.write( getPostData("title",article.getTitle()).getBytes("UTF-8") );
                        dos.write( getPostData("writer",article.getWriter()).getBytes("UTF-8") );
                        dos.write( getPostData("id",article.getId()).getBytes("UTF-8") );
                        dos.write( getPostData("content",article.getContent()).getBytes("UTF-8") );
                        dos.write( getPostData("writeDate",article.getWriteDate()).getBytes("UTF-8") );
                        dos.write( getPostData("imgName",article.getImgName()).getBytes("UTF-8") );
                   
                   
                        dos.writeBytes(twoHyphens + boundary + lineEnd);
 
                        // close streams
                        Log.i("Test", "File is written");
                       
                        dos.flush();
                        dos.close();
                       
                        int status = conn.getResponseCode();
                        Log.i("test", "statusUP:" + status);
                       
                        switch (status) {
                        case 200:
                        case 201:
                                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                                StringBuilder sb = new StringBuilder();
                                String line;
                                while ((line = br.readLine()) != null) {
                                        sb.append(line + "\n");
                                }
                                br.close();
                                return sb.toString();
                        }
 
                } catch (Exception e) {
                        e.printStackTrace();
                        Log.i("test", "UPLOAD ERROR:" + e);
                }
               
                return null;
        }**/
       
        // post방식에 맞게 데이터 형식을 추가
        /**private String getPostData(String key, String value) {
                String result = twoHyphens + boundary + lineEnd;
                result += "Content-Disposition: form-data; name=\"" + key + "\"" + lineEnd;
                result += lineEnd;
               
                result += value;
               
                result += lineEnd;
               
                return result;
        }**/
       
}