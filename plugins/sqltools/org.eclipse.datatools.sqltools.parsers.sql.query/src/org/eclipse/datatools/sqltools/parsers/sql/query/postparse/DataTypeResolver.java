/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.parsers.sql.query.postparse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.eclipse.datatools.modelbase.sql.datatypes.CharacterStringDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.DataType;
import org.eclipse.datatools.modelbase.sql.datatypes.PredefinedDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.PrimitiveType;
import org.eclipse.datatools.modelbase.sql.query.Predicate;
import org.eclipse.datatools.modelbase.sql.query.QueryStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryValueExpression;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryObject;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCast;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionFunction;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionNested;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionVariable;
import org.eclipse.datatools.modelbase.sql.query.helper.ValueExpressionHelper;
import org.eclipse.datatools.sqltools.parsers.sql.SQLParseErrorInfo;
import org.eclipse.datatools.sqltools.parsers.sql.SQLParserException;
import org.eclipse.datatools.sqltools.parsers.sql.SQLParserLogger;
import org.eclipse.datatools.sqltools.parsers.sql.postparse.PostParseProcessor;
import org.eclipse.datatools.sqltools.parsers.sql.postparse.PostParseProcessorConfiguration;
import org.eclipse.datatools.sqltools.parsers.sql.query.SQLQueryParserMessages;

/**
 * The <code>DataTypeResolver</code> resolves the <code>DataType</code>s of
 * <code>QueryValueExpression</code>s based on the <code>DataType</code>s
 * already been resolved for the <code>ValueExpressionColumn</code>s.
 * <b>Note:</b> the <code>QueryStatement</code> has to be processed by the
 * <code>TableReferenceResolver</code> before.
 * 
 * @author ckadner@us.ibm.com
 */
public class DataTypeResolver implements PostParseProcessor
{

    protected static Class[] CANDIDATE_TYPES =
        new Class[] { QueryStatement.class,
                      QueryValueExpression.class,
                      Predicate.class
                      }; 
    
    /** Error code constant - not a message, data type unresolved */
    public static final String ERROR_CODE_DATATYPE_UNRESOLVED = 
        "SQL_?DTU"; //$NON-NLS-1$

    /** Error code constant - not a message, host variable/parameter data type
     *  unresolved */
    public static final String ERROR_CODE_VARIABLE_TYPE_UNCLEAR = 
        "SQL_?HVTU"; //$NON-NLS-1$
    
    /** Error code constant - not a message, incompatible data types */
    public static final String ERROR_CODE_DATATYPES_INCOMPATIBLE = 
        "SQL_?DTI"; //$NON-NLS-1$
    
    
    protected static final String ERROR_MESSAGE_KEY_DATATYPE_UNRESOLVED =
        "DataTypeResolver.DATA_TYPE_UNDETERMINED"; //$NON-NLS-1$
    
    protected static final String ERROR_MESSAGE_KEY_VARIABLE_TYPE_UNCLEAR =
        "DataTypeResolver.VARIABLE_TYPE_UNDETERMINED"; //$NON-NLS-1$
    
    protected static final String ERROR_MESSAGE_KEY_DATATYPES_INCOMPATIBLE =
    	"DataTypeResolver.DATA_TYPES_INCOMPATIBLE"; //$NON-NLS-1$
    
    /** 
     * Stateful list of one <code>QueryStatement</code>'s
     * <code>ValueExpression</code>s, to check for success of resolving after
     * processing is completed, valid only for one <code>QueryStatement</code>.
     * To be cleared in this {@link #resetState()} method. 
     */ 
    protected List unresolvedExprList = new ArrayList();
    
