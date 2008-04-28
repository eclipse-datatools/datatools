/**
 * Created on 2008-03-28
 * 
 * Copyright (c) Sybase, Inc. 2004-2008. All rights reserved.
 */

package org.eclipse.datatools.enablement.sybase;

/**
 * 
 * @author linsong
 *
 */
public class VirtualNodeAdapter 
{
	private String groupId;
	
	public VirtualNodeAdapter(String groupId)
	{
		this.groupId = groupId;
	}
	
	public String getGroupId()
	{
		return this.groupId;
	}
}
