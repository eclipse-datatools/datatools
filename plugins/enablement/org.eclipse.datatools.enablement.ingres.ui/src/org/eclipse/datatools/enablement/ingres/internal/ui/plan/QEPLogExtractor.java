/*******************************************************************************
 * Copyright (c) 2006, 2007, 2008 Ingres Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    Ingres Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ingres.internal.ui.plan;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for extracting QEP's from the Ingres JDBC driver's
 * logfile. It watches the log file that is defined in the system property
 * "ingres.jdbc.trace.log".
 * 
 * @author enrico.schenk@ingres.com
 */
public final class QEPLogExtractor {

	private static final QEPLogExtractor INSTANCE = new QEPLogExtractor();

	private static final String LINE_START = "DBMS TRACE: ";

	private transient BufferedReader logReader;

	private QEPLogExtractor() {
	}

	public static QEPLogExtractor getInstance() {
		return INSTANCE;
	}

	/**
	 * Reads to the end of the file, without retrieving a QEP.
	 * 
	 * @throws IOException
	 */
	public void skipAll() throws IOException {
		final BufferedReader reader = getLogReader();

		synchronized (reader) {
			while (reader.readLine() != null) {
			}
		}
	}

	/**
	 * Reads the log file to the last line and returns the last QEP found.
	 * 
	 * @return String array containing the lines of the QEP. Empty if no QEP was
	 *         found.
	 * @throws IOException
	 */
	public String[] extractLastQEP() throws IOException {
		final BufferedReader reader = getLogReader();

		synchronized (reader) {
			final List messages = new ArrayList();

			String line;
			boolean insideQep = false;
			while ((line = reader.readLine()) != null) {
				if (line.startsWith(LINE_START)) {
					if (insideQep == false) {
						insideQep = true;
						messages.clear();
					}

					line = line.substring(LINE_START.length());
					messages.add(line);
				} else {
					insideQep = false;
				}
			}

			return (String[]) messages.toArray(new String[0]);
		}
	}

	private BufferedReader getLogReader() throws IOException {
		if (logReader == null) {
			logReader = createReader(findLogFile());
		}

		return logReader;
	}

	private BufferedReader createReader(final File file) throws IOException {
		return new BufferedReader(new FileReader(file));
	}

	private File findLogFile() {
		File log = null;

		final String file = System.getProperty("ingres.jdbc.trace.log");
		if (file != null) {
			log = new File(file);
		}

		return log;
	}

}
