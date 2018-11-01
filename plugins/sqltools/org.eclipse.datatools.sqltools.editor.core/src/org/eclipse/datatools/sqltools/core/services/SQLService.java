/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/

package org.eclipse.datatools.sqltools.core.services;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.datatools.sqltools.core.EditorCorePlugin;
import org.eclipse.datatools.sqltools.internal.core.Messages;
import org.eclipse.datatools.sqltools.sql.DefaultSQLSyntax;
import org.eclipse.datatools.sqltools.sql.ISQLSyntax;
import org.eclipse.datatools.sqltools.sql.identifier.IIdentifierValidator;
import org.eclipse.datatools.sqltools.sql.parser.ParserParameters;
import org.eclipse.datatools.sqltools.sql.parser.ParserProposalAdvisor;
import org.eclipse.datatools.sqltools.sql.parser.ParsingResult;
import org.eclipse.datatools.sqltools.sql.parser.SQLParser;
import org.eclipse.datatools.sqltools.sql.parser.ast.IASTSQLDelimiter;
import org.eclipse.datatools.sqltools.sql.parser.ast.IASTSQLStatementElement;
import org.eclipse.datatools.sqltools.sql.parser.ast.IASTStart;
import org.eclipse.datatools.sqltools.sql.parser.ast.Node;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.osgi.util.NLS;


/**
 * A SQL language related service specific to a database definition.
 * @author Hui Cao
 *
 */
public class SQLService
{
    /**
     * Returns an <code>ISQLSyntax</code> object which can be used to highlight sql statements in SQL editor.
     * 
     * @return an <code>ISQLSyntax</code> object specific to a SQL dialect.
     */
    public ISQLSyntax getSQLSyntax()
    {
        return new DefaultSQLSyntax();
    }

    /**
     * Returns a <code>SQLParser</code> object which is used to parse SQL dialect.
     * 
     * @return a <code>SQLParser</code> object
     */
    public SQLParser getSQLParser()
    {
        return null;
    }
    
    /**
     * Splits the SQL statement into groups of statements using SQL parser. 
     * If SQL parser failed to parse the SQL, splidByDefault param will decide whether to do a simply split by terminator or not. 
     * 
     * @param sql statement to be splitted
     * @param splitByDefault decided whether to do split when parser failed to parse sql.
     * @return sql statement array
     */
    public String[] splitSQL(String sql, boolean splitByDefault)
    {
        SQLParser parser = getSQLParser();
        if (parser == null)
        {
            return new String[]{sql};
        }
        ArrayList groups = new ArrayList();
        try
        {
            IDocument doc = new Document(sql);
            ParserParameters parserParameters = new ParserParameters(true);
            parserParameters.setProperty(ParserParameters.PARAM_CONSUME_EXCEPTION, Boolean.FALSE);
            ParsingResult result = parser.parse(sql, parserParameters);
            
            if (result.getExceptions() != null && !result.getExceptions().isEmpty())
            {
                if (splitByDefault)
                {
                    return splitSQLByTerminatorLine(sql, parser.getStatementTerminators());
                }
                else
                {
                    return new String[] {sql};
                }
            }

            IASTStart root = result.getRootNode();
            root.setDocument(doc);
            String group = "";
            if (root.jjtGetNumChildren() > 0)
            {
                for (int i = 0; i < root.jjtGetNumChildren(); i++)
                {
                    Node node = root.jjtGetChild(i);
                    if (node instanceof IASTSQLDelimiter)
                    {
                        //trim() will remove ascii control characters as well
                        if (!group.trim().equals(""))
                        {
                            groups.add(group);
                            group = "";
                        }
                    }
                    else if (node instanceof IASTSQLStatementElement)
                    {
                        group += node.getSQLText() + " ";
                    }
                    else
                    {
                        group += node.getSQLText() + System.getProperty("line.separator");

                    }
                }
            }
            else
            {
                group = sql;
            }
            if (!group.trim().equals(""))
            {
                groups.add(group);
            }
    
        }
        catch (Exception e1)
        {
            EditorCorePlugin.getDefault().log(NLS.bind(Messages.DefaultSQLSyntax_exception_splitSQL, sql), e1);
        }
        return (String[]) groups.toArray(new String[groups.size()]);
    
    }

    /**
     * Splits the sql statement into groups of statements according to SQL statement delimiter such as "go" or ";".
     * 
     * @param sql statement to be splitted
     * @return sql statement array
     */
	public String[] splitSQL(String sql) 
	{
	    return splitSQL(sql, true);
	}
	
	/**
	 * Splits SQL statements on any ";" characters and stand-alone "GO".
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
				boolean grouped = false;
				IRegion r = doc.getLineInformation(i);
				String line = doc.get(r.getOffset(), r.getLength());
				for (int j = 0; j < terminators.length; j++) {
					if (line.trim().equalsIgnoreCase(terminators[j])) {
						String string = doc.get(index, r.getOffset() - index);
						if (string.trim().length() > 0) {
							groups.add(string.trim());
						}
						index = r.getOffset() + doc.getLineLength(i);
						break;
					}
					else {
						int offset = r.getOffset();
						while (line.indexOf(";") >= 0) {
							if (line.indexOf(";") >= 0 && !isQuoted(doc, offset + line.indexOf(";") + 1)) {
								String string = doc.get(index, offset + line.indexOf(";") - index);
								if (string.trim().length() > 0) {
									groups.add(string.trim());
								}
								index = offset + line.indexOf(";") + 1;
								grouped = true;
								break;
							}
							offset += line.indexOf(";") + 1;
							line = line.substring(line.indexOf(";") + 1);
						}
					}
					if (grouped) {
						grouped = false;
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
	
	/**
	 * Check whether the ";" character is quoted 
	 * @param doc document
	 * @param offset offset of ";" character
	 * @return
	 */
	private boolean isQuoted(IDocument doc, int offset) {
        Pattern pSingle = Pattern.compile("'[^']*+('')*[^']*;+[^']*+('')*[^']*+'");
        Pattern pDouble = Pattern.compile("\"[^\"]*+(\"\")*[^\"]*;+[^\"]*+(\"\")*[^\"]*+\"");

		Matcher mSingle = pSingle.matcher(doc.get());
		while (mSingle.find()) {
			if (mSingle.start()<=offset && mSingle.end()>=offset) {
				return true;
			}
		}
		
		Matcher mDouble = pDouble.matcher(doc.get());
		while (mDouble.find()) {
			if (mDouble.start()<=offset && mDouble.end()>=offset) {
				return true;
			}
		}

		return false;
	}
	
	public ParserProposalAdvisor getParserProposalAdvisor()
	{
		return new ParserProposalAdvisor();
	}

    /**
     * Returns Identifier Validator
     * TODO implement IIdentifierValidator
     * @return
     */
    public IIdentifierValidator getIdentifierValidator()
    {
    	return null;
    }
}