    /** 
     * Stateful map of one <code>QueryStatement</code>'s datatype-resolved
     * <code>ValueExpressionVariable</code>s, indexed by their names.
     * The <code>Map</code>'s structure:
     * <ul>
     * 	<li>key: <code>ValueExpressionVariable</code>'s <code>name</code>
     * 	<li>value: <code>List</code> of all <code>ValueExpressionVariable</code>s
     * 	with the same <code>name</code>
     * </ul>
     * The purpose of this mapping is to resolve all occurence of the
     * <code>ValueExpressionVariable</code>s with the same name.
     * After one occurence of the <code>ValueExpressionVariable</code>s with
     * the same name has been resolved, all other unresolved
     * <code>ValueExpressionVariable</code>s will get the same
     * <code>DataType</code>. 
     * 
     * This <code>Map</code> is valid only for one <code>QueryStatement</code>.
     * To be cleared in this {@link #resetState()} method. 
     */
    /* If after all the resolving is done, there are
     * still <code>ValueExpressionVariable</code>s unresolved, this
     * <code>Map</code> will be used to look up other occurences of that
     * <code>ValueExpressionVariable</code> with the same name, that might have
     * been resolved.
     */ 
    protected HashMap resolvedVariableMap = new HashMap();
    
    protected boolean logError = true;
    
    
    // ********************************************************** public methods
    

    /**
     * Constructs a new <code>DataTypeResolver</code>.
     */
    public DataTypeResolver() {}
    
    /**
     * Constructs a new <code>DataTypeResolver</code>.
     * @param logError if <code>true</code> errors will be logged to the console,
     *   default is <code>true</code>
     */
    public DataTypeResolver(boolean logError)
    {
        this();
        this.logError = logError;
    }
    
    /**
     * This <code>DatTypeResolver</code>'s candidate types are:
     * <ul>
     * 	<li><code>QueryStatement</code></li>
     * 	<li><code>ValueExpression</code></li>
     * </ul>
     * @return <code>Class[]</code> of <code>SQLObject</code> types
     * 
     * @see org.eclipse.datatools.sqltools.parsers.sql.query.postparse.PostParseProcessor#getProcessCandidateTypes()
     */
    public Class[] getProcessCandidateTypes()
    {
        return DataTypeResolver.CANDIDATE_TYPES;
    }

    /** @inheritDoc */
    public void config(PostParseProcessorConfiguration config)
    {
        // nothing to configure here
    }
    
    /**
     * @inheritDoc org.eclipse.datatools.sqltools.parsers.sql.query.postparse.PostParseProcessor#process(org.eclipse.datatools.modelbase.sql.query.SQLQueryObject)
     */
    public List process(SQLQueryObject sqlQuery) throws SQLParserException
    {
        List errorList = new ArrayList();
        
        if (sqlQuery == null) {
            return errorList;
        }
        else if (sqlQuery instanceof QueryValueExpression)
        {
            QueryValueExpression valueExpr = (QueryValueExpression) sqlQuery;
            errorList.addAll(resolveDataType(valueExpr));

            if (valueExpr.getDataType() == null) {
                this.unresolvedExprList.add( valueExpr );
            }
        }
        
        
        // check for unresolved datatypes
        
        if (sqlQuery instanceof QueryStatement)
        {
            backtrackDataTypes();
            
            errorList.addAll( checkForUnresolvedDataTypes() );
        }
        
        return errorList;
    }

    /**
     * @see org.eclipse.datatools.sqltools.parsers.sql.query.postparse.PostParseProcessor#resetState()
     */
    public void resetState()
    {
        unresolvedExprList.clear();

    }

    /**
     * @inheritDoc org.eclipse.datatools.sqltools.parsers.sql.query.postparse.PostParseProcessor#getParsedObjectsReplacementMap()
     */
    public Map getParsedObjectsReplacementMap()
    {
        return null;
    }
    
    
    
    // ******************************************************* protected methods
    

