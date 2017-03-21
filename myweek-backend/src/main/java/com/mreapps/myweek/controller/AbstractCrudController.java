package com.mreapps.myweek.controller;

import com.mreapps.myweek.entity.BaseEntity;
import com.mreapps.myweek.exception.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 */
public abstract class AbstractCrudController<T extends BaseEntity>
{
    private final String entityName;
    private final PagingAndSortingRepository<T, Long> repository;

    public AbstractCrudController(String entityName, PagingAndSortingRepository<T, Long> repository)
    {
        this.entityName = entityName;
        this.repository = repository;
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET)
    public Page<T> list(Pageable pageable)
    {
        return repository.findAll(pageable);
    }

    @CrossOrigin
    @RequestMapping(value = "{entityId}", method = RequestMethod.GET)
    public T get(@PathVariable long entityId)
    {
        T entity = repository.findOne(entityId);

        if (entity == null)
        {
            throw new EntityNotFoundException(String.format("%s with id %s not found", entityId));
        }
        return entity;
    }
}
