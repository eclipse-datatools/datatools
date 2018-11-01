/*******************************************************************************
 * Copyright © 2000, 2008 Sybase, Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sqlbuilder.util;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.datatools.sqltools.editor.core.connection.ISQLEditorConnectionInfo;
import org.eclipse.datatools.sqltools.sqlbuilder.input.SQLBuilderEditorInput;
import org.eclipse.datatools.sqltools.sqlbuilder.input.SQLBuilderFileEditorInput;
import org.eclipse.datatools.sqltools.sqlbuilder.input.SQLBuilderInputFactory;
import org.eclipse.datatools.sqltools.sqlbuilder.input.SQLBuilderStorageEditorInput;
import org.eclipse.datatools.sqltools.sqlbuilder.model.IOmitSchemaInfo;
import org.eclipse.datatools.sqltools.sqlbuilder.model.ISQLStatementInfo;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLStatementInfo;
import org.eclipse.datatools.sqltools.sqleditor.SQLEditorStorage;
import org.eclipse.datatools.sqltools.sqleditor.SQLEditorStringStorage;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.WorkbenchException;
import org.eclipse.ui.XMLMemento;

/**
 * This class provides static methods for SQLBuilder EditorInputs.
 * 
 * @author Jeremy Lindop
 */
public class SQLBuilderEditorInputUtil {
	
	/**
	 * Creates a SQLBuilderStorageEditorInput from a string. The string should be
	 * a serialized XMLMemento created from a SQLBuilderStorageEditorInput.
	 * 
	 * @param s
	 * @return SQLBuilderFileEditorInput the <code>SQLBuilderFileEditorInput</code> created from the input string.
	 */
	public static SQLBuilderStorageEditorInput createSQLBuilderStorageEditorInput(String s) {
		IMemento memento = readMementoFromString( s );

		return createSQLBuilderStorageEditorInput( memento );
	}

	/**
	 * Create a new SQLBuilderStorageEditorInput from the specified memento.
	 * @param memento
	 * @return the <code>SQLBuilderFileEditorInput</code> created from the memento
	 */
    public static SQLBuilderStorageEditorInput createSQLBuilderStorageEditorInput(
            IMemento memento ) {
        SQLBuilderInputFactory factory = new SQLBuilderInputFactory();
		IAdaptable element = factory.createElement(memento);
		
        return (SQLBuilderStorageEditorInput) element;
    }
    
	/**
	 * Creates a SQLBuilderStorageEditorInput from a file.
	 * It consumes .sql files created in the DTP SQLEditor and possibly edited subsequently in
	 * the SQLBuilder.
	 * 
	 * @param file
	 * @return SQLBuilderStorageEditorInput the <code>SQLBuilderStorageEditorInput</code> created from the input file.
	 */
	public static SQLBuilderStorageEditorInput createSQLBuilderStorageEditorInput(IFile file){
		/*
		 * Get SQLStatement, ConnectionInfo and OmitSchemaInfo from file.
		 */
		SQLBuilderFileEditorInput fileEditorInput = new SQLBuilderFileEditorInput(file);
		String sSQL = fileEditorInput.getSQL();
		ISQLEditorConnectionInfo connectionInfo = fileEditorInput.getConnectionInfo();
		IOmitSchemaInfo omitSchemaInfo = fileEditorInput.getOmitSchemaInfo();
		
		/*
		 * Create SQLBuilderStorageEditorInput and put the SQL, ConnectionInfo and OmitSchemaInfo in it
		 */
		SQLEditorStorage storage = new SQLEditorStringStorage(file.getName(), sSQL);
		SQLBuilderStorageEditorInput storageEditorInput = new SQLBuilderStorageEditorInput(storage);
		storageEditorInput.setConnectionInfo(connectionInfo);
		storageEditorInput.setOmitSchemaInfo(omitSchemaInfo);
		
		return storageEditorInput;
	}
	
	/**
	 * Creates a SQLBuilderEditorInput from a file.
	 * It consumes .sql files created in the DTP SQLEditor and possibly edited subsequently in
	 * the SQLBuilder.
	 * 
	 * @param file
	 * @return SQLBuilderEditorInput the <code>SQLBuilderEditorInput</code> created from the input file.
	 */
	public static SQLBuilderEditorInput createSQLBuilderEditorInput(IFile file){
		/*
		 * Get SQLStatement, ConnectionInfo and OmitSchemaInfo from file.
		 */
		SQLBuilderFileEditorInput fileEditorInput = new SQLBuilderFileEditorInput(file);
		String sSQL = fileEditorInput.getSQL();
		ISQLEditorConnectionInfo connectionInfo = fileEditorInput.getConnectionInfo();
		IOmitSchemaInfo omitSchemaInfo = fileEditorInput.getOmitSchemaInfo();
		
		/*
		 * Create a new SQLStatementInfo
		 */
		ISQLStatementInfo sqlStatementInfo = new SQLStatementInfo(sSQL);
		
		/*
		 * Create SQLBuilderEditorInput with the SQL, ConnectionInfo and OmitSchemaInfo in it
		 */
		SQLBuilderEditorInput editorInput = new SQLBuilderEditorInput(connectionInfo, sqlStatementInfo, omitSchemaInfo);
		
		return editorInput;
	}
	
	/**
	 * Save a SQLBuilderStorageEditorInput to an XMLMemento
	 * 
	 * @param storageEditorInput
	 * @return XMLMemento the <code>XMLMemento</code> to which the input is saved.
	 */
	public static XMLMemento saveSQLBuilderStorageEditorInput(SQLBuilderStorageEditorInput storageEditorInput){
		XMLMemento memento = XMLMemento.createWriteRoot(SQLBuilderInputFactory.ID_XML_MEMENTO_ROOT_ELEMENT);
		SQLBuilderInputFactory.saveState(memento, storageEditorInput);
		return memento;
	}

	/**
	 * Utility function to write an XMLMemento to a string
	 * @return String
	 */
	public static String writeXMLMementoToString(XMLMemento memento){
		StringWriter writer = new StringWriter();
		try {
			memento.save(writer);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return writer.toString();
	}
    
    /**
     * Utility function to create a new IMemento from a a string.
     * @param s    a serialized XMLMemento
     * @return  the de-serialized IMemento
     */
    public static IMemento readMementoFromString( String s ) {
        StringReader reader = new StringReader(s);
        XMLMemento memento = null;
        try {
            memento = XMLMemento.createReadRoot(reader);
        } catch (WorkbenchException e) {
            e.printStackTrace();
        }
        return memento;
    }

}
