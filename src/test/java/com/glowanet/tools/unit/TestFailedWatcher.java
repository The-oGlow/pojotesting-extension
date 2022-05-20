package com.glowanet.tools.unit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

public class TestFailedWatcher extends TestWatcher {

    // static fields
    protected static final Logger LOGGER = LogManager.getLogger();
    // end - static fields
// fields
    private                Object parameterName;
    private                Object parameterValue;
// end - fields

    // constructors
    public TestFailedWatcher(Object parameterName, Object parameterValue) {
        this.parameterName = parameterName;
        this.parameterValue = parameterValue;
    }
// end - constructors

    public void setParameterName(Object parameterName) {
        this.parameterName = parameterName;
    }

    public void setParameterValue(Object parameterValue) {
        this.parameterValue = parameterValue;
    }

    // methods
    @Override
    protected void failed(Throwable e, Description description) {
        // fields
        LOGGER.info("The test failed with {}={}", parameterName, parameterValue);
    }
}
