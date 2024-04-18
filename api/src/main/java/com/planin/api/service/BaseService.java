package com.planin.api.service;

import java.util.List;

public interface BaseService<E> {
    public List<E> findAll();
    public E findById(Long id);
    public E save(E entity);
    public E update(Long id, E entity);
    public boolean delete(Long id);
}
