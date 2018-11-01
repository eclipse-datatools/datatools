/*******************************************************************************
 * Copyright © 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.datatools.sqltools.sqlbuilder.util;

import java.util.Observable;

import org.eclipse.datatools.sqltools.sqlbuilder.SQLBuilderPlugin;
import org.eclipse.datatools.sqltools.sqlbuilder.preferences.SQLBuilderPreferenceConstants;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;

/**
 * Class is used to call observer (SQLBuilder) of this class.
 * <p>
 * This comes into play when the user has a statement opened and then changes
 * the omit schema properties.  In such a case, the changes will apply to the 
 * currently opened statement without having to close then reopen it to take effect.
 */
public class OmitSchemaChangedNotifier extends Observable {
	
	private static OmitSchemaChangedNotifier myself;
	protected boolean omitSchema; // whether or not to omit schema
	
	/**
	 * Creates an instance of OmitSchemaChangedNotifier
	 */
	private OmitSchemaChangedNotifier() {
		super();
		SQLBuilderPlugin.getPlugin().getPreferenceStore().addPropertyChangeListener(
				new IPropertyChangeListener(){
					public void propertyChange(PropertyChangeEvent event) {
						if (event.getProperty().equals(SQLBuilderPreferenceConstants.OMIT_CURRENT_SCHEMA_IN_SQL)){
							schemaChanged(null, SQLBuilderPlugin.getPlugin().getPreferenceStore().getBoolean(SQLBuilderPreferenceConstants.OMIT_CURRENT_SCHEMA_IN_SQL));
						}
					}
				});
	}
	
	public static OmitSchemaChangedNotifier getInstance() {
		if (myself == null) {
			myself = new OmitSchemaChangedNotifier();
		}
		return myself;
	}
	
	/**
	 * Gets whether or not to omit the schema
	 * @return true if omit schema, false if not
	 */
	public boolean isOmitSchema() {
		return omitSchema;
	}
	
	/**
	 * Sets whether or not to omit the schema
	 * @param omit whether or not to omit the schema
	 */
	public void setOmitSchema(boolean omit) {
		omitSchema = omit;
	}
	
	/**
	 * Call this method to notify SQLBuilder of changes
	 * @param schema the schema name to pass to SQLBuilder
	 * @param omit whether or not to omit the schema
	 */
	public void schemaChanged(Object schema, boolean omit) {		
		setOmitSchema(omit);
		this.setChanged();
		this.notifyObservers(schema);
	}
}
