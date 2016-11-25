package io.realm;


public interface RecentRealmProxyInterface {
    public String realmGet$id();
    public void realmSet$id(String value);
    public java.util.Date realmGet$timestamp();
    public void realmSet$timestamp(java.util.Date value);
    public RealmList<com.anubis.phlix.models.Photo> realmGet$recentPhotos();
    public void realmSet$recentPhotos(RealmList<com.anubis.phlix.models.Photo> value);
    public RealmList<com.anubis.phlix.models.Tag> realmGet$hotTagList();
    public void realmSet$hotTagList(RealmList<com.anubis.phlix.models.Tag> value);
}
