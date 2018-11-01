/*******************************************************************************
 * Copyright © 2008, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sqlbuilder;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorMatchingStrategy;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.ide.ResourceUtil;

/**
 * An editor matching strategy (for the SQL Builder editor) that provides its own algorithm for matching 
 * the input of an open editor of this type to a given editor input. 
 * @author aoadio
 *
 */
public class SQLBuilderMatchingStrategy implements IEditorMatchingStrategy  {
	
	public static final String SQL_BUILDER_ID = "org.eclipse.datatools.sqltools.sqlbuilder"; // $NON-NLS-1$


	/**
	 * Returns whether the editor represented by the given editor reference matches the given editor input.
	 */
	public boolean matches(IEditorReference editorRef, IEditorInput input) {
		boolean isMatchFound = false;

		if (input != null && editorRef != null) {
			// Get the file associated with this Editor Input
			IFile inputFile = ResourceUtil.getFile(input);
			if (inputFile != null) {
				
				IEditorDescriptor editorDesc = IDE.getDefaultEditor(inputFile);
			    String inputFileEditorID = editorDesc.getId();
			    
			    // Check if the input file editor is the SQLBuilder
			    if(SQL_BUILDER_ID.equals(inputFileEditorID)){
			    	String editorRefID = editorRef.getId();
			    	
			    	// Check if the referenced editor is the SQLBuilder
			    	if (SQL_BUILDER_ID.equals(editorRefID)) {
						try {
							// Get the  editorRef's IEditorInput. 
							IEditorInput editorRefInput = editorRef
									.getEditorInput();
							if (editorRefInput instanceof IEditorInput) {
								// Get the file associated with editorRefInput and see if it's
								// the same as our input file.
								IFile editorRefFile = ResourceUtil
										.getFile(editorRefInput);
								if (editorRefFile != null) {
									isMatchFound = (inputFile.equals(editorRefFile));
								}
							}
						} catch (CoreException e) {
							SQLBuilderPlugin.getPlugin().getLogger().writeLog(e);
						}
					}
			    }
				
			}
		}

		return isMatchFound;
	} 
}

