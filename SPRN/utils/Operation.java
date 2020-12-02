package SPRN.utils;

import SPRN.OperationErrorType;

public class Operation {

    public static int safeAdd(int intA, int intB) {

        Double doubleOfIntA =  Double.valueOf(intA);
        Double doubleOfIntB =  Double.valueOf(intB);
        Double sumOfArguments = doubleOfIntA + doubleOfIntB;
        Validation.isValidInteger(sumOfArguments);

        return intA + intB;
    }

    public static int safeSubtract(int intA, int intB) {

        Double doubleOfIntA =  Double.valueOf(intA);
        Double doubleOfIntB =  Double.valueOf(intB);
        Double sumOfArguments = doubleOfIntA - doubleOfIntB;
        Validation.isValidInteger(sumOfArguments);

        return intA - intB;
    }

    public static int safeMultiplication(int intA, int intB) {

        Double doubleOfIntA =  Double.valueOf(intA);
        Double doubleOfIntB =  Double.valueOf(intB);
        Double multOfArguments = doubleOfIntA * doubleOfIntB;
        Validation.isValidInteger(multOfArguments);

        return intA * intB;
    }

    public static int safeDivision(int intA, int intB) {

        if (intB == 0) {
            throw new ArithmeticException(OperationErrorType.DIVIDE_BY_ZERO.getOperationErrorType());
        }

        if ((intA == Integer.MIN_VALUE) && (intB == -1)) {
            throw new ArithmeticException(OperationErrorType.INT_MIN_VALUE_DIVIDED_BY_MINUS_ONE.getOperationErrorType());
        }

        return intA / intB;
    }
}
