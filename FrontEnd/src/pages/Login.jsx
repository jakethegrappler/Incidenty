import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../auth/useAuth";
import "../css/Login.css";

const Login = () => {
    const { login } = useAuth();
    const navigate = useNavigate();
    const [credentials, setCredentials] = useState({ email: "", password: "" });
    const [error, setError] = useState("");

    const handleChange = (e) => {
        const { name, value } = e.target;
        setCredentials({ ...credentials, [name]: value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        setError("");

        try {
            const response = await fetch("http://localhost:8080/user/login", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                    Accept: "application/json",
                },
                body: JSON.stringify(credentials),
            });

            if (!response.ok) {
                throw new Error("Chybné přihlašovací údaje.");
            }

            const data = await response.json();
            const token = data.token;

            // dekódujeme JWT (nebo můžeš poslat i uživatelská data z backendu)
            const payload = JSON.parse(atob(token.split(".")[1]));

            login({
                email: payload.sub,
                token,
                role: payload.role,
            });

            navigate("/profile");
        } catch (err) {
            setError(err.message);
        }
    };

    return (
        <div className="login-wrapper">
            <h2>Přihlášení</h2>
            <form onSubmit={handleSubmit}>
                <input
                    type="email"
                    name="email"
                    placeholder="E-mail"
                    onChange={handleChange}
                />
                <input
                    type="password"
                    name="password"
                    placeholder="Heslo"
                    onChange={handleChange}
                />
                <button type="submit">Přihlásit se</button>
                {error && <p className="error">{error}</p>}
            </form>
        </div>
    );
};

export default Login;
