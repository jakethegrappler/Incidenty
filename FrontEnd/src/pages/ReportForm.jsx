import { useEffect, useState } from "react";
import { useAuth } from "../auth/useAuth"; // uprav cestu dle umístění
import "../css/ReportForm.css";

function ReportForm() {
    const { user } = useAuth();

    const [form, setForm] = useState({
        datetime: "",
        location: "",
        phone: "",
        description: "",
        reporter: "",
        photo: null,
    });

    // Předvyplnění údajů při načtení stránky, pokud je uživatel přihlášen
    useEffect(() => {

        if (user) {
            console.log(user.email);
            console.log(user.phoneNumber);
            console.log(user.role);
            setForm((prev) => ({
                ...prev,
                reporter: user.email,
                phone: user.phoneNumber || "",
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
            type: form.type || "NEZNÁMÝ", // nebo co používáš
            position: form.location,
            reporter: user?.email || null,
            izs: "NEZNÁMÉ",
            detail: form.description,
            solution: null,
            note: null,
            issueDate: null
        };

        formData.append("incident", new Blob(
            [JSON.stringify(incidentDto)],
            { type: "application/json" }
        ));

        if (form.photo) {
            formData.append("photo", form.photo);
        }


        try {
            const response = await fetch("http://localhost:8080/incident/create", {
                method: "POST",
                body: formData,
                headers: {
                    Authorization: `Bearer ${localStorage.getItem("token")}`,
                },
            });

            if (!response.ok) throw new Error(await response.text());

            alert("Incident byl úspěšně nahlášen!");
            setForm({
                datetime: "",
                location: "",
                phone: "",
                description: "",
                reporter: user?.email || "",
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
                <h1>Chci nahlásit: NAPADENÍ</h1>

                <div className="map-placeholder">
                    <span>Mapa bude doplněna</span>
                </div>

                <form onSubmit={handleSubmit} className="report-form">
                    <input
                        type="datetime-local"
                        name="datetime"
                        value={form.datetime}
                        onChange={handleChange}
                        placeholder="Datum a čas incidentu"
                        required
                    />
                    <input
                        type="text"
                        name="location"
                        value={form.location}
                        onChange={handleChange}
                        placeholder="Místo incidentu"
                        required
                    />
                    <input
                        type="text"
                        name="phone"
                        value={form.phone}
                        onChange={handleChange}
                        placeholder="Telefon"
                        required
                    />
                    <input
                        type="email"
                        name="reporter"
                        value={form.reporter}
                        onChange={handleChange}
                        placeholder="E-mail oznamovatele"
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
                    <button type="submit">Nahlásit</button>
                </form>
            </main>
        </div>
    );
}

export default ReportForm;
