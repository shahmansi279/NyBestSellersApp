package project.com.nybestsellerbooksapp.svc.rest;

import project.com.nybestsellerbooksapp.svc.model.GBookDataItems;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by mansshah on 6/24/18.
 */

public interface GBInterface {

    @GET("/books/v1/volumes")
    Call<GBookDataItems> getGBookIem( @Query("key") String apiKey, @Query("q") String query) ;
}
