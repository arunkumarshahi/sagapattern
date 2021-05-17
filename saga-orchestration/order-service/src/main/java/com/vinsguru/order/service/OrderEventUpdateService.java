package com.vinsguru.order.service;

import com.vinsguru.dto.OrchestratorResponseDTO;
import com.vinsguru.order.repository.PurchaseOrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Slf4j
public class OrderEventUpdateService {

    @Autowired
    private PurchaseOrderRepository repository;

    @Transactional
    public void updateOrder(final OrchestratorResponseDTO responseDTO){
        log.info("Update order called with {}",responseDTO);
        this.repository
                .findById(responseDTO.getOrderId())
                .ifPresent(po -> {
                    po.setStatus(responseDTO.getStatus());
                    this.repository.save(po);
                });
    }

}
