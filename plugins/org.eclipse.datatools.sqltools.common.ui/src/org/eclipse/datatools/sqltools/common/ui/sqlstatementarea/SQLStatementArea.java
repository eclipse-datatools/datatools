/**
 * Created on Jun 10, 2006
 * 
 * Copyright (c) Sybase, Inc. 2004-2006. All rights reserved.
 */
package org.eclipse.datatools.sqltools.common.ui.sqlstatementarea;

import java.util.Iterator;

import org.eclipse.datatools.sqltools.common.ui.internal.Activator;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.source.AnnotationModel;
import org.eclipse.jface.text.source.AnnotationRulerColumn;
import org.eclipse.jface.text.source.CompositeRuler;
import org.eclipse.jface.text.source.IAnnotationAccess;
import org.eclipse.jface.text.source.IAnnotationModel;
import org.eclipse.jface.text.source.ISharedTextColors;
import org.eclipse.jface.text.source.IVerticalRuler;
import org.eclipse.jface.text.source.IVerticalRulerColumn;
import org.eclipse.jface.text.source.LineNumberRulerColumn;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontMetrics;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.internal.editors.text.EditorsPlugin;
import org.eclipse.ui.texteditor.AbstractDecoratedTextEditorPreferenceConstants;
import org.eclipse.ui.texteditor.AnnotationPreference;
import org.eclipse.ui.texteditor.DefaultMarkerAnnotationAccess;
import org.eclipse.ui.texteditor.MarkerAnnotationPreferences;
import org.eclipse.ui.texteditor.SourceViewerDecorationSupport;

/**
 * A customized SWT widget based on <code>org.eclipse.swt.widgets.Composite</code> is for displaying SQL Statement in
 * other SWT Control. It looks like an embedded SQL Editor with Syntax high lighting, vertical ruler for line number,
 * etc.
 * <p>
 * Following example code is a demo for using the <code>SQLStatementArea</code> in a dialog which displayes some
 * pieces of SQL Statements with syntax high lighting and simple context menu:
 * 
 * <pre>
 *     public class SQLDialog extends TitleAreaDialog
 *     {
 *         private SQLStatementArea sta;
 *           .......
 *         protected Control createDialogArea(Composite parent)
 *         {
 *           sta = new SQLStatementArea(composite, SWT.BORDER, new DMPSQLSourceViewerService(), true);
 *           sta.setInput(sqlCode,dbType);
 *           sta.setEditable(false);
 *           sta.setEnabled(false);
 *           sta.configureViewer(new SQLSourceViewerConfiguration(dbType));
 *           sta.setLayoutData(new GridData(GridData.FILL_BOTH));
 *           createContextMenu();
 *          }
 *          
 *         private void createContextMenu()
 *         {
 *             ...... 
 *           MenuManager menuMgr = new MenuManager();
 *           menuMgr.setRemoveAllWhenShown(true);
 *           menuMgr.addMenuListener(new IMenuListener()
 *           {
 *               public void menuAboutToShow(IMenuManager mgr)
 *               {
 *                   mgr.add(new Separator());
 *                   mgr.add(select_action);
 *               }
 *           }); 
 *           Menu menu = menuMgr.createContextMenu(sta.getViewer().getTextWidget());
 *           sta.getViewer().getTextWidget().setMenu(menu);
 *          }
 *     }
 * </pre>
 * 
 * 
 * 
 * @author Shi-feng Yu
 */
public class SQLStatementArea extends Composite
{

    private final static String     CURRENT_LINE         = AbstractDecoratedTextEditorPreferenceConstants.EDITOR_CURRENT_LINE;
    private final static String     CURRENT_LINE_COLOR   = AbstractDecoratedTextEditorPreferenceConstants.EDITOR_CURRENT_LINE_COLOR;
    private static final int        VERTICAL_RULER_WIDTH = 12;

    private SourceViewer            _viewer;
    private IDocument               _document;
    private ISQLSourceViewerService _service;
    private LineNumberRulerColumn   _LineNumberRulerColumn;
    private boolean                 _needLineNumber;
    private ISharedTextColors       _sharedTextColors    = null;
    private FontMetrics             _fontMetrics;
    private Composite               _container           = null;
    private String                  _currentDbType       = null;

    /**
     * Instantiates a new SQLStatementArea.
     * <p>
     * 
     * @param parent a composite control which will be the parent of the new instance (cannot be null)
     * @param style the style of control to construct
     * @param service a helper class implemented by user
     * @param lineNum whether to display line number in vertial ruler
     */
    public SQLStatementArea(Composite parent, int style, ISQLSourceViewerService service, boolean lineNum)
    {
        super(parent, style);
        _needLineNumber = lineNum;
        _service = service;
        initialize();
    }

    /**
     * Sets the input for the <code>SQLStatementArea</code>. There could be two kinds of input:
     * <li> java.lang.String which contains the SQL Statements. If user passes in a String, we need to create a Document
     * for it.
     * <li> org.eclipse.jface.text.IDocument which contains the SQL Statements.
     * 
     * @param source The SQL Statement in String.
     * @param dbType The Database type for the SQL Statement.
     * 
     */
    public void setInput(String source, String dbType)
    {
        if (_currentDbType == null)
        {
            _currentDbType = dbType;
            _document = new Document(source);
            _service.setUpDocument(_document, dbType);
        }
        else
        {
            if (_currentDbType.equals(dbType))
            {
                if (_document == null)
                {
                    _document = new Document(source);
                }
                else
                {
                    _document.set(source);
                }
            }
            else
            {
                if (_document == null)
                {
                    _document = new Document(source);
                    this._service.setUpDocument(_document, dbType);
                }
                else
                {
                    _document.set(source);
                }
            }

        }
        this._viewer.refresh();
    }

