package shekar.com.snakeview.data;

import rx.Observable;
import shekar.com.snakeview.data.model.ImageModel;
import shekar.com.snakeview.data.services.ApiService;

/**
 * Created by Shekar on 3/3/17.
 */

public class DataManager {
    private ApiService mApiService;

    public DataManager(ApiService apiService) {

        mApiService = apiService;
    }

    public Observable<ImageModel> getImages() {
        return mApiService.getImages();
    }
}
