package refined_classes.in_progress;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.util.Calendar;
import java.util.Map;

import liquidjava.specification.ExternalRefinementsFor;
import liquidjava.specification.StateSet;
import liquidjava.specification.StateRefinement;
import liquidjava.specification.Refinement;
import liquidjava.specification.RefinementAlias;
import liquidjava.specification.Ghost;

@ExternalRefinementsFor("java.sql.ResultSet")
@StateSet({ "open", "closed", "beforeFirst", "onRow", "afterLast", "insertRow" })
@RefinementAlias("IsValidRow(ResultSet rs) { onRow(rs) || insertRow(rs) }")
@RefinementAlias("IsOnDataRow(ResultSet rs) { onRow(rs) }")
@Ghost("int fetchDirection")
@Ghost("int fetchSize")
// @Ghost("int type_")
@Ghost("int concurrency")
@Ghost("int holdability")
public interface ResultSetRefinements {

	// ========== Cursor Movement Methods ========== //

	@StateRefinement(from = "open(this) && (beforeFirst(this) || onRow(this) || afterLast(this))", to = "onRow(this) || afterLast(this)")
	@Refinement("return == true || return == false")
	public abstract boolean next() throws SQLException;

	@StateRefinement(from = "open(this) && (beforeFirst(this) || onRow(this) || afterLast(this))", to = "onRow(this) || beforeFirst(this)")
	@Refinement("return == true || return == false")
	public abstract boolean previous() throws SQLException;

	@StateRefinement(from = "open(this) && (beforeFirst(this) || onRow(this) || afterLast(this))", to = "onRow(this) || beforeFirst(this)")
	@Refinement("return == true || return == false")
	public abstract boolean first() throws SQLException;

	@StateRefinement(from = "open(this) && (beforeFirst(this) || onRow(this) || afterLast(this))", to = "onRow(this) || afterLast(this)")
	@Refinement("return == true || return == false")
	public abstract boolean last() throws SQLException;

	@StateRefinement(from = "open(this) && (beforeFirst(this) || onRow(this) || afterLast(this))", to = "onRow(this) || beforeFirst(this) || afterLast(this)")
	@Refinement("return == true || return == false")
	public abstract boolean absolute(@Refinement("_ != 0") int row) throws SQLException;

	@StateRefinement(from = "open(this) && (beforeFirst(this) || onRow(this) || afterLast(this))", to = "onRow(this) || beforeFirst(this) || afterLast(this)")
	@Refinement("return == true || return == false")
	public abstract boolean relative(@Refinement("_ != 0") int rows) throws SQLException;

	@StateRefinement(from = "open(this)", to = "beforeFirst(this)")
	public abstract void beforeFirst() throws SQLException;

	@StateRefinement(from = "open(this)", to = "afterLast(this)")
	public abstract void afterLast() throws SQLException;

	@StateRefinement(from = "open(this) && concurrency(this) == CONCUR_UPDATABLE", to = "insertRow(this)")
	public abstract void moveToInsertRow() throws SQLException;

	@StateRefinement(from = "open(this) && insertRow(this)", to = "onRow(this)")
	public abstract void moveToCurrentRow() throws SQLException;

	// ========== State Query Methods ========== //

	@StateRefinement(from = "open(this)")
	@Refinement("return == beforeFirst(this)")
	public abstract boolean isBeforeFirst() throws SQLException;

	@StateRefinement(from = "open(this)")
	@Refinement("return == afterLast(this)")
	public abstract boolean isAfterLast() throws SQLException;

	@StateRefinement(from = "open(this)")
	@Refinement("return == (onRow(this) && getRow(this) == 1)")
	public abstract boolean isFirst() throws SQLException;

	@StateRefinement(from = "open(this)")
	@Refinement("return == (onRow(this) && getRow(this) == lastRow(this))")
	public abstract boolean isLast() throws SQLException;

	@StateRefinement(from = "open(this)")
	@Refinement("return == closed(this)")
	public abstract boolean isClosed() throws SQLException;

	@StateRefinement(from = "open(this) && onRow(this)")
	@Refinement("return >= 0")
	public abstract int getRow() throws SQLException;

	// ========== Getter Methods - String ========== //

	@StateRefinement(from = "open(this) && IsOnDataRow(this)")
	public abstract String getString(@Refinement("_ >= 1") int columnIndex) throws SQLException;

	@StateRefinement(from = "open(this) && IsOnDataRow(this)")
	public abstract String getString(@Refinement("columnLabel != null") String columnLabel) throws SQLException;

	@StateRefinement(from = "open(this) && IsOnDataRow(this)")
	public abstract String getNString(@Refinement("_ >= 1") int columnIndex) throws SQLException;

	@StateRefinement(from = "open(this) && IsOnDataRow(this)")
	public abstract String getNString(@Refinement("columnLabel != null") String columnLabel) throws SQLException;

	// ========== Getter Methods - Boolean ========== //

	@StateRefinement(from = "open(this) && IsOnDataRow(this)")
	public abstract boolean getBoolean(@Refinement("_ >= 1") int columnIndex) throws SQLException;

	@StateRefinement(from = "open(this) && IsOnDataRow(this)")
	public abstract boolean getBoolean(@Refinement("columnLabel != null") String columnLabel) throws SQLException;

	// ========== Getter Methods - Byte ========== //

	@StateRefinement(from = "open(this) && IsOnDataRow(this)")
	public abstract byte getByte(@Refinement("_ >= 1") int columnIndex) throws SQLException;

	@StateRefinement(from = "open(this) && IsOnDataRow(this)")
	public abstract byte getByte(@Refinement("columnLabel != null") String columnLabel) throws SQLException;

	// ========== Getter Methods - Short ========== //

	@StateRefinement(from = "open(this) && IsOnDataRow(this)")
	public abstract short getShort(@Refinement("_ >= 1") int columnIndex) throws SQLException;

