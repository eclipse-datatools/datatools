/*
* Copyright (c) 2000, 2009 IBM Corporation and others.
* All rights reserved. This program and the accompanying materials 
* are made available under the terms of the Eclipse Public License v1.0
* which is available at
* http://www.eclipse.org/legal/epl-v10.html
*/

package org.eclipse.datatools.sqltools.parsers.sql.query;



import org.eclipse.datatools.modelbase.sql.datatypes.*;
import org.eclipse.datatools.modelbase.sql.query.*;
import org.eclipse.datatools.modelbase.sql.query.helper.*;
import org.eclipse.datatools.modelbase.sql.query.util.*;
import org.eclipse.datatools.sqltools.parsers.sql.SQLParserInternalException;


import lpg.lpgjavaruntime.*;
import java.util.List;
import org.eclipse.datatools.sqltools.parsers.sql.SQLParserInternalException;

class SQLQueryParser extends  AbstractSQLQueryParser  //SQLParser
{
SQLQueryParserFactory m_factory;


/**
 * @param lexStream
 * @param factory
 * @param sourceFormat
 * @param checkStmtOnly
 */
SQLQueryParser( LexStream lexStream,
               SQLQueryParserFactory factory,
               SQLQuerySourceFormat sourceFormat,
               boolean checkStmtOnly) throws SQLParserInternalException
{
    super(lexStream, new SQLQueryParserprs(), SQLQueryParserprs.EOFT_SYMBOL, sourceFormat, checkStmtOnly);
    this.m_factory = factory;
}

SQLQueryParser(LexStream lexStream, SQLQueryParserFactory factory, SQLQuerySourceFormat sourceFormat)
throws SQLParserInternalException
{
    this(lexStream, factory, sourceFormat, false);
}

SQLQueryParser(LexStream lexStream, SQLQueryParserFactory factory)
throws SQLParserInternalException
{
    this(lexStream, factory, SQLQuerySourceFormat.copyDefaultFormat());
}

SQLQueryParser(LexStream lexStream, SQLQueryParserFactory factory, boolean checkStmtOnly)
throws SQLParserInternalException
{
    this(lexStream, factory, SQLQuerySourceFormat.copyDefaultFormat(), checkStmtOnly);
}

SQLQueryParser(LexStream lexStream) throws SQLParserInternalException {
    this(lexStream, new SQLQueryParserFactory());
}

SQLQueryParser(LexStream lexStream, boolean checkStmtOnly) throws SQLParserInternalException {
    this(lexStream, new SQLQueryParserFactory(), checkStmtOnly);
}


public String[] orderedTerminalSymbols() { return SQLQueryParsersym.orderedTerminalSymbols; }


protected double getTokenDbl(int p_tok) {
    int tok = p_tok;
    int tokKind = getKind(p_tok);
    if (tokKind == SQLQueryParsersym.TK_MINUS_SIGN) {
        ++tok;
        return -1*Double.parseDouble(getTokenName(tok));
    } else if (tokKind == SQLQueryParsersym.TK_PLUS_SIGN) {
        ++tok;
    }
    return Double.parseDouble(getTokenName(p_tok));
}

protected int getTokenInt(int p_tok) {
    int tok = p_tok;
    int tokKind = getKind(p_tok);
    if (tokKind == SQLQueryParsersym.TK_MINUS_SIGN) {
        ++tok;
        return -1*Integer.parseInt(getTokenName(tok));
    } else if (tokKind == SQLQueryParsersym.TK_PLUS_SIGN) {
        ++tok;
    }
    return Integer.parseInt(getTokenName(tok));
}






public void ruleAction( int ruleNumber)
{
    switch(ruleNumber)
    {

 
        /*
         *  Rule 1:  <sql_dml_stmt_list> ::= <sql_dml_stmt_xspan>
         */
        case 1: 
        {
            setSym1( m_factory.listConcat(null, getSym(1))); 
        }
        break;  
 
        /*
         *  Rule 2:  <sql_dml_stmt_list> ::= <sql_dml_stmt_list> _STMT_TERM <sql_dml_stmt_xspan>
         */
        case 2: 
        {
           
				//List stmts = getList(1);
				//QueryStatement stmt = (QueryStatement) stmts.get(stmts.size()-1);
				//extendSpan(stmt,2);
				setSym1( m_factory.listConcat(getList(1), getSym(3)));
			
        }
        break;   
        /*
         *  Rule 4:  <sql_dml_stmt_xspan> ::= <sql_dml_stmt>
         */
        case 4: 
        {
           
	    	if (hasComments()) extendSpanToFollowingToken((QueryStatement) getSym(1), SQLQueryParsersym.TK_STATEMENT_TERMINATOR);
		
        }
        break;   
        /*
         *  Rule 11:  <all_or_any_cond> ::= <expression> <relop> ANY <subquery>
         */
        case 11: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createPredicateQuantifiedValueSelect((QueryValueExpression)getSym(1),getInt(2),SQLQueryParserFactory.QUANTIFIER_ANY,(QueryExpressionRoot)getSym(4))); 
        }
        break;  
 
