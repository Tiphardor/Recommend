package zju.edu.cn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import zju.edu.cn.mapper.AdminMapper;
import zju.edu.cn.po.Admin;
import zju.edu.cn.service.AdminService;

public class AdminServiceImpl implements AdminService{

	@Autowired
	private AdminMapper adminMapper;
	
	@Override
	public Admin findAdminByIdAndPass(String adminId, String password) {
		// TODO Auto-generated method stub
		Admin admin = new Admin();
		admin.setAdminId(adminId);
		admin.setPassword(password);
		Admin newAdmin = adminMapper.findAdmin(admin);
		return newAdmin;
	}

}
