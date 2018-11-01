/*******************************************************************************
 * Copyright (c) 2004, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.parsers.sql.query;

import org.eclipse.datatools.modelbase.sql.datatypes.ApproximateNumericDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.ArrayDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.BinaryStringDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.BooleanDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.CharacterStringDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.DataLinkDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.DataType;
import org.eclipse.datatools.modelbase.sql.datatypes.DateDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.FixedPrecisionDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.IntegerDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.IntervalDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.IntervalQualifierType;
import org.eclipse.datatools.modelbase.sql.datatypes.MultisetDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.PrimitiveType;
import org.eclipse.datatools.modelbase.sql.datatypes.TimeDataType;

/**
 * This interface defines methods and constants needed to construct
 * {@link org.eclipse.datatools.modelbase.sql.datatypes.PredefinedDataType}s.
 *
 * TODO: should we enforce restrictions here on values for length, precision, ...
 *
 * @author ckadner
 *
 */
public interface SQLQueryParserFactoryDataTypes
{
	/**
	 * The '<em><b>CHARACTER</b></em>' <code>PrimitiveType</code> constant.
	 *
	 * @see org.eclipse.wst.rdb.internal.models.sql.datatypes.PrimitiveType#CHARACTER
	 */
	public static final int PRIMITIVE_TYPE_CHARACTER = PrimitiveType.CHARACTER;

	/**
	 * The '<em><b>CHARACTER VARYING</b></em>' <code>PrimitiveType</code> constant.
	 *
	 * @see org.eclipse.wst.rdb.internal.models.sql.datatypes.PrimitiveType#CHARACTER_VARYING
	 */
	public static final int PRIMITIVE_TYPE_CHARACTER_VARYING = PrimitiveType.CHARACTER_VARYING;

	/**
	 * The '<em><b>CHARACTER LARGE OBJECT</b></em>' <code>PrimitiveType</code> constant.
	 *
	 * @see org.eclipse.wst.rdb.internal.models.sql.datatypes.PrimitiveType#CHARACTER_LARGE_OBJECT
	 */
	public static final int PRIMITIVE_TYPE_CHARACTER_LARGE_OBJECT = PrimitiveType.CHARACTER_LARGE_OBJECT;

	/**
	 * The '<em><b>NATIONAL CHARACTER</b></em>' <code>PrimitiveType</code> constant.
	 *
	 * @see org.eclipse.wst.rdb.internal.models.sql.datatypes.PrimitiveType#NATIONAL_CHARACTER
	 */
	public static final int PRIMITIVE_TYPE_NATIONAL_CHARACTER = PrimitiveType.NATIONAL_CHARACTER;

	/**
	 * The '<em><b>NATIONAL CHARACTER VARYING</b></em>' <code>PrimitiveType</code> constant.
	 *
	 * @see org.eclipse.wst.rdb.internal.models.sql.datatypes.PrimitiveType#NATIONAL_CHARACTER_VARYING
	 */
	public static final int PRIMITIVE_TYPE_NATIONAL_CHARACTER_VARYING = PrimitiveType.NATIONAL_CHARACTER_VARYING;

	/**
	 * The '<em><b>NATIONAL CHARACTER LARGE OBJECT</b></em>' <code>PrimitiveType</code> constant.
	 *
	 * @see org.eclipse.wst.rdb.internal.models.sql.datatypes.PrimitiveType#NATIONAL_CHARACTER_LARGE_OBJECT
	 */
	public static final int PRIMITIVE_TYPE_NATIONAL_CHARACTER_LARGE_OBJECT = PrimitiveType.NATIONAL_CHARACTER_LARGE_OBJECT;

	/**
	 * The '<em><b>BINARY</b></em>' <code>PrimitiveType</code> constant.
	 *
	 * @see org.eclipse.wst.rdb.internal.models.sql.datatypes.PrimitiveType#BINARY
	 */
	public static final int PRIMITIVE_TYPE_BINARY = PrimitiveType.BINARY;

