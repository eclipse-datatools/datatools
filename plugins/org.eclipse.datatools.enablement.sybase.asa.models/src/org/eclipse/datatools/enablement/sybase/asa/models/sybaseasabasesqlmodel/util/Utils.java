package org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.TypeOfDefault;

public class Utils {

	static public boolean isLiteralDefault(TypeOfDefault defaultType, String defaultValue)
	{
		boolean result = false;
		
		if (defaultType.equals(TypeOfDefault.USER_DEFINED_LITERAL)) 
		{
			result = parseLiteralDefault(defaultValue);
		}
		
		return result;
	}
	
	static private boolean parseLiteralDefault(String defaultValue)
	{
		Pattern p = Pattern.compile("'.*'", Pattern.DOTALL);
		Matcher m = p.matcher(defaultValue.trim());
		return m.matches();
	}
	
	static public int getDefaultGlobalIncrementPartitionSize(TypeOfDefault defaultType, String defaultValue)
	{
		int partSize = -1;
		
		if(defaultType.equals(TypeOfDefault.SYSTEM_DEFINED_LITERAL))
		{
			partSize = parseGlobalIncrementPartitionSizeDefault(defaultValue);
		}
		
		return partSize;
	}
	
	static private int parseGlobalIncrementPartitionSizeDefault(String defaultValue)
	{
		int result = -1;
		Pattern p = Pattern.compile(
				"global\\s+autoincrement\\s*\\(\\s*(\\d+)\\s*\\)",
				Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
		Matcher m = p.matcher(defaultValue.trim());
		if(m.matches())
		{
			String strSize = m.group(1);
			result = Integer.parseInt(strSize);
		}
		return result;
	}
	
}
