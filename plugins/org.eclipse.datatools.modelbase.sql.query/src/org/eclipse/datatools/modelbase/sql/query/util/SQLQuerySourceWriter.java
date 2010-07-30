/*******************************************************************************
 * Copyright (c) 2004, 2010 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     Actuate Corporation - implementation for BZ 320150
 *******************************************************************************/
package org.eclipse.datatools.modelbase.sql.query.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.datatools.modelbase.sql.datatypes.ApproximateNumericDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.ArrayDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.BinaryStringDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.CharacterStringDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.DataType;
import org.eclipse.datatools.modelbase.sql.datatypes.DateDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.DistinctUserDefinedType;
import org.eclipse.datatools.modelbase.sql.datatypes.ElementType;
import org.eclipse.datatools.modelbase.sql.datatypes.FixedPrecisionDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.IntegerDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.MultisetDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.PrimitiveType;
import org.eclipse.datatools.modelbase.sql.datatypes.StructuredUserDefinedType;
import org.eclipse.datatools.modelbase.sql.datatypes.TimeDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.XMLDataType;
import org.eclipse.datatools.modelbase.sql.query.CallStatement;
import org.eclipse.datatools.modelbase.sql.query.ColumnName;
import org.eclipse.datatools.modelbase.sql.query.Grouping;
import org.eclipse.datatools.modelbase.sql.query.GroupingExpression;
import org.eclipse.datatools.modelbase.sql.query.GroupingSets;
import org.eclipse.datatools.modelbase.sql.query.GroupingSetsElementExpression;
import org.eclipse.datatools.modelbase.sql.query.GroupingSetsElementSublist;
import org.eclipse.datatools.modelbase.sql.query.MergeInsertSpecification;
import org.eclipse.datatools.modelbase.sql.query.MergeOnCondition;
import org.eclipse.datatools.modelbase.sql.query.MergeOperationSpecification;
import org.eclipse.datatools.modelbase.sql.query.MergeSourceTable;
import org.eclipse.datatools.modelbase.sql.query.MergeTargetTable;
import org.eclipse.datatools.modelbase.sql.query.MergeUpdateSpecification;
import org.eclipse.datatools.modelbase.sql.query.NullOrderingType;
import org.eclipse.datatools.modelbase.sql.query.OrderByOrdinal;
import org.eclipse.datatools.modelbase.sql.query.OrderByResultColumn;
import org.eclipse.datatools.modelbase.sql.query.OrderBySpecification;
import org.eclipse.datatools.modelbase.sql.query.OrderByValueExpression;
import org.eclipse.datatools.modelbase.sql.query.OrderingSpecType;
import org.eclipse.datatools.modelbase.sql.query.Predicate;
import org.eclipse.datatools.modelbase.sql.query.PredicateBasic;
import org.eclipse.datatools.modelbase.sql.query.PredicateBetween;
import org.eclipse.datatools.modelbase.sql.query.PredicateComparisonOperator;
import org.eclipse.datatools.modelbase.sql.query.PredicateExists;
import org.eclipse.datatools.modelbase.sql.query.PredicateInValueList;
import org.eclipse.datatools.modelbase.sql.query.PredicateInValueRowSelect;
import org.eclipse.datatools.modelbase.sql.query.PredicateInValueSelect;
import org.eclipse.datatools.modelbase.sql.query.PredicateIsNull;
import org.eclipse.datatools.modelbase.sql.query.PredicateLike;
import org.eclipse.datatools.modelbase.sql.query.PredicateQuantifiedRowSelect;
import org.eclipse.datatools.modelbase.sql.query.PredicateQuantifiedType;
import org.eclipse.datatools.modelbase.sql.query.PredicateQuantifiedValueSelect;
import org.eclipse.datatools.modelbase.sql.query.ProcedureReference;
import org.eclipse.datatools.modelbase.sql.query.QueryCombined;
import org.eclipse.datatools.modelbase.sql.query.QueryCombinedOperator;
import org.eclipse.datatools.modelbase.sql.query.QueryDeleteStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryExpressionBody;
import org.eclipse.datatools.modelbase.sql.query.QueryExpressionRoot;
import org.eclipse.datatools.modelbase.sql.query.QueryInsertStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryMergeStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryNested;
import org.eclipse.datatools.modelbase.sql.query.QuerySearchCondition;
import org.eclipse.datatools.modelbase.sql.query.QuerySelect;
import org.eclipse.datatools.modelbase.sql.query.QuerySelectStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryUpdateStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryValueExpression;
import org.eclipse.datatools.modelbase.sql.query.QueryValues;
import org.eclipse.datatools.modelbase.sql.query.ResultColumn;
import org.eclipse.datatools.modelbase.sql.query.ResultTableAllColumns;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryObject;
import org.eclipse.datatools.modelbase.sql.query.SearchConditionCombined;
import org.eclipse.datatools.modelbase.sql.query.SearchConditionCombinedOperator;
import org.eclipse.datatools.modelbase.sql.query.SearchConditionNested;
import org.eclipse.datatools.modelbase.sql.query.SuperGroup;
import org.eclipse.datatools.modelbase.sql.query.SuperGroupElementExpression;
import org.eclipse.datatools.modelbase.sql.query.SuperGroupElementSublist;
import org.eclipse.datatools.modelbase.sql.query.SuperGroupType;
import org.eclipse.datatools.modelbase.sql.query.TableCorrelation;
import org.eclipse.datatools.modelbase.sql.query.TableExpression;
import org.eclipse.datatools.modelbase.sql.query.TableFunction;
import org.eclipse.datatools.modelbase.sql.query.TableInDatabase;
import org.eclipse.datatools.modelbase.sql.query.TableJoined;
import org.eclipse.datatools.modelbase.sql.query.TableJoinedOperator;
import org.eclipse.datatools.modelbase.sql.query.TableNested;
import org.eclipse.datatools.modelbase.sql.query.TableReference;
import org.eclipse.datatools.modelbase.sql.query.UpdatabilityExpression;
import org.eclipse.datatools.modelbase.sql.query.UpdatabilityType;
import org.eclipse.datatools.modelbase.sql.query.UpdateAssignmentExpression;
import org.eclipse.datatools.modelbase.sql.query.UpdateOfColumn;
import org.eclipse.datatools.modelbase.sql.query.UpdateSource;
import org.eclipse.datatools.modelbase.sql.query.UpdateSourceExprList;
import org.eclipse.datatools.modelbase.sql.query.UpdateSourceQuery;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionAtomic;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseElse;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSearch;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSearchContent;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSimple;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSimpleContent;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCast;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionColumn;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCombined;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCombinedOperator;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionDefaultValue;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionFunction;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionLabeledDuration;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionLabeledDurationType;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionNested;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionNullValue;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionRow;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionScalarSelect;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionSimple;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionUnaryOperator;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionVariable;
import org.eclipse.datatools.modelbase.sql.query.ValuesRow;
import org.eclipse.datatools.modelbase.sql.query.WithTableReference;
import org.eclipse.datatools.modelbase.sql.query.WithTableSpecification;
import org.eclipse.datatools.modelbase.sql.query.helper.DataTypeHelper;
import org.eclipse.datatools.modelbase.sql.query.helper.StatementHelper;
import org.eclipse.datatools.modelbase.sql.routines.Procedure;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.schema.helper.ISQLObjectNameHelper;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.emf.common.util.EList;


/**
 * The <code>SQLQuerySourceWriter</code> provides a generic <code>getSQL()</code>
 * method to generate the SQL source text of a given <code>queryObject</code> in
 * the package <code>org.eclipse.datatools.modelbase.sql.query</code>.
 * 
 * @author ckadner
 * @see SQLQueryObject#getSQL()
 * 
 * <p>
 * <b>Developer note:</b>
 * To extend this <code>SQLQuerySourceWriter</code> for another
 * <code>org.eclipse.datatools.modelbase.sql.query</code> package, the following naming
 * conventions have to be adhered to. The package name containing the
 * <code>SQLQueryObject</code>s must contain the part name
 * <code>sql.query</code>, e.g. <code>org.eclipse.datatools.modelbase.sql.query.db2.luw</code>.
 * The <code>SQLQuerySourceWriter</code> for that package must be under the sub-
 * package <code>util</code>, likewise
 * <code>org.eclipse.datatools.modelbase.sql.query.db2.luw.util</code>
 * The name of the <code>SQLQuerySourceWriter</code> class must be composed of
 * at least the last part of the <code>SQLQueryObject</code>s' package name and
 * the word "SourceWriter", e.g. for an <code>SQLQueryObject</code> in package
 * <code>org.eclipse.datatools.modelbase.sql.query.db2.luw</code> the appropriate
 * <code>SQLQuerySourceWriter</code> class could be
 * <code>org.eclipse.datatools.modelbase.sql.query.db2.luw.util.DB2LUWSourceWriter</code> or
 * <code>org.eclipse.datatools.modelbase.sql.query.db2.luw.util.LUWSourceWriter</code>. The
 * <code>SQLQuerySourceWriter</code> could also be capable of generatiing the
 * SQL source text of multiple <code>SQLQueryObject</code>s' packages and
 * consequently would have to be located in a higher package itself. Likewise
 * a <code>org.eclipse.datatools.modelbase.sql.query.db2.util.DB2SourceWriter</code> could be
 * capable of generatiing the SQL source text for the packages
 * <code>org.eclipse.datatools.modelbase.sql.query.db2</code> and
 * <code>org.eclipse.datatools.modelbase.sql.query.db2.luw</code> and
 * <code>org.eclipse.datatools.modelbase.sql.query.db2.zos</code> and
 * <code>org.eclipse.datatools.modelbase.sql.query.db2.cloudscape</code>.
 * <p>
 * 
 * There can be only one <code>SQLQuerySourceWriter</code> per
 * <code>SQLQueryObject</code> package!
 * 
 * <p>
 * A <code>SQLQuerySourceWriter</code> operates on interface level
 * 
 * <b>Developer note:</b> add only appendSQL methods that apply
 * to specific SQLQueryObjects so the runtime type will decide its invocation,
 * an invokation of appendSQL() should always be compiletime-bound to
 * appendSQL(SQLQueryObject, StrinBuffer) that via reflection then invokes the
 * right appendSQL method. Unless: you know the exact runtime type and this type
 * has no subtypes with special features or source generation, that are
 * formatted by another appendSQL method here
 *
 */
public class SQLQuerySourceWriter implements SQLSourceWriter
{
    protected static final int STANDARD_INDENT = 2;
    
    /* *************************** protected switches **************************** */

    /** 
     * usability aspect, always qualify column references with at least the the
     * table name or alias if there are multiple tables in the FROM-clause.
     */
    protected int displayWidth = 80;

    /** 
     * usability aspect, always qualify column references with at least the the
     * table name or alias if there are multiple tables in the FROM-clause.
     */
    protected boolean alwaysQualifyColumnNamesForMultipleTables = true;
    
    /** 
     * usability aspect, always qualify column references with at least the the
     * table name or alias in nested selects or subselects.
     * e.g. "t1.col1" in "select col1 from t1 where col1 in (select col2 from t2 where t1.col1 = t2.col2)"
     */
    protected boolean alwaysQualifyColumnNamesForSubqueries = false;
    
    /** 
     * usability aspect, always qualify column references with at least the the
     * table name or alias if there are multiple tables in the FROM-clause, then
     * also qualify columns in the nested or subqueries.
     * e.g. when set to false:
     * "select t1.col1, t2.col2 from t1, t2 where col1 in (select col3 from t3 where t1.col1 = col3)"
     * when set to true:
     * "select t1.col1, t2.col2 from t1, t2 where col1 in (select t3.col3 from t3 where t1.col1 = t3.col3)"
     */
    protected boolean qualifyColumnNamesInSubqueriesWhenQualifiedInSuperQuery = false;
    
    /** 
     * usability aspect, always qualify column references with at least the the
     * table name or alias in nested selects or subselects, if the column is
     * referenced in a subquery.
     * e.g. "t1.col1" in "select col1 from t1 where col1 in (select col2 from t2 where t1.col1 = col2)"
     */
    protected boolean alwaysQualifyColumnNamesReferencedInSubqueries = true;
    
    protected static final String SQL_OBJECT_NAME_HELPER = "org.eclipse.datatools.modelbase.sql.sqlObjectNameHelper"; //$NON-NLS-1$
    protected static final String SQL_OBJECT_NAME_HELPER_DBTYPE = "databaseType"; //$NON-NLS-1$
    protected static final String SQL_OBJECT_NAME_HELPER_CLASS = "class"; //$NON-NLS-1$
    
    /* *************************** private fields **************************** */
    
    
    /**
     * For pretty print of comments we align single line comments at the end of
     * line
     * <ul>
     * <li>key: StringBuffer containing single line comments
     * <li>value: Integer last end-of-line single line comment start column
     * index
     * </ul>
     * We want to align comments at the end of line that
     * would only work if we always work on the global StringBuffer but we can
     * also have a partial one which will get indented later and global messures
     * don't work, therefore we use a Map and we only align end-of-line comments
     * within one StringBuffer.
     * @see #appendComment(SQLComment, StringBuffer)
     */
    private Map lastSLCommentIndentMap = null;
    
    private char delimitedIdentifierQuote = '"';

    /** The current indent unit size (number of spaces). */
    private int indentUnitSize = STANDARD_INDENT;
    
    /* determined on SQLQueryObject basis by getSQL(SQO) */
    private boolean preserveComments = true;;
    
    
    /* ***************************** public constants *************************** */
    

    /**
     * String constant, used as SQL source for empty
     * <code>QuerySelectStatement</code>, value: "SELECT * FROM"
     */
    public static final String DEFAULT_STMT_SELECT      = "SELECT *\n  FROM";

    /** String constant for the single line comment prefix, value: "--".
     *  To be overwritten by SourceWriter extension. */
    protected static String COMMENT_PREFIX_SINGLE_LINE  = "--";

    /** String constant for the multi line comment prefix, value: "/*".
     *  To be overwritten by SourceWriter extension. */
    protected static String COMMENT_PREFIX_MULTI_LINE   = "/*";

    /** String constant for the multi line comment suffix, value: "*&#047".
     *  To be overwritten by SourceWriter extension. */
    protected static String COMMENT_SUFFIX_MULTI_LINE   = "*/";

    
    /** String constant for the name of the function count(), value: "COUNT" */
    private static final String FUNCTION_COUNT          = "COUNT";

    
    /* ******************** enumeration constants *************************** */
    
    
    /**
     * String constant for {@link org.eclipse.datatools.modelbase.sql.query.OrderingSpecType#ASC}
     */
    protected static final String ORDERING_SPEC_TYPE_ASC = "ASC";
    
    /**
     * String constant for {@link org.eclipse.datatools.modelbase.sql.query.OrderingSpecType#DESC}
     */
    protected static final String ORDERING_SPEC_TYPE_DESC = "DESC";
    
    /**
     * String constant for {@link org.eclipse.datatools.modelbase.sql.query.NullOrderingType#NULLS_FIRST}
     */
    protected static final String NULL_ORDERING_TYPE_NULLS_FIRST = "NULLS FIRST";
    
    /**
     * String constant for {@link org.eclipse.datatools.modelbase.sql.query.NullOrderingType#NULLS_LAST}
     */
    protected static final String NULL_ORDERING_TYPE_NULLS_LAST = "NULLS LAST";
    

    /* ******************** string constants ******************************** */
    
    
    /** char constant, value: '.' */
    protected static final char DOT                     = '.';

    /** char constant for the new line character, value: '\n' */
    protected static final char NEW_LINE                = '\n';

    /** String constant for the new line character, value: "\n" */
    protected static final String NEW_LINE_STRING       = String.valueOf(NEW_LINE);

    /** char constant, value: '(' */
    protected static final char   PAREN_LEFT            = '(';

    /** char constant, value: ')' */
    protected static final char   PAREN_RIGHT           = ')';

    /** char constant, value: '[' */
    protected static final char   BRACKET_LEFT          = '[';

    /** char constant, value: ']' */
    protected static final char   BRACKET_RIGHT         = ']';

    /** char constant, value: ' ' */
    protected static final char   SPACE                 = ' ';

    /** char constant, value: ',' */
    protected static final char   COMMA                 = ',';
  
    /**
     * String constant, value: "="
     *
     * @see org.eclipse.datatools.modelbase.sql.query.PredicateComparisonOperator#EQUAL
     */
    protected final static String EQUAL                 = "=";

    /**
     * String constant, value: "<>"
     *
     * @see org.eclipse.datatools.modelbase.sql.query.PredicateComparisonOperator#NOT_EQUAL
     */
    protected final static String NOT_EQUAL             = "<>";

    /**
     * String constant, value: "<"
     *
     * @see org.eclipse.datatools.modelbase.sql.query.PredicateComparisonOperator#LESS_THAN
     */
    protected final static String LESS_THAN             = "<";

    /**
     * String constant, value: ">"
     *
     * @see org.eclipse.datatools.modelbase.sql.query.PredicateComparisonOperator#GREATER_THAN
     */
    protected final static String GREATER_THAN          = ">";

    /**
     * String constant, value: "<="
     *
     * @see org.eclipse.datatools.modelbase.sql.query.PredicateComparisonOperator#LESS_THAN_OR_EQUAL
     */
    protected final static String LESS_THAN_OR_EQUAL    = "<=";

    /**
     * String constant, value: ">="
     *
     * @see org.eclipse.datatools.modelbase.sql.query.PredicateComparisonOperator#GREATER_THAN_OR_EQUAL
     */
    protected final static String GREATER_THAN_OR_EQUAL = ">=";

    /**
     * String constant, value: "AND"
     *
     * @see org.eclipse.datatools.modelbase.sql.query.SearchConditionCombinedOperator#AND
     */
    protected final static String AND                   = "AND";

    /**
     * String constant, value: "OR"
     *
     * @see org.eclipse.datatools.modelbase.sql.query.SearchConditionCombinedOperator#OR
     */
    protected final static String OR                    = "OR";
   
    /**
     * String constant, value: "+"
     *
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionUnaryOperator#PLUS
     */
    protected final static String PLUS                  = "+";

    /**
     * String constant, value: "-"
     *
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionUnaryOperator#MINUS
     */
    protected final static String MINUS                 = "-";

    /**
     * String constant, value: "+"
     *
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionCombinedOperator#ADD
     */
    protected final static String ADD                   = "+";

    /**
     * String constant, value: "-"
     *
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionCombinedOperator#SUBTRACT
     */
    protected final static String SUBTRACT              = "-";

    /**
     * String constant, value: "*"
     *
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionCombinedOperator#MULTIPLY
     */
    protected final static String MULTIPLY              = "*";

    /**
     * String constant, value: "/"
     *
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionCombinedOperator#DIVIDE
     */
    protected final static String DIVIDE                = "/";

    /**
     * String constant, value: "||"
     *
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionCombinedOperator#CONCATENATE
     */
    protected final static String CONCATENATE           = "||";

    /**
     * String constant, value: "UNION"
     *
     * @see org.eclipse.datatools.modelbase.sql.query.QueryCombinedOperator#UNION
     */
    protected final static String UNION                 = "UNION";

    /**
     * String constant, value: "UNION ALL"
     *
     * @see org.eclipse.datatools.modelbase.sql.query.QueryCombinedOperator#UNION_ALL
     */
    protected final static String UNION_ALL             = "UNION ALL";

    /**
     * String constant, value: "INTERSECT"
     *
     * @see org.eclipse.datatools.modelbase.sql.query.QueryCombinedOperator#INTERSECT
     */
    protected final static String INTERSECT             = "INTERSECT";

    /**
     * String constant, value: "INTERSECT ALL"
     *
     * @see org.eclipse.datatools.modelbase.sql.query.QueryCombinedOperator#INTERSECT_ALL
     */
    protected final static String INTERSECT_ALL         = "INTERSECT ALL";

    /**
     * String constant, value: "EXCEPT"
     *
     * @see org.eclipse.datatools.modelbase.sql.query.QueryCombinedOperator#EXCEPT
     */
    protected final static String EXCEPT                = "EXCEPT";

    /**
     * String constant, value: "EXCEPT ALL"
     *
     * @see org.eclipse.datatools.modelbase.sql.query.QueryCombinedOperator#EXCEPT_ALL
     */
    protected final static String EXCEPT_ALL            = "EXCEPT ALL";

    /** String constant, value: ":" */
    protected final static String COLON                 = ":";

    /** String constant, value: "?" */
    protected final static String QUESTIONMARK          = "?"; 
    
    /** String constant, value: "ARRAY" */
    protected final static String ARRAY                 = "ARRAY";

    /** String constant, value: "AS" */
    protected final static String AS                    = "AS";

    /** String constant, value: "ALL" */
    protected final static String ALL                   = "ALL";

    /** String constant, value: "ANY" */
    protected final static String ANY                   = "ANY";

    /** String constant, value: "ASC" */
    protected final static String ASC                   = "ASC";
    
    /** String constant, value: "*" */
    protected final static String ASTERISK              = "*";

    /** String constant, value: "BETWEEN" */
    protected final static String BETWEEN               = "BETWEEN";

    /** String constant, value: "CALL" */
    protected final static String CALL                 = "CALL";
    
