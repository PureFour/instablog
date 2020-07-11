import React, {useState, useEffect} from 'react';
import './App.css';
import axios from "axios";
import {Dropzone} from "../drop-and-create/dropzone";
import {BASE_URL} from "../model/BASE_URL";

const UserProfiles = () => {

    const [userProfiles, setUserProfiles] = useState([]);

    const fetchUserProfiles = () => {
        axios.get(BASE_URL.USER_PROFILE)
            .then(res => {
                console.log(res);
                setUserProfiles(res.data);
            });
    };

    const fetchUserProfileImage = (userProfile) => {
        if (!!userProfile.userProfileId) {
            return (
                <img alt={"userProfileImage"}
                     src={`${BASE_URL.USER_PROFILE}/${userProfile.userProfileId}/image/download`}/>
            );
        }
    };

    useEffect(() => {
        fetchUserProfiles();
    }, []);

    return userProfiles.map((userProfile, index) => {
        return (
            <div key={index}>
                {fetchUserProfileImage(userProfile)}
                <br/>
                <br/>
                <h1>{userProfile.username}</h1>
                <p>{userProfile.userProfileId}</p>
                <Dropzone {...userProfile}/>
                <br/>
            </div>
        )
    });
};

function App() {
    return (
        <div className="App">
            <UserProfiles/>
        </div>
    );
}

export default App;
