/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.modelbase.sql.schema.impl;

import java.io.ObjectStreamException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.eclipse.datatools.modelbase.sql.schema.Comment;
import org.eclipse.datatools.modelbase.sql.schema.Dependency;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENamedElementImpl;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EStringToStringMapEntryImpl;
import org.eclipse.emf.ecore.sdo.InternalEDataObject;
import org.eclipse.emf.ecore.sdo.util.SDOUtil;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import commonj.sdo.DataGraph;
import commonj.sdo.DataObject;
import commonj.sdo.Property;
import commonj.sdo.Sequence;
import commonj.sdo.Type;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>SQL Object</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.schema.impl.SQLObjectImpl#getDependencies <em>Dependencies</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.schema.impl.SQLObjectImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.schema.impl.SQLObjectImpl#getLabel <em>Label</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.schema.impl.SQLObjectImpl#getComments <em>Comments</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class SQLObjectImpl extends ENamedElementImpl implements SQLObject, InternalEDataObject {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The cached value of the '{@link #getDependencies() <em>Dependencies</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDependencies()
	 * @generated
	 * @ordered
	 */
	protected EList dependencies = null;

	/**
	 * The default value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected static final String DESCRIPTION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected String description = DESCRIPTION_EDEFAULT;

	/**
	 * The default value of the '{@link #getLabel() <em>Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLabel()
	 * @generated
	 * @ordered
	 */
	protected static final String LABEL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLabel() <em>Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLabel()
	 * @generated
	 * @ordered
	 */
	protected String label = LABEL_EDEFAULT;

	/**
	 * The cached value of the '{@link #getComments() <em>Comments</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getComments()
	 * @generated
	 * @ordered
	 */
	protected EList comments = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SQLObjectImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return SQLSchemaPackage.Literals.SQL_OBJECT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getDependencies() {
		if (dependencies == null) {
			dependencies = new EObjectContainmentEList(Dependency.class, this, SQLSchemaPackage.SQL_OBJECT__DEPENDENCIES);
		}
		return dependencies;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDescription(String newDescription) {
		String oldDescription = description;
		description = newDescription;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLSchemaPackage.SQL_OBJECT__DESCRIPTION, oldDescription, description));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLabel(String newLabel) {
		String oldLabel = label;
		label = newLabel;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLSchemaPackage.SQL_OBJECT__LABEL, oldLabel, label));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getComments() {
		if (comments == null) {
			comments = new EObjectWithInverseResolvingEList(Comment.class, this, SQLSchemaPackage.SQL_OBJECT__COMMENTS, SQLSchemaPackage.COMMENT__SQL_OBJECT);
		}
		return comments;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public EAnnotation addEAnnotation(String source) {
		EAnnotation eAnnotation = this.getEAnnotation(source);
		if (eAnnotation == null) {
			eAnnotation = (EAnnotation)EcoreFactory.eINSTANCE.create(EcorePackage.eINSTANCE.getEAnnotation());
			eAnnotation.setSource(source);
			this.getEAnnotations().add(eAnnotation);
		}
		
		return eAnnotation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public void addEAnnotationDetail(EAnnotation eAnnotation, String key, String value) {
		if (eAnnotation != null) {
			EStringToStringMapEntryImpl mapEntry =
				(EStringToStringMapEntryImpl)EcoreFactory.eINSTANCE.create(EcorePackage.eINSTANCE.getEStringToStringMapEntry());
				
			mapEntry.setTypedKey(key);
			mapEntry.setTypedValue(value);
			eAnnotation.getDetails().add(mapEntry);
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public String getEAnnotationDetail(EAnnotation eAnnotation, String key) {
		String value = ""; //$NON-NLS-1$
		if (eAnnotation != null) {
			Iterator eAnnotationDetailsIterator = eAnnotation.getDetails().iterator();
			while(eAnnotationDetailsIterator.hasNext()) {
				EStringToStringMapEntryImpl currentMapEntry = (EStringToStringMapEntryImpl)eAnnotationDetailsIterator.next();
				if (currentMapEntry.getTypedKey().equalsIgnoreCase(key)) {
					value = currentMapEntry.getTypedValue();
				}
			}
		}
		return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public void setAnnotationDetail(EAnnotation eAnnotation, String key, String value) {
		if (eAnnotation != null) {
			Iterator eAnnotationDetailsIterator = eAnnotation.getDetails().iterator();
			while(eAnnotationDetailsIterator.hasNext()) {
				EStringToStringMapEntryImpl currentMapEntry = (EStringToStringMapEntryImpl)eAnnotationDetailsIterator.next();
				if (currentMapEntry.getTypedKey().equalsIgnoreCase(key)) {
					currentMapEntry.setTypedValue(value);
				}
			}
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public void removeEAnnotationDetail(EAnnotation eAnnotation, String key) {
		if (eAnnotation != null) {
	         BasicEList deferredRemove = new BasicEList();
				Iterator eAnnotationDetailsIterator = eAnnotation.getDetails().iterator();
				while(eAnnotationDetailsIterator.hasNext()) {
					EStringToStringMapEntryImpl currentMapEntry = (EStringToStringMapEntryImpl)eAnnotationDetailsIterator.next();
					if (currentMapEntry.getTypedKey().equalsIgnoreCase(key)) {
	               deferredRemove.add(currentMapEntry);
					}
				}
	         for (Iterator iter = deferredRemove.iterator(); iter.hasNext();) {
	            eAnnotation.getDetails().remove(iter.next());
	         }
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public EAnnotation getEAnnotation(String source) {
		EAnnotation eAnnotation = null;
		Iterator eAnnotationIterator = this.getEAnnotations().iterator();
		while(eAnnotationIterator.hasNext()) {
			EAnnotation currentEAnnotation = (EAnnotation)eAnnotationIterator.next();
			if (currentEAnnotation.getSource().equalsIgnoreCase(source)) {
				eAnnotation = currentEAnnotation;
			}
		}
		return eAnnotation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object get(String path) {
		return SDOUtil.get(this, path);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void set(String path, Object value) {
		SDOUtil.set(this, path, value);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSet(String path) {
		return SDOUtil.isSet(this, path);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unset(String path) {
		SDOUtil.unset(this, path);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object get(int propertyIndex) {
		return SDOUtil.get(this, propertyIndex);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void set(int propertyIndex, Object value) {
		SDOUtil.set(this, propertyIndex, value);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSet(int propertyIndex) {
		return SDOUtil.isSet(this, propertyIndex);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unset(int propertyIndex) {
		SDOUtil.unset(this, propertyIndex);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object get(Property property) {
		return SDOUtil.get(this, property);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void set(Property property, Object value) {
		SDOUtil.set(this, property, value);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSet(Property property) {
		return SDOUtil.isSet(this, property);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unset(Property property) {
		SDOUtil.unset(this, property);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataObject getContainer() {
		return SDOUtil.getContainer(this);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Property getContainmentProperty() {
		return SDOUtil.getContainmentProperty(this);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataGraph getDataGraph() {
		return SDOUtil.getDataGraph(this);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Type getType() {
		return SDOUtil.getType(this);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigDecimal getBigDecimal(String path) {
		return SDOUtil.getBigDecimal(this, path);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger getBigInteger(String path) {
		return SDOUtil.getBigInteger(this, path);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean getBoolean(String path) {
		return SDOUtil.getBoolean(this, path);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public byte getByte(String path) {
		return SDOUtil.getByte(this, path);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public byte[] getBytes(String path) {
		return SDOUtil.getBytes(this, path);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public char getChar(String path) {
		return SDOUtil.getChar(this, path);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataObject getDataObject(String path) {
		return SDOUtil.getDataObject(this, path);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Date getDate(String path) {
		return SDOUtil.getDate(this, path);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getDouble(String path) {
		return SDOUtil.getDouble(this, path);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public float getFloat(String path) {
		return SDOUtil.getFloat(this, path);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getInt(String path) {
		return SDOUtil.getInt(this, path);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List getList(String path) {
		return SDOUtil.getList(this, path);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public long getLong(String path) {
		return SDOUtil.getLong(this, path);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Sequence getSequence(String path) {
		return SDOUtil.getSequence(this, path);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public short getShort(String path) {
		return SDOUtil.getShort(this, path);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getString(String path) {
		return SDOUtil.getString(this, path);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBigDecimal(String path, BigDecimal value) {
		SDOUtil.setBigDecimal(this, path, value);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBigInteger(String path, BigInteger value) {
		SDOUtil.setBigInteger(this, path, value);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBoolean(String path, boolean value) {
		SDOUtil.setBoolean(this, path, value);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setByte(String path, byte value) {
		SDOUtil.setByte(this, path, value);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBytes(String path, byte[] value) {
		SDOUtil.setBytes(this, path, value);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setChar(String path, char value) {
		SDOUtil.setChar(this, path, value);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDataObject(String path, DataObject value) {
		SDOUtil.setDataObject(this, path, value);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDate(String path, Date value) {
		SDOUtil.setDate(this, path, value);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDouble(String path, double value) {
		SDOUtil.setDouble(this, path, value);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFloat(String path, float value) {
		SDOUtil.setFloat(this, path, value);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInt(String path, int value) {
		SDOUtil.setInt(this, path, value);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setList(String path, List value) {
		SDOUtil.setList(this, path, value);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLong(String path, long value) {
		SDOUtil.setLong(this, path, value);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setShort(String path, short value) {
		SDOUtil.setShort(this, path, value);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setString(String path, String value) {
		SDOUtil.setString(this, path, value);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigDecimal getBigDecimal(int propertyIndex) {
		return SDOUtil.getBigDecimal(this, propertyIndex);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger getBigInteger(int propertyIndex) {
		return SDOUtil.getBigInteger(this, propertyIndex);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean getBoolean(int propertyIndex) {
		return SDOUtil.getBoolean(this, propertyIndex);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public byte getByte(int propertyIndex) {
		return SDOUtil.getByte(this, propertyIndex);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public byte[] getBytes(int propertyIndex) {
		return SDOUtil.getBytes(this, propertyIndex);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public char getChar(int propertyIndex) {
		return SDOUtil.getChar(this, propertyIndex);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataObject getDataObject(int propertyIndex) {
		return SDOUtil.getDataObject(this, propertyIndex);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Date getDate(int propertyIndex) {
		return SDOUtil.getDate(this, propertyIndex);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getDouble(int propertyIndex) {
		return SDOUtil.getDouble(this, propertyIndex);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public float getFloat(int propertyIndex) {
		return SDOUtil.getFloat(this, propertyIndex);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getInt(int propertyIndex) {
		return SDOUtil.getInt(this, propertyIndex);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List getList(int propertyIndex) {
		return SDOUtil.getList(this, propertyIndex);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public long getLong(int propertyIndex) {
		return SDOUtil.getLong(this, propertyIndex);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Sequence getSequence(int propertyIndex) {
		return SDOUtil.getSequence(this, propertyIndex);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public short getShort(int propertyIndex) {
		return SDOUtil.getShort(this, propertyIndex);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getString(int propertyIndex) {
		return SDOUtil.getString(this, propertyIndex);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBigDecimal(int propertyIndex, BigDecimal value) {
		SDOUtil.setBigDecimal(this, propertyIndex, value);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBigInteger(int propertyIndex, BigInteger value) {
		SDOUtil.setBigInteger(this, propertyIndex, value);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBoolean(int propertyIndex, boolean value) {
		SDOUtil.setBoolean(this, propertyIndex, value);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setByte(int propertyIndex, byte value) {
		SDOUtil.setByte(this, propertyIndex, value);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBytes(int propertyIndex, byte[] value) {
		SDOUtil.setBytes(this, propertyIndex, value);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setChar(int propertyIndex, char value) {
		SDOUtil.setChar(this, propertyIndex, value);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDataObject(int propertyIndex, DataObject value) {
		SDOUtil.setDataObject(this, propertyIndex, value);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDate(int propertyIndex, Date value) {
		SDOUtil.setDate(this, propertyIndex, value);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDouble(int propertyIndex, double value) {
		SDOUtil.setDouble(this, propertyIndex, value);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFloat(int propertyIndex, float value) {
		SDOUtil.setFloat(this, propertyIndex, value);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInt(int propertyIndex, int value) {
		SDOUtil.setInt(this, propertyIndex, value);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setList(int propertyIndex, List value) {
		SDOUtil.setList(this, propertyIndex, value);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLong(int propertyIndex, long value) {
		SDOUtil.setLong(this, propertyIndex, value);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setShort(int propertyIndex, short value) {
		SDOUtil.setShort(this, propertyIndex, value);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setString(int propertyIndex, String value) {
		SDOUtil.setString(this, propertyIndex, value);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigDecimal getBigDecimal(Property property) {
		return SDOUtil.getBigDecimal(this, property);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger getBigInteger(Property property) {
		return SDOUtil.getBigInteger(this, property);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean getBoolean(Property property) {
		return SDOUtil.getBoolean(this, property);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public byte getByte(Property property) {
		return SDOUtil.getByte(this, property);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public byte[] getBytes(Property property) {
		return SDOUtil.getBytes(this, property);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public char getChar(Property property) {
		return SDOUtil.getChar(this, property);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataObject getDataObject(Property property) {
		return SDOUtil.getDataObject(this, property);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Date getDate(Property property) {
		return SDOUtil.getDate(this, property);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getDouble(Property property) {
		return SDOUtil.getDouble(this, property);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public float getFloat(Property property) {
		return SDOUtil.getFloat(this, property);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getInt(Property property) {
		return SDOUtil.getInt(this, property);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List getList(Property property) {
		return SDOUtil.getList(this, property);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public long getLong(Property property) {
		return SDOUtil.getLong(this, property);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Sequence getSequence(Property property) {
		return SDOUtil.getSequence(this, property);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public short getShort(Property property) {
		return SDOUtil.getShort(this, property);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getString(Property property) {
		return SDOUtil.getString(this, property);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBigDecimal(Property property, BigDecimal value) {
		SDOUtil.setBigDecimal(this, property, value);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBigInteger(Property property, BigInteger value) {
		SDOUtil.setBigInteger(this, property, value);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBoolean(Property property, boolean value) {
		SDOUtil.setBoolean(this, property, value);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setByte(Property property, byte value) {
		SDOUtil.setByte(this, property, value);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBytes(Property property, byte[] value) {
		SDOUtil.setBytes(this, property, value);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setChar(Property property, char value) {
		SDOUtil.setChar(this, property, value);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDataObject(Property property, DataObject value) {
		SDOUtil.setDataObject(this, property, value);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDate(Property property, Date value) {
		SDOUtil.setDate(this, property, value);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDouble(Property property, double value) {
		SDOUtil.setDouble(this, property, value);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFloat(Property property, float value) {
		SDOUtil.setFloat(this, property, value);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInt(Property property, int value) {
		SDOUtil.setInt(this, property, value);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setList(Property property, List value) {
		SDOUtil.setList(this, property, value);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLong(Property property, long value) {
		SDOUtil.setLong(this, property, value);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setShort(Property property, short value) {
		SDOUtil.setShort(this, property, value);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setString(Property property, String value) {
		SDOUtil.setString(this, property, value);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataObject createDataObject(String propertyName) {
		return SDOUtil.createDataObject(this, propertyName);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataObject createDataObject(int propertyIndex) {
		return SDOUtil.createDataObject(this, propertyIndex);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataObject createDataObject(Property property) {
		return SDOUtil.createDataObject(this, property);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataObject createDataObject(String propertyName, String namespaceURI, String typeName) {
		return SDOUtil.createDataObject(this, propertyName, namespaceURI, typeName);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataObject createDataObject(int propertyIndex, String namespaceURI, String typeName) {
		return SDOUtil.createDataObject(this, propertyIndex, namespaceURI, typeName);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataObject createDataObject(Property property, Type type) {
		return SDOUtil.createDataObject(this, property, type);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void delete() {
		SDOUtil.delete(this);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List getInstanceProperties() {
		return SDOUtil.getInstanceProperties(this);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object writeReplace() throws ObjectStreamException {
		return SDOUtil.writeReplace(this);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SQLSchemaPackage.SQL_OBJECT__COMMENTS:
				return ((InternalEList)getComments()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SQLSchemaPackage.SQL_OBJECT__DEPENDENCIES:
				return ((InternalEList)getDependencies()).basicRemove(otherEnd, msgs);
			case SQLSchemaPackage.SQL_OBJECT__COMMENTS:
				return ((InternalEList)getComments()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case SQLSchemaPackage.SQL_OBJECT__DEPENDENCIES:
				return getDependencies();
			case SQLSchemaPackage.SQL_OBJECT__DESCRIPTION:
				return getDescription();
			case SQLSchemaPackage.SQL_OBJECT__LABEL:
				return getLabel();
			case SQLSchemaPackage.SQL_OBJECT__COMMENTS:
				return getComments();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case SQLSchemaPackage.SQL_OBJECT__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection)newValue);
				return;
			case SQLSchemaPackage.SQL_OBJECT__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case SQLSchemaPackage.SQL_OBJECT__LABEL:
				setLabel((String)newValue);
				return;
			case SQLSchemaPackage.SQL_OBJECT__COMMENTS:
				getComments().clear();
				getComments().addAll((Collection)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eUnset(int featureID) {
		switch (featureID) {
			case SQLSchemaPackage.SQL_OBJECT__DEPENDENCIES:
				getDependencies().clear();
				return;
			case SQLSchemaPackage.SQL_OBJECT__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case SQLSchemaPackage.SQL_OBJECT__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case SQLSchemaPackage.SQL_OBJECT__COMMENTS:
				getComments().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case SQLSchemaPackage.SQL_OBJECT__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case SQLSchemaPackage.SQL_OBJECT__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case SQLSchemaPackage.SQL_OBJECT__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case SQLSchemaPackage.SQL_OBJECT__COMMENTS:
				return comments != null && !comments.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (description: "); //$NON-NLS-1$
		result.append(description);
		result.append(", label: "); //$NON-NLS-1$
		result.append(label);
		result.append(')');
		return result.toString();
	}

} //SQLObjectImpl
