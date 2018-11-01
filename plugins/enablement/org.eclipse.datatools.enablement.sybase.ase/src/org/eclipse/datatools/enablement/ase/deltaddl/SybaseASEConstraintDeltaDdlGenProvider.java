/*******************************************************************************
 * Copyright (c) 2008 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.enablement.ase.deltaddl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.datatools.enablement.ase.ISybaseASEDdlConstants;
import org.eclipse.datatools.enablement.ase.SybaseASESQLUtil;
import org.eclipse.datatools.enablement.ase.ddl.SybaseASEDdlBuilder;
import org.eclipse.datatools.enablement.sybase.ddl.SybaseDdlBuilder;
import org.eclipse.datatools.enablement.sybase.ddl.SybaseDdlScript;
import org.eclipse.datatools.enablement.sybase.deltaddl.AbstractDeltaDdlGenProvider;
import org.eclipse.datatools.enablement.sybase.deltaddl.SybaseDeltaDdlGeneration.FeatureChangeRecord;
import org.eclipse.datatools.enablement.sybase.util.SQLUtil;
import org.eclipse.datatools.modelbase.sql.constraints.CheckConstraint;
import org.eclipse.datatools.modelbase.sql.constraints.ForeignKey;
import org.eclipse.datatools.modelbase.sql.constraints.PrimaryKey;
import org.eclipse.datatools.modelbase.sql.constraints.SQLConstraintsPackage;
import org.eclipse.datatools.modelbase.sql.constraints.TableConstraint;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.internal.SQLDevToolsUtil;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * @author David Cui
 */
public abstract class SybaseASEConstraintDeltaDdlGenProvider extends AbstractDeltaDdlGenProvider implements
        ISybaseASEDdlConstants
{
    SybaseDdlBuilder _builder = (SybaseDdlBuilder)SybaseASEDdlBuilder.getInstance();
    
    
    protected void addCreateStatement(SybaseDdlScript script, String statement)
    {
        script.addAlterTableAddConstraintStatement(statement);
    }

    
    protected void addDropStatement(SybaseDdlScript script, String statement)
    {
        script.addAlterTableDropConstraintStatement(statement);
    }

    protected void addConstraintRenameStatement(TableConstraint constraint, Object oldValue, Object newValue, boolean quoteIdentifiers, boolean qualifyNames, boolean fullSyntax, SybaseDdlScript script)
    {
        StringBuffer statement = new StringBuffer(128);
        String constraintName = newValue.toString();
        if(quoteIdentifiers)
        {
            constraintName = SQLDevToolsUtil.quoteWhenNecessary(constraintName, (DatabaseIdentifier)getParameter());
        }
        Table table = constraint.getBaseTable();
        /**
         * Primary key is a unique constraint and primary key also contains a unique constraint
         * During delta ddl generation, we must skip the contained unique constraint.
         */
        if(table==null && (constraint.eContainer() instanceof PrimaryKey))
        {
           return;
        }
        String tableName = table.getName();
        String oldObjectName = tableName + DOT + oldValue;
        if(!(constraint instanceof CheckConstraint))
        {
            if(constraint instanceof ForeignKey)
            {
                oldObjectName = (String)oldValue;
            }
            statement.append(EXEC).append(SPACE).append(SP_RENAME).append(SPACE).append(SQLUtil.quote(oldObjectName, SINGLE_QUOTE)).append(COMMA);
            statement.append(constraintName);
            Schema creator = constraint.getBaseTable().getSchema();
            String setUserStr = SybaseASESQLUtil.getSetNewUserStatement(creator);
            if(!setUserStr.equals(EMPTY_STRING))
            {
                script.addAlterTableRenameConstraintStatement(setUserStr);
            }
            script.addAlterTableRenameConstraintStatement(statement.toString());
            String setUserDbo = SybaseASESQLUtil.getSetUserDBOStatement(creator);
            if(!setUserDbo.equals(EMPTY_STRING))
            {
                script.addAlterTableRenameConstraintStatement(setUserDbo);
            }
        }
    }


    /* (non-Javadoc)
     * @see org.eclipse.datatools.enablement.sybase.deltaddl.ConstraintDeltaDdlGenProvider#getModificationResult(org.eclipse.datatools.modelbase.sql.schema.SQLObject, org.eclipse.emf.ecore.EStructuralFeature, java.lang.Object, java.lang.Object, boolean, boolean, boolean, org.eclipse.datatools.enablement.sybase.ddl.SybaseDdlScript)
     */
    
    protected void getModificationResult(SQLObject e, EStructuralFeature feature, Object oldValue, Object newValue, boolean quoteIdentifiers, boolean qualifyNames, boolean fullSyntax, SybaseDdlScript script)
    {
        if (!(e instanceof TableConstraint))
        {
            return;
        }
        //do nothing
    }

    protected boolean isConstraintNameChanged(Map modifyRecordMap, TableConstraint constraint)
    {
          List records = (List)modifyRecordMap.get(constraint);
          if (records != null)
          {
              for (Iterator iter = records.iterator(); iter.hasNext();)
              {
                  FeatureChangeRecord r = (FeatureChangeRecord) iter.next();
                  int featureID = r.feature.getFeatureID();
                  if(featureID == SQLConstraintsPackage.CONSTRAINT__NAME)
                  {
                      return true;
                  }
              }
          }
          return false;
      }

    protected String getConstraintOldName(Map modifyRecordMap, TableConstraint constraint)
    {
          List records = (List)modifyRecordMap.get(constraint);
          if (records != null)
          {
              for (Iterator iter = records.iterator(); iter.hasNext();)
              {
                  FeatureChangeRecord r = (FeatureChangeRecord) iter.next();
                  int featureID = r.feature.getFeatureID();
                  if(featureID == SQLConstraintsPackage.CONSTRAINT__NAME)
                  {
                      return (String)r.oldValue;
                  }
              }
          }
          return constraint.getName();
      }
}