        /*
         *  Rule 12:  <all_or_any_cond> ::= <expression> <relop> SOME <subquery>
         */
        case 12: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createPredicateQuantifiedValueSelect((QueryValueExpression)getSym(1),getInt(2),SQLQueryParserFactory.QUANTIFIER_SOME,(QueryExpressionRoot)getSym(4))); 
        }
        break;  
 
        /*
         *  Rule 13:  <all_or_any_cond> ::= <expression> <relop> ALL <subquery>
         */
        case 13: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createPredicateQuantifiedValueSelect((QueryValueExpression)getSym(1),getInt(2),SQLQueryParserFactory.QUANTIFIER_ALL,(QueryExpressionRoot)getSym(4))); 
        }
        break;  
 
        /*
         *  Rule 14:  <all_or_any_cond> ::= _LPAREN <expression_commalist_multiple_elements> _RPAREN _EQ ANY <subquery>
         */
        case 14: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createPredicateQuantifiedRowSelect(getList(2),SQLQueryParserFactory.QUANTIFIER_ANY,(QueryExpressionRoot)getSym(6))); 
        }
        break;  
 
        /*
         *  Rule 15:  <all_or_any_cond> ::= _LPAREN <expression_commalist_multiple_elements> _RPAREN _EQ SOME <subquery>
         */
        case 15: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createPredicateQuantifiedRowSelect(getList(2),SQLQueryParserFactory.QUANTIFIER_SOME,(QueryExpressionRoot)getSym(6))); 
        }
        break;  
 
        /*
         *  Rule 18:  <as_alias> ::= <opt_as> <alias_name>
         */
        case 18: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(getString(2)); 
        }
        break;  
 
        /*
         *  Rule 20:  <boolean_expression> ::= <boolean_expression> OR <boolean_term>
         */
        case 20: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
           
				setSym1(m_factory.createCombinedCondition((QuerySearchCondition)getSym(1), (QuerySearchCondition)getSym(3), SQLQueryParserFactory.COMBINED_OPERATOR_OR)); 
        }
        break;   
        /*
         *  Rule 22:  <boolean_term> ::= <boolean_term> AND <boolean_factor>
         */
        case 22: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createCombinedCondition((QuerySearchCondition)getSym(1), (QuerySearchCondition)getSym(3), SQLQueryParserFactory.COMBINED_OPERATOR_AND)); 
        }
        break;  
 
        /*
         *  Rule 24:  <boolean_factor> ::= <boolean_primary> IS <boolean_values>
         */
        case 24: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            m_factory.negateCondition((QuerySearchCondition)getSym(1),!getBoolean(3)); 
        }
        break;  
 
        /*
         *  Rule 25:  <boolean_factor> ::= <boolean_primary> IS NOT <boolean_values>
         */
        case 25: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            m_factory.negateCondition((QuerySearchCondition)getSym(1),getBoolean(4)); 
        }
        break;  
 
        /*
         *  Rule 26:  <boolean_values> ::= TRUE
         */
        case 26: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(new Boolean(true)); 
        }
        break;  
 
        /*
         *  Rule 27:  <boolean_values> ::= FALSE
         */
        case 27: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(new Boolean(false)); 
        }
        break;  
 
        /*
         *  Rule 29:  <boolean_primary> ::= NOT <simplecomp>
         */
        case 29: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.negatePredicate((Predicate)getSym(2),true)); 
        }
        break;  
 
        /*
         *  Rule 30:  <boolean_primary> ::= _LPAREN <boolean_expression> _RPAREN
         */
        case 30: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createNestedCondition((QuerySearchCondition)getSym(2))); 
        }
        break;  
 
        /*
         *  Rule 31:  <boolean_primary> ::= NOT _LPAREN <boolean_expression> _RPAREN
         */
        case 31: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createNestedConditionNegated((QuerySearchCondition)getSym(3))); 
        }
        break;  
 
        /*
         *  Rule 32:  <case_expression> ::= CASE <case_search_when_list> <opt_case_else> END
         */
        case 32: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createCaseSearchExpression(getList(2), (ValueExpressionCaseElse)getSym(3))); 
        }
        break;  
 
        /*
         *  Rule 33:  <case_expression> ::= CASE <expression> <case_simple_when_list> <opt_case_else> END
         */
        case 33: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createCaseSimpleExpression((QueryValueExpression)getSym(2), getList(3), (ValueExpressionCaseElse)getSym(4))); 
        }
        break;  
 
        /*
         *  Rule 34:  <case_search_when> ::= WHEN <condition> THEN <expression>
         */
        case 34: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createCaseSearchContent((QuerySearchCondition)getSym(2), (QueryValueExpression)getSym(4))); 
        }
        break;  
 
        /*
         *  Rule 35:  <case_search_when_list> ::= <case_search_when>
         */
        case 35: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createCaseSearchList(null,(ValueExpressionCaseSearchContent)getSym(1))); 
        }
        break;  
 
        /*
         *  Rule 36:  <case_search_when_list> ::= <case_search_when_list> <case_search_when>
         */
        case 36: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createCaseSearchList(getList(1),(ValueExpressionCaseSearchContent)getSym(2))); 
        }
        break;  
 
        /*
         *  Rule 37:  <case_simple_when> ::= WHEN <expression> THEN <expression>
         */
        case 37: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createCaseSimpleContent((QueryValueExpression)getSym(2), (QueryValueExpression)getSym(4))); 
        }
        break;  
 
        /*
         *  Rule 38:  <case_simple_when_list> ::= <case_simple_when>
         */
        case 38: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createCaseSimpleList(null,(ValueExpressionCaseSimpleContent)getSym(1))); 
        }
        break;  
 
        /*
         *  Rule 39:  <case_simple_when_list> ::= <case_simple_when_list> <case_simple_when>
         */
        case 39: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createCaseSimpleList(getList(1),(ValueExpressionCaseSimpleContent)getSym(2))); 
        }
        break;  
 
        /*
         *  Rule 40:  <cast_expression> ::= CAST _LPAREN <cast_operand> AS <cast_target> _RPAREN
         */
        case 40: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createCastExpression((QueryValueExpression)getSym(3), (DataType)getSym(5))); 
        }
        break;  
 
        /*
         *  Rule 45:  <column_name> ::= <identifier>
         */
        case 45: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createColumnName(getString(1))); 
        }
        break;  
 
        /*
         *  Rule 46:  <column_name_list> ::= <column_name>
         */
        case 46: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createColumnNameList(null,(ColumnName)getSym(1))); 
        }
        break;  
 
        /*
         *  Rule 47:  <column_name_list> ::= <column_name_list> _COMMA <column_name>
         */
        case 47: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createColumnNameList(getList(1),(ColumnName)getSym(3))); 
        }
        break;  
 
        /*
         *  Rule 48:  <column_ref> ::= <column>
         */
        case 48: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createColumnExpression(getString(1),null)); 
        }
        break;  
 
        /*
         *  Rule 49:  <column_ref> ::= <opt_schema_dot> <table> _DOT <column>
         */
        case 49: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createColumnExpression(getString(4),getString(2),getString(1))); 
        }
        break;  
 
        /*
         *  Rule 51:  <constant> ::= _STRING
         */
        case 51: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSimpleExpression(getTokenName(1))); 
        }
        break;  
 
        /*
         *  Rule 52:  <constant> ::= G _STRING
         */
        case 52: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSimpleExpression( "G".concat(getTokenName(2)) ));  //$NON-NLS-1$
			   
        }
        break;   
        /*
         *  Rule 53:  <constant> ::= N _STRING
         */
        case 53: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSimpleExpression( "N".concat(getTokenName(2)) ));  //$NON-NLS-1$
			   
        }
        break;   
        /*
         *  Rule 54:  <constant> ::= HEX_STRING_LITERAL
         */
        case 54: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSimpleExpression( getTokenName(1) ));  //$NON-NLS-1$
           
        }
        break;   
        /*
         *  Rule 55:  <constant> ::= _INTNUMBER
         */
        case 55: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSimpleExpression(getTokenName(1))); 
        }
        break;  
 
        /*
         *  Rule 56:  <constant> ::= _BIGINTEGER
         */
        case 56: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSimpleExpression(getTokenName(1))); 
        }
        break;  
 
        /*
         *  Rule 57:  <constant> ::= _DECIMALNUMBER
         */
        case 57: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSimpleExpression(getTokenName(1))); 
        }
        break;  
 
        /*
         *  Rule 58:  <constant> ::= _REALNUMBER
         */
        case 58: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSimpleExpression(getTokenName(1))); 
        }
        break;  
 
        /*
         *  Rule 65:  <datatype_array_type> ::= <datatype> ARRAY
         */
        case 65: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeArray((DataType)getSym(1), 0, DataTypeHelper.TYPENAME_ARRAY)); 
        }
        break;  
 
        /*
         *  Rule 66:  <datatype_array_type> ::= <datatype> ARRAY <left_bracket_or_trigraph> _INTNUMBER <right_bracket_or_trigraph>
         */
        case 66: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeArray((DataType)getSym(1), Integer.parseInt(getTokenName(4)), DataTypeHelper.TYPENAME_ARRAY )); 
        }
        break;  
 
        /*
         *  Rule 67:  <datatype_multiset_type> ::= <datatype> MULTISET
         */
        case 67: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeMultiset((DataType)getSym(1), DataTypeHelper.TYPENAME_MULTISET )); 
        }
        break;  
 
        /*
         *  Rule 74:  <datatype_date> ::= DATE
         */
        case 74: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeDate( DataTypeHelper.TYPENAME_DATE )); 
        }
        break;  
 
        /*
         *  Rule 75:  <datatype_time> ::= TIME
         */
        case 75: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeTime( SQLQueryParserFactory.PRIMITIVE_TYPE_TIME, 0, DataTypeHelper.TYPENAME_TIME )); 
        }
        break;  
 
        /*
         *  Rule 76:  <datatype_time> ::= TIMESTAMP
         */
        case 76: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeTime( SQLQueryParserFactory.PRIMITIVE_TYPE_TIMESTAMP, 0, DataTypeHelper.TYPENAME_TIMESTAMP )); 
        }
        break;  
 
        /*
         *  Rule 81:  <datatype_numerical_approximate> ::= FLOAT
         */
        case 81: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeNumericApproximate( SQLQueryParserFactory.PRIMITIVE_TYPE_FLOAT, 0, DataTypeHelper.TYPENAME_FLOAT)); 
        }
        break;  
 
        /*
         *  Rule 82:  <datatype_numerical_approximate> ::= FLOAT _LPAREN _INTNUMBER _RPAREN
         */
        case 82: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeNumericApproximate( SQLQueryParserFactory.PRIMITIVE_TYPE_FLOAT, Integer.parseInt(getTokenName(3)), DataTypeHelper.TYPENAME_FLOAT )); 
        }
        break;  
 
        /*
         *  Rule 83:  <datatype_numerical_approximate> ::= REAL
         */
        case 83: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeNumericApproximate( SQLQueryParserFactory.PRIMITIVE_TYPE_REAL, 0, DataTypeHelper.TYPENAME_REAL)); 
        }
        break;  
 
        /*
         *  Rule 84:  <datatype_numerical_approximate> ::= DOUBLE
         */
        case 84: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeNumericApproximate( SQLQueryParserFactory.PRIMITIVE_TYPE_DOUBLE_PRECISION, 0, DataTypeHelper.TYPENAME_DOUBLE )); 
        }
        break;  
 
        /*
         *  Rule 85:  <datatype_numerical_approximate> ::= DOUBLE PRECISION
         */
        case 85: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeNumericApproximate( SQLQueryParserFactory.PRIMITIVE_TYPE_DOUBLE_PRECISION, 0, DataTypeHelper.TYPENAME_DOUBLE_PRECISION )); 
        }
        break;  
 
        /*
         *  Rule 86:  <datatype_numerical_fixed_precision> ::= NUMERIC
         */
        case 86: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeNumericFixedPrecision( SQLQueryParserFactory.PRIMITIVE_TYPE_NUMERIC, 0, 0, DataTypeHelper.TYPENAME_NUMERIC)); 
        }
        break;  
 
        /*
         *  Rule 87:  <datatype_numerical_fixed_precision> ::= DECIMAL
         */
        case 87: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeNumericFixedPrecision( SQLQueryParserFactory.PRIMITIVE_TYPE_DECIMAL, 0, 0, DataTypeHelper.TYPENAME_DECIMAL)); 
        }
        break;  
 
        /*
         *  Rule 88:  <datatype_numerical_fixed_precision> ::= DEC
         */
        case 88: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeNumericFixedPrecision( SQLQueryParserFactory.PRIMITIVE_TYPE_DECIMAL, 0, 0, DataTypeHelper.TYPENAME_DEC)); 
        }
        break;  
 
        /*
         *  Rule 89:  <datatype_numerical_fixed_precision> ::= NUMERIC _LPAREN _INTNUMBER _RPAREN
         */
        case 89: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeNumericFixedPrecision( SQLQueryParserFactory.PRIMITIVE_TYPE_NUMERIC, Integer.parseInt(getTokenName(3)), 0, DataTypeHelper.TYPENAME_NUMERIC)); 
        }
        break;  
 
        /*
         *  Rule 90:  <datatype_numerical_fixed_precision> ::= DECIMAL _LPAREN _INTNUMBER _RPAREN
         */
        case 90: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeNumericFixedPrecision( SQLQueryParserFactory.PRIMITIVE_TYPE_DECIMAL, Integer.parseInt(getTokenName(3)), 0, DataTypeHelper.TYPENAME_DECIMAL)); 
        }
        break;  
 
        /*
         *  Rule 91:  <datatype_numerical_fixed_precision> ::= DEC _LPAREN _INTNUMBER _RPAREN
         */
        case 91: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeNumericFixedPrecision( SQLQueryParserFactory.PRIMITIVE_TYPE_DECIMAL, Integer.parseInt(getTokenName(3)), 0, DataTypeHelper.TYPENAME_DEC)); 
        }
        break;  
 
        /*
         *  Rule 92:  <datatype_numerical_fixed_precision> ::= NUMERIC _LPAREN _INTNUMBER _COMMA _INTNUMBER _RPAREN
         */
        case 92: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeNumericFixedPrecision( SQLQueryParserFactory.PRIMITIVE_TYPE_NUMERIC, Integer.parseInt(getTokenName(3)), Integer.parseInt(getTokenName(5)), DataTypeHelper.TYPENAME_NUMERIC )); 
        }
        break;  
 
        /*
         *  Rule 93:  <datatype_numerical_fixed_precision> ::= DECIMAL _LPAREN _INTNUMBER _COMMA _INTNUMBER _RPAREN
         */
        case 93: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeNumericFixedPrecision( SQLQueryParserFactory.PRIMITIVE_TYPE_DECIMAL, Integer.parseInt(getTokenName(3)), Integer.parseInt(getTokenName(5)), DataTypeHelper.TYPENAME_DECIMAL )); 
        }
        break;  
 
        /*
         *  Rule 94:  <datatype_numerical_fixed_precision> ::= DEC _LPAREN _INTNUMBER _COMMA _INTNUMBER _RPAREN
         */
        case 94: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeNumericFixedPrecision( SQLQueryParserFactory.PRIMITIVE_TYPE_DECIMAL, Integer.parseInt(getTokenName(3)), Integer.parseInt(getTokenName(5)), DataTypeHelper.TYPENAME_DEC )); 
        }
        break;  
 
        /*
         *  Rule 95:  <datatype_numerical_integer> ::= INTEGER
         */
        case 95: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeNumericInteger( SQLQueryParserFactory.PRIMITIVE_TYPE_INTEGER, 0, DataTypeHelper.TYPENAME_INTEGER )); 
        }
        break;  
 
        /*
         *  Rule 96:  <datatype_numerical_integer> ::= INT
         */
        case 96: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeNumericInteger( SQLQueryParserFactory.PRIMITIVE_TYPE_INTEGER, 0, DataTypeHelper.TYPENAME_INT )); 
        }
        break;  
 
        /*
         *  Rule 97:  <datatype_numerical_integer> ::= SMALLINT
         */
        case 97: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeNumericInteger( SQLQueryParserFactory.PRIMITIVE_TYPE_SMALLINT, 0, DataTypeHelper.TYPENAME_SMALLINT )); 
        }
        break;  
 
        /*
         *  Rule 98:  <datatype_numerical_integer> ::= BIGINT
         */
        case 98: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeNumericInteger( SQLQueryParserFactory.PRIMITIVE_TYPE_BIGINT, 0, DataTypeHelper.TYPENAME_BIGINT )); 
        }
        break;  
 
        /*
         *  Rule 99:  <datatype_binary> ::= BLOB
         */
        case 99: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeBinaryString( SQLQueryParserFactory.PRIMITIVE_TYPE_BINARY_LARGE_OBJECT, 0, null, DataTypeHelper.TYPENAME_BLOB )); 
        }
        break;  
 
        /*
         *  Rule 100:  <datatype_binary> ::= BLOB _LPAREN _INTNUMBER <datatype_opt_size_unit> _RPAREN
         */
        case 100: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeBinaryString( SQLQueryParserFactory.PRIMITIVE_TYPE_BINARY_LARGE_OBJECT, Integer.parseInt(getTokenName(3)), getString(4), DataTypeHelper.TYPENAME_BLOB )); 
        }
        break;  
 
        /*
         *  Rule 101:  <datatype_binary> ::= BINARY LARGE OBJECT
         */
        case 101: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeBinaryString( SQLQueryParserFactory.PRIMITIVE_TYPE_BINARY_LARGE_OBJECT, 0, null, DataTypeHelper.TYPENAME_BINARY_LARGE_OBJECT )); 
        }
        break;  
 
        /*
         *  Rule 102:  <datatype_binary> ::= BINARY LARGE OBJECT _LPAREN _INTNUMBER <datatype_opt_size_unit> _RPAREN
         */
        case 102: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeBinaryString( SQLQueryParserFactory.PRIMITIVE_TYPE_BINARY_LARGE_OBJECT, Integer.parseInt(getTokenName(3)), getString(4), DataTypeHelper.TYPENAME_BINARY_LARGE_OBJECT )); 
        }
        break;  
 
        /*
         *  Rule 103:  <datatype_character> ::= CHARACTER
         */
        case 103: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeCharacterString( SQLQueryParserFactory.PRIMITIVE_TYPE_CHARACTER, 0, null, DataTypeHelper.TYPENAME_CHARACTER )); 
        }
        break;  
 
        /*
         *  Rule 104:  <datatype_character> ::= CHAR
         */
        case 104: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeCharacterString( SQLQueryParserFactory.PRIMITIVE_TYPE_CHARACTER, 0, null, DataTypeHelper.TYPENAME_CHAR )); 
        }
        break;  
 
        /*
         *  Rule 105:  <datatype_character> ::= CHARACTER _LPAREN _INTNUMBER _RPAREN
         */
        case 105: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeCharacterString( SQLQueryParserFactory.PRIMITIVE_TYPE_CHARACTER, Integer.parseInt(getTokenName(3)), null, DataTypeHelper.TYPENAME_CHARACTER )); 
        }
        break;  
 
        /*
         *  Rule 106:  <datatype_character> ::= CHAR _LPAREN _INTNUMBER _RPAREN
         */
        case 106: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeCharacterString( SQLQueryParserFactory.PRIMITIVE_TYPE_CHARACTER, Integer.parseInt(getTokenName(3)), null, DataTypeHelper.TYPENAME_CHAR )); 
        }
        break;  
 
        /*
         *  Rule 107:  <datatype_character> ::= CHARACTER VARYING _LPAREN _INTNUMBER _RPAREN
         */
        case 107: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeCharacterString( SQLQueryParserFactory.PRIMITIVE_TYPE_CHARACTER_VARYING, Integer.parseInt(getTokenName(4)), null, DataTypeHelper.TYPENAME_CHARACTER_VARYING )); 
        }
        break;  
 
        /*
         *  Rule 108:  <datatype_character> ::= CHAR VARYING _LPAREN _INTNUMBER _RPAREN
         */
        case 108: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeCharacterString( SQLQueryParserFactory.PRIMITIVE_TYPE_CHARACTER_VARYING, Integer.parseInt(getTokenName(4)), null, DataTypeHelper.TYPENAME_CHAR_VARYING )); 
        }
        break;  
 
        /*
         *  Rule 109:  <datatype_character> ::= VARCHAR _LPAREN _INTNUMBER _RPAREN
         */
        case 109: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeCharacterString( SQLQueryParserFactory.PRIMITIVE_TYPE_CHARACTER_VARYING, Integer.parseInt(getTokenName(3)), null, DataTypeHelper.TYPENAME_VARCHAR )); 
        }
        break;  
 
        /*
         *  Rule 110:  <datatype_character> ::= CLOB
         */
        case 110: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeCharacterString( SQLQueryParserFactory.PRIMITIVE_TYPE_CHARACTER_LARGE_OBJECT, 0, null, DataTypeHelper.TYPENAME_CLOB )); 
        }
        break;  
 
        /*
         *  Rule 111:  <datatype_character> ::= CLOB _LPAREN _INTNUMBER <datatype_opt_size_unit> _RPAREN
         */
        case 111: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeCharacterString( SQLQueryParserFactory.PRIMITIVE_TYPE_CHARACTER_LARGE_OBJECT, Integer.parseInt(getTokenName(3)), getString(4), DataTypeHelper.TYPENAME_CLOB )); 
        }
        break;  
 
        /*
         *  Rule 112:  <datatype_character> ::= CHARACTER LARGE OBJECT
         */
        case 112: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeCharacterString( SQLQueryParserFactory.PRIMITIVE_TYPE_CHARACTER_LARGE_OBJECT, 0, null, DataTypeHelper.TYPENAME_CHARACTER_LARGE_OBJECT )); 
        }
        break;  
 
        /*
         *  Rule 113:  <datatype_character> ::= CHARACTER LARGE OBJECT _LPAREN _INTNUMBER <datatype_opt_size_unit> _RPAREN
         */
        case 113: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeCharacterString( SQLQueryParserFactory.PRIMITIVE_TYPE_CHARACTER_LARGE_OBJECT, Integer.parseInt(getTokenName(5)), getString(6), DataTypeHelper.TYPENAME_CHARACTER_LARGE_OBJECT )); 
        }
        break;  
 
        /*
         *  Rule 114:  <datatype_character> ::= CHAR LARGE OBJECT
         */
        case 114: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeCharacterString( SQLQueryParserFactory.PRIMITIVE_TYPE_CHARACTER_LARGE_OBJECT, 0, null, DataTypeHelper.TYPENAME_CHAR_LARGE_OBJECT )); 
        }
        break;  
 
        /*
         *  Rule 115:  <datatype_character> ::= CHAR LARGE OBJECT _LPAREN _INTNUMBER <datatype_opt_size_unit> _RPAREN
         */
        case 115: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
           	setSym1(m_factory.createDataTypeCharacterString( SQLQueryParserFactory.PRIMITIVE_TYPE_CHARACTER_LARGE_OBJECT, Integer.parseInt(getTokenName(5)), getString(6), DataTypeHelper.TYPENAME_CHAR_LARGE_OBJECT )); 
        }
        break;  
 
        /*
         *  Rule 116:  <datatype_character_national> ::= GRAPHIC
         */
        case 116: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeCharacterString( SQLQueryParserFactory.PRIMITIVE_TYPE_NATIONAL_CHARACTER, 0, null, DataTypeHelper.TYPENAME_GRAPHIC )); 
        }
        break;  
 
        /*
         *  Rule 117:  <datatype_character_national> ::= GRAPHIC _LPAREN _INTNUMBER _RPAREN
         */
        case 117: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeCharacterString( SQLQueryParserFactory.PRIMITIVE_TYPE_NATIONAL_CHARACTER, Integer.parseInt(getTokenName(3)), null, DataTypeHelper.TYPENAME_GRAPHIC )); 
        }
        break;  
 
        /*
         *  Rule 118:  <datatype_character_national> ::= VARGRAPHIC _LPAREN _INTNUMBER _RPAREN
         */
        case 118: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeCharacterString( SQLQueryParserFactory.PRIMITIVE_TYPE_NATIONAL_CHARACTER_VARYING, Integer.parseInt(getTokenName(3)), null, DataTypeHelper.TYPENAME_VARGRAPHIC )); 
        }
        break;  
 
        /*
         *  Rule 119:  <datatype_character_national> ::= DBCLOB
         */
        case 119: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeCharacterString( SQLQueryParserFactory.PRIMITIVE_TYPE_NATIONAL_CHARACTER_LARGE_OBJECT, 0, null, DataTypeHelper.TYPENAME_DBCLOB )); 
        }
        break;  
 
        /*
         *  Rule 120:  <datatype_character_national> ::= DBCLOB _LPAREN _INTNUMBER <datatype_opt_size_unit> _RPAREN
         */
        case 120: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeCharacterString( SQLQueryParserFactory.PRIMITIVE_TYPE_NATIONAL_CHARACTER_LARGE_OBJECT, Integer.parseInt(getTokenName(3)), getString(4), DataTypeHelper.TYPENAME_DBCLOB )); 
        }
        break;  
 
        /*
         *  Rule 121:  <datatype_opt_size_unit> ::= K
         */
        case 121: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1( getTokenName(1) ); 
        }
        break;  
 
        /*
         *  Rule 122:  <datatype_opt_size_unit> ::= M
         */
        case 122: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1( getTokenName(1) ); 
        }
        break;  
 
        /*
         *  Rule 123:  <datatype_opt_size_unit> ::= G
         */
        case 123: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1( getTokenName(1) ); 
        }
        break;  
 
        /*
         *  Rule 124:  <datatype_opt_size_unit> ::= $Empty
         */
        case 124: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1( null ); 
        }
        break;  
 
        /*
         *  Rule 125:  <datatype_path-resolved_user-defined_type_name> ::= <opt_schema_dot> <identifier>
         */
        case 125: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeUserDefinedType(getString(1), getString(2))); 
        }
        break;  
 
        /*
         *  Rule 126:  <datatype_user_defined_distinct> ::= <identifier>
         */
        case 126: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDistinctUserDefinedType(getString(1))); 
        }
        break;  
 
        /*
         *  Rule 128:  <default_option> ::= USER
         */
        case 128: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSpecialRegisterExpression(SQLQueryParserFactory.SPECIAL_REGISTER_USER)); 
        }
        break;  
 
        /*
         *  Rule 129:  <default_option> ::= CURRENT_USER
         */
        case 129: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSpecialRegisterExpression(SQLQueryParserFactory.SPECIAL_REGISTER_CURRENT_USER)); 
        }
        break;  
 
        /*
         *  Rule 130:  <default_option> ::= CURRENT_ROLE
         */
        case 130: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSpecialRegisterExpression(SQLQueryParserFactory.SPECIAL_REGISTER_CURRENT_ROLE)); 
        }
        break;  
 
        /*
         *  Rule 131:  <default_option> ::= SESSION_USER
         */
        case 131: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSpecialRegisterExpression(SQLQueryParserFactory.SPECIAL_REGISTER_SESSION_USER)); 
        }
        break;  
 
        /*
         *  Rule 132:  <default_option> ::= SYSTEM_USER
         */
        case 132: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSpecialRegisterExpression(SQLQueryParserFactory.SPECIAL_REGISTER_SYSTEM_USER)); 
        }
        break;  
 
        /*
         *  Rule 133:  <default_option> ::= CURRENT_PATH
         */
        case 133: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSpecialRegisterExpression(SQLQueryParserFactory.SPECIAL_REGISTER_CURRENT_PATH)); 
        }
        break;  
 
        /*
         *  Rule 134:  <delete_stmt> ::= DELETE FROM <target_table> <opt_as_target_table> <opt_where_clause>
         */
        case 134: 
        {
            setSym1(m_factory.createDeleteStatement((TableInDatabase)getSym(3), (TableCorrelation)getSym(4), (QuerySearchCondition)getSym(5))); 
        }
        break;  
 
        /*
         *  Rule 135:  <derived_column_list> ::= <column_ref>
         */
        case 135: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createColumnList(null,(ValueExpressionColumn) getSym(1)));  
        }
        break;  
 
        /*
         *  Rule 136:  <derived_column_list> ::= <target_column_list> _COMMA <column_ref>
         */
        case 136: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createColumnList(getList(1),(ValueExpressionColumn) getSym(3)));  
        }
        break;  
 
        /*
         *  Rule 137:  <duration> ::= DAYS
         */
        case 137: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setInt1(SQLQueryParserFactory.DURATION_TYPE_DAYS); 
        }
        break;  
 
        /*
         *  Rule 138:  <duration> ::= HOURS
         */
        case 138: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setInt1(SQLQueryParserFactory.DURATION_TYPE_HOURS); 
        }
        break;  
 
        /*
         *  Rule 139:  <duration> ::= MICROSECONDS
         */
        case 139: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setInt1(SQLQueryParserFactory.DURATION_TYPE_MICROSECONDS); 
        }
        break;  
 
        /*
         *  Rule 140:  <duration> ::= MINUTES
         */
        case 140: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setInt1(SQLQueryParserFactory.DURATION_TYPE_MINUTES); 
        }
        break;  
 
        /*
         *  Rule 141:  <duration> ::= MONTHS
         */
        case 141: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setInt1(SQLQueryParserFactory.DURATION_TYPE_MONTHS); 
        }
        break;  
 
        /*
         *  Rule 142:  <duration> ::= SECONDS
         */
        case 142: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setInt1(SQLQueryParserFactory.DURATION_TYPE_SECONDS); 
        }
        break;  
 
        /*
         *  Rule 143:  <duration> ::= YEARS
         */
        case 143: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setInt1(SQLQueryParserFactory.DURATION_TYPE_YEARS); 
        }
        break;  
 
        /*
         *  Rule 144:  <exists> ::= EXISTS _LPAREN <query_exp> _RPAREN
         */
        case 144: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1( m_factory.createPredicateExists((QueryExpressionBody) getSym(3)) ); 
        }
        break;  
 
        /*
         *  Rule 145:  <expression> ::= <expression> _PLUS <expression_term>
         */
        case 145: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createCombinedExpression((QueryValueExpression) getSym(1),SQLQueryParserFactory.COMBINED_OPERATOR_ADD,(QueryValueExpression) getSym(3))); 
        }
        break;  
 
        /*
         *  Rule 146:  <expression> ::= <expression> _MINUS <expression_term>
         */
        case 146: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createCombinedExpression((QueryValueExpression) getSym(1),SQLQueryParserFactory.COMBINED_OPERATOR_SUBTRACT,(QueryValueExpression) getSym(3))); 
        }
        break;  
 
        /*
         *  Rule 148:  <expression_commalist> ::= <expression>
         */
        case 148: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createExpressionList(null, (QueryValueExpression) getSym(1))); 
        }
        break;  
 
        /*
         *  Rule 149:  <expression_commalist> ::= <expression_commalist> _COMMA <expression>
         */
        case 149: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createExpressionList(getList(1), (QueryValueExpression) getSym(3))); 
        }
        break;  
 
        /*
         *  Rule 151:  <expression_commalist_multiple_elements> ::= <expression_commalist> _COMMA <expression>
         */
        case 151: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createExpressionList(getList(1), (QueryValueExpression) getSym(3))); 
        }
        break;  
 
        /*
         *  Rule 152:  <expression_factor> ::= _LPAREN <expression> _RPAREN
         */
        case 152: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createNestedExpression((QueryValueExpression) getSym(2))); 
        }
        break;  
 
        /*
         *  Rule 153:  <expression_factor> ::= <subquery>
         */
        case 153: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createScalarSelectExpression((QueryExpressionRoot) getSym(1))); 
        }
        break;  
 
        /*
         *  Rule 161:  <expression_factor> ::= _PLUS <expression_factor>
         */
        case 161: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1( m_factory.setUnaryOperator((QueryValueExpression)getSym(2),SQLQueryParserFactory.UNARY_OPERATOR_PLUS) ); 
        }
        break;  
 
        /*
         *  Rule 162:  <expression_factor> ::= _MINUS <expression_factor>
         */
        case 162: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1( m_factory.setUnaryOperator((QueryValueExpression)getSym(2),SQLQueryParserFactory.UNARY_OPERATOR_MINUS) ); 
        }
        break;  
 
        /*
         *  Rule 163:  <expression_factor> ::= DEFAULT
         */
        case 163: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDefaultExpression()); 
        }
        break;  
 
        /*
         *  Rule 164:  <expression_factor> ::= NULL
         */
        case 164: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createNullExpression()); 
        }
        break;  
 
        /*
         *  Rule 165:  <expression_term> ::= <expression_term> _STAR <expression_factor>
         */
        case 165: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createCombinedExpression((QueryValueExpression) getSym(1),SQLQueryParserFactory.COMBINED_OPERATOR_MULTIPLY,(QueryValueExpression) getSym(3))); 
        }
        break;  
 
        /*
         *  Rule 166:  <expression_term> ::= <expression_term> _SLASH <expression_factor>
         */
        case 166: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createCombinedExpression((QueryValueExpression) getSym(1),SQLQueryParserFactory.COMBINED_OPERATOR_DIVIDE,(QueryValueExpression) getSym(3))); 
        }
        break;  
 
        /*
         *  Rule 167:  <expression_term> ::= <expression_term> CONCAT <expression_factor>
         */
        case 167: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createCombinedExpression((QueryValueExpression) getSym(1),SQLQueryParserFactory.COMBINED_OPERATOR_CONCATENATE,(QueryValueExpression) getSym(3))); 
        }
        break;  
 
        /*
         *  Rule 168:  <expression_term> ::= <expression_term> _CONCAT_OPERATOR <expression_factor>
         */
        case 168: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createCombinedExpression((QueryValueExpression) getSym(1),SQLQueryParserFactory.COMBINED_OPERATOR_CONCATENATE,(QueryValueExpression) getSym(3))); 
        }
        break;  
 
        /*
         *  Rule 169:  <expression_term> ::= <expression_term> <duration>
         */
        case 169: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createLabeledDurationExpression((QueryValueExpression) getSym(1), getInt(2))); 
        }
        break;  
 
        /*
         *  Rule 172:  <fetch_first_clause> ::= FETCH FIRST <opt_fetch_first_row_count> <row_or_rows> ONLY
         */
        case 172: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setInt1(getInt(3)); 
        }
        break;  
 
        /*
         *  Rule 174:  <func_ref> ::= <alias_name> _DOT <identifier>
         */
        case 174: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(new String((getString(1).concat(".")).concat(getString(3))));  //$NON-NLS-1$
			   
        }
        break;   
        /*
         *  Rule 175:  <function> ::= <opt_schema_dot> <identifier> _LPAREN _STAR _RPAREN
         */
        case 175: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createFunctionExpression(getString(2), null, null, getString(1))); 
        }
        break;  
 
        /*
         *  Rule 176:  <function> ::= <opt_schema_dot> <identifier> _LPAREN <opt_all_distinct> <opt_expression_commalist> _RPAREN
         */
        case 176: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createFunctionExpression(getString(2), getString(4), getList(5), getString(1))); 
        }
        break;  
 
        /*
         *  Rule 179:  <grouping_exp> ::= <expression>
         */
        case 179: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1( m_factory.createGroupingExpression((QueryValueExpression) getSym(1)) ); 
        }
        break;  
 
        /*
         *  Rule 180:  <grouping_sets> ::= GROUPING SETS _LPAREN <grouping_sets_element_list> _RPAREN
         */
        case 180: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1( m_factory.createGroupingSets(getList(4)) ); 
        }
        break;  
 
        /*
         *  Rule 181:  <grouping_sets_element_list> ::= <grouping_sets_element>
         */
        case 181: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createGroupingSetsElementList(null,(GroupingSetsElement) getSym(1)));  
        }
        break;  
 
        /*
         *  Rule 182:  <grouping_sets_element_list> ::= <grouping_sets_element_list> _COMMA <grouping_sets_element>
         */
        case 182: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createGroupingSetsElementList(getList(1),(GroupingSetsElement) getSym(3)));  
        }
        break;  
 
        /*
         *  Rule 183:  <grouping_sets_element> ::= _LPAREN <grouping_sets_element_exp_list> _RPAREN
         */
        case 183: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1( m_factory.createGroupingSetsElementSublist(getList(2)) );  
        }
        break;  
 
        /*
         *  Rule 185:  <grouping_sets_element_exp> ::= <grouping>
         */
        case 185: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1( m_factory.createGroupingSetsElementExpression((Grouping) getSym(1)) ); 
        }
        break;  
 
        /*
         *  Rule 186:  <grouping_sets_element_exp_list> ::= <grouping_sets_element_exp>
         */
        case 186: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createGroupingSetsElementExprList(null,(GroupingSetsElementExpression) getSym(1)));  
        }
        break;  
 
        /*
         *  Rule 187:  <grouping_sets_element_exp_list> ::= <grouping_sets_element_exp_list> _COMMA <grouping_sets_element_exp>
         */
        case 187: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createGroupingSetsElementExprList(getList(1),(GroupingSetsElementExpression) getSym(3)));  
        }
        break;  
 
        /*
         *  Rule 190:  <grouping_spec_list> ::= <grouping_spec>
         */
        case 190: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createGroupingSpecificationList(null,(GroupingSpecification) getSym(1)));  
        }
        break;  
 
        /*
         *  Rule 191:  <grouping_spec_list> ::= <grouping_spec_list> _COMMA <grouping_spec>
         */
        case 191: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createGroupingSpecificationList(getList(1),(GroupingSpecification) getSym(3)));  
        }
        break;  
 
        /*
         *  Rule 192:  <identifier> ::= REGULAR_IDENTIFIER
         */
        case 192: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(getTokenName(1)); 
        }
        break;  
 
        /*
         *  Rule 193:  <identifier> ::= DELIMITED_IDENTIFIER
         */
        case 193: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(getTokenName(1)); 
        }
        break;  
 
        /*
         *  Rule 195:  <in_cond> ::= <expression> NOT IN _LPAREN <expression_commalist> _RPAREN
         */
        case 195: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            boolean notIn = true;
				setSym1(m_factory.createPredicateInValueList((QueryValueExpression)getSym(1), notIn, getList(5))); 
        }
        break;   
        /*
         *  Rule 196:  <in_cond> ::= <expression> IN _LPAREN <expression_commalist> _RPAREN
         */
        case 196: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            boolean notIn = false;
				setSym1(m_factory.createPredicateInValueList((QueryValueExpression)getSym(1), notIn, getList(4))); 
        }
        break;   
        /*
         *  Rule 197:  <in_cond> ::= <expression> NOT IN <subquery>
         */
        case 197: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            boolean notIn = true;
				setSym1(m_factory.createPredicateInValueSelect((QueryValueExpression)getSym(1), notIn, (QueryExpressionRoot)getSym(4))); 
        }
        break;   
        /*
         *  Rule 198:  <in_cond> ::= <expression> IN <subquery>
         */
        case 198: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            boolean notIn = false;
				setSym1(m_factory.createPredicateInValueSelect((QueryValueExpression)getSym(1), notIn, (QueryExpressionRoot)getSym(3))); 
        }
        break;   
        /*
         *  Rule 199:  <in_cond> ::= _LPAREN <expression_commalist> _COMMA <expression> _RPAREN NOT IN <subquery>
         */
        case 199: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            boolean notIn = true;
				setSym1(m_factory.createPredicateInValueRowSelect(m_factory.createExpressionList(getList(2),(QueryValueExpression)getSym(4)), notIn, (QueryExpressionRoot)getSym(8))); 
        }
        break;   
        /*
         *  Rule 200:  <in_cond> ::= _LPAREN <expression_commalist> _COMMA <expression> _RPAREN IN <subquery>
         */
        case 200: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            boolean notIn = false;
				setSym1(m_factory.createPredicateInValueRowSelect(m_factory.createExpressionList(getList(2),(QueryValueExpression)getSym(4)), notIn, (QueryExpressionRoot)getSym(7))); 
        }
        break;   
        /*
         *  Rule 201:  <insert_row> ::= _LPAREN <expression_commalist_multiple_elements> _RPAREN
         */
        case 201: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createInsertValuesRow(getList(2))); 
        }
        break;  
 
        /*
         *  Rule 202:  <insert_row> ::= <expression>
         */
        case 202: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createInsertValuesRow((QueryValueExpression)getSym(1))); 
        }
        break;  
 
        /*
         *  Rule 203:  <insert_row_commalist> ::= <insert_row>
         */
        case 203: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createInsertRow(null, (ValuesRow) getSym(1))); 
        }
        break;  
 
        /*
         *  Rule 204:  <insert_row_commalist> ::= <insert_row_commalist> _COMMA <insert_row>
         */
        case 204: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createInsertRow(getList(1), (ValuesRow) getSym(3))); 
        }
        break;  
 
        /*
         *  Rule 205:  <insert_stmt> ::= INSERT INTO <target_table> <opt_target_column_list> VALUES <insert_row_commalist>
         */
        case 205: 
        {
            setSym1(m_factory.createInsertStatement((TableInDatabase) getSym(3), getList(4), getList(6))); 
        }
        break;  
 
        /*
         *  Rule 206:  <insert_stmt> ::= INSERT INTO <target_table> <opt_target_column_list> <query_exp_root>
         */
        case 206: 
        {
            setSym1(m_factory.createInsertStatement((TableInDatabase)getSym(3), getList(4), (QueryExpressionRoot)getSym(5))); 
        }
        break;  
 
        /*
         *  Rule 207:  <intervaltest> ::= <expression> NOT BETWEEN <expression> AND <expression>
         */
        case 207: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            boolean notBetween = true;
				setSym1(m_factory.createPredicateBetween((QueryValueExpression)getSym(1), notBetween, (QueryValueExpression)getSym(4), (QueryValueExpression)getSym(6))); 
        }
        break;   
        /*
         *  Rule 208:  <intervaltest> ::= <expression> BETWEEN <expression> AND <expression>
         */
        case 208: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            boolean notBetween = false;
				setSym1(m_factory.createPredicateBetween((QueryValueExpression)getSym(1), notBetween, (QueryValueExpression)getSym(3), (QueryValueExpression)getSym(5))); 
        }
        break;   
        /*
         *  Rule 211:  <liketest> ::= <expression> NOT LIKE <expression> <opt_escape>
         */
        case 211: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            boolean notLike = true;
				setSym1(m_factory.createPredicateLike((QueryValueExpression)getSym(1), notLike, (QueryValueExpression)getSym(4), (QueryValueExpression)getSym(5))); 
        }
        break;   
        /*
         *  Rule 212:  <liketest> ::= <expression> LIKE <expression> <opt_escape>
         */
        case 212: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            boolean notLike = false;
				setSym1(m_factory.createPredicateLike((QueryValueExpression)getSym(1), notLike, (QueryValueExpression)getSym(3), (QueryValueExpression)getSym(4))); 
        }
        break;   
        /*
         *  Rule 213:  <merge statement> ::= MERGE INTO <merge target table> USING <merge source table> ON <merge on condition> <merge operation specification list>
         */
        case 213: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1( m_factory.createMergeStatement((MergeTargetTable) getSym(3), (MergeSourceTable) getSym(5), (MergeOnCondition) getSym(7), getList(8)) ); 
        }
        break;  
 
        /*
         *  Rule 214:  <merge target table> ::= <target_table> <opt_as_alias>
         */
        case 214: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1( m_factory.createMergeTargetTable((TableInDatabase) getSym(1), getString(2)) ); 
        }
        break;  
 
        /*
         *  Rule 215:  <merge source table> ::= <table_ref>
         */
        case 215: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1( m_factory.createMergeSourceTable((TableReference) getSym(1)) ); 
        }
        break;  
 
        /*
         *  Rule 216:  <merge on condition> ::= <condition>
         */
        case 216: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1( m_factory.createMergeOnCondition((QuerySearchCondition) getSym(1)) ); 
        }
        break;  
 
        /*
         *  Rule 217:  <merge operation specification list> ::= <merge when clause>
         */
        case 217: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1( m_factory.createMergeOperatationSpecificationList(null, (MergeOperationSpecification) getSym(1)) ); 
        }
        break;  
 
        /*
         *  Rule 218:  <merge operation specification list> ::= <merge operation specification list> <merge when clause>
         */
        case 218: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1( m_factory.createMergeOperatationSpecificationList(getList(1), (MergeOperationSpecification) getSym(2)) ); 
        }
        break;  
 
        /*
         *  Rule 221:  <merge when matched clause> ::= WHEN MATCHED THEN <merge update specification>
         */
        case 221: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(getSym(4)); 
        }
        break;  
 
        /*
         *  Rule 222:  <merge when not matched clause> ::= WHEN NOT MATCHED THEN <merge insert specification>
         */
        case 222: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(getSym(5)); 
        }
        break;  
 
        /*
         *  Rule 223:  <merge update specification> ::= UPDATE SET <update_assignment_clause>
         */
        case 223: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1( m_factory.createMergeUpdateSpecification(getList(3)) ); 
        }
        break;  
 
        /*
         *  Rule 224:  <merge insert specification> ::= INSERT <opt_target_column_list> VALUES <insert_row>
         */
        case 224: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1( m_factory.createMergeInsertSpecification(getList(2), (ValuesRow)getSym(4)) );  
        }
        break;  
 
        /*
         *  Rule 225:  <nulltest> ::= <expression> IS NOT NULL
         */
        case 225: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            boolean notNull = true;
				setSym1(m_factory.createPredicateNull((QueryValueExpression)getSym(1), notNull)); 
        }
        break;   
        /*
         *  Rule 226:  <nulltest> ::= <expression> IS NULL
         */
        case 226: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            boolean notNull = false;
				setSym1(m_factory.createPredicateNull((QueryValueExpression)getSym(1), notNull)); 
        }
        break;   
        /*
         *  Rule 227:  <null_specification> ::= NULL
         */
        case 227: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createNullExpression()); 
        }
        break;  
 
        /*
         *  Rule 228:  <opt_all_distinct> ::= $Empty
         */
        case 228: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1((String)null); 
        }
        break;  
 
        /*
         *  Rule 229:  <opt_all_distinct> ::= ALL
         */
        case 229: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(SQLQueryParserFactory.ALL); 
        }
        break;  
 
        /*
         *  Rule 230:  <opt_all_distinct> ::= DISTINCT
         */
        case 230: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(SQLQueryParserFactory.DISTINCT); 
        }
        break;  
 
        /*
         *  Rule 234:  <opt_as_alias> ::= $Empty
         */
        case 234: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(null); 
        }
        break;  
 
        /*
         *  Rule 235:  <opt_as_target_table> ::= <opt_as> <table>
         */
        case 235: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createTableCorrelation(getString(2))); 
        }
        break;  
 
        /*
         *  Rule 236:  <opt_as_target_table> ::= $Empty
         */
        case 236: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(null); 
        }
        break;  
 
        /*
         *  Rule 237:  <opt_asc_desc> ::= ASC
         */
        case 237: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setInt1(SQLQueryParserFactory.ORDERING_SPEC_TYPE_ASC); 
        }
        break;  
 
        /*
         *  Rule 238:  <opt_asc_desc> ::= DESC
         */
        case 238: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setInt1(SQLQueryParserFactory.ORDERING_SPEC_TYPE_DESC); 
        }
        break;  
 
        /*
         *  Rule 239:  <opt_asc_desc> ::= $Empty
         */
        case 239: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setInt1(SQLQueryParserFactory.ORDERING_SPEC_TYPE_NONE); 
        }
        break;  
 
        /*
         *  Rule 240:  <opt_case_else> ::= ELSE <expression>
         */
        case 240: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createCaseElse((QueryValueExpression)getSym(2))); 
        }
        break;  
 
        /*
         *  Rule 241:  <opt_case_else> ::= $Empty
         */
        case 241: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(null); 
        }
        break;  
 
        /*
         *  Rule 242:  <opt_column_name_list> ::= _LPAREN <column_name_list> _RPAREN
         */
        case 242: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(getList(2)); 
        }
        break;  
 
        /*
         *  Rule 243:  <opt_column_name_list> ::= $Empty
         */
        case 243: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(null); 
        }
        break;  
 
        /*
         *  Rule 244:  <opt_default_clause> ::= DEFAULT <default_option>
         */
        case 244: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(getSym(2)); 
        }
        break;  
 
        /*
         *  Rule 245:  <opt_default_clause> ::= $Empty
         */
        case 245: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(null); 
        }
        break;  
 
        /*
         *  Rule 246:  <opt_escape> ::= ESCAPE _STRING
         */
        case 246: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSimpleExpression(getTokenName(2))); 
        }
        break;  
 
        /*
         *  Rule 247:  <opt_escape> ::= $Empty
         */
        case 247: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(null); 
        }
        break;  
 
        /*
         *  Rule 249:  <opt_expression_commalist> ::= $Empty
         */
        case 249: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(null); 
        }
        break;  
 
        /*
         *  Rule 251:  <opt_fetch_first_clause> ::= $Empty
         */
        case 251: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setInt1(0); 
        }
        break;  
 
        /*
         *  Rule 252:  <opt_group_by_clause> ::= GROUP BY <grouping_spec_list>
         */
        case 252: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(getList(3)); 
        }
        break;  
 
        /*
         *  Rule 253:  <opt_group_by_clause> ::= GROUP BY <super_groups_element_list> WITH CUBE
         */
        case 253: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1( m_factory.createGroupingSpecificationList(null, m_factory.createSuperGroups(getList(3),SQLQueryParserFactory.SUPERGROUP_TYPE_CUBE)) ); 
        }
        break;  
 
        /*
         *  Rule 254:  <opt_group_by_clause> ::= GROUP BY <super_groups_element_list> WITH ROLLUP
         */
        case 254: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1( m_factory.createGroupingSpecificationList(null, m_factory.createSuperGroups(getList(3),SQLQueryParserFactory.SUPERGROUP_TYPE_ROLLUP)) ); 
        }
        break;  
 
        /*
         *  Rule 255:  <opt_group_by_clause> ::= $Empty
         */
        case 255: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1((List)null); 
        }
        break;  
 
        /*
         *  Rule 256:  <opt_having_clause> ::= HAVING <condition>
         */
        case 256: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(getSym(2)); 
        }
        break;  
 
        /*
         *  Rule 257:  <opt_having_clause> ::= $Empty
         */
        case 257: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(null); 
        }
        break;  
 
        /*
         *  Rule 258:  <opt_join_type> ::= INNER
         */
        case 258: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setInt1(SQLQueryParserFactory.JOIN_EXPLICIT_INNER); 
        }
        break;  
 
        /*
         *  Rule 259:  <opt_join_type> ::= LEFT <opt_join_type_outer>
         */
        case 259: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setInt1(SQLQueryParserFactory.JOIN_LEFT_OUTER); 
        }
        break;  
 
        /*
         *  Rule 260:  <opt_join_type> ::= RIGHT <opt_join_type_outer>
         */
        case 260: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setInt1(SQLQueryParserFactory.JOIN_RIGHT_OUTER); 
        }
        break;  
 
        /*
         *  Rule 261:  <opt_join_type> ::= FULL <opt_join_type_outer>
         */
        case 261: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setInt1(SQLQueryParserFactory.JOIN_FULL_OUTER); 
        }
        break;  
 
        /*
         *  Rule 262:  <opt_join_type> ::= $Empty
         */
        case 262: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setInt1(SQLQueryParserFactory.JOIN_DEFAULT_INNER); 
        }
        break;  
 
        /*
         *  Rule 265:  <opt_null_order> ::= NULLS FIRST
         */
        case 265: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setInt1(SQLQueryParserFactory.NULL_ORDERING_TYPE_NULLS_FIRST); 
        }
        break;  
 
        /*
         *  Rule 266:  <opt_null_order> ::= NULLS LAST
         */
        case 266: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setInt1(SQLQueryParserFactory.NULL_ORDERING_TYPE_NULLS_LAST); 
        }
        break;  
 
        /*
         *  Rule 267:  <opt_null_order> ::= $Empty
         */
        case 267: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setInt1(SQLQueryParserFactory.NULL_ORDERING_TYPE_NONE); 
        }
        break;  
 
        /*
         *  Rule 268:  <opt_order_by_clause> ::= ORDER BY <ordering_spec_commalist>
         */
        case 268: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(getList(3)); 
        }
        break;  
 
        /*
         *  Rule 269:  <opt_order_by_clause> ::= $Empty
         */
        case 269: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1((List)null); 
        }
        break;  
 
        /*
         *  Rule 270:  <opt_schema_dot> ::= <schema> _DOT
         */
        case 270: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(getString(1)); 
        }
        break;  
 
        /*
         *  Rule 271:  <opt_schema_dot> ::= $Empty
         */
        case 271: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(null); 
        }
        break;  
 
        /*
         *  Rule 273:  <opt_table_correlation> ::= $Empty
         */
        case 273: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(null); 
        }
        break;  
 
        /*
         *  Rule 274:  <opt_target_column_list> ::= _LPAREN <target_column_list> _RPAREN
         */
        case 274: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(getList(2)); 
        }
        break;  
 
        /*
         *  Rule 275:  <opt_target_column_list> ::= $Empty
         */
        case 275: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(null); 
        }
        break;  
 
        /*
         *  Rule 276:  <opt_updatability_clause> ::= <updatability_expression>
         */
        case 276: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(getSym(1)); 
        }
        break;  
 
        /*
         *  Rule 277:  <opt_updatability_clause> ::= $Empty
         */
        case 277: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(null); 
        }
        break;  
 
        /*
         *  Rule 278:  <opt_fetch_first_row_count> ::= <unsigned_integer>
         */
        case 278: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            
        String rowCountStr = getTokenName(1);
        int rowCount = 1;
        try {
            rowCount = Integer.parseInt(rowCountStr);
        }
        catch(NumberFormatException e) {
            // ignore
        }
        setInt1(rowCount);
        
        }
        break;   
        /*
         *  Rule 279:  <opt_fetch_first_row_count> ::= $Empty
         */
        case 279: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setInt1(1); 
        }
        break;  
 
        /*
         *  Rule 280:  <opt_where_clause> ::= WHERE <condition>
         */
        case 280: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1_keepSpan(getSym(2)); 
        }
        break;  
 
        /*
         *  Rule 281:  <opt_where_clause> ::= $Empty
         */
        case 281: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(null);  
        }
        break;  
 
        /*
         *  Rule 282:  <ordering_spec> ::= <expression> <opt_asc_desc> <opt_null_order>
         */
        case 282: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createOrderByExpression((QueryValueExpression)getSym(1), getInt(2), getInt(3))); 
        }
        break;  
 
        /*
         *  Rule 283:  <ordering_spec_commalist> ::= <ordering_spec>
         */
        case 283: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createOrderByClause(null,(OrderBySpecification) getSym(1))); 
        }
        break;  
 
        /*
         *  Rule 284:  <ordering_spec_commalist> ::= <ordering_spec_commalist> _COMMA <ordering_spec>
         */
        case 284: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createOrderByClause(getList(1),(OrderBySpecification) getSym(3))); 
        }
        break;  
 
        /*
         *  Rule 285:  <parameter> ::= _HOSTVAR
         */
        case 285: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createVariableExpression(getTokenName(1))); 
        }
        break;  
 
        /*
         *  Rule 286:  <parameter> ::= _PARAM_MARKER
         */
        case 286: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createVariableExpression(null)); 
        }
        break;  
 
        /*
         *  Rule 287:  <project> ::= <expression> <opt_as_alias>
         */
        case 287: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createResultColumn((QueryValueExpression) getSym(1), getString(2))); 
        }
        break;  
 
        /*
         *  Rule 288:  <project> ::= _STAR
         */
        case 288: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(null); 
        }
        break;  
 
        /*
         *  Rule 289:  <project> ::= <table> _DOT _STAR
         */
        case 289: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createResultTableAllColumns(getString(1))); 
        }
        break;  
 
        /*
         *  Rule 290:  <project> ::= <schema> _DOT <table> _DOT _STAR
         */
        case 290: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createResultTableAllColumns(getString(3), getString(1))); 
        }
        break;  
 
        /*
         *  Rule 291:  <query_combined> ::= <query_exp> <query_combined_operator> <query_term> <opt_order_by_clause> <opt_fetch_first_clause>
         */
        case 291: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createQueryCombined((QueryExpressionBody) getSym(1),getInt(2),(QueryExpressionBody) getSym(3), getList(4), getInt(5)));  
        }
        break;  
 
        /*
         *  Rule 292:  <query_combined_operator> ::= UNION
         */
        case 292: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setInt1(SQLQueryParserFactory.QUERY_COMBINED_UNION); 
        }
        break;  
 
        /*
         *  Rule 293:  <query_combined_operator> ::= UNION ALL
         */
        case 293: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setInt1(SQLQueryParserFactory.QUERY_COMBINED_UNION_ALL); 
        }
        break;  
 
        /*
         *  Rule 294:  <query_combined_operator> ::= INTERSECT
         */
        case 294: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setInt1(SQLQueryParserFactory.QUERY_COMBINED_INTERSECT); 
        }
        break;  
 
        /*
         *  Rule 295:  <query_combined_operator> ::= INTERSECT ALL
         */
        case 295: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setInt1(SQLQueryParserFactory.QUERY_COMBINED_INTERSECT_ALL); 
        }
        break;  
 
        /*
         *  Rule 296:  <query_combined_operator> ::= EXCEPT
         */
        case 296: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setInt1(SQLQueryParserFactory.QUERY_COMBINED_EXCEPT); 
        }
        break;  
 
        /*
         *  Rule 297:  <query_combined_operator> ::= EXCEPT ALL
         */
        case 297: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setInt1(SQLQueryParserFactory.QUERY_COMBINED_EXCEPT_ALL); 
        }
        break;  
 
        /*
         *  Rule 300:  <query_exp_root> ::= <with_clause> <query_exp>
         */
        case 300: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1( m_factory.createQueryExpressionRoot((QueryExpressionBody)getSym(2), getList(1)) ); 
        }
        break;  
 
        /*
         *  Rule 301:  <query_select> ::= SELECT <opt_all_distinct> <selection> FROM <table_ref_commalist> <opt_where_clause> <opt_group_by_clause> <opt_having_clause> <opt_order_by_clause> <opt_fetch_first_clause>
         */
        case 301: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createQuerySelect(getString(2),getList(3),getList(5),(QuerySearchCondition)getSym(6),getList(7),(QuerySearchCondition)getSym(8), getList(9), getInt(10))); 
        }
        break;  
 
        /*
         *  Rule 302:  <query_stmt> ::= <query_exp_root> <opt_order_by_clause> <opt_updatability_clause>
         */
        case 302: 
        {
            setSym1(m_factory.createSelectStatement((QueryExpressionRoot)getSym(1), getList(2), (UpdatabilityExpression) getSym(3))); 
        }
        break;  
 
        /*
         *  Rule 305:  <query_term> ::= _LPAREN <query_exp> _RPAREN <opt_order_by_clause> <opt_fetch_first_clause>
         */
        case 305: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1( m_factory.createQueryNested((QueryExpressionBody)getSym(2), getList(4), getInt(5)));  
        }
        break;  
 
        /*
         *  Rule 306:  <query_values> ::= VALUES <values_row_commalist> <opt_order_by_clause> <opt_fetch_first_clause>
         */
        case 306: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createQueryValues(getList(2), getList(3), getInt(4))); 
        }
        break;  
 
        /*
         *  Rule 307:  <relop> ::= _EQ
         */
        case 307: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setInt1(SQLQueryParserFactory.COMPARISON_OPERATOR_EQ); 
        }
        break;  
 
        /*
         *  Rule 308:  <relop> ::= _LT
         */
        case 308: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setInt1(SQLQueryParserFactory.COMPARISON_OPERATOR_LT); 
        }
        break;  
 
        /*
         *  Rule 309:  <relop> ::= _LE
         */
        case 309: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setInt1(SQLQueryParserFactory.COMPARISON_OPERATOR_LE); 
        }
        break;  
 
        /*
         *  Rule 310:  <relop> ::= _NE
         */
        case 310: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setInt1(SQLQueryParserFactory.COMPARISON_OPERATOR_NE); 
        }
        break;  
 
        /*
         *  Rule 311:  <relop> ::= _GT
         */
        case 311: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setInt1(SQLQueryParserFactory.COMPARISON_OPERATOR_GT); 
        }
        break;  
 
        /*
         *  Rule 312:  <relop> ::= _GE
         */
        case 312: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setInt1(SQLQueryParserFactory.COMPARISON_OPERATOR_GE); 
        }
        break;  
 
        /*
         *  Rule 315:  <row_comparison> ::= <value_expr_row> <relop> <value_expr_row>
         */
        case 315: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createPredicateBasic((QueryValueExpression)getSym(1),getInt(2),(QueryValueExpression)getSym(3))); 
        }
        break;  
 
        /*
         *  Rule 318:  <scalar_comparison> ::= <expression> <relop> <expression>
         */
        case 318: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createPredicateBasic((QueryValueExpression)getSym(1),getInt(2),(QueryValueExpression)getSym(3))); 
        }
        break;  
 
        /*
         *  Rule 319:  <schema> ::= <identifier>
         */
        case 319: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(getString(1)); 
        }
        break;  
 
        /*
         *  Rule 320:  <schema_qualified_name> ::= <identifier>
         */
        case 320: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(getString(1)); 
        }
        break;  
 
        /*
         *  Rule 321:  <schema_qualified_name> ::= <schema> _DOT <identifier>
         */
        case 321: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(getString(1)+"."+getString(3)); 
        }
        break;  
 
        /*
         *  Rule 322:  <selection> ::= <project>
         */
        case 322: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSelectClause(null,(QueryResultSpecification)getSym(1))); 
        }
        break;  
 
        /*
         *  Rule 323:  <selection> ::= <selection> _COMMA <project>
         */
        case 323: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSelectClause(getList(1),(QueryResultSpecification)getSym(3))); 
        }
        break;  
 
        /*
         *  Rule 332:  <special_register> ::= CURRENT_DATE
         */
        case 332: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSpecialRegisterExpression(SQLQueryParserFactory.SPECIAL_REGISTER_CURRENT_DATE)); 
        }
        break;  
 
        /*
         *  Rule 333:  <special_register> ::= CURRENT_TIME
         */
        case 333: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSpecialRegisterExpression(SQLQueryParserFactory.SPECIAL_REGISTER_CURRENT_TIME)); 
        }
        break;  
 
        /*
         *  Rule 334:  <special_register> ::= CURRENT_TIMESTAMP
         */
        case 334: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSpecialRegisterExpression(SQLQueryParserFactory.SPECIAL_REGISTER_CURRENT_TIMESTAMP)); 
        }
        break;  
 
        /*
         *  Rule 335:  <special_register> ::= CURRENT_TIMESTAMP _LPAREN <timestamp precision> _RPAREN
         */
        case 335: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSpecialRegisterExpression(SQLQueryParserFactory.SPECIAL_REGISTER_CURRENT_TIMESTAMP)); 
        }
        break;  
 
        /*
         *  Rule 336:  <special_register> ::= LOCALTIME
         */
        case 336: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSpecialRegisterExpression(SQLQueryParserFactory.SPECIAL_REGISTER_LOCALTIME)); 
        }
        break;  
 
        /*
         *  Rule 337:  <special_register> ::= LOCALTIME _LPAREN <time precision> _RPAREN
         */
        case 337: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSpecialRegisterExpression(SQLQueryParserFactory.SPECIAL_REGISTER_LOCALTIME)); 
        }
        break;  
 
        /*
         *  Rule 338:  <special_register> ::= LOCALTIMESTAMP
         */
        case 338: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSpecialRegisterExpression(SQLQueryParserFactory.SPECIAL_REGISTER_LOCALTIMESTAMP)); 
        }
        break;  
 
        /*
         *  Rule 339:  <special_register> ::= LOCALTIMESTAMP _LPAREN <timestamp precision> _RPAREN
         */
        case 339: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSpecialRegisterExpression(SQLQueryParserFactory.SPECIAL_REGISTER_LOCALTIMESTAMP)); 
        }
        break;  
 
        /*
         *  Rule 340:  <special_register> ::= CURRENT_DEFAULT_TRANSFORM_GROUP
         */
        case 340: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSpecialRegisterExpression(SQLQueryParserFactory.SPECIAL_REGISTER_CURRENT_DEFAULT_TRANSFORM_GROUP)); 
        }
        break;  
 
        /*
         *  Rule 341:  <special_register> ::= CURRENT_PATH
         */
        case 341: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSpecialRegisterExpression(SQLQueryParserFactory.SPECIAL_REGISTER_CURRENT_PATH)); 
        }
        break;  
 
        /*
         *  Rule 342:  <special_register> ::= CURRENT_ROLE
         */
        case 342: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSpecialRegisterExpression(SQLQueryParserFactory.SPECIAL_REGISTER_CURRENT_ROLE)); 
        }
        break;  
 
        /*
         *  Rule 343:  <special_register> ::= CURRENT_TRANSFORM_GROUP_FOR_TYPE <datatype_path-resolved_user-defined_type_name>
         */
        case 343: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSpecialRegisterExpression(SQLQueryParserFactory.SPECIAL_REGISTER_CURRENT_TRANSFORM_GROUP_FOR_TYPE, (UserDefinedType) getSym(2))); 
        }
        break;  
 
        /*
         *  Rule 344:  <special_register> ::= CURRENT_USER
         */
        case 344: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSpecialRegisterExpression(SQLQueryParserFactory.SPECIAL_REGISTER_CURRENT_USER)); 
        }
        break;  
 
        /*
         *  Rule 345:  <special_register> ::= SESSION_USER
         */
        case 345: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSpecialRegisterExpression(SQLQueryParserFactory.SPECIAL_REGISTER_SESSION_USER)); 
        }
        break;  
 
        /*
         *  Rule 346:  <special_register> ::= SYSTEM_USER
         */
        case 346: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSpecialRegisterExpression(SQLQueryParserFactory.SPECIAL_REGISTER_SYSTEM_USER)); 
        }
        break;  
 
        /*
         *  Rule 347:  <special_register> ::= USER
         */
        case 347: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSpecialRegisterExpression(SQLQueryParserFactory.SPECIAL_REGISTER_USER)); 
        }
        break;  
 
        /*
         *  Rule 348:  <special_register> ::= VALUE
         */
        case 348: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSpecialRegisterExpression(SQLQueryParserFactory.SPECIAL_REGISTER_VALUE)); 
        }
        break;  
 
        /*
         *  Rule 349:  <subquery> ::= _LPAREN <query_exp_root> _RPAREN
         */
        case 349: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(getSym(2)); 
        }
        break;  
 
        /*
         *  Rule 350:  <super_groups> ::= CUBE _LPAREN <super_groups_element_list> _RPAREN
         */
        case 350: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1( m_factory.createSuperGroups(getList(3), SQLQueryParserFactory.SUPERGROUP_TYPE_CUBE) ); 
        }
        break;  
 
        /*
         *  Rule 351:  <super_groups> ::= ROLLUP _LPAREN <super_groups_element_list> _RPAREN
         */
        case 351: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1( m_factory.createSuperGroups(getList(3), SQLQueryParserFactory.SUPERGROUP_TYPE_ROLLUP) ); 
        }
        break;  
 
        /*
         *  Rule 352:  <super_groups> ::= _LPAREN _RPAREN
         */
        case 352: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1( m_factory.createSuperGroups(null, SQLQueryParserFactory.SUPERGROUP_TYPE_GRANDTOTAL) ); 
        }
        break;  
 
        /*
         *  Rule 353:  <super_groups_element_exp> ::= <grouping_exp>
         */
        case 353: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1( m_factory.createSuperGroupsElementExpression((GroupingExpression) getSym(1)) ); 
        }
        break;  
 
        /*
         *  Rule 354:  <super_groups_element_exp_list> ::= <super_groups_element_exp>
         */
        case 354: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSuperGroupsElementExprList(null,(SuperGroupElementExpression) getSym(1)));  
        }
        break;  
 
        /*
         *  Rule 355:  <super_groups_element_exp_list> ::= <super_groups_element_exp_list> _COMMA <super_groups_element_exp>
         */
        case 355: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSuperGroupsElementExprList(getList(1),(SuperGroupElementExpression) getSym(3)));  
        }
        break;  
 
        /*
         *  Rule 356:  <super_groups_element> ::= _LPAREN <super_groups_element_exp_list> _RPAREN
         */
        case 356: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1( m_factory.createSuperGroupsElementSublist(getList(2)) );  
        }
        break;  
 
        /*
         *  Rule 358:  <super_groups_element_list> ::= <super_groups_element>
         */
        case 358: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSuperGroupsElementList(null,(SuperGroupElement) getSym(1)));  
        }
        break;  
 
        /*
         *  Rule 359:  <super_groups_element_list> ::= <super_groups_element_list> _COMMA <super_groups_element>
         */
        case 359: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSuperGroupsElementList(getList(1),(SuperGroupElement) getSym(3)));  
        }
        break;  
 
        /*
         *  Rule 360:  <table> ::= <identifier>
         */
        case 360: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(getString(1)); 
        }
        break;  
 
        /*
         *  Rule 361:  <table_correlation> ::= <as_alias> <opt_column_name_list>
         */
        case 361: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createTableCorrelation(getString(1), getList(2))); 
        }
        break;  
 
        /*
         *  Rule 362:  <table_func> ::= TABLE _LPAREN <opt_schema_dot> <identifier> _LPAREN <opt_expression_commalist> _RPAREN _RPAREN <table_correlation>
         */
        case 362: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createTableFunction(getString(4), getList(6), getString(3), (TableCorrelation)getSym(9)) ); 
        }
        break;  
 
        /*
         *  Rule 363:  <table_join> ::= <table_ref> <opt_join_type> JOIN <table_ref> ON <condition>
         */
        case 363: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createJoinedTable((TableReference)getSym(1), getInt(2), (TableReference)getSym(4), (QuerySearchCondition)getSym(6))); 
        }
        break;  
 
        /*
         *  Rule 364:  <table_nested> ::= _LPAREN <table_ref> _RPAREN
         */
        case 364: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createNestedTable((TableReference)getSym(2))); 
        }
        break;  
 
        /*
         *  Rule 365:  <table_qualified> ::= <schema> _DOT <table> <opt_table_correlation>
         */
        case 365: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createReferenceTable(getString(1), getString(3), (TableCorrelation)getSym(4))); 
        }
        break;  
 
        /*
         *  Rule 366:  <table_query> ::= _LPAREN <query_exp> _RPAREN <table_correlation>
         */
        case 366: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createTableExpressionQuery((QueryExpressionBody)getSym(2), (TableCorrelation)getSym(4))); 
        }
        break;  
 
        /*
         *  Rule 367:  <table_query> ::= TABLE _LPAREN <query_exp> _RPAREN <table_correlation>
         */
        case 367: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createTableExpressionQuery((QueryExpressionBody)getSym(3), (TableCorrelation)getSym(5))); 
        }
        break;  
 
        /*
         *  Rule 374:  <table_ref_commalist> ::= <table_ref>
         */
        case 374: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createFromClause((List)null,(TableReference)getSym(1))); 
        }
        break;  
 
        /*
         *  Rule 375:  <table_ref_commalist> ::= <table_ref_commalist> _COMMA <table_ref>
         */
        case 375: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createFromClause(getList(1),(TableReference)getSym(3))); 
        }
        break;  
 
        /*
         *  Rule 376:  <table_simple> ::= <table> <opt_table_correlation>
         */
        case 376: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createReferenceTable(null, getString(1), (TableCorrelation)getSym(2))); 
        }
        break;  
 
        /*
         *  Rule 377:  <target_column_list> ::= <column_ref>
         */
        case 377: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createColumnList(null,(ValueExpressionColumn) getSym(1)));  
        }
        break;  
 
        /*
         *  Rule 378:  <target_column_list> ::= <target_column_list> _COMMA <column_ref>
         */
        case 378: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createColumnList(getList(1),(ValueExpressionColumn) getSym(3)));  
        }
        break;  
 
        /*
         *  Rule 379:  <target_table> ::= <table>
         */
        case 379: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSimpleTable(null, getString(1))); 
        }
        break;  
 
        /*
         *  Rule 380:  <target_table> ::= <schema> _DOT <table>
         */
        case 380: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSimpleTable(getString(1), getString(3))); 
        }
        break;  
 
        /*
         *  Rule 381:  <time precision> ::= UNSIGNED_INTEGER
         */
        case 381: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(getTokenName(1)); 
        }
        break;  
 
        /*
         *  Rule 382:  <timestamp precision> ::= UNSIGNED_INTEGER
         */
        case 382: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(getTokenName(1)); 
        }
        break;  
 
        /*
         *  Rule 383:  <updatability_expression> ::= FOR READ ONLY
         */
        case 383: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createUpdatabilityExpression(SQLQueryParserFactory.UPDATABILITY_TYPE_FOR_READ_ONLY , null)); 
        }
        break;  
 
        /*
         *  Rule 384:  <updatability_expression> ::= FOR UPDATE
         */
        case 384: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createUpdatabilityExpression(SQLQueryParserFactory.UPDATABILITY_TYPE_FOR_UPDATE , null)); 
        }
        break;  
 
        /*
         *  Rule 385:  <updatability_expression> ::= FOR UPDATE OF <column_name_list>
         */
        case 385: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createUpdatabilityExpression(SQLQueryParserFactory.UPDATABILITY_TYPE_FOR_UPDATE , getList(4))); 
        }
        break;  
 
        /*
         *  Rule 387:  <update_assignment_expression> ::= <column_ref> _EQ <expression>
         */
        case 387: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createUpdateAssignmentExpression((ValueExpressionColumn)getSym(1),(QueryValueExpression)getSym(3))); 
        }
        break;  
 
        /*
         *  Rule 388:  <update_assignment_expression> ::= _LPAREN <target_column_list> _RPAREN _EQ _LPAREN <query_exp> _RPAREN
         */
        case 388: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createUpdateAssignmentExpression(getList(2),(QueryExpressionBody)getSym(6))); 
        }
        break;  
 
        /*
         *  Rule 389:  <update_assignment_expression> ::= _LPAREN <target_column_list> _RPAREN _EQ _LPAREN <expression_commalist> _RPAREN
         */
        case 389: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createUpdateAssignmentExpression(getList(2),getList(6))); 
        }
        break;  
 
        /*
         *  Rule 390:  <update_assignment_expression_commalist> ::= <update_assignment_expression>
         */
        case 390: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createUpdateAssignmentClause(null,(UpdateAssignmentExpression)getSym(1))); 
        }
        break;  
 
        /*
         *  Rule 391:  <update_assignment_expression_commalist> ::= <update_assignment_expression_commalist> _COMMA <update_assignment_expression>
         */
        case 391: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createUpdateAssignmentClause(getList(1),(UpdateAssignmentExpression)getSym(3))); 
        }
        break;  
 
        /*
         *  Rule 392:  <update_stmt> ::= UPDATE <target_table> <opt_as_target_table> SET <update_assignment_clause> <opt_where_clause>
         */
        case 392: 
        {
            setSym1(m_factory.createUpdateStatement((TableInDatabase)getSym(2), (TableCorrelation)getSym(3), getList(5), (QuerySearchCondition)getSym(6))); 
        }
        break;  
 
        /*
         *  Rule 393:  <value_expr_row> ::= _LPAREN <expression_commalist_multiple_elements> _RPAREN
         */
        case 393: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createValueExpressionRow(getList(2))); 
        }
        break;  
 
        /*
         *  Rule 394:  <values_row> ::= _LPAREN <expression_commalist> _RPAREN
         */
        case 394: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createValuesRow(getList(2))); 
        }
        break;  
 
        /*
         *  Rule 395:  <values_row> ::= <expression>
         */
        case 395: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createValuesRow((QueryValueExpression)getSym(1))); 
        }
        break;  
 
        /*
         *  Rule 396:  <values_row_commalist> ::= <values_row>
         */
        case 396: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createValuesRowList(null, (ValuesRow) getSym(1))); 
        }
        break;  
 
        /*
         *  Rule 397:  <values_row_commalist> ::= <values_row_commalist> _COMMA <values_row>
         */
        case 397: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createValuesRowList(getList(1), (ValuesRow) getSym(3))); 
        }
        break;  
 
        /*
         *  Rule 398:  <with_clause> ::= WITH <with_table_spec_list>
         */
        case 398: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(getList(2)); 
        }
        break;  
 
        /*
         *  Rule 399:  <with_clause> ::= $Empty
         */
        case 399: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(null); 
        }
        break;  
 
        /*
         *  Rule 400:  <with_table_spec_list> ::= <with_table_spec>
         */
        case 400: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createWithTableSpecificationList(null,(WithTableSpecification) getSym(1)));  
        }
        break;  
 
        /*
         *  Rule 401:  <with_table_spec_list> ::= <with_table_spec_list> _COMMA <with_table_spec>
         */
        case 401: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createWithTableSpecificationList(getList(1),(WithTableSpecification) getSym(3)));  
        }
        break;  
 
        /*
         *  Rule 402:  <with_table_spec> ::= <table> <opt_column_name_list> AS _LPAREN <query_exp> _RPAREN
         */
        case 402: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createWithTableSpecification(getString(1),getList(2),(QueryExpressionBody) getSym(5)));  
        }
        break;  


        default:
            break;

    }
    return;
}

}