	@StateRefinement(from = "open(this) && IsOnDataRow(this)")
	public abstract short getShort(@Refinement("columnLabel != null") String columnLabel) throws SQLException;

	// ========== Getter Methods - Int ========== //

	@StateRefinement(from = "open(this) && IsOnDataRow(this)")
	public abstract int getInt(@Refinement("_ >= 1") int columnIndex) throws SQLException;

	@StateRefinement(from = "open(this) && IsOnDataRow(this)")
	public abstract int getInt(@Refinement("columnLabel != null") String columnLabel) throws SQLException;

	// ========== Getter Methods - Long ========== //

	@StateRefinement(from = "open(this) && IsOnDataRow(this)")
	public abstract long getLong(@Refinement("_ >= 1") int columnIndex) throws SQLException;

	@StateRefinement(from = "open(this) && IsOnDataRow(this)")
	public abstract long getLong(@Refinement("columnLabel != null") String columnLabel) throws SQLException;

	// ========== Getter Methods - Float ========== //

	@StateRefinement(from = "open(this) && IsOnDataRow(this)")
	public abstract float getFloat(@Refinement("_ >= 1") int columnIndex) throws SQLException;

	@StateRefinement(from = "open(this) && IsOnDataRow(this)")
	public abstract float getFloat(@Refinement("columnLabel != null") String columnLabel) throws SQLException;

	// ========== Getter Methods - Double ========== //

	@StateRefinement(from = "open(this) && IsOnDataRow(this)")
	public abstract double getDouble(@Refinement("_ >= 1") int columnIndex) throws SQLException;

	@StateRefinement(from = "open(this) && IsOnDataRow(this)")
	public abstract double getDouble(@Refinement("columnLabel != null") String columnLabel) throws SQLException;

	// ========== Getter Methods - BigDecimal ========== //

	@StateRefinement(from = "open(this) && IsOnDataRow(this)")
	public abstract BigDecimal getBigDecimal(@Refinement("_ >= 1") int columnIndex, @Refinement("_ >= 0") int scale) throws SQLException;

	@StateRefinement(from = "open(this) && IsOnDataRow(this)")
	public abstract BigDecimal getBigDecimal(@Refinement("_ >= 1") int columnIndex) throws SQLException;

	@StateRefinement(from = "open(this) && IsOnDataRow(this)")
	public abstract BigDecimal getBigDecimal(@Refinement("columnLabel != null") String columnLabel, @Refinement("_ >= 0") int scale) throws SQLException;

	@StateRefinement(from = "open(this) && IsOnDataRow(this)")
	public abstract BigDecimal getBigDecimal(@Refinement("columnLabel != null") String columnLabel) throws SQLException;

	// ========== Getter Methods - Bytes ========== //

	@StateRefinement(from = "open(this) && IsOnDataRow(this)")
	public abstract byte[] getBytes(@Refinement("_ >= 1") int columnIndex) throws SQLException;

	@StateRefinement(from = "open(this) && IsOnDataRow(this)")
	public abstract byte[] getBytes(@Refinement("columnLabel != null") String columnLabel) throws SQLException;

	// ========== Getter Methods - Date ========== //

	@StateRefinement(from = "open(this) && IsOnDataRow(this)")
	public abstract Date getDate(@Refinement("_ >= 1") int columnIndex) throws SQLException;

	@StateRefinement(from = "open(this) && IsOnDataRow(this)")
	public abstract Date getDate(@Refinement("_ >= 1") int columnIndex, @Refinement("cal != null") Calendar cal) throws SQLException;

	@StateRefinement(from = "open(this) && IsOnDataRow(this)")
	public abstract Date getDate(@Refinement("columnLabel != null") String columnLabel) throws SQLException;

	@StateRefinement(from = "open(this) && IsOnDataRow(this)")
	public abstract Date getDate(@Refinement("columnLabel != null") String columnLabel, @Refinement("cal != null") Calendar cal) throws SQLException;

	// ========== Getter Methods - Time ========== //

	@StateRefinement(from = "open(this) && IsOnDataRow(this)")
	public abstract Time getTime(@Refinement("_ >= 1") int columnIndex) throws SQLException;

	@StateRefinement(from = "open(this) && IsOnDataRow(this)")
	public abstract Time getTime(@Refinement("_ >= 1") int columnIndex, @Refinement("cal != null") Calendar cal) throws SQLException;

	@StateRefinement(from = "open(this) && IsOnDataRow(this)")
	public abstract Time getTime(@Refinement("columnLabel != null") String columnLabel) throws SQLException;

	@StateRefinement(from = "open(this) && IsOnDataRow(this)")
	public abstract Time getTime(@Refinement("columnLabel != null") String columnLabel, @Refinement("cal != null") Calendar cal) throws SQLException;

	// ========== Getter Methods - Timestamp ========== //

	@StateRefinement(from = "open(this) && IsOnDataRow(this)")
	public abstract Timestamp getTimestamp(@Refinement("_ >= 1") int columnIndex) throws SQLException;

	@StateRefinement(from = "open(this) && IsOnDataRow(this)")
	public abstract Timestamp getTimestamp(@Refinement("_ >= 1") int columnIndex, @Refinement("cal != null") Calendar cal) throws SQLException;

	@StateRefinement(from = "open(this) && IsOnDataRow(this)")
	public abstract Timestamp getTimestamp(@Refinement("columnLabel != null") String columnLabel) throws SQLException;

	@StateRefinement(from = "open(this) && IsOnDataRow(this)")
	public abstract Timestamp getTimestamp(@Refinement("columnLabel != null") String columnLabel, @Refinement("cal != null") Calendar cal) throws SQLException;

	// ========== Getter Methods - Streams ========== //