	/**
	 * The '<em><b>BINARY VARYING</b></em>' <code>PrimitiveType</code> constant.
	 *
	 * @see org.eclipse.wst.rdb.internal.models.sql.datatypes.PrimitiveType#BINARY_VARYING
	 */
	public static final int PRIMITIVE_TYPE_BINARY_VARYING = PrimitiveType.BINARY_VARYING;

	/**
	 * The '<em><b>BINARY LARGE OBJECT</b></em>' <code>PrimitiveType</code> constant.
	 *
	 * @see org.eclipse.wst.rdb.internal.models.sql.datatypes.PrimitiveType#BINARY_LARGE_OBJECT
	 */
	public static final int PRIMITIVE_TYPE_BINARY_LARGE_OBJECT = PrimitiveType.BINARY_LARGE_OBJECT;

	/**
	 * The '<em><b>NUMERIC</b></em>' <code>PrimitiveType</code> constant.
	 *
	 * @see org.eclipse.wst.rdb.internal.models.sql.datatypes.PrimitiveType#NUMERIC
	 */
	public static final int PRIMITIVE_TYPE_NUMERIC = PrimitiveType.NUMERIC;

	/**
	 * The '<em><b>DECIMAL</b></em>' <code>PrimitiveType</code> constant.
	 *
	 * @see org.eclipse.wst.rdb.internal.models.sql.datatypes.PrimitiveType#DECIMAL
	 */
	public static final int PRIMITIVE_TYPE_DECIMAL = PrimitiveType.DECIMAL;

	/**
	 * The '<em><b>SMALLINT</b></em>' <code>PrimitiveType</code> constant.
	 *
	 * @see org.eclipse.wst.rdb.internal.models.sql.datatypes.PrimitiveType#SMALLINT
	 */
	public static final int PRIMITIVE_TYPE_SMALLINT = PrimitiveType.SMALLINT;

	/**
	 * The '<em><b>INTEGER</b></em>' <code>PrimitiveType</code> constant.
	 *
	 * @see org.eclipse.wst.rdb.internal.models.sql.datatypes.PrimitiveType#INTEGER
	 */
	public static final int PRIMITIVE_TYPE_INTEGER = PrimitiveType.INTEGER;

	/**
	 * The '<em><b>BIGINT</b></em>' <code>PrimitiveType</code> constant.
	 *
	 * @see org.eclipse.wst.rdb.internal.models.sql.datatypes.PrimitiveType#BIGINT
	 */
	public static final int PRIMITIVE_TYPE_BIGINT = PrimitiveType.BIGINT;

	/**
	 * The '<em><b>FLOAT</b></em>' <code>PrimitiveType</code> constant.
	 *
	 * @see org.eclipse.wst.rdb.internal.models.sql.datatypes.PrimitiveType#FLOAT
	 */
	public static final int PRIMITIVE_TYPE_FLOAT = PrimitiveType.FLOAT;

	/**
	 * The '<em><b>REAL</b></em>' <code>PrimitiveType</code> constant.
	 *
	 * @see org.eclipse.wst.rdb.internal.models.sql.datatypes.PrimitiveType#REAL
	 */
	public static final int PRIMITIVE_TYPE_REAL = PrimitiveType.REAL;

	/**
	 * The '<em><b>DOUBLE PRECISION</b></em>' <code>PrimitiveType</code> constant.
	 *
	 * @see org.eclipse.wst.rdb.internal.models.sql.datatypes.PrimitiveType#DOUBLE_PRECISION
	 */
	public static final int PRIMITIVE_TYPE_DOUBLE_PRECISION = PrimitiveType.DOUBLE_PRECISION;

	/**
	 * The '<em><b>BOOLEAN</b></em>' <code>PrimitiveType</code> constant.
	 *
	 * @see org.eclipse.wst.rdb.internal.models.sql.datatypes.PrimitiveType#BOOLEAN
	 */
	public static final int PRIMITIVE_TYPE_BOOLEAN = PrimitiveType.BOOLEAN;

