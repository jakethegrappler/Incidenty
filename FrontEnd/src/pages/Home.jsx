import { useState } from "react";
import { useNavigate } from "react-router-dom";
import IncidentsMap from "../components/IncidentsMap";
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

    const handleSectorClick = (sector) => {
        console.log("Klikl jsi na sektor:", sector);
        // Sem později můžeme dát přesměrování na detail sektoru
    };

    const toggleDropdown = () => {
        setDropdownOpen((prev) => !prev);
    };

    const selectIncident = (type) => {
        setDropdownOpen(false);
        navigate("/report", { state: { selectedType: type } });
    };

    return (
        <div className="page-wrapper fade-in">
            <h1 className="page-title">Mapa Kampusu FEL</h1>

            <div className="map-container">
                <IncidentsMap onSectorClick={handleSectorClick} />
            </div>

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
    );
}

export default MapPage;
