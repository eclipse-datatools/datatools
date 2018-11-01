/*******************************************************************************
 * Copyright (c) 2002-2005 IBM Corporation and others.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: rcernich - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.internal;

import java.security.SecureRandom;
import java.util.GregorianCalendar;

/**
 * This class is used to generate version 1 UUIDs as specified in <a
 * href="http://www.ietf.org/rfc/rfc4122.txt">IETF RFC 4122</a>.
 * 
 * The UUID generated is compatible with java.util.UUID in JDK 1.5.
 * 
 * (Note, DTP 1.0 must be compatible with Eclipse 3.1.x which precludes using
 * JDK 1.5 specific features.)
 * 
 * @author rcernich
 * 
 * Created on Dec 21, 2005
 */
public class UUID {

	private static int sClockSequence;
	private static byte sNodeID[];
	private static long sLastTime;
	private static int sNum100nsTicks;
	private static long sEpochOffset;
	private static char sHexChars[] = { '0', '1', '2', '3', '4', '5', '6', '7',
			'8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
	private static char sSeparator = '-';

	private byte mBytes[];

	public static UUID createUUID() {
		return new UUID();
	}

	private UUID() {
		super();

		mBytes = new byte[16];
		long currentTime = getCurrentTime();

		// bytes 0-3 == time low order bytes
		for (int i = 3; i > -1; --i) {
			mBytes[3 - i] = (byte) ((currentTime & (0xffL << (i * 8))) >> i * 8);
		}
		// bytes 4-5 == time mid bytes
		for (int i = 5; i > 3; --i) {
			mBytes[9 - i] = (byte) ((currentTime & (0xffL << (i * 8))) >> i * 8);
		}
		// bytes 6-7 == time high order bytes + version
		for (int i = 7; i > 5; --i) {
			mBytes[13 - i] = (byte) ((currentTime & (0xffL << (i * 8))) >> i * 8);
		}
		// set the version to version one (0001xxxx)
		mBytes[6] &= 0x0f;
		// set the version
		mBytes[6] |= 0x10;

		// byte 8 == sequence high order byte + variant (10xxxxxx)
		mBytes[8] = (byte) ((sClockSequence & 0xbf00) >> 8);
		// set the variant
		mBytes[8] |= 0x80;

		// byte 9 == sequence low order byte
		mBytes[9] = (byte) (sClockSequence & 0xff);

		// Copy over the node ID
		System.arraycopy(sNodeID, 0, mBytes, 10, 6);
	}

	public long getMostSigBytes() {
		long msb = 0;
		for (int i = 0; i < 8; ++i) {
			msb |= (((long) mBytes[i]) << 56) >>> (8 * (i));
		}
		return msb;
	}

	public long getLeastSigBytes() {
		long lsb = 0;
		for (int i = 8; i < 16; ++i) {
			lsb |= (((long) mBytes[i]) << 56) >>> (8 * (i - 8));
		}
		return lsb;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 4; ++i) {
			sb.append(sHexChars[(mBytes[i] & 0xf0) >> 4]);
			sb.append(sHexChars[mBytes[i] & 0x0f]);
		}
		sb.append(sSeparator);
		for (int i = 4; i < 6; ++i) {
			sb.append(sHexChars[(mBytes[i] & 0xf0) >> 4]);
			sb.append(sHexChars[mBytes[i] & 0x0f]);
		}
		sb.append(sSeparator);
		for (int i = 6; i < 8; ++i) {
			sb.append(sHexChars[(mBytes[i] & 0xf0) >> 4]);
			sb.append(sHexChars[mBytes[i] & 0x0f]);
		}
		sb.append(sSeparator);
		for (int i = 8; i < 10; ++i) {
			sb.append(sHexChars[(mBytes[i] & 0xf0) >> 4]);
			sb.append(sHexChars[mBytes[i] & 0x0f]);
		}
		sb.append(sSeparator);
		for (int i = 10; i < 16; ++i) {
			sb.append(sHexChars[(mBytes[i] & 0xf0) >> 4]);
			sb.append(sHexChars[mBytes[i] & 0x0f]);
		}
		return sb.toString();
	}

	/**
	 * May update sLastTime, sClockSequence, and sNum100nsTicks.
	 * 
	 * This basic algorithm used in this method was derived from
	 * org.eclipse.emf.ecore.util.EcoreUtil.UUID.updateCurrentTime().
	 * 
	 * @return the current time in units of 100ns
	 */
	private static synchronized long getCurrentTime() {
		long currentTime = System.currentTimeMillis();
		if (currentTime < sLastTime) {
			// clock must have been reset. increment the clock sequence
			++sClockSequence;
			if (sClockSequence > 0x3fff) {
				// roll the sequence over (high bits reserved for variant def)
				sClockSequence = 0;
			}
			// reset the number of sub-milli ticks
			sNum100nsTicks = 0;
		}
		else if (currentTime == sLastTime) {
			// increment the number of sub-milli ticks
			++sNum100nsTicks;
			if (sNum100nsTicks >= 10000) {
				// need to wait for the system clock to catch up
				for (currentTime = System.currentTimeMillis(); currentTime == sLastTime; currentTime = System
						.currentTimeMillis()) {
					try {
						Thread.sleep(1);
					}
					catch (InterruptedException e) {
					}
				}
				// reset the number of sub-milli ticks
				sNum100nsTicks = 0;
			}
		}
		else {
			// reset the number of sub-milli ticks
			sNum100nsTicks = 0;
		}
		// update last time
		sLastTime = currentTime;

		currentTime += sEpochOffset;
		currentTime *= 10000; // convert units from ms to 100ns
		currentTime += sNum100nsTicks;

		return currentTime;
	}

	static {
		// Theoretically, we could (should) be persisting this information
		// between executions

		// Initialize the random number generator
		SecureRandom rng = new SecureRandom();

		// Initialize the clock sequence
		sClockSequence = (short) rng.nextInt(0x3fff);

		// Initialize the node ID
		sNodeID = new byte[6];
		rng.nextBytes(sNodeID);
		// Set the multi-cast bit to distinguish this from a real MAC
		sNodeID[0] |= 0x80;

		sLastTime = System.currentTimeMillis();

		sNum100nsTicks = 0;

		// The following courtesy of org.eclipse.emf.ecore.util.EcoreUtil.UUID
		sEpochOffset = new GregorianCalendar(1970, 0, 1, 0, 0, 0).getTime()
				.getTime()
				- new GregorianCalendar(1582, 9, 15, 0, 0, 0).getTime()
						.getTime();
	}

}