	/**
	 * The '<em><b>DATE</b></em>' <code>PrimitiveType</code> constant.
	 *
	 * @see org.eclipse.wst.rdb.internal.models.sql.datatypes.PrimitiveType#DATE
	 */
	public static final int PRIMITIVE_TYPE_DATE = PrimitiveType.DATE;

	/**
	 * The '<em><b>TIME</b></em>' <code>PrimitiveType</code> constant.
	 *
	 * @see org.eclipse.wst.rdb.internal.models.sql.datatypes.PrimitiveType#TIME
	 */
	public static final int PRIMITIVE_TYPE_TIME = PrimitiveType.TIME;

	/**
	 * The '<em><b>TIMESTAMP</b></em>' <code>PrimitiveType</code> constant.
	 *
	 * @see org.eclipse.wst.rdb.internal.models.sql.datatypes.PrimitiveType#TIMESTAMP
	 */
	public static final int PRIMITIVE_TYPE_TIMESTAMP = PrimitiveType.TIMESTAMP;

	/**
	 * The '<em><b>INTERVAL</b></em>' <code>PrimitiveType</code> constant.
	 *
	 * @see org.eclipse.wst.rdb.internal.models.sql.datatypes.PrimitiveType#INTERVAL
	 */
	public static final int PRIMITIVE_TYPE_INTERVAL = PrimitiveType.INTERVAL;

	/**
	 * The '<em><b>DATALINK</b></em>' <code>PrimitiveType</code> constant.
	 *
	 * @see org.eclipse.wst.rdb.internal.models.sql.datatypes.PrimitiveType#DATALINK
	 */
	public static final int PRIMITIVE_TYPE_DATALINK = PrimitiveType.DATALINK;

	/**
	 * The one character String constant '<em><b>K</b></em>' representing the
	 * unit Kilobyte.
	 */
	public static final String UNIT_INDICATOR_K = "K"; //$NON-NLS-1$

	/**
	 * The one character String constant '<em><b>M</b></em>' representing the
	 * unit Megabyte.
	 */
	public static final String UNIT_INDICATOR_M = "M"; //$NON-NLS-1$

	/**
	 * The one character String constant '<em><b>G</b></em>' representing the
	 * unit Gigabyte.
	 */
	public static final String UNIT_INDICATOR_G = "G"; //$NON-NLS-1$


	/* ****************************************** public methods ************ */

    /**
     * Factory method to construct a <code>ArrayDataType</code> with the
     * element data type <code>elementDataType</code>.
     * 
     * @param elementDataType
     * 
     * @return the newly constructed <code>ArrayDataType</code>
     */
    public ArrayDataType createDataTypeArray(DataType elementDataType);

    /**
     * Factory method to construct a <code>ArrayDataType</code> with the
     * element data type <code>elementDataType</code> and with maximum
     * cardinality specified by <code>maxCardinality</code>.
     * 
     * @param elementDataType
     * @param maxCardinality
     * @return the newly constructed <code>ArrayDataType</code>
     */
    public ArrayDataType createDataTypeArray(DataType elementDataType, int maxCardinality);


