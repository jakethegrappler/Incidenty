import {createContext, useEffect, useState} from "react";

export const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
    const [role, setRole] = useState(null)
    const [isLoggedIn, setIsLoggedIn] = useState(false)
    const [token , setToken] = useState(null)

    
    useEffect(() => {
        const token = localStorage.getItem("token");

        if (token) {
            setToken(token);
            setIsLoggedIn(true)
            
        } else {
            setIsLoggedIn(false)
        }
    }, [])
    
    
    
    const login = (token, role) => {
        setRole(role);
        setToken(token);
        localStorage.setItem('token', token);
        setIsLoggedIn(true);
        // localStorage.setItem("user", JSON.stringify(userData));
    };

    const logout = () => {
        setRole(null);
        localStorage.removeItem("token");
        setIsLoggedIn(false);
    };

    return (
        <AuthContext.Provider value={{ role, token, login, logout, isLoggedIn }}>
            {children}
        </AuthContext.Provider>
    );
};
