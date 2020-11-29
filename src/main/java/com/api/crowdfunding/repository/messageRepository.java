package com.api.crowdfunding.repository;

import java.util.Optional;

import javax.persistence.Table;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.api.crowdfunding.model.messageModel;
import com.api.crowdfunding.model.project;

import java.util.List;

@Repository
@Table(name = "messagerie_interne")
public interface  messageRepository extends JpaRepository<messageModel, Long> {
	

	 @Query(value = "SELECT  *  FROM  messagerie_interne  WHERE  _token_user_dest=?1", nativeQuery = true)
	 List<messageModel> findAllMessagesRecusByTokenUser(String tokenUser);
	 
	 
	 @Query(value = "SELECT  *  FROM  messagerie_interne  WHERE  _token_user_exp=?1", nativeQuery = true)
	 List<messageModel> findAllMessagesEnvoyesByTokenUser(String tokenUser);

	 
	 @Query(value = "SELECT  *  FROM  messagerie_interne  WHERE  token=?1", nativeQuery = true)
	 Optional<messageModel>  findMessageByToken(String tokeMessage);
	 
	 @Query(value = "SELECT  COUNT(*)  FROM  messagerie_interne  WHERE  _token_user_dest=?1   AND date_consultation IS  NULL", nativeQuery = true)
	 int  countMessageNonLusByTokenUser(String tokeuser);
	 
	 @Query(value = "SELECT  * FROM  messagerie_interne  WHERE  _token_user_dest=?1   AND date_consultation IS  NULL  ORDER BY timestamp DESC   LIMIT 4", nativeQuery = true)
	 List<messageModel> findAllMessagesNonLusByTokenUser(String tokenUser);
}
