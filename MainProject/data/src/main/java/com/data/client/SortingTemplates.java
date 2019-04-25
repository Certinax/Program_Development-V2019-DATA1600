package com.data.client;


import java.util.ArrayList;

public final class SortingTemplates {

    public static String[] substituteTemplate() {
        return new String[] {"getFirstname", "getLastname", "getAddress", "getZipcode", "getCity", "getAge", "getSalaryRequirement",
        "getIndustry", "getEducation", "getWorkExperience", "getWorkReference"};
    }

    public static String[] testTemplate() {
        return new String[] {"getName", "getAddress", "getAge"};
    }


    public static String[] jobTemplate() {
        return new String[] {"getTitle"};
    }
}


