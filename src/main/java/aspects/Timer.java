package aspects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Timer {
    Long time;

    @Autowired // obiekt zostanie utworzony przy pomocy tego konstruktora time zostanie ustawiony z application.properties.
    public Timer(@Value("${time}") Long time) {
        this.time = time;
    }

    public void runner() throws InterruptedException {
        Thread.sleep(time);
    }

    public Long runner(Long time) throws InterruptedException {
        Thread.sleep(time);
        return time;
    }


    public Long getTime() {
        return time;
    }
}