    /** String constant, value: "CASE" */
    protected final static String CASE                  = "CASE";

    /** String constant, value: "CAST" */
    protected final static String CAST                  = "CAST";

    /** String constant, value: "CUBE" */
    protected final static String CUBE                  = "CUBE";

    /** String constant, value: "DAYS" */
    protected final static String DAYS                  = "DAYS";

    /** String constant, value: "DEFAULT" */
    protected final static String DEFAULT               = "DEFAULT";

    /** String constant, value: "DELETE" */
    protected final static String DELETE                = "DELETE";

    /** String constant, value: "DESC" */
    protected final static String DESC                  = "DESC";

    /** String constant, value: "DISTINCT" */
    protected final static String DISTINCT              = "DISTINCT";

    /** String constant, value: "ELSE" */
    protected final static String ELSE                  = "ELSE";

    /** String constant, value: "END" */
    protected final static String END                   = "END";

    /** String constant, value: "ESCAPE" */
    protected final static String ESCAPE                = "ESCAPE";

    /** String constant, value: "EXISTS" */
    protected final static String EXISTS                = "EXISTS";

    /** String constant, value: "FETCH" */
    protected final static String FETCH                 = "FETCH";
    
    /** String constant, value: "FIRST" */
    protected final static String FIRST                 = "FIRST";

    /** String constant, value: "FOR" */
    protected final static String FOR                   = "FOR";

    /** String constant, value: "FROM" */
    protected final static String FROM                  = "FROM";

    /** String constant, value: "FULL" */
    protected final static String FULL                  = "FULL";
    
    /** String constant, value: "GRANDTOTAL" */
    protected final static String GRANDTOTAL            = "GRANDTOTAL";
    
    /** String constant, value: "GROUP BY" */
    protected final static String GROUP_BY              = "GROUP BY";
    
    /** String constant, value: "GROUPING SETS" */
    protected final static String GROUPING_SETS         = "GROUPING SETS";
    
    /** String constant, value: "HAVING" */
    protected final static String HAVING                = "HAVING";

    /** String constant, value: "HOURS" */
    protected final static String HOURS                 = "HOURS";

    /** String constant, value: "IN" */
    protected final static String IN                    = "IN";

    /** String constant, value: "INSERT" */
    protected final static String INSERT                = "INSERT";

    /** String constant, value: "INNER" */
    protected final static String INNER                 = "INNER";

    /** String constant, value: "INTO" */
    protected final static String INTO                  = "INTO";

    /** String constant, value: "IS" */
    protected final static String IS                    = "IS";

    /** String constant, value: "JOIN" */
    protected final static String JOIN                  = "JOIN";
    
    /** String constant, value: "LIKE" */
    protected final static String LIKE                  = "LIKE";

    /** String constant, value: "LEFT" */
    protected final static String LEFT                  = "LEFT";

    /** String constant, value: "MATCHED" */
    protected final static String MATCHED               = "MATCHED";
    
    /** String constant, value: "MERGE" */
    protected final static String MERGE                 = "MERGE";

    /** String constant, value: "MICROSECONDS" */
    protected final static String MICROSECONDS          = "MICROSECONDS";

    /** String constant, value: "MINUTES" */
    protected final static String MINUTES               = "MINUTES";

    /** String constant, value: "MONTHS" */
    protected final static String MONTHS                = "MONTHS";
    
    /** String constant, value: "MULTISET" */
    protected final static String MULTISET              = "MULTISET";
   
    /** String constant, value: "NOT" */
    protected final static String NOT                   = "NOT";

    /** String constant, value: "NULL" */
    protected final static String NULL                  = "NULL";

    /** String constant, value: "OF" */
    protected final static String OF                    = "OF";
    
    /** String constant, value: "ON" */
    protected final static String ON                    = "ON";

    /** String constant, value: "ONLY" */
    protected final static String ONLY                  = "ONLY";

    /** String constant, value: "ORDER BY" */
    protected final static String ORDER_BY              = "ORDER BY";

    /** String constant, value: "OUTER" */
    protected final static String OUTER                 = "OUTER";

    /** String constant, value: "READ" */
    protected final static String READ                  = "READ";

    /** String constant, value: "RIGHT" */
    protected final static String RIGHT                 = "RIGHT";

    /** String constant, value: "ROLLUP" */
    protected final static String ROLLUP                = "ROLLUP";

    /** String constant, value: "ROW" */
    protected final static String ROW                   = "ROW";

    /** String constant, value: "ROWS" */
    protected final static String ROWS                  = "ROWS";

    /** String constant, value: "SECONDS" */
    protected final static String SECONDS               = "SECONDS";

    /** String constant, value: "SELECT" */
    protected final static String SELECT                = "SELECT";

    /** String constant, value: "SELECTIVITY" */
    protected final static String SELECTIVITY           = "SELECTIVITY";

    /** String constant, value: "SET" */
    protected final static String SET                   = "SET";

    /** String constant, value: {@link #ASTERISK} */
    protected final static String STAR                  = ASTERISK;

    /** String constant, value: "SOME" */
    protected final static String SOME                  = "SOME";

    /** String constant, value: "TABLE" */
    protected final static String TABLE                 = "TABLE";

    /** String constant, value: "THEN" */
    protected final static String THEN                  = "THEN";

    /** String constant, value: "UPDATE" */
    protected final static String UPDATE                = "UPDATE";

    /** String constant, value: "USING" */
    protected final static String USING                 = "USING";

    /** String constant, value: "VALUES" */
    protected final static String VALUES                = "VALUES";

    /** String constant, value: "WITH" */
    protected final static String WITH                  = "WITH";

    /** String constant, value: "WHEN" */
    protected final static String WHEN                  = "WHEN";

    /** String constant, value: "WHERE" */
    protected final static String WHERE                 = "WHERE";

    /** String constant, value: "YEARS" */
    protected final static String YEARS                 = "YEARS";

    /**
     * This reference is used in
     * {@link #appendExternalSQL(SQLObject, StringBuffer)} and
     * should avoid endless loops searching for external
     * {@link SQLQuerySourceWriter} via call
     * to {@link SQLQueryObject#getSQL()}, if this
     * <code>SQLQuerySourceWriter</code> doesn't provide the methods to generate
     * the SQL source for the given <code>SQLQueryObject</code>. 
     */
    protected static Object lastExternalyProcessed = null;
    

    /* ****************************** static methods ******************************* */
    
    
    /**
     * @param queryObject
     * @return
     */
    static String getInterfaceName(Class sqlObjectClass)
    {
        if (sqlObjectClass == null) { return null; }

        StringBuffer className = null;
        String interfaceName = null;
        
        className = new StringBuffer(sqlObjectClass
                        .getName());
        
        // get the interface type of the given SQLQueryObject
        if (sqlObjectClass.getPackage().getName().endsWith("impl"))
        {
            int implStart = className.lastIndexOf(".impl.") + 1;
            int implEnd = implStart + 5;
            className.delete(implStart, implEnd);
        }
        // we are only working with interfaces
        if (sqlObjectClass.getName().endsWith("Impl"))
        {
            className.delete(className.length() - 4, className.length());
        }

        interfaceName = className.toString();
        return interfaceName;
    }

    /**
     * returns the <code>Method</code> 
     * <code>appendSQL(SQLObject,StringBuffer)</code> for the argument of the
     * same runtime type as the given <code>sqlObjectClass</code>  
     * @param sourceWriterClass the <code>SQLQuerySourceWriter</code> class on
     *      which the specific <code>appendSpecificSQL</code> method for the
     *      given <code>sqlObjectClass</code> should be found
     * @param queryObjectInterfaceClass
     * @param sqlObjectClass
     * @return the <code>Method appendSQL</code> for the runtime type of the
     *            given <code>sqlObjectClass</code>
     * @throws NoSuchMethodException
     */
    static Method getSpecificAppendSQLMethod(Class sourceWriterClass,
                                             Class queryObjectInterfaceClass) throws NoSuchMethodException
    {
        if (queryObjectInterfaceClass == null || sourceWriterClass == null)
        { 
            return null;
        }

        Method appendSQL = null;

        try
        {
            Class stringBufferClass = StringBuffer.class;
            Class[] methodArgTypes = new Class[] { queryObjectInterfaceClass,
                            stringBufferClass };
            appendSQL = sourceWriterClass.getDeclaredMethod("appendSpecificSQL", //$NON-NLS-1$
                            methodArgTypes);
        }
        catch (NoSuchMethodException nsme)
        {
            //StatementHelper.logError(NEW_LINE + sourceWriterClass.getName()
            //                + ": getSQL(" + interfaceName
            //                + ") not implemented. Given argument type: "
            //                + sqlObject.getClass().getName());
            //nsme.printStackTrace();
            
            // does this class extend a SourceWriter that has the method?
            // walk up the inheritance hierarchy
            Class superClass = sourceWriterClass.getSuperclass();
            if (superClass != null &&
                            SQLQuerySourceWriter.class.isAssignableFrom(superClass)) 
            {
                appendSQL =
                    getSpecificAppendSQLMethod(superClass, queryObjectInterfaceClass);
            } 
            else 
            {
                throw nsme;
            }
        }
        catch (IllegalArgumentException iae)
        {
            // TODO: handle exception properly
            iae.printStackTrace();
        }
        return appendSQL;
    }
    
    
    /* ***************************** public methods ***************************** */

    
    /**
     * Generic method to return the SQL source of the given <code>queryObject</code>.
     * @param queryObject
     * @return
     */
    public String getSQL(SQLQueryObject queryObject)
    {
        String sql = null;
        
        if (queryObject != null)
        {
            // register the sourceWriter as we assume that this SourceWriter is
            // only called under the covers and therefore correct by implementation
            // or is being called purposely on a subclass the base SourceWriter
            try
            {
                if (getSpecificAppendSQLMethod(queryObject) != null)
                {
                    SQLQuerySourceWriterProvider.getInstance().registerSourceWriter(this.getClass(),
                                    queryObject.getClass().getPackage().getName());
                }
            }
            catch (NoSuchMethodException e)
            {
                // don't register sourceWriter then here!
            }

            SQLQuerySourceFormat sourceFormat =
                queryObject.getSourceInfo().getSqlFormat();
            
            preserveComments =
                sourceFormat.isPreserveComments()
                && (queryObject instanceof QueryStatement 
                        || !sourceFormat.isGenerateCommentsForStatementOnly());
            
            
            delimitedIdentifierQuote = sourceFormat.getDelimitedIdentifierQuote();

            StringBuffer sb = new StringBuffer();
            appendSQL(queryObject, sb);
            sql = sb.toString();

            sql = filterOutEmptyLines(sql);

            this.lastSLCommentIndentMap = null;  // for reuse of this SourceWriter
        }
        return sql;
    }


    /* ***************************** protected methods ***************************** */


    /**
     * @param comment
     * @param sb
     */
    protected void appendComment(SQLComment comment, StringBuffer sb)
    {
        if (comment == null)  {  return; }
        
        if ((comment.getRelativePosition() == SQLComment.COMMENT_POSITION_NEXT_LINE
                        || comment.getRelativePosition() == SQLComment.COMMENT_POSITION_PREV_LINE)
                        && !isLastLineEmpty(sb))
        {
            int lastLineIndent = getLastLineIndent(sb);
            sb.append(NEW_LINE);
            appendSpace(sb, lastLineIndent); // check if that also applies for multiline comments
        }

        if (comment.isMultiLineComment())
        {
            String text = comment.getText();
            String trimText = text.trim();
            StringBuffer sbComment = new StringBuffer();
            if (trimText.startsWith(COMMENT_PREFIX_MULTI_LINE) == false) {
                sbComment.append(COMMENT_PREFIX_MULTI_LINE);
            }
            sbComment.append(text);
            if (trimText.endsWith(COMMENT_SUFFIX_MULTI_LINE) == false) {
                sbComment.append(COMMENT_SUFFIX_MULTI_LINE);
            }
            // indentSQLToLastLineLengthOfContainer(sbComment, sb);
            sb.append(sbComment);
            
            // multiline comment is delimited, doesn't need a line break
            if (getLastLineLength(sbComment) > 40
                            && !text.endsWith(NEW_LINE_STRING))
            {
                sb.append(NEW_LINE);
            }
        }
        else
        {
            String text = comment.getText();
            
            if (!isLastLineEmpty(sb))
            {
                sb.append(SPACE);
            }

            // for pretty printing we want to align comments at the end of line
            // that would only work if we always work on the global StringBuffer
            // but we can also have a partial one which will get indented later
            // and global messures don't work, therefore we use a Map and we
            // only align end-of-line comments within one StringBuffer
            if (comment.getRelativePosition()
                            == SQLComment.COMMENT_POSITION_LINE_END)
            {
                int currentIndent = getLastLineLength(sb);
                
                if (lastSLCommentIndentMap == null)
                {
                    lastSLCommentIndentMap = new HashMap();
                }
                
                if (lastSLCommentIndentMap.containsKey(sb))
                {
                    int lastSingleLineCommentStart =
                        ((Integer) lastSLCommentIndentMap.get(sb)).intValue();
                    if (lastSingleLineCommentStart > currentIndent)
                    {
                        appendSpace(sb, lastSingleLineCommentStart - currentIndent);
                    }
                    else
                    {
                        lastSLCommentIndentMap.put(sb, new Integer(currentIndent));
                    }
                }
                else
                {
                    lastSLCommentIndentMap.put(sb, new Integer(currentIndent));
                }
            }
            
            if (!text.startsWith(COMMENT_PREFIX_SINGLE_LINE))
            {
                sb.append(COMMENT_PREFIX_SINGLE_LINE);
            }
            sb.append(text);
            
            if (!text.endsWith(NEW_LINE_STRING))
            {
                sb.append(NEW_LINE);
            }
        }
        
    }

    /**
     * @param queryObject
     * @param sb
     */
    protected void appendCommentsPreceeding(SQLQueryObject queryObject, StringBuffer sb)
    {
        List comments = queryObject.getSourceInfo().getComments();
        
        if (comments != null)
        {
            for (Iterator it = comments.iterator(); it.hasNext();)
            {
                SQLComment comment = (SQLComment) it.next();
                
                if (comment.getRelativePosition()
                                == SQLComment.COMMENT_POSITION_PREV_LINE)
                {
                    appendComment(comment, sb);
                }
            }
        }
    }

    /**
     * @param queryObject
     * @param sb
     */
    protected void appendCommentsSucceeding(SQLQueryObject queryObject, StringBuffer sb)
    {
        List comments = queryObject.getSourceInfo().getComments();
        
        if (comments != null)
        {
            for (Iterator it = comments.iterator(); it.hasNext();)
            {
                SQLComment comment = (SQLComment) it.next();
                
                if (comment.getRelativePosition()
                                != SQLComment.COMMENT_POSITION_PREV_LINE)
                {
                    appendComment(comment, sb);
                }
            }
        }
    }

    /**
     * Appends the given data type name to the given string buffer.  The name could be the
     * name of a built-in type, a user-distinct type, or a user-defined type.
     * 
     * @param sb the string buffer to which the data type name should be appended
     * @param typeName the data type name to append
     * @since DTP 1.8.1
     */
    protected void appendDataTypeName(StringBuffer sb, String dataTypeName) {
        sb.append(dataTypeName);
    }

    /**
     * This method will be invoked if the given <code>queryObject</code> could
     * not have been processed successfully by this
     * <code>SQLQuerySourceWriter</code>. It will invoke
     * {@link SQLQueryObject#getSQL()} on the given <code>queryObject</code>
     * and append the returned SQL to the given <code>StringBuffer</code>.
     *  
     * @param queryObject
     * @param sb
     * @return <code>true</code> if an external <code>SQLQuerySourceWriter</code>
     *      was found to generate the SQL source for the given
     *      <code>queryObject</code>
     */
    protected boolean appendExternalSQL(SQLQueryObject queryObject, StringBuffer sb)
    {
        boolean foundExternalSourceWriter = false;
        // avoid the endless loop by keeping the reference to queryObject
        if (lastExternalyProcessed != queryObject)
        {
            // avoid the endless loop by keeping the reference lastExternalyProcessed
            // to the given queryObject,
            // getSQL() invokation could cause the endless loop, if getSQL()
            // forwards to this SQLQuerySourceWriter and appendSQL(SQLQueryObect,StringBuffer)
            // again doesn't find the proper appendSQL()-method and invokes this
            // method appendExternalSQL()
            lastExternalyProcessed = queryObject;
            String externalSQL = null;
            
            Class swClass = SQLQuerySourceWriterProvider.getInstance().getQuerySourceWriterClass(queryObject.getClass());
            SQLQuerySourceWriter sw = null;
            if (swClass != null) {
                try
                {
                    sw = (SQLQuerySourceWriter) swClass.newInstance();
                    
                    externalSQL = sw.getSQL(queryObject);
                    
                    if (externalSQL != null && externalSQL.length() > 0)
                    {
                        SQLQuerySourceWriterProvider.getInstance().registerSourceWriter(swClass, queryObject.getClass().getPackage().getName());
                        
                        StringBuffer externalSQLSB = new StringBuffer(externalSQL);
                        int lastLineIndent = getLastLineIndent(sb);
                        int externalSQLIndent = lastLineIndent + STANDARD_INDENT;
                        indentSQL(externalSQLSB, externalSQLIndent);
                        sb.append(externalSQLSB);
                        
                        foundExternalSourceWriter = true;
                        lastExternalyProcessed = null;
                    }
        
                }
                catch (InstantiationException e)
                {
                    e.printStackTrace();
                }
                catch (IllegalAccessException e)
                {
                    e.printStackTrace();
                }
            }
        }
        return foundExternalSourceWriter;
        
    }

    /**
     * Appends the given keyword to the given string buffer.  This gives the opportunity to control the
     * case of the generated code.
     * 
     * @param sb the string buffer to which the keyword should be appended
     * @param keyword the keyword to append
     */
    protected void appendKeyword(StringBuffer sb, String keyword) {
        sb.append(keyword);
    }
    
    /**
     * Appends the given function name to the given string buffer.  The function can be a built-in
     * or user-defined function.
     * 
     * @param sb the string buffer to which the function name should be appended
     * @param funcName the function name to append
     * @since DTP 1.8.1
     */
    protected void appendFunctionName(StringBuffer sb, String funcName) {
        sb.append(funcName);
    }
    
    /**
     * Appends the given identifier to the given string buffer.  The identifier 
     * might or might not be delimited or qualified.  (That is, it could be 
     * "bar", "foo"."bar", foo."bar" ...)
     * 
     * @param sb the string buffer to which the identifier should be appended
     * @param ident the identifier to append
     * @since DTP 1.8.1
     */
    protected void appendIdentifier(StringBuffer sb, String ident) {
        sb.append(ident);
    }
    
    /**
     * Appends one "indent unit" of spaces to the given string buffer.
     * 
     * @param sb the string buffer to which the indent space should be appended
     */
    protected void appendIndent(StringBuffer sb) {
        int indentSpace = getIndentUnitSize();
        appendSpace(sb, indentSpace);
    }
    
    /**
     * Appends the specified number of "indent units" to the given string buffer.
     * 
     * @param sb the string buffer to which the indent space should be appended
     * @param indentUnitCount the number of indent units to append
     */
    protected void appendIndent(StringBuffer sb, int indentUnitCount) {
        int indentSpace = getIndentUnitSize() * indentUnitCount;
        appendSpace(sb, indentSpace);
    }

    /**
     * Appends the given integer value to the given string buffer.
     * 
     * @param sb the string buffer to which the value should be appended
     * @param intVal the integer value to append
     */
    protected void appendInt(StringBuffer sb, int intVal) {
        sb.append(intVal);
    }
    
    /**
     * Appends a newline character ({@link #NEW_LINE}) to the given string buffer only if the last line
     * does not already end with a newline.
     *
     * @param sb the string buffer to which the newline should be appended
     */
    protected void appendNewLine(StringBuffer sb) {
        if (!isLastLineEmpty(sb)) {
            sb.append(NEW_LINE);
        }
    }
    
    /**
     * Appends the given operator (such as "+" or AND) to the given string buffer.
     * 
     * @param sb string buffer to which the operator should be appended
     * @param oper the operator to append
     */
    protected void appendOperator(StringBuffer sb, String oper) {
        sb.append(oper);
    }
    
    /**
     * Appends one space character to the given string buffer.
     * 
     * @param sb the string buffer to which the space should be appended
     */
    protected void appendSpace(StringBuffer sb) {
        sb.append(SPACE);
    }
    
    /**
     * Appends the given number of space characters to the given string buffer.
     * 
     * @param sb the string buffer to which the spaces should be appended
     */
    protected void appendSpace(StringBuffer sb, int number) {
        for (int i = 0; i < number; i++) {
            sb.append(SPACE);
        }
    }
    
    /**
     * Appends the given special register name to the given string buffer.  A
     * special register is a pre-defined value such as CURRENT_SCHEMA (or 
     * CURRENT SCHEMA).  Special registers are not delimited or qualified.
     * 
     * @param sb the string buffer to which to append the special register name
     * @param regName the special register name to append
     * @since DTP 1.8.1
     */
    protected void appendSpecialRegisterName(StringBuffer sb, String regName) {
        sb.append(regName);
    }
    
