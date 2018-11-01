/*******************************************************************************
 * Copyright (c) 2003, 2014 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ibm.db2.luw.ddl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.datatools.connectivity.sqm.core.definition.DataModelElementFactory;
import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.enablement.ibm.db2.ddl.DB2DdlParser;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2MaterializedQueryTable;
import org.eclipse.datatools.modelbase.sql.expressions.QueryExpression;
import org.eclipse.datatools.modelbase.sql.expressions.SQLExpressionsPackage;
import org.eclipse.datatools.modelbase.sql.expressions.SearchCondition;
import org.eclipse.datatools.modelbase.sql.expressions.SearchConditionDefault;
import org.eclipse.datatools.modelbase.sql.statements.SQLStatement;
import org.eclipse.datatools.modelbase.sql.statements.SQLStatementDefault;
import org.eclipse.datatools.modelbase.sql.statements.SQLStatementsPackage;
import org.eclipse.datatools.modelbase.sql.tables.Trigger;

public class LUWDdlParser extends DB2DdlParser {

	public LUWDdlParser(DatabaseDefinition def){
		super(def);
	}

    public DB2MaterializedQueryTable parseMQT(String mqtText) {
    	DataModelElementFactory factory = this.def.getDataModelElementFactory();
    	DB2MaterializedQueryTable mqt = (DB2MaterializedQueryTable)factory.create(LUWPackage.eINSTANCE.getLUWMaterializedQueryTable());

    	String body = mqtText;
    	String[] tokens = mqtText.split("\\s[Aa][Ss]\\s",2); //$NON-NLS-1$
    	if (tokens.length > 1) {
    		body = tokens[1].trim();
    	}
    	int pos = this.parseMQTFullStatement(body);
    	if (pos != -1) {
    		body = body.substring(1,pos);
    	}
		QueryExpression queryExpression = (QueryExpression) factory.create(SQLExpressionsPackage.eINSTANCE.getQueryExpressionDefault());
		mqt.setQueryExpression(queryExpression);
		queryExpression.setSQL(body);
		return mqt;    	
    }

    public void parseTrigger (Trigger trigger,String triggerText){
    	DataModelElementFactory factory = this.def.getDataModelElementFactory();
    	
    	String body = triggerText;
    	String whenClause=""; //$NON-NLS-1$

		Pattern pattern1 = java.util.regex.Pattern.compile("[\\s]*CREATE.*[\\s]+?TRIGGER(.*)ON(.*)[\\s]+?FOR[\\s]+?EACH[\\s]+?(.*)",java.util.regex.Pattern.CASE_INSENSITIVE|java.util.regex.Pattern.DOTALL); //$NON-NLS-1$
    	Matcher matcher1 = pattern1.matcher(triggerText);
    	if (matcher1.matches()) {
    		this.parseUpdateColumns(matcher1.group(1).trim(),trigger);
    		this.parseReferencingCluase(matcher1.group(2).trim(),trigger);
    		body = matcher1.group(3).trim();
    		if (body.toUpperCase().startsWith("ROW")) { //$NON-NLS-1$
    			body = body.substring("ROW".length() +1); //$NON-NLS-1$
    		} else if (body.toUpperCase().startsWith("STATEMENT")) { //$NON-NLS-1$
    			body = body.substring("STATEMENT".length() +1); //$NON-NLS-1$
    		}
        	Pattern pattern = java.util.regex.Pattern.compile("[\\s]*MODE[\\s]+?DB2SQL(.*)",java.util.regex.Pattern.CASE_INSENSITIVE|java.util.regex.Pattern.DOTALL); //$NON-NLS-1$
        	Matcher matcher = pattern.matcher(body);
        	if (matcher.matches()) {
        		body = matcher.group(1).trim();
    			int whenPos = this.parseWhenClause(body);
    			if (whenPos >= 0) {
    				whenClause = body.substring(body.indexOf("(")+1,whenPos); //$NON-NLS-1$
    				body = body.substring(whenPos+1).trim();
    			}
        	} else {
    			int whenPos = this.parseWhenClause(body);
    			if (whenPos >= 0) {
    				whenClause = body.substring(body.indexOf("(")+1,whenPos); //$NON-NLS-1$
    				body = body.substring(whenPos+1).trim();
    			}
        	}
    		
    	}
    	
		SearchCondition searchCondition = (SearchCondition) factory.create(SQLExpressionsPackage.eINSTANCE.getSearchConditionDefault());
		trigger.setWhen(searchCondition);
		((SearchConditionDefault)searchCondition).setSQL(whenClause);

    	
		SQLStatement sqlbody = (SQLStatement) factory.create(SQLStatementsPackage.eINSTANCE.getSQLStatementDefault());
		((SQLStatementDefault)sqlbody).setSQL(body);
		trigger.getActionStatement().add(sqlbody);
    	
    }
    
/*	public void parseView (ViewTable view,String viewText){
		try {
			Database[] dbs = new Database[]{view.getSchema().getDatabase()};
	        Schema schema =  view.getSchema();
	    	DDLParser luwParser = this.def.getDdlParser();
	    	if (luwParser == null) return;
	    	
	        File temp = File.createTempFile("luwview", ".ddl");
	        
	        // Delete temp file when program exits.
	        temp.deleteOnExit();
	    
	        // Write to temp file
	        BufferedWriter out = new BufferedWriter(new FileWriter(temp));
	        String currentSchema= "SET SCHEMA " + schema.getName() + ";" +  System.getProperty("line.separator");
	        out.write(currentSchema);
	        out.write(viewText);
	        out.write(" ;");
	        out.close();

	        view.getSchema().getTables().remove(view);
	        ViewTable tempView = null;
	    	luwParser.parse(temp.getAbsolutePath(), dbs, null);
    		for (Iterator iter = schema.getTables().iterator(); iter.hasNext();){
    			Table table = (Table) iter.next();
    			if (table.getName().equals(view.getName()) && table instanceof ViewTable) {
    				tempView = (ViewTable)table;
    				QueryExpression expression = ((ViewTable)table).getQueryExpression();
    				if (expression == null) break;
    				String sqlbody = expression.getSQL();
    		    	DataModelElementFactory factory = this.def.getDataModelElementFactory();
    				QueryExpression queryExpression = (QueryExpression) factory.create(SQLExpressionsPackage.eINSTANCE.getQueryExpressionDefault());
    				((QueryExpressionDefault)queryExpression).setSQL(sqlbody);
    				view.setQueryExpression(queryExpression);
    				
    				break;
    			}
    		}
	    	
	    	if (tempView != null) {
	    		schema.getTables().remove(tempView);
	    	}
	    	
	    	schema.getTables().add(view);
	    	
		} catch(Exception e){
			e.printStackTrace();
		}
    }
*/
    private int parseMQTFullStatement(String body){
    	int pos = -1;
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
  
    	return pos;
    	
    }
}
