/*
 *+------------------------------------------------------------------------+
 *| Licensed Materials - Property of IBM                                   |
 *| (C) Copyright IBM Corp. 2003, 2012.  All Rights Reserved.              |
 *|                                                                        |
 *| US Government Users Restricted Rights - Use, duplication or disclosure |
 *| restricted by GSA ADP Schedule Contract with IBM Corp.                 |
 *+------------------------------------------------------------------------+
 */
package org.eclipse.datatools.enablement.ibm.db2.ddl;

import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.datatools.connectivity.sqm.core.definition.DataModelElementFactory;
import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2MaterializedQueryTable;
import org.eclipse.datatools.enablement.ibm.ddl.DdlParser;
import org.eclipse.datatools.modelbase.sql.expressions.QueryExpression;
import org.eclipse.datatools.modelbase.sql.expressions.QueryExpressionDefault;
import org.eclipse.datatools.modelbase.sql.expressions.SQLExpressionsPackage;
import org.eclipse.datatools.modelbase.sql.expressions.SearchCondition;
import org.eclipse.datatools.modelbase.sql.expressions.SearchConditionDefault;
import org.eclipse.datatools.modelbase.sql.statements.SQLStatement;
import org.eclipse.datatools.modelbase.sql.statements.SQLStatementDefault;
import org.eclipse.datatools.modelbase.sql.statements.SQLStatementsPackage;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.datatools.modelbase.sql.tables.Trigger;
import org.eclipse.datatools.modelbase.sql.tables.ViewTable;
import org.eclipse.emf.common.util.EList;

public abstract class DB2DdlParser implements DdlParser{

	public DB2DdlParser(DatabaseDefinition def){
		this.def = def;
	}

	public void parseView (ViewTable view,String viewText){
    	DataModelElementFactory factory = this.def.getDataModelElementFactory();

    	String[] tokens = viewText.split("[)\\s][aA][sS][\\s(]",Pattern.CASE_INSENSITIVE);
    	String body = viewText.substring(tokens[0].length()).trim();
    	body = body.replaceAll("^[)]?[aA][sS]", "");

    	Pattern pattern = Pattern.compile("(.*SELECT.*)[\\s]+?WITH[\\s]+?.*CHECK[\\s]+?OPTION.*",Pattern.CASE_INSENSITIVE|Pattern.DOTALL); //$NON-NLS-1$
    	Matcher matcher = pattern.matcher(body);
		if (matcher.matches()) {
			body = matcher.group(1).trim();
		}

		body = body.trim();
		if (body.startsWith("(") ) {
			int pos = this.findMatchParenths(body);
			if (pos == body.length()-1 ) {
				body = body.substring(1, body.length() -1);
			}
		}
    	QueryExpression queryExpression = (QueryExpression) factory.create(SQLExpressionsPackage.eINSTANCE.getQueryExpressionDefault());
		((QueryExpressionDefault)queryExpression).setSQL(body);
		view.setQueryExpression(queryExpression);
    }

    public void parseTrigger (Trigger trigger,String triggerText){
    	DataModelElementFactory factory = this.def.getDataModelElementFactory();
    	
    	String body = triggerText;
    	String whenClause=""; //$NON-NLS-1$
		Pattern pattern = java.util.regex.Pattern.compile("[\\s]*CREATE[\\s]+?TRIGGER(.*)ON(.*)[\\s]+?MODE[\\s]+?DB2SQL(.*)",java.util.regex.Pattern.CASE_INSENSITIVE|java.util.regex.Pattern.DOTALL); //$NON-NLS-1$
    	Matcher matcher = pattern.matcher(triggerText);
    	if (matcher.matches()) {
    		this.parseUpdateColumns(matcher.group(1).trim(),trigger);
    		this.parseReferencingCluase(matcher.group(2).trim(),trigger);
    		body = matcher.group(3).trim();
			int whenPos = this.parseWhenClause(body);
			if (whenPos >= 0) {
				whenClause = body.substring(body.indexOf("(")+1,whenPos); //$NON-NLS-1$
				body = body.substring(whenPos+1).trim();
			}
    	}
    	
		SearchCondition searchCondition = (SearchCondition) factory.create(SQLExpressionsPackage.eINSTANCE.getSearchConditionDefault());
		trigger.setWhen(searchCondition);
		((SearchConditionDefault)searchCondition).setSQL(whenClause);

    	
		SQLStatement sqlbody = (SQLStatement) factory.create(SQLStatementsPackage.eINSTANCE.getSQLStatementDefault());
		((SQLStatementDefault)sqlbody).setSQL(body);
		trigger.getActionStatement().clear();
		trigger.getActionStatement().add(sqlbody);
    	
    }
	
