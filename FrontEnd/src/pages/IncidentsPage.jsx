import { useEffect, useState } from "react";
import {CSVLink} from "react-csv";

import "../css/IncidentsPage.css";

const IncidentsPage = () => {
    const [incidents, setIncidents] = useState([]);
    const [editingIncident, setEditingIncident] = useState(null);

    const [showToast, setShowToast] = useState(false);
    const [errorMessage, setErrorMessage] = useState("");
    const [searchQuery, setSearchQuery] = useState("");

    const [currentPage, setCurrentPage] = useState(1);
    const itemsPerPage = 15;

    const [sortConfig, setSortConfig] = useState({ key: "date", direction: "desc" });

    const [form, setForm] = useState({
        detail: "",
        solution: "",
        note: "",
        issueDate: "",
        verified: false,
        izs: "Ne"
    });


    useEffect(() => {
        fetchIncidents();
    }, []);

    const fetchIncidents = async () => {
        try {
            const token = localStorage.getItem("token");
            const response = await fetch("http://localhost:8080/incident/all", {
                headers: { Authorization: `Bearer ${token}` },
            });

            if (response.ok) {
                const data = await response.json();
                setIncidents(data);
            } else {
                console.error("Chyba při načítání incidentů:", await response.text());
            }
        } catch (error) {
            console.error("Chyba:", error);
        }
    };

    const handleEditClick = (incident) => {
        setEditingIncident(incident);
        setForm({
            detail: incident.detail || "",
            solution: incident.solution || "",
            note: incident.note || "",
            issueDate: incident.issueDate || "",
            verified: incident.verified || false,
            izs: incident.izs || "Ne"
        });

    };

    const handleChange = (e) => {
        const { name, value } = e.target;
        setForm((prev) => ({ ...prev, [name]: value }));
    };

    const triggerToast = () => {
        setShowToast(true);
        setTimeout(() => setShowToast(false), 2500);
    };

    const triggerErrorToast = (message) => {
        setErrorMessage(message);
        setTimeout(() => setErrorMessage(""), 3000);
    };

    const handleSave = async () => {
        try {
            const token = localStorage.getItem("token");
            const response = await fetch(`http://localhost:8080/incident/update/${editingIncident.id}`, {
                method: "PUT",
                headers: {
                    "Content-Type": "application/json",
                    Authorization: `Bearer ${token}`,
                },
                body: JSON.stringify(form),
            });

            if (response.ok) {
                setEditingIncident(null);
                fetchIncidents();
                triggerToast();
            } else {
                const text = await response.text();
                console.error("Chyba při ukládání změn:", text);
                triggerErrorToast("❌ Nepodařilo se uložit změny.");
            }
        } catch (error) {
            console.error("Chyba:", error);
            triggerErrorToast("❌ Chyba při komunikaci se serverem.");
        }
    };

    const filteredIncidents = incidents.filter((incident) => {
        const query = searchQuery.toLowerCase();
        return (
            incident.type?.toLowerCase().includes(query) ||
            incident.position?.toLowerCase().includes(query) ||
            incident.reporter?.toLowerCase().includes(query) //||
            // incident.detail?.toLowerCase().includes(query)
        );
    });

    const sortedIncidents = [...filteredIncidents].sort((a, b) => {
        const { key, direction } = sortConfig;
        let aVal = a[key];
        let bVal = b[key];

        if (key === "date" || key === "issueDate") {
            aVal = aVal || "";
            bVal = bVal || "";
        }

        if (aVal < bVal) return direction === "asc" ? -1 : 1;
        if (aVal > bVal) return direction === "asc" ? 1 : -1;
        return 0;
    });


    const indexOfLastItem = currentPage * itemsPerPage;
    const indexOfFirstItem = indexOfLastItem - itemsPerPage;
    const currentIncidents = sortedIncidents.slice(indexOfFirstItem, indexOfLastItem);

    const csvHeaders = [
        { label: "ID", key: "id" },
        { label: "Datum", key: "date" },
        { label: "Typ", key: "type" },
        { label: "Lokace", key: "position" },
        { label: "IZS", key: "izs" },
        { label: "Reporter", key: "reporter" },
        { label: "Vyřešeno", key: "issueDate" },
        { label: "Detail", key: "detail" },
        { label: "Řešení", key: "solution" },
        { label: "Poznámka", key: "note" },
        { label: "Ověřeno", key: "verified" },
        { label: "Telefon", key: "customPhoneNumber" }
    ];

    const csvData = incidents.map((incident) => ({
        ...incident,
        date: incident.date?.substring(0, 10),
        issueDate: incident.issueDate?.substring(0, 10),
        verified: incident.verified ? "Ano" : "Ne"
    }));


    const totalPages = Math.ceil(incidents.length / itemsPerPage);

    const handleSort = (column) => {
        setSortConfig((prev) => ({
            key: column,
            direction: prev.key === column && prev.direction === "asc" ? "desc" : "asc"
        }));
    };

    return (
        <div className="page-wrapper fade-in">

            <div className="search-bar">
                <input
                    type="text"
                    placeholder="🔍 Zadejte detaily"
                    value={searchQuery}
                    onChange={(e) => {
                        setSearchQuery(e.target.value);
                        setCurrentPage(1); // reset na 1. stránku při vyhledávání
                    }}
                />
            </div>

            <div className="table-wrapper">
                <table className="incidents-table">
                    <thead>
                    <tr>
                        <th onClick={() => handleSort("id")}>
                            ID {sortConfig.key === "id" && (sortConfig.direction === "asc" ? "↑" : "↓")}
                        </th>
                        <th onClick={() => handleSort("date")}>
                            Datum {sortConfig.key === "date" && (sortConfig.direction === "asc" ? "↑" : "↓")}
                        </th>
                        <th onClick={() => handleSort("type")}>
                            Typ {sortConfig.key === "type" && (sortConfig.direction === "asc" ? "↑" : "↓")}
                        </th>
                        <th onClick={() => handleSort("position")}>
                            Lokace {sortConfig.key === "position" && (sortConfig.direction === "asc" ? "↑" : "↓")}
                        </th>
                        <th onClick={() => handleSort("izs")}>
                            IZS {sortConfig.key === "izs" && (sortConfig.direction === "asc" ? "↑" : "↓")}
                        </th>
                        <th onClick={() => handleSort("reporter")}>
                            Reporter {sortConfig.key === "reporter" && (sortConfig.direction === "asc" ? "↑" : "↓")}
                        </th>
                        <th onClick={() => handleSort("issueDate")}>
                            Vyřešeno
                            dne {sortConfig.key === "issueDate" && (sortConfig.direction === "asc" ? "↑" : "↓")}
                        </th>
                        <th>Detail</th>
                        <th>Řešení</th>
                        <th>Poznámka</th>
                        <th>Telefon</th>
                        <th>Oveřeno</th>
                        <th>Foto</th>
                        <th>Akce</th>


                    </tr>
                    </thead>

                    <tbody>
                    {currentIncidents.map((incident) => (
                        <tr key={incident.id}>
                            <td>{incident.id}</td>
                            <td>{incident.date ? incident.date.substring(0, 10) : "-"}</td>
                            <td>{incident.type}</td>
                            <td>{incident.position}</td>
                            <td>{incident.izs}</td>
                            <td>{incident.reporter || "Anonym"}</td>
                            <td>{incident.issueDate ? incident.issueDate.substring(0, 10) : "-"}</td>
                            <td>{incident.detail || "-"}</td>
                            <td>{incident.solution || "-"}</td>
                            <td>{incident.note || "-"}</td>
                            <td>{incident.customPhoneNumber || "-"}</td>
                            <td>{incident.verified ? "✅" : "✖️"}</td>
                            <td>
                                {incident.photoPath ? (
                                    <a
                                        href={`http://localhost:8080/${incident.photoPath}`}
                                        target="_blank"
                                        rel="noopener noreferrer"
                                        title="Zobrazit fotku"
                                    >
                                        📷
                                    </a>
                                ) : (
                                    "-"
                                )}
                            </td>
                            <td>
                                <button className="edit-btn" onClick={() => handleEditClick(incident)}>
                                    Upravit
                                </button>
                            </td>

                        </tr>
                    ))}
                    </tbody>
                </table>
            </div>


            {/* Stránkování */}
            <div className="pagination">
                {Array.from({length: totalPages}, (_, i) => (
                    <button
                        key={i + 1}
                        className={`page-btn ${currentPage === i + 1 ? "active" : ""}`}
                        onClick={() => setCurrentPage(i + 1)}
                    >
                        {i + 1}
                    </button>
                ))}
            </div>

            <div className="table-toolbar">
                <CSVLink
                    data={csvData}
                    headers={csvHeaders}
                    separator=";"
                    filename={`incidenty_${new Date().toISOString().slice(0, 10)}.csv`}
                    className="export-btn"
                >
                    📤 Export do CSV
                </CSVLink>
            </div>

            {/* POPUP pro úpravu */}
            {editingIncident && (
                <div className="popup-backdrop">
                    <div className="popup-content">
                        <h3>Upravit Incident #{editingIncident.id}</h3>

                        <label htmlFor="issueDate">Datum vyřešení incidentu</label>
                        <input
                            id="issueDate"
                            type="datetime-local"
                            name="issueDate"
                            value={form.issueDate}
                            onChange={handleChange}
                        />

                        <label htmlFor="izs">IZS</label>
                        <select
                            id="izs"
                            name="izs"
                            value={form.izs}
                            onChange={handleChange}
                        >
                            <option value="Ne">Ne</option>
                            <option value="PČR">PČR</option>
                            <option value="HZS">HZS</option>
                            <option value="ZZSHMP">ZZSHMP</option>
                        </select>

                        <label htmlFor="detail">Detail</label>
                        <input
                            id="detail"
                            type="text"
                            name="detail"
                            value={form.detail}
                            onChange={handleChange}
                        />

                        <label htmlFor="solution">Řešení</label>
                        <input
                            id="solution"
                            type="text"
                            name="solution"
                            value={form.solution}
                            onChange={handleChange}
                        />

                        <label htmlFor="note">Poznámka</label>
                        <input
                            id="note"
                            type="text"
                            name="note"
                            value={form.note}
                            onChange={handleChange}
                        />

                        <label>
                            <input
                                type="checkbox"
                                name="verified"
                                checked={form.verified || false}
                                onChange={(e) =>
                                    setForm((prev) => ({
                                        ...prev,
                                        verified: e.target.checked,
                                    }))
                                }
                            />
                            Ověřeno
                        </label>

                        <div className="popup-buttons">
                            <button onClick={handleSave} className="save-btn">💾 Uložit změny</button>
                            <button className="cancel-btn" onClick={() => setEditingIncident(null)}>❌ Zrušit</button>
                        </div>
                    </div>
                </div>
            )}


            {/* Toast zprávy */}
            {showToast && <div className="toast-success">✅ Úprava incidentu byla úspěšná!</div>}
            {errorMessage && <div className="toast-error">{errorMessage}</div>}
        </div>
    );
};

export default IncidentsPage;
