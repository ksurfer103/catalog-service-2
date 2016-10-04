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
        IA("IOWA");
        /*KANSAS("KS"),
        KENTUCKY("KY"),
        LOUISIANA("LA"),
        MAINE("ME"),
        MARYLAND("MD"),
        MASSACHUSETTS("MA"),
        MICHIGAN("MI"),
        MINNESOTA("MN"),
        MISSISSIPPI("MS"),
        MISSOURI("MO"),
        MONTANA("MT"),
        NEBRASKA("NE"),
        NEVADA("NV"),
        NEW_HAMPSHIRE("NH"),
        NEW_JERSEY("NJ"),
        NEW_MEXICO("NM"),
        NEW_YORK("NY"),
        NORTH_CAROLINA("NC"),
        NORTH_DAKOTA("ND"),
        OHIO("OH"),
        OKLAHOMA("OK"),
        OREGON("OR"),
        PENNSYLVANIA("PA"),
        RHODE_ISLAND("RI"),
        SOUTH_CAROLINA("SC"),
        SOUTH_DAKOTA("SD"),
        TENNESSEE("TN"),
        TEXAS("TX"),
        UTAH("UT"),
        VERMONT("VT"),
        VIRGINIA("VA"),
        WASHINGTON("WA"),
        WEST_VIRGINIA("WV"),
        WISCONSIN("WI"),
        WYOMING("WY"),
        PUERTO_RICO("PR");
        */

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

