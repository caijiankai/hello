package cn.smbms.dao.user;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import cn.smbms.pojo.User;

public interface UserMapper {
	/**
	 * 查询用户表记录数
	 * @return
	 */
	public int count();
	
	/**
	 * 查询用户列表
	 * @param userName
	 * @param roleId
	 * @return
	 */
	public List<User> getUserList(@Param("userName")String userName,@Param("userRole")Integer roleId);
	
	/**
	 * 增加用户
	 * @param user
	 * @return
	 */
	public int add(User user);
	/**
	 * 修改用户信息
	 * @param user
	 * @return
	 */
	public int modify(User user);
	
	/**
	 * 修改当前用户密码
	 * @param id
	 * @param pwd
	 * @return
	 */
	public int updatePwd(@Param("id")Integer id, @Param("userPassword")String pwd);
	
	/**
	 * 根据userId删除用户信息
	 * @param delId
	 * @return
	 */
	public int deleteUserById(@Param("id")Integer delId);
	
}