    protected int parseWhenClause(String body) {
    	int pos = -1;
    	Pattern pattern = Pattern.compile("[\\s]*WHEN[\\s]*?\\u0028(.*)",Pattern.CASE_INSENSITIVE|Pattern.DOTALL); //$NON-NLS-1$
    	Matcher matcher = pattern.matcher(body);
    	if (matcher.matches()) {
    		String whenClause =matcher.group(1);
    		int matches = 0;
    		for (int i = 0; i < body.length(); i++) {
    			if (body.charAt(i)=='(') {
    				matches++;
    			} else if (body.charAt(i) ==')') {
    				matches--;
    				if (matches == 0) {
    					pos = i;
    					break;
    				}
    			}
    		}
    	}
    	return pos;
    }
    
    protected void parseUpdateColumns(String body,Trigger trigger) {
		Pattern pattern = Pattern.compile(".*[\\s]+?UPDATE[\\s]+?OF(.*)",Pattern.CASE_INSENSITIVE|Pattern.DOTALL); //$NON-NLS-1$
    	Matcher matcher = pattern.matcher(body);
    	if (matcher.matches()) {
	    	EList columns = trigger.getTriggerColumn();
	        StringTokenizer tokenizer = new StringTokenizer(matcher.group(1), ","); //$NON-NLS-1$
	        String token = ""; //$NON-NLS-1$
	        while (tokenizer.hasMoreTokens()) {
	            token = tokenizer.nextToken().trim().replaceAll("\"", "");
	        	Column column = this.getColumn(trigger.getSubjectTable(), token);
	        	if (column != null) columns.add(column);
	        }
    	}
    }

    protected void parseReferencingCluase(String body,Trigger trigger) {
		final byte OLD = 1;
		final byte NEW = 2;
		final byte OLD_TABLE = 3;
		final byte NEW_TABLE = 4;		
    	
        StringTokenizer tokenizer = new StringTokenizer(body, " \r\n\t"); //$NON-NLS-1$
        byte previousToken = 0;
        boolean expectingNameOrASToken = false;
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken().trim();
            if (token.equalsIgnoreCase("OLD") && previousToken != OLD && !expectingNameOrASToken){ //$NON-NLS-1$
            	previousToken = OLD;
            	expectingNameOrASToken = true;
            } else if (token.equalsIgnoreCase("NEW") && previousToken != NEW && !expectingNameOrASToken){ //$NON-NLS-1$
            	previousToken = NEW;
            	expectingNameOrASToken = true;
            } else if (token.equalsIgnoreCase("OLD_TABLE") && previousToken != OLD_TABLE 
            		&& !expectingNameOrASToken) { //$NON-NLS-1$
            	previousToken = OLD_TABLE;
            	expectingNameOrASToken = true;
            } else if (token.equalsIgnoreCase("NEW_TABLE") && previousToken != NEW_TABLE 
            		&& !expectingNameOrASToken) { //$NON-NLS-1$
            	previousToken = NEW_TABLE;
            	expectingNameOrASToken = true;
            } else if (token.equalsIgnoreCase("AS")){ //$NON-NLS-1$
            	expectingNameOrASToken = true;
            } else {
            	switch (previousToken){
            	case OLD:
            		trigger.setOldRow(token);
            		previousToken = 0;
            		break;
            	case NEW:
            		trigger.setNewRow(token);
            		previousToken = 0;
            		break;
            	case OLD_TABLE:
            		trigger.setOldTable(token);
            		previousToken = 0;
            		break;
            	case NEW_TABLE:
            		trigger.setNewTable(token);
            		previousToken = 0;
            		break;
            	}
            	expectingNameOrASToken = false;
            }
        
        }
    }
    private Column getColumn(Table table, String columnName){
		Iterator it = table.getColumns().iterator();
		while(it.hasNext()) {
			Column c = (Column) it.next();
			if(c.getName().equalsIgnoreCase(columnName)) return c;
		}
		return null;
    }
    
    private int findMatchParenths(String body){
    	int pos = -1;
    	int matches = 1;
		for (int i = 1; i < body.length(); i++) {
			if (body.charAt(i)=='(') {
				matches++;
			} else if (body.charAt(i) ==')') {
				matches--;
				if (matches == 0) {
					pos = i;
					break;
				}
			}
		}
  
    	return pos;
    	
    }

    abstract public DB2MaterializedQueryTable parseMQT(String mqtText);
    
    protected DatabaseDefinition def = null;
}
