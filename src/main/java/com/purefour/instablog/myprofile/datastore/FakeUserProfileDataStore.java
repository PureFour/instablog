package com.purefour.instablog.myprofile.datastore;

import com.purefour.instablog.myprofile.model.UserProfile;
import com.purefour.instablog.myprofile.model.exception.ObjectNotExistsException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Repository
public class FakeUserProfileDataStore implements UserProfileDataStore {

    private static final List<UserProfile> USER_PROFILES = new ArrayList<>();

    static {
        USER_PROFILES.add(new UserProfile(UUID.fromString("570da4e1-e5fd-4f1c-bf42-a64d6619b11f"), "Weronika", null));
        USER_PROFILES.add(new UserProfile(UUID.fromString("199b8f6f-a751-4857-a830-e8a26460eba5"), "Michał", null));
    }

    public List<UserProfile> getUserProfiles() {
        return USER_PROFILES;
    }

    public UserProfile getUserProfileById(UUID userProfileId) throws ObjectNotExistsException {
        return USER_PROFILES.stream()
                .filter(userProfile -> Objects.equals(userProfileId, userProfile.getUserProfileId()))
                .findFirst()
                .orElseThrow(() -> new ObjectNotExistsException("User with given id not exists in database!"));
    }
}
