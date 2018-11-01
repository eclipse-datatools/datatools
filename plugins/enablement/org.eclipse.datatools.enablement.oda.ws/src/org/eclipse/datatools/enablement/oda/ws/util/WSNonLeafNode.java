
/*******************************************************************************
 * Copyright (c) 2004, 2005 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *  Actuate Corporation  - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.oda.ws.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 */

public class WSNonLeafNode
{
	private String name;
	//private String type;
	private List subNode;
	//private WSLeafNode wsLeafNode;
	
	public WSNonLeafNode()
	{
		subNode=new ArrayList();
		//wsLeafNode=new WSLeafNode();
	}
	
	public List addNonLeafNodeToList(WSNonLeafNode wsNonLeafNodeName)
	{
		subNode.add( wsNonLeafNodeName );
		return subNode;
	}
	
	public List addLeafNodeToList(WSLeafNode wsLeafNodeName)
	{
		subNode.add( wsLeafNodeName );
		return subNode;
	}
	
	public List getNodeList()
	{
		return this.subNode;
	}
	
	public void setNodeList(List nodeList)
	{
		this.subNode=nodeList;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public void setName(String name)
	{
		this.name=name;
	}
			
}
