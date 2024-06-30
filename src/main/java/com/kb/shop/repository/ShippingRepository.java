package com.kb.shop.repository;

import com.kb.shop.domain.ShippingInfo;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ShippingRepository {

    private final JdbcTemplate jdbcTemplate;

    public ShippingRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public ShippingInfo selectShippingInfo(Long id) {
        String sql = "SELECT * FROM shipping_info WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, BeanPropertyRowMapper.newInstance(ShippingInfo.class));
    }

    public ShippingInfo selectShippingInfoByOrderIdAndOrderItemId (Long orderId, Long orderItemId) {
        String sql = "SELECT * FROM shipping_info WHERE order_id = ? AND order_item_id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{orderId, orderItemId}, BeanPropertyRowMapper.newInstance(ShippingInfo.class));
    }

    public void updateShippingStatus(Long id, ShippingInfo shippingInfo) {
        String sql = "UPDATE SET (shipping_status = ?) FROM shipping_info WHERE id = ?";
        jdbcTemplate.update(sql, shippingInfo.getShippingStatus().toString(), id);
    }

    public void insertShippingInfo(ShippingInfo shippingInfo) {
        String sql = "INSERT INTO shipping_info (order_id, order_item_Id, shipping_status) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, shippingInfo.getOrderId(), shippingInfo.getOrderItemId(), shippingInfo.getShippingStatus().toString());
    }
}
