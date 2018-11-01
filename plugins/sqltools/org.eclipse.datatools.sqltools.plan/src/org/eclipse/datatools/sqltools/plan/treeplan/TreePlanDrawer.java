/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.plan.treeplan;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.datatools.sqltools.plan.AbstractPlanDrawer;
import org.eclipse.datatools.sqltools.plan.IExecutionPlanDocument;
import org.eclipse.datatools.sqltools.plan.internal.PlanViewPlugin;
import org.eclipse.datatools.sqltools.plan.internal.util.Images;
import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseListener;
import org.eclipse.draw2d.MouseMotionListener;
import org.eclipse.draw2d.Panel;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.ScrollPane;
import org.eclipse.draw2d.SimpleLoweredBorder;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.text.FlowPage;
import org.eclipse.draw2d.text.TextFlow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Canvas;

/**
 * This is a tree-structure graphic plan drawer which implements <code>drawPlan(IExecutionPlanDocument)</code>
 * methods. We will convert the <code>IExecutionPlanDocument</code> to our own model before drawing the plan
 * 
 * @author Dafan Yang
 * 
 */
public class TreePlanDrawer extends AbstractPlanDrawer
{
    private IExecutionPlanDocument _planDoc;
    private static final int       WIDTH            = 100;
    private static final int       HEIGHT           = 35;
    private static final int       BIG_HEIGHT       = 50;
    private static final int       HORIZONTAL_SPACE = 15;
    private static final int       VERTICAL_SPACE   = 25;

    private ArrayList              _nodePainted     = new ArrayList();
    private HashMap                _node2Plan       = new HashMap();

    private int                    _maxNodes        = 1;
    private IFigure                _last;
    private int                    _maxWidth;
    private int                    _maxHeight;

    private String                 _htmlStart       = "<html><body>";
    private String                 _htmlEnd         = "</body></html>";
    private static final String    HTML_FILE_NAME   = "query_plan.html";
    private String                 _canvasTip       = "";
    private Font                   _objFont         = new Font(null, "Arial", 10, SWT.BOLD);
    
    public TreePlanDrawer()
    {
        super();
    }
    
    public TreePlanDrawer(Canvas canvas, Browser browser)
    {
        super(canvas, browser);
    }

    public void init()
    {
        _lws = new LightweightSystem(_canvas);
        _canvas.addDisposeListener(new DisposeListener()
        {
            public void widgetDisposed(DisposeEvent e)
            {
                if(_objFont != null && !_objFont.isDisposed())
                {
                    _objFont.dispose();
                }
            }
        });
    }

    /**
     * Creates node figure
     * 
     * @param plan the given tree node
     * @param levelStart 
     * @param y
     * @param level
     * @return a figure
     */
    private Figure createNodeFigure(TreePlanNodeComponent plan, int levelStart, int y, int level)
    {
        //Keep track of how many figures are created
        if (_nodePainted.size() <= level)
        {
            _nodePainted.add(new Integer(1));
        }
        else
        {
            _nodePainted.set(level, new Integer(((Integer) _nodePainted.get(level)).intValue() + 1));
        }
        int x = (((Integer) _nodePainted.get(level)).intValue() - 1) * (WIDTH + HORIZONTAL_SPACE) + levelStart;
        
        Figure node1 = new CompartmentFigure(plan.getName(), plan.getLabel1(), plan.isLabel1Highlighted(), plan
                .getLabel2(), plan.isLabel2Highlighted());
        FlowPage page = new FlowPage();
        page.setBorder(new MarginBorder(5, 5, 5, 5));
        String tooltip = plan.getToolTip();
        
        page.add(new TextFlow(tooltip));
        node1.setToolTip(page);
        
        //Suppress the canvas' tooltip on the node
        node1.addMouseMotionListener(new MouseMotionListener()
        {
            public void mouseDragged(MouseEvent me)
            {

            }

            public void mouseEntered(MouseEvent me)
            {
                _canvas.setToolTipText("");
            }

            public void mouseExited(MouseEvent me)
            {
                _canvas.setToolTipText(_canvasTip);
            }

            public void mouseHover(MouseEvent me)
            {

            }

            public void mouseMoved(MouseEvent me)
            {

            }
        });
        _node2Plan.put(node1, plan);
        node1.setLocation(new Point(x, y));
        if (node1.getChildren().size() == 2)
        {
            node1.setSize(WIDTH, HEIGHT);
        }
        else
        {
            node1.setSize(WIDTH, BIG_HEIGHT);
        }
        node1.setEnabled(true);
        new Dragger(node1);

        return node1;
    }

