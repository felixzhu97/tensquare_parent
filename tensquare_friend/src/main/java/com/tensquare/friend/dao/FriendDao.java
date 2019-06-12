package com.tensquare.friend.dao;

import com.tensquare.friend.pojo.Friend;
import com.tensquare.friend.service.FriendService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface FriendDao extends JpaRepository<Friend,String> {

	public Friend findByUseridAndFriendid(String userid,String friendid);

	@Modifying
	@Query(value = "update tb_friend set islike = ? where userid = ? and friendid = ?",nativeQuery = true)
	public void updateIslike(String islike,String userid,String friendid);

	@Modifying
	@Query(value = "DELETE FROM tb_friend WHERE userid = ? AND friendid = ?",nativeQuery = true)
	public void deleteFriend(String userid, String friendid);
}
