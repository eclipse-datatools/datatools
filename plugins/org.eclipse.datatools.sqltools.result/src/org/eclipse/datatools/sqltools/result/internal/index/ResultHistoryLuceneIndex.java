/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.result.internal.index;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.WhitespaceAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Hits;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.Searcher;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.eclipse.datatools.sqltools.result.OperationCommand;
import org.eclipse.datatools.sqltools.result.internal.ResultsViewPlugin;
import org.eclipse.datatools.sqltools.result.internal.model.IResultInstance;
import org.eclipse.datatools.sqltools.result.internal.utils.ILogger;

/**
 * Lucene based index
 * 
 * @author Dafan Yang
 */
public class ResultHistoryLuceneIndex implements IResultHistoryIndex
{
    private static ILogger      _log             = ResultsViewPlugin.getLogger(null);
    private static final String FIELD_OPERATION  = "operation";                      //$NON-NLS-1$                        //$NON-NLS-1$
    private static final String FIELD_ACTION     = "action";                         //$NON-NLS-1$
    private static final String FIELD_CONSUMER   = "consumer";                       //$NON-NLS-1$
    private static final String FIELD_FREQ       = "frequency";                      //$NON-NLS-1$
    private static final String FIELD_IDENTIFIER = "identifier";                     //$NON-NLS-1$
    private static int          ID               = 10000;
    private Map                 _id2result;
    private Map                 _result2id;
    private Directory           _ramDir;
    private IndexWriter         _writer;
    private Analyzer            _analyzer;
    
    public ResultHistoryLuceneIndex()
    {
        _ramDir = new RAMDirectory();
        
        // Use which space tokenizer as the tokenizer
        _analyzer = new WhitespaceAnalyzer();
        _id2result = new HashMap();
        _result2id = new HashMap();
        
        // Create the index
        try
        {
            _writer = new IndexWriter(_ramDir, _analyzer, true);
            _writer.close();
        }
        catch(IOException ioe)
        {
            throw new RuntimeException(ioe);
        }
    }

    public void addResult(IResultInstance instance)
    {
        addResults(new IResultInstance[]
        {
            instance
        });
    }

    public void addResults(IResultInstance[] instances)
    {
        synchronized (this)
        {
            if (instances != null)
            {
                try
                {
                    // Append new document to the index
                    _writer = new IndexWriter(_ramDir, _analyzer, false);
                    for (int i = 0; i < instances.length; i++)
                    {
                        IResultInstance instance = instances[i];
                        if (instance != null)
                        {
                            Document doc = new Document();
                            doc.add(Field.Text(FIELD_OPERATION, instance.getOperationCommand().getDisplayString()));
                            doc.add(Field.Text(FIELD_ACTION, OperationCommand.getActionString(instance
                                    .getOperationCommand().getActionType())));
                            doc.add(Field.Text(FIELD_CONSUMER, instance.getOperationCommand().getConsumerName()));
                            doc.add(Field.Text(FIELD_FREQ, Integer.toString(instance.getFrequency())));
                            doc.add(Field.Keyword(FIELD_IDENTIFIER, Integer.toString(ID)));
                            _id2result.put(Integer.toString(ID), instance);
                            _result2id.put(instance, Integer.toString(ID));
                            ID++;
                            try
                            {
                                _writer.addDocument(doc);
                            }
                            catch (IOException ioe)
                            {
                                _log.error("ResultHistoryLuceneIndex_io_error", ioe); //$NON-NLS-1$
                            }
                        }
                    }
                    _writer.close();
                }
                catch (IOException ioe)
                {
                    _log.error("ResultHistoryLuceneIndex_io_error", ioe); //$NON-NLS-1$
                }
            }
        }
    }

    public void removeResult(IResultInstance instance)
    {
        removeResults(new IResultInstance[]
        {
            instance
        });
    }

    public void removeResults(IResultInstance[] instances)
    {
        synchronized (this)
        {
            try
            {
                IndexReader reader = IndexReader.open(_ramDir);
                if(instances != null)
                {
                    IResultInstance instance = null;
                    for(int i=0;i<instances.length;i++)
                    {
                        instance = instances[i];
                        if(instance != null)
                        {
                            String id = (String)_result2id.get(instance);
                            _result2id.remove(instance);
                            _id2result.remove(id);
                            
                            if(id != null)
                            {
                                try
                                {
                                    reader.delete(new Term(FIELD_IDENTIFIER, id));
                                }
                                catch(IOException ioe)
                                {
                                    _log.error("ResultHistoryLuceneIndex_io_error", ioe); //$NON-NLS-1$
                                }
                            }
                        }
                    }
                    reader.close();
                }
            }
            catch(IOException ioe)
            {
                _log.error("ResultHistoryLuceneIndex_io_error", ioe); //$NON-NLS-1$
            }
        }
    }

    public IResultInstance[] search(String expression)
    {
        if (expression == null)
        {
            return new IResultInstance[0];
        }
        synchronized (this)
        {
            QueryParser parser = new QueryParser(FIELD_OPERATION, _analyzer);
            try
            {
                Query query = parser.parse(expression);
                Searcher searcher = new IndexSearcher(_ramDir);
                Hits hits = searcher.search(query);
                int count = hits.length();
                IResultInstance[] instances = new IResultInstance[count];
                
                for(int i=0;i<count;i++)
                {
                    Document doc = hits.doc(i);
                    instances[i] = (IResultInstance)_id2result.get(doc.getField(FIELD_IDENTIFIER).stringValue());
                }
                return instances;
            }
            catch(ParseException pe)
            {
                // Ignore
            }
            catch(IOException ioe)
            {
                _log.error("ResultHistoryLuceneIndex_io_error", ioe); //$NON-NLS-1$
            }
            return new IResultInstance[0];
        }
    }

    public void refreshResult(IResultInstance instance)
    {
        synchronized (instance)
        {
            removeResult(instance);
            addResult(instance);
        }
    }
}
