/*******************************************************************************
 * Copyright (c) 2002, 2014 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ibm.util;

import java.util.Stack;

/**
 * A StringBuffer with whose capacity is not reset by setLength(0)
 * and whose char[] is copied for each toString().
 * <p>
 * For the toString() in StringBuffer, the char[] is not copied, so that the
 * new String is set to use the existing char[] in the StringBuffer (including
 * any of its unused capacity), and the StringBuffer is marked "shared."
 * A "shared" StringBuffer is copied if it is reused.
 * <p>
 * The class java.lang.StringBuffer has a default capacity of 16,
 * which it reverts to if you get the toString() and call setLength(0).
 * StringBuffer has a fixed expansion factor of <code>(n + 1) * 2</code>.
 * <p>
 * This class has a default capacity of 128 and a default expansion factor of 1.5.
 * The initial capacity for this and for StringBuffer is determined by using
 * the constructor that takes an int, but for this class you may also
 * set the expansion factor.
 * <p>
 * For determining a good initial capacity and expansion factor of ReuseStringBuffer,
 * construct and use a tool.BufferMonitor and call its
 * <code>dumpMonitors()</code> method after your last use of the instance.
 * Ask Thomas Sharp for DumpMonitor.
 * <p>
 * Most StringBuffer methods are copied here also because StringBuffer is final
 * (which means that it cannot be subclassed).
 * <p>
 * The boolean <code>shared</code> in StringBuffer is not needed here for the same
 * purpose. If you call ReuseStringBuffer.toString(), String must copy the value array.
 * The boolean <code>shared</code> is needed for StringBuffer
 * because the String constructor collaborates with StringBuffer to avoid copying
 * the array unless it is reused after a toString().
 * Because of this feature, ReuseStringBuffer.setLength(0) reuses the existing value array.
 * This saves ReuseStringBuffer from having to extend a new array of default capacity
 * and also avoids assigning unused buffer to the new String created by the toString().
 * <p>
 * In addition to acting as a StringBuffer replacement, ReuseStringBuffer also acts
 * as a ReuseStringBuffer factory and pool. The methods getBuffer, toString(Buffer),
 * and freeBuffer let you share ReuseStringBuffers for different uses.
 * <p>
 * A possible enhancement to ReuseStringBuffer would be to restore the boolean <code>shared</code>
 * and use it to optimize repeated toString() calls without intermediate changes.
 * This optimation could allow toString() to return the same String if the flag is not reset
 * by an intermediate change. Currently, we do not believe repeated toStrings()
 * are a typical pattern of usage; instead, the user is advised to retain the String
 * and discard the buffer.
 * <p>
 * @author	Thomas Sharp, sharpt@us.ibm.com
 * @see java.lang.StringBuffer
 */
public class ReuseStringBuffer implements CharSequence
{
   /** Our wrapped buffer. */
   protected StringBuffer buffer;
   
   /** Shared string buffers. */
   protected static Stack<ReuseStringBuffer> buffers;
   
   /**
    * Gets a reusable ReuseStringBuffer for string concatenation.
    * @return A ReuseStringBuffer constructed with arguments (128, (float)1.5).
    */
   public static ReuseStringBuffer getBuffer()
   {
      return getBuffer(128);
   }
   
   /**
    * Gets a reusable ReuseStringBuffer for string concatenation and appends
    * to it the given text.
    * @param str The initial contents of the buffer.
    * @return A ReuseStringBuffer constructed with arguments (text.length() + 128, (float)1.5).
    */
   public static ReuseStringBuffer getBuffer(String str)
   {
      ReuseStringBuffer b = getBuffer(str.length() + 128);
      b.append(str);
      return b;
   }
   
   /**
    * Gets a reusable ReuseStringBuffer for string concatenation.
    * @param capacity The minimum capacity. The actual capacity of the
    * ReuseStringBuffer returned may be greater.
    * @return A ReuseStringBuffer constructed with arguments (capacity, (float)1.5).
    */
   public static synchronized ReuseStringBuffer getBuffer(int capacity)
   {
      if (buffers == null)
         buffers = new Stack<ReuseStringBuffer>();
      if (!buffers.empty()) {
         ReuseStringBuffer b;
         for (int i = buffers.size() - 1; i > -1; i--)
         {
            b = buffers.elementAt(i);
            if (b.capacity() >= capacity)
            {
               buffers.removeElementAt(i);
               return b;
            }
         }
      }
      return new ReuseStringBuffer(capacity);
   }
   
   /**
    * Frees a given buffer for reuse and returns the toString().
    * @param buffer Any ReuseStringBuffer, whether retrieved from ReuseStringBuffer.getBuffer() or not.
    * @return The toString().
    */
   public static String toString(ReuseStringBuffer buffer)
   {
      if (buffers == null)
         buffers = new Stack<ReuseStringBuffer>();
      String ts = buffer.toString();
      buffer.setLength(0);
      buffers.push(buffer);
      return ts;
   }
   
   /**
    * Frees a given buffer for reuse.
    * @param buffer Any ReuseStringBuffer, whether retrieved from ReuseStringBuffer.getBuffer() or not.
    */
   public static void freeBuffer(ReuseStringBuffer buffer)
   {
      if (buffers == null)
         buffers = new Stack<ReuseStringBuffer>();
      buffer.setLength(0);
      buffers.push(buffer);
   }
   
