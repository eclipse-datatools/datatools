/**
 * Created on Aug 26, 2008
 * 
 * Copyright (c) Sybase, Inc. 2004-2008. All rights reserved.
 */
package org.eclipse.datatools.sqltools.sqleditor;

import org.eclipse.datatools.sqltools.sqleditor.internal.matching.AbstractPairMatcher;
import org.eclipse.datatools.sqltools.sqleditor.internal.matching.GenericSQLMatchingPairs;
import org.eclipse.datatools.sqltools.sqleditor.internal.matching.GenericSQLPairMatcher;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IPaintPositionManager;
import org.eclipse.jface.text.IPainter;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.Position;
import org.eclipse.jface.text.source.ICharacterPairMatcher;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

/**
 * Referenced by <code>MatchingCharacterPainter</code>.
 * 
 * @see class org.eclipse.jface.text.source.MatchingCharacterPainter
 * 
 * @author juewu
 */
class MatchingPairPainter implements IPainter, PaintListener
{
    /** Indicates whether this painter is active */
    private boolean               fIsActive     = false;
    /** The source viewer this painter is associated with */
    private ISourceViewer         fSourceViewer;
    /** The viewer's widget */
    private StyledText            fTextWidget;
    /** The color in which to highlight the peer character */
    private Color                 fColor;
    /** The paint position manager */
    private IPaintPositionManager fPaintPositionManager;
    /** The position tracking the matching characters */
    private Position              fPairPosition = new Position(0, 0);
    /** The anchor indicating whether the character is left or right of the caret */
    private int                   fAnchor;

    private AbstractPairMatcher   fPairMatcher;

    public MatchingPairPainter(ISourceViewer sourceViewer, ICharacterPairMatcher matcher)
    {
        if (matcher instanceof AbstractPairMatcher)
        {
            fPairMatcher = (AbstractPairMatcher) matcher;
        }
        else
        {
            fPairMatcher = new GenericSQLPairMatcher(GenericSQLMatchingPairs.getInstance());
        }

        fSourceViewer = sourceViewer;
        fTextWidget = sourceViewer.getTextWidget();
    }

    public void setColor(Color color)
    {
        fColor = color;
    }

    public void deactivate(boolean redraw)
    {
        if (fIsActive)
        {
            fIsActive = false;
            fTextWidget.removePaintListener(this);
            if (fPaintPositionManager != null)
                fPaintPositionManager.unmanagePosition(fPairPosition);
            if (redraw)
                handleDrawRequest(null);
        }
    }

    public void dispose()
    {
        if (fPairMatcher != null)
        {
            fPairMatcher.clear();
            fPairMatcher = null;
        }

        fColor = null;
    }

    public void paint(int reason)
    {
        IDocument document = fSourceViewer.getDocument();
        if (document == null)
        {
            deactivate(false);
            return;
        }

        Point selection = fSourceViewer.getSelectedRange();
        if (selection.y > 0)
        {
            deactivate(true);
            return;
        }

        IRegion pair = fPairMatcher.match(document, selection.x);
        if (pair == null)
        {
            deactivate(true);
            redraw();
            return;
        }

        if (fIsActive)
        {
            if (IPainter.CONFIGURATION == reason)
            {
                // redraw current highlighting
                handleDrawRequest(null);

            }
            else if (pair.getOffset() != fPairPosition.getOffset() || pair.getLength() != fPairPosition.getLength()
                    || fPairMatcher.getAnchor() != fAnchor)
            {
                // otherwise only do something if position is different

                // remove old highlighting
                handleDrawRequest(null);
                // update position
                fPairPosition.isDeleted = false;
                fPairPosition.offset = pair.getOffset();
                fPairPosition.length = pair.getLength();
                fAnchor = fPairMatcher.getAnchor();
                // apply new highlighting
                handleDrawRequest(null);
            }
        }
        else
        {
            fIsActive = true;

            fPairPosition.isDeleted = false;
            fPairPosition.offset = pair.getOffset();
            fPairPosition.length = pair.getLength();
            fAnchor = fPairMatcher.getAnchor();

            fTextWidget.addPaintListener(this);
            fPaintPositionManager.managePosition(fPairPosition);
            handleDrawRequest(null);
        }
    }

    public void setPositionManager(IPaintPositionManager manager)
    {
        fPaintPositionManager = manager;
    }

    public void paintControl(PaintEvent event)
    {
        if (fTextWidget != null)
            handleDrawRequest(event.gc);
    }

    private void handleDrawRequest(GC gc)
    {

        if (fPairPosition.isDeleted)
            return;

        int offset = fPairPosition.getOffset();
        int length = fPairPosition.getLength();
        if (length < 1)
            return;

        IDocument document = fSourceViewer.getDocument();
        Point selection = fSourceViewer.getSelectedRange();
        IRegion pair = fPairMatcher.match(document, selection.x);

        if (pair == null)
            return;

        offset = 0;
        length = fPairMatcher.getMatchingTokenLength();
        fAnchor = fPairMatcher.getAnchor();

        // Adjust offset according to the token is left or right.
        if (ICharacterPairMatcher.RIGHT == fAnchor)
        {
            offset = pair.getOffset();
        }
        else
        {
            offset = pair.getOffset() + pair.getLength() - length;
        }

        // If offset is out of bound, return immediately.
        if (offset >= document.get().length())
            return;

        draw(gc, offset, length);
    }

    private void draw(GC gc, int offset, int length)
    {
        if (gc != null)
        {

            gc.setForeground(fColor);

            Rectangle bounds;
            if (length > 0)
                bounds = fTextWidget.getTextBounds(offset, offset + length - 1);
            else
            {
                Point loc = fTextWidget.getLocationAtOffset(offset);
                bounds = new Rectangle(loc.x, loc.y, 1, fTextWidget.getLineHeight(offset));
            }

            // draw box around line segment
            gc.drawRectangle(bounds.x, bounds.y, bounds.width - 1, bounds.height - 1);

        }
        else
        {
            redraw();
        }
    }

    private void redraw()
    {

        if (fTextWidget == null)
            return;

        fTextWidget.redraw();
    }

    protected AbstractPairMatcher getFPairMatcher()
    {
        return fPairMatcher;
    }

    protected void setFPairMatcher(AbstractPairMatcher pairMatcher)
    {
        fPairMatcher = pairMatcher;
    }
}
