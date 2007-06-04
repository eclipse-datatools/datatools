package org.eclipse.datatools.sqltools.debugger.core;

import java.sql.SQLException;

import org.eclipse.datatools.sqltools.core.ConnectionException;
import org.eclipse.datatools.sqltools.core.IControlConnection;
import org.eclipse.datatools.sqltools.core.ProcIdentifier;


/**
 * To handle all debug related issues. For example, it is responsible for notifying the <code>IThread</code> that some
 * connection events occur.
 * Also, it is responsible for updating the client connections for the External Clients View.
 * Will hold one instance of this class for each profile.
 * @author Idull
 */
public interface IDebugHandler
{
    /**
     * Initialize this debug handler, should be called soon after the instance is constructed
     */
    public void init();

    /**
     * Adds a connection observer, which will be notified when connection events occur for the corresponding connection
     * @param connectionId the id of the connection
     * @param connectionObserver when the connection event occurs on the given connectionId, this observer will be notified
     */
    public void addConnectionObserver(int connectionId, IConnectionObserver connectionObserver);

    /**
     * Removes the connection observer for the given connection id
     * @param connectionId the given connection id
     */
    public void removeConnectionObserver(int connectionId);

    /**
     * Returns the client connections info. of this debug handler (In fact, this client connections info. are identical
     * for the same server)
     * 
     * @return
     */
    public ClientConInfo[] getClientConInfos();

    /**
     * Refreshes the client connections immediately
     *
     */
    public void refreshExternalClients();

    /**
     * Disposes the related resources
     *
     */
    public void dispose();

    /**
     * Checks if can debug now
     * @return
     */
    public String readyToDebug();

    /**
     * Tests whether the specified connection is attached by debugger
     * 
     * @param connid
     * @return
     */
    public boolean isAttached(int connid);

    /**
     * Requests to stop debugging on the specified connection
     * @param connId
     */
    public void requestDetach(int connId);

    /**
     * Checks whether there is registered debuggee.
     * @return
     */
    public boolean hasDebuggee();

    /**
     * Checks if this instance is external clients view's content provider or not
     * @return
     */
    public boolean isClientConnectionProvider();

    /**
     * Returns the profile name
     * @return
     */
    public String getProfileName();

    /**
     * As the connection id used inside IControlConnection may be different from those external ids. So sometimes when
     * people have external id and exteranl name, they need to convert to internal id first.
     * For vendors that don't care about it, just return the externalId is fine.
     * 
     * @param externalId external connection id
     * @param exteranlName optional connection name
     * @throws ConnectionException 
     */
    public int convertToInternalConnId(String externalId, String exteranlName) throws ConnectionException ;

    /**
     * Given the ProcIdentifier and the line numbers, find out the valid line numbers that is 
     * greater than number that can set breakpoint.
     * 
     * if for some reason, this operation is not supported, the original number will be returned.
     * @param proc
     * @param lineNumbers
     * @return if the returning line number is -1, means can't find a valid location.
     */
    public int[] getValidBreakpointLocations(ProcIdentifier proc, int[] lineNumbers,
        IControlConnection controlConnection) throws SQLException;

    /**
     * Returns the number of debug process which is currently active.
     * @return
     */
    public int getNumOfDebugProcess();
    
    /**
     * Set the profile name
     */
    public void setProfileName(String profileName);
}
