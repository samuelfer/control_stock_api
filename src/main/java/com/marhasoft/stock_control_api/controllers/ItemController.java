package com.marhasoft.stock_control_api.controllers;

import com.marhasoft.stock_control_api.models.Item;
import com.marhasoft.stock_control_api.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/itens")
public class ItemController {

    private ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('VIEW_ITEM')")
    public List<Item> getAll(){
        return itemService.getAllItems();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('VIEW_ITEM')")
    public Item getById(@PathVariable("id") Long id){
        return itemService.getItemById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('UPDATE_ITEM')")
    public Item update(@RequestBody() Item item, @PathVariable("id") Long id){
        return itemService.save(item);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE_ITEM')")
    public Item create(@RequestBody() Item item){
        return itemService.save(item);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE_ITEM')")
    public void delete(@PathVariable("id") Long id){
        itemService.deleteItem(id);
    }
}
