package com.purefour.instablog.myprofile.endpoint;

import com.purefour.instablog.myprofile.model.UserProfile;
import com.purefour.instablog.myprofile.model.exception.EmptyFileException;
import com.purefour.instablog.myprofile.model.exception.ObjectNotExistsException;
import com.purefour.instablog.myprofile.service.UserProfileService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/user-profile")
@AllArgsConstructor
@CrossOrigin("*")
public class UserProfileController {

    private final UserProfileService userProfileService;

    @GetMapping()
    public List<UserProfile> getUserProfiles() {
        return userProfileService.getUserProfile();
    }

    @PostMapping(
            path = "{userProfileId}/image/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public void uploadUserProfileImage(@PathVariable("userProfileId") UUID userProfileId,
                                       @RequestParam("file") MultipartFile file) throws EmptyFileException, ObjectNotExistsException {
        userProfileService.uploadUserProfileImage(userProfileId, file);
    }

    @GetMapping("{userProfileId}/image/download")
    public byte[] downloadUserProfileImage(@PathVariable("userProfileId") UUID userProfileId) throws ObjectNotExistsException {
        return userProfileService.downloadUserProfileImage(userProfileId);
    }
}
