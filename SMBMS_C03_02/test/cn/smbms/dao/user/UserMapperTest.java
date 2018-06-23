package cn.smbms.dao.user;

import static org.junit.Assert.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import cn.smbms.pojo.Address;
import cn.smbms.pojo.User;
import cn.smbms.utils.MyBatisUtil;

public class UserMapperTest {
	
	private Logger logger = Logger.getLogger(UserMapperTest.class);
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testCount() {
		SqlSession sqlSession = null;
		int count = 0;
		try {
			sqlSession = MyBatisUtil.createSqlSession();
			
			//第一种方式:调用selectOne方法执行查询操作
			//count = sqlSession.selectOne("cn.smbms.dao.user.UserMapper.count");
			
			//第二种方式:调用getMapper(Mapper.class)执行dao接口方法来实现对数据库的查询操作
			count = sqlSession.getMapper(UserMapper.class).count();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			MyBatisUtil.closeSqlSession(sqlSession);
		}
		
		logger.debug("UserDaoTest count---> " + count);
	}
	
	@Test
	public void testGetUserList(){
		SqlSession sqlSession = null;
		List<User> userList = new ArrayList<User>();
		try {
			sqlSession = MyBatisUtil.createSqlSession();
			String userName = "";
			//Integer roleId = 3;
			Integer roleId = null;
			userList = sqlSession.getMapper(UserMapper.class).getUserList(userName,roleId);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			MyBatisUtil.closeSqlSession(sqlSession);
		}
		logger.debug("userlist.size ----> " + userList.size());
		for(User user: userList){
			logger.debug("testGetUserList=======> id: " + user.getId() +
						" and userCode: " + user.getUserCode() + 
						" and userName: " + user.getUserName() + 
						" and userRole: " + user.getUserRole() + 
						" and userRoleName: " + user.getUserRoleName() +
						" and age: " + user.getAge() +
						" and phone: " + user.getPhone() +
						" and gender: " + user.getGender());
		}
	}
	
	
	@Test
	public void testAdd(){
		logger.debug("testAdd !===================");
		SqlSession sqlSession = null;
		int count = 0;
		try {
			sqlSession = MyBatisUtil.createSqlSession();
			User user = new User();
			user.setUserCode("test001");
			user.setUserName("测试用户001");
			user.setUserPassword("1234567");
			Date birthday = new SimpleDateFormat("yyyy-MM-dd").parse("1984-12-12");
			user.setBirthday(birthday);
			user.setCreationDate(new Date());
			user.setAddress("地址测试");
			user.setGender(1);
			user.setPhone("13688783697");
			user.setUserRole(1);
			user.setCreatedBy(1);
			user.setCreationDate(new Date());
			count = sqlSession.getMapper(UserMapper.class).add(user);
			//模拟异常，进行回滚
			//int i = 2/0;
			sqlSession.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			sqlSession.rollback();
			count = 0;
		}finally{
			MyBatisUtil.closeSqlSession(sqlSession);
		}
		logger.debug("testAdd count: " + count);
	}
	
	@Test
	public void testModify(){
		logger.debug("testModify !===================");
		SqlSession sqlSession = null;
		int count = 0;
		try {
			User user = new User();
			user.setId(25);
			user.setUserCode("testmodify");
			user.setUserName("测试用户修改");
			user.setUserPassword("0000000");
			Date birthday = new SimpleDateFormat("yyyy-MM-dd").parse("1980-10-10");
			user.setBirthday(birthday);
			user.setCreationDate(new Date());
			user.setAddress("地址测试修改");
			user.setGender(2);
			user.setPhone("13600002222");
			user.setUserRole(2);
			user.setModifyBy(1);
			user.setModifyDate(new Date());
			sqlSession = MyBatisUtil.createSqlSession();
			count = sqlSession.getMapper(UserMapper.class).modify(user);
			//模拟异常，进行回滚
			//int i = 2/0;
			sqlSession.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			sqlSession.rollback();
			count = 0;
		}finally{
			MyBatisUtil.closeSqlSession(sqlSession);
		}
		logger.debug("testModify count: " + count);
	}
	
	@Test
	public void testUpdatePwd() {
		logger.debug("testUpdatePwd !===================");
		SqlSession sqlSession = null;
		String pwd = "8888888";
		Integer id = 1;
		int count = 0;
		try {
			sqlSession = MyBatisUtil.createSqlSession();
			count = sqlSession.getMapper(UserMapper.class).updatePwd(id, pwd);
			sqlSession.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			sqlSession.rollback();
			count = 0;
		}finally{
			MyBatisUtil.closeSqlSession(sqlSession);
		}
		logger.debug("testUpdatePwd count： " + count);
	}
	
	@Test
	public void testDeleteUserById() {
		logger.debug("testDeleteUserById !===================");
		SqlSession sqlSession = null;
		Integer delId = 25;
		int count = 0;
		try {
			sqlSession = MyBatisUtil.createSqlSession();
			count = sqlSession.getMapper(UserMapper.class).deleteUserById(delId);
			sqlSession.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			sqlSession.rollback();
			count = 0;
		}finally{
			MyBatisUtil.closeSqlSession(sqlSession);
		}
		logger.debug("testDeleteUserById count: " + count);
	}
}
