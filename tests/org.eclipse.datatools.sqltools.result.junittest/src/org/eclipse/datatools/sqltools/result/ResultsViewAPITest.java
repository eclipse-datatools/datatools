/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.result;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * Test cases for <code>ResultsViewAPI</code>, this class tries to print out all the useful information to the
 * console for the tester, if the tester also need to view the results in real SQL Results View, do the following steps:
 * <ul>
 * <li>Set the workspace location of Junit luanch configuration the same as which of eclipse application on which SQL
 * Results View plugin is running (Because all the two kinds of launches need to read the persisted results saved in
 * workspace);
 * <li>Uncheck the "Clear workspace data before launch" for both Junit and eclipse application launch dialog, in this
 * case, the results generated in the test cases can be reserved;
 * <li>After running serveral cases, run the eclipse application launch on which SQL Results View is running.
 * <li>Make sure the option "Data tools->SQL Results View Options->History Options->Automatically persist result
 * history" and option "Display results of unresolvable connection profiles" on SQL Results Filters dialog are both
 * turned on
 * </ul>
 * 
 * @author Dafan Yang
 */
public class ResultsViewAPITest extends TestCase
{
    ResultsViewAPI _resultsViewAPI;
    
    public ResultsViewAPITest(String name)
    {
        super(name);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        _resultsViewAPI = ResultsViewAPI.getInstance();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /**
     * Tests getting the status of a result instance
     *
     */
    public void testGetCurrentStatus()
    {
        OperationCommand cmd = new OperationCommand(OperationCommand.ACTION_EXECUTE, "Junit test string", "Junit",
                "ase", "master");
        boolean succeeded = true;
        succeeded = _resultsViewAPI.createNewInstance(cmd, null);
        System.out.println("Create a new result instance (expect:true):" + succeeded);
        Assert.assertEquals(true, succeeded);
        
        succeeded = _resultsViewAPI.updateStatus(cmd, OperationCommand.STATUS_CRITICAL_ERROR);
        System.out.println("Update status (expect:true):" + succeeded);
        Assert.assertEquals(true, succeeded);
        
        System.out
                .println("Current status: " + OperationCommand.getStatusString(_resultsViewAPI.getCurrentStatus(cmd)));
        Assert.assertEquals(OperationCommand.STATUS_CRITICAL_ERROR, _resultsViewAPI.getCurrentStatus(cmd));
        
        succeeded = _resultsViewAPI.updateStatus(cmd, OperationCommand.STATUS_SUCCEEDED);
        System.out.println("Update status (expect:false):" + succeeded);
        Assert.assertEquals(false, succeeded);
    }

    /**
     * Creates a new result instance and append plain messages to it, then set its status
     *
     */
    public void testAppendPlainMessage()
    {
        OperationCommand cmd = new OperationCommand(OperationCommand.ACTION_EXECUTE, "Junit test string", "Junit",
                "ase", "master");
        boolean succeeded = true;
        succeeded = _resultsViewAPI.createNewInstance(cmd, null);
        System.out.println("Create a new result instance (expect:true):" + succeeded);
        Assert.assertEquals(true, succeeded);
        
        succeeded = _resultsViewAPI.appendPlainMessage(cmd, "Junit plain message - 1");
        System.out.println("Append plain message (expect:true):" + succeeded);
        Assert.assertEquals(true, succeeded);
        
        // negative case
        succeeded = _resultsViewAPI.appendPlainMessage(cmd, null);
        System.out.println("Append plain message (expect:false):" + succeeded);
        Assert.assertEquals(false, succeeded);
        
        succeeded = _resultsViewAPI.appendPlainMessage(cmd, "Junit plain message - 3");
        System.out.println("Append plain message (expect:true):" + succeeded);
        Assert.assertEquals(true, succeeded);
        
        succeeded = _resultsViewAPI.appendPlainMessage(cmd, "Junit plain message - 4");
        System.out.println("Append plain message (expect:true):" + succeeded);
        Assert.assertEquals(true, succeeded);
        
        // negative case
        succeeded = _resultsViewAPI.appendPlainMessage(null, "Junit plain message - 5");
        System.out.println("Append plain message (expect:false):" + succeeded);
        Assert.assertEquals(false, succeeded);
        
        succeeded = _resultsViewAPI.updateStatus(cmd, OperationCommand.STATUS_SUCCEEDED);
        System.out.println("Update status (expect:true):" + succeeded);
        Assert.assertEquals(true, succeeded);
        
        // negative case
        succeeded = _resultsViewAPI.appendPlainMessage(cmd, "message");
        Assert.assertEquals(false, succeeded);
    }

    /**
     * Create new result instance and append result set object, then set its status
     *
     */
    public void testAppendResultSetOperationCommandIResultSetObject()
    {
        OperationCommand cmd = new OperationCommand(OperationCommand.ACTION_EXECUTE, "Junit test string", "Junit",
                "ase", "master");
        boolean succeeded = true;
        succeeded = _resultsViewAPI.createNewInstance(cmd, null);
        System.out.println("Create a new result instance (expect:true):" + succeeded);
        Assert.assertEquals(true, succeeded);
        
        List rows = new ArrayList();
        IResultSetRow row1 = new ResultSetRow(new Object[]
        {
            new Integer(1), "Name-1"
        });
        IResultSetRow row2 = new ResultSetRow(new Object[]
        {
            new Integer(2), "Name-2"
        });
        IResultSetRow row3 = new ResultSetRow(new Object[]
        {
            new Integer(3), "Name-3"
        });
        
        // negative case
        IResultSetRow row4 = new ResultSetRow(new Object[]
        {
            new Integer(4), null
        });
        rows.add(row1);
        rows.add(row2);
        rows.add(row3);
        rows.add(row4);
        
        String[] headings = new String[]
        {
            "id", "name"
        };
        int[] types = new int[]
        {
            Types.INTEGER, Types.CHAR
        };
        int[] sizes = new int[]
        {
            5, 20
        };
        IResultSetObject rs = new ResultSetObject(rows, headings, types, sizes);
        
        succeeded = _resultsViewAPI.appendResultSet(cmd, rs);
        System.out.println("Append result set object (expect:true):" + succeeded);
        Assert.assertEquals(true, succeeded);
        
        // negative case
        succeeded = _resultsViewAPI.appendResultSet(cmd, (IResultSetObject) null);
        System.out.println("Append result set object (expect:false):" + succeeded);
        Assert.assertEquals(false, succeeded);
        
        succeeded = _resultsViewAPI.updateStatus(cmd, OperationCommand.STATUS_SUCCEEDED);
        System.out.println("Update status (expect:true):" + succeeded);
        Assert.assertEquals(true, succeeded);
        
        // negative case
        succeeded = _resultsViewAPI.appendResultSet(cmd, rs);
        Assert.assertEquals(false, succeeded);
    }

    /**
     * Create new result instance and append result set to it, then set its status.
     * <p>
     * ATTN: This case wont pass unless the url is properly set, and the driver class for Sybase database can be found
     * 
     */
    public void testAppendResultSetOperationCommandResultSet()
    {
        OperationCommand cmd = new OperationCommand(OperationCommand.ACTION_EXECUTE, "Junit test string", "Junit",
                "ase", "master");
        boolean succeeded = true;
        succeeded = _resultsViewAPI.createNewInstance(cmd, null);
        System.out.println("Create a new result instance (expect:true):" + succeeded);
        Assert.assertEquals(true, succeeded);
        
        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;
        
        // put a right url here when testing this case
        String url = "jdbc:sybase:Tds:127.0.0.1:5000/master";
        try
        {
            // put a right driver class when testing this case
            Class.forName("com.sybase.jdbc3.jdbc.SybDriver").newInstance();
            
            // put a right user name and password here when testing this case
            conn = DriverManager.getConnection(url, "sa", "");
            stat = conn.createStatement();
            try
            {
                stat.execute("drop table tst_table_123");
            }
            catch (Exception ex)
            {
                // expected
            }
            stat.execute("create table tst_table_123 (idd int, name1 char(10))");
            stat.executeUpdate("insert into tst_table_123 values (1, 'Jack')");
            stat.executeUpdate("insert into tst_table_123 values (2, 'Mike')");
            rs = stat.executeQuery("select * from tst_table_123");
            succeeded = _resultsViewAPI.appendResultSet(cmd, rs);
            System.out.println("Append result set (expect:true):" + succeeded);
            Assert.assertEquals(true, succeeded);
            
            // negative case
            succeeded = _resultsViewAPI.appendResultSet(cmd, (ResultSet)null);
            System.out.println("Append result set (expect:false):" + succeeded);
            Assert.assertEquals(false, succeeded);
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
            succeeded = _resultsViewAPI.appendStatusMessage(cmd, ex.getMessage());
            System.out.println("Append status message (except:true):" + succeeded);
            Assert.assertEquals(true, succeeded);
            
            succeeded = _resultsViewAPI.updateStatus(cmd, OperationCommand.STATUS_FAILED);
            System.out.println("Update status (expect:true):" + succeeded);
            Assert.assertEquals(true, succeeded);
            Assert.fail(ex.getMessage());
        }
        finally
        {
            try
            {
                if (rs != null)
                {
                    rs.close();
                }
                if (stat != null)
                {
                    stat.close();
                }
                if (conn != null && !conn.isClosed())
                {
                    conn.close();
                }
            }
            catch (SQLException sqlex)
            {
                // ignore
            }
        }
        succeeded = _resultsViewAPI.updateStatus(cmd, OperationCommand.STATUS_SUCCEEDED);
        System.out.println("Update status (expect:true):" + succeeded);
        Assert.assertEquals(true, succeeded);
    }

    /**
     * Creates a new result instance and append some status messages, then set its status to "SUCCEEDED"
     * 
     */
    public void testAppendStatusMessage()
    {
        OperationCommand cmd = new OperationCommand(OperationCommand.ACTION_EXECUTE, "Junit test string", "Junit",
                "ase", "master");
        boolean succeeded = true;
        succeeded = _resultsViewAPI.createNewInstance(cmd, null);
        System.out.println("Create a new result instance (expect:true):" + succeeded);
        Assert.assertEquals(true, succeeded);
        
        succeeded = _resultsViewAPI.appendStatusMessage(cmd, "Junit status message - 1");
        System.out.println("Append status message (expect:true):" + succeeded);
        Assert.assertEquals(true, succeeded);
        
        // negative case
        succeeded = _resultsViewAPI.appendStatusMessage(null, "Junit status message - 2");
        System.out.println("Append status message (expect:false):" + succeeded);
        Assert.assertEquals(false, succeeded);
        
        succeeded = _resultsViewAPI.appendStatusMessage(cmd, "Junit status message - 3");
        System.out.println("Append status message (expect:true):" + succeeded);
        Assert.assertEquals(true, succeeded);
        
        succeeded = _resultsViewAPI.appendStatusMessage(cmd, "Junit status message - 4");
        System.out.println("Append status message (expect:true):" + succeeded);
        Assert.assertEquals(true, succeeded);
        
        // negative case
        succeeded = _resultsViewAPI.appendStatusMessage(null, "Junit status message - 5");
        System.out.println("Append status message (expect:false):" + succeeded);
        Assert.assertEquals(false, succeeded);
        
        succeeded = _resultsViewAPI.updateStatus(cmd, OperationCommand.STATUS_SUCCEEDED);
        System.out.println("Update status (expect:true):" + succeeded);
        Assert.assertEquals(true, succeeded);
        
        // negative case
        succeeded = _resultsViewAPI.appendStatusMessage(cmd, "message");
        Assert.assertEquals(false, succeeded);
    }

    /**
     * Creates a new result instance, and append some update count message to this result, then set its status to
     * "SUCCEEDED"
     * 
     */
    public void testAppendUpdateCountMessage()
    {
        OperationCommand cmd = new OperationCommand(OperationCommand.ACTION_EXECUTE, "Junit test string", "Junit",
                "ase", "master");
        boolean succeeded = true;
        succeeded = _resultsViewAPI.createNewInstance(cmd, null);
        System.out.println("Create a new result instance (expect:true):" + succeeded);
        Assert.assertEquals(true, succeeded);
        
        succeeded = _resultsViewAPI.appendUpdateCountMessage(cmd, 1);
        System.out.println("Append an update count message (expect:true):" + succeeded);
        Assert.assertEquals(true, succeeded);
        
        // negative case
        succeeded = _resultsViewAPI.appendUpdateCountMessage(null, 32);
        System.out.println("Append an update count message (expect:false):" + succeeded);
        Assert.assertEquals(false, succeeded);
        
        // negative case
        succeeded = _resultsViewAPI.appendUpdateCountMessage(cmd, -10);
        System.out.println("Append an update count message (expect:false):" + succeeded);
        Assert.assertEquals(false, succeeded);
        
        succeeded = _resultsViewAPI.appendUpdateCountMessage(cmd, 0);
        System.out.println("Append an update count message (expect:ture):" + succeeded);
        Assert.assertEquals(true, succeeded);
        
        succeeded = _resultsViewAPI.appendUpdateCountMessage(cmd, 6);
        System.out.println("Append an update count message (expect:true):" + succeeded);
        Assert.assertEquals(true, succeeded);
        
        succeeded = _resultsViewAPI.updateStatus(cmd, OperationCommand.STATUS_SUCCEEDED);
        System.out.println("Update status (expect:true):" + succeeded);
        Assert.assertEquals(true, succeeded);
        
        // negative case
        succeeded = _resultsViewAPI.appendUpdateCountMessage(cmd, 3);
        Assert.assertEquals(false, succeeded);
    }

    /**
     * Creates a new result instance, and append an XML result set to this
     * result, then set its status to "SUCCEEDED".
     * 
     */
    public void testAppendXMLResultSet()
    {
        OperationCommand cmd = new OperationCommand(OperationCommand.ACTION_EXECUTE, "Junit test string", "Junit",
                "ase", "master");
        boolean succeeded = true;
        succeeded = _resultsViewAPI.createNewInstance(cmd, null);
        System.out.println("Create a new result instance (expect:true):" + succeeded);
        Assert.assertEquals(true, succeeded);
        
        String xml = "<junit>This is a test xml document for testing purpose</junit>";
        succeeded = _resultsViewAPI.appendXMLResultSet(cmd, xml);
        System.out.println("Append an XML result (expect:true):" + succeeded);
        Assert.assertEquals(true, succeeded);
        
        // negative case
        xml = "<junit>This is a test xml document for testing purpose</juni>";
        succeeded = _resultsViewAPI.appendXMLResultSet(cmd, xml);
        System.out.println("Append an XML result (expect:false):" + succeeded);
        Assert.assertEquals(false, succeeded);
        
        succeeded = _resultsViewAPI.updateStatus(cmd, OperationCommand.STATUS_SUCCEEDED);
        System.out.println("Update status (expect:true):" + succeeded);
        Assert.assertEquals(true, succeeded);
        
        // negative case
        succeeded = _resultsViewAPI.appendXMLResultSet(cmd, "<a>a</a>");
        Assert.assertEquals(false, succeeded);
    }

    /**
     * Creates new result instance and set its status to "SUCCEEDED".
     * 
     */
    public void testCreateNewInstance()
    {
        System.out.println(ResultsViewAPI.getInstance().getResultManager().getAllResults().length
                + " result(s) found in results manager before the test case is running");
        int numBefore = ResultsViewAPI.getInstance().getResultManager().getAllResults().length;
        
        OperationCommand cmd = new OperationCommand(OperationCommand.ACTION_EXECUTE, "Junit test string", "Junit",
                "ase", "master");
        boolean succeeded = true;
        succeeded = _resultsViewAPI.createNewInstance(cmd, null);
        System.out.println("Create a new result instance (expect:true):" + succeeded);
        Assert.assertEquals(true, succeeded);
        
        succeeded = _resultsViewAPI.updateStatus(cmd, OperationCommand.STATUS_SUCCEEDED);
        System.out.println("Update status (expect:true):" + succeeded);
        Assert.assertEquals(true, succeeded);
        
        // negative case
        cmd = new OperationCommand(-1, null, null, null, null);
        succeeded = _resultsViewAPI.createNewInstance(cmd, null);
        System.out.println("Create a new result instance (expect:true):" + succeeded);
        Assert.assertEquals(true, succeeded);
        
        succeeded = _resultsViewAPI.updateStatus(cmd, OperationCommand.STATUS_SUCCEEDED);
        System.out.println("Update status (expect:true):" + succeeded);
        Assert.assertEquals(true, succeeded);
        
        System.out.println(ResultsViewAPI.getInstance().getResultManager().getAllResults().length
                + " result(s) found in results manager after the running the test case");
        Assert.assertEquals(numBefore + 2, ResultsViewAPI.getInstance().getResultManager().getAllResults().length);
    }

    /**
     * Creates a new result instance, show the parameters and set its status to
     * "SUCCEEDED".
     * 
     */
    public void testShowParameters()
    {
        OperationCommand cmd = new OperationCommand(OperationCommand.ACTION_EXECUTE, "Junit test string", "Junit",
                "ase", "master");
        boolean succeeded = true;
        succeeded = _resultsViewAPI.createNewInstance(cmd, null);
        System.out.println("Create a new result instance (expect:true):" + succeeded);
        Assert.assertEquals(true, succeeded);
        
        List params = new ArrayList();
        Parameter param = new Parameter("@param1", Parameter.INPUT, "abc", "varchar(5)");
        params.add(param);
        
        // negative case
        params.add(null);
        
        // negative case
        Parameter param1 = new Parameter("@param2", Parameter.INPUT, null, null);
        params.add(param1);
        Parameter param2 = new Parameter("@param3", Parameter.IN_OUT, "a", "char(5)","b");
        params.add(param2);
        
        succeeded = _resultsViewAPI.showParameters(cmd, params);
        System.out.println("Show parameters (expect:true):" + succeeded);
        Assert.assertEquals(true, succeeded);
        
        succeeded = _resultsViewAPI.updateStatus(cmd, OperationCommand.STATUS_SUCCEEDED);
        System.out.println("Update status (expect:true):" + succeeded);
        Assert.assertEquals(true, succeeded);
    }

    /**
     * Creates a new result instance, and update its status multiple times.
     *
     */
    public void testUpdateStatus()
    {
        OperationCommand cmd = new OperationCommand(OperationCommand.ACTION_EXECUTE, "Junit test string", "Junit",
                "ase", "master");
        boolean succeeded = true;
        succeeded = _resultsViewAPI.createNewInstance(cmd, null);
        System.out.println("Create a new result instance (expect:true):" + succeeded);
        Assert.assertEquals(true, succeeded);
        
        succeeded = _resultsViewAPI.updateStatus(cmd, OperationCommand.STATUS_CRITICAL_ERROR);
        System.out.println("Update status (expect:true):" + succeeded);
        Assert.assertEquals(true, succeeded);
        
        // negative cases
        succeeded = _resultsViewAPI.updateStatus(cmd, OperationCommand.STATUS_FAILED);
        System.out.println("Update status (expect:false):" + succeeded);
        Assert.assertEquals(false, succeeded);
        succeeded = _resultsViewAPI.updateStatus(cmd, OperationCommand.STATUS_RUNNING);
        System.out.println("Update status (expect:false):" + succeeded);
        Assert.assertEquals(false, succeeded);
        succeeded = _resultsViewAPI.updateStatus(cmd, OperationCommand.STATUS_STARTED);
        System.out.println("Update status (expect:false):" + succeeded);
        Assert.assertEquals(false, succeeded);
        succeeded = _resultsViewAPI.updateStatus(cmd, OperationCommand.STATUS_SUCCEEDED);
        System.out.println("Update status (expect:false):" + succeeded);
        Assert.assertEquals(false, succeeded);
        succeeded = _resultsViewAPI.updateStatus(cmd, OperationCommand.STATUS_TERMINATED);
        System.out.println("Update status (expect:false):" + succeeded);
        Assert.assertEquals(false, succeeded);
        succeeded = _resultsViewAPI.updateStatus(cmd, OperationCommand.STATUS_WARNING);
        System.out.println("Update status (expect:false):" + succeeded);
        Assert.assertEquals(false, succeeded);
    }
    
    /**
     * Create a sub instance of an existing instance
     *
     */
    public void testCreateSubInstance()
    {
        OperationCommand cmd = new OperationCommand(OperationCommand.ACTION_EXECUTE, "Junit test string", "Junit",
                "ase", "master");
        boolean succeeded = true;
        succeeded = _resultsViewAPI.createNewInstance(cmd, null);
        assertEquals(true, succeeded);
        
        OperationCommand subCmd = new OperationCommand(OperationCommand.ACTION_EXECUTE, "Junit test string", "Junit",
                "ase", "master");
        succeeded = _resultsViewAPI.createSubInstance(cmd, subCmd, null);
        assertEquals(true, succeeded);
        
        succeeded = _resultsViewAPI.updateStatus(subCmd, OperationCommand.STATUS_SUCCEEDED);
        assertEquals(true, succeeded);
        
        succeeded = _resultsViewAPI.updateStatus(cmd, OperationCommand.STATUS_SUCCEEDED);
        assertEquals(true, succeeded);
        
        // negative case
        OperationCommand subCmd1 = new OperationCommand(OperationCommand.ACTION_EXECUTE, "Junit test string", "Junit",
                "ase", "master");
        succeeded = _resultsViewAPI.createSubInstance(cmd, subCmd1, null);
        assertEquals(false, succeeded);
    }
    
    /**
     * Create two sub instances, and get them
     *
     */
    public void testGetSubOperationCommand()
    {
        OperationCommand cmd = new OperationCommand(OperationCommand.ACTION_EXECUTE, "Junit test string", "Junit",
                "ase", "master");
        boolean succeeded = true;
        succeeded = _resultsViewAPI.createNewInstance(cmd, null);
        assertEquals(true, succeeded);
        
        OperationCommand subCmd1 = new OperationCommand(OperationCommand.ACTION_EXECUTE, "Junit test string", "Junit",
                "ase", "master");
        succeeded = _resultsViewAPI.createSubInstance(cmd, subCmd1, null);
        assertEquals(true, succeeded);
        
        succeeded = _resultsViewAPI.updateStatus(subCmd1, OperationCommand.STATUS_SUCCEEDED);
        assertEquals(true, succeeded);
        
        OperationCommand subCmd2 = new OperationCommand(OperationCommand.ACTION_EXECUTE, "Junit test string", "Junit",
                "ase", "master");
        succeeded = _resultsViewAPI.createSubInstance(cmd, subCmd2, null);
        assertEquals(true, succeeded);
        
        succeeded = _resultsViewAPI.updateStatus(subCmd2, OperationCommand.STATUS_SUCCEEDED);
        assertEquals(true, succeeded);
        
        succeeded = _resultsViewAPI.updateStatus(cmd, OperationCommand.STATUS_SUCCEEDED);
        assertEquals(true, succeeded);
        
        OperationCommand subCommandReturned1 = _resultsViewAPI.getSubOperationCommand(cmd, 0);
        assertNotNull(subCommandReturned1);
        
        assertEquals(subCmd1, subCommandReturned1);
        
        OperationCommand subCommandReturned2 = _resultsViewAPI.getSubOperationCommand(cmd, 1);
        assertNotNull(subCommandReturned2);
        
        assertEquals(subCmd2, subCommandReturned2);
    }
    
    /**
     * Create two sub instances, change the status of them, calculte the status of the parent instance
     *
     */
    public void testCalculateStatus()
    {
        OperationCommand cmd = new OperationCommand(OperationCommand.ACTION_EXECUTE, "Junit test string", "Junit",
                "ase", "master");
        boolean succeeded = true;
        succeeded = _resultsViewAPI.createNewInstance(cmd, null);
        assertEquals(true, succeeded);
        
        OperationCommand subCmd1 = new OperationCommand(OperationCommand.ACTION_EXECUTE, "Junit test string", "Junit",
                "ase", "master");
        succeeded = _resultsViewAPI.createSubInstance(cmd, subCmd1, null);
        assertEquals(true, succeeded);
        
        succeeded = _resultsViewAPI.updateStatus(subCmd1, OperationCommand.STATUS_SUCCEEDED);
        assertEquals(true, succeeded);
        
        OperationCommand subCmd2 = new OperationCommand(OperationCommand.ACTION_EXECUTE, "Junit test string", "Junit",
                "ase", "master");
        succeeded = _resultsViewAPI.createSubInstance(cmd, subCmd2, null);
        assertEquals(true, succeeded);
        
        int status = _resultsViewAPI.calculateStatus(cmd);
        assertEquals(OperationCommand.STATUS_STARTED, status);
        
        succeeded = _resultsViewAPI.updateStatus(subCmd2, OperationCommand.STATUS_WARNING);
        assertEquals(true, succeeded);
        
        status = _resultsViewAPI.calculateStatus(cmd);
        assertEquals(OperationCommand.STATUS_WARNING, status);
        
        assertEquals(true, _resultsViewAPI.updateStatus(cmd, status));
    }
}
