/**
 * <copyright>
 * </copyright>
 *
 * $Id: QueryDefinitionImpl.java,v 1.1 2005/08/02 22:56:23 ledunnel Exp $
 */
package org.eclipse.datatools.modelbase.dbdefinition.impl;

import org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage;
import org.eclipse.datatools.modelbase.dbdefinition.QueryDefinition;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Query Definition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.QueryDefinitionImpl#getIdentifierQuoteString <em>Identifier Quote String</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.QueryDefinitionImpl#getHostVariableMarker <em>Host Variable Marker</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.QueryDefinitionImpl#isHostVariableMarkerSupported <em>Host Variable Marker Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.QueryDefinitionImpl#isCastExpressionSupported <em>Cast Expression Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.QueryDefinitionImpl#isDefaultKeywordForInsertValueSupported <em>Default Keyword For Insert Value Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.QueryDefinitionImpl#isExtendedGroupingSupported <em>Extended Grouping Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.QueryDefinitionImpl#isTableAliasInDeleteSupported <em>Table Alias In Delete Supported</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class QueryDefinitionImpl extends EObjectImpl implements QueryDefinition {
	/**
	 * The default value of the '{@link #getIdentifierQuoteString() <em>Identifier Quote String</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIdentifierQuoteString()
	 * @generated
	 * @ordered
	 */
	protected static final String IDENTIFIER_QUOTE_STRING_EDEFAULT = "\\\""; //$NON-NLS-1$

	/**
	 * The cached value of the '{@link #getIdentifierQuoteString() <em>Identifier Quote String</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIdentifierQuoteString()
	 * @generated
	 * @ordered
	 */
	protected String identifierQuoteString = IDENTIFIER_QUOTE_STRING_EDEFAULT;

	/**
	 * The default value of the '{@link #getHostVariableMarker() <em>Host Variable Marker</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHostVariableMarker()
	 * @generated
	 * @ordered
	 */
	protected static final String HOST_VARIABLE_MARKER_EDEFAULT = ":"; //$NON-NLS-1$

	/**
	 * The cached value of the '{@link #getHostVariableMarker() <em>Host Variable Marker</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHostVariableMarker()
	 * @generated
	 * @ordered
	 */
	protected String hostVariableMarker = HOST_VARIABLE_MARKER_EDEFAULT;

	/**
	 * The default value of the '{@link #isHostVariableMarkerSupported() <em>Host Variable Marker Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isHostVariableMarkerSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean HOST_VARIABLE_MARKER_SUPPORTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isHostVariableMarkerSupported() <em>Host Variable Marker Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isHostVariableMarkerSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean hostVariableMarkerSupported = HOST_VARIABLE_MARKER_SUPPORTED_EDEFAULT;

	/**
	 * The default value of the '{@link #isCastExpressionSupported() <em>Cast Expression Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isCastExpressionSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean CAST_EXPRESSION_SUPPORTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isCastExpressionSupported() <em>Cast Expression Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isCastExpressionSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean castExpressionSupported = CAST_EXPRESSION_SUPPORTED_EDEFAULT;

	/**
	 * The default value of the '{@link #isDefaultKeywordForInsertValueSupported() <em>Default Keyword For Insert Value Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDefaultKeywordForInsertValueSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean DEFAULT_KEYWORD_FOR_INSERT_VALUE_SUPPORTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isDefaultKeywordForInsertValueSupported() <em>Default Keyword For Insert Value Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDefaultKeywordForInsertValueSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean defaultKeywordForInsertValueSupported = DEFAULT_KEYWORD_FOR_INSERT_VALUE_SUPPORTED_EDEFAULT;

	/**
	 * The default value of the '{@link #isExtendedGroupingSupported() <em>Extended Grouping Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isExtendedGroupingSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean EXTENDED_GROUPING_SUPPORTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isExtendedGroupingSupported() <em>Extended Grouping Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isExtendedGroupingSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean extendedGroupingSupported = EXTENDED_GROUPING_SUPPORTED_EDEFAULT;

	/**
	 * The default value of the '{@link #isTableAliasInDeleteSupported() <em>Table Alias In Delete Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isTableAliasInDeleteSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean TABLE_ALIAS_IN_DELETE_SUPPORTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isTableAliasInDeleteSupported() <em>Table Alias In Delete Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isTableAliasInDeleteSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean tableAliasInDeleteSupported = TABLE_ALIAS_IN_DELETE_SUPPORTED_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected QueryDefinitionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return DatabaseDefinitionPackage.Literals.QUERY_DEFINITION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getIdentifierQuoteString() {
		return identifierQuoteString;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIdentifierQuoteString(String newIdentifierQuoteString) {
		String oldIdentifierQuoteString = identifierQuoteString;
		identifierQuoteString = newIdentifierQuoteString;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.QUERY_DEFINITION__IDENTIFIER_QUOTE_STRING, oldIdentifierQuoteString, identifierQuoteString));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getHostVariableMarker() {
		return hostVariableMarker;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHostVariableMarker(String newHostVariableMarker) {
		String oldHostVariableMarker = hostVariableMarker;
		hostVariableMarker = newHostVariableMarker;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.QUERY_DEFINITION__HOST_VARIABLE_MARKER, oldHostVariableMarker, hostVariableMarker));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isHostVariableMarkerSupported() {
		return hostVariableMarkerSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHostVariableMarkerSupported(boolean newHostVariableMarkerSupported) {
		boolean oldHostVariableMarkerSupported = hostVariableMarkerSupported;
		hostVariableMarkerSupported = newHostVariableMarkerSupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.QUERY_DEFINITION__HOST_VARIABLE_MARKER_SUPPORTED, oldHostVariableMarkerSupported, hostVariableMarkerSupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isCastExpressionSupported() {
		return castExpressionSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCastExpressionSupported(boolean newCastExpressionSupported) {
		boolean oldCastExpressionSupported = castExpressionSupported;
		castExpressionSupported = newCastExpressionSupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.QUERY_DEFINITION__CAST_EXPRESSION_SUPPORTED, oldCastExpressionSupported, castExpressionSupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isDefaultKeywordForInsertValueSupported() {
		return defaultKeywordForInsertValueSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDefaultKeywordForInsertValueSupported(boolean newDefaultKeywordForInsertValueSupported) {
		boolean oldDefaultKeywordForInsertValueSupported = defaultKeywordForInsertValueSupported;
		defaultKeywordForInsertValueSupported = newDefaultKeywordForInsertValueSupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.QUERY_DEFINITION__DEFAULT_KEYWORD_FOR_INSERT_VALUE_SUPPORTED, oldDefaultKeywordForInsertValueSupported, defaultKeywordForInsertValueSupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isExtendedGroupingSupported() {
		return extendedGroupingSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExtendedGroupingSupported(boolean newExtendedGroupingSupported) {
		boolean oldExtendedGroupingSupported = extendedGroupingSupported;
		extendedGroupingSupported = newExtendedGroupingSupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.QUERY_DEFINITION__EXTENDED_GROUPING_SUPPORTED, oldExtendedGroupingSupported, extendedGroupingSupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isTableAliasInDeleteSupported() {
		return tableAliasInDeleteSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTableAliasInDeleteSupported(boolean newTableAliasInDeleteSupported) {
		boolean oldTableAliasInDeleteSupported = tableAliasInDeleteSupported;
		tableAliasInDeleteSupported = newTableAliasInDeleteSupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.QUERY_DEFINITION__TABLE_ALIAS_IN_DELETE_SUPPORTED, oldTableAliasInDeleteSupported, tableAliasInDeleteSupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case DatabaseDefinitionPackage.QUERY_DEFINITION__IDENTIFIER_QUOTE_STRING:
				return getIdentifierQuoteString();
			case DatabaseDefinitionPackage.QUERY_DEFINITION__HOST_VARIABLE_MARKER:
				return getHostVariableMarker();
			case DatabaseDefinitionPackage.QUERY_DEFINITION__HOST_VARIABLE_MARKER_SUPPORTED:
				return isHostVariableMarkerSupported() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.QUERY_DEFINITION__CAST_EXPRESSION_SUPPORTED:
				return isCastExpressionSupported() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.QUERY_DEFINITION__DEFAULT_KEYWORD_FOR_INSERT_VALUE_SUPPORTED:
				return isDefaultKeywordForInsertValueSupported() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.QUERY_DEFINITION__EXTENDED_GROUPING_SUPPORTED:
				return isExtendedGroupingSupported() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.QUERY_DEFINITION__TABLE_ALIAS_IN_DELETE_SUPPORTED:
				return isTableAliasInDeleteSupported() ? Boolean.TRUE : Boolean.FALSE;
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
			case DatabaseDefinitionPackage.QUERY_DEFINITION__IDENTIFIER_QUOTE_STRING:
				setIdentifierQuoteString((String)newValue);
				return;
			case DatabaseDefinitionPackage.QUERY_DEFINITION__HOST_VARIABLE_MARKER:
				setHostVariableMarker((String)newValue);
				return;
			case DatabaseDefinitionPackage.QUERY_DEFINITION__HOST_VARIABLE_MARKER_SUPPORTED:
				setHostVariableMarkerSupported(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.QUERY_DEFINITION__CAST_EXPRESSION_SUPPORTED:
				setCastExpressionSupported(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.QUERY_DEFINITION__DEFAULT_KEYWORD_FOR_INSERT_VALUE_SUPPORTED:
				setDefaultKeywordForInsertValueSupported(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.QUERY_DEFINITION__EXTENDED_GROUPING_SUPPORTED:
				setExtendedGroupingSupported(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.QUERY_DEFINITION__TABLE_ALIAS_IN_DELETE_SUPPORTED:
				setTableAliasInDeleteSupported(((Boolean)newValue).booleanValue());
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
			case DatabaseDefinitionPackage.QUERY_DEFINITION__IDENTIFIER_QUOTE_STRING:
				setIdentifierQuoteString(IDENTIFIER_QUOTE_STRING_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.QUERY_DEFINITION__HOST_VARIABLE_MARKER:
				setHostVariableMarker(HOST_VARIABLE_MARKER_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.QUERY_DEFINITION__HOST_VARIABLE_MARKER_SUPPORTED:
				setHostVariableMarkerSupported(HOST_VARIABLE_MARKER_SUPPORTED_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.QUERY_DEFINITION__CAST_EXPRESSION_SUPPORTED:
				setCastExpressionSupported(CAST_EXPRESSION_SUPPORTED_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.QUERY_DEFINITION__DEFAULT_KEYWORD_FOR_INSERT_VALUE_SUPPORTED:
				setDefaultKeywordForInsertValueSupported(DEFAULT_KEYWORD_FOR_INSERT_VALUE_SUPPORTED_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.QUERY_DEFINITION__EXTENDED_GROUPING_SUPPORTED:
				setExtendedGroupingSupported(EXTENDED_GROUPING_SUPPORTED_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.QUERY_DEFINITION__TABLE_ALIAS_IN_DELETE_SUPPORTED:
				setTableAliasInDeleteSupported(TABLE_ALIAS_IN_DELETE_SUPPORTED_EDEFAULT);
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
			case DatabaseDefinitionPackage.QUERY_DEFINITION__IDENTIFIER_QUOTE_STRING:
				return IDENTIFIER_QUOTE_STRING_EDEFAULT == null ? identifierQuoteString != null : !IDENTIFIER_QUOTE_STRING_EDEFAULT.equals(identifierQuoteString);
			case DatabaseDefinitionPackage.QUERY_DEFINITION__HOST_VARIABLE_MARKER:
				return HOST_VARIABLE_MARKER_EDEFAULT == null ? hostVariableMarker != null : !HOST_VARIABLE_MARKER_EDEFAULT.equals(hostVariableMarker);
			case DatabaseDefinitionPackage.QUERY_DEFINITION__HOST_VARIABLE_MARKER_SUPPORTED:
				return hostVariableMarkerSupported != HOST_VARIABLE_MARKER_SUPPORTED_EDEFAULT;
			case DatabaseDefinitionPackage.QUERY_DEFINITION__CAST_EXPRESSION_SUPPORTED:
				return castExpressionSupported != CAST_EXPRESSION_SUPPORTED_EDEFAULT;
			case DatabaseDefinitionPackage.QUERY_DEFINITION__DEFAULT_KEYWORD_FOR_INSERT_VALUE_SUPPORTED:
				return defaultKeywordForInsertValueSupported != DEFAULT_KEYWORD_FOR_INSERT_VALUE_SUPPORTED_EDEFAULT;
			case DatabaseDefinitionPackage.QUERY_DEFINITION__EXTENDED_GROUPING_SUPPORTED:
				return extendedGroupingSupported != EXTENDED_GROUPING_SUPPORTED_EDEFAULT;
			case DatabaseDefinitionPackage.QUERY_DEFINITION__TABLE_ALIAS_IN_DELETE_SUPPORTED:
				return tableAliasInDeleteSupported != TABLE_ALIAS_IN_DELETE_SUPPORTED_EDEFAULT;
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
		result.append(" (identifierQuoteString: "); //$NON-NLS-1$
		result.append(identifierQuoteString);
		result.append(", hostVariableMarker: "); //$NON-NLS-1$
		result.append(hostVariableMarker);
		result.append(", hostVariableMarkerSupported: "); //$NON-NLS-1$
		result.append(hostVariableMarkerSupported);
		result.append(", castExpressionSupported: "); //$NON-NLS-1$
		result.append(castExpressionSupported);
		result.append(", defaultKeywordForInsertValueSupported: "); //$NON-NLS-1$
		result.append(defaultKeywordForInsertValueSupported);
		result.append(", extendedGroupingSupported: "); //$NON-NLS-1$
		result.append(extendedGroupingSupported);
		result.append(", tableAliasInDeleteSupported: "); //$NON-NLS-1$
		result.append(tableAliasInDeleteSupported);
		result.append(')');
		return result.toString();
	}

} //QueryDefinitionImpl
