 /*******************************************************************************
  * Copyright (c) 2005, 2008. Versant Corporation and others.
  * All rights reserved. This program and the accompanying materials
  * are made available under the terms of the Eclipse Public License 2.0
  * which accompanies this distribution, and is available at
  * https://www.eclipse.org/legal/epl-2.0/
  * 
  * Contributors:
  *     Versant Corporation - initial API and implementation
  *     IBM Corporation - fix for 237964
  *******************************************************************************/
package org.eclipse.datatools.enablement.mysql.ddl.shared;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeSet;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.datatools.connectivity.sqm.core.containment.ContainmentServiceImpl;
import org.eclipse.datatools.connectivity.sqm.core.definition.EngineeringOptionID;
import org.eclipse.datatools.connectivity.sqm.core.rte.DDLGenerator;
import org.eclipse.datatools.connectivity.sqm.core.rte.EngineeringOption;
import org.eclipse.datatools.connectivity.sqm.core.rte.IEngineeringCallBack;
import org.eclipse.datatools.connectivity.sqm.internal.core.rte.EngineeringOptionCategory;
import org.eclipse.datatools.connectivity.sqm.internal.core.rte.EngineeringOptionCategoryID;
import org.eclipse.datatools.connectivity.sqm.internal.core.rte.fe.GenericDdlGenerationOptions;
import org.eclipse.datatools.enablement.mysql.ddl.MySqlDdlBuilder;
import org.eclipse.datatools.enablement.mysql.ddl.MySqlDdlScript;
import org.eclipse.datatools.modelbase.sql.constraints.CheckConstraint;
import org.eclipse.datatools.modelbase.sql.constraints.ForeignKey;
import org.eclipse.datatools.modelbase.sql.constraints.Index;
import org.eclipse.datatools.modelbase.sql.constraints.PrimaryKey;
import org.eclipse.datatools.modelbase.sql.constraints.UniqueConstraint;
import org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedType;
import org.eclipse.datatools.modelbase.sql.routines.Procedure;
import org.eclipse.datatools.modelbase.sql.routines.UserDefinedFunction;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.schema.Sequence;
import org.eclipse.datatools.modelbase.sql.tables.PersistentTable;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.datatools.modelbase.sql.tables.Trigger;
import org.eclipse.datatools.modelbase.sql.tables.ViewTable;

//TODO: Uncomment this file once RDB modifications are migrated to DTP


public class MySqlDdlGeneratorHelper implements DDLGenerator {	
     private EngineeringOption[] options = null;
     private EngineeringOptionCategory[] categories = null;
     private MySqlDdlBuilderHelper builder = null;

 	public MySqlDdlGeneratorHelper() {
	}

 	public String[] generateDDL(SQLObject[] elements, IProgressMonitor progressMonitor){
		return this.generateDDL(elements, progressMonitor, null);
	}

    public String[] createSQLObjects(SQLObject[] elements, boolean quoteIdentifiers, boolean qualifyNames, IProgressMonitor progressMonitor){
    	return this.createSQLObjects(elements, quoteIdentifiers, qualifyNames, progressMonitor,null);
    }
    
    public String[] dropSQLObjects(SQLObject[] elements, boolean quoteIdentifiers, boolean qualifyNames, IProgressMonitor progressMonitor){
    	return this.dropSQLObjects(elements, quoteIdentifiers, qualifyNames, progressMonitor,null);
    }

     
     public String[] generateDDL(SQLObject[] elements,
    		 				     IProgressMonitor progressMonitor,
    		 				     IEngineeringCallBack callback) {
     	this.builder.setEngineeringCallBack(callback);
        String[] statements = new String[0];
     	EngineeringOption[] options = this.getSelectedOptions(elements);
     	
        if (generateCreateStatement(options)) {
         	statements = this.createSQLObjects(elements, generateQuotedIdentifiers(options),
                    generateFullyQualifiedNames(options), progressMonitor);
        }
     	
     	if(generateDropStatement(options)) {
     		String[] drop = this.dropSQLObjects(elements, generateQuotedIdentifiers(options),
     						generateFullyQualifiedNames(options), progressMonitor);    	
     		String[] temp = statements;
     		statements = new String[temp.length + drop.length];
            System.arraycopy(drop, 0, statements, 0, drop.length);
            System.arraycopy(temp, 0, statements, drop.length , temp.length);
     	}
     	 
        return statements;
     }

