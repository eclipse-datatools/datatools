/*******************************************************************************
 * Copyright (c) 2001, 2004, 2008 IBM Corporation and others. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which accompanies this distribution,
 * and is available at https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: IBM Corporation - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.internal.core.connection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConnectionFilterImpl implements ConnectionFilter {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 3689626986939299641L;

    private String predicate;
    private IFilter filter;
	
	private static final String NOT_PREDICATE_TEXT = "NOT"; //$NON-NLS-1$

	private static final String IDENTIFIER_DELIMITER = "'"; //$NON-NLS-1$

	public static final int STARTS_WITH_OPERATOR = 1;

	public static final int CONTAINS_OPERATOR = 2;

	public static final int ENDS_WITH_OPERATOR = 3;

	public static final int NOT_START_WITH_OPERATOR = 4;

	public static final int NOT_CONTAIN_OPERATOR = 5;

	public static final int NOT_END_WITH_OPERATOR = 6;
	
	private ArrayList predicates = new ArrayList();
	
    public ConnectionFilterImpl() {

    }

    public ConnectionFilterImpl(String predicate) {
        setPredicate(predicate);
    }

    public void setPredicate(String predicate) {
    	filter = null;
        this.predicate = predicate;
    }

    public String getPredicate() {
        return this.predicate;
    }

    public boolean isValid(String predicate) {
        return predicate != null && predicate.length() > 0;
    }

    public String getPattern() {
        String pattern = ""; //$NON-NLS-1$
        if (predicate != null) {
            try {
                if (predicate.indexOf("(") < 0) { //$NON-NLS-1$
                    pattern = predicate.substring(predicate.indexOf("'"), //$NON-NLS-1$
                            predicate.length());
                } else {
                    pattern = predicate.substring(predicate.indexOf("("), //$NON-NLS-1$
                            predicate.length());
                }
            } catch (Exception e) {
                // Bury the exception - if the predicate is an unexpected string
                // we want to return an empty string
            }
        }
        return pattern;
    }

    public String getOperator() {
        String operator = ""; //$NON-NLS-1$
        if (predicate != null) {
            try {
                if (predicate.indexOf("(") < 0) { //$NON-NLS-1$
                    operator = predicate.substring(0, predicate.indexOf("'")).trim(); //$NON-NLS-1$
                } else {
                    operator = predicate.substring(0, predicate.indexOf("(")).trim(); //$NON-NLS-1$
                }
            } catch (Exception e) {
                // Bury the exception - if the predicate is an unexpected string
                // we want to return an empty string
            }
        }
        return operator;
    }

    public String[] getPatternElements() {
        String[] elements = new String[] {};
        Vector patternVector = new Vector();
        String pattern = this.getPattern();
        String[] patterns = pattern.split("'"); //$NON-NLS-1$
        for (int i = 0; i < patterns.length; i++) {
            if (!patterns[i].equals(", ") && !patterns[i].equals("(") //$NON-NLS-1$ //$NON-NLS-2$
                    && !patterns[i].equals(")")) { //$NON-NLS-1$
                patternVector.add(patterns[i]);
            }
        }
        elements = new String[patternVector.size()];
        patternVector.copyInto(elements);
        return elements;
    }
    
    public boolean isOperatorInclusive(){
        boolean isInclusive = false;
        if (getOperator().equals("IN")){ //$NON-NLS-1$
            isInclusive = true;
        }
        return isInclusive;
    }
    
    public boolean isOperatorExclusive(){
        boolean isExclusive = false;
        if (getOperator().equals("NOT IN")){ //$NON-NLS-1$
            isExclusive = true;
        }
        return isExclusive;
    }
    
    public boolean isFiltered(String name) {
    	if (filter == null) {
    		createFilter();
    	}
    	return filter.isFiltered(name);
    }
    
    private void createFilter() {
    	String operator = getOperator();
    	if (OPERATOR_LIKE.equals(operator)) {
    		filter = new LikeFilter(getPattern());
    	}
    	else if (OPERATOR_NOT_LIKE.equals(operator)) {
    		filter = new NotLikeFilter(getPattern()); 
    	}
    	else if (OPERATOR_IN.equals(operator)) {
    		filter = new InFilter(getPattern()); 
    	}
    	else if (OPERATOR_NOT_IN.equals(operator)) {
    		filter = new NotInFilter(getPattern()); 
    	}
    }
    
    private static interface IFilter {

		boolean isFiltered(String name);
	}

	private static class NotLikeFilter implements IFilter {

		Pattern pattern;

		NotLikeFilter(String pattern) {
			String regex;
			if (pattern == null || pattern.length() < 2) {
				regex = new String();
			}
			else {
				regex = quote(pattern.substring(1, pattern.length() - 1));
			}
			this.pattern = Pattern.compile(regex);
		}

		public boolean isFiltered(String name) {
			return pattern.matcher(name).matches();
		}
		
		private String quote(String pattern) {
			StringBuffer buf = new StringBuffer();
			StringTokenizer tokenizer = new StringTokenizer(pattern, "%_", true);

			while (tokenizer.hasMoreTokens()) {
				String token = tokenizer.nextToken();
				if ("%".equals(token)) {
					buf.append(".*");
				} else if ("_".equals(token)) {
					buf.append(".?");
				} else {
					buf.append(token.replaceAll("\\p{Punct}", "\\\\$0"));
					/* 
					 * The following is not supported in JDK 1.4
					 * buf.append(Pattern.quote(token));
					 */
				}
			}
			return buf.toString();
		}

	}

	private static class LikeFilter extends NotLikeFilter {

		LikeFilter(String pattern) {
			super(pattern);
		}

		public boolean isFiltered(String name) {
			return !super.isFiltered(name);
		}
	}

	private static class NotInFilter implements IFilter {
		
		static Pattern regex = Pattern.compile("'(.*?)'");

		Set values;

		NotInFilter(String pattern) {
			if (pattern == null || pattern.length() < 2) {
				values = Collections.EMPTY_SET;
			}
			else {
				values = new TreeSet();
				for (Matcher m = regex.matcher(pattern); m.find(); values.add(m
						.group(1))) {
				}
			}
		}

		public boolean isFiltered(String name) {
			return values.contains(name);
		}
	}

	private static class InFilter extends NotInFilter {

		InFilter(String pattern) {
			super(pattern);
		}

		public boolean isFiltered(String name) {
			return !super.isFiltered(name);
		}
	}

	public ArrayList getPredicatesCollection() {
		predicates = new ArrayList();
		
		if(predicate != null && !predicate.equals("")){
			String[] tokenArray = predicate.split(IDENTIFIER_DELIMITER);
			
			for(int i = 0; i < tokenArray.length; i++)
				tokenArray[i] = tokenArray[i].trim();
			
			ArrayList tempList = new ArrayList();
			
			for(int i = 0; i + 1 < tokenArray.length; i = i + 2){
				tokenArray[i + 1] = "'" + tokenArray[i + 1] + "'";
				
				if(tokenArray[i].startsWith("AND"))
					tokenArray[i] = tokenArray[i].substring(4, tokenArray[i].length());
				else if(tokenArray[i].startsWith("OR"))
					tokenArray[i] = tokenArray[i].substring(3, tokenArray[i].length());
				
				tempList.add(tokenArray[i]);
				tempList.add(tokenArray[i + 1]);
			}
			
			int operator = -1;
			String value = "";
			String tempValue = "";
			
			for(int j = 0; j + 1 < tempList.size(); j = j + 2){
				
				if(tempList.size() == 2)
					operator = findExpressionOperator(tempList.get(j).toString() + " " + tempList.get(j+1).toString());
				else {
					tempValue = tempList.get(j).toString() + " " + tempList.get(j+1).toString();
					tempValue = tempValue.substring(4, tempValue.length());
					operator = findExpressionOperator(tempValue);
				}
				
				value = findExpressionValue(tempList.get(j).toString() + " " + tempList.get(j+1).toString());
				Predicate p = new Predicate(operator, value);
				predicates.add(p);
			}
		}
		return predicates;
	}

	private int findExpressionOperator(String filterPredicate) {
		int enumExpressionPredicate = 1;
		boolean isNotLike = false;
		boolean startsWithPercentSign = false;
		boolean endsWithPercentSign = false;
		if (filterPredicate.startsWith(NOT_PREDICATE_TEXT)) {
			isNotLike = true;
		}
		String expressionValue = filterPredicate.split(IDENTIFIER_DELIMITER)[1];
		if (expressionValue.startsWith("%")) { //$NON-NLS-1$
			startsWithPercentSign = true;
		}

		if (expressionValue.endsWith("%")) { //$NON-NLS-1$
			endsWithPercentSign = true;
		}

		if (startsWithPercentSign && endsWithPercentSign) {
			if (isNotLike) {
				enumExpressionPredicate = NOT_CONTAIN_OPERATOR;
			} else {
				enumExpressionPredicate = CONTAINS_OPERATOR;
			}
		} else if (startsWithPercentSign) {
			if (isNotLike) {
				enumExpressionPredicate = NOT_END_WITH_OPERATOR;
			} else {
				enumExpressionPredicate = ENDS_WITH_OPERATOR;
			}
		} else if (endsWithPercentSign) {
			if (isNotLike) {
				enumExpressionPredicate = NOT_START_WITH_OPERATOR;
			} else {
				enumExpressionPredicate = STARTS_WITH_OPERATOR;
			}
		}
		return enumExpressionPredicate;
	}

	private String findExpressionValue(String filterPredicate) {
		String expressionValue = filterPredicate.split(IDENTIFIER_DELIMITER)[1];
		if (expressionValue.startsWith("%")) { //$NON-NLS-1$
			expressionValue = expressionValue.substring(1);
		}

		if (expressionValue.endsWith("%")) { //$NON-NLS-1$
			expressionValue = expressionValue.substring(0, (expressionValue
					.length() - 1));
		}

		return expressionValue;
	}

	public boolean isMeetsAllConditions() {
		predicates = new ArrayList();
		
		if(predicate != null && !predicate.equals("")){
			String[] tokenArray = predicate.split(" ");
			
			for(int i = 0; i < tokenArray.length; i++){
				if(tokenArray[i].equals("AND"))
					return true;
				else if(tokenArray[i].equals("OR"))
					return false;
			}
		}
		return true;
	}
}