// Copyright (c) Microsoft. All rights reserved.
// Licensed under the MIT license. See LICENSE file in the project root for full license information.

package com.microsoft.azure.sdk.iot.deps.serializer;

import mockit.Deencapsulation;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Unit tests for serializer utility helpers
 */
public class UtilityTest
{
    /* Tests_SRS_UTILITY_21_001: [The validateStringUTF8 shall do nothing if the string is valid.] */
    @Test
    public void validateStringUTF8_success()
    {
        // act
        Utility.validateStringUTF8("test-string1#");
    }

    /* Tests_SRS_UTILITY_21_002: [The validateStringUTF8 shall throw IllegalArgumentException is the provided string is null or empty.] */
    @Test (expected = IllegalArgumentException.class)
    public void validateStringUTF8_null_failed()
    {
        // act
        Utility.validateStringUTF8(null);
    }

    /* Tests_SRS_UTILITY_21_002: [The validateStringUTF8 shall throw IllegalArgumentException is the provided string is null or empty.] */
    @Test (expected = IllegalArgumentException.class)
    public void validateStringUTF8_empty_failed()
    {
        // act
        Utility.validateStringUTF8("");
    }

    /* Tests_SRS_UTILITY_21_003: [The validateStringUTF8 shall throw IllegalArgumentException is the provided string contains at least one not UTF-8 character.] */
    @Test (expected = IllegalArgumentException.class)
    public void validateStringUTF8_invalid_failed()
    {
        // act
        Utility.validateStringUTF8("\u1234test-string1#");
    }

    /* Tests_SRS_UTILITY_21_004: [The validateInteger shall do nothing if the integer is valid.] */
    @Test
    public void validateInteger_success()
    {
        // act
        Utility.validateInteger(1234);
    }

    /* Tests_SRS_UTILITY_21_005: [The validateInteger shall throw IllegalArgumentException is the provided integer is null.] */
    @Test (expected = IllegalArgumentException.class)
    public void validateInteger_null_failed()
    {
        // act
        Utility.validateInteger(null);
    }

    /* Tests_SRS_UTILITY_21_006: [The validateBoolean shall do nothing if the boolean is valid.] */
    @Test
    public void validateBoolean_success()
    {
        // act
        Utility.validateBoolean(true);
    }
    
    /* Tests_SRS_UTILITY_21_007: [The validateBoolean shall throw IllegalArgumentException is the provided boolean is null.] */
    @Test (expected = IllegalArgumentException.class)
    public void validateBoolean_null_failed()
    {
        // act
        Utility.validateBoolean(null);
    }

    /* Tests_SRS_UTILITY_21_008: [The getDateTimeUtc shall parse the provide string using `UTC` timezone.] */
    /* Tests_SRS_UTILITY_21_009: [The getDateTimeUtc shall parse the provide string using the data format `yyyy-MM-dd'T'HH:mm:ss.SSSS'Z'`.] */
    @Test
    public void getDateTimeUtc_success()
    {
        // act
        Date date = Utility.getDateTimeUtc("2016-06-01T21:22:43.7996883Z");

        // assert
        assertEquals(1464824159883L, date.getTime());
    }

    /* Tests_SRS_UTILITY_21_010: [If the provide string is null, empty or contains an invalid data format, the getDateTimeUtc shall throw IllegalArgumentException.] */
    @Test (expected = IllegalArgumentException.class)
    public void getDateTimeUtc_null_failed()
    {
        // act
        Utility.getDateTimeUtc(null);
    }

    /* Tests_SRS_UTILITY_21_010: [If the provide string is null, empty or contains an invalid data format, the getDateTimeUtc shall throw IllegalArgumentException.] */
    @Test (expected = IllegalArgumentException.class)
    public void getDateTimeUtc_empty_failed()
    {
        // act
        Utility.getDateTimeUtc("");
    }

    /* Tests_SRS_UTILITY_21_010: [If the provide string is null, empty or contains an invalid data format, the getDateTimeUtc shall throw IllegalArgumentException.] */
    @Test (expected = IllegalArgumentException.class)
    public void getDateTimeUtc_invalid_text_failed()
    {
        // act
        Utility.getDateTimeUtc("This is not a data and time");
    }

    /* Tests_SRS_UTILITY_21_010: [If the provide string is null, empty or contains an invalid data format, the getDateTimeUtc shall throw IllegalArgumentException.] */
    @Test (expected = IllegalArgumentException.class)
    public void getDateTimeUtc_wrong_format_failed()
    {
        // act
        Utility.getDateTimeUtc("2016-06-01T21:22:43");
    }

    /* Tests_SRS_UTILITY_21_011: [The getDateTimeOffset shall parse the provide string using `UTC` timezone.] */
    /* Tests_SRS_UTILITY_21_012: [The getDateTimeOffset shall parse the provide string using the data format `2016-06-01T21:22:41+00:00`.] */
    @Test
    public void getDateTimeOffset_success()
    {
        // act
        Date date = Utility.getDateTimeOffset("2016-06-01T21:22:41+00:00");

        // assert
        assertEquals(1464816161000L, date.getTime());
    }

    /* Tests_SRS_UTILITY_21_013: [If the provide string is null, empty or contains an invalid data format, the getDateTimeOffset shall throw IllegalArgumentException.] */
    @Test (expected = IllegalArgumentException.class)
    public void getDateTimeOffset_null_failed()
    {
        // act
        Utility.getDateTimeOffset(null);
    }

    /* Tests_SRS_UTILITY_21_013: [If the provide string is null, empty or contains an invalid data format, the getDateTimeOffset shall throw IllegalArgumentException.] */
    @Test (expected = IllegalArgumentException.class)
    public void getDateTimeOffset_empty_failed()
    {
        // act
        Utility.getDateTimeOffset("");
    }

    /* Tests_SRS_UTILITY_21_013: [If the provide string is null, empty or contains an invalid data format, the getDateTimeOffset shall throw IllegalArgumentException.] */
    @Test (expected = IllegalArgumentException.class)
    public void getDateTimeOffset_invalid_text_failed()
    {
        // act
        Utility.getDateTimeOffset("This is not a data and time");
    }

    /* Tests_SRS_UTILITY_21_013: [If the provide string is null, empty or contains an invalid data format, the getDateTimeOffset shall throw IllegalArgumentException.] */
    @Test (expected = IllegalArgumentException.class)
    public void getDateTimeOffset_wrong_format_failed()
    {
        // act
        Utility.getDateTimeOffset("2016-06-01T21:22:43");
    }
}