     public String[] createSQLObjects(SQLObject[] elements, 
    		 						  boolean quoteIdentifiers, boolean qualifyNames,
    		 						  IProgressMonitor progressMonitor,
    		 						  IEngineeringCallBack callback) {
    	 this.builder.setEngineeringCallBack(callback);
         String[] statements = this.createStatements(elements, quoteIdentifiers,
                 qualifyNames, progressMonitor, 100);
         return statements;
     }

     public String[] dropSQLObjects(SQLObject[] elements,
                                    boolean quoteIdentifiers, boolean qualifyNames,
                                    IProgressMonitor progressMonitor,
                                    IEngineeringCallBack callback) {
    	 this.builder.setEngineeringCallBack(callback);
         String[] statements = this.dropStatements(elements, quoteIdentifiers,
                 qualifyNames, progressMonitor, 100);
         return statements;
     }

     private String[] createStatements(SQLObject[] elements,
                                       boolean quoteIdentifiers, boolean qualifyNames,
                                       IProgressMonitor progressMonitor, int task) {
         MySqlDdlScript script = new MySqlDdlScript();
         if (this.builder == null) {
             this.builder = new MySqlDdlBuilder();
         }

//         Set set = null;
//         if (optionDependency != null)
//             set = optionDependency.getElementsToUse();

         Iterator it = null;
//         if (set != null && set.size() > 0)
//             it = optionDependency.getElementsToUse().iterator();
//         else
             it = getAllContainedDisplayableElementSet(elements).iterator();
         
         EngineeringOption[] options = this.getSelectedOptions(elements);
         
         while (it.hasNext()) {
             Object o = it.next();
             if (o instanceof PersistentTable) {
             	 if (!generateTables(options)) continue;
                 String statement = builder.createTable((PersistentTable) o,
                         quoteIdentifiers, qualifyNames, generatePKConstraints(options));
                 if (statement != null)
                     script.addCreateTableStatement(statement);
             } else if (o instanceof ViewTable) {
             	 if (!generateViews(options)) continue;
                 String statement = builder.createView((ViewTable) o,
                         quoteIdentifiers, qualifyNames);
                 if (statement != null)
                     script.addCreateViewStatement(statement);
             } else if (o instanceof Trigger) {
             	 if (!generateTriggers(options)) continue;
                 String statement = builder.createTrigger((Trigger) o,
                         quoteIdentifiers, qualifyNames);
                 if (statement != null)
                     script.addCreateTriggerStatement(statement);
             } else if (o instanceof CheckConstraint) {
             	 if (!generateCKConstraints(options)) continue;
                 String statement = builder.addCheckConstraint(
                         (CheckConstraint) o, quoteIdentifiers, qualifyNames);
                 if (statement != null)
                     script.addAlterTableAddConstraintStatement(statement);
             } else if (o instanceof UniqueConstraint) {
                 String statement = null;
                 if (o instanceof PrimaryKey) {
                 	 if(!generatePKConstraints(options)) continue;
                     statement = builder.addUniqueConstraint(
                             (UniqueConstraint) o, quoteIdentifiers, qualifyNames);
                 } else {
                 	 if (!generateIndexes(options)) continue;
                     statement = builder.addUniqueConstraint(
                             (UniqueConstraint) o, quoteIdentifiers, qualifyNames);
                 }
                 if (statement != null)
                     script.addAlterTableAddConstraintStatement(statement);
             } else if (o instanceof ForeignKey) {
             	 if(!generateFKConstraints(options)) continue;
                 String statement = builder.addForeignKey((ForeignKey) o,
                         quoteIdentifiers, qualifyNames);
                 if (statement != null)
                     script.addAlterTableAddForeignKeyStatement(statement);
             } else if (o instanceof Index) {
             	 if (!generateIndexes(options)) continue;
                 String statement = builder.createIndex((Index) o,
                         quoteIdentifiers, qualifyNames);
                 if (statement != null)
                     script.addCreateIndexStatement(statement);
             }
         }
         return script.getStatements();
     }

