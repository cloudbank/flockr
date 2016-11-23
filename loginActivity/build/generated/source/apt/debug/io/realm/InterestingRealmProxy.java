package io.realm;


import android.annotation.TargetApi;
import android.os.Build;
import android.util.JsonReader;
import android.util.JsonToken;
import io.realm.RealmObjectSchema;
import io.realm.RealmSchema;
import io.realm.exceptions.RealmMigrationNeededException;
import io.realm.internal.ColumnInfo;
import io.realm.internal.LinkView;
import io.realm.internal.RealmObjectProxy;
import io.realm.internal.Row;
import io.realm.internal.SharedRealm;
import io.realm.internal.Table;
import io.realm.internal.TableOrView;
import io.realm.internal.android.JsonUtils;
import io.realm.log.RealmLog;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class InterestingRealmProxy extends com.anubis.phlix.models.Interesting
    implements RealmObjectProxy, InterestingRealmProxyInterface {

    static final class InterestingColumnInfo extends ColumnInfo
        implements Cloneable {

        public long idIndex;
        public long timestampIndex;
        public long interestingPhotosIndex;

        InterestingColumnInfo(String path, Table table) {
            final Map<String, Long> indicesMap = new HashMap<String, Long>(3);
            this.idIndex = getValidColumnIndex(path, table, "Interesting", "id");
            indicesMap.put("id", this.idIndex);
            this.timestampIndex = getValidColumnIndex(path, table, "Interesting", "timestamp");
            indicesMap.put("timestamp", this.timestampIndex);
            this.interestingPhotosIndex = getValidColumnIndex(path, table, "Interesting", "interestingPhotos");
            indicesMap.put("interestingPhotos", this.interestingPhotosIndex);

            setIndicesMap(indicesMap);
        }

        @Override
        public final void copyColumnInfoFrom(ColumnInfo other) {
            final InterestingColumnInfo otherInfo = (InterestingColumnInfo) other;
            this.idIndex = otherInfo.idIndex;
            this.timestampIndex = otherInfo.timestampIndex;
            this.interestingPhotosIndex = otherInfo.interestingPhotosIndex;

            setIndicesMap(otherInfo.getIndicesMap());
        }

        @Override
        public final InterestingColumnInfo clone() {
            return (InterestingColumnInfo) super.clone();
        }

    }
    private InterestingColumnInfo columnInfo;
    private ProxyState proxyState;
    private RealmList<com.anubis.phlix.models.Photo> interestingPhotosRealmList;
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>();
        fieldNames.add("id");
        fieldNames.add("timestamp");
        fieldNames.add("interestingPhotos");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    InterestingRealmProxy() {
        if (proxyState == null) {
            injectObjectContext();
        }
        proxyState.setConstructionFinished();
    }

    private void injectObjectContext() {
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (InterestingColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState(com.anubis.phlix.models.Interesting.class, this);
        proxyState.setRealm$realm(context.getRealm());
        proxyState.setRow$realm(context.getRow());
        proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
        proxyState.setExcludeFields$realm(context.getExcludeFields());
    }

    @SuppressWarnings("cast")
    public String realmGet$id() {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.idIndex);
    }

    public void realmSet$id(String value) {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        if (proxyState.isUnderConstruction()) {
            // default value of the primary key is always ignored.
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        throw new io.realm.exceptions.RealmException("Primary key field 'id' cannot be changed after object was created.");
    }

    @SuppressWarnings("cast")
    public Date realmGet$timestamp() {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        proxyState.getRealm$realm().checkIfValid();
        if (proxyState.getRow$realm().isNull(columnInfo.timestampIndex)) {
            return null;
        }
        return (java.util.Date) proxyState.getRow$realm().getDate(columnInfo.timestampIndex);
    }

    public void realmSet$timestamp(Date value) {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.timestampIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setDate(columnInfo.timestampIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.timestampIndex);
            return;
        }
        proxyState.getRow$realm().setDate(columnInfo.timestampIndex, value);
    }

    public RealmList<com.anubis.phlix.models.Photo> realmGet$interestingPhotos() {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        proxyState.getRealm$realm().checkIfValid();
        // use the cached value if available
        if (interestingPhotosRealmList != null) {
            return interestingPhotosRealmList;
        } else {
            LinkView linkView = proxyState.getRow$realm().getLinkList(columnInfo.interestingPhotosIndex);
            interestingPhotosRealmList = new RealmList<com.anubis.phlix.models.Photo>(com.anubis.phlix.models.Photo.class, linkView, proxyState.getRealm$realm());
            return interestingPhotosRealmList;
        }
    }

    public void realmSet$interestingPhotos(RealmList<com.anubis.phlix.models.Photo> value) {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            if (proxyState.getExcludeFields$realm().contains("interestingPhotos")) {
                return;
            }
            if (value != null && !value.isManaged()) {
                final Realm realm = (Realm) proxyState.getRealm$realm();
                final RealmList<com.anubis.phlix.models.Photo> original = value;
                value = new RealmList<com.anubis.phlix.models.Photo>();
                for (com.anubis.phlix.models.Photo item : original) {
                    if (item == null || RealmObject.isManaged(item)) {
                        value.add(item);
                    } else {
                        value.add(realm.copyToRealm(item));
                    }
                }
            }
        }

        proxyState.getRealm$realm().checkIfValid();
        LinkView links = proxyState.getRow$realm().getLinkList(columnInfo.interestingPhotosIndex);
        links.clear();
        if (value == null) {
            return;
        }
        for (RealmModel linkedObject : (RealmList<? extends RealmModel>) value) {
            if (!(RealmObject.isManaged(linkedObject) && RealmObject.isValid(linkedObject))) {
                throw new IllegalArgumentException("Each element of 'value' must be a valid managed object.");
            }
            if (((RealmObjectProxy)linkedObject).realmGet$proxyState().getRealm$realm() != proxyState.getRealm$realm()) {
                throw new IllegalArgumentException("Each element of 'value' must belong to the same Realm.");
            }
            links.add(((RealmObjectProxy)linkedObject).realmGet$proxyState().getRow$realm().getIndex());
        }
    }

    public static RealmObjectSchema createRealmObjectSchema(RealmSchema realmSchema) {
        if (!realmSchema.contains("Interesting")) {
            RealmObjectSchema realmObjectSchema = realmSchema.create("Interesting");
            realmObjectSchema.add(new Property("id", RealmFieldType.STRING, Property.PRIMARY_KEY, Property.INDEXED, !Property.REQUIRED));
            realmObjectSchema.add(new Property("timestamp", RealmFieldType.DATE, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED));
            if (!realmSchema.contains("Photo")) {
                PhotoRealmProxy.createRealmObjectSchema(realmSchema);
            }
            realmObjectSchema.add(new Property("interestingPhotos", RealmFieldType.LIST, realmSchema.get("Photo")));
            return realmObjectSchema;
        }
        return realmSchema.get("Interesting");
    }

    public static Table initTable(SharedRealm sharedRealm) {
        if (!sharedRealm.hasTable("class_Interesting")) {
            Table table = sharedRealm.getTable("class_Interesting");
            table.addColumn(RealmFieldType.STRING, "id", Table.NULLABLE);
            table.addColumn(RealmFieldType.DATE, "timestamp", Table.NULLABLE);
            if (!sharedRealm.hasTable("class_Photo")) {
                PhotoRealmProxy.initTable(sharedRealm);
            }
            table.addColumnLink(RealmFieldType.LIST, "interestingPhotos", sharedRealm.getTable("class_Photo"));
            table.addSearchIndex(table.getColumnIndex("id"));
            table.setPrimaryKey("id");
            return table;
        }
        return sharedRealm.getTable("class_Interesting");
    }

    public static InterestingColumnInfo validateTable(SharedRealm sharedRealm, boolean allowExtraColumns) {
        if (sharedRealm.hasTable("class_Interesting")) {
            Table table = sharedRealm.getTable("class_Interesting");
            final long columnCount = table.getColumnCount();
            if (columnCount != 3) {
                if (columnCount < 3) {
                    throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field count is less than expected - expected 3 but was " + columnCount);
                }
                if (allowExtraColumns) {
                    RealmLog.debug("Field count is more than expected - expected 3 but was %1$d", columnCount);
                } else {
                    throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field count is more than expected - expected 3 but was " + columnCount);
                }
            }
            Map<String, RealmFieldType> columnTypes = new HashMap<String, RealmFieldType>();
            for (long i = 0; i < 3; i++) {
                columnTypes.put(table.getColumnName(i), table.getColumnType(i));
            }

            final InterestingColumnInfo columnInfo = new InterestingColumnInfo(sharedRealm.getPath(), table);

            if (!columnTypes.containsKey("id")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'id' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("id") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'id' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.idIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(),"@PrimaryKey field 'id' does not support null values in the existing Realm file. Migrate using RealmObjectSchema.setNullable(), or mark the field as @Required.");
            }
            if (table.getPrimaryKey() != table.getColumnIndex("id")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Primary key not defined for field 'id' in existing Realm file. Add @PrimaryKey.");
            }
            if (!table.hasSearchIndex(table.getColumnIndex("id"))) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Index not defined for field 'id' in existing Realm file. Either set @Index or migrate using io.realm.internal.Table.removeSearchIndex().");
            }
            if (!columnTypes.containsKey("timestamp")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'timestamp' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("timestamp") != RealmFieldType.DATE) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'Date' for field 'timestamp' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.timestampIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'timestamp' is required. Either set @Required to field 'timestamp' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("interestingPhotos")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'interestingPhotos'");
            }
            if (columnTypes.get("interestingPhotos") != RealmFieldType.LIST) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'Photo' for field 'interestingPhotos'");
            }
            if (!sharedRealm.hasTable("class_Photo")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing class 'class_Photo' for field 'interestingPhotos'");
            }
            Table table_2 = sharedRealm.getTable("class_Photo");
            if (!table.getLinkTarget(columnInfo.interestingPhotosIndex).hasSameSchema(table_2)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid RealmList type for field 'interestingPhotos': '" + table.getLinkTarget(columnInfo.interestingPhotosIndex).getName() + "' expected - was '" + table_2.getName() + "'");
            }
            return columnInfo;
        } else {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "The 'Interesting' class is missing from the schema for this Realm.");
        }
    }

    public static String getTableName() {
        return "class_Interesting";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static com.anubis.phlix.models.Interesting createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = new ArrayList<String>(1);
        com.anubis.phlix.models.Interesting obj = null;
        if (update) {
            Table table = realm.getTable(com.anubis.phlix.models.Interesting.class);
            long pkColumnIndex = table.getPrimaryKey();
            long rowIndex = TableOrView.NO_MATCH;
            if (json.isNull("id")) {
                rowIndex = table.findFirstNull(pkColumnIndex);
            } else {
                rowIndex = table.findFirstString(pkColumnIndex, json.getString("id"));
            }
            if (rowIndex != TableOrView.NO_MATCH) {
                final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
                try {
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.schema.getColumnInfo(com.anubis.phlix.models.Interesting.class), false, Collections.<String> emptyList());
                    obj = new io.realm.InterestingRealmProxy();
                } finally {
                    objectContext.clear();
                }
            }
        }
        if (obj == null) {
            if (json.has("interestingPhotos")) {
                excludeFields.add("interestingPhotos");
            }
            if (json.has("id")) {
                if (json.isNull("id")) {
                    obj = (io.realm.InterestingRealmProxy) realm.createObjectInternal(com.anubis.phlix.models.Interesting.class, null, true, excludeFields);
                } else {
                    obj = (io.realm.InterestingRealmProxy) realm.createObjectInternal(com.anubis.phlix.models.Interesting.class, json.getString("id"), true, excludeFields);
                }
            } else {
                throw new IllegalArgumentException("JSON object doesn't have the primary key field 'id'.");
            }
        }
        if (json.has("timestamp")) {
            if (json.isNull("timestamp")) {
                ((InterestingRealmProxyInterface) obj).realmSet$timestamp(null);
            } else {
                Object timestamp = json.get("timestamp");
                if (timestamp instanceof String) {
                    ((InterestingRealmProxyInterface) obj).realmSet$timestamp(JsonUtils.stringToDate((String) timestamp));
                } else {
                    ((InterestingRealmProxyInterface) obj).realmSet$timestamp(new Date(json.getLong("timestamp")));
                }
            }
        }
        if (json.has("interestingPhotos")) {
            if (json.isNull("interestingPhotos")) {
                ((InterestingRealmProxyInterface) obj).realmSet$interestingPhotos(null);
            } else {
                ((InterestingRealmProxyInterface) obj).realmGet$interestingPhotos().clear();
                JSONArray array = json.getJSONArray("interestingPhotos");
                for (int i = 0; i < array.length(); i++) {
                    com.anubis.phlix.models.Photo item = PhotoRealmProxy.createOrUpdateUsingJsonObject(realm, array.getJSONObject(i), update);
                    ((InterestingRealmProxyInterface) obj).realmGet$interestingPhotos().add(item);
                }
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static com.anubis.phlix.models.Interesting createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        boolean jsonHasPrimaryKey = false;
        com.anubis.phlix.models.Interesting obj = new com.anubis.phlix.models.Interesting();
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("id")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((InterestingRealmProxyInterface) obj).realmSet$id(null);
                } else {
                    ((InterestingRealmProxyInterface) obj).realmSet$id((String) reader.nextString());
                }
                jsonHasPrimaryKey = true;
            } else if (name.equals("timestamp")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((InterestingRealmProxyInterface) obj).realmSet$timestamp(null);
                } else if (reader.peek() == JsonToken.NUMBER) {
                    long timestamp = reader.nextLong();
                    if (timestamp > -1) {
                        ((InterestingRealmProxyInterface) obj).realmSet$timestamp(new Date(timestamp));
                    }
                } else {
                    ((InterestingRealmProxyInterface) obj).realmSet$timestamp(JsonUtils.stringToDate(reader.nextString()));
                }
            } else if (name.equals("interestingPhotos")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((InterestingRealmProxyInterface) obj).realmSet$interestingPhotos(null);
                } else {
                    ((InterestingRealmProxyInterface) obj).realmSet$interestingPhotos(new RealmList<com.anubis.phlix.models.Photo>());
                    reader.beginArray();
                    while (reader.hasNext()) {
                        com.anubis.phlix.models.Photo item = PhotoRealmProxy.createUsingJsonStream(realm, reader);
                        ((InterestingRealmProxyInterface) obj).realmGet$interestingPhotos().add(item);
                    }
                    reader.endArray();
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        if (!jsonHasPrimaryKey) {
            throw new IllegalArgumentException("JSON object doesn't have the primary key field 'id'.");
        }
        obj = realm.copyToRealm(obj);
        return obj;
    }

    public static com.anubis.phlix.models.Interesting copyOrUpdate(Realm realm, com.anubis.phlix.models.Interesting object, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().threadId != realm.threadId) {
            throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
        }
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return object;
        }
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        RealmObjectProxy cachedRealmObject = cache.get(object);
        if (cachedRealmObject != null) {
            return (com.anubis.phlix.models.Interesting) cachedRealmObject;
        } else {
            com.anubis.phlix.models.Interesting realmObject = null;
            boolean canUpdate = update;
            if (canUpdate) {
                Table table = realm.getTable(com.anubis.phlix.models.Interesting.class);
                long pkColumnIndex = table.getPrimaryKey();
                String value = ((InterestingRealmProxyInterface) object).realmGet$id();
                long rowIndex = TableOrView.NO_MATCH;
                if (value == null) {
                    rowIndex = table.findFirstNull(pkColumnIndex);
                } else {
                    rowIndex = table.findFirstString(pkColumnIndex, value);
                }
                if (rowIndex != TableOrView.NO_MATCH) {
                    try {
                        objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.schema.getColumnInfo(com.anubis.phlix.models.Interesting.class), false, Collections.<String> emptyList());
                        realmObject = new io.realm.InterestingRealmProxy();
                        cache.put(object, (RealmObjectProxy) realmObject);
                    } finally {
                        objectContext.clear();
                    }
                } else {
                    canUpdate = false;
                }
            }

            if (canUpdate) {
                return update(realm, realmObject, object, cache);
            } else {
                return copy(realm, object, update, cache);
            }
        }
    }

    public static com.anubis.phlix.models.Interesting copy(Realm realm, com.anubis.phlix.models.Interesting newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.anubis.phlix.models.Interesting) cachedRealmObject;
        } else {
            // rejecting default values to avoid creating unexpected objects from RealmModel/RealmList fields.
            com.anubis.phlix.models.Interesting realmObject = realm.createObjectInternal(com.anubis.phlix.models.Interesting.class, ((InterestingRealmProxyInterface) newObject).realmGet$id(), false, Collections.<String>emptyList());
            cache.put(newObject, (RealmObjectProxy) realmObject);
            ((InterestingRealmProxyInterface) realmObject).realmSet$timestamp(((InterestingRealmProxyInterface) newObject).realmGet$timestamp());

            RealmList<com.anubis.phlix.models.Photo> interestingPhotosList = ((InterestingRealmProxyInterface) newObject).realmGet$interestingPhotos();
            if (interestingPhotosList != null) {
                RealmList<com.anubis.phlix.models.Photo> interestingPhotosRealmList = ((InterestingRealmProxyInterface) realmObject).realmGet$interestingPhotos();
                for (int i = 0; i < interestingPhotosList.size(); i++) {
                    com.anubis.phlix.models.Photo interestingPhotosItem = interestingPhotosList.get(i);
                    com.anubis.phlix.models.Photo cacheinterestingPhotos = (com.anubis.phlix.models.Photo) cache.get(interestingPhotosItem);
                    if (cacheinterestingPhotos != null) {
                        interestingPhotosRealmList.add(cacheinterestingPhotos);
                    } else {
                        interestingPhotosRealmList.add(PhotoRealmProxy.copyOrUpdate(realm, interestingPhotosList.get(i), update, cache));
                    }
                }
            }

            return realmObject;
        }
    }

    public static long insert(Realm realm, com.anubis.phlix.models.Interesting object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.anubis.phlix.models.Interesting.class);
        long tableNativePtr = table.getNativeTablePointer();
        InterestingColumnInfo columnInfo = (InterestingColumnInfo) realm.schema.getColumnInfo(com.anubis.phlix.models.Interesting.class);
        long pkColumnIndex = table.getPrimaryKey();
        String primaryKeyValue = ((InterestingRealmProxyInterface) object).realmGet$id();
        long rowIndex = TableOrView.NO_MATCH;
        if (primaryKeyValue == null) {
            rowIndex = Table.nativeFindFirstNull(tableNativePtr, pkColumnIndex);
        } else {
            rowIndex = Table.nativeFindFirstString(tableNativePtr, pkColumnIndex, primaryKeyValue);
        }
        if (rowIndex == TableOrView.NO_MATCH) {
            rowIndex = table.addEmptyRowWithPrimaryKey(primaryKeyValue, false);
        } else {
            Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
        }
        cache.put(object, rowIndex);
        java.util.Date realmGet$timestamp = ((InterestingRealmProxyInterface)object).realmGet$timestamp();
        if (realmGet$timestamp != null) {
            Table.nativeSetTimestamp(tableNativePtr, columnInfo.timestampIndex, rowIndex, realmGet$timestamp.getTime(), false);
        }

        RealmList<com.anubis.phlix.models.Photo> interestingPhotosList = ((InterestingRealmProxyInterface) object).realmGet$interestingPhotos();
        if (interestingPhotosList != null) {
            long interestingPhotosNativeLinkViewPtr = Table.nativeGetLinkView(tableNativePtr, columnInfo.interestingPhotosIndex, rowIndex);
            for (com.anubis.phlix.models.Photo interestingPhotosItem : interestingPhotosList) {
                Long cacheItemIndexinterestingPhotos = cache.get(interestingPhotosItem);
                if (cacheItemIndexinterestingPhotos == null) {
                    cacheItemIndexinterestingPhotos = PhotoRealmProxy.insert(realm, interestingPhotosItem, cache);
                }
                LinkView.nativeAdd(interestingPhotosNativeLinkViewPtr, cacheItemIndexinterestingPhotos);
            }
            LinkView.nativeClose(interestingPhotosNativeLinkViewPtr);
        }

        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.anubis.phlix.models.Interesting.class);
        long tableNativePtr = table.getNativeTablePointer();
        InterestingColumnInfo columnInfo = (InterestingColumnInfo) realm.schema.getColumnInfo(com.anubis.phlix.models.Interesting.class);
        long pkColumnIndex = table.getPrimaryKey();
        com.anubis.phlix.models.Interesting object = null;
        while (objects.hasNext()) {
            object = (com.anubis.phlix.models.Interesting) objects.next();
            if(!cache.containsKey(object)) {
                if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex());
                    continue;
                }
                String primaryKeyValue = ((InterestingRealmProxyInterface) object).realmGet$id();
                long rowIndex = TableOrView.NO_MATCH;
                if (primaryKeyValue == null) {
                    rowIndex = Table.nativeFindFirstNull(tableNativePtr, pkColumnIndex);
                } else {
                    rowIndex = Table.nativeFindFirstString(tableNativePtr, pkColumnIndex, primaryKeyValue);
                }
                if (rowIndex == TableOrView.NO_MATCH) {
                    rowIndex = table.addEmptyRowWithPrimaryKey(primaryKeyValue, false);
                } else {
                    Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
                }
                cache.put(object, rowIndex);
                java.util.Date realmGet$timestamp = ((InterestingRealmProxyInterface)object).realmGet$timestamp();
                if (realmGet$timestamp != null) {
                    Table.nativeSetTimestamp(tableNativePtr, columnInfo.timestampIndex, rowIndex, realmGet$timestamp.getTime(), false);
                }

                RealmList<com.anubis.phlix.models.Photo> interestingPhotosList = ((InterestingRealmProxyInterface) object).realmGet$interestingPhotos();
                if (interestingPhotosList != null) {
                    long interestingPhotosNativeLinkViewPtr = Table.nativeGetLinkView(tableNativePtr, columnInfo.interestingPhotosIndex, rowIndex);
                    for (com.anubis.phlix.models.Photo interestingPhotosItem : interestingPhotosList) {
                        Long cacheItemIndexinterestingPhotos = cache.get(interestingPhotosItem);
                        if (cacheItemIndexinterestingPhotos == null) {
                            cacheItemIndexinterestingPhotos = PhotoRealmProxy.insert(realm, interestingPhotosItem, cache);
                        }
                        LinkView.nativeAdd(interestingPhotosNativeLinkViewPtr, cacheItemIndexinterestingPhotos);
                    }
                    LinkView.nativeClose(interestingPhotosNativeLinkViewPtr);
                }

            }
        }
    }

    public static long insertOrUpdate(Realm realm, com.anubis.phlix.models.Interesting object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.anubis.phlix.models.Interesting.class);
        long tableNativePtr = table.getNativeTablePointer();
        InterestingColumnInfo columnInfo = (InterestingColumnInfo) realm.schema.getColumnInfo(com.anubis.phlix.models.Interesting.class);
        long pkColumnIndex = table.getPrimaryKey();
        String primaryKeyValue = ((InterestingRealmProxyInterface) object).realmGet$id();
        long rowIndex = TableOrView.NO_MATCH;
        if (primaryKeyValue == null) {
            rowIndex = Table.nativeFindFirstNull(tableNativePtr, pkColumnIndex);
        } else {
            rowIndex = Table.nativeFindFirstString(tableNativePtr, pkColumnIndex, primaryKeyValue);
        }
        if (rowIndex == TableOrView.NO_MATCH) {
            rowIndex = table.addEmptyRowWithPrimaryKey(primaryKeyValue, false);
        }
        cache.put(object, rowIndex);
        java.util.Date realmGet$timestamp = ((InterestingRealmProxyInterface)object).realmGet$timestamp();
        if (realmGet$timestamp != null) {
            Table.nativeSetTimestamp(tableNativePtr, columnInfo.timestampIndex, rowIndex, realmGet$timestamp.getTime(), false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.timestampIndex, rowIndex, false);
        }

        long interestingPhotosNativeLinkViewPtr = Table.nativeGetLinkView(tableNativePtr, columnInfo.interestingPhotosIndex, rowIndex);
        LinkView.nativeClear(interestingPhotosNativeLinkViewPtr);
        RealmList<com.anubis.phlix.models.Photo> interestingPhotosList = ((InterestingRealmProxyInterface) object).realmGet$interestingPhotos();
        if (interestingPhotosList != null) {
            for (com.anubis.phlix.models.Photo interestingPhotosItem : interestingPhotosList) {
                Long cacheItemIndexinterestingPhotos = cache.get(interestingPhotosItem);
                if (cacheItemIndexinterestingPhotos == null) {
                    cacheItemIndexinterestingPhotos = PhotoRealmProxy.insertOrUpdate(realm, interestingPhotosItem, cache);
                }
                LinkView.nativeAdd(interestingPhotosNativeLinkViewPtr, cacheItemIndexinterestingPhotos);
            }
        }
        LinkView.nativeClose(interestingPhotosNativeLinkViewPtr);

        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.anubis.phlix.models.Interesting.class);
        long tableNativePtr = table.getNativeTablePointer();
        InterestingColumnInfo columnInfo = (InterestingColumnInfo) realm.schema.getColumnInfo(com.anubis.phlix.models.Interesting.class);
        long pkColumnIndex = table.getPrimaryKey();
        com.anubis.phlix.models.Interesting object = null;
        while (objects.hasNext()) {
            object = (com.anubis.phlix.models.Interesting) objects.next();
            if(!cache.containsKey(object)) {
                if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex());
                    continue;
                }
                String primaryKeyValue = ((InterestingRealmProxyInterface) object).realmGet$id();
                long rowIndex = TableOrView.NO_MATCH;
                if (primaryKeyValue == null) {
                    rowIndex = Table.nativeFindFirstNull(tableNativePtr, pkColumnIndex);
                } else {
                    rowIndex = Table.nativeFindFirstString(tableNativePtr, pkColumnIndex, primaryKeyValue);
                }
                if (rowIndex == TableOrView.NO_MATCH) {
                    rowIndex = table.addEmptyRowWithPrimaryKey(primaryKeyValue, false);
                }
                cache.put(object, rowIndex);
                java.util.Date realmGet$timestamp = ((InterestingRealmProxyInterface)object).realmGet$timestamp();
                if (realmGet$timestamp != null) {
                    Table.nativeSetTimestamp(tableNativePtr, columnInfo.timestampIndex, rowIndex, realmGet$timestamp.getTime(), false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.timestampIndex, rowIndex, false);
                }

                long interestingPhotosNativeLinkViewPtr = Table.nativeGetLinkView(tableNativePtr, columnInfo.interestingPhotosIndex, rowIndex);
                LinkView.nativeClear(interestingPhotosNativeLinkViewPtr);
                RealmList<com.anubis.phlix.models.Photo> interestingPhotosList = ((InterestingRealmProxyInterface) object).realmGet$interestingPhotos();
                if (interestingPhotosList != null) {
                    for (com.anubis.phlix.models.Photo interestingPhotosItem : interestingPhotosList) {
                        Long cacheItemIndexinterestingPhotos = cache.get(interestingPhotosItem);
                        if (cacheItemIndexinterestingPhotos == null) {
                            cacheItemIndexinterestingPhotos = PhotoRealmProxy.insertOrUpdate(realm, interestingPhotosItem, cache);
                        }
                        LinkView.nativeAdd(interestingPhotosNativeLinkViewPtr, cacheItemIndexinterestingPhotos);
                    }
                }
                LinkView.nativeClose(interestingPhotosNativeLinkViewPtr);

            }
        }
    }

    public static com.anubis.phlix.models.Interesting createDetachedCopy(com.anubis.phlix.models.Interesting realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.anubis.phlix.models.Interesting unmanagedObject;
        if (cachedObject != null) {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.anubis.phlix.models.Interesting)cachedObject.object;
            } else {
                unmanagedObject = (com.anubis.phlix.models.Interesting)cachedObject.object;
                cachedObject.minDepth = currentDepth;
            }
        } else {
            unmanagedObject = new com.anubis.phlix.models.Interesting();
            cache.put(realmObject, new RealmObjectProxy.CacheData(currentDepth, unmanagedObject));
        }
        ((InterestingRealmProxyInterface) unmanagedObject).realmSet$id(((InterestingRealmProxyInterface) realmObject).realmGet$id());
        ((InterestingRealmProxyInterface) unmanagedObject).realmSet$timestamp(((InterestingRealmProxyInterface) realmObject).realmGet$timestamp());

        // Deep copy of interestingPhotos
        if (currentDepth == maxDepth) {
            ((InterestingRealmProxyInterface) unmanagedObject).realmSet$interestingPhotos(null);
        } else {
            RealmList<com.anubis.phlix.models.Photo> managedinterestingPhotosList = ((InterestingRealmProxyInterface) realmObject).realmGet$interestingPhotos();
            RealmList<com.anubis.phlix.models.Photo> unmanagedinterestingPhotosList = new RealmList<com.anubis.phlix.models.Photo>();
            ((InterestingRealmProxyInterface) unmanagedObject).realmSet$interestingPhotos(unmanagedinterestingPhotosList);
            int nextDepth = currentDepth + 1;
            int size = managedinterestingPhotosList.size();
            for (int i = 0; i < size; i++) {
                com.anubis.phlix.models.Photo item = PhotoRealmProxy.createDetachedCopy(managedinterestingPhotosList.get(i), nextDepth, maxDepth, cache);
                unmanagedinterestingPhotosList.add(item);
            }
        }
        return unmanagedObject;
    }

    static com.anubis.phlix.models.Interesting update(Realm realm, com.anubis.phlix.models.Interesting realmObject, com.anubis.phlix.models.Interesting newObject, Map<RealmModel, RealmObjectProxy> cache) {
        ((InterestingRealmProxyInterface) realmObject).realmSet$timestamp(((InterestingRealmProxyInterface) newObject).realmGet$timestamp());
        RealmList<com.anubis.phlix.models.Photo> interestingPhotosList = ((InterestingRealmProxyInterface) newObject).realmGet$interestingPhotos();
        RealmList<com.anubis.phlix.models.Photo> interestingPhotosRealmList = ((InterestingRealmProxyInterface) realmObject).realmGet$interestingPhotos();
        interestingPhotosRealmList.clear();
        if (interestingPhotosList != null) {
            for (int i = 0; i < interestingPhotosList.size(); i++) {
                com.anubis.phlix.models.Photo interestingPhotosItem = interestingPhotosList.get(i);
                com.anubis.phlix.models.Photo cacheinterestingPhotos = (com.anubis.phlix.models.Photo) cache.get(interestingPhotosItem);
                if (cacheinterestingPhotos != null) {
                    interestingPhotosRealmList.add(cacheinterestingPhotos);
                } else {
                    interestingPhotosRealmList.add(PhotoRealmProxy.copyOrUpdate(realm, interestingPhotosList.get(i), true, cache));
                }
            }
        }
        return realmObject;
    }

    @Override
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("Interesting = [");
        stringBuilder.append("{id:");
        stringBuilder.append(realmGet$id() != null ? realmGet$id() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{timestamp:");
        stringBuilder.append(realmGet$timestamp() != null ? realmGet$timestamp() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{interestingPhotos:");
        stringBuilder.append("RealmList<Photo>[").append(realmGet$interestingPhotos().size()).append("]");
        stringBuilder.append("}");
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    @Override
    public ProxyState realmGet$proxyState() {
        return proxyState;
    }

    @Override
    public int hashCode() {
        String realmName = proxyState.getRealm$realm().getPath();
        String tableName = proxyState.getRow$realm().getTable().getName();
        long rowIndex = proxyState.getRow$realm().getIndex();

        int result = 17;
        result = 31 * result + ((realmName != null) ? realmName.hashCode() : 0);
        result = 31 * result + ((tableName != null) ? tableName.hashCode() : 0);
        result = 31 * result + (int) (rowIndex ^ (rowIndex >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InterestingRealmProxy aInteresting = (InterestingRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aInteresting.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aInteresting.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aInteresting.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }

}
