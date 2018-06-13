package shekar.com.snakeview.injection.module;

import android.content.Context;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import shekar.com.snakeview.BuildConfig;
import shekar.com.snakeview.data.DataManager;
import shekar.com.snakeview.data.services.ApiService;
import shekar.com.snakeview.data.services.MockClient;
import shekar.com.snakeview.injection.scope.ApplicationContext;
import shekar.com.snakeview.utils.NetworkUtils;

@Module public class NetworkModule {

  @Provides @Singleton OkHttpClient provideOkHttpClient(MockClient mockClient) {
    OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder();
    if (BuildConfig.DEBUG) {
      HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
      httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
      httpBuilder.interceptors().add(httpLoggingInterceptor);
    }
    httpBuilder.interceptors().add(mockClient);
    return httpBuilder.build();
  }

  @Provides @Singleton Retrofit provideRestAdapter(OkHttpClient okHttpClient) {
    return new Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .client(okHttpClient)
        .build();
  }

  @Provides @Singleton MockClient provideMockClient(@ApplicationContext Context context) {
    return new MockClient(context);
  }

  @Provides @Singleton ApiService provideApiService(Retrofit restAdapter) {
    return restAdapter.create(ApiService.class);
  }


  @Provides @Singleton NetworkUtils provideNetworkUtils(@ApplicationContext Context context) {
    return new NetworkUtils(context);
  }

  @Provides @Singleton DataManager getDataManager(@ApplicationContext Context context, ApiService apiService) {
    return new DataManager(apiService);
  }
}
