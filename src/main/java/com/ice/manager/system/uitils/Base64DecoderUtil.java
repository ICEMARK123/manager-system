package com.ice.manager.system.uitils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ice.manager.system.model.UserAccess;

import java.util.Base64;

/**
 * @ Author     ：lhl.
 * @ Date       ：Created in 14:46 2024/4/25
 * @ Description：Base64 Decoder
 * @ Modified By：
 * @Version:
 */

public class Base64DecoderUtil {
    public static UserAccess decodeHeader(String header) throws JsonProcessingException {
        byte[] decodedBytes = Base64.getDecoder().decode(header);
        String decodedString = new String(decodedBytes);
        return new ObjectMapper().readValue(decodedString, UserAccess.class);
    }
}
