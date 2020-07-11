package com.purefour.instablog.myprofile.datastore;

import com.purefour.instablog.myprofile.model.UserProfile;
import com.purefour.instablog.myprofile.model.exception.ObjectNotExistsException;

import java.util.List;
import java.util.UUID;

public interface UserProfileDataStore {

    List<UserProfile> getUserProfiles();
    UserProfile getUserProfileById(UUID userProfileId) throws ObjectNotExistsException;
}
