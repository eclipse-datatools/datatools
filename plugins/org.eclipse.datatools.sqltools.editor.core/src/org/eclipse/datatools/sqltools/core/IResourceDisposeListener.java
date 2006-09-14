package org.eclipse.datatools.sqltools.core;

/**
 * 
 * @author Idull
 */
public interface IResourceDisposeListener
{
    /**
     * Will be invoked when the given profile is closing
     * @param profileName
     */
    public void dispose();
}
