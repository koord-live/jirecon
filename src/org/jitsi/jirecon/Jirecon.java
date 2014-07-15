/*
 * Jirecon, the Jitsi recorder container.
 * 
 * Distributable under LGPL license. See terms of license at gnu.org.
 */
package org.jitsi.jirecon;

import net.java.sip.communicator.service.protocol.OperationFailedException;


/**
 * Jirecon, the Jitsi recorder container, is responsible for recording specified
 * Jitsi-meeting. It can hold several recording job at a time.
 * 
 * @author lishunyang
 * 
 */
public interface Jirecon
{
    /**
     * Initialize <tt>Jirecon</tt>.
     * <p>
     * Once this method has been executed successfully, <tt>Jirecon</tt> should
     * be ready to start working.
     * 
     * @param configurationPath is the configuration file path.
     * @throws OperationFailedException if failed to initialize Jirecon.
     */
    public void init(String configurationPath) 
        throws OperationFailedException;

    /**
     * Uninitialize <tt>Jirecon</tt>, prepare for GC.
     * <p>
     * <strong>Warning:</tt> If there is any residue <tt>JireconTask</tt>,
     * </tt>Jirecon</tt> will stop them and notify <tt>JireconEventListener</tt>
     * s.
     */
    public void uninit();

    /**
     * Create a new recording task for a specified Jitsi-meeting.
     * <p>
     * <strong>Warning:</strong> This method is asynchronous, it will return
     * immediately while it doesn't mean the task has been started successfully.
     * If the task failed, it will notify event listeners.
     * 
     * @param mucJid indicates which Jitsi-meeting you want to record.
     * @return true if the task has been started successfully, otherwise false.
     *         Notice that the task may fail during the execution.
     */
    public boolean startJireconTask(String mucJid);

    /**
     * Stop a recording task for a specified Jitsi-meeting.
     * 
     * @param mucJid indicates which Jitsi-meeting you want to record.
     * @param keepData Whether keeping the data. Keep the output files if it is
     *            true, otherwise remove them.
     * @return true if the task has been stopped successfully, otherwise false,
     *         such as task is not found.
     */
    public boolean stopJireconTask(String mucJid, boolean keepData);

    /**
     * Register an event listener, if some important things happen, they'll be
     * notified.
     * 
     * @param listener is the event listener you want to register.
     */
    public void addEventListener(JireconEventListener listener);

    /**
     * Remove an event listener.
     * 
     * @param listener is the event listener you want to remove.
     */
    public void removeEventListener(JireconEventListener listener);
}
