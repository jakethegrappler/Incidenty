import { useEffect, useState } from "react";
import { useAuth } from "../auth/useAuth";
import "../css/Profile.css";

const Profile = () => {
    const { user } = useAuth();
    const [localUser, setLocalUser] = useState(null);

    useEffect(() => {
        const fetchUserInfo = async () => {
            try {
                const token = localStorage.getItem("token");
                const response = await fetch("http://localhost:8080/user/info", {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json",
                        Authorization: `Bearer ${token}`,
                    },
                });

                if (response.ok) {
                    const userData = await response.json();
                    setLocalUser(userData);
                } else {
                    console.error("Chyba při načítání profilu:", await response.text());
                }
            } catch (error) {
                console.error("Chyba:", error);
            }
        };

        if (!user) {
            fetchUserInfo();
        } else {
            setLocalUser(user);
        }
    }, [user]);

    if (!localUser) {
        return (
            <div className="page-wrapper fade-in">
                <div className="profile-loading">Načítání údajů...</div>
            </div>
        );
    }

    return (
        <div className="page-wrapper fade-in">
            <div className="profile-container">
                <h2 className="profile-title">Profil uživatele</h2>

                <div className="profile-section">
                    <div className="profile-item">
                        <span className="profile-label">👤 Jméno:</span>
                        <span>{localUser.firstName}</span>
                    </div>
                    <div className="profile-item">
                        <span className="profile-label">👥 Příjmení:</span>
                        <span>{localUser.lastName}</span>
                    </div>
                    <div className="profile-item">
                        <span className="profile-label">📛 Uživatelské jméno:</span>
                        <span>{localUser.username}</span>
                    </div>
                    <div className="profile-item">
                        <span className="profile-label">📧 Email:</span>
                        <span>{localUser.email}</span>
                    </div>
                    <div className="profile-item">
                        <span className="profile-label">📞 Telefon:</span>
                        <span>{localUser.phoneNumber}</span>
                    </div>
                    <div className="profile-item">
                        <span className="profile-label">🛡️ Role:</span>
                        <span>{localUser.role}</span>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default Profile;
