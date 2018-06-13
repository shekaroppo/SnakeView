package shekar.com.snakeview.data.services;

import android.content.Context;
import java.io.IOException;
import java.io.InputStream;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;
import shekar.com.snakeview.BuildConfig;

public class MockClient implements Interceptor {

  Context context;

  public MockClient(Context context) {
    this.context = context;
  }

  @Override public Response intercept(Chain chain) throws IOException {

    HttpUrl url = chain.request().url();
    final String endpoint = url.encodedPath().substring(url.encodedPath().lastIndexOf('/') + 1);
    switch (endpoint) {
      case BuildConfig.ASSET_URL:
        String response = loadJSONFromAsset();
        return new Response.Builder().code(200)
            .message(response)
            .request(chain.request())
            .protocol(Protocol.HTTP_1_1)
            .body(ResponseBody.create(MediaType.parse("application/json"), response.getBytes()))
            .addHeader("content-type", "application/json")
            .build();
      default:
        return chain.proceed(chain.request());
    }
  }

  public String loadJSONFromAsset() {
    String json = null;
    try {
      InputStream is = context.getAssets().open("images.json");
      int size = is.available();
      byte[] buffer = new byte[size];
      is.read(buffer);
      is.close();
      json = new String(buffer, "UTF-8");
    } catch (IOException ex) {
      ex.printStackTrace();
      return null;
    }
    return json;
  }
}