	/**
     * Factory method to construct a <code>CharacterStringDataType</code> with
     * its <code>primitiveType</code> and <code>length</code>.
     * <p>
     * <b>Note: </b> The optional parameter <code>optionalUnitsIndicator</code>
     * may only be specified with the given <code>primitiveType</code>
     * {@link #PRIMITIVE_TYPE_CHARACTER_LARGE_OBJECT}or
     * {@link #PRIMITIVE_TYPE_NATIONAL_CHARACTER_LARGE_OBJECT}, where
     * {@link #UNIT_INDICATOR_K}measures the given <code>length</code> in
     * Kilobyte (for the given <code>length</code><= 2097152),
     * {@link #UNIT_INDICATOR_M}measures the given <code>length</code> in
     * Megabyte (for the given <code>length</code><= 2048),
     * {@link #UNIT_INDICATOR_G}measures the given <code>length</code> in
     * Gigabyte (for the given <code>length</code><= 2).
     * 
     * <p>
     * <b>Note: </b> The given <code>primitiveType</code> must be one of the
     * constants for the primitve types in this interface/class correlated to
     * the <code>CharacterStringDataType</code>.
     * 
     * @see #PRIMITIVE_TYPE_CHARACTER
     * @see #PRIMITIVE_TYPE_CHARACTER_VARYING
     * @see #PRIMITIVE_TYPE_CHARACTER_LARGE_OBJECT
     * @see #PRIMITIVE_TYPE_NATIONAL_CHARACTER
     * @see #PRIMITIVE_TYPE_NATIONAL_CHARACTER_VARYING
     * @see #PRIMITIVE_TYPE_NATIONAL_CHARACTER_LARGE_OBJECT
     * 
     * @param primitiveType one of the correlated the primitive types for the
     *        CharacterStringDataType
     * @param length the length of this CharacterStringDataType
     * @param optionalUnitsIndicator optional parameter indicating the given
     *        <code>length</code> is measured in Kilobyte (use
     *        {@link #UNIT_INDICATOR_K}), Megabyte (use
     *        {@link #UNIT_INDICATOR_M}) or Gigabyte (use
     *        {@link #UNIT_INDICATOR_G})
     * 
     * @return the newly constructed CharacterStringDataType
     */
	CharacterStringDataType createDataTypeCharacterString(int primitiveType, int length, String optionalUnitsIndicator);




    /**
	 * Factory method to construct a <code>BooleanDataType</code> with
	 * <code>primitiveType</code> {@link PrimitiveType.BOOLEAN_LITERAL}.
	 *
	 * @return the newly constructed BooleanDataType
     */
    public BooleanDataType createDataTypeBoolean();


	/**
	 * Factory method to construct a <code>BinaryStringDataType</code> with
	 * its <code>primitiveType</code> and <code>length</code>.
	 * <p>
	 * <b>Note:</b>
	 * The optional parameter <code>optionalUnitsIndicator</code> may only be
	 * specified with the given <code>primitiveType</code>
	 * {@link #PRIMITIVE_TYPE_BINARY_LARGE_OBJECT}, where
	 * {@link #UNIT_INDICATOR_K} measures the given <code>length</code>
	 * in Kilobyte (for the given <code>length</code> <= 2097152),
	 * {@link #UNIT_INDICATOR_M} measures the given <code>length</code>
	 * in Megabyte (for the given <code>length</code> <= 2048),
	 * {@link #UNIT_INDICATOR_G} measures the given <code>length</code>
	 * in Gigabyte (for the given <code>length</code> <= 2).
	 * 
	 * <p>
	 * <b>Note</b>:
	 * The given <code>primitiveType</code> must be one of the constants for the
	 * primitve types in this interface/class correlated to the
	 * <code>BinaryStringDataType</code>.
	 * @see #PRIMITIVE_TYPE_BINARY
	 * @see #PRIMITIVE_TYPE_BINARY_VARYING
	 * @see #PRIMITIVE_TYPE_BINARY_LARGE_OBJECT
	 *
	 * @param primitiveType one of the correlated the primitive types for
	 *   the BinaryStringDataType
	 * @param length the length of this BinaryStringDataType
	 * @param optionalUnitsIndicator 
	 * 		optional parameter indicating the given <code>length</code>
	 * 		is measured in Kilobyte (use {@link #UNIT_INDICATOR_K}),
	 * 		Megabyte (use {@link #UNIT_INDICATOR_M}) or 
	 * 		Gigabyte (use {@link #UNIT_INDICATOR_G})
	 * 
	 * @return the newly constructed BinaryStringDataType
	 */
    public BinaryStringDataType createDataTypeBinaryString(int primitiveType,
                                                           int length,
                                                           String optionalUnitsIndicator);


