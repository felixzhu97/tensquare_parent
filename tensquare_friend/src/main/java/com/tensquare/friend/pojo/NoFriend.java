package com.tensquare.friend.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "tb_nofriend")
@IdClass(NoFriend.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoFriend implements Serializable {

	@Id
	private String userid;

	@Id String friendid;

}
