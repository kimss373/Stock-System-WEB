package kr.ac.kopo.member.dao;

import org.apache.ibatis.session.SqlSession;

import kr.ac.kopo.util.MyBatisConfig;
import kr.ac.kopo.vo.MemberVO;

public class MemberDAO {

	private SqlSession session;
	
	public MemberDAO() {
		session = new MyBatisConfig().getInstance();
		System.out.println("MemberDAO Á¢¼Ó session : " + session);
	}
	
	public MemberVO selectOneMemberById(String id) {
		return session.selectOne("member.dao.MemberDAO.selectOneMemberById", id);
	}
	
	public void insertRegisterMember(MemberVO memberVO) {
		session.insert("member.dao.MemberDAO.insertRegisterMember", memberVO);
		session.commit();
	}
	
	public MemberVO selectOneByIdPwd(MemberVO memberVO) {
		return session.selectOne("member.dao.MemberDAO.selectOneByIdPwd", memberVO);
	}
	
	
}
