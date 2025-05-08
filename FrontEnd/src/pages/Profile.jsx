import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../auth/useAuth";
import "../css/Profile.css";

const Profile = () => {
    const { user } = useAuth();
    const navigate = useNavigate();
    const [localUser, setLocalUser] = useState(null);
    const [incidents, setIncidents] = useState([]);
    const [readIds, setReadIds] = useState(() => {
        const stored = localStorage.getItem("readIncidentIds");
        return stored ? JSON.parse(stored) : [];
    });

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



    useEffect(() => {
        const fetchIncidents = async () => {
            try {
                const token = localStorage.getItem("token");
                const response = await fetch("http://localhost:8080/incident/all", {
                    headers: { Authorization: `Bearer ${token}` }
                });
                if (response.ok) {
                    const data = await response.json();
                    setIncidents(data);
                } else {
                    console.error("Chyba při načítání incidentů:", await response.text());
                }
            } catch (error) {
                console.error("Chyba:", error);
            }
        };

        if (localUser?.role === "ROLE_ADMIN" || localUser?.role === "ROLE_EMPLOYEE") {
            fetchIncidents();
        }
    }, [localUser]);

    const unread = incidents.filter(i => !readIds.includes(i.id));

    const markAsRead = (id) => {
        const updated = [...readIds, id];
        setReadIds(updated);
        localStorage.setItem("readIncidentIds", JSON.stringify(updated));
    };


    const handleDatabaseClick = () => {
        navigate("/incidents");
    };

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
                    <div className="profile-item"><span className="profile-label">👤 Jméno:</span>{localUser.firstName}</div>
                    <div className="profile-item"><span className="profile-label">👥 Příjmení:</span>{localUser.lastName}</div>
                    <div className="profile-item"><span className="profile-label">📛 Uživatelské jméno:</span>{localUser.username}</div>
                    <div className="profile-item"><span className="profile-label">📧 Email:</span>{localUser.email}</div>
                    <div className="profile-item"><span className="profile-label">📞 Telefon:</span>{localUser.phoneNumber}</div>
                    <div className="profile-item"><span className="profile-label">🛡️ Role:</span>{localUser.role}</div>
                </div>

                {/* 📚 Správa incidentů */}
                {(localUser.role === "ROLE_ADMIN" || localUser.role === "ROLE_EMPLOYEE") && (
                    <>
                        <div className="database-button-container">
                            <button className="database-button" onClick={handleDatabaseClick}>
                                📚 Správa Incidentů
                            </button>
                        </div>

                        {/* 🔔 Notifikace */}
                        {unread.length > 0 && (
                            <div className="notifications-box">
                                <h3>🆕 Nové incidenty ({unread.length})</h3>
                                <ul>
                                    {unread.map((incident) => (
                                        <li key={incident.id}>
                                            <strong>{incident.type}</strong> v {incident.position} ({incident.date?.substring(0, 10)})
                                            <button onClick={() => markAsRead(incident.id)}>✖</button>
                                        </li>
                                    ))}
                                </ul>
                            </div>
                        )}
                    </>
                )}
            </div>
        </div>
    );
};

export default Profile;
