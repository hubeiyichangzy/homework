package meeting;

import java.io.*;
import java.util.LinkedList;
import java.util.Properties;

public class Controller {
    public static void main(String [] args) throws IOException{
    	if(args.length<1){
    		System.out.println("Use error, please provide the location of properties file");
    		System.exit(1);
    	}
    	Controller controller=new Controller();
    	controller.init(args[0]);
    	controller.run();
    }
    
    private Session session;
    private LinkedList<MeetingItem> todoList;
    private EventScheduler scheduler;
    
    public void init(String pathProperty) throws IOException{
    	//load property
        FileInputStream fis=new FileInputStream(pathProperty);
        Properties properties=new Properties();
        properties.load(fis);
        fis.close();
        //read session info
        session= Session.createFromString(properties.getProperty("sessions"));
        String descFileName=properties.getProperty("desc.file");
        //read task info
        String canonicalPath = new File(".").getCanonicalPath();
        String descFileFullPath = canonicalPath + "/out/production/Chen/" + descFileName;
        BufferedReader br=new BufferedReader(new FileReader(new File(descFileFullPath)));
        todoList=new LinkedList<MeetingItem>();
        String line=null;
        int serial=0;
        while((line=br.readLine())!=null){
        	todoList.add(MeetingItem.createMeetingItem(serial, line));
        	serial++;
        }
        br.close();

        String schedulerPattern=properties.getProperty("scheduler","default");
        scheduler=createScheduler(schedulerPattern);
    }
    
    //could be used as strategy pattern, if other scheduler present in the future, could handily incorporate 
    public EventScheduler createScheduler(String description){
    	return new MeetingScheduler(session,todoList);
    }
    
    public void run(){
    	scheduler.schedule();
    }
}