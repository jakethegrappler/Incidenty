import { useState } from "react";
import { useNavigate } from "react-router-dom";
import IncidentsMap from "../components/IncidentsMap";
import SectorStatsModal from "../components/SectorStatsModal";
import "../css/Home.css";

function MapPage() {
    const [dropdownOpen, setDropdownOpen] = useState(false);
    const [selectedSector, setSelectedSector] = useState(null);
    const [sectorStats, setSectorStats] = useState(null);
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
        setDropdownOpen(prev => !prev);
    };

    const selectIncident = (type) => {
        setDropdownOpen(false);
        navigate("/report", { state: { selectedType: type } });
    };

    const handleSectorClick = async (sector) => {
        try {
            const response = await fetch(`http://localhost:8080/incident/stats/${sector}`);
            if (!response.ok) throw new Error("Chyba při načítání statistik.");
            const data = await response.json();
            setSelectedSector(sector);
            setSectorStats(data);
        } catch (err) {
            console.error(err);
            alert("Nepodařilo se načíst statistiky sektoru.");
        }
    };

    const closeModal = () => {
        setSelectedSector(null);
        setSectorStats(null);
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

            {/* 📊 Modální okno se statistikami */}
            {selectedSector && sectorStats && (
                <SectorStatsModal
                    sector={selectedSector}
                    stats={sectorStats}
                    onClose={closeModal}
                />
            )}
        </div>
    );
}

export default MapPage;
