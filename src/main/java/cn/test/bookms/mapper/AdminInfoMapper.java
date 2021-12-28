package cn.test.bookms.mapper;

import org.apache.ibatis.annotations.Param;

import cn.test.bookms.entity.AdminInfo;

public interface AdminInfoMapper {
    int deleteByPrimaryKey(Integer aid);

    int insert(AdminInfo record);

    int insertSelective(AdminInfo record);

    AdminInfo selectByPrimaryKey(Integer aid);

    int updateByPrimaryKeySelective(AdminInfo record);

    int updateByPrimaryKey(AdminInfo record);
    
    /**
     * 管理员登录
     * @param adminNum
     * @param adminPwd
     * @return
     */
    AdminInfo adminLogin(@Param("adminNum")String adminNum,@Param("adminPwd")String adminPwd);
}