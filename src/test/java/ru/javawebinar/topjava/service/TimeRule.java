package ru.javawebinar.topjava.service;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.repository.mock.InMemoryUserRepositoryImpl;

import java.time.LocalTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;

/**
 * Created by Денис on 04.10.2016.
 */
public class TimeRule implements TestRule {
    private static final Logger LOG = LoggerFactory.getLogger(InMemoryUserRepositoryImpl.class);

    @Override
    public Statement apply(Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                LocalTime startTime = LocalTime.now();
                base.evaluate();
                long time = ChronoUnit.MILLIS.between(startTime, LocalTime.now());
                LOG.info(description.getMethodName()+" - the duration of the test was "+ time + " millis");
            }
        };
    }
}
