package com.ice.manager.system.model;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @ Author     ：lhl.
 * @ Date       ：Created in 14:47 2024/4/25
 * @ Description：user access
 * @ Modified By：
 * @Version:
 */

@Setter
@Getter
public class UserAccess {
    private Long userId;
    private String accountName;
    private String role;
    List<String> endpoints;
}
