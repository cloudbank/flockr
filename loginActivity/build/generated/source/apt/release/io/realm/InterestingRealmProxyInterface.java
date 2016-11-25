package io.realm;


public interface InterestingRealmProxyInterface {
    public String realmGet$id();
    public void realmSet$id(String value);
    public java.util.Date realmGet$timestamp();
    public void realmSet$timestamp(java.util.Date value);
    public RealmList<com.anubis.phlix.models.Photo> realmGet$interestingPhotos();
    public void realmSet$interestingPhotos(RealmList<com.anubis.phlix.models.Photo> value);
}
