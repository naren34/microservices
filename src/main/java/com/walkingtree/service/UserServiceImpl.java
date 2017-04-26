package com.walkingtree.service;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.walkingtree.entity.User;
import com.walkingtree.entity.UserElasticSearch;
import com.walkingtree.model.UserElastcSearchModel;

import com.walkingtree.model.UserModel;
import com.walkingtree.model.UserMysqlModel;
import com.walkingtree.repository.UserElasticSearchRepository;
import com.walkingtree.repository.UserMysqlRepository;

@Service
public class UserServiceImpl implements UserService{
public static final	Logger senderLog=LoggerFactory.getLogger(UserServiceImpl.class);
	@Value("${topic.json}")
	private String topic;
	
	@Autowired
	private UserMysqlRepository userMysqlRepository;
	
	@Autowired
	  private KafkaTemplate<String, Object> kafkaTemplate;
	
	
	@Autowired
	private UserElasticSearchRepository userElasticSearchRepository;

	@Override
	@Transactional
	public UserModel saveUserInMysql(UserModel user) {
		ModelMapper mapper=new ModelMapper();
		User userData=mapper.map(user,User.class);
		userData= userMysqlRepository.save(userData);
		return mapper.map(userData, UserModel.class);
	}

	@Override
	@Transactional
	public UserModel saveUserInElasticSearch(UserModel user) {
		// TODO Auto-generated method stub
		ModelMapper mapper=new ModelMapper();
		UserElasticSearch userData=mapper.map(user,UserElasticSearch.class);
		userData=userElasticSearchRepository.save(userData);
		return mapper.map(userData, UserModel.class);
	}

	@Override
	public UserMysqlModel getUserFromMysql(long id) {
		// TODO Auto-generated method stub
		
		User user=userMysqlRepository.findOne(id);
		ModelMapper mapper=new ModelMapper();
		return mapper.map(user, UserMysqlModel.class);
	}

	@Override
	public UserElastcSearchModel getUserFromElasticSearch(String id) {
		// TODO Auto-generated method stub
		UserElasticSearch user=userElasticSearchRepository.findOne(id);
		ModelMapper mapper=new ModelMapper();
		//mapper.addMappings(new UserMapper());
		return mapper.map(user, UserElastcSearchModel.class);
	}

	@Override
	public String sendUserToKafka(UserModel user) {
		// TODO Auto-generated method stub
		ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(topic,(Object) user);

	    // register a callback with the listener to receive the result of the send asynchronously
	    future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {

	      @Override
	      public void onSuccess(SendResult<String, Object> result) {
	    	 senderLog.info("sent message='{}' with offset={}", result.toString(),
	            result.getRecordMetadata().offset());
	      }

	      @Override
	      public void onFailure(Throwable ex) {
	    	  senderLog.error("unable to send message='{}'", user.toString(), ex);
	    		
	      }
	    });
		return "Successfully send user to kafka";
	}

}