     private String[] dropStatements(SQLObject[] elements,
                                     boolean quoteIdentifiers, boolean qualifyNames,
                                     IProgressMonitor progressMonitor, int task) {
         MySqlDdlScript script = new MySqlDdlScript();
         if (this.builder == null) {
             this.builder = new MySqlDdlBuilder();
         }

         Iterator it = getAllContainedDisplayableElementSet(elements).iterator();
         EngineeringOption[] options = this.getSelectedOptions(elements);
         
         while (it.hasNext()) {
             Object o = it.next();
             if (o instanceof PersistentTable) {
             	 if (!generateTables(options)) continue;
                 String statement = builder.dropTable((PersistentTable) o,
                         quoteIdentifiers, qualifyNames);
                 if (statement != null)
                     script.addDropTableStatement(statement);
             } else if (o instanceof ViewTable) {
             	 if (!generateViews(options)) continue;
                 String statement = builder.dropView((ViewTable) o,
                         quoteIdentifiers, qualifyNames);
                 if (statement != null)
                     script.addDropViewStatement(statement);
             } else if (o instanceof Trigger) {
             	 if (!generateTriggers(options)) continue;
                 String statement = builder.dropTrigger((Trigger) o,
                         quoteIdentifiers, qualifyNames);
                 if (statement != null)
                     script.addDropTriggerStatement(statement);
             } else if (o instanceof CheckConstraint) {
             	 if (!generateCKConstraints(options)) continue;
                 List statements = builder.dropTableConstraint(
                         (CheckConstraint) o, quoteIdentifiers, qualifyNames);
                 if (!statements.isEmpty()) {
                     for (Iterator iter = statements.iterator(); iter.hasNext();) {
                         String statement = (String) iter.next();
                         script.addAlterTableDropConstraintStatement(statement);
                     }
                 }
             } else if (o instanceof UniqueConstraint) {
                 List statements = null;
                 if (o instanceof PrimaryKey) {
                 	 if(!generatePKConstraints(options)) continue;
                     statements = builder.dropTableConstraint(
                             (UniqueConstraint) o, quoteIdentifiers, qualifyNames);
                 } else {
                 	 if (!generateIndexes(options)) continue;
                     statements = builder.dropTableConstraint(
                             (UniqueConstraint) o, quoteIdentifiers, qualifyNames);
                 }
                 if (!statements.isEmpty()) {
                     for (Iterator iter = statements.iterator(); iter.hasNext();) {
                         String statement = (String) iter.next();
                         script.addAlterTableDropConstraintStatement(statement);
                     }
                 }
             } else if (o instanceof ForeignKey) {
             	 if(!generateFKConstraints(options)) continue;
                 List statements = builder.dropTableConstraint((ForeignKey) o,
                         quoteIdentifiers, qualifyNames);
                 if (!statements.isEmpty()) {
                     for (Iterator iter = statements.iterator(); iter.hasNext();) {
                         String statement = (String) iter.next();
                         script.addAlterTableDropForeignKeyStatement(statement);
                     }
                 }
             } else if (o instanceof Index) {
             	 if (!generateIndexes(options)) continue;
                 String statement = builder.dropIndex((Index) o,
                         quoteIdentifiers, qualifyNames);
                 if (statement != null)
                     script.addDropIndexStatement(statement);
             }
         }
         return script.getStatements();
     }

