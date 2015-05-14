package meeting;

import utils.OtherUtils;

//immutable class
public final class Session {
	public static final String INTERVAL_SPLITER=";";
	public static final String DUR_SPLITER="-";
	private final int seq;
    private final int start;
    private final int end;
    private Session next=null;
    public Session(int seq,int start,int end){
    	this.seq=seq;
    	this.start=start;
    	this.end=end;
    }
    public final int getStart(){
    	return start;
    }
    public final int getEnd(){
    	return end;
    }
    public final int getSeq(){
    	return seq;
    }
    public final Session next(){
    	return next;
    }
    private final void setNext(Session next){
    	this.next=next;
    }
    
    public final int len(){
    	return end-start;
    }
    
    public static final Session createFromString(String sessionStr){
    	String [] intervals=sessionStr.split(INTERVAL_SPLITER);
        boolean isFirst=true;
        Session first=null;
        Session last=null;
        int seqNow=1;
    	for(int i=0;i<intervals.length;i++){
    	    int index=intervals[i].indexOf(DUR_SPLITER);
    	    int start=OtherUtils.getTimeStampByString(intervals[i].substring(0,index));
    	    int end=OtherUtils.getTimeStampByString(intervals[i].substring(index+1));
            Session nw=new Session(seqNow,start,end);
            seqNow++;
            if(isFirst){
            	isFirst=false;
            	first=nw;
            }
            else{
            	last.setNext(nw);
            }
            last=nw;
    	}
    	last.setNext(first);
    	return first;
    }
}