package aspects;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TimerTest {

    @Autowired
    Timer timer;

    @Test
    public void runner() throws InterruptedException {
        //When&&Then
        timer.runner();
        timer.runner(1000L);
    }
}