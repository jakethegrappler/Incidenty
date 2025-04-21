import { useAuth } from "../auth/useAuth";

const Profile = () => {
    const { user } = useAuth();

    return (
        <div className="profile-wrapper">
            <h2>Vítej, {user?.email}</h2>
            <p>Role: {user?.role}</p>
        </div>
    );
};

export default Profile;
