package com.example.demo.service;

import java.util.Optional;

import org.dozer.Mapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.UserInfo;
import com.example.demo.form.SignupForm;
import com.example.demo.repository.UserInfoRepository;

import lombok.RequiredArgsConstructor;

/**
 * ユーザー登録画面 Service
 * 
 * @author takaosecond
 *
 */

@Service
@RequiredArgsConstructor
public class SignupService {
	
	/** ユーザー情報テーブルDAO */
	private final UserInfoRepository repository;
	
	/** Dozer Mapper */
	private final Mapper mapper;
	
	/** PasswordEncoder */
	private final PasswordEncoder passwordEncoder;
	
	/**
	 * ユーザー情報テーブル 新規登録
	 * 
	 * @param form 入力情報
	 * @return 登録(ユーザー情報Entity)、既に同じユーザIDで登録がある場合は
	 */
	public Optional<UserInfo> resistUserInfo(SignupForm form){
		var userInfoExistedOpt = repository.findById(form.getLoginId());
		if(userInfoExistedOpt.isPresent()) {
			return Optional.empty();
		}
		
		var userInfo = mapper.map(form, UserInfo.class);
		
		var encodedPassword = passwordEncoder.encode(form.getPassword());
		userInfo.setPassword(encodedPassword);
		
//		Dozer Mapperへ切り替え
//		var userInfo = new UserInfo();
//		userInfo.setLoginId(form.getLoginId());
//		userInfo.setPassword(form.getPassword());
		
		return Optional.of(repository.save(userInfo));
	}

}
