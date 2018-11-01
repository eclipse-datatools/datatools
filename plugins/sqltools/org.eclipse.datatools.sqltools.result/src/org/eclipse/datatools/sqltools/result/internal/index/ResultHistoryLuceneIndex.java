/*******************************************************************************
 * Copyright (c) 2005, 2017 Sybase and Other Contributors
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *******************************************************************************/

package org.eclipse.datatools.sqltools.result.internal.index;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.eclipse.datatools.sqltools.result.OperationCommand;
import org.eclipse.datatools.sqltools.result.ResultsViewPlugin;
import org.eclipse.datatools.sqltools.result.internal.utils.ILogger;
import org.eclipse.datatools.sqltools.result.model.IResultInstance;

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
    private List                _instances;
    public ResultHistoryLuceneIndex()
    {
        _ramDir = new RAMDirectory();
        
        _analyzer = new StandardAnalyzer();
        _id2result = new HashMap();
        _result2id = new HashMap();
        _instances = new ArrayList();
        
        // Create the index
        try
        {
            IndexWriterConfig conf = new IndexWriterConfig(_analyzer);
            conf.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
            _writer = new IndexWriter(_ramDir, conf);
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

    private String getCombinedDisplayString(IResultInstance instance)
    {
        StringBuffer sb = new StringBuffer("");
        sb.append(instance.getOperationCommand().getDisplayString()).append(" ");
        Iterator iter = instance.getSubResults().iterator();
        while(iter.hasNext())
        {
            IResultInstance ins = (IResultInstance)iter.next();
            sb.append(getCombinedDisplayString(ins));
        }
        return sb.toString();
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
                    IndexWriterConfig conf = new IndexWriterConfig(_analyzer);
                    conf.setOpenMode(IndexWriterConfig.OpenMode.APPEND);
                    _writer = new IndexWriter(_ramDir, conf);
                    for (int i = 0; i < instances.length; i++)
                    {
                        IResultInstance instance = instances[i];
                        if(_instances.contains(instance))
                        {
                            continue;
                        }
                        if (instance != null)
                        {
                            _instances.add(instance);
                            Document doc = new Document();
                            doc.add(new TextField(FIELD_OPERATION, getCombinedDisplayString(instance), Field.Store.YES));
                            doc.add(new TextField(FIELD_ACTION, OperationCommand.getActionString(instance
                                    .getOperationCommand().getActionType()), Field.Store.YES));
                            doc.add(new TextField(FIELD_CONSUMER, instance.getOperationCommand().getConsumerName(), Field.Store.YES));
                            doc.add(new TextField(FIELD_FREQ, Integer.toString(instance.getFrequency()), Field.Store.YES));
                            doc.add(new StringField(FIELD_IDENTIFIER, Integer.toString(ID), Field.Store.YES));
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
                if(instances != null)
                {
                    IndexWriterConfig conf = new IndexWriterConfig(_analyzer);
                    conf.setOpenMode(IndexWriterConfig.OpenMode.APPEND);
                    _writer = new IndexWriter(_ramDir, conf);
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
                                    _writer.deleteDocuments(new Term(FIELD_IDENTIFIER, id));
                                }
                                catch(IOException ioe)
                                {
                                    _log.error("ResultHistoryLuceneIndex_io_error", ioe); //$NON-NLS-1$
                                }
                            }
                        }
                    }
                    _writer.close();
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
                IndexReader reader = DirectoryReader.open(_ramDir);
                Query query = parser.parse(expression);
                IndexSearcher searcher = new IndexSearcher(reader);
                TopDocs hits = searcher.search(query, reader.maxDoc());
                int count = (int)hits.totalHits;
                IResultInstance[] instances = new IResultInstance[count];
                
                for(int i=0;i<count;i++)
                {
		            int docID = hits.scoreDocs[i].doc;
                    Document doc = searcher.doc(docID);
                    instances[i] = (IResultInstance)_id2result.get(doc.getField(FIELD_IDENTIFIER).stringValue());
                }
                reader.close();
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
