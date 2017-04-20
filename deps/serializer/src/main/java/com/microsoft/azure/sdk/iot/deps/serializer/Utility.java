// Copyright (c) Microsoft. All rights reserved.
// Licensed under the MIT license. See LICENSE file in the project root for full license information.

package com.microsoft.azure.sdk.iot.deps.serializer;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Set of static functions to help the serializer.
 */
public class Utility
{
    private static final String DATEFORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSS'Z'";
    private static final String OFFSETFORMAT = "yyyy-MM-dd'T'HH:mm:ssXXX";
    private static final String TIMEZONE = "UTC";

    /**
     * Helper to validate if the provided string is not null, empty, and all characters are UTF-8.
     *
     * @param str is the string to be validated.
     * @throws IllegalArgumentException if the string do not fit the criteria.
     */
    protected static void validateStringUTF8(String str) throws IllegalArgumentException
    {
        /* Codes_SRS_UTILITY_21_002: [The validateStringUTF8 shall throw IllegalArgumentException is the provided string is null or empty.] */
        if((str == null) || str.isEmpty())
        {
            throw new IllegalArgumentException("parameter is null or empty");
        }

        /* Codes_SRS_UTILITY_21_003: [The validateStringUTF8 shall throw IllegalArgumentException is the provided string contains at least one not UTF-8 character.] */
        try
        {
            if(str.getBytes("UTF-8").length != str.length())
            {
                throw new IllegalArgumentException("invalid parameter");
            }
        }
        catch(UnsupportedEncodingException e)
        {
            throw new IllegalArgumentException("invalid parameter");
        }

        /* Codes_SRS_UTILITY_21_001: [The validateStringUTF8 shall do nothing if the string is valid.] */
    }

    /**
     * Helper to validate if the provided integer is not null.
     *
     * @param val is the integer to be validated.
     * @throws IllegalArgumentException if the integer do not fit the criteria.
     */
    protected static void validateInteger(Integer val) throws IllegalArgumentException
    {
        /* Codes_SRS_UTILITY_21_004: [The validateInteger shall do nothing if the integer is valid.] */
        /* Codes_SRS_UTILITY_21_005: [The validateInteger shall throw IllegalArgumentException is the provided integer is null.] */
        if(val == null)
        {
            throw new IllegalArgumentException("parameter is null");
        }
    }

    /**
     * Helper to validate if the provided boolean is not null.
     *
     * @param condition is the boolean to be validated.
     * @throws IllegalArgumentException if the boolean do not fit the criteria.
     */
    protected static void validateBoolean(Boolean condition) throws IllegalArgumentException
    {
        /* Codes_SRS_UTILITY_21_006: [The validateBoolean shall do nothing if the boolean is valid.] */
        /* Codes_SRS_UTILITY_21_007: [The validateBoolean shall throw IllegalArgumentException is the provided boolean is null.] */
        if(condition == null)
        {
            throw new IllegalArgumentException("parameter is null");
        }
    }

    /**
     * Helper to convert the provided string in a UTC Date.
     * Expected format:
     *      "2016-06-01T21:22:43.7996883Z"
     *
     * @param dataTime is the string with the date and time
     * @return Date parsed from the string
     * @throws IllegalArgumentException if the date and time in the string is not in the correct format.
     */
    protected static Date getDateTimeUtc(String dataTime) throws IllegalArgumentException
    {
        Date dateTimeUtc;
        /* Codes_SRS_UTILITY_21_008: [The getDateTimeUtc shall parse the provide string using `UTC` timezone.] */
        /* Codes_SRS_UTILITY_21_009: [The getDateTimeUtc shall parse the provide string using the data format `yyyy-MM-dd'T'HH:mm:ss.SSSS'Z'`.] */
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATEFORMAT);
        dateFormat.setTimeZone(TimeZone.getTimeZone(TIMEZONE));

        /* Codes_SRS_UTILITY_21_010: [If the provide string is null, empty or contains an invalid data format, the getDateTimeUtc shall throw IllegalArgumentException.] */
        if((dataTime == null) || dataTime.isEmpty())
        {
            throw new IllegalArgumentException("date is null or empty");
        }

        try
        {
            dateTimeUtc = dateFormat.parse(dataTime);
        }
        catch (ParseException e)
        {
            throw new IllegalArgumentException("invalid time:" + e.toString());
        }
        
        return dateTimeUtc;
    }

    /**
     * Helper to convert the provided string in a offset Date.
     * Expected format:
     *      "2016-06-01T21:22:41+00:00"
     *
     * @param dataTime is the string with the date and time
     * @return Date parsed from the string
     * @throws IllegalArgumentException if the date and time in the string is not in the correct format.
     */
    protected static Date getDateTimeOffset(String dataTime) throws IllegalArgumentException
    {
        Date dateTimeOffset;

        /* Codes_SRS_UTILITY_21_011: [The getDateTimeOffset shall parse the provide string using `UTC` timezone.] */
        /* Codes_SRS_UTILITY_21_012: [The getDateTimeOffset shall parse the provide string using the data format `2016-06-01T21:22:41+00:00`.] */
        SimpleDateFormat dateFormat = new SimpleDateFormat(OFFSETFORMAT);
        dateFormat.setTimeZone(TimeZone.getTimeZone(TIMEZONE));

        /* Codes_SRS_UTILITY_21_013: [If the provide string is null, empty or contains an invalid data format, the getDateTimeOffset shall throw IllegalArgumentException.] */
        if((dataTime == null) || dataTime.isEmpty())
        {
            throw new IllegalArgumentException("date is null or empty");
        }

        try
        {
            dateTimeOffset = dateFormat.parse(dataTime);
        }
        catch (ParseException e)
        {
            throw new IllegalArgumentException("invalid time:" + e.toString());
        }

        return dateTimeOffset;
    }
}
