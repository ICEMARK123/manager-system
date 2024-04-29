package com.ice.manager.system.uitils;

import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @ Author     ：lhl.
 * @ Date       ：Created in 14:58 2024/4/29
 * @ Description：File Operator
 * @ Modified By：
 * @Version:
 */
public class FileReadWriteUtil {
    public static void writeJsonToFile(String filePath, String json) {
        try {
            Files.write(Paths.get(filePath), json.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String readJsonFromFile(String filePath) {
        try {
            return new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
