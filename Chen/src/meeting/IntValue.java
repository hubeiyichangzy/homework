package meeting;

import utils.INFValue;
import utils.Value;

public class IntValue implements Value {
    private int value;
    public IntValue(int value){
    	this.value=value;
    }
    @Override
    public int compareTo(Value another){
    	if(another instanceof INFValue){
    		return -1;
    	}
        return this.value-((IntValue)another).value;
    }
}