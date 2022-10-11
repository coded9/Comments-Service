package com.socialmedia.comments.mapper;

import com.socialmedia.comments.dto.AppUsersResponse;
import com.socialmedia.comments.dto.AppUsersResponse.AppUserDto;
import com.socialmedia.comments.model.AppUser;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AppUserMapper {

  private final PaginationMapper<AppUser> appUserPaginationMapper;

  public AppUsersResponse getAppUsersResponse(Page<AppUser> appUserPage){
       return AppUsersResponse.builder().users(getUsers(appUserPage)).pagination(appUserPaginationMapper.getPaginationDto(appUserPage))
                              .build();
  }

  private List<AppUserDto> getUsers(Page<AppUser> appUserPage){
    return appUserPage.getContent().stream().map( appUser -> getAppUserDto(appUser)).collect(
        Collectors.toList());
  }

  private AppUserDto getAppUserDto(AppUser appUser){
    return AppUserDto.builder().id(appUser.getId()).fullName(appUser.getFullName()).userName(
        appUser.getUserName()).build();
  }
}
