/*
 * Licensed Materials - Property of IBM
 * com.ibm.db.models.sql.query
 * (C) Copyright IBM Corporation 2004, 2005. All Rights Reserved.
 *
 * Note to U.S. Government Users Restricted Rights:
 *   Use, duplication or disclosure restricted 
 *   by GSA ADP Schedule Contract with IBM Corp.
 */
package org.eclipse.datatools.modelbase.sql.query.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * The <code>SQLQuerySourceFormat</code> can be used to provide settings
 * regarding the SQL source text for parsing or source generation from
 * a <code>SQLQueryObject</code> model representation.
 * The SQLQuerySourceFormat will be maintained during the lifecycle of a
 * <code>QueryStatement</code> instance (via reference to SQLQuerySourceInfo,
 * see {@link org.eclipse.datatools.modelbase.sql.query.SQLQueryObject#getSourceInfo()},
 * {@link org.eclipse.datatools.modelbase.sql.query.util.SQLQuerySourceInfo#getSqlFormat()}),
 * where all <code>SQLQueryObject</code> elements of one
 * <code>QueryStatement</code> share one instance of a
 * <code>SQLQuerySourceFormat</code>. That way the settings you specify for the
 * parse are still available for generating the SQL source text for a
 * <code>QueryStatement</code>.
 * Most source format options can be modified during the life cycle of a
 * <code>QueryStatement</code>, exceptions are marked with a * below.<p>
 * Source format options:
 * 
 * <ul>
 *     <li> <code>preserveSourceFormat</code> = the option to preserve the
 * 			input source formating when SQL source text is generated
 *     <li> <code>statementTerminator</code> = the character separating multiple
 * 			SQL statements
 *     <li> <code>hostVariablePrefix</code> = the character that preceedes a
 * 			host language variable
 *     <li> <code>parameterMarker</code> = the character that identifies a
 * 			host language parameter
 *     <li> <code>delimitedIdentifierQuote</code>* = the character that encloses
 * 			delimited identifiers whose writing in case will be preserved
 *     <li> <code>omitSchema</code> = the current schema 
 *          (omitted in SQL source, implicit to unqualified table references)
 *     <li> <code>qualifyIdentifiers</code> = the flag describing how
 *          identifiers in the SQL source will be qualified
 *     <li> <code>preserveComments</code> = the option to preserve comments in
 *          the parsed SQL source or/and the generated SQL source
 *     <li> <code>generateCommentsForStatementOnly</code> = the option to
 *          generate comments for the SQL source only in the
 *          context of the complete statement, or if set to <code>false</code>,
 *          for single SQL Query objects outside the context of a statement
 *          as well
 * </ul> 
 *    
 * <br><br>
 *  * Source format options that can not be modified during the life cycle of a
 * <code>QueryStatement</code>

 * <br><br>
 * See {@link #copyDefaultFormat()} to see how to get a
 * <code>SQLQuerySourceFormat</code> with the default settings.
 * <br>
 * See {@link #copyFields(SQLQuerySourceFormat)} to see how to copy the values
 * from another <code>SQLQuerySourceFormat</code>.
 * 
 * @author ckadner
 */
public class SQLQuerySourceFormat
{
    
    
    /**
     *  Default value for <code>statementTerminator</code>: ';' 
     */
    public static final char STATEMENT_TERMINATOR_DEFAULT = ';';
    
    /**
     *  Default value for <code>hostVariablePrefix</code>: ':' 
     */
    public static final char HOSTVARIABLE_PREFIX_DEFAULT = ':';
    
    /**
     *  Default value for <code>parameterMarker</code>: '?' 
     */
    public static final char PARAMETER_MARKER_DEFAULT = '?';
    
    /**
     *  Default value for <code>delimitedIdentifierQuote</code>: '"' 
     */
    public static final char DELIMITED_IDENTIFIER_QUOTE_DEFAULT = '\"';
    
    /**
     * Default constant for {@link #setQualifyIdentifiers(int)}, and
     * {@link #getQualifyIdentifiers()}, used for SQL source generation. Column
     * and table names will be qualified depending on the context of the SQL
     * statement.
     */
    public static final int QUALIFY_IDENTIFIERS_IN_CONTEXT = 0;

    /**
     * Constant for {@link #setQualifyIdentifiers(int)}, and
     * {@link #getQualifyIdentifiers()}, used for SQL source generation. Table
     * names will always be qualified with schema names and column names with
     * table and schema names.
     */
    public static final int QUALIFY_IDENTIFIERS_WITH_SCHEMA_NAMES = 1;

    /**
     * Constant for {@link #setQualifyIdentifiers(int)}, and
     * {@link #getQualifyIdentifiers()}, used for SQL source generation. Table
     * names will never be qualified with schema names and column names will
     * only be qualified with table names.
     * <p>
     * <b>Note: </b> this configuration can lead to incorrectly generated source
     * for SQL statements!
     */
    public static final int QUALIFY_IDENTIFIERS_WITH_TABLE_NAMES = 2;

    /**
     * Constant for {@link #setQualifyIdentifiers(int)}, and
     * {@link #getQualifyIdentifiers()}, used for SQL source generation. Table
     * names will never be qualified with schema names and column names will
     * only be qualified with table names.
     * <p>
     * <b>Note: </b> this configuration can lead to incorrectly generated source
     * for SQL statements!
     */
    public static final int QUALIFY_IDENTIFIERS_NEVER = 3;

    
    /**
     * <p>
     * <b>Note:</b> this <code>SQLQuerySourceFormat</code>'s is
     * <code>final</code> that means modifications on its members are not allowed.
     * Every attempt to do so will raise an
     * <code>UnsupportedOperationException</code>. Use
     * <code>{@link #copyDefaultFormat()}</code> to get a copy of 
     * <code>{@link #SQL_SOURCE_FORMAT_DEFAULT}</code>
     * </p>
     * Default <code>SQLQuerySourceFormat</code> properties:
     * <ul>
     *     <li> <code>preserveSourceFormat</code> = <code>false</code>
     *     <li> <code>statementTerminator</code> = {@link #STATEMENT_TERMINATOR_DEFAULT}
     *     <li> <code>hostVariablePrefix</code> = {@link #HOSTVARIABLE_PREFIX_DEFAULT}
     *     <li> <code>parameterMarker</code> = {@link #PARAMETER_MARKER_DEFAULT}
     *     <li> <code>delimitedIdentifierQuote</code> = {@link #DELIMITED_IDENTIFIER_QUOTE_DEFAULT}
     *     <li> <code>omitSchema</code> = <code>null</code>;
     *     <li> <code>qualifyIdentifiers</code> = {@link #QUALIFY_IDENTIFIERS_IN_CONTEXT}
     *     <li> <code>preserveComments</code> = <code>true</code>
     *     <li> <code>generateCommentsForStatementOnly</code> = <code>true</code>
     * </ul>
     * @see #copyDefaultFormat()
     */
    public static final SQLQuerySourceFormat SQL_SOURCE_FORMAT_DEFAULT          
            = new SQLQuerySourceFormat( false,
                                        STATEMENT_TERMINATOR_DEFAULT,
                                        HOSTVARIABLE_PREFIX_DEFAULT,
                                        PARAMETER_MARKER_DEFAULT,
                                        DELIMITED_IDENTIFIER_QUOTE_DEFAULT,
                                        null, QUALIFY_IDENTIFIERS_IN_CONTEXT,
                                        true,true,true);
    
    private boolean thisInstanceIsFinal = false;
    

    private boolean preserveSourceFormat = false;
    private char statementTerminator = STATEMENT_TERMINATOR_DEFAULT;
    private char hostVariablePrefix = HOSTVARIABLE_PREFIX_DEFAULT;
    private char parameterMarker = PARAMETER_MARKER_DEFAULT;
    private char delimitedIdentifierQuote = DELIMITED_IDENTIFIER_QUOTE_DEFAULT;
    private String omitSchema = null;
    private int qualifyIdentifiers = QUALIFY_IDENTIFIERS_IN_CONTEXT;
    private boolean preserveComments = true;
    private boolean generateCommentsForStatementOnly = true;

    
    
    /**
     * @param preserveSourceFormat
     * @param statementTerminator
     * @param hostVariablePrefix
     * @param parameterMarker
     * @param delimitedIdentifierQuote
     * @param omitSchema
     * @param qualifyIdentifiers
     * @param preserveComments
     * @param generateCommentsForStatementOnly
     */
    protected SQLQuerySourceFormat(boolean preserveSourceFormat,
                                char statementTerminator,
                                char hostVariablePrefix,
                                char parameterMarker,
                                char delimitedIdentifierQuote,
                                String omitSchema,
                                int qualifyIdentifiers,
                                boolean preserveComments,
                                boolean generateCommentsForStatementOnly)
    {
        this(preserveSourceFormat,statementTerminator,hostVariablePrefix,parameterMarker,delimitedIdentifierQuote,omitSchema,qualifyIdentifiers,preserveComments,generateCommentsForStatementOnly,false);
    }
    
    /**
     * @param preserveSourceFormat
     * @param statementTerminator
     * @param hostVariablePrefix
     * @param parameterMarker
     * @param delimitedIdentifierQuote
     * @param omitSchema
     * @param qualifyIdentifiers
     * @param preserveComments
     * @param generateCommentsForStatementOnly
     * @param finalInstance indicates that the consructed instance will not be modifyable
     */
    private SQLQuerySourceFormat(boolean preserveSourceFormat,
                                char statementTerminator,
                                char hostVariablePrefix,
                                char parameterMarker,
                                char delimitedIdentifierQuote,
                                String omitSchema,
                                int qualifyIdentifiers,
                                boolean preserveComments,
                                boolean generateCommentsForStatementOnly,
                                boolean finalInstance)
    {
        this.preserveSourceFormat = preserveSourceFormat;
        this.statementTerminator = statementTerminator;
        this.hostVariablePrefix = hostVariablePrefix;
        this.parameterMarker = parameterMarker;
        this.delimitedIdentifierQuote = delimitedIdentifierQuote;
        this.omitSchema = omitSchema;
        this.qualifyIdentifiers = qualifyIdentifiers;
        this.preserveComments = preserveComments;
        this.generateCommentsForStatementOnly = generateCommentsForStatementOnly;
        this.thisInstanceIsFinal = finalInstance;
    }
    

    
    private SQLQuerySourceFormat() {}
    
    
    /**
     * @return a new copy of the {@link #SQL_SOURCE_FORMAT_DEFAULT}
     */
    public static SQLQuerySourceFormat copyDefaultFormat()
    {
        return copySourceFormat(SQL_SOURCE_FORMAT_DEFAULT);
    }
    
    /**
     * @param sourceFormat the <code>SQLQuerySourceFormat</code> to be copied.
     * @return a new copy of the given <code>sourceFormat</code>.
     */
    public static SQLQuerySourceFormat copySourceFormat(SQLQuerySourceFormat sourceFormat)
    {
        SQLQuerySourceFormat copy = 
            new SQLQuerySourceFormat();
//          new SQLQuerySourceFormat(
//                        sourceFormat.isPreserveSourceFormat(),
//                        sourceFormat.getStatementTerminator(),
//                        sourceFormat.getHostVariablePrefix(),
//                        sourceFormat.getParameterMarker(),
//                        sourceFormat.getDelimitedIdentifierQuote(),
//                        sourceFormat.getOmitSchema(),
//                        sourceFormat.getQualifyIdentifiers(),
//                        sourceFormat.getPreserveComments(),
//                        sourceFormat.getGenerateCommentsForStatementOnly());
        copyFields(sourceFormat, copy);
        return copy;
    }
    
    /**
     * Copies the fields from the given <code>SQLQuerySourceFormat</code>
     * <code>donor</code>
     * to the <code>SQLQuerySourceFormat</code>
     * <code>recipient</code>.
     * 
     * @param donor
     *            the <code>SQLQuerySourceFormat</code> whichs fields to copy
     * @param recipient
     *            the <code>SQLQuerySourceFormat</code> whichs fields to be
     *            overwritten
     */
    public static void copyFields(SQLQuerySourceFormat donor, SQLQuerySourceFormat recipient)
    {
        Field[] fields = SQLQuerySourceFormat.class.getDeclaredFields();
        
        for (int i = 0; i < fields.length; i++)
        {
            Field field = fields[i];
            //field.setAccessible(true);
            
            if (!Modifier.isFinal(field.getModifiers())
                            && !field.getName().equals("thisInstanceIsFinal")) //$NON-NLS-1$
            {
	            try
	            {
	                field.set(recipient, field.get(donor));
	            }
	            catch (IllegalArgumentException e)
	            {
	                e.printStackTrace();
	            }
	            catch (IllegalAccessException e)
	            {
	                e.printStackTrace();
	            }
            }
            
        }
        
        // just to make sure
        recipient.thisInstanceIsFinal = false;
    }
    
    /**
     * Copies the fields from the given <code>SQLQuerySourceFormat</code>
     * <code>sourceFormat</code> to this <code>SQLQuerySourceFormat</code>.
     * 
     * @param sourceFormat
     *            the <code>SQLQuerySourceFormat</code> whichs fields to copy
     */
    public void copyFields(SQLQuerySourceFormat sourceFormat)
    {
        SQLQuerySourceFormat.copyFields(sourceFormat, this);
    }
    
    
    /** This method is to be called first within every setter 
     * @throws UnsupportedOperationException */
    private void checkThisForFinalInstance() throws UnsupportedOperationException 
    {
        if (this.thisInstanceIsFinal) {
            throw new UnsupportedOperationException("This " + this.getClass().getName() +
                            " is final! Property modifications illegal." +
                            " Use "+ this.getClass().getName() + "#copyDefaultFormat() to get a copy of this instance.");
        }
    }
    
    /**
     * @return Returns the delimitedIdentifierQuote.
     */
    public char getDelimitedIdentifierQuote()
    {
        return delimitedIdentifierQuote;
    }
    /**
     * <b>Note:</b>
     * No modifications of that field allowed, after using this
     * <code>SQLQuerySourceFormat</code> as a parameter for a parse!
     * The delimited identifier quote is stored within
     * the identifiers of the <code>SQLQueryObject</code>s and further
     * modifications of this <code>SQLQuerySourceFormat</code>'s
     * <code>delimitedIdentifierQuote</code>, would result into wrong name
     * comparisons.
     * If you need to modify it, make a copy of the current
     * <code>SQLQuerySourceFormat</code> by using
     * {@link #copySourceFormat(SQLQuerySourceFormat)} to keep previously
     * parsed <code>SQLQueryObject</code>s reference to
     * this <code>SQLQuerySourceFormat</code> and its
     * <code>delimitedIdentifierQuote</code> valid.
     * 
     * @param delimitedIdentifierQuote The delimitedIdentifierQuote to set.
     */
    public void setDelimitedIdentifierQuote(char delimitedIdentifierQuote)
    {
        checkThisForFinalInstance();
        this.delimitedIdentifierQuote = delimitedIdentifierQuote;
    }
    
    /**
     * @return Returns the hostVariablePrefix.
     */
    public char getHostVariablePrefix()
    {
        return hostVariablePrefix;
    }
    /**
     * @param hostVariablePrefix The hostVariablePrefix to set.

     *     */
    public void setHostVariablePrefix(char hostVariablePrefix)
    {
        checkThisForFinalInstance();
        this.hostVariablePrefix = hostVariablePrefix;
    }
    /**
     * @return Returns the parameterMarker.
     */
    public char getParameterMarker()
    {
        return parameterMarker;
    }
    /**
     * @param parameterMarker The parameterMarker to set.
     */
    public void setParameterMarker(char parameterMarker)
    {
        this.parameterMarker = parameterMarker;
    }
    /**
     * @return Returns the omitSchema.
     */
    public String getOmitSchema()
    {
        return omitSchema;
    }
    /**
     * @param omitSchema The omitSchema to set.
     */
    public void setOmitSchema(String omitSchema)
    {
        checkThisForFinalInstance();
        this.omitSchema = omitSchema;
    }
    /**
     * @return Returns the preserveSourceFormat.
     */
    public boolean isPreserveSourceFormat()
    {
        return preserveSourceFormat;
    }
    /**
     * @param preserveSourceFormat The preserveSourceFormat to set.
     */
    public void setPreserveSourceFormat(boolean preserveSourceFormat)
    {
        checkThisForFinalInstance();
        this.preserveSourceFormat = preserveSourceFormat;
    }
    /**
     * @return Returns the statementTerminator.
     */
    public char getStatementTerminator()
    {
        return statementTerminator;
    }
    /**
     * @param statementTerminator The statementTerminator to set.
     */
    public void setStatementTerminator(char statementTerminator)
    {
        checkThisForFinalInstance();
        this.statementTerminator = statementTerminator;
    }
    /**
     * Returns the policy on how identifiers in the SQL source will be qualified,
     * compare to one of the constants {@link #QUALIFY_IDENTIFIERS_IN_CONTEXT},
     * , or .
     * 
     * @return The constant determining how the identifiers in
     *        the SQL source will be qualified, default is
     *        {@link #QUALIFY_IDENTIFIERS_IN_CONTEXT}
     */
    public int getQualifyIdentifiers() {
        return qualifyIdentifiers;
    }
    /**
     * Sets the policy on how identifiers in the SQL source will be qualified,
     * default is {@link #QUALIFY_IDENTIFIERS_IN_CONTEXT}.
     * 
     * @param qualifyIdentifiers The constant determining how the identifiers in
     *        the SQL source will be qualified, default is
     *        {@link #QUALIFY_IDENTIFIERS_IN_CONTEXT}
     */
    public void setQualifyIdentifiers(int qualifyIdentifiers) {
        this.qualifyIdentifiers = qualifyIdentifiers;
    }
    /**DOCME
     * @return Returns the preserveComments.
     */
    public boolean isPreserveComments() {
        return preserveComments;
    }
    /**DOCME
     * @param preserveComments The preserveComments to set.
     */
    public void setPreserveComments(boolean preserveComments) {
        this.preserveComments = preserveComments;
    }
    /** DOCME
     * @return Returns the generateCommentsForStatementOnly.
     */
    public boolean isGenerateCommentsForStatementOnly() {
        return generateCommentsForStatementOnly;
    }
    /** DOCME
     * @param generateCommentsForStatementOnly The generateCommentsForStatementOnly to set.
     */
    public void setGenerateCommentsForStatementOnly(boolean generateCommentsForStatementOnly) {
        this.generateCommentsForStatementOnly = generateCommentsForStatementOnly;
    }
}
