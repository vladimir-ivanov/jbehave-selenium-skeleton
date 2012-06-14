package com.datasift.fido.stepdefinitions;

import org.jbehave.core.annotations.AfterScenario;
import org.jbehave.core.annotations.BeforeScenario;


public class BeforeAndAfterSteps extends BaseSteps {

    @BeforeScenario
    public void start() {
        selenium.start();
    }

    @AfterScenario
    public void tearDownScenario() throws Exception {
        // perhaps logout here
        selenium.stop();
    }

    @AfterScenario(uponOutcome = AfterScenario.Outcome.FAILURE)
    public void tearDownFailedScenario() throws Exception {
        selenium.stop();
    }
}