   /**
    * Constructs a string buffer with no characters in it,
    * an initial capacity of 128 characters,
    * and an expansion factor of 1.5.
    */
   public ReuseStringBuffer() {
      this(128, (float)1.5);
   }
   
   /**
    * Constructs a string buffer with no characters in it,
    * an initial capacity specified by the <code>length</code> argument,
    * and an expansion factor of 1.5.
    * <p>
    * @param		   capacity	the initial capacity.
    * @exception	NegativeArraySizeException	if the <code>length</code>
    *					argument is less than <code>0</code>.
    */
   public ReuseStringBuffer(int capacity) {
      this(capacity, (float)1.5);
   }
   
   /**
    * Constructs a string buffer so that it represents the same
    * sequence of characters as the string argument; in other
    * words, the initial contents of the string buffer is a copy of the
    * argument string. The initial capacity of the string buffer is
    * <code>128</code> plus the length of the string argument.
    * The expansion factor is 1.5.
    * <p>
    * @param	str	the initial contents of the buffer.
    */
   public ReuseStringBuffer(String str) {
      this(str.length() + 128);
      buffer.append(str);
   }
   
   /**
    * @deprecated expansionFactor is not used.
    * Constructs a string buffer with no characters in it,
    * an initial capacity of 128 characters,
    * and an expansion factor specified by the <code>expansionFactor</code> arguement.
    * <p>
    * @param expansionFactor A float specify the factor by which to
    * expand the capacity, if needed.
    */
   public ReuseStringBuffer(float expansionFactor) {
      this(128);
   }
   
   /**
    * @deprecated expansionFactor is not used.
    * Constructs a string buffer with no characters in it,
    * an initial capacity specified by the <code>length</code> argument,
    * and an expansion factor specified by the <code>expansionFactor</code> arguement.
    * <p>
    * @param expansionFactor A float specify the factor by which to
    * expand the capacity, if needed.
    * @param		   capacity	the initial capacity.
    * @param expansionFactor A float specify the factor by which to
    * expand the capacity, if needed.
    * @exception	NegativeArraySizeException	if the <code>length</code>
    *					argument is less than <code>0</code>.
    */
   public ReuseStringBuffer(int capacity, float expansionFactor) {
      buffer = new StringBuffer(capacity); //expansionFactor is not used 
   }
   
   /**
    * @deprecated expansionFactor is not used.
    * Constructs a string buffer so that it represents the same
    * sequence of characters as the string argument; in other
    * words, the initial contents of the string buffer is a copy of the
    * argument string. The initial capacity of the string buffer is
    * <code>128</code> plus the length of the string argument.
    * The expansion factor is specified by the <code>expansionFactor</code> arguement.
    * <p>
    * @param	str	the initial contents of the buffer.
    * @param expansionFactor A float specify the factor by which to
    * expand the capacity, if needed.
    */
   public ReuseStringBuffer(String str, float expansionFactor) {
      this(str.length() + 128);
      buffer.append(str);
   }
   
   /**
    * Returns the length (character count) of this string buffer.
    * <p>
    * @return	the length of the sequence of characters currently
    *			represented by this string buffer.
    */
   public int length() {
      return buffer.length();
   }
   
   /**
    * Returns the current capacity of the String buffer. The capacity
    * is the amount of storage available for newly inserted
    * characters; beyond which an allocation will occur.
    * <p>
    * @return	the current capacity of this string buffer.
    */
   public int capacity() {
      return buffer.capacity();
   }
   
   /**
    * Ensures that the capacity of the buffer is at least equal to the
    * specified minimum.
    * If the current capacity of this string buffer is less than the
    * argument, then a new internal buffer is allocated with greater
    * capacity. The new capacity is the larger of:
    * <ul>
    * <li>The <code>minimumCapacity</code> argument.
    * <li>The old capacity times <code>expansionFactor</code>.
    * </ul>
    * If the <code>minimumCapacity</code> argument is nonpositive, this
    * method takes no action and simply returns.
    * <p>
    * @param	minimumCapacity	the minimum desired capacity.
    */
   public synchronized void ensureCapacity(int minimumCapacity) {
      buffer.ensureCapacity(minimumCapacity);
   }
   
   /**
    * Sets the length of this String buffer.
    * This string buffer is altered to represent a new character sequence
    * whose length is specified by the argument. For every nonnegative
    * index <i>k</i> less than <code>newLength</code>, the character at
    * index <i>k</i> in the new character sequence is the same as the
    * character at index <i>k</i> in the old sequence if <i>k</i> is less
    * than the length of the old character sequence; otherwise, it is the
    * null character <code>'\u0000'</code>.
    * <p>	
    * In other words, if the <code>newLength</code> argument is less than
    * the current length of the string buffer, the string buffer is
    * truncated to contain exactly the number of characters given by the
    * <code>newLength</code> argument.
    * <p>
    * If the <code>newLength</code> argument is greater than or equal
    * to the current length, sufficient null characters
    * (<code>'&#92;u0000'</code>) are appended to the string buffer so that
    * length becomes the <code>newLength</code> argument.
    * <p>
    * The <code>newLength</code> argument must be greater than or equal
    * to <code>0</code>.
    * <p>
    * @param		newLength	the new length of the buffer.
    * @exception	IndexOutOfBoundsException	if the
    *					<code>newLength</code> argument is negative.
    * @see			#length()
    */
   public synchronized void setLength(int newLength) {
      buffer.setLength(newLength);
   }
   
