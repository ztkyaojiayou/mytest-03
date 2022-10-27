package com.sfauto.cloud.gateway.dao;

import com.sfauto.realdb.CriticalSection;
import com.sfauto.realdb.DBTable;
import com.sfauto.realdb.record.TableRecord;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Repository;

import java.nio.MappedByteBuffer;
import java.util.Vector;

@Repository
public class RealdbTableRecordDao implements TableRecord {

    /**
     * @param i
     * @return
     */
    @Override
    public Vector getValues(int i) {
        return null;
    }

    /**
     * @param i
     * @return
     */
    @Override
    public String[] getFields(int i) {
        return new String[0];
    }

    /**
     * @param i
     * @return
     */
    @Override
    public Object getValue(int i) {
        return null;
    }

    /**
     * @param s
     * @return
     */
    @Override
    public Object getValue(String s) {
        return null;
    }

    /**
     * @param s
     * @return
     */
    @Override
    public Object getValueByCName(String s) {
        return null;
    }

    /**
     * @param i
     * @param o
     * @return
     */
    @Override
    public boolean setValue(int i, Object o) {
        return false;
    }

    /**
     * @param s
     * @param o
     * @return
     */
    @Override
    public boolean setValue(String s, Object o) {
        return false;
    }

    /**
     * @param s
     * @param o
     * @return
     */
    @Override
    public boolean setValueByCName(String s, Object o) {
        return false;
    }

    /**
     *
     */
    @Override
    public void broadcast() {

    }

    /**
     * @param vector
     * @return
     */
    @Override
    public boolean setValues(Vector vector) {
        return false;
    }

    /**
     * @return
     */
    @Override
    public long memoID() {
        return 0;
    }

    /**
     * @return
     */
    @Override
    public long key() {
        return 0;
    }

    /**
     * @param l
     */
    @Override
    public void key(long l) {

    }

    /**
     * @return
     */
    @Override
    public int offset() {
        return 0;
    }

    /**
     * @return
     */
    @Override
    public int index() {
        return 0;
    }

    /**
     * @param i
     * @param b
     * @return
     */
    @Override
    public int offset(int i, boolean b) {
        return 0;
    }

    /**
     * @return
     */
    @Override
    public int size() {
        return 0;
    }

    /**
     *
     */
    @Override
    public void syncToMap() {

    }

    /**
     *
     */
    @Override
    public void syncFromMap() {

    }

    /**
     * @param mappedByteBuffers
     */
    @Override
    public void mapBuf(MappedByteBuffer[] mappedByteBuffers) {

    }

    /**
     * @param criticalSection
     */
    @Override
    public void setCS(CriticalSection criticalSection) {

    }

    /**
     * @param o
     * @param b
     */
    @Override
    public void setRecord(Object o, boolean b) {

    }

    /**
     * @return
     */
    @Override
    public byte[] getBytes() {
        return new byte[0];
    }

    /**
     *
     */
    @Override
    public void clearDirty() {

    }

    /**
     * @return
     */
    @Override
    public int[] getDirty() {
        return new int[0];
    }

    /**
     * @param dbTable
     */
    @Override
    public void setDBTable(DBTable dbTable) {

    }

    /**
     * @return
     */
    @Override
    public Object clone() {
        return null;
    }

    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     *
     * <p>The implementor must ensure <tt>sgn(x.compareTo(y)) ==
     * -sgn(y.compareTo(x))</tt> for all <tt>x</tt> and <tt>y</tt>.  (This
     * implies that <tt>x.compareTo(y)</tt> must throw an exception iff
     * <tt>y.compareTo(x)</tt> throws an exception.)
     *
     * <p>The implementor must also ensure that the relation is transitive:
     * <tt>(x.compareTo(y)&gt;0 &amp;&amp; y.compareTo(z)&gt;0)</tt> implies
     * <tt>x.compareTo(z)&gt;0</tt>.
     *
     * <p>Finally, the implementor must ensure that <tt>x.compareTo(y)==0</tt>
     * implies that <tt>sgn(x.compareTo(z)) == sgn(y.compareTo(z))</tt>, for
     * all <tt>z</tt>.
     *
     * <p>It is strongly recommended, but <i>not</i> strictly required that
     * <tt>(x.compareTo(y)==0) == (x.equals(y))</tt>.  Generally speaking, any
     * class that implements the <tt>Comparable</tt> interface and violates
     * this condition should clearly indicate this fact.  The recommended
     * language is "Note: this class has a natural ordering that is
     * inconsistent with equals."
     *
     * <p>In the foregoing description, the notation
     * <tt>sgn(</tt><i>expression</i><tt>)</tt> designates the mathematical
     * <i>signum</i> function, which is defined to return one of <tt>-1</tt>,
     * <tt>0</tt>, or <tt>1</tt> according to whether the value of
     * <i>expression</i> is negative, zero or positive.
     *
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     */
    @Override
    public int compareTo(@NotNull Object o) {
        return 0;
    }
}
