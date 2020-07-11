package com.purefour.instablog.myprofile.service;

import com.purefour.instablog.myprofile.datastore.UserProfileDataStore;
import com.purefour.instablog.myprofile.model.UserProfile;
import com.purefour.instablog.myprofile.model.exception.ObjectNotExistsException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class UserProfileDataAccessService {

    private final UserProfileDataStore userProfileDataStore;

    List<UserProfile> getUserProfiles() {
        return userProfileDataStore.getUserProfiles();
    }

    UserProfile getUserProfileById(UUID userProfileId) throws ObjectNotExistsException {
        return userProfileDataStore.getUserProfileById(userProfileId);
    }
}
