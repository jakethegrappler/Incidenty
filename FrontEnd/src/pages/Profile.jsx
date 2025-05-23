import {useEffect, useState} from "react";
import {useNavigate} from "react-router-dom";
import {useAuth} from "../auth/useAuth";
import "../css/Profile.css";

const Profile = () => {
    const {user} = useAuth();
    const navigate = useNavigate();
    const [localUser, setLocalUser] = useState(null);
    const [allIncidents, setAllIncidents] = useState([]);
    const [notifications, setNotifications] = useState([]);

    // 🧠 Načti user info
    useEffect(() => {
        const fetchUserInfo = async () => {
            try {
                const token = localStorage.getItem("token");
                const response = await fetch(`${import.meta.env.VITE_API_URL}/user/info`, {
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
                const response = await fetch(`${import.meta.env.VITE_API_URL}/incident/all`, {
                    headers: {Authorization: `Bearer ${token}`}
                });
                if (response.ok) {
                    const data = await response.json();
                    setAllIncidents(data);
                } else {
                    console.error("Chyba při načítání incidentů:", await response.text());
                }
            } catch (error) {
                console.error("Chyba:", error);
            }
        };

        fetchIncidents();
    }, []);


    useEffect(() => {
        if (localUser && localUser.notifications?.length > 0) {
            const relevant = allIncidents.filter(i => localUser.notifications.includes(i.id));
            setNotifications(relevant);
        } else {
            setNotifications([]);
        }
    }, [localUser, allIncidents]);

    const handleDismissNotification = async (incidentId) => {
        try {
            const token = localStorage.getItem("token");
            const response = await fetch(`${import.meta.env.VITE_API_URL}/user/notifications/remove/${incidentId}`, {
                method: "PUT",
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });

            if (response.ok) {
                // aktualizuj frontend
                setNotifications(prev => prev.filter(i => i.id !== incidentId));
            } else {
                console.error("Nepodařilo se odstranit notifikaci");
            }
        } catch (err) {
            console.error("Chyba při mazání notifikace:", err);
        }
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
                    <div className="profile-item"><span className="profile-label">👤 Jméno:</span>{localUser.firstName}
                    </div>
                    <div className="profile-item"><span className="profile-label">👥 Příjmení:</span>{localUser.lastName}
                    </div>
                    <div className="profile-item"><span
                        className="profile-label">📛 Uživatelské jméno:</span>{localUser.username}</div>
                    <div className="profile-item"><span className="profile-label">📧 Email:</span>{localUser.email}</div>
                    <div className="profile-item"><span
                        className="profile-label">📞 Telefon:</span>{localUser.phoneNumber}</div>
                    <div className="profile-item"><span className="profile-label">🛡️ Role:</span>{localUser.role}</div>
                </div>

                {(localUser.role === "ROLE_ADMIN" || localUser.role === "ROLE_EMPLOYEE") && (
                    <>
                        <div className="database-button-container">
                            <button className="database-button" onClick={handleDatabaseClick}>
                                📚 Správa Incidentů
                            </button>
                        </div>

                        {notifications.length > 0 && (
                            <div className="notifications-box">
                                <h3>🆕 Nové incidenty ({notifications.length})</h3>
                                <ul>
                                    {notifications.map((incident) => (
                                        <li key={incident.id}>
                                            <strong>{incident.type}</strong> v {incident.position} ({incident.date ? new Date(incident.date).toLocaleString("cs-CZ", {
                                            day: "numeric",
                                            month: "numeric",
                                            year: "numeric",
                                            hour: "2-digit",
                                            minute: "2-digit"
                                        }) : "-"}
                                            )
                                            <button onClick={() => handleDismissNotification(incident.id)}>✖</button>
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