    /**
     * TODO: finish method signature and doc properly!
     * @param length
     * @return
     */
    public DataLinkDataType createDataTypeDataLink(int length);


	/**
	 * Factory method to construct a <code>IntervalDataType</code> with
	 * <code>primitiveType</code> {@link PrimitiveType#INTERVAL}.
	 *
	 * @param leadingQualifier
	 * @param trailingQualifier
	 * @param leadingFieldPrecision
	 * @param trailingFieldPrecision
	 * @param fractionalSecondsPrecision
	 * @return
	 */
    public IntervalDataType createDataTypeInterval(
                                                   IntervalQualifierType leadingQualifier,
                                                   IntervalQualifierType trailingQualifier,
                                                   int leadingFieldPrecision,
                                                   int trailingFieldPrecision,
                                                   int fractionalSecondsPrecision);


	/**
	 * Factory method to construct a <code>TimeDataType</code> with
	 * its <code>primitiveType</code> and <code>length</code>.
	 * <p>
	 * <b>Note</b>:
	 * The given <code>primitiveType</code> must be one of the constants for the
	 * primitve types in this interface/class correlated to the
	 * <code>TimeDataType</code>.
	 * @see #PRIMITIVE_TYPE_TIME
	 * @see #PRIMITIVE_TYPE_TIMESTAMP
	 * <p>
	 * <b>Note:</b>
	 * If the optional parameter <code>fractionalSecondsPrecision</code> is not
	 * specified, the default value for the given <code>primitiveType</code>
	 * {@link #PRIMITIVE_TYPE_TIME} is 0 and for <code>primitiveType</code>
	 * {@link #PRIMITIVE_TYPE_TIMESTAMP} is 6. 
	 *
	 * @param primitiveType one of the correlated the primitive types for
	 *   the TimeDataType {@link #PRIMITIVE_TYPE_TIME},
	 *  {@link #PRIMITIVE_TYPE_TIMESTAMP}
	 * @param fractionalSecondsPrecision optional precision in fractional seconds of
	 * 		this TimeDataType 
	 * 		({@link TimeDataType#setFractionalSecondsPrecision(int)}), if not
	 * 		specified default value	for given <code>primitiveType</code> 
	 * 	  	{@link #PRIMITIVE_TYPE_TIME} is 0 and for 
	 * 		{@link #PRIMITIVE_TYPE_TIMESTAMP} is 6 
	 * 		
	 * @return the newly constructed TimeDataType
	 */
     public TimeDataType createDataTypeTime(int primitiveType, int fractionalSecondsPrecision);


    /**
	 * Factory method to construct a <code>DateDataType</code> with
	 * <code>primitiveType</code> <code>PrimitiveType.DATE_LITERAL</code>.
	 * @see #PRIMITIVE_TYPE_DATE
	 *
	 * @return the newly constructed DateDataType
     */
    public DateDataType createDataTypeDate();

    /**
     * Factory method to construct a <code>MultisetDataType</code> with the
     * element data type <code>elementDataType</code>.
     * 
     * @param elementDataType
     * 
     * @return the newly constructed <code>MultisetDataType</code>
     */
    public MultisetDataType createDataTypeMultiset(DataType elementDataType);



