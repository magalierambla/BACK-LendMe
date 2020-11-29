package com.api.crowdfunding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.api.crowdfunding.model.User;


import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findByUsernameOrEmail(String username, String email);

    List<User> findByIdIn(List<Long> userIds);

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
    
    
    @Query(value = "SELECT  *  FROM  users,user_roles   WHERE users.id=user_roles.user_id  AND  user_roles.role_id=1   ", nativeQuery = true)
    List<User> findUsersProject();
    
    @Query(value = "SELECT  *  FROM  users  WHERE  token=?1 ", nativeQuery = true)
	Optional<User> checkExistUserByToken(String token);
    
    @Query(value = "SELECT COUNT(*) AS nbrUsers FROM  users,user_roles  WHERE  users.id = user_roles.user_id  AND YEAR( users.created_at ) = YEAR( NOW( ) )  AND  user_roles.role_id = 1", nativeQuery = true)
	int  countNbrUsersForYearCurrent();
	   
	@Query(value = "SELECT COUNT(*) AS nbrUsers FROM  users,user_roles  WHERE  users.id = user_roles.user_id  AND YEAR( users.created_at ) = YEAR( ADDDATE( CURDATE( ) , INTERVAL -1 YEAR ) )  AND  user_roles.role_id = 1 ", nativeQuery = true)
	int  countNbrUsersForLast1Year();
	   
     @Query(value = "SELECT COUNT(*) AS nbrUsers FROM  users,user_roles  WHERE users.id = user_roles.user_id  AND   YEAR( users.created_at ) = YEAR( ADDDATE( CURDATE( ) , INTERVAL -2 YEAR ) )  AND  user_roles.role_id = 1", nativeQuery = true)
	 int  countNbrUsersForLast2Year();
	   
	 @Query(value = "SELECT COUNT(*) AS nbrUsers FROM  users,user_roles  WHERE users.id = user_roles.user_id  AND   YEAR( users.created_at ) = YEAR( ADDDATE( CURDATE( ) , INTERVAL -3 YEAR ) )  AND  user_roles.role_id = 1", nativeQuery = true)
	 int  countNbrUsersForLast3Year();
	   
	 @Query(value = "SELECT COUNT(*) AS nbrUsers FROM  users,user_roles   WHERE  users.id = user_roles.user_id  AND   YEAR( users.created_at ) = YEAR( ADDDATE( CURDATE( ) , INTERVAL -4 YEAR ) )  AND  user_roles.role_id = 1", nativeQuery = true)
	 int  countNbrUsersForLast4Year();
	   
	 @Query(value = "SELECT  *  FROM  users  WHERE  token_confir_register=?1 ", nativeQuery = true)
	 Optional<User> checkExistUserByTokenIscription(String token);
}
