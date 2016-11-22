
package com.anubis.phlix.models;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "photo_id",
        "comments"
})
public class Comments_ extends RealmObject {
    public Comments_() {
    }

    @PrimaryKey

    @JsonProperty("photo_id")
    private String photoId;
    @JsonProperty("comment")

    public Date timestamp;

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }



    public RealmList<Comment> commentsList;

    public RealmList<Comment> getCommentsList() {
        return commentsList;
    }

    public void setCommentsList(RealmList<Comment> commentsList) {
        this.commentsList = commentsList;
    }

    @Ignore
    private List<Comment> comments = new ArrayList<Comment>();
    @JsonIgnore
    @Ignore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * @return The photoId
     */
    @JsonProperty("photo_id")
    public String getPhotoId() {
        return photoId;
    }

    /**
     * @param photoId The photo_id
     */
    @JsonProperty("photo_id")
    public void setPhotoId(String photoId) {
        this.photoId = photoId;
    }

    /**
     * @return The comments
     */
    @JsonProperty("comment")
    public List<Comment> getComments() {
        return comments;
    }

    /**
     * @param comments The comments
     */
    @JsonProperty("comment")
    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