    //TODO: add the the restrictions and DEFAULT values on the precision value
	/**
	 * Factory method to construct a <code>FixedPrecisionDataType</code> with
	 * its <code>primitiveType</code>, <code>precision</code> and <code>scale</code>.
	 * <b>Note</b>:
	 * The given <code>primitiveType</code> must be one of the constants for the
	 * primitve types in this interface/class correlated to the
	 * <code>FixedPrecisionDataType</code>.
	 * @see #PRIMITIVE_TYPE_NUMERIC
	 * @see #PRIMITIVE_TYPE_DECIMAL
	 *
	 * @param primitiveType one of the correlated the primitive types for
	 *   	the FixedPrecisionDataType {@link #PRIMITIVE_TYPE_NUMERIC},
	 *  	{@link #PRIMITIVE_TYPE_DECIMAL}
	 * @param precision
	 * 		the total number of digits
	 * 		{@link org.eclipse.wst.rdb.internal.models.sql.datatypes.NumericalDataType#setPrecision(int)} ranges between 1 and 31
	 * 
	 * @param scale
	 * 		the number of digits to the right of the decimal point,
	 * 		ranges between 0 and precision
	 * 		{@link org.eclipse.wst.rdb.internal.models.sql.datatypes.ExactNumericDataType#setScale(int)}
	 * @return the newly constructed FixedPrecisionDataType
	 */
    public FixedPrecisionDataType createDataTypeNumericFixedPrecision(int primitiveType,
                                                                      int precision,
                                                                      int scale);





	/**
	 * Factory method to construct a <code>IntegerDataType</code> with
	 * its <code>primitiveType</code>. The <code>precision</code> is implicitly
	 * given for the <code>primitiveType</code>s
	 * {@link #PRIMITIVE_TYPE_SMALLINT} (5) and
	 * {@link #PRIMITIVE_TYPE_INTEGER} (10). The <code>precision</code>  has to
	 * be explicitely specified for the <code>primitiveType</code>
	 * {@link #PRIMITIVE_TYPE_BIGINT} or will be set to 10 by default.
	 * <b>Note</b>:
	 * The given <code>primitiveType</code> must be one of the constants for the
	 * primitve types in this interface/class correlated to the
	 * <code>intDataType</code>.
	 * @see #PRIMITIVE_TYPE_SMALLINT
	 * @see #PRIMITIVE_TYPE_INTEGER
	 * @see #PRIMITIVE_TYPE_BIGINT
	 *
	 * @param primitiveType one of the correlated the primitive types for
	 *   	the intDataType {@link #PRIMITIVE_TYPE_SMALLINT} precision is 5,
	 *  	{@link #PRIMITIVE_TYPE_INTEGER} precision is 10,
	 * 		{@link #PRIMITIVE_TYPE_BIGINT} precision has to be given, default is 10
	 * @param precision
	 * 		the total number of digits for given <code>primitiveType</code>
	 * 		{@link #PRIMITIVE_TYPE_BIGINT}, default is 10
	 * 		{@link org.eclipse.wst.rdb.internal.models.sql.datatypes.NumericalDataType#setPrecision(int)} ranges between 1 and 31
	 * @return the newly constructed intDataType
	 */
    public IntegerDataType createDataTypeNumericInteger(int primitiveType, int precision);

	/**
	 * Factory method to construct a <code>ApproximateNumericDataType</code> with
	 * its <code>primitiveType</code> and <code>precision</code>.
	 * <b>Note</b>:
	 * The given <code>primitiveType</code> must be one of the constants for the
	 * primitve types in this interface/class correlated to the
	 * <code>ApproximateNumericDataType</code>.
	 * @see #PRIMITIVE_TYPE_FLOAT
	 * @see #PRIMITIVE_TYPE_REAL
	 * @see #PRIMITIVE_TYPE_DOUBLE_PRECISION
	 *
	 * @param primitiveType one of the correlated the primitive types for
	 *   	the FixedPrecisionDataType {@link #PRIMITIVE_TYPE_FLOAT},
	 *  	{@link #PRIMITIVE_TYPE_REAL}, {@link #PRIMITIVE_TYPE_DOUBLE_PRECISION}
	 * @param precision
	 * 		the total number of digits
	 * 		{@link org.eclipse.wst.rdb.internal.models.sql.datatypes.NumericalDataType#setPrecision(int)} ranges between 1 and 31
	 * @return the newly constructed ApproximateNumericDataType
	 */
    public ApproximateNumericDataType createDataTypeNumericApproximate(
                                                                       int primitiveType,
                                                                       int precision);



}
