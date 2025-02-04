package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PathInput {

    public static String canonizedPath(String pathInput){

        pathInput = pathInput.replaceAll("\\s+", "");

        StringBuilder pathForAnalysis = new StringBuilder();

        Pattern pattern = Pattern.compile("(\\d+)([RLF])");
        Matcher matcher = pattern.matcher(pathInput);

        while (matcher.find()){
            String coefficient = matcher.group(1);
            String pointedDirection = matcher.group(2);
            int repeatStep = Integer.parseInt(coefficient);

            for (int i = 0; i < repeatStep; i++){

                pathForAnalysis.append(pointedDirection);

            }
        }
        return pathForAnalysis.toString();
    }

    //go through and factorize the program output
    public static String factorizePath(String pathInput) {
        //remove "RL" sequences because they contradict one another
        String simplified = pathInput.replace("RL", "");
        if (simplified == null || simplified.isEmpty()) {
            return "";
        }
        StringBuilder outputPath = new StringBuilder();
        char currentChar = simplified.charAt(0);
        int count = 1;

        for (int i = 1; i < simplified.length(); i++) {
            if (simplified.charAt(i) == currentChar) {
                count++;
            } else {
                if (count > 1) {
                    outputPath.append(count);
                }
                outputPath.append(currentChar);
                currentChar = simplified.charAt(i);
                count = 1;
            }
        }

        if (count > 1) {
            outputPath.append(count);
        }
        outputPath.append(currentChar);

        return outputPath.toString();
    }

}
