package com.walkingtree.kafka;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import com.walkingtree.model.UserModel;
import com.walkingtree.service.UserService;


@RestController
public class Receiver {
	@Autowired
	UserService userService;
	private static final Logger logger=LoggerFactory.getLogger(Receiver.class);
	@KafkaListener(topics = "${topic.json}")
	  public void receive(ConsumerRecord<String, LinkedHashMap<String,Object>>obj) {
		logger.info("received object is"+obj.toString());
		UserModel userModel=new UserModel();
		userModel.setUserName(obj.value().get("userName").toString());
		userModel.setPassword(obj.value().get("password").toString());
		userModel.setfName(obj.value().get("fName").toString());
		//userModel.setLName(obj.value().get("lName").toString());
		userModel.setEmail(obj.value().get("email").toString());
		userModel.setPhoneNumber((int)obj.value().get("phoneNumber"));
		userService.saveUserInMysql(userModel);
		userService.saveUserInElasticSearch(userModel);
	  }
}
