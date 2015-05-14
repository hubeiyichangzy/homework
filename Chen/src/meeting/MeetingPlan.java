package meeting;

import utils.OtherUtils;
import utils.Plan;

import java.util.Iterator;
import java.util.LinkedList;

public class MeetingPlan implements Plan{
	LinkedList<MeetingState.MeetingDone> seq;
	public MeetingPlan(LinkedList<MeetingState.MeetingDone> seq){
		this.seq=seq;
	}
    public String handOver(){
    	StringBuffer sb=new StringBuffer();
    	int prevSessionSeq=-1;
    	for(Iterator<MeetingState.MeetingDone> it=seq.iterator();it.hasNext();){
    		MeetingState.MeetingDone md=it.next();
    		if(md.session.getSeq()!=prevSessionSeq){
    			prevSessionSeq=md.session.getSeq();
    			sb.append("Track "+prevSessionSeq+":");
    			sb.append("\n");
    		}
            sb.append(md.item.getDescription());
            sb.append(" ");
            int timeStamp=md.session.getStart()+md.offset;
            sb.append(OtherUtils.getTimePresentationByMinute(timeStamp));
            sb.append("\n");
    	}
    	return sb.toString();
    }
}