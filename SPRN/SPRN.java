package SPRN;

import SPRN.utils.Validation;

import java.util.*;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static SPRN.utils.Operation.*;

public class SPRN {

    List<Integer> integerList = new ArrayList<>();

    Integer accumulator;

    Pattern pattern;

    public SPRN() {

        String regex = "-?\\d+|\\*|\\/|%|\\+|\\-|d|=|#([\\s\\S]*?)#";
        pattern = Pattern.compile(regex);
    }

    private static boolean isInteger(String str) {

        if (str.isEmpty()) {
            return false;
        }

        try {
            Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    private void removeLastNFromList(List list, Integer n) {

        list.subList(list.size() - n, list.size()).clear();
    }

    private void removeLastTwoFromFromListAndAddNumber(List list, Integer number) {

        removeLastNFromList(list, 2);
        accumulator = number;
        list.add(accumulator);
    }

    private void processOperator(Enum operator) {

        Integer optionalInteger = null;

        try {

            Validation.isValidList(integerList);

            Stream<Integer> stream = integerList
                    .stream()
                    .skip(Math.max(0, integerList.size() - 2));

            if (OperatorType.SUM.equals(operator)) {
                optionalInteger = stream
                        .reduce((integer, integer2) ->
                                safeAdd(integer, integer2))
                        .get();

            }

            if (OperatorType.SUBTRACT.equals(operator)) {
                optionalInteger = stream
                        .reduce((integer, integer2) ->
                                safeSubtract(integer, integer2))
                        .get();
            }

            if (OperatorType.DIVIDE.equals(operator)) {
                optionalInteger = stream
                        .reduce((integer, integer2) ->
                                safeDivision(integer, integer2)
                        )
                        .get();
            }

            if (OperatorType.MULTIPLY.equals(operator)) {
                optionalInteger = stream
                        .reduce((integer, integer2) ->
                                safeMultiplication(integer, integer2)
                        )
                        .get();
            }

            if (OperatorType.REMAINDER.equals(operator)) {
                optionalInteger = stream
                        .reduce((integer, integer2) ->
                                integer % integer2)
                        .get();
            }

        } catch (IndexOutOfBoundsException e) {

            System.out.println(OperationErrorType.STACK_UNDERFLOW.getOperationErrorType());

        } catch (ArithmeticException e) {

            if (e.getMessage().equals(OperationErrorType.INTEGER_OVERFLOW.getOperationErrorType())) {
                optionalInteger = Integer.MAX_VALUE;
            }
            if (e.getMessage().equals(OperationErrorType.INTEGER_UNDERFLOW.getOperationErrorType())) {
                optionalInteger = Integer.MIN_VALUE;
            }
            if (e.getMessage().equals(OperationErrorType.INT_MIN_VALUE_DIVIDED_BY_MINUS_ONE.getOperationErrorType())) {
                optionalInteger = Integer.MAX_VALUE;
            }

            if (e.getMessage().equals(OperationErrorType.DIVIDE_BY_ZERO.getOperationErrorType())) {
                System.out.println(OperationErrorType.DIVIDE_BY_ZERO.getOperationErrorType());
            }

        }

        if (optionalInteger != null) {
            removeLastTwoFromFromListAndAddNumber(integerList, optionalInteger);
        }
    }

    private Boolean isComment(String str) {
        return str.substring(0, 1).equals("#") && str.substring(str.length() - 1).equals("#");
    }

    private List<String> parseCommandLine(String str) {

        return Arrays.stream(pattern
                .matcher(str)
                .results()
                .map(MatchResult::group)
                .toArray(String[]::new)).collect(Collectors.toList());
    }

    private void applyCommand(String command) {

        if (isInteger(command)) {
            integerList.add(Integer.parseInt(command));
            return;
        }

        if (isComment(command)) {
            return;
        }

        if (command.equals("=")) {
            integerList.clear();
            integerList.add(accumulator);
            System.out.println(accumulator);
            return;
        }

        OperatorType operatorType = OperatorType.get(command);

        if (operatorType != null) {
            processOperator(operatorType);
            return;
        }

        if (command.equals("d")) {
            integerList.forEach(element -> System.out.println(element));
            return;
        }

        System.out.println("Unrecognised operator or operand " + "\"" + command + "\"");
    }

    public void processCommand(String command) {

        if (command.isEmpty()) return;
        List<String> list = parseCommandLine(command);
        System.out.println(list);
        for (String el : list) {
            applyCommand(el);

        }
    }
}
