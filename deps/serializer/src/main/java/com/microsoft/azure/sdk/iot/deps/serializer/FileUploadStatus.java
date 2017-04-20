// Copyright (c) Microsoft. All rights reserved.
// Licensed under the MIT license. See LICENSE file in the project root for full license information.

package com.microsoft.azure.sdk.iot.deps.serializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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
    private static final String CORRELATION_ID_TAG = "correlationId";
    @Expose(serialize = true, deserialize = true)
    @SerializedName(CORRELATION_ID_TAG)
    private String correlationId = null;

    private static final String IS_SUCCESS_TAG = "isSuccess";
    @Expose(serialize = true, deserialize = true)
    @SerializedName(IS_SUCCESS_TAG)
    private Boolean isSuccess = null;

    private static final String STATUS_CODE_TAG = "statusCode";
    @Expose(serialize = true, deserialize = true)
    @SerializedName(STATUS_CODE_TAG)
    private Integer statusCode = null;

    private static final String STATUS_DESCRIPTION_TAG = "statusDescription";
    @Expose(serialize = true, deserialize = true)
    @SerializedName(STATUS_DESCRIPTION_TAG)
    private String statusDescription = null;

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
    {
        /* Codes_SRS_FILE_UPLOAD_STATUS_21_001: [The constructor shall create an instance of the FileUploadStatus.] */
        /* Codes_SRS_FILE_UPLOAD_STATUS_21_002: [The constructor shall set the `correlationId`, `isSuccess`, `statusCode`, and `statusDescription` in the new class with the provided parameters.] */
        /* Codes_SRS_FILE_UPLOAD_STATUS_21_003: [If one of the provided parameters is null, empty, or not valid, the constructor shall throws IllegalArgumentException.] */
        setCorrelationId(correlationId);
        updateStatus(isSuccess, statusCode, statusDescription);
    }

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
    {
        /* Codes_SRS_FILE_UPLOAD_STATUS_21_005: [If `isSuccess`, or `statusCode` is null, the updateStatus shall throws IllegalArgumentException.] */
        Utility.validateBoolean(isSuccess);
        Utility.validateInteger(statusCode);

        /* Codes_SRS_FILE_UPLOAD_STATUS_21_006: [If `statusDescription` is null, empty, or not valid, the updateStatus shall throws IllegalArgumentException.] */
        Utility.validateStringUTF8(statusDescription);

        /* Codes_SRS_FILE_UPLOAD_STATUS_21_004: [The updateStatus shall set the `isSuccess`, `statusCode` and `statusDescription` with the provided information.] */
        this.isSuccess = isSuccess;
        this.statusCode = statusCode;
        this.statusDescription = statusDescription;

        /* Codes_SRS_FILE_UPLOAD_STATUS_21_007: [The updateStatus shall return a string with a json that represents the contend of the FileUploadStatus.] */
        return toJson();
    }

    /**
     * Convert this class in a valid json.
     *
     * @return a valid json that represents the content of this class.
     */
    public String toJson()
    {
        Gson gson = new GsonBuilder().serializeNulls().create();

        /* Codes_SRS_FILE_UPLOAD_STATUS_21_008: [The toJson shall return a string with a json that represents the contend of the FileUploadStatus.] */
        return gson.toJson(this);
    }

    private void setCorrelationId(String correlationId)
    {
        Utility.validateStringUTF8(correlationId);
        this.correlationId = correlationId;
    }
}
