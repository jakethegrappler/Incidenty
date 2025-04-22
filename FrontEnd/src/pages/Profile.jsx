// import { useAuth } from "../auth/useAuth";
import {useState} from "react";

const Profile = () => {
    const { role, setRole } = useState();

    return (
        <div className="profile-wrapper">
            <h2>Vítej, {}</h2>
            <p>Role: {role}</p>
        </div>
    );
};

export default Profile;
