 /*******************************************************************************
  * Copyright (c) 2005 Versant Corporation and others.
  * All rights reserved. This program and the accompanying materials
  * are made available under the terms of the Eclipse Public License v1.0
  * which accompanies this distribution, and is available at
  * http://www.eclipse.org/legal/epl-v10.html
  * 
  * Contributors:
  *     Versant Corporation - initial API and implementation
  *******************************************************************************/
 package org.eclipse.datatools.enablement.mysql.ddl;

//TODO: Uncomment this file once RDB modifications are migrated to DTP

 /**
  * This class uses the MySqlDdlBuilder to generates the sql scripts.
  */
 public class MySqlDdlGenerator /*implements DDLGenerator*/ {
//     private EngineeringOption[] options = null;
//     private EngineeringOptionCategory[] categories = null;
//     private MySqlDdlBuilder builder = null;
//
//     public String[] generateDDL(SQLObject[] elements,
//                                 IProgressMonitor progressMonitor) {
//        String[] statements = new String[0];
//     	EngineeringOption[] options = this.getSelectedOptions(elements);
//     	
//        if (EngineeringOptionID.generateCreateStatement(options)) {
//         	statements = this.createSQLObjects(elements, EngineeringOptionID.generateQuotedIdentifiers(options),
//                    EngineeringOptionID.generateFullyQualifiedNames(options), progressMonitor);
//        }
//     	
//     	if(EngineeringOptionID.generateDropStatement(options)) {
//     		String[] drop = this.dropSQLObjects(elements, EngineeringOptionID.generateQuotedIdentifiers(options),
//     						EngineeringOptionID.generateFullyQualifiedNames(options), progressMonitor);    	
//     		String[] temp = statements;
//     		statements = new String[temp.length + drop.length];
//            System.arraycopy(drop, 0, statements, 0, drop.length);
//            System.arraycopy(temp, 0, statements, drop.length , temp.length);
//     	}
//     	 
//        return statements;
//     }
//
//     public String[] createSQLObjects(SQLObject[] elements,
//                                      boolean quoteIdentifiers, boolean qualifyNames,
//                                      IProgressMonitor progressMonitor) {
//         String[] statements = this.createStatements(elements, quoteIdentifiers,
//                 qualifyNames, progressMonitor, 100);
//         return statements;
//     }
//
//     public String[] dropSQLObjects(SQLObject[] elements,
//                                    boolean quoteIdentifiers, boolean qualifyNames,
//                                    IProgressMonitor progressMonitor) {
//         String[] statements = this.dropStatements(elements, quoteIdentifiers,
//                 qualifyNames, progressMonitor, 100);
//         return statements;
//     }
//
//     private String[] createStatements(SQLObject[] elements,
//                                       boolean quoteIdentifiers, boolean qualifyNames,
//                                       IProgressMonitor progressMonitor, int task) {
//         MySqlDdlScript script = new MySqlDdlScript();
//         if (this.builder == null) {
//             this.builder = new MySqlDdlBuilder();
//         }
//
//         Set set = null;
//         if (optionDependency != null)
//             set = optionDependency.getElementsToUse();
//
//         Iterator it = null;
//         if (set != null && set.size() > 0)
//             it = optionDependency.getElementsToUse().iterator();
//         else
//             it = GenericDdlGenerationOptions.getAllContainedDisplayableElementSet(elements).iterator();
//         
//         EngineeringOption[] options = this.getSelectedOptions(elements);
//         
//         while (it.hasNext()) {
//             Object o = it.next();
//             if (o instanceof PersistentTable) {
//             	 if (!EngineeringOptionID.generateTables(options)) continue;
//                 String statement = builder.createTable((PersistentTable) o,
//                         quoteIdentifiers, qualifyNames, EngineeringOptionID.generatePKConstraints(options));
//                 if (statement != null)
//                     script.addCreateTableStatement(statement);
//             } else if (o instanceof ViewTable) {
//             	 if (!EngineeringOptionID.generateViews(options)) continue;
//                 String statement = builder.createView((ViewTable) o,
//                         quoteIdentifiers, qualifyNames);
//                 if (statement != null)
//                     script.addCreateViewStatement(statement);
//             } else if (o instanceof Trigger) {
//             	 if (!EngineeringOptionID.generateTriggers(options)) continue;
//                 String statement = builder.createTrigger((Trigger) o,
//                         quoteIdentifiers, qualifyNames);
//                 if (statement != null)
//                     script.addCreateTriggerStatement(statement);
//             } else if (o instanceof CheckConstraint) {
//             	 if (!EngineeringOptionID.generateCKConstraints(options)) continue;
//                 String statement = builder.addCheckConstraint(
//                         (CheckConstraint) o, quoteIdentifiers, qualifyNames);
//                 if (statement != null)
//                     script.addAlterTableAddConstraintStatement(statement);
//             } else if (o instanceof UniqueConstraint) {
//                 String statement = null;
//                 if (o instanceof PrimaryKey) {
//                 	 if(!EngineeringOptionID.generatePKConstraints(options)) continue;
//                     statement = builder.addUniqueConstraint(
//                             (UniqueConstraint) o, quoteIdentifiers, qualifyNames);
//                 } else {
//                 	 if (!EngineeringOptionID.generateIndexes(options)) continue;
//                     statement = builder.addUniqueConstraint(
//                             (UniqueConstraint) o, quoteIdentifiers, qualifyNames);
//                 }
//                 if (statement != null)
//                     script.addAlterTableAddConstraintStatement(statement);
//             } else if (o instanceof ForeignKey) {
//             	 if(!EngineeringOptionID.generateFKConstraints(options)) continue;
//                 String statement = builder.addForeignKey((ForeignKey) o,
//                         quoteIdentifiers, qualifyNames);
//                 if (statement != null)
//                     script.addAlterTableAddForeignKeyStatement(statement);
//             } else if (o instanceof Index) {
//             	 if (!EngineeringOptionID.generateIndexes(options)) continue;
//                 String statement = builder.createIndex((Index) o,
//                         quoteIdentifiers, qualifyNames);
//                 if (statement != null)
//                     script.addCreateIndexStatement(statement);
//             }
//         }
//         return script.getStatements();
//     }
//
//     private String[] dropStatements(SQLObject[] elements,
//                                     boolean quoteIdentifiers, boolean qualifyNames,
//                                     IProgressMonitor progressMonitor, int task) {
//         MySqlDdlScript script = new MySqlDdlScript();
//         if (this.builder == null) {
//             this.builder = new MySqlDdlBuilder();
//         }
//
//         Iterator it = GenericDdlGenerationOptions.getAllContainedDisplayableElementSet(elements).iterator();
//         EngineeringOption[] options = this.getSelectedOptions(elements);
//         
//         while (it.hasNext()) {
//             Object o = it.next();
//             if (o instanceof PersistentTable) {
//             	 if (!EngineeringOptionID.generateTables(options)) continue;
//                 String statement = builder.dropTable((PersistentTable) o,
//                         quoteIdentifiers, qualifyNames);
//                 if (statement != null)
//                     script.addDropTableStatement(statement);
//             } else if (o instanceof ViewTable) {
//             	 if (!EngineeringOptionID.generateViews(options)) continue;
//                 String statement = builder.dropView((ViewTable) o,
//                         quoteIdentifiers, qualifyNames);
//                 if (statement != null)
//                     script.addDropViewStatement(statement);
//             } else if (o instanceof Trigger) {
//             	 if (!EngineeringOptionID.generateTriggers(options)) continue;
//                 String statement = builder.dropTrigger((Trigger) o,
//                         quoteIdentifiers, qualifyNames);
//                 if (statement != null)
//                     script.addDropTriggerStatement(statement);
//             } else if (o instanceof CheckConstraint) {
//             	 if (!EngineeringOptionID.generateCKConstraints(options)) continue;
//                 List statements = builder.dropTableConstraint(
//                         (CheckConstraint) o, quoteIdentifiers, qualifyNames);
//                 if (!statements.isEmpty()) {
//                     for (Iterator iter = statements.iterator(); iter.hasNext();) {
//                         String statement = (String) iter.next();
//                         script.addAlterTableDropConstraintStatement(statement);
//                     }
//                 }
//             } else if (o instanceof UniqueConstraint) {
//                 List statements = null;
//                 if (o instanceof PrimaryKey) {
//                 	 if(!EngineeringOptionID.generatePKConstraints(options)) continue;
//                     statements = builder.dropTableConstraint(
//                             (UniqueConstraint) o, quoteIdentifiers, qualifyNames);
//                 } else {
//                 	 if (!EngineeringOptionID.generateIndexes(options)) continue;
//                     statements = builder.dropTableConstraint(
//                             (UniqueConstraint) o, quoteIdentifiers, qualifyNames);
//                 }
//                 if (!statements.isEmpty()) {
//                     for (Iterator iter = statements.iterator(); iter.hasNext();) {
//                         String statement = (String) iter.next();
//                         script.addAlterTableDropConstraintStatement(statement);
//                     }
//                 }
//             } else if (o instanceof ForeignKey) {
//             	 if(!EngineeringOptionID.generateFKConstraints(options)) continue;
//                 List statements = builder.dropTableConstraint((ForeignKey) o,
//                         quoteIdentifiers, qualifyNames);
//                 if (!statements.isEmpty()) {
//                     for (Iterator iter = statements.iterator(); iter.hasNext();) {
//                         String statement = (String) iter.next();
//                         script.addAlterTableDropForeignKeyStatement(statement);
//                     }
//                 }
//             } else if (o instanceof Index) {
//             	 if (!EngineeringOptionID.generateIndexes(options)) continue;
//                 String statement = builder.dropIndex((Index) o,
//                         quoteIdentifiers, qualifyNames);
//                 if (statement != null)
//                     script.addDropIndexStatement(statement);
//             }
//         }
//         return script.getStatements();
//     }
//
//     public EngineeringOption[] getOptions() {
//         if(this.options == null) {
//             this.options = GenericDdlGenerationOptions.createDDLGenerationOptions(this.getOptionCategories());
//         }
//
//         return this.options;
//     }
//
// 	public EngineeringOption[] getOptions(SQLObject[] elements) {
// 	       return getOptions(elements, false);
// 	}
// 	     
// 	public EngineeringOption[] getOptions(SQLObject[] elements, boolean autoDiscovery) {
//         EngineeringOptionCategory[] categories_new = this.getOptionCategories();
//         
//         EngineeringOptionCategory general_options =null;
//         EngineeringOptionCategory additional_element =null;
//         for (int i = 0; i < categories_new.length; i++) {
//           if (categories_new[i].getId().equals(EngineeringOptionCategoryID.GENERATE_OPTIONS)){
//               general_options = categories_new[i];
//           } else if (categories_new[i].getId().equals(EngineeringOptionCategoryID.GENERATE_ELEMENTS)){
//               additional_element = categories_new[i];
//           }
//         }           
//
//         //Get the main object, the ones that do no have parents.
//         //Look for dependency   
//         int idx = 0, size = 0;
//         optionDependency = new OptionDependency(elements, autoDiscovery);
//         Set sOptions = optionDependency.getOptions();
//
//         this.options = new EngineeringOption[sOptions.size()];
//         int i = 0;
//         for (Iterator it=sOptions.iterator(); it.hasNext(); i++) {
//             this.options[i] = GenericDdlGenerationOptions.getEngineeringOption((String)it.next(), general_options, additional_element);
//             if (this.options[i] != null && this.options[i].getCategory().getId().equals(EngineeringOptionCategoryID.GENERATE_ELEMENTS)) {
//             	idx = i;
//             	size++;
//             }
//         }
//         if (size == 1) {
//         	EngineeringOption option = this.options[idx];
//         	option.setBoolean(true);
//         }       
//         return this.options;
//     }
//     
//     private static Set getAllContainedDisplayableElementSetDepedency(SQLObject[] elements) {
//         SingletonOptionDependency sod = SingletonOptionDependency.getSingletonObject();
//         
//         Set s = new TreeSet();
//         for(int i=0; i<elements.length; ++i) {
//             Class key = null;
//             if (elements[i] instanceof Database) {
//             	key = Database.class;
//             } else if (elements[i] instanceof Schema) {
//             	key = Schema.class;
//             } else if (elements[i] instanceof PersistentTable) {
//            	key = PersistentTable.class;
//             } else if (elements[i] instanceof Index) {
//            	key = Index.class;
//             } else if (elements[i] instanceof ViewTable) {
//            	key = ViewTable.class;
//             } else if (elements[i] instanceof Trigger) {
//            	key = Trigger.class;
//             } else if (elements[i] instanceof UniqueConstraint) {
//            	key = UniqueConstraint.class;
//             } else if(elements[i] instanceof CheckConstraint) {
//            	key = CheckConstraint.class;
//             } else if(elements[i] instanceof ForeignKey) {
//            	key = ForeignKey.class;
//             }
//
//             try {
//             	int mask = sod.getMask(key).intValue();
//             	if (mask != -1)
//             		EngineeringOptionID.populateOptions(s, mask);
//             } catch (Exception e) {
//             	System.err.println("Missing definition for: " + elements[i].getClass().toString());
//             	e.printStackTrace();
//             }
//         }
//         return s;
//     }
//     
//     public EngineeringOptionCategory[] getOptionCategories() {
//         if(this.categories == null) {
//             this.categories = GenericDdlGenerationOptions.createDDLGenerationOptionCategories();
//         }
//         return this.categories;
//     }
//
//     public EngineeringOption[] getSelectedOptions() {
//         if (options == null)
//             this.getOptions();
//         return options;
//     }
//
//     public EngineeringOption[] getSelectedOptions(SQLObject[] elements) {
//         if (options == null)
//             if (optionDependency == null)
//                 this.getOptions(elements);
//             else
//                 this.getOptions();
//         return options;
//     }
//     
// 	private OptionDependency optionDependency = null;
// 	
//     public class OptionDependency {
//         private SQLObject[] elements = null;
//         private Set sOptions = new LinkedHashSet();
//         private Set sElementsToUse = null;        
//         
//         public OptionDependency(SQLObject[] elements, boolean autoDiscovery)
//         {
//             this.elements = elements;
//             sElementsToUse = new LinkedHashSet();
//             doDiscovery(autoDiscovery);
//         }
//
//         private void doDiscovery(boolean autoDiscovery)
//         {
//               setOption(EngineeringOptionID.GENERATE_FULLY_QUALIFIED_NAME);
//               setOption(EngineeringOptionID.GENERATE_QUOTED_IDENTIFIER);
//               setOption(EngineeringOptionID.GENERATE_DROP_STATEMENTS);
//               setOption(EngineeringOptionID.GENERATE_CREATE_STATEMENTS);
//
//               Set additionalOptions = new TreeSet();
//               if (autoDiscovery) {
//                   Iterator it = GenericDdlGenerationOptions.getAllContainedDisplayableElementSet(elements).iterator();
//                   while(it.hasNext()) {
//                       Object o = it.next();
//     
//                       sElementsToUse.add(o);
//                       if (o instanceof PersistentTable) {
//                         additionalOptions.add(EngineeringOptionID.GENERATE_TABLES);
//                       } else if (o instanceof Index) {
//                         additionalOptions.add(EngineeringOptionID.GENERATE_INDICES);
//                       } else if (o instanceof ViewTable) {
//                         additionalOptions.add(EngineeringOptionID.GENERATE_VIEWS);
//                       } else if (o instanceof Trigger) {
//                         additionalOptions.add(EngineeringOptionID.GENERATE_TRIGGERS);
//                       } else if (o instanceof UniqueConstraint) {
//                         additionalOptions.add(EngineeringOptionID.GENERATE_PK_CONSTRAINTS);
//                       } else if(o instanceof CheckConstraint) {
//                         additionalOptions.add(EngineeringOptionID.GENERATE_CK_CONSTRAINTS);
//                       } else if(o instanceof ForeignKey) {
//                         additionalOptions.add(EngineeringOptionID.GENERATE_FK_CONSTRAINTS);
//                       } else if(o instanceof Column) {
//                       }
//                   }
//                   for (it = additionalOptions.iterator(); it.hasNext(); )
//                     setOption((String)it.next());
//               } else { //autoDiscovery == FALSE
//                   sOptions.addAll(MySqlDdlGenerator.getAllContainedDisplayableElementSetDepedency(elements));
//               }
//         }
//         /**
//          * @return Returns the option.
//          */
//         public Set getOptions() {
//             return sOptions;
//         }
//         /**
//          * @param option The option to set.
//          */
//         public void setOption(String option) {
//             this.sOptions.add(option);
//         }
//
//         /**
//          * @return Returns the sElementsToUse.
//          */
//         public Set getElementsToUse() {
//             return sElementsToUse;
//         }
//     }
//     
//     public static class SingletonOptionDependency {
// 		 
//         private Map data = new HashMap();
//         private static SingletonOptionDependency ref;
//         
//         private SingletonOptionDependency()
//         {
//         }
// 		 
//         public static SingletonOptionDependency getSingletonObject()
//         {
//             if (ref == null) {
//                 // it's ok, we can call this constructor
//                 ref = new SingletonOptionDependency();
//
//                 //Database
//                 int mask = EngineeringOptionID.TABLE | EngineeringOptionID.INDEX | 
//                          EngineeringOptionID.VIEW | EngineeringOptionID.TRIGGER | 
//						  EngineeringOptionID.UNIQUE_CONSTRAINT | EngineeringOptionID.CHECK_CONSTRAINT | 
//                          EngineeringOptionID.FOREIGN_KEY;
//                 ref.data.put(Database.class,new Integer(mask));
//
//                 //Schema
//                 mask = EngineeringOptionID.TABLE | EngineeringOptionID.INDEX | 
//                        EngineeringOptionID.VIEW | EngineeringOptionID.TRIGGER | 
//                        EngineeringOptionID.UNIQUE_CONSTRAINT | EngineeringOptionID.CHECK_CONSTRAINT | 
//                        EngineeringOptionID.FOREIGN_KEY;
//                 ref.data.put(Schema.class, new Integer(mask));
//                
//                 //Table
//                 mask = EngineeringOptionID.TABLE | EngineeringOptionID.INDEX | EngineeringOptionID.TRIGGER | 
//                            EngineeringOptionID.UNIQUE_CONSTRAINT | EngineeringOptionID.CHECK_CONSTRAINT | 
//                            EngineeringOptionID.FOREIGN_KEY;
//                 ref.data.put(PersistentTable.class, new Integer(mask));
//                 
//                 //Index
//                 mask = EngineeringOptionID.INDEX;
//                 ref.data.put(Index.class, new Integer(mask));
//
//                 //ViewTable
//                 mask = EngineeringOptionID.VIEW | EngineeringOptionID.TRIGGER;
//                 ref.data.put(ViewTable.class, new Integer(mask));
//
//                 //Trigger
//                 mask = EngineeringOptionID.TRIGGER;
//                 ref.data.put(Trigger.class, new Integer(mask));
//
//                 //UniqueConstraint
//                 mask = EngineeringOptionID.UNIQUE_CONSTRAINT;
//                 ref.data.put(UniqueConstraint.class, new Integer(mask));
//
//                 //ForeignKey
//                 mask = EngineeringOptionID.FOREIGN_KEY;
//                 ref.data.put(ForeignKey.class, new Integer(mask));
//
//                 //CheckConstraint
//                 mask = EngineeringOptionID.CHECK_CONSTRAINT;
//                 ref.data.put(CheckConstraint.class, new Integer(mask));
//           }
//           return ref;
//         }        
//        
//         /**
//          * @return Returns the data.
//          */
//         public Integer getMask(Class key) {
//        	 if (key == null)
//        		 return new Integer(-1);
//             return (Integer)data.get(key);
//         }
//     }
}
