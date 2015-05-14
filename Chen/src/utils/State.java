package utils;

import java.util.LinkedList;

public interface State{
	//Get all states that are reachable from this state
    public LinkedList<State> getOutGoingStates();
    //Get the evaluation of this state
    public Value getHeristic();
    //Get the cost already happened
    public Value getCost();
    //Judge if this state has reached the goal
    public boolean isGoal();
    //create a plan to reach current state
    public Plan createPlan();
}