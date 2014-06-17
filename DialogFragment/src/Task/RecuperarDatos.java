package Task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import Gson.Country;
import Gson.DatosPersona;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

public class RecuperarDatos extends AsyncTask<String, Void, Void> {

	 private static final int REGISTRATION_TIMEOUT = 3 * 1000;
	  private static final int WAIT_TIMEOUT = 30 * 1000;
	  private final HttpClient httpclient = new DefaultHttpClient();
	  private static final String LOG_TAG = "JSONStreamReader";
	  private ArrayList<Country> countryList = new ArrayList<Country>();
	  private boolean success = false;
	  final HttpParams params = httpclient.getParams();
	  private boolean error = false;
	 
	  protected Void doInBackground(String... urls) {
	 
	   String URL = null;
	 
	   try {
	 
	    //URL passed to the AsyncTask 
	    URL = urls[0];
	    HttpConnectionParams.setConnectionTimeout(params, REGISTRATION_TIMEOUT);
	    HttpConnectionParams.setSoTimeout(params, WAIT_TIMEOUT);
	    ConnManagerParams.setTimeout(params, WAIT_TIMEOUT);
	 
	 
	    HttpPost httpPost = new HttpPost(URL);
	 
	    //Response from the Http Request
	    HttpResponse response = httpclient.execute(httpPost);
	 
	    //Check the Http Request for success
	    StatusLine statusLine = response.getStatusLine();
	    if(statusLine.getStatusCode() == HttpStatus.SC_OK){
	      
	     Gson gson = new Gson();
	     //create a new JSON reader from the response input stream 
	     JsonReader jsonReader = new JsonReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
	     //begin parsing
	     jsonReader.beginObject();
	     //stay in loop as long as there are more data elements
	     while (jsonReader.hasNext()) {
	      //get the element name
	      String name = jsonReader.nextName();
	       
	      if (name.equals("success")) {
	       success = jsonReader.nextBoolean();
	      }
	      //if the element name is the list of countries then start the array
	      else if(name.equals("countryList")){
	       jsonReader.beginArray();
	       while (jsonReader.hasNext()) {
	        //parse every element and convert that to a country object
	        Country country = gson.fromJson(jsonReader, Country.class);
	        //add the country object to the list
	        countryList.add(country);
	       }
	       jsonReader.endArray();
	      }
	     }
	     //end reader and close the stream
	     jsonReader.endObject();
	     jsonReader.close();
	 
	    }
	    else{
	     //Closes the connection.
	     Log.w(LOG_TAG,statusLine.getReasonPhrase());
	     response.getEntity().getContent().close();
	     throw new IOException(statusLine.getReasonPhrase());
	    }
	 
	 
	   } catch (Exception e) {
	    Log.w(LOG_TAG,e );
	    error = true;
	    cancel(true);
	   }
	 
	   return null;
	 
	  }
	 
	  protected void onCancelled() {
	   Log.e(LOG_TAG,"Error occured during data download");
	  }
	 
	  protected void onPostExecute(Void unused) {
	   if (error) {
	    Log.e(LOG_TAG,"Data download ended abnormally!");
	   } else {
	         Log.d("Salida",countryList.toString());
	   }
	  }
}