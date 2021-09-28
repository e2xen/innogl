package innogl.ru.application.config.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class AuthorizedActionAspect {

    @Around("@annotation(innogl.ru.application.annotation.AuthorizedAction)")
    public Object trace(ProceedingJoinPoint joinPoint) throws Throwable {
        Arrays.stream(joinPoint.getArgs());

        return joinPoint.proceed();
    }
}
