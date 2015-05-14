package meeting;

import utils.AStarFramework;
import utils.Plan;

import java.util.LinkedList;

public class MeetingScheduler implements EventScheduler{
	private Session beginSession;
	private LinkedList<MeetingItem> todo;
    public MeetingScheduler(Session beginSession, LinkedList<MeetingItem> todo){
    	this.beginSession=beginSession;
    	this.todo=todo;
    }
    public void schedule(){
    	MeetingState initState=new MeetingState(new LinkedList<MeetingState.MeetingDone>(),todo,beginSession,0,0);
    	AStarFramework asfw=new AStarFramework(initState);
    	Plan plan=asfw.plan();
    	System.out.println(plan.handOver());
    }
}