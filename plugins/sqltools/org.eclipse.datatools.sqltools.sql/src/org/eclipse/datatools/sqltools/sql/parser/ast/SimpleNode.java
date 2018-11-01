/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/

package org.eclipse.datatools.sqltools.sql.parser.ast;

import org.eclipse.core.runtime.Assert;
import org.eclipse.datatools.sqltools.sql.internal.SQLActivator;
import org.eclipse.datatools.sqltools.sql.parser.Messages;
import org.eclipse.datatools.sqltools.sql.parser.ParsingResult;
import org.eclipse.datatools.sqltools.sql.parser.SQLParser;
import org.eclipse.datatools.sqltools.sql.parser.Token;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;

/**
 * Base implementation of <code>Node</code>.
 * @author Hui Cao
 *
 */
public class SimpleNode implements Node
{
    protected Node        _parent;
    protected Node[]      _children;
    protected int         _id;
    protected SQLParser   _parser;
    protected Token       _firstToken;
    protected Token       _lastToken;

    public SimpleNode(int i)
    {
        _id = i;
    }

    public SimpleNode(SQLParser p, int i)
    {
        this(i);
        _parser = p;
    }

    public void jjtOpen()
    {
    }

    public void jjtClose()
    {
    }

    public void jjtSetParent(Node n)
    {
        _parent = n;
    }

    public Node jjtGetParent()
    {
        return _parent;
    }

    public void jjtAddChild(Node n, int i)
    {
        if (_children == null)
        {
            _children = new Node[i + 1];
        }
        else if (i >= _children.length)
        {
            Node c[] = new Node[i + 1];
            System.arraycopy(_children, 0, c, 0, _children.length);
            _children = c;
        }
        _children[i] = n;
    }

    public Node jjtGetChild(int i)
    {
        return _children[i];
    }

    public int jjtGetNumChildren()
    {
        return (_children == null) ? 0 : _children.length;
    }

    /*
     * You can override these two methods in subclasses of SimpleNode to customize the way the node appears when the
     * tree is dumped. If your output uses more than one line you should override toString(String), otherwise overriding
     * toString() is probably all you need to do.
     */

    public String toString()
    {
        return this.getClass().getName();
    }

    public String toString(String prefix)
    {
        return prefix + toString();
    }

    /*
     * Override this method if you want to customize how the node dumps out its children.
     */

    public void dump(String prefix)
    {
        System.out.println(toString(prefix));
        if (_children != null)
        {
            for (int i = 0; i < _children.length; ++i)
            {
                SimpleNode n = (SimpleNode) _children[i];
                if (n != null)
                {
                    n.dump(prefix + " "); //$NON-NLS-1$
                }
            }
        }
    }

    public Token getLastToken()
    {
        return _lastToken;
    }

    public void setLastToken(Token token)
    {
        _lastToken = token;
    }

    public Token getFirstToken()
    {
        return _firstToken;
    }

    public void setFirstToken(Token token)
    {
        _firstToken = token;
    }

    /**
     * The concrete implementation for this method has be removed into Thoken.java.
     * By stephen
     * @param viewer where the node is displayed
     * @return
     */
    public int getStartOffset(IDocument document)
    {
        Assert.isNotNull(document);
        int offset = 0;
        if(_firstToken != null)
        {
            offset = _firstToken.getStartOffset(document);
        }
        return offset;
    }

    /**
     * 
     * @param viewer where the node is displayed
     * @see getGreatestEndOffset
     */
    public int getEndOffset(IDocument document)
    {
        Assert.isNotNull(document);
        int offset = 0;
        if (_lastToken != null)
        {
            offset = _lastToken.getEndOffset(document);
        }
        return offset;
    }

    /**
     * this method differs with getEndOffset in that it takes the trailing spaces into account 
     * 
     * @param viewer where the node is displayed
     * @return
     */
    public int getGreatestEndOffset(IDocument document)
    {
        Assert.isNotNull(document);
        int offset = 0;
        if (_lastToken != null)
        {
            try
            {
                Token next = _lastToken.next;
                if (next == null || next.kind == 0)
                {
                    return document.getLength() > 0 ? document.getLength() : 0;
                }

                offset = document.getLineOffset(next.beginLine - 1) + next.beginColumn - 1 ;
            }
            catch (BadLocationException e1)
            {
                SQLActivator.getDefault().log( Messages.SimpleNode_1, e1); 
            }
        }
        return offset;
    }