     public EngineeringOptionCategory[] getOptionCategories() {
         if(this.categories == null) {
             this.categories = GenericDdlGenerationOptions.createDDLGenerationOptionCategories();
         }
         return this.categories;
     }
     
     
     public EngineeringOption[] getOptions(SQLObject[] elements) {
         return this.calculateOptions(elements);
     }
     
     
     public EngineeringOption[] getSelectedOptions(SQLObject[] elements) {
         if (options == null)
              this.getOptions(elements);
         return options;
     }

     
     public boolean generateDropStatement(EngineeringOption[] options) {
         return getOptionValueByID(EngineeringOptionID.GENERATE_DROP_STATEMENTS, options);
     }
     
     public boolean generateCreateStatement(EngineeringOption[] options) {
         return getOptionValueByID(EngineeringOptionID.GENERATE_CREATE_STATEMENTS, options);
     }

     public boolean generateCommentStatement(EngineeringOption[] options) {
         return getOptionValueByID(EngineeringOptionID.GENERATE_COMMENTS, options);
     }

     public boolean generateDatabase(EngineeringOption[] options) {
         return getOptionValueByID(EngineeringOptionID.GENERATE_DATABASE, options);
     }
     
     public boolean generateSchemas(EngineeringOption[] options) {
         return getOptionValueByID(EngineeringOptionID.GENERATE_SCHEMAS, options);
     }

     public boolean generateQuotedIdentifiers(EngineeringOption[] options) {
         return getOptionValueByID(EngineeringOptionID.GENERATE_QUOTED_IDENTIFIER, options);
     }

     public boolean generateFullyQualifiedNames(EngineeringOption[] options) {
         return getOptionValueByID(EngineeringOptionID.GENERATE_FULLY_QUALIFIED_NAME, options);
     }

     public boolean generateTables(EngineeringOption[] options){
         return getOptionValueByID(EngineeringOptionID.GENERATE_TABLES, options);
     }
     
     public boolean generateIndexes(EngineeringOption[] options){
         return getOptionValueByID(EngineeringOptionID.GENERATE_INDICES, options);
     }
     
     public boolean generateStoredProcedures(EngineeringOption[] options){
         return getOptionValueByID(EngineeringOptionID.GENERATE_STOREDPROCEDURES, options);
     }

     public boolean generateViews(EngineeringOption[] options){
         return getOptionValueByID(EngineeringOptionID.GENERATE_VIEWS, options);
     }

     public boolean generateTriggers(EngineeringOption[] options){
         return getOptionValueByID(EngineeringOptionID.GENERATE_TRIGGERS, options);
     }

     public boolean generateSequences(EngineeringOption[] options){
         return getOptionValueByID(EngineeringOptionID.GENERATE_SEQUENCES, options);
     }
     
     public boolean generateFunctions(EngineeringOption[] options){
         return getOptionValueByID(EngineeringOptionID.GENERATE_FUNCTIONS, options);
     }
     
     public boolean generateUserDefinedTypes(EngineeringOption[] options){
         return getOptionValueByID(EngineeringOptionID.GENERATE_USER_DEFINED_TYPE, options);
     }

     public boolean generateCKConstraints(EngineeringOption[] options){
         return getOptionValueByID(EngineeringOptionID.GENERATE_CK_CONSTRAINTS, options);
     }
     
     public boolean generatePKConstraints(EngineeringOption[] options){
         return getOptionValueByID(EngineeringOptionID.GENERATE_PK_CONSTRAINTS, options);
     }
     
     public boolean generateFKConstraints(EngineeringOption[] options){
         return getOptionValueByID(EngineeringOptionID.GENERATE_FK_CONSTRAINTS, options);
     }
     
     public boolean generateAssertions(EngineeringOption[] options){
         return getOptionValueByID(EngineeringOptionID.GENERATE_ASSERTIONS, options);
     }
     
     private boolean getOptionValueByID(String optionID, EngineeringOption[] options){
     	return EngineeringOptionID.getOptionValueByID(optionID, options);
     }
     
