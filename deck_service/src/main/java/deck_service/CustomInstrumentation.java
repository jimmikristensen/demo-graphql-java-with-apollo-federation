package deck_service;

import graphql.ExecutionResult;
import graphql.execution.instrumentation.InstrumentationContext;
import graphql.execution.instrumentation.SimpleInstrumentation;
import graphql.execution.instrumentation.parameters.InstrumentationExecutionParameters;

public class CustomInstrumentation extends SimpleInstrumentation {

    @Override
    public InstrumentationContext<ExecutionResult> beginExecution(InstrumentationExecutionParameters parameters) {
        System.out.println(parameters.getExecutionInput());
        return super.beginExecution(parameters);
    }

}
