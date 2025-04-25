import { useEffect, useState } from "react";
import { useAuth } from "../auth/useAuth";
import { useLocation } from "react-router-dom";
import "../css/ReportForm.css";

function ReportForm() {
    const { user } = useAuth();
    const location = useLocation();
    const selectedType = location.state?.selectedType || "NEZNÁMÝ";

    const [form, setForm] = useState({
        datetime: "",
        location: "",
        customPhoneNumber: "",
        description: "",
        reporter: "",
        type: selectedType,
        photo: null,
    });

    useEffect(() => {
        if (user) {
            setForm((prev) => ({
                ...prev,
                reporter: user.email,
                customPhoneNumber: user.phoneNumber || "",
            }));
        }
    }, [user]);

    const handleChange = (e) => {
        const { name, value, files } = e.target;
        setForm((prev) => ({
            ...prev,
            [name]: files ? files[0] : value,
        }));
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        const formData = new FormData();
        const incidentDto = {
            date: form.datetime,
            type: form.type,
            position: form.location,
            reporter: user?.email || "Anonym",
            izs: "TBA",
            detail: form.description,
            solution: null,
            note: null,
            issueDate: null,
            customPhoneNumber: !user ? form.customPhoneNumber || null : null,
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

            alert("Incident byl úspěšně nahlášen!");
            setForm({
                datetime: "",
                location: "",
                customPhoneNumber: "",
                description: "",
                reporter: user?.email || form.reporter || "Anonym",
                type: selectedType,
                photo: null,
            });
        } catch (err) {
            console.error("Chyba:", err);
            alert("Nahlášení selhalo.");
        }
    };

    return (
        <div className="main-wrapper">
            <main className="main-content">
                <h1>Chci nahlásit: {form.type.toUpperCase()}</h1>

                <div className="map-placeholder">
                    <span>Mapa bude doplněna</span>
                </div>

                <form onSubmit={handleSubmit} className="report-form">
                    <input
                        type="datetime-local"
                        name="datetime"
                        value={form.datetime}
                        onChange={handleChange}
                        required />
                    <input
                        type="text"
                        name="location"
                        placeholder="Lokace incidentu"
                        value={form.location}
                        onChange={handleChange}
                        required />

                    {!user && (
                        <input
                            type="text"
                            name="customPhoneNumber"
                            value={form.customPhoneNumber}
                            onChange={handleChange}
                            placeholder="Telefon (nepovinné)"
                        />
                    )}

                    <input
                        type="email"
                        name="reporter"
                        value={form.reporter}
                        onChange={handleChange}
                        placeholder="E-mail"
                        required
                    />

                    <textarea name="description" value={form.description} onChange={handleChange} required />
                    <input type="file" name="photo" accept="image/*" onChange={handleChange} />
                    <button type="submit">Nahlásit</button>
                </form>
            </main>
        </div>
    );
}

export default ReportForm;
