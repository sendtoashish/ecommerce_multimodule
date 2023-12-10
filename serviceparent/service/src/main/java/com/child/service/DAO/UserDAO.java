package com.child.service.DAO;


import com.child.model.response.User;
import com.child.service.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO extends JpaRepository<UserEntity,Long> {


    @Query("SELECT u FROM user_details u WHERE u.email= :email")
    public UserEntity findUserWithEmail(String email);


    @Query("SELECT u FROM user_details u WHERE u.username= :username")
    public UserEntity findUserWithUsername(String username);
}
