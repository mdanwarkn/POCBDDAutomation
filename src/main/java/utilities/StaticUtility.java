package utilities;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class StaticUtility {


    public static boolean IsListSortedInAscending(List<Integer> list){
        int i;
        int j;
        for( i = 0 , j = 1; j < list.size() && list.get(i)<=list.get(j) ; i++ , j++);
        return j==list.size();
    }
    public static boolean IsFirstElementInTheListIsLowest(List<Integer> list){
        int i;
        int j;
        for( i = 0 , j = 1; j < list.size() && list.get(i)<=list.get(j) ; j++);
        return j==list.size();
    }

    public static String timestamp() {
        return new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
    }
    
    public static DecimalFormat getDecimalFormatForIndianCurrency(int noOfDecimalDigits) {
    	Locale locale = new Locale("en","IN");
	 	DecimalFormat decimalFormat = (DecimalFormat) DecimalFormat.getCurrencyInstance(locale);
	    DecimalFormatSymbols dfs = DecimalFormatSymbols.getInstance(locale);
	    dfs.setCurrencySymbol("\u20B9");
	    decimalFormat.setDecimalFormatSymbols(dfs);
	    decimalFormat.setMaximumFractionDigits(noOfDecimalDigits);
	    return decimalFormat;
    }
    
    public static String getValueAssociatedWithTheColumn(Map<String,String> inputRow,String parameter) {
    	if(parameter.startsWith("<") && parameter.endsWith(">")) {
    		return inputRow.get(parameter.substring(1, parameter.length()-1));
    	}else {
    		return parameter;
    	} 
    }
    
    public static String formatNullToEmpty(String str){
        return str==null?"":str;
    }
}