    public boolean equals(Object obj)
    {
        if (!(obj instanceof SimpleNode)) 
        {
            return false; 
        }
        try
        {
            SimpleNode other = (SimpleNode) obj;
            boolean equals = _id == other._id && _firstToken.equals(other._firstToken);// &&
            // _lastToken.equals(other._lastToken);
            return equals;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.sybase.stf.dmp.ui.sqleditor.sql.parser.Node#setDocument(org.eclipse.jface.text.IDocument)
     */
    public void setDocument(IDocument document)
    {
        if (this instanceof IASTStart)
        {
            ((IASTStart) this).doSetDocument(document);
            return;
        }
        Node parent = this;
        while (parent.jjtGetParent() != null)
        {
            parent = parent.jjtGetParent();
        }
        if (parent == this)
        {
            parent.setDocument(document);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.sybase.stf.dmp.ui.sqleditor.sql.parser.Node#getDocument()
     */
    public IDocument getDocument()
    {
        if (this instanceof IASTStart)
        {
            return ((IASTStart) this).doGetDocument();
        }
        Node parent = this;
        while (parent.jjtGetParent() != null)
        {
            parent = parent.jjtGetParent();
        }
        if (parent == this)
        {
            return null;
        }
        return parent.getDocument();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.sybase.stf.dmp.ui.sqleditor.sql.parser.Node#getStartOffset()
     */
    public int getStartOffset()
    {
        return getStartOffset(getDocument());
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.sybase.stf.dmp.ui.sqleditor.sql.parser.Node#getEndOffset()
     */
    public int getEndOffset()
    {
        return getEndOffset(getDocument());
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.sybase.stf.dmp.ui.sqleditor.sql.parser.Node#getGreatestEndOffset()
     */
    public int getGreatestEndOffset()
    {
        return getGreatestEndOffset(getDocument());
    }

    public String getSQLText()
    {
        IDocument doc = getDocument();
        Assert.isNotNull(doc);
        int start = getStartOffset(doc);
        int end = getEndOffset(doc);
        int length = end - start;
        //To make these codes more robuster, we should check length value and provent it being negative.
        length = length>0?length:0;
        try
        {
            return doc.get(start, length);
        }
        catch (BadLocationException e)
        {
            SQLActivator.getDefault().log( Messages.SimpleNode_2, e); 
        }
        return ""; //$NON-NLS-1$
    }


    public int getNextTokenOffset()
    {
        return getNextTokenOffset(getDocument());
    }

    /**
     * Get the next token offset
     * @param viewer where the node is displayed
     * @see getGreatestEndOffset
     */
    public int getNextTokenOffset(IDocument document)
    {
        Assert.isNotNull(document);
        int offset = 0;
        Token nextToken = _lastToken.next;
        if (_lastToken != null && (nextToken.image.equals(",") || nextToken.image.equals(";")))  
        {
            try
            {
                offset = document.getLineOffset(nextToken.endLine - 1) + nextToken.endColumn;
            }
            catch (BadLocationException e1)
            {
                SQLActivator.getDefault().log( Messages.SimpleNode_1, e1); 
            }
        }
        return offset;
    }

    public Node getPreviousNode() 
    {
        this.getStartOffset();
        Node previousNode = ParsingResult.findNode(getDocument(), this.getStartOffset(), getRootNode(), true );
        return previousNode;
    }


    private Node getRootNode()
    {
        Node rootNode = this;
        while(rootNode.jjtGetParent() != null)
        {
            rootNode = rootNode.jjtGetParent();
        }
        return rootNode;
    }

    /**
     * Returns the node text by concatenate tokens with white spaces.
     * @return node text
     * @author Li Huang
     */
    public String getText()
    {
        Node node = this;
        StringBuffer text = new StringBuffer("");
        if (node != null)
        {
            Token token = node.getFirstToken();
            text.append(token.image);
            while(token.next != null && token != node.getLastToken())
            {
            	//to prevent insert white spaces between a qualified db object name
                if (token.image.equals(".") || token.next.image.equals("."))
                {
                    token = token.next;
                }
                else
                {
                    token = token.next;
                    text.append(" ");
                }
                text.append(token.image);
            }
        }
        return text.toString();
    }
    
    /**
     *  Accepts the visitor. 
     */
    public Object jjtAccept(ISQLParserVisitor visitor, Object data) {
      return visitor.visit(this, data);
    }

    /**
     *  Accepts the visitor for all children. 
     */
    public Object acceptChildren(ISQLParserVisitor visitor, Object data) {
      if (_children != null) {
        for (int i = 0; i < _children.length; ++i) {
          _children[i].jjtAccept(visitor, data);
        }
      }
      return data;
    }

    public boolean exists()
    {
        return _firstToken != null && _lastToken != null && _lastToken.next != _firstToken;
    }
}

