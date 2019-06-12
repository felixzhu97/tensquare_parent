package com.tensquare.friend.controller;

import com.tensquare.friend.client.UserClient;
import com.tensquare.friend.service.FriendService;
import entity.Result;
import entity.StatusCode;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/friend")
public class FriendController {

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private FriendService friendService;

	@Autowired
	private UserClient userClient;

	/**
	 * 添加好友或者添加非好友
	 * @return
	 */
	@RequestMapping(value = "/like/{friendid}/{type}",method = RequestMethod.PUT)
	public Result addFriend(@PathVariable String friendid,@PathVariable String type) {
		//验证是否登录，并且拿到当前登录的用户id
		Claims claims = (Claims) request.getAttribute("claims_user");
		if (CollectionUtils.isEmpty(claims)) {
			//说明当前用户没有user角色
			return new Result(false, StatusCode.LOGINERROR,"权限不足");
		}
		//得到当前登陆的用户id
		String userid = claims.getId();
		//判断是添加好友还是添加非好友
		if (type != null) {
			if (type.equals("1")) {
				//添加好友
				int flag = friendService.addFriend(userid,friendid);
				if (flag == 0) {
					return new Result(false,StatusCode.REPERROR,"不能重复添加好友");
				}
				if (flag == 1) {
					userClient.updateFansCountAndFollowCount(userid,friendid,1);
					return new Result(true,StatusCode.OK,"添加成功");
				}
			}else if (type.equals("2")) {
				//添加非好友
				int flag = friendService.addNoFriend(userid,friendid);
				if (flag == 0) {
					return new Result(false,StatusCode.REPERROR,"不能重复添加非好友");
				}
				if (flag == 1) {
					return new Result(true,StatusCode.OK,"添加成功");
				}
			}
			return new Result(false, StatusCode.LOGINERROR,"参数异常");
		}else {
			return new Result(false, StatusCode.LOGINERROR,"参数异常");
		}
	}

	@RequestMapping(value = "/{friendid}",method = RequestMethod.DELETE)
	public Result deleteFriend(@PathVariable String friendid) {
		//验证是否登录，并且拿到当前登录的用户id
		Claims claims = (Claims) request.getAttribute("claims_user");
		if (CollectionUtils.isEmpty(claims)) {
			//说明当前用户没有user角色
			return new Result(false, StatusCode.LOGINERROR,"权限不足");
		}
		//得到当前登录的用户id
		String userid = claims.getId();
		friendService.deleteFriend(userid,friendid);
		userClient.updateFansCountAndFollowCount(userid,friendid,-1);
		return new Result(true,StatusCode.OK,"删除成功");
	}
}
