package com.anpai.shoesservice.service.impl;

import com.anpai.shoesservice.entity.User;
import com.anpai.shoesservice.mapper.UserMapper;
import com.anpai.shoesservice.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-06-09
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
