package aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Watcher {

    public static final Logger LOGGER = LoggerFactory.getLogger(Watcher.class);

    @Before("execution(void aspects.Timer.runner())")
    public void logEvent() {
        LOGGER.info("Logging Event");
    }

    @Before("execution(Long aspects.Timer.runner(Long))" +
            "&& args(time) && target(object)")
    public void logEvent(Long time, Object object) {
        LOGGER.info("Logging Event time " + time + " Object " + object.getClass().getSimpleName());
    }

    @Around("execution(Long aspects.Timer.runner(Long))" +
            "&& args(time) && target(object)")
    public Object measureTime(final ProceedingJoinPoint proceedingJoinPoint, Long time, Object object) throws Throwable {
        Object result;
        try {
            long begin = System.currentTimeMillis();
            result = proceedingJoinPoint.proceed();
            long end = System.currentTimeMillis();
            LOGGER.info("Time consumed: " + (end - begin) + "[ms]" + " real time from application.properties "
                    + time + " Aspect for " + object.getClass().getSimpleName());
        } catch (Throwable throwable) {
            LOGGER.error(throwable.getMessage());
            throw throwable;
        }
        return result;
    }
}
