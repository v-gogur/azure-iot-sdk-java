# Util helpers for the serializer

## Overview

Set of static functions to help the serializer.

## References

[Azure IoT Hub developer guide](https://docs.microsoft.com/en-us/azure/iot-hub/iot-hub-devguide)

## Exposed API

```java
/**
 * Set of static functions to help the serializer.
 */
public class Utility
{
    protected static void validateStringUTF8(String str) throws IllegalArgumentException;
    protected static void validateInteger(Integer val) throws IllegalArgumentException;
    protected static void validateBoolean(Boolean condition) throws IllegalArgumentException;
    
    protected static Date getDateTimeUtc(String dataTime) throws IllegalArgumentException;
    protected static Date getDateTimeOffset(String dataTime) throws IllegalArgumentException;
}
```

### validateStringUTF8
```java
/**
 * Helper to validate if the provided string is not null, empty, and all characters are UTF-8.
 *
 * @param str is the string to be validated.
 * @throws IllegalArgumentException if the string do not fit the criteria.
 */
protected static void validateStringUTF8(String str) throws IllegalArgumentException
```
**SRS_UTILITY_21_001: [**The validateStringUTF8 shall do nothing if the string is valid.**]**  
**SRS_UTILITY_21_002: [**The validateStringUTF8 shall throw IllegalArgumentException is the provided string is null or empty.**]**  
**SRS_UTILITY_21_003: [**The validateStringUTF8 shall throw IllegalArgumentException is the provided string contains at least one not UTF-8 character.**]**  

### validateInteger
```java
/**
 * Helper to validate if the provided integer is not null.
 *
 * @param val is the integer to be validated.
 * @throws IllegalArgumentException if the integer do not fit the criteria.
 */
protected static void validateInteger(Integer val) throws IllegalArgumentException
```
**SRS_UTILITY_21_004: [**The validateInteger shall do nothing if the integer is valid.**]**  
**SRS_UTILITY_21_005: [**The validateInteger shall throw IllegalArgumentException is the provided integer is null.**]**  

### validateBoolean
```java
/**
 * Helper to validate if the provided boolean is not null.
 *
 * @param condition is the boolean to be validated.
 * @throws IllegalArgumentException if the boolean do not fit the criteria.
 */
protected static void validateBoolean(Boolean condition) throws IllegalArgumentException
```
**SRS_UTILITY_21_006: [**The validateBoolean shall do nothing if the boolean is valid.**]**  
**SRS_UTILITY_21_007: [**The validateBoolean shall throw IllegalArgumentException is the provided boolean is null.**]**  

### getDateTimeUtc
```java
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
```
**SRS_UTILITY_21_008: [**The getDateTimeUtc shall parse the provide string using `UTC` timezone.**]**  
**SRS_UTILITY_21_009: [**The getDateTimeUtc shall parse the provide string using the data format `yyyy-MM-dd'T'HH:mm:ss.SSSS'Z'`.**]**  
**SRS_UTILITY_21_010: [**If the provide string is null, empty or contains an invalid data format, the getDateTimeUtc shall throw IllegalArgumentException.**]**  

### getDateTimeOffset
```java
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
```
**SRS_UTILITY_21_011: [**The getDateTimeOffset shall parse the provide string using `UTC` timezone.**]**  
**SRS_UTILITY_21_012: [**The getDateTimeOffset shall parse the provide string using the data format `2016-06-01T21:22:41+00:00`.**]**  
**SRS_UTILITY_21_013: [**If the provide string is null, empty or contains an invalid data format, the getDateTimeOffset shall throw IllegalArgumentException.**]**  