   /**
    * The specified character of the sequence currently represented by
    * the string buffer, as indicated by the <code>index</code> argument,
    * is returned. The first character of a string buffer is at index
    * <code>0</code>, the next at index <code>1</code>, and so on, for
    * array indexing.
    * <p>
    * The index argument must be greater than or equal to
    * <code>0</code>, and less than the length of this string buffer.
    * <p>
    * @param		index	the index of the desired character.
    * @return		the character at the specified index of this string buffer.
    * @exception	IndexOutOfBoundsException	if <code>index</code> is
    *				negative or greater than or equal to <code>length()</code>.
    * @see			#length()
    */
   public synchronized char charAt(int index) {
      return buffer.charAt(index);
   }
   
   /**
    * Characters are copied from this string buffer into the
    * destination character array <code>dst</code>. The first character to
    * be copied is at index <code>srcBegin</code>; the last character to
    * be copied is at index <code>srcEnd-1</code>. The total number of
    * characters to be copied is <code>srcEnd-srcBegin</code>. The
    * characters are copied into the subarray of <code>dst</code> starting
    * at index <code>dstBegin</code> and ending at index:
    * <p><blockquote><pre>
    * dstbegin + (srcEnd-srcBegin) - 1
    * </pre></blockquote>
    * <p>
    * @param		srcBegin	start copying at this offset in the string buffer.
    * @param		srcEnd		stop copying at this offset in the string buffer.
    * @param		dst			the array to copy the data into.
    * @param		dstBegin	offset into <code>dst</code>.
    * @exception	NullPointerException if <code>dst</code> is
    *				<code>null</code>.
    * @exception	IndexOutOfBoundsException	if any of the following is true:
    *				<ul>
    *				<li><code>srcBegin</code> is negative
    *				<li><code>dstBegin</code> is negative
    *				<li>the <code>srcBegin</code> argument is greater than
    *				the <code>srcEnd</code> argument.
    *				<li><code>srcEnd</code> is greater than
    *				<code>this.length()</code>, the current length of this
    *				string buffer.
    *				<li><code>dstBegin+srcEnd-srcBegin</code> is greater than
    *				<code>dst.length</code>
    *				</ul>
    */
   public synchronized void getChars(int srcBegin, int srcEnd, char[] dst, int dstBegin) {
      buffer.getChars(srcBegin, srcEnd, dst, dstBegin);
   }
   
   /**
    * The character at the specified index of this string buffer is set
    * to <code>ch</code>. The string buffer is altered to represent a new
    * character sequence that is identical to the old character sequence,
    * except that it contains the character <code>ch</code> at position
    * <code>index</code>.
    * <p>
    * The offset argument must be greater than or equal to
    * <code>0</code>, and less than the length of this string buffer.
    * <p>
    * @param		index	the index of the character to modify.
    * @param		ch		the new character.
    * @exception	IndexOutOfBoundsException	if <code>index</code> is
    *				negative or greater than or equal to <code>length()</code>.
    * @see			#length()
    */
   public synchronized void setCharAt(int index, char ch) {
      buffer.setCharAt(index, ch);
   }
   
   /**
    * Appends the string representation of the <code>Object</code>
    * argument to this string buffer.
    * <p>
    * The argument is converted to a string as if by the method
    * <code>String.valueOf</code>, and the characters of that
    * string are then appended to this string buffer.
    *
    * @param	obj	an <code>Object</code>.
    * @return	a reference to this <code>ReuseStringBuffer</code> object.
    * @see		java.lang.String#valueOf(java.lang.Object)
    * @see		#append(java.lang.String)
    */
   public synchronized ReuseStringBuffer append(Object obj) {
      buffer.append(obj);
      return this;
   }
   
   /**
    * Appends the string to this string buffer.
    * <p>
    * The characters of the <code>String</code> argument are appended, in
    * order, to the contents of this string buffer, increasing the
    * length of this string buffer by the length of the argument.
    * If <code>str</code> is <code>null</code>, then the four characters
    * <code>"null"</code> are appended to this string buffer.
    * <p>
    * Let <i>n</i> be the length of the old character sequence, the one
    * contained in the string buffer just prior to execution of the
    * <code>append</code> method. Then the character at index <i>k</i> in
    * the new character sequence is equal to the character at index <i>k</i>
    * in the old character sequence, if <i>k</i> is less than <i>n</i>;
    * otherwise, it is equal to the character at index <i>k-n</i> in the
    * argument <code>str</code>.
    * <p>
    * @param	str	a string.
    * @return	a reference to this <code>ReuseStringBuffer</code>.
    */
   public synchronized ReuseStringBuffer append(String str) {
      buffer.append(str);
      return this;
   }
   
