// Copyright (c) Microsoft. All rights reserved.
// Licensed under the MIT license. See LICENSE file in the project root for full license information.

package com.microsoft.azure.sdk.iot.deps.serializer;

import mockit.Deencapsulation;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Unit tests for File Upload Notification deserializer
 */
public class FileUploadNotificationTest 
{
    private static final String VALID_DEVICEID = "test-device1";
    private static final String INVALID_DEVICEID = "\u1234test-device1";
    private static final String VALID_BLOB_UTI = "https://storageaccount.blob.core.windows.net/containername/test-device1/image.jpg";
    private static final String INVALID_BLOB_URI = "https://\u1234storageaccount.blob.core.windows.net/containername/test-device1/image.jpg";
    private static final String VALID_BLOB_NAME = "test-device1/image.jpg";
    private static final String INVALID_BLOB_NAME = "\u1234test-device1/image.jpg";
    private static final String VALID_LAST_UPDATE_TIME = "2016-06-01T21:22:41+00:00";
    private static final long VALID_LAST_UPDATE_TIME_IN_MILLISECONDS = 1464816161000L;
    private static final String INVALID_LAST_UPDATE_TIME = "\u12342016-06-01T21:22:41+00:00";
    private static final String INVALID_DATETIME_OFFSET = "2016-06-40T21:22:41+00:00";
    private static final String VALID_ENQUEUED_TIME_UTC = "2016-06-01T21:22:43.7996883Z";
    private static final long VALID_ENQUEUED_TIME_UTC_IN_MILLISECONDS = 1464824159883L;
    private static final String INVALID_ENQUEUED_TIME_UTC = "\u12342016-06-01T21:22:43.7996883Z";
    private static final String INVALID_DATETIME_UTC = "2016-6-1T4:22:43.7996883Z";
    private static final Integer VALID_BLOB_SIZE_IN_BYTES = 1234;

