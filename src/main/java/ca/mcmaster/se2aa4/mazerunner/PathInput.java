package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;
import java.util.List;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;

public class PathInput {

    private static List<Character> path = new ArrayList<>();
    public void Path(){

    }


    // TODO: make sure this can account for spaces in the input, it currently does not

    public static String canonizedPath(String pathInput){

        pathInput = pathInput.replaceAll("\\s+", "");

        StringBuilder pathForAnalysis = new StringBuilder();

//        Pattern pattern = Pattern.compile("(\\d+)([RLF])");
//        Matcher matcher = pattern.matcher(pathInput);
//
//        while (matcher.find()){
//            String coefficient = matcher.group(1);
//            String pointedDirection = matcher.group(2);
//            int repeatStep = Integer.parseInt(coefficient);
//
//            for (int i = 0; i < repeatStep; i++){
//
//                pathForAnalysis.append(pointedDirection);
//
//            }
//        }
        return pathForAnalysis.toString();
    }

}