	@StateRefinement(from = "open(this) && IsOnDataRow(this)")
	public abstract InputStream getAsciiStream(@Refinement("_ >= 1") int columnIndex) throws SQLException;

	@StateRefinement(from = "open(this) && IsOnDataRow(this)")
	public abstract InputStream getAsciiStream(@Refinement("columnLabel != null") String columnLabel) throws SQLException;

	@StateRefinement(from = "open(this) && IsOnDataRow(this)")
	public abstract InputStream getBinaryStream(@Refinement("_ >= 1") int columnIndex) throws SQLException;

	@StateRefinement(from = "open(this) && IsOnDataRow(this)")
	public abstract InputStream getBinaryStream(@Refinement("columnLabel != null") String columnLabel) throws SQLException;

	@Deprecated
	@StateRefinement(from = "open(this) && IsOnDataRow(this)")
	public abstract InputStream getUnicodeStream(@Refinement("_ >= 1") int columnIndex) throws SQLException;

	@Deprecated
	@StateRefinement(from = "open(this) && IsOnDataRow(this)")
	public abstract InputStream getUnicodeStream(@Refinement("columnLabel != null") String columnLabel) throws SQLException;

	@StateRefinement(from = "open(this) && IsOnDataRow(this)")
	public abstract Reader getCharacterStream(@Refinement("_ >= 1") int columnIndex) throws SQLException;

	@StateRefinement(from = "open(this) && IsOnDataRow(this)")
	public abstract Reader getCharacterStream(@Refinement("columnLabel != null") String columnLabel) throws SQLException;

	@StateRefinement(from = "open(this) && IsOnDataRow(this)")
	public abstract Reader getNCharacterStream(@Refinement("_ >= 1") int columnIndex) throws SQLException;

	@StateRefinement(from = "open(this) && IsOnDataRow(this)")
	public abstract Reader getNCharacterStream(@Refinement("columnLabel != null") String columnLabel) throws SQLException;

	// ========== Getter Methods - Object ========== //

	@StateRefinement(from = "open(this) && IsOnDataRow(this)")
	public abstract Object getObject(@Refinement("_ >= 1") int columnIndex) throws SQLException;

	@StateRefinement(from = "open(this) && IsOnDataRow(this)")
	public abstract Object getObject(@Refinement("_ >= 1") int columnIndex, @Refinement("map != null") Map<String, Class<?>> map) throws SQLException;

	@StateRefinement(from = "open(this) && IsOnDataRow(this)")
	public abstract <T> T getObject(@Refinement("_ >= 1") int columnIndex, @Refinement("type != null") Class<T> type) throws SQLException;

	@StateRefinement(from = "open(this) && IsOnDataRow(this)")
	public abstract Object getObject(@Refinement("columnLabel != null") String columnLabel) throws SQLException;

	@StateRefinement(from = "open(this) && IsOnDataRow(this)")
	public abstract Object getObject(@Refinement("columnLabel != null") String columnLabel, @Refinement("map != null") Map<String, Class<?>> map) throws SQLException;

	@StateRefinement(from = "open(this) && IsOnDataRow(this)")
	public abstract <T> T getObject(@Refinement("columnLabel != null") String columnLabel, @Refinement("type != null") Class<T> type) throws SQLException;

	// ========== Getter Methods - Ref, Blob, Clob, Array ========== //

	@StateRefinement(from = "open(this) && IsOnDataRow(this)")
	public abstract Ref getRef(@Refinement("_ >= 1") int columnIndex) throws SQLException;

	@StateRefinement(from = "open(this) && IsOnDataRow(this)")
	public abstract Ref getRef(@Refinement("columnLabel != null") String columnLabel) throws SQLException;

	@StateRefinement(from = "open(this) && IsOnDataRow(this)")
	public abstract Blob getBlob(@Refinement("_ >= 1") int columnIndex) throws SQLException;

	@StateRefinement(from = "open(this) && IsOnDataRow(this)")
	public abstract Blob getBlob(@Refinement("columnLabel != null") String columnLabel) throws SQLException;

	@StateRefinement(from = "open(this) && IsOnDataRow(this)")
	public abstract Clob getClob(@Refinement("_ >= 1") int columnIndex) throws SQLException;

	@StateRefinement(from = "open(this) && IsOnDataRow(this)")
	public abstract Clob getClob(@Refinement("columnLabel != null") String columnLabel) throws SQLException;

	@StateRefinement(from = "open(this) && IsOnDataRow(this)")
	public abstract NClob getNClob(@Refinement("_ >= 1") int columnIndex) throws SQLException;

	@StateRefinement(from = "open(this) && IsOnDataRow(this)")
	public abstract NClob getNClob(@Refinement("columnLabel != null") String columnLabel) throws SQLException;

	@StateRefinement(from = "open(this) && IsOnDataRow(this)")
	public abstract Array getArray(@Refinement("_ >= 1") int columnIndex) throws SQLException;

	@StateRefinement(from = "open(this) && IsOnDataRow(this)")
	public abstract Array getArray(@Refinement("columnLabel != null") String columnLabel) throws SQLException;

	@StateRefinement(from = "open(this) && IsOnDataRow(this)")
	public abstract RowId getRowId(@Refinement("_ >= 1") int columnIndex) throws SQLException;

	@StateRefinement(from = "open(this) && IsOnDataRow(this)")
	public abstract RowId getRowId(@Refinement("columnLabel != null") String columnLabel) throws SQLException;

	@StateRefinement(from = "open(this) && IsOnDataRow(this)")
	public abstract SQLXML getSQLXML(@Refinement("_ >= 1") int columnIndex) throws SQLException;

	@StateRefinement(from = "open(this) && IsOnDataRow(this)")
	public abstract SQLXML getSQLXML(@Refinement("columnLabel != null") String columnLabel) throws SQLException;

