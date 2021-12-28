package com.bankass.bankass.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bankass.bankass.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	@Query("SELECT COUNT(u) FROM User u")
	Long getTotalUsers();

	@Query("SELECT u FROM User u where u.isLogin ='true' ")
	User finUserSignIn();

	@Modifying(clearAutomatically =true)
	@Transactional
	@Query("UPDATE User SET isLogin ='true' where email =:email and password = :password")
	void setUserAsSignIn(@Param("email") String email, @Param("password") String password);

	@Modifying(clearAutomatically = true)
	@Transactional
	@Query("UPDATE User SET isLogin = 'false' where isLogin ='true'")
	void setUserAsSignOut();

	User findByEmail(String email);

	User findByEmailAndPassword(String email, String password);


}
