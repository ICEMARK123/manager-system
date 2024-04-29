package com.ice.manager.system.service;

import com.alibaba.fastjson2.JSON;
import com.ice.manager.system.model.UserAccess;
import com.ice.manager.system.uitils.FileReadWriteUtil;
import org.springframework.stereotype.Service;

/**
 * @ Author     ：lhl.
 * @ Date       ：Created in 14:49 2024/4/25
 * @ Description：user service
 * @ Modified By：
 * @Version:
 */

@Service
public class UserService {

    public final static String filePath = "src/main/resources/user/user-access.json";

    public void addUserAccess(UserAccess userAccess) {
        FileReadWriteUtil.writeJsonToFile(filePath, JSON.toJSONString(userAccess));
    }

    public boolean hasAccess(Long userId, String resource) {
        String s = FileReadWriteUtil.readJsonFromFile(filePath);
        UserAccess userAccess = JSON.parseObject(s, UserAccess.class);
        if (userAccess != null) {
            return userAccess.getUserId().equals(userId) && userAccess.getEndpoints().contains(resource);
        }
        return false;
    }

}
