/*******************************************************************************
 * Copyright (c) 2002, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.parsers.sql.query;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.eclipse.datatools.modelbase.sql.datatypes.ApproximateNumericDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.ArrayDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.BinaryStringDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.BooleanDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.CharacterStringDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.DataLinkDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.DataType;
import org.eclipse.datatools.modelbase.sql.datatypes.DateDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.DistinctUserDefinedType;
import org.eclipse.datatools.modelbase.sql.datatypes.ElementType;
import org.eclipse.datatools.modelbase.sql.datatypes.FixedPrecisionDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.IntegerDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.IntervalDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.IntervalQualifierType;
import org.eclipse.datatools.modelbase.sql.datatypes.MultisetDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.PredefinedDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.PrimitiveType;
import org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesFactory;
import org.eclipse.datatools.modelbase.sql.datatypes.TimeDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedType;
import org.eclipse.datatools.modelbase.sql.query.CallStatement;
import org.eclipse.datatools.modelbase.sql.query.ColumnName;
import org.eclipse.datatools.modelbase.sql.query.Grouping;
import org.eclipse.datatools.modelbase.sql.query.GroupingExpression;
import org.eclipse.datatools.modelbase.sql.query.GroupingSets;
import org.eclipse.datatools.modelbase.sql.query.GroupingSetsElement;
import org.eclipse.datatools.modelbase.sql.query.GroupingSetsElementExpression;
import org.eclipse.datatools.modelbase.sql.query.GroupingSetsElementSublist;
import org.eclipse.datatools.modelbase.sql.query.GroupingSpecification;
import org.eclipse.datatools.modelbase.sql.query.MergeInsertSpecification;
import org.eclipse.datatools.modelbase.sql.query.MergeOnCondition;
import org.eclipse.datatools.modelbase.sql.query.MergeOperationSpecification;
import org.eclipse.datatools.modelbase.sql.query.MergeSourceTable;
import org.eclipse.datatools.modelbase.sql.query.MergeTargetTable;
import org.eclipse.datatools.modelbase.sql.query.MergeUpdateSpecification;
import org.eclipse.datatools.modelbase.sql.query.NullOrderingType;
import org.eclipse.datatools.modelbase.sql.query.OrderByOrdinal;
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
import org.eclipse.datatools.modelbase.sql.query.QueryResultSpecification;
import org.eclipse.datatools.modelbase.sql.query.QuerySearchCondition;
import org.eclipse.datatools.modelbase.sql.query.QuerySelect;
import org.eclipse.datatools.modelbase.sql.query.QuerySelectStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryUpdateStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryValueExpression;
import org.eclipse.datatools.modelbase.sql.query.QueryValues;
import org.eclipse.datatools.modelbase.sql.query.ResultColumn;
import org.eclipse.datatools.modelbase.sql.query.ResultTableAllColumns;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryModelFactory;
import org.eclipse.datatools.modelbase.sql.query.SearchConditionCombined;
import org.eclipse.datatools.modelbase.sql.query.SearchConditionCombinedOperator;
import org.eclipse.datatools.modelbase.sql.query.SearchConditionNested;
import org.eclipse.datatools.modelbase.sql.query.SuperGroup;
import org.eclipse.datatools.modelbase.sql.query.SuperGroupElement;
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
import org.eclipse.datatools.modelbase.sql.query.UpdateSourceExprList;
import org.eclipse.datatools.modelbase.sql.query.UpdateSourceQuery;
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
import org.eclipse.datatools.modelbase.sql.query.WithTableSpecification;
import org.eclipse.datatools.modelbase.sql.query.helper.DataTypeHelper;
import org.eclipse.datatools.modelbase.sql.query.helper.StatementHelper;
import org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl;
import org.eclipse.datatools.modelbase.sql.query.util.SQLQueryArrayDataTypeImpl;
import org.eclipse.datatools.modelbase.sql.query.util.SQLQueryMultisetDataTypeImpl;
import org.eclipse.datatools.modelbase.sql.query.util.SQLQuerySourceFormat;
import org.eclipse.datatools.modelbase.sql.routines.Function;
import org.eclipse.datatools.modelbase.sql.routines.Procedure;
import org.eclipse.datatools.modelbase.sql.routines.SQLRoutinesFactory;
import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaFactory;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.schema.impl.SQLSchemaPackageImpl;
import org.eclipse.datatools.modelbase.sql.tables.SQLTablesFactory;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.datatools.sqltools.parsers.sql.SQLParserFactory;
import org.eclipse.datatools.sqltools.parsers.sql.SQLParserLogger;
import org.eclipse.emf.common.util.EList;

/**
 * This class provides methods for creating elements of the SQL
 * query model.
 *
 * I wraps the factories:
 *   - RDBSchemaFactory
 *
 */
public class SQLQueryParserFactory implements SQLParserFactory,SQLQueryParserFactoryDataTypes {

    /**
     * char constant for the quote that are used for delimited identifiers
     * like "Col1", where this delimited identifier should not be modified to or
     * be compared equal to COL1 or col1, value: the character '"' */
    static final char DEFAULT_DELIMITED_IDENTIFIER_QUOTE = '\"';

  /**
   * The quote string that is used for delimited identifiers like
   * "Col1", where the delimited identifier should not be modified to upper case
   * characters or be compared equal to COL1 or col1, default value:
   * {@link #DEFAULT_DELIMITED_IDENTIFIER_QUOTE}
   */
  protected char getDelimitedIdentifierQuote() {
      if (sourceFormat != null) {
          return sourceFormat.getDelimitedIdentifierQuote();
      } else {
          return DEFAULT_DELIMITED_IDENTIFIER_QUOTE;
      }
  }


  protected static  SQLSchemaFactory rdbFactory           = null;
  protected static  SQLQueryModelFactory  sqlQueryModelFactory = null;
  protected static  SQLTablesFactory tableFactory         = null;

  // TODO: wherever we use delimitedIdentifierQuote change to quote property in source format
  // but use it from the given SQLObjects created by the parser
  //protected SQLQuerySourceFormat sourceFormat		= SQLQuerySourceFormat.SQL_SOURCE_FORMAT_DEFAULT;
  protected SQLQuerySourceFormat sourceFormat	= null;

  //protected boolean statementTypeOnly			= false;

      /**
       * int constant for {@link PredicateComparisonOperator#EQUAL} "="
       * @see org.eclipse.datatools.modelbase.sql.query.PredicateComparisonOperator#EQUAL
       */
      public final static int COMPARISON_OPERATOR_EQ = PredicateComparisonOperator.EQUAL;
    
      /**
       * int constant for {@link PredicateComparisonOperator#NOT_EQUAL} "<>"
       * @see org.eclipse.datatools.modelbase.sql.query.PredicateComparisonOperator#NOT_EQUAL
       */
      public final static int COMPARISON_OPERATOR_NE = PredicateComparisonOperator.NOT_EQUAL;
    
      /**
       * int constant for {@link PredicateComparisonOperator#LESS_THAN} "<"
       * @see org.eclipse.datatools.modelbase.sql.query.PredicateComparisonOperator#LESS_THAN
       */
      public final static int COMPARISON_OPERATOR_LT = PredicateComparisonOperator.LESS_THAN;
    
      /**
       * int constant for {@link PredicateComparisonOperator#GREATER_THAN} ">"
       * @see org.eclipse.datatools.modelbase.sql.query.PredicateComparisonOperator#GREATER_THAN
       */
      public final static int COMPARISON_OPERATOR_GT = PredicateComparisonOperator.GREATER_THAN;
    
      /**
       * int constant for {@link PredicateComparisonOperator#LESS_THAN_OR_EQUAL} "<="
       * @see org.eclipse.datatools.modelbase.sql.query.PredicateComparisonOperator#LESS_THAN_OR_EQUAL
       */
      public final static int COMPARISON_OPERATOR_LE = PredicateComparisonOperator.LESS_THAN_OR_EQUAL;
    
      /**
       * int constant for {@link PredicateComparisonOperator#GREATER_THAN_OR_EQUAL} ">="
       * @see org.eclipse.datatools.modelbase.sql.query.PredicateComparisonOperator#GREATER_THAN_OR_EQUAL
       */
      public final static int COMPARISON_OPERATOR_GE = PredicateComparisonOperator.GREATER_THAN_OR_EQUAL;
    
    
      /**
       * int constant for {@link SearchConditionCombinedOperator#AND}
       * @see org.eclipse.datatools.modelbase.sql.query.SearchConditionCombinedOperator#AND
       */
      public final static int COMBINED_OPERATOR_AND = SearchConditionCombinedOperator.AND;
    
      /**
       * int constant for {@link SearchConditionCombinedOperator#OR}
       * @see org.eclipse.datatools.modelbase.sql.query.SearchConditionCombinedOperator#OR
       */
      public final static int COMBINED_OPERATOR_OR  = SearchConditionCombinedOperator.OR;
    
    
      /**
       * int constant for {@link ValueExpressionUnaryOperator#PLUS} "+"
       * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionUnaryOperator#PLUS
       */
      public final static int UNARY_OPERATOR_PLUS   = ValueExpressionUnaryOperator.PLUS;
    
      /**
       * int constant for {@link ValueExpressionUnaryOperator#MINUS} "-"
       * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionUnaryOperator#MINUS
       */
      public final static int UNARY_OPERATOR_MINUS  = ValueExpressionUnaryOperator.MINUS;
    
    
      /**
       * int constant for {@link ValueExpressionCombinedOperator#ADD} "+"
       *
       * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionCombinedOperator#ADD
       */
      public final static int COMBINED_OPERATOR_ADD           = ValueExpressionCombinedOperator.ADD;
    
      /**
       * int constant for {@link ValueExpressionCombinedOperator#SUBTRACT} "-"
       *
       * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionCombinedOperator#SUBTRACT
       */
      public final static int COMBINED_OPERATOR_SUBTRACT      = ValueExpressionCombinedOperator.SUBTRACT;
    
      /**
       * int constant for {@link ValueExpressionCombinedOperator#MULTIPLY} "*"
       *
       * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionCombinedOperator#MULTIPLY
       */
      public final static int COMBINED_OPERATOR_MULTIPLY      = ValueExpressionCombinedOperator.MULTIPLY;
    
      /**
       * int constant for {@link ValueExpressionCombinedOperator#DIVIDE} "/"
       *
       * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionCombinedOperator#DIVIDE
       */
      public final static int COMBINED_OPERATOR_DIVIDE        = ValueExpressionCombinedOperator.DIVIDE;
    
      /**
       * int constant for {@link ValueExpressionCombinedOperator#CONCATENATE} "||"
       *
       * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionCombinedOperator#CONCATENATE
       */
      public final static int COMBINED_OPERATOR_CONCATENATE = ValueExpressionCombinedOperator.CONCATENATE;
    
      /**
       * int constant for {@link ValueExpressionLabeledDurationType#DAYS}
       *
       * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionLabeledDurationType#DAYS
       */
      public final static int DURATION_TYPE_DAYS = ValueExpressionLabeledDurationType.DAYS;
    
      /**
       * int constant for {@link ValueExpressionLabeledDurationType#HOURS}
       *
       * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionLabeledDurationType#HOURS
       */
      public final static int DURATION_TYPE_HOURS = ValueExpressionLabeledDurationType.HOURS;
    
      /**
       * int constant for {@link ValueExpressionLabeledDurationType#MICROSECONDS}
       *
       * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionLabeledDurationType#MICROSECONDS
       */
      public final static int DURATION_TYPE_MICROSECONDS = ValueExpressionLabeledDurationType.MICROSECONDS;
    
      /**
       * int constant for {@link ValueExpressionLabeledDurationType#MINUTES}
       *
       * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionLabeledDurationType#MINUTES
       */
      public final static int DURATION_TYPE_MINUTES = ValueExpressionLabeledDurationType.MINUTES;
    
      /**
       * int constant for {@link ValueExpressionLabeledDurationType#MONTHS}
       *
       * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionLabeledDurationType#MONTHS
       */
      public final static int DURATION_TYPE_MONTHS = ValueExpressionLabeledDurationType.MONTHS;
    
      /**
       * int constant for {@link ValueExpressionLabeledDurationType#SECONDS}
       *
       * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionLabeledDurationType#SECONDS
       */
      public final static int DURATION_TYPE_SECONDS = ValueExpressionLabeledDurationType.SECONDS;
    
      /**
       * int constant for {@link ValueExpressionLabeledDurationType#YEARS}
       *
       * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionLabeledDurationType#YEARS
       */
      public final static int DURATION_TYPE_YEARS = ValueExpressionLabeledDurationType.YEARS;
    
      
      /**
       * int constant for {@link NullOrderingType#NONE}
       *
       * @see org.eclipse.datatools.modelbase.sql.query.NullOrderingType#NONE
       */
      public final static int NULL_ORDERING_TYPE_NONE = NullOrderingType.NONE;
      
      /**
       * int constant for {@link NullOrderingType#NULLS_FIRST}
       *
       * @see org.eclipse.datatools.modelbase.sql.query.NullOrderingType#NULLS_FIRST
       */
      public final static int NULL_ORDERING_TYPE_NULLS_FIRST = NullOrderingType.NULLS_FIRST;
      
      /**
       * int constant for {@link NullOrderingType#NULLS_LAST}
       *
       * @see org.eclipse.datatools.modelbase.sql.query.NullOrderingType#NULLS_LAST
       */
      public final static int NULL_ORDERING_TYPE_NULLS_LAST = NullOrderingType.NULLS_LAST;
      
      
      /**
       * int constant for {@link OrderingSpecType#NONE}
       *
       * @see org.eclipse.datatools.modelbase.sql.query.OrderingSpecType#NONE
       */
      public final static int ORDERING_SPEC_TYPE_NONE = OrderingSpecType.NONE;
      
      /**
       * int constant for {@link OrderingSpecType#ASC}
       *
       * @see org.eclipse.datatools.modelbase.sql.query.OrderingSpecType#ASC
       */
      public final static int ORDERING_SPEC_TYPE_ASC = OrderingSpecType.ASC;
      
      /**
       * int constant for {@link OrderingSpecType#DESC}
       *
       * @see org.eclipse.datatools.modelbase.sql.query.OrderingSpecType#DESC
       */
      public final static int ORDERING_SPEC_TYPE_DESC = OrderingSpecType.DESC;
      
      
      
