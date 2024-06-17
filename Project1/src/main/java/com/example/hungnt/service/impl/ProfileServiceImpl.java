package com.example.hungnt.service.impl;

import com.example.hungnt.dto.ProfileDto;
import com.example.hungnt.entity.Profile;
import com.example.hungnt.entity.User;
import com.example.hungnt.repository.ProfileRepository;
import com.example.hungnt.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileServiceImpl implements ProfileService {
    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public void update(Profile profile) {
        profileRepository.save(profile);
    }

    @Override
    public void save(Profile profile) {
        profileRepository.save(profile);
    }
}
