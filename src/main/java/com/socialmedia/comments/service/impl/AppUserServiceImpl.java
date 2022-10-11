package com.socialmedia.comments.service.impl;

import com.socialmedia.comments.dto.AppUsersResponse;
import com.socialmedia.comments.mapper.AppUserMapper;
import com.socialmedia.comments.model.AppUser;
import com.socialmedia.comments.repository.AppUserRepository;
import com.socialmedia.comments.service.AppUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AppUserServiceImpl implements AppUserService {
    private final AppUserRepository appUserRepository;
    private final AppUserMapper appUserMapper;

    @Override
    public AppUsersResponse getAppUsersResponse(Pageable pageable) {
        Page<AppUser> appUserPage = appUserRepository.findAll(pageable);
        return appUserMapper.getAppUsersResponse(appUserPage);
    }
}
