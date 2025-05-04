import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import IncidentsMap from "../components/IncidentsMap";
import SectorStatsModal from "../components/SectorStatsModal";
import "../css/Home.css";

function MapPage() {
    const [dropdownOpen, setDropdownOpen] = useState(false);
    const [selectedSector, setSelectedSector] = useState(null);
    const [sectorStats, setSectorStats] = useState(null);
    const [incidentPoints, setIncidentPoints] = useState([]);

    const [selectedDays, setSelectedDays] = useState(0); // 0 = bez omezení
    const [selectedTypes, setSelectedTypes] = useState([]);

    const navigate = useNavigate();

    const incidentTypes = [
        "Napadení",
        "Havárie",
        "Vandalismus",
        "Krádež",
        "Požár",
        "Úraz"
    ];

    useEffect(() => {
        fetch("http://localhost:8080/incident/all")
            .then(res => res.json())
            .then(data => setIncidentPoints(data))
            .catch(err => console.error("Chyba načítání incidentů:", err));
    }, []);

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

    const filteredIncidents = incidentPoints.filter((incident) => {
        const incidentDate = new Date(incident.date);

        // filtr: čas
        if (selectedDays > 0) {
            const now = new Date();
            const cutoff = new Date();
            cutoff.setDate(now.getDate() - selectedDays);
            if (incidentDate < cutoff) return false;
        }

        // filtr: typ
        if (selectedTypes.length > 0 && !selectedTypes.includes(incident.type)) {
            return false;
        }

        return true;
    });

    return (
        <div className="page-wrapper fade-in">
            <h1 className="page-title">Mapa Kampusu FEL</h1>

            {/* 🔍 Filtrační panel */}
            <div className="filter-panel">
                <label>
                    Časové období:&nbsp;
                    <select value={selectedDays} onChange={(e) => setSelectedDays(Number(e.target.value))}>
                        <option value={0}>Vše</option>
                        <option value={7}>Posledních 7 dní</option>
                        <option value={14}>Posledních 14 dní</option>
                        <option value={31}>Posledních 31 dní</option>
                    </select>
                </label>

                <div className="type-filter">
                    <p><strong>Typy incidentů:</strong></p>
                    <div className="checkbox-group">
                        {incidentTypes.map((type) => (
                            <label key={type}>
                                <input
                                    type="checkbox"
                                    value={type}
                                    checked={selectedTypes.includes(type)}
                                    onChange={(e) => {
                                        const checked = e.target.checked;
                                        const value = e.target.value;
                                        setSelectedTypes((prev) =>
                                            checked
                                                ? [...prev, value]
                                                : prev.filter((t) => t !== value)
                                        );
                                    }}
                                />
                                {type}
                            </label>
                        ))}
                    </div>
                </div>

            </div>

            <div className="map-container">
                <IncidentsMap
                    onSectorClick={handleSectorClick}
                    incidents={filteredIncidents}
                />
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
