import { useState } from "react";
import {Link, useNavigate} from "react-router-dom";
import { useAuth } from "../auth/useAuth.js";
import "../css/Header.css";
import logo from "../assets/Incidenty-logo.png";
// import login from "../pages/Login.jsx";

function Header() {
    const [menuOpen, setMenuOpen] = useState(false);
    const navigate = useNavigate();

    const { isLoggedIn, logout, user } = useAuth(); // ⬅️ nový způsob

    const toggleMenu = () => {
        // console.log(isLoggedIn);
        setMenuOpen((prev) => !prev);
    };

    const handleLogout = () => {
        logout(); // ⬅️ zavolá logout z AuthContext
        navigate("/home");
    };

    function navigateHome() {
        navigate("/home");
    }


    return (
        <header className="header">
            <div className="logo-container">
                <img src={logo} alt="logo picture" className="logo" onClick={navigateHome} />
            </div>
            <div className="center-button-wrapper">
                <a href="/report" className="reportB">📢 NAHLÁSIT INCIDENT</a>
            </div>
            <div className="menu-wrapper">
                <button className="menu-toggle" onClick={toggleMenu}>
                    ☰
                </button>
                {menuOpen && (
                    <ul className="dropdown-nav">
                        <li><a href="/home">Hlavní stránka</a></li>
                        <li><a href="/report">Nahlásit</a></li>

                        {isLoggedIn ? (
                            <>
                                {user?.role === "ROLE_ADMIN" && (
                                    <li><a href="/registrace">Registrace</a></li>
                                )}
                                <li><a href="/profile">Profil</a></li>
                                <li><button onClick={handleLogout} className="logout-btn">Odhlásit se</button></li>
                            </>
                        ) : (
                            // <Link to={login}>Login</Link>
                            <li><a href="/login">Login</a></li>
                        )}

                    </ul>
                )}
            </div>
        </header>
    );
}

export default Header;
