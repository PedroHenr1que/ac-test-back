package com.api.test_accenture.service;

import com.api.test_accenture.model.BaseEntity;
import com.api.test_accenture.repository.BaseCrudRepository;

import java.util.List;

public abstract class BaseCrudService<T extends BaseEntity> {

    protected BaseCrudRepository<T> baseCrudRepository;

    protected BaseCrudService(BaseCrudRepository<T> baseCrudRepository) {
        this.baseCrudRepository = baseCrudRepository;
    }

    public T save(T object) {
        return this.baseCrudRepository.save(object);
    }

    public T update(T object) {
        if (!this.baseCrudRepository.existsById(object.getId())) {
            throw new RuntimeException("The entity doesn't exists");
        } else {
            return this.save(object);
        }
    }

    public void deleteById(Long id) {
        this.baseCrudRepository.deleteById(id);
    }

    public T findById(Long id) {
        var res = this.baseCrudRepository.findById(id);

        if (res.isPresent()) {
            return res.get();
        }

        throw new RuntimeException("Object not found");
    }

    public List<T> findAll() {
        return this.baseCrudRepository.findAll();
    }
}
