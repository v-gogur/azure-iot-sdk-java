// Copyright (c) Microsoft. All rights reserved.
// Licensed under the MIT license. See LICENSE file in the project root for full license information.

package com.microsoft.azure.sdk.iot.deps.serializer;

import mockit.Deencapsulation;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Unit tests for File Upload Status serializer
 */
public class FileUploadStatusTest
{
    private static final String VALID_CORRELATION_ID = "somecorrelationid";
    private static final String INVALID_CORRELATION_ID = "some\u1234correlationid";
    private static final String VALID_STATUS_DESCRIPTION = "Description of status";
    private static final String INVALID_STATUS_DESCRIPTION = "some\u1234 description of status";
    private static final Boolean VALID_IS_SUCCESS = true;
    private static final Integer VALID_STATUS_CODE = 200;

    private static void assertFileUploadStatus(FileUploadStatus fileUploadStatus, String expectedCorrelationId, Boolean expectedIsSuccess, Integer expectedStatusCode, String expectedStatusDescription)
    {
        assertNotNull(fileUploadStatus);

        String correlationId = Deencapsulation.getField(fileUploadStatus, "correlationId");
        Boolean isSuccess = Deencapsulation.getField(fileUploadStatus, "isSuccess");
        Integer statusCode = Deencapsulation.getField(fileUploadStatus, "statusCode");
        String statusDescription = Deencapsulation.getField(fileUploadStatus, "statusDescription");

        assertEquals(expectedCorrelationId, correlationId);
        assertEquals(expectedIsSuccess, isSuccess);
        assertEquals(expectedStatusCode, statusCode);
        assertEquals(expectedStatusDescription, statusDescription);
    }

    private static String createJson(String correlationId, Boolean isSuccess, Integer statusCode, String statusDescription)
    {
        return "{\n" +
                "    \"correlationId\": " + (correlationId == null ? "null" : "\"" + correlationId + "\"") + ",\n" +
                "    \"isSuccess\": " + isSuccess + ",\n" +
                "    \"statusCode\": " + statusCode + ",\n" +
                "    \"statusDescription\": " + (statusDescription == null ? "null" : "\"" + statusDescription + "\"") + "\n" +
                "}";
    }

    private static class TestParameters
    {
        String correlationId;
        Boolean isSuccess;
        Integer statusCode;
        String statusDescription;
    }
    private static final TestParameters[] tests = new TestParameters[]
    {
            new TestParameters(){{ correlationId = null; isSuccess = true; statusCode = 200;  statusDescription = VALID_STATUS_DESCRIPTION; }},
            new TestParameters(){{ correlationId = ""; isSuccess = true; statusCode = 200;  statusDescription = VALID_STATUS_DESCRIPTION; }},
            new TestParameters(){{ correlationId = INVALID_CORRELATION_ID; isSuccess = true; statusCode = 200; statusDescription = VALID_STATUS_DESCRIPTION; }},

            new TestParameters(){{ correlationId = VALID_CORRELATION_ID; isSuccess = null; statusCode = 200; statusDescription = VALID_STATUS_DESCRIPTION; }},

            new TestParameters(){{ correlationId = VALID_CORRELATION_ID; isSuccess = true; statusCode = null; statusDescription = VALID_STATUS_DESCRIPTION; }},

            new TestParameters(){{ correlationId = VALID_CORRELATION_ID; isSuccess = true; statusCode = 200; statusDescription = null; }},
            new TestParameters(){{ correlationId = VALID_CORRELATION_ID; isSuccess = true; statusCode = 200; statusDescription = ""; }},
            new TestParameters(){{ correlationId = VALID_CORRELATION_ID; isSuccess = true; statusCode = 200; statusDescription = INVALID_STATUS_DESCRIPTION; }},
    };
    
    /* Tests_SRS_FILE_UPLOAD_STATUS_21_001: [The constructor shall create an instance of the FileUploadStatus.] */
    /* Tests_SRS_FILE_UPLOAD_STATUS_21_002: [The constructor shall set the `correlationId`, `isSuccess`, `statusCode`, and `statusDescription` in the new class with the provided parameters.] */
    @Test
    public void constructor_succeed()
    {
        // act
        FileUploadStatus fileUploadStatus = new FileUploadStatus(VALID_CORRELATION_ID, VALID_IS_SUCCESS, VALID_STATUS_CODE, VALID_STATUS_DESCRIPTION);

        // assert
        assertFileUploadStatus(fileUploadStatus, VALID_CORRELATION_ID, VALID_IS_SUCCESS, VALID_STATUS_CODE, VALID_STATUS_DESCRIPTION);
    }

    /* Tests_SRS_FILE_UPLOAD_STATUS_21_003: [If one of the provided parameters is null, empty, or not valid, the constructor shall throws IllegalArgumentException.] */
    @Test
    public void constructor_failed()
    {
        for (TestParameters test:tests)
        {
            // act
            try
            {
                new FileUploadStatus(test.correlationId, test.isSuccess, test.statusCode, test.statusDescription);
                System.out.println("Test failed: correlationId=" + test.correlationId + ", isSuccess=" + test.isSuccess +
                        ", statusCode=" + test.statusCode + ", statusDescription=" + test.statusDescription);
                assert false;
            }
            catch (IllegalArgumentException expected)
            {
                // Don't do anything, expected throw.
            }
        }
    }

