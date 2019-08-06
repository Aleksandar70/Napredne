package com.msgnetconomy.appraisalsheet.dao;

import com.msgnetconomy.appraisalsheet.domain.UserEntity;
import com.msgnetconomy.appraisalsheet.domain.UserGroupEntity;
import com.msgnetconomy.appraisalsheet.domain.UserManagerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
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

    @Modifying
    @Transactional
    @Query("UPDATE UserEntity u SET u.firstName=?2, u.lastName=?3,u.username=?4,u.userGroup=?5,u.userManager=?6  WHERE u.userId=?1")
    int updateUser(int userId, String firstName, String lastName, String username, UserGroupEntity userGroup, UserManagerEntity manager);

    @Modifying
    @Transactional
    @Query("UPDATE UserEntity u SET u.firstName=?2, u.lastName=?3,u.username=?4,u.password=?5,u.userGroup=?6,u.userManager=?7  WHERE u.userId=?1")
    int updateUser(int userId, String firstName, String lastName, String username, String password, UserGroupEntity userGroup, UserManagerEntity manager);
}