   /**
    * Appends the specified <tt>StringBuffer</tt> to this
    * <tt>ReuseStringBuffer</tt>.
    * <p>
    * The characters of the <tt>StringBuffer</tt> argument are appended,
    * in order, to the contents of this <tt>ReuseStringBuffer</tt>, increasing the
    * length of this <tt>ReuseStringBuffer</tt> by the length of the argument.
    * If <tt>sb</tt> is <tt>null</tt>, then the four characters
    * <tt>"null"</tt> are appended to this <tt>ReuseStringBuffer</tt>.
    * <p>
    * Let <i>n</i> be the length of the old character sequence, the one
    * contained in the <tt>ReuseStringBuffer</tt> just prior to execution of the
    * <tt>append</tt> method. Then the character at index <i>k</i> in
    * the new character sequence is equal to the character at index <i>k</i>
    * in the old character sequence, if <i>k</i> is less than <i>n</i>;
    * otherwise, it is equal to the character at index <i>k-n</i> in the
    * argument <code>sb</code>.
    * <p>
    * The method <tt>ensureCapacity</tt> is first called on this
    * <tt>ReuseStringBuffer</tt> with the new buffer length as its argument.
    * (This ensures that the storage of this <tt>ReuseStringBuffer</tt> is
    * adequate to contain the additional characters being appended.)
    *
    * @param   sb         the <tt>StringBuffer</tt> to append.
    * @return  a reference to this <tt>ReuseStringBuffer</tt>.
    */
   public synchronized ReuseStringBuffer append(StringBuffer sb) {
      buffer.append(sb);
      return this;
   }
   
   /**
    * Appends the specified <tt>ReuseStringBuffer</tt> to this
    * <tt>ReuseStringBuffer</tt>.
    * <p>
    * The characters of the <tt>ReuseStringBuffer</tt> argument are appended,
    * in order, to the contents of this <tt>ResuseStringBuffer</tt>, increasing the
    * length of this <tt>ResuseStringBuffer</tt> by the length of the argument.
    * If <tt>sb</tt> is <tt>null</tt>, then the four characters
    * <tt>"null"</tt> are appended to this <tt>ResuseStringBuffer</tt>.
    * <p>
    * Let <i>n</i> be the length of the old character sequence, the one
    * contained in the <tt>StringBuffer</tt> just prior to execution of the
    * <tt>append</tt> method. Then the character at index <i>k</i> in
    * the new character sequence is equal to the character at index <i>k</i>
    * in the old character sequence, if <i>k</i> is less than <i>n</i>;
    * otherwise, it is equal to the character at index <i>k-n</i> in the
    * argument <code>sb</code>.
    * 
    * @param   sb         the <tt>StringBuffer</tt> to append.
    * @return  a reference to this <tt>ReuseStringBuffer</tt>.
    */
   public synchronized ReuseStringBuffer append(ReuseStringBuffer sb) {
      if (sb == null)
         buffer.append("null"); //$NON-NLS-1$
      else
         buffer.append(sb.toString());
      return this;
   }
   
   /**
    * Appends the string representation of the <code>char</code> array
    * argument to this string buffer.
    * <p>
    * The characters of the array argument are appended, in order, to
    * the contents of this string buffer. The length of this string
    * buffer increases by the length of the argument.
    * <p>
    * The overall effect is exactly as if the argument were converted to
    * a string by the method {@link String#valueOf(char[])} and the
    * characters of that string were then {@link #append(String) appended}
    * to this <code>ReuseStringBuffer</code> object.
    * <p>
    * @param	str	the characters to be appended.
    * @return	a reference to this <code>ReuseStringBuffer</code> object.
    */
   public synchronized ReuseStringBuffer append(char[] str) {
      buffer.append(str);
      return this;
   }
   
   /**
    * Appends the string representation of a subarray of the
    * <code>char</code> array argument to this string buffer.
    * <p>
    * Characters of the character array <code>str</code>, starting at
    * index <code>offset</code>, are appended, in order, to the contents
    * of this string buffer. The length of this string buffer increases
    * by the value of <code>len</code>.
    * <p>
    * The overall effect is exactly as if the arguments were converted to
    * a string by the method {@link String#valueOf(char[],int,int)} and the
    * characters of that string were then {@link #append(String) appended}
    * to this <code>ReuseStringBuffer</code> object.
    * <p>
    * @param	str		the characters to be appended.
    * @param	offset	the index of the first character to append.
    * @param	len		the number of characters to append.
    * @return	a reference to this <code>ReuseStringBuffer</code> object.
    */
   public synchronized ReuseStringBuffer append(char[] str, int offset, int len) {
      buffer.append(str, offset, len);
      return this;
   }
   
   /**
    * Appends the string representation of the <code>boolean</code>
    * argument to the string buffer.
    * <p>
    * The argument is converted to a string as if by the method
    * <code>String.valueOf</code>, and the characters of that
    * string are then appended to this string buffer.
    * <p>
    * @param	b	a <code>boolean</code>.
    * @return	a reference to this <code>ReuseStringBuffer</code>.
    * @see		java.lang.String#valueOf(boolean)
    * @see		#append(java.lang.String)
    */
   public synchronized ReuseStringBuffer append(boolean b) {
      buffer.append(b);
      return this;
   }
   
   /**
    * Appends the string representation of the <code>char</code>
    * argument to this string buffer.
    * <p>
    * The argument is appended to the contents of this string buffer.
    * The length of this string buffer increases by <code>1</code>.
    * <p>
    * The overall effect is exactly as if the argument were converted to
    * a string by the method {@link String#valueOf(char)} and the character
    * in that string were then {@link #append(String) appended} to this
    * <code>ReuseStringBuffer</code> object.
    * <p>
    * @param	c	a <code>char</code>.
    * @return	a reference to this <code>ReuseStringBuffer</code> object.
    */
   public synchronized ReuseStringBuffer append(char c) {
      buffer.append(c);
      return this;
   }
   
