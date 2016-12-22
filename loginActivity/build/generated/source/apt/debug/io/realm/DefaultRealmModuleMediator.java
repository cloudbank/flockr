package io.realm;


import android.util.JsonReader;
import io.realm.RealmObjectSchema;
import io.realm.internal.ColumnInfo;
import io.realm.internal.RealmObjectProxy;
import io.realm.internal.RealmProxyMediator;
import io.realm.internal.Row;
import io.realm.internal.SharedRealm;
import io.realm.internal.Table;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

@io.realm.annotations.RealmModule
class DefaultRealmModuleMediator extends RealmProxyMediator {

    private static final Set<Class<? extends RealmModel>> MODEL_CLASSES;
    static {
        Set<Class<? extends RealmModel>> modelClasses = new HashSet<Class<? extends RealmModel>>();
        modelClasses.add(com.anubis.phlix.models.Comment.class);
        modelClasses.add(com.anubis.phlix.models.Recent.class);
        modelClasses.add(com.anubis.phlix.models.Photo.class);
        modelClasses.add(com.anubis.phlix.models.Comments_.class);
        modelClasses.add(com.anubis.phlix.models.UserModel.class);
        modelClasses.add(com.anubis.phlix.models.Common.class);
        modelClasses.add(com.anubis.phlix.models.Interesting.class);
        modelClasses.add(com.anubis.phlix.models.Tag.class);
        MODEL_CLASSES = Collections.unmodifiableSet(modelClasses);
    }

