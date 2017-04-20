// Copyright (c) Microsoft. All rights reserved.
// Licensed under the MIT license. See LICENSE file in the project root for full license information.

package com.microsoft.azure.sdk.iot.deps.serializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import jdk.nashorn.internal.ir.annotations.Ignore;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Representation of the notification of a single File Upload, with a Json deserializer.
 * Ex of JSON format:
 *  {
 *      "deviceId":"mydevice",
 *      "blobUri":"https://{storage account}.blob.core.windows.net/{container name}/mydevice/myfile.jpg",
 *      "blobName":"mydevice/myfile.jpg",
 *      "lastUpdatedTime":"2016-06-01T21:22:41+00:00",
 *      "blobSizeInBytes":1234,
 *      "enqueuedTimeUtc":"2016-06-01T21:22:43.7996883Z"
 *  }
 */
public class FileUploadNotification
{
    private static final String DEVICE_ID_TAG = "deviceId";
    @Expose(serialize = true, deserialize = true)
    @SerializedName(DEVICE_ID_TAG)
    private String deviceId = null;

    private static final String BLOB_URI_TAG = "blobUri";
    @Expose(serialize = true, deserialize = true)
    @SerializedName(BLOB_URI_TAG)
    private String blobUri = null;

    private static final String BLOB_NAME_TAG = "blobName";
    @Expose(serialize = true, deserialize = true)
    @SerializedName(BLOB_NAME_TAG)
    private String blobName = null;

    private static final String LAST_UPDATED_TIME_TAG = "lastUpdatedTime";
    @Expose(serialize = true, deserialize = true)
    @SerializedName(LAST_UPDATED_TIME_TAG)
    private String lastUpdatedTime = null;
    @Ignore
    private Date lastUpdatedTimeDate;

    private static final String BLOB_SIZE_IN_BYTES_TAG = "blobSizeInBytes";
    @Expose(serialize = true, deserialize = true)
    @SerializedName(BLOB_SIZE_IN_BYTES_TAG)
    private Integer blobSizeInBytes = null;

    private static final String ENQUEUED_TIME_UTC_TAG = "enqueuedTimeUtc";
    @Expose(serialize = true, deserialize = true)
    @SerializedName(ENQUEUED_TIME_UTC_TAG)
    private String enqueuedTimeUtc = null;
    @Ignore
    private Date enqueuedTimeUtcDate;

    /**
     * CONSTRUCTOR
     * Create an instance of the FileUploadNotification using the information in the provided json.
     *
     * @param json is the string that contains a valid json with the FileUpload notification.
     * @throws IllegalArgumentException if the json is null, empty, or not valid.
     */
    public FileUploadNotification(String json) throws IllegalArgumentException
    {
        /* Codes_SRS_FILE_UPLOAD_NOTIFICATION_21_001: [The constructor shall create an instance of the FileUploadNotification.] */
        Gson gson = new GsonBuilder().serializeNulls().create();
        FileUploadNotification newFileUploadNotification;

        /* Codes_SRS_FILE_UPLOAD_NOTIFICATION_21_003: [If the provided json is null, empty, or not valid, the constructor shall throws IllegalArgumentException.] */
        Utility.validateStringUTF8(json);
        try
        {
            newFileUploadNotification = gson.fromJson(json, FileUploadNotification.class);
        }
        catch (Exception malformed)
        {
            throw new IllegalArgumentException("Malformed json:" + malformed);
        }

        /* Codes_SRS_FILE_UPLOAD_NOTIFICATION_21_004: [If the provided json do not contains a valid `deviceId`, `blobUri`, `blobName`, `lastUpdatedTime`, `enqueuedTimeUtc`, and `blobSizeInBytes`, the constructor shall throws IllegalArgumentException.] */
        Utility.validateStringUTF8(newFileUploadNotification.deviceId);
        Utility.validateStringUTF8(newFileUploadNotification.blobUri);
        Utility.validateStringUTF8(newFileUploadNotification.blobName);
        Utility.validateStringUTF8(newFileUploadNotification.enqueuedTimeUtc);
        Utility.validateStringUTF8(newFileUploadNotification.lastUpdatedTime);
        Utility.validateInteger(newFileUploadNotification.blobSizeInBytes);

        /* Codes_SRS_FILE_UPLOAD_NOTIFICATION_21_002: [The constructor shall parse the provided json and initialize `correlationId`, `hostName`, `containerName`, `blobName`, and `sasToken` using the information in the json.] */
        this.deviceId = newFileUploadNotification.deviceId;
        this.blobUri = newFileUploadNotification.blobUri;
        this.blobName = newFileUploadNotification.blobName;
        this.lastUpdatedTime = newFileUploadNotification.lastUpdatedTime;
        this.enqueuedTimeUtc = newFileUploadNotification.enqueuedTimeUtc;
        this.blobSizeInBytes = newFileUploadNotification.blobSizeInBytes;
        this.enqueuedTimeUtcDate = Utility.getDateTimeUtc(this.enqueuedTimeUtc);
        this.lastUpdatedTimeDate = Utility.getDateTimeOffset(this.lastUpdatedTime);
    }

    /**
     * Getter for the device identification.
     *
     * @return string with the device identification.
     */
    public String getDeviceId()
    {
        /* Codes_SRS_FILE_UPLOAD_NOTIFICATION_21_005: [The getDeviceId shall return the string stored in `deviceId`.] */
        return this.deviceId;
    }

    /**
     * Getter for the file uri.
     *
     * @return string with the blob URI.
     */
    public String getBlobUri()
    {
        /* Codes_SRS_FILE_UPLOAD_NOTIFICATION_21_006: [The getBlobUri shall return the string stored in `blobUri`.] */
        return this.blobUri;
    }

    /**
     * Getter for the file name.
     *
     * @return string with the blob name.
     */
    public String getBlobName()
    {
        /* Codes_SRS_FILE_UPLOAD_NOTIFICATION_21_007: [The getBlobName shall return the string stored in `blobName`.] */
        return this.blobName;
    }

    /**
     * Getter for the last update time.
     *
     * @return string with the last update time.
     */
    public Date getLastUpdatedTime()
    {
        /* Codes_SRS_FILE_UPLOAD_NOTIFICATION_21_008: [The getLastUpdateTime shall return the string stored in `lastUpdateTime`.] */
        return this.lastUpdatedTimeDate;
    }

    /**
     * Getter for the enqueued time UTC.
     *
     * @return string with the enqueued time UTC.
     */
    public Date getEnqueuedTimeUtc()
    {
        /* Codes_SRS_FILE_UPLOAD_NOTIFICATION_21_009: [The getEnqueuedTimeUtc shall return the string stored in `enqueuedTimeUtcDate`.] */

        return this.enqueuedTimeUtcDate;
    }

    /**
     * Getter for the file size.
     *
     * @return integer with the blob size in bytes.
     */
    public Integer getBlobSizeInBytesTag()
    {
        /* Codes_SRS_FILE_UPLOAD_NOTIFICATION_21_010: [The getBlobSizeInBytesTag shall return the integer stored in `blobSizeInBytes`.] */
        return this.blobSizeInBytes;
    }
}
