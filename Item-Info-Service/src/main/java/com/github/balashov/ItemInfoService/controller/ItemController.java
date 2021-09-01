package com.github.balashov.ItemInfoService.controller;

import com.github.balashov.ItemInfoService.domain.Item;
import com.github.balashov.ItemInfoService.service.ItemService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/items")
public class ItemController {

    public final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    /**
     * Retrieve how many different items are stored.
     * @return
     */
    @GetMapping("/count")
    public Mono<Long> count() {
        return itemService.count();
    }

    /**
     * Retrieve all items
     * @return
     */
    @GetMapping("")
    public Flux<Item> findAll() {
        return itemService.findAll();
    }

    /**
     * Save an item to the database (Will overwrite an existing item if not careful
     * @param item the item to save to database
     * @return returns the item saved as a Mono
     */
    @PostMapping("")
    public Mono<Item>save(@RequestBody Item item) {
        return itemService.save(item).log();
    }

    /**
     * Retrieves an item by the specified id
     * @param id id of the item requested
     * @return Returns the found item
     */
    @GetMapping("/byId/{id}")
    public Mono<Item> findById(@PathVariable int id) {
        return itemService.findById(id).log();
    }

    /**
     * Retrieves all items with the matching category
     * @param category Category to search by (doesn't support multiple category searches)
     * @return Items with corresponding category
     */
    @GetMapping("/byCategory/{category}")
    public Flux<Item> findByCategory(@PathVariable String category) {
        return itemService.findByCategory(category).log();
    }

    /**
     * Retrieves all items with the matching name
     * @param name Name to search by
     * @return Items with corresponding name
     */
    @GetMapping("/byName/{name}")
    public Flux<Item> findByName(@PathVariable String name) {
        return itemService.findByName(name).log();
    }

    /**
     * Updates an existing item if it exists
     * @param id Item ID to update
     * @param item values from item to use
     * @return Update item
     */
    @PutMapping("/update/{id}")
    public Mono<Item> update(@PathVariable int id, @RequestBody Item item) {
        return itemService.update(id, item).log();
    }

    /**
     * Adds categories to the specified item using item json
     * @param id
     * @param item
     * @return
     */
    @PutMapping("/addCategory/{id}")
    public Mono<Item> addCategoryById(@PathVariable int id, @RequestBody Item item) {
        String[] categories = item.getCategory().toArray(new String[(int) item.getCategory().stream().count()]);
        return itemService.addCategoryToItem(id, categories);
    }

    /**
     * Adds categories to the specified item using a passed string variable separated by spaces (%20)
     * @param id Item id
     * @param categories Single string of categories with spaces (%20) as separator
     * @return Updated item
     */
    @PutMapping("/addCategory/{id}/{categories}")
    public Mono<Item> addCategoryById(@PathVariable int id, @PathVariable String categories) {
        String[] array = categories.split(" ");
        return itemService.addCategoryToItem(id, array);
    }

}
