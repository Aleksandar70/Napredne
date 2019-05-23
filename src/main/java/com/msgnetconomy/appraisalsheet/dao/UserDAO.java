package com.msgnetconomy.appraisalsheet.dao;

import com.msgnetconomy.appraisalsheet.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@Repository
@CrossOrigin(origins = "http://localhost:4200")
public interface UserDAO extends JpaRepository<UserEntity, Integer> {

    @Query("SELECT u FROM UserEntity u WHERE u.username= ?1 AND u.password = ?2")
    UserEntity login(String username, String password);

    @Query("SELECT u FROM UserEntity u where u.username = ?1")
    UserEntity findByUsername(String username);

    @Query("SELECT u FROM UserEntity u WHERE LOCATE(u.firstName,?1)>0 AND LOCATE(u.lastName, ?1)>0")
    UserEntity findByFirstNameAndLastName(String employeeName);
}
