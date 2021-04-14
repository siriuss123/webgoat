package org.owasp.webgoat.util;

import java.util.HashMap;
import java.util.Map;

/*******************************************************************************
 * 
 * 
 * This file is part of WebGoat, an Open Web Application Security Project
 * utility. For details, please see http://www.owasp.org/
 * 
 * Copyright (c) 2002 - 2007 Bruce Mayhew
 * 
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc., 59 Temple
 * Place - Suite 330, Boston, MA 02111-1307, USA.
 * 
 * Getting Source ==============
 * 
 * Source for this application is maintained at code.google.com, a repository
 * for free software projects.
 * 
 * For details, please see http://code.google.com/p/webgoat/
 */
public class HtmlEncoder
{

    static Map e2i = new HashMap();

    static Map i2e = new HashMap();

    // html entity list
    private static Object[][] entities = { { "quot", new Integer(34) },
	    { "amp", new Integer(38) },
	    { "lt", new Integer(60) },
	    { "gt", new Integer(62) },
	    { "nbsp", new Integer(160) },
	    { "copy", new Integer(169) },
	    { "reg", new Integer(174) },
	    { "Agrave", new Integer(192) },
	    { "Aacute", new Integer(193) },
	    { "Acirc", new Integer(194) },
	    { "Atilde", new Integer(195) },
	    { "Auml", new Integer(196) },
	    { "Aring", new Integer(197) },
	    { "AElig", new Integer(198) },
	    { "Ccedil", new Integer(199) },
	    { "Egrave", new Integer(200) },
	    { "Eacute", new Integer(201) },
	    { "Ecirc", new Integer(202) },
	    { "Euml", new Integer(203) },
	    { "Igrave", new Integer(204) },
	    { "Iacute", new Integer(205) },
	    { "Icirc", new Integer(206) },
	    { "Iuml", new Integer(207) },
	    { "ETH", new Integer(208) },
	    { "Ntilde", new Integer(209) },
	    { "Ograve", new Integer(210) },
	    { "Oacute", new Integer(211) },
	    { "Ocirc", new Integer(212) },
	    { "Otilde", new Integer(213) },
	    { "Ouml", new Integer(214) },
	    { "Oslash", new Integer(216) },
	    { "Ugrave", new Integer(217) },
	    { "Uacute", new Integer(218) },
	    { "Ucirc", new Integer(219) },
	    { "Uuml", new Integer(220) },
	    { "Yacute", new Integer(221) },
	    { "THORN", new Integer(222) },
	    { "szlig", new Integer(223) },
	    { "agrave", new Integer(224) },
	    { "aacute", new Integer(225) },
	    { "acirc", new Integer(226) },
	    { "atilde", new Integer(227) },
	    { "auml", new Integer(228) },
	    { "aring", new Integer(229) },
	    { "aelig", new Integer(230) },
	    { "ccedil", new Integer(231) },
	    { "egrave", new Integer(232) },
	    { "eacute", new Integer(233) },
	    { "ecirc", new Integer(234) },
	    { "euml", new Integer(235) },
	    { "igrave", new Integer(236) },
	    { "iacute", new Integer(237) },
	    { "icirc", new Integer(238) },
	    { "iuml", new Integer(239) },
	    { "igrave", new Integer(236) },
	    { "iacute", new Integer(237) },
	    { "icirc", new Integer(238) },
	    { "iuml", new Integer(239) },
	    { "eth", new Integer(240) },
	    { "ntilde", new Integer(241) },
	    { "ograve", new Integer(242) },
	    { "oacute", new Integer(243) },
	    { "ocirc", new Integer(244) },
	    { "otilde", new Integer(245) },
	    { "ouml", new Integer(246) },
	    { "oslash", new Integer(248) },
	    { "ugrave", new Integer(249) },
	    { "uacute", new Integer(250) },
	    { "ucirc", new Integer(251) },
	    { "uuml", new Integer(252) },
	    { "yacute", new Integer(253) },
	    { "thorn", new Integer(254) }, 
	    { "yuml", new Integer(255) },
	    { "euro", new Integer(8364) },
    };


    public HtmlEncoder()
    {
	for (int i = 0; i < entities.length; i++)
	    e2i.put(entities[i][0], entities[i][1]);
	for (int i = 0; i < entities.length; i++)
	    i2e.put(entities[i][1], entities[i][0]);
    }


    /**
     *  Turns funky characters into HTML entity equivalents<p>
     *
     *  e.g. <tt>"bread" & "butter"</tt> => <tt>&amp;quot;bread&amp;quot; &amp;amp;
     *  &amp;quot;butter&amp;quot;</tt> . Update: supports nearly all HTML entities, including funky
     *  accents. See the source code for more detail. Adapted from
     *  http://www.purpletech.com/code/src/com/purpletech/util/Utils.java.
     *
     * @param  s1  Description of the Parameter
     * @return     Description of the Return Value
     */
    public static String encode(String s1)
    {
	StringBuffer buf = new StringBuffer();

	int i;
	for (i = 0; i < s1.length(); ++i)
	{
	    char ch = s1.charAt(i);

	    String entity = (String) i2e.get(new Integer((int) ch));

	    if (entity == null)
	    {
		if (((int) ch) > 128)
		{
		    buf.append("&#" + ((int) ch) + ";");
		}
		else
		{
		    buf.append(ch);
		}
	    }
	    else
	    {
		buf.append("&" + entity + ";");
	    }
	}

	return buf.toString();
    }


    /**
     *  Given a string containing entity escapes, returns a string containing the actual Unicode
     *  characters corresponding to the escapes. Adapted from
     *  http://www.purpletech.com/code/src/com/purpletech/util/Utils.java.
     *
     * @param  s1  Description of the Parameter
     * @return     Description of the Return Value
     */
    public static String decode(String s1)
    {
	StringBuffer buf = new StringBuffer();

	int i;
	for (i = 0; i < s1.length(); ++i)
	{
	    char ch = s1.charAt(i);

	    if (ch == '&')
	    {
		int semi = s1.indexOf(';', i + 1);
		if (semi == -1)
		{
		    buf.append(ch);
		    continue;
		}
		String entity = s1.substring(i + 1, semi);
		Integer iso;
		if (entity.charAt(0) == '#')
		{
		    iso = new Integer(entity.substring(1));
		}
		else
		{
		    iso = (Integer) e2i.get(entity);
		}
		if (iso == null)
		{
		    buf.append("&" + entity + ";");
		}
		else
		{
		    buf.append((char) (iso.intValue()));
		}
		i = semi;
	    }
	    else
	    {
		buf.append(ch);
	    }
	}

	return buf.toString();
    }
}
