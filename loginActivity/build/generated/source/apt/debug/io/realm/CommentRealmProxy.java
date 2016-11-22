package io.realm;


import android.annotation.TargetApi;
import android.os.Build;
import android.util.JsonReader;
import android.util.JsonToken;

import io.realm.exceptions.RealmMigrationNeededException;
import io.realm.internal.ColumnInfo;
import io.realm.internal.RealmObjectProxy;
import io.realm.internal.Row;
import io.realm.internal.SharedRealm;
import io.realm.internal.Table;
import io.realm.internal.TableOrView;
import io.realm.log.RealmLog;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

public class CommentRealmProxy extends com.anubis.phlix.models.Comment
    implements RealmObjectProxy, CommentRealmProxyInterface {

    static final class CommentColumnInfo extends ColumnInfo
        implements Cloneable {

        public long idIndex;
        public long authorIndex;
        public long authorIsDeletedIndex;
        public long authornameIndex;
        public long iconserverIndex;
        public long iconfarmIndex;
        public long datecreateIndex;
        public long permalinkIndex;
        public long pathAliasIndex;
        public long realnameIndex;
        public long contentIndex;

        CommentColumnInfo(String path, Table table) {
            final Map<String, Long> indicesMap = new HashMap<String, Long>(11);
            this.idIndex = getValidColumnIndex(path, table, "Comment", "id");
            indicesMap.put("id", this.idIndex);
            this.authorIndex = getValidColumnIndex(path, table, "Comment", "author");
            indicesMap.put("author", this.authorIndex);
            this.authorIsDeletedIndex = getValidColumnIndex(path, table, "Comment", "authorIsDeleted");
            indicesMap.put("authorIsDeleted", this.authorIsDeletedIndex);
            this.authornameIndex = getValidColumnIndex(path, table, "Comment", "authorname");
            indicesMap.put("authorname", this.authornameIndex);
            this.iconserverIndex = getValidColumnIndex(path, table, "Comment", "iconserver");
            indicesMap.put("iconserver", this.iconserverIndex);
            this.iconfarmIndex = getValidColumnIndex(path, table, "Comment", "iconfarm");
            indicesMap.put("iconfarm", this.iconfarmIndex);
            this.datecreateIndex = getValidColumnIndex(path, table, "Comment", "datecreate");
            indicesMap.put("datecreate", this.datecreateIndex);
            this.permalinkIndex = getValidColumnIndex(path, table, "Comment", "permalink");
            indicesMap.put("permalink", this.permalinkIndex);
            this.pathAliasIndex = getValidColumnIndex(path, table, "Comment", "pathAlias");
            indicesMap.put("pathAlias", this.pathAliasIndex);
            this.realnameIndex = getValidColumnIndex(path, table, "Comment", "realname");
            indicesMap.put("realname", this.realnameIndex);
            this.contentIndex = getValidColumnIndex(path, table, "Comment", "content");
            indicesMap.put("content", this.contentIndex);

            setIndicesMap(indicesMap);
        }

        @Override
        public final void copyColumnInfoFrom(ColumnInfo other) {
            final CommentColumnInfo otherInfo = (CommentColumnInfo) other;
            this.idIndex = otherInfo.idIndex;
            this.authorIndex = otherInfo.authorIndex;
            this.authorIsDeletedIndex = otherInfo.authorIsDeletedIndex;
            this.authornameIndex = otherInfo.authornameIndex;
            this.iconserverIndex = otherInfo.iconserverIndex;
            this.iconfarmIndex = otherInfo.iconfarmIndex;
            this.datecreateIndex = otherInfo.datecreateIndex;
            this.permalinkIndex = otherInfo.permalinkIndex;
            this.pathAliasIndex = otherInfo.pathAliasIndex;
            this.realnameIndex = otherInfo.realnameIndex;
            this.contentIndex = otherInfo.contentIndex;

            setIndicesMap(otherInfo.getIndicesMap());
        }

        @Override
        public final CommentColumnInfo clone() {
            return (CommentColumnInfo) super.clone();
        }

    }
    private CommentColumnInfo columnInfo;
    private ProxyState proxyState;
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>();
        fieldNames.add("id");
        fieldNames.add("author");
        fieldNames.add("authorIsDeleted");
        fieldNames.add("authorname");
        fieldNames.add("iconserver");
        fieldNames.add("iconfarm");
        fieldNames.add("datecreate");
        fieldNames.add("permalink");
        fieldNames.add("pathAlias");
        fieldNames.add("realname");
        fieldNames.add("content");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    CommentRealmProxy() {
        if (proxyState == null) {
            injectObjectContext();
        }
        proxyState.setConstructionFinished();
    }

    private void injectObjectContext() {
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (CommentColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState(com.anubis.phlix.models.Comment.class, this);
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
    public Integer realmGet$authorIsDeleted() {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        proxyState.getRealm$realm().checkIfValid();
        if (proxyState.getRow$realm().isNull(columnInfo.authorIsDeletedIndex)) {
            return null;
        }
        return (int) proxyState.getRow$realm().getLong(columnInfo.authorIsDeletedIndex);
    }

    public void realmSet$authorIsDeleted(Integer value) {
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
                row.getTable().setNull(columnInfo.authorIsDeletedIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setLong(columnInfo.authorIsDeletedIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.authorIsDeletedIndex);
            return;
        }
        proxyState.getRow$realm().setLong(columnInfo.authorIsDeletedIndex, value);
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
    public String realmGet$iconserver() {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.iconserverIndex);
    }

    public void realmSet$iconserver(String value) {
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
                row.getTable().setNull(columnInfo.iconserverIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.iconserverIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.iconserverIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.iconserverIndex, value);
    }

    @SuppressWarnings("cast")
    public Integer realmGet$iconfarm() {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        proxyState.getRealm$realm().checkIfValid();
        if (proxyState.getRow$realm().isNull(columnInfo.iconfarmIndex)) {
            return null;
        }
        return (int) proxyState.getRow$realm().getLong(columnInfo.iconfarmIndex);
    }

    public void realmSet$iconfarm(Integer value) {
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
                row.getTable().setNull(columnInfo.iconfarmIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setLong(columnInfo.iconfarmIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.iconfarmIndex);
            return;
        }
        proxyState.getRow$realm().setLong(columnInfo.iconfarmIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$datecreate() {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.datecreateIndex);
    }

    public void realmSet$datecreate(String value) {
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
                row.getTable().setNull(columnInfo.datecreateIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.datecreateIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.datecreateIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.datecreateIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$permalink() {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.permalinkIndex);
    }

    public void realmSet$permalink(String value) {
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
                row.getTable().setNull(columnInfo.permalinkIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.permalinkIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.permalinkIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.permalinkIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$pathAlias() {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.pathAliasIndex);
    }

    public void realmSet$pathAlias(String value) {
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
                row.getTable().setNull(columnInfo.pathAliasIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.pathAliasIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.pathAliasIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.pathAliasIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$realname() {
        if (proxyState == null) {
            // Called from model's constructor. Inject context.
            injectObjectContext();
        }

        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.realnameIndex);
    }

    public void realmSet$realname(String value) {
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
                row.getTable().setNull(columnInfo.realnameIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.realnameIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.realnameIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.realnameIndex, value);
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

    public static RealmObjectSchema createRealmObjectSchema(RealmSchema realmSchema) {
        if (!realmSchema.contains("Comment")) {
            RealmObjectSchema realmObjectSchema = realmSchema.create("Comment");
            realmObjectSchema.add(new Property("id", RealmFieldType.STRING, Property.PRIMARY_KEY, Property.INDEXED, !Property.REQUIRED));
            realmObjectSchema.add(new Property("author", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED));
            realmObjectSchema.add(new Property("authorIsDeleted", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED));
            realmObjectSchema.add(new Property("authorname", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED));
            realmObjectSchema.add(new Property("iconserver", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED));
            realmObjectSchema.add(new Property("iconfarm", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED));
            realmObjectSchema.add(new Property("datecreate", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED));
            realmObjectSchema.add(new Property("permalink", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED));
            realmObjectSchema.add(new Property("pathAlias", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED));
            realmObjectSchema.add(new Property("realname", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED));
            realmObjectSchema.add(new Property("content", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED));
            return realmObjectSchema;
        }
        return realmSchema.get("Comment");
    }

    public static Table initTable(SharedRealm sharedRealm) {
        if (!sharedRealm.hasTable("class_Comment")) {
            Table table = sharedRealm.getTable("class_Comment");
            table.addColumn(RealmFieldType.STRING, "id", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "author", Table.NULLABLE);
            table.addColumn(RealmFieldType.INTEGER, "authorIsDeleted", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "authorname", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "iconserver", Table.NULLABLE);
            table.addColumn(RealmFieldType.INTEGER, "iconfarm", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "datecreate", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "permalink", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "pathAlias", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "realname", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "content", Table.NULLABLE);
            table.addSearchIndex(table.getColumnIndex("id"));
            table.setPrimaryKey("id");
            return table;
        }
        return sharedRealm.getTable("class_Comment");
    }

    public static CommentColumnInfo validateTable(SharedRealm sharedRealm, boolean allowExtraColumns) {
        if (sharedRealm.hasTable("class_Comment")) {
            Table table = sharedRealm.getTable("class_Comment");
            final long columnCount = table.getColumnCount();
            if (columnCount != 11) {
                if (columnCount < 11) {
                    throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field count is less than expected - expected 11 but was " + columnCount);
                }
                if (allowExtraColumns) {
                    RealmLog.debug("Field count is more than expected - expected 11 but was %1$d", columnCount);
                } else {
                    throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field count is more than expected - expected 11 but was " + columnCount);
                }
            }
            Map<String, RealmFieldType> columnTypes = new HashMap<String, RealmFieldType>();
            for (long i = 0; i < 11; i++) {
                columnTypes.put(table.getColumnName(i), table.getColumnType(i));
            }

            final CommentColumnInfo columnInfo = new CommentColumnInfo(sharedRealm.getPath(), table);

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
            if (!columnTypes.containsKey("author")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'author' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("author") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'author' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.authorIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'author' is required. Either set @Required to field 'author' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("authorIsDeleted")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'authorIsDeleted' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("authorIsDeleted") != RealmFieldType.INTEGER) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'Integer' for field 'authorIsDeleted' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.authorIsDeletedIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(),"Field 'authorIsDeleted' does not support null values in the existing Realm file. Either set @Required, use the primitive type for field 'authorIsDeleted' or migrate using RealmObjectSchema.setNullable().");
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
            if (!columnTypes.containsKey("iconserver")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'iconserver' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("iconserver") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'iconserver' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.iconserverIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'iconserver' is required. Either set @Required to field 'iconserver' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("iconfarm")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'iconfarm' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("iconfarm") != RealmFieldType.INTEGER) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'Integer' for field 'iconfarm' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.iconfarmIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(),"Field 'iconfarm' does not support null values in the existing Realm file. Either set @Required, use the primitive type for field 'iconfarm' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("datecreate")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'datecreate' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("datecreate") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'datecreate' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.datecreateIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'datecreate' is required. Either set @Required to field 'datecreate' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("permalink")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'permalink' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("permalink") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'permalink' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.permalinkIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'permalink' is required. Either set @Required to field 'permalink' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("pathAlias")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'pathAlias' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("pathAlias") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'pathAlias' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.pathAliasIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'pathAlias' is required. Either set @Required to field 'pathAlias' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("realname")) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'realname' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("realname") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'realname' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.realnameIndex)) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'realname' is required. Either set @Required to field 'realname' or migrate using RealmObjectSchema.setNullable().");
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
            return columnInfo;
        } else {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "The 'Comment' class is missing from the schema for this Realm.");
        }
    }

    public static String getTableName() {
        return "class_Comment";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static com.anubis.phlix.models.Comment createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = Collections.<String> emptyList();
        com.anubis.phlix.models.Comment obj = null;
        if (update) {
            Table table = realm.getTable(com.anubis.phlix.models.Comment.class);
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
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.schema.getColumnInfo(com.anubis.phlix.models.Comment.class), false, Collections.<String> emptyList());
                    obj = new io.realm.CommentRealmProxy();
                } finally {
                    objectContext.clear();
                }
            }
        }
        if (obj == null) {
            if (json.has("id")) {
                if (json.isNull("id")) {
                    obj = (io.realm.CommentRealmProxy) realm.createObjectInternal(com.anubis.phlix.models.Comment.class, null, true, excludeFields);
                } else {
                    obj = (io.realm.CommentRealmProxy) realm.createObjectInternal(com.anubis.phlix.models.Comment.class, json.getString("id"), true, excludeFields);
                }
            } else {
                throw new IllegalArgumentException("JSON object doesn't have the primary key field 'id'.");
            }
        }
        if (json.has("author")) {
            if (json.isNull("author")) {
                ((CommentRealmProxyInterface) obj).realmSet$author(null);
            } else {
                ((CommentRealmProxyInterface) obj).realmSet$author((String) json.getString("author"));
            }
        }
        if (json.has("authorIsDeleted")) {
            if (json.isNull("authorIsDeleted")) {
                ((CommentRealmProxyInterface) obj).realmSet$authorIsDeleted(null);
            } else {
                ((CommentRealmProxyInterface) obj).realmSet$authorIsDeleted((int) json.getInt("authorIsDeleted"));
            }
        }
        if (json.has("authorname")) {
            if (json.isNull("authorname")) {
                ((CommentRealmProxyInterface) obj).realmSet$authorname(null);
            } else {
                ((CommentRealmProxyInterface) obj).realmSet$authorname((String) json.getString("authorname"));
            }
        }
        if (json.has("iconserver")) {
            if (json.isNull("iconserver")) {
                ((CommentRealmProxyInterface) obj).realmSet$iconserver(null);
            } else {
                ((CommentRealmProxyInterface) obj).realmSet$iconserver((String) json.getString("iconserver"));
            }
        }
        if (json.has("iconfarm")) {
            if (json.isNull("iconfarm")) {
                ((CommentRealmProxyInterface) obj).realmSet$iconfarm(null);
            } else {
                ((CommentRealmProxyInterface) obj).realmSet$iconfarm((int) json.getInt("iconfarm"));
            }
        }
        if (json.has("datecreate")) {
            if (json.isNull("datecreate")) {
                ((CommentRealmProxyInterface) obj).realmSet$datecreate(null);
            } else {
                ((CommentRealmProxyInterface) obj).realmSet$datecreate((String) json.getString("datecreate"));
            }
        }
        if (json.has("permalink")) {
            if (json.isNull("permalink")) {
                ((CommentRealmProxyInterface) obj).realmSet$permalink(null);
            } else {
                ((CommentRealmProxyInterface) obj).realmSet$permalink((String) json.getString("permalink"));
            }
        }
        if (json.has("pathAlias")) {
            if (json.isNull("pathAlias")) {
                ((CommentRealmProxyInterface) obj).realmSet$pathAlias(null);
            } else {
                ((CommentRealmProxyInterface) obj).realmSet$pathAlias((String) json.getString("pathAlias"));
            }
        }
        if (json.has("realname")) {
            if (json.isNull("realname")) {
                ((CommentRealmProxyInterface) obj).realmSet$realname(null);
            } else {
                ((CommentRealmProxyInterface) obj).realmSet$realname((String) json.getString("realname"));
            }
        }
        if (json.has("content")) {
            if (json.isNull("content")) {
                ((CommentRealmProxyInterface) obj).realmSet$content(null);
            } else {
                ((CommentRealmProxyInterface) obj).realmSet$content((String) json.getString("content"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static com.anubis.phlix.models.Comment createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        boolean jsonHasPrimaryKey = false;
        com.anubis.phlix.models.Comment obj = new com.anubis.phlix.models.Comment();
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("id")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((CommentRealmProxyInterface) obj).realmSet$id(null);
                } else {
                    ((CommentRealmProxyInterface) obj).realmSet$id((String) reader.nextString());
                }
                jsonHasPrimaryKey = true;
            } else if (name.equals("author")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((CommentRealmProxyInterface) obj).realmSet$author(null);
                } else {
                    ((CommentRealmProxyInterface) obj).realmSet$author((String) reader.nextString());
                }
            } else if (name.equals("authorIsDeleted")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((CommentRealmProxyInterface) obj).realmSet$authorIsDeleted(null);
                } else {
                    ((CommentRealmProxyInterface) obj).realmSet$authorIsDeleted((int) reader.nextInt());
                }
            } else if (name.equals("authorname")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((CommentRealmProxyInterface) obj).realmSet$authorname(null);
                } else {
                    ((CommentRealmProxyInterface) obj).realmSet$authorname((String) reader.nextString());
                }
            } else if (name.equals("iconserver")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((CommentRealmProxyInterface) obj).realmSet$iconserver(null);
                } else {
                    ((CommentRealmProxyInterface) obj).realmSet$iconserver((String) reader.nextString());
                }
            } else if (name.equals("iconfarm")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((CommentRealmProxyInterface) obj).realmSet$iconfarm(null);
                } else {
                    ((CommentRealmProxyInterface) obj).realmSet$iconfarm((int) reader.nextInt());
                }
            } else if (name.equals("datecreate")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((CommentRealmProxyInterface) obj).realmSet$datecreate(null);
                } else {
                    ((CommentRealmProxyInterface) obj).realmSet$datecreate((String) reader.nextString());
                }
            } else if (name.equals("permalink")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((CommentRealmProxyInterface) obj).realmSet$permalink(null);
                } else {
                    ((CommentRealmProxyInterface) obj).realmSet$permalink((String) reader.nextString());
                }
            } else if (name.equals("pathAlias")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((CommentRealmProxyInterface) obj).realmSet$pathAlias(null);
                } else {
                    ((CommentRealmProxyInterface) obj).realmSet$pathAlias((String) reader.nextString());
                }
            } else if (name.equals("realname")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((CommentRealmProxyInterface) obj).realmSet$realname(null);
                } else {
                    ((CommentRealmProxyInterface) obj).realmSet$realname((String) reader.nextString());
                }
            } else if (name.equals("content")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((CommentRealmProxyInterface) obj).realmSet$content(null);
                } else {
                    ((CommentRealmProxyInterface) obj).realmSet$content((String) reader.nextString());
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

    public static com.anubis.phlix.models.Comment copyOrUpdate(Realm realm, com.anubis.phlix.models.Comment object, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().threadId != realm.threadId) {
            throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
        }
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return object;
        }
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        RealmObjectProxy cachedRealmObject = cache.get(object);
        if (cachedRealmObject != null) {
            return (com.anubis.phlix.models.Comment) cachedRealmObject;
        } else {
            com.anubis.phlix.models.Comment realmObject = null;
            boolean canUpdate = update;
            if (canUpdate) {
                Table table = realm.getTable(com.anubis.phlix.models.Comment.class);
                long pkColumnIndex = table.getPrimaryKey();
                String value = ((CommentRealmProxyInterface) object).realmGet$id();
                long rowIndex = TableOrView.NO_MATCH;
                if (value == null) {
                    rowIndex = table.findFirstNull(pkColumnIndex);
                } else {
                    rowIndex = table.findFirstString(pkColumnIndex, value);
                }
                if (rowIndex != TableOrView.NO_MATCH) {
                    try {
                        objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.schema.getColumnInfo(com.anubis.phlix.models.Comment.class), false, Collections.<String> emptyList());
                        realmObject = new io.realm.CommentRealmProxy();
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

    public static com.anubis.phlix.models.Comment copy(Realm realm, com.anubis.phlix.models.Comment newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.anubis.phlix.models.Comment) cachedRealmObject;
        } else {
            // rejecting default values to avoid creating unexpected objects from RealmModel/RealmList fields.
            com.anubis.phlix.models.Comment realmObject = realm.createObjectInternal(com.anubis.phlix.models.Comment.class, ((CommentRealmProxyInterface) newObject).realmGet$id(), false, Collections.<String>emptyList());
            cache.put(newObject, (RealmObjectProxy) realmObject);
            ((CommentRealmProxyInterface) realmObject).realmSet$author(((CommentRealmProxyInterface) newObject).realmGet$author());
            ((CommentRealmProxyInterface) realmObject).realmSet$authorIsDeleted(((CommentRealmProxyInterface) newObject).realmGet$authorIsDeleted());
            ((CommentRealmProxyInterface) realmObject).realmSet$authorname(((CommentRealmProxyInterface) newObject).realmGet$authorname());
            ((CommentRealmProxyInterface) realmObject).realmSet$iconserver(((CommentRealmProxyInterface) newObject).realmGet$iconserver());
            ((CommentRealmProxyInterface) realmObject).realmSet$iconfarm(((CommentRealmProxyInterface) newObject).realmGet$iconfarm());
            ((CommentRealmProxyInterface) realmObject).realmSet$datecreate(((CommentRealmProxyInterface) newObject).realmGet$datecreate());
            ((CommentRealmProxyInterface) realmObject).realmSet$permalink(((CommentRealmProxyInterface) newObject).realmGet$permalink());
            ((CommentRealmProxyInterface) realmObject).realmSet$pathAlias(((CommentRealmProxyInterface) newObject).realmGet$pathAlias());
            ((CommentRealmProxyInterface) realmObject).realmSet$realname(((CommentRealmProxyInterface) newObject).realmGet$realname());
            ((CommentRealmProxyInterface) realmObject).realmSet$content(((CommentRealmProxyInterface) newObject).realmGet$content());
            return realmObject;
        }
    }

    public static long insert(Realm realm, com.anubis.phlix.models.Comment object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.anubis.phlix.models.Comment.class);
        long tableNativePtr = table.getNativeTablePointer();
        CommentColumnInfo columnInfo = (CommentColumnInfo) realm.schema.getColumnInfo(com.anubis.phlix.models.Comment.class);
        long pkColumnIndex = table.getPrimaryKey();
        String primaryKeyValue = ((CommentRealmProxyInterface) object).realmGet$id();
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
        String realmGet$author = ((CommentRealmProxyInterface)object).realmGet$author();
        if (realmGet$author != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.authorIndex, rowIndex, realmGet$author, false);
        }
        Number realmGet$authorIsDeleted = ((CommentRealmProxyInterface)object).realmGet$authorIsDeleted();
        if (realmGet$authorIsDeleted != null) {
            Table.nativeSetLong(tableNativePtr, columnInfo.authorIsDeletedIndex, rowIndex, realmGet$authorIsDeleted.longValue(), false);
        }
        String realmGet$authorname = ((CommentRealmProxyInterface)object).realmGet$authorname();
        if (realmGet$authorname != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.authornameIndex, rowIndex, realmGet$authorname, false);
        }
        String realmGet$iconserver = ((CommentRealmProxyInterface)object).realmGet$iconserver();
        if (realmGet$iconserver != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.iconserverIndex, rowIndex, realmGet$iconserver, false);
        }
        Number realmGet$iconfarm = ((CommentRealmProxyInterface)object).realmGet$iconfarm();
        if (realmGet$iconfarm != null) {
            Table.nativeSetLong(tableNativePtr, columnInfo.iconfarmIndex, rowIndex, realmGet$iconfarm.longValue(), false);
        }
        String realmGet$datecreate = ((CommentRealmProxyInterface)object).realmGet$datecreate();
        if (realmGet$datecreate != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.datecreateIndex, rowIndex, realmGet$datecreate, false);
        }
        String realmGet$permalink = ((CommentRealmProxyInterface)object).realmGet$permalink();
        if (realmGet$permalink != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.permalinkIndex, rowIndex, realmGet$permalink, false);
        }
        String realmGet$pathAlias = ((CommentRealmProxyInterface)object).realmGet$pathAlias();
        if (realmGet$pathAlias != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.pathAliasIndex, rowIndex, realmGet$pathAlias, false);
        }
        String realmGet$realname = ((CommentRealmProxyInterface)object).realmGet$realname();
        if (realmGet$realname != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.realnameIndex, rowIndex, realmGet$realname, false);
        }
        String realmGet$content = ((CommentRealmProxyInterface)object).realmGet$content();
        if (realmGet$content != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.contentIndex, rowIndex, realmGet$content, false);
        }
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.anubis.phlix.models.Comment.class);
        long tableNativePtr = table.getNativeTablePointer();
        CommentColumnInfo columnInfo = (CommentColumnInfo) realm.schema.getColumnInfo(com.anubis.phlix.models.Comment.class);
        long pkColumnIndex = table.getPrimaryKey();
        com.anubis.phlix.models.Comment object = null;
        while (objects.hasNext()) {
            object = (com.anubis.phlix.models.Comment) objects.next();
            if(!cache.containsKey(object)) {
                if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex());
                    continue;
                }
                String primaryKeyValue = ((CommentRealmProxyInterface) object).realmGet$id();
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
                String realmGet$author = ((CommentRealmProxyInterface)object).realmGet$author();
                if (realmGet$author != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.authorIndex, rowIndex, realmGet$author, false);
                }
                Number realmGet$authorIsDeleted = ((CommentRealmProxyInterface)object).realmGet$authorIsDeleted();
                if (realmGet$authorIsDeleted != null) {
                    Table.nativeSetLong(tableNativePtr, columnInfo.authorIsDeletedIndex, rowIndex, realmGet$authorIsDeleted.longValue(), false);
                }
                String realmGet$authorname = ((CommentRealmProxyInterface)object).realmGet$authorname();
                if (realmGet$authorname != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.authornameIndex, rowIndex, realmGet$authorname, false);
                }
                String realmGet$iconserver = ((CommentRealmProxyInterface)object).realmGet$iconserver();
                if (realmGet$iconserver != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.iconserverIndex, rowIndex, realmGet$iconserver, false);
                }
                Number realmGet$iconfarm = ((CommentRealmProxyInterface)object).realmGet$iconfarm();
                if (realmGet$iconfarm != null) {
                    Table.nativeSetLong(tableNativePtr, columnInfo.iconfarmIndex, rowIndex, realmGet$iconfarm.longValue(), false);
                }
                String realmGet$datecreate = ((CommentRealmProxyInterface)object).realmGet$datecreate();
                if (realmGet$datecreate != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.datecreateIndex, rowIndex, realmGet$datecreate, false);
                }
                String realmGet$permalink = ((CommentRealmProxyInterface)object).realmGet$permalink();
                if (realmGet$permalink != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.permalinkIndex, rowIndex, realmGet$permalink, false);
                }
                String realmGet$pathAlias = ((CommentRealmProxyInterface)object).realmGet$pathAlias();
                if (realmGet$pathAlias != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.pathAliasIndex, rowIndex, realmGet$pathAlias, false);
                }
                String realmGet$realname = ((CommentRealmProxyInterface)object).realmGet$realname();
                if (realmGet$realname != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.realnameIndex, rowIndex, realmGet$realname, false);
                }
                String realmGet$content = ((CommentRealmProxyInterface)object).realmGet$content();
                if (realmGet$content != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.contentIndex, rowIndex, realmGet$content, false);
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, com.anubis.phlix.models.Comment object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.anubis.phlix.models.Comment.class);
        long tableNativePtr = table.getNativeTablePointer();
        CommentColumnInfo columnInfo = (CommentColumnInfo) realm.schema.getColumnInfo(com.anubis.phlix.models.Comment.class);
        long pkColumnIndex = table.getPrimaryKey();
        String primaryKeyValue = ((CommentRealmProxyInterface) object).realmGet$id();
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
        String realmGet$author = ((CommentRealmProxyInterface)object).realmGet$author();
        if (realmGet$author != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.authorIndex, rowIndex, realmGet$author, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.authorIndex, rowIndex, false);
        }
        Number realmGet$authorIsDeleted = ((CommentRealmProxyInterface)object).realmGet$authorIsDeleted();
        if (realmGet$authorIsDeleted != null) {
            Table.nativeSetLong(tableNativePtr, columnInfo.authorIsDeletedIndex, rowIndex, realmGet$authorIsDeleted.longValue(), false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.authorIsDeletedIndex, rowIndex, false);
        }
        String realmGet$authorname = ((CommentRealmProxyInterface)object).realmGet$authorname();
        if (realmGet$authorname != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.authornameIndex, rowIndex, realmGet$authorname, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.authornameIndex, rowIndex, false);
        }
        String realmGet$iconserver = ((CommentRealmProxyInterface)object).realmGet$iconserver();
        if (realmGet$iconserver != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.iconserverIndex, rowIndex, realmGet$iconserver, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.iconserverIndex, rowIndex, false);
        }
        Number realmGet$iconfarm = ((CommentRealmProxyInterface)object).realmGet$iconfarm();
        if (realmGet$iconfarm != null) {
            Table.nativeSetLong(tableNativePtr, columnInfo.iconfarmIndex, rowIndex, realmGet$iconfarm.longValue(), false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.iconfarmIndex, rowIndex, false);
        }
        String realmGet$datecreate = ((CommentRealmProxyInterface)object).realmGet$datecreate();
        if (realmGet$datecreate != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.datecreateIndex, rowIndex, realmGet$datecreate, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.datecreateIndex, rowIndex, false);
        }
        String realmGet$permalink = ((CommentRealmProxyInterface)object).realmGet$permalink();
        if (realmGet$permalink != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.permalinkIndex, rowIndex, realmGet$permalink, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.permalinkIndex, rowIndex, false);
        }
        String realmGet$pathAlias = ((CommentRealmProxyInterface)object).realmGet$pathAlias();
        if (realmGet$pathAlias != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.pathAliasIndex, rowIndex, realmGet$pathAlias, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.pathAliasIndex, rowIndex, false);
        }
        String realmGet$realname = ((CommentRealmProxyInterface)object).realmGet$realname();
        if (realmGet$realname != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.realnameIndex, rowIndex, realmGet$realname, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.realnameIndex, rowIndex, false);
        }
        String realmGet$content = ((CommentRealmProxyInterface)object).realmGet$content();
        if (realmGet$content != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.contentIndex, rowIndex, realmGet$content, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.contentIndex, rowIndex, false);
        }
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.anubis.phlix.models.Comment.class);
        long tableNativePtr = table.getNativeTablePointer();
        CommentColumnInfo columnInfo = (CommentColumnInfo) realm.schema.getColumnInfo(com.anubis.phlix.models.Comment.class);
        long pkColumnIndex = table.getPrimaryKey();
        com.anubis.phlix.models.Comment object = null;
        while (objects.hasNext()) {
            object = (com.anubis.phlix.models.Comment) objects.next();
            if(!cache.containsKey(object)) {
                if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex());
                    continue;
                }
                String primaryKeyValue = ((CommentRealmProxyInterface) object).realmGet$id();
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
                String realmGet$author = ((CommentRealmProxyInterface)object).realmGet$author();
                if (realmGet$author != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.authorIndex, rowIndex, realmGet$author, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.authorIndex, rowIndex, false);
                }
                Number realmGet$authorIsDeleted = ((CommentRealmProxyInterface)object).realmGet$authorIsDeleted();
                if (realmGet$authorIsDeleted != null) {
                    Table.nativeSetLong(tableNativePtr, columnInfo.authorIsDeletedIndex, rowIndex, realmGet$authorIsDeleted.longValue(), false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.authorIsDeletedIndex, rowIndex, false);
                }
                String realmGet$authorname = ((CommentRealmProxyInterface)object).realmGet$authorname();
                if (realmGet$authorname != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.authornameIndex, rowIndex, realmGet$authorname, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.authornameIndex, rowIndex, false);
                }
                String realmGet$iconserver = ((CommentRealmProxyInterface)object).realmGet$iconserver();
                if (realmGet$iconserver != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.iconserverIndex, rowIndex, realmGet$iconserver, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.iconserverIndex, rowIndex, false);
                }
                Number realmGet$iconfarm = ((CommentRealmProxyInterface)object).realmGet$iconfarm();
                if (realmGet$iconfarm != null) {
                    Table.nativeSetLong(tableNativePtr, columnInfo.iconfarmIndex, rowIndex, realmGet$iconfarm.longValue(), false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.iconfarmIndex, rowIndex, false);
                }
                String realmGet$datecreate = ((CommentRealmProxyInterface)object).realmGet$datecreate();
                if (realmGet$datecreate != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.datecreateIndex, rowIndex, realmGet$datecreate, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.datecreateIndex, rowIndex, false);
                }
                String realmGet$permalink = ((CommentRealmProxyInterface)object).realmGet$permalink();
                if (realmGet$permalink != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.permalinkIndex, rowIndex, realmGet$permalink, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.permalinkIndex, rowIndex, false);
                }
                String realmGet$pathAlias = ((CommentRealmProxyInterface)object).realmGet$pathAlias();
                if (realmGet$pathAlias != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.pathAliasIndex, rowIndex, realmGet$pathAlias, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.pathAliasIndex, rowIndex, false);
                }
                String realmGet$realname = ((CommentRealmProxyInterface)object).realmGet$realname();
                if (realmGet$realname != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.realnameIndex, rowIndex, realmGet$realname, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.realnameIndex, rowIndex, false);
                }
                String realmGet$content = ((CommentRealmProxyInterface)object).realmGet$content();
                if (realmGet$content != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.contentIndex, rowIndex, realmGet$content, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.contentIndex, rowIndex, false);
                }
            }
        }
    }

    public static com.anubis.phlix.models.Comment createDetachedCopy(com.anubis.phlix.models.Comment realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.anubis.phlix.models.Comment unmanagedObject;
        if (cachedObject != null) {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.anubis.phlix.models.Comment)cachedObject.object;
            } else {
                unmanagedObject = (com.anubis.phlix.models.Comment)cachedObject.object;
                cachedObject.minDepth = currentDepth;
            }
        } else {
            unmanagedObject = new com.anubis.phlix.models.Comment();
            cache.put(realmObject, new RealmObjectProxy.CacheData(currentDepth, unmanagedObject));
        }
        ((CommentRealmProxyInterface) unmanagedObject).realmSet$id(((CommentRealmProxyInterface) realmObject).realmGet$id());
        ((CommentRealmProxyInterface) unmanagedObject).realmSet$author(((CommentRealmProxyInterface) realmObject).realmGet$author());
        ((CommentRealmProxyInterface) unmanagedObject).realmSet$authorIsDeleted(((CommentRealmProxyInterface) realmObject).realmGet$authorIsDeleted());
        ((CommentRealmProxyInterface) unmanagedObject).realmSet$authorname(((CommentRealmProxyInterface) realmObject).realmGet$authorname());
        ((CommentRealmProxyInterface) unmanagedObject).realmSet$iconserver(((CommentRealmProxyInterface) realmObject).realmGet$iconserver());
        ((CommentRealmProxyInterface) unmanagedObject).realmSet$iconfarm(((CommentRealmProxyInterface) realmObject).realmGet$iconfarm());
        ((CommentRealmProxyInterface) unmanagedObject).realmSet$datecreate(((CommentRealmProxyInterface) realmObject).realmGet$datecreate());
        ((CommentRealmProxyInterface) unmanagedObject).realmSet$permalink(((CommentRealmProxyInterface) realmObject).realmGet$permalink());
        ((CommentRealmProxyInterface) unmanagedObject).realmSet$pathAlias(((CommentRealmProxyInterface) realmObject).realmGet$pathAlias());
        ((CommentRealmProxyInterface) unmanagedObject).realmSet$realname(((CommentRealmProxyInterface) realmObject).realmGet$realname());
        ((CommentRealmProxyInterface) unmanagedObject).realmSet$content(((CommentRealmProxyInterface) realmObject).realmGet$content());
        return unmanagedObject;
    }

    static com.anubis.phlix.models.Comment update(Realm realm, com.anubis.phlix.models.Comment realmObject, com.anubis.phlix.models.Comment newObject, Map<RealmModel, RealmObjectProxy> cache) {
        ((CommentRealmProxyInterface) realmObject).realmSet$author(((CommentRealmProxyInterface) newObject).realmGet$author());
        ((CommentRealmProxyInterface) realmObject).realmSet$authorIsDeleted(((CommentRealmProxyInterface) newObject).realmGet$authorIsDeleted());
        ((CommentRealmProxyInterface) realmObject).realmSet$authorname(((CommentRealmProxyInterface) newObject).realmGet$authorname());
        ((CommentRealmProxyInterface) realmObject).realmSet$iconserver(((CommentRealmProxyInterface) newObject).realmGet$iconserver());
        ((CommentRealmProxyInterface) realmObject).realmSet$iconfarm(((CommentRealmProxyInterface) newObject).realmGet$iconfarm());
        ((CommentRealmProxyInterface) realmObject).realmSet$datecreate(((CommentRealmProxyInterface) newObject).realmGet$datecreate());
        ((CommentRealmProxyInterface) realmObject).realmSet$permalink(((CommentRealmProxyInterface) newObject).realmGet$permalink());
        ((CommentRealmProxyInterface) realmObject).realmSet$pathAlias(((CommentRealmProxyInterface) newObject).realmGet$pathAlias());
        ((CommentRealmProxyInterface) realmObject).realmSet$realname(((CommentRealmProxyInterface) newObject).realmGet$realname());
        ((CommentRealmProxyInterface) realmObject).realmSet$content(((CommentRealmProxyInterface) newObject).realmGet$content());
        return realmObject;
    }

    @Override
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("Comment = [");
        stringBuilder.append("{id:");
        stringBuilder.append(realmGet$id() != null ? realmGet$id() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{author:");
        stringBuilder.append(realmGet$author() != null ? realmGet$author() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{authorIsDeleted:");
        stringBuilder.append(realmGet$authorIsDeleted() != null ? realmGet$authorIsDeleted() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{authorname:");
        stringBuilder.append(realmGet$authorname() != null ? realmGet$authorname() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{iconserver:");
        stringBuilder.append(realmGet$iconserver() != null ? realmGet$iconserver() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{iconfarm:");
        stringBuilder.append(realmGet$iconfarm() != null ? realmGet$iconfarm() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{datecreate:");
        stringBuilder.append(realmGet$datecreate() != null ? realmGet$datecreate() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{permalink:");
        stringBuilder.append(realmGet$permalink() != null ? realmGet$permalink() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{pathAlias:");
        stringBuilder.append(realmGet$pathAlias() != null ? realmGet$pathAlias() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{realname:");
        stringBuilder.append(realmGet$realname() != null ? realmGet$realname() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{content:");
        stringBuilder.append(realmGet$content() != null ? realmGet$content() : "null");
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
        CommentRealmProxy aComment = (CommentRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aComment.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aComment.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aComment.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }

}
