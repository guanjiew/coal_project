package com.dream.ccms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dream.ccms.entity.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	public List<User> findByNameAndPassword(String name,String pass);
	public List<User> findByName(String name);

}