    @Override
    public Table createTable(Class<? extends RealmModel> clazz, SharedRealm sharedRealm) {
        checkClass(clazz);

        if (clazz.equals(com.anubis.phlix.models.Comment.class)) {
            return io.realm.CommentRealmProxy.initTable(sharedRealm);
        } else if (clazz.equals(com.anubis.phlix.models.Recent.class)) {
            return io.realm.RecentRealmProxy.initTable(sharedRealm);
        } else if (clazz.equals(com.anubis.phlix.models.Photo.class)) {
            return io.realm.PhotoRealmProxy.initTable(sharedRealm);
        } else if (clazz.equals(com.anubis.phlix.models.Comments_.class)) {
            return io.realm.Comments_RealmProxy.initTable(sharedRealm);
        } else if (clazz.equals(com.anubis.phlix.models.UserModel.class)) {
            return io.realm.UserModelRealmProxy.initTable(sharedRealm);
        } else if (clazz.equals(com.anubis.phlix.models.Common.class)) {
            return io.realm.CommonRealmProxy.initTable(sharedRealm);
        } else if (clazz.equals(com.anubis.phlix.models.Interesting.class)) {
            return io.realm.InterestingRealmProxy.initTable(sharedRealm);
        } else if (clazz.equals(com.anubis.phlix.models.Tag.class)) {
            return io.realm.TagRealmProxy.initTable(sharedRealm);
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

    @Override
    public RealmObjectSchema createRealmObjectSchema(Class<? extends RealmModel> clazz, RealmSchema realmSchema) {
        checkClass(clazz);

        if (clazz.equals(com.anubis.phlix.models.Comment.class)) {
            return io.realm.CommentRealmProxy.createRealmObjectSchema(realmSchema);
        } else if (clazz.equals(com.anubis.phlix.models.Recent.class)) {
            return io.realm.RecentRealmProxy.createRealmObjectSchema(realmSchema);
        } else if (clazz.equals(com.anubis.phlix.models.Photo.class)) {
            return io.realm.PhotoRealmProxy.createRealmObjectSchema(realmSchema);
        } else if (clazz.equals(com.anubis.phlix.models.Comments_.class)) {
            return io.realm.Comments_RealmProxy.createRealmObjectSchema(realmSchema);
        } else if (clazz.equals(com.anubis.phlix.models.UserModel.class)) {
            return io.realm.UserModelRealmProxy.createRealmObjectSchema(realmSchema);
        } else if (clazz.equals(com.anubis.phlix.models.Common.class)) {
            return io.realm.CommonRealmProxy.createRealmObjectSchema(realmSchema);
        } else if (clazz.equals(com.anubis.phlix.models.Interesting.class)) {
            return io.realm.InterestingRealmProxy.createRealmObjectSchema(realmSchema);
        } else if (clazz.equals(com.anubis.phlix.models.Tag.class)) {
            return io.realm.TagRealmProxy.createRealmObjectSchema(realmSchema);
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

    @Override
    public ColumnInfo validateTable(Class<? extends RealmModel> clazz, SharedRealm sharedRealm, boolean allowExtraColumns) {
        checkClass(clazz);

        if (clazz.equals(com.anubis.phlix.models.Comment.class)) {
            return io.realm.CommentRealmProxy.validateTable(sharedRealm, allowExtraColumns);
        } else if (clazz.equals(com.anubis.phlix.models.Recent.class)) {
            return io.realm.RecentRealmProxy.validateTable(sharedRealm, allowExtraColumns);
        } else if (clazz.equals(com.anubis.phlix.models.Photo.class)) {
            return io.realm.PhotoRealmProxy.validateTable(sharedRealm, allowExtraColumns);
        } else if (clazz.equals(com.anubis.phlix.models.Comments_.class)) {
            return io.realm.Comments_RealmProxy.validateTable(sharedRealm, allowExtraColumns);
        } else if (clazz.equals(com.anubis.phlix.models.UserModel.class)) {
            return io.realm.UserModelRealmProxy.validateTable(sharedRealm, allowExtraColumns);
        } else if (clazz.equals(com.anubis.phlix.models.Common.class)) {
            return io.realm.CommonRealmProxy.validateTable(sharedRealm, allowExtraColumns);
        } else if (clazz.equals(com.anubis.phlix.models.Interesting.class)) {
            return io.realm.InterestingRealmProxy.validateTable(sharedRealm, allowExtraColumns);
        } else if (clazz.equals(com.anubis.phlix.models.Tag.class)) {
            return io.realm.TagRealmProxy.validateTable(sharedRealm, allowExtraColumns);
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

    @Override
    public List<String> getFieldNames(Class<? extends RealmModel> clazz) {
        checkClass(clazz);

        if (clazz.equals(com.anubis.phlix.models.Comment.class)) {
            return io.realm.CommentRealmProxy.getFieldNames();
        } else if (clazz.equals(com.anubis.phlix.models.Recent.class)) {
            return io.realm.RecentRealmProxy.getFieldNames();
        } else if (clazz.equals(com.anubis.phlix.models.Photo.class)) {
            return io.realm.PhotoRealmProxy.getFieldNames();
        } else if (clazz.equals(com.anubis.phlix.models.Comments_.class)) {
            return io.realm.Comments_RealmProxy.getFieldNames();
        } else if (clazz.equals(com.anubis.phlix.models.UserModel.class)) {
            return io.realm.UserModelRealmProxy.getFieldNames();
        } else if (clazz.equals(com.anubis.phlix.models.Common.class)) {
            return io.realm.CommonRealmProxy.getFieldNames();
        } else if (clazz.equals(com.anubis.phlix.models.Interesting.class)) {
            return io.realm.InterestingRealmProxy.getFieldNames();
        } else if (clazz.equals(com.anubis.phlix.models.Tag.class)) {
            return io.realm.TagRealmProxy.getFieldNames();
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

    @Override
    public String getTableName(Class<? extends RealmModel> clazz) {
        checkClass(clazz);

        if (clazz.equals(com.anubis.phlix.models.Comment.class)) {
            return io.realm.CommentRealmProxy.getTableName();
        } else if (clazz.equals(com.anubis.phlix.models.Recent.class)) {
            return io.realm.RecentRealmProxy.getTableName();
        } else if (clazz.equals(com.anubis.phlix.models.Photo.class)) {
            return io.realm.PhotoRealmProxy.getTableName();
        } else if (clazz.equals(com.anubis.phlix.models.Comments_.class)) {
            return io.realm.Comments_RealmProxy.getTableName();
        } else if (clazz.equals(com.anubis.phlix.models.UserModel.class)) {
            return io.realm.UserModelRealmProxy.getTableName();
        } else if (clazz.equals(com.anubis.phlix.models.Common.class)) {
            return io.realm.CommonRealmProxy.getTableName();
        } else if (clazz.equals(com.anubis.phlix.models.Interesting.class)) {
            return io.realm.InterestingRealmProxy.getTableName();
        } else if (clazz.equals(com.anubis.phlix.models.Tag.class)) {
            return io.realm.TagRealmProxy.getTableName();
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

    @Override
    public <E extends RealmModel> E newInstance(Class<E> clazz, Object baseRealm, Row row, ColumnInfo columnInfo, boolean acceptDefaultValue, List<String> excludeFields) {
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        try {
            objectContext.set((BaseRealm) baseRealm, row, columnInfo, acceptDefaultValue, excludeFields);
            checkClass(clazz);

            if (clazz.equals(com.anubis.phlix.models.Comment.class)) {
                return clazz.cast(new io.realm.CommentRealmProxy());
            } else if (clazz.equals(com.anubis.phlix.models.Recent.class)) {
                return clazz.cast(new io.realm.RecentRealmProxy());
            } else if (clazz.equals(com.anubis.phlix.models.Photo.class)) {
                return clazz.cast(new io.realm.PhotoRealmProxy());
            } else if (clazz.equals(com.anubis.phlix.models.Comments_.class)) {
                return clazz.cast(new io.realm.Comments_RealmProxy());
            } else if (clazz.equals(com.anubis.phlix.models.UserModel.class)) {
                return clazz.cast(new io.realm.UserModelRealmProxy());
            } else if (clazz.equals(com.anubis.phlix.models.Common.class)) {
                return clazz.cast(new io.realm.CommonRealmProxy());
            } else if (clazz.equals(com.anubis.phlix.models.Interesting.class)) {
                return clazz.cast(new io.realm.InterestingRealmProxy());
            } else if (clazz.equals(com.anubis.phlix.models.Tag.class)) {
                return clazz.cast(new io.realm.TagRealmProxy());
            } else {
                throw getMissingProxyClassException(clazz);
            }
        } finally {
            objectContext.clear();
        }
    }

    @Override
    public Set<Class<? extends RealmModel>> getModelClasses() {
        return MODEL_CLASSES;
    }

    @Override
    public <E extends RealmModel> E copyOrUpdate(Realm realm, E obj, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
        // This cast is correct because obj is either
        // generated by RealmProxy or the original type extending directly from RealmObject
        @SuppressWarnings("unchecked") Class<E> clazz = (Class<E>) ((obj instanceof RealmObjectProxy) ? obj.getClass().getSuperclass() : obj.getClass());

        if (clazz.equals(com.anubis.phlix.models.Comment.class)) {
            return clazz.cast(io.realm.CommentRealmProxy.copyOrUpdate(realm, (com.anubis.phlix.models.Comment) obj, update, cache));
        } else if (clazz.equals(com.anubis.phlix.models.Recent.class)) {
            return clazz.cast(io.realm.RecentRealmProxy.copyOrUpdate(realm, (com.anubis.phlix.models.Recent) obj, update, cache));
        } else if (clazz.equals(com.anubis.phlix.models.Photo.class)) {
            return clazz.cast(io.realm.PhotoRealmProxy.copyOrUpdate(realm, (com.anubis.phlix.models.Photo) obj, update, cache));
        } else if (clazz.equals(com.anubis.phlix.models.Comments_.class)) {
            return clazz.cast(io.realm.Comments_RealmProxy.copyOrUpdate(realm, (com.anubis.phlix.models.Comments_) obj, update, cache));
        } else if (clazz.equals(com.anubis.phlix.models.UserModel.class)) {
            return clazz.cast(io.realm.UserModelRealmProxy.copyOrUpdate(realm, (com.anubis.phlix.models.UserModel) obj, update, cache));
        } else if (clazz.equals(com.anubis.phlix.models.Common.class)) {
            return clazz.cast(io.realm.CommonRealmProxy.copyOrUpdate(realm, (com.anubis.phlix.models.Common) obj, update, cache));
        } else if (clazz.equals(com.anubis.phlix.models.Interesting.class)) {
            return clazz.cast(io.realm.InterestingRealmProxy.copyOrUpdate(realm, (com.anubis.phlix.models.Interesting) obj, update, cache));
        } else if (clazz.equals(com.anubis.phlix.models.Tag.class)) {
            return clazz.cast(io.realm.TagRealmProxy.copyOrUpdate(realm, (com.anubis.phlix.models.Tag) obj, update, cache));
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

    @Override
    public void insert(Realm realm, RealmModel object, Map<RealmModel, Long> cache) {
        // This cast is correct because obj is either
        // generated by RealmProxy or the original type extending directly from RealmObject
        @SuppressWarnings("unchecked") Class<RealmModel> clazz = (Class<RealmModel>) ((object instanceof RealmObjectProxy) ? object.getClass().getSuperclass() : object.getClass());

        if (clazz.equals(com.anubis.phlix.models.Comment.class)) {
            io.realm.CommentRealmProxy.insert(realm, (com.anubis.phlix.models.Comment) object, cache);
        } else if (clazz.equals(com.anubis.phlix.models.Recent.class)) {
            io.realm.RecentRealmProxy.insert(realm, (com.anubis.phlix.models.Recent) object, cache);
        } else if (clazz.equals(com.anubis.phlix.models.Photo.class)) {
            io.realm.PhotoRealmProxy.insert(realm, (com.anubis.phlix.models.Photo) object, cache);
        } else if (clazz.equals(com.anubis.phlix.models.Comments_.class)) {
            io.realm.Comments_RealmProxy.insert(realm, (com.anubis.phlix.models.Comments_) object, cache);
        } else if (clazz.equals(com.anubis.phlix.models.UserModel.class)) {
            io.realm.UserModelRealmProxy.insert(realm, (com.anubis.phlix.models.UserModel) object, cache);
        } else if (clazz.equals(com.anubis.phlix.models.Common.class)) {
            io.realm.CommonRealmProxy.insert(realm, (com.anubis.phlix.models.Common) object, cache);
        } else if (clazz.equals(com.anubis.phlix.models.Interesting.class)) {
            io.realm.InterestingRealmProxy.insert(realm, (com.anubis.phlix.models.Interesting) object, cache);
        } else if (clazz.equals(com.anubis.phlix.models.Tag.class)) {
            io.realm.TagRealmProxy.insert(realm, (com.anubis.phlix.models.Tag) object, cache);
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

    @Override
    public void insert(Realm realm, Collection<? extends RealmModel> objects) {
        Iterator<? extends RealmModel> iterator = objects.iterator();
        RealmModel object = null;
        Map<RealmModel, Long> cache = new IdentityHashMap<RealmModel, Long>(objects.size());
        if (iterator.hasNext()) {
            //  access the first element to figure out the clazz for the routing below
            object = iterator.next();
            // This cast is correct because obj is either
            // generated by RealmProxy or the original type extending directly from RealmObject
            @SuppressWarnings("unchecked") Class<RealmModel> clazz = (Class<RealmModel>) ((object instanceof RealmObjectProxy) ? object.getClass().getSuperclass() : object.getClass());

            if (clazz.equals(com.anubis.phlix.models.Comment.class)) {
                io.realm.CommentRealmProxy.insert(realm, (com.anubis.phlix.models.Comment) object, cache);
            } else if (clazz.equals(com.anubis.phlix.models.Recent.class)) {
                io.realm.RecentRealmProxy.insert(realm, (com.anubis.phlix.models.Recent) object, cache);
            } else if (clazz.equals(com.anubis.phlix.models.Photo.class)) {
                io.realm.PhotoRealmProxy.insert(realm, (com.anubis.phlix.models.Photo) object, cache);
            } else if (clazz.equals(com.anubis.phlix.models.Comments_.class)) {
                io.realm.Comments_RealmProxy.insert(realm, (com.anubis.phlix.models.Comments_) object, cache);
            } else if (clazz.equals(com.anubis.phlix.models.UserModel.class)) {
                io.realm.UserModelRealmProxy.insert(realm, (com.anubis.phlix.models.UserModel) object, cache);
            } else if (clazz.equals(com.anubis.phlix.models.Common.class)) {
                io.realm.CommonRealmProxy.insert(realm, (com.anubis.phlix.models.Common) object, cache);
            } else if (clazz.equals(com.anubis.phlix.models.Interesting.class)) {
                io.realm.InterestingRealmProxy.insert(realm, (com.anubis.phlix.models.Interesting) object, cache);
            } else if (clazz.equals(com.anubis.phlix.models.Tag.class)) {
                io.realm.TagRealmProxy.insert(realm, (com.anubis.phlix.models.Tag) object, cache);
            } else {
                throw getMissingProxyClassException(clazz);
            }
            if (iterator.hasNext()) {
                if (clazz.equals(com.anubis.phlix.models.Comment.class)) {
                    io.realm.CommentRealmProxy.insert(realm, iterator, cache);
                } else if (clazz.equals(com.anubis.phlix.models.Recent.class)) {
                    io.realm.RecentRealmProxy.insert(realm, iterator, cache);
                } else if (clazz.equals(com.anubis.phlix.models.Photo.class)) {
                    io.realm.PhotoRealmProxy.insert(realm, iterator, cache);
                } else if (clazz.equals(com.anubis.phlix.models.Comments_.class)) {
                    io.realm.Comments_RealmProxy.insert(realm, iterator, cache);
                } else if (clazz.equals(com.anubis.phlix.models.UserModel.class)) {
                    io.realm.UserModelRealmProxy.insert(realm, iterator, cache);
                } else if (clazz.equals(com.anubis.phlix.models.Common.class)) {
                    io.realm.CommonRealmProxy.insert(realm, iterator, cache);
                } else if (clazz.equals(com.anubis.phlix.models.Interesting.class)) {
                    io.realm.InterestingRealmProxy.insert(realm, iterator, cache);
                } else if (clazz.equals(com.anubis.phlix.models.Tag.class)) {
                    io.realm.TagRealmProxy.insert(realm, iterator, cache);
                } else {
                    throw getMissingProxyClassException(clazz);
                }
            }
        }
    }

    @Override
    public void insertOrUpdate(Realm realm, RealmModel obj, Map<RealmModel, Long> cache) {
        // This cast is correct because obj is either
        // generated by RealmProxy or the original type extending directly from RealmObject
        @SuppressWarnings("unchecked") Class<RealmModel> clazz = (Class<RealmModel>) ((obj instanceof RealmObjectProxy) ? obj.getClass().getSuperclass() : obj.getClass());

        if (clazz.equals(com.anubis.phlix.models.Comment.class)) {
            io.realm.CommentRealmProxy.insertOrUpdate(realm, (com.anubis.phlix.models.Comment) obj, cache);
        } else if (clazz.equals(com.anubis.phlix.models.Recent.class)) {
            io.realm.RecentRealmProxy.insertOrUpdate(realm, (com.anubis.phlix.models.Recent) obj, cache);
        } else if (clazz.equals(com.anubis.phlix.models.Photo.class)) {
            io.realm.PhotoRealmProxy.insertOrUpdate(realm, (com.anubis.phlix.models.Photo) obj, cache);
        } else if (clazz.equals(com.anubis.phlix.models.Comments_.class)) {
            io.realm.Comments_RealmProxy.insertOrUpdate(realm, (com.anubis.phlix.models.Comments_) obj, cache);
        } else if (clazz.equals(com.anubis.phlix.models.UserModel.class)) {
            io.realm.UserModelRealmProxy.insertOrUpdate(realm, (com.anubis.phlix.models.UserModel) obj, cache);
        } else if (clazz.equals(com.anubis.phlix.models.Common.class)) {
            io.realm.CommonRealmProxy.insertOrUpdate(realm, (com.anubis.phlix.models.Common) obj, cache);
        } else if (clazz.equals(com.anubis.phlix.models.Interesting.class)) {
            io.realm.InterestingRealmProxy.insertOrUpdate(realm, (com.anubis.phlix.models.Interesting) obj, cache);
        } else if (clazz.equals(com.anubis.phlix.models.Tag.class)) {
            io.realm.TagRealmProxy.insertOrUpdate(realm, (com.anubis.phlix.models.Tag) obj, cache);
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

    @Override
    public void insertOrUpdate(Realm realm, Collection<? extends RealmModel> objects) {
        Iterator<? extends RealmModel> iterator = objects.iterator();
        RealmModel object = null;
        Map<RealmModel, Long> cache = new IdentityHashMap<RealmModel, Long>(objects.size());
        if (iterator.hasNext()) {
            //  access the first element to figure out the clazz for the routing below
            object = iterator.next();
            // This cast is correct because obj is either
            // generated by RealmProxy or the original type extending directly from RealmObject
            @SuppressWarnings("unchecked") Class<RealmModel> clazz = (Class<RealmModel>) ((object instanceof RealmObjectProxy) ? object.getClass().getSuperclass() : object.getClass());

            if (clazz.equals(com.anubis.phlix.models.Comment.class)) {
                io.realm.CommentRealmProxy.insertOrUpdate(realm, (com.anubis.phlix.models.Comment) object, cache);
            } else if (clazz.equals(com.anubis.phlix.models.Recent.class)) {
                io.realm.RecentRealmProxy.insertOrUpdate(realm, (com.anubis.phlix.models.Recent) object, cache);
            } else if (clazz.equals(com.anubis.phlix.models.Photo.class)) {
                io.realm.PhotoRealmProxy.insertOrUpdate(realm, (com.anubis.phlix.models.Photo) object, cache);
            } else if (clazz.equals(com.anubis.phlix.models.Comments_.class)) {
                io.realm.Comments_RealmProxy.insertOrUpdate(realm, (com.anubis.phlix.models.Comments_) object, cache);
            } else if (clazz.equals(com.anubis.phlix.models.UserModel.class)) {
                io.realm.UserModelRealmProxy.insertOrUpdate(realm, (com.anubis.phlix.models.UserModel) object, cache);
            } else if (clazz.equals(com.anubis.phlix.models.Common.class)) {
                io.realm.CommonRealmProxy.insertOrUpdate(realm, (com.anubis.phlix.models.Common) object, cache);
            } else if (clazz.equals(com.anubis.phlix.models.Interesting.class)) {
                io.realm.InterestingRealmProxy.insertOrUpdate(realm, (com.anubis.phlix.models.Interesting) object, cache);
            } else if (clazz.equals(com.anubis.phlix.models.Tag.class)) {
                io.realm.TagRealmProxy.insertOrUpdate(realm, (com.anubis.phlix.models.Tag) object, cache);
            } else {
                throw getMissingProxyClassException(clazz);
            }
            if (iterator.hasNext()) {
                if (clazz.equals(com.anubis.phlix.models.Comment.class)) {
                    io.realm.CommentRealmProxy.insertOrUpdate(realm, iterator, cache);
                } else if (clazz.equals(com.anubis.phlix.models.Recent.class)) {
                    io.realm.RecentRealmProxy.insertOrUpdate(realm, iterator, cache);
                } else if (clazz.equals(com.anubis.phlix.models.Photo.class)) {
                    io.realm.PhotoRealmProxy.insertOrUpdate(realm, iterator, cache);
                } else if (clazz.equals(com.anubis.phlix.models.Comments_.class)) {
                    io.realm.Comments_RealmProxy.insertOrUpdate(realm, iterator, cache);
                } else if (clazz.equals(com.anubis.phlix.models.UserModel.class)) {
                    io.realm.UserModelRealmProxy.insertOrUpdate(realm, iterator, cache);
                } else if (clazz.equals(com.anubis.phlix.models.Common.class)) {
                    io.realm.CommonRealmProxy.insertOrUpdate(realm, iterator, cache);
                } else if (clazz.equals(com.anubis.phlix.models.Interesting.class)) {
                    io.realm.InterestingRealmProxy.insertOrUpdate(realm, iterator, cache);
                } else if (clazz.equals(com.anubis.phlix.models.Tag.class)) {
                    io.realm.TagRealmProxy.insertOrUpdate(realm, iterator, cache);
                } else {
                    throw getMissingProxyClassException(clazz);
                }
            }
        }
    }

    @Override
    public <E extends RealmModel> E createOrUpdateUsingJsonObject(Class<E> clazz, Realm realm, JSONObject json, boolean update)
        throws JSONException {
        checkClass(clazz);

        if (clazz.equals(com.anubis.phlix.models.Comment.class)) {
            return clazz.cast(io.realm.CommentRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        } else if (clazz.equals(com.anubis.phlix.models.Recent.class)) {
            return clazz.cast(io.realm.RecentRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        } else if (clazz.equals(com.anubis.phlix.models.Photo.class)) {
            return clazz.cast(io.realm.PhotoRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        } else if (clazz.equals(com.anubis.phlix.models.Comments_.class)) {
            return clazz.cast(io.realm.Comments_RealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        } else if (clazz.equals(com.anubis.phlix.models.UserModel.class)) {
            return clazz.cast(io.realm.UserModelRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        } else if (clazz.equals(com.anubis.phlix.models.Common.class)) {
            return clazz.cast(io.realm.CommonRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        } else if (clazz.equals(com.anubis.phlix.models.Interesting.class)) {
            return clazz.cast(io.realm.InterestingRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        } else if (clazz.equals(com.anubis.phlix.models.Tag.class)) {
            return clazz.cast(io.realm.TagRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

    @Override
    public <E extends RealmModel> E createUsingJsonStream(Class<E> clazz, Realm realm, JsonReader reader)
        throws IOException {
        checkClass(clazz);

        if (clazz.equals(com.anubis.phlix.models.Comment.class)) {
            return clazz.cast(io.realm.CommentRealmProxy.createUsingJsonStream(realm, reader));
        } else if (clazz.equals(com.anubis.phlix.models.Recent.class)) {
            return clazz.cast(io.realm.RecentRealmProxy.createUsingJsonStream(realm, reader));
        } else if (clazz.equals(com.anubis.phlix.models.Photo.class)) {
            return clazz.cast(io.realm.PhotoRealmProxy.createUsingJsonStream(realm, reader));
        } else if (clazz.equals(com.anubis.phlix.models.Comments_.class)) {
            return clazz.cast(io.realm.Comments_RealmProxy.createUsingJsonStream(realm, reader));
        } else if (clazz.equals(com.anubis.phlix.models.UserModel.class)) {
            return clazz.cast(io.realm.UserModelRealmProxy.createUsingJsonStream(realm, reader));
        } else if (clazz.equals(com.anubis.phlix.models.Common.class)) {
            return clazz.cast(io.realm.CommonRealmProxy.createUsingJsonStream(realm, reader));
        } else if (clazz.equals(com.anubis.phlix.models.Interesting.class)) {
            return clazz.cast(io.realm.InterestingRealmProxy.createUsingJsonStream(realm, reader));
        } else if (clazz.equals(com.anubis.phlix.models.Tag.class)) {
            return clazz.cast(io.realm.TagRealmProxy.createUsingJsonStream(realm, reader));
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

    @Override
    public <E extends RealmModel> E createDetachedCopy(E realmObject, int maxDepth, Map<RealmModel, RealmObjectProxy.CacheData<RealmModel>> cache) {
        // This cast is correct because obj is either
        // generated by RealmProxy or the original type extending directly from RealmObject
        @SuppressWarnings("unchecked") Class<E> clazz = (Class<E>) realmObject.getClass().getSuperclass();

        if (clazz.equals(com.anubis.phlix.models.Comment.class)) {
            return clazz.cast(io.realm.CommentRealmProxy.createDetachedCopy((com.anubis.phlix.models.Comment) realmObject, 0, maxDepth, cache));
        } else if (clazz.equals(com.anubis.phlix.models.Recent.class)) {
            return clazz.cast(io.realm.RecentRealmProxy.createDetachedCopy((com.anubis.phlix.models.Recent) realmObject, 0, maxDepth, cache));
        } else if (clazz.equals(com.anubis.phlix.models.Photo.class)) {
            return clazz.cast(io.realm.PhotoRealmProxy.createDetachedCopy((com.anubis.phlix.models.Photo) realmObject, 0, maxDepth, cache));
        } else if (clazz.equals(com.anubis.phlix.models.Comments_.class)) {
            return clazz.cast(io.realm.Comments_RealmProxy.createDetachedCopy((com.anubis.phlix.models.Comments_) realmObject, 0, maxDepth, cache));
        } else if (clazz.equals(com.anubis.phlix.models.UserModel.class)) {
            return clazz.cast(io.realm.UserModelRealmProxy.createDetachedCopy((com.anubis.phlix.models.UserModel) realmObject, 0, maxDepth, cache));
        } else if (clazz.equals(com.anubis.phlix.models.Common.class)) {
            return clazz.cast(io.realm.CommonRealmProxy.createDetachedCopy((com.anubis.phlix.models.Common) realmObject, 0, maxDepth, cache));
        } else if (clazz.equals(com.anubis.phlix.models.Interesting.class)) {
            return clazz.cast(io.realm.InterestingRealmProxy.createDetachedCopy((com.anubis.phlix.models.Interesting) realmObject, 0, maxDepth, cache));
        } else if (clazz.equals(com.anubis.phlix.models.Tag.class)) {
            return clazz.cast(io.realm.TagRealmProxy.createDetachedCopy((com.anubis.phlix.models.Tag) realmObject, 0, maxDepth, cache));
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

}
