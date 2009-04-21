package org.eclipse.datatools.sqltools.result.junittest;

import org.eclipse.datatools.sqltools.result.ResultsViewAPITest;
import org.eclipse.datatools.sqltools.result.internal.index.ResultHistoryLuceneIndexTest;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllAvailableCasesForResultsView extends TestSuite
{

    public static Test suite()
    {
        TestSuite suite = new TestSuite("Test for org.eclipse.datatools.sqltools.result");
        //$JUnit-BEGIN$
        suite.addTestSuite(ResultsViewAPITest.class);
        suite.addTestSuite(ResultHistoryLuceneIndexTest.class);
        //$JUnit-END$
        return suite;
    }

}
