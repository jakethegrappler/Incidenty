import { useState } from "react";
import "../css/ReportForm.css";

function ReportForm() {
    const [form, setForm] = useState({
        datetime: "",
        location: "",
        phone: "",
        description: "",
        photo: null,
    });


    const handleChange = (e) => {
        const { name, value, files } = e.target;
        setForm((prev) => ({
            ...prev,
            [name]: files ? files[0] : value,
        }));

    };


    const handleSubmit = async (e) => {
        e.preventDefault();

        // Vytvoření FormData objektu pro odeslání souborů + textových dat
        const formData = new FormData();
        formData.append("datetime", form.datetime);
        formData.append("location", form.location);
        formData.append("phone", form.phone);
        formData.append("description", form.description);

        if (form.photo) {
            formData.append("photo", form.photo);
        }

        try {
            const response = await fetch("http://localhost:8080/incident/create", {
                method: "POST",
                credentials: "include", // Podpora autentizace v budoucnu
                body: formData
            });

            if (!response.ok) {
                throw new Error(`Chyba při odesílání: ${response.statusText}`);
            }

            const result = await response.json();
            console.log("Úspěšně odesláno:", result);
            alert("Incident byl úspěšně nahlášen!");

            // Reset formuláře po úspěšném odeslání
            setForm({
                datetime: "",
                location: "",
                phone: "",
                description: "",
                photo: null,
            });

        } catch (error) {
            console.error("Chyba:", error);
            alert("Nepodařilo se odeslat incident.");
        }
    };



    /**
     * @todo Misto "Chci nahlasti:NAPADENI" budu prebirat hodnotu z predchozi ReportPage zavisejici na zvolene hodnote z DDMenu
     * @todo Implement this function.
     */


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
                    />

                    <input
                        type="text"
                        name="location"
                        value={form.location}
                        onChange={handleChange}
                        placeholder="Místo incidentu"
                    />

                    <input
                        type="tel"
                        name="phone"
                        value={form.phone}
                        onChange={handleChange}
                        placeholder="Tel. číslo..."
                    />

                    <textarea
                        name="description"
                        value={form.description}
                        onChange={handleChange}
                        placeholder="Popis"
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
