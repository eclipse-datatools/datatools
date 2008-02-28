package org.eclipse.datatools.sqltools.sql.util.test;

import junit.framework.TestCase;

import org.eclipse.datatools.modelbase.sql.schema.Catalog;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.schema.impl.SQLSchemaFactoryImpl;
import org.eclipse.datatools.sqltools.sql.util.ModelUtil;
import org.eclipse.emf.common.util.EList;

public class ModelUtilTest extends TestCase {

	public void testGetSchemas() {
		//Legacy code Database contains Schemas directly, no catalog
		Database db = SQLSchemaFactoryImpl.eINSTANCE.createDatabase();
		db.setName("database");
		Schema s1 = SQLSchemaFactoryImpl.eINSTANCE.createSchema();
		s1.setName("s1");
		Schema s2 = SQLSchemaFactoryImpl.eINSTANCE.createSchema();
		s2.setName("s2");
		db.getSchemas().add(s1);
		db.getSchemas().add(s2);
		
		EList ss = ModelUtil.getSchemas(db, null);
		assertTrue(ss.size() == 2);
		
		//Both Database.getSchemas and Catalog.getSchemas exist, return all
		db = SQLSchemaFactoryImpl.eINSTANCE.createDatabase();
		db.setName("database");
		s1 = SQLSchemaFactoryImpl.eINSTANCE.createSchema();
		s1.setName("s1");
		s2 = SQLSchemaFactoryImpl.eINSTANCE.createSchema();
		s2.setName("s2");
		db.getSchemas().add(s1);
		db.getSchemas().add(s2);
		Catalog cat1 = SQLSchemaFactoryImpl.eINSTANCE.createCatalog();
		cat1.setName("cat1");
		db.getCatalogs().add(cat1);
		cat1.getSchemas().add(s1);
		Catalog cat2 = SQLSchemaFactoryImpl.eINSTANCE.createCatalog();
		cat2.setName("cat2");
		db.getCatalogs().add(cat2);
		cat2.getSchemas().add(s2);
		
		ss = ModelUtil.getSchemas(db, null);
		assertTrue(ss.size() == 2);

		//Both Database.getSchemas and Catalog.getSchemas exist, but only the latter is valid
		db = SQLSchemaFactoryImpl.eINSTANCE.createDatabase();
		db.setName("database");
		s1 = SQLSchemaFactoryImpl.eINSTANCE.createSchema();
		s1.setName("s1");
		s2 = SQLSchemaFactoryImpl.eINSTANCE.createSchema();
		s2.setName("s2");
		Schema s3 = SQLSchemaFactoryImpl.eINSTANCE.createSchema();
		s3.setName("");
		db.getSchemas().add(s3);
		cat1 = SQLSchemaFactoryImpl.eINSTANCE.createCatalog();
		cat1.setName("cat1");
		db.getCatalogs().add(cat1);
		cat1.getSchemas().add(s1);
		cat2 = SQLSchemaFactoryImpl.eINSTANCE.createCatalog();
		cat2.setName("cat2");
		db.getCatalogs().add(cat2);
		cat2.getSchemas().add(s2);
		
		ss = ModelUtil.getSchemas(db, null);
		assertTrue(ss.size() == 2);
		
		//No Database.getSchemas, use catalogName null to retrieve all schemas
		db = SQLSchemaFactoryImpl.eINSTANCE.createDatabase();
		db.setName("database");
		s1 = SQLSchemaFactoryImpl.eINSTANCE.createSchema();
		s1.setName("s1");
		s2 = SQLSchemaFactoryImpl.eINSTANCE.createSchema();
		s2.setName("s2");
		cat1 = SQLSchemaFactoryImpl.eINSTANCE.createCatalog();
		cat1.setName("cat1");
		db.getCatalogs().add(cat1);
		cat1.getSchemas().add(s1);
		cat2 = SQLSchemaFactoryImpl.eINSTANCE.createCatalog();
		cat2.setName("cat2");
		db.getCatalogs().add(cat2);
		cat2.getSchemas().add(s2);
		
		ss = ModelUtil.getSchemas(db, null);
		assertTrue(ss.size() == 2);
		
		//No Database.getSchemas, catalog is a dummy node, use catalogName "" to retrieve all schemas
		db = SQLSchemaFactoryImpl.eINSTANCE.createDatabase();
		db.setName("database");
		s1 = SQLSchemaFactoryImpl.eINSTANCE.createSchema();
		s1.setName("s1");
		s2 = SQLSchemaFactoryImpl.eINSTANCE.createSchema();
		s2.setName("s2");
		cat1 = SQLSchemaFactoryImpl.eINSTANCE.createCatalog();
		cat1.setName("");
		db.getCatalogs().add(cat1);
		cat1.getSchemas().add(s1);
		cat1.getSchemas().add(s2);
		
		ss = ModelUtil.getSchemas(db, "");
		assertTrue(ss.size() == 2);
		
		//No Database.getSchemas, use catalogName to retrieve all schemas for a specific catalog
		db = SQLSchemaFactoryImpl.eINSTANCE.createDatabase();
		db.setName("database");
		s1 = SQLSchemaFactoryImpl.eINSTANCE.createSchema();
		s1.setName("s1");
		s2 = SQLSchemaFactoryImpl.eINSTANCE.createSchema();
		s2.setName("s2");
		cat1 = SQLSchemaFactoryImpl.eINSTANCE.createCatalog();
		cat1.setName("cat1");
		db.getCatalogs().add(cat1);
		cat1.getSchemas().add(s1);
		cat2 = SQLSchemaFactoryImpl.eINSTANCE.createCatalog();
		cat2.setName("cat2");
		db.getCatalogs().add(cat2);
		cat2.getSchemas().add(s2);
		
		ss = ModelUtil.getSchemas(db, "cat1");
		assertTrue(ss.size() == 1);
		assertEquals(((Schema)ss.get(0)).getName(), "s1");
		
		//No Database.getSchemas, and Database only has a dummy catalog,
		//use catalogName to retrieve all schemas for a specific catalog.
		//This is for backward compatibility 
		db = SQLSchemaFactoryImpl.eINSTANCE.createDatabase();
		db.setName("database");
		s1 = SQLSchemaFactoryImpl.eINSTANCE.createSchema();
		s1.setName("s1");
		s2 = SQLSchemaFactoryImpl.eINSTANCE.createSchema();
		s2.setName("s2");
		cat1 = SQLSchemaFactoryImpl.eINSTANCE.createCatalog();
		cat1.setName("");
		db.getCatalogs().add(cat1);
		cat1.getSchemas().add(s1);
		cat1.getSchemas().add(s2);
		
		ss = ModelUtil.getSchemas(db, "database");
		assertTrue(ss.size() == 2);
	}

}