   /**
    * Appends the string representation of the <code>int</code>
    * argument to this string buffer.
    * <p>
    * The argument is converted to a string as if by the method
    * <code>String.valueOf</code>, and the characters of that
    * string are then appended to this string buffer.
    *
    * @param	i	an <code>int</code>.
    * @return	a reference to this <code>ReuseStringBuffer</code> object.
    * @see		java.lang.String#valueOf(int)
    * @see		#append(java.lang.String)
    */
   public synchronized ReuseStringBuffer append(int i) {
      buffer.append(i);
      return this;
   }
   
   /**
    * Appends the string representation of the <code>long</code>
    * argument to this string buffer.
    * <p>
    * The argument is converted to a string as if by the method
    * <code>String.valueOf</code>, and the characters of that
    * string are then appended to this string buffer.
    * <p>
    * @param	l	a <code>long</code>.
    * @return	a referenct to this <code>ReuseStringBuffer</code> object.
    * @see		java.lang.String#valueOf(long)
    * @see		#append(java.lang.String)
    */
   public synchronized ReuseStringBuffer append(long l) {
      buffer.append(l);
      return this;
   }
   
   /**
    * Appends the string representation of the <code>float</code>
    * argument to this string buffer.
    * <p>
    * The argument is converted to a string as if by the method
    * <code>String.valueOf</code>, and the characters of that
    * string are then appended to this string buffer.
    * <p>
    * @param	f	a <code>float</code>.
    * @return	a reference to this <code>ReuseStringBuffer</code> object.
    * @see		java.lang.String#valueOf(float)
    * @see		#append(java.lang.String)
    */
   public synchronized ReuseStringBuffer append(float f) {
      buffer.append(f);
      return this;
   }
   
   /**
    * Appends the string representation of the <code>double</code>
    * argument to this string buffer.
    * <p>
    * The argument is converted to a string as if by the method
    * <code>String.valueOf</code>, and the characters of that
    * string are then appended to this string buffer.
    *
    * @param	d	a <code>double</code>.
    * @return	a reference to this <code>ReuseStringBuffer</code> object.
    * @see		java.lang.String#valueOf(double)
    * @see		#append(java.lang.String)
    */
   public synchronized ReuseStringBuffer append(double d) {
      buffer.append(d);
      return this;
   }
   
   /**
    * Removes the characters in a substring of this <code>ReuseStringBuffer</code>.
    * The substring begins at the specified <code>start</code> and extends to
    * the character at index <code>end - 1</code> or to the end of the
    * <code>ReuseStringBuffer</code> if no such character exists. If
    * <code>start</code> is equal to <code>end</code>, no changes are made.
    * <p>
    * @param		start	The beginning index, inclusive.
    * @param		end	The ending index, exclusive.
    * @return		a reference to this <code>ReuseStringBuffer</code> object.
    * @exception	StringIndexOutOfBoundsException	if <code>start</code>
    *				is negative, greater than <code>length()</code>, or
    *			greater than <code>end</code>.
    */
   public synchronized ReuseStringBuffer delete(int start, int end) {
      buffer.delete(start, end);
      return this;
   }
   
   /**
    * Removes the character at the specified position in this
    * <code>ReuseStringBuffer</code> (shortening the <code>ReuseStringBuffer</code>
    * by one character).
    * <p>
    * @param		index	Index of character to remove
    * @return		a reference to this <code>ReuseStringBuffer</code> object.
    * @exception	StringIndexOutOfBoundsException	if the <code>index</code>
    *			is negative or greater than or equal to
    *			<code>length()</code>.
    */
   public synchronized ReuseStringBuffer deleteCharAt(int index) {
      buffer.deleteCharAt(index);
      return this;
   }
   
   
   /**
    * Replaces the characters in a substring of this <code>StringBuffer</code>
    * with characters in the specified <code>String</code>. The substring
    * begins at the specified <code>start</code> and extends to the character
    * at index <code>end - 1</code> or to the end of the
    * <code>StringBuffer</code> if no such character exists. First the
    * characters in the substring are removed and then the specified
    * <code>String</code> is inserted at <code>start</code>. (The
    * <code>StringBuffer</code> will be lengthened to accommodate the
    * specified String if necessary.)
    *
    * @param      start    The beginning index, inclusive.
    * @param      end      The ending index, exclusive.
    * @param      str   String that will replace previous contents.
    * @return     a reference to this <code>ReuseStringBuffer</code> object.
    * @exception  StringIndexOutOfBoundsException  if <code>start</code>
    *             is negative, greater than <code>length()</code>, or
    *             greater than <code>end</code>.
    * @since      1.2
    */
   public synchronized ReuseStringBuffer replace(int start, int end, String str) {
      buffer.replace(start, end, str);
      return this;
   }
   
   /**
    * Returns a new <code>String</code> that contains a subsequence of
    * characters currently contained in this <code>ReuseStringBuffer</code>.The
    * substring begins at the specified index and extends to the end of the
    * <code>ReuseStringBuffer</code>.
    * <p>
    * @param		start	The beginning index, inclusive.
    * @return		The new string.
    * @exception	StringIndexOutOfBoundsException	if <code>start</code> is
    *				less than zero, or greater than the length of this
    *				<code>ReuseStringBuffer</code>.
    */
   public synchronized String substring(int start) {
      return buffer.substring(start);
   }
   
