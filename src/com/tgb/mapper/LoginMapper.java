package com.tgb.mapper;

import java.util.List;

import com.tgb.model.LoginUser;

public interface LoginMapper {
	
	List<LoginUser> login(LoginUser loginUser);

}
