/*******************************************************************************
 * Copyright © 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sqlbuilder.views.graph;

import java.util.Iterator;
import java.util.List;

import org.eclipse.datatools.modelbase.sql.query.QueryDeleteStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryInsertStatement;
import org.eclipse.datatools.modelbase.sql.query.QuerySelect;
import org.eclipse.datatools.modelbase.sql.query.QuerySelectStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryUpdateStatement;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryObject;
import org.eclipse.datatools.modelbase.sql.query.TableExpression;
import org.eclipse.datatools.modelbase.sql.query.TableJoined;
import org.eclipse.datatools.modelbase.sql.query.helper.JoinHelper;
import org.eclipse.datatools.modelbase.sql.query.helper.StatementHelper;
import org.eclipse.datatools.modelbase.sql.query.impl.QuerySelectImpl;
import org.eclipse.datatools.modelbase.sql.query.impl.QueryStatementImpl;
import org.eclipse.datatools.sqltools.sqlbuilder.Messages;
import org.eclipse.datatools.sqltools.sqlbuilder.SQLBuilder;
import org.eclipse.datatools.sqltools.sqlbuilder.model.DeleteHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.model.InsertHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLDomainModel;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SelectHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.model.UpdateHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.views.graph.editparts.ColumnEditPart;
import org.eclipse.datatools.sqltools.sqlbuilder.views.graph.editparts.SQLRootEditPart;
import org.eclipse.datatools.sqltools.sqlbuilder.views.graph.editparts.TableEditPart;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.FocusBorder;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.EditDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.editparts.AbstractEditPart;
import org.eclipse.gef.tools.SelectionTool;
import org.eclipse.gef.ui.parts.GraphicalViewerKeyHandler;
import org.eclipse.gef.ui.parts.ScrollingGraphicalViewer;
import org.eclipse.jface.viewers.ContentViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.MouseTrackAdapter;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.views.navigator.LocalSelectionTransfer;

public class GraphControl extends ContentViewer {

    protected SQLDomainModel domainModel;
    protected EditDomain editDomain;
    protected Control control;
    protected ScrollingGraphicalViewer graphView;
    private SQLBuilder sqlBuilder;

    public GraphControl(SQLDomainModel domainModel) {
        this.domainModel = domainModel;
        graphView = new ScrollingGraphicalViewer();
        GraphicalViewerKeyHandler graphKeyHandler = new GraphicalViewerKeyHandler(graphView);
        graphView.setKeyHandler(graphKeyHandler);

        editDomain = new DefaultEditDomain(null);
        ((DefaultEditDomain) editDomain).setDefaultTool(new SelectionTool());
        editDomain.loadDefaultTool();
        editDomain.addViewer(graphView);

        GraphContextMenuProvider menuHandler = new GraphContextMenuProvider(graphView, domainModel);
        graphView.setContextMenu(menuHandler);

        setContentProvider(domainModel.createContentProvider());
    }

    public Control getControl() {
        return labelComp;
    }

    // [RATLC01124214] bgp 11Aug2006 - new method
    /** 
     * Gets text to be displayed in the graph control when there are no 
     * tables yet in the query.  (The default text instructs the user to
     * add a table to the query.)
     * @return the user prompt text
     */
    protected String getTextForUserPrompt() {
        return Messages._UI_GRAPH_PROMPT;
    }

    // if there are no tables in the view give a prompt to tell the user what to do
    protected void giveUserPrompt() {
        List tableList = currentPart.getModelChildren();   // [RATLC01122262] bgp 04Aug2006
        if (tableList != null && tableList.size() != 0) {  // [RATLC01122262] bgp 04Aug2006
            if (label != null) {
                if (currentPart.getFigure().getChildren().contains(label)) {
                    currentPart.getFigure().remove(label);
                }
                label = null;

                if (currentStatement instanceof QueryStatementImpl || currentStatement instanceof QuerySelectImpl) {
                    //((SQLQueryStatementImpl) currentStatement).setImproperStatement(null);
                    domainModel.setImproperStatement(null);
                }

                if (sqlBuilder != null) {
                    sqlBuilder.updateProperStatement(true);
                    sqlBuilder.reloadFromModel();
                }
            }
        }
        else {
            if (label == null && control.isEnabled()) {
                String userPromptText = getTextForUserPrompt();       // [RATLC01124214] bgp 11Aug2006
                label = new org.eclipse.draw2d.Label(userPromptText); // [RATLC01124214] bgp 11Aug2006
                currentPart.getFigure().add(label);
                label.setSize(label.getTextBounds().getSize());
                label.setLocation(new Point(5, 5));
            }
        }
    }

    public void refresh() {
        Display.getCurrent().asyncExec(new Runnable() {

            public void run() {

                if (currentPart != null) {
                    giveUserPrompt();
                    //TODO this method call causes many unwanted refreshes.
                    // But seems to be needed to refresh properly in some cases
                    // Graphical creation and editing of joins are the two cases 
                    currentPart.update(null, null);
                }
            }
        });
    }

    /**
     * Updates this view and source view after a table was successfully droped
     * into this view
     */
    public void updateForDND() {
        if (label != null) {
            label.setText(""); //$NON-NLS-1$
        }
        currentPart.update(null, null);
        // refresh source viewer also, DND support  
        sqlBuilder.getSourceViewer().refreshSource();
        sqlBuilder.getSourceViewer().setTextDirty(true);
        //temporary disable the parser
        boolean changed = sqlBuilder.getSourceViewer().isTextChanged();
        sqlBuilder.getSourceViewer().setTextChanged(false);
        this.getControl().setFocus();   
        sqlBuilder.getSourceViewer().setTextChanged(changed);
    }

    Label label = null;

    public void setSelection(ISelection selection, boolean reveal) {
    }

    public ISelection getSelection() {
        return null;
    }

    Composite labelComp;

    public void createControl(Composite comp) {
        labelComp = new Composite(comp, SWT.NONE);
        GridLayout compLayout = new GridLayout();
        compLayout.marginHeight = 0;
        compLayout.marginWidth = 0;
        labelComp.setLayout(compLayout);

        GridData data = new GridData();
        data.verticalAlignment = GridData.FILL;
        data.horizontalAlignment = GridData.FILL;
        data.grabExcessHorizontalSpace = true;
        data.grabExcessVerticalSpace = true;
        labelComp.setLayoutData(data);

        control = graphView.createControl(labelComp);
        //GridData canvasData = new GridData();
        data = new GridData();
        data.verticalAlignment = GridData.FILL;
        data.horizontalAlignment = GridData.FILL;
        data.grabExcessHorizontalSpace = true;
        data.grabExcessVerticalSpace = true;
        control.setLayoutData(data);

        //
        // Add drag and drop support
        //
        int operations = DND.DROP_MOVE | DND.DROP_COPY | DND.DROP_LINK;
        Transfer[] types = new Transfer[] { LocalSelectionTransfer.getInstance() };
        DropTarget target = new DropTarget(control, operations);
        target.setTransfer(types);

        target.addDropListener(new org.eclipse.datatools.sqltools.sqlbuilder.views.RDBTableDropListener(this, domainModel));

        new HoverTrackAdapter(control);
        control.addKeyListener(new KeyAdapter() {

            public void keyPressed(KeyEvent ke) {
                if (ke.character == 0x7F) // delete key = 0x7F   backspace key = 0x8
                {
                    QueryStatement statement = domainModel.getSQLStatement();

                    // get the selected parts
                    List selectedEditParts = graphView.getSelectedEditParts();

                    EditPart part = null;
                    Iterator iter = null;
                    if (selectedEditParts != null && !selectedEditParts.isEmpty()) {
                        part = (EditPart) selectedEditParts.get(0);
                    }
                    if (part instanceof TableEditPart) {
                        if (statement instanceof QuerySelectStatement ||
                                statement instanceof QuerySelect ) {
                            //QuerySelectStatement selectStmt = (QuerySelectStatement) statement;
                            TableExpression table;
                            // there could be multiple tables selected
                            iter = selectedEditParts.iterator();
                            while (iter.hasNext()) {
                                table = (TableExpression) ((TableEditPart) iter.next()).getModel();
                                TableJoined joinedTable = table.getTableJoinedLeft();
                                if (joinedTable == null) {
                                    joinedTable = table.getTableJoinedRight();
                                }
                                if(joinedTable != null){
                                    List fromClauseList;
                	                if(statement instanceof QuerySelect){
                	                    fromClauseList = ((QuerySelect)statement).getFromClause();
                	                    JoinHelper.removeJoinsForTable(fromClauseList,table);
                	                }
                	                else if(statement instanceof QuerySelectStatement){
                	                    QuerySelect querySelect = SelectHelper.getQuerySelect((QuerySelectStatement)statement);
                	                    fromClauseList = querySelect.getFromClause();
                	                    JoinHelper.removeJoinsForTable(fromClauseList,table);
                	                }
                                }
                                    StatementHelper.removeTableExpressionFromQueryStatement(table, statement);
                            }
        	                SelectHelper.refresh(statement);
        	                //multi table delete has refresh issues. so force a refresh
        	                refresh();
                        }
                        else if (statement instanceof QueryInsertStatement) {
                            String name = statement.getName();
                            String stmtlabel = statement.getLabel();
                            InsertHelper.clearStatementContents((QueryInsertStatement) statement);
                            statement.setName(name);
                            statement.setLabel(stmtlabel);
                        }
                        else if (statement instanceof QueryUpdateStatement) {
                            String name = statement.getName();
                            String stmtlabel = statement.getLabel();
                            UpdateHelper.clearStatementContents((QueryUpdateStatement) statement);
                            statement.setName(name);
                            statement.setLabel(stmtlabel);
                        }
                        else if (statement instanceof QueryDeleteStatement) {
                            String name = statement.getName();
                            String stmtlabel = statement.getLabel();
                            DeleteHelper.clearStatementContents((QueryDeleteStatement) statement);
                            statement.setName(name);
                            statement.setLabel(stmtlabel);
                        }
                    }
                }
            }
            
        });

        control.addFocusListener(new FocusListener() {

            /**
             * @see org.eclipse.swt.events.FocusListener#focusGained(FocusEvent)
             */
            public void focusGained(org.eclipse.swt.events.FocusEvent e) {
                // add border around the graph view
                IFigure fig = currentPart.getFigure();
                fig.setBorder(new InFocusBorder());
                fig.repaint();
            }

            /**
             * @see org.eclipse.swt.events.FocusListener#focusLost(FocusEvent)
             */
            public void focusLost(org.eclipse.swt.events.FocusEvent e) {
                // remove border around the view
                IFigure fig = currentPart.getFigure();
                fig.setBorder(null);
                fig.repaint();
                // deselect any selected objects
                Iterator childList = currentPart.getChildren().iterator();
                while (childList.hasNext()) {
                    AbstractEditPart element = (AbstractEditPart) childList.next();
                    element.setSelected(EditPart.SELECTED_NONE);
                }
            }

            class InFocusBorder extends FocusBorder {

                public void paint(IFigure figure, Graphics graphics, Insets insets) {
                    tempRect.setBounds(getPaintRectangle(figure, insets));
                    Point origin = tempRect.getLocation();
                    tempRect.setLocation(origin.x + 1, origin.y + 1);
                    tempRect.width = tempRect.width - 3;
                    tempRect.height = tempRect.height - 3;
                    graphics.setXORMode(true);
                    graphics.setForegroundColor(ColorConstants.white);
                    graphics.setBackgroundColor(ColorConstants.blue);
                    graphics.drawFocus(tempRect);
                }
            }
        });

        hookControl(control);
    }

    protected void handleDispose(DisposeEvent event) {
        super.handleDispose(event);
        currentPart = null;
    }

    SQLRootEditPart currentPart = null;
    SQLQueryObject currentStatement = null;

    //QueryStatement currentStatement = null;
    public void setInput(Object o) {
        super.setInput(o);
        if (o instanceof QueryStatement || o instanceof QuerySelect) {
            if (currentPart != null
                    && ((currentPart.getModel() instanceof QueryStatement && o instanceof QuerySelect) || (currentPart.getModel() instanceof QuerySelect && o instanceof QueryStatement))) {
                currentPart = null;
            }
            if (currentPart == null) {
                currentPart = new SQLRootEditPart(domainModel);
                currentPart.setModel(o);
                graphView.setContents(currentPart);
                // need to do this setSelection on SQLRootEditPart for Linux so
                //   add table works right away
                graphView.setSelection(new StructuredSelection(currentPart));
                currentPart.getFigure().setBounds(new Rectangle(graphView.getControl().getBounds()));
                currentStatement = (SQLQueryObject) o;
                refresh();
                return;
            }

            if (currentStatement == o) {
                currentPart.getFigure().invalidate();
            }
            else {
                currentPart.setModel(o);
                currentStatement = (SQLQueryObject) o;
            }
            currentPart.refresh();
        }
    }

    // only show hover from this listener if the mouse button is
    // not down.  Hover for creating joins and reconnecting
    // joins is done in the column edit policy
    public class HoverTrackAdapter extends MouseTrackAdapter implements MouseMoveListener, MouseListener {

        protected ColumnEditPart currentColumnPart = null;

        protected Control adaptedControl;

        public HoverTrackAdapter(Control control) {
            adaptedControl = control;
            adaptedControl.addMouseListener(this);
            adaptedControl.addMouseTrackListener(this);
            adaptedControl.addMouseMoveListener(this);
        }

        public void mouseExit(MouseEvent e) {
            adaptedControl.setToolTipText(null);
            currentColumnPart = null;
        }

        public void mouseMove(MouseEvent e) {
            if (isMouseDown == false) {
                if (currentColumnPart == null || !currentColumnPart.getFigure().containsPoint(new Point(e.x, e.y))) {
                    EditPart part = graphView.findObjectAt(new Point(e.x, e.y));
                    if (part != currentColumnPart) {
                        adaptedControl.setToolTipText(null);
                        if (part instanceof ColumnEditPart) {
                            currentColumnPart = (ColumnEditPart) part;
                            String tooltip = currentColumnPart.getToolTip();
                            adaptedControl.setToolTipText(tooltip);
                        }
                        else {
                            currentColumnPart = null;
                        }
                    }
                }
            }
        }

        boolean isMouseDown = false;

        public void mouseDoubleClick(MouseEvent e) {
        }

        public void mouseUp(MouseEvent e) {
            isMouseDown = false;
        }

        public void mouseDown(MouseEvent e) {
            adaptedControl.setToolTipText(null);
            currentColumnPart = null;
            isMouseDown = true;
        }

    }

    public void setSQLBuilder(SQLBuilder sb) {
        sqlBuilder = sb;
    }

    public void setEnabled(boolean enable) {
        control.setEnabled(enable);
        //The label is replaced by a message dialog, which is shown from 
        //changeGraphControlEnableState() in SQLBuilderEditor
        if (enable) {
            currentPart.getFigure().setBackgroundColor(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
            refresh();
        }
        else {
            currentPart.getFigure().setBackgroundColor(control.getBackground());
            refresh();
        }
    }

    public boolean isEnabled() {
        if (currentPart == null) {
            return false;
        }
        return !currentPart.getFigure().getBackgroundColor().equals(control.getBackground());
    }
}