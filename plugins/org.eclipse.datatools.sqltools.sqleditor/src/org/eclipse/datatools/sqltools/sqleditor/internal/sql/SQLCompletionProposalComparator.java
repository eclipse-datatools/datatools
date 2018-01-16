/**
 * Created on Dec 9, 2004
 * 
 * Copyright (c) Sybase, Inc. 2004-2006 All rights reserved.
 */
package org.eclipse.datatools.sqltools.sqleditor.internal.sql;

import java.util.Comparator;

import org.eclipse.datatools.sqltools.sqleditor.internal.templates.SQLTemplateProposal;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.templates.TemplateProposal;

/**
 * @author Li Huang
 *  
 */
public class SQLCompletionProposalComparator implements Comparator
{
    private static SQLCompletionProposalComparator _instance = new SQLCompletionProposalComparator();

    public static SQLCompletionProposalComparator getInstance()
    {
        return _instance;
    }

    private boolean _orderAlphabetically;

    /**
     * Constructor for CompletionProposalComparator.
     */
    public SQLCompletionProposalComparator()
    {
        _orderAlphabetically = false;
    }

    public void setOrderAlphabetically(boolean orderAlphabetically)
    {
        _orderAlphabetically = orderAlphabetically;
    }

    /*
     * (non-Javadoc)
     * 
     * @see Comparator#compare(Object, Object)
     */
    public int compare(Object o1, Object o2)
    {
        ICompletionProposal p1 = (ICompletionProposal) o1;
        ICompletionProposal p2 = (ICompletionProposal) o2;

        if (!_orderAlphabetically)
        {
            int r1 = getRelevance(p1);
            int r2 = getRelevance(p2);
            int relevanceDif = r2 - r1;
            if (relevanceDif != 0)
            {
                return relevanceDif;
            }
        }

        return p1.getDisplayString().compareToIgnoreCase(p2.getDisplayString());
    }

    private int getRelevance(ICompletionProposal obj)
    {
        if (obj instanceof ISQLCompletionProposal)
        {
            ISQLCompletionProposal scp = (ISQLCompletionProposal) obj;
            return scp.getRelevance();
        }
        else if (obj instanceof SQLTemplateProposal)
        {
            SQLTemplateProposal tp = (SQLTemplateProposal) obj;
            return tp.getRelevance();
        }
        // catch all
        return 0;
    }

}
