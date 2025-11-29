/*
 * CLASS PURPOSE: Utility class for file downloading operations from URLs.
 * Handles file downloads with proper naming conventions and error handling.
 */
package org.cowary.arttrackerback.util;

import lombok.experimental.UtilityClass;

import java.io.File;

@UtilityClass
public class FileUtil {

    public static String path = "src/main/resources/images/";

    /**
     * Downloads a file from the given URL to the specified path with the given filename.
     * @param urlString The URL to download from
     * @param fileName The name for the downloaded file (without extension)
     * @return The downloaded File object
     * @throws RuntimeException if download fails or file doesn't exist
     */
    public static File downloadFile(String urlString, String fileName) {
        // TODO: Implement actual file download logic
        // For now, throw an exception to indicate this is not implemented
        throw new RuntimeException("File download not implemented");
    }
}