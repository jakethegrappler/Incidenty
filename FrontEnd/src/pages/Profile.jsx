import { useEffect, useState } from "react";
import "../css/Profile.css";

const Profile = () => {
    const [user, setUser] = useState(null);

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
                    setUser(userData);
                } else {
                    console.error("Chyba při načítání profilu:", await response.text());
                }
            } catch (error) {
                console.error("Chyba:", error);
            }
        };

        fetchUserInfo();
    }, []);

    if (!user) {
        return <div className="profile-wrapper"><p>Načítání údajů...</p></div>;
    }

    return (
        <div className="profile-wrapper">
            <div className="profile-header">
                <h2>Vítej, {user.firstName}!</h2>
                <p>Zde najdeš své uživatelské údaje.</p>
            </div>

            <div className="profile-card">
                <div className="label">Jméno:</div>
                <div className="value">{user.firstName}</div>

                <div className="label">Příjmení:</div>
                <div className="value">{user.lastName}</div>

                <div className="label">Uživatelské jméno:</div>
                <div className="value">{user.username}</div>

                <div className="label">Email:</div>
                <div className="value">{user.email}</div>

                <div className="label">Telefon:</div>
                <div className="value">{user.phoneNumber}</div>

                <div className="label">Role:</div>
                <div className="value">{user.role}</div>
            </div>
        </div>
    );
};

export default Profile;
