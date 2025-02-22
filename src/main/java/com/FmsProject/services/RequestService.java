package com.FmsProject.services;

import com.FmsProject.mappers.RequestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestService {

    @Autowired
    private RequestMapper requestMapper;

    public int getNumOfIncity(String username) {
        return requestMapper.getNumOfIncity(username);
    }

    public int getNumOfOfftime(String username) {
        return requestMapper.getNumOfOfftime(username);
    }

    public int getNumOfField(String username) {
        return requestMapper.getNumOfField(username);
    }

    public String isDelegated(String username) {
        return requestMapper.isDelegated(username);
    }
}