    /**
     * 
     * @param planDoc
     * @param parentNode
     * @param parentFigure
     * @param panel
     * @param y
     * @param level
     */
    private void paintChildNodes(TreeExecutionPlanDocument planDoc, TreePlanNodeComponent parentNode, IFigure parentFigure,
        IFigure panel, int y, int level)
    {
        int count = planDoc.getWidth(level + 1);
        int width = count * WIDTH + (count - 1) * HORIZONTAL_SPACE;
        int nextHeight = y + HEIGHT + VERTICAL_SPACE;
        _maxHeight = _maxHeight >= nextHeight ? _maxHeight : nextHeight;
        for (int i = 0; i < parentNode.getChildrenCount(); i++)
        {
            TreePlanNodeComponent plan = (TreePlanNodeComponent) parentNode.getChild(i);
            Figure node1 = createNodeFigure(plan, (_maxWidth - width) / 2, y, level + 1);

            PolylineConnection conn = new PolylineConnection();
            conn.setSourceAnchor(new ChopboxAnchor(parentFigure));
            conn.setTargetAnchor(new ChopboxAnchor(node1));
            conn.setTargetDecoration(new PolygonDecoration());

            panel.add(node1);
            panel.add(conn);
            paintChildNodes(planDoc, plan, node1, panel, nextHeight, level + 1);
        }
    }

    /**
     * Updates figure color and description
     * 
     * @param f the selected figure
     */
    private void selectFigure(IFigure f)
    {
        if (_node2Plan.get(f) != null && _last != f)
        {
            if (_last != null)
            {
                CompartmentFigure figure = (CompartmentFigure) _last;
                figure.getNameLabel().setIcon(null);
                figure.getNameLabel().setText(" " + figure.getNameLabel().getText());
                _last.setBackgroundColor(ColorConstants.menuBackground);
                _last.setForegroundColor(ColorConstants.black);
            }
            f.setBackgroundColor(ColorConstants.lightBlue);
            CompartmentFigure figure = (CompartmentFigure) f;
            figure.getNameLabel().setIcon(Images.get(Images.IMG_CURRENT_OPERATOR));
            figure.getNameLabel().setText(figure.getNameLabel().getText().trim());
            _last = f;
            String planFileStr = PlanViewPlugin.getDefault().getStateLocation().append(HTML_FILE_NAME).toOSString();
            File planFile = new File(planFileStr);
            String content = _htmlStart + ((TreePlanNodeComponent) _node2Plan.get(f)).getDetail() + _htmlEnd;
            if (planFile.exists())
            {
                planFile.delete();
            }
            try
            {
                planFile.createNewFile();
                FileOutputStream fos = new FileOutputStream(planFile);
                fos.write(content.getBytes());
                // Does this work in Unix system?
                _browser.setUrl("file:///" + planFileStr);
            }
            catch (Exception ex)
            {
                _browser.setText(content);
            }
        }
    }