   /**
    * Returns a new character sequence that is a subsequence of this sequence.
    *
    * <p> An invocation of this method of the form
    *
    * <blockquote><pre>
    * sb.subSequence(begin,&nbsp;end)</pre></blockquote>
    *
    * behaves in exactly the same way as the invocation
    *
    * <blockquote><pre>
    * sb.substring(begin,&nbsp;end)</pre></blockquote>
    *
    * This method is provided so that the <tt>ReuseStringBuffer</tt> class can
    * implement the {@link CharSequence} interface. </p>
    *
    * @param      start   the start index, inclusive.
    * @param      end     the end index, exclusive.
    * @return     the specified subsequence.
    *
    * @throws  IndexOutOfBoundsException
    *          if <tt>start</tt> or <tt>end</tt> are negative,
    *          if <tt>end</tt> is greater than <tt>length()</tt>,
    *          or if <tt>start</tt> is greater than <tt>end</tt>
    *
    * @since 1.4
    * @spec JSR-51
    */
   public CharSequence subSequence(int start, int end) {
       return buffer.substring(start, end);
   }
   
   /**
    * Returns a new <code>String</code> that contains a subsequence of
    * characters currently contained in this <code>ReuseStringBuffer</code>. The
    * substring begins at the specified <code>start</code> and
    * extends to the character at index <code>end - 1</code>. An
    * exception is thrown if
    * <p>
    * @param		start	The beginning index, inclusive.
    * @param		end		The ending index, exclusive.
    * @return		The new string.
    * @exception	StringIndexOutOfBoundsException	if <code>start</code>
    *				or <code>end</code> are negative or greater than
    *			<code>length()</code>, or <code>start</code> is
    *			greater than <code>end</code>.
    */
   public synchronized String substring(int start, int end) {
      return buffer.substring(start, end);
   }
   
   /**
    * Inserts the string representation of a subarray of the <code>str</code>
    * array argument into this string buffer. The subarray begins at the
    * specified <code>offset</code> and extends <code>len</code> characters.
    * The characters of the subarray are inserted into this string buffer at
    * the position indicated by <code>index</code>. The length of this
    * <code>ReuseStringBuffer</code> increases by <code>len</code> characters.
    * <p>
    * @param		index	position at which to insert subarray.
    * @param		str		A character array.
    * @param		offset	the index of the first character in subarray to
    *			to be inserted.
    * @param		len		the number of characters in the subarray to
    *			to be inserted.
    * @return		a reference to this <code>ReuseStringBuffer</code> object.
    * @exception	StringIndexOutOfBoundsException	if <code>index</code>
    *				is negative or greater than <code>length()</code>, or
    *			<code>offset</code> or <code>len</code> are negative, or
    *			<code>(offset+len)</code> is greater than
    *			<code>str.length</code>.
    */
   public synchronized ReuseStringBuffer insert(int index, char[] str, int offset, int len) {
      buffer.insert(index, str, offset, len);
      return this;
   }
   
   /**
    * Inserts the string representation of the <code>Object</code>
    * argument into this string buffer.
    * <p>
    * The second argument is converted to a string as if by the method
    * <code>String.valueOf</code>, and the characters of that
    * string are then inserted into this string buffer at the indicated
    * offset.
    * <p>
    * The offset argument must be greater than or equal to
    * <code>0</code>, and less than or equal to the length of this
    * string buffer.
    *
    * @param		offset	the offset.
    * @param		obj		an <code>Object</code>.
    * @return		a reference to this <code>ReuseStringBuffer</code> object.
    * @exception	StringIndexOutOfBoundsException	if the offset is invalid.
    * @see			java.lang.String#valueOf(java.lang.Object)
    * @see			#insert(int, java.lang.String)
    * @see			#length()
    */
   public synchronized ReuseStringBuffer insert(int offset, Object obj) {
      buffer.insert(offset, obj);
      return this;
   }
   
   /**
    * Inserts the string into this string buffer.
    * <p>
    * The characters of the <code>String</code> argument are inserted, in
    * order, into this string buffer at the indicated offset, moving up any
    * characters originally above that position and increasing the length
    * of this string buffer by the length of the argument. If
    * <code>str</code> is <code>null</code>, then the four characters
    * <code>"null"</code> are inserted into this string buffer.
    * <p>
    * The character at index <i>k</i> in the new character sequence is
    * equal to:
    * <ul>
    * <li>the character at index <i>k</i> in the old character sequence, if
    * <i>k</i> is less than <code>offset</code>
    * <li>the character at index <i>k</i><code>-offset</code> in the
    * argument <code>str</code>, if <i>k</i> is not less than
    * <code>offset</code> but is less than <code>offset+str.length()</code>
    * <li>the character at index <i>k</i><code>-str.length()</code> in the
    * old character sequence, if <i>k</i> is not less than
    * <code>offset+str.length()</code>
    * </ul><p>
    * The offset argument must be greater than or equal to
    * <code>0</code>, and less than or equal to the length of this
    * string buffer.
    * <p>
    * @param		offset	the offset.
    * @param		str		a string.
    * @return		a reference to this <code>ReuseStringBuffer</code> object.
    * @exception	StringIndexOutOfBoundsException	if the offset is invalid.
    * @see			#length()
    */
   public synchronized ReuseStringBuffer insert(int offset, String str) {
      buffer.insert(offset, str);
      return this;
   }
   
