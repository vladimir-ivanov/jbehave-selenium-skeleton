package com.datasift.fido.stepdefinitions;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.failures.FailingUponPendingStep;
import org.jbehave.core.junit.JUnitStory;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.CandidateSteps;
import org.jbehave.core.steps.InstanceStepsFactory;

import java.util.Arrays;
import java.util.List;

public class JBehaveStory extends JUnitStory {
    private List<Object> stepDefinitionInstances;

    @Override
    public Configuration configuration() {
        return new MostUsefulConfiguration() {
            {
                useStoryReporterBuilder(new StoryReporterBuilder().withFormats(Format.CONSOLE))
                        .usePendingStepStrategy(new FailingUponPendingStep());
            }
        };
    }

    @Override
    public List<CandidateSteps> candidateSteps() {
        return new InstanceStepsFactory(configuration(), stepDefinitionInstances).createCandidateSteps();
    }

    protected void useSteps(Object... stepDefinitionInstances) {
        this.stepDefinitionInstances = Arrays.asList(stepDefinitionInstances);
    }
}
