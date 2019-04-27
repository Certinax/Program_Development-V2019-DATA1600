package com.data.validator;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <h1>DataValidator</h1>
 * 
 * Class with static methods to validate Strings,
 * ints and lists of Strings in the application.
 *
 * @author Lise Sundrønning
 * @since 27-04-2019
 */
public class DataValidator {

  DataValidator(){}

  public static boolean adressCheck(String adress){
    Pattern regex = Pattern.compile("^[a-zæøåA-ZÆØÅ-]+ *[0-9]*$");
    Matcher matcher = regex.matcher(adress);

    return matcher.find();
  }

  public static boolean zipcodeCheck(int zipcode){
    return (zipcode > 0 && zipcode < 100_000);
  }

  public static boolean zipcodeCheck(String zipcode){
    Pattern regex = Pattern.compile("^[0-9]{4,5}$");
    Matcher matcher = regex.matcher(zipcode);
    return matcher.find();
  }

  public static boolean cityCheck(String city){
    return true;
  }

  public static boolean industryCheck(String industry){
    return true;
  }

  public static boolean nameCheck(String name){
    Pattern regex = Pattern.compile("^[a-zæøåA-ZÆØÅ-]+$");
    Matcher matcher = regex.matcher(name);

    return matcher.find();
  }

  public static boolean ageCheck(int age){
    return (age > 0 && age < 200);
  }

  public static boolean ageCheck(String age){
    Pattern regex = Pattern.compile("^[0-9]{1,3}");
    Matcher matcher = regex.matcher(age);

    return matcher.find();
  }

  public static boolean salaryReqirementsIsSet(int salaryRequirement){
    return salaryRequirement <= 0;
  }

  public static boolean salaryReqirementsIsSet(String salaryRequirement){
    Pattern regex = Pattern.compile("^[0-9]*$");
    Matcher matcher = regex.matcher(salaryRequirement);

    return matcher.find();
  }

  public static boolean educationCheck(ArrayList<String> education){
    for (String edu : education) {
      Pattern regex = Pattern.compile("^[a-zæøåA-ZÆØÅ 0-9-]+$");
      Matcher matcher = regex.matcher(edu);

      if (!matcher.find()){
        return false;
      }
    }
    return true;
  }

  public static boolean workExperianceCheck(ArrayList<String> workExperiance){
    for (String exp : workExperiance) {
      Pattern regex = Pattern.compile("^[a-zæøåA-ZÆØÅ 0-9-]+$");
      Matcher matcher = regex.matcher(exp);

      if (!matcher.find()){
        return false;
      }
    }
    return true;
  }

  public static boolean workReferenceCheck(ArrayList<String> workReference){
    for (String ref : workReference) {
      Pattern regex = Pattern.compile("^[a-zæøåA-ZÆØÅ._%+@ 0-9-]+$");
      Matcher matcher = regex.matcher(ref);

      if (!matcher.find()){
        return false;
      }
    }
    return true;
  }

  public static boolean jobListChack(ArrayList<String> jobList){
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
