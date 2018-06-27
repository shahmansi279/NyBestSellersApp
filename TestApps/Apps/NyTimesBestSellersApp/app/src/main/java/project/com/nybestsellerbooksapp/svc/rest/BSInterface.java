package project.com.nybestsellerbooksapp.svc.rest;

import io.reactivex.Observable;
import project.com.nybestsellerbooksapp.svc.model.BSBookList;
import project.com.nybestsellerbooksapp.svc.model.BSHistoryList;
import project.com.nybestsellerbooksapp.svc.model.BSList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface BSInterface {

    @GET("/svc/books/v3/lists/names.{format}")
    Call<BSList> getBSListItems(@Path("format") String format, @Query("api_key") String apiKey) ;

    @GET("/svc/books/v3/lists.{format}")
    Call<BSBookList> getBSBookListItems(@Path("format") String format, @Query("api_key") String apiKey,
                                        @Query("list") String list) ;

    //Trying to do history page
    @GET("/svc/books/v3/lists.{format}")
    Call<BSBookList> getBSBookItem(@Path("format") String format, @Query("api_key") String apiKey,
                                        @Query("isbn") String isbn, @Query("rank") Integer rank, @Query("list") String listName, @Query("published_date") String publishedDate) ;


    @GET("/svc/books/v3/lists/best-sellers/history.json")
    Call<BSHistoryList> getBSHistory( @Query("api_key") String apiKey, @Query("offset") int offset);

    @GET("/svc/books/v3/lists/best-sellers/history.json")
    Observable<BSHistoryList> getBSHistoryByAuthor( @Query("api_key") String apiKey, @Query("author") String author, @Query("offset") int offset);

    @GET("/svc/books/v3/lists/best-sellers/history.json")
    Observable<BSHistoryList> getBSHistoryByTitle( @Query("api_key") String apiKey, @Query("title") String title, @Query("offset") int offset);


}
