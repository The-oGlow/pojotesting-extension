package com.glowanet.tools.unit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

public class TestFailedWatcher extends TestWatcher {

    protected static final Logger LOGGER = LogManager.getLogger();

    private Object parameterName;
    private Object parameterValue;

    public TestFailedWatcher(Object parameterName, Object parameterValue) {
        this.parameterName = parameterName;
        this.parameterValue = parameterValue;
    }

    public void setParameterName(Object parameterName) {
        this.parameterName = parameterName;
    }

    public void setParameterValue(Object parameterValue) {
        this.parameterValue = parameterValue;
    }

    @Override
    protected void failed(Throwable e, Description description) {

        LOGGER.info("The test failed with {}={}", parameterName, parameterValue);
    }
}
