package com.walkingtree.api;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.walkingtree.model.UserElastcSearchModel;
import com.walkingtree.model.UserModel;
import com.walkingtree.model.UserMysqlModel;
import com.walkingtree.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(path={"/send"},method=RequestMethod.POST)
	public String sendUser(@RequestBody UserModel userModel){
	return	userService.sendUserToKafka(userModel);
	}
	
	@RequestMapping(path={"/getUserByMysql"},method=RequestMethod.GET)
	public UserMysqlModel getUserFromMysql(@RequestParam long id){
		return userService.getUserFromMysql(id);
	}
	
	@RequestMapping(path={"/getUserByElasicSearch"},method=RequestMethod.GET)
	public UserElastcSearchModel getUserFromElasticSearch(@RequestParam String id){
		return userService.getUserFromElasticSearch(id);
	}

}
