package pl.mp.ecommerce_platform.inventory_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.mp.ecommerce_platform.inventory_service.model.Inventory;
import pl.mp.ecommerce_platform.inventory_service.service.InventoryService;

import java.util.Optional;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/{productId}")
    public ResponseEntity<Inventory> getInventory(@PathVariable Long productId) {
        Optional<Inventory> opt = inventoryService.getInventoryByProductId(productId);
        return opt.map(inventory -> new ResponseEntity<>(inventory, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));

    }

    @PostMapping
    public ResponseEntity<Inventory> addInventory(@RequestBody Inventory inventory) {
        return new ResponseEntity<>(inventoryService.addInventory(inventory), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Inventory> updateInventory(@RequestParam Long productId, @RequestParam int quantity) {
        return new ResponseEntity<>(inventoryService.updateInventory(productId, quantity), HttpStatus.OK);
    }

    @GetMapping("/{productId}/in-stock")
    public ResponseEntity<Boolean> isInStock(@PathVariable Long productId) {
        return new ResponseEntity<>(inventoryService.isProductInStock(productId), HttpStatus.OK);
    }
}