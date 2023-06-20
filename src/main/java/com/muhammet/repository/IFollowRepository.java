package com.muhammet.repository;

import com.muhammet.repository.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFollowRepository  extends JpaRepository<Follow,Long> {
}