    /**
     * @param valueExpr
     * @return <code>List</code> of <code>SQLParseErrorInfo</code>s
     */
    protected List resolveDataType(QueryValueExpression valueExpr)
    {
        List errorList = new ArrayList();

        ValueExpressionHelper.resolveValueExpressionDatatype(valueExpr);
        
        // keep track of hostVariables
        if (valueExpr instanceof ValueExpressionVariable) 
        {
            List sameNameVars = 
                (List) resolvedVariableMap.get(valueExpr.getName());
            
            // if we have one hostVar resolved keep a reference to it for future
            // resolving of other occurrences of the same hostVar (same name)
            if (valueExpr.getName() != null && valueExpr.getDataType() != null) 
            {
                if (sameNameVars == null) {
                    sameNameVars = new ArrayList();
                    resolvedVariableMap.put(valueExpr.getName(), sameNameVars);
                }
                sameNameVars.add(valueExpr);
            }
            else
            {
                // if we couldn't resolve the hostVar datatype, try to find
                // another occurrence (same name) that was resolved
                if (sameNameVars != null && sameNameVars.size() > 0) {
                    // TODO: don't just take the first other occurrence but try
                    // to figure out common datatype for all its occurrences!
                    ValueExpressionVariable otherOccurence = 
                        (ValueExpressionVariable) sameNameVars.get(0);
                    ValueExpressionHelper.copyDataType(otherOccurence, valueExpr);
                }
            }
        }
        
        else if (valueExpr instanceof ValueExpressionCast) 
        {
            errorList.addAll( checkCastExpressionType(valueExpr.getDataType()) );
        }

        
        return errorList;
    }

    /**
     * 
     */
    protected void backtrackDataTypes()
    {
        // iteratively resolve datatypes, TODO: give that more thinking!
        // iterate through the list of unresolved valueExprs forward and reverse
        // in alternating order as the the order of the list reflects the
        // bottom-up order of the AST (abstract syntax tree)
        // and valueExpressions that didn't get resolved the first iteration
        // might need information of higher-level AST elements that were
        // resolved in the last iteration
        // for the next iteration
        
        int unresolved = this.unresolvedExprList.size();
        int prevUnresolved = unresolved + 1;
        boolean reverseIt = true;
        
        //while (prevUnresolved > unresolved)
        {
/*            prevUnresolved = unresolved;
            unresolved = this.unresolvedExprList.size();
*/            
            // reverse iterate the list for efficiency
            ListIterator resolveIt = null;
            boolean hasMoreElements = false;
            
            if (reverseIt) 
            {
                resolveIt = this.unresolvedExprList.listIterator(unresolved);
                hasMoreElements = resolveIt.hasPrevious();
            }
/*            else 
            {
                resolveIt = this.unresolvedExprList.listIterator();
                hasMoreElements = resolveIt.hasNext();
            }
*/            
            while (hasMoreElements)
            {
                Object element = (reverseIt) ? 
                                resolveIt.previous() : resolveIt.next();

                QueryValueExpression valueExpr = (QueryValueExpression) element;
                resolveDataType(valueExpr);

                if (valueExpr.getDataType() != null)
                {
                    resolveIt.remove();
                    unresolved--;
                }
                
                hasMoreElements = (reverseIt) ? 
                                resolveIt.hasPrevious() : resolveIt.hasNext();
                                
                if (!hasMoreElements && unresolved > 0 && 
                                unresolved < prevUnresolved) {
                    // iterate the other way
                    reverseIt = !reverseIt;
                    hasMoreElements = true;
                    prevUnresolved = unresolved;
                }
            }
        }
    }
    
