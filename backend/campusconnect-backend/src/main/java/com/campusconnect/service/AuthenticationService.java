package com.campusconnect.service;

import com.campusconnect.dto.LoginRequest;
import com.campusconnect.dto.LoginResponse;

public interface AuthenticationService {

    LoginResponse login(LoginRequest request);
}