	@StateRefinement(from = "open(this) && IsOnDataRow(this)")
	public abstract URL getURL(@Refinement("_ >= 1") int columnIndex) throws SQLException;

	@StateRefinement(from = "open(this) && IsOnDataRow(this)")
	public abstract URL getURL(@Refinement("columnLabel != null") String columnLabel) throws SQLException;

	// ========== Updater Methods - Null ========== //

	@StateRefinement(from = "open(this) && concurrency(this) == CONCUR_UPDATABLE && IsValidRow(this)")
	public abstract void updateNull(@Refinement("_ >= 1") int columnIndex) throws SQLException;

	@StateRefinement(from = "open(this) && concurrency(this) == CONCUR_UPDATABLE && IsValidRow(this)")
	public abstract void updateNull(@Refinement("columnLabel != null") String columnLabel) throws SQLException;

	// ========== Updater Methods - Boolean ========== //

	@StateRefinement(from = "open(this) && concurrency(this) == CONCUR_UPDATABLE && IsValidRow(this)")
	public abstract void updateBoolean(@Refinement("_ >= 1") int columnIndex, boolean x) throws SQLException;

	@StateRefinement(from = "open(this) && concurrency(this) == CONCUR_UPDATABLE && IsValidRow(this)")
	public abstract void updateBoolean(@Refinement("columnLabel != null") String columnLabel, boolean x) throws SQLException;

	// ========== Updater Methods - Byte ========== //

	@StateRefinement(from = "open(this) && concurrency(this) == CONCUR_UPDATABLE && IsValidRow(this)")
	public abstract void updateByte(@Refinement("_ >= 1") int columnIndex, byte x) throws SQLException;

	@StateRefinement(from = "open(this) && concurrency(this) == CONCUR_UPDATABLE && IsValidRow(this)")
	public abstract void updateByte(@Refinement("columnLabel != null") String columnLabel, byte x) throws SQLException;

	// ========== Updater Methods - Short ========== //

	@StateRefinement(from = "open(this) && concurrency(this) == CONCUR_UPDATABLE && IsValidRow(this)")
	public abstract void updateShort(@Refinement("_ >= 1") int columnIndex, short x) throws SQLException;

	@StateRefinement(from = "open(this) && concurrency(this) == CONCUR_UPDATABLE && IsValidRow(this)")
	public abstract void updateShort(@Refinement("columnLabel != null") String columnLabel, short x) throws SQLException;

	// ========== Updater Methods - Int ========== //

	@StateRefinement(from = "open(this) && concurrency(this) == CONCUR_UPDATABLE && IsValidRow(this)")
	public abstract void updateInt(@Refinement("_ >= 1") int columnIndex, int x) throws SQLException;

	@StateRefinement(from = "open(this) && concurrency(this) == CONCUR_UPDATABLE && IsValidRow(this)")
	public abstract void updateInt(@Refinement("columnLabel != null") String columnLabel, int x) throws SQLException;

	// ========== Updater Methods - Long ========== //

	@StateRefinement(from = "open(this) && concurrency(this) == CONCUR_UPDATABLE && IsValidRow(this)")
	public abstract void updateLong(@Refinement("_ >= 1") int columnIndex, long x) throws SQLException;

	@StateRefinement(from = "open(this) && concurrency(this) == CONCUR_UPDATABLE && IsValidRow(this)")
	public abstract void updateLong(@Refinement("columnLabel != null") String columnLabel, long x) throws SQLException;

	// ========== Updater Methods - Float ========== //

	@StateRefinement(from = "open(this) && concurrency(this) == CONCUR_UPDATABLE && IsValidRow(this)")
	public abstract void updateFloat(@Refinement("_ >= 1") int columnIndex, float x) throws SQLException;

	@StateRefinement(from = "open(this) && concurrency(this) == CONCUR_UPDATABLE && IsValidRow(this)")
	public abstract void updateFloat(@Refinement("columnLabel != null") String columnLabel, float x) throws SQLException;

	// ========== Updater Methods - Double ========== //

	@StateRefinement(from = "open(this) && concurrency(this) == CONCUR_UPDATABLE && IsValidRow(this)")
	public abstract void updateDouble(@Refinement("_ >= 1") int columnIndex, double x) throws SQLException;

	@StateRefinement(from = "open(this) && concurrency(this) == CONCUR_UPDATABLE && IsValidRow(this)")
	public abstract void updateDouble(@Refinement("columnLabel != null") String columnLabel, double x) throws SQLException;

	// ========== Updater Methods - BigDecimal ========== //

	@StateRefinement(from = "open(this) && concurrency(this) == CONCUR_UPDATABLE && IsValidRow(this)")
	public abstract void updateBigDecimal(@Refinement("_ >= 1") int columnIndex, @Refinement("x != null") BigDecimal x) throws SQLException;

	@StateRefinement(from = "open(this) && concurrency(this) == CONCUR_UPDATABLE && IsValidRow(this)")
	public abstract void updateBigDecimal(@Refinement("columnLabel != null") String columnLabel, @Refinement("x != null") BigDecimal x) throws SQLException;

	// ========== Updater Methods - String ========== //

	@StateRefinement(from = "open(this) && concurrency(this) == CONCUR_UPDATABLE && IsValidRow(this)")
	public abstract void updateString(@Refinement("_ >= 1") int columnIndex, @Refinement("x != null") String x) throws SQLException;

	@StateRefinement(from = "open(this) && concurrency(this) == CONCUR_UPDATABLE && IsValidRow(this)")
	public abstract void updateString(@Refinement("columnLabel != null") String columnLabel, @Refinement("x != null") String x) throws SQLException;

	@StateRefinement(from = "open(this) && concurrency(this) == CONCUR_UPDATABLE && IsValidRow(this)")
	public abstract void updateNString(@Refinement("_ >= 1") int columnIndex, @Refinement("nString != null") String nString) throws SQLException;