    private static class TestParameters
    {
        String deviceId;
        String blobUri;
        String blobName;
        String lastUpdatedTime;
        String enqueuedTimeUtc;
        Integer blobSizeInBytes;
    }
    private static final TestParameters[] tests = new TestParameters[]
    {
            new TestParameters(){{ deviceId = null; blobUri = VALID_BLOB_UTI; blobName = VALID_BLOB_NAME; lastUpdatedTime = VALID_LAST_UPDATE_TIME; enqueuedTimeUtc = VALID_ENQUEUED_TIME_UTC; blobSizeInBytes=VALID_BLOB_SIZE_IN_BYTES; }},
            new TestParameters(){{ deviceId = ""; blobUri = VALID_BLOB_UTI; blobName = VALID_BLOB_NAME; lastUpdatedTime = VALID_LAST_UPDATE_TIME; enqueuedTimeUtc = VALID_ENQUEUED_TIME_UTC; blobSizeInBytes=VALID_BLOB_SIZE_IN_BYTES; }},
            new TestParameters(){{ deviceId = INVALID_DEVICEID; blobUri = VALID_BLOB_UTI; blobName = VALID_BLOB_NAME; lastUpdatedTime = VALID_LAST_UPDATE_TIME; enqueuedTimeUtc = VALID_ENQUEUED_TIME_UTC; blobSizeInBytes=VALID_BLOB_SIZE_IN_BYTES; }},

            new TestParameters(){{ deviceId = VALID_DEVICEID; blobUri = null; blobName = VALID_BLOB_NAME; lastUpdatedTime = VALID_LAST_UPDATE_TIME; enqueuedTimeUtc = VALID_ENQUEUED_TIME_UTC; blobSizeInBytes=VALID_BLOB_SIZE_IN_BYTES; }},
            new TestParameters(){{ deviceId = VALID_DEVICEID; blobUri = ""; blobName = VALID_BLOB_NAME; lastUpdatedTime = VALID_LAST_UPDATE_TIME; enqueuedTimeUtc = VALID_ENQUEUED_TIME_UTC; blobSizeInBytes=VALID_BLOB_SIZE_IN_BYTES; }},
            new TestParameters(){{ deviceId = VALID_DEVICEID; blobUri = INVALID_BLOB_URI; blobName = VALID_BLOB_NAME; lastUpdatedTime = VALID_LAST_UPDATE_TIME; enqueuedTimeUtc = VALID_ENQUEUED_TIME_UTC; blobSizeInBytes=VALID_BLOB_SIZE_IN_BYTES; }},

            new TestParameters(){{ deviceId = VALID_DEVICEID; blobUri = VALID_BLOB_UTI; blobName = null; lastUpdatedTime = VALID_LAST_UPDATE_TIME; enqueuedTimeUtc = VALID_ENQUEUED_TIME_UTC; blobSizeInBytes=VALID_BLOB_SIZE_IN_BYTES; }},
            new TestParameters(){{ deviceId = VALID_DEVICEID; blobUri = VALID_BLOB_UTI; blobName = ""; lastUpdatedTime = VALID_LAST_UPDATE_TIME; enqueuedTimeUtc = VALID_ENQUEUED_TIME_UTC; blobSizeInBytes=VALID_BLOB_SIZE_IN_BYTES; }},
            new TestParameters(){{ deviceId = VALID_DEVICEID; blobUri = VALID_BLOB_UTI; blobName = INVALID_BLOB_NAME; lastUpdatedTime = VALID_LAST_UPDATE_TIME; enqueuedTimeUtc = VALID_ENQUEUED_TIME_UTC; blobSizeInBytes=VALID_BLOB_SIZE_IN_BYTES; }},

            new TestParameters(){{ deviceId = VALID_DEVICEID; blobUri = VALID_BLOB_UTI; blobName = VALID_BLOB_NAME; lastUpdatedTime = null; enqueuedTimeUtc = VALID_ENQUEUED_TIME_UTC; blobSizeInBytes=VALID_BLOB_SIZE_IN_BYTES; }},
            new TestParameters(){{ deviceId = VALID_DEVICEID; blobUri = VALID_BLOB_UTI; blobName = VALID_BLOB_NAME; lastUpdatedTime = ""; enqueuedTimeUtc = VALID_ENQUEUED_TIME_UTC; blobSizeInBytes=VALID_BLOB_SIZE_IN_BYTES; }},
            new TestParameters(){{ deviceId = VALID_DEVICEID; blobUri = VALID_BLOB_UTI; blobName = VALID_BLOB_NAME; lastUpdatedTime = INVALID_LAST_UPDATE_TIME; enqueuedTimeUtc = VALID_ENQUEUED_TIME_UTC; blobSizeInBytes=VALID_BLOB_SIZE_IN_BYTES; }},
            new TestParameters(){{ deviceId = VALID_DEVICEID; blobUri = VALID_BLOB_UTI; blobName = VALID_BLOB_NAME; lastUpdatedTime = INVALID_DATETIME_OFFSET; enqueuedTimeUtc = VALID_ENQUEUED_TIME_UTC; blobSizeInBytes=VALID_BLOB_SIZE_IN_BYTES; }},

            new TestParameters(){{ deviceId = VALID_DEVICEID; blobUri = VALID_BLOB_UTI; blobName = VALID_BLOB_NAME; lastUpdatedTime = VALID_LAST_UPDATE_TIME; enqueuedTimeUtc = null; blobSizeInBytes=VALID_BLOB_SIZE_IN_BYTES; }},
            new TestParameters(){{ deviceId = VALID_DEVICEID; blobUri = VALID_BLOB_UTI; blobName = VALID_BLOB_NAME; lastUpdatedTime = VALID_LAST_UPDATE_TIME; enqueuedTimeUtc = ""; blobSizeInBytes=VALID_BLOB_SIZE_IN_BYTES; }},
            new TestParameters(){{ deviceId = VALID_DEVICEID; blobUri = VALID_BLOB_UTI; blobName = VALID_BLOB_NAME; lastUpdatedTime = VALID_LAST_UPDATE_TIME; enqueuedTimeUtc = INVALID_ENQUEUED_TIME_UTC; blobSizeInBytes=VALID_BLOB_SIZE_IN_BYTES; }},
            new TestParameters(){{ deviceId = VALID_DEVICEID; blobUri = VALID_BLOB_UTI; blobName = VALID_BLOB_NAME; lastUpdatedTime = VALID_LAST_UPDATE_TIME; enqueuedTimeUtc = INVALID_DATETIME_UTC; blobSizeInBytes=VALID_BLOB_SIZE_IN_BYTES; }},

            new TestParameters(){{ deviceId = VALID_DEVICEID; blobUri = VALID_BLOB_UTI; blobName = VALID_BLOB_NAME; lastUpdatedTime = VALID_LAST_UPDATE_TIME; enqueuedTimeUtc = VALID_ENQUEUED_TIME_UTC; blobSizeInBytes=null; }},
    };

