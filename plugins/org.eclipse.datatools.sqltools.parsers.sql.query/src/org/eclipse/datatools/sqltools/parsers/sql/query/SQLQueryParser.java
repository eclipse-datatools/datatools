/*
* Copyright (c) 2000, 2007 IBM Corporation and others.
* All rights reserved. This program and the accompanying materials 
* are made available under the terms of the Eclipse Public License v1.0
* which is available at
* http://www.eclipse.org/legal/epl-v10.html
*/

package org.eclipse.datatools.sqltools.parsers.sql.query;



import org.eclipse.datatools.modelbase.sql.query.*;
import org.eclipse.datatools.modelbase.sql.query.util.*;
import org.eclipse.datatools.modelbase.sql.datatypes.*;
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
         *  Rule 10:  <all_or_any_cond> ::= <expression> <relop> ANY <subquery>
         */
        case 10: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createPredicateQuantifiedValueSelect((QueryValueExpression)getSym(1),getInt(2),SQLQueryParserFactory.QUANTIFIER_ANY,(QueryExpressionRoot)getSym(4))); 
        }
        break;  
 
        /*
         *  Rule 11:  <all_or_any_cond> ::= <expression> <relop> SOME <subquery>
         */
        case 11: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createPredicateQuantifiedValueSelect((QueryValueExpression)getSym(1),getInt(2),SQLQueryParserFactory.QUANTIFIER_SOME,(QueryExpressionRoot)getSym(4))); 
        }
        break;  
 
        /*
         *  Rule 12:  <all_or_any_cond> ::= <expression> <relop> ALL <subquery>
         */
        case 12: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createPredicateQuantifiedValueSelect((QueryValueExpression)getSym(1),getInt(2),SQLQueryParserFactory.QUANTIFIER_ALL,(QueryExpressionRoot)getSym(4))); 
        }
        break;  
 
        /*
         *  Rule 13:  <all_or_any_cond> ::= _LPAREN <expression_commalist_multiple_elements> _RPAREN _EQ ANY <subquery>
         */
        case 13: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createPredicateQuantifiedRowSelect(getList(2),SQLQueryParserFactory.QUANTIFIER_ANY,(QueryExpressionRoot)getSym(6))); 
        }
        break;  
 
        /*
         *  Rule 14:  <all_or_any_cond> ::= _LPAREN <expression_commalist_multiple_elements> _RPAREN _EQ SOME <subquery>
         */
        case 14: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createPredicateQuantifiedRowSelect(getList(2),SQLQueryParserFactory.QUANTIFIER_SOME,(QueryExpressionRoot)getSym(6))); 
        }
        break;  
 
        /*
         *  Rule 15:  <as_alias> ::= <opt_as> <alias_name>
         */
        case 15: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(getString(2)); 
        }
        break;  
 
        /*
         *  Rule 17:  <boolean_expression> ::= <boolean_expression> OR <boolean_term>
         */
        case 17: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
           
				setSym1(m_factory.createCombinedCondition((QuerySearchCondition)getSym(1), (QuerySearchCondition)getSym(3), SQLQueryParserFactory.COMBINED_OPERATOR_OR)); 
        }
        break;   
        /*
         *  Rule 19:  <boolean_term> ::= <boolean_term> AND <boolean_factor>
         */
        case 19: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createCombinedCondition((QuerySearchCondition)getSym(1), (QuerySearchCondition)getSym(3), SQLQueryParserFactory.COMBINED_OPERATOR_AND)); 
        }
        break;  
 
        /*
         *  Rule 21:  <boolean_factor> ::= <boolean_primary> IS <boolean_values>
         */
        case 21: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            m_factory.negateCondition((QuerySearchCondition)getSym(1),!getBoolean(3)); 
        }
        break;  
 
        /*
         *  Rule 22:  <boolean_factor> ::= <boolean_primary> IS NOT <boolean_values>
         */
        case 22: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            m_factory.negateCondition((QuerySearchCondition)getSym(1),getBoolean(4)); 
        }
        break;  
 
        /*
         *  Rule 23:  <boolean_values> ::= TRUE
         */
        case 23: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(new Boolean(true)); 
        }
        break;  
 
        /*
         *  Rule 24:  <boolean_values> ::= FALSE
         */
        case 24: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(new Boolean(false)); 
        }
        break;  
 
        /*
         *  Rule 26:  <boolean_primary> ::= NOT <simplecomp>
         */
        case 26: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.negatePredicate((Predicate)getSym(2),true)); 
        }
        break;  
 
        /*
         *  Rule 27:  <boolean_primary> ::= _LPAREN <boolean_expression> _RPAREN
         */
        case 27: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createNestedCondition((QuerySearchCondition)getSym(2))); 
        }
        break;  
 
        /*
         *  Rule 28:  <boolean_primary> ::= NOT _LPAREN <boolean_expression> _RPAREN
         */
        case 28: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createNestedConditionNegated((QuerySearchCondition)getSym(3))); 
        }
        break;  
 
        /*
         *  Rule 29:  <case_expression> ::= CASE <case_search_when_list> <opt_case_else> END
         */
        case 29: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createCaseSearchExpression(getList(2), (ValueExpressionCaseElse)getSym(3))); 
        }
        break;  
 
        /*
         *  Rule 30:  <case_expression> ::= CASE <expression> <case_simple_when_list> <opt_case_else> END
         */
        case 30: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createCaseSimpleExpression((QueryValueExpression)getSym(2), getList(3), (ValueExpressionCaseElse)getSym(4))); 
        }
        break;  
 
        /*
         *  Rule 31:  <case_search_when> ::= WHEN <condition> THEN <expression>
         */
        case 31: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createCaseSearchContent((QuerySearchCondition)getSym(2), (QueryValueExpression)getSym(4))); 
        }
        break;  
 
        /*
         *  Rule 32:  <case_search_when_list> ::= <case_search_when>
         */
        case 32: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createCaseSearchList(null,(ValueExpressionCaseSearchContent)getSym(1))); 
        }
        break;  
 
        /*
         *  Rule 33:  <case_search_when_list> ::= <case_search_when_list> <case_search_when>
         */
        case 33: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createCaseSearchList(getList(1),(ValueExpressionCaseSearchContent)getSym(2))); 
        }
        break;  
 
        /*
         *  Rule 34:  <case_simple_when> ::= WHEN <expression> THEN <expression>
         */
        case 34: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createCaseSimpleContent((QueryValueExpression)getSym(2), (QueryValueExpression)getSym(4))); 
        }
        break;  
 
        /*
         *  Rule 35:  <case_simple_when_list> ::= <case_simple_when>
         */
        case 35: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createCaseSimpleList(null,(ValueExpressionCaseSimpleContent)getSym(1))); 
        }
        break;  
 
        /*
         *  Rule 36:  <case_simple_when_list> ::= <case_simple_when_list> <case_simple_when>
         */
        case 36: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createCaseSimpleList(getList(1),(ValueExpressionCaseSimpleContent)getSym(2))); 
        }
        break;  
 
        /*
         *  Rule 37:  <cast_expression> ::= CAST _LPAREN <cast_operand> AS <cast_target> _RPAREN
         */
        case 37: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createCastExpression((QueryValueExpression)getSym(3), (DataType)getSym(5))); 
        }
        break;  
 
        /*
         *  Rule 41:  <cast_target> ::= <domain_name>
         */
        case 41: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createUserDefinedTypeFromDomainName(getString(1))); 
        }
        break;  
 
        /*
         *  Rule 43:  <column_name> ::= <identifier>
         */
        case 43: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createColumnName(getString(1))); 
        }
        break;  
 
        /*
         *  Rule 44:  <column_name_list> ::= <column_name>
         */
        case 44: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createColumnNameList(null,(ColumnName)getSym(1))); 
        }
        break;  
 
        /*
         *  Rule 45:  <column_name_list> ::= <column_name_list> _COMMA <column_name>
         */
        case 45: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createColumnNameList(getList(1),(ColumnName)getSym(3))); 
        }
        break;  
 
        /*
         *  Rule 46:  <column_ref> ::= <column>
         */
        case 46: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createColumnExpression(getString(1),null)); 
        }
        break;  
 
        /*
         *  Rule 47:  <column_ref> ::= <opt_schema_dot> <table> _DOT <column>
         */
        case 47: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createColumnExpression(getString(4),getString(2),getString(1))); 
        }
        break;  
 
        /*
         *  Rule 49:  <constant> ::= _STRING
         */
        case 49: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSimpleExpression(getTokenName(1))); 
        }
        break;  
 
        /*
         *  Rule 50:  <constant> ::= G _STRING
         */
        case 50: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSimpleExpression( "G".concat(getTokenName(2)) ));  //$NON-NLS-1$
			   
        }
        break;   
        /*
         *  Rule 51:  <constant> ::= N _STRING
         */
        case 51: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSimpleExpression( "N".concat(getTokenName(2)) ));  //$NON-NLS-1$
			   
        }
        break;   
        /*
         *  Rule 52:  <constant> ::= HEX_STRING_LITERAL
         */
        case 52: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSimpleExpression( getTokenName(1) ));  //$NON-NLS-1$
           
        }
        break;   
        /*
         *  Rule 53:  <constant> ::= _INTNUMBER
         */
        case 53: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSimpleExpression(getTokenName(1))); 
        }
        break;  
 
        /*
         *  Rule 54:  <constant> ::= _BIGINTEGER
         */
        case 54: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSimpleExpression(getTokenName(1))); 
        }
        break;  
 
        /*
         *  Rule 55:  <constant> ::= _DECIMALNUMBER
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
         *  Rule 56:  <constant> ::= _REALNUMBER
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
         *  Rule 62:  <datatype_array_type> ::= <datatype> ARRAY
         */
        case 62: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeArray((DataType)getSym(1))); 
        }
        break;  
 
        /*
         *  Rule 63:  <datatype_array_type> ::= <datatype> ARRAY <left_bracket_or_trigraph> <unsigned_integer> <right_bracket_or_trigraph>
         */
        case 63: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeArray((DataType)getSym(1), getInt(4))); 
        }
        break;  
 
        /*
         *  Rule 64:  <datatype_multiset_type> ::= <datatype> MULTISET
         */
        case 64: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeMultiset((DataType)getSym(1))); 
        }
        break;  
 
        /*
         *  Rule 71:  <datatype_date> ::= DATE
         */
        case 71: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeDate()); 
        }
        break;  
 
        /*
         *  Rule 72:  <datatype_time> ::= TIME
         */
        case 72: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeTime( SQLQueryParserFactory.PRIMITIVE_TYPE_TIME, 0 )); 
        }
        break;  
 
        /*
         *  Rule 73:  <datatype_time> ::= TIMESTAMP
         */
        case 73: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeTime( SQLQueryParserFactory.PRIMITIVE_TYPE_TIMESTAMP, 0 )); 
        }
        break;  
 
        /*
         *  Rule 78:  <datatype_numerical_approximate> ::= FLOAT
         */
        case 78: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeNumericApproximate( SQLQueryParserFactory.PRIMITIVE_TYPE_FLOAT, 0) ); 
        }
        break;  
 
        /*
         *  Rule 79:  <datatype_numerical_approximate> ::= FLOAT _LPAREN _INTNUMBER _RPAREN
         */
        case 79: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeNumericApproximate( SQLQueryParserFactory.PRIMITIVE_TYPE_FLOAT, Integer.parseInt(getTokenName(3))) ); 
        }
        break;  
 
        /*
         *  Rule 80:  <datatype_numerical_approximate> ::= REAL
         */
        case 80: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeNumericApproximate( SQLQueryParserFactory.PRIMITIVE_TYPE_REAL, 0) ); 
        }
        break;  
 
        /*
         *  Rule 81:  <datatype_numerical_approximate> ::= DOUBLE
         */
        case 81: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeNumericApproximate( SQLQueryParserFactory.PRIMITIVE_TYPE_DOUBLE_PRECISION, 0) ); 
        }
        break;  
 
        /*
         *  Rule 82:  <datatype_numerical_approximate> ::= DOUBLE PRECISION
         */
        case 82: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeNumericApproximate( SQLQueryParserFactory.PRIMITIVE_TYPE_DOUBLE_PRECISION, 0) ); 
        }
        break;  
 
        /*
         *  Rule 83:  <datatype_numerical_fixed_precision> ::= NUMERIC
         */
        case 83: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeNumericFixedPrecision( SQLQueryParserFactory.PRIMITIVE_TYPE_NUMERIC, 0, 0) ); 
        }
        break;  
 
        /*
         *  Rule 84:  <datatype_numerical_fixed_precision> ::= DECIMAL
         */
        case 84: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeNumericFixedPrecision( SQLQueryParserFactory.PRIMITIVE_TYPE_DECIMAL, 0, 0) ); 
        }
        break;  
 
        /*
         *  Rule 85:  <datatype_numerical_fixed_precision> ::= DEC
         */
        case 85: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeNumericFixedPrecision( SQLQueryParserFactory.PRIMITIVE_TYPE_DECIMAL, 0, 0) ); 
        }
        break;  
 
        /*
         *  Rule 86:  <datatype_numerical_fixed_precision> ::= NUMERIC _LPAREN _INTNUMBER _RPAREN
         */
        case 86: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeNumericFixedPrecision( SQLQueryParserFactory.PRIMITIVE_TYPE_NUMERIC, Integer.parseInt(getTokenName(3)), 0) ); 
        }
        break;  
 
        /*
         *  Rule 87:  <datatype_numerical_fixed_precision> ::= DECIMAL _LPAREN _INTNUMBER _RPAREN
         */
        case 87: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeNumericFixedPrecision( SQLQueryParserFactory.PRIMITIVE_TYPE_DECIMAL, Integer.parseInt(getTokenName(3)), 0) ); 
        }
        break;  
 
        /*
         *  Rule 88:  <datatype_numerical_fixed_precision> ::= DEC _LPAREN _INTNUMBER _RPAREN
         */
        case 88: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeNumericFixedPrecision( SQLQueryParserFactory.PRIMITIVE_TYPE_DECIMAL, Integer.parseInt(getTokenName(3)), 0) ); 
        }
        break;  
 
        /*
         *  Rule 89:  <datatype_numerical_fixed_precision> ::= NUMERIC _LPAREN _INTNUMBER _COMMA _INTNUMBER _RPAREN
         */
        case 89: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeNumericFixedPrecision( SQLQueryParserFactory.PRIMITIVE_TYPE_NUMERIC, Integer.parseInt(getTokenName(3)), Integer.parseInt(getTokenName(5))) ); 
        }
        break;  
 
        /*
         *  Rule 90:  <datatype_numerical_fixed_precision> ::= DECIMAL _LPAREN _INTNUMBER _COMMA _INTNUMBER _RPAREN
         */
        case 90: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeNumericFixedPrecision( SQLQueryParserFactory.PRIMITIVE_TYPE_DECIMAL, Integer.parseInt(getTokenName(3)), Integer.parseInt(getTokenName(5))) ); 
        }
        break;  
 
        /*
         *  Rule 91:  <datatype_numerical_fixed_precision> ::= DEC _LPAREN _INTNUMBER _COMMA _INTNUMBER _RPAREN
         */
        case 91: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeNumericFixedPrecision( SQLQueryParserFactory.PRIMITIVE_TYPE_DECIMAL, Integer.parseInt(getTokenName(3)), Integer.parseInt(getTokenName(5))) ); 
        }
        break;  
 
        /*
         *  Rule 92:  <datatype_numerical_integer> ::= INTEGER
         */
        case 92: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeNumericInteger( SQLQueryParserFactory.PRIMITIVE_TYPE_INTEGER, 0) ); 
        }
        break;  
 
        /*
         *  Rule 93:  <datatype_numerical_integer> ::= INT
         */
        case 93: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeNumericInteger( SQLQueryParserFactory.PRIMITIVE_TYPE_INTEGER, 0) ); 
        }
        break;  
 
        /*
         *  Rule 94:  <datatype_numerical_integer> ::= SMALLINT
         */
        case 94: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeNumericInteger( SQLQueryParserFactory.PRIMITIVE_TYPE_SMALLINT, 0) ); 
        }
        break;  
 
        /*
         *  Rule 95:  <datatype_numerical_integer> ::= BIGINT
         */
        case 95: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeNumericInteger( SQLQueryParserFactory.PRIMITIVE_TYPE_BIGINT, 0) ); 
        }
        break;  
 
        /*
         *  Rule 96:  <datatype_binary> ::= BLOB
         */
        case 96: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeBinaryString( SQLQueryParserFactory.PRIMITIVE_TYPE_BINARY_LARGE_OBJECT, 0, null )); 
        }
        break;  
 
        /*
         *  Rule 97:  <datatype_binary> ::= BLOB _LPAREN _INTNUMBER <datatype_opt_size_unit> _RPAREN
         */
        case 97: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeBinaryString( SQLQueryParserFactory.PRIMITIVE_TYPE_BINARY_LARGE_OBJECT, Integer.parseInt(getTokenName(3)), getString(4) )); 
        }
        break;  
 
        /*
         *  Rule 98:  <datatype_binary> ::= BINARY LARGE OBJECT
         */
        case 98: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeBinaryString( SQLQueryParserFactory.PRIMITIVE_TYPE_BINARY_LARGE_OBJECT, 0, null )); 
        }
        break;  
 
        /*
         *  Rule 99:  <datatype_binary> ::= BINARY LARGE OBJECT _LPAREN _INTNUMBER <datatype_opt_size_unit> _RPAREN
         */
        case 99: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeBinaryString( SQLQueryParserFactory.PRIMITIVE_TYPE_BINARY_LARGE_OBJECT, Integer.parseInt(getTokenName(3)), getString(4) )); 
        }
        break;  
 
        /*
         *  Rule 100:  <datatype_character> ::= CHARACTER
         */
        case 100: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeCharacterString( SQLQueryParserFactory.PRIMITIVE_TYPE_CHARACTER, 0, null) ); 
        }
        break;  
 
        /*
         *  Rule 101:  <datatype_character> ::= CHAR
         */
        case 101: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeCharacterString( SQLQueryParserFactory.PRIMITIVE_TYPE_CHARACTER, 0, null) ); 
        }
        break;  
 
        /*
         *  Rule 102:  <datatype_character> ::= CHARACTER _LPAREN _INTNUMBER _RPAREN
         */
        case 102: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeCharacterString( SQLQueryParserFactory.PRIMITIVE_TYPE_CHARACTER, Integer.parseInt(getTokenName(3)), null )); 
        }
        break;  
 
        /*
         *  Rule 103:  <datatype_character> ::= CHAR _LPAREN _INTNUMBER _RPAREN
         */
        case 103: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeCharacterString( SQLQueryParserFactory.PRIMITIVE_TYPE_CHARACTER, Integer.parseInt(getTokenName(3)), null )); 
        }
        break;  
 
        /*
         *  Rule 104:  <datatype_character> ::= CHARACTER VARYING _LPAREN _INTNUMBER _RPAREN
         */
        case 104: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeCharacterString( SQLQueryParserFactory.PRIMITIVE_TYPE_CHARACTER_VARYING, Integer.parseInt(getTokenName(4)), null )); 
        }
        break;  
 
        /*
         *  Rule 105:  <datatype_character> ::= CHAR VARYING _LPAREN _INTNUMBER _RPAREN
         */
        case 105: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeCharacterString( SQLQueryParserFactory.PRIMITIVE_TYPE_CHARACTER_VARYING, Integer.parseInt(getTokenName(4)), null )); 
        }
        break;  
 
        /*
         *  Rule 106:  <datatype_character> ::= VARCHAR _LPAREN _INTNUMBER _RPAREN
         */
        case 106: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeCharacterString( SQLQueryParserFactory.PRIMITIVE_TYPE_CHARACTER_VARYING, Integer.parseInt(getTokenName(3)), null )); 
        }
        break;  
 
        /*
         *  Rule 107:  <datatype_character> ::= CLOB
         */
        case 107: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeCharacterString( SQLQueryParserFactory.PRIMITIVE_TYPE_CHARACTER_LARGE_OBJECT, 0, null )); 
        }
        break;  
 
        /*
         *  Rule 108:  <datatype_character> ::= CLOB _LPAREN _INTNUMBER <datatype_opt_size_unit> _RPAREN
         */
        case 108: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeCharacterString( SQLQueryParserFactory.PRIMITIVE_TYPE_CHARACTER_LARGE_OBJECT, Integer.parseInt(getTokenName(3)), getString(4) )); 
        }
        break;  
 
        /*
         *  Rule 109:  <datatype_character> ::= CHARACTER LARGE OBJECT
         */
        case 109: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeCharacterString( SQLQueryParserFactory.PRIMITIVE_TYPE_CHARACTER_LARGE_OBJECT, 0, null )); 
        }
        break;  
 
        /*
         *  Rule 110:  <datatype_character> ::= CHARACTER LARGE OBJECT _LPAREN _INTNUMBER <datatype_opt_size_unit> _RPAREN
         */
        case 110: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeCharacterString( SQLQueryParserFactory.PRIMITIVE_TYPE_CHARACTER_LARGE_OBJECT, Integer.parseInt(getTokenName(5)), getString(6) )); 
        }
        break;  
 
        /*
         *  Rule 111:  <datatype_character> ::= CHAR LARGE OBJECT
         */
        case 111: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeCharacterString( SQLQueryParserFactory.PRIMITIVE_TYPE_CHARACTER_LARGE_OBJECT, 0, null )); 
        }
        break;  
 
        /*
         *  Rule 112:  <datatype_character> ::= CHAR LARGE OBJECT _LPAREN _INTNUMBER <datatype_opt_size_unit> _RPAREN
         */
        case 112: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeCharacterString( SQLQueryParserFactory.PRIMITIVE_TYPE_CHARACTER_LARGE_OBJECT, Integer.parseInt(getTokenName(5)), getString(6) )); 
        }
        break;  
 
        /*
         *  Rule 113:  <datatype_character_national> ::= GRAPHIC
         */
        case 113: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeCharacterString( SQLQueryParserFactory.PRIMITIVE_TYPE_NATIONAL_CHARACTER, 0, null )); 
        }
        break;  
 
        /*
         *  Rule 114:  <datatype_character_national> ::= GRAPHIC _LPAREN _INTNUMBER _RPAREN
         */
        case 114: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeCharacterString( SQLQueryParserFactory.PRIMITIVE_TYPE_NATIONAL_CHARACTER, Integer.parseInt(getTokenName(3)), null )); 
        }
        break;  
 
        /*
         *  Rule 115:  <datatype_character_national> ::= VARGRAPHIC _LPAREN _INTNUMBER _RPAREN
         */
        case 115: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeCharacterString( SQLQueryParserFactory.PRIMITIVE_TYPE_NATIONAL_CHARACTER_VARYING, Integer.parseInt(getTokenName(3)), null )); 
        }
        break;  
 
        /*
         *  Rule 116:  <datatype_character_national> ::= DBCLOB
         */
        case 116: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeCharacterString( SQLQueryParserFactory.PRIMITIVE_TYPE_NATIONAL_CHARACTER_LARGE_OBJECT, 0, null )); 
        }
        break;  
 
        /*
         *  Rule 117:  <datatype_character_national> ::= DBCLOB _LPAREN _INTNUMBER <datatype_opt_size_unit> _RPAREN
         */
        case 117: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeCharacterString( SQLQueryParserFactory.PRIMITIVE_TYPE_NATIONAL_CHARACTER_LARGE_OBJECT, Integer.parseInt(getTokenName(3)), getString(4) )); 
        }
        break;  
 
        /*
         *  Rule 118:  <datatype_opt_size_unit> ::= K
         */
        case 118: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1( getTokenName(1) ); 
        }
        break;  
 
        /*
         *  Rule 119:  <datatype_opt_size_unit> ::= M
         */
        case 119: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1( getTokenName(1) ); 
        }
        break;  
 
        /*
         *  Rule 120:  <datatype_opt_size_unit> ::= G
         */
        case 120: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1( getTokenName(1) ); 
        }
        break;  
 
        /*
         *  Rule 121:  <datatype_opt_size_unit> ::= $Empty
         */
        case 121: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1( null ); 
        }
        break;  
 
        /*
         *  Rule 122:  <datatype_user_defined_distinct> ::= <identifier>
         */
        case 122: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDistinctUserDefinedType(getString(1))); 
        }
        break;  
 
        /*
         *  Rule 124:  <default_option> ::= USER
         */
        case 124: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSpecialRegisterExpression(SQLQueryParserFactory.SPECIAL_REGISTER_USER)); 
        }
        break;  
 
        /*
         *  Rule 125:  <default_option> ::= CURRENT_USER
         */
        case 125: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSpecialRegisterExpression(SQLQueryParserFactory.SPECIAL_REGISTER_CURRENT_USER)); 
        }
        break;  
 
        /*
         *  Rule 126:  <default_option> ::= CURRENT_ROLE
         */
        case 126: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSpecialRegisterExpression(SQLQueryParserFactory.SPECIAL_REGISTER_CURRENT_ROLE)); 
        }
        break;  
 
        /*
         *  Rule 127:  <default_option> ::= SESSION_USER
         */
        case 127: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSpecialRegisterExpression(SQLQueryParserFactory.SPECIAL_REGISTER_SESSION_USER)); 
        }
        break;  
 
        /*
         *  Rule 128:  <default_option> ::= SYSTEM_USER
         */
        case 128: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSpecialRegisterExpression(SQLQueryParserFactory.SPECIAL_REGISTER_SYSTEM_USER)); 
        }
        break;  
 
        /*
         *  Rule 129:  <default_option> ::= CURRENT_PATH
         */
        case 129: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSpecialRegisterExpression(SQLQueryParserFactory.SPECIAL_REGISTER_CURRENT_PATH)); 
        }
        break;  
 
        /*
         *  Rule 130:  <delete_stmt> ::= DELETE FROM <target_table> <opt_as_target_table> <opt_where_clause>
         */
        case 130: 
        {
            setSym1(m_factory.createDeleteStatement((TableInDatabase)getSym(3), (TableCorrelation)getSym(4), (QuerySearchCondition)getSym(5))); 
        }
        break;  
 
        /*
         *  Rule 131:  <derived_column_list> ::= <column_ref>
         */
        case 131: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createColumnList(null,(ValueExpressionColumn) getSym(1)));  
        }
        break;  
 
        /*
         *  Rule 132:  <derived_column_list> ::= <target_column_list> _COMMA <column_ref>
         */
        case 132: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createColumnList(getList(1),(ValueExpressionColumn) getSym(3)));  
        }
        break;  
 
        /*
         *  Rule 134:  <duration> ::= DAYS
         */
        case 134: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setInt1(SQLQueryParserFactory.DURATION_TYPE_DAYS); 
        }
        break;  
 
        /*
         *  Rule 135:  <duration> ::= HOURS
         */
        case 135: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setInt1(SQLQueryParserFactory.DURATION_TYPE_HOURS); 
        }
        break;  
 
        /*
         *  Rule 136:  <duration> ::= MICROSECONDS
         */
        case 136: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setInt1(SQLQueryParserFactory.DURATION_TYPE_MICROSECONDS); 
        }
        break;  
 
        /*
         *  Rule 137:  <duration> ::= MINUTES
         */
        case 137: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setInt1(SQLQueryParserFactory.DURATION_TYPE_MINUTES); 
        }
        break;  
 
        /*
         *  Rule 138:  <duration> ::= MONTHS
         */
        case 138: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setInt1(SQLQueryParserFactory.DURATION_TYPE_MONTHS); 
        }
        break;  
 
        /*
         *  Rule 139:  <duration> ::= SECONDS
         */
        case 139: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setInt1(SQLQueryParserFactory.DURATION_TYPE_SECONDS); 
        }
        break;  
 
        /*
         *  Rule 140:  <duration> ::= YEARS
         */
        case 140: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setInt1(SQLQueryParserFactory.DURATION_TYPE_YEARS); 
        }
        break;  
 
        /*
         *  Rule 141:  <exists> ::= EXISTS _LPAREN <query_exp> _RPAREN
         */
        case 141: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1( m_factory.createPredicateExists((QueryExpressionBody) getSym(3)) ); 
        }
        break;  
 
        /*
         *  Rule 142:  <expression> ::= <expression> _PLUS <expression_term>
         */
        case 142: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createCombinedExpression((QueryValueExpression) getSym(1),SQLQueryParserFactory.COMBINED_OPERATOR_ADD,(QueryValueExpression) getSym(3))); 
        }
        break;  
 
        /*
         *  Rule 143:  <expression> ::= <expression> _MINUS <expression_term>
         */
        case 143: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createCombinedExpression((QueryValueExpression) getSym(1),SQLQueryParserFactory.COMBINED_OPERATOR_SUBTRACT,(QueryValueExpression) getSym(3))); 
        }
        break;  
 
        /*
         *  Rule 145:  <expression_commalist> ::= <expression>
         */
        case 145: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createExpressionList(null, (QueryValueExpression) getSym(1))); 
        }
        break;  
 
        /*
         *  Rule 146:  <expression_commalist> ::= <expression_commalist> _COMMA <expression>
         */
        case 146: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createExpressionList(getList(1), (QueryValueExpression) getSym(3))); 
        }
        break;  
 
        /*
         *  Rule 148:  <expression_commalist_multiple_elements> ::= <expression_commalist> _COMMA <expression>
         */
        case 148: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createExpressionList(getList(1), (QueryValueExpression) getSym(3))); 
        }
        break;  
 
        /*
         *  Rule 149:  <expression_factor> ::= _LPAREN <expression> _RPAREN
         */
        case 149: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createNestedExpression((QueryValueExpression) getSym(2))); 
        }
        break;  
 
        /*
         *  Rule 150:  <expression_factor> ::= <subquery>
         */
        case 150: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createScalarSelectExpression((QueryExpressionRoot) getSym(1))); 
        }
        break;  
 
        /*
         *  Rule 158:  <expression_factor> ::= _PLUS <expression_factor>
         */
        case 158: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1( m_factory.setUnaryOperator((QueryValueExpression)getSym(2),SQLQueryParserFactory.UNARY_OPERATOR_PLUS) ); 
        }
        break;  
 
        /*
         *  Rule 159:  <expression_factor> ::= _MINUS <expression_factor>
         */
        case 159: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1( m_factory.setUnaryOperator((QueryValueExpression)getSym(2),SQLQueryParserFactory.UNARY_OPERATOR_MINUS) ); 
        }
        break;  
 
        /*
         *  Rule 160:  <expression_factor> ::= DEFAULT
         */
        case 160: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDefaultExpression()); 
        }
        break;  
 
        /*
         *  Rule 161:  <expression_factor> ::= NULL
         */
        case 161: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createNullExpression()); 
        }
        break;  
 
        /*
         *  Rule 162:  <expression_term> ::= <expression_term> _STAR <expression_factor>
         */
        case 162: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createCombinedExpression((QueryValueExpression) getSym(1),SQLQueryParserFactory.COMBINED_OPERATOR_MULTIPLY,(QueryValueExpression) getSym(3))); 
        }
        break;  
 
        /*
         *  Rule 163:  <expression_term> ::= <expression_term> _SLASH <expression_factor>
         */
        case 163: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createCombinedExpression((QueryValueExpression) getSym(1),SQLQueryParserFactory.COMBINED_OPERATOR_DIVIDE,(QueryValueExpression) getSym(3))); 
        }
        break;  
 
        /*
         *  Rule 164:  <expression_term> ::= <expression_term> CONCAT <expression_factor>
         */
        case 164: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createCombinedExpression((QueryValueExpression) getSym(1),SQLQueryParserFactory.COMBINED_OPERATOR_CONCATENATE,(QueryValueExpression) getSym(3))); 
        }
        break;  
 
        /*
         *  Rule 165:  <expression_term> ::= <expression_term> _CONCAT_OPERATOR <expression_factor>
         */
        case 165: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createCombinedExpression((QueryValueExpression) getSym(1),SQLQueryParserFactory.COMBINED_OPERATOR_CONCATENATE,(QueryValueExpression) getSym(3))); 
        }
        break;  
 
        /*
         *  Rule 166:  <expression_term> ::= <expression_term> <duration>
         */
        case 166: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createLabeledDurationExpression((QueryValueExpression) getSym(1), getInt(2))); 
        }
        break;  
 
        /*
         *  Rule 170:  <func_ref> ::= <alias_name> _DOT <identifier>
         */
        case 170: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(new String((getString(1).concat(".")).concat(getString(3))));  //$NON-NLS-1$
			   
        }
        break;   
        /*
         *  Rule 171:  <function> ::= <opt_schema_dot> <identifier> _LPAREN _STAR _RPAREN
         */
        case 171: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createFunctionExpression(getString(2), null, null, getString(1))); 
        }
        break;  
 
        /*
         *  Rule 172:  <function> ::= <opt_schema_dot> <identifier> _LPAREN <opt_all_distinct> <opt_expression_commalist> _RPAREN
         */
        case 172: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createFunctionExpression(getString(2), getString(4), getList(5), getString(1))); 
        }
        break;  
 
        /*
         *  Rule 175:  <grouping_exp> ::= <expression>
         */
        case 175: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1( m_factory.createGroupingExpression((QueryValueExpression) getSym(1)) ); 
        }
        break;  
 
        /*
         *  Rule 176:  <grouping_sets> ::= GROUPING SETS _LPAREN <grouping_sets_element_list> _RPAREN
         */
        case 176: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1( m_factory.createGroupingSets(getList(4)) ); 
        }
        break;  
 
        /*
         *  Rule 177:  <grouping_sets_element_list> ::= <grouping_sets_element>
         */
        case 177: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createGroupingSetsElementList(null,(GroupingSetsElement) getSym(1)));  
        }
        break;  
 
        /*
         *  Rule 178:  <grouping_sets_element_list> ::= <grouping_sets_element_list> _COMMA <grouping_sets_element>
         */
        case 178: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createGroupingSetsElementList(getList(1),(GroupingSetsElement) getSym(3)));  
        }
        break;  
 
        /*
         *  Rule 179:  <grouping_sets_element> ::= _LPAREN <grouping_sets_element_exp_list> _RPAREN
         */
        case 179: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1( m_factory.createGroupingSetsElementSublist(getList(2)) );  
        }
        break;  
 
        /*
         *  Rule 181:  <grouping_sets_element_exp> ::= <grouping>
         */
        case 181: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1( m_factory.createGroupingSetsElementExpression((Grouping) getSym(1)) ); 
        }
        break;  
 
        /*
         *  Rule 182:  <grouping_sets_element_exp_list> ::= <grouping_sets_element_exp>
         */
        case 182: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createGroupingSetsElementExprList(null,(GroupingSetsElementExpression) getSym(1)));  
        }
        break;  
 
        /*
         *  Rule 183:  <grouping_sets_element_exp_list> ::= <grouping_sets_element_exp_list> _COMMA <grouping_sets_element_exp>
         */
        case 183: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createGroupingSetsElementExprList(getList(1),(GroupingSetsElementExpression) getSym(3)));  
        }
        break;  
 
        /*
         *  Rule 186:  <grouping_spec_list> ::= <grouping_spec>
         */
        case 186: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createGroupingSpecificationList(null,(GroupingSpecification) getSym(1)));  
        }
        break;  
 
        /*
         *  Rule 187:  <grouping_spec_list> ::= <grouping_spec_list> _COMMA <grouping_spec>
         */
        case 187: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createGroupingSpecificationList(getList(1),(GroupingSpecification) getSym(3)));  
        }
        break;  
 
        /*
         *  Rule 188:  <identifier> ::= REGULAR_IDENTIFIER
         */
        case 188: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(getTokenName(1)); 
        }
        break;  
 
        /*
         *  Rule 189:  <identifier> ::= DELIMITED_IDENTIFIER
         */
        case 189: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(getTokenName(1)); 
        }
        break;  
 
        /*
         *  Rule 191:  <in_cond> ::= <expression> NOT IN _LPAREN <expression_commalist> _RPAREN
         */
        case 191: 
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
         *  Rule 192:  <in_cond> ::= <expression> IN _LPAREN <expression_commalist> _RPAREN
         */
        case 192: 
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
         *  Rule 193:  <in_cond> ::= <expression> NOT IN <subquery>
         */
        case 193: 
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
         *  Rule 194:  <in_cond> ::= <expression> IN <subquery>
         */
        case 194: 
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
         *  Rule 195:  <in_cond> ::= _LPAREN <expression_commalist> _COMMA <expression> _RPAREN NOT IN <subquery>
         */
        case 195: 
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
         *  Rule 196:  <in_cond> ::= _LPAREN <expression_commalist> _COMMA <expression> _RPAREN IN <subquery>
         */
        case 196: 
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
         *  Rule 197:  <insert_row> ::= _LPAREN <expression_commalist_multiple_elements> _RPAREN
         */
        case 197: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createInsertValuesRow(getList(2))); 
        }
        break;  
 
        /*
         *  Rule 198:  <insert_row> ::= <expression>
         */
        case 198: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createInsertValuesRow((QueryValueExpression)getSym(1))); 
        }
        break;  
 
        /*
         *  Rule 199:  <insert_row_commalist> ::= <insert_row>
         */
        case 199: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createInsertRow(null, (ValuesRow) getSym(1))); 
        }
        break;  
 
        /*
         *  Rule 200:  <insert_row_commalist> ::= <insert_row_commalist> _COMMA <insert_row>
         */
        case 200: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createInsertRow(getList(1), (ValuesRow) getSym(3))); 
        }
        break;  
 
        /*
         *  Rule 201:  <insert_stmt> ::= INSERT INTO <target_table> <opt_target_column_list> VALUES <insert_row_commalist>
         */
        case 201: 
        {
            setSym1(m_factory.createInsertStatement((TableInDatabase) getSym(3), getList(4), getList(6))); 
        }
        break;  
 
        /*
         *  Rule 202:  <insert_stmt> ::= INSERT INTO <target_table> <opt_target_column_list> <query_exp_root>
         */
        case 202: 
        {
            setSym1(m_factory.createInsertStatement((TableInDatabase)getSym(3), getList(4), (QueryExpressionRoot)getSym(5))); 
        }
        break;  
 
        /*
         *  Rule 203:  <intervaltest> ::= <expression> NOT BETWEEN <expression> AND <expression>
         */
        case 203: 
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
         *  Rule 204:  <intervaltest> ::= <expression> BETWEEN <expression> AND <expression>
         */
        case 204: 
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
         *  Rule 207:  <liketest> ::= <expression> NOT LIKE <expression> <opt_escape>
         */
        case 207: 
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
         *  Rule 208:  <liketest> ::= <expression> LIKE <expression> <opt_escape>
         */
        case 208: 
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
         *  Rule 209:  <nulltest> ::= <expression> IS NOT NULL
         */
        case 209: 
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
         *  Rule 210:  <nulltest> ::= <expression> IS NULL
         */
        case 210: 
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
         *  Rule 211:  <null_specification> ::= NULL
         */
        case 211: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createNullExpression()); 
        }
        break;  
 
        /*
         *  Rule 212:  <opt_all_distinct> ::= $Empty
         */
        case 212: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1((String)null); 
        }
        break;  
 
        /*
         *  Rule 213:  <opt_all_distinct> ::= ALL
         */
        case 213: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(SQLQueryParserFactory.ALL); 
        }
        break;  
 
        /*
         *  Rule 214:  <opt_all_distinct> ::= DISTINCT
         */
        case 214: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(SQLQueryParserFactory.DISTINCT); 
        }
        break;  
 
        /*
         *  Rule 218:  <opt_as_alias> ::= $Empty
         */
        case 218: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(null); 
        }
        break;  
 
        /*
         *  Rule 219:  <opt_as_target_table> ::= <opt_as> <table>
         */
        case 219: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createTableCorrelation(getString(2))); 
        }
        break;  
 
        /*
         *  Rule 220:  <opt_as_target_table> ::= $Empty
         */
        case 220: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(null); 
        }
        break;  
 
        /*
         *  Rule 221:  <opt_asc_desc> ::= ASC
         */
        case 221: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setInt1(SQLQueryParserFactory.ORDERING_SPEC_TYPE_ASC); 
        }
        break;  
 
        /*
         *  Rule 222:  <opt_asc_desc> ::= DESC
         */
        case 222: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setInt1(SQLQueryParserFactory.ORDERING_SPEC_TYPE_DESC); 
        }
        break;  
 
        /*
         *  Rule 223:  <opt_asc_desc> ::= $Empty
         */
        case 223: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setInt1(SQLQueryParserFactory.ORDERING_SPEC_TYPE_NONE); 
        }
        break;  
 
        /*
         *  Rule 224:  <opt_case_else> ::= ELSE <expression>
         */
        case 224: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createCaseElse((QueryValueExpression)getSym(2))); 
        }
        break;  
 
        /*
         *  Rule 225:  <opt_case_else> ::= $Empty
         */
        case 225: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(null); 
        }
        break;  
 
        /*
         *  Rule 226:  <opt_column_name_list> ::= _LPAREN <column_name_list> _RPAREN
         */
        case 226: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(getList(2)); 
        }
        break;  
 
        /*
         *  Rule 227:  <opt_column_name_list> ::= $Empty
         */
        case 227: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(null); 
        }
        break;  
 
        /*
         *  Rule 228:  <opt_default_clause> ::= DEFAULT <default_option>
         */
        case 228: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(getSym(2)); 
        }
        break;  
 
        /*
         *  Rule 229:  <opt_default_clause> ::= $Empty
         */
        case 229: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(null); 
        }
        break;  
 
        /*
         *  Rule 230:  <opt_escape> ::= ESCAPE _STRING
         */
        case 230: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSimpleExpression(getTokenName(2))); 
        }
        break;  
 
        /*
         *  Rule 231:  <opt_escape> ::= $Empty
         */
        case 231: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(null); 
        }
        break;  
 
        /*
         *  Rule 233:  <opt_expression_commalist> ::= $Empty
         */
        case 233: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(null); 
        }
        break;  
 
        /*
         *  Rule 234:  <opt_group_by_clause> ::= GROUP BY <grouping_spec_list>
         */
        case 234: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(getList(3)); 
        }
        break;  
 
        /*
         *  Rule 235:  <opt_group_by_clause> ::= GROUP BY <super_groups_element_list> WITH CUBE
         */
        case 235: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1( m_factory.createGroupingSpecificationList(null, m_factory.createSuperGroups(getList(3),SQLQueryParserFactory.SUPERGROUP_TYPE_CUBE)) ); 
        }
        break;  
 
        /*
         *  Rule 236:  <opt_group_by_clause> ::= GROUP BY <super_groups_element_list> WITH ROLLUP
         */
        case 236: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1( m_factory.createGroupingSpecificationList(null, m_factory.createSuperGroups(getList(3),SQLQueryParserFactory.SUPERGROUP_TYPE_ROLLUP)) ); 
        }
        break;  
 
        /*
         *  Rule 237:  <opt_group_by_clause> ::= $Empty
         */
        case 237: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1((List)null); 
        }
        break;  
 
        /*
         *  Rule 238:  <opt_having_clause> ::= HAVING <condition>
         */
        case 238: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(getSym(2)); 
        }
        break;  
 
        /*
         *  Rule 239:  <opt_having_clause> ::= $Empty
         */
        case 239: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(null); 
        }
        break;  
 
        /*
         *  Rule 240:  <opt_join_type> ::= INNER
         */
        case 240: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setInt1(SQLQueryParserFactory.JOIN_EXPLICIT_INNER); 
        }
        break;  
 
        /*
         *  Rule 241:  <opt_join_type> ::= LEFT <opt_join_type_outer>
         */
        case 241: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setInt1(SQLQueryParserFactory.JOIN_LEFT_OUTER); 
        }
        break;  
 
        /*
         *  Rule 242:  <opt_join_type> ::= RIGHT <opt_join_type_outer>
         */
        case 242: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setInt1(SQLQueryParserFactory.JOIN_RIGHT_OUTER); 
        }
        break;  
 
        /*
         *  Rule 243:  <opt_join_type> ::= FULL <opt_join_type_outer>
         */
        case 243: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setInt1(SQLQueryParserFactory.JOIN_FULL_OUTER); 
        }
        break;  
 
        /*
         *  Rule 244:  <opt_join_type> ::= $Empty
         */
        case 244: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setInt1(SQLQueryParserFactory.JOIN_DEFAULT_INNER); 
        }
        break;  
 
        /*
         *  Rule 247:  <opt_null_order> ::= NULLS FIRST
         */
        case 247: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setInt1(SQLQueryParserFactory.NULL_ORDERING_TYPE_NULLS_FIRST); 
        }
        break;  
 
        /*
         *  Rule 248:  <opt_null_order> ::= NULLS LAST
         */
        case 248: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setInt1(SQLQueryParserFactory.NULL_ORDERING_TYPE_NULLS_LAST); 
        }
        break;  
 
        /*
         *  Rule 249:  <opt_null_order> ::= $Empty
         */
        case 249: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setInt1(SQLQueryParserFactory.NULL_ORDERING_TYPE_NONE); 
        }
        break;  
 
        /*
         *  Rule 250:  <opt_order_by_clause> ::= ORDER BY <ordering_spec_commalist>
         */
        case 250: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(getList(3)); 
        }
        break;  
 
        /*
         *  Rule 251:  <opt_order_by_clause> ::= $Empty
         */
        case 251: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1((List)null); 
        }
        break;  
 
        /*
         *  Rule 252:  <opt_schema_dot> ::= <schema> _DOT
         */
        case 252: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(getString(1)); 
        }
        break;  
 
        /*
         *  Rule 253:  <opt_schema_dot> ::= $Empty
         */
        case 253: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(null); 
        }
        break;  
 
        /*
         *  Rule 255:  <opt_table_correlation> ::= $Empty
         */
        case 255: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(null); 
        }
        break;  
 
        /*
         *  Rule 256:  <opt_target_column_list> ::= _LPAREN <target_column_list> _RPAREN
         */
        case 256: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(getList(2)); 
        }
        break;  
 
        /*
         *  Rule 257:  <opt_target_column_list> ::= $Empty
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
         *  Rule 258:  <opt_where_clause> ::= WHERE <condition>
         */
        case 258: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1_keepSpan(getSym(2)); 
        }
        break;  
 
        /*
         *  Rule 259:  <opt_where_clause> ::= $Empty
         */
        case 259: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(null);  
        }
        break;  
 
        /*
         *  Rule 260:  <ordering_spec> ::= <expression> <opt_asc_desc> <opt_null_order>
         */
        case 260: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createOrderByExpression((QueryValueExpression)getSym(1), getInt(2), getInt(3))); 
        }
        break;  
 
        /*
         *  Rule 261:  <ordering_spec_commalist> ::= <ordering_spec>
         */
        case 261: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createOrderByClause(null,(OrderBySpecification) getSym(1))); 
        }
        break;  
 
        /*
         *  Rule 262:  <ordering_spec_commalist> ::= <ordering_spec_commalist> _COMMA <ordering_spec>
         */
        case 262: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createOrderByClause(getList(1),(OrderBySpecification) getSym(3))); 
        }
        break;  
 
        /*
         *  Rule 263:  <parameter> ::= _HOSTVAR
         */
        case 263: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createVariableExpression(getTokenName(1))); 
        }
        break;  
 
        /*
         *  Rule 264:  <parameter> ::= _PARAM_MARKER
         */
        case 264: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createVariableExpression(null)); 
        }
        break;  
 
        /*
         *  Rule 265:  <path-resolved user-defined type name> ::= <opt_schema_dot> <identifier>
         */
        case 265: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
           
            setSym1(getString(1).concat(".").concat(getString(2))); //$NON-NLS-1$
           
        }
        break;   
        /*
         *  Rule 266:  <project> ::= <expression> <opt_as_alias>
         */
        case 266: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createResultColumn((QueryValueExpression) getSym(1), getString(2))); 
        }
        break;  
 
        /*
         *  Rule 267:  <project> ::= _STAR
         */
        case 267: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(null); 
        }
        break;  
 
        /*
         *  Rule 268:  <project> ::= <table> _DOT _STAR
         */
        case 268: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createResultTableAllColumns(getString(1))); 
        }
        break;  
 
        /*
         *  Rule 269:  <project> ::= <schema> _DOT <table> _DOT _STAR
         */
        case 269: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createResultTableAllColumns(getString(3), getString(1))); 
        }
        break;  
 
        /*
         *  Rule 270:  <query_combined> ::= <query_exp> <query_combined_operator> <query_term>
         */
        case 270: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createQueryCombined((QueryExpressionBody) getSym(1),getInt(2),(QueryExpressionBody) getSym(3)));  
        }
        break;  
 
        /*
         *  Rule 271:  <query_combined_operator> ::= UNION
         */
        case 271: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setInt1(SQLQueryParserFactory.QUERY_COMBINED_UNION); 
        }
        break;  
 
        /*
         *  Rule 272:  <query_combined_operator> ::= UNION ALL
         */
        case 272: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setInt1(SQLQueryParserFactory.QUERY_COMBINED_UNION_ALL); 
        }
        break;  
 
        /*
         *  Rule 273:  <query_combined_operator> ::= INTERSECT
         */
        case 273: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setInt1(SQLQueryParserFactory.QUERY_COMBINED_INTERSECT); 
        }
        break;  
 
        /*
         *  Rule 274:  <query_combined_operator> ::= INTERSECT ALL
         */
        case 274: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setInt1(SQLQueryParserFactory.QUERY_COMBINED_INTERSECT_ALL); 
        }
        break;  
 
        /*
         *  Rule 275:  <query_combined_operator> ::= EXCEPT
         */
        case 275: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setInt1(SQLQueryParserFactory.QUERY_COMBINED_EXCEPT); 
        }
        break;  
 
        /*
         *  Rule 276:  <query_combined_operator> ::= EXCEPT ALL
         */
        case 276: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setInt1(SQLQueryParserFactory.QUERY_COMBINED_EXCEPT_ALL); 
        }
        break;  
 
        /*
         *  Rule 279:  <query_exp_root> ::= <with_clause> <query_exp>
         */
        case 279: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1( m_factory.createQueryExpressionRoot((QueryExpressionBody)getSym(2), getList(1)) ); 
        }
        break;  
 
        /*
         *  Rule 280:  <query_select> ::= SELECT <opt_all_distinct> <selection> FROM <table_ref_commalist> <opt_where_clause> <opt_group_by_clause> <opt_having_clause>
         */
        case 280: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createQuerySelect(getString(2),getList(3),getList(5),(QuerySearchCondition)getSym(6),getList(7),(QuerySearchCondition)getSym(8))); 
        }
        break;  
 
        /*
         *  Rule 281:  <query_stmt> ::= <query_exp_root> <opt_order_by_clause>
         */
        case 281: 
        {
            setSym1(m_factory.createSelectStatement((QueryExpressionRoot)getSym(1), getList(2))); 
        }
        break;  
 
        /*
         *  Rule 284:  <query_term> ::= _LPAREN <query_exp> _RPAREN
         */
        case 284: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1( m_factory.createQueryNested((QueryExpressionBody)getSym(2)) );  
        }
        break;  
 
        /*
         *  Rule 285:  <query_values> ::= VALUES <values_row_commalist>
         */
        case 285: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createQueryValues(getList(2))); 
        }
        break;  
 
        /*
         *  Rule 286:  <relop> ::= _EQ
         */
        case 286: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setInt1(SQLQueryParserFactory.COMPARISON_OPERATOR_EQ); 
        }
        break;  
 
        /*
         *  Rule 287:  <relop> ::= _LT
         */
        case 287: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setInt1(SQLQueryParserFactory.COMPARISON_OPERATOR_LT); 
        }
        break;  
 
        /*
         *  Rule 288:  <relop> ::= _LE
         */
        case 288: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setInt1(SQLQueryParserFactory.COMPARISON_OPERATOR_LE); 
        }
        break;  
 
        /*
         *  Rule 289:  <relop> ::= _NE
         */
        case 289: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setInt1(SQLQueryParserFactory.COMPARISON_OPERATOR_NE); 
        }
        break;  
 
        /*
         *  Rule 290:  <relop> ::= _GT
         */
        case 290: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setInt1(SQLQueryParserFactory.COMPARISON_OPERATOR_GT); 
        }
        break;  
 
        /*
         *  Rule 291:  <relop> ::= _GE
         */
        case 291: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setInt1(SQLQueryParserFactory.COMPARISON_OPERATOR_GE); 
        }
        break;  
 
        /*
         *  Rule 294:  <row_comparison> ::= <value_expr_row> <relop> <value_expr_row>
         */
        case 294: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createPredicateBasic((QueryValueExpression)getSym(1),getInt(2),(QueryValueExpression)getSym(3))); 
        }
        break;  
 
        /*
         *  Rule 295:  <scalar_comparison> ::= <expression> <relop> <expression>
         */
        case 295: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createPredicateBasic((QueryValueExpression)getSym(1),getInt(2),(QueryValueExpression)getSym(3))); 
        }
        break;  
 
        /*
         *  Rule 296:  <schema> ::= <identifier>
         */
        case 296: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(getString(1)); 
        }
        break;  
 
        /*
         *  Rule 297:  <schema_qualified_name> ::= <identifier>
         */
        case 297: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(getString(1)); 
        }
        break;  
 
        /*
         *  Rule 298:  <schema_qualified_name> ::= <schema> _DOT <identifier>
         */
        case 298: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(getString(1)+"."+getString(3)); 
        }
        break;  
 
        /*
         *  Rule 299:  <selection> ::= <project>
         */
        case 299: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSelectClause(null,(QueryResultSpecification)getSym(1))); 
        }
        break;  
 
        /*
         *  Rule 300:  <selection> ::= <selection> _COMMA <project>
         */
        case 300: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSelectClause(getList(1),(QueryResultSpecification)getSym(3))); 
        }
        break;  
 
        /*
         *  Rule 309:  <special_register> ::= CURRENT_DATE
         */
        case 309: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSpecialRegisterExpression(SQLQueryParserFactory.SPECIAL_REGISTER_CURRENT_DATE)); 
        }
        break;  
 
        /*
         *  Rule 310:  <special_register> ::= CURRENT_TIME
         */
        case 310: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSpecialRegisterExpression(SQLQueryParserFactory.SPECIAL_REGISTER_CURRENT_TIME)); 
        }
        break;  
 
        /*
         *  Rule 311:  <special_register> ::= CURRENT_TIMESTAMP
         */
        case 311: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSpecialRegisterExpression(SQLQueryParserFactory.SPECIAL_REGISTER_CURRENT_TIMESTAMP)); 
        }
        break;  
 
        /*
         *  Rule 312:  <special_register> ::= CURRENT_TIMESTAMP _LPAREN <timestamp precision> _RPAREN
         */
        case 312: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSpecialRegisterExpression(SQLQueryParserFactory.SPECIAL_REGISTER_CURRENT_TIMESTAMP)); 
        }
        break;  
 
        /*
         *  Rule 313:  <special_register> ::= LOCALTIME
         */
        case 313: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSpecialRegisterExpression(SQLQueryParserFactory.SPECIAL_REGISTER_LOCALTIME)); 
        }
        break;  
 
        /*
         *  Rule 314:  <special_register> ::= LOCALTIME _LPAREN <time precision> _RPAREN
         */
        case 314: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSpecialRegisterExpression(SQLQueryParserFactory.SPECIAL_REGISTER_LOCALTIME)); 
        }
        break;  
 
        /*
         *  Rule 315:  <special_register> ::= LOCALTIMESTAMP
         */
        case 315: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSpecialRegisterExpression(SQLQueryParserFactory.SPECIAL_REGISTER_LOCALTIMESTAMP)); 
        }
        break;  
 
        /*
         *  Rule 316:  <special_register> ::= LOCALTIMESTAMP _LPAREN <timestamp precision> _RPAREN
         */
        case 316: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSpecialRegisterExpression(SQLQueryParserFactory.SPECIAL_REGISTER_LOCALTIMESTAMP)); 
        }
        break;  
 
        /*
         *  Rule 317:  <special_register> ::= CURRENT_DEFAULT_TRANSFORM_GROUP
         */
        case 317: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSpecialRegisterExpression(SQLQueryParserFactory.SPECIAL_REGISTER_CURRENT_DEFAULT_TRANSFORM_GROUP)); 
        }
        break;  
 
        /*
         *  Rule 318:  <special_register> ::= CURRENT_PATH
         */
        case 318: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSpecialRegisterExpression(SQLQueryParserFactory.SPECIAL_REGISTER_CURRENT_PATH)); 
        }
        break;  
 
        /*
         *  Rule 319:  <special_register> ::= CURRENT_ROLE
         */
        case 319: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSpecialRegisterExpression(SQLQueryParserFactory.SPECIAL_REGISTER_CURRENT_ROLE)); 
        }
        break;  
 
        /*
         *  Rule 320:  <special_register> ::= CURRENT_TRANSFORM_GROUP_FOR_TYPE <path-resolved user-defined type name>
         */
        case 320: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSpecialRegisterExpression(SQLQueryParserFactory.SPECIAL_REGISTER_CURRENT_TRANSFORM_GROUP_FOR_TYPE,getString(2))); 
        }
        break;  
 
        /*
         *  Rule 321:  <special_register> ::= CURRENT_USER
         */
        case 321: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSpecialRegisterExpression(SQLQueryParserFactory.SPECIAL_REGISTER_CURRENT_USER)); 
        }
        break;  
 
        /*
         *  Rule 322:  <special_register> ::= SESSION_USER
         */
        case 322: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSpecialRegisterExpression(SQLQueryParserFactory.SPECIAL_REGISTER_SESSION_USER)); 
        }
        break;  
 
        /*
         *  Rule 323:  <special_register> ::= SYSTEM_USER
         */
        case 323: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSpecialRegisterExpression(SQLQueryParserFactory.SPECIAL_REGISTER_SYSTEM_USER)); 
        }
        break;  
 
        /*
         *  Rule 324:  <special_register> ::= USER
         */
        case 324: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSpecialRegisterExpression(SQLQueryParserFactory.SPECIAL_REGISTER_USER)); 
        }
        break;  
 
        /*
         *  Rule 325:  <special_register> ::= VALUE
         */
        case 325: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSpecialRegisterExpression(SQLQueryParserFactory.SPECIAL_REGISTER_VALUE)); 
        }
        break;  
 
        /*
         *  Rule 326:  <subquery> ::= _LPAREN <query_exp_root> _RPAREN
         */
        case 326: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(getSym(2)); 
        }
        break;  
 
        /*
         *  Rule 327:  <super_groups> ::= CUBE _LPAREN <super_groups_element_list> _RPAREN
         */
        case 327: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1( m_factory.createSuperGroups(getList(3), SQLQueryParserFactory.SUPERGROUP_TYPE_CUBE) ); 
        }
        break;  
 
        /*
         *  Rule 328:  <super_groups> ::= ROLLUP _LPAREN <super_groups_element_list> _RPAREN
         */
        case 328: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1( m_factory.createSuperGroups(getList(3), SQLQueryParserFactory.SUPERGROUP_TYPE_ROLLUP) ); 
        }
        break;  
 
        /*
         *  Rule 329:  <super_groups> ::= _LPAREN _RPAREN
         */
        case 329: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1( m_factory.createSuperGroups(null, SQLQueryParserFactory.SUPERGROUP_TYPE_GRANDTOTAL) ); 
        }
        break;  
 
        /*
         *  Rule 330:  <super_groups_element_exp> ::= <grouping_exp>
         */
        case 330: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1( m_factory.createSuperGroupsElementExpression((GroupingExpression) getSym(1)) ); 
        }
        break;  
 
        /*
         *  Rule 331:  <super_groups_element_exp_list> ::= <super_groups_element_exp>
         */
        case 331: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSuperGroupsElementExprList(null,(SuperGroupElementExpression) getSym(1)));  
        }
        break;  
 
        /*
         *  Rule 332:  <super_groups_element_exp_list> ::= <super_groups_element_exp_list> _COMMA <super_groups_element_exp>
         */
        case 332: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSuperGroupsElementExprList(getList(1),(SuperGroupElementExpression) getSym(3)));  
        }
        break;  
 
        /*
         *  Rule 333:  <super_groups_element> ::= _LPAREN <super_groups_element_exp_list> _RPAREN
         */
        case 333: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1( m_factory.createSuperGroupsElementSublist(getList(2)) );  
        }
        break;  
 
        /*
         *  Rule 335:  <super_groups_element_list> ::= <super_groups_element>
         */
        case 335: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSuperGroupsElementList(null,(SuperGroupElement) getSym(1)));  
        }
        break;  
 
        /*
         *  Rule 336:  <super_groups_element_list> ::= <super_groups_element_list> _COMMA <super_groups_element>
         */
        case 336: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSuperGroupsElementList(getList(1),(SuperGroupElement) getSym(3)));  
        }
        break;  
 
        /*
         *  Rule 337:  <table> ::= <identifier>
         */
        case 337: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(getString(1)); 
        }
        break;  
 
        /*
         *  Rule 338:  <table_correlation> ::= <as_alias> <opt_column_name_list>
         */
        case 338: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createTableCorrelation(getString(1), getList(2))); 
        }
        break;  
 
        /*
         *  Rule 339:  <table_join> ::= <table_ref> <opt_join_type> JOIN <table_ref> ON <condition>
         */
        case 339: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createJoinedTable((TableReference)getSym(1), getInt(2), (TableReference)getSym(4), (QuerySearchCondition)getSym(6))); 
        }
        break;  
 
        /*
         *  Rule 340:  <table_ref> ::= <schema> _DOT <table> <opt_table_correlation>
         */
        case 340: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createReferenceTable(getString(1), getString(3), (TableCorrelation)getSym(4))); 
        }
        break;  
 
        /*
         *  Rule 341:  <table_ref> ::= <table> <opt_table_correlation>
         */
        case 341: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createReferenceTable(null, getString(1), (TableCorrelation)getSym(2))); 
        }
        break;  
 
        /*
         *  Rule 344:  <table_ref> ::= _LPAREN <table_ref> _RPAREN
         */
        case 344: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createNestedTable((TableReference)getSym(2))); 
        }
        break;  
 
        /*
         *  Rule 345:  <table_ref> ::= TABLE _LPAREN <opt_schema_dot> <identifier> _LPAREN <opt_expression_commalist> _RPAREN _RPAREN <table_correlation>
         */
        case 345: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createTableFunction(getString(4), getList(6), getString(3), (TableCorrelation)getSym(9)) ); 
        }
        break;  
 
        /*
         *  Rule 346:  <table_query> ::= _LPAREN <query_exp> _RPAREN <table_correlation>
         */
        case 346: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createTableExpressionQuery((QueryExpressionBody)getSym(2), (TableCorrelation)getSym(4))); 
        }
        break;  
 
        /*
         *  Rule 347:  <table_query> ::= TABLE _LPAREN <query_exp> _RPAREN <table_correlation>
         */
        case 347: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createTableExpressionQuery((QueryExpressionBody)getSym(3), (TableCorrelation)getSym(5))); 
        }
        break;  
 
        /*
         *  Rule 348:  <table_ref_commalist> ::= <table_ref>
         */
        case 348: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createFromClause((List)null,(TableReference)getSym(1))); 
        }
        break;  
 
        /*
         *  Rule 349:  <table_ref_commalist> ::= <table_ref_commalist> _COMMA <table_ref>
         */
        case 349: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createFromClause(getList(1),(TableReference)getSym(3))); 
        }
        break;  
 
        /*
         *  Rule 350:  <target_column_list> ::= <column_ref>
         */
        case 350: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createColumnList(null,(ValueExpressionColumn) getSym(1)));  
        }
        break;  
 
        /*
         *  Rule 351:  <target_column_list> ::= <target_column_list> _COMMA <column_ref>
         */
        case 351: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createColumnList(getList(1),(ValueExpressionColumn) getSym(3)));  
        }
        break;  
 
        /*
         *  Rule 352:  <target_table> ::= <table>
         */
        case 352: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSimpleTable(null, getString(1))); 
        }
        break;  
 
        /*
         *  Rule 353:  <target_table> ::= <schema> _DOT <table>
         */
        case 353: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSimpleTable(getString(1), getString(3))); 
        }
        break;  
 
        /*
         *  Rule 354:  <time precision> ::= UNSIGNED_INTEGER
         */
        case 354: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(getTokenName(1)); 
        }
        break;  
 
        /*
         *  Rule 355:  <timestamp precision> ::= UNSIGNED_INTEGER
         */
        case 355: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(getTokenName(1)); 
        }
        break;  
 
        /*
         *  Rule 357:  <update_assignment_expression> ::= <column_ref> _EQ <expression>
         */
        case 357: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createUpdateAssignmentExpression((ValueExpressionColumn)getSym(1),(QueryValueExpression)getSym(3))); 
        }
        break;  
 
        /*
         *  Rule 358:  <update_assignment_expression> ::= _LPAREN <target_column_list> _RPAREN _EQ _LPAREN <query_exp> _RPAREN
         */
        case 358: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createUpdateAssignmentExpression(getList(2),(QueryExpressionBody)getSym(6))); 
        }
        break;  
 
        /*
         *  Rule 359:  <update_assignment_expression> ::= _LPAREN <target_column_list> _RPAREN _EQ _LPAREN <expression_commalist> _RPAREN
         */
        case 359: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createUpdateAssignmentExpression(getList(2),getList(6))); 
        }
        break;  
 
        /*
         *  Rule 360:  <update_assignment_expression_commalist> ::= <update_assignment_expression>
         */
        case 360: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createUpdateAssignmentClause(null,(UpdateAssignmentExpression)getSym(1))); 
        }
        break;  
 
        /*
         *  Rule 361:  <update_assignment_expression_commalist> ::= <update_assignment_expression_commalist> _COMMA <update_assignment_expression>
         */
        case 361: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createUpdateAssignmentClause(getList(1),(UpdateAssignmentExpression)getSym(3))); 
        }
        break;  
 
        /*
         *  Rule 362:  <update_stmt> ::= UPDATE <target_table> <opt_as_target_table> SET <update_assignment_clause> <opt_where_clause>
         */
        case 362: 
        {
            setSym1(m_factory.createUpdateStatement((TableInDatabase)getSym(2), (TableCorrelation)getSym(3), getList(5), (QuerySearchCondition)getSym(6))); 
        }
        break;  
 
        /*
         *  Rule 363:  <value_expr_row> ::= _LPAREN <expression_commalist_multiple_elements> _RPAREN
         */
        case 363: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createValueExpressionRow(getList(2))); 
        }
        break;  
 
        /*
         *  Rule 364:  <values_row> ::= _LPAREN <expression_commalist> _RPAREN
         */
        case 364: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createValuesRow(getList(2))); 
        }
        break;  
 
        /*
         *  Rule 365:  <values_row> ::= <expression>
         */
        case 365: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createValuesRow((QueryValueExpression)getSym(1))); 
        }
        break;  
 
        /*
         *  Rule 366:  <values_row_commalist> ::= <values_row>
         */
        case 366: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createValuesRowList(null, (ValuesRow) getSym(1))); 
        }
        break;  
 
        /*
         *  Rule 367:  <values_row_commalist> ::= <values_row_commalist> _COMMA <values_row>
         */
        case 367: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createValuesRowList(getList(1), (ValuesRow) getSym(3))); 
        }
        break;  
 
        /*
         *  Rule 368:  <with_clause> ::= WITH <with_table_spec_list>
         */
        case 368: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(getList(2)); 
        }
        break;  
 
        /*
         *  Rule 369:  <with_clause> ::= $Empty
         */
        case 369: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(null); 
        }
        break;  
 
        /*
         *  Rule 370:  <with_table_spec_list> ::= <with_table_spec>
         */
        case 370: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createWithTableSpecificationList(null,(WithTableSpecification) getSym(1)));  
        }
        break;  
 
        /*
         *  Rule 371:  <with_table_spec_list> ::= <with_table_spec_list> _COMMA <with_table_spec>
         */
        case 371: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createWithTableSpecificationList(getList(1),(WithTableSpecification) getSym(3)));  
        }
        break;  
 
        /*
         *  Rule 372:  <with_table_spec> ::= <table> <opt_column_name_list> AS _LPAREN <query_exp> _RPAREN
         */
        case 372: 
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

