package com.vinsguru.inventory.controller;

import com.vinsguru.dto.InventoryRequestDTO;
import com.vinsguru.dto.InventoryResponseDTO;
import com.vinsguru.inventory.service.InventoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("inventory")
@Slf4j
public class InventoryController {

    @Autowired
    private InventoryService service;

    @PostMapping("/deduct")
    public InventoryResponseDTO deduct(@RequestBody final InventoryRequestDTO requestDTO){
        log.info("controller for deduct = {}",requestDTO);
        return this.service.deductInventory(requestDTO);
    }

    @PostMapping("/add")
    public void add(@RequestBody final InventoryRequestDTO requestDTO){
        log.info("controller for add = {}",requestDTO);
        this.service.addInventory(requestDTO);
    }

}
