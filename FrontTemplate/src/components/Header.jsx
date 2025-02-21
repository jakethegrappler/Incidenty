import React, { useState, useEffect } from 'react';
import '../CSS/Header.css';
import { Link } from 'react-router-dom';

const Header = () => {
    const [isMenuOpen, setIsMenuOpen] = useState(false);
    const [isAuthenticated, setIsAuthenticated] = useState(false);


    useEffect(() => {
        const userStatus = sessionStorage.getItem('user');
        setIsAuthenticated(userStatus === 'true');
    }, []);

    const toggleMenu = () => {
        setIsMenuOpen(!isMenuOpen);
    };



    const handleLogout = () => {
        try {
            const response = fetch('http://localhost:8080/customer/logout', {
                method: 'POST',
                headers: {
                    'Connection': 'keep-alive',
                },
                credentials: 'include',
            });

            if (response.ok) {
                console.log('Logout successful:');
            } else {
                console.error('Error registering customer:', response.statusText);
            }
        } catch (error) {
            console.error('Error:', error);
        }
        setIsAuthenticated(false);
        setIsMenuOpen(false);
        sessionStorage.clear();
    };

    return (
        <header className="header">
            <img className="logo" src="./public/NSSAUNA.png" alt="Logo" />
            <div className="burger-menu" onClick={toggleMenu}>
                &#9776;
            </div>
            {isMenuOpen && (
                <div className="dropdown-menu">
                    <Link to="/" onClick={toggleMenu}>Home</Link>
                    {!isAuthenticated ? (
                        <>
                            <Link to="/login" onClick={toggleMenu}>Login</Link>
                            <Link to="/registrace" onClick={toggleMenu}>Registrace</Link>
                        </>
                    ) : (
                        <>
                            <Link to="/profil" onClick={toggleMenu}>Profil</Link>
                            <button onClick={handleLogout} className={"ceremony-button"}>Logout</button>
                        </>
                    )}
                </div>
            )}
        </header>
    );
};

export default Header;