    /* Tests_SRS_FILE_UPLOAD_STATUS_21_004: [The updateStatus shall set the `isSuccess`, `statusCode` and `statusDescription` with the provided information.] */
    /* Tests_SRS_FILE_UPLOAD_STATUS_21_007: [The updateStatus shall return a string with a json that represents the contend of the FileUploadStatus.] */
    @Test
    public void updateStatus_succeed()
    {
        // arrange
        FileUploadStatus fileUploadStatus = new FileUploadStatus(VALID_CORRELATION_ID, VALID_IS_SUCCESS, VALID_STATUS_CODE, VALID_STATUS_DESCRIPTION);

        // act
        String json = fileUploadStatus.updateStatus(false, 205, "new status description");

        // assert
        assertFileUploadStatus(fileUploadStatus, VALID_CORRELATION_ID, false, 205, "new status description");
        Helpers.assertJson(json, createJson(VALID_CORRELATION_ID, false, 205, "new status description"));
    }

    /* Tests_SRS_FILE_UPLOAD_STATUS_21_005: [If `isSuccess`, or `statusCode` is null, the updateStatus shall throws IllegalArgumentException.] */
    @Test (expected = IllegalArgumentException.class)
    public void updateStatus_null_isSuccess_failed()
    {
        // arrange
        FileUploadStatus fileUploadStatus = new FileUploadStatus(VALID_CORRELATION_ID, VALID_IS_SUCCESS, VALID_STATUS_CODE, VALID_STATUS_DESCRIPTION);

        // act
        fileUploadStatus.updateStatus(null, 205, "new status description");
    }

    /* Tests_SRS_FILE_UPLOAD_STATUS_21_005: [If `isSuccess`, or `statusCode` is null, the updateStatus shall throws IllegalArgumentException.] */
    @Test (expected = IllegalArgumentException.class)
    public void updateStatus_null_statusCode_failed()
    {
        // arrange
        FileUploadStatus fileUploadStatus = new FileUploadStatus(VALID_CORRELATION_ID, VALID_IS_SUCCESS, VALID_STATUS_CODE, VALID_STATUS_DESCRIPTION);

        // act
        fileUploadStatus.updateStatus(false, null, "new status description");
    }

    /* Tests_SRS_FILE_UPLOAD_STATUS_21_006: [If `statusDescription` is null, empty, or not valid, the updateStatus shall throws IllegalArgumentException.] */
    @Test (expected = IllegalArgumentException.class)
    public void updateStatus_null_statusDescription_failed()
    {
        // arrange
        FileUploadStatus fileUploadStatus = new FileUploadStatus(VALID_CORRELATION_ID, VALID_IS_SUCCESS, VALID_STATUS_CODE, VALID_STATUS_DESCRIPTION);

        // act
        fileUploadStatus.updateStatus(false, 205, null);
    }

    /* Tests_SRS_FILE_UPLOAD_STATUS_21_006: [If `statusDescription` is null, empty, or not valid, the updateStatus shall throws IllegalArgumentException.] */
    @Test (expected = IllegalArgumentException.class)
    public void updateStatus_empty_statusDescription_failed()
    {
        // arrange
        FileUploadStatus fileUploadStatus = new FileUploadStatus(VALID_CORRELATION_ID, VALID_IS_SUCCESS, VALID_STATUS_CODE, VALID_STATUS_DESCRIPTION);

        // act
        fileUploadStatus.updateStatus(false, 205, "");
    }

    /* Tests_SRS_FILE_UPLOAD_STATUS_21_006: [If `statusDescription` is null, empty, or not valid, the updateStatus shall throws IllegalArgumentException.] */
    @Test (expected = IllegalArgumentException.class)
    public void updateStatus_invalid_statusDescription_failed()
    {
        // arrange
        FileUploadStatus fileUploadStatus = new FileUploadStatus(VALID_CORRELATION_ID, VALID_IS_SUCCESS, VALID_STATUS_CODE, VALID_STATUS_DESCRIPTION);

        // act
        fileUploadStatus.updateStatus(false, 205, INVALID_STATUS_DESCRIPTION);
    }

    /* Tests_SRS_FILE_UPLOAD_STATUS_21_008: [The toJson shall return a string with a json that represents the contend of the FileUploadStatus.] */
    @Test
    public void toJson_succeed()
    {
        // arrange
        FileUploadStatus fileUploadStatus = new FileUploadStatus(VALID_CORRELATION_ID, VALID_IS_SUCCESS, VALID_STATUS_CODE, VALID_STATUS_DESCRIPTION);
        String expectedJson = createJson(VALID_CORRELATION_ID, VALID_IS_SUCCESS, VALID_STATUS_CODE, VALID_STATUS_DESCRIPTION);

        // act
        String json = fileUploadStatus.toJson();

        // assert
        Helpers.assertJson(json, expectedJson);
    }
}