	@StateRefinement(from = "open(this) && concurrency(this) == CONCUR_UPDATABLE && IsValidRow(this)")
	public abstract void updateNString(@Refinement("columnLabel != null") String columnLabel, @Refinement("nString != null") String nString) throws SQLException;

	// ========== Updater Methods - Bytes ========== //

	@StateRefinement(from = "open(this) && concurrency(this) == CONCUR_UPDATABLE && IsValidRow(this)")
	public abstract void updateBytes(@Refinement("_ >= 1") int columnIndex, @Refinement("x != null") byte[] x) throws SQLException;

	@StateRefinement(from = "open(this) && concurrency(this) == CONCUR_UPDATABLE && IsValidRow(this)")
	public abstract void updateBytes(@Refinement("columnLabel != null") String columnLabel, @Refinement("x != null") byte[] x) throws SQLException;

	// ========== Updater Methods - Date ========== //

	@StateRefinement(from = "open(this) && concurrency(this) == CONCUR_UPDATABLE && IsValidRow(this)")
	public abstract void updateDate(@Refinement("_ >= 1") int columnIndex, @Refinement("x != null") Date x) throws SQLException;

	@StateRefinement(from = "open(this) && concurrency(this) == CONCUR_UPDATABLE && IsValidRow(this)")
	public abstract void updateDate(@Refinement("columnLabel != null") String columnLabel, @Refinement("x != null") Date x) throws SQLException;

	// ========== Updater Methods - Time ========== //

	@StateRefinement(from = "open(this) && concurrency(this) == CONCUR_UPDATABLE && IsValidRow(this)")
	public abstract void updateTime(@Refinement("_ >= 1") int columnIndex, @Refinement("x != null") Time x) throws SQLException;

	@StateRefinement(from = "open(this) && concurrency(this) == CONCUR_UPDATABLE && IsValidRow(this)")
	public abstract void updateTime(@Refinement("columnLabel != null") String columnLabel, @Refinement("x != null") Time x) throws SQLException;

	// ========== Updater Methods - Timestamp ========== //

	@StateRefinement(from = "open(this) && concurrency(this) == CONCUR_UPDATABLE && IsValidRow(this)")
	public abstract void updateTimestamp(@Refinement("_ >= 1") int columnIndex, @Refinement("x != null") Timestamp x) throws SQLException;

	@StateRefinement(from = "open(this) && concurrency(this) == CONCUR_UPDATABLE && IsValidRow(this)")
	public abstract void updateTimestamp(@Refinement("columnLabel != null") String columnLabel, @Refinement("x != null") Timestamp x) throws SQLException;

	// ========== Updater Methods - Streams ========== //

	@StateRefinement(from = "open(this) && concurrency(this) == CONCUR_UPDATABLE && IsValidRow(this)")
	public abstract void updateAsciiStream(@Refinement("_ >= 1") int columnIndex, @Refinement("x != null") InputStream x, @Refinement("_ >= 0") int length) throws SQLException;

	@StateRefinement(from = "open(this) && concurrency(this) == CONCUR_UPDATABLE && IsValidRow(this)")
	public abstract void updateAsciiStream(@Refinement("_ >= 1") int columnIndex, @Refinement("x != null") InputStream x, @Refinement("_ >= 0") long length) throws SQLException;

	@StateRefinement(from = "open(this) && concurrency(this) == CONCUR_UPDATABLE && IsValidRow(this)")
	public abstract void updateAsciiStream(@Refinement("_ >= 1") int columnIndex, @Refinement("x != null") InputStream x) throws SQLException;

	@StateRefinement(from = "open(this) && concurrency(this) == CONCUR_UPDATABLE && IsValidRow(this)")
	public abstract void updateAsciiStream(@Refinement("columnLabel != null") String columnLabel, @Refinement("x != null") InputStream x, @Refinement("_ >= 0") int length) throws SQLException;

	@StateRefinement(from = "open(this) && concurrency(this) == CONCUR_UPDATABLE && IsValidRow(this)")
	public abstract void updateAsciiStream(@Refinement("columnLabel != null") String columnLabel, @Refinement("x != null") InputStream x, @Refinement("_ >= 0") long length) throws SQLException;

	@StateRefinement(from = "open(this) && concurrency(this) == CONCUR_UPDATABLE && IsValidRow(this)")
	public abstract void updateAsciiStream(@Refinement("columnLabel != null") String columnLabel, @Refinement("x != null") InputStream x) throws SQLException;

	@StateRefinement(from = "open(this) && concurrency(this) == CONCUR_UPDATABLE && IsValidRow(this)")
	public abstract void updateBinaryStream(@Refinement("_ >= 1") int columnIndex, @Refinement("x != null") InputStream x, @Refinement("_ >= 0") int length) throws SQLException;

	@StateRefinement(from = "open(this) && concurrency(this) == CONCUR_UPDATABLE && IsValidRow(this)")
	public abstract void updateBinaryStream(@Refinement("_ >= 1") int columnIndex, @Refinement("x != null") InputStream x, @Refinement("_ >= 0") long length) throws SQLException;

	@StateRefinement(from = "open(this) && concurrency(this) == CONCUR_UPDATABLE && IsValidRow(this)")
	public abstract void updateBinaryStream(@Refinement("_ >= 1") int columnIndex, @Refinement("x != null") InputStream x) throws SQLException;

	@StateRefinement(from = "open(this) && concurrency(this) == CONCUR_UPDATABLE && IsValidRow(this)")
	public abstract void updateBinaryStream(@Refinement("columnLabel != null") String columnLabel, @Refinement("x != null") InputStream x, @Refinement("_ >= 0") int length) throws SQLException;

	@StateRefinement(from = "open(this) && concurrency(this) == CONCUR_UPDATABLE && IsValidRow(this)")
	public abstract void updateBinaryStream(@Refinement("columnLabel != null") String columnLabel, @Refinement("x != null") InputStream x, @Refinement("_ >= 0") long length) throws SQLException;

