package com.FmsProject.mappers;

import org.apache.ibatis.annotations.Select;

public interface RequestMapper {

    @Select("select count(id) from incityrequest where requestBy = #{username}")
    int getNumOfIncity(String username);

    @Select("select count(id) from tbOffTimeRequisition where reqBy = #{username}")
    int getNumOfOfftime(String username);

    @Select("select count(id) from fieldRequest where requestedBy = #{username}")
    int getNumOfField(String username);

    @Select("SELECT isDelegated FROM users where username = #{username}")
    String isDelegated(String username);

}
