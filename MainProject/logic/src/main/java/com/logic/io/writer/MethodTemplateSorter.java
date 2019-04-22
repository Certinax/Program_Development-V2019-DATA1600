package com.logic.io.writer;

public final class MethodTemplateSorter {

/*
    public static String[] template(Object obj) {
        String[] template;
        switch (obj.getClass().getName()) {
            case "com.data.client.Employer":
                template = employerTemplate(obj.getClass());
                break;
            case "com.data.dlient.Employee":
                template = employeeTemplate(obj.getClass());
                break;
            default:
                return null;
        }
        return template;
    }

    private static String[] employerTemplate(Class clazz) {
        String[] template;
        switch (clazz.getName()) {
            case "com.data.client.Employer":
                template =  new String[] {"getSector", "getIndustry", "getJoblist"};
                break;
            case "com.data.client.Client":
                template = new String[] {"getName", "getAddress", "getZipcode", "getCity"};
                break;
            default:
                return null;
        }
        return template;
    }

    private static String[] employeeTemplate(Class clazz) {
        String[] template;
        switch(clazz.getName()) {
            case "com.data.client.Employee":
                template = new String[] {"getSsn", "getAge", "getSalaryRequirement",
                        "getEducation", "getJobExperience", "getJobRequirements", "getReferences"};
                break;
            case "com.data.client.Client":
                template = new String[] {"getFirstname", "getLastname", "getAddress", "getZipcode", "getCity"};
                break;
            default:
                return null;
        }
        return template;
    }*/
}
