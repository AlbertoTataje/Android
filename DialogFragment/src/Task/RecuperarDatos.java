package Task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import Gson.DatosPersona;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import com.google.gson.Gson;

public class RecuperarDatos extends AsyncTask<String, Void, String> {

	String url = "http://internetria.com/prueba.json";
	Context context;

	public RecuperarDatos(Context context) {
		this.context = context;
	}

	@Override
	protected String doInBackground(String... params) {
		try {
			Gson miGson = new Gson();
	        URL url = new URL("http://internetria.com/prueba.json");
	        BufferedReader reader = new BufferedReader(
	                new InputStreamReader(url.openStream(),
	                        Charset.forName("UTF-8")));
	 
	        //Pasamos la info del json a un objeto para consultarlo
	        DatosPersona data = miGson.fromJson(reader, DatosPersona.class);
		//Convertir a esta clase
		//DatosPersona response = gson.fromJson(reader,DatosPersona.class);
		Log.d("Resultado",data.toString());
	
		return "Exito";
		} catch (Exception e) {
		   	// TODO: handle exception
			Log.d("ErrorGson",e.getMessage());
		}
		return null;
	}

	private InputStream RecuperarString(String url) {
		DefaultHttpClient client = new DefaultHttpClient();

		HttpGet getRequest = new HttpGet(url);

		try {

			HttpResponse getResponse = client.execute(getRequest);

			final int statusCode = getResponse.getStatusLine().getStatusCode();

	
			HttpEntity getResponseEntity = getResponse.getEntity();

			return getResponseEntity.getContent();

		}

		catch (IOException e) {

			getRequest.abort();

			Log.w(getClass().getSimpleName(), "Error for URL " + url, e);

		}

		return null;

	}

}
