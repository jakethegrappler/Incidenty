import { useState } from "react";
import "../css/Header.css";
import logo from "../assets/Incidenty-logo.png";
function Header() {
    const [menuOpen, setMenuOpen] = useState(false);

    const toggleMenu = () => {
        setMenuOpen((prev) => !prev);
    };

    return (
        <header className="header">
            <div className="logo-container">
               <img src={logo} alt="logo picture" className="logo" />
            </div>
            <div className="menu-wrapper">
                <button className="menu-toggle" onClick={toggleMenu}>
                    {menuOpen ? "☰" : "☰"}
                </button>
                {menuOpen && (
                    <ul className="dropdown-nav">
                        <li><a href="/home">Hlavní stránka</a></li>
                        <li><a href="/report">Nahlasit</a></li>
                        <li><a href="/registrace">Registrace</a></li>
                        <li><a href="/login">Login</a></li>
                    </ul>
                )}
            </div>
        </header>
    );
}

export default Header;
