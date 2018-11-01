/*******************************************************************************
 * Copyright (c) 2001, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.core.rte;

import org.eclipse.datatools.connectivity.sqm.internal.core.rte.EngineeringOptionCategory;

public final class EngineeringOption implements Cloneable {
	public static final class EnumLiteral {
		public EnumLiteral(String name, String description) {
			this.name = name;
			this.desc = description;
		}
		public String getName() {
			return this.name;
		}
		public String getDescription() {
			return this.desc;
		}
		private String name;
		private String desc;
	}

	public static final byte BOOLEAN_OPTION = 0;
	public static final byte INTEGER_OPTION = 1;
	public static final byte STRING_OPTION  = 2;
	public static final byte ENUM_OPTION    = 3;
	
	public EngineeringOption(String name, String description, boolean initialValue) {
		this.initOption(null,name,description,initialValue,null);
	}
	
	public EngineeringOption(String id,String name, String description, boolean initialValue, EngineeringOptionCategory category) {
		this.initOption(id,name,description,initialValue,category);
	}
	
	public EngineeringOption(String name, String description, String initialValue) {
		this.initOption(null,name,description,initialValue,null);
	}

	public EngineeringOption(String id,String name, String description, String initialValue,EngineeringOptionCategory category) {
		this.initOption(id,name,description,initialValue,category);
	}

	public EngineeringOption(String name, String description, int initialValue) {
		this.initOption(null,name,description,initialValue, null);
	}
	public EngineeringOption(String id,String name, String description, int initialValue,EngineeringOptionCategory category) {
		this.initOption(id,name,description,initialValue,category);
	}
	
	public EngineeringOption(String name, String description, int initialValue, EnumLiteral[] choices) {
		this.initOption(null,name,description,initialValue,choices,null);
	}
	public EngineeringOption(String id,String name, String description, int initialValue, EnumLiteral[] choices,EngineeringOptionCategory category) {
		this.initOption(id,name,description,initialValue,choices,category);
	}

	public String  getOptionName() {
		return this.name;
	}
	
	public String  getOptionDescription() {
		return this.desc;
	}
	
	public byte    getOptionType() {
		return this.type;
	}
	
	public EnumLiteral[] getEnumLiterals() {
		if(this.type != EngineeringOption.ENUM_OPTION) throw new RuntimeException("Invalid Call"); //$NON-NLS-1$
		return this.choices;
	}
	
	public boolean getBoolean() {
		if(this.type != EngineeringOption.BOOLEAN_OPTION) throw new RuntimeException("Invalid Call"); //$NON-NLS-1$
		return this.booleanValue;
	}
	
	public int getInt() {
		if(this.type != EngineeringOption.INTEGER_OPTION && this.type != EngineeringOption.ENUM_OPTION)
			throw new RuntimeException("Invalid Call"); //$NON-NLS-1$
		return this.intValue;
		
	}
	
	public String  getString() {
		if(this.type != EngineeringOption.STRING_OPTION) throw new RuntimeException("Invalid Call"); //$NON-NLS-1$
		return this.stringValue;
	}
	
	public void setBoolean(boolean value) {
		if(this.type != EngineeringOption.BOOLEAN_OPTION) throw new RuntimeException("Invalid Call"); //$NON-NLS-1$
		this.booleanValue = value;
	}
	
	public void setInt(int value) {
		if(this.type != EngineeringOption.INTEGER_OPTION && this.type != EngineeringOption.ENUM_OPTION)
			throw new RuntimeException("Invalid Call"); //$NON-NLS-1$
		this.intValue = value;		
	}
	
	public void setString(String value) {
		if(this.type != EngineeringOption.STRING_OPTION) throw new RuntimeException("Invalid Call"); //$NON-NLS-1$
		this.stringValue = value;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public EngineeringOptionCategory getCategory() {
		return category;
	}
	public void setCategory(EngineeringOptionCategory category) {
		this.category = category;
	}

	private void initOption(String id,String name, String description, boolean initialValue, EngineeringOptionCategory category) {
		this.type = EngineeringOption.BOOLEAN_OPTION;
		if (id != null) this.id = id;
		this.name = name;
		this.desc = description;
		this.booleanValue = initialValue;
		this.category = category;
	}

	public void initOption (String id,String name, String description, String initialValue,EngineeringOptionCategory category) {
		this.type = EngineeringOption.STRING_OPTION;
		if (id != null) this.id = id;
		this.name = name;
		this.desc = description;
		this.stringValue = initialValue;
		this.category = category;
	}
	
	public void initOption(String id,String name, String description, int initialValue,EngineeringOptionCategory category) {
		this.type = EngineeringOption.INTEGER_OPTION;
		if (id != null) this.id = id;
		this.name = name;
		this.desc = description;
		this.intValue = initialValue;		
		this.category = category;
	}
	
	public void initOption(String id,String name, String description, int initialValue, EnumLiteral[] choices,EngineeringOptionCategory category) {
		this.type = EngineeringOption.ENUM_OPTION;
		if (id != null) this.id = id;
		this.name = name;
		this.desc = description;
		this.intValue = initialValue;	
		this.choices = choices;
		this.category = category;
	}
	private String id = "generated." + this.hashCode(); //$NON-NLS-1$
	private String name;
	private String desc;
	private EngineeringOptionCategory category = null;
	private boolean booleanValue;
	private int intValue;
	private String stringValue;
	private byte type;
	private EnumLiteral[] choices;
}