    /**
     * Appends the given symbol (such a paren or comma) to the given string buffer.
     * 
     * @param sb the string buffer to which the symbol should be appended
     * @param symbol the symbol to append
     */
    protected void appendSymbol(StringBuffer sb, char symbol) {
        sb.append(symbol);
    }

    /**
     * Appends an arbitrary string to the given string buffer.
     * 
     * @param sb the string buffer to which the string should be appended
     * @param str the string to append
     */
    protected void appendString(StringBuffer sb, String str) {
        sb.append(str);
    }
    
    /**
     * Appends the content of one string buffer to another.
     * 
     * @param sb the string buffer to which the content will be appended
     * @param bufToAppend the string buffer whose content will be appended to the other string buffer
     */
    protected void appendStringBuffer(StringBuffer sb, StringBuffer bufToAppend) {
        sb.append(bufToAppend);
    }
    
    /**
     * TODO: refactor this so where ever its invoked now to invoke a similar
     *  implementation in PlugIn org.eclipse.datatools.modelbase.sql
     */
    protected void appendSQL(SQLObject sqlObject, StringBuffer sb)
    {
        if (sqlObject == null) { return; }

        // this method gets mistakenly called for SQLQueryObjects, redirect
        if (sqlObject instanceof SQLQueryObject)
        {
            appendSQL((SQLQueryObject)sqlObject, sb);
            return;
        }
        
        try
        {
            Method getSQL = getSpecificAppendSQLMethod(sqlObject);

            //StringBuffer localSpanSQL = new StringBuffer();
            Object[] invokationArgs = new Object[] { sqlObject, sb };
            getSQL.setAccessible(true);
            getSQL.invoke(this, invokationArgs);
            //sb.append(localSpanSQL);
        }
        catch (NoSuchMethodException nsme)
        {
            // already been stacktraced
        }
        catch (IllegalAccessException iae)
        {
            // check if  getSQL.setAccessible(true); did work 10 lines above
            iae.printStackTrace();
        }
        catch (InvocationTargetException ite)
        {
            // TODO: handle exception properly
            ite.printStackTrace();
            ite.getTargetException().printStackTrace();
        }

    }

    /**
     * Generic method invoking the appropriate <code>appendSpecificSQL</code>
     * method for the runtime type of the given <code>queryObject</code>
     * appending to the given StringBuffer <code>sb</code>.
     * @param queryObject
     * @param sb
     */
    protected void appendSQL(SQLQueryObject queryObject, StringBuffer sb)
    {
        if (queryObject == null) { return; }

        try
        {
            Method getSQL = getSpecificAppendSQLMethod(queryObject);

            if (getSQL != null)
            {
                Object[] invokationArgs = new Object[] { queryObject, sb };
                getSQL.setAccessible(true);

                if (preserveComments) appendCommentsPreceeding(queryObject, sb);

                //int startIndex = sb.length();

                getSQL.invoke(this, invokationArgs);

//                // check if the given queryObject has a name in catalog format, that
//                // means the identifier is supposed to be delimited
//                String catalogName = queryObject.getName();
//                if (catalogName != null && catalogName.length() > 0)
//                {
//                    char idDelimiterQt = queryObject.getSourceInfo().getSqlFormat().getDelimitedIdentifierQuote();
//                    String sqlName = convertCatalogIdentifierToSQLFormat(catalogName, idDelimiterQt);
//                    if (!catalogName.equals(sqlName))
//                    {
//                        int i = sb.indexOf(catalogName, startIndex);
//                        while (i > -1) {
//                            sb.replace(i, i + catalogName.length(), sqlName);
//                            i = sb.indexOf(catalogName, i + catalogName.length());
//                        }
//                    }
//                }

                if (preserveComments) appendCommentsSucceeding(queryObject, sb);
            }
            else
                throw new NoSuchMethodException(
                                "appendSQL(" +
                                "\""+queryObject.getClass().getName()+"\")" +
                                " not found in "+this.getClass().getName());

        }
        catch (NoSuchMethodException nsme)
        {
            // already been stacktraced
            boolean isSqlGenerated = 
                appendExternalSQL(queryObject, sb);

            if (!isSqlGenerated) {
//                String interfaceName = getInterfaceName(queryObject.getClass());
//                    
//                StatementHelper.logError(NEW_LINE + this.getClass().getName()
//                              + ": getSQL(" + interfaceName
//                              + ") not implemented for given argument type: "
//                              + queryObject.getClass().getName());
                //nsme.printStackTrace();
            }
        }
        catch (IllegalAccessException iae)
        {
            // TODO: handle exception properly
            // check if  getSQL.setAccessible(true); 10 lines above was succesful
            iae.printStackTrace();
        }
        catch (IllegalArgumentException iae)
        {
            // TODO: handle exception properly
            iae.printStackTrace();
        }
        catch (InvocationTargetException ite)
        {
            // TODO: handle exception properly
            ite.printStackTrace();
            ite.getTargetException().printStackTrace();
        }
 
    }

    /**
     * Appends the FETCH FIRST n ROWS ONLY clause with the given row fetch limit to the 
     * given string buffer.
     * 
     * @param aRowFetchLimit the row fetch limit to use
     * @param sb the string buffer to which the clause should be appended
     */
    protected void appendSQLForFetchFirstClause(int aRowFetchLimit, StringBuffer sb) {
        if (aRowFetchLimit > 0) {
            appendKeyword(sb, FETCH);
            appendSpace(sb);
            appendKeyword(sb, FIRST);
            appendSpace(sb);
            /* When the row fetch limit is 1 we generate the abbreviated form 
             * FETCH FIRST ROW ONLY. */
            if (aRowFetchLimit == 1) {
                appendKeyword(sb, ROW);
            }
            else {
                appendInt(sb, aRowFetchLimit);
                appendSpace(sb);
                appendKeyword(sb, ROWS);
            }
            appendSpace(sb);
            appendKeyword(sb, ONLY);
        }
    }
    
    /** 
     * Appends the given large object length value to the given string buffer, after 
     * converting the length value to 'K', 'M', or 'G' units.
     *
     * @param size the length value to append
     * @param sb the string buffer to which the length should be appended
     */
    protected void appendSQLForLargeObjectSize(int size, StringBuffer sb) {
        int kilo = 1024,
            mega = 1024 * 1024,
            giga = 1024 * 1024 * 1024;

        if (size > kilo && (size % kilo == 0)) {
            if (size > mega && (size % mega == 0)) {
                if (size > giga && (size % giga == 0)) {
                    appendInt(sb, size/giga);
                    appendSymbol(sb, 'G');
                } else {
                    appendInt(sb, size/mega);
                    appendSymbol(sb, 'M');
                }
            } else {
                appendInt(sb, size/kilo);
                appendSymbol(sb, 'K');
            }
        } else if (size == Integer.MAX_VALUE) {
            appendInt(sb, 2);
            appendSymbol(sb, 'G');
        } else {
            appendInt(sb, size);
        }
    }

    /**
     * @param orderByClause
     * @param sb
     */
    protected void appendSQLForOrderByClause(List orderByClause, StringBuffer sb) {
        sb.append(ORDER_BY);
        sb.append(SPACE);

        for (Iterator it = orderByClause.iterator(); it.hasNext();) {
            OrderBySpecification orderBySpec = (OrderBySpecification) it.next();
            if (StatementHelper.isOrderBySpecificationValid(orderBySpec)) {
                appendSQL(orderBySpec, sb);
                if (it.hasNext()) {
                    sb.append(COMMA);
                    sb.append(SPACE);
                }
            }
        }
    }
 
    
    /**
     * Appends the proper SQL source for a List of any <code>SQLObject</code>
     * without enclosing parenthesis.
     *
     * @param sqlObjectList the List of <code>SQLObject</code>s
     * @param sb the <code>StringBuffer</code> to fill in
     */
    protected void appendSQLForSQLObjectList(List sqlObjectList, StringBuffer sb)
    {
        //int indent = getLastLineLength(sb);
        int indent = getLastLineIndent(sb) + STANDARD_INDENT;

        if (sqlObjectList != null)
        {
            for (Iterator it = sqlObjectList.iterator(); it.hasNext();)
            {
                SQLObject sqlObject = (SQLObject) it.next();
                if (sqlObject != null)
                {
                    appendSQL(sqlObject, sb);

                    if (it.hasNext())
                    {
                        sb.append(COMMA);

                        if (getLastLineLength(sb) > displayWidth - 20)
                        {
                            sb.append(NEW_LINE);
                            appendSpace(sb, indent);
                        }
                        else
                        {
                            sb.append(SPACE);
                        }
                    }
                }
            }
        }
    }

    /**
     * Appends the SQL source of the given table reference. It distinguishes
     * between the <code>TableReference</code> types {@link TableNested} and 
     * {@link TableJoined} and the <code>TableExpression</code> types
     * {@link TableInDatabase}, 
     * {@link org.eclipse.datatools.modelbase.sql.query.TableFunction}, 
     * {@link org.eclipse.datatools.modelbase.sql.query.TableWithSpecification} and 
     * - most important - {@link QueryExpressionBody}.
     *
     * @param tableRef the <code>TableReference</code>
     * @param sb
     */
    protected void appendSQLForTableExpression(TableReference tableRef, StringBuffer sb) {
        // TableReference is a subquery/tablequery?
        if (tableRef instanceof QueryExpressionBody) {
            QueryExpressionBody nestedQuery = (QueryExpressionBody) tableRef;
            
            appendSymbol(sb, PAREN_LEFT);
            appendSQL(nestedQuery, sb);
            appendSymbol(sb, PAREN_RIGHT);
            
            TableCorrelation tableCorr = nestedQuery.getTableCorrelation();
            if (tableCorr != null) {
                SQLQuerySourceInfo sourceInfo = nestedQuery.getSourceInfo();
                SQLQuerySourceFormat sourceFormat = sourceInfo.getSqlFormat();
                if (sourceFormat.getGenerateAsKeywordForTableCorrID() == true) {
                    appendSpace(sb);
                    appendKeyword(sb, AS);
                }
                appendSpace(sb);
                appendSQL(tableCorr, sb);
            }
        }
        else if (tableRef instanceof TableFunction) {
            TableFunction tableFunction = (TableFunction) tableRef;
            appendSQL(tableFunction, sb);
        }
        else  {
            // is there a new type to be considered?
            // invoke the general dispatcher appendSQL method to get the correct runtime type
            appendSQL((SQLQueryObject)tableRef, sb);
        } 
    }

    /**
     * Appends the SQL source of the given <code>TableInDatabase</code> object
     * to the given string buffer without any table correlation ID ("AS" alias). 
     * Depending on the {@link SQLQuerySourceFormat#getQualifyIdentifiers()} option,
     * this methods appends the simple name or the schema qualified name of the table.
     * 
     * @param tableInDB the table to append
     * @param sb the string buffer to which to append the table name
     */
    protected void appendSQLForTableInDatabase(TableInDatabase tableInDB, StringBuffer sb) {
        if (tableInDB != null) {
            String tableName = tableInDB.getName();
            Schema schema = null;
            char delimQuoteChar = getDelimitedIdentifierQuote();
            ISQLObjectNameHelper nameHelper = null;
            
            Table dbTable = tableInDB.getDatabaseTable();
            if (dbTable != null) {
                schema = tableInDB.getDatabaseTable().getSchema();
                
                /* If we have a SQL model Database object associated with the query model table object,
                 * then try to get an object name helper from the SQL model. If we can get an object name 
                 * helper, we will use that to delimit and qualify the table name rather than our local 
                 * service. */
                Database db = getDatabase(dbTable);
                if (db != null) {
                    nameHelper = getSQLObjectNameHelper(db);
                    if (nameHelper != null) {
                        String delimQuoteStr = Character.toString(delimQuoteChar);
                        nameHelper.setIdentifierQuoteString(delimQuoteStr);
                    }
                }
            }

            /* Determine whether or not we should qualify the table name with a schema name, based on the 
             * presence or absence of a schema object and the source format setting. */
            boolean qualify = false;
            if (schema != null && schema.getName() != null && schema.getName().length() > 0) {
                qualify = true;
                
                SQLQuerySourceInfo sourceInfo = tableInDB.getSourceInfo();
                SQLQuerySourceFormat sourceFormat = sourceInfo.getSqlFormat();
                int qualifySpec = sourceFormat.getQualifyIdentifiers();
                if (qualifySpec == SQLQuerySourceFormat.QUALIFY_IDENTIFIERS_NEVER
                 || qualifySpec == SQLQuerySourceFormat.QUALIFY_IDENTIFIERS_WITH_TABLE_NAMES) {
                    qualify = false;
                }
                else if (qualifySpec == SQLQuerySourceFormat.QUALIFY_IDENTIFIERS_WITH_SCHEMA_NAMES) {
                    qualify = true;
                }
                else if (qualifySpec == SQLQuerySourceFormat.QUALIFY_IDENTIFIERS_IN_CONTEXT) {
                    qualify = !StatementHelper.omitSchema(tableInDB);
                }
            }

            String sqlFormatName = "";
            
            /* If we have a name helper, use it.  Qualify the table name if necessary. */
            if (nameHelper != null) {
                if (qualify == true) {
                    sqlFormatName = nameHelper.getQualifiedNameInSQLFormat( dbTable );
                }
                else {
                    sqlFormatName = nameHelper.getNameInSQLFormat( dbTable );
                }
                appendIdentifier(sb, sqlFormatName);
            }
            /* Otherwise use our local service to delimit the table name. */
            else {
                if (qualify) {
                    String schemaName = schema.getName();
                    sqlFormatName = convertCatalogIdentifierToSQLFormat(schemaName, delimQuoteChar);
                    sqlFormatName = sqlFormatName + DOT;
                }
                String sqlFormatTableName = convertCatalogIdentifierToSQLFormat(tableName, getDelimitedIdentifierQuote());
                sqlFormatName = sqlFormatName + sqlFormatTableName;
                appendIdentifier(sb, sqlFormatName);
            }
        }
    }

    /**
     * Appends the SQL source <code>sqlObject.getSQL()</code> to the given
     * StringBuffer <code>sb</code>, breaking the lines if the
     * <code>displayWidth</code> is exceeded.
     * 
     * @param sqlObject
     * @param sb the StringBuffer to appand the SQL source of the given
     *        <code>sqlObject</code> to
     * @param indent number of white space ' ' to be inserted infromt of new
     *        lines
     * @param displayWidth
     */
    protected void appendWithConditionalLineBreaks(SQLObject sqlObject, StringBuffer sb, int indent, int displayWidth) {
        StringBuffer localSB = new StringBuffer();
        appendSQL(sqlObject, localSB);
        
        int lineLength = getLastLineLength(sb);
        int nameSpaceLength = localSB.length();
        
        if (lineLength + nameSpaceLength > displayWidth) {
            sb.append(NEW_LINE);
            indentSQL(localSB, indent + STANDARD_INDENT);
        } 
        else {
            sb.append(SPACE);
        }
        sb.append(localSB);
    }

    /**
     * Converts the given identifier from "catalog" format (that is, the way the identifier is stored in
     * the database catalog) to "SQL" format (that is, a way suitable for use in a SQL statement) using the
     * given identifier quote character.  That means an identifier containing spaces or special characters
     * will be "delimited" by the quote chars and any internal quote chars will be doubled.
     * 
     * @param catalogIdentifier the identifier to convert
     * @param idDelimiterQuote the quote character (such as ") to use to delimit the identifier
     * @since DTP 1.8.1
     */
    protected String convertCatalogIdentifierToSQLFormat(String catalogIdentifier, char idDelimiterQuote) {
        return StatementHelper.convertCatalogIdentifierToSQLFormat( catalogIdentifier, idDelimiterQuote );
    }

    /**
     * Filters out empty lines and lines with only whitespaces from given
     * String <code>sql</code>.
     * 
     * @param sql
     * @return filtered String <code>sql</code>
     */
    protected String filterOutEmptyLines(String sql)
    {
        // filter out empty lines and lines with only whitespaces "\s"
        // TODO: don't remove new lines within String literals
        // TODO: better approach make sure there is no empty lines generated
        String previousSql = sql;
        sql = sql.replaceAll("\n(\\s*\\n)+", NEW_LINE_STRING);
        if (previousSql.length() > sql.length()) {
//            StatementHelper.logError(this.getClass().getName()+NEW_LINE_STRING
//                            + "...find out why we generate an empty line here!"
//                            + NEW_LINE_STRING
//                            + previousSql.replaceAll("\n", "/n")
//                            + " was substituted with " +NEW_LINE_STRING 
//                            + sql.replaceAll("\n", "/n")
//                            + "\n .../n = new line character\n");
        }
        
        if (sql.startsWith( NEW_LINE_STRING ))
        {
            sql = sql.substring(1);
        }
        return sql;
    }


    /**
     * @return
     */
    protected char getDelimitedIdentifierQuote() {
        return delimitedIdentifierQuote;
    }

    /**
     * Gets the number of spaces for the current "indent unit".
     *  
     * @return the standard indent size
     */
    protected int getIndentUnitSize() {
        return indentUnitSize;
    }
    
    /**
     * Returns the indentation of the last line in the given StringBuffer.
     * The indentation is determined by the number of white space
     * <code>character</code>s ({@link #SPACE}) following the last new line
     * <code>character</code> '\n' ({@link #NEW_LINE}).
     * If no line break is found, the number of white space characters at the
     * beginning of the given StringBuffer is returned.
     * @see #SPACE
     * @see #NEW_LINE
     * @param sb a StringBuffer
     * @return the number of white space characters in the last line of the
     *         given StringBuffer sb
     */
    protected int getLastLineIndent(StringBuffer sb)
    {
        int lastIndexOfLineBreak = sb.lastIndexOf(NEW_LINE_STRING);
        int i = 0; // last line indent

        for (i = 0; i < sb.length()-lastIndexOfLineBreak-1; i++)
        {
            if (sb.charAt(lastIndexOfLineBreak+i+1) != SPACE) {
                break;
            }
        }
        return i;
    }
    

    /**
     * Gets the Database object associated with the given Table object.
     * 
     * @param table the table for which the database is wanted
     * @return the database associated with the table
     */
    private Database getDatabase(Table table) {
        Database db = null;
        
        if (table != null) {
            Schema schema = table.getSchema();
            if (schema != null) {
                db = schema.getCatalog() == null ? schema.getDatabase() : schema.getCatalog().getDatabase();
            }
        }
        
        return db;
    }
    
    /**
     * Gets a SQL Object name helper for the given database, if any.
     * 
     * @param database the current database
     * @return the name helper, or null if none found for the current database
     */
    private ISQLObjectNameHelper getSQLObjectNameHelper(Database database) {
        ISQLObjectNameHelper nameHelper = null;
        
        if (database != null) {
            /* Get the current database type. */
            String currentDBVendor = database.getVendor();
            
            /* Get an array of extenders of the SQL Object Name Handler extension point. */
            IExtensionRegistry extensionRegistry = Platform.getExtensionRegistry();
            IExtensionPoint nameHandlerExtensionPoint = 
                extensionRegistry.getExtensionPoint(SQL_OBJECT_NAME_HELPER);
            
            if (nameHandlerExtensionPoint != null) {
                IExtension [] nameHandlerExtensions = nameHandlerExtensionPoint.getExtensions();

                /* Scan the array to get an extender registered for the current database type, if any. 
                 * Stop on the first one found. */
                int i = 0;
                while (i < nameHandlerExtensions.length && nameHelper == null) {
                    IExtension ext = nameHandlerExtensions[i];
                    IConfigurationElement [] configElements = ext.getConfigurationElements();
                    int j = 0;
                    while (j < configElements.length && nameHelper == null) {
                        String extVendor = configElements[j].getAttribute(SQL_OBJECT_NAME_HELPER_DBTYPE);
                        if (currentDBVendor.equalsIgnoreCase(extVendor)) {
                            try {
                                Object executableExtension = 
                                    configElements[j].createExecutableExtension(SQL_OBJECT_NAME_HELPER_CLASS);
                                if (executableExtension instanceof ISQLObjectNameHelper) {
                                    nameHelper = (ISQLObjectNameHelper) executableExtension;
                                }
                            }
                            catch(CoreException ex) {
                                // ignore error
                            }
                        }
                        j++;
                    }
                    i++;
                }
            }
        }

        return nameHelper;
    }
  
    /**
     * Returns the length of the last line in the given StringBuffer. The length
     * is determined by the number of characters following the last new line
     * character '\n' ({@link #NEW_LINE}).
     * If no line break is found, the length of the given
     * StringBuffer is returned.
     * @see #NEW_LINE
     * @param sb a StringBuffer
     * @return the length of the last line in the given StringBuffer
     */
    protected int getLastLineLength(StringBuffer sb)
    {
        // TODO: that should not consider line length including comments on end of line or separate line!!! 
        
        int lastIndexOfLineBreak = sb.lastIndexOf(NEW_LINE_STRING);
        int lastLineLength = 0;

        if (lastIndexOfLineBreak == -1) {
            lastLineLength = sb.length();
        } else {
            lastLineLength = sb.length()-lastIndexOfLineBreak-1;
        }
        return lastLineLength;
    }

