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

public class TagRealmProxy extends com.anubis.phlix.models.Tag
    implements RealmObjectProxy, TagRealmProxyInterface {

    static final class TagColumnInfo extends ColumnInfo
        implements Cloneable {

        public long idIndex;
        public long authorIndex;
        public long authornameIndex;
        public long rawIndex;
        public long contentIndex;
        public long machineTagIndex;

        TagColumnInfo(String path, Table table) {
            final Map<String, Long> indicesMap = new HashMap<String, Long>(6);
            this.idIndex = getValidColumnIndex(path, table, "Tag", "id");
            indicesMap.put("id", this.idIndex);
            this.authorIndex = getValidColumnIndex(path, table, "Tag", "author");
            indicesMap.put("author", this.authorIndex);
            this.authornameIndex = getValidColumnIndex(path, table, "Tag", "authorname");
            indicesMap.put("authorname", this.authornameIndex);
            this.rawIndex = getValidColumnIndex(path, table, "Tag", "raw");
            indicesMap.put("raw", this.rawIndex);
            this.contentIndex = getValidColumnIndex(path, table, "Tag", "content");
            indicesMap.put("content", this.contentIndex);
            this.machineTagIndex = getValidColumnIndex(path, table, "Tag", "machineTag");
            indicesMap.put("machineTag", this.machineTagIndex);

            setIndicesMap(indicesMap);
        }

        @Override
        public final void copyColumnInfoFrom(ColumnInfo other) {
            final TagColumnInfo otherInfo = (TagColumnInfo) other;
            this.idIndex = otherInfo.idIndex;
            this.authorIndex = otherInfo.authorIndex;
            this.authornameIndex = otherInfo.authornameIndex;
            this.rawIndex = otherInfo.rawIndex;
            this.contentIndex = otherInfo.contentIndex;
            this.machineTagIndex = otherInfo.machineTagIndex;

            setIndicesMap(otherInfo.getIndicesMap());
        }

        @Override
        public final TagColumnInfo clone() {
            return (TagColumnInfo) super.clone();
        }

    }
    private TagColumnInfo columnInfo;
    private ProxyState proxyState;
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>();
        fieldNames.add("id");
        fieldNames.add("author");
        fieldNames.add("authorname");
        fieldNames.add("raw");
        fieldNames.add("content");
        fieldNames.add("machineTag");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    TagRealmProxy() {
        if (proxyState == null) {
            injectObjectContext();
        }
        proxyState.setConstructionFinished();
    }

    private void injectObjectContext() {
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (TagColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState(com.anubis.phlix.models.Tag.class, this);
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
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.idIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.idIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.idIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.idIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$author() {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.authorIndex);
    }

    public void realmSet$author(String value) {
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
                row.getTable().setNull(columnInfo.authorIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.authorIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.authorIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.authorIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$authorname() {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.authornameIndex);
    }

    public void realmSet$authorname(String value) {
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
                row.getTable().setNull(columnInfo.authornameIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.authornameIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.authornameIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.authornameIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$raw() {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.rawIndex);
    }

    public void realmSet$raw(String value) {
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
                row.getTable().setNull(columnInfo.rawIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.rawIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.rawIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.rawIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$content() {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.contentIndex);
    }

    public void realmSet$content(String value) {
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
                row.getTable().setNull(columnInfo.contentIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.contentIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.contentIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.contentIndex, value);
    }

    @SuppressWarnings("cast")
    public boolean realmGet$machineTag() {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        proxyState.getRealm$realm().checkIfValid();
        return (boolean) proxyState.getRow$realm().getBoolean(columnInfo.machineTagIndex);
    }

    public void realmSet$machineTag(boolean value) {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setBoolean(columnInfo.machineTagIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setBoolean(columnInfo.machineTagIndex, value);
    }

    public static RealmObjectSchema createRealmObjectSchema(RealmSchema realmSchema) {
        if (!realmSchema.contains("Tag")) {
            RealmObjectSchema realmObjectSchema = realmSchema.create("Tag");
            realmObjectSchema.add(new Property("id", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED));
            realmObjectSchema.add(new Property("author", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED));
            realmObjectSchema.add(new Property("authorname", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED));
            realmObjectSchema.add(new Property("raw", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED));
            realmObjectSchema.add(new Property("content", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED));
            realmObjectSchema.add(new Property("machineTag", RealmFieldType.BOOLEAN, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED));
            return realmObjectSchema;
        }
        return realmSchema.get("Tag");
    }

    public static Table initTable(SharedRealm sharedRealm) {
        if (!sharedRealm.hasTable("class_Tag")) {
            Table table = sharedRealm.getTable("class_Tag");
            table.addColumn(RealmFieldType.STRING, "id", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "author", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "authorname", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "raw", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "content", Table.NULLABLE);
            table.addColumn(RealmFieldType.BOOLEAN, "machineTag", Table.NOT_NULLABLE);
            table.setPrimaryKey("");
            return table;
        }
        return sharedRealm.getTable("class_Tag");
    }

    public static TagColumnInfo validateTable(SharedRealm sharedRealm, boolean allowExtraColumns) {
        if (sharedRealm.hasTable("class_Tag")) {
            Table table = sharedRealm.getTable("class_Tag");
            final long columnCount = table.getColumnCount();
            if (columnCount != 6) {
                if (columnCount < 6) {
                    throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field count is less than expected - expected 6 but was " + columnCount);
                }
                if (allowExtraColumns) {
                    RealmLog.debug("Field count is more than expected - expected 6 but was %1$d", columnCount);
                } else {
                    throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field count is more than expected - expected 6 but was " + columnCount);
                }
            }
            Map<String, RealmFieldType> columnTypes = new HashMap<String, RealmFieldType>();
            for (long i = 0; i < 6; i++) {
                columnTypes.put(table.getColumnName(i), table.getColumnType(i));
            }

            final TagColumnInfo columnInfo = new TagColumnInfo(sharedRealm.getPath(), table);

            if (!columnTypes.containsKey("id")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'id' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("id") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'id' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.idIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'id' is required. Either set @Required to field 'id' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("author")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'author' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("author") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'author' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.authorIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'author' is required. Either set @Required to field 'author' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("authorname")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'authorname' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("authorname") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'authorname' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.authornameIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'authorname' is required. Either set @Required to field 'authorname' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("raw")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'raw' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("raw") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'raw' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.rawIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'raw' is required. Either set @Required to field 'raw' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("content")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'content' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("content") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'content' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.contentIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'content' is required. Either set @Required to field 'content' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("machineTag")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'machineTag' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("machineTag") != RealmFieldType.BOOLEAN) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'boolean' for field 'machineTag' in existing Realm file.");
            }
            if (table.isColumnNullable(columnInfo.machineTagIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'machineTag' does support null values in the existing Realm file. Use corresponding boxed type for field 'machineTag' or migrate using RealmObjectSchema.setNullable().");
            }
            return columnInfo;
        } else {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "The 'Tag' class is missing from the schema for this Realm.");
        }
    }

    public static String getTableName() {
        return "class_Tag";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static com.anubis.phlix.models.Tag createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = Collections.<String> emptyList();
        com.anubis.phlix.models.Tag obj = realm.createObjectInternal(com.anubis.phlix.models.Tag.class, true, excludeFields);
        if (json.has("id")) {
            if (json.isNull("id")) {
                ((TagRealmProxyInterface) obj).realmSet$id(null);
            } else {
                ((TagRealmProxyInterface) obj).realmSet$id((String) json.getString("id"));
            }
        }
        if (json.has("author")) {
            if (json.isNull("author")) {
                ((TagRealmProxyInterface) obj).realmSet$author(null);
            } else {
                ((TagRealmProxyInterface) obj).realmSet$author((String) json.getString("author"));
            }
        }
        if (json.has("authorname")) {
            if (json.isNull("authorname")) {
                ((TagRealmProxyInterface) obj).realmSet$authorname(null);
            } else {
                ((TagRealmProxyInterface) obj).realmSet$authorname((String) json.getString("authorname"));
            }
        }
        if (json.has("raw")) {
            if (json.isNull("raw")) {
                ((TagRealmProxyInterface) obj).realmSet$raw(null);
            } else {
                ((TagRealmProxyInterface) obj).realmSet$raw((String) json.getString("raw"));
            }
        }
        if (json.has("content")) {
            if (json.isNull("content")) {
                ((TagRealmProxyInterface) obj).realmSet$content(null);
            } else {
                ((TagRealmProxyInterface) obj).realmSet$content((String) json.getString("content"));
            }
        }
        if (json.has("machineTag")) {
            if (json.isNull("machineTag")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'machineTag' to null.");
            } else {
                ((TagRealmProxyInterface) obj).realmSet$machineTag((boolean) json.getBoolean("machineTag"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static com.anubis.phlix.models.Tag createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        com.anubis.phlix.models.Tag obj = new com.anubis.phlix.models.Tag();
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("id")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((TagRealmProxyInterface) obj).realmSet$id(null);
                } else {
                    ((TagRealmProxyInterface) obj).realmSet$id((String) reader.nextString());
                }
            } else if (name.equals("author")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((TagRealmProxyInterface) obj).realmSet$author(null);
                } else {
                    ((TagRealmProxyInterface) obj).realmSet$author((String) reader.nextString());
                }
            } else if (name.equals("authorname")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((TagRealmProxyInterface) obj).realmSet$authorname(null);
                } else {
                    ((TagRealmProxyInterface) obj).realmSet$authorname((String) reader.nextString());
                }
            } else if (name.equals("raw")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((TagRealmProxyInterface) obj).realmSet$raw(null);
                } else {
                    ((TagRealmProxyInterface) obj).realmSet$raw((String) reader.nextString());
                }
            } else if (name.equals("content")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((TagRealmProxyInterface) obj).realmSet$content(null);
                } else {
                    ((TagRealmProxyInterface) obj).realmSet$content((String) reader.nextString());
                }
            } else if (name.equals("machineTag")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'machineTag' to null.");
                } else {
                    ((TagRealmProxyInterface) obj).realmSet$machineTag((boolean) reader.nextBoolean());
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        obj = realm.copyToRealm(obj);
        return obj;
    }

    public static com.anubis.phlix.models.Tag copyOrUpdate(Realm realm, com.anubis.phlix.models.Tag object, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().threadId != realm.threadId) {
            throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
        }
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return object;
        }
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        RealmObjectProxy cachedRealmObject = cache.get(object);
        if (cachedRealmObject != null) {
            return (com.anubis.phlix.models.Tag) cachedRealmObject;
        } else {
            return copy(realm, object, update, cache);
        }
    }

    public static com.anubis.phlix.models.Tag copy(Realm realm, com.anubis.phlix.models.Tag newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.anubis.phlix.models.Tag) cachedRealmObject;
        } else {
            // rejecting default values to avoid creating unexpected objects from RealmModel/RealmList fields.
            com.anubis.phlix.models.Tag realmObject = realm.createObjectInternal(com.anubis.phlix.models.Tag.class, false, Collections.<String>emptyList());
            cache.put(newObject, (RealmObjectProxy) realmObject);
            ((TagRealmProxyInterface) realmObject).realmSet$id(((TagRealmProxyInterface) newObject).realmGet$id());
            ((TagRealmProxyInterface) realmObject).realmSet$author(((TagRealmProxyInterface) newObject).realmGet$author());
            ((TagRealmProxyInterface) realmObject).realmSet$authorname(((TagRealmProxyInterface) newObject).realmGet$authorname());
            ((TagRealmProxyInterface) realmObject).realmSet$raw(((TagRealmProxyInterface) newObject).realmGet$raw());
            ((TagRealmProxyInterface) realmObject).realmSet$content(((TagRealmProxyInterface) newObject).realmGet$content());
            ((TagRealmProxyInterface) realmObject).realmSet$machineTag(((TagRealmProxyInterface) newObject).realmGet$machineTag());
            return realmObject;
        }
    }

    public static long insert(Realm realm, com.anubis.phlix.models.Tag object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.anubis.phlix.models.Tag.class);
        long tableNativePtr = table.getNativeTablePointer();
        TagColumnInfo columnInfo = (TagColumnInfo) realm.schema.getColumnInfo(com.anubis.phlix.models.Tag.class);
        long rowIndex = Table.nativeAddEmptyRow(tableNativePtr, 1);
        cache.put(object, rowIndex);
        String realmGet$id = ((TagRealmProxyInterface)object).realmGet$id();
        if (realmGet$id != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.idIndex, rowIndex, realmGet$id, false);
        }
        String realmGet$author = ((TagRealmProxyInterface)object).realmGet$author();
        if (realmGet$author != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.authorIndex, rowIndex, realmGet$author, false);
        }
        String realmGet$authorname = ((TagRealmProxyInterface)object).realmGet$authorname();
        if (realmGet$authorname != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.authornameIndex, rowIndex, realmGet$authorname, false);
        }
        String realmGet$raw = ((TagRealmProxyInterface)object).realmGet$raw();
        if (realmGet$raw != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.rawIndex, rowIndex, realmGet$raw, false);
        }
        String realmGet$content = ((TagRealmProxyInterface)object).realmGet$content();
        if (realmGet$content != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.contentIndex, rowIndex, realmGet$content, false);
        }
        Table.nativeSetBoolean(tableNativePtr, columnInfo.machineTagIndex, rowIndex, ((TagRealmProxyInterface)object).realmGet$machineTag(), false);
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.anubis.phlix.models.Tag.class);
        long tableNativePtr = table.getNativeTablePointer();
        TagColumnInfo columnInfo = (TagColumnInfo) realm.schema.getColumnInfo(com.anubis.phlix.models.Tag.class);
        com.anubis.phlix.models.Tag object = null;
        while (objects.hasNext()) {
            object = (com.anubis.phlix.models.Tag) objects.next();
            if(!cache.containsKey(object)) {
                if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex());
                    continue;
                }
                long rowIndex = Table.nativeAddEmptyRow(tableNativePtr, 1);
                cache.put(object, rowIndex);
                String realmGet$id = ((TagRealmProxyInterface)object).realmGet$id();
                if (realmGet$id != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.idIndex, rowIndex, realmGet$id, false);
                }
                String realmGet$author = ((TagRealmProxyInterface)object).realmGet$author();
                if (realmGet$author != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.authorIndex, rowIndex, realmGet$author, false);
                }
                String realmGet$authorname = ((TagRealmProxyInterface)object).realmGet$authorname();
                if (realmGet$authorname != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.authornameIndex, rowIndex, realmGet$authorname, false);
                }
                String realmGet$raw = ((TagRealmProxyInterface)object).realmGet$raw();
                if (realmGet$raw != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.rawIndex, rowIndex, realmGet$raw, false);
                }
                String realmGet$content = ((TagRealmProxyInterface)object).realmGet$content();
                if (realmGet$content != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.contentIndex, rowIndex, realmGet$content, false);
                }
                Table.nativeSetBoolean(tableNativePtr, columnInfo.machineTagIndex, rowIndex, ((TagRealmProxyInterface)object).realmGet$machineTag(), false);
            }
        }
    }

    public static long insertOrUpdate(Realm realm, com.anubis.phlix.models.Tag object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.anubis.phlix.models.Tag.class);
        long tableNativePtr = table.getNativeTablePointer();
        TagColumnInfo columnInfo = (TagColumnInfo) realm.schema.getColumnInfo(com.anubis.phlix.models.Tag.class);
        long rowIndex = Table.nativeAddEmptyRow(tableNativePtr, 1);
        cache.put(object, rowIndex);
        String realmGet$id = ((TagRealmProxyInterface)object).realmGet$id();
        if (realmGet$id != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.idIndex, rowIndex, realmGet$id, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.idIndex, rowIndex, false);
        }
        String realmGet$author = ((TagRealmProxyInterface)object).realmGet$author();
        if (realmGet$author != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.authorIndex, rowIndex, realmGet$author, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.authorIndex, rowIndex, false);
        }
        String realmGet$authorname = ((TagRealmProxyInterface)object).realmGet$authorname();
        if (realmGet$authorname != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.authornameIndex, rowIndex, realmGet$authorname, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.authornameIndex, rowIndex, false);
        }
        String realmGet$raw = ((TagRealmProxyInterface)object).realmGet$raw();
        if (realmGet$raw != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.rawIndex, rowIndex, realmGet$raw, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.rawIndex, rowIndex, false);
        }
        String realmGet$content = ((TagRealmProxyInterface)object).realmGet$content();
        if (realmGet$content != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.contentIndex, rowIndex, realmGet$content, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.contentIndex, rowIndex, false);
        }
        Table.nativeSetBoolean(tableNativePtr, columnInfo.machineTagIndex, rowIndex, ((TagRealmProxyInterface)object).realmGet$machineTag(), false);
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.anubis.phlix.models.Tag.class);
        long tableNativePtr = table.getNativeTablePointer();
        TagColumnInfo columnInfo = (TagColumnInfo) realm.schema.getColumnInfo(com.anubis.phlix.models.Tag.class);
        com.anubis.phlix.models.Tag object = null;
        while (objects.hasNext()) {
            object = (com.anubis.phlix.models.Tag) objects.next();
            if(!cache.containsKey(object)) {
                if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex());
                    continue;
                }
                long rowIndex = Table.nativeAddEmptyRow(tableNativePtr, 1);
                cache.put(object, rowIndex);
                String realmGet$id = ((TagRealmProxyInterface)object).realmGet$id();
                if (realmGet$id != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.idIndex, rowIndex, realmGet$id, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.idIndex, rowIndex, false);
                }
                String realmGet$author = ((TagRealmProxyInterface)object).realmGet$author();
                if (realmGet$author != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.authorIndex, rowIndex, realmGet$author, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.authorIndex, rowIndex, false);
                }
                String realmGet$authorname = ((TagRealmProxyInterface)object).realmGet$authorname();
                if (realmGet$authorname != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.authornameIndex, rowIndex, realmGet$authorname, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.authornameIndex, rowIndex, false);
                }
                String realmGet$raw = ((TagRealmProxyInterface)object).realmGet$raw();
                if (realmGet$raw != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.rawIndex, rowIndex, realmGet$raw, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.rawIndex, rowIndex, false);
                }
                String realmGet$content = ((TagRealmProxyInterface)object).realmGet$content();
                if (realmGet$content != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.contentIndex, rowIndex, realmGet$content, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.contentIndex, rowIndex, false);
                }
                Table.nativeSetBoolean(tableNativePtr, columnInfo.machineTagIndex, rowIndex, ((TagRealmProxyInterface)object).realmGet$machineTag(), false);
            }
        }
    }

    public static com.anubis.phlix.models.Tag createDetachedCopy(com.anubis.phlix.models.Tag realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.anubis.phlix.models.Tag unmanagedObject;
        if (cachedObject != null) {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.anubis.phlix.models.Tag)cachedObject.object;
            } else {
                unmanagedObject = (com.anubis.phlix.models.Tag)cachedObject.object;
                cachedObject.minDepth = currentDepth;
            }
        } else {
            unmanagedObject = new com.anubis.phlix.models.Tag();
            cache.put(realmObject, new RealmObjectProxy.CacheData(currentDepth, unmanagedObject));
        }
        ((TagRealmProxyInterface) unmanagedObject).realmSet$id(((TagRealmProxyInterface) realmObject).realmGet$id());
        ((TagRealmProxyInterface) unmanagedObject).realmSet$author(((TagRealmProxyInterface) realmObject).realmGet$author());
        ((TagRealmProxyInterface) unmanagedObject).realmSet$authorname(((TagRealmProxyInterface) realmObject).realmGet$authorname());
        ((TagRealmProxyInterface) unmanagedObject).realmSet$raw(((TagRealmProxyInterface) realmObject).realmGet$raw());
        ((TagRealmProxyInterface) unmanagedObject).realmSet$content(((TagRealmProxyInterface) realmObject).realmGet$content());
        ((TagRealmProxyInterface) unmanagedObject).realmSet$machineTag(((TagRealmProxyInterface) realmObject).realmGet$machineTag());
        return unmanagedObject;
    }

    @Override
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("Tag = [");
        stringBuilder.append("{id:");
        stringBuilder.append(realmGet$id() != null ? realmGet$id() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{author:");
        stringBuilder.append(realmGet$author() != null ? realmGet$author() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{authorname:");
        stringBuilder.append(realmGet$authorname() != null ? realmGet$authorname() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{raw:");
        stringBuilder.append(realmGet$raw() != null ? realmGet$raw() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{content:");
        stringBuilder.append(realmGet$content() != null ? realmGet$content() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{machineTag:");
        stringBuilder.append(realmGet$machineTag());
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
        TagRealmProxy aTag = (TagRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aTag.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aTag.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aTag.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }

}
