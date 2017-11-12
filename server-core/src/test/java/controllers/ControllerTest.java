package controllers;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.*;

public class ControllerTest {

    @Mock
    RestTemplate restTemplate;

    @Mock
    String str;

    @InjectMocks
    Controller mockedController;

    @Test(expected = NullPointerException.class)
    public void relayIntegration() throws Exception {
        String result = new Controller(new RestTemplate()).relay(str);
        assertNotNull(result);
    }

    @Test
    public void relayIntegration2() throws Exception {
        str = "[\"{\\\"speed\\\":0.31774687476689245,\\\"latitude\\\":0.7063388373366248,\\\"longitude\\\":0.2042980547685368,\\\"azimuth\\\":0.972731846318812}\"]";
// ["{\"speed\":0.31774687476689245,\"latitude\":0.7063388373366248,\"longitude\":0.2042980547685368,\"azimuth\":0.972731846318812}"]
        String result = new Controller(new RestTemplate()).relay(str);
        assertNotNull(result);
        assertEquals("true",result);
    }

}