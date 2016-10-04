package com.healthesystems.catalog.model;

/**
 * Created by apurdon on 10/3/16.
 */
public enum PriceLocale {



        ALABAMA("Alabama","AL"),
        ALASKA("Alaska","AK"),
        ARIZONA("Arizona","AZ"),
        ARKANSAS("Arkansas","AR","PriceLocale-AR"),
        CALIFORNIA("California","CA","PriceLocale-CA"),
        COLORADO("Colorado","CO","PriceLocale-CO"),
        CONNECTICUT("Connecticut","CT","PriceLocale-CT"),
        DELAWARE("Delaware","DE","PriceLocale-DE"),
        DISTRICT_OF_COLUMBIA("District of Columbia","DC","PriceLocale-DC"),
        FLORIDA("Florida","FL","PriceLocale-FL"),
        GEORGIA("Georgia","GA","PriceLocale-GA"),
        HAWAII("Hawaii","HI","PriceLocale-HI"),
        IDAHO("Idaho","ID","PriceLocale-ID"),
        ILLINOIS("Illinois","IL","PriceLocale-IL"),
        INDIANA("IN"),
        IOWA("IA"),
        KANSAS("KS"),
        KENTUCKY("KY"),
        LOUISIANA("LA",),
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
        NEW_HAMPSHIRE(,"NH"),
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

//        String unnabreviated;
//        String ANSIabbreviation;
//        String ISOabbreviation;
//
//        PriceLocale(String unnabreviated, String ANSIabbreviation, String ISOabbreviation) {
//            this.unnabreviated = unnabreviated;
//            this.ANSIabbreviation = ANSIabbreviation;
//            this.ISOabbreviation = ISOabbreviation;
       }

//        /**
//         * Parse string input to enum. Accepts unabbreviated and abbreviated forms.
//         * Case insensitive.
//         * @param input String to parse
//         * @return The parsed PriceLocale state, or null on failure.
//         */
//        public static PriceLocale parse(String input) {
//            if (null == input) {
//                return null;
//            }
//            input = input.trim();
//            for (PriceLocale state : values()) {
//                if (state.unnabreviated.equalsIgnoreCase(input)    ||
//                        state.ANSIabbreviation.equalsIgnoreCase(input) ||
//                        state.ISOabbreviation.equalsIgnoreCase(input)) {
//                    return state;
//                }
//            }
//            return null;
//        }
//    }
//}
