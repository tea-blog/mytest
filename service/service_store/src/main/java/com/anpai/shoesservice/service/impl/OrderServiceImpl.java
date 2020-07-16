package com.anpai.shoesservice.service.impl;

import com.anpai.shoesservice.entity.Order;
import com.anpai.shoesservice.mapper.OrderMapper;
import com.anpai.shoesservice.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xsz
 * @since 2020-06-14
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

}
