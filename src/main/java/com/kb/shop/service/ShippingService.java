package com.kb.shop.service;

import com.kb.shop.domain.ShippingInfo;
import com.kb.shop.repository.ShippingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShippingService {

    @Autowired
    ShippingRepository shippingRepository;

    public ShippingInfo getShippingInfo (Long id) {
        return shippingRepository.selectShippingInfo(id);
    }

    public ShippingInfo changeShippingStatus (Long id, ShippingInfo shippingInfo) {
        shippingRepository.updateShippingStatus(id, shippingInfo);

        return shippingRepository.selectShippingInfoByOrderIdAndOrderItemId(shippingInfo.getOrderId(), shippingInfo.getOrderItemId());
    }


    public ShippingInfo setShippingInfo (ShippingInfo shippingInfo) {
        shippingRepository.insertShippingInfo(shippingInfo);

        return shippingRepository.selectShippingInfoByOrderIdAndOrderItemId(shippingInfo.getOrderId(), shippingInfo.getOrderItemId());
    }
}