      /**
       * int constant for {@link SuperGroupType#CUBE}
       *
       * @see org.eclipse.datatools.modelbase.sql.query.SuperGroupType#CUBE
       */
      public final static int SUPERGROUP_TYPE_CUBE = SuperGroupType.CUBE;
    
      /**
       * int constant for {@link SuperGroupType#ROLLUP}
       *
       * @see org.eclipse.datatools.modelbase.sql.query.SuperGroupType#ROLLUP
       */
      public final static int SUPERGROUP_TYPE_ROLLUP = SuperGroupType.ROLLUP;
    
      /**
       * int constant for {@link SuperGroupType#GRANDTOTAL}
       *
       * @see org.eclipse.datatools.modelbase.sql.query.SuperGroupType#GRANDTOTAL
       */
      public final static int SUPERGROUP_TYPE_GRANDTOTAL = SuperGroupType.GRANDTOTAL;
    
    
      /**
       * int constant for {@link PredicateQuantifiedType#ALL} "ALL"
       * @see org.eclipse.datatools.modelbase.sql.query.PredicateQuantifiedType#ALL
       */
      public final static int QUANTIFIER_ALL     = PredicateQuantifiedType.ALL;
    
      /**
       * int constant for {@link PredicateQuantifiedType#ANY} "ANY"
       * @see org.eclipse.datatools.modelbase.sql.query.PredicateQuantifiedType#ANY
       */
      public final static int QUANTIFIER_ANY     = PredicateQuantifiedType.ANY;
    
      /**
       * int constant for {@link PredicateQuantifiedType#SOME} "SOME"
       * @see org.eclipse.datatools.modelbase.sql.query.PredicateQuantifiedType#SOME
       */
      public final static int QUANTIFIER_SOME    = PredicateQuantifiedType.SOME;
    
    
      /**
       * int constant for {@link TableJoinedOperator#DEFAULT_INNER} "JOIN"
       * @see org.eclipse.datatools.modelbase.sql.query.TableJoinedOperator#DEFAULT_INNER
       */
      public final static int JOIN_DEFAULT_INNER  = TableJoinedOperator.DEFAULT_INNER;
    
      /**
       * int constant for {@link TableJoinedOperator#EXPLICIT_INNER} "INNER JOIN"
       * @see org.eclipse.datatools.modelbase.sql.query.TableJoinedOperator#EXPLICIT_INNER
       */
      public final static int JOIN_EXPLICIT_INNER = TableJoinedOperator.EXPLICIT_INNER;
    
      /**
       * int constant for {@link TableJoinedOperator#LEFT_OUTER} "LEFT JOIN"
       * @see org.eclipse.datatools.modelbase.sql.query.TableJoinedOperator#LEFT_OUTER
       */
      public final static int JOIN_LEFT_OUTER     = TableJoinedOperator.LEFT_OUTER;
    
      /**
       * int constant for {@link TableJoinedOperator#RIGHT_OUTER} "RIGHT JOIN"
       * @see org.eclipse.datatools.modelbase.sql.query.TableJoinedOperator#RIGHT_OUTER
       */
      public final static int JOIN_RIGHT_OUTER    = TableJoinedOperator.RIGHT_OUTER;
    
      /**
       * int constant for {@link TableJoinedOperator#FULL_OUTER} "FULL JOIN"
       * @see org.eclipse.datatools.modelbase.sql.query.TableJoinedOperator#FULL_OUTER
       */
      public final static int JOIN_FULL_OUTER     = TableJoinedOperator.FULL_OUTER;
    
    
      /**
       * int constant for {@link QueryCombinedOperator#UNION} "UNION"
       * @see org.eclipse.datatools.modelbase.sql.query.QueryCombinedOperator#UNION
       */
      public final static int QUERY_COMBINED_UNION         = QueryCombinedOperator.UNION;
    
      /**
       * int constant for {@link QueryCombinedOperator#UNION_ALL} "UNION ALL"
       * @see org.eclipse.datatools.modelbase.sql.query.QueryCombinedOperator#UNION_ALL
       */
      public final static int QUERY_COMBINED_UNION_ALL     = QueryCombinedOperator.UNION_ALL;
    
      /**
       * int constant for {@link QueryCombinedOperator#INTERSECT} "INTERSECT"
       * @see org.eclipse.datatools.modelbase.sql.query.QueryCombinedOperator#INTERSECT
       */
      public final static int QUERY_COMBINED_INTERSECT     = QueryCombinedOperator.INTERSECT;
    
      /**
       * int constant for {@link QueryCombinedOperator#INTERSECT_ALL} "INTERSECT ALL"
       * @see org.eclipse.datatools.modelbase.sql.query.QueryCombinedOperator#INTERSECT_ALL
       */
      public final static int QUERY_COMBINED_INTERSECT_ALL = QueryCombinedOperator.INTERSECT_ALL;
    
      /**
       * int constant for {@link QueryCombinedOperator#EXCEPT} "EXCEPT"
       * @see org.eclipse.datatools.modelbase.sql.query.QueryCombinedOperator#EXCEPT
       */
      public final static int QUERY_COMBINED_EXCEPT     = QueryCombinedOperator.EXCEPT;
    
      /**
       * int constant for {@link QueryCombinedOperator#EXCEPT_ALL} "EXCEPT ALL"
       * @see org.eclipse.datatools.modelbase.sql.query.QueryCombinedOperator#EXCEPT_ALL
       */
      public final static int QUERY_COMBINED_EXCEPT_ALL = QueryCombinedOperator.EXCEPT_ALL;
    
      /**
       * int constant for {@link UpdatabilityType#READ_ONLY} "FOR READ ONLY"
       * @see org.eclipse.datatools.modelbase.sql.query.UpdatabilityType#READ_ONLY
       */
      public final static int UPDATABILITY_TYPE_FOR_READ_ONLY = UpdatabilityType.READ_ONLY;
      
      /**
       * int constant for {@link UpdatabilityType#UPDATE} "FOR UPDATE"
       * @see org.eclipse.datatools.modelbase.sql.query.UpdatabilityType#UPDATE
       */
      public final static int UPDATABILITY_TYPE_FOR_UPDATE = UpdatabilityType.UPDATE;
    
    
      // ****************************************************** String constants


    /** String constant for special register, value "CURRENT_DATE" */
    public final static String SPECIAL_REGISTER_CURRENT_DATE = "CURRENT_DATE"; //$NON-NLS-1$

    /** String constant for special register, value "CURRENT_TIME" */
    public final static String SPECIAL_REGISTER_CURRENT_TIME = "CURRENT_TIME"; //$NON-NLS-1$

    /** String constant for special register, value "CURRENT_TIMESTAMP" */
    public final static String SPECIAL_REGISTER_CURRENT_TIMESTAMP = "CURRENT_TIMESTAMP"; //$NON-NLS-1$

    /** String constant for special register, value "LOCALTIME" */
    public final static String SPECIAL_REGISTER_LOCALTIME = "LOCALTIME"; //$NON-NLS-1$

    /** String constant for special register, value "LOCALTIMESTAMP" */
    public final static String SPECIAL_REGISTER_LOCALTIMESTAMP = "LOCALTIMESTAMP"; //$NON-NLS-1$

    /**
     * String constant for special register, value
     * "CURRENT_DEFAULT_TRANSFORM_GROUP"
     */
    public final static String SPECIAL_REGISTER_CURRENT_DEFAULT_TRANSFORM_GROUP = "CURRENT_DEFAULT_TRANSFORM_GROUP"; //$NON-NLS-1$

    /**
     * String constant for special register, value
     * "CURRENT_TRANSFORM_GROUP_FOR_TYPE"
     */
    public final static String SPECIAL_REGISTER_CURRENT_TRANSFORM_GROUP_FOR_TYPE = "CURRENT_TRANSFORM_GROUP_FOR_TYPE"; //$NON-NLS-1$

    /** String constant for special register, value "CURRENT_PATH" */
    public final static String SPECIAL_REGISTER_CURRENT_PATH = "CURRENT_PATH"; //$NON-NLS-1$

    /** String constant for special register, value "CURRENT_ROLE" */
    public final static String SPECIAL_REGISTER_CURRENT_ROLE = "CURRENT_ROLE"; //$NON-NLS-1$

    /** String constant for special register, value "CURRENT_USER" */
    public final static String SPECIAL_REGISTER_CURRENT_USER = "CURRENT_USER"; //$NON-NLS-1$

    /** String constant for special register, value "SESSION_USER" */
    public final static String SPECIAL_REGISTER_SESSION_USER = "SESSION_USER"; //$NON-NLS-1$

    /** String constant for special register, value "SYSTEM_USER" */
    public final static String SPECIAL_REGISTER_SYSTEM_USER = "SYSTEM_USER"; //$NON-NLS-1$

    /** String constant for special register, value "USER" */
    public final static String SPECIAL_REGISTER_USER = "USER"; //$NON-NLS-1$

    /** String constant for special register, value "VALUE" */
    public final static String SPECIAL_REGISTER_VALUE = "VALUE"; //$NON-NLS-1$





      /**
       * String constant for OrderBySpecification "ASC"
       * 
       * @see org.eclipse.datatools.modelbase.sql.query.OrderBySpecification#isDescending()
       */
      public final static String ORDER_ASC      = "ASC"; //$NON-NLS-1$

      /**
       * String constant for OrderBySpecification "DESC"
       *
       * @see org.eclipse.datatools.modelbase.sql.query.OrderBySpecification#isDescending()
       */
      public final static String ORDER_DESC     = "DESC"; //$NON-NLS-1$


      /**
       * String constant for QuerySelect "ALL"
       *
       * @see org.eclipse.datatools.modelbase.sql.query.QuerySelect#isDistinct()
       */
      public final static String ALL                   = "ALL"; //$NON-NLS-1$

      /**
       * String constant for QuerySelect "DISTINCT"
       *
       * @see org.eclipse.datatools.modelbase.sql.query.QuerySelect#isDistinct()
       */
      public final static String DISTINCT              = "DISTINCT"; //$NON-NLS-1$