    /**
     * returns the <code>Method</code> 
     * <code>appendSQL(SQLObject,StringBuffer)</code> for the argument of the
     * same runtime type as the given <code>sqlObject</code>  
     * @param sourceWriterClass the <code>SQLQuerySourceWriter</code> class on
     *      which the specific <code>appendSpecificSQL</code> method for the
     *      given <code>sqlObject</code> should be found
     * @param sqlObject
     * @return the <code>Method appendSQL</code> for the runtime type of the
     *            given <code>sqlObject</code>
     */
    protected Method getSpecificAppendSQLMethod(Class sourceWriterClass,
                                                SQLObject sqlObject) throws NoSuchMethodException
    {
        if (sqlObject == null) { return null; }

        String interfaceName = null;
        Method appendSQL = null;

        Class sqlObjectClass = sqlObject.getClass();
        Class sqlObjectInterfaceClass = sqlObjectClass;
        
        if (sqlObjectClass.getName().endsWith("Impl"))
        {
            // if we have an impl we need to find its interface as all
            // appendSQL methods have the interface as argument
            // Class.forName doesn't help us in the eclipse runtime as
            // the class loader of the SQLQuery model has no access to its
            // extending plugins (no runtime dependency)
            interfaceName = getInterfaceName(sqlObject.getClass());
            Class[] sqlObjectInterfaces = sqlObjectClass.getInterfaces();
            
            for (int i = 0; i < sqlObjectInterfaces.length; i++)
            {
                Class interfaceClass = sqlObjectClass.getInterfaces()[i];
                if (interfaceClass.getName().equals(interfaceName))
                {
                    sqlObjectInterfaceClass = interfaceClass;
                    break;
                }
            }
        }
        appendSQL = getSpecificAppendSQLMethod(sourceWriterClass, 
                            sqlObjectInterfaceClass);

        return appendSQL;
    }

    /**
     * returns the <code>Method</code> 
     * <code>appendSQL(SQLObject,StringBuffer)</code> for the argument of the
     * same runtime type as the given <code>sqlObject</code>  
     * @param sqlObject
     * @return the <code>Method appendSQL</code> for the runtime type of the
     *            given <code>sqlObject</code>
     */
    protected Method getSpecificAppendSQLMethod(SQLObject sqlObject) throws NoSuchMethodException
    {
        return getSpecificAppendSQLMethod(this.getClass(), sqlObject);
    }

    /**
     * indents the given StringBuffer with <code>indent</code> number of
     * spaces on a new line. a new-line will optionally be appended to the
     * StringBuffer before appending the white space characters. If the last
     * line is empty already (only contains white space), the StringBuffer will
     * be stripped of any white space exceeding the given <code>indent</code>
     * or will be appended the white space that are missing to match the given
     * <code>indent</code>.
     * 
     * @param sb
     * @param indent
     */
    protected void indentOnNewLine(StringBuffer sb, int indent) {
        int lastLineLength = getLastLineLength(sb);
        if (isLastLineEmpty(sb)) {
            if (lastLineLength > indent) {
                int hangOver = lastLineLength - indent;
                sb.delete(sb.length() - hangOver, sb.length());
            }
            else {
                appendSpace(sb, indent - lastLineLength);
            }
        }
        else {
            sb.append(NEW_LINE);
            appendSpace(sb, indent);
        }
    }

    /**
     * Indents the given StringBuffer <code>sql</code> according to the length
     * of the given <code>indent</code>, means every line in <code>sql</code>
     * will be prefixed with indent number of white space characters ' ' except
     * for the first line. This method is useful to line up the SQL source of
     * the given StringBuffer <code>sql</code> with the indentation of a
     * StringBuffer that it will be appended to.
     * 
     * @param sql
     * @param indent
     */
    protected void indentSQL(StringBuffer sql, int indent) {
        String newLine = NEW_LINE_STRING;
        StringBuffer indentWhiteSpace = new StringBuffer(indent);
        appendSpace(indentWhiteSpace, indent);
        int i = sql.indexOf(newLine, 0);
        while (i > 0) {
            sql.insert(i+1, indentWhiteSpace);
            i = sql.indexOf(newLine, i+1);
        }
    }

    /**
     * Indents the given StringBuffer <code>subcomponentToIndent</code>
     * according to the length of the last line in the given StringBuffer
     * <code>container</code>,
     * means every line in <code>subcomponentToIndent</code> will be prefixed
     * with white space characters ' ' to line up with the end of the last
     * character in the last line of the <code>container</code> StringBuffer.
     *
     * @param subcomponentToIndent
     * @param container
     */
    protected void indentSQLToLastLineLengthOfContainer(StringBuffer subcomponentToIndent, StringBuffer container) {
        String newLine = NEW_LINE_STRING;
        int indentLength = getLastLineLength(container);
        StringBuffer indent = new StringBuffer(indentLength);
        appendSpace(indent, indentLength);

        int i = subcomponentToIndent.indexOf(newLine, 0);
        while (i > 0)
        {
            subcomponentToIndent.insert(i+1, indent);
            i = subcomponentToIndent.indexOf(newLine, i+1);
        }
    }

    /**
     * Returns the <code>true</code> if the last line in the given StringBuffer
     * is empty. The last line is regarded empty, if the given StringBuffer only
     * contains white-space <code>character</code>s ({@link #SPACE}) or after
     * the last new-line <code>character</code> '\n' ({@link #NEW_LINE}) in the
     * given StringBuffer only contains white-space <code>character</code>s.
     * @see #SPACE
     * @see #NEW_LINE
     * @param sb a StringBuffer
     * @return the number of white space characters in the last line of the
     *         given StringBuffer sb
     */
    protected boolean isLastLineEmpty(StringBuffer sb)
    {
        boolean isLastLineEmpty = true;
        
        int lastIndexOfLineBreak = sb.lastIndexOf(NEW_LINE_STRING);
        
        for (int i = lastIndexOfLineBreak + 1; i < sb.length(); i++)
        {
            if (sb.charAt(i) != SPACE) {
                isLastLineEmpty = false;
                break;
            }
        }
        return isLastLineEmpty;
    }

    /**
     * Determins if the given <code>column</code> should be qualified according to the current configuration 
     * parameters specified or if it is ambiguous.
     * <p>
     * Referenced configuration parameters:
     * <ul>
     * <li>alwaysQualifyColumnNamesForMultipleTables
     * <li>qualifyColumnNamesInSubqueriesWhenQualifiedInSuperQuery
     * <li>alwaysQualifyColumnNamesForSubqueries
     * <li>alwaysQualifyColumnNamesReferencedInSubqueries
     * </ul>
     * 
     * @param column the column to check
     * @return true when the column name should be qualified, otherwise false
     */
    protected boolean isQualifiedColumnNameRequired(ValueExpressionColumn column) {
        boolean shouldQualify = false;
        
        /* Get the QuerySelect that contains the column reference.*/
        QuerySelect colRefSelect = (QuerySelect) StatementHelper.getEContainerRecursively(column, QuerySelect.class);
        if (colRefSelect != null) {
            /* Check whether we need to qualify the column name because there are multiple tables in scope. */
            if (shouldQualify == false && alwaysQualifyColumnNamesForMultipleTables == true) {
                /* Get the FROM clause of the QuerySelect and determine if it has multiple tables. */
                List fromTables = StatementHelper.getTableExpressionsInQuerySelect(colRefSelect);
                if (fromTables != null && fromTables.size() > 1) {
                    shouldQualify = true;
                }

                /* If the immediately enclosing query didn't have multiple tables, then check to see if an
                 * enclosing QuerySelect has multiple tables. Example:
                 *   SELECT T1.COL1, T3.COL3 FROM T1, T3 WHERE EXISTS (SELECT COL2 FROM T2 WHERE T1.COL1 = T2.COL2) */
                if (shouldQualify == false && qualifyColumnNamesInSubqueriesWhenQualifiedInSuperQuery) {
                    QuerySelect subSelect = colRefSelect;
                    QuerySelect superSelect = (QuerySelect) StatementHelper.getEContainerRecursively(subSelect, QuerySelect.class);
                    
                    /* Check the super queries, if any. */
                    while (shouldQualify == false && superSelect != null) {
                        fromTables = StatementHelper.getTableExpressionsInQuerySelect(superSelect);
                        if (fromTables != null && fromTables.size() > 1) {
                            shouldQualify = true;
                        }
                        
                        subSelect = superSelect;
                        superSelect = (QuerySelect) StatementHelper.getEContainerRecursively(subSelect, QuerySelect.class);
                    }
                }
            }

            /* Check whether we should qualify the column because it is in a subquery.  (That is, we need to 
             * distinguish between columns in scope of the subquery and columns of superior scope.  For example,
             *   "SELECT * FROM T1 WHERE EXISTS (SELECT * FROM T2 WHERE T1.COL1 = T2.COL2)" */
            if (shouldQualify == false && alwaysQualifyColumnNamesForSubqueries == true) {              
                QuerySelect superSelect = (QuerySelect) StatementHelper.getEContainerRecursively(colRefSelect, QuerySelect.class);
                if (superSelect != null) {
                    shouldQualify = true;
                }
            }

            /* Check whether we should qualify the column because it belongs to a table in a different  
             * QuerySelect than that which contains the column reference.  For example, the reference "T1.COL1" in 
             * the statement "SELECT COL1 FROM T1 WHERE EXISTS (SELECT * FROM T2 WHERE T1.COL1 = COL2)" refers
             * to a table in the enclosing QuerySelect.
             * Note: a column contained in an OrderBySpecification has no QuerySelect as container, but can also 
             * never be referred-to in a subselect. */
            if (shouldQualify == false && alwaysQualifyColumnNamesReferencedInSubqueries == true) {
                /* Get the QuerySelect that contains the table the column reference belongs to. */                
                TableExpression colTableExpr = column.getTableExpr();
                /* Don't qualify if the table associated with the column is the same as the query select
                 * expression containing the column.  (That prevents an internal ORDER BY expression from
                 * getting qualified.)  Otherwise go on to check for the condition described above. */
                if (colTableExpr != colRefSelect) {                
                    QuerySelect tableSelect = StatementHelper.getQuerySelectForTableReference(colTableExpr);
                    if (colRefSelect != null && tableSelect != colRefSelect) {
                        shouldQualify = true;
                    }
                }
            }
        }
        
        /* Check whether the column name is ambiguous in its context. 
         * (This applies to columns in Merge statements as well as Selects.) */
        if (shouldQualify == false) {
           shouldQualify = StatementHelper.isColumnNameAmbiguous(column);
        }
        
        return shouldQualify;
    }

    /**
     * Sets the number of spaces for the current "indent unit".
     *  
     * @param size the standard indent size to set
     */
    protected void setIndentUnitSize(int size) {
        indentUnitSize = size;
    }
    
    /**
     * Removes multiple occurences of white space characters {@link #NEW_LINE}
     * and {@link #SPACE} in the given StringBuffer.
     *
     * @param toBeTrimmed
     */
    protected void trimWhiteSpace(StringBuffer toBeTrimmed) {
        StringBuffer trimmed = new StringBuffer();
        char lastChar = SPACE;
        for (int i = 0; i < toBeTrimmed.length(); i++)
        {
            char currentChar = toBeTrimmed.charAt(i);

            if (currentChar == NEW_LINE) {
                if (lastChar != SPACE) {
                    trimmed.append(SPACE);
                    lastChar = SPACE;
                }
            }
            else if (currentChar != SPACE || lastChar != SPACE) {
                trimmed.append(currentChar);
                lastChar = currentChar;
            }
        }
        toBeTrimmed.replace(0, toBeTrimmed.length(), trimmed.toString());
    }

    /**
     * takes the given StringBuffer that holds the SQL source of the given
     * OrderBySpecification and appends {@link #DESC}if the
     * OrderBySpecification is descending (
     * <code>orderBySpec.isDescending() == true</code>)
     */
    protected void wrapSQL(OrderBySpecification orderBySpec, StringBuffer toWrapUp) {
        if (orderBySpec != null) {
            OrderingSpecType orderingSpecType = orderBySpec.getOrderingSpecOption();
            if (orderingSpecType != OrderingSpecType.NONE_LITERAL) {
                appendSpace(toWrapUp);
                appendSpecificSQL(orderingSpecType, toWrapUp);
            }
            NullOrderingType nullOrderingType = orderBySpec.getNullOrderingOption();
            if (nullOrderingType != NullOrderingType.NONE_LITERAL) {
                appendSpace(toWrapUp);
                appendSpecificSQL(nullOrderingType, toWrapUp);
            }
        }
    }

    /**
     * takes the given StringBuffer that holds the SQL source of the given
     * SQLPredicate and appends {@link #NOT}if the SQLPredicate is negated (
     * <code>pred.isNegatedPredicate() == true</code>)
     */
    protected void wrapSQL(Predicate pred, StringBuffer toWrapUp) {
        if (pred != null) {
            if (pred.isNegatedPredicate()) {
                StringBuffer prefix = new StringBuffer(4);
                appendOperator(prefix, NOT);
                appendSpace(prefix);
                indentSQLToLastLineLengthOfContainer(toWrapUp, prefix);
                toWrapUp.insert(0, prefix);
            }
            wrapSQL((QuerySearchCondition) pred, toWrapUp);
        }
    }

    /**
     * takes the given StringBuffer that holds the SQL source of the given
     * SQLSearchCondition and puts it in parenthesis and appends {@link #NOT}if
     * the SQLSearchCondition is negated (
     * <code>pred.isNegatedCondition() == true</code>)
     */
    protected void wrapSQL(QuerySearchCondition cond, StringBuffer toWrapUp) {
        if (cond != null) {
            if (cond.isNegatedCondition()) {
                StringBuffer prefix = new StringBuffer(4);
                appendOperator(prefix, NOT);
                appendSpace(prefix);
                appendSymbol(prefix, PAREN_LEFT);
                indentSQLToLastLineLengthOfContainer(toWrapUp, prefix);
                toWrapUp.insert(0, prefix);
                appendSymbol(toWrapUp, PAREN_RIGHT);
            }
        }
    }

    /**
     * takes the given StringBuffer that holds the SQL source of the given
     * SQLValueExpression and appends the unary operator, if present (
     * <code>expr.getUnaryOperator() != null</code>)
     */
    protected void wrapSQL(QueryValueExpression expr, StringBuffer toWrapUp) {
        if (expr != null) {
            ValueExpressionUnaryOperator unaryOp = expr.getUnaryOperator();
            if (unaryOp != null) {
                StringBuffer prefix = new StringBuffer();
                appendSpecificSQL(unaryOp, prefix);
                indentSQLToLastLineLengthOfContainer(toWrapUp, prefix);
                toWrapUp.insert(0, prefix);
            }
        }
    }


    /* ********************** protected append specific SQL methods *************************** */


    /**
     * @see org.eclipse.datatools.modelbase.sql.datatypes.ApproximateNumericDataType#getSQL()
     */
    protected void appendSpecificSQL(ApproximateNumericDataType dataType, StringBuffer sb) {
        String typeName = dataType.getName();
        if (typeName != null && typeName.length() > 0) {
            appendKeyword(sb, typeName);
        }
        else {
            PrimitiveType primitiveType = dataType.getPrimitiveType();
            appendSpecificSQL(primitiveType, sb);
        }

        if (dataType.getPrecision() != 0) {
            appendSpace(sb);
            appendSymbol(sb, PAREN_LEFT);
            appendInt(sb, dataType.getPrecision());
            appendSymbol(sb, PAREN_RIGHT);
        }
    }

    /**
     * @see org.eclipse.datatools.modelbase.sql.datatypes.ArrayDataType#getSQL()
     */
    protected void appendSpecificSQL(ArrayDataType arrayDataType, StringBuffer sb) {
        ElementType elementType = arrayDataType.getElementType();
        if (elementType != null) {
            DataType dataType = elementType.getDataType();
            if (dataType != null) {
                appendSQL(dataType, sb);
            }
        }

        appendSpace(sb);
        appendKeyword(sb, ARRAY);
        
        if (arrayDataType.getMaxCardinality() != 0) {
            appendSymbol(sb, BRACKET_LEFT);
            appendInt(sb, arrayDataType.getMaxCardinality());
            appendSymbol(sb, BRACKET_RIGHT);
        }
    }

    /**
     * @see org.eclipse.datatools.modelbase.sql.datatypes.BinaryStringDataType#getSQL()
     */
    protected void appendSpecificSQL(BinaryStringDataType dataType, StringBuffer sb) {
        String typeName = dataType.getName();
        
        /* DB2 supports the "FOR BIT DATA" suffix on CHARACTER data types, which transforms
         * them into binary string datatypes.  When generating SQL, need to substitute the length 
         * back into the name, which looks like this:  CHARACTER () FOR BIT DATA 
         * Note: this would be better handled by creating a DB2-specific datatype class so
         * we can generate the SQL in the DB2-specific source writer. */
        int parenLoc = typeName.indexOf('(');
        if (parenLoc > 0) {
            StringBuffer lengthSB = new StringBuffer();

            if (dataType.getLength() > 0)  {
                if (dataType.getPrimitiveType() == PrimitiveType.BINARY_LARGE_OBJECT_LITERAL) {
                    appendSQLForLargeObjectSize(dataType.getLength(), lengthSB);
                }
                else {
                    appendInt(lengthSB, dataType.getLength());
                }
            }
            String dataTypeLen = lengthSB.toString();
            typeName = typeName.substring(0, parenLoc + 1) + dataTypeLen + typeName.substring(parenLoc + 1);
            appendKeyword(sb, typeName);
        }
        else {
            if (typeName != null && typeName.length() > 0) {
                appendKeyword(sb, typeName);
            }
            else {
                PrimitiveType primitiveType = dataType.getPrimitiveType();
                appendSpecificSQL(primitiveType, sb);
            }

            int dataTypeLen = dataType.getLength();
            if (dataTypeLen > 0) {
                appendSymbol(sb, PAREN_LEFT);

                if (dataType.getPrimitiveType() == PrimitiveType.BINARY_LARGE_OBJECT_LITERAL) {
                    appendSQLForLargeObjectSize(dataTypeLen, sb);
                }
                else {
                    appendInt(sb, dataTypeLen);
                }
                appendSymbol(sb, PAREN_RIGHT);
            }
        }
    }

    /**
     * @see org.eclipse.datatools.modelbase.sql.query.CallStatement#getSQL()
     */
    protected void appendSpecificSQL(CallStatement callStmt, StringBuffer sb) {
        appendKeyword(sb, CALL);
        appendSpace(sb);
        ProcedureReference ref = callStmt.getProcedureRef();
        appendSpecificSQL(ref, sb);
       
        appendSpace(sb);
        appendSymbol(sb, PAREN_LEFT);
        List argList = callStmt.getArgumentList();
        appendSQLForSQLObjectList(argList, sb);
        appendSymbol(sb, PAREN_RIGHT);
    }
    
    /**
     * @see org.eclipse.datatools.modelbase.sql.datatypes.CharacterStringDataType#getSQL()
     */
    protected void appendSpecificSQL(CharacterStringDataType dataType, StringBuffer sb) {
        String typeName = dataType.getName();
        if (typeName != null && typeName.length() > 0) {
            appendKeyword(sb, typeName);
        }
        else {
            PrimitiveType primitiveType = dataType.getPrimitiveType();
            appendSpecificSQL(primitiveType, sb);
        }

        if (dataType.getLength() > 0) {
            appendSpace(sb);
            appendSymbol(sb, PAREN_LEFT);

            if (dataType.getPrimitiveType() == PrimitiveType.CHARACTER_LARGE_OBJECT_LITERAL
                            || dataType.getPrimitiveType() == PrimitiveType.NATIONAL_CHARACTER_LARGE_OBJECT_LITERAL) {
                appendSQLForLargeObjectSize(dataType.getLength(), sb);
            }
            else {
                appendInt(sb, dataType.getLength());
            }
            appendSymbol(sb, PAREN_RIGHT);
        }
    }

    /**
     * @see org.eclipse.datatools.modelbase.sql.query.ColumnName#getSQL()
     */
    protected void appendSpecificSQL(ColumnName columnName, StringBuffer sb) {
        if (columnName != null) {
            String colNameStr = columnName.getName();
            String sqlFormatName = convertCatalogIdentifierToSQLFormat(colNameStr, getDelimitedIdentifierQuote());
            appendIdentifier(sb, sqlFormatName);
        }
    }

    /**
     * @see org.eclipse.datatools.modelbase.sql.datatypes.DateDataType#getSQL()
     */
    protected void appendSpecificSQL(DateDataType dataType, StringBuffer sb) {
        if (dataType != null) {
            String typeName = dataType.getName();
            if (typeName != null && typeName.length() > 0) {
                appendDataTypeName(sb, typeName);
            }
            else {
                PrimitiveType primitiveType = dataType.getPrimitiveType();
                appendSpecificSQL(primitiveType, sb);
            }
        }
    }

    /**
     * @see DistinctUserDefinedType#getSQL()
     */
    protected void appendSpecificSQL(DistinctUserDefinedType type, StringBuffer sb) {
        if (type != null) {
            char delimChar = getDelimitedIdentifierQuote();
            Schema schema = type.getSchema();
            if (schema != null && schema.getName() != null && schema.getName().length() > 0) {
                String schemaName = schema.getName();
                String sqlFormatSchemaName = convertCatalogIdentifierToSQLFormat(schemaName, delimChar);
                appendIdentifier(sb, sqlFormatSchemaName);
                appendSymbol(sb, DOT);           
            }

            String typeName = type.getName();
            String sqlFormatTypeName = convertCatalogIdentifierToSQLFormat(typeName, delimChar);
            appendDataTypeName(sb, sqlFormatTypeName);
        }
    }

