import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import '../css/Login.css';

const Login = () => {
    const navigate = useNavigate();
    const [credentials, setCredentials] = useState({
        email: '',
        password: ''
    });
    const [error, setError] = useState('');

    const handleChange = (e) => {
        const { name, value } = e.target;
        setCredentials({ ...credentials, [name]: value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        setError('');

        try {
            const response = await fetch('http://localhost:8080/user/login', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    Accept: 'application/json',
                },
                credentials: 'include',
                body: JSON.stringify(credentials),
            });

            if (response.ok) {
                const data = await response.json();
                console.log('Token:', data.token);
                navigate('/');
            } else {
                const msg = await response.text();
                setError(msg || 'Přihlášení selhalo.');
            }
        } catch (err) {
            setError('Chyba připojení k serveru.');
        }
    };

    return (
        <div className="page-content">
        <div className="login-container">
            <h2>Přihlášení</h2>
            <form onSubmit={handleSubmit}>
                <input
                    type="email"
                    name="email"
                    placeholder="E-mail"
                    onChange={handleChange}
                    required
                />
                <input
                    type="password"
                    name="password"
                    placeholder="Heslo"
                    onChange={handleChange}
                    required
                />
                <button type="submit">Přihlásit se</button>
                {error && <p className="error">{error}</p>}
            </form>
        </div>
        </div>
    );
};

export default Login;
