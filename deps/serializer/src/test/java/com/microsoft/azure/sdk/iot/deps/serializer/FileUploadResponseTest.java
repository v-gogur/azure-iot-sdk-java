// Copyright (c) Microsoft. All rights reserved.
// Licensed under the MIT license. See LICENSE file in the project root for full license information.

package com.microsoft.azure.sdk.iot.deps.serializer;

import mockit.Deencapsulation;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Unit tests for File Upload Response deserializer
 */
public class FileUploadResponseTest
{
    private static final String VALID_CORRELATION_ID = "somecorrelationid";
    private static final String INVALID_CORRELATION_ID = "some\u1234correlationid";
    private static final String VALID_HOST_NAME = "contoso.azure-devices.net";
    private static final String INVALID_HOST_NAME = "\u1234contoso.azure-devices.net";
    private static final String VALID_CONTAINER_NAME = "testcontainer";
    private static final String INVALID_CONTAINER_NAME = "testcontainer\u1234";
    private static final String VALID_BLOB_NAME = "test-device1/image.jpg";
    private static final String INVALID_BLOB_NAME = "\u1234 test-device1/image.jpg";
    private static final String VALID_SAS_TOKEN = "1234asdfSAStoken";
    private static final String INVALID_SAS_TOKEN = "\u1234asdfSAStoken";

    private static void assertFileUploadResponse(FileUploadResponse fileUploadResponse, String expectedHostName, String expectedContainerName, String expectedCorrelationId, String expectedBlobName, String expectedSasToken)
    {
        assertNotNull(fileUploadResponse);

        String correlationId = Deencapsulation.getField(fileUploadResponse, "correlationId");
        String hostName = Deencapsulation.getField(fileUploadResponse, "hostName");
        String containerName = Deencapsulation.getField(fileUploadResponse, "containerName");
        String blobName = Deencapsulation.getField(fileUploadResponse, "blobName");
        String sasToken = Deencapsulation.getField(fileUploadResponse, "sasToken");

        assertEquals(expectedCorrelationId, correlationId);
        assertEquals(expectedHostName, hostName);
        assertEquals(expectedContainerName, containerName);
        assertEquals(expectedBlobName, blobName);
        assertEquals(expectedSasToken, sasToken);
    }

    private static String createJson(String hostName, String containerName, String correlationId, String blobName, String sasToken)
    {
        return "{\n" +
                "    \"correlationId\": " + (correlationId == null ? "null" : "\"" + correlationId + "\"") + ",\n" +
                "    \"hostName\": " + (hostName == null ? "null" : "\"" + hostName + "\"") + ",\n" +
                "    \"containerName\": " + (containerName == null ? "null" : "\"" + containerName + "\"") + ",\n" +
                "    \"blobName\": " + (blobName == null ? "null" : "\"" + blobName + "\"") + ",\n" +
                "    \"sasToken\": " + (sasToken == null ? "null" : "\"" + sasToken + "\"") + "\n" +
                "}";
    }

    private static class TestParameters
    {
        String correlationId;
        String hostName;
        String containerName;
        String blobName;
        String sasToken;
    }
    private static final TestParameters[] tests = new TestParameters[]
    {
            new TestParameters(){{ correlationId = VALID_CORRELATION_ID; hostName = null; containerName = VALID_CONTAINER_NAME; blobName = VALID_BLOB_NAME; sasToken = VALID_SAS_TOKEN; }},
            new TestParameters(){{ correlationId = VALID_CORRELATION_ID; hostName = ""; containerName = VALID_CONTAINER_NAME; blobName = VALID_BLOB_NAME; sasToken = VALID_SAS_TOKEN; }},
            new TestParameters(){{ correlationId = VALID_CORRELATION_ID; hostName = INVALID_HOST_NAME; containerName = VALID_CONTAINER_NAME; blobName = VALID_BLOB_NAME;   sasToken = VALID_SAS_TOKEN; }},

            new TestParameters(){{ correlationId = VALID_CORRELATION_ID; hostName = VALID_HOST_NAME; containerName = null; blobName = VALID_BLOB_NAME; sasToken = VALID_SAS_TOKEN; }},
            new TestParameters(){{ correlationId = VALID_CORRELATION_ID; hostName = VALID_HOST_NAME; containerName = ""; blobName = VALID_BLOB_NAME; sasToken = VALID_SAS_TOKEN; }},
            new TestParameters(){{ correlationId = VALID_CORRELATION_ID; hostName = VALID_HOST_NAME; containerName = INVALID_CONTAINER_NAME; blobName = VALID_BLOB_NAME;   sasToken = VALID_SAS_TOKEN; }},

            new TestParameters(){{ correlationId = null; hostName = VALID_HOST_NAME; containerName = VALID_CONTAINER_NAME; blobName = VALID_BLOB_NAME; sasToken = VALID_SAS_TOKEN; }},
            new TestParameters(){{ correlationId = ""; hostName = VALID_HOST_NAME; containerName = VALID_CONTAINER_NAME; blobName = VALID_BLOB_NAME; sasToken = VALID_SAS_TOKEN; }},
            new TestParameters(){{ correlationId = INVALID_CORRELATION_ID; hostName = VALID_HOST_NAME; containerName = VALID_CONTAINER_NAME; blobName = VALID_BLOB_NAME;   sasToken = VALID_SAS_TOKEN; }},

            new TestParameters(){{ correlationId = VALID_CORRELATION_ID; hostName = VALID_HOST_NAME; containerName = VALID_CONTAINER_NAME; blobName = null; sasToken = VALID_SAS_TOKEN; }},
            new TestParameters(){{ correlationId = VALID_CORRELATION_ID; hostName = VALID_HOST_NAME; containerName = VALID_CONTAINER_NAME; blobName = ""; sasToken = VALID_SAS_TOKEN; }},
            new TestParameters(){{ correlationId = VALID_CORRELATION_ID; hostName = VALID_HOST_NAME; containerName = VALID_CONTAINER_NAME; blobName = INVALID_BLOB_NAME; sasToken = VALID_SAS_TOKEN; }},

            new TestParameters(){{ correlationId = VALID_CORRELATION_ID; hostName = VALID_HOST_NAME; containerName = VALID_CONTAINER_NAME; blobName = VALID_BLOB_NAME; sasToken = null; }},
            new TestParameters(){{ correlationId = VALID_CORRELATION_ID; hostName = VALID_HOST_NAME; containerName = VALID_CONTAINER_NAME; blobName = VALID_BLOB_NAME; sasToken = ""; }},
            new TestParameters(){{ correlationId = VALID_CORRELATION_ID; hostName = VALID_HOST_NAME; containerName = VALID_CONTAINER_NAME; blobName = VALID_BLOB_NAME; sasToken = INVALID_SAS_TOKEN; }},
    };

