package SPRN;

import java.util.HashMap;
import java.util.Map;

public enum OperatorType {
    SUM("+"),
    SUBTRACT("-"),
    REMAINDER("%"),
    MULTIPLY("*"),
    DIVIDE("/");

    private String operatorType;

    OperatorType(String operatorType) {
        this.operatorType = operatorType;
    }

    public String getOperator() {
        return operatorType;
    }

    private static final Map<String, OperatorType> lookup = new HashMap<>();

    static {
        for (OperatorType type : OperatorType.values()) {
            lookup.put(type.getOperator(), type);
        }
    }

    public static OperatorType get(String operatorType) {
        return lookup.get(operatorType);
    }
}
