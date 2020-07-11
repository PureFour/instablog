package com.purefour.instablog.buckets;

import lombok.Getter;

public enum BucketName {
    PROFILE_IMAGE("instablog-image-upload");

    @Getter
    private final String bucketName;

    BucketName(String bucketName) {
        this.bucketName = bucketName;
    }
}
