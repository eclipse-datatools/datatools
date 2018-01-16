package org.eclipse.datatools.sqltools.plan.junittest;

import org.eclipse.datatools.sqltools.plan.EPVFacadeTest;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllAvailableCasesForEPV
{

    public static Test suite()
    {
        TestSuite suite = new TestSuite("Test for org.eclipse.datatools.sqltools.plan");
        //$JUnit-BEGIN$
        suite.addTestSuite(EPVFacadeTest.class);
        //$JUnit-END$
        return suite;
    }

}
