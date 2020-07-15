import React, { useEffect, useState } from "react";
import axios from "axios";
import _ from "lodash";
import { BASE_URL } from "../../model/BASE_URL";
import { Dropzone } from "../dropAndCreate/dropzone";
import Header from "../header/header";

const UserProfileID = "199b8f6f-a751-4857-a830-e8a26460eba5";

export const MyProfile = () => {
  const [userProfiles, setUserProfiles] = useState([]);

  const fetchUserProfiles = () => {
    axios.get(BASE_URL.USER_PROFILE).then((res) => {
      console.log(res);
      setUserProfiles(res.data);
    });
  };

  const fetchUserProfileImage = (userProfileId) => {
    if (!!userProfileId) {
      return (
        <div>
          <image
            className={"h-64 w-64 rounded-full"}
            alt={"No IMAGE!!!"}
            src={`${BASE_URL.USER_PROFILE}/${userProfileId}/image/download`}
          />
        </div>
      );
    }
  };

  const fetchUserInfo = (userProfileId) => {
    if (!!userProfileId) {
      const userProfile = userProfiles.find(
        (userProfiles) => userProfiles.userProfileId === userProfileId
      );
      const userProfileInfo = _.get(userProfile, "userInfo");
      return <p>{userProfileInfo}</p>;
    }
  };

  useEffect(() => {
    fetchUserProfiles();
  }, []);

  return (
    <>
      <Header/>
        <div className={"h-screen w-full bg-green-200 rounded-lg"}>
          {fetchUserProfileImage(UserProfileID)}
          <Dropzone userProfileId={UserProfileID} />
          {fetchUserInfo(UserProfileID)}
        </div>
    </>
  );
  // userProfiles.map((userProfile, index) => {
  //   const { userProfileId, username } = userProfile;
  //   return (
  //     <div key={index}>
  //       {fetchUserProfileImage(userProfile)}
  //       <br />
  //       <br />
  //       <h1>{username}</h1>
  //       <Dropzone {...userProfile} />
  //       <br />
  //     </div>
  //   );
  // }));
};
