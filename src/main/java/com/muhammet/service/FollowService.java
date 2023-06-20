package com.muhammet.service;

import com.muhammet.repository.IFollowRepository;
import com.muhammet.repository.entity.Follow;
import com.muhammet.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class FollowService extends ServiceManager<Follow,Long> {
    private final IFollowRepository repository;
    public FollowService(IFollowRepository repository){
        super(repository);
        this.repository=repository;
    }
}
