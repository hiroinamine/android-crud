package br.com.caelum.cadastro.support;

import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 * Created by android5193 on 14/04/15.
 */
public class WebClient {

    private static final String URL = "http://wwww.caelum.com.br/mobile";

    public String post(String json){
        try {
            HttpPost post = new HttpPost(URL);
            post.setEntity(new StringEntity(json));
            post.setHeader("Content-type", "application/json");
            post.setHeader("Accept", "application/json");

            HttpClient client = new DefaultHttpClient();
            HttpResponse response = client.execute(post);

            return EntityUtils.toString(response.getEntity());
        }
        catch (Exception e){
            Log.e("Debug", e.toString(), e);
            return null;
        }
    }

}
