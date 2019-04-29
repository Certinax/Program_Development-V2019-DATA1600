package com.data;

/**
 * <h1>Industry</h1>
 *
 * Enumlist for all predefined industry-types
 *
 * Used for industry selector
 *
 * @Author Mathias Lund Ahrn
 * @since 15-04-2019
 */

public enum Industry {

    ANALYSIS("Analysis"),
    ARCHITECT_AND_PLANNING("Architect and planning"),
    ARCHIVIST_AND_LIBRARIAN("Archivist and librarian"),
    ART_DIRECTOR("Art Director"),
    KINDERGARTEN("Kindergarten"),
    CHILD_WELFARE("Child welfare"),
    BIOLOGY("Biology"),
    FIREFIGHTER("Firefighter"),
    SUPPORT("Support"),
    STORE_EMPLOYEE("Store employee"),
    STORE_MANAGER("Store manager"),
    DESIGN("Design"),
    DOCUMENTATION("Documentation"),
    CUSTODIAN("Custodian"),
    BUSINESS_DEVELOPMENT_AND_STRATEGY("Business development and strategy"),
    RESEARCH("Research"),
    MEDIA("Media"),
    HAIRDRESSER("Hairdresser"),
    HEALTH_PROFESSIONALS("Health professionals"),
    HSE("HSE"),
    HOTEL_AND_ACCOMODATION("Hotel and accomodation"),
    HR_PERSONAL_AND_RECRUITMENT("HR, personal and recruitment"),
    SKINCARE("Skincare"),
    ENGINEER("Engineer"),
    PROCUREMENT("Procurement"),
    IT_OPERATION_AND_MAINTENANCE("IT operation and maintenance"),
    IT_DEVELOPMENT("IT development"),
    JOURNALIST("Journalist"),
    LAWYER("Lawyer"),
    CONSULTANT("Consultant"),
    OFFICE_AND_ADMINISTRATION("Office and administration"),
    COORDINATION("Coordination"),
    COURSE_AND_TRAINING("Course and training"),
    QUALITYENSURANCE("Qualityensurance"),
    MANAGEMENT("Management"),
    DOCTOR("Doctor"),
    LOGISTICS_AND_WAREHOUSING("Logistics and warehousing"),
    MARKETER("Marketer"),
    MACHINE_OPERATOR("Machine operator"),
    FOOD_SERVICE("Food service"),
    BROKER("Broker"),
    MECHANICS_AND_INSTALLATION("Mechanics and installation"),
    CARE_AND_SOCIAL_WORK("Care and social work"),
    PILOT_AND_FLIGHTCREW("Pilot and flightcrew"),
    PRODUCTION("Production"),
    PORJECT_MANAGEMENT("Project management"),
    PR_AND_INFORMATION("PR and information"),
    CLEANING("Cleaning"),
    AUDIT_AND_CONTROL("Audit and control"),
    CONSULTING("Consulting"),
    EXECUTIVE_OFFICER("Executive officer"),
    SALE("Sale"),
    SALE_MANAGEMENT("Sale management"),
    SOCIAL_SCIENTIST("Social scientist"),
    SECURITY("Security"),
    SEAFARING("Seafaring"),
    SOCIAL_WORKER("Social worker"),
    NURSE("Nurse"),
    SERVICEMAN("Serviceman"),
    SERVICE("Service"),
    INTERPRETER("Interpreter"),
    TRANSPORTATION_AND_DRIVER("Transportation and driver"),
    PERSONAL_TRAINER("Personal trainer"),
    TEACHING_AND_PEDAGOGY("Teaching and pedagogy"),
    PERFORMING_ARTS("Performing arts"),
    GUARD_AND_SECURITY("Guard and security"),
    VETRINARIAN("Vetrinarian"),
    FINANCE_AND_ACCOUNTING("Finance and accounting"),
    OTHER("Other");





    private final String industryName;

    Industry(String industryName) {
        this.industryName = industryName;
    }

    public String getIndustryName() {
        return this.industryName;
    }
}
