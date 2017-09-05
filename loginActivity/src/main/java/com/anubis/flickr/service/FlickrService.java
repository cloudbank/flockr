package com.anubis.flickr.service;

import com.anubis.flickr.models.Comment;
import com.anubis.flickr.models.Comments;
import com.anubis.flickr.models.Hottags;
import com.anubis.flickr.models.PhotoInfo;
import com.anubis.flickr.models.Photos;
import com.anubis.flickr.models.Who;
import com.anubis.oauthkit.BuildConfig;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by sabine on 9/18/16.
 */

public interface FlickrService {


    String API_BASE_URL = "https://api.flickr.com/services/rest/";

    String KEY = "api_key="+BuildConfig.consumerKey;



    @GET(API_BASE_URL + "?method=flickr.photos.getContactsPublicPhotos&per_page=500&format=json&nojsoncallback=1&"+ KEY +"&just_friends=1&extras=date_taken,owner_name,url_s,tags&count=50&include_self=1")
    Observable<Photos> getFriendsPhotos(@Query("user_id") String userId);

    @GET(API_BASE_URL + "?method=flickr.people.getPhotos&format=json&nojsoncallback=1&"+ KEY +"&extras=date_taken,owner_name&count=50")
    Observable<Photos> getMyPhotos(@Query("user_id") String userId);

    @GET(API_BASE_URL + "?method=flickr.interestingness.getList&per_page=50&format=json&nojsoncallback=1&"+ KEY +"&extras=date_taken,owner_name,url_s,tags")
    Observable<Photos> getInterestingPhotos(@Query("page") String page);

    @GET(API_BASE_URL + "?method=flickr.photos.comments.getList&format=json&nojsoncallback=1&"+ KEY )
    Observable<Comments> getComments(@Query("photo_id") String photoId);

    @GET(API_BASE_URL + "?method=flickr.photos.getInfo&format=json&nojsoncallback=1&"+ KEY )
    Observable<PhotoInfo> getPhotoInfo(@Query("photo_id") String photoId);

    @POST(API_BASE_URL + "?method=flickr.photos.comments.addComment&format=json&nojsoncallback=1&"+ KEY )
    Observable<Comment> addComment(@QueryMap Map<String, String> options);


    @GET(API_BASE_URL + "?method=flickr.tags.getHotList&format=json&nojsoncallback=1&"+ KEY )
    Observable<Hottags> getHotTags();

    @GET(API_BASE_URL + "?method=flickr.photos.search&per_page=500&extras=date_taken,owner_name,tags,description&format=json&nojsoncallback=1&"+ KEY )
    Observable<Photos> search(@QueryMap Map<String, String> options);

    @GET(API_BASE_URL + "?method=flickr.photos.search&per_page=500&extras=date_taken,owner_name,tags,description,url_s&format=json&nojsoncallback=1&"+ KEY +"&is_commons=true&view_all=1")
    Observable<Photos> commons(@Query("page") String page);

    @GET(API_BASE_URL + "?method=flickr.photos.getRecent&format=json&nojsoncallback=1&"+ KEY +"&extras=date_taken,owner_name,tags&per_page=500")
    Observable<Photos> getRecentPhotos();

    @GET(API_BASE_URL + "?method=flickr.tags.getListUser&format=json&nojsoncallback=1&"+ KEY )
    Observable<Who> getTags(@Query("user_id") String uid);


    @GET(API_BASE_URL + "?method=flickr.photos.search&text='hokusai+or+koson+or+hiroshige'&safe_search='1'&format=json&nojsoncallback=1&"+ KEY )
    Observable<Photos> getPaintingsPhotos();


    @Multipart
    @POST("https://up.flickr.com/services/upload?format=json&nojsoncallback=1&"+ KEY )
    Observable<ResponseBody> postPhoto(@Part MultipartBody.Part filePart);





}

