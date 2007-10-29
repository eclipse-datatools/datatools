/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.db.derby.core.services;

import java.util.ArrayList;

import org.eclipse.datatools.sqltools.db.derby.parser.DerbySQLParser;
import org.eclipse.datatools.sqltools.db.derby.sql.DerbySQLSyntax;
import org.eclipse.datatools.sqltools.db.generic.service.GenericSQLService;
import org.eclipse.datatools.sqltools.sql.ISQLSyntax;
import org.eclipse.datatools.sqltools.sql.parser.SQLParser;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;

/**
 * @author Hui Cao
 * 
 */
public class DerbySQLService extends GenericSQLService {

	public SQLParser getSQLParser() {
		return DerbySQLParser.getInstance();
	}

	public ISQLSyntax getSQLSyntax() {
		return new DerbySQLSyntax();
	}

	/**
	 * Overrides the generic implementation to split SQL statements on any ";" characters and stand-alone "GO"
	 */
	public String[] splitSQLByTerminatorLine(String sql, String[] terminators)
	{
		IDocument doc = new Document(sql);
		ArrayList groups = new ArrayList();
		//the start position for current group
		int index = 0;
		int numberOfLines = doc.getNumberOfLines();
		try {
			for (int i = 0; i < numberOfLines; i++) {
				IRegion r = doc.getLineInformation(i);
				String line = doc.get(r.getOffset(), r.getLength());
				for (int j = 0; j < terminators.length; j++) {
					if (line.trim().equalsIgnoreCase(terminators[j])) {
						String string = doc.get(index, r.getOffset() - index);
						if (string.trim().length() > 0) {
							groups.add(string);
						}
						index = r.getOffset() + doc.getLineLength(i);
						break;
					}
					else if (line.indexOf(";")>=0)
					{
						String string = doc.get(index, r.getOffset() + line.indexOf(";") - index);
						if (string.trim().length() > 0) {
							groups.add(string);
						}
						index = r.getOffset() + line.indexOf(";") + 1;
						break;
					}
				}
			}
			if (index < doc.getLength() - 1) {
				String string = doc.get(index, doc.getLength() - index);
				if (string.trim().length() > 0) {
					groups.add(string);
				}
			}
		} catch (Exception e) {
			//parse error, simply return
			return new String[]{sql};
		}
		return (String[]) groups.toArray(new String[groups.size()]);
	}
	

	
}