   /**
    * Inserts the string representation of the <code>char</code> array
    * argument into this string buffer.
    * <p>
    * The characters of the array argument are inserted into the
    * contents of this string buffer at the position indicated by
    * <code>offset</code>. The length of this string buffer increases by
    * the length of the argument.
    * <p>
    * The overall effect is exactly as if the argument were converted to
    * a string by the method {@link String#valueOf(char[])} and the
    * characters of that string were then
    * {@link #insert(int,String) inserted} into this
    * <code>ReuseStringBuffer</code>	object at the position indicated by
    * <code>offset</code>.
    * <p>
    * @param		offset	the offset.
    * @param		str		a character array.
    * @return		a reference to this <code>ReuseStringBuffer</code> object.
    * @exception	StringIndexOutOfBoundsException	if the offset is invalid.
    */
   public synchronized ReuseStringBuffer insert(int offset, char[] str) {
      buffer.insert(offset, str);
      return this;
   }
   
   /**
    * Inserts the string representation of the <code>boolean</code>
    * argument into this string buffer.
    * <p>
    * The second argument is converted to a string as if by the method
    * <code>String.valueOf</code>, and the characters of that
    * string are then inserted into this string buffer at the indicated
    * offset.
    * <p>
    * The offset argument must be greater than or equal to
    * <code>0</code>, and less than or equal to the length of this
    * string buffer.
    * <p>
    * @param		offset	the offset.
    * @param		b			a <code>boolean</code>.
    * @return		a reference to this <code>ReuseStringBuffer</code> object.
    * @exception	StringIndexOutOfBoundsException	if the offset is invalid.
    * @see			java.lang.String#valueOf(boolean)
    * @see			#insert(int, java.lang.String)
    * @see			#length()
    */
   public ReuseStringBuffer insert(int offset, boolean b) {
      buffer.insert(offset, b);
      return this;
   }
   
   /**
    * Inserts the string representation of the <code>char</code>
    * argument into this string buffer.
    * <p>
    * The second argument is inserted into the contents of this string
    * buffer at the position indicated by <code>offset</code>. The length
    * of this string buffer increases by one.
    * <p>
    * The overall effect is exactly as if the argument were converted to
    * a string by the method {@link String#valueOf(char)} and the character
    * in that string were then {@link #insert(int, String) inserted} into
    * this <code>ReuseStringBuffer</code> object at the position indicated by
    * <code>offset</code>.
    * <p>
    * The offset argument must be greater than or equal to
    * <code>0</code>, and less than or equal to the length of this
    * string buffer.
    * <p>
    * @param		offset	the offset.
    * @param		c			a <code>char</code>.
    * @return		a reference to this <code>ReuseStringBuffer</code> object.
    * @exception	IndexOutOfBoundsException	if the offset is invalid.
    * @see			#length()
    */
   public synchronized ReuseStringBuffer insert(int offset, char c) {
      buffer.insert(offset, c);
      return this;
   }
   
   /**
    * Inserts the string representation of the second <code>int</code>
    * argument into this string buffer.
    * <p>
    * The second argument is converted to a string as if by the method
    * <code>String.valueOf</code>, and the characters of that
    * string are then inserted into this string buffer at the indicated
    * offset.
    * <p>
    * The offset argument must be greater than or equal to
    * <code>0</code>, and less than or equal to the length of this
    * string buffer.
    * <p>
    * @param		offset	the offset.
    * @param		i			an <code>int</code>.
    * @return		a reference to this <code>ReuseStringBuffer</code> object.
    * @exception	StringIndexOutOfBoundsException	if the offset is invalid.
    * @see			java.lang.String#valueOf(int)
    * @see			#insert(int, java.lang.String)
    * @see			#length()
    */
   public ReuseStringBuffer insert(int offset, int i) {
      buffer.insert(offset, i);
      return this;
   }
   
   /**
    * Inserts the string representation of the <code>long</code>
    * argument into this string buffer.
    * <p>
    * The second argument is converted to a string as if by the method
    * <code>String.valueOf</code>, and the characters of that
    * string are then inserted into this string buffer at the position
    * indicated by <code>offset</code>.
    * <p>
    * The offset argument must be greater than or equal to
    * <code>0</code>, and less than or equal to the length of this
    * string buffer.
    * <p>
    * @param		offset	the offset.
    * @param		l			a <code>long</code>.
    * @return		a reference to this <code>ReuseStringBuffer</code> object.
    * @exception	StringIndexOutOfBoundsException	if the offset is invalid.
    * @see			java.lang.String#valueOf(long)
    * @see			#insert(int, java.lang.String)
    * @see			#length()
    */
   public ReuseStringBuffer insert(int offset, long l) {
      buffer.insert(offset, l);
      return this;
   }
   