  /**
   * proxy method for later logger implementation,
   *
   * method for log level warning, prints message and class name out on console.
   *
   * @param message
   */
  protected void logWarning(String message) {
      SQLParserLogger.getLogger().writeInfo("WARNING: " + this.getClass() + "#" + message); //$NON-NLS-1$ //$NON-NLS-2$
  }


/**
 * Constructs an instance of this class.  This is the default constructor.
 *
 * Resulting <code>SQLQueryParserFactory</code> is equal to the resulting
 * <code>SQLQueryParserFactory</code> of
 * {@link #SQLQueryParserFactory(SQLQuerySourceFormat)} with
 * <code>SQLQuerySourceFormat =
 *             SQLQuerySourceFormat.SQL_SOURCE_FORMAT_DEFAULT</code>
 * @see SQLQuerySourceFormat#SQL_SOURCE_FORMAT_DEFAULT
 */
public SQLQueryParserFactory() {
  if (SQLSchemaFactory.eINSTANCE == null)
    SQLSchemaPackageImpl.init();
  rdbFactory = SQLSchemaFactory.eINSTANCE;
  if (SQLQueryModelFactory.eINSTANCE == null) {
    SQLQueryModelPackageImpl.init();
  }
  sqlQueryModelFactory = SQLQueryModelFactory.eINSTANCE;

  tableFactory = SQLTablesFactory.eINSTANCE;
}

///**
// * Constructs an instance of this class.
// * If <code>statementTypeOnly</code> is
// * <code>true</code>, only the top-level <code>QueryStatement</code> will
// * be constructed, no other <code>SQLObject</code> AST elements.
// *
// * @param statementTypeOnly
// *            if specified <code>true</code> only the top-level
// *            <code>QueryStatement</code> will be constructed, no other
// *            <code>SQLObject</code> AST elements
// */
//public SQLQueryParserFactory(boolean statementTypeOnly) {
//  this();
//  this.statementTypeOnly = statementTypeOnly;
//}


/**
 * Constructor.
 * @param aSourceFormat
 */
public SQLQueryParserFactory(SQLQuerySourceFormat aSourceFormat) {
    this();
    this.sourceFormat = aSourceFormat;
}


public SQLQuerySourceFormat getSQLSourceFormat()
{
    if (sourceFormat == SQLQuerySourceFormat.SQL_SOURCE_FORMAT_DEFAULT
                    || sourceFormat == null)
    {
        sourceFormat = SQLQuerySourceFormat.copyDefaultFormat();
    }
    return sourceFormat;
}
public void setSQLSourceFormat(SQLQuerySourceFormat sourceFormat)
{
    if (sourceFormat == SQLQuerySourceFormat.SQL_SOURCE_FORMAT_DEFAULT
                    || sourceFormat == null)
    {
        sourceFormat = SQLQuerySourceFormat.copyDefaultFormat();
    }
    this.sourceFormat = sourceFormat;
}


/**
 * Utility used to append elements to an List at the end.
 */
public List listConcat(List p_list, Object p_rhs) {
	List list = (p_list!=null) ? p_list : new Vector();
	if (p_rhs==null) throw new IllegalStateException("RHS is null elist="+p_list); //$NON-NLS-1$
	list.add(p_rhs);
	return list;
}
/**
 * Utility used to append elements to an List in the beginning.
 */
public List listConcat(Object p_lhs, List p_list) {
	List list = (p_list!=null) ? p_list : new Vector();
	if (p_lhs==null) throw new IllegalStateException("LHS is null elist="+ p_list); //$NON-NLS-1$
	list.add(0,p_lhs);
	return list;
}



/**
 * Adds the given <code>tableCorrelation</code> to the given <code>tableExpr</code>.
 * @param tableExpr
 * @param tableCorrelation
 * @return the given <code>tableExpr</code>
 */
public TableExpression addTableCorrelationToTableExpression(TableExpression tableExpr, TableCorrelation tableCorrelation) {
    tableExpr.setTableCorrelation(tableCorrelation);
    return tableExpr;
}


/* see IBM DB2 Universal Database
 * SQL Reference for Cross-Platform Development Version 2
 * Chapter 5. Statements p.427 */
protected int convertIntoBytesFromBytesInUnit(int bytesInUnit, String unit, int primitiveType) {
    int kilo = 1024,
		mega = 1024 * 1024,
		giga = 1024 * 1024 * 1024;
    int maxInt = Integer.MAX_VALUE; //2147483647;
    // the SQL spec allows round up of the correct compution
    //int maxKilo = maxInt/kilo, //-> 2147483647 / (1024) == 2097151.999
	//	  maxMega = maxInt/mega, //-> 2147483647 / (1024*1024) == 2047.999
	//	  maxGiga = maxInt/giga; //-> 2147483647 / (1024*1024*1024) == 1.999
    int maxKilo = 2097152,	//-> 2147483647 / (1024) == 2097151.999
		maxMega = 2048,		//-> 2147483647 / (1024*1024) == 2047.999
		maxGiga = 2;		//-> 2147483647 / (1024*1024*1024) == 1.999

    int sizeInByte = bytesInUnit;
    boolean sizeExceeds_MAX_INT = false;

    if (UNIT_INDICATOR_K.equalsIgnoreCase(unit)) {
        // catch overflow by 1 we're tolerant and so is the SQL spec
        if (bytesInUnit == maxKilo) {
            sizeInByte = maxInt;
        } else if (bytesInUnit < maxKilo) {
            sizeInByte *= kilo;
        } else {
            sizeExceeds_MAX_INT = true;
        }
    }
    else if (UNIT_INDICATOR_M.equalsIgnoreCase(unit)) {
        // catch overflow by 1 we're tolerant and so is the SQL spec
        if (bytesInUnit == maxMega) {
            sizeInByte = maxInt;
        } else if (bytesInUnit < maxMega) {
            sizeInByte *= mega;
        } else {
            sizeExceeds_MAX_INT = true;
        }
    }
    else if (UNIT_INDICATOR_G.equalsIgnoreCase(unit)) {
        // catch overflow by 1 we're tolerant and so is the SQL spec
        if (bytesInUnit == maxGiga) {
            sizeInByte = maxInt;
        } else if (bytesInUnit < maxGiga) {
            sizeInByte *= giga;
        } else {
            sizeExceeds_MAX_INT = true;
        }
    }

    if (sizeExceeds_MAX_INT) {
        throw new NumberFormatException("Size "+bytesInUnit+" in "+unit+"byte for "+PrimitiveType.get(primitiveType)+" exceeds "+Integer.MAX_VALUE+" byte."); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
    }

    return sizeInByte;

}


public ValueExpressionCaseElse createCaseElse( QueryValueExpression expr ) {
  //if (statementTypeOnly) {return null;}
  ValueExpressionCaseElse caseElse = sqlQueryModelFactory.createValueExpressionCaseElse();
  caseElse.setValueExpr( expr );
  return caseElse;
}

public ValueExpressionCaseSearchContent createCaseSearchContent( QuerySearchCondition aWhenCond, QueryValueExpression aExpr ) {
  //if (statementTypeOnly) {return null;}
  ValueExpressionCaseSearchContent caseSearchContent = sqlQueryModelFactory.createValueExpressionCaseSearchContent();
  caseSearchContent.setSearchCondition( aWhenCond );
  caseSearchContent.setValueExpr( aExpr );
  return caseSearchContent;
}

public ValueExpressionCaseSearch createCaseSearchExpression( List aCaseSearchWhenList, ValueExpressionCaseElse aCaseElse ) {
  //if (statementTypeOnly) {return null;}
  ValueExpressionCaseSearch caseSearch = sqlQueryModelFactory.createValueExpressionCaseSearch();
  EList caseSearchWhenList = caseSearch.getSearchContentList();
  caseSearchWhenList.addAll( aCaseSearchWhenList );
  if (aCaseElse != null)
    caseSearch.setCaseElse( aCaseElse );
  return caseSearch;
}

public List createCaseSearchList( List aCaseSearchList, ValueExpressionCaseSearchContent aCaseSearchContent ) {
  //if (statementTypeOnly) {return null;}
  if (aCaseSearchList == null)
    aCaseSearchList = new Vector();
  aCaseSearchList.add( aCaseSearchContent );
  return aCaseSearchList;
}

public ValueExpressionCaseSimpleContent createCaseSimpleContent( QueryValueExpression aWhenExpr, QueryValueExpression aResultExpr ) {
  //if (statementTypeOnly) {return null;}
  ValueExpressionCaseSimpleContent caseSimpleContent = sqlQueryModelFactory.createValueExpressionCaseSimpleContent();
  caseSimpleContent.setWhenValueExpr( aWhenExpr );
  caseSimpleContent.setResultValueExpr( aResultExpr );
  return caseSimpleContent;
}

public ValueExpressionCaseSimple createCaseSimpleExpression( QueryValueExpression aCaseValue, List aCaseSimpleWhenList, ValueExpressionCaseElse aCaseElse ) {
  //if (statementTypeOnly) {return null;}
  ValueExpressionCaseSimple caseSimple = sqlQueryModelFactory.createValueExpressionCaseSimple();
  caseSimple.setValueExpr( aCaseValue );
  EList caseSimpleWhenList = caseSimple.getContentList();
  caseSimpleWhenList.addAll( aCaseSimpleWhenList );
  if (aCaseElse != null)
    caseSimple.setCaseElse( aCaseElse );
  return caseSimple;
}

public List createCaseSimpleList( List aCaseSimpleList, ValueExpressionCaseSimpleContent aCaseSimpleContent ) {
  //if (statementTypeOnly) {return null;}
  if (aCaseSimpleList == null)
    aCaseSimpleList = new Vector();
  aCaseSimpleList.add( aCaseSimpleContent );
  return aCaseSimpleList;
}

public ValueExpressionCast createCastExpression( QueryValueExpression aExpr, String aCastType ) {
  //if (statementTypeOnly) {return null;}
    ValueExpressionCast castExpr = sqlQueryModelFactory.createValueExpressionCast();
    castExpr.setValueExpr( aExpr );
    DataType dataType = createPredefinedDataType(aCastType );
    //TODO: check that (UserDefinedDataType)! validate against database (post parse processing)
    if (dataType == null) {
        dataType = createDataType(aCastType);
    }
    castExpr.setDataType( dataType );
    return castExpr;
  }

public ValueExpressionCast createCastExpression( QueryValueExpression aExpr, DataType aCastType ) {
  //if (statementTypeOnly) {return null;}
    ValueExpressionCast castExpr = sqlQueryModelFactory.createValueExpressionCast();
    castExpr.setValueExpr( aExpr );
    castExpr.setDataType( aCastType );
    return castExpr;
  }

/*
public ValueExpressionCast createCastExpression( QueryValueExpression aExpr, QueryValueExpression aFuncExpr, Variables aVarSet ) {
  //if (statementTypeOnly) {return null;}
  ValueExpressionCast castExpr = sqlModelFactory.createValueExpressionCast();
  castExpr.setValueExpr( aExpr );

  log_warning("createCastExpression: cast type and function not specified");

  String type = ((SQLValueExpressionFunction) aFuncExpr).getName();

  castExpr.setCastTypeName( type );

  int valExprCount = aVarSet.valueExpressions.size();
  aVarSet.valueExpressions.remove( valExprCount - 1 );
  List paramList = ((SQLValueExpressionFunction) aFuncExpr).getParameters();
  if (paramList.size() > 0) {
    SQLValueExpression param1ValExpr = (SQLValueExpression) paramList.get( 0 );
    if (param1ValExpr != null) {
      String precision = param1ValExpr.getSQL();      // @d301485 bgp 06Feb2004
      castExpr.setCastTypePrecision( precision );     // @d301485 bgp 06Feb2004
      aVarSet.valueExpressions.remove( valExprCount - 2 );
    }
    if (paramList.size() > 1) {                       // @d301485 bgp 5Feb2004
      SQLValueExpression param2ValExpr = (SQLValueExpression) paramList.get( 1 );
      if (param2ValExpr != null) {
        String scale = param2ValExpr.getSQL();        // @d301485 bgp 06Feb2004
        castExpr.setCastTypeScale( scale );           // @d301485 bgp 06Feb2004
        aVarSet.valueExpressions.remove( valExprCount - 3);
      }
    }                                                 // @d301485 bgp 5Feb2004
  }

  return castExpr;
}
*/

/** if the same table name and same column name is referenced in two different
 *  schemas and the tables in the FROM-clause are not given an alias name
 *  e.g. select s1.t1.col1, s2.t1.col1 from s1.t1, s2.t1; */
public ValueExpressionColumn createColumnExpression( String aColumnName, String aTableName, String aSchemaName ) {
  //if (statementTypeOnly) {return null;}
    if (aSchemaName == null) { return createColumnExpression(aColumnName, aTableName); }

    ValueExpressionColumn colExpr = sqlQueryModelFactory.createValueExpressionColumn();


    colExpr.setName(StatementHelper.convertSQLIdentifierToCatalogFormat( aColumnName,  getDelimitedIdentifierQuote()));

    if (aTableName != null) {
      TableInDatabase tableExpr = createSimpleTable(aSchemaName, aTableName);
      colExpr.setTableExpr(tableExpr);
    }

    return colExpr;
  }


public ValueExpressionColumn createColumnExpression( String aColumnName, String aQualifier ) {
  //if (statementTypeOnly) {return null;}
  ValueExpressionColumn colExpr = createColumnExpression(aColumnName);

  if (aQualifier != null) {
    TableInDatabase tableRef = sqlQueryModelFactory.createTableInDatabase();

/*
    // TODO: check if we need to flag every column w/ TemporaryTable
    Table tempTable = tableFactory.createTemporaryTable(); //hetty
    tableRef.setDatabaseTable(tempTable);
*/

    // we don't know: Tablename or Correlationname (Alias), we assume tablename
    tableRef.setName(StatementHelper.convertSQLIdentifierToCatalogFormat( aQualifier ,  getDelimitedIdentifierQuote()));

    colExpr.setTableExpr(tableRef);
  }

  return colExpr;
}

public ValueExpressionColumn createColumnExpression( String aColumnName) {
  //if (statementTypeOnly) {return null;}
    ValueExpressionColumn colExpr = sqlQueryModelFactory.createValueExpressionColumn();

      colExpr.setName(StatementHelper.convertSQLIdentifierToCatalogFormat( aColumnName ,  getDelimitedIdentifierQuote()));

    return colExpr;
}

public List createColumnList( List aColumnNameList, ValueExpressionColumn aColumnExpr ) {
  //if (statementTypeOnly) {return null;}
    if (aColumnNameList == null)
        aColumnNameList = new Vector();
      aColumnNameList.add( aColumnExpr );
      return aColumnNameList;
}

//ck
public List createColumnNameList( List aColumnNameList, ColumnName aColumnName ) {
  //if (statementTypeOnly) {return null;}
    if (aColumnName != null) {
	    if (aColumnNameList == null) {
	        aColumnNameList = new Vector();
	    }
	    aColumnNameList.add( aColumnName );
    } else {
        logWarning("#createColumnNameList: expected ColumnName to be not null."); //$NON-NLS-1$
    }
    return aColumnNameList;
}

//ck
public ColumnName createColumnName( String aColumnName ) {
  //if (statementTypeOnly) {return null;}
    ColumnName colName = sqlQueryModelFactory.createColumnName();

    colName.setName(StatementHelper.convertSQLIdentifierToCatalogFormat( aColumnName ,  getDelimitedIdentifierQuote()));

    return colName;
  }

public SearchConditionCombined createCombinedCondition( QuerySearchCondition aLeftCond, QuerySearchCondition aRightCond, int aCondOper ) {
  //if (statementTypeOnly) {return null;}
  SearchConditionCombined condCombined = sqlQueryModelFactory.createSearchConditionCombined();
  condCombined.setLeftCondition( aLeftCond );
  if (aRightCond != null) {
    condCombined.setRightCondition( aRightCond );
  }
  condCombined.setCombinedOperator( SearchConditionCombinedOperator.get( aCondOper ));

  //TODO: check if this name setting is legal
  //condCombined.setName(SearchConditionCombinedOperator.get( aCondOper ).toString());

  return condCombined;
}

public ValueExpressionCombined createCombinedExpression( QueryValueExpression aLeftExpr, int aExprOper, QueryValueExpression aRightExpr ) {
  //if (statementTypeOnly) {return null;}
  ValueExpressionCombined exprCombined = sqlQueryModelFactory.createValueExpressionCombined();
  exprCombined.setLeftValueExpr( aLeftExpr );
  exprCombined.setCombinedOperator( ValueExpressionCombinedOperator.get( aExprOper ));
  exprCombined.setRightValueExpr( aRightExpr );

  // TODO:check for correctness of name setting
  //exprCombined.setName(ValueExpressionCombinedOperator.get( aExprOper ).toString());

  return exprCombined;
}

public DataType createDataType(String aTypeName) {
  //if (statementTypeOnly) {return null;}
    DataType dataType = createPredefinedDataType(aTypeName);
    if (dataType == null) {
        dataType = createDataTypeDistinctUserDefinedType(aTypeName);
    }
    return dataType;
}

/**
 * @see org.eclipse.datatools.sqltools.parsers.sql.query.SQLQueryParserFactoryDataTypes#createDataTypeArray(DataType)
 */
public ArrayDataType createDataTypeArray(DataType dataType) {
    return createDataTypeArray(dataType, 0, null);         
}

/**
 * @see org.eclipse.datatools.sqltools.parsers.sql.query.SQLQueryParserFactoryDataTypes#createDataTypeArray(DataType, int)
 */
public ArrayDataType createDataTypeArray(DataType dataType, int maxCardinality) {
    return createDataTypeArray(dataType, maxCardinality, null);
}

/**
 * @see org.eclipse.datatools.sqltools.parsers.sql.query.SQLQueryParserFactoryDataTypes#createDataTypeArray(DataType, int)
 */
public ArrayDataType createDataTypeArray(DataType dataType, int maxCardinality, String typeName) {
    // TODO make use of the proper factory methods to be created in SQL Model
    ArrayDataType arrayDataType = new SQLQueryArrayDataTypeImpl();        
    ElementType elementType = SQLDataTypesFactory.eINSTANCE.createElementType();
    elementType.setDataType(dataType);
    arrayDataType.setElementType(elementType);
    if (maxCardinality != 0) {
        arrayDataType.setMaxCardinality(maxCardinality);
    }
    arrayDataType.setName(DataTypeHelper.TYPENAME_ARRAY);
    
    return arrayDataType;
}

/**
 * @inheritDoc org.eclipse.datatools.sqltools.parsers.sql.query.SQLQueryParserFactoryDataTypes#createDataTypeBinaryString(int, int, String)
 */
public BinaryStringDataType createDataTypeBinaryString(int primitiveType, int length, String optionalUnitsIndicator) {
    return createDataTypeBinaryString(primitiveType, length, optionalUnitsIndicator, null );
}

/**
 * @inheritDoc org.eclipse.datatools.sqltools.parsers.sql.query.SQLQueryParserFactoryDataTypes#createDataTypeBinaryString(int, int, String)
 */
public BinaryStringDataType createDataTypeBinaryString(int primitiveType, int length, String optionalUnitsIndicator, String typeName) {
  //if (statementTypeOnly) {return null;}
    BinaryStringDataType dataType = SQLDataTypesFactory.eINSTANCE.createBinaryStringDataType();
    // int constant for primitve type here must be equal to the
    // int constant for PrimitiveType literal in PrimitveType
    dataType.setPrimitiveType(PrimitiveType.get(primitiveType));
    dataType.setName(typeName);

//  TODO: dangerous, consider model change to add measuring unit
    dataType.setLength(convertIntoBytesFromBytesInUnit(length,optionalUnitsIndicator,primitiveType));

    return dataType;
}

/**
 * @inheritDoc org.eclipse.datatools.sqltools.parsers.sql.query.SQLQueryParserFactoryDataTypes#createDataTypeBoolean()
 */
public BooleanDataType createDataTypeBoolean() {
    return createDataTypeBoolean(null);
}

/**
 * @inheritDoc org.eclipse.datatools.sqltools.parsers.sql.query.SQLQueryParserFactoryDataTypes#createDataTypeBoolean()
 */
public BooleanDataType createDataTypeBoolean(String typeName) {
  //if (statementTypeOnly) {return null;}
    BooleanDataType dataType = SQLDataTypesFactory.eINSTANCE.createBooleanDataType();
    dataType.setPrimitiveType(PrimitiveType.BOOLEAN_LITERAL);
    dataType.setName(typeName);
    
    return dataType;
}

/**
 * @inheritDoc org.eclipse.datatools.sqltools.parsers.sql.query.SQLQueryParserFactoryDataTypes#createDataTypeCharacterString(int, int, String)
 */
public CharacterStringDataType createDataTypeCharacterString(int primitiveType, int length, String optionalUnitsIndicator) {
    return createDataTypeCharacterString(primitiveType, length, optionalUnitsIndicator, null);
}

public CharacterStringDataType createDataTypeCharacterString(int primitiveType, int length, String optionalUnitsIndicator, String typeName) {
    //if (statementTypeOnly) {return null;}
      CharacterStringDataType dataType = SQLDataTypesFactory.eINSTANCE.createCharacterStringDataType();
      // int constant for primitve type here must be equal to the
      // int constant for PrimitiveType literal in PrimitveType
      dataType.setPrimitiveType(PrimitiveType.get(primitiveType));
      dataType.setName(typeName);

  //  TODO: dangerous, consider model change to add measuring unit
      int convertedLength = convertIntoBytesFromBytesInUnit(length,optionalUnitsIndicator,primitiveType);
      dataType.setLength(convertedLength);

      return dataType;
  }

/**
 * @inheritDoc org.eclipse.datatools.sqltools.parsers.sql.query.SQLQueryParserFactoryDataTypes#createDataTypeDataLink(int)
 */
public DataLinkDataType createDataTypeDataLink(int length) {
    return createDataTypeDataLink(length, null);
}
    
/**
 * @inheritDoc org.eclipse.datatools.sqltools.parsers.sql.query.SQLQueryParserFactoryDataTypes#createDataTypeDataLink(int)
 */
public DataLinkDataType createDataTypeDataLink(int length, String typeName) {
  //if (statementTypeOnly) {return null;}
    DataLinkDataType dataType = SQLDataTypesFactory.eINSTANCE.createDataLinkDataType();
    // int constant for primitve type here must be equal to the
    // int constant for PrimitiveType literal in PrimitveType
    dataType.setLength(length);
    dataType.setPrimitiveType(PrimitiveType.DATALINK_LITERAL);
    dataType.setName(typeName);
    
    return dataType;
}

/**
 * @see SQLDataTypesFactory#createDateDataType()
 */
public DateDataType createDataTypeDate() {
    return createDataTypeDate(null);
}

/**
 * @see SQLDataTypesFactory#createDateDataType()
 */
public DateDataType createDataTypeDate(String typeName) {
  //if (statementTypeOnly) {return null;}
    DateDataType dataType = SQLDataTypesFactory.eINSTANCE.createDateDataType();
    // int constant for primitve type here must be equal to the
    // int constant for PrimitiveType literal in PrimitveType
    dataType.setPrimitiveType(PrimitiveType.DATE_LITERAL);
    dataType.setName(typeName);
    
    return dataType;
}

public DistinctUserDefinedType createDataTypeDistinctUserDefinedType(String aTypeName) {
    DistinctUserDefinedType dataType = SQLDataTypesFactory.eINSTANCE.createDistinctUserDefinedType();
    dataType.setName(StatementHelper.convertSQLIdentifierToCatalogFormat(aTypeName,  getDelimitedIdentifierQuote()));
    return dataType;
}

/**
 * @inheritDoc org.eclipse.datatools.sqltools.parsers.sql.query.SQLQueryParserFactoryDataTypes#createDataTypeInterval(IntervalQualifierType, IntervalQualifierType, int, int,int)
 */
public IntervalDataType createDataTypeInterval(IntervalQualifierType leadingQualifier,
                                               IntervalQualifierType trailingQualifier,
                                               int leadingFieldPrecision,
                                               int trailingFieldPrecision,
                                               int fractionalSecondsPrecision) {
    return createDataTypeInterval(leadingQualifier, trailingQualifier, leadingFieldPrecision, trailingFieldPrecision, fractionalSecondsPrecision, null);
}

/**
 * @inheritDoc org.eclipse.datatools.sqltools.parsers.sql.query.SQLQueryParserFactoryDataTypes#createDataTypeInterval(IntervalQualifierType, IntervalQualifierType, int, int,int)
 */
public IntervalDataType createDataTypeInterval(IntervalQualifierType leadingQualifier,
                                               IntervalQualifierType trailingQualifier,
                                               int leadingFieldPrecision,
                                               int trailingFieldPrecision,
                                               int fractionalSecondsPrecision,
                                               String typeName) {
	//if (statementTypeOnly) {return null;}
    IntervalDataType dataType = SQLDataTypesFactory.eINSTANCE.createIntervalDataType();
    // int constant for primitve type here must be equal to the
    // int constant for PrimitiveType literal in PrimitveType
    dataType.setPrimitiveType(PrimitiveType.INTERVAL_LITERAL);
    dataType.setLeadingQualifier(leadingQualifier);
    dataType.setTrailingQualifier(trailingQualifier);
    dataType.setLeadingFieldPrecision(leadingFieldPrecision);
    dataType.setTrailingFieldPrecision(trailingFieldPrecision);
    dataType.setFractionalSecondsPrecision(fractionalSecondsPrecision);
    dataType.setName(typeName);
    
    return dataType;
}


/**
 * @see org.eclipse.datatools.sqltools.parsers.sql.query.SQLQueryParserFactoryDataTypes#createDataTypeMultiset(DataType)
 */
public MultisetDataType createDataTypeMultiset(DataType dataType) {
    return createDataTypeMultiset(dataType, DataTypeHelper.TYPENAME_MULTISET);
}

/**
 * @see org.eclipse.datatools.sqltools.parsers.sql.query.SQLQueryParserFactoryDataTypes#createDataTypeMultiset(DataType)
 */
public MultisetDataType createDataTypeMultiset(DataType dataType, String typeName) {
    // TODO make use of the proper factory methods to be created in SQL Model
    MultisetDataType multisetDataType = new SQLQueryMultisetDataTypeImpl();    
    ElementType elementType = SQLDataTypesFactory.eINSTANCE.createElementType();
    elementType.setDataType(dataType);
    multisetDataType.setElementType(elementType);
    multisetDataType.setName(typeName);
    
    return multisetDataType;            
}


/**
 * @inheritDoc org.eclipse.datatools.sqltools.parsers.sql.query.SQLQueryParserFactoryDataTypes#createDataTypeNumericApproximate(int,int)
 */
public ApproximateNumericDataType createDataTypeNumericApproximate(int primitiveType, int precision) {
    return createDataTypeNumericApproximate(primitiveType, precision, null);
}


/**
 * @inheritDoc org.eclipse.datatools.sqltools.parsers.sql.query.SQLQueryParserFactoryDataTypes#createDataTypeNumericApproximate(int,int)
 */
public ApproximateNumericDataType createDataTypeNumericApproximate(int primitiveType, int precision, String typeName) {
  //if (statementTypeOnly) {return null;}
    ApproximateNumericDataType dataType = SQLDataTypesFactory.eINSTANCE.createApproximateNumericDataType();
    // int constant for primitve type here must be equal to the
    // int constant for PrimitiveType literal in PrimitveType
    dataType.setPrecision(precision);
    dataType.setPrimitiveType(PrimitiveType.get(primitiveType));
    dataType.setName(typeName);
    
    return dataType;
}


/**
 * @inheritDoc org.eclipse.datatools.sqltools.parsers.sql.query.SQLQueryParserFactoryDataTypes#createDataTypeNumericFixedPrecision(int,int,int)
 */
public FixedPrecisionDataType createDataTypeNumericFixedPrecision(int primitiveType, int precision, int scale) {
    return createDataTypeNumericFixedPrecision( primitiveType, precision, scale, null);
}

/**
 * @inheritDoc org.eclipse.datatools.sqltools.parsers.sql.query.SQLQueryParserFactoryDataTypes#createDataTypeNumericFixedPrecision(int,int,int)
 */
public FixedPrecisionDataType createDataTypeNumericFixedPrecision(int primitiveType, int precision, int scale, String typeName) {
  //if (statementTypeOnly) {return null;}
    FixedPrecisionDataType dataType = SQLDataTypesFactory.eINSTANCE.createFixedPrecisionDataType();
    // int constant for primitve type here must be equal to the
    // int constant for PrimitiveType literal in PrimitveType
    dataType.setPrecision(precision);
    dataType.setScale(scale);
    dataType.setPrimitiveType(PrimitiveType.get(primitiveType));
    dataType.setName(typeName);
    
    return dataType;
}

/**
 * @inheritDoc org.eclipse.datatools.sqltools.parsers.sql.query.SQLQueryParserFactoryDataTypes#createDataTypeNumericInteger(int, int)
 */
public IntegerDataType createDataTypeNumericInteger(int primitiveType, int precision) {
    return createDataTypeNumericInteger( primitiveType, precision, null);
}


/**
 * @inheritDoc org.eclipse.datatools.sqltools.parsers.sql.query.SQLQueryParserFactoryDataTypes#createDataTypeNumericInteger(int, int)
 */
public IntegerDataType createDataTypeNumericInteger(int primitiveType, int precision, String typeName) {
  //if (statementTypeOnly) {return null;}
    IntegerDataType dataType = SQLDataTypesFactory.eINSTANCE.createIntegerDataType();
    // int constant for primitve type here must be equal to the
    // int constant for PrimitiveType literal in PrimitveType
    if (precision == 0) {
	    if (primitiveType == PRIMITIVE_TYPE_SMALLINT) {
	        precision = 5;
	    } else if (primitiveType == PRIMITIVE_TYPE_INTEGER) {
	        precision = 10;
    	} else if (primitiveType == PRIMITIVE_TYPE_BIGINT) {
    	    precision = 19;
	    }
    }
    dataType.setPrecision(precision);
    dataType.setScale(0);
    dataType.setPrimitiveType(PrimitiveType.get(primitiveType));
    dataType.setName(typeName);
    
    return dataType;
}

public PredefinedDataType createDataTypePredefinedDataType(String typeName) {
    return DataTypeHelper.getPredefinedDataTypeForNamedType(typeName);
}

/**
 * @inheritDoc org.eclipse.datatools.sqltools.parsers.sql.query.SQLQueryParserFactoryDataTypes#createDataTypeTime(int, int)
 */
public TimeDataType createDataTypeTime(int primitiveType, int precision) {
    return createDataTypeTime(primitiveType, precision, null);
}


/**
 * @inheritDoc org.eclipse.datatools.sqltools.parsers.sql.query.SQLQueryParserFactoryDataTypes#createDataTypeTime(int, int)
 */
public TimeDataType createDataTypeTime(int primitiveType, int precision, String typeName) {
  //if (statementTypeOnly) {return null;}
    TimeDataType dataType = SQLDataTypesFactory.eINSTANCE.createTimeDataType();
    // int constant for primitve type here must be equal to the
    // int constant for PrimitiveType literal in PrimitveType
    dataType.setPrimitiveType(PrimitiveType.get(primitiveType));
    dataType.setFractionalSecondsPrecision(precision);
    dataType.setName(typeName);
    
    return dataType;
}

public UserDefinedType createDataTypeUserDefinedType(String aSchemaName, String aTypeName) {
    /* At this time only the Distinct flavor of user-defined type is supported. */
    UserDefinedType dataType = SQLDataTypesFactory.eINSTANCE.createDistinctUserDefinedType();
    String catalogTypeName = StatementHelper.convertSQLIdentifierToCatalogFormat(aTypeName,  getDelimitedIdentifierQuote());
    dataType.setName(catalogTypeName);
    
    /* Add the schema, if provided. */
    if (aSchemaName != null) {
        Schema rdbSchema = rdbFactory.createSchema();
        String catalogSchemaName = StatementHelper.convertSQLIdentifierToCatalogFormat(aSchemaName,  getDelimitedIdentifierQuote());
        rdbSchema.setName(catalogSchemaName);
        dataType.setSchema(rdbSchema);
    }
    
    return dataType;
}

public ValueExpressionDefaultValue  createDefaultExpression() {
	//if (statementTypeOnly) {return null;}
  ValueExpressionDefaultValue defaultExpr = sqlQueryModelFactory.createValueExpressionDefaultValue();

  // TODO: check if name setting here is legal
  //defaultExpr.setName("DEFAULT"); //$NON-NLS-1$

  return defaultExpr;
}

/** @deprecated use {@link #createDeleteStatement(SQLTableExpression, SQLTableCorrelation, SQLSearchCondition)}*/
public QueryDeleteStatement createDeleteStatement_deprecated( TableInDatabase aTargetTable, String aAsTable, QuerySearchCondition aWhereClause ) {
    return createDeleteStatement(aTargetTable, createTableCorrelation(aAsTable), aWhereClause);
}

/**
 * [New Model]
 */
public QueryDeleteStatement createDeleteStatement( TableInDatabase aTargetTable, TableCorrelation aAsTable, QuerySearchCondition aWhereClause ) {
    QueryDeleteStatement sqlDelete = sqlQueryModelFactory.createQueryDeleteStatement();
    if (aAsTable != null)
        aTargetTable.setTableCorrelation(aAsTable);
    sqlDelete.setTargetTable( aTargetTable );
    if (aWhereClause != null)
      sqlDelete.setWhereClause( aWhereClause );

    return sqlDelete;
  }


public DistinctUserDefinedType createDistinctUserDefinedType(String aTypeName) {
    return createDataTypeDistinctUserDefinedType(aTypeName);
}


public List createExpressionList( List aExprList, QueryValueExpression aValExpr ) {
  //if (statementTypeOnly) {return null;}
  if (aExprList == null)
    aExprList = new Vector();
  aExprList.add( aValExpr );
  return aExprList;
}

public List createFromClause( List aTableRefList, TableReference aTableRef ) {
  //if (statementTypeOnly) {return null;}
  if (aTableRefList == null)
    aTableRefList = new Vector();
  aTableRefList.add( aTableRef );
  return aTableRefList;
}

public ValueExpressionFunction createFunctionExpression( String aFuncName, String distinct, List aFuncParmList, String aSchemaName ) {
  //if (statementTypeOnly) {return null;}
  ValueExpressionFunction func = sqlQueryModelFactory.createValueExpressionFunction();
  Function function = SQLRoutinesFactory.eINSTANCE.createFunction();
  func.setFunction(function);
  func.setName(StatementHelper.convertSQLIdentifierToCatalogFormat( aFuncName,  getDelimitedIdentifierQuote()));

  if(aSchemaName != null){
  	Schema schema = createSchema(aSchemaName);
  	function.setSchema(schema);
  }

  if (aFuncParmList != null) {
    EList funcParmList = func.getParameterList();
    funcParmList.addAll( aFuncParmList );
  }
  func.setSpecialRegister( false );

  //TODO: check for function names not allowed to be DISTINCT (predefined, not in a user schema, not derived from column function)
  // to be checked by db specific postparse processor
  if (DISTINCT.equals(distinct)) {
      func.setDistinct( true );
  }
/*  //function can be distinct if user defined and derived from aggregation function
  if (DISTINCT.equals(distinct)) {
    func.setDistinct( isDistinctColumnFunction( aFuncName ));
  }
  else {
    func.setDistinct( false );
  }
*/
  func.setColumnFunction( isColumnFunction( aFuncName ));

  //TODO: associate Schema to function
  //func.getFunction().setSchema();

  return func;
}

public GroupingExpression createGroupingExpression( QueryValueExpression aExpr ) {
  //if (statementTypeOnly) {return null;}
  GroupingExpression groupingExpr = sqlQueryModelFactory.createGroupingExpression();
  groupingExpr.setValueExpr( aExpr );
  return groupingExpr;
}

//ck
public GroupingSets createGroupingSets( List aGroupingSetsElementList ) {
  //if (statementTypeOnly) {return null;}
  GroupingSets superGroup = sqlQueryModelFactory.createGroupingSets();
  if (aGroupingSetsElementList != null) {
      superGroup.getGroupingSetsElementList().addAll(aGroupingSetsElementList);
  }
  return superGroup;
}

//ck
public List createGroupingSetsElementList( List aGroupingSetsElementList, GroupingSetsElement aGroupingSetsElement ) {
  //if (statementTypeOnly) {return null;}
  if (aGroupingSetsElementList == null) {
      aGroupingSetsElementList = new Vector();
  }
  aGroupingSetsElementList.add( aGroupingSetsElement );
  return aGroupingSetsElementList;
}

//ck
public GroupingSetsElementExpression createGroupingSetsElementExpression( Grouping aGrouping ) {
  //if (statementTypeOnly) {return null;}
  GroupingSetsElementExpression superGroupsElementExpr = sqlQueryModelFactory.createGroupingSetsElementExpression();
  superGroupsElementExpr.setGrouping( aGrouping );
  return superGroupsElementExpr;
}

//ck
public List createGroupingSetsElementExprList( List aGroupingSetsElementExprList, GroupingSetsElementExpression aGroupingSetsElementExpr ) {
  //if (statementTypeOnly) {return null;}
  if (aGroupingSetsElementExprList == null) {
      aGroupingSetsElementExprList = new Vector();
  }
  aGroupingSetsElementExprList.add( aGroupingSetsElementExpr );
  return aGroupingSetsElementExprList;
}

//ck
public GroupingSetsElementSublist createGroupingSetsElementSublist( List aGroupingSetsElementExprList ) {
  //if (statementTypeOnly) {return null;}
  GroupingSetsElementSublist superGroupSublist = sqlQueryModelFactory.createGroupingSetsElementSublist();
  if (aGroupingSetsElementExprList != null) {
      superGroupSublist.getGroupingSetsElementExprList().addAll(aGroupingSetsElementExprList);
  }
  return superGroupSublist;
}




//ck
public List createGroupingSpecificationList( List aGroupingSpecList, GroupingSpecification aGroupingSpec ) {
  //if (statementTypeOnly) {return null;}
    if (aGroupingSpecList == null) {
        aGroupingSpecList = new Vector();
    }
    aGroupingSpecList.add( aGroupingSpec );
    return aGroupingSpecList;
}


public List createInsertRow( List aInsertExprList, ValuesRow aInsertExpr ) {
  //if (statementTypeOnly) {return null;}
  if (aInsertExprList == null)
    aInsertExprList = new Vector();
  aInsertExprList.add( aInsertExpr );
  return aInsertExprList;
}

/**
 * Started to change right here!
 *
 * has either a list of source values aSrcValuesRowList rather than a SrcQuery
 *
 * @param aTargetTable
 * @param aTargetColumnList
 * @param aSrcValuesRowList
 * @return
 */
public QueryInsertStatement createInsertStatement( TableInDatabase aTargetTable,
                                                 List aTargetColumnList,
                                                 List aSrcValuesRowList) {
  QueryInsertStatement sqlInsert = sqlQueryModelFactory.createQueryInsertStatement();

  sqlInsert.setTargetTable( aTargetTable );

  if (aTargetColumnList != null) {
    EList colList = sqlInsert.getTargetColumnList();
    colList.addAll( aTargetColumnList );

    // tie table and columns together
    aTargetTable.getValueExprColumns().addAll(aTargetColumnList);
  }

  if (aSrcValuesRowList != null) {
    EList valuesList = sqlInsert.getSourceValuesRowList();
    valuesList.addAll( aSrcValuesRowList );
  }
  return sqlInsert;
}
/**
 * has SrcQuery rather than a list of source values aSrcValuesRowList
 *
 * @param aTargetTable
 * @param aColumnList
 * @param aSrcQuery
 * @return
 */
public QueryInsertStatement createInsertStatement( TableInDatabase aTargetTable,
                           List aColumnList,
                           QueryExpressionRoot aSrcQuery) {

  // first check if the parser mistakenly (ambiguous rules!) created aSrcQuery
  //  that is a QueryValues, but should rather be the list of ValuesRow
  if (aSrcQuery != null && aSrcQuery.getQuery() != null
                  && aSrcQuery.getQuery() instanceof QueryValues) {
      QueryValues queryValues = (QueryValues) aSrcQuery.getQuery();
      List valuesRowList = queryValues.getValuesRowList();
      return createInsertStatement(aTargetTable, aColumnList, valuesRowList);
  }

  QueryInsertStatement sqlInsert = sqlQueryModelFactory.createQueryInsertStatement();

  sqlInsert.setTargetTable( aTargetTable );

  if (aColumnList != null) {
    EList colList = sqlInsert.getTargetColumnList();
    colList.addAll( aColumnList );
  }
  sqlInsert.setSourceQuery(aSrcQuery);

  return sqlInsert;
}

public ValuesRow createInsertValuesRow( List aExprList ) {
  //if (statementTypeOnly) {return null;}
  ValuesRow valuesRow = sqlQueryModelFactory.createValuesRow();
  EList exprList = valuesRow.getExprList();
  if (aExprList != null) {
    exprList.addAll( aExprList );
  }
  return valuesRow;
}


public ValuesRow createInsertValuesRow( QueryValueExpression aValueExpr ) {
  //if (statementTypeOnly) {return null;}
    ValuesRow valuesRow = sqlQueryModelFactory.createValuesRow();
    EList exprList = valuesRow.getExprList();
    if (aValueExpr != null) {
        exprList.add( aValueExpr );
    }
    return valuesRow;
}


public TableJoined createJoinedTable( TableReference aLeftTable, int aJoinType, TableReference aRightTable, QuerySearchCondition aJoinCond ) {
  //if (statementTypeOnly) {return null;}
  TableJoined joinedTable = sqlQueryModelFactory.createTableJoined();
  joinedTable.setTableRefLeft( aLeftTable );
  joinedTable.setTableRefRight( aRightTable );
  joinedTable.setJoinOperator( TableJoinedOperator.get( aJoinType ));
  if (aJoinCond != null) {
      joinedTable.setJoinCondition( aJoinCond );
  }
  return joinedTable;
}

public ValueExpressionLabeledDuration createLabeledDurationExpression( QueryValueExpression aExpr, int aDurationType ) {
  //if (statementTypeOnly) {return null;}
  ValueExpressionLabeledDuration labeledDur = sqlQueryModelFactory.createValueExpressionLabeledDuration();
  labeledDur.setValueExpr( aExpr );
  labeledDur.setLabeledDurationType( ValueExpressionLabeledDurationType.get( aDurationType ));

  // TODO: check if name setting here is correct
  //labeledDur.setName( ValueExpressionLabeledDurationType.get( aDurationType ).toString() );

  return labeledDur;
}

public QueryMergeStatement createMergeStatement(MergeTargetTable aTargetTable, MergeSourceTable aSourceTable, MergeOnCondition aOnCondition, List aOperSpecList) {
    QueryMergeStatement mergeStmt = sqlQueryModelFactory.createQueryMergeStatement();
    
    mergeStmt.setTargetTable(aTargetTable);
    mergeStmt.setSourceTable(aSourceTable);
    mergeStmt.setOnCondition(aOnCondition);
    
    /* Ignore any extra update spec or insert spec.  Can have at most one of each. */
    List operSpecList = mergeStmt.getOperationSpecList();
    boolean addedUpdateSpec = false;
    boolean addedInsertSpec = false;
    Iterator aOperSpecListIter = aOperSpecList.iterator();
    while (aOperSpecListIter.hasNext()) {
        MergeOperationSpecification aOperSpec = (MergeOperationSpecification) aOperSpecListIter.next();
        if (aOperSpec instanceof MergeUpdateSpecification && addedUpdateSpec == false) {
            operSpecList.add(aOperSpec);
            addedUpdateSpec = true;
        }
        if (aOperSpec instanceof MergeInsertSpecification && addedInsertSpec == false) {
            operSpecList.add(aOperSpec);
            addedInsertSpec = true;
        }
    }
    
    return mergeStmt;
}

public MergeTargetTable createMergeTargetTable(TableExpression aTableExpr, String aTableAliasName) {
    MergeTargetTable targetTable = sqlQueryModelFactory.createMergeTargetTable();
    
    if (aTableAliasName != null) {
        TableCorrelation tableCorr = sqlQueryModelFactory.createTableCorrelation();
        String convertedName = StatementHelper.convertSQLIdentifierToCatalogFormat( aTableAliasName,  getDelimitedIdentifierQuote());
        tableCorr.setName(convertedName);
        aTableExpr.setTableCorrelation(tableCorr);
    }
    targetTable.setTableExpr(aTableExpr);
    
    return targetTable;
}

public MergeSourceTable createMergeSourceTable(TableReference aTableRef) {
    MergeSourceTable sourceTable = sqlQueryModelFactory.createMergeSourceTable();
    
    sourceTable.setTableRef(aTableRef);
    
    return sourceTable;
}

public MergeOnCondition createMergeOnCondition(QuerySearchCondition aSearchCond) {
    MergeOnCondition onCondition = sqlQueryModelFactory.createMergeOnCondition();
    
    onCondition.setSearchCondition(aSearchCond);
    
    return onCondition;
}

public MergeInsertSpecification createMergeInsertSpecification(List aTargetColList, ValuesRow aValuesRow) {
    MergeInsertSpecification mergeInsertSpec = sqlQueryModelFactory.createMergeInsertSpecification();
    
    mergeInsertSpec.getTargetColumnList().addAll(aTargetColList);
    mergeInsertSpec.setSourceValuesRow(aValuesRow);
    
    return mergeInsertSpec;
}

public MergeUpdateSpecification createMergeUpdateSpecification(List aAssignmentExprList) {
    MergeUpdateSpecification mergeUpdateSpec = sqlQueryModelFactory.createMergeUpdateSpecification();
    
    mergeUpdateSpec.getAssignementExprList().addAll(aAssignmentExprList);
    
    return mergeUpdateSpec;
}

/** 
 * This is for as syntax hack in the parser.  The Merge statement can have an update operation spec, 
 * or an insert operation spec or both, in either order.  The simple way to handle this in the grammar 
 * is to treat them as a list of merge operation specs. */
public List createMergeOperatationSpecificationList(List aMergeSpecList, MergeOperationSpecification aMergeSpec) {
    if (aMergeSpecList == null) {
        aMergeSpecList = new ArrayList();
    }
    aMergeSpecList.add(aMergeSpec);
    
    return aMergeSpecList;
}

public ValueExpressionNullValue createNullExpression() {
  //if (statementTypeOnly) {return null;}
  ValueExpressionNullValue nullExpr = sqlQueryModelFactory.createValueExpressionNullValue();
  return nullExpr;
}

public SearchConditionNested createNestedCondition( QuerySearchCondition aCondition ) {
  //if (statementTypeOnly) {return null;}
    SearchConditionNested nest = sqlQueryModelFactory.createSearchConditionNested();
    if (aCondition != null) {
        nest.setNestedCondition( aCondition );
    }
    return nest;
}

public SearchConditionNested createNestedConditionNegated( QuerySearchCondition aCondition ) {
  //if (statementTypeOnly) {return null;}
  SearchConditionNested nest = createNestedCondition(aCondition);
  if (nest != null) {
      nest.setNegatedCondition(true);

      // TODO: check if name setting here is okay
      //nest.setName("NOT"); //$NON-NLS-1$
  }
  return nest;
}

public ValueExpressionNested createNestedExpression( QueryValueExpression aNestedExpr) {
  //if (statementTypeOnly) {return null;}
    ValueExpressionNested nest = sqlQueryModelFactory.createValueExpressionNested();
    nest.setNestedValueExpr( aNestedExpr );
    return nest;
}

public TableNested createNestedTable( TableReference aNestedTable ) {
  //if (statementTypeOnly) {return null;}
    TableNested nest = sqlQueryModelFactory.createTableNested();
    aNestedTable.setNest( nest );

    //TODO: add resultCols of nested table to columnList of this TableNested

    return nest;
  }

public List createOrderByClause( List aOrderByList, OrderBySpecification aOrderByExpr ) {
  //if (statementTypeOnly) {return null;}
  if (aOrderByList == null)
    aOrderByList = new Vector();
  aOrderByList.add( aOrderByExpr );
  return aOrderByList;
}

/**
 * creates an <code>OrderBySpecification</code>.
 * 
 * @param aOrderByExpr <code>QueryValueExpression</code> to order by
 * @param orderingSpec one of the constants {@link #ORDERING_SPEC_TYPE_ASC},
 *        {@link #ORDERING_SPEC_TYPE_DESC} or {@link #ORDERING_SPEC_TYPE_NONE}
 * @param nullOrdering one of the constants
 *        {@link #NULL_ORDERING_TYPE_NULLS_FIRST},
 *        {@link #NULL_ORDERING_TYPE_NULLS_LAST} or
 *        {@link #NULL_ORDERING_TYPE_NONE}
 * @return <code>OrderBySpecification</code>
 */
public OrderBySpecification createOrderByExpression( QueryValueExpression aOrderByExpr, int orderingSpec, int nullOrdering) {
  //if (statementTypeOnly) {return null;}
    OrderBySpecification orderBy = null;
	if (aOrderByExpr != null && aOrderByExpr instanceof ValueExpressionSimple) {
	    try
        {
	        String value = ((ValueExpressionSimple)aOrderByExpr).getValue();
	        int ordinal = Integer.parseInt(value);
	        OrderByOrdinal orderByOrdinal = sqlQueryModelFactory.createOrderByOrdinal();
	        orderByOrdinal.setOrdinalValue( ordinal );
	        orderBy = orderByOrdinal;
        }
        catch (NumberFormatException e) {
            // will be handled
        }
	}
    if (orderBy == null) {
		OrderByValueExpression orderByExpr = sqlQueryModelFactory.createOrderByValueExpression();
		orderByExpr.setValueExpr( aOrderByExpr );
		orderBy = orderByExpr;
    }
    
    orderBy.setOrderingSpecOption(OrderingSpecType.get(orderingSpec));
    orderBy.setNullOrderingOption(NullOrderingType.get(nullOrdering));
    
    // TODO: deprecated field
	orderBy.setDescending( orderingSpec == ORDERING_SPEC_TYPE_DESC );
	return orderBy;
}

/**
 * creates an <code>OrderBySpecification</code>
 * 
 * @param aOrderByExpr <code>QueryValueExpression</code> to order by
 * @param optAscDesc one of the following constants {@link #ORDER_ASC} or
 *        {@link #ORDER_DESC}
 * @deprecated use
 *             {@link #createOrderByExpression(QueryValueExpression, int, int)}
 */
public OrderBySpecification createOrderByExpression( QueryValueExpression aOrderByExpr, String optAscDesc) {
  //if (statementTypeOnly) {return null;}
    OrderBySpecification orderBy = null;
	if (aOrderByExpr != null && aOrderByExpr instanceof ValueExpressionSimple) {
	    try
        {
	        String value = ((ValueExpressionSimple)aOrderByExpr).getValue();
	        int ordinal = Integer.parseInt(value);
	        OrderByOrdinal orderByOrdinal = sqlQueryModelFactory.createOrderByOrdinal();
	        orderByOrdinal.setOrdinalValue( ordinal );
	        orderBy = orderByOrdinal;
        }
        catch (NumberFormatException e) {
            // will be handled
        }
	}
    if (orderBy == null) {
		OrderByValueExpression orderByExpr = sqlQueryModelFactory.createOrderByValueExpression();
		orderByExpr.setValueExpr( aOrderByExpr );
		orderBy = orderByExpr;
    }
	orderBy.setDescending( ORDER_DESC.equals(optAscDesc) );
	return orderBy;
}

public PredefinedDataType createPredefinedDataType(String aTypeName) {
    return createDataTypePredefinedDataType(aTypeName);
}

public PredicateBasic createPredicateBasic( QueryValueExpression aLeftExpr, int aRelOper, QueryValueExpression aRightExpr ) {
  //if (statementTypeOnly) {return null;}
  PredicateBasic predBasic = sqlQueryModelFactory.createPredicateBasic();
  predBasic.setLeftValueExpr( aLeftExpr );
  predBasic.setComparisonOperator( PredicateComparisonOperator.get( aRelOper ));
  predBasic.setRightValueExpr( aRightExpr );

  //TODO: check if this name setting is legal
  //predBasic.setName( PredicateComparisonOperator.get( aRelOper ).toString() );

  return predBasic;
}

public PredicateBetween createPredicateBetween( QueryValueExpression aLeftExpr, boolean isNotBetween, QueryValueExpression aRightExpr1, QueryValueExpression aRightExpr2 ) {
  //if (statementTypeOnly) {return null;}
  PredicateBetween predBetween = sqlQueryModelFactory.createPredicateBetween();
  predBetween.setLeftValueExpr( aLeftExpr );
  predBetween.setNotBetween( isNotBetween );
  predBetween.setRightValueExpr1( aRightExpr1 );
  predBetween.setRightValueExpr2( aRightExpr2 );
  // TODO: name is here just for SysOut convenience, check and remove if illegal
  //predBetween.setName((isNotBetween)?"NOT BETWEEN":"BETWEEN"); //$NON-NLS-1$ //$NON-NLS-2$
  return predBetween;
}

public PredicateExists createPredicateExists( QueryExpressionBody aQueryExpr) {
  //if (statementTypeOnly) {return null;}
    PredicateExists exists = sqlQueryModelFactory.createPredicateExists();
    exists.setQueryExpr(aQueryExpr);
    return exists;
}

public PredicateInValueList createPredicateInValueList( QueryValueExpression aLeftExpr, boolean isNotIn, List aValuesList ) {
  //if (statementTypeOnly) {return null;}
  PredicateInValueList predIn = sqlQueryModelFactory.createPredicateInValueList();
  predIn.setValueExpr( aLeftExpr );
  predIn.setNotIn( isNotIn );
  EList rightExprList = predIn.getValueExprList();
  rightExprList.addAll( aValuesList );
  return predIn;
}

public PredicateInValueRowSelect createPredicateInValueRowSelect( List aValuesList, boolean isNotIn, QueryExpressionRoot aQueryExpr ) {
  //if (statementTypeOnly) {return null;}
    PredicateInValueRowSelect predIn = sqlQueryModelFactory.createPredicateInValueRowSelect();
    predIn.getValueExprList().addAll( aValuesList );
    predIn.setNotIn( isNotIn );
    predIn.setQueryExpr(aQueryExpr);
    return predIn;
}

public PredicateInValueSelect createPredicateInValueSelect( QueryValueExpression aLeftExpr, boolean isNotIn, QueryExpressionRoot aQueryExpr ) {
  //if (statementTypeOnly) {return null;}
    PredicateInValueSelect predIn = sqlQueryModelFactory.createPredicateInValueSelect();
    predIn.setValueExpr( aLeftExpr );
    predIn.setNotIn( isNotIn );
    predIn.setQueryExpr(aQueryExpr);
    return predIn;
  }

public PredicateLike createPredicateLike( QueryValueExpression aMatchingExpr, boolean isNotLike, QueryValueExpression aPatternExpr, QueryValueExpression aEscapeExpr ) {
  //if (statementTypeOnly) {return null;}
  PredicateLike predLike = sqlQueryModelFactory.createPredicateLike();
  predLike.setMatchingValueExpr( aMatchingExpr );
  predLike.setNotLike( isNotLike );
  predLike.setPatternValueExpr( aPatternExpr );
  if (aEscapeExpr != null)
    predLike.setEscapeValueExpr( aEscapeExpr );
  return predLike;
}

public PredicateIsNull createPredicateNull( QueryValueExpression aValueExpr, boolean isNotNull) {
  //if (statementTypeOnly) {return null;}
    PredicateIsNull predNull = sqlQueryModelFactory.createPredicateIsNull();
    if (aValueExpr != null) {
        predNull.setValueExpr( aValueExpr );
    }
    predNull.setNotNull(isNotNull);

    // TODO: check if name setting here is legal
    //predNull.setName((isNotNull)?"NOT NULL":"NULL"); //$NON-NLS-1$ //$NON-NLS-2$

    return predNull;
}

public PredicateQuantifiedValueSelect createPredicateQuantifiedValueSelect( QueryValueExpression aValueExpr, int aRelOper, int aQuantType, QueryExpressionRoot aQueryExpr ) {
  //if (statementTypeOnly) {return null;}
    PredicateQuantifiedValueSelect predQuant = sqlQueryModelFactory.createPredicateQuantifiedValueSelect();
    predQuant.setValueExpr( aValueExpr );
    predQuant.setComparisonOperator( PredicateComparisonOperator.get( aRelOper ));
    predQuant.setQuantifiedType( PredicateQuantifiedType.get( aQuantType ));
    predQuant.setQueryExpr( aQueryExpr );

    //TODO: check if this name setting is legal
    //predQuant.setName( predQuant.getComparisonOperator().toString()
    //                + ' ' + predQuant.getQuantifiedType().toString());

    return predQuant;
  }

public PredicateQuantifiedRowSelect createPredicateQuantifiedRowSelect( List aValueExprList, int aQuantType, QueryExpressionRoot aQueryExpr ) {
  //if (statementTypeOnly) {return null;}
    PredicateQuantifiedRowSelect predQuant = sqlQueryModelFactory.createPredicateQuantifiedRowSelect();
    if (aValueExprList != null) {
        predQuant.getValueExprList().addAll( aValueExprList );
	}
    predQuant.setQuantifiedType( PredicateQuantifiedType.get( aQuantType ));
    predQuant.setQueryExpr( aQueryExpr );

    //TODO: check if this name setting is legal
    //predQuant.setName( predQuant.getQuantifiedType().toString() );

    return predQuant;
  }

public ProcedureReference createProcedureReference(String aSchemaName, String aProcName) {  
    ProcedureReference procRef = sqlQueryModelFactory.createProcedureReference();
    Procedure proc = SQLRoutinesFactory.eINSTANCE.createProcedure();
    Schema rdbSchema = rdbFactory.createSchema();
    
    char quoteChar = getDelimitedIdentifierQuote();
    if (aSchemaName != null) {
        String convertedSchemaName = StatementHelper.convertSQLIdentifierToCatalogFormat(aSchemaName, quoteChar);
        rdbSchema.setName(convertedSchemaName);
    } 
    else {
        /* If there is no schema name, then use the schema name that was set as the "omit schema" in the source format. 
         * Note: the "omit schema" name is already in catalog format. */
        if (this.sourceFormat != null) {     
            String omitSchemaName = this.sourceFormat.getOmitSchema();
            rdbSchema.setName(omitSchemaName);
        }
    }
    
    proc.setSchema(rdbSchema);      
    String convertedProcName = StatementHelper.convertSQLIdentifierToCatalogFormat(aProcName, quoteChar);
    proc.setName(convertedProcName);
    procRef.setProcedure(proc);
      
    return procRef;
}

public QueryCombined createQueryCombined(QueryExpressionBody aLeftQuery,
        int combinedOperator,
        QueryExpressionBody aRightQuery) {
    return createQueryCombined(aLeftQuery, combinedOperator, aRightQuery, null, 0);
}


public QueryCombined createQueryCombined(QueryExpressionBody aLeftQuery,
                                         int combinedOperator,
                                         QueryExpressionBody aRightQuery,
                                         List aSortSpecList,
                                         int aRowFetchLimit) {
	//if (statementTypeOnly) {return null;}
    QueryCombined queryCombined = sqlQueryModelFactory.createQueryCombined();

    queryCombined.setName(QueryCombinedOperator.get(combinedOperator).toString());

    queryCombined.setLeftQuery(aLeftQuery);
    queryCombined.setCombinedOperator(QueryCombinedOperator.get(combinedOperator));
    queryCombined.setRightQuery(aRightQuery);

    if (aSortSpecList != null) {
        List sortSpecList = queryCombined.getSortSpecList();
        sortSpecList.addAll(aSortSpecList);
    }
    queryCombined.setRowFetchLimit(aRowFetchLimit);
    
    return queryCombined;
}

public QueryNested createQueryNested(QueryExpressionBody queryExprBody){
    return createQueryNested(queryExprBody, null, 0);
}

public QueryNested createQueryNested(QueryExpressionBody queryExprBody, List aSortSpecList, int aRowFetchLimit){
    QueryNested queryNested = sqlQueryModelFactory.createQueryNested();
    
    queryNested.setNestedQuery(queryExprBody);
    queryExprBody.setQueryNest(queryNested);
    
    if (aSortSpecList != null) {
        List sortSpecList = queryNested.getSortSpecList();
        sortSpecList.addAll(aSortSpecList);
    }
    queryNested.setRowFetchLimit(aRowFetchLimit);
    
    return queryNested;
}

//This method is called in the end, when all the clauses of the query
//have been processed.  This method also relinks the pointers to the
//table ref for the columns from Select clause and Where clause, to the
//correct table ref pointers from the From clause.
public QuerySelect createQuerySelect(String optAllOrDistinct,
                                       List aResultColList,
                                       List aTableRefList,
                                       QuerySearchCondition aWhereClause,
                                       List aGroupByList,
                                       QuerySearchCondition aHavingClause) {
    return createQuerySelect(optAllOrDistinct, aResultColList, aTableRefList, aWhereClause, aGroupByList, aHavingClause, null, 0);
}

public QuerySelect createQuerySelect(String optAllOrDistinct,
                                        List aResultColList,
                                        List aTableRefList,
                                        QuerySearchCondition aWhereClaus,
                                        List aGroupByList,
                                        QuerySearchCondition aHavingClaus,
                                        List aSortSpecList,
                                        int aRowFetchLimit) {
  //if (statementTypeOnly) {return null;}
  QuerySelect qrySel = sqlQueryModelFactory.createQuerySelect();

  qrySel.setDistinct(DISTINCT.equals(optAllOrDistinct));

  if (aResultColList != null) {
    qrySel.getSelectClause().addAll(aResultColList);
  }
  if (aTableRefList != null) {
    qrySel.getFromClause().addAll(aTableRefList);
  }
  qrySel.setWhereClause(aWhereClaus);
  if (aGroupByList != null) {
    qrySel.getGroupByClause().addAll(aGroupByList);
  }
  qrySel.setHavingClause(aHavingClaus);

  if (aSortSpecList != null) {
      List sortSpecList = qrySel.getSortSpecList();
      sortSpecList.addAll(aSortSpecList);
  }
  qrySel.setRowFetchLimit(aRowFetchLimit);
  
  return qrySel;
}

public QueryValues createQueryValues(List aValuesRowList) {
    return createQueryValues(aValuesRowList, null, 0);
}

public QueryValues createQueryValues(List aValuesRowList, List aSortSpecList, int aRowFetchLimit) {
  //if (statementTypeOnly) {return null;}
    QueryValues queryValues = sqlQueryModelFactory.createQueryValues();

	if (aValuesRowList != null) {
	    queryValues.getValuesRowList().addAll(aValuesRowList);
	}
	
	if (aSortSpecList != null) {
        List sortSpecList = queryValues.getSortSpecList();
        sortSpecList.addAll(aSortSpecList);
    }
	queryValues.setRowFetchLimit(aRowFetchLimit);
	
	return queryValues;
}

public QueryExpressionRoot createQueryExpressionRoot(QueryExpressionBody aQuery, List aWithTableSpecList) {
  //if (statementTypeOnly) {return null;}
    QueryExpressionRoot queryExpr = sqlQueryModelFactory.createQueryExpressionRoot();
    queryExpr.setQuery(aQuery);
    if (aWithTableSpecList != null) {
        queryExpr.getWithClause().addAll(aWithTableSpecList);
    }
    return queryExpr;
}


/** use <code>createReferenceTable(aSchemaName, aTableName,
 *  createTableCorrelation(aCorrName))</code>
 *  @deprecated use {@link #createReferenceTable(String, String, TableCorrelation)}
 */
public TableExpression createReferenceTable( String aSchemaName, String aTableName, String aCorrName ) {
  //if (statementTypeOnly) {return null;}
    return createReferenceTable(aSchemaName, aTableName, createTableCorrelation(aCorrName));
  }


public TableExpression createReferenceTable( String aSchemaName, String aTableName, TableCorrelation aTableCorr ) {
  //if (statementTypeOnly) {return null;}
    TableInDatabase table = createSimpleTable( aSchemaName, aTableName);
    table.setTableCorrelation(aTableCorr);
    return table;
  }


public ResultColumn createResultColumn( QueryValueExpression aResultColExpr, String aAsName ) {
  //if (statementTypeOnly) {return null;}
  ResultColumn resultCol = sqlQueryModelFactory.createResultColumn();
  resultCol.setValueExpr( aResultColExpr );

  if (aAsName != null) {
      resultCol.setName(StatementHelper.convertSQLIdentifierToCatalogFormat( aAsName,  getDelimitedIdentifierQuote()));
  }
  // don't set Name of ResultColumn if "AS"-alias was not specified!!!
  // it would mess up SQL source genereation
/*  else
  {
      if (aResultColExpr instanceof ValueExpressionColumn) {
          ValueExpressionColumn column = (ValueExpressionColumn)aResultColExpr;
          //TODO: check if name setting here is correct
          resultCol.setName(StatementHelper.convertSQLIdentifierToCatalogFormat( column.getName() ,  getDelimitedIdentifierQuote()));
      }
  }
*/
  return resultCol;
}


public ResultTableAllColumns createResultTableAllColumns(String aTableName) {
      return createResultTableAllColumns(aTableName, null);
  }


public ResultTableAllColumns createResultTableAllColumns(String aTableName, String aSchemaName) {
    //if (statementTypeOnly) {return null;}
      ResultTableAllColumns resultTableAll = sqlQueryModelFactory.createResultTableAllColumns();

      TableInDatabase tableInDB = createSimpleTable(aSchemaName, aTableName);
      resultTableAll.setTableExpr(tableInDB);
      
      // ! the name is not in catalog format but is already in SQL format !
      //   maybe the ResultTableAllColumn can not be resolved and has no reference
      //   to a TableExpr, then we have to use the name, which could be
      //   the concatenation of a schema name and a table name
      //resultTableAll.setName(StatementHelper.convertSQLIdentifierToCatalogFormat(aTableName,  getDelimitedIdentifierQuote()));
      String identDelimQt = String.valueOf(getDelimitedIdentifierQuote());
      if (aSchemaName != null
              && !aSchemaName.startsWith(identDelimQt)
              && !aSchemaName.endsWith(identDelimQt)) {
          aSchemaName = aSchemaName.toUpperCase();
      }
      if (!aTableName.startsWith(identDelimQt)
              && !aTableName.endsWith(identDelimQt)) {
          aTableName = aTableName.toUpperCase();
      }

      if (aSchemaName != null) {
          resultTableAll.setName(aSchemaName+"."+aTableName); //$NON-NLS-1$
      } 
      else {
          resultTableAll.setName(aTableName);
      }

      return resultTableAll;
  }


public ValueExpressionScalarSelect createScalarSelectExpression( QueryExpressionRoot aQueryExpr ) {
  //if (statementTypeOnly) {return null;}
    ValueExpressionScalarSelect scalarSelect = sqlQueryModelFactory.createValueExpressionScalarSelect();
    scalarSelect.setQueryExpr( aQueryExpr );
    return scalarSelect;
}


public Schema createSchema(String aSchemaName){
	Schema schema = rdbFactory.createSchema();
    schema.setName(StatementHelper.convertSQLIdentifierToCatalogFormat(aSchemaName,  getDelimitedIdentifierQuote()));
	return schema;
}


public List createSelectClause( List aResultSpecList, QueryResultSpecification aResultCol ) {
  //if (statementTypeOnly) {return null;}
  if (aResultSpecList == null)
    aResultSpecList = new Vector();
  if (aResultCol != null)
    aResultSpecList.add( aResultCol );
  return aResultSpecList;
}


public QuerySelectStatement createSelectStatement( QuerySelect aQrySel, List aOrderByList ) {
    QueryExpressionRoot qryExpr = sqlQueryModelFactory.createQueryExpressionRoot();
    qryExpr.setQuery(aQrySel);
    return createSelectStatement(qryExpr, aOrderByList);
}

public QuerySelectStatement createSelectStatement( QueryExpressionRoot aQryExpr, List aOrderByList ) {
    return createSelectStatement(aQryExpr, aOrderByList, null);
}

public QuerySelectStatement createSelectStatement( QueryExpressionRoot aQryExpr, List aOrderByList, UpdatabilityExpression aUpdatabilityExpr ) {
    QuerySelectStatement qryStatement = sqlQueryModelFactory.createQuerySelectStatement();
    qryStatement.setQueryExpr(aQryExpr);
    EList orderByList = qryStatement.getOrderByClause();
    if (aOrderByList != null) {
      orderByList.addAll( aOrderByList );
    }
    if (aUpdatabilityExpr != null) {
        qryStatement.setUpdatabilityExpr(aUpdatabilityExpr);
    }
    
    return qryStatement;
}

public ValueExpressionSimple createSimpleExpression( String aExpr ) {
  //if (statementTypeOnly) {return null;}
  ValueExpressionSimple exprSimple = sqlQueryModelFactory.createValueExpressionSimple();
  exprSimple.setValue( aExpr );
  return exprSimple;
}

public TableInDatabase createSimpleTable(String aSchemaName, String aTableName) {
  //if (statementTypeOnly) {return null;}
        //RDBTable rdbTable = rdbFactory.createRDBTable();
        TableInDatabase table = sqlQueryModelFactory.createTableInDatabase();
        //Table rdbTable = rdbFactory.createRDBTable();
        Table rdbTable = tableFactory.createTemporaryTable(); //hetty

        //TODO: do we want rdbTable to have a Schema even without name?
        Schema rdbSchema = rdbFactory.createSchema();

        table.setDatabaseTable(rdbTable);
        rdbTable.setSchema(rdbSchema);

        if (aSchemaName != null) {
            rdbSchema.setName(StatementHelper.convertSQLIdentifierToCatalogFormat(aSchemaName,  getDelimitedIdentifierQuote()));
        } else {
            // associate the default schema that was omited in the SQL input
            if (this.sourceFormat != null) {
                // TODO: need fix for wsdbu00036477, so the omitSchema name really
                //       matches the schema to be omited, should be in catalog format here
                //       but in SQL format when it is entered in preferenc page by user
                aSchemaName = this.sourceFormat.getOmitSchema();
                rdbSchema.setName(aSchemaName); //omitSchemaName is already in catalog format
            }
        }

        table.setName(StatementHelper.convertSQLIdentifierToCatalogFormat(aTableName,  getDelimitedIdentifierQuote()));
        rdbTable.setName(StatementHelper.convertSQLIdentifierToCatalogFormat(aTableName,  getDelimitedIdentifierQuote()));

        return table;
    }

/**
 * Creates a <code>ValueExpressionFunction</code> with name <code>aSpecialReg</code>
 * and field <code>{@link ValueExpressionFunction#isSpecialRegister()}==true</code>
 * @param aSpecialReg the register name, e.g. {@link #SPECIAL_REGISTER_CURRENT_DATE}.
 * @return a <code>ValueExpressionFunction</code>
 */
public ValueExpressionFunction createSpecialRegisterExpression( String aSpecialReg ) {
  ValueExpressionFunction func = sqlQueryModelFactory.createValueExpressionFunction();
  func.setName( aSpecialReg );
  func.setSpecialRegister( true );
  return func;
}

/**
 * Creates a function expression object for the special register with the name and 
 * user-defined data type.  This is for the special register CURRENT_TRANSFORM_GROUP_FOR_TYPE.
 */
public ValueExpressionFunction createSpecialRegisterExpression( String aRegName, UserDefinedType aDataType ) {
  ValueExpressionFunction func = sqlQueryModelFactory.createValueExpressionFunction();
  func.setName( aRegName );
  func.setSpecialRegister( true );
  
  if(aDataType != null){
      func.setDataType(aDataType);
  }
  
  return func;
}


/**
 * Creates a function expression object for the special register with the name and 
 * user-defined type name.  This is for the special register CURRENT_TRANSFORM_GROUP_FOR_TYPE.
 * @return a <code>ValueExpressionFunction</code>
 * @deprecated use createSpecialRegisterExpression(String, UserDefinedType) instead
 */
public ValueExpressionFunction createSpecialRegisterExpression( String aSpecialReg, String value ) {
  ValueExpressionFunction func = sqlQueryModelFactory.createValueExpressionFunction();
  func.setName( aSpecialReg );
  func.setSpecialRegister( true );
  if(value != null){
      ValueExpressionSimple valueExprSimple = sqlQueryModelFactory.createValueExpressionSimple();
      valueExprSimple.setValue(value);
      func.getParameterList().add(valueExprSimple);
  }
  return func;
}

//ck
public SuperGroup createSuperGroups( List aSuperGroupsElementList, int aSuperGroupType ) {
  //if (statementTypeOnly) {return null;}
    SuperGroup superGroup = sqlQueryModelFactory.createSuperGroup();
    if (aSuperGroupsElementList != null) {
        superGroup.getSuperGroupElementList().addAll(aSuperGroupsElementList);
    }
    superGroup.setSuperGroupType( SuperGroupType.get(aSuperGroupType) );
    return superGroup;
}


//ck
public List createSuperGroupsElementList( List aSuperGroupsElementList, SuperGroupElement aSuperGroupsElement ) {
  //if (statementTypeOnly) {return null;}
    if (aSuperGroupsElementList == null) {
        aSuperGroupsElementList = new Vector();
    }
    aSuperGroupsElementList.add( aSuperGroupsElement );
    return aSuperGroupsElementList;
}


//ck
public SuperGroupElementExpression createSuperGroupsElementExpression( GroupingExpression aGroupingExpr ) {
  //if (statementTypeOnly) {return null;}
    SuperGroupElementExpression superGroupsElementExpr = sqlQueryModelFactory.createSuperGroupElementExpression();
    superGroupsElementExpr.setGroupingExpr( aGroupingExpr );
    return superGroupsElementExpr;
}


//ck
public List createSuperGroupsElementExprList( List aSuperGroupsElementExprList, SuperGroupElementExpression aSuperGroupsElementExpr ) {
  //if (statementTypeOnly) {return null;}
    if (aSuperGroupsElementExprList == null) {
        aSuperGroupsElementExprList = new Vector();
    }
    aSuperGroupsElementExprList.add( aSuperGroupsElementExpr );
    return aSuperGroupsElementExprList;
}


//ck
public SuperGroupElementSublist createSuperGroupsElementSublist( List aSuperGroupsElementExprList ) {
  //if (statementTypeOnly) {return null;}
    SuperGroupElementSublist superGroupSublist = sqlQueryModelFactory.createSuperGroupElementSublist();
    if (aSuperGroupsElementExprList != null) {
        superGroupSublist.getSuperGroupElementExprList().addAll(aSuperGroupsElementExprList);
    }
    return superGroupSublist;
}


/**
 * @param aCorrName correlation name
 * @return SQLTableCorrelation or null if given correlation name is null
 */
public TableCorrelation createTableCorrelation(String aCorrName) {
    return createTableCorrelation(aCorrName, null);
}


public TableCorrelation createTableCorrelation(String aCorrName, List aColNameList) {
    TableCorrelation corr = null;
    if (aCorrName != null) {
        corr = sqlQueryModelFactory.createTableCorrelation();
        corr.setName(StatementHelper.convertSQLIdentifierToCatalogFormat(aCorrName,  getDelimitedIdentifierQuote()));
        
        if (aColNameList != null && aColNameList.size() > 0) {
            corr.getColumnNameList().addAll(aColNameList);
        }
    }
    return corr;
}


/**
 * use <code>createTableExpressionQuery(aSubquery,
 *  createTableCorrelation(aCorrName))</code>
 * @param aSubquery
 * @param aCorrName
 * @return
 * @deprecated use {@link #createTableExpressionQuery(QueryExpressionBody, TableCorrelation)}
 */
public TableExpression createTableExpressionQuery(QueryExpressionBody aSubquery, String aCorrName) {
    //if (statementTypeOnly) {return null;}
    return createTableExpressionQuery(aSubquery, createTableCorrelation(aCorrName));
}


public TableExpression createTableExpressionQuery(QueryExpressionBody aSubquery, TableCorrelation aTableCorr) {
  //if (statementTypeOnly) {return null;}
    TableExpression nestedQueryTableExpr = aSubquery;
    nestedQueryTableExpr.setTableCorrelation(aTableCorr);
    return nestedQueryTableExpr;
}


public TableFunction createTableFunction(String aFuncName, List aFuncParmList, String aSchemaName, TableCorrelation aTableCorr) {
    TableFunction func = sqlQueryModelFactory.createTableFunction();
    Function function = SQLRoutinesFactory.eINSTANCE.createFunction();
    func.setFunction(function);
    func.setName(StatementHelper.convertSQLIdentifierToCatalogFormat( aFuncName,  getDelimitedIdentifierQuote()));

    if(aSchemaName != null){
      Schema schema = createSchema(aSchemaName);
      function.setSchema(schema);
    }

    if(aSchemaName != null){
      Schema schema = createSchema(aSchemaName);
      function.setSchema(schema);
    }

    if (aFuncParmList != null) {
      EList funcParmList = func.getParameterList();
      funcParmList.addAll( aFuncParmList );
    }
    
    func.setTableCorrelation(aTableCorr);
    
    return func;
}


public UpdatabilityExpression createUpdatabilityExpression(int aUpdatabilityType, List aUpdateOfColList) {
    UpdatabilityExpression updatabilityExpr = sqlQueryModelFactory.createUpdatabilityExpression();
    
    updatabilityExpr.setUpdatabilityType(UpdatabilityType.get(aUpdatabilityType));
    if (aUpdateOfColList != null) {
        List updateOfColList = updatabilityExpr.getUpdateOfColumnList();
        Iterator aUpdateOfColListIter = aUpdateOfColList.iterator();
        while (aUpdateOfColListIter.hasNext()) {
            Object listObj = aUpdateOfColListIter.next();
            if (listObj instanceof ColumnName) {
                ColumnName colNameObj = (ColumnName) listObj;
                String colName = colNameObj.getName();
                UpdateOfColumn updateOfCol = sqlQueryModelFactory.createUpdateOfColumn();
                updateOfCol.setName(colName);
                updateOfColList.add(updateOfCol);
            }
        }
    }
    
    return updatabilityExpr;
}


public List createUpdateAssignmentClause(List aExprList, UpdateAssignmentExpression aExpr ) {
  //if (statementTypeOnly) {return null;}
  if (aExprList == null)
    aExprList = new Vector();
  aExprList.add( aExpr );
  return aExprList;
}


public UpdateAssignmentExpression createUpdateAssignmentExpression( ValueExpressionColumn aTargetCol, QueryValueExpression aExpr ) {
  //if (statementTypeOnly) {return null;}
    List targetColList = new Vector(1);
    List exprList = new Vector(1);
    targetColList.add(aTargetCol);
    exprList.add(aExpr);
    return createUpdateAssignmentExpression(targetColList, exprList);
}


public UpdateAssignmentExpression createUpdateAssignmentExpression( List aTargetColList, List aExprList ) {
  //if (statementTypeOnly) {return null;}
    // parser conflict -> filter out accidently created ScalarSelect
    if (aExprList != null && aExprList.size() == 1 && aExprList.get(0) != null
                    && aExprList.get(0) instanceof ValueExpressionScalarSelect) {
        ValueExpressionScalarSelect scalarSelect =
            (ValueExpressionScalarSelect) aExprList.get(0);
        QueryExpressionRoot queryExpr = scalarSelect.getQueryExpr();
        QueryExpressionBody query = queryExpr.getQuery();
        queryExpr.setQuery(null); // unhook the query in case of EMF ownership is not modelled correctly
        return createUpdateAssignmentExpression(aTargetColList, query);
    }
    else
    {
        UpdateAssignmentExpression assign = sqlQueryModelFactory.createUpdateAssignmentExpression();
        EList targetColList = assign.getTargetColumnList();
        targetColList.addAll( aTargetColList );
        UpdateSourceExprList exprList = sqlQueryModelFactory.createUpdateSourceExprList();
        EList valueExprList = exprList.getValueExprList();
        valueExprList.addAll( aExprList );
        assign.setUpdateSource(exprList);
        return assign;
    }
}


public UpdateAssignmentExpression createUpdateAssignmentExpression( List aTargetColList, QueryExpressionBody aUpdateQuery ) {
  //if (statementTypeOnly) {return null;}
    UpdateAssignmentExpression assign = sqlQueryModelFactory.createUpdateAssignmentExpression();
    EList targetColList = assign.getTargetColumnList();
    targetColList.addAll( aTargetColList );
    UpdateSourceQuery sourceQuery = sqlQueryModelFactory.createUpdateSourceQuery();
    sourceQuery.setQueryExpr(aUpdateQuery);
    assign.setUpdateSource(sourceQuery);
    return assign;
}


public QueryUpdateStatement createUpdateStatement( TableInDatabase aTargetTable,
                								 TableCorrelation aAsTable,
                								 List aAssignmentList,
                								 QuerySearchCondition aWhereCond ) {
  QueryUpdateStatement sqlUpdate = sqlQueryModelFactory.createQueryUpdateStatement();
  if (aAsTable != null)
      aTargetTable.setTableCorrelation(aAsTable);
  sqlUpdate.setTargetTable( aTargetTable );
  EList assignmentList = sqlUpdate.getAssignmentClause();
  if (aAssignmentList != null) {
      assignmentList.addAll( aAssignmentList );
  }
  sqlUpdate.setWhereClause( aWhereCond );

  return sqlUpdate;
}

public CallStatement createCallStatement(ProcedureReference procRef, List argList) {
    CallStatement callStmt = sqlQueryModelFactory.createCallStatement();
    callStmt.setProcedureRef(procRef);
    
    if (argList != null) {
        List callStmtArgList = callStmt.getArgumentList();
        Iterator argListIter = argList.iterator();
        while (argListIter.hasNext()) {
            Object obj = argListIter.next();
            callStmtArgList.add(obj);
        }
    }
    
    return callStmt;
}

//TODO scan the domain name for quotes and delimited identifiers with dots and ge
// parse out the schema name and the UDT e.g: "schema"".""1"."Udt" schema:   schema"."1    UDT: Udt
/** @deprecated */
public DataType createUserDefinedTypeFromDomainName(String domainName){
    return null; 
}

public ValueExpressionVariable createVariableExpression( String aVarName ) {
  //if (statementTypeOnly) {return null;}
  ValueExpressionVariable varExpr = sqlQueryModelFactory
                        .createValueExpressionVariable();
  if (aVarName != null) {
    // strip of the hostvariable prefix
    String hostVarPrefix = String.valueOf(sourceFormat.getHostVariablePrefix());
    if (aVarName.startsWith(hostVarPrefix)) {
      aVarName = aVarName.substring(1);
    }
    varExpr.setName(aVarName); // [bug 221028]
  }
  return varExpr;
}

public ValueExpressionRow createValueExpressionRow( List aExprList ) {
    ValueExpressionRow valExprRow = sqlQueryModelFactory.createValueExpressionRow();
    if (aExprList != null) {
        valExprRow.getValueExprList().addAll(aExprList);
    }
    
    return valExprRow;
}

public ValuesRow createValuesRow(List aValueExprList) {
  //if (statementTypeOnly) {return null;}
    ValuesRow valuesRow = sqlQueryModelFactory.createValuesRow();
	if (aValueExprList != null) {
	    valuesRow.getExprList().addAll(aValueExprList);
	}
	return valuesRow;
}

public ValuesRow createValuesRow(QueryValueExpression aValueExpr) {
  //if (statementTypeOnly) {return null;}
    ValuesRow valuesRow = sqlQueryModelFactory.createValuesRow();

    // check if the parser interpreted the parenthesis around the expression as
    // a part of a nested expression, if so, un-nest it
    if (aValueExpr instanceof ValueExpressionNested) {
        ValueExpressionNested nest = (ValueExpressionNested) aValueExpr;
        aValueExpr = nest.getNestedValueExpr();
    }

    if (aValueExpr != null) {
        valuesRow.getExprList().add(aValueExpr);
	}
	return valuesRow;
}

public List createValuesRowList(List aValuesRowList, ValuesRow aValuesRow) {
  //if (statementTypeOnly) {return null;}
    if (aValuesRowList == null) {
        aValuesRowList = new Vector();
    }
    aValuesRowList.add( aValuesRow );
    return aValuesRowList;
}


//ck
public WithTableSpecification createWithTableSpecification( String aTableIdentifier, List aColumnNameList, QueryExpressionBody aWithSource ) {
  //if (statementTypeOnly) {return null;}
    WithTableSpecification withTable = sqlQueryModelFactory.createWithTableSpecification();

    withTable.setName(StatementHelper.convertSQLIdentifierToCatalogFormat(aTableIdentifier,  getDelimitedIdentifierQuote()));


    if (aColumnNameList != null && !aColumnNameList.isEmpty()) {
	    withTable.getColumnNameList().addAll(aColumnNameList);
    }
    withTable.setWithTableQueryExpr(aWithSource);

    return withTable;
  }


//ck
public List createWithTableSpecificationList( List aTableWithSpecList, WithTableSpecification aTableWithSpec ) {
  //if (statementTypeOnly) {return null;}
    if (aTableWithSpecList == null) {
        aTableWithSpecList = new Vector();
    }
    aTableWithSpecList.add( aTableWithSpec );
    return aTableWithSpecList;
}


/**
 * Returns whether or not the given function name is a column (summary) function.
 * @param name the function name to check
 * @return true when the given function is a column function, otherwise false
 */
public static boolean isColumnFunction( String aFuncName ) {
  String tempName = aFuncName.trim().toUpperCase();
  if (isDistinctColumnFunction( tempName )
   || tempName.equals( "CORR" ) //$NON-NLS-1$
   || tempName.equals( "CORRELATION" ) //$NON-NLS-1$
   || tempName.equals( "COVAR" ) //$NON-NLS-1$
   || tempName.equals( "COVARIANCE" ) //$NON-NLS-1$
   || tempName.equals( "GROUPING" ) //$NON-NLS-1$
   || tempName.equals( "REGR_AVGX" ) //$NON-NLS-1$
   || tempName.equals( "REGR_AVGY" ) //$NON-NLS-1$
   || tempName.equals( "REGR_COUNT" ) //$NON-NLS-1$
   || tempName.equals( "REGR_INTERCEPT" ) //$NON-NLS-1$
   || tempName.equals( "REGR_ICPT" ) //$NON-NLS-1$
   || tempName.equals( "REGR_R2" ) //$NON-NLS-1$
   || tempName.equals( "REGR_SLOPE" ) //$NON-NLS-1$
   || tempName.equals( "REGR_SXX" ) //$NON-NLS-1$
   || tempName.equals( "REGR_SXY" ) //$NON-NLS-1$
   || tempName.equals( "REGR_SYY" ) //$NON-NLS-1$
     )
    return true;
  else
    return false;
}

/**
 * Returns whether or not the given function name is a column (summary)
 * function that allows the DISTINCT keyword in the parameter list.
 * @param name the function name to check
 * @return true when the given function is a column function with distinct,
 * otherwise false
 */
public static boolean isDistinctColumnFunction( String aFuncName ) {
  String tempName = aFuncName.trim().toUpperCase();
  if (tempName.equals( "AVG" ) //$NON-NLS-1$
   || tempName.equals( "COUNT" ) //$NON-NLS-1$
   || tempName.equals( "COUNT_BIG" ) //$NON-NLS-1$
   || tempName.equals( "MAX" ) //$NON-NLS-1$
   || tempName.equals( "MIN" ) //$NON-NLS-1$
   || tempName.equals( "STDDEV" ) //$NON-NLS-1$
   || tempName.equals( "SUM" ) //$NON-NLS-1$
   || tempName.equals( "VAR" ) //$NON-NLS-1$
   || tempName.equals( "VARIANCE" ) //$NON-NLS-1$
     )
    return true;
  else
    return false;
}

public QuerySearchCondition negateCondition(QuerySearchCondition aSearchCond, boolean negate) {
  //if (statementTypeOnly) {return null;}
    if (aSearchCond instanceof Predicate) {
        return negatePredicate((Predicate)aSearchCond, negate);
    }
    if (negate) {
        boolean isNegated = aSearchCond.isNegatedCondition();
        aSearchCond.setNegatedCondition(!isNegated);

        // TODO: check if name setting here is okay
        //aSearchCond.setName("NOT "+aSearchCond.getName()); //$NON-NLS-1$
    }

    return aSearchCond;
}

public Predicate negatePredicate(Predicate aPredicate, boolean negate) {
  //if (statementTypeOnly) {return null;}
    if (negate) {
        boolean isNegated = aPredicate.isNegatedPredicate();
        aPredicate.setNegatedPredicate(!isNegated);

        // TODO: check if name setting here is okay
        //aPredicate.setName("NOT "+aPredicate.getName()); //$NON-NLS-1$
    }

    return aPredicate;
}


public QueryValueExpression setUnaryOperator(QueryValueExpression expr, int op) {
  //if (statementTypeOnly) {return null;}
    expr.setUnaryOperator( ValueExpressionUnaryOperator.get( op ) );
    return expr;
}


} // end class