 	protected Set getAllContainedDisplayableElementSet(SQLObject[] elements) {
         Set s = new HashSet();
         for(int i=0; i<elements.length; ++i) {
             s.add(elements[i]);
             s.addAll(ContainmentServiceImpl.INSTANCE.getAllContainedDisplayableElements(elements[i]));
         }
         return s;
     }

     private EngineeringOption[] calculateOptions(SQLObject[] elements) {
     	EngineeringOptionCategory[] categories_new = this.getOptionCategories();
             
     	EngineeringOptionCategory general_options =null;
     	EngineeringOptionCategory additional_element =null;
         for (int i = 0; i < categories_new.length; i++) {
           if (categories_new[i].getId().equals(EngineeringOptionCategoryID.GENERATE_OPTIONS)){
               general_options = categories_new[i];
           } else if (categories_new[i].getId().equals(EngineeringOptionCategoryID.GENERATE_ELEMENTS)){
               additional_element = categories_new[i];
           }
         }           
         this.options = this.getOptionDependency(elements, general_options, additional_element);
         return this.options;
     }

     
    private EngineeringOption[] getOptionDependency (SQLObject[] elements,EngineeringOptionCategory general_options, EngineeringOptionCategory additional_element){
     	Set sOptions = new LinkedHashSet();

     	sOptions.add(EngineeringOptionID.GENERATE_FULLY_QUALIFIED_NAME);
     	sOptions.add(EngineeringOptionID.GENERATE_QUOTED_IDENTIFIER);
     	sOptions.add(EngineeringOptionID.GENERATE_DROP_STATEMENTS);
     	sOptions.add(EngineeringOptionID.GENERATE_CREATE_STATEMENTS);
     	sOptions.add(EngineeringOptionID.GENERATE_COMMENTS);
     	sOptions.addAll(this.getAllContainedDisplayableElementSetDepedency(elements));

         int idx = 0, size = 0;
         EngineeringOption[] options = new EngineeringOption[sOptions.size()];
         int i = 0;
         for (Iterator it=sOptions.iterator(); it.hasNext(); i++) {
             options[i] = this.getEngineeringOption((String)it.next(), general_options, additional_element);
             if (options[i] != null && options[i].getCategory().getId().equals(EngineeringOptionCategoryID.GENERATE_ELEMENTS)) {
             	idx = i;
             	size++;
             }
         }
         if (size == 1) {
         	EngineeringOption option = options[idx];
         	option.setBoolean(true);
         }
         return options;
     }
     
     
     protected  EngineeringOption getEngineeringOption(String id, EngineeringOptionCategory general_options, EngineeringOptionCategory additional_element)
     {
         ResourceBundle resource = ResourceBundle.getBundle("org.eclipse.datatools.connectivity.sqm.internal.core.rte.fe.GenericDdlGeneration"); //$NON-NLS-1$

         try {
             if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_FULLY_QUALIFIED_NAME))
                 return new EngineeringOption(id,resource.getString("GENERATE_FULLY_QUALIFIED_NAME"), resource.getString("GENERATE_FULLY_QUALIFIED_NAME_DES"), false,general_options); //$NON-NLS-1$ //$NON-NLS-2$
             else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_QUOTED_IDENTIFIER))
                 return new EngineeringOption(id,resource.getString("GENERATE_QUOTED_IDENTIFIER"), resource.getString("GENERATE_QUOTED_IDENTIFIER_DES"),false,general_options); //$NON-NLS-1$ //$NON-NLS-2$
             else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_DROP_STATEMENTS))
                 return new EngineeringOption(id,resource.getString("GENERATE_DROP_STATEMENTS"), resource.getString("GENERATE_DROP_STATEMENTS_DES"),false,general_options); //$NON-NLS-1$ //$NON-NLS-2$
             else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_CREATE_STATEMENTS))
                 return new EngineeringOption(id,resource.getString("GENERATE_CREATE_STATEMENTS"), resource.getString("GENERATE_CREATE_STATEMENTS_DES"),true,general_options); //$NON-NLS-1$ //$NON-NLS-2$
             else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_COMMENTS))
                 return new EngineeringOption(id,resource.getString("GENERATE_COMMENTS"), resource.getString("GENERATE_COMMENTS_DES"),true,general_options); //$NON-NLS-1$ //$NON-NLS-2$
             else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_SCHEMAS))
                 return new EngineeringOption(id,resource.getString("GENERATE_SCHEMAS"), resource.getString("GENERATE_SCHEMAS_DES"),true,additional_element); //$NON-NLS-1$ //$NON-NLS-2$
             else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_TABLES))
                 return new EngineeringOption(id,resource.getString("GENERATE_TABLES"), resource.getString("GENERATE_TABLES_DES"),true,additional_element); //$NON-NLS-1$ //$NON-NLS-2$
             else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_PK_CONSTRAINTS))
                 return new EngineeringOption(id,resource.getString("GENERATE_PK_CONSTRAINTS"), resource.getString("GENERATE_PK_CONSTRAINTS_DES"),true,additional_element); //$NON-NLS-1$ //$NON-NLS-2$
             else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_CK_CONSTRAINTS))
                 return new EngineeringOption(id,resource.getString("GENERATE_CK_CONSTRAINTS"), resource.getString("GENERATE_CK_CONSTRAINTS_DES"),true,additional_element); //$NON-NLS-1$ //$NON-NLS-2$
             else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_FK_CONSTRAINTS))
                 return new EngineeringOption(id,resource.getString("GENERATE_FK_CONSTRAINTS"), resource.getString("GENERATE_FK_CONSTRAINTS_DES"),true,additional_element); //$NON-NLS-1$ //$NON-NLS-2$
             else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_INDICES))
                 return new EngineeringOption(id,resource.getString("GENERATE_INDEX"), resource.getString("GENERATE_INDEX_DES"),true,additional_element); //$NON-NLS-1$ //$NON-NLS-2$
             else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_STOREDPROCEDURES))
                 return new EngineeringOption(id,resource.getString("GENERATE_STOREDPROCEDURE"), resource.getString("GENERATE_STOREDPROCEDURE_DES"),true,additional_element); //$NON-NLS-1$ //$NON-NLS-2$
             else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_FUNCTIONS))
                 return new EngineeringOption(id,resource.getString("GENERATE_FUNCTION"), resource.getString("GENERATE_FUNCTION_DES"),true,additional_element); //$NON-NLS-1$ //$NON-NLS-2$
             else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_VIEWS))
                 return new EngineeringOption(id,resource.getString("GENERATE_VIEW"), resource.getString("GENERATE_VIEW_DES"),true,additional_element); //$NON-NLS-1$ //$NON-NLS-2$
             else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_TRIGGERS))
                 return new EngineeringOption(id,resource.getString("GENERATE_TIGGER"), resource.getString("GENERATE_TIGGER_DES"),true,additional_element); //$NON-NLS-1$ //$NON-NLS-2$
             else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_SEQUENCES))
                 return new EngineeringOption(id,resource.getString("GENERATE_SEQUENCE"), resource.getString("GENERATE_SEQUENCE_DES"),true,additional_element); //$NON-NLS-1$ //$NON-NLS-2$
             else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_USER_DEFINED_TYPE))
                 return new EngineeringOption(id,resource.getString("GENERATE_USER_DEFINED_TYPE"), resource.getString("GENERATE_USER_DEFINED_TYPE_DES"),true,additional_element); //$NON-NLS-1$ //$NON-NLS-2$
             else if (id.equalsIgnoreCase(EngineeringOptionID.GENERATE_ASSERTIONS))
                 return new EngineeringOption(id,resource.getString("GENERATE_ASSERTIONS"), resource.getString("GENERATE_GENERATE_ASSERTION_DES"),true,additional_element); //$NON-NLS-1$ //$NON-NLS-2$
         } catch (Exception e) {
             //The resource was not found
         	e.printStackTrace();
         }
         return null;
     }

     
     protected Set getAllContainedDisplayableElementSetDepedency(SQLObject[] elements) {
         SingletonOptionDependency sod = SingletonOptionDependency.getSingletonObject();
         
         Set s = new TreeSet();
         for(int i=0; i<elements.length; ++i) {
             Class key = null;
             if(elements[i] instanceof Database) {
                 key = Database.class;
             } else if(elements[i] instanceof Schema) {
                 key = Schema.class;
             } else if (elements[i] instanceof PersistentTable) {
                  key = Table.class;
             } else if (elements[i] instanceof Index) {
                 key = Index.class;
             } else if (elements[i] instanceof Procedure) {
                 key = Procedure.class;
             } else if (elements[i] instanceof UserDefinedFunction) {
                 key = UserDefinedFunction.class;
             } else if (elements[i] instanceof ViewTable) {
                 key = ViewTable.class;
             } else if (elements[i] instanceof Trigger) {
                 key = Trigger.class;
             } else if (elements[i] instanceof Sequence) {
                 key = Sequence.class;
             } else if (elements[i] instanceof UserDefinedType) {
                 key = UserDefinedType.class;
             } else if (elements[i] instanceof UniqueConstraint) {
                 key = UniqueConstraint.class;
             } else if(elements[i] instanceof CheckConstraint) {
                 key = CheckConstraint.class;
             } else if(elements[i] instanceof ForeignKey) {
                key = ForeignKey.class;
             }

             try {
                 int mask = sod.getMask(key).intValue();
                 MySqlDdlGeneratorHelper.this.populateOptions(s, mask);
             } catch (Exception e) {
                 System.err.println("Missing definition for: " + elements[i].getClass().toString());
                 e.printStackTrace();
             }
         }
         return s;
     }
     
     
     protected void populateOptions(Set s, int mask) {
         if ((mask & EngineeringOptionID.DATABASE) == EngineeringOptionID.DATABASE)
             s.add(EngineeringOptionID.GENERATE_DATABASE);
         if ((mask & EngineeringOptionID.TABLE) == EngineeringOptionID.TABLE)
             s.add(EngineeringOptionID.GENERATE_TABLES);
         if ((mask & EngineeringOptionID.INDEX) == EngineeringOptionID.INDEX)
             s.add(EngineeringOptionID.GENERATE_INDICES);
         if ((mask & EngineeringOptionID.PROCEDURE) == EngineeringOptionID.PROCEDURE)
             s.add(EngineeringOptionID.GENERATE_STOREDPROCEDURES);
         if ((mask & EngineeringOptionID.USER_DEFINED_FUNCTION) == EngineeringOptionID.USER_DEFINED_FUNCTION)
             s.add(EngineeringOptionID.GENERATE_FUNCTIONS);
         if ((mask & EngineeringOptionID.VIEW) == EngineeringOptionID.VIEW)
             s.add(EngineeringOptionID.GENERATE_VIEWS);
         if ((mask & EngineeringOptionID.TRIGGER) == EngineeringOptionID.TRIGGER)
             s.add(EngineeringOptionID.GENERATE_TRIGGERS);
         if ((mask & EngineeringOptionID.SEQUENCE) == EngineeringOptionID.SEQUENCE)
             s.add(EngineeringOptionID.GENERATE_SEQUENCES);
         if ((mask & EngineeringOptionID.USER_DEFINED_TYPE) == EngineeringOptionID.USER_DEFINED_TYPE)
             s.add(EngineeringOptionID.GENERATE_USER_DEFINED_TYPE);
         if ((mask & EngineeringOptionID.UNIQUE_CONSTRAINT) == EngineeringOptionID.UNIQUE_CONSTRAINT)
             s.add(EngineeringOptionID.GENERATE_PK_CONSTRAINTS);
         if ((mask & EngineeringOptionID.CHECK_CONSTRAINT) == EngineeringOptionID.CHECK_CONSTRAINT)
             s.add(EngineeringOptionID.GENERATE_CK_CONSTRAINTS);
         if ((mask & EngineeringOptionID.FOREIGN_KEY) == EngineeringOptionID.FOREIGN_KEY)
             s.add(EngineeringOptionID.GENERATE_FK_CONSTRAINTS);
         if ((mask & EngineeringOptionID.SCHEMA) == EngineeringOptionID.SCHEMA)
             s.add(EngineeringOptionID.GENERATE_SCHEMAS);
     }    


     public static class SingletonOptionDependency {

         private Map data = new HashMap();
         private static SingletonOptionDependency ref;
         
         private SingletonOptionDependency()
         {
         }

         public static SingletonOptionDependency getSingletonObject()
         {
             if (ref == null) {
                 ref = new SingletonOptionDependency();

                 //Database
                 int mask = EngineeringOptionID.TABLE | EngineeringOptionID.INDEX | EngineeringOptionID.VIEW | 
                          EngineeringOptionID.TRIGGER | EngineeringOptionID.UNIQUE_CONSTRAINT | 
                          EngineeringOptionID.CHECK_CONSTRAINT | EngineeringOptionID.FOREIGN_KEY; 
                 ref.data.put(Database.class, new Integer(mask));

                 //Schema
                 mask = EngineeringOptionID.TABLE | EngineeringOptionID.INDEX | EngineeringOptionID.VIEW | 
                          EngineeringOptionID.TRIGGER | EngineeringOptionID.UNIQUE_CONSTRAINT | 
                          EngineeringOptionID.CHECK_CONSTRAINT | EngineeringOptionID.FOREIGN_KEY; 
                 ref.data.put(Schema.class, new Integer(mask));
                
                 //Table
                 mask = EngineeringOptionID.TABLE | EngineeringOptionID.INDEX | EngineeringOptionID.TRIGGER | 
                          EngineeringOptionID.UNIQUE_CONSTRAINT | EngineeringOptionID.CHECK_CONSTRAINT | 
                          EngineeringOptionID.FOREIGN_KEY;
                 ref.data.put(Table.class, new Integer(mask));
                 
                 
                 //Index
                 mask = EngineeringOptionID.INDEX;
                 ref.data.put(Index.class, new Integer(mask));

                 //Procedure
                 mask = EngineeringOptionID.PROCEDURE;
                 ref.data.put(Procedure.class, new Integer(mask));

                 //UserDefinedFunction
                 mask = EngineeringOptionID.USER_DEFINED_FUNCTION;
                 ref.data.put(UserDefinedFunction.class, new Integer(mask));
  
                 //ViewTable
                 mask = EngineeringOptionID.VIEW | EngineeringOptionID.TRIGGER;
                 ref.data.put(ViewTable.class, new Integer(mask));

                 //Trigger
                 mask = EngineeringOptionID.TRIGGER;
                 ref.data.put(Trigger.class, new Integer(mask));

                 //Sequence
                 mask = EngineeringOptionID.SEQUENCE;
                 ref.data.put(Sequence.class, new Integer(mask));

                 //UserDefinedType
                 mask = EngineeringOptionID.USER_DEFINED_TYPE;
                 ref.data.put(UserDefinedType.class, new Integer(mask));

                 //UniqueConstraint
                 mask = EngineeringOptionID.UNIQUE_CONSTRAINT;
                 ref.data.put(UniqueConstraint.class, new Integer(mask));

                 //ForeignKey
                 mask = EngineeringOptionID.FOREIGN_KEY;
                 ref.data.put(ForeignKey.class, new Integer(mask));

                 //CheckConstraint
                 mask = EngineeringOptionID.CHECK_CONSTRAINT;
                 ref.data.put(CheckConstraint.class, new Integer(mask));
           }
           return ref;
         }        
        
         public Integer getMask(Class key) {
             return (Integer)data.get(key);
         }
     }


	protected MySqlDdlBuilderHelper getBuilder() {
		return builder;
	}

	protected void setBuilder(MySqlDdlBuilderHelper builder) {
		this.builder = builder;
	}

	
}
