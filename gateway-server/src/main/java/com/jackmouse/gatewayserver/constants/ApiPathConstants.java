package com.jackmouse.gatewayserver.constants;

public class ApiPathConstants {
    // API版本
    public static final String API_VERSION = "/api/v1";
    
    // 公开API路径
    public static final String PUBLIC_API = API_VERSION + "/public";
    public static final String AUTH_API = API_VERSION + "/auth";
    
    // 受保护API路径
    public static final String USER_API = API_VERSION + "/user";
    public static final String ADMIN_API = API_VERSION + "/admin";
    public static final String PROTECTED_API = API_VERSION + "/protected";
    
    // 具体API端点
    public static final String LOGIN = AUTH_API + "/login";
    public static final String REGISTER = AUTH_API + "/register";
    public static final String USER_PROFILE = USER_API + "/profile";
} 