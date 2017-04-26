package com.walkingtree.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.walkingtree.entity.User;

@Repository
public interface UserMysqlRepository  extends JpaRepository<User, Long>{

}
