package SPRN;

import java.util.HashMap;
import java.util.Map;

public enum OperationErrorType {
    INTEGER_OVERFLOW("Integer overflow"),
    STACK_UNDERFLOW("Stack underflow."),
    INTEGER_UNDERFLOW("Integer underflow"),
    DIVIDE_BY_ZERO("Divide by 0."),
    INT_MIN_VALUE_DIVIDED_BY_MINUS_ONE("Min value divided by minus one");

    private String operatorErrorType;

    OperationErrorType(String operationErrorMessage) {
        this.operatorErrorType = operationErrorMessage;
    }

    public String getOperationErrorType() {
        return operatorErrorType;
    }

    private static final Map<String, OperationErrorType> lookup = new HashMap<>();

    static {
        for (OperationErrorType type : OperationErrorType.values()) {
            lookup.put(type.getOperationErrorType(), type);
        }
    }

    public static OperationErrorType get(String operationErrorType) {
        return lookup.get(operationErrorType);
    }
}
