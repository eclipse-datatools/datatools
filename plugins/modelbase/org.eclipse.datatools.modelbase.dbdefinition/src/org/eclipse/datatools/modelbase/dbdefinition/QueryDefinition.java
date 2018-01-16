/**
 * <copyright>
 * </copyright>
 *
 * $Id: QueryDefinition.java,v 1.2 2005/12/22 23:32:55 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.dbdefinition;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Query Definition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.QueryDefinition#getIdentifierQuoteString <em>Identifier Quote String</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.QueryDefinition#getHostVariableMarker <em>Host Variable Marker</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.QueryDefinition#isHostVariableMarkerSupported <em>Host Variable Marker Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.QueryDefinition#isCastExpressionSupported <em>Cast Expression Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.QueryDefinition#isDefaultKeywordForInsertValueSupported <em>Default Keyword For Insert Value Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.QueryDefinition#isExtendedGroupingSupported <em>Extended Grouping Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.QueryDefinition#isTableAliasInDeleteSupported <em>Table Alias In Delete Supported</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getQueryDefinition()
 * @model
 * @generated
 */
public interface QueryDefinition extends EObject {
	/**
	 * Returns the value of the '<em><b>Identifier Quote String</b></em>' attribute.
	 * The default value is <code>"\\\""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Identifier Quote String</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Identifier Quote String</em>' attribute.
	 * @see #setIdentifierQuoteString(String)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getQueryDefinition_IdentifierQuoteString()
	 * @model default="\\\""
	 * @generated
	 */
	String getIdentifierQuoteString();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.QueryDefinition#getIdentifierQuoteString <em>Identifier Quote String</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Identifier Quote String</em>' attribute.
	 * @see #getIdentifierQuoteString()
	 * @generated
	 */
	void setIdentifierQuoteString(String value);

	/**
	 * Returns the value of the '<em><b>Host Variable Marker</b></em>' attribute.
	 * The default value is <code>":"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Host Variable Marker</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Host Variable Marker</em>' attribute.
	 * @see #setHostVariableMarker(String)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getQueryDefinition_HostVariableMarker()
	 * @model default=":"
	 * @generated
	 */
	String getHostVariableMarker();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.QueryDefinition#getHostVariableMarker <em>Host Variable Marker</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Host Variable Marker</em>' attribute.
	 * @see #getHostVariableMarker()
	 * @generated
	 */
	void setHostVariableMarker(String value);

	/**
	 * Returns the value of the '<em><b>Host Variable Marker Supported</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Host Variable Marker Supported</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Host Variable Marker Supported</em>' attribute.
	 * @see #setHostVariableMarkerSupported(boolean)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getQueryDefinition_HostVariableMarkerSupported()
	 * @model default="false"
	 * @generated
	 */
	boolean isHostVariableMarkerSupported();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.QueryDefinition#isHostVariableMarkerSupported <em>Host Variable Marker Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Host Variable Marker Supported</em>' attribute.
	 * @see #isHostVariableMarkerSupported()
	 * @generated
	 */
	void setHostVariableMarkerSupported(boolean value);

	/**
	 * Returns the value of the '<em><b>Cast Expression Supported</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Cast Expression Supported</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Cast Expression Supported</em>' attribute.
	 * @see #setCastExpressionSupported(boolean)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getQueryDefinition_CastExpressionSupported()
	 * @model default="false"
	 * @generated
	 */
	boolean isCastExpressionSupported();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.QueryDefinition#isCastExpressionSupported <em>Cast Expression Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cast Expression Supported</em>' attribute.
	 * @see #isCastExpressionSupported()
	 * @generated
	 */
	void setCastExpressionSupported(boolean value);

	/**
	 * Returns the value of the '<em><b>Default Keyword For Insert Value Supported</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Default Keyword For Insert Value Supported</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Default Keyword For Insert Value Supported</em>' attribute.
	 * @see #setDefaultKeywordForInsertValueSupported(boolean)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getQueryDefinition_DefaultKeywordForInsertValueSupported()
	 * @model default="false"
	 * @generated
	 */
	boolean isDefaultKeywordForInsertValueSupported();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.QueryDefinition#isDefaultKeywordForInsertValueSupported <em>Default Keyword For Insert Value Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Default Keyword For Insert Value Supported</em>' attribute.
	 * @see #isDefaultKeywordForInsertValueSupported()
	 * @generated
	 */
	void setDefaultKeywordForInsertValueSupported(boolean value);

	/**
	 * Returns the value of the '<em><b>Extended Grouping Supported</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Extended Grouping Supported</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Extended Grouping Supported</em>' attribute.
	 * @see #setExtendedGroupingSupported(boolean)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getQueryDefinition_ExtendedGroupingSupported()
	 * @model default="false"
	 * @generated
	 */
	boolean isExtendedGroupingSupported();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.QueryDefinition#isExtendedGroupingSupported <em>Extended Grouping Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Extended Grouping Supported</em>' attribute.
	 * @see #isExtendedGroupingSupported()
	 * @generated
	 */
	void setExtendedGroupingSupported(boolean value);

	/**
	 * Returns the value of the '<em><b>Table Alias In Delete Supported</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Table Alias In Delete Supported</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Table Alias In Delete Supported</em>' attribute.
	 * @see #setTableAliasInDeleteSupported(boolean)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getQueryDefinition_TableAliasInDeleteSupported()
	 * @model default="false"
	 * @generated
	 */
	boolean isTableAliasInDeleteSupported();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.QueryDefinition#isTableAliasInDeleteSupported <em>Table Alias In Delete Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Table Alias In Delete Supported</em>' attribute.
	 * @see #isTableAliasInDeleteSupported()
	 * @generated
	 */
	void setTableAliasInDeleteSupported(boolean value);

} // QueryDefinition
