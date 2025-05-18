import {useEffect, useState} from "react";
import {useAuth} from "../auth/useAuth";
import {useLocation} from "react-router-dom";
import IncidentsMap from "../components/IncidentsMap";
import "../css/ReportForm.css";

function ReportForm() {
    const {user} = useAuth();
    const location = useLocation();
    const selectedType = location.state?.selectedType || "NEZNÁMÝ";
    const [incidentTypes, setIncidentTypes] = useState([]);


    const formatLocalDatetime = (date) => {
        const pad = (n) => String(n).padStart(2, "0");

        const year = date.getFullYear();
        const month = pad(date.getMonth() + 1);
        const day = pad(date.getDate());
        const hours = pad(date.getHours());
        const minutes = pad(date.getMinutes());

        return `${year}-${month}-${day}T${hours}:${minutes}`;
    };

    const now = new Date();


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
        console.log("VITE_API_URL:", import.meta.env.VITE_API_URL);

        if (user) {
            setForm((prev) => ({
                ...prev,
                reporter: user.email,
                customPhoneNumber: user.phoneNumber || "",
            }));
        }
        fetch(`${import.meta.env.VITE_API_URL}/incident/incident-types`)
            .then((res) => res.json())
            .then((data) => {
                console.log("Načtené typy:", data)
                setIncidentTypes(data)
            })
            .catch((err) => console.error("Nepodařilo se načíst typy incidentů:", err));
    }, [user]);

    const MAX_FILE_SIZE = 5 * 1024 * 1024; // 5 MB

    const handleChange = (e) => {
        const {name, value, files} = e.target;
        console.log(now);

        if (name === "photo" && files && files[0]) {
            const file = files[0];
            if (file.size > MAX_FILE_SIZE) {
                setFormError("Fotka je příliš velká. Maximální velikost je 5 MB.");
                return;
            }
        }

        let newValue = value;

        if (name === "customPhoneNumber") {
            // 🔢 Odstranění všech nečíselných znaků
            const digits = value.replace(/\D/g, "").slice(0, 9);

            // 💄 Formátování do trojic (777 123 456)
            const part1 = digits.slice(0, 3);
            const part2 = digits.slice(3, 6);
            const part3 = digits.slice(6, 9);
            newValue = [part1, part2, part3].filter(Boolean).join("-");
        }

        setForm((prev) => ({
            ...prev,
            [name]: files ? files[0] : newValue,
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


        if (form.datetime > now) {
            setFormError("Datum incidentu nemůže být v budoucnosti.");
            return;

        }
        if (form.description == null) {
            setFormError("Popište co se stalo.");
            return;
        }
        const cleanedPhone = form.customPhoneNumber.replace(/\D/g, "");

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
            customPhoneNumber: user?.phoneNumber || cleanedPhone,
            x: form.x,
            y: form.y
        };

        formData.append("incident", new Blob([JSON.stringify(incidentDto)], {type: "application/json"}));
        if (form.photo) formData.append("photo", form.photo);

        try {
            const headers = {};
            const token = localStorage.getItem("token");
            if (token) {
                headers["Authorization"] = `Bearer ${token}`;
            }

            const response = await fetch(`${import.meta.env.VITE_API_URL}/incident/create`, {
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
                type: "",
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
                {/*<h1 className="form-title">Nahlásit: {form.type.toUpperCase()}</h1>*/}

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
                        selectedPoint={form.x && form.y ? { x: form.x, y: form.y } : null}

                    />
                    <p className="selected-sector-info">
                        Vybraná lokace: <strong>{form.location || ""}</strong>
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

                    <select
                        name="type"
                        value={form.type}
                        onChange={handleChange}
                        required>
                        <option value="">-- vyberte typ incidentu --</option>

                        {incidentTypes.map((t) => (
                            <option key={t} value={t}>{t}</option>
                        ))}
                    </select>


                    <input
                        type="datetime-local"
                        name="datetime"
                        value={form.datetime}
                        onChange={handleChange}
                        max={formatLocalDatetime(now)}

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
                        placeholder="Telefonní číslo(XXX-YYY-ZZZ)"
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
                        className="upload-button"
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
