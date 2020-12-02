package SPRN.utils;

import SPRN.OperationErrorType;

import java.util.List;

public class Validation {

    public static void isValidList(List list) {
        if (list.size() <= 1) {
            throw new IndexOutOfBoundsException();
        }
    }

    public static void isValidInteger(Double value) {
        if (value > Integer.MAX_VALUE) {
            throw new ArithmeticException(OperationErrorType.INTEGER_OVERFLOW.getOperationErrorType());
        }

        if (value < Integer.MIN_VALUE) {
            throw new ArithmeticException(OperationErrorType.INTEGER_UNDERFLOW.getOperationErrorType());
        }
    }

}
