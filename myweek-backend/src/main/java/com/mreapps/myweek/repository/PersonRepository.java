package com.mreapps.myweek.repository;

import com.mreapps.myweek.entity.Person;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 */
public interface PersonRepository extends PagingAndSortingRepository<Person, Long>
{
}