    /**
     * Draws the execution plan document
     * 
     */
    private void draw()
    {
        _canvasTip = _canvas.getToolTipText();
        _node2Plan.clear();
        TreeExecutionPlanDocument treeModel = (TreeExecutionPlanDocument) _planDoc;
        _lws.getRootFigure().erase();
        ScrollPane scroll = new ScrollPane();
        IFigure panel = new Panel();
        panel.setBackgroundColor(ColorConstants.white);
        scroll.setContents(panel);
        scroll.setBorder(new SimpleLoweredBorder());
        _lws.setContents(scroll);

        _maxNodes = treeModel.getMaxWidth();

        _maxWidth = _maxNodes * WIDTH + (_maxNodes + 1) * HORIZONTAL_SPACE;
        _maxHeight = 0;
        _nodePainted = new ArrayList();
        Figure node1 = createNodeFigure(treeModel.getRootNode(), (_maxWidth - WIDTH) / 2, VERTICAL_SPACE, 0);
        selectFigure(node1);
        panel.add(node1);
        paintChildNodes(treeModel, treeModel.getRootNode(), node1, panel, HEIGHT + 2 * VERTICAL_SPACE, 0);

        panel.setSize(new Dimension(_maxWidth, _maxHeight - HEIGHT - VERTICAL_SPACE));
        scroll.revalidate();
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.datatools.sqltools.plan.AbstractPlanDrawer#drawPlan(org.eclipse.datatools.sqltools.plan.IExecutionPlanDocument)
     */
    public void drawPlan(IExecutionPlanDocument planDoc)
    {
        _planDoc = planDoc;
        draw();
    }

    class Dragger extends MouseMotionListener.Stub implements MouseListener
    {
        public Dragger(IFigure figure)
        {
            this._figure = figure;
            figure.addMouseMotionListener(this);
            figure.addMouseListener(this);
        }

        IFigure _figure;

        public void mouseReleased(MouseEvent e)
        {
        }

        public void mouseClicked(MouseEvent e)
        {
        }

        public void mouseDoubleClicked(MouseEvent e)
        {
        }

        public void mousePressed(MouseEvent e)
        {
            IFigure f = ((IFigure) e.getSource());
            selectFigure(f);
        }

        public void mouseDragged(MouseEvent e)
        {
            
        }

        public void dispose()
        {
            if (_figure != null)
            {
                _figure.removeMouseMotionListener(this);
                _figure.removeMouseListener(this);
            }
        }
    }

    class CompartmentFigure extends Figure
    {
        private Label   _nameLabel;
        private Label   _label1;
        private Label   _label2;

        public CompartmentFigure(String name, String label1, boolean isLabel1Highlighted, String label2, boolean isLabel2Highlighted)
        {
            ToolbarLayout layout = new ToolbarLayout();
            layout.setMinorAlignment(ToolbarLayout.ALIGN_TOPLEFT);
            layout.setStretchMinorAxis(false);
            layout.setSpacing(2);
            setBorder(new LineBorder(ColorConstants.black, 1));

            setLayoutManager(layout);
            setBackgroundColor(ColorConstants.menuBackground);
            setOpaque(true);
            _nameLabel = new Label(" " + name);
            add(_nameLabel);
            if(label1 != null && label1.trim().length() != 0)
            {
                _label1 = new Label(" " + label1);
                if(isLabel1Highlighted)
                {
                    _label1.setFont(_objFont);
                    _label1.setForegroundColor(ColorConstants.blue);
                }
                add(_label1);
            }
            if (label2 != null && label2.trim().length() != 0)
            {
                _label2 = new Label(" " + label2);
                if(isLabel2Highlighted)
                {
                    Font objFont = new Font(null, "Arial", 10, SWT.BOLD);
                    _label2.setFont(objFont);
                    _label2.setForegroundColor(ColorConstants.blue);
                }
                add(_label2);
            }
        }

        /**
         * Returns the name label
         * @return the name label
         */
        public Label getNameLabel()
        {
            return _nameLabel;
        }

        /**
         * Returns the first label
         * @return the first label
         */
        public Label getLabel1()
        {
            return _label1;
        }
        
        /**
         * Returns the second label
         * @return the second label
         */
        public Label getLabel2()
        {
            return _label2;
        }
    }

}

