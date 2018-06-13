package shekar.com.snakeview.data.services;

import retrofit2.http.GET;
import rx.Observable;
import shekar.com.snakeview.BuildConfig;
import shekar.com.snakeview.data.model.ImageModel;

public interface ApiService {
    @GET("m4hue") Observable<ImageModel> getImages();
    @GET(BuildConfig.ASSET_URL) Observable<ImageModel> getImagesFromAssest();
}