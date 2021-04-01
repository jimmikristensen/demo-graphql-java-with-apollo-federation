package deck;

import graphql.ExecutionResult;
import graphql.execution.instrumentation.InstrumentationContext;
import graphql.execution.instrumentation.SimpleInstrumentation;
import graphql.execution.instrumentation.parameters.InstrumentationExecutionParameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomInstrumentation extends SimpleInstrumentation {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomInstrumentation.class);

    @Override
    public InstrumentationContext<ExecutionResult> beginExecution(InstrumentationExecutionParameters parameters) {
        LOGGER.debug(parameters.getExecutionInput().toString());
        return super.beginExecution(parameters);
    }

}
