package org.eclipse.datatools.connectivity.sqm.server.internal.ui.dialogs;

import java.util.Arrays;
import java.util.Iterator;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.datatools.connectivity.sqm.server.internal.ui.util.resources.ResourceLoader;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IconAndMessageDialog;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;

/**
 * @author ljulien
 */
public class SQLExceptionDialog extends IconAndMessageDialog
{
    private static final ResourceLoader resourceLoader = ResourceLoader.INSTANCE;

    private static final String HEADER_TITLE = resourceLoader.queryString("DATATOOLS.SERVER.UI.CONNECTION.SQLEXCEPTION.TITLE"); //$NON-NLS-1$
    private static final String RECONNECT = resourceLoader.queryString("DATATOOLS.SERVER.UI.CONNECTION.SQLEXCEPTION.RECONNECT"); //$NON-NLS-1$
    private static final String DISCONNECT = resourceLoader.queryString("DATATOOLS.SERVER.UI.CONNECTION.SQLEXCEPTION.DISCONNECT"); //$NON-NLS-1$
    private static final String IGNORE = resourceLoader.queryString("DATATOOLS.SERVER.UI.CONNECTION.SQLEXCEPTION.IGNORE"); //$NON-NLS-1$
    private static final String MESSAGE = resourceLoader.queryString("DATATOOLS.SERVER.UI.CONNECTION.SQLEXCEPTION.MESSAGE"); //$NON-NLS-1$

    public static final int RECONNECT_ID = 1;
    public static final int DISCONNECT_ID = 2;
    public static final int IGNORE_ID = 3;
    private static boolean AUTOMATED_MODE = false;
    private static final int LIST_ITEM_COUNT = 7;
    private static final String NESTING_INDENT = "  "; //$NON-NLS-1$

    private int returnCode;
    private String title;
    private List list;
    private boolean listCreated = false;
    private int displayMask = 0xFFFF;
    private java.util.List statusList;

    private Button detailsButton;
    private IStatus status;
    private Clipboard clipboard;

    private void populateList(List listToPopulate)
    {
        Iterator it = statusList.iterator();
        while (it.hasNext())
        {
            IStatus childStatus = (IStatus) it.next();
            populateList(listToPopulate, childStatus, 0);
        }
    }

    private void populateList(List listToPopulate, IStatus buildingStatus, int nesting)
    {
        if (!buildingStatus.matches(displayMask))
        {
            return;
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < nesting; i++)
        {
            sb.append(NESTING_INDENT); //$NON-NLS-1$
        }
        sb.append(buildingStatus.getMessage());
        listToPopulate.add(sb.toString());
        IStatus[] children = buildingStatus.getChildren();
        for (int i = 0; i < children.length; i++)
        {
            populateList(listToPopulate, children[i], nesting + 1);
        }
    }

    private void toggleDetailsArea()
    {
        Point windowSize = getShell().getSize();
        Point oldSize = getShell().computeSize(SWT.DEFAULT, SWT.DEFAULT);
        if (listCreated)
        {
            list.dispose();
            listCreated = false;
            detailsButton.setText(IDialogConstants.SHOW_DETAILS_LABEL);
        }
        else
        {
            list = createDropDownList((Composite) getContents());
            detailsButton.setText(IDialogConstants.HIDE_DETAILS_LABEL);
        }
        Point newSize = getShell().computeSize(SWT.DEFAULT, SWT.DEFAULT);
        getShell().setSize(new Point(windowSize.x, windowSize.y + (newSize.y - oldSize.y)));
    }

    private void populateCopyBuffer(IStatus buildingStatus, StringBuffer buffer, int nesting)
    {
        if (!buildingStatus.matches(displayMask))
        {
            return;
        }
        for (int i = 0; i < nesting; i++)
        {
            buffer.append(NESTING_INDENT); //$NON-NLS-1$
        }
        buffer.append(buildingStatus.getMessage());
        buffer.append("\n"); //$NON-NLS-1$
        IStatus[] children = buildingStatus.getChildren();
        for (int i = 0; i < children.length; i++)
        {
            populateCopyBuffer(children[i], buffer, nesting + 1);
        }
    }

    private void copyToClipboard()
    {
        if (clipboard != null)
            clipboard.dispose();
        StringBuffer statusBuffer = new StringBuffer();
        populateCopyBuffer(status, statusBuffer, 0);
        clipboard = new Clipboard(list.getDisplay());
        clipboard.setContents(new Object[] { statusBuffer.toString() }, new Transfer[] { TextTransfer.getInstance() });
    }
    protected void configureShell(Shell shell)
    {
        super.configureShell(shell);
        shell.setText(title);
    }