    /**
     * Sets the editable state
     * 
     * @param editable
     */
    public void setEditable(boolean editable)
    {
        checkWidget();
        _viewer.setEditable(editable);
    }

    /**
     * Configures the source viewer using the given configuration.
     * 
     * @param configuration the source viewer configuration to be used
     */
    public void configureViewer(SourceViewerConfiguration configuration)
    {
        _viewer.configure(configuration);
        configureViewer();
    }

    private void initialize()
    {
        initializeUnits(this);
        _sharedTextColors = Activator.getDefault().getSharedTextColors();
        GridLayout gl = new GridLayout();
        gl.marginWidth = 0;
        gl.marginHeight = 0;
        this.setLayout(gl);
        createComposite();
        _viewer = new SourceViewer(_container, createVerticalRuler(), SWT.V_SCROLL | SWT.H_SCROLL);

        Font font = JFaceResources.getTextFont();
        _viewer.getTextWidget().setFont(font);
    }

    private void createComposite()
    {
        GridData gridData = new org.eclipse.swt.layout.GridData();
        gridData.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
        gridData.grabExcessHorizontalSpace = true;
        gridData.grabExcessVerticalSpace = true;
        gridData.verticalAlignment = org.eclipse.swt.layout.GridData.FILL;
        _container = new Composite(this, SWT.NONE);
        GridLayout gl = new GridLayout();
        gl.marginWidth = 0;
        gl.marginHeight = 0;        
        _container.setLayout(gl);
        _container.setLayoutData(gridData);
    }

    private void configureSourceViewerDecorationSupport(SourceViewerDecorationSupport support)
    {
        MarkerAnnotationPreferences _AnnotationPreferences = new MarkerAnnotationPreferences();

        Iterator e = _AnnotationPreferences.getAnnotationPreferences().iterator();
        while (e.hasNext())
        {
            support.setAnnotationPreference((AnnotationPreference) e.next());
        }
        support.setCursorLinePainterPreferenceKeys(CURRENT_LINE, CURRENT_LINE_COLOR);
        support.setSymbolicFontName(JFaceResources.TEXT_FONT);
    }

    /**
     * Configures the viewer internally.
     * <li>set document with annation mode
     * <li>configure sourceViewer decoration support
     * <li>compute the viewer's size and set layout
     * <p>
     * Note: this customized SWT composite:<code>SQLStatementArea</code> is outside of the Eclipse edit framework.
     * However, we still use the same preference store which is used in Eclipse edit framework.
     */
    private void configureViewer()
    {
        IAnnotationModel am = new AnnotationModel();
        _viewer.setDocument(_document, am);
        _viewer.showAnnotations(true);

        IAnnotationAccess annotationAcc = new DefaultMarkerAnnotationAccess();
        SourceViewerDecorationSupport svds = new SourceViewerDecorationSupport(_viewer, null, annotationAcc,
            _sharedTextColors);
        configureSourceViewerDecorationSupport(svds);
        svds.install(EditorsPlugin.getDefault().getPreferenceStore());
        Control control = _viewer.getControl();
        GridData data = new GridData(GridData.FILL_BOTH);
        data.heightHint = Dialog.convertHeightInCharsToPixels(_fontMetrics, 15);
        control.setLayoutData(data);
    }

    /**
     * Initializes the computation of horizontal and vertical units based on the size of current font.
     * <p>
     * This method must be called before any of the unit based conversion methods are called.
     * </p>
     * 
     * @param testControl a control from which to obtain the current font
     */
    private void initializeUnits(Control testControl)
    {
        GC gc = new GC(testControl);
        gc.setFont(JFaceResources.getTextFont());
        _fontMetrics = gc.getFontMetrics();
        gc.dispose();
    }

    private IVerticalRuler createVerticalRuler()
    {
        CompositeRuler ruler = new CompositeRuler();
        ruler.addDecorator(0, new AnnotationRulerColumn(VERTICAL_RULER_WIDTH, new DefaultMarkerAnnotationAccess()));
        if (_needLineNumber)
        {
            ruler.addDecorator(1, createLineNumberRulerColumn());
        }
        return ruler;
    }

    private IVerticalRulerColumn createLineNumberRulerColumn()
    {
        _LineNumberRulerColumn = new LineNumberRulerColumn();
        initializeLineNumberRulerColumn(_LineNumberRulerColumn);
        return _LineNumberRulerColumn;
    }

    private void initializeLineNumberRulerColumn(LineNumberRulerColumn rulerColumn)
    {
        rulerColumn.setForeground(_sharedTextColors.getColor(new RGB(133, 133, 133)));
        rulerColumn.setBackground(null);
        rulerColumn.redraw();
    }

    /**
     * Gets the Viewer which is an instance of <code>SourceViewer</code>.
     * 
     * @return
     */
    public SourceViewer getViewer()
    {
        return _viewer;
    }

    /**
     * Gets the SQL Statements String which is displayed in this SQLStatementArea.
     * 
     * @return
     */
    public String getContentInString()
    {
        return _document.get();
    }
}
