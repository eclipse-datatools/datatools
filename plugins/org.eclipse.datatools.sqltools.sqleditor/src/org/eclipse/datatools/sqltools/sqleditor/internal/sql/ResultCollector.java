/**
 * Created on Dec 8, 2004
 * 
 * Copyright (c) Sybase, Inc. 2004-2006 All rights reserved.
 */
package org.eclipse.datatools.sqltools.sqleditor.internal.sql;

import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.datatools.sqltools.sql.parser.SQLParser;

/**
 * @author Li Huang
 * 
 * Bin to collect the proposal of the infrastructure on code assist in a SQL editor.
 */
public class ResultCollector
{
    private ArrayList _databaseList          = new ArrayList();
    private ArrayList _columnList            = new ArrayList();
    private ArrayList _tableList             = new ArrayList();
    private ArrayList _viewList              = new ArrayList();
    private ArrayList _storedProcedureList   = new ArrayList();
    private ArrayList _functionList          = new ArrayList();
    private ArrayList _triggerList           = new ArrayList();
    private ArrayList _eventList             = new ArrayList();
    private ArrayList _reservedKeywordList   = new ArrayList();
    private ArrayList _unreservedKeywordList = new ArrayList();
    private ArrayList _variableList          = new ArrayList();
    private ArrayList _template              = new ArrayList();
    private ArrayList _operator              = new ArrayList();
    private ArrayList _indexList             = new ArrayList();
    private ArrayList _segmentList           = new ArrayList();
    /** The other list * */
    private ArrayList _otherList             = new ArrayList();

    ArrayList[]       _results               = null;

    /**
     * @return Returns the reservedKeywordList.
     */
    public ArrayList getReservedKeywordList()
    {
        return _reservedKeywordList;
    }

    /**
     * @param keywordList The reservedKeywordList to set.
     */
    public void setReservedKeywordList(ArrayList keywordList)
    {
        _reservedKeywordList = keywordList;
    }

    /**
     * @return Returns the unreservedKeywordList.
     */
    public ArrayList getUnreservedKeywordList()
    {
        return _unreservedKeywordList;
    }

    /**
     * @param keywordList The unreservedKeywordList to set.
     */
    public void setUnreservedKeywordList(ArrayList keywordList)
    {
        _unreservedKeywordList = keywordList;
    }

    /**
     * @return Returns the databaseList.
     */
    public ArrayList getDatabaseList()
    {
        return _databaseList;
    }

    /**
     * @param list The databaseList to set.
     */
    public void setDatabaseList(ArrayList list)
    {
        _databaseList = list;
    }

    /**
     * @return Returns the eventList.
     */
    public ArrayList getEventList()
    {
        return _eventList;
    }

    /**
     * @param list The eventList to set.
     */
    public void setEventList(ArrayList list)
    {
        _eventList = list;
    }

    /**
     * @return Returns the storedProcedureList.
     */
    public ArrayList getStoredProcedureList()
    {
        return _storedProcedureList;
    }

    /**
     * @param procedureList The storedProcedureList to set.
     */
    public void setStoredProcedureList(ArrayList procedureList)
    {
        _storedProcedureList = procedureList;
    }

    /**
     * @return Returns the storedProcedureList.
     */
    public ArrayList getFunctionList()
    {
        return _functionList;
    }
    
    /**
     * @param procedureList The storedProcedureList to set.
     */
    public void setFunctionList(ArrayList procedureList)
    {
        _functionList = procedureList;
    }    
    /**
     * @return Returns the tableList.
     */
    public ArrayList getTableList()
    {
        return _tableList;
    }

    /**
     * 
     * @return the viewList.
     */
    public ArrayList getViewList()
    {
        return _viewList;
    }
    /**
     * @param list The tableList to set.
     */
    public void setTableList(ArrayList list)
    {
        _tableList = list;
    }

    /**
     * 
     * @param list The viewList to set.
     */
    public void setViewList(ArrayList list)
    {
        _viewList = list;
    }
    /**
     * @return Returns the triggerList.
     */
    public ArrayList getTriggerList()
    {
        return _triggerList;
    }

    /**
     * @param list The triggerList to set.
     */
    public void setTriggerList(ArrayList list)
    {
        _triggerList = list;
    }

    /**
     * @return Returns the indexList.
     */
    public ArrayList getIndexList()
    {
        return _indexList;
    }

    /**
     * @param list The indexList to set.
     */
    public void setIndexList(ArrayList list)
    {
        _indexList = list;
    }

    /**
     * @return Returns the columnList.
     */
    public ArrayList getColumnList()
    {
        return _columnList;
    }

    /**
     * @param list The columnList to set.
     */
    public void setColumnList(ArrayList list)
    {
        _columnList = list;
    }

