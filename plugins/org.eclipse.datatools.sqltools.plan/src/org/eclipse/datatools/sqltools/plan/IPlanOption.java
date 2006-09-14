/*
 * Created on 2005-8-30 Copyright (c) Sybase, Inc. 2004-2006 All rights reserved.
 */
package org.eclipse.datatools.sqltools.plan;

/**
 * @author Dafan Yang
 *  
 */
public interface IPlanOption
{
    /**
     * get the vendor name of this kind of database
     * 
     * @return
     */
    public String getVendorName();

    /**
     * get all available plan types, in string mode.
     * 
     * @return
     */
    public String[] getPlanTypes();

    /**
     * if current type id is graphic type
     * 
     * @param type
     * @return
     */
    public boolean isGraphicPlan(int type);

    /**
     * get the default type
     * 
     * @return
     */
    public int getDefaultOption();

    /**
     * get name by id
     * 
     * @param type
     * @return
     */
    public String getOptionName(int type);

    /**
     * get id by name
     * 
     * @param name
     * @return
     */
    public int getOptionId(String name);

    /**
     * if xml document is used to store the graphic plan
     * 
     * @return
     */
    public boolean isGraphicXMLType();

    /**
     * Get the current option type configured by the user
     * 
     * @return
     */
    public int getCurrentType();
}
