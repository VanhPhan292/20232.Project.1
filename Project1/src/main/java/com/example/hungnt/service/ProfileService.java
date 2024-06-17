package com.example.hungnt.service;

import com.example.hungnt.dto.ProfileDto;
import com.example.hungnt.entity.Profile;
import com.example.hungnt.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface ProfileService {
    void update(Profile profile);

    void save(Profile profile);
}
