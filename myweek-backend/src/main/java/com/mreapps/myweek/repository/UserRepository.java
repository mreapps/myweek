package com.mreapps.myweek.repository;

import com.mreapps.myweek.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 */
public interface UserRepository extends PagingAndSortingRepository<User, Long>
{
}