	@StateRefinement(from = "open(this) && concurrency(this) == CONCUR_UPDATABLE && IsValidRow(this)")
	public abstract void updateBinaryStream(@Refinement("columnLabel != null") String columnLabel, @Refinement("x != null") InputStream x) throws SQLException;

	@StateRefinement(from = "open(this) && concurrency(this) == CONCUR_UPDATABLE && IsValidRow(this)")
	public abstract void updateCharacterStream(@Refinement("_ >= 1") int columnIndex, @Refinement("x != null") Reader reader, @Refinement("_ >= 0") int length) throws SQLException;

	@StateRefinement(from = "open(this) && concurrency(this) == CONCUR_UPDATABLE && IsValidRow(this)")
	public abstract void updateCharacterStream(@Refinement("_ >= 1") int columnIndex, @Refinement("x != null") Reader reader, @Refinement("_ >= 0") long length) throws SQLException;

	@StateRefinement(from = "open(this) && concurrency(this) == CONCUR_UPDATABLE && IsValidRow(this)")
	public abstract void updateCharacterStream(@Refinement("_ >= 1") int columnIndex, @Refinement("x != null") Reader reader) throws SQLException;

	@StateRefinement(from = "open(this) && concurrency(this) == CONCUR_UPDATABLE && IsValidRow(this)")
	public abstract void updateCharacterStream(@Refinement("columnLabel != null") String columnLabel, @Refinement("x != null") Reader reader, @Refinement("_ >= 0") int length) throws SQLException;

	@StateRefinement(from = "open(this) && concurrency(this) == CONCUR_UPDATABLE && IsValidRow(this)")
	public abstract void updateCharacterStream(@Refinement("columnLabel != null") String columnLabel, @Refinement("x != null") Reader reader, @Refinement("_ >= 0") long length) throws SQLException;

	@StateRefinement(from = "open(this) && concurrency(this) == CONCUR_UPDATABLE && IsValidRow(this)")
	public abstract void updateCharacterStream(@Refinement("columnLabel != null") String columnLabel, @Refinement("x != null") Reader reader) throws SQLException;

	@StateRefinement(from = "open(this) && concurrency(this) == CONCUR_UPDATABLE && IsValidRow(this)")
	public abstract void updateNCharacterStream(@Refinement("_ >= 1") int columnIndex, @Refinement("x != null") Reader value, @Refinement("_ >= 0") long length) throws SQLException;

	@StateRefinement(from = "open(this) && concurrency(this) == CONCUR_UPDATABLE && IsValidRow(this)")
	public abstract void updateNCharacterStream(@Refinement("_ >= 1") int columnIndex, @Refinement("x != null") Reader value) throws SQLException;

	@StateRefinement(from = "open(this) && concurrency(this) == CONCUR_UPDATABLE && IsValidRow(this)")
	public abstract void updateNCharacterStream(@Refinement("columnLabel != null") String columnLabel, @Refinement("reader != null") Reader reader, @Refinement("_ >= 0") long length) throws SQLException;

	@StateRefinement(from = "open(this) && concurrency(this) == CONCUR_UPDATABLE && IsValidRow(this)")
	public abstract void updateNCharacterStream(@Refinement("columnLabel != null") String columnLabel, @Refinement("reader != null") Reader reader) throws SQLException;

	// ========== Updater Methods - Object ========== //

	@StateRefinement(from = "open(this) && concurrency(this) == CONCUR_UPDATABLE && IsValidRow(this)")
	public abstract void updateObject(@Refinement("_ >= 1") int columnIndex, @Refinement("x != null") Object x) throws SQLException;

	@StateRefinement(from = "open(this) && concurrency(this) == CONCUR_UPDATABLE && IsValidRow(this)")
	public abstract void updateObject(@Refinement("_ >= 1") int columnIndex, @Refinement("x != null") Object x, @Refinement("_ >= 0") int scaleOrLength) throws SQLException;

	@StateRefinement(from = "open(this) && concurrency(this) == CONCUR_UPDATABLE && IsValidRow(this)")
	public abstract void updateObject(@Refinement("_ >= 1") int columnIndex, @Refinement("x != null") Object x, @Refinement("targetSqlType != null") SQLType targetSqlType) throws SQLException;

	@StateRefinement(from = "open(this) && concurrency(this) == CONCUR_UPDATABLE && IsValidRow(this)")
	public abstract void updateObject(@Refinement("_ >= 1") int columnIndex, @Refinement("x != null") Object x, @Refinement("targetSqlType != null") SQLType targetSqlType, @Refinement("_ >= 0") int scaleOrLength) throws SQLException;

	@StateRefinement(from = "open(this) && concurrency(this) == CONCUR_UPDATABLE && IsValidRow(this)")
	public abstract void updateObject(@Refinement("columnLabel != null") String columnLabel, @Refinement("x != null") Object x) throws SQLException;

	@StateRefinement(from = "open(this) && concurrency(this) == CONCUR_UPDATABLE && IsValidRow(this)")
	public abstract void updateObject(@Refinement("columnLabel != null") String columnLabel, @Refinement("x != null") Object x, @Refinement("_ >= 0") int scaleOrLength) throws SQLException;

	@StateRefinement(from = "open(this) && concurrency(this) == CONCUR_UPDATABLE && IsValidRow(this)")
	public abstract void updateObject(@Refinement("columnLabel != null") String columnLabel, @Refinement("x != null") Object x, @Refinement("targetSqlType != null") SQLType targetSqlType) throws SQLException;

	@StateRefinement(from = "open(this) && concurrency(this) == CONCUR_UPDATABLE && IsValidRow(this)")
	public abstract void updateObject(@Refinement("columnLabel != null") String columnLabel, @Refinement("x != null") Object x, @Refinement("targetSqlType != null") SQLType targetSqlType, @Refinement("_ >= 0") int scaleOrLength) throws SQLException;

