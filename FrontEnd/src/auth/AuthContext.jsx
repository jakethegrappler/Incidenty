import { createContext, useEffect, useState } from "react";

export const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
    const [token, setToken] = useState(null);
    const [isLoggedIn, setIsLoggedIn] = useState(false);
    const [role, setRole] = useState(null);
    const [user, setUser] = useState(null); // <- přidáno

    useEffect(() => {
        const token = localStorage.getItem("token");
        if (token) {
            setToken(token);
            setIsLoggedIn(true);
            fetchUserInfo(token); // <- nová funkce
        } else {
            setIsLoggedIn(false);
        }
    }, []);
    const fetchUserInfo = async (token) => {
        try {
            const response = await fetch(`${import.meta.env.VITE_API_URL}/user/info`, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                    Authorization: `Bearer ${token}`,
                },
            });

            if (response.ok) {
                const userData = await response.json();
                setUser(userData);
                setRole(userData.role);
            } else {
                logout();
                console.error("Chyba při získání údajů o uživateli");
            }
        } catch (error) {
            console.error("Chyba:", error);
            logout();
        }
    };

    const login = (token) => {
        setToken(token);
        setIsLoggedIn(true);
        localStorage.setItem("token", token);
        fetchUserInfo(token);
    };

    const logout = () => {
        setToken(null);
        setUser(null);
        setRole(null);
        localStorage.removeItem("token");
        setIsLoggedIn(false);
    };

    return (
        <AuthContext.Provider value={{ token, isLoggedIn, login, logout, role, user }}>
            {children}
        </AuthContext.Provider>
    );
};