    private static void assertFileUploadNotification(FileUploadNotification fileUploadNotification,
                                                     String expectedDeviceId, String expectedBlobUri, String expectedBlobName,
                                                     String expectedLastUpdatedTime, String expectedEnqueuedTimeUtc, Integer expectedBlobSizeInBytes)
    {
        assertNotNull(fileUploadNotification);

        String deviceId = Deencapsulation.getField(fileUploadNotification, "deviceId");
        String blobUri = Deencapsulation.getField(fileUploadNotification, "blobUri");
        String blobName = Deencapsulation.getField(fileUploadNotification, "blobName");
        String lastUpdatedTime = Deencapsulation.getField(fileUploadNotification, "lastUpdatedTime");
        String enqueuedTimeUtc = Deencapsulation.getField(fileUploadNotification, "enqueuedTimeUtc");
        Integer blobSizeInBytes = Deencapsulation.getField(fileUploadNotification, "blobSizeInBytes");

        assertEquals(expectedDeviceId, deviceId);
        assertEquals(expectedBlobUri, blobUri);
        assertEquals(expectedBlobName, blobName);
        assertEquals(expectedLastUpdatedTime, lastUpdatedTime);
        assertEquals(expectedEnqueuedTimeUtc, enqueuedTimeUtc);
        assertEquals(expectedBlobSizeInBytes, blobSizeInBytes);
    }

    private static String createJson(String deviceId, String blobUri, String blobName, String lastUpdatedTime, String enqueuedTimeUtc, Integer blobSizeInBytes)
    {
        return "{\n" +
                "    \"deviceId\": " + (deviceId == null ? "null" : "\"" + deviceId + "\"") + ",\n" +
                "    \"blobUri\": " + (blobUri == null ? "null" : "\"" + blobUri + "\"") + ",\n" +
                "    \"blobName\": " + (blobName == null ? "null" : "\"" + blobName + "\"") + ",\n" +
                "    \"lastUpdatedTime\": " + (lastUpdatedTime == null ? "null" : "\"" + lastUpdatedTime + "\"") + ",\n" +
                "    \"blobSizeInBytes\": " + blobSizeInBytes + ",\n" +
                "    \"enqueuedTimeUtc\": " + (enqueuedTimeUtc == null ? "null" : "\"" + enqueuedTimeUtc + "\"") + "\n" +
                "}";
    }

    /* Tests_SRS_FILE_UPLOAD_NOTIFICATION_21_001: [The constructor shall create an instance of the FileUploadNotification.] */
    /* Tests_SRS_FILE_UPLOAD_NOTIFICATION_21_002: [The constructor shall parse the provided json and initialize `correlationId`, `hostName`, `containerName`, `blobName`, and `sasToken` using the information in the json.] */
    @Test
    public void constructor_json_succeed()
    {
        // arrange
        String validJson = createJson(VALID_DEVICEID, VALID_BLOB_UTI, VALID_BLOB_NAME, VALID_LAST_UPDATE_TIME, VALID_ENQUEUED_TIME_UTC, VALID_BLOB_SIZE_IN_BYTES);

        // act
        FileUploadNotification fileUploadNotification = new FileUploadNotification(validJson);

        // assert
        assertFileUploadNotification(fileUploadNotification, VALID_DEVICEID, VALID_BLOB_UTI, VALID_BLOB_NAME, VALID_LAST_UPDATE_TIME, VALID_ENQUEUED_TIME_UTC, VALID_BLOB_SIZE_IN_BYTES);
    }