    /**
     * @see org.eclipse.datatools.modelbase.sql.datatypes.FixedPrecisionDataType#getSQL()
     */
    protected void appendSpecificSQL(FixedPrecisionDataType dataType, StringBuffer sb) {
        String typeName = dataType.getName();
        if (typeName != null && typeName.length() > 0) {
            appendKeyword(sb, typeName);
        }
        else {
            PrimitiveType primitiveType = dataType.getPrimitiveType();
            appendSpecificSQL(primitiveType, sb);
        }

        if (dataType.getPrecision() != 0) {
            appendSpace(sb);
            appendSymbol(sb, PAREN_LEFT);
            appendInt(sb, dataType.getPrecision());

            if (dataType.getScale() != 0) {
                appendSymbol(sb, COMMA);
                appendInt(sb, dataType.getScale());
            }

            appendSymbol(sb, PAREN_RIGHT);
        }
    }

    /**
     * @see org.eclipse.datatools.modelbase.sql.query.GroupingExpression#getSQL()
     */
    protected void appendSpecificSQL(GroupingExpression groupingExpr, StringBuffer sb) {
        if (groupingExpr != null) {
            QueryValueExpression valExpr = groupingExpr.getValueExpr();
            appendSQL(valExpr, sb);
        }
    }

    /**
     * @see org.eclipse.datatools.modelbase.sql.query.GroupingSets#getSQL()
     */
    protected void appendSpecificSQL(GroupingSets groupingSets, StringBuffer sb) {
        if (groupingSets != null) {
            appendKeyword(sb, GROUPING_SETS);
            appendSpace(sb);

            appendSymbol(sb, PAREN_LEFT);
            List groupingSetsElementList = groupingSets.getGroupingSetsElementList();
            appendSQLForSQLObjectList(groupingSetsElementList, sb);
            appendSymbol(sb, PAREN_RIGHT);
        }
    }

    /**
     * @see org.eclipse.datatools.modelbase.sql.query.GroupingSetsElementExpression#getSQL()
     */
    protected void appendSpecificSQL(GroupingSetsElementExpression groupingSetsElementExpr, StringBuffer sb) {
        if (groupingSetsElementExpr != null) {
            Grouping grouping = groupingSetsElementExpr.getGrouping();
            appendSQL(grouping, sb);
        }
    }

    
    /**
     * @see org.eclipse.datatools.modelbase.sql.query.GroupingSetsElementSublist#getSQL()
     */
    protected void appendSpecificSQL(GroupingSetsElementSublist groupingSetsSublist, StringBuffer sb) {
        if (groupingSetsSublist != null) {
            appendSymbol(sb, PAREN_LEFT);
            List groupingSetsElementExprList = groupingSetsSublist.getGroupingSetsElementExprList();
            appendSQLForSQLObjectList(groupingSetsElementExprList, sb);
            appendSymbol(sb, PAREN_RIGHT);
        }
    }

    /**
     * @see org.eclipse.datatools.modelbase.sql.datatypes.IntegerDataType#getSQL()
     */
    protected void appendSpecificSQL(IntegerDataType dataType, StringBuffer sb) {
        String typeName = dataType.getName();
        if (typeName != null && typeName.length() > 0) {
            appendKeyword(sb, typeName);
        }
        else {
            PrimitiveType primitiveType = dataType.getPrimitiveType();
            appendSpecificSQL(primitiveType, sb);
        }
    }

    /**
     * @see org.eclipse.datatools.modelbase.sql.query.MergeInsertSpecification#getSQL()
     */
    protected void appendSpecificSQL(MergeInsertSpecification insertSpec, StringBuffer sb) {
        appendKeyword(sb, WHEN);
        appendSpace(sb);
        appendKeyword(sb, NOT);
        appendSpace(sb);
        appendKeyword(sb, MATCHED);
        appendSpace(sb);
        appendKeyword(sb, THEN);
        appendNewLine(sb);
        appendIndent(sb);
        appendKeyword(sb, INSERT);
        appendSpace(sb);
        appendSymbol(sb, PAREN_LEFT);
        List targetColList = insertSpec.getTargetColumnList();
        appendSQLForSQLObjectList(targetColList, sb);
        appendSymbol(sb, PAREN_RIGHT);
        appendNewLine(sb);
        appendIndent(sb);
        appendKeyword(sb, VALUES);
        appendSpace(sb);
        ValuesRow sourceValuesRow = insertSpec.getSourceValuesRow();
        appendSQL(sourceValuesRow, sb);
    }

    /**
     * @see org.eclipse.datatools.modelbase.sql.query.MergeOnCondition#getSQL()
     */
    protected void appendSpecificSQL(MergeOnCondition onCondition, StringBuffer sb) {
        QuerySearchCondition searchCond = onCondition.getSearchCondition();
        appendSQL(searchCond, sb);
    }

    /**
     * @see org.eclipse.datatools.modelbase.sql.query.MergeSourceTable#getSQL()
     */
    protected void appendSpecificSQL(MergeSourceTable sourceTable, StringBuffer sb) {
        TableReference tableRef = sourceTable.getTableRef();
        if (tableRef instanceof QueryExpressionBody) {
            appendNewLine(sb);
            appendIndent(sb);
        }
        appendSQLForTableExpression(tableRef, sb);
    }

    /**
     * @see org.eclipse.datatools.modelbase.sql.query.MergeTargetTable#getSQL()
     */
    protected void appendSpecificSQL(MergeTargetTable targetTable, StringBuffer sb) {
        TableExpression tableExpr = targetTable.getTableExpr();
        appendSQL(tableExpr, sb); 
    }
    
    /**
     * @see org.eclipse.datatools.modelbase.sql.query.MergeUpdateSpecification#getSQL()
     */
    protected void appendSpecificSQL(MergeUpdateSpecification updateSpec, StringBuffer sb) {
        appendKeyword(sb, WHEN);
        appendSpace(sb);
        appendKeyword(sb, MATCHED);
        appendSpace(sb);
        appendKeyword(sb, THEN);
        appendNewLine(sb);
        appendIndent(sb);
        appendKeyword(sb, UPDATE);
        appendSpace(sb);
        appendKeyword(sb, SET);
        List assignExprList = updateSpec.getAssignementExprList();
        Iterator assignExprListIter = assignExprList.iterator();
        while (assignExprListIter.hasNext()) {
            UpdateAssignmentExpression assignExpr = (UpdateAssignmentExpression) assignExprListIter.next();
            appendNewLine(sb);
            appendIndent(sb, 2);
            appendSQL(assignExpr, sb);
            
            if (assignExprListIter.hasNext()) {
                appendSymbol(sb, COMMA);
            }
        }
    }
 
    /**
     * @see org.eclipse.datatools.modelbase.sql.datatypes.MultisetDataType#getSQL()
     */
    protected void appendSpecificSQL(MultisetDataType multisetDataType, StringBuffer sb) {
        ElementType elementType = multisetDataType.getElementType();
        if (elementType != null) {
            DataType dataType = elementType.getDataType();
            if (dataType != null) {
                appendSQL(dataType,sb);
            }
        }

        appendSpace(sb);
        appendKeyword(sb, MULTISET);
    }

