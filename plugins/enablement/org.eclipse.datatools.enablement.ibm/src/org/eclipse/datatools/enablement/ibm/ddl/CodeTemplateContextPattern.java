/*******************************************************************************
 * Copyright (c) 2003, 2014 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ibm.ddl;

public class CodeTemplateContextPattern {

	private String name;
	private String createProlog;
	private String createPostlog;
	private String dropProlog;
	private String dropPostlog;
	private int appliedType = 0;
	
	public String getName(){
		return this.name;
	}
	
	public void setName (String name) {
		this.name = name;
	}
	
	public int getAppliedType(){
		return this.appliedType;
	}
	
	public void setAppliedType(int type){
		this.appliedType =  this.appliedType | type;
	}
	
	public void setCreateProlog(String prolog){
		this.createProlog = prolog;
	}
	
	public String getCreateProlog(){
		return this.createProlog;
	}

	public void setCreatePostlog(String postlog){
		this.createPostlog = postlog;
	}
	
	public String getCreatePostlog(){
		return this.createPostlog;
	}
	
	public void setDropProlog(String prolog){
		this.dropProlog = prolog;
	}
	
	public String getDropProlog(){
		return this.dropProlog;
	}

	public void setDropPostlog(String postlog){
		this.dropPostlog = postlog;
	}
	
	public String getDropPostlog(){
		return this.dropPostlog;
	}
	
}
