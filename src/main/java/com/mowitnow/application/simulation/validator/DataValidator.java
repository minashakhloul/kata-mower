package com.mowitnow.application.simulation.validator;

import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class DataValidator {
    public static final String DIGIT_REGEX = "\\d+";
    public static final String INPUT_DELIMITER = " ";
    public static final String ORIENTATION_REGEX = "[^NEWS]";
    public static final String PIVOT_REGEX = "[^GAD]";
    private static final Pattern orientationPattern = Pattern.compile(ORIENTATION_REGEX);
    private static final Pattern pivotPattern = Pattern.compile(PIVOT_REGEX);

    public static boolean isValid(List<String> lines) {
        if (lines.isEmpty()) {
            throw new IllegalArgumentException("Cannot validate input data as file provided is empty");
        }
        boolean isLineValid = validateLawnInput(lines.get(0));
        for (int i = 1; i < lines.size(); i++) {
            if (i % 2 != 0) {
                isLineValid = isLineValid && validateMowerInput(lines.get(i), i);
            } else {
                isLineValid = isLineValid && validateInstructionInput(lines.get(i), i);
            }
        }
        return isLineValid;
    }

    private static boolean validateInstructionInput(String instructionInput, int index) {
        boolean instructionMovement = !pivotPattern.matcher(instructionInput).find();
        if (!instructionMovement) {
            throw new IllegalArgumentException(String.format("Instruction input is not valid at line %s", index));
        }
        return true;
    }

    private static boolean validateMowerInput(String mowerInput, int index) {
        String[] mowerInputs = mowerInput.split(INPUT_DELIMITER);
        boolean isValid = mowerInputs.length == 3 &&
                mowerInputs[0].matches(DIGIT_REGEX) &&
                mowerInputs[1].matches(DIGIT_REGEX) &&
                !orientationPattern.matcher(mowerInputs[2]).find();
        if (!isValid) {
            throw new IllegalArgumentException(String.format("Mower input is invalid at line %s", index));
        }
        return true;
    }

    private static boolean validateLawnInput(String firstLine) {
        String[] characters = firstLine.split(INPUT_DELIMITER);
        boolean isValid = characters.length == 2 &&
                characters[0].matches(DIGIT_REGEX) &&
                characters[1].matches(DIGIT_REGEX);
        if (!isValid) {
            throw new IllegalArgumentException("Lawn input is not valid !");
        }
        return true;
    }
}
