package com.myproject.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.myproject.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper   // 必须加
public interface UserMapper extends BaseMapper<User> {
}