package com.colin.community.community.mapper;

import com.colin.community.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created with IntelliJ IDEA
 * User : HeJianGong
 * Date : 2020/11/29
 * Time : 17:06
 */
@Mapper
public interface UserMapper
{
    @Insert("insert into user (name,account_id,token,gmt_create,gmt_modified) values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified})")
    void  insert(User user);
}
