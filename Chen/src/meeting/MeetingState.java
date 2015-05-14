package meeting;

import utils.Plan;
import utils.State;
import utils.Value;

import java.util.Iterator;
import java.util.LinkedList;

public class MeetingState implements State{
	//the lists of done items and items to be done
    private LinkedList<MeetingDone> doneItem;
    private LinkedList<MeetingItem> todoItem;
    //save the cost spent till now
    private int cost;
    //performance concern, to store the total time needed to do all the items remain to be done
    private int todoLeft=-1;
    //denotes the current session and used offset of it
    private Session session;
    private int offset;
    
    public static class MeetingDone{
    	public Session session;
    	public int offset;
    	public MeetingItem item;
    	public MeetingDone(Session session, int offset, MeetingItem item){
    		this.session=session;
    		this.offset=offset;
    		this.item=item;
    	}
    }
    
    @Override 
    public boolean equals(Object another){
        if(!(another instanceof MeetingState)){
        	return false;
        }
    	if(another==this){
    		return true;
    	}
    	if(((MeetingState)another).todoItem.containsAll(todoItem)&&todoItem.containsAll(((MeetingState)another).todoItem)){
    		return true;
    	}
    	return false;
    }
    
    public MeetingState(LinkedList<MeetingDone> doneItem, 
    		            LinkedList<MeetingItem> todoItem,
    		            Session session,
    		            int offset,
    		            int cost){
    	this.session=session;
    	this.offset=offset;
    	this.doneItem=doneItem;
    	this.todoItem=todoItem;
    	this.cost=cost;   	
    }
    
    private static MeetingState createNewState(MeetingState original, MeetingItem doneAnother){
        int sessionLeftLen=original.session.len()-original.offset;
        int require=doneAnother.getDuration();
        int nwOffset;
        int nwCost;
        Session nwSession;
        //same session
        if(sessionLeftLen>=require){
        	nwOffset=original.offset+require;
        	nwSession=original.session;
        	nwCost=original.cost+require;
        }else{
        	//find next possible session
        	nwCost=sessionLeftLen;
        	nwSession=original.session.next();
        	Session loopDetect=nwSession;
        	boolean found=false;
        	do{
        	    if(nwSession.len()>=require){
        	    	found=true;
        	    	break;
        	    }
        	    nwCost+=nwSession.len();
        	}while(!nwSession.equals(loopDetect));
        	assert(found);
        	nwCost+=require+original.cost;
        	nwOffset=require;
        }
    	LinkedList<MeetingDone> nwDoneItem=new LinkedList<MeetingDone>();
    	LinkedList<MeetingItem> nwTodoItem=new LinkedList<MeetingItem>();
    	nwDoneItem.addAll(original.doneItem);
    	nwTodoItem.addAll(original.todoItem);
        nwDoneItem.add(new MeetingDone(nwSession,nwOffset-require,doneAnother));
        nwTodoItem.remove(doneAnother);
        MeetingState ret=new MeetingState(nwDoneItem,nwTodoItem,nwSession,nwOffset,nwCost);
        if(original.todoLeft!=-1){
        	ret.todoLeft=original.todoLeft-doneAnother.getDuration();
        }
        return ret;
    }
    
    public LinkedList<State> getOutGoingStates(){
        LinkedList<State> ret=new LinkedList<State>();
        for(Iterator<MeetingItem> it=todoItem.iterator();it.hasNext();){
        	MeetingItem fetch=it.next();
        	MeetingState nw=createNewState(this,fetch);
        	ret.add(nw);
        }
        return ret;
    }
    
    public Value getCost(){
    	return new IntValue(this.cost);
    }
    
    public Value getHeristic(){
    	if(todoLeft==-1){
    		int sum=0;
    		for(Iterator<MeetingItem> it=todoItem.iterator();it.hasNext();){
    			MeetingItem fetch=it.next();
    			sum+=fetch.getDuration();
    		}
    		todoLeft=sum;
    	}
    	return new IntValue(todoLeft);
    }
    
    public boolean isGoal(){
    	return todoItem.isEmpty();
    }
    
    public Plan createPlan(){
    	return new MeetingPlan(this.doneItem);
    }
}