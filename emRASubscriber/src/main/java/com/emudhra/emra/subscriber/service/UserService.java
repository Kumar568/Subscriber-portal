package com.emudhra.emra.subscriber.service;

import java.util.HashMap;

import com.emudhra.emra.subscriber.dto.LoginRequestDto;

public interface UserService {

	HashMap<String, String> authenticateUser(LoginRequestDto loginRequestDto) throws Exception;
}
