package com.logic.utilities.validators;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <h1>String Validator</h1>
 *
 * Class with static methods to validate Strings in the application
 *
 * @author Candidate 730, Candidate 778
 * @since 15-04-2019
 */

public class DataValidator {

    private DataValidator() { //Private constructor to deter initialization
    }

    public static boolean addressChecker(String address) {
        String[] adrSplit = address.split(" ");

        if (adrSplit.length != 2) {
            return false;
        }

        if (adrSplit[0].length() < 2) {
            return false;
        }

        if (!isInteger(adrSplit[1])) {
            return false;
        }
        return true;
    }

    private static boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


    public static boolean intLengthChecker(int input, int desiredLength) {
        int length = (int) (Math.log10(input) + 1);

        return length == desiredLength;
    }

    public static boolean emailChecker(String email) {
        //This regex mathces any string that has two words consisting of any characters and numbers, separated by an @.
        //There is also a third set of characters which is separated from the original pair by a punctuation mark.
        Pattern regex = Pattern.compile("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,6}$");
        Matcher matcher = regex.matcher(email);

        return matcher.find();
    }

    public static boolean zipcodeChecker(int zipcode){
        return (zipcode > 0 && zipcode < 100_000);
    }

    public static boolean zipcodeChecker(String zipcode){
        if (!isInteger(zipcode)) return false;
        Pattern regex = Pattern.compile("^[0-9]{4,5}$");
        Matcher matcher = regex.matcher(zipcode);
        return matcher.find();
    }

    public static boolean cityChecker(String city){
        if (city == null) return false;
        Pattern regex = Pattern.compile("^[a-zæøåA-ZÆØÅ-]+$");
        Matcher matcher = regex.matcher(city);
        return matcher.find();
    }

    public static boolean industryChecker(String industry){
        if (industry == null) return false;
        Pattern regex = Pattern.compile("^[a-zæøåA-ZÆØÅ-]+$");
        Matcher matcher = regex.matcher(industry);
        return matcher.find();
    }

    public static boolean nameChecker(String name){
        if (name == null) return false;
        Pattern regex = Pattern.compile("^[A-ZÆØÅ][a-zæøå -]+$");
        Matcher matcher = regex.matcher(name);

        return matcher.find();
    }

    public static boolean ageChecker(int age){
        return (age > 0 && age < 200);
    }

    public static boolean ageChecker(String age){
        if (age == null) return false;
        if (!isInteger(age)) return false;
        Pattern regex = Pattern.compile("^[0-9]{1,3}");
        Matcher matcher = regex.matcher(age);

        return matcher.find();
    }

    public static boolean salaryReqirementsIsSet(int salaryRequirement){
        return salaryRequirement <= 0;
    }

    public static boolean salaryReqirementsIsSet(String salaryRequirement){
        if (salaryRequirement == null) return false;
        if (!isInteger(salaryRequirement)) return false;
        Pattern regex = Pattern.compile("^[0-9]*$");
        Matcher matcher = regex.matcher(salaryRequirement);

        return matcher.find();
    }

    public static boolean educationChecker(ArrayList<String> education){
        if (education == null) return false;
        for (String edu : education) {
            Pattern regex = Pattern.compile("^[a-zæøåA-ZÆØÅ 0-9-]+$");
            Matcher matcher = regex.matcher(edu);

            if (!matcher.find()){
                return false;
            }
        }
        return true;
    }


    // TODO all following methods need to be updated once logic is in place
    public static boolean workExperianceChecker(ArrayList<String> workExperiance){
        if (workExperiance == null) return false;
        for (String exp : workExperiance) {
            Pattern regex = Pattern.compile("^[a-zæøåA-ZÆØÅ 0-9-]+$");
            Matcher matcher = regex.matcher(exp);

            if (!matcher.find()){
                return false;
            }
        }
        return true;
    }

    public static boolean workReferenceChecker(ArrayList<String> workReference){
        if (workReference == null) return false;
        for (String ref : workReference) {
            Pattern regex = Pattern.compile("^[a-zæøåA-ZÆØÅ._%+@ 0-9-]+$");
            Matcher matcher = regex.matcher(ref);

            if (!matcher.find()){
                return false;
            }
        }
        return true;
    }

    public static boolean jobListChacker(ArrayList<String> jobList){
        if (jobList == null) return false;
        for (String job : jobList) {

            // TODO change regex to fit with jobList ID
            Pattern regex = Pattern.compile("^[a-zæøåA-ZÆØÅ._%+@ 0-9-]+$");
            Matcher matcher = regex.matcher(job);

            if (!matcher.find()){
                return false;
            }
        }
        return true;
    }
}
