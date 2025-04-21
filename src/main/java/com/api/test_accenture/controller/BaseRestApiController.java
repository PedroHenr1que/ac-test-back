package com.api.test_accenture.controller;

import com.api.test_accenture.model.BaseEntity;
import com.api.test_accenture.service.BaseCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public abstract class BaseRestApiController<T extends BaseEntity> {

    private BaseCrudService<T> crudService;

    @Autowired
    public BaseRestApiController(BaseCrudService<T> crudService) {
        this.crudService = crudService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<T> save(@RequestBody T object) {
        return new ResponseEntity<>(this.crudService.save(object), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<T> update(@RequestBody T object) {
        return new ResponseEntity<>(this.crudService.update(object), HttpStatus.OK);
    }

    @DeleteMapping(
            value = "{id}"
    )
    public ResponseEntity<T> delete(@PathVariable Long id) {
        this.crudService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(
            value = "{id}"
    )
    public ResponseEntity<T> findOne(@PathVariable Long id) {
        return new ResponseEntity<>(this.crudService.findById(id),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<T>> findAll() {
        return new ResponseEntity<>(this.crudService.findAll(), HttpStatus.OK);
    }
}
