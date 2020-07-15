import React, {useCallback} from "react";
import {useDropzone} from 'react-dropzone'
import axios from "axios";
import {BASE_URL} from '../../model/BASE_URL'

export const Dropzone = ({userProfileId}) => {
    
    const onDrop = useCallback(acceptedFiles => {
        const file = acceptedFiles[0];
        console.log(file);
        const formData = new FormData();
        formData.append("file", file);

        axios.post(`${BASE_URL.USER_PROFILE}/${userProfileId}/image/upload`, formData, {
            headers: {
                "Content-Type": "multipart/form-data"
            }
        }).then(() => {
            console.log("File uploaded successfully!");
            // window.location.reload(false);
        }).catch((err) => {
            console.log(err);
        });
    }, []);

    const {getRootProps, getInputProps, isDragActive} = useDropzone({onDrop})

    return (
        <div {...getRootProps()}>
            <input {...getInputProps()} />
            {
                isDragActive ?
                    <p className="badge badge-primary text-wrap">Drop the files here ...</p> :
                    <p className="badge badge-primary text-wrap">Drag 'n' drop some files here, or click to select files</p>
            }
        </div>
    )
};