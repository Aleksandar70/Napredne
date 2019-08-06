package com.msgnetconomy.appraisalsheet.dao;

import com.msgnetconomy.appraisalsheet.domain.UserManagerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserManagerDAO extends JpaRepository<UserManagerEntity, Integer> {
}
