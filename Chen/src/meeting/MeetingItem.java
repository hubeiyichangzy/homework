package meeting;

//immutable class
public final class MeetingItem {
	private static class TimePresentation{
		public static final String MINSUFFIX="min";
		public static final String LIGHTENING="lightning";
		public static int getRealMinutes(String timeString){
			if(timeString.endsWith(MINSUFFIX)){
				int index=timeString.indexOf(MINSUFFIX);
				return Integer.parseInt(timeString.substring(0,index));
			}
			if(timeString.equals(LIGHTENING)){
				return 5;
			}
			return 0;
		}
	}
	public static final MeetingItem createMeetingItem(int serial,String str){
		int index=str.lastIndexOf(' ');
		return new MeetingItem(serial,str.substring(0,index), TimePresentation.getRealMinutes(str.substring(index + 1)));
	}
	private final int ID;
    private final String description;
    private final int duration;
    public MeetingItem(int ID, String description, int duration){
    	this.ID=ID;
    	this.description=description;
    	this.duration=duration;
    }
    public final String getDescription(){
    	return description;
    }
    public final int getDuration(){
    	return duration;
    }
    public final int getID(){
    	return ID;
    }
    @Override
    public final boolean equals(Object another){
    	if(another==null){
    		return false;
    	}
    	if(!(another instanceof MeetingItem)){
    		return false;
    	}
    	if(another==this){
    		return true;
    	}
        if(((MeetingItem)another).ID==ID){
        	return true;
        }
    	return false;
    }
}