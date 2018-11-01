/*
* Copyright (c) 2000, 2010 IBM Corporation and others.
* All rights reserved. This program and the accompanying materials 
* are made available under the terms of the Eclipse Public License 2.0
* which is available at
* https://www.eclipse.org/legal/epl-2.0/
*/

package org.eclipse.datatools.sqltools.parsers.sql.query;



import org.eclipse.datatools.modelbase.sql.datatypes.*;
import org.eclipse.datatools.modelbase.sql.query.*;
import org.eclipse.datatools.modelbase.sql.query.helper.*;
import org.eclipse.datatools.modelbase.sql.query.util.*;


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
         *  Rule 12:  <all_or_any_cond> ::= <expression> <relop> ANY <subquery>
         */
        case 12: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createPredicateQuantifiedValueSelect((QueryValueExpression)getSym(1),getInt(2),SQLQueryParserFactory.QUANTIFIER_ANY,(QueryExpressionRoot)getSym(4))); 
        }
        break;  
 
        /*
         *  Rule 13:  <all_or_any_cond> ::= <expression> <relop> SOME <subquery>
         */
        case 13: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createPredicateQuantifiedValueSelect((QueryValueExpression)getSym(1),getInt(2),SQLQueryParserFactory.QUANTIFIER_SOME,(QueryExpressionRoot)getSym(4))); 
        }
        break;  
 
        /*
         *  Rule 14:  <all_or_any_cond> ::= <expression> <relop> ALL <subquery>
         */
        case 14: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createPredicateQuantifiedValueSelect((QueryValueExpression)getSym(1),getInt(2),SQLQueryParserFactory.QUANTIFIER_ALL,(QueryExpressionRoot)getSym(4))); 
        }
        break;  
 
        /*
         *  Rule 15:  <all_or_any_cond> ::= _LPAREN <expression_commalist_multiple_elements> _RPAREN _EQ ANY <subquery>
         */
        case 15: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createPredicateQuantifiedRowSelect(getList(2),SQLQueryParserFactory.QUANTIFIER_ANY,(QueryExpressionRoot)getSym(6))); 
        }
        break;  
 
        /*
         *  Rule 16:  <all_or_any_cond> ::= _LPAREN <expression_commalist_multiple_elements> _RPAREN _EQ SOME <subquery>
         */
        case 16: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createPredicateQuantifiedRowSelect(getList(2),SQLQueryParserFactory.QUANTIFIER_SOME,(QueryExpressionRoot)getSym(6))); 
        }
        break;  
 
        /*
         *  Rule 18:  <argument_list> ::= _LPAREN <opt_argument_list_body> _RPAREN
         */
        case 18: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(getList(2));   
        }
        break;  
 
        /*
         *  Rule 19:  <argument_list_body> ::= <argument>
         */
        case 19: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1( m_factory.listConcat(null, getSym(1)));
    
        }
        break;   
        /*
         *  Rule 20:  <argument_list_body> ::= <argument_list_body> _COMMA <argument>
         */
        case 20: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1( m_factory.listConcat(getList(1), getSym(3)));
    
        }
        break;   
        /*
         *  Rule 23:  <as_alias> ::= <opt_as> <alias_name>
         */
        case 23: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(getString(2)); 
        }
        break;  
 
        /*
         *  Rule 25:  <boolean_expression> ::= <boolean_expression> OR <boolean_term>
         */
        case 25: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
           
				setSym1(m_factory.createCombinedCondition((QuerySearchCondition)getSym(1), (QuerySearchCondition)getSym(3), SQLQueryParserFactory.COMBINED_OPERATOR_OR)); 
        }
        break;   
        /*
         *  Rule 27:  <boolean_term> ::= <boolean_term> AND <boolean_factor>
         */
        case 27: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createCombinedCondition((QuerySearchCondition)getSym(1), (QuerySearchCondition)getSym(3), SQLQueryParserFactory.COMBINED_OPERATOR_AND)); 
        }
        break;  
 
        /*
         *  Rule 29:  <boolean_factor> ::= <boolean_primary> IS <boolean_values>
         */
        case 29: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            m_factory.negateCondition((QuerySearchCondition)getSym(1),!getBoolean(3)); 
        }
        break;  
 
        /*
         *  Rule 30:  <boolean_factor> ::= <boolean_primary> IS NOT <boolean_values>
         */
        case 30: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            m_factory.negateCondition((QuerySearchCondition)getSym(1),getBoolean(4)); 
        }
        break;  
 
        /*
         *  Rule 31:  <boolean_values> ::= TRUE
         */
        case 31: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(new Boolean(true)); 
        }
        break;  
 
        /*
         *  Rule 32:  <boolean_values> ::= FALSE
         */
        case 32: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(new Boolean(false)); 
        }
        break;  
 
        /*
         *  Rule 34:  <boolean_primary> ::= NOT <simplecomp>
         */
        case 34: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.negatePredicate((Predicate)getSym(2),true)); 
        }
        break;  
 
        /*
         *  Rule 35:  <boolean_primary> ::= _LPAREN <boolean_expression> _RPAREN
         */
        case 35: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createNestedCondition((QuerySearchCondition)getSym(2))); 
        }
        break;  
 
        /*
         *  Rule 36:  <boolean_primary> ::= NOT _LPAREN <boolean_expression> _RPAREN
         */
        case 36: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createNestedConditionNegated((QuerySearchCondition)getSym(3))); 
        }
        break;  
 
        /*
         *  Rule 37:  <call_statement> ::= CALL <procedure_object> <opt_argument_list>
         */
        case 37: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            
        setSym1(m_factory.createCallStatement((ProcedureReference)getSym(2), getList(3)));
        
        }
        break;   
        /*
         *  Rule 38:  <case_expression> ::= CASE <case_search_when_list> <opt_case_else> END
         */
        case 38: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createCaseSearchExpression(getList(2), (ValueExpressionCaseElse)getSym(3))); 
        }
        break;  
 
        /*
         *  Rule 39:  <case_expression> ::= CASE <expression> <case_simple_when_list> <opt_case_else> END
         */
        case 39: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createCaseSimpleExpression((QueryValueExpression)getSym(2), getList(3), (ValueExpressionCaseElse)getSym(4))); 
        }
        break;  
 
        /*
         *  Rule 40:  <case_search_when> ::= WHEN <condition> THEN <expression>
         */
        case 40: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createCaseSearchContent((QuerySearchCondition)getSym(2), (QueryValueExpression)getSym(4))); 
        }
        break;  
 
        /*
         *  Rule 41:  <case_search_when_list> ::= <case_search_when>
         */
        case 41: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createCaseSearchList(null,(ValueExpressionCaseSearchContent)getSym(1))); 
        }
        break;  
 
        /*
         *  Rule 42:  <case_search_when_list> ::= <case_search_when_list> <case_search_when>
         */
        case 42: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createCaseSearchList(getList(1),(ValueExpressionCaseSearchContent)getSym(2))); 
        }
        break;  
 
        /*
         *  Rule 43:  <case_simple_when> ::= WHEN <expression> THEN <expression>
         */
        case 43: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createCaseSimpleContent((QueryValueExpression)getSym(2), (QueryValueExpression)getSym(4))); 
        }
        break;  
 
        /*
         *  Rule 44:  <case_simple_when_list> ::= <case_simple_when>
         */
        case 44: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createCaseSimpleList(null,(ValueExpressionCaseSimpleContent)getSym(1))); 
        }
        break;  
 
        /*
         *  Rule 45:  <case_simple_when_list> ::= <case_simple_when_list> <case_simple_when>
         */
        case 45: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createCaseSimpleList(getList(1),(ValueExpressionCaseSimpleContent)getSym(2))); 
        }
        break;  
 
        /*
         *  Rule 46:  <cast_expression> ::= CAST _LPAREN <cast_operand> AS <cast_target> _RPAREN
         */
        case 46: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createCastExpression((QueryValueExpression)getSym(3), (DataType)getSym(5))); 
        }
        break;  
 
        /*
         *  Rule 51:  <column_name> ::= <identifier>
         */
        case 51: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createColumnName(getString(1))); 
        }
        break;  
 
        /*
         *  Rule 52:  <column_name_list> ::= <column_name>
         */
        case 52: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createColumnNameList(null,(ColumnName)getSym(1))); 
        }
        break;  
 
        /*
         *  Rule 53:  <column_name_list> ::= <column_name_list> _COMMA <column_name>
         */
        case 53: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createColumnNameList(getList(1),(ColumnName)getSym(3))); 
        }
        break;  
 
        /*
         *  Rule 54:  <column_ref> ::= <column>
         */
        case 54: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createColumnExpression(getString(1),null)); 
        }
        break;  
 
        /*
         *  Rule 55:  <column_ref> ::= <opt_schema_dot> <table> _DOT <column>
         */
        case 55: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createColumnExpression(getString(4),getString(2),getString(1))); 
        }
        break;  
 
        /*
         *  Rule 57:  <constant> ::= _STRING
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
         *  Rule 58:  <constant> ::= G _STRING
         */
        case 58: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSimpleExpression( "G".concat(getTokenName(2)) ));  //$NON-NLS-1$
			   
        }
        break;   
        /*
         *  Rule 59:  <constant> ::= N _STRING
         */
        case 59: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSimpleExpression( "N".concat(getTokenName(2)) ));  //$NON-NLS-1$
			   
        }
        break;   
        /*
         *  Rule 60:  <constant> ::= HEX_STRING_LITERAL
         */
        case 60: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSimpleExpression( getTokenName(1) ));  //$NON-NLS-1$
           
        }
        break;   
        /*
         *  Rule 61:  <constant> ::= _INTNUMBER
         */
        case 61: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSimpleExpression(getTokenName(1))); 
        }
        break;  
 
        /*
         *  Rule 62:  <constant> ::= _BIGINTEGER
         */
        case 62: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSimpleExpression(getTokenName(1))); 
        }
        break;  
 
        /*
         *  Rule 63:  <constant> ::= _DECIMALNUMBER
         */
        case 63: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSimpleExpression(getTokenName(1))); 
        }
        break;  
 
        /*
         *  Rule 64:  <constant> ::= _REALNUMBER
         */
        case 64: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSimpleExpression(getTokenName(1))); 
        }
        break;  
 
        /*
         *  Rule 71:  <datatype_array_type> ::= <datatype> ARRAY
         */
        case 71: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeArray((DataType)getSym(1), 0, DataTypeHelper.TYPENAME_ARRAY)); 
        }
        break;  
 
        /*
         *  Rule 72:  <datatype_array_type> ::= <datatype> ARRAY <left_bracket_or_trigraph> _INTNUMBER <right_bracket_or_trigraph>
         */
        case 72: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeArray((DataType)getSym(1), Integer.parseInt(getTokenName(4)), DataTypeHelper.TYPENAME_ARRAY )); 
        }
        break;  
 
        /*
         *  Rule 73:  <datatype_multiset_type> ::= <datatype> MULTISET
         */
        case 73: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeMultiset((DataType)getSym(1), DataTypeHelper.TYPENAME_MULTISET )); 
        }
        break;  
 
        /*
         *  Rule 80:  <datatype_date> ::= DATE
         */
        case 80: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeDate( DataTypeHelper.TYPENAME_DATE )); 
        }
        break;  
 
        /*
         *  Rule 81:  <datatype_time> ::= TIME
         */
        case 81: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeTime( SQLQueryParserFactory.PRIMITIVE_TYPE_TIME, 0, DataTypeHelper.TYPENAME_TIME )); 
        }
        break;  
 
        /*
         *  Rule 82:  <datatype_time> ::= TIMESTAMP
         */
        case 82: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeTime( SQLQueryParserFactory.PRIMITIVE_TYPE_TIMESTAMP, 0, DataTypeHelper.TYPENAME_TIMESTAMP )); 
        }
        break;  
 
        /*
         *  Rule 87:  <datatype_numerical_approximate> ::= FLOAT
         */
        case 87: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeNumericApproximate( SQLQueryParserFactory.PRIMITIVE_TYPE_FLOAT, 0, DataTypeHelper.TYPENAME_FLOAT)); 
        }
        break;  
 
        /*
         *  Rule 88:  <datatype_numerical_approximate> ::= FLOAT _LPAREN _INTNUMBER _RPAREN
         */
        case 88: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeNumericApproximate( SQLQueryParserFactory.PRIMITIVE_TYPE_FLOAT, Integer.parseInt(getTokenName(3)), DataTypeHelper.TYPENAME_FLOAT )); 
        }
        break;  
 
        /*
         *  Rule 89:  <datatype_numerical_approximate> ::= REAL
         */
        case 89: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeNumericApproximate( SQLQueryParserFactory.PRIMITIVE_TYPE_REAL, 0, DataTypeHelper.TYPENAME_REAL)); 
        }
        break;  
 
        /*
         *  Rule 90:  <datatype_numerical_approximate> ::= DOUBLE
         */
        case 90: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeNumericApproximate( SQLQueryParserFactory.PRIMITIVE_TYPE_DOUBLE_PRECISION, 0, DataTypeHelper.TYPENAME_DOUBLE )); 
        }
        break;  
 
        /*
         *  Rule 91:  <datatype_numerical_approximate> ::= DOUBLE PRECISION
         */
        case 91: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeNumericApproximate( SQLQueryParserFactory.PRIMITIVE_TYPE_DOUBLE_PRECISION, 0, DataTypeHelper.TYPENAME_DOUBLE_PRECISION )); 
        }
        break;  
 
        /*
         *  Rule 92:  <datatype_numerical_fixed_precision> ::= NUMERIC
         */
        case 92: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeNumericFixedPrecision( SQLQueryParserFactory.PRIMITIVE_TYPE_NUMERIC, 0, 0, DataTypeHelper.TYPENAME_NUMERIC)); 
        }
        break;  
 
        /*
         *  Rule 93:  <datatype_numerical_fixed_precision> ::= DECIMAL
         */
        case 93: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeNumericFixedPrecision( SQLQueryParserFactory.PRIMITIVE_TYPE_DECIMAL, 0, 0, DataTypeHelper.TYPENAME_DECIMAL)); 
        }
        break;  
 
        /*
         *  Rule 94:  <datatype_numerical_fixed_precision> ::= DEC
         */
        case 94: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeNumericFixedPrecision( SQLQueryParserFactory.PRIMITIVE_TYPE_DECIMAL, 0, 0, DataTypeHelper.TYPENAME_DEC)); 
        }
        break;  
 
        /*
         *  Rule 95:  <datatype_numerical_fixed_precision> ::= NUMERIC _LPAREN _INTNUMBER _RPAREN
         */
        case 95: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeNumericFixedPrecision( SQLQueryParserFactory.PRIMITIVE_TYPE_NUMERIC, Integer.parseInt(getTokenName(3)), 0, DataTypeHelper.TYPENAME_NUMERIC)); 
        }
        break;  
 
        /*
         *  Rule 96:  <datatype_numerical_fixed_precision> ::= DECIMAL _LPAREN _INTNUMBER _RPAREN
         */
        case 96: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeNumericFixedPrecision( SQLQueryParserFactory.PRIMITIVE_TYPE_DECIMAL, Integer.parseInt(getTokenName(3)), 0, DataTypeHelper.TYPENAME_DECIMAL)); 
        }
        break;  
 
        /*
         *  Rule 97:  <datatype_numerical_fixed_precision> ::= DEC _LPAREN _INTNUMBER _RPAREN
         */
        case 97: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeNumericFixedPrecision( SQLQueryParserFactory.PRIMITIVE_TYPE_DECIMAL, Integer.parseInt(getTokenName(3)), 0, DataTypeHelper.TYPENAME_DEC)); 
        }
        break;  
 
        /*
         *  Rule 98:  <datatype_numerical_fixed_precision> ::= NUMERIC _LPAREN _INTNUMBER _COMMA _INTNUMBER _RPAREN
         */
        case 98: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeNumericFixedPrecision( SQLQueryParserFactory.PRIMITIVE_TYPE_NUMERIC, Integer.parseInt(getTokenName(3)), Integer.parseInt(getTokenName(5)), DataTypeHelper.TYPENAME_NUMERIC )); 
        }
        break;  
 
        /*
         *  Rule 99:  <datatype_numerical_fixed_precision> ::= DECIMAL _LPAREN _INTNUMBER _COMMA _INTNUMBER _RPAREN
         */
        case 99: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeNumericFixedPrecision( SQLQueryParserFactory.PRIMITIVE_TYPE_DECIMAL, Integer.parseInt(getTokenName(3)), Integer.parseInt(getTokenName(5)), DataTypeHelper.TYPENAME_DECIMAL )); 
        }
        break;  
 
        /*
         *  Rule 100:  <datatype_numerical_fixed_precision> ::= DEC _LPAREN _INTNUMBER _COMMA _INTNUMBER _RPAREN
         */
        case 100: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeNumericFixedPrecision( SQLQueryParserFactory.PRIMITIVE_TYPE_DECIMAL, Integer.parseInt(getTokenName(3)), Integer.parseInt(getTokenName(5)), DataTypeHelper.TYPENAME_DEC )); 
        }
        break;  
 
        /*
         *  Rule 101:  <datatype_numerical_integer> ::= INTEGER
         */
        case 101: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeNumericInteger( SQLQueryParserFactory.PRIMITIVE_TYPE_INTEGER, 0, DataTypeHelper.TYPENAME_INTEGER )); 
        }
        break;  
 
        /*
         *  Rule 102:  <datatype_numerical_integer> ::= INT
         */
        case 102: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeNumericInteger( SQLQueryParserFactory.PRIMITIVE_TYPE_INTEGER, 0, DataTypeHelper.TYPENAME_INT )); 
        }
        break;  
 
        /*
         *  Rule 103:  <datatype_numerical_integer> ::= SMALLINT
         */
        case 103: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeNumericInteger( SQLQueryParserFactory.PRIMITIVE_TYPE_SMALLINT, 0, DataTypeHelper.TYPENAME_SMALLINT )); 
        }
        break;  
 
        /*
         *  Rule 104:  <datatype_numerical_integer> ::= BIGINT
         */
        case 104: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeNumericInteger( SQLQueryParserFactory.PRIMITIVE_TYPE_BIGINT, 0, DataTypeHelper.TYPENAME_BIGINT )); 
        }
        break;  
 
        /*
         *  Rule 105:  <datatype_binary> ::= BLOB
         */
        case 105: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeBinaryString( SQLQueryParserFactory.PRIMITIVE_TYPE_BINARY_LARGE_OBJECT, 0, null, DataTypeHelper.TYPENAME_BLOB )); 
        }
        break;  
 
        /*
         *  Rule 106:  <datatype_binary> ::= BLOB _LPAREN _INTNUMBER <datatype_opt_size_unit> _RPAREN
         */
        case 106: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeBinaryString( SQLQueryParserFactory.PRIMITIVE_TYPE_BINARY_LARGE_OBJECT, Integer.parseInt(getTokenName(3)), getString(4), DataTypeHelper.TYPENAME_BLOB )); 
        }
        break;  
 
        /*
         *  Rule 107:  <datatype_binary> ::= BINARY LARGE OBJECT
         */
        case 107: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeBinaryString( SQLQueryParserFactory.PRIMITIVE_TYPE_BINARY_LARGE_OBJECT, 0, null, DataTypeHelper.TYPENAME_BINARY_LARGE_OBJECT )); 
        }
        break;  
 
        /*
         *  Rule 108:  <datatype_binary> ::= BINARY LARGE OBJECT _LPAREN _INTNUMBER <datatype_opt_size_unit> _RPAREN
         */
        case 108: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeBinaryString( SQLQueryParserFactory.PRIMITIVE_TYPE_BINARY_LARGE_OBJECT, Integer.parseInt(getTokenName(3)), getString(4), DataTypeHelper.TYPENAME_BINARY_LARGE_OBJECT )); 
        }
        break;  
 
        /*
         *  Rule 109:  <datatype_character> ::= CHARACTER
         */
        case 109: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeCharacterString( SQLQueryParserFactory.PRIMITIVE_TYPE_CHARACTER, 0, null, DataTypeHelper.TYPENAME_CHARACTER )); 
        }
        break;  
 
        /*
         *  Rule 110:  <datatype_character> ::= CHAR
         */
        case 110: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeCharacterString( SQLQueryParserFactory.PRIMITIVE_TYPE_CHARACTER, 0, null, DataTypeHelper.TYPENAME_CHAR )); 
        }
        break;  
 
        /*
         *  Rule 111:  <datatype_character> ::= CHARACTER _LPAREN _INTNUMBER _RPAREN
         */
        case 111: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeCharacterString( SQLQueryParserFactory.PRIMITIVE_TYPE_CHARACTER, Integer.parseInt(getTokenName(3)), null, DataTypeHelper.TYPENAME_CHARACTER )); 
        }
        break;  
 
        /*
         *  Rule 112:  <datatype_character> ::= CHAR _LPAREN _INTNUMBER _RPAREN
         */
        case 112: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeCharacterString( SQLQueryParserFactory.PRIMITIVE_TYPE_CHARACTER, Integer.parseInt(getTokenName(3)), null, DataTypeHelper.TYPENAME_CHAR )); 
        }
        break;  
 
        /*
         *  Rule 113:  <datatype_character> ::= CHARACTER VARYING _LPAREN _INTNUMBER _RPAREN
         */
        case 113: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeCharacterString( SQLQueryParserFactory.PRIMITIVE_TYPE_CHARACTER_VARYING, Integer.parseInt(getTokenName(4)), null, DataTypeHelper.TYPENAME_CHARACTER_VARYING )); 
        }
        break;  
 
        /*
         *  Rule 114:  <datatype_character> ::= CHAR VARYING _LPAREN _INTNUMBER _RPAREN
         */
        case 114: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeCharacterString( SQLQueryParserFactory.PRIMITIVE_TYPE_CHARACTER_VARYING, Integer.parseInt(getTokenName(4)), null, DataTypeHelper.TYPENAME_CHAR_VARYING )); 
        }
        break;  
 
        /*
         *  Rule 115:  <datatype_character> ::= VARCHAR _LPAREN _INTNUMBER _RPAREN
         */
        case 115: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeCharacterString( SQLQueryParserFactory.PRIMITIVE_TYPE_CHARACTER_VARYING, Integer.parseInt(getTokenName(3)), null, DataTypeHelper.TYPENAME_VARCHAR )); 
        }
        break;  
 
        /*
         *  Rule 116:  <datatype_character> ::= CLOB
         */
        case 116: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeCharacterString( SQLQueryParserFactory.PRIMITIVE_TYPE_CHARACTER_LARGE_OBJECT, 0, null, DataTypeHelper.TYPENAME_CLOB )); 
        }
        break;  
 
        /*
         *  Rule 117:  <datatype_character> ::= CLOB _LPAREN _INTNUMBER <datatype_opt_size_unit> _RPAREN
         */
        case 117: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeCharacterString( SQLQueryParserFactory.PRIMITIVE_TYPE_CHARACTER_LARGE_OBJECT, Integer.parseInt(getTokenName(3)), getString(4), DataTypeHelper.TYPENAME_CLOB )); 
        }
        break;  
 
        /*
         *  Rule 118:  <datatype_character> ::= CHARACTER LARGE OBJECT
         */
        case 118: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeCharacterString( SQLQueryParserFactory.PRIMITIVE_TYPE_CHARACTER_LARGE_OBJECT, 0, null, DataTypeHelper.TYPENAME_CHARACTER_LARGE_OBJECT )); 
        }
        break;  
 
        /*
         *  Rule 119:  <datatype_character> ::= CHARACTER LARGE OBJECT _LPAREN _INTNUMBER <datatype_opt_size_unit> _RPAREN
         */
        case 119: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeCharacterString( SQLQueryParserFactory.PRIMITIVE_TYPE_CHARACTER_LARGE_OBJECT, Integer.parseInt(getTokenName(5)), getString(6), DataTypeHelper.TYPENAME_CHARACTER_LARGE_OBJECT )); 
        }
        break;  
 
        /*
         *  Rule 120:  <datatype_character> ::= CHAR LARGE OBJECT
         */
        case 120: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeCharacterString( SQLQueryParserFactory.PRIMITIVE_TYPE_CHARACTER_LARGE_OBJECT, 0, null, DataTypeHelper.TYPENAME_CHAR_LARGE_OBJECT )); 
        }
        break;  
 
        /*
         *  Rule 121:  <datatype_character> ::= CHAR LARGE OBJECT _LPAREN _INTNUMBER <datatype_opt_size_unit> _RPAREN
         */
        case 121: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
           	setSym1(m_factory.createDataTypeCharacterString( SQLQueryParserFactory.PRIMITIVE_TYPE_CHARACTER_LARGE_OBJECT, Integer.parseInt(getTokenName(5)), getString(6), DataTypeHelper.TYPENAME_CHAR_LARGE_OBJECT )); 
        }
        break;  
 
        /*
         *  Rule 122:  <datatype_character_national> ::= GRAPHIC
         */
        case 122: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeCharacterString( SQLQueryParserFactory.PRIMITIVE_TYPE_NATIONAL_CHARACTER, 0, null, DataTypeHelper.TYPENAME_GRAPHIC )); 
        }
        break;  
 
        /*
         *  Rule 123:  <datatype_character_national> ::= GRAPHIC _LPAREN _INTNUMBER _RPAREN
         */
        case 123: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeCharacterString( SQLQueryParserFactory.PRIMITIVE_TYPE_NATIONAL_CHARACTER, Integer.parseInt(getTokenName(3)), null, DataTypeHelper.TYPENAME_GRAPHIC )); 
        }
        break;  
 
        /*
         *  Rule 124:  <datatype_character_national> ::= VARGRAPHIC _LPAREN _INTNUMBER _RPAREN
         */
        case 124: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeCharacterString( SQLQueryParserFactory.PRIMITIVE_TYPE_NATIONAL_CHARACTER_VARYING, Integer.parseInt(getTokenName(3)), null, DataTypeHelper.TYPENAME_VARGRAPHIC )); 
        }
        break;  
 
        /*
         *  Rule 125:  <datatype_character_national> ::= DBCLOB
         */
        case 125: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeCharacterString( SQLQueryParserFactory.PRIMITIVE_TYPE_NATIONAL_CHARACTER_LARGE_OBJECT, 0, null, DataTypeHelper.TYPENAME_DBCLOB )); 
        }
        break;  
 
        /*
         *  Rule 126:  <datatype_character_national> ::= DBCLOB _LPAREN _INTNUMBER <datatype_opt_size_unit> _RPAREN
         */
        case 126: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeCharacterString( SQLQueryParserFactory.PRIMITIVE_TYPE_NATIONAL_CHARACTER_LARGE_OBJECT, Integer.parseInt(getTokenName(3)), getString(4), DataTypeHelper.TYPENAME_DBCLOB )); 
        }
        break;  
 
        /*
         *  Rule 127:  <datatype_opt_size_unit> ::= K
         */
        case 127: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1( getTokenName(1) ); 
        }
        break;  
 
        /*
         *  Rule 128:  <datatype_opt_size_unit> ::= M
         */
        case 128: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1( getTokenName(1) ); 
        }
        break;  
 
        /*
         *  Rule 129:  <datatype_opt_size_unit> ::= G
         */
        case 129: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1( getTokenName(1) ); 
        }
        break;  
 
        /*
         *  Rule 130:  <datatype_opt_size_unit> ::= $Empty
         */
        case 130: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1( null ); 
        }
        break;  
 
        /*
         *  Rule 131:  <datatype_path-resolved_user-defined_type_name> ::= <opt_schema_dot> <identifier>
         */
        case 131: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDataTypeUserDefinedType(getString(1), getString(2))); 
        }
        break;  
 
        /*
         *  Rule 132:  <datatype_user_defined_distinct> ::= <identifier>
         */
        case 132: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDistinctUserDefinedType(getString(1))); 
        }
        break;  
 
        /*
         *  Rule 134:  <default_option> ::= USER
         */
        case 134: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSpecialRegisterExpression(SQLQueryParserFactory.SPECIAL_REGISTER_USER)); 
        }
        break;  
 
        /*
         *  Rule 135:  <default_option> ::= CURRENT_USER
         */
        case 135: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSpecialRegisterExpression(SQLQueryParserFactory.SPECIAL_REGISTER_CURRENT_USER)); 
        }
        break;  
 
        /*
         *  Rule 136:  <default_option> ::= CURRENT_ROLE
         */
        case 136: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSpecialRegisterExpression(SQLQueryParserFactory.SPECIAL_REGISTER_CURRENT_ROLE)); 
        }
        break;  
 
        /*
         *  Rule 137:  <default_option> ::= SESSION_USER
         */
        case 137: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSpecialRegisterExpression(SQLQueryParserFactory.SPECIAL_REGISTER_SESSION_USER)); 
        }
        break;  
 
        /*
         *  Rule 138:  <default_option> ::= SYSTEM_USER
         */
        case 138: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSpecialRegisterExpression(SQLQueryParserFactory.SPECIAL_REGISTER_SYSTEM_USER)); 
        }
        break;  
 
        /*
         *  Rule 139:  <default_option> ::= CURRENT_PATH
         */
        case 139: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSpecialRegisterExpression(SQLQueryParserFactory.SPECIAL_REGISTER_CURRENT_PATH)); 
        }
        break;  
 
        /*
         *  Rule 140:  <delete_stmt> ::= DELETE FROM <target_table> <opt_as_target_table> <opt_where_clause>
         */
        case 140: 
        {
            setSym1(m_factory.createDeleteStatement((TableInDatabase)getSym(3), (TableCorrelation)getSym(4), (QuerySearchCondition)getSym(5))); 
        }
        break;  
 
        /*
         *  Rule 141:  <derived_column_list> ::= <column_ref>
         */
        case 141: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createColumnList(null,(ValueExpressionColumn) getSym(1)));  
        }
        break;  
 
        /*
         *  Rule 142:  <derived_column_list> ::= <target_column_list> _COMMA <column_ref>
         */
        case 142: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createColumnList(getList(1),(ValueExpressionColumn) getSym(3)));  
        }
        break;  
 
        /*
         *  Rule 143:  <duration> ::= DAYS
         */
        case 143: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setInt1(SQLQueryParserFactory.DURATION_TYPE_DAYS); 
        }
        break;  
 
        /*
         *  Rule 144:  <duration> ::= HOURS
         */
        case 144: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setInt1(SQLQueryParserFactory.DURATION_TYPE_HOURS); 
        }
        break;  
 
        /*
         *  Rule 145:  <duration> ::= MICROSECONDS
         */
        case 145: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setInt1(SQLQueryParserFactory.DURATION_TYPE_MICROSECONDS); 
        }
        break;  
 
        /*
         *  Rule 146:  <duration> ::= MINUTES
         */
        case 146: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setInt1(SQLQueryParserFactory.DURATION_TYPE_MINUTES); 
        }
        break;  
 
        /*
         *  Rule 147:  <duration> ::= MONTHS
         */
        case 147: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setInt1(SQLQueryParserFactory.DURATION_TYPE_MONTHS); 
        }
        break;  
 
        /*
         *  Rule 148:  <duration> ::= SECONDS
         */
        case 148: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setInt1(SQLQueryParserFactory.DURATION_TYPE_SECONDS); 
        }
        break;  
 
        /*
         *  Rule 149:  <duration> ::= YEARS
         */
        case 149: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setInt1(SQLQueryParserFactory.DURATION_TYPE_YEARS); 
        }
        break;  
 
        /*
         *  Rule 150:  <exists> ::= EXISTS _LPAREN <query_exp> _RPAREN
         */
        case 150: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1( m_factory.createPredicateExists((QueryExpressionBody) getSym(3)) ); 
        }
        break;  
 
        /*
         *  Rule 151:  <expression> ::= <expression> _PLUS <expression_term>
         */
        case 151: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createCombinedExpression((QueryValueExpression) getSym(1),SQLQueryParserFactory.COMBINED_OPERATOR_ADD,(QueryValueExpression) getSym(3))); 
        }
        break;  
 
        /*
         *  Rule 152:  <expression> ::= <expression> _MINUS <expression_term>
         */
        case 152: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createCombinedExpression((QueryValueExpression) getSym(1),SQLQueryParserFactory.COMBINED_OPERATOR_SUBTRACT,(QueryValueExpression) getSym(3))); 
        }
        break;  
 
        /*
         *  Rule 154:  <expression_commalist> ::= <expression>
         */
        case 154: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createExpressionList(null, (QueryValueExpression) getSym(1))); 
        }
        break;  
 
        /*
         *  Rule 155:  <expression_commalist> ::= <expression_commalist> _COMMA <expression>
         */
        case 155: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createExpressionList(getList(1), (QueryValueExpression) getSym(3))); 
        }
        break;  
 
        /*
         *  Rule 157:  <expression_commalist_multiple_elements> ::= <expression_commalist> _COMMA <expression>
         */
        case 157: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createExpressionList(getList(1), (QueryValueExpression) getSym(3))); 
        }
        break;  
 
        /*
         *  Rule 158:  <expression_factor> ::= _LPAREN <expression> _RPAREN
         */
        case 158: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createNestedExpression((QueryValueExpression) getSym(2))); 
        }
        break;  
 
        /*
         *  Rule 159:  <expression_factor> ::= <subquery>
         */
        case 159: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createScalarSelectExpression((QueryExpressionRoot) getSym(1))); 
        }
        break;  
 
        /*
         *  Rule 167:  <expression_factor> ::= _PLUS <expression_factor>
         */
        case 167: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1( m_factory.setUnaryOperator((QueryValueExpression)getSym(2),SQLQueryParserFactory.UNARY_OPERATOR_PLUS) ); 
        }
        break;  
 
        /*
         *  Rule 168:  <expression_factor> ::= _MINUS <expression_factor>
         */
        case 168: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1( m_factory.setUnaryOperator((QueryValueExpression)getSym(2),SQLQueryParserFactory.UNARY_OPERATOR_MINUS) ); 
        }
        break;  
 
        /*
         *  Rule 169:  <expression_factor> ::= DEFAULT
         */
        case 169: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createDefaultExpression()); 
        }
        break;  
 
        /*
         *  Rule 170:  <expression_factor> ::= NULL
         */
        case 170: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createNullExpression()); 
        }
        break;  
 
        /*
         *  Rule 171:  <expression_term> ::= <expression_term> _STAR <expression_factor>
         */
        case 171: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createCombinedExpression((QueryValueExpression) getSym(1),SQLQueryParserFactory.COMBINED_OPERATOR_MULTIPLY,(QueryValueExpression) getSym(3))); 
        }
        break;  
 
        /*
         *  Rule 172:  <expression_term> ::= <expression_term> _SLASH <expression_factor>
         */
        case 172: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createCombinedExpression((QueryValueExpression) getSym(1),SQLQueryParserFactory.COMBINED_OPERATOR_DIVIDE,(QueryValueExpression) getSym(3))); 
        }
        break;  
 
        /*
         *  Rule 173:  <expression_term> ::= <expression_term> CONCAT <expression_factor>
         */
        case 173: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createCombinedExpression((QueryValueExpression) getSym(1),SQLQueryParserFactory.COMBINED_OPERATOR_CONCATENATE,(QueryValueExpression) getSym(3))); 
        }
        break;  
 
        /*
         *  Rule 174:  <expression_term> ::= <expression_term> _CONCAT_OPERATOR <expression_factor>
         */
        case 174: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createCombinedExpression((QueryValueExpression) getSym(1),SQLQueryParserFactory.COMBINED_OPERATOR_CONCATENATE,(QueryValueExpression) getSym(3))); 
        }
        break;  
 
        /*
         *  Rule 175:  <expression_term> ::= <expression_term> <duration>
         */
        case 175: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createLabeledDurationExpression((QueryValueExpression) getSym(1), getInt(2))); 
        }
        break;  
 
        /*
         *  Rule 178:  <fetch_first_clause> ::= FETCH FIRST <opt_fetch_first_row_count> <row_or_rows> ONLY
         */
        case 178: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setInt1(getInt(3)); 
        }
        break;  
 
        /*
         *  Rule 180:  <func_ref> ::= <alias_name> _DOT <identifier>
         */
        case 180: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(new String((getString(1).concat(".")).concat(getString(3))));  //$NON-NLS-1$
			   
        }
        break;   
        /*
         *  Rule 181:  <function> ::= <opt_schema_dot> <identifier> _LPAREN _STAR _RPAREN
         */
        case 181: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createFunctionExpression(getString(2), null, null, getString(1))); 
        }
        break;  
 
        /*
         *  Rule 182:  <function> ::= <opt_schema_dot> <identifier> _LPAREN <opt_all_distinct> <opt_expression_commalist> _RPAREN
         */
        case 182: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createFunctionExpression(getString(2), getString(4), getList(5), getString(1))); 
        }
        break;  
 
        /*
         *  Rule 185:  <grouping_exp> ::= <expression>
         */
        case 185: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1( m_factory.createGroupingExpression((QueryValueExpression) getSym(1)) ); 
        }
        break;  
 
        /*
         *  Rule 186:  <grouping_sets> ::= GROUPING SETS _LPAREN <grouping_sets_element_list> _RPAREN
         */
        case 186: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1( m_factory.createGroupingSets(getList(4)) ); 
        }
        break;  
 
        /*
         *  Rule 187:  <grouping_sets_element_list> ::= <grouping_sets_element>
         */
        case 187: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createGroupingSetsElementList(null,(GroupingSetsElement) getSym(1)));  
        }
        break;  
 
        /*
         *  Rule 188:  <grouping_sets_element_list> ::= <grouping_sets_element_list> _COMMA <grouping_sets_element>
         */
        case 188: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createGroupingSetsElementList(getList(1),(GroupingSetsElement) getSym(3)));  
        }
        break;  
 
        /*
         *  Rule 189:  <grouping_sets_element> ::= _LPAREN <grouping_sets_element_exp_list> _RPAREN
         */
        case 189: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1( m_factory.createGroupingSetsElementSublist(getList(2)) );  
        }
        break;  
 
        /*
         *  Rule 191:  <grouping_sets_element_exp> ::= <grouping>
         */
        case 191: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1( m_factory.createGroupingSetsElementExpression((Grouping) getSym(1)) ); 
        }
        break;  
 
        /*
         *  Rule 192:  <grouping_sets_element_exp_list> ::= <grouping_sets_element_exp>
         */
        case 192: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createGroupingSetsElementExprList(null,(GroupingSetsElementExpression) getSym(1)));  
        }
        break;  
 
        /*
         *  Rule 193:  <grouping_sets_element_exp_list> ::= <grouping_sets_element_exp_list> _COMMA <grouping_sets_element_exp>
         */
        case 193: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createGroupingSetsElementExprList(getList(1),(GroupingSetsElementExpression) getSym(3)));  
        }
        break;  
 
        /*
         *  Rule 196:  <grouping_spec_list> ::= <grouping_spec>
         */
        case 196: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createGroupingSpecificationList(null,(GroupingSpecification) getSym(1)));  
        }
        break;  
 
        /*
         *  Rule 197:  <grouping_spec_list> ::= <grouping_spec_list> _COMMA <grouping_spec>
         */
        case 197: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createGroupingSpecificationList(getList(1),(GroupingSpecification) getSym(3)));  
        }
        break;  
 
        /*
         *  Rule 198:  <identifier> ::= REGULAR_IDENTIFIER
         */
        case 198: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(getTokenName(1)); 
        }
        break;  
 
        /*
         *  Rule 199:  <identifier> ::= DELIMITED_IDENTIFIER
         */
        case 199: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(getTokenName(1)); 
        }
        break;  
 
        /*
         *  Rule 201:  <in_cond> ::= <expression> NOT IN _LPAREN <expression_commalist> _RPAREN
         */
        case 201: 
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
         *  Rule 202:  <in_cond> ::= <expression> IN _LPAREN <expression_commalist> _RPAREN
         */
        case 202: 
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
         *  Rule 203:  <in_cond> ::= <expression> NOT IN <subquery>
         */
        case 203: 
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
         *  Rule 204:  <in_cond> ::= <expression> IN <subquery>
         */
        case 204: 
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
         *  Rule 205:  <in_cond> ::= _LPAREN <expression_commalist> _COMMA <expression> _RPAREN NOT IN <subquery>
         */
        case 205: 
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
         *  Rule 206:  <in_cond> ::= _LPAREN <expression_commalist> _COMMA <expression> _RPAREN IN <subquery>
         */
        case 206: 
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
         *  Rule 207:  <insert_row> ::= _LPAREN <expression_commalist_multiple_elements> _RPAREN
         */
        case 207: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createInsertValuesRow(getList(2))); 
        }
        break;  
 
        /*
         *  Rule 208:  <insert_row> ::= <expression>
         */
        case 208: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createInsertValuesRow((QueryValueExpression)getSym(1))); 
        }
        break;  
 
        /*
         *  Rule 209:  <insert_row_commalist> ::= <insert_row>
         */
        case 209: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createInsertRow(null, (ValuesRow) getSym(1))); 
        }
        break;  
 
        /*
         *  Rule 210:  <insert_row_commalist> ::= <insert_row_commalist> _COMMA <insert_row>
         */
        case 210: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createInsertRow(getList(1), (ValuesRow) getSym(3))); 
        }
        break;  
 
        /*
         *  Rule 211:  <insert_stmt> ::= INSERT INTO <target_table> <opt_target_column_list> VALUES <insert_row_commalist>
         */
        case 211: 
        {
            setSym1(m_factory.createInsertStatement((TableInDatabase) getSym(3), getList(4), getList(6))); 
        }
        break;  
 
        /*
         *  Rule 212:  <insert_stmt> ::= INSERT INTO <target_table> <opt_target_column_list> <query_exp_root>
         */
        case 212: 
        {
            setSym1(m_factory.createInsertStatement((TableInDatabase)getSym(3), getList(4), (QueryExpressionRoot)getSym(5))); 
        }
        break;  
 
        /*
         *  Rule 213:  <intervaltest> ::= <expression> NOT BETWEEN <expression> AND <expression>
         */
        case 213: 
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
         *  Rule 214:  <intervaltest> ::= <expression> BETWEEN <expression> AND <expression>
         */
        case 214: 
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
         *  Rule 217:  <liketest> ::= <expression> NOT LIKE <expression> <opt_escape>
         */
        case 217: 
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
         *  Rule 218:  <liketest> ::= <expression> LIKE <expression> <opt_escape>
         */
        case 218: 
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
         *  Rule 219:  <merge statement> ::= MERGE INTO <merge target table> USING <merge source table> ON <merge on condition> <merge operation specification list>
         */
        case 219: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1( m_factory.createMergeStatement((MergeTargetTable) getSym(3), (MergeSourceTable) getSym(5), (MergeOnCondition) getSym(7), getList(8)) ); 
        }
        break;  
 
        /*
         *  Rule 220:  <merge target table> ::= <target_table> <opt_as_alias>
         */
        case 220: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1( m_factory.createMergeTargetTable((TableInDatabase) getSym(1), getString(2)) ); 
        }
        break;  
 
        /*
         *  Rule 221:  <merge source table> ::= <table_ref>
         */
        case 221: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1( m_factory.createMergeSourceTable((TableReference) getSym(1)) ); 
        }
        break;  
 
        /*
         *  Rule 222:  <merge on condition> ::= <condition>
         */
        case 222: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1( m_factory.createMergeOnCondition((QuerySearchCondition) getSym(1)) ); 
        }
        break;  
 
        /*
         *  Rule 223:  <merge operation specification list> ::= <merge when clause>
         */
        case 223: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1( m_factory.createMergeOperatationSpecificationList(null, (MergeOperationSpecification) getSym(1)) ); 
        }
        break;  
 
        /*
         *  Rule 224:  <merge operation specification list> ::= <merge operation specification list> <merge when clause>
         */
        case 224: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1( m_factory.createMergeOperatationSpecificationList(getList(1), (MergeOperationSpecification) getSym(2)) ); 
        }
        break;  
 
        /*
         *  Rule 227:  <merge when matched clause> ::= WHEN MATCHED THEN <merge update specification>
         */
        case 227: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(getSym(4)); 
        }
        break;  
 
        /*
         *  Rule 228:  <merge when not matched clause> ::= WHEN NOT MATCHED THEN <merge insert specification>
         */
        case 228: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(getSym(5)); 
        }
        break;  
 
        /*
         *  Rule 229:  <merge update specification> ::= UPDATE SET <update_assignment_clause>
         */
        case 229: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1( m_factory.createMergeUpdateSpecification(getList(3)) ); 
        }
        break;  
 
        /*
         *  Rule 230:  <merge insert specification> ::= INSERT <opt_target_column_list> VALUES <insert_row>
         */
        case 230: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1( m_factory.createMergeInsertSpecification(getList(2), (ValuesRow)getSym(4)) );  
        }
        break;  
 
        /*
         *  Rule 231:  <nulltest> ::= <expression> IS NOT NULL
         */
        case 231: 
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
         *  Rule 232:  <nulltest> ::= <expression> IS NULL
         */
        case 232: 
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
         *  Rule 233:  <null_specification> ::= NULL
         */
        case 233: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createNullExpression()); 
        }
        break;  
 
        /*
         *  Rule 234:  <opt_all_distinct> ::= $Empty
         */
        case 234: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1((String)null); 
        }
        break;  
 
        /*
         *  Rule 235:  <opt_all_distinct> ::= ALL
         */
        case 235: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(SQLQueryParserFactory.ALL); 
        }
        break;  
 
        /*
         *  Rule 236:  <opt_all_distinct> ::= DISTINCT
         */
        case 236: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(SQLQueryParserFactory.DISTINCT); 
        }
        break;  
 
        /*
         *  Rule 244:  <opt_as_alias> ::= $Empty
         */
        case 244: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(null); 
        }
        break;  
 
        /*
         *  Rule 245:  <opt_as_target_table> ::= <opt_as> <table>
         */
        case 245: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createTableCorrelation(getString(2))); 
        }
        break;  
 
        /*
         *  Rule 246:  <opt_as_target_table> ::= $Empty
         */
        case 246: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(null); 
        }
        break;  
 
        /*
         *  Rule 247:  <opt_asc_desc> ::= ASC
         */
        case 247: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setInt1(SQLQueryParserFactory.ORDERING_SPEC_TYPE_ASC); 
        }
        break;  
 
        /*
         *  Rule 248:  <opt_asc_desc> ::= DESC
         */
        case 248: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setInt1(SQLQueryParserFactory.ORDERING_SPEC_TYPE_DESC); 
        }
        break;  
 
        /*
         *  Rule 249:  <opt_asc_desc> ::= $Empty
         */
        case 249: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setInt1(SQLQueryParserFactory.ORDERING_SPEC_TYPE_NONE); 
        }
        break;  
 
        /*
         *  Rule 250:  <opt_case_else> ::= ELSE <expression>
         */
        case 250: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createCaseElse((QueryValueExpression)getSym(2))); 
        }
        break;  
 
        /*
         *  Rule 251:  <opt_case_else> ::= $Empty
         */
        case 251: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(null); 
        }
        break;  
 
        /*
         *  Rule 252:  <opt_column_name_list> ::= _LPAREN <column_name_list> _RPAREN
         */
        case 252: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(getList(2)); 
        }
        break;  
 
        /*
         *  Rule 253:  <opt_column_name_list> ::= $Empty
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
         *  Rule 254:  <opt_default_clause> ::= DEFAULT <default_option>
         */
        case 254: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(getSym(2)); 
        }
        break;  
 
        /*
         *  Rule 255:  <opt_default_clause> ::= $Empty
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
         *  Rule 256:  <opt_escape> ::= ESCAPE _STRING
         */
        case 256: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSimpleExpression(getTokenName(2))); 
        }
        break;  
 
        /*
         *  Rule 257:  <opt_escape> ::= $Empty
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
         *  Rule 259:  <opt_expression_commalist> ::= $Empty
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
         *  Rule 261:  <opt_fetch_first_clause> ::= $Empty
         */
        case 261: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setInt1(0); 
        }
        break;  
 
        /*
         *  Rule 262:  <opt_group_by_clause> ::= GROUP BY <grouping_spec_list>
         */
        case 262: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(getList(3)); 
        }
        break;  
 
        /*
         *  Rule 263:  <opt_group_by_clause> ::= GROUP BY <super_groups_element_list> WITH CUBE
         */
        case 263: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1( m_factory.createGroupingSpecificationList(null, m_factory.createSuperGroups(getList(3),SQLQueryParserFactory.SUPERGROUP_TYPE_CUBE)) ); 
        }
        break;  
 
        /*
         *  Rule 264:  <opt_group_by_clause> ::= GROUP BY <super_groups_element_list> WITH ROLLUP
         */
        case 264: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1( m_factory.createGroupingSpecificationList(null, m_factory.createSuperGroups(getList(3),SQLQueryParserFactory.SUPERGROUP_TYPE_ROLLUP)) ); 
        }
        break;  
 
        /*
         *  Rule 265:  <opt_group_by_clause> ::= $Empty
         */
        case 265: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1((List)null); 
        }
        break;  
 
        /*
         *  Rule 266:  <opt_having_clause> ::= HAVING <condition>
         */
        case 266: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(getSym(2)); 
        }
        break;  
 
        /*
         *  Rule 267:  <opt_having_clause> ::= $Empty
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
         *  Rule 268:  <opt_join_type> ::= INNER
         */
        case 268: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setInt1(SQLQueryParserFactory.JOIN_EXPLICIT_INNER); 
        }
        break;  
 
        /*
         *  Rule 269:  <opt_join_type> ::= LEFT <opt_join_type_outer>
         */
        case 269: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setInt1(SQLQueryParserFactory.JOIN_LEFT_OUTER); 
        }
        break;  
 
        /*
         *  Rule 270:  <opt_join_type> ::= RIGHT <opt_join_type_outer>
         */
        case 270: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setInt1(SQLQueryParserFactory.JOIN_RIGHT_OUTER); 
        }
        break;  
 
        /*
         *  Rule 271:  <opt_join_type> ::= FULL <opt_join_type_outer>
         */
        case 271: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setInt1(SQLQueryParserFactory.JOIN_FULL_OUTER); 
        }
        break;  
 
        /*
         *  Rule 272:  <opt_join_type> ::= $Empty
         */
        case 272: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setInt1(SQLQueryParserFactory.JOIN_DEFAULT_INNER); 
        }
        break;  
 
        /*
         *  Rule 275:  <opt_null_order> ::= NULLS FIRST
         */
        case 275: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setInt1(SQLQueryParserFactory.NULL_ORDERING_TYPE_NULLS_FIRST); 
        }
        break;  
 
        /*
         *  Rule 276:  <opt_null_order> ::= NULLS LAST
         */
        case 276: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setInt1(SQLQueryParserFactory.NULL_ORDERING_TYPE_NULLS_LAST); 
        }
        break;  
 
        /*
         *  Rule 277:  <opt_null_order> ::= $Empty
         */
        case 277: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setInt1(SQLQueryParserFactory.NULL_ORDERING_TYPE_NONE); 
        }
        break;  
 
        /*
         *  Rule 278:  <opt_order_by_clause> ::= ORDER BY <ordering_spec_commalist>
         */
        case 278: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(getList(3)); 
        }
        break;  
 
        /*
         *  Rule 279:  <opt_order_by_clause> ::= $Empty
         */
        case 279: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1((List)null); 
        }
        break;  
 
        /*
         *  Rule 280:  <opt_schema_dot> ::= <schema> _DOT
         */
        case 280: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(getString(1)); 
        }
        break;  
 
        /*
         *  Rule 281:  <opt_schema_dot> ::= $Empty
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
         *  Rule 283:  <opt_table_correlation> ::= $Empty
         */
        case 283: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(null); 
        }
        break;  
 
        /*
         *  Rule 284:  <opt_target_column_list> ::= _LPAREN <target_column_list> _RPAREN
         */
        case 284: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(getList(2)); 
        }
        break;  
 
        /*
         *  Rule 285:  <opt_target_column_list> ::= $Empty
         */
        case 285: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(null); 
        }
        break;  
 
        /*
         *  Rule 286:  <opt_updatability_clause> ::= <updatability_expression>
         */
        case 286: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(getSym(1)); 
        }
        break;  
 
        /*
         *  Rule 287:  <opt_updatability_clause> ::= $Empty
         */
        case 287: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(null); 
        }
        break;  
 
        /*
         *  Rule 288:  <opt_fetch_first_row_count> ::= <unsigned_integer>
         */
        case 288: 
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
         *  Rule 289:  <opt_fetch_first_row_count> ::= $Empty
         */
        case 289: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setInt1(1); 
        }
        break;  
 
        /*
         *  Rule 290:  <opt_where_clause> ::= WHERE <condition>
         */
        case 290: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1_keepSpan(getSym(2)); 
        }
        break;  
 
        /*
         *  Rule 291:  <opt_where_clause> ::= $Empty
         */
        case 291: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(null);  
        }
        break;  
 
        /*
         *  Rule 292:  <ordering_spec> ::= <expression> <opt_asc_desc> <opt_null_order>
         */
        case 292: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createOrderByExpression((QueryValueExpression)getSym(1), getInt(2), getInt(3))); 
        }
        break;  
 
        /*
         *  Rule 293:  <ordering_spec_commalist> ::= <ordering_spec>
         */
        case 293: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createOrderByClause(null,(OrderBySpecification) getSym(1))); 
        }
        break;  
 
        /*
         *  Rule 294:  <ordering_spec_commalist> ::= <ordering_spec_commalist> _COMMA <ordering_spec>
         */
        case 294: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createOrderByClause(getList(1),(OrderBySpecification) getSym(3))); 
        }
        break;  
 
        /*
         *  Rule 295:  <parameter> ::= _HOSTVAR
         */
        case 295: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createVariableExpression(getTokenName(1))); 
        }
        break;  
 
        /*
         *  Rule 296:  <parameter> ::= _PARAM_MARKER
         */
        case 296: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createVariableExpression(null)); 
        }
        break;  
 
        /*
         *  Rule 297:  <procedure_object> ::= <opt_schema_dot> <identifier>
         */
        case 297: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1( m_factory.createProcedureReference(getString(1), getString(2)));
    
        }
        break;   
        /*
         *  Rule 298:  <project> ::= <expression> <opt_as_alias>
         */
        case 298: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createResultColumn((QueryValueExpression) getSym(1), getString(2))); 
        }
        break;  
 
        /*
         *  Rule 299:  <project> ::= _STAR
         */
        case 299: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(null); 
        }
        break;  
 
        /*
         *  Rule 300:  <project> ::= <table> _DOT _STAR
         */
        case 300: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createResultTableAllColumns(getString(1))); 
        }
        break;  
 
        /*
         *  Rule 301:  <project> ::= <schema> _DOT <table> _DOT _STAR
         */
        case 301: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createResultTableAllColumns(getString(3), getString(1))); 
        }
        break;  
 
        /*
         *  Rule 302:  <query_combined> ::= <query_exp> <query_combined_operator> <query_term> <opt_order_by_clause> <opt_fetch_first_clause>
         */
        case 302: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createQueryCombined((QueryExpressionBody) getSym(1),getInt(2),(QueryExpressionBody) getSym(3), getList(4), getInt(5)));  
        }
        break;  
 
        /*
         *  Rule 303:  <query_combined_operator> ::= UNION
         */
        case 303: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setInt1(SQLQueryParserFactory.QUERY_COMBINED_UNION); 
        }
        break;  
 
        /*
         *  Rule 304:  <query_combined_operator> ::= UNION ALL
         */
        case 304: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setInt1(SQLQueryParserFactory.QUERY_COMBINED_UNION_ALL); 
        }
        break;  
 
        /*
         *  Rule 305:  <query_combined_operator> ::= INTERSECT
         */
        case 305: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setInt1(SQLQueryParserFactory.QUERY_COMBINED_INTERSECT); 
        }
        break;  
 
        /*
         *  Rule 306:  <query_combined_operator> ::= INTERSECT ALL
         */
        case 306: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setInt1(SQLQueryParserFactory.QUERY_COMBINED_INTERSECT_ALL); 
        }
        break;  
 
        /*
         *  Rule 307:  <query_combined_operator> ::= EXCEPT
         */
        case 307: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setInt1(SQLQueryParserFactory.QUERY_COMBINED_EXCEPT); 
        }
        break;  
 
        /*
         *  Rule 308:  <query_combined_operator> ::= EXCEPT ALL
         */
        case 308: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setInt1(SQLQueryParserFactory.QUERY_COMBINED_EXCEPT_ALL); 
        }
        break;  
 
        /*
         *  Rule 311:  <query_exp_root> ::= <with_clause> <query_exp>
         */
        case 311: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1( m_factory.createQueryExpressionRoot((QueryExpressionBody)getSym(2), getList(1)) ); 
        }
        break;  
 
        /*
         *  Rule 312:  <query_select> ::= SELECT <opt_all_distinct> <selection> FROM <table_ref_commalist> <opt_where_clause> <opt_group_by_clause> <opt_having_clause> <opt_order_by_clause> <opt_fetch_first_clause>
         */
        case 312: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createQuerySelect(getString(2),getList(3),getList(5),(QuerySearchCondition)getSym(6),getList(7),(QuerySearchCondition)getSym(8), getList(9), getInt(10))); 
        }
        break;  
 
        /*
         *  Rule 313:  <query_stmt> ::= <query_exp_root> <opt_order_by_clause> <opt_updatability_clause>
         */
        case 313: 
        {
            setSym1(m_factory.createSelectStatement((QueryExpressionRoot)getSym(1), getList(2), (UpdatabilityExpression) getSym(3))); 
        }
        break;  
 
        /*
         *  Rule 316:  <query_term> ::= _LPAREN <query_exp> _RPAREN <opt_order_by_clause> <opt_fetch_first_clause>
         */
        case 316: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1( m_factory.createQueryNested((QueryExpressionBody)getSym(2), getList(4), getInt(5)));  
        }
        break;  
 
        /*
         *  Rule 317:  <query_values> ::= VALUES <values_row_commalist> <opt_order_by_clause> <opt_fetch_first_clause>
         */
        case 317: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createQueryValues(getList(2), getList(3), getInt(4))); 
        }
        break;  
 
        /*
         *  Rule 318:  <relop> ::= _EQ
         */
        case 318: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setInt1(SQLQueryParserFactory.COMPARISON_OPERATOR_EQ); 
        }
        break;  
 
        /*
         *  Rule 319:  <relop> ::= _LT
         */
        case 319: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setInt1(SQLQueryParserFactory.COMPARISON_OPERATOR_LT); 
        }
        break;  
 
        /*
         *  Rule 320:  <relop> ::= _LE
         */
        case 320: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setInt1(SQLQueryParserFactory.COMPARISON_OPERATOR_LE); 
        }
        break;  
 
        /*
         *  Rule 321:  <relop> ::= _NE
         */
        case 321: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setInt1(SQLQueryParserFactory.COMPARISON_OPERATOR_NE); 
        }
        break;  
 
        /*
         *  Rule 322:  <relop> ::= _GT
         */
        case 322: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setInt1(SQLQueryParserFactory.COMPARISON_OPERATOR_GT); 
        }
        break;  
 
        /*
         *  Rule 323:  <relop> ::= _GE
         */
        case 323: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setInt1(SQLQueryParserFactory.COMPARISON_OPERATOR_GE); 
        }
        break;  
 
        /*
         *  Rule 326:  <row_comparison> ::= <value_expr_row> <relop> <value_expr_row>
         */
        case 326: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createPredicateBasic((QueryValueExpression)getSym(1),getInt(2),(QueryValueExpression)getSym(3))); 
        }
        break;  
 
        /*
         *  Rule 329:  <scalar_comparison> ::= <expression> <relop> <expression>
         */
        case 329: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createPredicateBasic((QueryValueExpression)getSym(1),getInt(2),(QueryValueExpression)getSym(3))); 
        }
        break;  
 
        /*
         *  Rule 330:  <schema> ::= <identifier>
         */
        case 330: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(getString(1)); 
        }
        break;  
 
        /*
         *  Rule 331:  <schema_qualified_name> ::= <identifier>
         */
        case 331: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(getString(1)); 
        }
        break;  
 
        /*
         *  Rule 332:  <schema_qualified_name> ::= <schema> _DOT <identifier>
         */
        case 332: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(getString(1)+"."+getString(3)); 
        }
        break;  
 
        /*
         *  Rule 333:  <selection> ::= <project>
         */
        case 333: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSelectClause(null,(QueryResultSpecification)getSym(1))); 
        }
        break;  
 
        /*
         *  Rule 334:  <selection> ::= <selection> _COMMA <project>
         */
        case 334: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSelectClause(getList(1),(QueryResultSpecification)getSym(3))); 
        }
        break;  
 
        /*
         *  Rule 343:  <special_register> ::= CURRENT_DATE
         */
        case 343: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSpecialRegisterExpression(SQLQueryParserFactory.SPECIAL_REGISTER_CURRENT_DATE)); 
        }
        break;  
 
        /*
         *  Rule 344:  <special_register> ::= CURRENT_TIME
         */
        case 344: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSpecialRegisterExpression(SQLQueryParserFactory.SPECIAL_REGISTER_CURRENT_TIME)); 
        }
        break;  
 
        /*
         *  Rule 345:  <special_register> ::= CURRENT_TIMESTAMP
         */
        case 345: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSpecialRegisterExpression(SQLQueryParserFactory.SPECIAL_REGISTER_CURRENT_TIMESTAMP)); 
        }
        break;  
 
        /*
         *  Rule 346:  <special_register> ::= CURRENT_TIMESTAMP _LPAREN <timestamp precision> _RPAREN
         */
        case 346: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSpecialRegisterExpression(SQLQueryParserFactory.SPECIAL_REGISTER_CURRENT_TIMESTAMP)); 
        }
        break;  
 
        /*
         *  Rule 347:  <special_register> ::= LOCALTIME
         */
        case 347: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSpecialRegisterExpression(SQLQueryParserFactory.SPECIAL_REGISTER_LOCALTIME)); 
        }
        break;  
 
        /*
         *  Rule 348:  <special_register> ::= LOCALTIME _LPAREN <time precision> _RPAREN
         */
        case 348: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSpecialRegisterExpression(SQLQueryParserFactory.SPECIAL_REGISTER_LOCALTIME)); 
        }
        break;  
 
        /*
         *  Rule 349:  <special_register> ::= LOCALTIMESTAMP
         */
        case 349: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSpecialRegisterExpression(SQLQueryParserFactory.SPECIAL_REGISTER_LOCALTIMESTAMP)); 
        }
        break;  
 
        /*
         *  Rule 350:  <special_register> ::= LOCALTIMESTAMP _LPAREN <timestamp precision> _RPAREN
         */
        case 350: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSpecialRegisterExpression(SQLQueryParserFactory.SPECIAL_REGISTER_LOCALTIMESTAMP)); 
        }
        break;  
 
        /*
         *  Rule 351:  <special_register> ::= CURRENT_DEFAULT_TRANSFORM_GROUP
         */
        case 351: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSpecialRegisterExpression(SQLQueryParserFactory.SPECIAL_REGISTER_CURRENT_DEFAULT_TRANSFORM_GROUP)); 
        }
        break;  
 
        /*
         *  Rule 352:  <special_register> ::= CURRENT_PATH
         */
        case 352: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSpecialRegisterExpression(SQLQueryParserFactory.SPECIAL_REGISTER_CURRENT_PATH)); 
        }
        break;  
 
        /*
         *  Rule 353:  <special_register> ::= CURRENT_ROLE
         */
        case 353: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSpecialRegisterExpression(SQLQueryParserFactory.SPECIAL_REGISTER_CURRENT_ROLE)); 
        }
        break;  
 
        /*
         *  Rule 354:  <special_register> ::= CURRENT_TRANSFORM_GROUP_FOR_TYPE <datatype_path-resolved_user-defined_type_name>
         */
        case 354: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSpecialRegisterExpression(SQLQueryParserFactory.SPECIAL_REGISTER_CURRENT_TRANSFORM_GROUP_FOR_TYPE, (UserDefinedType) getSym(2))); 
        }
        break;  
 
        /*
         *  Rule 355:  <special_register> ::= CURRENT_USER
         */
        case 355: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSpecialRegisterExpression(SQLQueryParserFactory.SPECIAL_REGISTER_CURRENT_USER)); 
        }
        break;  
 
        /*
         *  Rule 356:  <special_register> ::= SESSION_USER
         */
        case 356: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSpecialRegisterExpression(SQLQueryParserFactory.SPECIAL_REGISTER_SESSION_USER)); 
        }
        break;  
 
        /*
         *  Rule 357:  <special_register> ::= SYSTEM_USER
         */
        case 357: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSpecialRegisterExpression(SQLQueryParserFactory.SPECIAL_REGISTER_SYSTEM_USER)); 
        }
        break;  
 
        /*
         *  Rule 358:  <special_register> ::= USER
         */
        case 358: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSpecialRegisterExpression(SQLQueryParserFactory.SPECIAL_REGISTER_USER)); 
        }
        break;  
 
        /*
         *  Rule 359:  <special_register> ::= VALUE
         */
        case 359: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSpecialRegisterExpression(SQLQueryParserFactory.SPECIAL_REGISTER_VALUE)); 
        }
        break;  
 
        /*
         *  Rule 360:  <subquery> ::= _LPAREN <query_exp_root> _RPAREN
         */
        case 360: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(getSym(2)); 
        }
        break;  
 
        /*
         *  Rule 361:  <super_groups> ::= CUBE _LPAREN <super_groups_element_list> _RPAREN
         */
        case 361: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1( m_factory.createSuperGroups(getList(3), SQLQueryParserFactory.SUPERGROUP_TYPE_CUBE) ); 
        }
        break;  
 
        /*
         *  Rule 362:  <super_groups> ::= ROLLUP _LPAREN <super_groups_element_list> _RPAREN
         */
        case 362: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1( m_factory.createSuperGroups(getList(3), SQLQueryParserFactory.SUPERGROUP_TYPE_ROLLUP) ); 
        }
        break;  
 
        /*
         *  Rule 363:  <super_groups> ::= _LPAREN _RPAREN
         */
        case 363: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1( m_factory.createSuperGroups(null, SQLQueryParserFactory.SUPERGROUP_TYPE_GRANDTOTAL) ); 
        }
        break;  
 
        /*
         *  Rule 364:  <super_groups_element_exp> ::= <grouping_exp>
         */
        case 364: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1( m_factory.createSuperGroupsElementExpression((GroupingExpression) getSym(1)) ); 
        }
        break;  
 
        /*
         *  Rule 365:  <super_groups_element_exp_list> ::= <super_groups_element_exp>
         */
        case 365: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSuperGroupsElementExprList(null,(SuperGroupElementExpression) getSym(1)));  
        }
        break;  
 
        /*
         *  Rule 366:  <super_groups_element_exp_list> ::= <super_groups_element_exp_list> _COMMA <super_groups_element_exp>
         */
        case 366: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSuperGroupsElementExprList(getList(1),(SuperGroupElementExpression) getSym(3)));  
        }
        break;  
 
        /*
         *  Rule 367:  <super_groups_element> ::= _LPAREN <super_groups_element_exp_list> _RPAREN
         */
        case 367: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1( m_factory.createSuperGroupsElementSublist(getList(2)) );  
        }
        break;  
 
        /*
         *  Rule 369:  <super_groups_element_list> ::= <super_groups_element>
         */
        case 369: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSuperGroupsElementList(null,(SuperGroupElement) getSym(1)));  
        }
        break;  
 
        /*
         *  Rule 370:  <super_groups_element_list> ::= <super_groups_element_list> _COMMA <super_groups_element>
         */
        case 370: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSuperGroupsElementList(getList(1),(SuperGroupElement) getSym(3)));  
        }
        break;  
 
        /*
         *  Rule 371:  <table> ::= <identifier>
         */
        case 371: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(getString(1)); 
        }
        break;  
 
        /*
         *  Rule 372:  <table_correlation> ::= <as_alias> <opt_column_name_list>
         */
        case 372: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createTableCorrelation(getString(1), getList(2))); 
        }
        break;  
 
        /*
         *  Rule 373:  <table_func> ::= TABLE _LPAREN <opt_schema_dot> <identifier> _LPAREN <opt_expression_commalist> _RPAREN _RPAREN <table_correlation>
         */
        case 373: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createTableFunction(getString(4), getList(6), getString(3), (TableCorrelation)getSym(9)) ); 
        }
        break;  
 
        /*
         *  Rule 374:  <table_join> ::= <table_ref> <opt_join_type> JOIN <table_ref> ON <condition>
         */
        case 374: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createJoinedTable((TableReference)getSym(1), getInt(2), (TableReference)getSym(4), (QuerySearchCondition)getSym(6))); 
        }
        break;  
 
        /*
         *  Rule 375:  <table_nested> ::= _LPAREN <table_ref> _RPAREN
         */
        case 375: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createNestedTable((TableReference)getSym(2))); 
        }
        break;  
 
        /*
         *  Rule 376:  <table_qualified> ::= <schema> _DOT <table> <opt_table_correlation>
         */
        case 376: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createReferenceTable(getString(1), getString(3), (TableCorrelation)getSym(4))); 
        }
        break;  
 
        /*
         *  Rule 377:  <table_query> ::= _LPAREN <query_exp> _RPAREN <table_correlation>
         */
        case 377: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createTableExpressionQuery((QueryExpressionBody)getSym(2), (TableCorrelation)getSym(4))); 
        }
        break;  
 
        /*
         *  Rule 378:  <table_query> ::= TABLE _LPAREN <query_exp> _RPAREN <table_correlation>
         */
        case 378: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createTableExpressionQuery((QueryExpressionBody)getSym(3), (TableCorrelation)getSym(5))); 
        }
        break;  
 
        /*
         *  Rule 385:  <table_ref_commalist> ::= <table_ref>
         */
        case 385: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createFromClause((List)null,(TableReference)getSym(1))); 
        }
        break;  
 
        /*
         *  Rule 386:  <table_ref_commalist> ::= <table_ref_commalist> _COMMA <table_ref>
         */
        case 386: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createFromClause(getList(1),(TableReference)getSym(3))); 
        }
        break;  
 
        /*
         *  Rule 387:  <table_simple> ::= <table> <opt_table_correlation>
         */
        case 387: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createReferenceTable(null, getString(1), (TableCorrelation)getSym(2))); 
        }
        break;  
 
        /*
         *  Rule 388:  <target_column_list> ::= <column_ref>
         */
        case 388: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createColumnList(null,(ValueExpressionColumn) getSym(1)));  
        }
        break;  
 
        /*
         *  Rule 389:  <target_column_list> ::= <target_column_list> _COMMA <column_ref>
         */
        case 389: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createColumnList(getList(1),(ValueExpressionColumn) getSym(3)));  
        }
        break;  
 
        /*
         *  Rule 390:  <target_table> ::= <table>
         */
        case 390: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSimpleTable(null, getString(1))); 
        }
        break;  
 
        /*
         *  Rule 391:  <target_table> ::= <schema> _DOT <table>
         */
        case 391: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createSimpleTable(getString(1), getString(3))); 
        }
        break;  
 
        /*
         *  Rule 392:  <time precision> ::= UNSIGNED_INTEGER
         */
        case 392: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(getTokenName(1)); 
        }
        break;  
 
        /*
         *  Rule 393:  <timestamp precision> ::= UNSIGNED_INTEGER
         */
        case 393: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(getTokenName(1)); 
        }
        break;  
 
        /*
         *  Rule 394:  <updatability_expression> ::= FOR READ ONLY
         */
        case 394: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createUpdatabilityExpression(SQLQueryParserFactory.UPDATABILITY_TYPE_FOR_READ_ONLY , null)); 
        }
        break;  
 
        /*
         *  Rule 395:  <updatability_expression> ::= FOR UPDATE
         */
        case 395: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createUpdatabilityExpression(SQLQueryParserFactory.UPDATABILITY_TYPE_FOR_UPDATE , null)); 
        }
        break;  
 
        /*
         *  Rule 396:  <updatability_expression> ::= FOR UPDATE OF <column_name_list>
         */
        case 396: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createUpdatabilityExpression(SQLQueryParserFactory.UPDATABILITY_TYPE_FOR_UPDATE , getList(4))); 
        }
        break;  
 
        /*
         *  Rule 398:  <update_assignment_expression> ::= <column_ref> _EQ <expression>
         */
        case 398: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createUpdateAssignmentExpression((ValueExpressionColumn)getSym(1),(QueryValueExpression)getSym(3))); 
        }
        break;  
 
        /*
         *  Rule 399:  <update_assignment_expression> ::= _LPAREN <target_column_list> _RPAREN _EQ _LPAREN <query_exp> _RPAREN
         */
        case 399: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createUpdateAssignmentExpression(getList(2),(QueryExpressionBody)getSym(6))); 
        }
        break;  
 
        /*
         *  Rule 400:  <update_assignment_expression> ::= _LPAREN <target_column_list> _RPAREN _EQ _LPAREN <expression_commalist> _RPAREN
         */
        case 400: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createUpdateAssignmentExpression(getList(2),getList(6))); 
        }
        break;  
 
        /*
         *  Rule 401:  <update_assignment_expression_commalist> ::= <update_assignment_expression>
         */
        case 401: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createUpdateAssignmentClause(null,(UpdateAssignmentExpression)getSym(1))); 
        }
        break;  
 
        /*
         *  Rule 402:  <update_assignment_expression_commalist> ::= <update_assignment_expression_commalist> _COMMA <update_assignment_expression>
         */
        case 402: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createUpdateAssignmentClause(getList(1),(UpdateAssignmentExpression)getSym(3))); 
        }
        break;  
 
        /*
         *  Rule 403:  <update_stmt> ::= UPDATE <target_table> <opt_as_target_table> SET <update_assignment_clause> <opt_where_clause>
         */
        case 403: 
        {
            setSym1(m_factory.createUpdateStatement((TableInDatabase)getSym(2), (TableCorrelation)getSym(3), getList(5), (QuerySearchCondition)getSym(6))); 
        }
        break;  
 
        /*
         *  Rule 404:  <value_expr_row> ::= _LPAREN <expression_commalist_multiple_elements> _RPAREN
         */
        case 404: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createValueExpressionRow(getList(2))); 
        }
        break;  
 
        /*
         *  Rule 405:  <values_row> ::= _LPAREN <expression_commalist> _RPAREN
         */
        case 405: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createValuesRow(getList(2))); 
        }
        break;  
 
        /*
         *  Rule 406:  <values_row> ::= <expression>
         */
        case 406: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createValuesRow((QueryValueExpression)getSym(1))); 
        }
        break;  
 
        /*
         *  Rule 407:  <values_row_commalist> ::= <values_row>
         */
        case 407: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createValuesRowList(null, (ValuesRow) getSym(1))); 
        }
        break;  
 
        /*
         *  Rule 408:  <values_row_commalist> ::= <values_row_commalist> _COMMA <values_row>
         */
        case 408: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createValuesRowList(getList(1), (ValuesRow) getSym(3))); 
        }
        break;  
 
        /*
         *  Rule 409:  <with_clause> ::= WITH <with_table_spec_list>
         */
        case 409: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(getList(2)); 
        }
        break;  
 
        /*
         *  Rule 410:  <with_clause> ::= $Empty
         */
        case 410: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(null); 
        }
        break;  
 
        /*
         *  Rule 411:  <with_table_spec_list> ::= <with_table_spec>
         */
        case 411: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createWithTableSpecificationList(null,(WithTableSpecification) getSym(1)));  
        }
        break;  
 
        /*
         *  Rule 412:  <with_table_spec_list> ::= <with_table_spec_list> _COMMA <with_table_spec>
         */
        case 412: 
        {
            if (checkStmtOnly) {
                setSym1(null);
                break;
            }
            setSym1(m_factory.createWithTableSpecificationList(getList(1),(WithTableSpecification) getSym(3)));  
        }
        break;  
 
        /*
         *  Rule 413:  <with_table_spec> ::= <table> <opt_column_name_list> AS _LPAREN <query_exp> _RPAREN
         */
        case 413: 
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

