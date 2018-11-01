/*******************************************************************************
 * Copyright (c) 2004, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.parsers.sql.query.util;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.eclipse.datatools.modelbase.sql.query.SQLQueryObject;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

/**
 * @author ckadner
 * 
 */
public class EObjectPrinter // implements CMESwitch //extends LuwSwitch //extends DDLLUWSwitch
{ 
    private static final String SPAN_COMPARE_COLUMN = "_______________________________________"; //$NON-NLS-1$
    private boolean showSpan = false;
    private boolean sortLists = true;
    
    /**
     * Printing the AST takes more time if set to true, all references of the
     * AST will be indexed with their level in the AST, so the references of 
     * an EObject contained in the AST will only be traversed, if there's no 
     * higher level occurence of the same EObject in the AST.
     */
    public boolean preprocessReferences = true;
    
    Vector levels = null;
   
    String indentLevelConnector = ":. "; //$NON-NLS-1$
    String indent               = "   "; //$NON-NLS-1$
    //StringBuffer p_buff         = new StringBuffer();
    //PrintWriter  ps		      = null;
    //HashSet      visited        = new HashSet(); // EObjects which's refernces have already been traversed

    
    
    /**
     * Returns <code>true</code> if this given <code>EObject</code> was visited
     * by this EObjectPrinter already.
     */
    protected boolean visited(EObject p_object, HashSet visited) {
        return visited.contains(p_object);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.datatools.sqltools.models.db2.ddl.luw.util.LuwSwitch#defaultCase(org.eclipse.emf.ecore.EObject)
     */
    protected Object defaultCase(EObject p_object, PrintWriter pw, StringBuffer p_buff) {
        String p_object_String = p_object.toString();
        
        if (showSpan) 
        {
            String sourceSnippet = new String();
            String generatedSQL = new String();
            StringBuffer sourceColumn = new StringBuffer(SPAN_COMPARE_COLUMN);
	        StringBuffer generatedSQLColumn = new StringBuffer(SPAN_COMPARE_COLUMN);
	        
	        if (p_object instanceof SQLQueryObject) 
	        {
	            SQLQueryObject qo = (SQLQueryObject) p_object;
	            sourceSnippet = trimWhiteSpace(qo.getSourceInfo().getSourceSnippet());
	            if (sourceSnippet != null) {
		            int endOffset = (sourceSnippet.length()>sourceColumn.length())?sourceColumn.length():sourceSnippet.length();
		            if (sourceSnippet.length()>sourceColumn.length()) {
		                sourceColumn.replace(0, sourceColumn.length(), sourceSnippet+'\n');
		                generatedSQLColumn.append(SPAN_COMPARE_COLUMN).append("____"); //$NON-NLS-1$
		            } else {
		                sourceColumn.replace(0, sourceSnippet.length(), sourceSnippet);
		            }
	            }
	            
	            generatedSQL = trimWhiteSpace(qo.getSQL());
	            if (generatedSQL != null) {
		            int endOffset = (generatedSQL.length()>generatedSQLColumn.length())?generatedSQLColumn.length():generatedSQL.length();
		            if (generatedSQL.length()>generatedSQLColumn.length()) {
		                generatedSQLColumn = new StringBuffer(generatedSQL);
		                generatedSQLColumn.append('\n');
		                generatedSQLColumn.append(SPAN_COMPARE_COLUMN);
		                generatedSQLColumn.append(SPAN_COMPARE_COLUMN);
		                generatedSQLColumn.append("____"); //$NON-NLS-1$
			        } else {
		                generatedSQLColumn.replace(0, generatedSQL.length(), generatedSQL);
		            }
	            }
	            
	        }
	        pw.print(sourceColumn);
	        if (stripWhiteSpace(sourceSnippet).equalsIgnoreCase(stripWhiteSpace(generatedSQL))) {
	            pw.print(" == "); //$NON-NLS-1$
	        } else {
	            pw.print(" ## "); //$NON-NLS-1$
	        }
	        if (sourceColumn.length()>SPAN_COMPARE_COLUMN.length()) {
	            pw.print('\n');
	        }
	        pw.print(generatedSQLColumn);
	        
	        //int classNameStartIndex = p_object_String.substring(0,p_object_String.indexOf('@')).lastIndexOf('.')+1;
	        //p_object_String = p_object_String.substring(classNameStartIndex);
        }
        int classNameStartIndex = p_object_String.substring(0,p_object_String.indexOf('@')).lastIndexOf('.')+1;
        p_object_String = p_object_String.substring(classNameStartIndex);

        pw.println(p_buff.toString() + p_object_String);
        return null;
    }
    
    /**
     * Removes multiple occurences of white space characters {@link #NEW_LINE}
     * and {@link #SPACE} in the given StringBuffer.
     * 
     * @param toBeTrimmed
     */
    private String trimWhiteSpace(String toBeTrimmed) {
        if (toBeTrimmed == null) {
            return ""; //$NON-NLS-1$
        }
        StringBuffer trimmed = new StringBuffer();
        char SPACE = ' ';
        char NEW_LINE = '\n';
        char lastChar = ' ';
        for (int i = 0; i < toBeTrimmed.length(); i++)
        {
            char currentChar = toBeTrimmed.charAt(i);
            
            if (currentChar == NEW_LINE && lastChar != SPACE) {
                trimmed.append(SPACE);
            } 
            else if (currentChar != SPACE || lastChar != SPACE) {
                trimmed.append(currentChar);
            }
            lastChar = trimmed.charAt(trimmed.length()-1);
        }
        return trimmed.toString();
    }
    
    /**
     * Removes white space characters {@link #NEW_LINE} and {@link #SPACE}
     * in the given StringBuffer.
     * 
     * @param toBeTrimmed
     */
    private String stripWhiteSpace(String toBeTrimmed) {
        if (toBeTrimmed == null) {
            return ""; //$NON-NLS-1$
        }
        StringBuffer trimmed = new StringBuffer();
        char SPACE = ' ';
        char NEW_LINE = '\n';
        for (int i = 0; i < toBeTrimmed.length(); i++)
        {
            char currentChar = toBeTrimmed.charAt(i);
            
            if (currentChar != NEW_LINE && currentChar != SPACE) {
                trimmed.append(currentChar);
            }
        }
        return trimmed.toString();
    }
    

    protected void setLevel(int p_level, StringBuffer p_buff) {
        if ((p_buff.length() / indent.length()) != p_level) {
            p_buff.setLength(0);

            for (int i = 0; i < p_level; i++) {
                p_buff.append(indent);
            }
            // just for the style :-)
            if (p_level >= 1) {
                int conStart = p_buff.length() - indent.length();
                int conEnd = p_buff.length();
                p_buff.replace(conStart,conEnd,indentLevelConnector);
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see db2.luw.CMESwitch#doSwitch(org.eclipse.emf.ecore.EObject)
     */
    protected Object doSwitch(EObject p_theEObject, PrintWriter pw, StringBuffer p_buff) {
        return defaultCase(p_theEObject, pw, p_buff);
    }
    
    
    /** 
     * Prints the given EObject and its refernced EObjects in a tree structure
     * to the PrintStream. The output will be flushed
     * to the underlying System.out stream.
     */
    public PrintWriter printEObjectReferenceTree(EObject p_object) {
        return printEObjectReferenceTree(p_object, true);
    }
    
    /** 
     * Prints the given EObject and its refernced EObjects in a tree structure
     * to the PrintStream. If autoflush is set to true the output will be flushed
     * to the underlying System.out stream.
     */
    public PrintWriter printEObjectReferenceTree(EObject p_object, boolean flush) {
        PrintWriter pw = new PrintWriter(System.out,false);
        StringBuffer p_buff = new StringBuffer();
        HashSet visited = new HashSet();
        
        Map levelMap = null;
        if (preprocessReferences) {
            if (levels == null) {
                levels = new Vector();
            }
            levelMap = initLevelMap(p_object, 0, levelMap);               
        }
        
        foreachInternal(p_object, 0, null, visited, levelMap, pw, p_buff);
        if (flush) pw.flush();
        return pw;
    }
    
    protected Map initLevelMap(EObject p_object, int p_level, Map levelMap) {

        if (levelMap == null) {
            levelMap = new HashMap();
        }

        if (p_object == null) {
            return levelMap;
        }
        
         
        // if the current p_level is not represented by an Integer in the levels
        // array, add it
        if (levels.size() == p_level) {
            levels.add(new Integer(p_level));
        }
        
	    if (isHighestLevel(p_object, p_level, levelMap)) {
	        levelMap.put(p_object, levels.get(p_level));
	        //System.out.println("level:"+p_level+p_object  );
        	for(Iterator itCon = p_object.eClass().getEAllReferences().iterator(); itCon.hasNext();) {
				EReference ref = (EReference)itCon.next();
				if(ref.isMany()) {
					EList list = (EList)p_object.eGet(ref);
					for(Iterator itChild=list.iterator();itChild.hasNext();) {
					    EObject child = (EObject)itChild.next();
						initLevelMap(child, p_level+1, levelMap);
					}
				} else {
					EObject child = (EObject)p_object.eGet(ref);
					initLevelMap(child, p_level+1, levelMap);
					
				}
			}
	    	
	    }
        
        return levelMap;
    }
    
    protected boolean isHighestLevel(EObject p_object, int p_level, Map levelMap){
	    Integer highestLevel = (Integer)levelMap.get(p_object);
	    return (highestLevel == null || highestLevel.intValue() >= p_level);
	}

    
	/**
	 *  give the EObject <code>referencedBy</code> that referenced the given
	 *  EObject <code>p_object</code> so we can avoid traversing the back-reference
	 *  and don't end up in an endless loop.
	 */
	private void foreachInternal(EObject p_object, 
	                             int p_level, 
	                             EObject referencedBy,
	                             HashSet visited, 
	                             Map levelMap, 
	                             PrintWriter pw, 
	                             StringBuffer p_buff) 
	{
		if (p_object==null) return;

		// we might wanna see all references; even the objects already visited 
		// - but then without following all it's references a second time
		// so we eliminate the loop with the visited() method 
		// we eliminate the imediate backflip with the comparison of referencedBy
		//  with p_object.eGet(Ref) so we compare our parent with our child

		
		// only traverse this EObjects references if there's no higher level
		// occurence of the this EObject in the AST
		if (preprocessReferences && !isHighestLevel(p_object, p_level, levelMap)) {
		    
		    // but before breaking check for hidden references:
		    // e.g. A-ref-B with A on level x and B on level x+1 and another occurence of 
		    //      B-ref-A with B on level x and A on level x+1
		    if (isHighestLevel(p_object, p_level-1, levelMap) && !visited(p_object, visited)) {
				this.setLevel(p_level, p_buff);
				this.doSwitch(p_object, pw, p_buff);
		    }
		    
		    return;
		    
		}

		this.setLevel(p_level, p_buff);
		this.doSwitch(p_object, pw, p_buff);

		
		// don't travers references if we already traversed!
		if (visited(p_object, visited)) { 
		     return;
		} else {
		    visited.add(p_object);
		}
		
		
		for(Iterator itCon=p_object.eClass().getEAllReferences().iterator();itCon.hasNext();) {
			EReference ref = (EReference)itCon.next();
			if(ref.isMany()) {
				List list = (EList)p_object.eGet(ref);
				
				if (sortLists) {
				    list = new ArrayList(list);
					Collections.sort(list, eNamedElementComparator);
				}
				
				for(Iterator itChild=list.iterator();itChild.hasNext();) {
				    EObject child = (EObject)itChild.next();
				    // don't go back where we came from
				    if (child != referencedBy) {
					    foreachInternal(child, p_level+1,p_object,visited,levelMap, pw, p_buff);
					}
				}
			} else {
				EObject child = (EObject)p_object.eGet(ref);
				// don't go back where we came from
				if (child != referencedBy) {
				    foreachInternal(child, p_level+1,p_object,visited, levelMap, pw, p_buff);
				}
			}
			
		}
		
	}
	
	private Comparator eNamedElementComparator = new Comparator()
    {
        public int compare(Object o1, Object o2)
        {
            int result = 0;
            
            if (o1 instanceof ENamedElement && o2 instanceof ENamedElement) 
            {
                ENamedElement e1 = (ENamedElement) o1;
                ENamedElement e2 = (ENamedElement) o2;
                
                String e1Name = null;
                String e2Name = null;

                // get the names
                if (e1 != null)
                {
                    e1Name = e1.getName();
                } 
                if (e2 != null)
                {
                    e2Name = e2.getName();
                }

                // compare names properly
                result = compareNames(e1Name, e2Name);

                // deeper compare if equal on the name level, unlikely to have same references, too!
                if (result == 0 && e1 != null && e2 != null 
                                && e1.getClass() == e2.getClass()) 
                {
                	for (Iterator itCon = e1.eClass().getEAllReferences().iterator();itCon.hasNext();) 
                	{
            			EReference ref = (EReference)itCon.next();
            			if(!ref.isMany()) 
            			{
            			    ENamedElement e1child = (ENamedElement)e1.eGet(ref);
            			    ENamedElement e2child = (ENamedElement)e2.eGet(ref);
            				
            			    if (e1child == e2child) {
            			        // we don't want to compare the parent with it self or a refernce to same child
            			        result = 0;
            			    }
            			    else if (e1child != null && e2child != null) {
            				    result = compareNames(e1child.getName(), e2child.getName());
            				} 
            			    else if (e1child != null) {
            			        result = 1;
            			    }
            			    else if (e2child != null) {
            			        result = -1;
            			    }
            			}
            			if (result != 0) break; 
            		}
                }
            }
            
            return result;
        }

        /**
         * @param e1Name
         * @param e2Name
         * @return
         */
        private int compareNames(String e1Name, String e2Name)
        {
            int result = 0;
            
            // compare the names properly
            if (e1Name != null && e2Name != null)
            {
                result = e1Name.compareTo(e2Name);
            } 
            else if (e1Name != null)
            {
                result = 1;
            }
            else if (e2Name != null)
            {
                result = -1;
            }
            return result;
        }
    };

}