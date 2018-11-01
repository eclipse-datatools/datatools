/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2007-2008 SolutionsIQ, Inc.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *   SolutionsIQ, Inc. - Initial API and implementation
 *
 * </copyright>
 *******************************************************************************/
package org.eclipse.datatools.enablement.oda.ecore.ui.sourceviewer;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Composite;

public class DefaultSourceViewer implements IOCLSourceViewer {

	private IOCLSourceViewer state;

	public DefaultSourceViewer() {
		state = new UninitializedState();
	}

	private static final class InitializedState implements IOCLSourceViewer {

		private final SourceViewer sourceViewer;

		private EObject context;

		InitializedState(final SourceViewer sourceViewer) {
			this.sourceViewer = sourceViewer;
		}

		public Composite createExpressionControl(final Composite parent) {
			throw new IllegalStateException("The expression control has already been created.");
		}

		public String getExpression() {
			return sourceViewer.getTextWidget().getText();
		}

		public Composite getExpressionControl() {
			return sourceViewer.getTextWidget();
		}

		public SourceViewer getSourceViewer() {
			return sourceViewer;
		}

		public void setContext(final EObject context) {
			this.context = context;
		}

		public void setExpression(final String expression) {
			sourceViewer.setDocument(new Document(expression));
		}

		public EObject getContext() {
			return context;
		}
	}

	private final class UninitializedState implements IOCLSourceViewer {

		public Composite createExpressionControl(final Composite parent) {
			final SourceViewer sourceViewer = new SourceViewer(parent, null, SWT.BORDER);
			sourceViewer.configure(new SourceViewerConfiguration());
			sourceViewer.setEditable(true);
			final StyledText textWidget = sourceViewer.getTextWidget();
			state = new InitializedState(sourceViewer);
			return textWidget;
		}

		public Composite getExpressionControl() {
			throw new IllegalStateException("The expression control has not been created.");
		}

		public String getExpression() {
			throw new IllegalStateException("The expression control has not been created.");
		}

		public SourceViewer getSourceViewer() {
			throw new IllegalStateException("The expression control has not been created.");
		}

		public void setContext(final EObject context) {
			throw new IllegalStateException("The expression control has not been created.");
		}

		public void setExpression(final String expression) {
			throw new IllegalStateException("The expression control has not been created.");
		}

		public EObject getContext() {
			throw new IllegalStateException("The expression control has not been created.");
		}

	}

	public Composite createExpressionControl(final Composite parent) {
		return state.createExpressionControl(parent);
	}

	public Composite getExpressionControl() {
		return state.getExpressionControl();
	}

	public String getExpression() {
		return state.getExpression();
	}

	public SourceViewer getSourceViewer() {
		return state.getSourceViewer();
	}

	public void setContext(final EObject context) {
		state.setContext(context);
	}

	public void setExpression(final String expression) {
		state.setExpression(expression);
	}

	public EObject getContext() {
		return state.getContext();
	}
}
