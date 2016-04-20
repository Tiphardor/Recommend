package zju.edu.cn.service;

import zju.edu.cn.po.Admin;

public interface AdminService {
	public Admin findAdminByIdAndPass(String adminId,String password);
}