    /* Tests_SRS_FILE_UPLOAD_RESPONSE_21_001: [The constructor shall create an instance of the FileUploadResponse.] */
    /* Tests_SRS_FILE_UPLOAD_RESPONSE_21_002: [The constructor shall parse the provided json and initialize `correlationId`, `hostName`, `containerName`, `blobName`, and `sasToken` using the information in the json.] */
    @Test
    public void constructor_json_succeed()
    {
        // arrange
        String validJson = createJson(VALID_HOST_NAME, VALID_CONTAINER_NAME, VALID_CORRELATION_ID, VALID_BLOB_NAME, VALID_SAS_TOKEN);

        // act
        FileUploadResponse fileUploadResponse = new FileUploadResponse(validJson);

        // assert
        assertFileUploadResponse(fileUploadResponse, VALID_HOST_NAME, VALID_CONTAINER_NAME, VALID_CORRELATION_ID, VALID_BLOB_NAME, VALID_SAS_TOKEN);
    }

    /* Tests_SRS_FILE_UPLOAD_RESPONSE_21_003: [If the provided json is null, empty, or not valid, the constructor shall throws IllegalArgumentException.] */
    @Test (expected = IllegalArgumentException.class)
    public void constructor_null_json_failed()
    {
        // act
        new FileUploadResponse(null);
    }

    /* Tests_SRS_FILE_UPLOAD_RESPONSE_21_003: [If the provided json is null, empty, or not valid, the constructor shall throws IllegalArgumentException.] */
    @Test (expected = IllegalArgumentException.class)
    public void constructor_empty_json_failed()
    {
        // act
        new FileUploadResponse("");
    }

    /* Tests_SRS_FILE_UPLOAD_RESPONSE_21_003: [If the provided json is null, empty, or not valid, the constructor shall throws IllegalArgumentException.] */
    @Test (expected = IllegalArgumentException.class)
    public void constructor_invalid_json_failed()
    {
        // act
        new FileUploadResponse("{&*");
    }

    /* Tests_SRS_FILE_UPLOAD_RESPONSE_21_004: [If the provided json do not contains a valid `correlationId`, `hostName`, `containerName`, `blobName`, and `sasToken`, the constructor shall throws IllegalArgumentException.] */
    @Test
    public void constructor_json_failed()
    {
        for (TestParameters test:tests)
        {
            // arrange
            String invalidJson = createJson(test.hostName, test.containerName, test.correlationId, test.blobName, test.sasToken);

            // act
            try
            {
                new FileUploadResponse(invalidJson);
                System.out.println("Test failed: hostName=" + test.hostName + ", containerName=" + test.containerName +
                        ", correlationId=" + test.correlationId + ", blobName=" + test.blobName + ", sasToken=" + test.sasToken);
                assert false;
            }
            catch (IllegalArgumentException expected)
            {
                // Don't do anything, expected throw.
            }
        }
    }

    /* Tests_SRS_FILE_UPLOAD_RESPONSE_21_005: [The getCorrelationId shall return the string stored in `correlationId`.] */
    /* Tests_SRS_FILE_UPLOAD_RESPONSE_21_006: [The getHostName shall return the string stored in `hostName`.] */
    /* Tests_SRS_FILE_UPLOAD_RESPONSE_21_007: [The getContainerName shall return the string stored in `containerName`.] */
    /* Tests_SRS_FILE_UPLOAD_RESPONSE_21_008: [The getBlobName shall return the string stored in `blobName`.] */
    /* Tests_SRS_FILE_UPLOAD_RESPONSE_21_009: [The getSasToken shall return the string stored in `sasToken`.] */
    @Test
    public void getters_succeed()
    {
        // arrange
        String validJson = createJson(VALID_HOST_NAME, VALID_CONTAINER_NAME, VALID_CORRELATION_ID, VALID_BLOB_NAME, VALID_SAS_TOKEN);
        FileUploadResponse fileUploadResponse = new FileUploadResponse(validJson);

        // act
        // assert
        assertEquals(VALID_CORRELATION_ID, fileUploadResponse.getCorrelationId());
        assertEquals(VALID_HOST_NAME, fileUploadResponse.getHostName());
        assertEquals(VALID_CONTAINER_NAME, fileUploadResponse.getContainerName());
        assertEquals(VALID_BLOB_NAME, fileUploadResponse.getBlobName());
        assertEquals(VALID_SAS_TOKEN, fileUploadResponse.getSasToken());
    }
}
