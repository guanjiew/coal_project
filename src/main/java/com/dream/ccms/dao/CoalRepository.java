package com.dream.ccms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dream.ccms.entity.CokingCoal;

@Repository
public interface CoalRepository extends JpaRepository<CokingCoal, Long> {
	List<CokingCoal> findByObjectInactiveFalse();

}