	// ========== Updater Methods - Ref, Blob, Clob, Array, RowId, SQLXML ========== //

	@StateRefinement(from = "open(this) && concurrency(this) == CONCUR_UPDATABLE && IsValidRow(this)")
	public abstract void updateRef(@Refinement("_ >= 1") int columnIndex, @Refinement("ref != null") Ref ref) throws SQLException;

	@StateRefinement(from = "open(this) && concurrency(this) == CONCUR_UPDATABLE && IsValidRow(this)")
	public abstract void updateRef(@Refinement("columnLabel != null") String columnLabel, @Refinement("ref != null") Ref ref) throws SQLException;

	@StateRefinement(from = "open(this) && concurrency(this) == CONCUR_UPDATABLE && IsValidRow(this)")
	public abstract void updateBlob(@Refinement("_ >= 1") int columnIndex, @Refinement("blob != null") Blob blob) throws SQLException;

	@StateRefinement(from = "open(this) && concurrency(this) == CONCUR_UPDATABLE && IsValidRow(this)")
	public abstract void updateBlob(@Refinement("_ >= 1") int columnIndex, @Refinement("inputStream != null") InputStream inputStream, @Refinement("_ >= 0") long length) throws SQLException;

	@StateRefinement(from = "open(this) && concurrency(this) == CONCUR_UPDATABLE && IsValidRow(this)")
	public abstract void updateBlob(@Refinement("_ >= 1") int columnIndex, @Refinement("inputStream != null") InputStream inputStream) throws SQLException;

	@StateRefinement(from = "open(this) && concurrency(this) == CONCUR_UPDATABLE && IsValidRow(this)")
	public abstract void updateBlob(@Refinement("columnLabel != null") String columnLabel, @Refinement("blob != null") Blob blob) throws SQLException;

	@StateRefinement(from = "open(this) && concurrency(this) == CONCUR_UPDATABLE && IsValidRow(this)")
	public abstract void updateBlob(@Refinement("columnLabel != null") String columnLabel, @Refinement("inputStream != null") InputStream inputStream, @Refinement("_ >= 0") long length) throws SQLException;

	@StateRefinement(from = "open(this) && concurrency(this) == CONCUR_UPDATABLE && IsValidRow(this)")
	public abstract void updateBlob(@Refinement("columnLabel != null") String columnLabel, @Refinement("inputStream != null") InputStream inputStream) throws SQLException;

	@StateRefinement(from = "open(this) && concurrency(this) == CONCUR_UPDATABLE && IsValidRow(this)")
	public abstract void updateClob(@Refinement("_ >= 1") int columnIndex, @Refinement("clob != null") Clob clob) throws SQLException;

	@StateRefinement(from = "open(this) && concurrency(this) == CONCUR_UPDATABLE && IsValidRow(this)")
	public abstract void updateClob(@Refinement("_ >= 1") int columnIndex, @Refinement("reader != null") Reader reader, @Refinement("_ >= 0") long length) throws SQLException;

	@StateRefinement(from = "open(this) && concurrency(this) == CONCUR_UPDATABLE && IsValidRow(this)")
	public abstract void updateClob(@Refinement("_ >= 1") int columnIndex, @Refinement("reader != null") Reader reader) throws SQLException;

	@StateRefinement(from = "open(this) && concurrency(this) == CONCUR_UPDATABLE && IsValidRow(this)")
	public abstract void updateClob(@Refinement("columnLabel != null") String columnLabel, @Refinement("clob != null") Clob clob) throws SQLException;

	@StateRefinement(from = "open(this) && concurrency(this) == CONCUR_UPDATABLE && IsValidRow(this)")
	public abstract void updateClob(@Refinement("columnLabel != null") String columnLabel, @Refinement("reader != null") Reader reader, @Refinement("_ >= 0") long length) throws SQLException;

	@StateRefinement(from = "open(this) && concurrency(this) == CONCUR_UPDATABLE && IsValidRow(this)")
	public abstract void updateClob(@Refinement("columnLabel != null") String columnLabel, @Refinement("reader != null") Reader reader) throws SQLException;

	@StateRefinement(from = "open(this) && concurrency(this) == CONCUR_UPDATABLE && IsValidRow(this)")
	public abstract void updateNClob(@Refinement("_ >= 1") int columnIndex, @Refinement("nClob != null") NClob nClob) throws SQLException;

	@StateRefinement(from = "open(this) && concurrency(this) == CONCUR_UPDATABLE && IsValidRow(this)")
	public abstract void updateNClob(@Refinement("_ >= 1") int columnIndex, @Refinement("reader != null") Reader reader, @Refinement("_ >= 0") long length) throws SQLException;

	@StateRefinement(from = "open(this) && concurrency(this) == CONCUR_UPDATABLE && IsValidRow(this)")
	public abstract void updateNClob(@Refinement("_ >= 1") int columnIndex, @Refinement("reader != null") Reader reader) throws SQLException;

	@StateRefinement(from = "open(this) && concurrency(this) == CONCUR_UPDATABLE && IsValidRow(this)")
	public abstract void updateNClob(@Refinement("columnLabel != null") String columnLabel, @Refinement("nClob != null") NClob nClob) throws SQLException;

	@StateRefinement(from = "open(this) && concurrency(this) == CONCUR_UPDATABLE && IsValidRow(this)")
	public abstract void updateNClob(@Refinement("columnLabel != null") String columnLabel, @Refinement("reader != null") Reader reader, @Refinement("_ >= 0") long length) throws SQLException;

	@StateRefinement(from = "open(this) && concurrency(this) == CONCUR_UPDATABLE && IsValidRow(this)")
	public abstract void updateNClob(@Refinement("columnLabel != null") String columnLabel, @Refinement("reader != null") Reader reader) throws SQLException;

