package com.healthesystems.catalog.model;

/**
 * Created by apurdon on 10/3/16.
 */
public enum PriceLocale {


        XX("All_STATES"),
        AL("ALABAMA"),
        AK("ALASKA"),
        AZ("ARIZONA"),
        AR("ARKANSAS"),
        CA("CALIFORNIA"),
        CO("COLORADO"),
        CT("CONNECTICUT"),
        DE("DELAWARE"),
        DC("DISTRICT_OF_COLUMBIA"),
        FL("FLORIDA"),
        GA("GEORGIA"),
        HI("HAWAII"),
        ID("IDAHO"),
        IL("ILLINOIS"),
        IN("INDIANA"),
        IA("IOWA"),
        KS("KANSAS"),
        KY("KENTUCKY"),
        LA("LOUISIANA"),
        ME("MAINE"),
        MD("MARYLAND"),
        MA("MASSACHUSETTS"),
        MI("MICHIGAN"),
        MN("MINNESOTA"),
        MS("MISSISSIPPI"),
        MO("MISSOURI"),
        MT("MTMONTANA"),
        NE("NEBRASKA"),
        NV("NEVADA"),
        NH("NEW_HAMPSHIRE"),
        NJ("NEW_JERSEY"),
        NM("NEW_MEXICO"),
        NY("NEW_YORK"),
        NC("NORTH_CAROLINA"),
        ND("NORTH_DAKOTA"),
        OH("OHIO"),
        OK("OKLAHOMA"),
        OR("OR"),
        PA("PENNSYLVANIA"),
        RI("RHODE_ISLAND"),
        SC("SOUTH_CAROLINA"),
        SD("SOUTH_DAKOTA"),
        TN("TENNESSEE"),
        TX("TEXAS"),
        UT("UTAH"),
        VT("VERMONT"),
        VA("VIRGINIA"),
        WA("WASHINGTON"),
        WV("WEST_VIRGINIA"),
        WI("WISCONSIN"),
        WY("WYOMING"),
        PR("PUERTO_RICO");

    private String ANSIabbreviation;

        PriceLocale( String ANSIabbreviation) {
            this.ANSIabbreviation = ANSIabbreviation;
       }

        public static PriceLocale parse(String input) {
            if (null == input) {
                return null;
            }
            input = input.trim();
            for (PriceLocale state : values()) {
                if (state.ANSIabbreviation.equalsIgnoreCase(input) ) return state;
            }
            return null;
        }
    }