    /**
     * @see org.eclipse.datatools.modelbase.sql.query.NullOrderingType#getSQL()
     */
    protected void appendSpecificSQL(NullOrderingType nullOrderingType, StringBuffer sb) {
        if (nullOrderingType != null) {
            int typeVal = nullOrderingType.getValue();
            switch(typeVal){
                case NullOrderingType.NULLS_FIRST:
                    appendKeyword(sb, NULL_ORDERING_TYPE_NULLS_FIRST);
                    break;
                case NullOrderingType.NULLS_LAST:
                    appendKeyword(sb, NULL_ORDERING_TYPE_NULLS_LAST);
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * @see org.eclipse.datatools.modelbase.sql.query.OrderByOrdinal#getSQL()
     */
    protected void appendSpecificSQL(OrderByOrdinal orderByOrd, StringBuffer sb) {
        if (orderByOrd != null) {
            StringBuffer localSb = new StringBuffer();
            int ordVal = orderByOrd.getOrdinalValue();
            appendInt(localSb, ordVal);
            wrapSQL((OrderBySpecification) orderByOrd, localSb);
            appendStringBuffer(sb, localSb);
        }
    }

    /**
     * @see org.eclipse.datatools.modelbase.sql.query.OrderByResultColumn#getSQL()
     */
    protected void appendSpecificSQL(OrderByResultColumn orderByExpr, StringBuffer sb) {
        if (orderByExpr != null && orderByExpr.getResultCol() != null) {
            StringBuffer localSB = new StringBuffer();
            ResultColumn resultColumn = orderByExpr.getResultCol();
            
            String sqlFormatResultCol = "";
            String resultColName = resultColumn.getName();
            if (resultColName != null) {
                sqlFormatResultCol = convertCatalogIdentifierToSQLFormat(resultColName, getDelimitedIdentifierQuote());
            }
            else {
                QueryValueExpression resultColValExpr = resultColumn.getValueExpr();
                sqlFormatResultCol = getSQL(resultColValExpr);
            }

            appendString(localSB, sqlFormatResultCol);
            wrapSQL((OrderBySpecification) orderByExpr, localSB);
            
            appendStringBuffer(sb, localSB);
        }
    }

    /**
     * @see org.eclipse.datatools.modelbase.sql.query.OrderByValueExpression#getSQL()
     */
    protected void appendSpecificSQL(OrderByValueExpression orderByExpr, StringBuffer sb) {
        if (orderByExpr != null) {
            StringBuffer localSb = new StringBuffer();
            QueryValueExpression valExpr = orderByExpr.getValueExpr();
            appendSQL(valExpr, localSb);
            wrapSQL((OrderBySpecification) orderByExpr, localSb);
            appendStringBuffer(sb, localSb);
        }
    }

    /**
     * @see org.eclipse.datatools.modelbase.sql.query.OrderingSpecType#getSQL()
     */
    protected void appendSpecificSQL(OrderingSpecType orderingType, StringBuffer sb) {
        if (orderingType != null) {
            int typeVal = orderingType.getValue();
            switch(typeVal){
                case OrderingSpecType.ASC:
                    appendKeyword(sb, ORDERING_SPEC_TYPE_ASC);
                    break;
                case OrderingSpecType.DESC:
                    appendKeyword(sb, ORDERING_SPEC_TYPE_DESC);
                    break;
                default:
                    break;
            }
        }
    }    

    /**
     * @see org.eclipse.datatools.modelbase.sql.query.PredicateBasic#getSQL()
     */
    protected void appendSpecificSQL(PredicateBasic pred, StringBuffer sb) {
        if (pred != null) {
            StringBuffer sbPred = new StringBuffer();

            QueryValueExpression leftValExpr = pred.getLeftValueExpr();
            if (leftValExpr != null) {
                appendSQL(leftValExpr, sbPred);
            }

            appendSpace(sbPred);

            PredicateComparisonOperator predCompOp = pred.getComparisonOperator();
            if (predCompOp != null) {
                appendSpecificSQL(predCompOp, sbPred);
            }

            appendSpace(sbPred);

            QueryValueExpression rightValExpr = pred.getRightValueExpr();
            if (rightValExpr != null) {
                appendSQL(rightValExpr, sbPred);
            }

            if (pred.isHasSelectivity()) {
                appendSpace(sbPred);
                appendKeyword(sbPred, SELECTIVITY);
                appendSpace(sbPred);
                Integer predSelVal = pred.getSelectivityValue();
                int predSelValInt = predSelVal.intValue();
                appendInt(sbPred, predSelValInt);
            }

            wrapSQL((Predicate) pred, sbPred);
            appendStringBuffer(sb, sbPred);
        }
    }
    
    /**
     * @see org.eclipse.datatools.modelbase.sql.query.PredicateBetween#getSQL()
     */
    protected void appendSpecificSQL(PredicateBetween predBetween, StringBuffer sb) {
        if (predBetween != null) {
            StringBuffer sbPred = new StringBuffer();

            if (predBetween.isNotBetween()) {
                appendOperator(sbPred, NOT);
                appendSpace(sbPred);
            }
            
            QueryValueExpression leftValExpr = predBetween.getLeftValueExpr();
            appendSQL(leftValExpr, sbPred);

            appendSpace(sbPred);
            appendKeyword(sbPred, BETWEEN);
            appendSpace(sbPred);
            
            QueryValueExpression rightValExpr1 = predBetween.getRightValueExpr1();
            appendSQL(rightValExpr1, sbPred);

            appendSpace(sbPred);
            appendOperator(sbPred, AND);
            appendSpace(sbPred);

            QueryValueExpression rightValExpr2 = predBetween.getRightValueExpr2();
            appendSQL(rightValExpr2, sbPred);

            wrapSQL((Predicate) predBetween, sbPred);
            appendStringBuffer(sb, sbPred);
        }
    }

    /**
     * @see org.eclipse.datatools.modelbase.sql.query.PredicateComparisonOperator#getSQL()
     */
    protected void appendSpecificSQL(PredicateComparisonOperator op, StringBuffer sb) {
        if (op != null) {
            int opVal = op.getValue();

            switch (opVal) {
                case PredicateComparisonOperator.EQUAL:
                    appendOperator(sb, EQUAL);
                    break;
                case PredicateComparisonOperator.NOT_EQUAL:
                    appendOperator(sb, NOT_EQUAL);
                    break;
                case PredicateComparisonOperator.LESS_THAN:
                    appendOperator(sb, LESS_THAN);
                    break;
                case PredicateComparisonOperator.GREATER_THAN:
                    appendOperator(sb, GREATER_THAN);
                    break;
                case PredicateComparisonOperator.LESS_THAN_OR_EQUAL:
                    appendOperator(sb, LESS_THAN_OR_EQUAL);
                    break;
                case PredicateComparisonOperator.GREATER_THAN_OR_EQUAL:
                    appendOperator(sb, GREATER_THAN_OR_EQUAL);
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * @see org.eclipse.datatools.modelbase.sql.query.PredicateExists#getSQL()
     */
    protected void appendSpecificSQL(PredicateExists predExists, StringBuffer sb) {
        if (predExists != null) {
            StringBuffer sbPred = new StringBuffer();

            appendKeyword(sbPred, EXISTS);
            appendSpace(sbPred);
            appendSymbol(sbPred, PAREN_LEFT);
            QueryExpressionBody queryExpr = predExists.getQueryExpr();
            appendSQL(queryExpr, sbPred);
            appendSymbol(sbPred, PAREN_RIGHT);

            wrapSQL((Predicate) predExists, sbPred);
            indentSQLToLastLineLengthOfContainer(sbPred, sb);
            appendStringBuffer(sb, sbPred);
        }
    }

    /**
     * @see org.eclipse.datatools.modelbase.sql.query.PredicateInValueList#getSQL()
     */
    protected void appendSpecificSQL(PredicateInValueList predInValueList, StringBuffer sb)
    {
        StringBuffer sbPred = new StringBuffer();

        appendSQL(predInValueList.getValueExpr(), sbPred);
        sbPred.append(SPACE);

        if (predInValueList.isNotIn())
        {
            sbPred.append(NOT);
            sbPred.append(SPACE);
        }

        sbPred.append(IN);
        sbPred.append(SPACE);

        if (predInValueList.getValueExprList() != null)
        {
            sbPred.append(PAREN_LEFT);

/*            for (Iterator valIt = predInValueList.getValueExprList().iterator(); valIt
                            .hasNext();)
            {
                QueryValueExpression valueExpr = (QueryValueExpression) valIt
                                .next();
                appendSQL(valueExpr, sbPred);
                sbPred.append(COMMA);
                sbPred.append(SPACE);
            }
            sbPred.delete(sbPred.length() - 2, sbPred.length());
*/
            appendSQLForSQLObjectList(predInValueList.getValueExprList(), sbPred);
            sbPred.append(PAREN_RIGHT);

        }

        wrapSQL((Predicate) predInValueList, sbPred);
        sb.append(sbPred);
    }

    /**
     * @see org.eclipse.datatools.modelbase.sql.query.PredicateInValueRowSelect#getSQL()
     */
    protected void appendSpecificSQL(PredicateInValueRowSelect predInRowSelect,
                           StringBuffer sb)
    {
        StringBuffer sbPred = new StringBuffer();

        if (predInRowSelect.getValueExprList() != null)
        {
            sbPred.append(PAREN_LEFT);

/*            for (Iterator valIt = predInRowSelect.getValueExprList().iterator(); valIt
                            .hasNext();)
            {
                QueryValueExpression valueExpr = (QueryValueExpression) valIt
                                .next();
                appendSQL(valueExpr, sbPred);
                sbPred.append(COMMA);
                sbPred.append(SPACE);
            }
            sbPred.delete(sbPred.length() - 2, sbPred.length());
*/
            appendSQLForSQLObjectList(predInRowSelect.getValueExprList(), sbPred);
            sbPred.append(PAREN_RIGHT);

        }

        sbPred.append(SPACE);

        if (predInRowSelect.isNotIn())
        {
            sbPred.append(NOT);
            sbPred.append(SPACE);
        }

        sbPred.append(IN);
        sbPred.append(SPACE);

        if (predInRowSelect.getQueryExpr() != null)
        {
            sbPred.append(PAREN_LEFT);
            appendSQL(predInRowSelect.getQueryExpr(), sbPred);
            sbPred.append(PAREN_RIGHT);
        }

        wrapSQL((Predicate) predInRowSelect, sbPred);
        indentSQLToLastLineLengthOfContainer(sbPred,sb);
        sb.append(sbPred);
    }

    /**
     * @see org.eclipse.datatools.modelbase.sql.query.PredicateInValueSelect#getSQL()
     */
    protected void appendSpecificSQL(PredicateInValueSelect predInSelect, StringBuffer sb)
    {
        StringBuffer sbPred = new StringBuffer();

        appendSQL(predInSelect.getValueExpr(), sbPred);
        sbPred.append(SPACE);

        if (predInSelect.isNotIn())
        {
            sbPred.append(NOT);
            sbPred.append(SPACE);
        }

        sbPred.append(IN);
        sbPred.append(SPACE);

        if (predInSelect.getQueryExpr() != null)
        {
            sbPred.append(PAREN_LEFT);
            appendSQL(predInSelect.getQueryExpr(), sbPred);
            sbPred.append(PAREN_RIGHT);
        }

        wrapSQL((Predicate) predInSelect, sbPred);
        indentSQLToLastLineLengthOfContainer(sbPred,sb);
        sb.append(sbPred);
    }

    /**
     * @see org.eclipse.datatools.modelbase.sql.query.PredicateIsNull#getSQL()
     */
    protected void appendSpecificSQL(PredicateIsNull predNull, StringBuffer sb)
    {
        StringBuffer sbPred = new StringBuffer();

        appendSQL(predNull.getValueExpr(), sbPred);
        sbPred.append(SPACE);
        sbPred.append(IS);
        sbPred.append(SPACE);

        if (predNull.isNotNull())
        {
            sbPred.append(NOT);
            sbPred.append(SPACE);
        }
        sbPred.append(NULL);

        wrapSQL((Predicate) predNull, sbPred);
        sb.append(sbPred);
    }

    /**
     * @see org.eclipse.datatools.modelbase.sql.query.PredicateLike#getSQL()
     */
    protected void appendSpecificSQL(PredicateLike like, StringBuffer sb)
    {
        StringBuffer sbPred = new StringBuffer();

        appendSQL(like.getMatchingValueExpr(), sbPred);
        sbPred.append(SPACE);
        if (like.isNotLike())
        {
            sbPred.append(NOT);
            sbPred.append(SPACE);
        }
        sbPred.append(LIKE);
        sbPred.append(SPACE);
        appendSQL(like.getPatternValueExpr(), sbPred);

        if (like.getEscapeValueExpr() != null)
        {
            sbPred.append(SPACE);
            sbPred.append(ESCAPE);
            sbPred.append(SPACE);
            appendSQL(like.getEscapeValueExpr(), sbPred);
        }

        wrapSQL((Predicate) like, sbPred);
        sb.append(sbPred);

    }


    /**
     * @see org.eclipse.datatools.modelbase.sql.query.PredicateQuantifiedRowSelect#getSQL()
     */
    protected void appendSpecificSQL(PredicateQuantifiedRowSelect predQuantified, StringBuffer sb) {
        if (predQuantified != null) {
            StringBuffer sbPred = new StringBuffer();

            appendSymbol(sbPred, PAREN_LEFT);
            List valExprList = predQuantified.getValueExprList();
            appendSQLForSQLObjectList(valExprList, sbPred);
            appendSymbol(sbPred, PAREN_RIGHT);
            appendSpace(sbPred);

            appendOperator(sbPred, EQUAL);
            appendSpace(sbPred);

            PredicateQuantifiedType predQuantType = predQuantified.getQuantifiedType();
            appendSpecificSQL(predQuantType, sbPred);
            appendSpace(sbPred);

            QueryExpressionRoot queryExpr = predQuantified.getQueryExpr();
            if (queryExpr != null) {
                appendSymbol(sbPred, PAREN_LEFT);
                appendSQL(queryExpr, sbPred);
                appendSymbol(sbPred, PAREN_RIGHT);
            }

            wrapSQL((Predicate) predQuantified, sbPred);
            indentSQLToLastLineLengthOfContainer(sbPred, sb);
            appendStringBuffer(sb, sbPred);
        }
    }

    /**
     * @see org.eclipse.datatools.modelbase.sql.query.PredicateQuantifiedType#getSQL()
     */
    protected void appendSpecificSQL(PredicateQuantifiedType quantifier, StringBuffer sb)
    {

        int operator = quantifier.getValue();

        switch (operator)
        {
            case PredicateQuantifiedType.ALL:
                sb.append(ALL);
                break;

            case PredicateQuantifiedType.ANY:
                sb.append(ANY);
                break;

            case PredicateQuantifiedType.SOME:
                sb.append(SOME);
                break;

            default:
                break;
        }

    }

    /**
     * @see org.eclipse.datatools.modelbase.sql.query.PredicateQuantifiedValueSelect#getSQL()
     */
    protected void appendSpecificSQL(PredicateQuantifiedValueSelect predQuantified, StringBuffer sb)
    {
        StringBuffer sbPred = new StringBuffer();

        appendSQL(predQuantified.getValueExpr(), sbPred);
        sbPred.append(SPACE);

        appendSpecificSQL(predQuantified.getComparisonOperator(), sbPred);
        sbPred.append(SPACE);

        appendSpecificSQL(predQuantified.getQuantifiedType(), sbPred);
        sbPred.append(SPACE);

        if (predQuantified.getQueryExpr() != null)
        {
            sbPred.append(PAREN_LEFT);
            appendSQL(predQuantified.getQueryExpr(), sbPred);
            sbPred.append(PAREN_RIGHT);
        }

        wrapSQL((Predicate) predQuantified, sbPred);
        indentSQLToLastLineLengthOfContainer(sbPred, sb);
        sb.append(sbPred);
    }

    /**
     * @see org.eclipse.datatools.modelbase.sql.datatypes.PrimitiveType#getSQL()
     */
    protected void appendSpecificSQL(PrimitiveType primitiveType, StringBuffer sb) {
        if (primitiveType != null) {
            //sb.append(StatementHelper.convertCatalogIdentifierToSQLFormat(primitiveType.getName(), getDelimitedIdentifierQuote()));
            // ! primitiveType CLOB .getName() == CHARACTER_LARGE_OBJECT
            String typeName = DataTypeHelper.getPrimitiveTypeName(primitiveType);
            appendDataTypeName(sb, typeName);
        }
    }

    /**
     * @see org.eclipse.datatools.modelbase.sql.query.procedurereference#getSQL()
     */
    protected void appendSpecificSQL(ProcedureReference procRef, StringBuffer sb) {
        if (procRef != null && procRef.getProcedure() != null) {   
            Procedure proc = procRef.getProcedure();        
            String schemaName = null;
            Schema schema = proc.getSchema();
            if (schema != null) {
                schemaName = schema.getName();
            }
            
            char quoteChar = getDelimitedIdentifierQuote();
            if (schemaName != null) {
                String convertedSchemaName = convertCatalogIdentifierToSQLFormat(schemaName, quoteChar);
                appendString(sb, convertedSchemaName);
                appendSymbol(sb, DOT);
            }
        
            String procName = proc.getName();
            if (procName != null) {
                String convertedProcName = convertCatalogIdentifierToSQLFormat(procName, quoteChar);
                appendString(sb, convertedProcName);
            }
        }
    }
    
    /**
     * @see org.eclipse.datatools.modelbase.sql.query.QueryCombined#getSQL()
     */
    protected void appendSpecificSQL(QueryCombined qryComb, StringBuffer sb) {
        if (qryComb != null) {
            int combinedIndent = getLastLineLength(sb);
            QueryExpressionBody leftQuery = qryComb.getLeftQuery();
            appendSQL(leftQuery, sb);
            appendNewLine(sb);
            appendSpace(sb, combinedIndent);
            QueryCombinedOperator queryCombinedOp = qryComb.getCombinedOperator();
            appendSpecificSQL(queryCombinedOp, sb);
            appendNewLine(sb);

            /* Do we have a nested QueryCombined on the right side?  If so, then it must have been in 
             * parens for precedence indication because the parser has precedence from left to right 
             * (that is, left recursion). */
            boolean isRightQueryNested = qryComb.getRightQuery() instanceof QueryCombined;
            if (isRightQueryNested) {
                appendSymbol(sb, PAREN_LEFT);
                appendNewLine(sb);
                appendSpace(sb, 4);
            }
            appendSpace(sb, combinedIndent);
            QueryExpressionBody rightQuery = qryComb.getRightQuery();
            appendSQL(rightQuery, sb);
            if (isRightQueryNested) {
                appendNewLine(sb);
                appendSpace(sb, combinedIndent);
                appendSymbol(sb, PAREN_RIGHT);
            }

            List sortSpecList = qryComb.getSortSpecList();
            if (StatementHelper.isOrderByClauseContainsValidOrderBySpecification(sortSpecList)) {
                appendNewLine(sb);
                appendSpace(sb, combinedIndent);
                appendSpace(sb, STANDARD_INDENT);
                appendSQLForOrderByClause(sortSpecList, sb);
            }

            int rowFetchLimit = qryComb.getRowFetchLimit();
            if (rowFetchLimit > 0) {
                appendSpace(sb);
                appendSQLForFetchFirstClause(rowFetchLimit, sb);
            }
        }
    }

    /**
     * @see org.eclipse.datatools.modelbase.sql.query.QueryCombinedOperator#getSQL()
     */
    protected void appendSpecificSQL(QueryCombinedOperator op, StringBuffer sb) {
        if (op != null) {
            int opVal = op.getValue();
            switch (opVal) {
                case QueryCombinedOperator.UNION:
                    appendOperator(sb, UNION);
                    break;
                case QueryCombinedOperator.UNION_ALL:
                    appendOperator(sb, UNION_ALL);
                    break;
                case QueryCombinedOperator.INTERSECT:
                    appendOperator(sb, INTERSECT);
                    break;
                case QueryCombinedOperator.INTERSECT_ALL:
                    appendOperator(sb, INTERSECT_ALL);
                    break;
                case QueryCombinedOperator.EXCEPT:
                    appendOperator(sb, EXCEPT);
                    break;
                case QueryCombinedOperator.EXCEPT_ALL:
                    appendOperator(sb, EXCEPT_ALL);
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * @see org.eclipse.datatools.modelbase.sql.query.QueryDeleteStatement#getSQL()
     */
    protected void appendSpecificSQL(QueryDeleteStatement deleteStmt, StringBuffer sb)
    {

        sb.append(DELETE);
        appendSpace(sb, 1);
        sb.append(FROM);
        appendSpace(sb, 1);
        appendSQL(deleteStmt.getTargetTable(), sb);
        appendNewLine(sb);

        if (deleteStmt.getWhereClause() != null)
        {
            appendSpace(sb, 1);
            sb.append(WHERE);
            appendSpace(sb, 1);
            appendSQL(deleteStmt.getWhereClause(), sb);
        }

        if (deleteStmt.getWhereCurrentOfClause() != null)
        {
            sb.append("  CURSOR REFERENCE TO BE getSQL()ed IN "
                            + deleteStmt.getClass() + "! ");
        }

    }

    /**
     * @see org.eclipse.datatools.modelbase.sql.query.QueryExpressionRoot#getSQL()
     */
    protected void appendSpecificSQL(QueryExpressionRoot qryExprRoot, StringBuffer sb)
    {
        int withTableIndent = STANDARD_INDENT;

        if (qryExprRoot != null) {
            List withTableSpecList = qryExprRoot.getWithClause();

            if (withTableSpecList != null && !withTableSpecList.isEmpty()) {

                sb.append(WITH);
                sb.append(NEW_LINE);
                appendSpace(sb,withTableIndent);

                for (Iterator withIt = withTableSpecList.iterator(); withIt
                                .hasNext();)
                {
                    WithTableSpecification tableWith =
                        (WithTableSpecification) withIt.next();

                    appendSQL(tableWith, sb);

                    if (withIt.hasNext()) {
                        sb.append(COMMA);
                        sb.append(NEW_LINE);
                        appendSpace(sb, withTableIndent);
                    }
                }

                appendNewLine(sb);
            }
            appendSQL(qryExprRoot.getQuery(), sb);
        }
    }


    /**
     * @see org.eclipse.datatools.modelbase.sql.query.QueryInsertStatement#getSQL()
     */
    protected void appendSpecificSQL(QueryInsertStatement insertStmt, StringBuffer sb)
    {

        sb.append(INSERT);
        appendSpace(sb, 1);
        sb.append(INTO);
        appendSpace(sb, 1);
        if (insertStmt.getTargetTable() != null)
        {
            appendSQL(insertStmt.getTargetTable(), sb);
        }

        if (!insertStmt.getTargetColumnList().isEmpty())
        {
            appendSpace(sb, 1);

            List targetColumnList = insertStmt.getTargetColumnList();

            sb.append(PAREN_LEFT);

/*          for (Iterator it = insertStmt.getTargetColumnList().iterator(); it
                            .hasNext();)
            {
                ValueExpressionColumn column = (ValueExpressionColumn) it
                                .next();
                appendSQL(column, sb);
                sb.append(COMMA);
                sb.append(SPACE);
            }
            //delete the last comma and space
            sb.delete(sb.length() - 2, sb.length());
*/
            appendSQLForSQLObjectList(targetColumnList, sb);
            sb.append(PAREN_RIGHT);
        }

        if (insertStmt.getSourceQuery() != null)
        {
            appendNewLine(sb);
            appendSpace(sb, STANDARD_INDENT);
            appendSQL(insertStmt.getSourceQuery(),sb);
        }
        else {
            appendNewLine(sb);
            appendSpace(sb, STANDARD_INDENT);
            sb.append(VALUES);
            appendSpace(sb, 1);
            if (!insertStmt.getSourceValuesRowList().isEmpty())  {
                for (Iterator it = insertStmt.getSourceValuesRowList().iterator(); it.hasNext();){
                    ValuesRow row = (ValuesRow) it.next();
                    //sb.append(PAREN_LEFT);
                    appendSQL(row, sb);
                    //sb.append(PAREN_RIGHT);
                    if (it.hasNext())
                    {
                        sb.append(COMMA);
                        sb.append(NEW_LINE);
                        appendSpace(sb, 9);
                    }
                }
            }
        }

    }

    /**
     * @see org.eclipse.datatools.modelbase.sql.query.QueryMergeStatement#getSQL()
     */
    protected void appendSpecificSQL(QueryMergeStatement mergeStmt, StringBuffer sb) {
        appendKeyword(sb, MERGE);
        appendSpace(sb);
        appendKeyword(sb, INTO);
        appendSpace(sb);   
        MergeTargetTable targetTable = mergeStmt.getTargetTable();
        appendSQL(targetTable, sb);
        
        appendNewLine(sb);
        appendKeyword(sb, USING);
        appendSpace(sb);
        MergeSourceTable sourceTable = mergeStmt.getSourceTable();
        appendNewLine(sb);
        appendIndent(sb);
        appendSQL(sourceTable, sb);
        
        appendNewLine(sb);
        appendKeyword(sb, ON);
        appendSpace(sb);
        MergeOnCondition onCondition = mergeStmt.getOnCondition();
        appendSQL(onCondition, sb);
        
        List specOperList = mergeStmt.getOperationSpecList();
        Iterator specOperListIter = specOperList.iterator();
        while (specOperListIter.hasNext()) {
            MergeOperationSpecification operSpec = (MergeOperationSpecification) specOperListIter.next();
            if (operSpec instanceof MergeUpdateSpecification) {
                appendNewLine(sb);
                MergeUpdateSpecification updateSpec = (MergeUpdateSpecification) operSpec;
                appendSQL(updateSpec, sb);
            }
            else if (operSpec instanceof MergeInsertSpecification) {
                appendNewLine(sb);
                MergeInsertSpecification insertSpec = (MergeInsertSpecification) operSpec;
                appendSQL(insertSpec, sb);
            }
        }
        
    }

    /**
     * @see com.ibm.db.models.sql.query.QueryNested#getSQL()
     */
    protected void appendSpecificSQL(QueryNested qryNest, StringBuffer sb) {
        sb.append(PAREN_LEFT);
        appendSQL(qryNest.getNestedQuery(),sb);
        sb.append(PAREN_RIGHT);
        
        List sortSpecList = qryNest.getSortSpecList();
        if (StatementHelper.isOrderByClauseContainsValidOrderBySpecification(sortSpecList)) {
            appendNewLine(sb);
            appendSpace(sb, STANDARD_INDENT);
            appendSQLForOrderByClause(sortSpecList, sb);
        }
        
        int rowFetchLimit = qryNest.getRowFetchLimit();
        if (rowFetchLimit > 0) {
            appendSpace(sb);
            appendSQLForFetchFirstClause(rowFetchLimit, sb);
        }
    }

    /**
     * @see org.eclipse.datatools.modelbase.sql.query.QuerySelect#getSQL()
     */
    protected void appendSpecificSQL(QuerySelect select, StringBuffer sb)
    {
        StringBuffer sbSelect = new StringBuffer();

        //if this select is not a subquery, break lines between clauses
        StringBuffer clauseIndent = new StringBuffer();
        int selectStartOffset = getLastLineLength(sb);
        String spacer4 = "    ";

        // if this is a subselect, don't start it after column 30
        if (selectStartOffset > 20) {
            selectStartOffset = getLastLineIndent(sb)+4;
            sbSelect.append(NEW_LINE);
            appendSpace(sbSelect,selectStartOffset);
        }
        //clauseSeparator.append(NEW_LINE);
        appendSpace(clauseIndent,selectStartOffset+STANDARD_INDENT);


        // select-clause begin
        sbSelect.append(SELECT);
        sbSelect.append(SPACE);

        if (select.isDistinct())
        {
            sbSelect.append(DISTINCT);
            sbSelect.append(SPACE);
        }

        if (select.getSelectClause() != null
                        && !select.getSelectClause().isEmpty())
        {
            appendSQLForSQLObjectList(select.getSelectClause(), sbSelect);
        }
        else
        {
            sbSelect.append(ASTERISK);
        }
        // select-clause end

        // from-clause begin
        appendNewLine(sbSelect);
        sbSelect.append(clauseIndent);

        sbSelect.append(FROM);
        sbSelect.append(SPACE);

        if (select.getFromClause() != null && !select.getFromClause().isEmpty())
        {
            int lastTableStartIndex = sbSelect.length();
            for (Iterator fromIt = select.getFromClause().iterator(); fromIt.hasNext();)
            {
                TableReference tableRef = (TableReference) fromIt.next();

                appendSQLForTableExpression(tableRef, sbSelect);

                if (fromIt.hasNext()) {
                    sbSelect.append(COMMA);
                    sbSelect.append(SPACE);
                }
                
                if (getLastLineLength(sbSelect) > displayWidth) {
                    sbSelect.insert(lastTableStartIndex-1,spacer4);
                    sbSelect.insert(lastTableStartIndex-1,clauseIndent.toString());
                    sbSelect.insert(lastTableStartIndex-1,NEW_LINE);
                }

                lastTableStartIndex = sbSelect.length();
            }
        }
        // from-clause end

        // where-clause begin
        if (select.getWhereClause() != null)
        {
            appendNewLine(sbSelect);
            sbSelect.append(clauseIndent);

            sbSelect.append(WHERE);
            sbSelect.append(SPACE);

            appendSQL(select.getWhereClause(), sbSelect);
        }
        // where-clause end

        // group-by-clause begin
        if (select.getGroupByClause() != null && !select.getGroupByClause().isEmpty())
        {
            appendNewLine(sbSelect);
            sbSelect.append(clauseIndent);

            sbSelect.append(GROUP_BY);
            sbSelect.append(SPACE);

/*            for (Iterator groupIt = select.getGroupByClause().iterator(); groupIt.hasNext();)
            {
                GroupingSpecification groupSpec = (GroupingSpecification) groupIt.next();
                appendSQL(groupSpec,sbSelect);
                sbSelect.append(COMMA);
                sbSelect.append(SPACE);
            }
            // delete the last comma and the trailing space
            sbSelect.delete(sbSelect.length() - 2, sbSelect.length());
*/
            appendSQLForSQLObjectList(select.getGroupByClause(),sbSelect);
        }
        // group-by-clause end

        // having-clause begin
        if (select.getHavingClause() != null)
        {
            appendNewLine(sbSelect);
            sbSelect.append(clauseIndent);

            sbSelect.append(HAVING);
            sbSelect.append(SPACE);

            appendSQL(select.getHavingClause(), sbSelect);
        }
        // having-clause end

        // ORDER BY clause
        List sortSpecList = select.getSortSpecList();
        if (StatementHelper.isOrderByClauseContainsValidOrderBySpecification(sortSpecList)) {
            appendNewLine(sbSelect);
            appendStringBuffer(sbSelect, clauseIndent);
            appendSQLForOrderByClause(sortSpecList, sbSelect);
        }
        
        // FETCH FIRST n ROWS ONLY clause
        int rowFetchLimit = select.getRowFetchLimit();
        if (rowFetchLimit > 0)
        {
            appendNewLine(sbSelect);
            appendStringBuffer(sbSelect, clauseIndent);
            appendSQLForFetchFirstClause(rowFetchLimit, sbSelect);
        }
        
        //TODO: complete with into expression variable
        if (select.getIntoClause() != null && !select.getIntoClause().isEmpty())
        {
            String msg = "#appendSQL(QuerySelect) not implemented" +
                    " for IntoClause!";
            throw new UnsupportedOperationException(
                            this.getClass().getName() + msg);

        }


        // if select is nested select (not the top select stmt)
        // and its source is very short we don't break lines
        if (!(select.eContainer() instanceof QueryExpressionRoot && select
                        .eContainer().eContainer() instanceof QuerySelectStatement)
                        && sbSelect.length() < 0)
        {
            trimWhiteSpace(sbSelect);
        }

        sb.append(sbSelect);
    }

    /**
     * @see org.eclipse.datatools.modelbase.sql.query.QuerySelectStatement#getSQL()
     */
    protected void appendSpecificSQL(QuerySelectStatement selectStmt, StringBuffer sb) {
        if (selectStmt.getQueryExpr() != null) {
            appendSQL(selectStmt.getQueryExpr(), sb);
            
            /* Add the ORDER BY clause, if any. */
            List orderByClause = selectStmt.getOrderByClause();
            if (StatementHelper.isOrderByClauseContainsValidOrderBySpecification(orderByClause)) {
                appendNewLine(sb);
                appendSpace(sb, STANDARD_INDENT);
                appendSQLForOrderByClause(orderByClause, sb);
            }
            
            /* Add the "updateability" hint clause, if any */
            UpdatabilityExpression updatabilityExpr = selectStmt.getUpdatabilityExpr();
            if (updatabilityExpr != null) {
                appendNewLine(sb);
                appendSpace(sb, STANDARD_INDENT);
                appendSQL(updatabilityExpr, sb);
            }
        } 
        else {
            sb.append(DEFAULT_STMT_SELECT);
        }
        appendNewLine(sb);
    }

    /**
     * @see org.eclipse.datatools.modelbase.sql.query.QueryUpdateStatement#getSQL()
     */
    protected void appendSpecificSQL(QueryUpdateStatement updateStmt, StringBuffer sb)
    {
        int clauseIndent = STANDARD_INDENT;
        sb.append(UPDATE);
        appendSpace(sb, 1);
        appendSQL(updateStmt.getTargetTable(), sb);
        appendNewLine(sb);
        appendSpace(sb, clauseIndent);

        if (!updateStmt.getAssignmentClause().isEmpty())
        {
            sb.append(SET);
            sb.append(SPACE);
            
            for (Iterator it = updateStmt.getAssignmentClause().iterator(); it
                            .hasNext();)
            {
                UpdateAssignmentExpression assign = (UpdateAssignmentExpression) it
                                .next();
                appendSQL(assign, sb);
                
                if (it.hasNext()) 
                {
                    sb.append(COMMA);
                    
                    if (getLastLineLength(sb) > 60) 
                    {
                        sb.append(NEW_LINE);
                        appendSpace(sb, clauseIndent + 4);
                    }
                    else 
                    {
                        sb.append(SPACE);
                    }
                }
            }
        }

        if (updateStmt.getWhereClause() != null)
        {
            appendNewLine(sb);
            appendSpace(sb, clauseIndent);
            sb.append(WHERE);
            appendSpace(sb, 1);
            appendSQL(updateStmt.getWhereClause(), sb);
        }

        if (updateStmt.getWhereCurrentOfClause() != null)
        {
            sb.append("  CURSOR REFERENCE TO BE getSQL()ed IN "
                            + updateStmt.getClass() + "! ");
        }

    }



    /**
     * @see org.eclipse.datatools.modelbase.sql.query.QueryValues#getSQL()
     */
    protected void appendSpecificSQL(QueryValues queryValues, StringBuffer sb)
    {
        StringBuffer sbValues = new StringBuffer();

        int valuesStartOffset = getLastLineLength(sb);

        // if this is a subselect, don't start it after column 30
        if (valuesStartOffset > 20) {
            valuesStartOffset = getLastLineIndent(sb)+4;
            sbValues.append(NEW_LINE);
            appendSpace(sbValues,valuesStartOffset);
        }

        // Values-clause begin
        sbValues.append(VALUES);
        sbValues.append(SPACE);
        
        List valuesRowList = queryValues.getValuesRowList();
        appendSQLForSQLObjectList(valuesRowList, sbValues);
 
        // Append the ORDER BY clause, if a sort spec list is present.
        List sortSpecList = queryValues.getSortSpecList();
        if (StatementHelper.isOrderByClauseContainsValidOrderBySpecification(sortSpecList)) {
            appendSpace(sbValues);
            appendSQLForOrderByClause(sortSpecList, sbValues);
        }
        
        // append the FETCH FIRST clause, if present.
        int rowFetchLimit = queryValues.getRowFetchLimit();
        if (rowFetchLimit > 0)
        {
//            appendNewLine(sbValues);
//            appendSpace(sbValues, valuesStartOffset);
            appendSpace(sbValues);
            appendSQLForFetchFirstClause(rowFetchLimit, sbValues);
        }
        
        // if select is subselect (sb is empty) and is very short don't break lines
        if (sb.length() > 0 &&  sbValues.length() < displayWidth) 
        {
            trimWhiteSpace(sbValues);
        }

        sb.append(sbValues);
        
    }

    /**
     * @see org.eclipse.datatools.modelbase.sql.query.ResultColumn#getSQL()
     */
    protected void appendSpecificSQL(ResultColumn resultCol, StringBuffer sb) {
        if (resultCol != null) {
            QueryValueExpression resultColValExpr = resultCol.getValueExpr();
            appendSQL(resultColValExpr, sb);
            String resultColName = resultCol.getName();
            if (resultColName != null && resultColName.trim().length() > 0) {
                appendSpace(sb);
                appendKeyword(sb, AS);
                appendSpace(sb);
                String sqlFormatResultColName = convertCatalogIdentifierToSQLFormat(resultColName, getDelimitedIdentifierQuote());
                appendIdentifier(sb, sqlFormatResultColName);
            }
        }
    }

    /**
     * @see org.eclipse.datatools.modelbase.sql.query.ResultTableAllColumns#getSQL()
     */
    protected void appendSpecificSQL(ResultTableAllColumns resultAllCols, StringBuffer sb) {
        if (resultAllCols != null) {
            boolean haveName = false;
            
            /* Get the table expression associated with the "all columns" object.  */
            TableExpression tableExpr = resultAllCols.getTableExpr();
            
            /* If we have a table expression, then get a name for it and write it out, since we want 
             * to put out "<tablename>.*" (ie, MYTABLE.*). */
            if (tableExpr != null) { 
                /* If the table has a correlation ID (alias), then use it. */
                TableCorrelation tableCor = tableExpr.getTableCorrelation();
                if (tableCor != null && tableCor.getName() != null && tableCor.getName().trim().length() > 0) {
                    String tableCorrName = tableCor.getName();
                    tableCorrName = convertCatalogIdentifierToSQLFormat(tableCorrName, getDelimitedIdentifierQuote());
                    appendIdentifier(sb, tableCorrName);
                    haveName = true;
                } 
                /* Else if the table expression is a database table, use the table name. */
                else if (tableExpr instanceof TableInDatabase) {
                    // that will take care of qualified table name
                    TableInDatabase tableInDB = (TableInDatabase) tableExpr;
                    appendSQLForTableExpression(tableInDB, sb);
                    haveName = true;
                }
                /* Otherwise use whatever name has been given to the table expression. */
                else {
                    String tableExprName = tableExpr.getName();
                    if (tableExprName != null && tableExprName.trim().length() > 0) {
                        appendIdentifier(sb, tableExprName);
                        haveName = true;
                    }
                }
            }
            else {
                /* If the "all columns" object doesn't have a table expression,
                 * then get whatever name is attached directly to the all columns object.
                 * (This can happen if the all columns object wasn't properly resolved.)
                 * This could be a qualified name in already in SQL format. */
                String allColsName = resultAllCols.getName();
                if (allColsName != null && allColsName.length() > 0) {
                    appendIdentifier(sb, allColsName);
                    haveName = true;
                }
            }
            
            if (haveName == true) {
                appendSymbol(sb, DOT);
                appendString(sb, STAR);
            }
        }
    }

    /**
     * @see org.eclipse.datatools.modelbase.sql.query.SearchConditionCombined#getSQL()
     */
    protected void appendSpecificSQL(SearchConditionCombined condCombined, StringBuffer sb)
    {
        StringBuffer sbCond = new StringBuffer();
        StringBuffer sbRightCond = new StringBuffer();

        if (condCombined.getLeftCondition() != null)
        {
            appendSQL(condCombined.getLeftCondition(), sbCond);
        }
        if (condCombined.getRightCondition() != null)
        {
            appendSQL(condCombined.getRightCondition(), sbRightCond);
        }
        // break line would be longer than 80 chars and is not still "blank"
        int currentLineLength = sbCond.length() - sbCond.lastIndexOf(NEW_LINE_STRING);
        int prospectiveNewLineLength = currentLineLength + sbRightCond.length();
        if (currentLineLength > displayWidth || prospectiveNewLineLength > displayWidth)
        {
            sbCond.append(NEW_LINE);
            appendSpace(sbCond, 4);
        } else {
            appendSpace(sbCond, 1);
        }
        appendSpecificSQL(condCombined.getCombinedOperator(), sbCond);
        appendSpace(sbCond, 1);

        sbCond.append(sbRightCond);

        wrapSQL((QuerySearchCondition) condCombined, sbCond);
        sb.append(sbCond);
    }

    /**
     * @see org.eclipse.datatools.modelbase.sql.query.SearchConditionCombinedOperator#getSQL()
     */
    protected void appendSpecificSQL(SearchConditionCombinedOperator op, StringBuffer sb) {
        if (op != null) {
            int opVal = op.getValue();

            switch (opVal) {
                case SearchConditionCombinedOperator.AND:
                    appendOperator(sb, AND);
                    break;
                case SearchConditionCombinedOperator.OR:
                    appendOperator(sb, OR);
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * @see org.eclipse.datatools.modelbase.sql.query.SearchConditionNested#getSQL()
     */
    protected void appendSpecificSQL(SearchConditionNested condNest, StringBuffer sb) {
        if (condNest != null) {
            if (condNest.isNegatedCondition()) {
                appendOperator(sb, NOT);
                appendSpace(sb);
            }

            appendSymbol(sb, PAREN_LEFT);
            if (condNest.getNestedCondition() != null) {
                appendSQL(condNest.getNestedCondition(), sb);
            }

            appendSymbol(sb, PAREN_RIGHT);
        }
    }

    /**
     * @see SQLQueryArrayDataType
     */
    protected void appendSpecificSQL(SQLQueryArrayDataType type, StringBuffer sb) {
        appendSpecificSQL((ArrayDataType) type, sb);
    }
    
    /**
     * @see SQLQueryMultisetDataType
     */
    protected void appendSpecificSQL(SQLQueryMultisetDataType type, StringBuffer sb) {
        appendSpecificSQL((MultisetDataType) type, sb);
    }
   
    /**
     * @see StructuredUserDefinedType#getSQL()
     */
    protected void appendSpecificSQL(StructuredUserDefinedType type, StringBuffer sb) {
        if (type != null) {
            String typeName = type.getName();
            typeName = convertCatalogIdentifierToSQLFormat(typeName, getDelimitedIdentifierQuote());
            appendDataTypeName(sb, typeName);
        }
    }


    /**
     * @see org.eclipse.datatools.modelbase.sql.query.SuperGroup#getSQL()
     */
    protected void appendSpecificSQL(SuperGroup superGroup, StringBuffer sb)
    {
        appendSpecificSQL(superGroup.getSuperGroupType(),sb);

        sb.append(PAREN_LEFT);

        List superGroupElementList = superGroup.getSuperGroupElementList();

/*      for (Iterator it = superGroupElementList.iterator(); it.hasNext();)
        {
            SuperGroupElement superGroupElement = (SuperGroupElement) it.next();
            appendSQL(superGroupElement,sb);

            if (it.hasNext()) {
                sb.append(COMMA);
                sb.append(SPACE);
            }

        }
*/
        appendSQLForSQLObjectList(superGroupElementList, sb);
        sb.append(PAREN_RIGHT);
    }

    /**
     * @see org.eclipse.datatools.modelbase.sql.query.SuperGroupElementExpression#getSQL()
     */
    protected void appendSpecificSQL(SuperGroupElementExpression superGroupElementExpr, StringBuffer sb)
    {
        appendSQL(superGroupElementExpr.getGroupingExpr(),sb);

    }

    /**
     * @see org.eclipse.datatools.modelbase.sql.query.SuperGroupElementSublist#getSQL()
     */
    protected void appendSpecificSQL(SuperGroupElementSublist superGroupSublist, StringBuffer sb)
    {
        sb.append(PAREN_LEFT);

        List superGroupElementExprList = superGroupSublist.getSuperGroupElementExprList();

/*      for (Iterator it = superGroupElementExprList.iterator(); it.hasNext();)
        {
            SuperGroupElementExpression superGroupElementExpr =
                (SuperGroupElementExpression) it.next();
            appendSQL(superGroupElementExpr,sb);

            if (it.hasNext()) {
                sb.append(COMMA);
                sb.append(SPACE);
            }

        }
*/
        appendSQLForSQLObjectList(superGroupElementExprList, sb);
        sb.append(PAREN_RIGHT);
    }

    /**
     * @see org.eclipse.datatools.modelbase.sql.query.SuperGroupType#getSQL()
     */
    protected void appendSpecificSQL(SuperGroupType type, StringBuffer sb)
    {

        int grouping = type.getValue();

        switch (grouping)
        {
            case SuperGroupType.CUBE:
                sb.append(CUBE);
                break;

            case SuperGroupType.ROLLUP:
                sb.append(ROLLUP);
                break;

            case SuperGroupType.GRANDTOTAL:
                break;

            default:
                break;
        }

    }

    /**
     * @see org.eclipse.datatools.modelbase.sql.query.TableCorrelation#getSQL()
     */
    protected void appendSpecificSQL(TableCorrelation tableCorrelation, StringBuffer sb) {
        if (tableCorrelation != null) {
            String tableCorrName = tableCorrelation.getName();
            String sqlFormatTableCorrName = convertCatalogIdentifierToSQLFormat(tableCorrName, getDelimitedIdentifierQuote());
            appendIdentifier(sb, sqlFormatTableCorrName);

            /* Add the column alias list, if any. */
            List colNameList = tableCorrelation.getColumnNameList();
            if (colNameList.size() > 0) {
                appendSpace(sb);
                appendSymbol(sb, PAREN_LEFT);
                appendSQLForSQLObjectList(colNameList, sb);
                appendSymbol(sb, PAREN_RIGHT);
            }
        }
    }

    /**
     * @see org.eclipse.datatools.modelbase.sql.query.TableFunction#getSQL()
     */
    protected void appendSpecificSQL(TableFunction tableFunc, StringBuffer sb) {
        if (tableFunc != null) {
            appendKeyword(sb, TABLE);
            appendSpace(sb);
            appendSymbol(sb, PAREN_LEFT);

            if (tableFunc.getFunction() != null && tableFunc.getFunction().getSchema() != null) {
                String schemaName = tableFunc.getFunction().getSchema().getName();
                if (schemaName != null) {
                    String sqlFormatSchemaName = convertCatalogIdentifierToSQLFormat(schemaName, getDelimitedIdentifierQuote());
                    appendIdentifier(sb, sqlFormatSchemaName);
                    appendSymbol(sb, DOT);
                }
            }

            String tableFuncName = tableFunc.getName();
            String sqlFormatTableFuncName = convertCatalogIdentifierToSQLFormat(tableFuncName, getDelimitedIdentifierQuote());
            appendIdentifier(sb, sqlFormatTableFuncName);
            appendSymbol(sb, PAREN_LEFT);

            List paramList = tableFunc.getParameterList();
            if (paramList != null) {
                appendSQLForSQLObjectList(paramList, sb);
            }

            appendSymbol(sb, PAREN_RIGHT);
            appendSymbol(sb, PAREN_RIGHT);

            TableCorrelation tableCorr = tableFunc.getTableCorrelation();
            if (tableCorr != null) {
                SQLQuerySourceInfo sourceInfo = tableFunc.getSourceInfo();
                SQLQuerySourceFormat sourceFormat = sourceInfo.getSqlFormat();
                if (sourceFormat.getGenerateAsKeywordForTableCorrID() == true) {
                    sb.append(SPACE);
                    sb.append(AS);
                }
                appendSpace(sb);
                appendSQL(tableCorr, sb);
            }
        }
    }

    /**
     * @see org.eclipse.datatools.modelbase.sql.query.TableInDatabase#getSQL()
     */
    protected void appendSpecificSQL(TableInDatabase tableInDB, StringBuffer sb) {
        appendSQLForTableInDatabase(tableInDB, sb);

        TableCorrelation tableCorr = tableInDB.getTableCorrelation();
        if (tableCorr != null ) {
            SQLQuerySourceInfo sourceInfo = tableInDB.getSourceInfo();
            SQLQuerySourceFormat sourceFormat = sourceInfo.getSqlFormat();
            if (sourceFormat.getGenerateAsKeywordForTableCorrID() == true) {
                appendSpace(sb);
                appendKeyword(sb, AS);
            }
            appendSpace(sb);
            appendSQL(tableCorr, sb);
        }
    }

    /**
     * @see org.eclipse.datatools.modelbase.sql.query.TableJoined#getSQL()
     */
    protected void appendSpecificSQL(TableJoined tableJoined, StringBuffer sb)
    {
        // <table_ref> <opt_join_type> JOIN <table_ref> ON <condition>


        appendSQLForTableExpression(tableJoined.getTableRefLeft(), sb);

        if (tableJoined.getJoinOperator() != TableJoinedOperator.DEFAULT_INNER_LITERAL) {
            sb.append(SPACE);
            appendSpecificSQL(tableJoined.getJoinOperator(),sb);
        }

        sb.append(SPACE);
        sb.append(JOIN);
        sb.append(SPACE);

        appendSQLForTableExpression(tableJoined.getTableRefRight(), sb);

        sb.append(SPACE);
        sb.append(ON);
        sb.append(SPACE);

        appendSQL(tableJoined.getJoinCondition(),sb);

    }

    /**
     * @see org.eclipse.datatools.modelbase.sql.query.TableJoinedOperator#getSQL()
     */
    protected void appendSpecificSQL(TableJoinedOperator op, StringBuffer sb)
    {

        int operator = op.getValue();

        switch (operator)
        {
            case TableJoinedOperator.DEFAULT_INNER:
                break;

            case TableJoinedOperator.EXPLICIT_INNER:
                sb.append(INNER);
                break;

            case TableJoinedOperator.FULL_OUTER:
                sb.append(FULL);
                sb.append(SPACE);
                sb.append(OUTER);
                break;

            case TableJoinedOperator.LEFT_OUTER:
                sb.append(LEFT);
                sb.append(SPACE);
                sb.append(OUTER);
                break;

            case TableJoinedOperator.RIGHT_OUTER:
                sb.append(RIGHT);
                sb.append(SPACE);
                sb.append(OUTER);
                break;

            default:
                break;
        }

    }

    /**
     * @see org.eclipse.datatools.modelbase.sql.query.TableNested#getSQL()
     */
    protected void appendSpecificSQL(TableNested tableNested, StringBuffer sb)
    {
        TableReference tableRef = null;

        tableRef = tableNested.getNestedTableRef();

        sb.append(PAREN_LEFT);

        appendSQLForTableExpression(tableRef, sb);

        sb.append(PAREN_RIGHT);
    }

    /**
     * @see org.eclipse.datatools.modelbase.sql.datatypes.TimeDataType#getSQL()
     */
    protected void appendSpecificSQL(TimeDataType dataType, StringBuffer sb)
    {
        String typeName = dataType.getName();
        if (typeName != null && typeName.length() > 0) {
            appendKeyword(sb, typeName);
        }
        else {
            PrimitiveType primitiveType = dataType.getPrimitiveType();
            appendSpecificSQL(primitiveType, sb);
        }
        // TODO: TimeDataType what to do with the timezone and fractional precision?
    }

    protected void appendSpecificSQL(UpdatabilityExpression updatabilityExpr, StringBuffer sb) {
        UpdatabilityType updateType = updatabilityExpr.getUpdatabilityType();
        appendSpecificSQL(updateType, sb);
        List updateOfColList = updatabilityExpr.getUpdateOfColumnList();
        if (updateOfColList != null && updateOfColList.size() > 0) {
            appendSpace(sb);
            appendKeyword(sb, OF);
            appendSpace(sb);
            appendSQLForSQLObjectList(updateOfColList, sb);
        }
        
    }
    
    protected void appendSpecificSQL(UpdatabilityType updatabilityType, StringBuffer sb) {
        int updateType = updatabilityType.getValue();
        
        switch (updateType) {
            case UpdatabilityType.READ_ONLY:
                appendKeyword(sb, FOR);
                appendSpace(sb);
                appendKeyword(sb, READ);
                appendSpace(sb);
                appendKeyword(sb, ONLY);
                break;
            case UpdatabilityType.UPDATE:
                appendKeyword(sb, FOR);
                appendSpace(sb);
                appendKeyword(sb, UPDATE);
                break;
            default:
                break;
        }
    }
    
    /**
     * @see org.eclipse.datatools.modelbase.sql.query.UpdateAssignmentExpression#getSQL()
     */
    protected void appendSpecificSQL(UpdateAssignmentExpression impl, StringBuffer sb) {
        if (impl != null) {
            List targetColList = impl.getTargetColumnList();
            if (targetColList != null && targetColList.size() > 0) {
                if (targetColList.size() == 1) {
                    Object listObj = targetColList.get(0);
                    if (listObj instanceof SQLQueryObject) { 
                        appendSQL((SQLQueryObject) listObj, sb);
                    }
                }
                else {
                    appendSymbol(sb, PAREN_LEFT);
                    appendSQLForSQLObjectList(targetColList, sb);
                    appendSymbol(sb, PAREN_RIGHT);
                }
            }

            appendSpace(sb);
            appendOperator(sb, EQUAL);
            appendSpace(sb);

            UpdateSource updateSrc = impl.getUpdateSource();
            appendSQL(updateSrc, sb);
        }
    }

    /**
     * @see org.eclipse.datatools.modelbase.sql.query.UpdateOfColumn#getSQL()
     */
    protected void appendSpecificSQL(UpdateOfColumn updateOfCol, StringBuffer sb) {
        if (updateOfCol != null) {
            String colName = updateOfCol.getName();
            String sqlFormatColName = convertCatalogIdentifierToSQLFormat(colName, getDelimitedIdentifierQuote());
            appendIdentifier(sb, sqlFormatColName);
        }
    }
    
    /**
     * @see org.eclipse.datatools.modelbase.sql.query.UpdateSourceExprList#getSQL()
     */
    protected void appendSpecificSQL(UpdateSourceExprList sourceExprList, StringBuffer sb) {
        EList values = sourceExprList.getValueExprList();

        if (values != null)
        {
            if (values.size() == 1)
            {
                QueryValueExpression expr = (QueryValueExpression) values
                                .get(0);
                appendSQL(expr, sb);
            }
            else
            {
                sb.append(PAREN_LEFT);
/*              for (Iterator it = values.iterator(); it.hasNext();)
                {
                    QueryValueExpression expr = (QueryValueExpression) it
                                    .next();
                    appendSQL(expr, sb);
                    sb.append(COMMA);
                    sb.append(SPACE);

                }
                //sb.setCharAt(sb.length()-1,')'); //delete the last comma
                sb.replace(sb.length() - 2, sb.length(), ")"); //delete the
                // last comma
*/
                appendSQLForSQLObjectList(values, sb);
                sb.append(PAREN_RIGHT);
            }
        }
    }
    
    /**
     * @see org.eclipse.datatools.modelbase.sql.query.UpdateSourceQuery#getSQL()
     */
    protected void appendSpecificSQL(UpdateSourceQuery sourceQuery, StringBuffer sb) {

        sb.append(PAREN_LEFT);
        appendSQL(sourceQuery.getQueryExpr(), sb);
        sb.append(PAREN_RIGHT);
    }

    /**
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionAtomic#getSQL()
     */
    protected void appendSpecificSQL(ValueExpressionAtomic atomicExpr, StringBuffer sb)
    {
        // a ValueExpressionAtomic is actually not expected
        // what would its SQL be??? so we append nothing
    }

    /**
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseElse#getSQL()
     */
    protected void appendSpecificSQL(ValueExpressionCaseElse caseElse, StringBuffer sbCase)
    {
        sbCase.append(ELSE);
        sbCase.append(SPACE);

        appendSQL(caseElse.getValueExpr(),sbCase);

    }

    /**
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSearch#getSQL()
     */
    protected void appendSpecificSQL(ValueExpressionCaseSearch caseExpr, StringBuffer sb)
    {
        StringBuffer sbCase = new StringBuffer();
        boolean multipleCases = (caseExpr.getSearchContentList() != null) && (caseExpr.getSearchContentList().size() > 1);

        int startOffset = getLastLineLength(sb);
        if (startOffset > 30) {
            startOffset = getLastLineIndent(sb)+4;
            if (multipleCases) {
                sbCase.append(NEW_LINE);
                appendSpace(sbCase, startOffset);
            }
        }

        sbCase.append(CASE);

        if (caseExpr.getSearchContentList() != null) {
            for (Iterator caseIt = caseExpr.getSearchContentList().iterator(); caseIt.hasNext();)
            {
                ValueExpressionCaseSearchContent oneCase = (ValueExpressionCaseSearchContent) caseIt.next();

                if (multipleCases) {
                    sbCase.append(NEW_LINE);
                    appendSpace(sbCase, startOffset+STANDARD_INDENT);
                } else {
                    sbCase.append(SPACE);
                }

                appendSQL(oneCase,sbCase);
            }
        }

        if (caseExpr.getCaseElse() != null) {

            if (multipleCases) {
                sbCase.append(NEW_LINE);
                appendSpace(sbCase, startOffset+STANDARD_INDENT);
            } else {
                sbCase.append(SPACE);
            }

            appendSQL(caseExpr.getCaseElse(),sbCase);

        }

        if (multipleCases) {
            sbCase.append(NEW_LINE);
            appendSpace(sbCase, startOffset);
        } else {
            sbCase.append(SPACE);
        }

        sbCase.append(END);

        wrapSQL(caseExpr, sbCase);
        sb.append(sbCase);

    }

    /**
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSearchContent#getSQL()
     */
    protected void appendSpecificSQL(ValueExpressionCaseSearchContent caseSearchContent, StringBuffer sbCase)
    {
        sbCase.append(WHEN);
        sbCase.append(SPACE);

        appendSQL(caseSearchContent.getSearchCondition(),sbCase);

        sbCase.append(SPACE);
        sbCase.append(THEN);
        sbCase.append(SPACE);

        appendSQL(caseSearchContent.getValueExpr(),sbCase);
    }

    /**
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSimple#getSQL()
     */
    protected void appendSpecificSQL(ValueExpressionCaseSimple caseExpr, StringBuffer sb)
    {
        StringBuffer sbCase = new StringBuffer();
        boolean multipleCases = (caseExpr.getContentList() != null) && (caseExpr.getContentList().size() > 1);

        int startOffset = getLastLineLength(sb);
        if (startOffset > 30) {
            startOffset = getLastLineIndent(sb)+4;
            if (multipleCases) {
                sbCase.append(NEW_LINE);
                appendSpace(sbCase, startOffset);
            }
        }

        sbCase.append(CASE);

        sbCase.append(SPACE);
        appendSQL(caseExpr.getValueExpr(),sbCase);

        if (caseExpr.getContentList() != null) {
            for (Iterator caseIt = caseExpr.getContentList().iterator(); caseIt.hasNext();)
            {
                ValueExpressionCaseSimpleContent oneCase = (ValueExpressionCaseSimpleContent) caseIt.next();

                if (multipleCases) {
                    sbCase.append(NEW_LINE);
                    appendSpace(sbCase, startOffset+STANDARD_INDENT);
                } else {
                    sbCase.append(SPACE);
                }

                appendSQL(oneCase,sbCase);

            }
        }

        if (caseExpr.getCaseElse() != null) {

            if (multipleCases) {
                sbCase.append(NEW_LINE);
                appendSpace(sbCase, startOffset+STANDARD_INDENT);
            } else {
                sbCase.append(SPACE);
            }

            appendSQL(caseExpr.getCaseElse(),sbCase);

        }

        if (multipleCases) {
            sbCase.append(NEW_LINE);
            appendSpace(sbCase, startOffset);
        } else {
            sbCase.append(SPACE);
        }

        sbCase.append(END);

        wrapSQL(caseExpr, sbCase);
        sb.append(sbCase);

    }

    /**
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSimpleContent#getSQL()
     */
    protected void appendSpecificSQL(ValueExpressionCaseSimpleContent caseSimpleContent, StringBuffer sbCase)
    {
        sbCase.append(WHEN);
        sbCase.append(SPACE);

        appendSQL(caseSimpleContent.getWhenValueExpr(),sbCase);

        sbCase.append(SPACE);
        sbCase.append(THEN);
        sbCase.append(SPACE);

        appendSQL(caseSimpleContent.getResultValueExpr(),sbCase);
    }

    /**
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionCast#getSQL()
     */
    protected void appendSpecificSQL(ValueExpressionCast castExpr, StringBuffer sb)
    {
        StringBuffer sbCast = new StringBuffer();

        sbCast.append(CAST);
        sbCast.append(SPACE);
        sbCast.append(PAREN_LEFT);
        appendSQL(castExpr.getValueExpr(),sbCast);
        sbCast.append(SPACE);
        sbCast.append(AS);
        sbCast.append(SPACE);
        appendSQL(castExpr.getDataType(),sbCast);

        sbCast.append(PAREN_RIGHT);

        wrapSQL(castExpr, sbCast);
        sb.append(sbCast);

    }

    /**
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionColumn#getSQL()
     */
    protected void appendSpecificSQL(ValueExpressionColumn valExprCol, StringBuffer sb) {
        if (valExprCol != null) {
            StringBuffer sbExpr = new StringBuffer();

            TableExpression tableExpr = valExprCol.getTableExpr();
            if (tableExpr != null) {
                /* We need to determine whether or not to qualify the column expression with a table name 
                 * or table correlation ID.  We get the qualify setting from the source format object.
                 * In the case of "qualify in context", we need to check further to see if the same column
                 * name is used in more than one table. */
                SQLQuerySourceInfo sourceInfo = valExprCol.getSourceInfo();
                SQLQuerySourceFormat sourceFormat = sourceInfo.getSqlFormat();
                int qualifySpec = sourceFormat.getQualifyIdentifiers();

                // boolean qualifyNever =      (qualifySpec == SQLQuerySourceFormat.QUALIFY_IDENTIFIERS_NEVER);
                boolean qualifyWithSchema = (qualifySpec == SQLQuerySourceFormat.QUALIFY_IDENTIFIERS_WITH_SCHEMA_NAMES);
                boolean qualifyWithTable =  (qualifySpec == SQLQuerySourceFormat.QUALIFY_IDENTIFIERS_WITH_TABLE_NAMES);
                boolean qualifyInContext =  (qualifySpec == SQLQuerySourceFormat.QUALIFY_IDENTIFIERS_IN_CONTEXT);

                boolean qualify = 
                    qualifyWithSchema || qualifyWithTable || (qualifyInContext && isQualifiedColumnNameRequired(valExprCol));

                if (qualify) {
                    /* If we have a correlation ID (table alias), write that out. */
                    if (tableExpr.getTableCorrelation() != null && tableExpr.getTableCorrelation().getName() != null) {
                        String tableCorrName = tableExpr.getTableCorrelation().getName();
                        String sqlFormatTableCorrName = convertCatalogIdentifierToSQLFormat(tableCorrName, getDelimitedIdentifierQuote());
                        appendIdentifier(sbExpr, sqlFormatTableCorrName);
                    }
                    /* Otherwise put out the name of table expression itself. */
                    else { 
                        /* If the table expression is a database table, write out the table name.  The table might need
                         * to be qualified by the schema name. */
                        if (tableExpr instanceof TableInDatabase) {
                            TableInDatabase tableInDB = (TableInDatabase) tableExpr;
                            appendSQLForTableInDatabase(tableInDB, sbExpr);
                        }
                        /* Otherwise use the name attached to the table expression itself. */
                        else {
                            String tableExprName = tableExpr.getName();
                            String sqlFormatTableExprName = convertCatalogIdentifierToSQLFormat(tableExprName, getDelimitedIdentifierQuote());
                            appendIdentifier(sbExpr, sqlFormatTableExprName);
                        }

                    }
                    appendSymbol(sbExpr, DOT);
                }
            }

            /* Write out the column name. */
            String valExprColName = valExprCol.getName();
            String sqlFormatValExprColName = convertCatalogIdentifierToSQLFormat(valExprColName, getDelimitedIdentifierQuote());
            appendIdentifier(sbExpr, sqlFormatValExprColName);

            wrapSQL(valExprCol, sbExpr);
            appendStringBuffer(sb, sbExpr);
        }
    }

    /**
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionCombined#getSQL()
     */
    protected void appendSpecificSQL(ValueExpressionCombined exprCombined, StringBuffer sb)
    {

        if (exprCombined.getLeftValueExpr() != null) {
            appendSQL(exprCombined.getLeftValueExpr(), sb);
        }
        sb.append(SPACE);
        appendSpecificSQL(exprCombined.getCombinedOperator(), sb);
        sb.append(SPACE);
        if (exprCombined.getRightValueExpr() != null) { 
            appendSQL(exprCombined.getRightValueExpr(), sb);
        }

    }


    /**
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionCombinedOperator#getSQL()
     */
    protected void appendSpecificSQL(ValueExpressionCombinedOperator op, StringBuffer sb)
    {

        int operator = op.getValue();

        switch (operator)
        {
            case ValueExpressionCombinedOperator.ADD:
                sb.append(ADD);
                break;

            case ValueExpressionCombinedOperator.SUBTRACT:
                sb.append(SUBTRACT);
                break;

            case ValueExpressionCombinedOperator.MULTIPLY:
                sb.append(MULTIPLY);
                break;

            case ValueExpressionCombinedOperator.DIVIDE:
                sb.append(DIVIDE);
                break;

            case ValueExpressionCombinedOperator.CONCATENATE:
                sb.append(CONCATENATE);
                break;

            default:
                break;
        }

    }

    /**
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionDefaultValue#getSQL()
     */
    protected void appendSpecificSQL(ValueExpressionDefaultValue exprDefault,
                           StringBuffer sb)
    {

        sb.append(DEFAULT);

    }

    /**
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionFunction#getSQL()
     */
    protected void appendSpecificSQL(ValueExpressionFunction valExprFunc, StringBuffer sb) {
        if (valExprFunc != null) {
            StringBuffer sbExpr = new StringBuffer();

            /* If we have a schema associated with the function, write out the schema name. */
            if (valExprFunc.getFunction() != null && valExprFunc.getFunction().getSchema() != null) {
                String schemaName = valExprFunc.getFunction().getSchema().getName();
                if (schemaName != null) {
                    String sqlFormatSchemaName = convertCatalogIdentifierToSQLFormat(schemaName, getDelimitedIdentifierQuote());
                    appendIdentifier(sbExpr, sqlFormatSchemaName);
                    appendSymbol(sbExpr, DOT);
                }
            }

            /* The "special registers" (that is, pre-defined variables such as CURRENT_SCHEMA) need
             * special handling.  They act like functions but are not followed by parens. */
            if (valExprFunc.isSpecialRegister()) {
                String regName = valExprFunc.getName();
                appendSpecialRegisterName(sbExpr, regName);
                
                /* Some special registers are followed by an argument. The argument is stored 
                 * in the function parameter list. */
                List paramList = valExprFunc.getParameterList();
                if (paramList != null && paramList.size() > 0) {
                    /* The param list should consist of a single simple value expression. */
                    Object listObj = paramList.get(0);
                    if (listObj instanceof ValueExpressionSimple) {
                        ValueExpressionSimple valExprSimple = (ValueExpressionSimple) listObj;
                        String value = valExprSimple.getValue();
                        if (value != null) {
                            appendSpace(sbExpr);
                            appendString(sbExpr, value);
                        }
                    }
                }
                /* Handle a special case for one of the special registers. */
                if (valExprFunc.getName().equalsIgnoreCase("CURRENT_TRANSFORM_GROUP_FOR_TYPE")) { //$NON-NLS-1$
                    DataType datatype = valExprFunc.getDataType();
                    appendSpace(sbExpr);
                    appendSQL(datatype, sbExpr);
                }
            }
            else {
                String funcName = valExprFunc.getName();
                String sqlFormatFuncName = convertCatalogIdentifierToSQLFormat(funcName, getDelimitedIdentifierQuote());
                appendFunctionName(sbExpr, sqlFormatFuncName);

                appendSymbol(sbExpr, PAREN_LEFT);

                /* Aggregate functions have a DISTINCT option. */
                if (valExprFunc.isDistinct()) {
                    appendKeyword(sbExpr, DISTINCT);
                    appendSpace(sbExpr);
                }

                /* Process the function argument list. */
                List paramList = valExprFunc.getParameterList();

                /* Handle special case for COUNT. */
                boolean isCountAll =
                    valExprFunc.getName().equalsIgnoreCase(FUNCTION_COUNT)
                    && (paramList == null || paramList.isEmpty());

                if (isCountAll) {
                    appendString(sbExpr, STAR);
                }
                else if (paramList != null) {
                    appendSQLForSQLObjectList(paramList, sbExpr);
                }

                appendSymbol(sbExpr, PAREN_RIGHT);
            }

            wrapSQL(valExprFunc, sbExpr);
            appendStringBuffer(sb, sbExpr);
        }
    }

    /**
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionLabeledDuration#getSQL()
     */
    protected void appendSpecificSQL(ValueExpressionLabeledDuration duration, StringBuffer sb)
    {
        StringBuffer sbDuration = new StringBuffer();
        
        appendSQL(duration.getValueExpr(), sbDuration);
        sbDuration.append(SPACE);
        appendSpecificSQL(duration.getLabeledDurationType(), sbDuration);
        
        wrapSQL(duration, sbDuration);
        sb.append(sbDuration);
    }

    /**
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionLabeledDurationType#getSQL()
     */
    protected void appendSpecificSQL(ValueExpressionLabeledDurationType duration, StringBuffer sb)
    {
        int type = duration.getValue();

        switch (type)
        {
            case ValueExpressionLabeledDurationType.YEARS:
                sb.append(YEARS);
                break;

            case ValueExpressionLabeledDurationType.MONTHS:
                sb.append(MONTHS);
                break;

            case ValueExpressionLabeledDurationType.DAYS:
                sb.append(DAYS);
                break;

            case ValueExpressionLabeledDurationType.HOURS:
                sb.append(HOURS);
                break;

            case ValueExpressionLabeledDurationType.MINUTES:
                sb.append(MINUTES);
                break;

            case ValueExpressionLabeledDurationType.SECONDS:
                sb.append(SECONDS);
                break;

            case ValueExpressionLabeledDurationType.MICROSECONDS:
                sb.append(MICROSECONDS);
                break;

            default:
                break;
        }
    }

    /**
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionNested#getSQL()
     */
    protected void appendSpecificSQL(ValueExpressionNested exprNest, StringBuffer sb)
    {
        StringBuffer sbExprNest = new StringBuffer();
        sbExprNest.append(PAREN_LEFT);
        appendSQL(exprNest.getNestedValueExpr(), sbExprNest);
        sbExprNest.append(PAREN_RIGHT);

        wrapSQL(exprNest, sbExprNest);
        sb.append(sbExprNest);

    }

    /**
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionNullValue#getSQL()
     */
    protected void appendSpecificSQL(ValueExpressionNullValue exprNull, StringBuffer sb)
    {

        sb.append(NULL);

    }

    /**
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionRow#getSQL()
     */
    protected void appendSpecificSQL(ValueExpressionRow valExprRow, StringBuffer sb) 
    {
        sb.append(PAREN_LEFT);
        appendSQLForSQLObjectList(valExprRow.getValueExprList(), sb);  
        sb.append(PAREN_RIGHT);
    }

    /**
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionSimple#getSQL()
     */
    protected void appendSpecificSQL(ValueExpressionScalarSelect exprSelect, StringBuffer sb)
    {
        StringBuffer sbExpr = new StringBuffer();
        sbExpr.append(PAREN_LEFT);
        appendSQL(exprSelect.getQueryExpr(),sbExpr);
        sbExpr.append(PAREN_RIGHT);

        wrapSQL(exprSelect, sbExpr);
        indentSQLToLastLineLengthOfContainer(sbExpr, sb);
        sb.append(sbExpr);

    }

    /**
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionSimple#getSQL()
     */
    protected void appendSpecificSQL(ValueExpressionSimple exprSimple, StringBuffer sb)
    {
        StringBuffer sbExpr = new StringBuffer();
        sbExpr.append(exprSimple.getValue());

        wrapSQL(exprSimple, sbExpr);
        sb.append(sbExpr);

    }

    /**
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionUnaryOperator#getSQL()
     */
    protected void appendSpecificSQL(ValueExpressionUnaryOperator op, StringBuffer sb) {
        if (op != null) {
            int operator = op.getValue();

            switch (operator) {            
                case ValueExpressionUnaryOperator.NONE:
                    break;
                case ValueExpressionUnaryOperator.PLUS:
                    appendOperator(sb, PLUS);
                    break;
                case ValueExpressionUnaryOperator.MINUS:
                    appendOperator(sb, MINUS);
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionVariable#getSQL()
     */
    protected void appendSpecificSQL(ValueExpressionVariable variable, StringBuffer sb)
    {
        StringBuffer sbExpr = new StringBuffer();

        String hostVarPrefix = COLON;
        String paramMarker = QUESTIONMARK;

        if (variable.getSourceInfo() != null && variable.getSourceInfo().getSqlFormat() != null) 
        {
            SQLQuerySourceFormat sf = variable.getSourceInfo().getSqlFormat();
            hostVarPrefix = String.valueOf(sf.getHostVariablePrefix());
            paramMarker = String.valueOf(sf.getParameterMarker());
        }

        
        // we use variable as hostVar (if it has a name) or as parameter marker (if it has no name) 
        if (variable.getName() != null) 
        {
            sbExpr.append(hostVarPrefix);
            sbExpr.append(variable.getName()); // [bug 221028]
        }
        else
        {
            sbExpr.append(paramMarker);
        }

        wrapSQL(variable, sbExpr);
        sb.append(sbExpr);

    }

    /**
     * @see org.eclipse.datatools.modelbase.sql.query.ValuesRow#getSQL()
     */
    protected void appendSpecificSQL(ValuesRow valuesRow, StringBuffer sb)
    {
        if (valuesRow.getExprList().size() == 1)
        {
            appendSQLForSQLObjectList(valuesRow.getExprList(), sb);  
        }
        else if (valuesRow.getExprList().size() > 1)
        {
            sb.append(PAREN_LEFT);
            appendSQLForSQLObjectList(valuesRow.getExprList(), sb);  
            sb.append(PAREN_RIGHT);
        }
    }

    /**
     * @see org.eclipse.datatools.modelbase.sql.query.WithTableReference#getSQL()
     */
    protected void appendSpecificSQL(WithTableReference withTableRef, StringBuffer sb) {
        if (withTableRef != null) {
            String withTableRefName = withTableRef.getName();
            String sqlFormatWithTableRefName = convertCatalogIdentifierToSQLFormat(withTableRefName, getDelimitedIdentifierQuote());
            appendIdentifier(sb, sqlFormatWithTableRefName);

            TableCorrelation tableCorr = withTableRef.getTableCorrelation();
            if (tableCorr != null) {
                SQLQuerySourceInfo sourceInfo = withTableRef.getSourceInfo();
                SQLQuerySourceFormat sourceFormat = sourceInfo.getSqlFormat();
                if (sourceFormat.getGenerateAsKeywordForTableCorrID() == true) {
                    appendSpace(sb);
                    appendKeyword(sb, AS);
                }
                appendSpace(sb);
                appendSQL(tableCorr, sb);
            }
        }
    }

    /**
     * @see org.eclipse.datatools.modelbase.sql.query.TableWithSpecification#getSQL()
     */
    protected void appendSpecificSQL(WithTableSpecification withTable, StringBuffer sb) {
        if (withTable != null) {
            int tableWithIndent = getLastLineIndent(sb);
            int tableWithQueryIndent = tableWithIndent + 3;

            String withTableName = withTable.getName();
            String sqlFormatWithTableName = convertCatalogIdentifierToSQLFormat(withTableName, getDelimitedIdentifierQuote());
            appendIdentifier(sb, sqlFormatWithTableName);
            if (!withTable.getColumnNameList().isEmpty()) {
                List columnNameList = withTable.getColumnNameList();
                appendSpace(sb);
                appendSymbol(sb, PAREN_LEFT);
                appendSQLForSQLObjectList(columnNameList, sb);
                appendSymbol(sb, PAREN_RIGHT);
            }

            appendSpace(sb);
            appendKeyword(sb, AS);
            appendNewLine(sb);

            appendSpace(sb, tableWithQueryIndent);
            appendSymbol(sb, PAREN_LEFT);
            appendSQL(withTable.getWithTableQueryExpr(), sb);
            appendSymbol(sb, PAREN_RIGHT);
        }
    }

    /**
     * @see org.eclipse.datatools.modelbase.sql.datatypes.XMLDataType#getSQL()
     */
    protected void appendSpecificSQL(XMLDataType dataType, StringBuffer sb) {
        String typeName = dataType.getName();
        if (typeName != null && typeName.length() > 0) {
            appendKeyword(sb, typeName);
        }
        else {
            PrimitiveType primitiveType = dataType.getPrimitiveType();
            appendSpecificSQL(primitiveType, sb);
        }
    }
    
}
