package com.dream.ccms.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dream.ccms.entity.TargetValue;
@Repository
public interface TargetRepository extends JpaRepository<TargetValue, Long> {

}
