package com.sfauto.cloud.gateway.dao;

import com.sfauto.realdb.DBField;
import com.sfauto.realdb.DBTable;
import com.sfauto.realdb.record.TableRecord;
import org.springframework.stereotype.Repository;

@Repository
public class RealdbTableDao implements DBTable {
    /**
     * @return
     */
    @Override
    public long getBaseID64() {
        return 0;
    }

    /**
     * @return
     */
    @Override
    public String getName() {
        return null;
    }

    /**
     * @return
     */
    @Override
    public String getCname() {
        return null;
    }

    /**
     * @return
     */
    @Override
    public int getDbid() {
        return 0;
    }

    /**
     * @return
     */
    @Override
    public String getDbName() {
        return null;
    }

    /**
     * @return
     */
    @Override
    public String getDbCname() {
        return null;
    }

    /**
     * @return
     */
    @Override
    public int getTableid() {
        return 0;
    }

    /**
     * @return
     */
    @Override
    public int getMaxSize() {
        return 0;
    }

    /**
     * @param s
     * @return
     */
    @Override
    public DBField getFieldByName(String s) {
        return null;
    }

    /**
     * @param tableRecord
     * @return
     */
    @Override
    public TableRecord setDefaultValue(TableRecord tableRecord) {
        return null;
    }

    /**
     * @param tableRecord
     * @return
     */
    @Override
    public TableRecord addRecord(TableRecord tableRecord) {
        return null;
    }

    /**
     * @return
     */
    @Override
    public boolean clearRecord() {
        return false;
    }

    /**
     * @param i
     * @return
     */
    @Override
    public TableRecord getRecord(int i) {
        return null;
    }

    /**
     * @return
     */
    @Override
    public TableRecord[] getRecord() {
        return new TableRecord[0];
    }

    /**
     * @return
     */
    @Override
    public TableRecord createRecord() {
        return null;
    }

    /**
     * @return
     */
    @Override
    public int getRecordCount() {
        return 0;
    }

    /**
     * @param l
     * @return
     */
    @Override
    public boolean removeRecord(long l) {
        return false;
    }

    /**
     * @param l
     * @return
     */
    @Override
    public TableRecord findByKey(long l) {
        return null;
    }

    /**
     * @param ints
     * @param bytes
     * @return
     */
    @Override
    public TableRecord[] findByKey(int[] ints, byte[] bytes) {
        return new TableRecord[0];
    }

    /**
     * @param s
     * @return
     */
    @Override
    public TableRecord findByName(String s) {
        return null;
    }

    /**
     * @param l
     * @return
     */
    @Override
    public int findIndexByKey(long l) {
        return 0;
    }

    /**
     * @param tableRecord
     * @return
     */
    @Override
    public int findIndex(TableRecord tableRecord) {
        return 0;
    }

    /**
     * @param tableRecord
     * @return
     */
    @Override
    public boolean broadcast(TableRecord tableRecord) {
        return false;
    }

    /**
     * @param l
     * @return
     */
    @Override
    public boolean broadcastAtKey(long l) {
        return false;
    }

    /**
     * @return
     */
    @Override
    public boolean tableBroadcast() {
        return false;
    }

    /**
     * @param i
     * @return
     */
    @Override
    public boolean tableBroadcastColumnAt(int i) {
        return false;
    }

    /**
     * @param s
     * @return
     */
    @Override
    public boolean tableBroadcastColumn(String s) {
        return false;
    }

    /**
     * @return
     */
    @Override
    public boolean saveData() {
        return false;
    }

    /**
     *
     */
    @Override
    public void rebuild() {

    }

    /**
     * @return
     */
    @Override
    public boolean isMapChanged() {
        return false;
    }

    /**
     * @return
     */
    @Override
    public boolean syncFromMap() {
        return false;
    }

    /**
     * @param l
     */
    @Override
    public void removeRecordFromList(long l) {

    }

    /**
     * @return
     */
    @Override
    public boolean isLoaded() {
        return false;
    }
}
