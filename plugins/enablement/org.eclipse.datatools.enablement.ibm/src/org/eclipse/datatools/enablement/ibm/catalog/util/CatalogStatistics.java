/*******************************************************************************
 * Copyright (c) 2007, 2014 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ibm.catalog.util;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Collection;

public class CatalogStatistics {

	public static final byte BOOLEAN_TYPE = 0;
	public static final byte INTEGER_TYPE = 1;
	public static final byte STRING_TYPE  = 2;
	public static final byte FLOAT_TYPE   = 3;
	public static final byte BIGINT_TYPE  = 4;
	public static final byte TIMESTAMP_TYPE  = 5;
	public static final byte COLLECTION_TYPE  = 6;
	
	
	public CatalogStatistics(String id,String name, String description, boolean initialValue, String syscatalog) {
		this.init(id,name,description,initialValue,syscatalog);
	}

	public CatalogStatistics(String id,String name, String description, String initialValue,String syscatalog) {
		this.init(id,name,description,initialValue,syscatalog);
	}
	
	public CatalogStatistics(String id,String name, String description, int initialValue,String syscatalog) {
		this.init(id,name,description,initialValue,syscatalog);
	}

	public CatalogStatistics(String id,String name, String description, float initialValue,String syscatalog) {
		this.init(id,name,description,initialValue,syscatalog);
	}
	
	public CatalogStatistics(String id,String name, String description, BigInteger initialValue,String syscatalog) {
		this.init(id,name,description,initialValue,syscatalog);
	}

	public CatalogStatistics(String id,String name, String description, Timestamp initialValue,String syscatalog) {
		this.init(id,name,description,initialValue,syscatalog);
	}
	
	public CatalogStatistics(String id,String name, String description, Collection initialValue,String syscatalog) {
		this.init(id,name,description,initialValue,syscatalog);
	}

	public String getId(){
		return this.id;
	}
	public String  getName() {
		return this.name;
	}
	
	public String  getDescription() {
		return this.desc;
	}
	
	public String  getSyscatalog() {
		return this.syscatalog;
	}

	public byte  getType() {
		return this.type;
	}
	
	public boolean getBoolean() {
		if(this.type != CatalogStatistics.BOOLEAN_TYPE) throw new RuntimeException("Invalid Call"); //$NON-NLS-1$
		return this.booleanValue;
	}

	public void setBoolean(boolean value) {
		if(this.type != CatalogStatistics.BOOLEAN_TYPE) throw new RuntimeException("Invalid Call"); //$NON-NLS-1$
		this.booleanValue = value;
	}

	public int getInt() {
		if(this.type != CatalogStatistics.INTEGER_TYPE)throw new RuntimeException("Invalid Call"); //$NON-NLS-1$
		return this.intValue;
		
	}

	public void setInt(int value) {
		if(this.type != CatalogStatistics.INTEGER_TYPE) throw new RuntimeException("Invalid Call"); //$NON-NLS-1$
		this.intValue = value;		
	}
	
	public String  getString() {
		if(this.type != CatalogStatistics.STRING_TYPE) throw new RuntimeException("Invalid Call"); //$NON-NLS-1$
		return this.stringValue;
	}
	
	public void setString(String value) {
		if(this.type != CatalogStatistics.STRING_TYPE) throw new RuntimeException("Invalid Call"); //$NON-NLS-1$
		this.stringValue = value;
	}

	public float  getFloat() {
		if(this.type != CatalogStatistics.FLOAT_TYPE) throw new RuntimeException("Invalid Call"); //$NON-NLS-1$
		return this.floatValue;
	}
	
	public void setFloat(float value) {
		if(this.type != CatalogStatistics.FLOAT_TYPE) throw new RuntimeException("Invalid Call"); //$NON-NLS-1$
		this.floatValue = value;
	}

	public BigInteger  getBiginteger() {
		if(this.type != CatalogStatistics.BIGINT_TYPE) throw new RuntimeException("Invalid Call"); //$NON-NLS-1$
		return this.bigIntegerValue;
	}
	
	public void setBigInteger(BigInteger value) {
		if(this.type != CatalogStatistics.BIGINT_TYPE) throw new RuntimeException("Invalid Call"); //$NON-NLS-1$
		this.bigIntegerValue = value;
	}

	public Timestamp  getTimestamp() {
		if(this.type != CatalogStatistics.TIMESTAMP_TYPE) throw new RuntimeException("Invalid Call"); //$NON-NLS-1$
		return this.timestampValue;
	}
	
	public void setTimestampr(Timestamp value) {
		if(this.type != CatalogStatistics.TIMESTAMP_TYPE) throw new RuntimeException("Invalid Call"); //$NON-NLS-1$
		this.timestampValue = value;
	}

	public Collection  getCollection() {
		if(this.type != CatalogStatistics.COLLECTION_TYPE) throw new RuntimeException("Invalid Call"); //$NON-NLS-1$
		return this.collectionValue;
	}
	
	public void setComplex(Collection value) {
		if(this.type != CatalogStatistics.COLLECTION_TYPE) throw new RuntimeException("Invalid Call"); //$NON-NLS-1$
		this.collectionValue = value;
	}

	
	private void init(String id,String name, String description, boolean initialValue,String syscatalog) {
		this.type = CatalogStatistics.BOOLEAN_TYPE;
		this.id = id;
		this.name = name;
		this.desc = description;
		this.syscatalog = syscatalog;
		this.booleanValue = initialValue;
	}

	public void init (String id,String name, String description, String initialValue,String syscatalog) {
		this.type = CatalogStatistics.STRING_TYPE;
		this.id = id;
		this.name = name;
		this.desc = description;
		this.syscatalog = syscatalog;
		this.stringValue = initialValue;
	}
	
	public void init (String id,String name, String description, int initialValue,String syscatalog) {
		this.type = CatalogStatistics.INTEGER_TYPE;
		this.id = id;
		this.name = name;
		this.desc = description;
		this.syscatalog = syscatalog;
		this.intValue = initialValue;		
	}

	public void init (String id,String name, String description, float initialValue,String syscatalog) {
		this.type = CatalogStatistics.FLOAT_TYPE;
		this.id = id;
		this.name = name;
		this.desc = description;
		this.syscatalog = syscatalog;
		this.floatValue = initialValue;		
	}

	public void init (String id,String name, String description, BigInteger initialValue,String syscatalog) {
		this.type = CatalogStatistics.BIGINT_TYPE;
		this.id = id;
		this.name = name;
		this.desc = description;
		this.syscatalog = syscatalog;
		this.bigIntegerValue = initialValue;		
	}

	public void init (String id,String name, String description, Timestamp initialValue,String syscatalog) {
		this.type = CatalogStatistics.TIMESTAMP_TYPE;
		this.id = id;
		this.name = name;
		this.desc = description;
		this.syscatalog = syscatalog;
		this.timestampValue = initialValue;		
	}

	public void init (String id,String name, String description, Collection initialValue,String syscatalog) {
		this.type = CatalogStatistics.COLLECTION_TYPE;
		this.id = id;
		this.name = name;
		this.desc = description;
		this.syscatalog = syscatalog;
		this.collectionValue = initialValue;		
	}

	public String toString() {
		switch (this.getType()) {
		case CatalogStatistics.BOOLEAN_TYPE:
			return Boolean.toString(this.booleanValue);
		case CatalogStatistics.INTEGER_TYPE:
			return Integer.toString(this.intValue);
		case CatalogStatistics.STRING_TYPE:
			return this.stringValue;
		case CatalogStatistics.FLOAT_TYPE:
			return Float.toString(this.floatValue);
		case CatalogStatistics.TIMESTAMP_TYPE:
			return this.timestampValue.toString();
		case CatalogStatistics.BIGINT_TYPE:
			return this.bigIntegerValue.toString();
		}
		return "";
	}

	public String getForBitData(){
		return this.forBitData;
	}
	
	public void setForBitData(String fotBitData) {
		this.forBitData = fotBitData;
	}
	
	public void setForBitDataFromNumeric() 
	{
		this.forBitDataFromNumeric = true;
	}
	
	public boolean isForBitDataFromNumeric() 
	{
		return this.forBitDataFromNumeric;
	}

	private String id;
	private String name;
	private String desc;
	private String syscatalog;
	private boolean booleanValue;
	private int intValue;
	private String stringValue;
	private float floatValue;
	private BigInteger bigIntegerValue;
	private Timestamp timestampValue;
	private Collection collectionValue;
	private byte type;
	private String forBitData;
	private boolean forBitDataFromNumeric = false;
}
