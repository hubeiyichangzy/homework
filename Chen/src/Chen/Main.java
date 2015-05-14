package Chen;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String canonicalPath = new File(".").getCanonicalPath();
        String pathProperty = canonicalPath + "/out/production/Chen/meetingproperties.properties";
        System.out.println(pathProperty);

        meeting.Controller controller=new meeting.Controller();
        controller.init(pathProperty);
        controller.run();
    }
}
