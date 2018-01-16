package org.eclipse.datatools.sqltools.result.internal.index;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.eclipse.datatools.sqltools.result.OperationCommand;
import org.eclipse.datatools.sqltools.result.ResultsViewAPI;
import org.eclipse.datatools.sqltools.result.ResultsViewPlugin;
import org.eclipse.datatools.sqltools.result.internal.core.IResultManager;
import org.eclipse.datatools.sqltools.result.model.IResultInstance;

/**
 * This class is to test the lucene index which is used for results searching purpose.
 * @author Dafan Yang
 */
public class ResultHistoryLuceneIndexTest extends TestCase
{
    IResultHistoryIndex _historyIndex;
    IResultManager _resultManager;
    ResultsViewAPI _resultsViewAPI;
    
    public ResultHistoryLuceneIndexTest(String name)
    {
        super(name);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        
        // initiate a new index
        _historyIndex = new ResultHistoryLuceneIndex();
        _resultManager = ResultsViewAPI.getInstance().getResultManager();
        _resultsViewAPI = ResultsViewAPI.getInstance();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /*
     * Test method for 'org.eclipse.datatools.sqltools.result.internal.index.ResultHistoryLuceneIndex.addResult(IResultInstance)'
     */
    public void testAddResult()
    {
        OperationCommand cmd = new OperationCommand(OperationCommand.ACTION_EXECUTE, "Junit test string", "Junit",
                "ase", "master");
        _resultsViewAPI.createNewInstance(cmd, null);
        IResultInstance instance = _resultManager.getInstance(cmd);
        _historyIndex.addResult(instance);
        System.out.println("Executed IResultHistoryIndex.addResult(instance)");
        
        IResultInstance[] instances = _historyIndex.search("test");
        System.out.println(instances.length + " result (s) found using 'test' to search (expect:1)");
        
        Assert.assertEquals(1, instances.length);
        
        // should have only one result without error
        for (int i = 0; i < instances.length; i++)
        {
            System.out.println("Display str: " + instances[i].getOperationCommand().getDisplayString());
        }
        
        instances = _historyIndex.search("Test");
        System.out.println(instances.length + " result (s) found using 'Test' to search (expect:0)");
        
        Assert.assertEquals(0, instances.length);
        // should have only no result without error (case sensitive)
        for (int i = 0; i < instances.length; i++)
        {
            System.out.println("Display str: " + instances[i].getOperationCommand().getDisplayString());
        }
        
        // negative case
        cmd = new OperationCommand(OperationCommand.ACTION_EXECUTE, null, null, null, null);
        _resultsViewAPI.createNewInstance(cmd, null);
        instance = _resultManager.getInstance(cmd);
        _historyIndex.addResult(instance);
        System.out.println("Executed IResultHistoryIndex.addResult(instance)");
        
        instances = _historyIndex.search("test");
        System.out.println(instances.length + " result (s) found using 'test' to search (expect:1)");
        Assert.assertEquals(1, instances.length);
        
        // should have only one result without error
        for (int i = 0; i < instances.length; i++)
        {
            System.out.println("Display str: " + instances[i].getOperationCommand().getDisplayString());
        }
    }

    /*
     * Test method for 'org.eclipse.datatools.sqltools.result.internal.index.ResultHistoryLuceneIndex.addResults(IResultInstance[])'
     */
    public void testAddResults()
    {
        IResultInstance[] instances = new IResultInstance[10];
        for(int i=0;i<9;i++)
        {
            OperationCommand cmd = new OperationCommand(OperationCommand.ACTION_EXECUTE, "Junit test string" + i,
                    "Junit", "ase", "master");
            _resultsViewAPI.createNewInstance(cmd, null);
            IResultInstance instance = _resultManager.getInstance(cmd);
            instances[i] = instance;
        }
        // negative case
        instances[9] = null;
        
        _historyIndex.addResults(instances);
        System.out.println("Executed IResultHistoryIndex.addResults(instances)");
        
        IResultInstance[] ins = _historyIndex.search("string7");
        System.out.println(ins.length + " result (s) found using 'string7' to search (expect:1)");
        Assert.assertEquals(1, ins.length);
        
        for (int i = 0; i < ins.length; i++)
        {
            System.out.println("Display str: " + ins[i].getOperationCommand().getDisplayString());
        }
        
        ins = _historyIndex.search("String6");
        System.out.println(ins.length + " result (s) found using 'String6' to search (expect:0)");
        Assert.assertEquals(0, ins.length);
        
        for (int i = 0; i < ins.length; i++)
        {
            System.out.println("Display str: " + ins[i].getOperationCommand().getDisplayString());
        }
    }

    /*
     * Test method for 'org.eclipse.datatools.sqltools.result.internal.index.ResultHistoryLuceneIndex.removeResult(IResultInstance)'
     */
    public void testRemoveResult()
    {
        IResultInstance[] instances = new IResultInstance[10];
        for(int i=0;i<10;i++)
        {
            OperationCommand cmd = new OperationCommand(OperationCommand.ACTION_EXECUTE, "Junit test string" + i,
                    "Junit", "ase", "master");
            _resultsViewAPI.createNewInstance(cmd, null);
            IResultInstance instance = _resultManager.getInstance(cmd);
            instances[i] = instance;
        }
        
        _historyIndex.addResults(instances);
        System.out.println("Executed IResultHistoryIndex.addResults(instances)");
        
        IResultInstance[] ins = _historyIndex.search("string7");
        System.out.println(ins.length + " result (s) found using 'string7' to search (expect:1)");
        Assert.assertEquals(1, ins.length);
        
        for (int i = 0; i < ins.length; i++)
        {
            System.out.println("Display str: " + ins[i].getOperationCommand().getDisplayString());
        }
        
        _historyIndex.removeResult(instances[7]);
        System.out.println("Executed IResultHistoryIndex.removeResult(instance) to remove the 8th result");
        
        ins = _historyIndex.search("string7");
        System.out.println(ins.length + " result (s) found using 'string7' to search (expect:0)");
        Assert.assertEquals(0, ins.length);
        
        for (int i = 0; i < ins.length; i++)
        {
            System.out.println("Display str: " + ins[i].getOperationCommand().getDisplayString());
        }
    }

    /*
     * Test method for 'org.eclipse.datatools.sqltools.result.internal.index.ResultHistoryLuceneIndex.removeResults(IResultInstance[])'
     */
    public void testRemoveResults()
    {
        IResultInstance[] instances = new IResultInstance[10];
        for(int i=0;i<10;i++)
        {
            OperationCommand cmd = new OperationCommand(OperationCommand.ACTION_EXECUTE, "Junit test string" + i,
                    "Junit", "ase", "master");
            _resultsViewAPI.createNewInstance(cmd, null);
            IResultInstance instance = _resultManager.getInstance(cmd);
            instances[i] = instance;
        }
        
        _historyIndex.addResults(instances);
        System.out.println("Executed IResultHistoryIndex.addResults(instances)");
        
        IResultInstance[] ins = _historyIndex.search("string7");
        System.out.println(ins.length + " result (s) found using 'string7' to search (expect:1)");
        Assert.assertEquals(1, ins.length);
        
        for (int i = 0; i < ins.length; i++)
        {
            System.out.println("Display str: " + ins[i].getOperationCommand().getDisplayString());
        }
        
        IResultInstance[] removingResults = new IResultInstance[4];
        removingResults[0] = instances[3];
        removingResults[1] = instances[5];
        removingResults[2] = instances[8];
        
        // negative case
        removingResults[3] = null;
        
        _historyIndex.removeResults(removingResults);
        
        System.out.println("Executed IResultHistoryIndex.removeResult(instance) to remove the 4th, 6th and 9th result");
        
        ins = _historyIndex.search("string3");
        System.out.println(ins.length + " result (s) found using 'string3' to search (expect:0)");
        Assert.assertEquals(0, ins.length);
        
        for (int i = 0; i < ins.length; i++)
        {
            System.out.println("Display str: " + ins[i].getOperationCommand().getDisplayString());
        }
        
        ins = _historyIndex.search("string5");
        System.out.println(ins.length + " result (s) found using 'string5' to search (expect:0)");
        Assert.assertEquals(0, ins.length);
        
        for (int i = 0; i < ins.length; i++)
        {
            System.out.println("Display str: " + ins[i].getOperationCommand().getDisplayString());
        }
        
        ins = _historyIndex.search("string8");
        System.out.println(ins.length + " result (s) found using 'string8' to search (expect:0)");
        Assert.assertEquals(0, ins.length);
        
        for (int i = 0; i < ins.length; i++)
        {
            System.out.println("Display str: " + ins[i].getOperationCommand().getDisplayString());
        }
    }

    /*
     * Test method for 'org.eclipse.datatools.sqltools.result.internal.index.ResultHistoryLuceneIndex.search(String)'
     */
    public void testSearch()
    {
        IResultInstance[] instances = new IResultInstance[10];
        for(int i=0;i<10;i++)
        {
            OperationCommand cmd = new OperationCommand(i+1, "Junit test string" + i,
                    "Junit" + (10 - i), "ase", "master");
            _resultsViewAPI.createNewInstance(cmd, null);
            IResultInstance instance = _resultManager.getInstance(cmd);
            instances[i] = instance;
        }
        
        _historyIndex.addResults(instances);
        System.out.println("Executed IResultHistoryIndex.addResults(instances)");
        
        IResultInstance[] ins = _historyIndex.search("string8");
        System.out.println(ins.length + " result (s) found using 'string8' to search (expect:1)");
        Assert.assertEquals(1, ins.length);
        
        for (int i = 0; i < ins.length; i++)
        {
            System.out.println("Display str: " + ins[i].getOperationCommand().getDisplayString());
        }
        
        ins = _historyIndex.search("operation:string8 AND consumer:Junit1");
        System.out.println(ins.length + " result (s) found using 'operation:string8 AND consumer:Junit1' to search (expect:0)");
        Assert.assertEquals(0, ins.length);
        
        for (int i = 0; i < ins.length; i++)
        {
            System.out.println("Display str: " + ins[i].getOperationCommand().getDisplayString());
        }
        
        ins = _historyIndex.search("operation:(string5 OR string6)");
        System.out.println(ins.length + " result (s) found using 'operation:(string5 OR string6)' to search (expect:2)");
        Assert.assertEquals(2, ins.length);
        for (int i = 0; i < ins.length; i++)
        {
            System.out.println("Display str: " + ins[i].getOperationCommand().getDisplayString());
        }
        
        ins = _historyIndex.search("operation:(string3 OR string8) AND consumer:(Junit2 OR Junit5)");
        System.out.println(ins.length + " result (s) found using 'operation:(string5 OR string6) AND consumer:(Junit2 OR Junit5)' to search (expect:1)");
        Assert.assertEquals(1, ins.length);
        
        for (int i = 0; i < ins.length; i++)
        {
            System.out.println("Display str: " + ins[i].getOperationCommand().getDisplayString());
        }
        
        ins = _historyIndex.search("operation:(string2 OR string7) OR action:Execute");
        System.out.println(ins.length + " result (s) found using 'operation:(string2 OR string7) OR action:Execute' to search (expect:3)");
        Assert.assertEquals(3, ins.length);
        
        for (int i = 0; i < ins.length; i++)
        {
            System.out.println("Display str: " + ins[i].getOperationCommand().getDisplayString());
        }
        
        ins = _historyIndex.search("");
        System.out.println(ins.length + " result (s) found using ' ' to search (expect:0)");
        Assert.assertEquals(0, ins.length);
        
        for (int i = 0; i < ins.length; i++)
        {
            System.out.println("Display str: " + ins[i].getOperationCommand().getDisplayString());
        }
        
        ins = _historyIndex.search("frequency:1");
        System.out.println(ins.length + " result (s) found using 'frequency:1' to search (expect:10)");
        Assert.assertEquals(10, ins.length);
        
        for (int i = 0; i < ins.length; i++)
        {
            System.out.println("Display str: " + ins[i].getOperationCommand().getDisplayString());
        }
        
        // negative case
        ins = _historyIndex.search(null);
        System.out.println(ins.length + " result (s) found using 'null' to search (expect:0)");
        Assert.assertEquals(0, ins.length);
        
        for (int i = 0; i < ins.length; i++)
        {
            System.out.println("Display str: " + ins[i].getOperationCommand().getDisplayString());
        }
        
        //search after group results feature is supported
        OperationCommand cmd = new OperationCommand(OperationCommand.ACTION_EXECUTE, "parent display string", "Junit",
                "ase", "master");
        boolean succeeded = true;
        succeeded = _resultsViewAPI.createNewInstance(cmd, null);
        assertEquals(true, succeeded);
        
        OperationCommand subCmd = new OperationCommand(OperationCommand.ACTION_EXECUTE, "sub display string", "Junit",
                "ase", "master");
        succeeded = _resultsViewAPI.createSubInstance(cmd, subCmd, null);
        assertEquals(true, succeeded);
        
        _historyIndex.addResult(_resultManager.getInstance(cmd));
        
        ins = _historyIndex.search("sub AND parent");
        assertEquals(1, ins.length);
        assertEquals("parent display string", ins[0].getOperationCommand().getDisplayString());
    }

    /*
     * Test method for 'org.eclipse.datatools.sqltools.result.internal.index.ResultHistoryLuceneIndex.refreshResult(IResultInstance)'
     */
    public void testRefreshResult()
    {
        OperationCommand cmd = new OperationCommand(OperationCommand.ACTION_EXECUTE, "Junit test string", "Junit",
                "ase", "master");
        _resultsViewAPI.createNewInstance(cmd, null);
        IResultInstance instance = _resultManager.getInstance(cmd);
        _historyIndex.addResult(instance);
        
        IResultInstance[] instances = _historyIndex.search("frequency:1");
        System.out.println(instances.length + " result (s) found using 'frequency:1' to search (expect:1)");
        Assert.assertEquals(1, instances.length);
        
        // should have only one result without error
        for (int i = 0; i < instances.length; i++)
        {
            System.out.println("Display str: " + instances[i].getOperationCommand().getDisplayString());
        }
        
        instance.increaseFrequency();
        instance.increaseFrequency();
        
        _historyIndex.refreshResult(instance);
        instances = _historyIndex.search("frequency:3");
        System.out.println(instances.length + " result (s) found using 'frequency:3' to search (expect:1)");
        Assert.assertEquals(1, instances.length);
        
        // should have only one result without error
        for (int i = 0; i < instances.length; i++)
        {
            System.out.println("Display str: " + instances[i].getOperationCommand().getDisplayString());
        }
        
    }

}
