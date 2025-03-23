import React, { useState } from "react";
import "../css/Header.css";

function Header() {
    const [menuOpen, setMenuOpen] = useState(false);

    const toggleMenu = () => {
        setMenuOpen((prev) => !prev);
    };

    return (
        <header className="header">
            <div className="logo">LOGO</div>
            <div className="menu-wrapper">
                <button className="menu-toggle" onClick={toggleMenu}>
                    {menuOpen ? "☰" : "☰"}
                </button>
                {menuOpen && (
                    <ul className="dropdown-nav">
                        <li><a href="/home">Hlavní stránka</a></li>
                        <li><a href="/report">Nahlasit</a></li>
                        <li><a href="/history">Dalsi idk</a></li>
                    </ul>
                )}
            </div>
        </header>
    );
}

export default Header;
