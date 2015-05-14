package utils;

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class AStarFramework {
    private State init=null;
    private Value stopSign= INFValue.getInstance();
    
    public AStarFramework(State init){
        this.init=init;
    }
    
    public AStarFramework(State init, Value stopSign){
    	this(init);
    	this.stopSign=stopSign;
    }
    
    public final Plan plan(){
    	//little root heap, with lowest heristic value at first
    	PriorityQueue<State> queue=new PriorityQueue<State>(11,new Comparator<State>(){
    		public int compare(State s1, State s2){
    		    Value v1=s1.getHeristic();
    		    Value v2=s2.getHeristic();
    		    return v1.compareTo(v2);
    		}
    	});
    	//take records which states have already been examined, avoid duplicated efforts
    	LinkedList<State> alreadyExamined=new LinkedList<State>();
    	//push the initial state in
    	queue.add(init);
    	while(!queue.isEmpty()){
    		//fetch the least heristic valued state
    		State s=queue.poll();
    		//goal achieved, succeed
    		if(s.isGoal()){
    			return s.createPlan();
    		}
    		//already examined before, at a lower heristic value, then lower cost, no need to examine again
    		if(alreadyExamined.contains(s)){
    			continue;
    		}
    		LinkedList<State> newState=s.getOutGoingStates();
    		for(Iterator<State> it=newState.iterator();it.hasNext();){
    			State nw=it.next();
    			if(alreadyExamined.contains(nw)){
    				//already examined before, at a lower heristic value, drop
    				continue;
    			}
    			Value newValue=nw.getHeristic();
    			if(newValue.compareTo(stopSign)>0){
    				//already reached the boundary, discard
    				continue;
    			}
    			queue.add(nw);
    		}
    		//take record to avoid duplicated efforts
    		alreadyExamined.add(s);
    	}
    	//impossible to reach the goal
    	return null;
    }
}