package com.walkingtree.service;

import org.springframework.stereotype.Service;

import com.walkingtree.model.UserElastcSearchModel;
import com.walkingtree.model.UserModel;
import com.walkingtree.model.UserMysqlModel;

@Service
public interface UserService {

	public UserModel saveUserInMysql(UserModel user);
	public UserModel saveUserInElasticSearch(UserModel user);
	public UserMysqlModel getUserFromMysql(long id);
	public UserElastcSearchModel getUserFromElasticSearch(String id);
	public String sendUserToKafka(UserModel user) ;
}
