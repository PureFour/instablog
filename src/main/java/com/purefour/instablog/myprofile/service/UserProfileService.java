package com.purefour.instablog.myprofile.service;

import com.purefour.instablog.buckets.BucketName;
import com.purefour.instablog.filestore.FileStore;
import com.purefour.instablog.myprofile.model.UserProfile;
import com.purefour.instablog.myprofile.model.exception.EmptyFileException;
import com.purefour.instablog.myprofile.model.exception.ObjectNotExistsException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserProfileService {

    private final UserProfileDataAccessService userProfileDataAccessService;
    private final FileStore fileStore;

    public List<UserProfile> getUserProfile() {
        return userProfileDataAccessService.getUserProfiles();
    }

    public byte[] downloadUserProfileImage(UUID userProfileId) throws ObjectNotExistsException {
        final UserProfile userProfile = userProfileDataAccessService.getUserProfileById(userProfileId);
        final String path = String.format("%s/%s",
                BucketName.PROFILE_IMAGE.getBucketName(), userProfile.getUserProfileId());
        return userProfile.getUserProfileImageLink().map(key -> fileStore.download(path, key)).orElse(new byte[0]);
    }

    public void uploadUserProfileImage(UUID userProfileId, MultipartFile file) throws EmptyFileException, IllegalArgumentException, ObjectNotExistsException {
        // 1. Check if image is not empty and file is image
        validateImageContent(file);

        // 2. Check the user exists in our database
        final UserProfile databaseUserProfile = userProfileDataAccessService.getUserProfileById(userProfileId);

        // 3. If so grab some metadata from file if any
        final Map<String, String> metadata = fileStore.extractMetadata(file);

        // 4. Store the image in s3 and update database with s3 image link (userProfileImageKey)

        final String path = String.format("%s/%s", BucketName.PROFILE_IMAGE.getBucketName(), databaseUserProfile.getUserProfileId());
        final String fileName = String.format("%s-%s", file.getOriginalFilename(), UUID.randomUUID());

        try {
            fileStore.save(path, fileName, Optional.of(metadata), file.getInputStream());
            databaseUserProfile.setUserProfileImageLink(fileName);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private void validateImageContent(MultipartFile file) throws EmptyFileException {
        if (Objects.isNull(file) || file.isEmpty()) {
            throw new EmptyFileException("Uploaded file is empty!");
        } else if (!isFileAnImage(file)) {
            throw new IllegalArgumentException("Uploaded file is not an image!");
        }
    }

    private boolean isFileAnImage(MultipartFile file) {
        final String mimeType = file.getContentType();
        return mimeType.startsWith("image/");
    }
}
