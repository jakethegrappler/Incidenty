import { useState } from "react";
import { useNavigate } from "react-router-dom";
import "../css/Home.css";

function MapPage() {
    const [dropdownOpen, setDropdownOpen] = useState(false);
    const navigate = useNavigate();

    const incidentTypes = [
        "Napadení",
        "Havárie",
        "Vandalismus",
        "Krádež",
        "Požár",
        "Úraz"
    ];

    const toggleDropdown = () => {
        setDropdownOpen(!dropdownOpen);
    };

    const selectIncident = (type) => {
        setDropdownOpen(false);
        navigate("/report", { state: { selectedType: type } });
    };

    return (
        <div className="main-wrapper">
            <main className="main-content">
                <div className="map-placeholder">
                    <span>Mapa bude doplněna</span>
                </div>

                <div className="map-header">
                    <div className="dropdown-container">
                        <button onClick={toggleDropdown} className="dropdown-toggle">
                            {dropdownOpen ? "▲" : "▼"} Chci nahlásit:
                        </button>
                        {dropdownOpen && (
                            <ul className="dropdown-menu">
                                {incidentTypes.map((type, index) => (
                                    <li key={index} onClick={() => selectIncident(type)}>
                                        {type}
                                    </li>
                                ))}
                            </ul>
                        )}
                    </div>
                </div>
            </main>
        </div>
    );
}

export default MapPage;
