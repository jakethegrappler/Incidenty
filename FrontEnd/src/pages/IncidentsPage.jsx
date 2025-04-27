import { useEffect, useState } from "react";
import "../css/IncidentsPage.css";

const IncidentsPage = () => {
    const [incidents, setIncidents] = useState([]);
    const [editingIncident, setEditingIncident] = useState(null);
    const [form, setForm] = useState({ detail: "", solution: "", note: "" });

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
        });
    };

    const handleChange = (e) => {
        const { name, value } = e.target;
        setForm((prev) => ({ ...prev, [name]: value }));
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
                alert("Incident úspěšně aktualizován!");
                setEditingIncident(null);
                fetchIncidents(); // reload
            } else {
                console.error("Chyba při ukládání:", await response.text());
            }
        } catch (error) {
            console.error("Chyba:", error);
        }
    };

    return (
        <div className="page-wrapper fade-in">
            <h2 className="incidents-title">Databáze Incidentů</h2>

            <div className="table-wrapper">
                <table className="incidents-table">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Datum</th>
                        <th>Typ</th>
                        <th>Lokace</th>
                        <th>IZS</th>
                        <th>Reporter</th>
                        <th>Vyřešeno dne</th>
                        <th>Detail</th>
                        <th>Řešení</th>
                        <th>Poznámka</th>
                        <th>Telefon</th>
                        <th>Akce</th>
                    </tr>
                    </thead>
                    <tbody>
                    {incidents.map((incident) => (
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
                            <td>
                                <button className="edit-btn" onClick={() => handleEditClick(incident)}>Upravit</button>
                            </td>
                        </tr>
                    ))}
                    </tbody>
                </table>
            </div>

            {/* POPUP okno */}
            {editingIncident && (
                <div className="popup-backdrop">
                    <div className="popup-content">
                        <h3>Upravit Incident #{editingIncident.id}</h3>
                        <input
                            type="text"
                            name="detail"
                            placeholder="Detail"
                            value={form.detail}
                            onChange={handleChange}
                        />
                        <input
                            type="text"
                            name="solution"
                            placeholder="Řešení"
                            value={form.solution}
                            onChange={handleChange}
                        />
                        <input
                            type="text"
                            name="note"
                            placeholder="Poznámka"
                            value={form.note}
                            onChange={handleChange}
                        />
                        <div className="popup-buttons">
                            <button onClick={handleSave}>💾 Uložit změny</button>
                            <button className="cancel-btn" onClick={() => setEditingIncident(null)}>❌ Zrušit</button>
                        </div>
                    </div>
                </div>
            )}
        </div>
    );
};

export default IncidentsPage;