	@StateRefinement(from = "open(this) && concurrency(this) == CONCUR_UPDATABLE && IsValidRow(this)")
	public abstract void updateArray(@Refinement("_ >= 1") int columnIndex, @Refinement("array != null") Array array) throws SQLException;

	@StateRefinement(from = "open(this) && concurrency(this) == CONCUR_UPDATABLE && IsValidRow(this)")
	public abstract void updateArray(@Refinement("columnLabel != null") String columnLabel, @Refinement("array != null") Array array) throws SQLException;

	@StateRefinement(from = "open(this) && concurrency(this) == CONCUR_UPDATABLE && IsValidRow(this)")
	public abstract void updateRowId(@Refinement("_ >= 1") int columnIndex, @Refinement("xid != null") RowId xid) throws SQLException;

	@StateRefinement(from = "open(this) && concurrency(this) == CONCUR_UPDATABLE && IsValidRow(this)")
	public abstract void updateRowId(@Refinement("columnLabel != null") String columnLabel, @Refinement("xid != null") RowId xid) throws SQLException;

	@StateRefinement(from = "open(this) && concurrency(this) == CONCUR_UPDATABLE && IsValidRow(this)")
	public abstract void updateSQLXML(@Refinement("_ >= 1") int columnIndex, @Refinement("xmlObject != null") SQLXML xmlObject) throws SQLException;

	@StateRefinement(from = "open(this) && concurrency(this) == CONCUR_UPDATABLE && IsValidRow(this)")
	public abstract void updateSQLXML(@Refinement("columnLabel != null") String columnLabel, @Refinement("xmlObject != null") SQLXML xmlObject) throws SQLException;

	// ========== Row Operations ========== //

	@StateRefinement(from = "open(this) && concurrency(this) == CONCUR_UPDATABLE && insertRow(this)")
	public abstract void insertRow() throws SQLException;

	@StateRefinement(from = "open(this) && concurrency(this) == CONCUR_UPDATABLE && onRow(this)")
	public abstract void updateRow() throws SQLException;

	@StateRefinement(from = "open(this) && concurrency(this) == CONCUR_UPDATABLE && onRow(this)")
	public abstract void deleteRow() throws SQLException;

	@StateRefinement(from = "open(this) && concurrency(this) == CONCUR_UPDATABLE && onRow(this)")
	public abstract void refreshRow() throws SQLException;

	@StateRefinement(from = "open(this) && concurrency(this) == CONCUR_UPDATABLE && IsValidRow(this)")
	public abstract void cancelRowUpdates() throws SQLException;

	// ========== Metadata and Information Methods ========== //

	@StateRefinement(from = "open(this)")
	public abstract ResultSetMetaData getMetaData() throws SQLException;

	@StateRefinement(from = "open(this)")
	@Refinement("return >= 1")
	public abstract int findColumn(@Refinement("columnLabel != null") String columnLabel) throws SQLException;

	@StateRefinement(from = "open(this)")
	public abstract SQLWarning getWarnings() throws SQLException;

	@StateRefinement(from = "open(this)")
	public abstract void clearWarnings() throws SQLException;

	@StateRefinement(from = "open(this)")
	public abstract String getCursorName() throws SQLException;

	@StateRefinement(from = "open(this)")
	public abstract Statement getStatement() throws SQLException;

	@StateRefinement(from = "open(this)")
	@Refinement("return == TYPE_FORWARD_ONLY || return == TYPE_SCROLL_INSENSITIVE || return == TYPE_SCROLL_SENSITIVE")
	public abstract int getType() throws SQLException;

	@StateRefinement(from = "open(this)")
	@Refinement("return == CONCUR_READ_ONLY || return == CONCUR_UPDATABLE")
	public abstract int getConcurrency() throws SQLException;

	@StateRefinement(from = "open(this)")
	@Refinement("return == HOLD_CURSORS_OVER_COMMIT || return == CLOSE_CURSORS_AT_COMMIT")
	public abstract int getHoldability() throws SQLException;

	@StateRefinement(from = "open(this)")
	@Refinement("return == FETCH_FORWARD || return == FETCH_REVERSE || return == FETCH_UNKNOWN")
	public abstract int getFetchDirection() throws SQLException;

	@StateRefinement(from = "open(this)", to = "fetchDirection(this) == direction")
	public abstract void setFetchDirection(@Refinement("direction == FETCH_FORWARD || direction == FETCH_REVERSE || direction == FETCH_UNKNOWN") int direction) throws SQLException;

	@StateRefinement(from = "open(this)")
	@Refinement("return >= 0")
	public abstract int getFetchSize() throws SQLException;

	@StateRefinement(from = "open(this)", to = "fetchSize(this) == rows")
	public abstract void setFetchSize(@Refinement("_ >= 0") int rows) throws SQLException;

	@StateRefinement(from = "open(this) && concurrency(this) == CONCUR_UPDATABLE && onRow(this)")
	@Refinement("return == true || return == false")
	public abstract boolean rowUpdated() throws SQLException;

	@StateRefinement(from = "open(this) && concurrency(this) == CONCUR_UPDATABLE && onRow(this)")
	@Refinement("return == true || return == false")
	public abstract boolean rowInserted() throws SQLException;

	@StateRefinement(from = "open(this) && concurrency(this) == CONCUR_UPDATABLE && onRow(this)")
	@Refinement("return == true || return == false")
	public abstract boolean rowDeleted() throws SQLException;

	@StateRefinement(from = "open(this) && IsOnDataRow(this)")
	public abstract boolean wasNull() throws SQLException;

	// ========== Close Method ========== //

	@StateRefinement(from = "open(this)", to = "closed(this)")
	public abstract void close() throws SQLException;
}