    /**
     * @param select
     * @return
     */
    protected List checkForUnresolvedDataTypes()
    {
        List errorList = new ArrayList();
        
        for (Iterator exprIt = unresolvedExprList.iterator(); exprIt.hasNext();)
        {
            QueryValueExpression expr = 
                (QueryValueExpression) exprIt.next();
            
            // error if expression has no datatype
            if (expr.getDataType() == null)
            {
                if (expr instanceof ValueExpressionVariable) 
                {
                    errorList.add(new SQLParseErrorInfo(expr.getSourceInfo(),
                                    null, SQLQueryParserMessages.getString(
                                                    ERROR_MESSAGE_KEY_VARIABLE_TYPE_UNCLEAR,
                                                    new String[] {expr.getSQL()}),
                                    ERROR_CODE_VARIABLE_TYPE_UNCLEAR));
                }
                else if (expr instanceof ValueExpressionFunction)
                {
                    // FIXME: remove supression of error msg for unresolved functions
                    //   resolve function argument datatype and return datatype properly!!!
                }
                else if (expr instanceof ValueExpressionNested
                                && ((ValueExpressionNested)expr).getNestedValueExpr() != null
                                && ((ValueExpressionNested)expr).getNestedValueExpr().getDataType() == null)
                {
                    // don't report as we report for the nested expression already!!!
                }
                else
                {
                    errorList.add(new SQLParseErrorInfo(expr.getSourceInfo(),
                                    null, SQLQueryParserMessages.getString(
                                                    ERROR_MESSAGE_KEY_DATATYPE_UNRESOLVED,
                                                    new String[] {expr.getSQL()}),
                                    ERROR_CODE_DATATYPE_UNRESOLVED));
                }
            }
        }
        
        // TODO: do not suppress the errors once the ValueExpressionHelper code
        //       is overhauled, do not log errors either
        printErrorList(errorList);
        errorList.clear();
        
        return errorList;
    }

    
    /**
     * @param errorList
     */
    protected void printErrorList(List errorList)
    {
        if (errorList == null || errorList.isEmpty()) {return;}
        
        SQLParserLogger.getLogger().writeInfo("Errors encountered by "+this.getClass().getName()+":"); //$NON-NLS-1$ //$NON-NLS-2$
        for (Iterator errorIt = errorList.iterator(); errorIt.hasNext();)
        {
            SQLParseErrorInfo errorInfo = (SQLParseErrorInfo) errorIt.next();

            String expected = ((errorInfo.getExpectedText() != null) ? ", expected: \"" //$NON-NLS-1$
                            + errorInfo.getExpectedText() + "\"" //$NON-NLS-1$
                            : ""); //$NON-NLS-1$
            
            SQLParserLogger.getLogger().writeInfo(errorInfo.getParserErrorMessage()+
                            " at line:column "+ //$NON-NLS-1$
                            errorInfo.getLineNumberStart()+":"+ //$NON-NLS-1$
                            errorInfo.getColumnNumberStart()+
                            " to line:column "+ //$NON-NLS-1$
                            errorInfo.getLineNumberEnd()+":"+ //$NON-NLS-1$
                            errorInfo.getColumnNumberEnd()+
                            " \""+ //$NON-NLS-1$
                            errorInfo.getErrorSourceText()+"\""+ //$NON-NLS-1$
                            expected
                            );
        }
    }


    /**
     * @param castType
     * @return List of <code>SQLParseErrorInfo</code>
     */
    protected List checkCastExpressionType(DataType castType)
    {
        List errorList = new ArrayList();
        
        // TODO: checkCastExpressionType(), return errorInfos
        
        if (castType != null && castType instanceof PredefinedDataType) 
        {
            PrimitiveType primitiveType = 
                ((PredefinedDataType)castType).getPrimitiveType();
            
            if (primitiveType != null) {
            
	            if (castType instanceof CharacterStringDataType) {
	                
	                CharacterStringDataType charType = 
	                    (CharacterStringDataType) castType;
	                
	                if ((primitiveType
                                    .equals(PrimitiveType.CHARACTER_LARGE_OBJECT_LITERAL) || primitiveType
                                    .equals(PrimitiveType.NATIONAL_CHARACTER_LARGE_OBJECT_LITERAL))
                                    && charType.getLength() == 0)
                    {
                        charType.setLength(1024 * 1024);
                        //TODO: set messuring unit to M and length to 1
                        //      for default is 1M -> Hemant
                    }
	                
	                else if ((primitiveType
                                    .equals(PrimitiveType.CHARACTER_LITERAL) || primitiveType
                                    .equals(PrimitiveType.NATIONAL_CHARACTER_LITERAL))
                                    && charType.getLength() == 0)
	                {
	                    charType.setLength(1);
	                }
	                
	                
	            }
            }
            
            
        }
        return errorList;
    }


    

}
