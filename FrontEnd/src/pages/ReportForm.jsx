import { useEffect, useState } from "react";
import { useAuth } from "../auth/useAuth";
import { useLocation } from "react-router-dom";
import IncidentsMap from "../components/IncidentsMap";
import "../css/ReportForm.css";

function ReportForm() {
    const { user } = useAuth();
    const location = useLocation();

    const selectedType = location.state?.selectedType || "NEZNÁMÝ";

    const [form, setForm] = useState({
        datetime: "",
        location: "",
        x: null,
        y: null,
        customPhoneNumber: "",
        description: "",
        reporter: "",
        type: selectedType,
        photo: null,
    });

    const [successMessage, setSuccessMessage] = useState("");
    const [formError, setFormError] = useState("");

    useEffect(() => {
        if (user) {
            setForm((prev) => ({
                ...prev,
                reporter: user.email,
                customPhoneNumber: user.phoneNumber || "",
            }));
        }
    }, [user]);

    const MAX_FILE_SIZE = 5 * 1024 * 1024; // 5 MB

    const handleChange = (e) => {
        const { name, value, files } = e.target;

        if (name === "photo" && files && files[0]) {
            const file = files[0];
            if (file.size > MAX_FILE_SIZE) {
                setFormError("Fotka je příliš velká. Maximální velikost je 5 MB.");
                return;
            }
        }

        setForm((prev) => ({
            ...prev,
            [name]: files ? files[0] : value,
        }));
    };


    const handleSubmit = async (e) => {
        e.preventDefault();

        if (!form.location) {
            setFormError("Musíte vybrat lokaci na mapě.");
            return;
        }
        if (form.photo && form.photo.size > MAX_FILE_SIZE) {
            setFormError("Fotka přesahuje povolenou velikost.");
            return;
        }

        setFormError("");

        const formData = new FormData();
        const incidentDto = {
            date: form.datetime,
            type: form.type,
            position: form.location,
            reporter: user ? user.email : "Anonym",
            izs: "TBA",
            detail: form.description,
            solution: null,
            note: null,
            issueDate: null,
            customPhoneNumber: user?.phoneNumber || form.customPhoneNumber,
            x: form.x,
            y: form.y
        };

        formData.append("incident", new Blob([JSON.stringify(incidentDto)], { type: "application/json" }));
        if (form.photo) formData.append("photo", form.photo);

        try {
            const headers = {};
            const token = localStorage.getItem("token");
            if (token) {
                headers["Authorization"] = `Bearer ${token}`;
            }

            const response = await fetch("http://localhost:8080/incident/create", {
                method: "POST",
                body: formData,
                headers: headers,
            });

            if (!response.ok) throw new Error(await response.text());

            setSuccessMessage("Incident byl úspěšně nahlášen!");
            setForm({
                datetime: "",
                location: "",
                x: null,
                y: null,
                customPhoneNumber: "",
                description: "",
                reporter: user?.email || "",
                type: selectedType,
                photo: null,
            });

            setTimeout(() => setSuccessMessage(""), 5000);

        } catch (err) {
            console.error("Chyba:", err);
            alert("Nahlášení selhalo.");
        }
    };

    return (
        <div className="page-wrapper fade-in">
            <div className="form-container">
                <h1 className="form-title">Nahlásit: {form.type.toUpperCase()}</h1>

                <div className="map-container">
                    <IncidentsMap
                        onMapClick={(x, y, sector) => {
                            setForm((prev) => ({
                                ...prev,
                                location: sector,
                                x: Math.round(x),
                                y: Math.round(y)
                            }));
                            setFormError("");
                        }}
                    />
                    <p className="selected-sector-info">
                        Vybraná lokace: <strong>{form.location || "Žádná"}</strong>
                    </p>
                </div>

                {formError && (
                    <div className="error-message">
                        {formError}
                    </div>
                )}

                {successMessage && (
                    <div className="success-message animate-success">
                        {successMessage}
                    </div>
                )}

                <form onSubmit={handleSubmit} className="report-form">
                    <input
                        type="datetime-local"
                        name="datetime"
                        value={form.datetime}
                        onChange={handleChange}
                        required
                    />

                    <input
                        type="text"
                        name="location"
                        value={form.location}
                        readOnly
                        hidden
                    />

                    <input
                        type="text"
                        name="customPhoneNumber"
                        value={form.customPhoneNumber}
                        onChange={handleChange}
                        placeholder="Telefonní číslo"
                        required
                    />

                    <textarea
                        name="description"
                        value={form.description}
                        onChange={handleChange}
                        placeholder="Popis události"
                        required
                    />

                    <input
                        type="file"
                        name="photo"
                        accept="image/*"
                        onChange={handleChange}
                    />

                    <button type="submit">Nahlásit incident</button>
                </form>
            </div>
        </div>
    );
}

export default ReportForm;