   /**
    * Inserts the string representation of the <code>float</code>
    * argument into this string buffer.
    * <p>
    * The second argument is converted to a string as if by the method
    * <code>String.valueOf</code>, and the characters of that
    * string are then inserted into this string buffer at the indicated
    * offset.
    * <p>
    * The offset argument must be greater than or equal to
    * <code>0</code>, and less than or equal to the length of this
    * string buffer.
    * <p>
    * @param		offset	the offset.
    * @param		f			a <code>float</code>.
    * @return		a reference to this <code>ReuseStringBuffer</code> object.
    * @exception	StringIndexOutOfBoundsException	if the offset is invalid.
    * @see			java.lang.String#valueOf(float)
    * @see			#insert(int, java.lang.String)
    * @see			#length()
    */
   public ReuseStringBuffer insert(int offset, float f) {
      buffer.insert(offset, f);
      return this;
   }
   
   /**
    * Inserts the string representation of the <code>double</code>
    * argument into this string buffer.
    * <p>
    * The second argument is converted to a string as if by the method
    * <code>String.valueOf</code>, and the characters of that
    * string are then inserted into this string buffer at the indicated
    * offset.
    * <p>
    * The offset argument must be greater than or equal to
    * <code>0</code>, and less than or equal to the length of this
    * string buffer.
    * <p>
    * @param		offset	the offset.
    * @param		d			a <code>double</code>.
    * @return		a reference to this <code>ReuseStringBuffer</code> object.
    * @exception	StringIndexOutOfBoundsException	if the offset is invalid.
    * @see			java.lang.String#valueOf(double)
    * @see			#insert(int, java.lang.String)
    * @see			#length()
    */
   public ReuseStringBuffer insert(int offset, double d) {
      buffer.insert(offset, d);
      return this;
   }
   
   /**
    * Returns the index within this string of the first occurrence of the
    * specified substring. The integer returned is the smallest value
    * <i>k</i> such that:
    * <blockquote><pre>
    * this.toString().startsWith(str, <i>k</i>)
    * </pre></blockquote>
    * is <code>true</code>.
    *
    * @param   str   any string.
    * @return  if the string argument occurs as a substring within this
    *          object, then the index of the first character of the first
    *          such substring is returned; if it does not occur as a
    *          substring, <code>-1</code> is returned.
    * @exception java.lang.NullPointerException if <code>str</code> is
    *          <code>null</code>.
    * @since   1.4
    */
   public int indexOf(String str) {
       return buffer.indexOf(str);
   }

   /**
    * Returns the index within this string of the first occurrence of the
    * specified substring, starting at the specified index.  The integer
    * returned is the smallest value <tt>k</tt> for which:
    * <blockquote><pre>
    *     k >= Math.min(fromIndex, str.length()) &&
    *                   this.toString().startsWith(str, k)
    * </pre></blockquote>
    * If no such value of <i>k</i> exists, then -1 is returned.
    *
    * @param   str         the substring for which to search.
    * @param   fromIndex   the index from which to start the search.
    * @return  the index within this string of the first occurrence of the
    *          specified substring, starting at the specified index.
    * @exception java.lang.NullPointerException if <code>str</code> is
    *            <code>null</code>.
    * @since   1.4
    */
   public synchronized int indexOf(String str, int fromIndex) {
       return buffer.indexOf(str, fromIndex);
   }

   /**
    * Returns the index within this string of the rightmost occurrence
    * of the specified substring.  The rightmost empty string "" is
    * considered to occur at the index value <code>this.length()</code>.
    * The returned index is the largest value <i>k</i> such that
    * <blockquote><pre>
    * this.toString().startsWith(str, k)
    * </pre></blockquote>
    * is true.
    *
    * @param   str   the substring to search for.
    * @return  if the string argument occurs one or more times as a substring
    *          within this object, then the index of the first character of
    *          the last such substring is returned. If it does not occur as
    *          a substring, <code>-1</code> is returned.
    * @exception java.lang.NullPointerException  if <code>str</code> is
    *          <code>null</code>.
    * @since   1.4
    */
   public synchronized int lastIndexOf(String str) {
       return buffer.lastIndexOf(str);
   }

   /**
    * Returns the index within this string of the last occurrence of the
    * specified substring. The integer returned is the largest value <i>k</i>
    * such that:
    * <blockquote><pre>
    *     k <= Math.min(fromIndex, str.length()) &&
    *                   this.toString().startsWith(str, k)
    * </pre></blockquote>
    * If no such value of <i>k</i> exists, then -1 is returned.
    *
    * @param   str         the substring to search for.
    * @param   fromIndex   the index to start the search from.
    * @return  the index within this string of the last occurrence of the
    *          specified substring.
    * @exception java.lang.NullPointerException if <code>str</code> is
    *          <code>null</code>.
    * @since   1.4
    */
   public synchronized int lastIndexOf(String str, int fromIndex) {
       return buffer.lastIndexOf(str, fromIndex);
   }
   
   /*
    * The character sequence contained in this string buffer is
    * replaced by the reverse of the sequence.
    * <p>
    * Let <i>n</i> be the length of the old character sequence, the one
    * contained in the string buffer just prior to execution of the
    * <code>reverse</code> method. Then the character at index <i>k</i> in
    * the new character sequence is equal to the character at index
    * <i>n-k-1</i> in the old character sequence.
    *
    * @return	a reference to this <code>ReuseStringBuffer</code> object.
    */
   public synchronized ReuseStringBuffer reverse() {
      buffer.reverse();
      return this;
   }
   
   /* (non-Javadoc)
    * @see java.lang.Object#toString()
    */
   public String toString() {
      return buffer.toString();
   }
   
   
}