    protected Control createDialogArea(Composite parent)
    {
        createMessageArea(parent);
        // create a composite with standard margins and spacing
        Composite composite = new Composite(parent, SWT.NONE);
        GridLayout layout = new GridLayout();
        layout.marginHeight = convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_MARGIN);
        layout.marginWidth = convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_MARGIN);
        layout.verticalSpacing = convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_SPACING);
        layout.horizontalSpacing = convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_SPACING);
        layout.numColumns = 2;
        composite.setLayout(layout);
        GridData childData = new GridData(GridData.FILL_BOTH);
        childData.horizontalSpan = 2;
        composite.setLayoutData(childData);
        composite.setFont(parent.getFont());
        return composite;
    }

    protected void createDialogAndButtonArea(Composite parent)
    {
        super.createDialogAndButtonArea(parent);
        if (this.dialogArea instanceof Composite)
        {
            Composite dialogComposite = (Composite) dialogArea;
            if (dialogComposite.getChildren().length == 0)
                new Label(dialogComposite, SWT.NULL);
        }
    }

    protected Image getImage()
    {
        if (status != null)
        {
            if (status.getSeverity() == IStatus.WARNING)
                return getWarningImage();
            if (status.getSeverity() == IStatus.INFO)
                return getInfoImage();
        }
        return getErrorImage();
    }

    protected List createDropDownList(Composite parent)
    {
        list = new List(parent, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.MULTI);

        populateList(list);
        GridData data = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL
                | GridData.VERTICAL_ALIGN_FILL | GridData.GRAB_VERTICAL);
        data.heightHint = list.getItemHeight() * LIST_ITEM_COUNT;
        data.horizontalSpan = 2;
        list.setLayoutData(data);
        list.setFont(parent.getFont());
        Menu copyMenu = new Menu(list);
        MenuItem copyItem = new MenuItem(copyMenu, SWT.NONE);
        copyItem.addSelectionListener(new SelectionListener()
        {
            public void widgetSelected(SelectionEvent e)
            {
                copyToClipboard();
            }

            public void widgetDefaultSelected(SelectionEvent e)
            {
                copyToClipboard();
            }
        });
        copyItem.setText(JFaceResources.getString("copy")); //$NON-NLS-1$
        list.setMenu(copyMenu);
        listCreated = true;
        return list;
    }

    
    protected static boolean shouldDisplay(IStatus status, int mask)
    {
        IStatus[] children = status.getChildren();
        if (children == null || children.length == 0)
        {
            return status.matches(mask);
        }
        for (int i = 0; i < children.length; i++)
        {
            if (children[i].matches(mask))
                return true;
        }
        return false;
    }

    protected void createButtonsForButtonBar(Composite parent)
    {
        createButton(parent, RECONNECT_ID, RECONNECT, true);
        createButton(parent, DISCONNECT_ID, DISCONNECT, false);
        createButton(parent, IGNORE_ID, IGNORE, false);
        this.detailsButton = createButton(parent, IDialogConstants.DETAILS_ID, IDialogConstants.SHOW_DETAILS_LABEL,
                false);
    }

    protected void buttonPressed(int buttonId)
    {
        this.returnCode = buttonId;
        if (buttonId == IDialogConstants.DETAILS_ID)
        {
            toggleDetailsArea();
        }
        else
        {
            close();
        }
    }

    public SQLExceptionDialog(IStatus status)
    {
        this(Display.getCurrent().getActiveShell(), HEADER_TITLE, MESSAGE, status, IStatus.ERROR);
    }

    public SQLExceptionDialog(Shell parentShell, String dialogTitle, String message, IStatus status, int displayMask)
    {
        super(parentShell);
        this.title = dialogTitle == null ? JFaceResources.getString("Problem_Occurred") : //$NON-NLS-1$
                dialogTitle;
        this.message = message == null ? status.getMessage() : JFaceResources.format(
                "Reason", new Object[] { message, status.getMessage() }); //$NON-NLS-1$
        this.status = status;
        statusList = Arrays.asList(status.getChildren());
        this.displayMask = displayMask;
        setShellStyle(SWT.DIALOG_TRIM | SWT.RESIZE | SWT.APPLICATION_MODAL);
    }

    public int getReturnCode()
    {
        return returnCode;
    }

    public int open()
    {
        if (!AUTOMATED_MODE && shouldDisplay(status, displayMask))
        {
            return super.open();
        }
        setReturnCode(returnCode);
        return returnCode;
    }

    public boolean close()
    {
        if (clipboard != null)
        {
            clipboard.dispose();
        }
        return super.close();
    }
}