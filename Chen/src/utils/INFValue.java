package utils;

public class INFValue implements Value{
	private INFValue(){}
	private static INFValue instance=null;
	public static INFValue getInstance(){
		if(instance==null){
			instance=new INFValue();
		}
		return instance;
	}
	@Override
    public int compareTo(Value another){
		return 1;
	}
}