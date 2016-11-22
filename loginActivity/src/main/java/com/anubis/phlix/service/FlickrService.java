package com.anubis.phlix.service;

import com.anubis.phlix.models.Comment;
import com.anubis.phlix.models.Comments;
import com.anubis.phlix.models.Hottags;
import com.anubis.phlix.models.PhotoInfo;
import com.anubis.phlix.models.Photos;
import com.anubis.phlix.models.User;
import com.anubis.phlix.models.Who;

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


    public static final String API_BASE_URL = "https://api.flickr.com/services/rest/";

    public static final String API_CALLBACK_URL = "oauth://cprest";

    @GET(API_BASE_URL + "?method=flickr.test.login&format=json&nojsoncallback=1&api_key=3b9d2687f93eb4b4835a112b41d28db0")
    Observable<User> testLogin();

    @GET(API_BASE_URL + "?method=flickr.photos.getContactsPublicPhotos&per_page=500&format=json&nojsoncallback=1&api_key=3b9d2687f93eb4b4835a112b41d28db0&just_friends=1&extras=date_taken,owner_name,url_s,tags&count=50&include_self=1")
    Observable<Photos> getFriendsPhotos(@Query("user_id") String userId);

    @GET(API_BASE_URL + "?method=flickr.people.getPhotos&format=json&nojsoncallback=1&api_key=3b9d2687f93eb4b4835a112b41d28db0&extras=date_taken,owner_name&count=50")
    Observable<Photos> getMyPhotos(@Query("user_id") String userId);

    @GET(API_BASE_URL + "?method=flickr.interestingness.getList&per_page=500&format=json&nojsoncallback=1&api_key=3b9d2687f93eb4b4835a112b41d28db0&extras=date_taken,owner_name,url_s,tags")
    Observable<Photos> getInterestingPhotos(@Query("page") String page);

    @GET(API_BASE_URL + "?method=flickr.photos.comments.getList&format=json&nojsoncallback=1&api_key=3b9d2687f93eb4b4835a112b41d28db0")
    Observable<Comments> getComments(@Query("photo_id") String photoId);

    @GET(API_BASE_URL + "?method=flickr.photos.getInfo&format=json&nojsoncallback=1&api_key=3b9d2687f93eb4b4835a112b41d28db0")
    Observable<PhotoInfo> getPhotoInfo(@Query("photo_id") String photoId);

    @POST(API_BASE_URL + "?method=flickr.photos.comments.addComment&format=json&nojsoncallback=1&api_key=3b9d2687f93eb4b4835a112b41d28db0")
    Observable<Comment> addComment(@QueryMap Map<String, String> options);


    @GET(API_BASE_URL + "?method=flickr.tags.getHotList&format=json&nojsoncallback=1&api_key=3b9d2687f93eb4b4835a112b41d28db0")
    Observable<Hottags> getHotTags();

    @GET(API_BASE_URL + "?method=flickr.photos.search&per_page=500&extras=date_taken,owner_name,tags,description&format=json&nojsoncallback=1&api_key=3b9d2687f93eb4b4835a112b41d28db0")
    Observable<Photos> search(@QueryMap Map<String, String> options);

    @GET(API_BASE_URL + "?method=flickr.photos.search&per_page=500&extras=date_taken,owner_name,tags,description,url_s&format=json&nojsoncallback=1&api_key=3b9d2687f93eb4b4835a112b41d28db0&is_commons=true&view_all=1")
    Observable<Photos> commons(@Query("page") String page);

    @GET(API_BASE_URL + "?method=flickr.photos.getRecent&format=json&nojsoncallback=1&api_key=3b9d2687f93eb4b4835a112b41d28db0&extras=date_taken,owner_name,tags&per_page=500")
    Observable<Photos> getRecentPhotos();

    @GET(API_BASE_URL + "?method=flickr.tags.getListUser&format=json&nojsoncallback=1&api_key=3b9d2687f93eb4b4835a112b41d28db0")
    Observable<Who> getTags(@Query("user_id") String uid);

    @Multipart
    @POST("https://up.flickr.com/services/upload?format=json&nojsoncallback=1&api_key=3b9d2687f93eb4b4835a112b41d28db0")
    Observable<ResponseBody> postPhoto(@Part MultipartBody.Part filePart);





}

