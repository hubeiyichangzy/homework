package utils;

public class OtherUtils {
    private OtherUtils(){}
    //To represent a time on clock by minute offset from 00:00
    public static String getTimePresentationByMinute(int min){
    	int hour=min/60;
    	int minute=min%60;
    	String ret=hour+":";
    	if(minute<10){
    		ret+="0";
    	}
    	return ret+minute;
    }
    
    public static int getTimeStampByString(String str){
    	int index=str.indexOf(':');
        int hour=Integer.valueOf(str.substring(0,index));
        int minute=Integer.valueOf(str.substring(index+1));
        return hour*60+minute;
    }
}