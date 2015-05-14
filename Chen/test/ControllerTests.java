import meeting.Controller;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.security.InvalidParameterException;

public class ControllerTests {
    @Test(expected = InvalidParameterException.class)
    public void shouldReturnErrorTopContainsNumber() throws IOException, InvalidParameterException {
        String canonicalPath = new File(".").getCanonicalPath();
        String pathProperty = canonicalPath + "/out/production/Chen/test.properties";

        Controller controller = new Controller();
        controller.init(pathProperty);
        controller.run();
    }
}