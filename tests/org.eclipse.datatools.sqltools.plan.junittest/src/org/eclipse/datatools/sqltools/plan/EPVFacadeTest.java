package org.eclipse.datatools.sqltools.plan;

import org.eclipse.datatools.sqltools.plan.internal.IPlanInstance;
import org.eclipse.datatools.sqltools.plan.internal.PlanViewPlugin;

import junit.framework.Assert;
import junit.framework.TestCase;

public class EPVFacadeTest extends TestCase
{
    EPVFacade _facade;
    
    public EPVFacadeTest(String name)
    {
        super(name);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        _facade = EPVFacade.getInstance();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /*
     * Test method for 'org.eclipse.datatools.sqltools.plan.EPVFacade.createNewPlanInstance(PlanRequest)'
     */
    public void testCreateNewPlanInstance()
    {
        IPlanInstance[] instances = PlanViewPlugin.getPlanManager().getAllPlanInstances();
        System.out.println(instances.length + " plan(s) before creating a new plan instance");
        String sql = "select id, name from person";
        String dbid = "Adaptive Server Enterprise_15.0";
        PlanRequest request = new PlanRequest(sql, dbid, 0, PlanRequest.VIEW_ACTIVATE);
        _facade.createNewPlanInstance(request);
        IPlanInstance instance = PlanViewPlugin.getPlanManager().getPlanInstance(request);
        System.out.println("Create a new plan instance");
        Assert.assertEquals(IPlanInstance.RUNNING, instance.getStatus());
        
        int numBefore = instances.length;
        instances = PlanViewPlugin.getPlanManager().getAllPlanInstances();
        System.out.println(instances.length + " plan(s) after creating a new plan instance");
        
        Assert.assertEquals(numBefore + 1, instances.length);
        String planData = "The content of the TEXT plan";
        _facade.planGenerated(request, planData);
        
        Assert.assertEquals(planData, instance.getRawPlan());
        Assert.assertEquals(IPlanInstance.SUCCESS, instance.getStatus());
    }

    /*
     * Test method for 'org.eclipse.datatools.sqltools.plan.EPVFacade.planFailed(PlanRequest, Throwable)'
     */
    public void testPlanFailed()
    {
        IPlanInstance[] instances = PlanViewPlugin.getPlanManager().getAllPlanInstances();
        System.out.println(instances.length + " plan(s) before creating a new plan instance");
        String sql = "select id, name from person";
        String dbid = "Adaptive Server Enterprise_15.0";
        PlanRequest request = new PlanRequest(sql, dbid, 0, PlanRequest.VIEW_ACTIVATE);
        _facade.createNewPlanInstance(request);
        IPlanInstance instance = PlanViewPlugin.getPlanManager().getPlanInstance(request);
        System.out.println("Create a new plan instance");
        Assert.assertEquals(IPlanInstance.RUNNING, instance.getStatus());
        
        int numBefore = instances.length;
        instances = PlanViewPlugin.getPlanManager().getAllPlanInstances();
        System.out.println(instances.length + " plan(s) after creating a new plan instance");
        
        Assert.assertEquals(numBefore + 1, instances.length);
        
        _facade.planFailed(request, new Exception("Fail to generate plan for the request"));
        
        Assert.assertEquals(IPlanInstance.FAILED, instance.getStatus());
    }

    /*
     * Test method for 'org.eclipse.datatools.sqltools.plan.EPVFacade.planGenerated(PlanRequest, String)'
     */
    public void testPlanGenerated()
    {
        IPlanInstance[] instances = PlanViewPlugin.getPlanManager().getAllPlanInstances();
        System.out.println(instances.length + " plan(s) before creating a new plan instance");
        String sql = "select id, name from person";
        String dbid = "Adaptive Server Enterprise_15.0";
        PlanRequest request = new PlanRequest(sql, dbid, 1, PlanRequest.VIEW_VISIBLE);
        _facade.createNewPlanInstance(request);
        IPlanInstance instance = PlanViewPlugin.getPlanManager().getPlanInstance(request);
        System.out.println("Create a new plan instance");
        Assert.assertEquals(IPlanInstance.RUNNING, instance.getStatus());
        
        int numBefore = instances.length;
        instances = PlanViewPlugin.getPlanManager().getAllPlanInstances();
        System.out.println(instances.length + " plan(s) after creating a new plan instance");
        
        Assert.assertEquals(numBefore + 1, instances.length);
        String planData = "The content of the GRAPHIC plan";
        _facade.planGenerated(request, planData);
        
        Assert.assertEquals(planData, instance.getRawPlan());
        
        // will fail because no extension defined to draw graphic plan
        Assert.assertEquals(IPlanInstance.FAILED, instance.getStatus());
    }

}