    /**
     * @return Returns the otherList.
     */
    public ArrayList getOtherList()
    {
        return _otherList;
    }

    /**
     * @param list The otherList to set.
     */
    public void setOtherList(ArrayList list)
    {
        _otherList = list;
    }

    /**
     * @return Returns the segmentList.
     */
    public ArrayList getSegmentList()
    {
        return _segmentList;
    }

    /**
     * @param list The segmentList to set.
     */
    public void setSegmentList(ArrayList list)
    {
        _segmentList = list;
    }

    public void addReservedKeyword(SQLCompletionProposal completionProposal)
    {
        _reservedKeywordList.add(completionProposal);
    }

    public void addUnreservedKeywordList(SQLCompletionProposal completionProposal)
    {
        _unreservedKeywordList.add(completionProposal);
    }

    /**
     * @return Returns the variableList.
     */
    public ArrayList getVariableList()
    {
        return _variableList;
    }

    /**
     * @param list The variableList to set.
     */
    public void setVariableList(ArrayList list)
    {
        _variableList = list;
    }

    public void addVariable(SQLCompletionProposal completionProposal)
    {
        _variableList.add(completionProposal);
    }


    public void addOperator(SQLCompletionProposal completionProposal)
    {
        _operator.add(completionProposal);
    }
    /**
     * get the proposal results, sort by relevance.
     * 
     * @return SQLCompletionProposal[]
     */
    public ISQLCompletionProposal[] getResults()
    {
        _results = new ArrayList[] 
        {
            _databaseList, _columnList, _tableList, _viewList, _storedProcedureList, _functionList, _triggerList,
                _indexList, _eventList, _reservedKeywordList, _unreservedKeywordList, _variableList, _operator, _segmentList, _otherList
        }
        ;

        int totLen = 0;

        for (int i = 0; i < _results.length; i++)
        {
            if (_results[i].size() > 0)
            {
                totLen += _results[i].size();
            }
        }
        SQLCompletionProposal[] result = new SQLCompletionProposal[totLen];
        int k = 0;
        for (int i = 0; i < _results.length; i++)
        {
            ArrayList curr = _results[i];
            int currLen = curr.size();
            if (currLen > 0)
            {
                for (int j = 0; j < currLen; j++)
                {
                    result[k++] = (SQLCompletionProposal) curr.get(j);
                }
            }
        }

        return result;
    }

	public void setDBProposalList(ArrayList proposals, int scope) {
        switch (scope)
        {
            case SQLParser.SCOPE_CATALOGS:
                setDatabaseList(proposals);
                break;
            case SQLParser.SCOPE_COLUMNS:
            case SQLParser.SCOPE_COLUMNS | SQLParser.SCOPE_WITHOUT_TABLE:
            case SQLParser.SCOPE_WITHOUT_TABLE:
                setColumnList(proposals);
                break;
            case SQLParser.SCOPE_TABLES:
                setTableList(proposals);
                break;
            case SQLParser.SCOPE_TABLES | SQLParser.SCOPE_COLUMNS:
            	setColumnList(getProposalsByRelevance(proposals, SQLCompletionProposal.COLUMN));
            	setTableList(getProposalsByRelevance(proposals, SQLCompletionProposal.TABLE));
            	break;
            case SQLParser.SCOPE_STORED_PROCEDURES:
                setStoredProcedureList(proposals);
                break;
            case SQLParser.SCOPE_FUNCTIONS:
            	setFunctionList(proposals);
            	break;
            case SQLParser.SCOPE_STORED_PROCEDURES| SQLParser.SCOPE_FUNCTIONS:
            	setStoredProcedureList(proposals);
            	break;            	
            case SQLParser.SCOPE_TRIGGERS:
                setTriggerList(proposals);
                break;
            case SQLParser.SCOPE_EVENTS:
                setEventList(proposals);
                break;
            case SQLParser.SCOPE_VIEWS:
                setViewList(proposals);
                break;          
            case SQLParser.SCOPE_INDEXES:
                setIndexList(proposals);
                break;          
            case SQLParser.SCOPE_SEGMENT:
                setSegmentList(proposals);
                break;          
            default: 
            	// we are in some undermined state as of now but probably looking at the
                // expected sequence, we can add proposals to the list. So lets begin
            	//203804
                setOtherList(proposals);
                break;
        }

	}

	private ArrayList getProposalsByRelevance(ArrayList proposals, int relevance) {
		ArrayList result = new ArrayList();
		for (Iterator iter = proposals.iterator(); iter.hasNext();) {
			SQLCompletionProposal prop = (SQLCompletionProposal) iter.next();
			if (prop.getRelevance() == relevance)
			{
				result.add(prop);
			}
		}
		return result;
	}


}