    /* Tests_SRS_FILE_UPLOAD_NOTIFICATION_21_003: [If the provided json is null, empty, or not valid, the constructor shall throws IllegalArgumentException.] */
    @Test (expected = IllegalArgumentException.class)
    public void constructor_null_json_failed()
    {
        // act
        new FileUploadNotification(null);
    }

    /* Tests_SRS_FILE_UPLOAD_NOTIFICATION_21_003: [If the provided json is null, empty, or not valid, the constructor shall throws IllegalArgumentException.] */
    @Test (expected = IllegalArgumentException.class)
    public void constructor_empty_json_failed()
    {
        // act
        new FileUploadNotification("");
    }

    /* Tests_SRS_FILE_UPLOAD_NOTIFICATION_21_003: [If the provided json is null, empty, or not valid, the constructor shall throws IllegalArgumentException.] */
    @Test (expected = IllegalArgumentException.class)
    public void constructor_invalid_json_failed()
    {
        // act
        new FileUploadNotification("{&*");
    }

    /* Tests_SRS_FILE_UPLOAD_NOTIFICATION_21_004: [If the provided json do not contains a valid `deviceId`, `blobUri`, `blobName`, `lastUpdatedTime`, `enqueuedTimeUtc`, and `blobSizeInBytes`, the constructor shall throws IllegalArgumentException.] */
    @Test
    public void constructor_json_failed()
    {
        for (TestParameters test:tests)
        {
            // arrange
            String invalidJson = createJson(test.deviceId, test.blobUri, test.blobName, test.lastUpdatedTime, test.enqueuedTimeUtc, test.blobSizeInBytes);

            // act
            try
            {
                new FileUploadResponse(invalidJson);
                System.out.println("Test failed:");
                System.out.println(invalidJson);
                assert false;
            }
            catch (IllegalArgumentException expected)
            {
                // Don't do anything, expected throw.
            }
        }
    }

    /* Tests_SRS_FILE_UPLOAD_NOTIFICATION_21_005: [The getDeviceId shall return the string stored in `deviceId`.] */
    /* Tests_SRS_FILE_UPLOAD_NOTIFICATION_21_006: [The getBlobUri shall return the string stored in `blobUri`.] */
    /* Tests_SRS_FILE_UPLOAD_NOTIFICATION_21_007: [The getBlobName shall return the string stored in `blobName`.] */
    /* Tests_SRS_FILE_UPLOAD_NOTIFICATION_21_008: [The getLastUpdateTime shall return the string stored in `lastUpdateTime`.] */
    /* Tests_SRS_FILE_UPLOAD_NOTIFICATION_21_009: [The getEnqueuedTimeUtc shall return the string stored in `enqueuedTimeUtc`.] */
    /* Tests_SRS_FILE_UPLOAD_NOTIFICATION_21_010: [The getBlobSizeInBytesTag shall return the integer stored in `blobSizeInBytes`.] */
    @Test
    public void getters_succeed()
    {
        // arrange
        String validJson = createJson(VALID_DEVICEID, VALID_BLOB_UTI, VALID_BLOB_NAME, VALID_LAST_UPDATE_TIME, VALID_ENQUEUED_TIME_UTC, VALID_BLOB_SIZE_IN_BYTES);
        FileUploadNotification fileUploadNotification = new FileUploadNotification(validJson);
        Date expectedLastUpdatedTime = new Date(VALID_LAST_UPDATE_TIME_IN_MILLISECONDS);
        Date expectedEnqueuedTimeUtc = new Date(VALID_ENQUEUED_TIME_UTC_IN_MILLISECONDS);

        // act
        // assert
        assertEquals(VALID_DEVICEID, fileUploadNotification.getDeviceId());
        assertEquals(VALID_BLOB_UTI, fileUploadNotification.getBlobUri());
        assertEquals(VALID_BLOB_NAME, fileUploadNotification.getBlobName());
        assertEquals(expectedLastUpdatedTime, fileUploadNotification.getLastUpdatedTime());
        assertEquals(expectedEnqueuedTimeUtc, fileUploadNotification.getEnqueuedTimeUtc());
        assertEquals(VALID_BLOB_SIZE_IN_BYTES, fileUploadNotification.getBlobSizeInBytesTag());
    }

}
