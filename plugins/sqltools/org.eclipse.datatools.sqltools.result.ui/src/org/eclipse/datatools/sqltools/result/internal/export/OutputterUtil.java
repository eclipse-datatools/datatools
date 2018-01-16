package org.eclipse.datatools.sqltools.result.internal.export;

import java.io.IOException;
import java.io.PrintWriter;

public class OutputterUtil
{
    public static void writeStringData(PrintWriter w, String s) throws IOException
    {
        if (containsNonXMLChar(s))
        {
            w.write("<![CDATA[");
            encodeCDATA(w, s.getBytes("UTF8"));
            w.write("]]>");
        }
        else
        {
            w.write(escapeForXML(s));
        }
    }

    public static  void writeBinaryData(PrintWriter w, Object obj) throws IOException
    {
        if (obj instanceof byte[])
        {
            w.write("<![CDATA[");
            encodeCDATA(w, (byte[]) obj);
            w.write("]]>");
        }
    }

    private static boolean containsNonXMLChar(String s)
    {
        boolean flag = false;
        int j = s.length();
        for (int i = 0; i < j; i++)
        {
            if (isXMLChar(s.charAt(i)))
            {
                continue;
            }
            flag = true;
            break;
        }

        return flag;
    }

    private static boolean isXMLChar(char c)
    {
        return c == '\t' || c == '\n' || c == '\r' || ' ' <= c && c <= '\uD7FF' || '\uE000' <= c && c <= '\uFFFD';
    }

    public static String escapeForXML(String s)
    {
        StringBuffer stringbuffer = new StringBuffer(s.length() + 16);
        int j = s.length();
        for (int i = 0; i < j; i++)
        {
            char c = s.charAt(i);
            if (c == '&')
            {
                stringbuffer.append("&amp;");
            }
            else if (c == '<')
            {
                stringbuffer.append("&lt;");
            }
            else if (c == '>')
            {
                stringbuffer.append("&gt;");
            }
            else if (c == '\'')
            {
                stringbuffer.append("&apos;");
            }
            else if (c == '"')
            {
                stringbuffer.append("&quot;");
            }
            else
            {
                stringbuffer.append(c);
            }
        }

        return stringbuffer.toString();
    }

    public static void encodeCDATA(PrintWriter w, byte[] bytes) throws IOException
    {
        int size = bytes.length;
        for (int i = 0; i < size;)
        {
            int k = (bytes[i] & 0xf0) >> 4;
            w.write(hexTable[k]);
            k = bytes[i] & 0xf;
            w.write(hexTable[k]);
            if (++i % 32 == 0)
            {
                w.println();
            }
        }
    }

    static final char hexTable[] =
                                 {
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
                                 };
}
