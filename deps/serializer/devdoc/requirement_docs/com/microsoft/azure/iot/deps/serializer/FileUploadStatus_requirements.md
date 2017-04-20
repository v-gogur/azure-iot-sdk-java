# File Upload Status Requirements

## Overview

FileUploadStatus is a representation of a single file for the File Upload, with a Json serializer.

## References

[File uploads with IoT Hub](https://docs.microsoft.com/en-us/azure/iot-hub/iot-hub-devguide-file-upload)

## Exposed API

```java
/**
 * Representation of the status of a single file for the File Upload, with a Json serializer.
 * Ex of JSON format:
 *  {
 *      "correlationId": "{correlation ID received from the initial request}",
 *      "isSuccess": bool,
 *      "statusCode": XXX,
 *      "statusDescription": "Description of status"
 *  }
 */
public class FileUploadStatus
{
    public FileUploadStatus(String correlationId, Boolean isSuccess, Integer statusCode, String statusDescription) 
            throws IllegalArgumentException;
    public void updateStatus(Boolean isSuccess, Integer statusCode, String statusDescription) throws IllegalArgumentException;
    public String toJson();
}
```


### FileUploadStatus
```java
/**
 * CONSTRUCTOR
 * Create an instance of the FileUploadStatus for a single file in Azure Storage.
 *
 * @param correlationId is an unique file identification.
 * @param isSuccess is a Boolean representing whether the file was uploaded successfully.
 * @param statusCode is the status for the upload of the file to storage.
 * @param statusDescription is the description of the status code.
 * @throws IllegalArgumentException if one of the parameters is null, empty, or not valid.
 */
public FileUploadStatus(String correlationId, Boolean isSuccess, Integer statusCode, String statusDescription)
        throws IllegalArgumentException
```
**SRS_FILE_UPLOAD_STATUS_21_001: [**The constructor shall create an instance of the FileUploadStatus.**]**  
**SRS_FILE_UPLOAD_STATUS_21_002: [**The constructor shall set the `correlationId`, `isSuccess`, `statusCode`, and `statusDescription` in the new class with the provided parameters.**]**  
**SRS_FILE_UPLOAD_STATUS_21_003: [**If one of the provided parameters is null, empty, or not valid, the constructor shall throws IllegalArgumentException.**]**  

### updateStatus
```java
/**
 * Update the status information in the collection, and return the new json.
 *
 * @param isSuccess is a Boolean representing whether the file was uploaded successfully.
 * @param statusCode is the status for the upload of the file to storage.
 * @param statusDescription is the description of the status code.
 * @return a valid json that represents the content of this class.
 * @throws IllegalArgumentException if one of the parameters is null, empty, or not valid.
 */
public synchronized String updateStatus(Boolean isSuccess, Integer statusCode, String statusDescription) throws IllegalArgumentException
```
**SRS_FILE_UPLOAD_STATUS_21_004: [**The updateStatus shall set the `isSuccess`, `statusCode` and `statusDescription` with the provided information.**]**  
**SRS_FILE_UPLOAD_STATUS_21_005: [**If `isSuccess`, or `statusCode` is null, the updateStatus shall throws IllegalArgumentException.**]**  
**SRS_FILE_UPLOAD_STATUS_21_006: [**If `statusDescription` is null, empty, or not valid, the updateStatus shall throws IllegalArgumentException.**]**  
**SRS_FILE_UPLOAD_STATUS_21_007: [**The updateStatus shall return a string with a json that represents the contend of the FileUploadStatus.**]**  

### toJson
```java
/**
 * Convert this class in a valid json.
 *
 * @return a valid json that represents the content of this class.
 */
public String toJson()
```
**SRS_FILE_UPLOAD_STATUS_21_008: [**The toJson shall return a string with a json that represents the contend of the FileUploadStatus.**]**  
