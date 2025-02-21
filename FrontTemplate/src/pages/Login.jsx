import React, {useState} from 'react';
import { useNavigate } from 'react-router-dom';
import '../CSS/Login.css';

const Login = () => {

    const navigate = useNavigate();
    const [formData, setFormData] = useState({
        username: "",
        password: ""
    });

    const handleChange = (e) => {
        const {name, value} = e.target;
        setFormData({
            ...formData,
            [name]: value,
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        const customer = new FormData();
        customer.append("username", formData.username);
        customer.append("password", formData.password);

        try {
            const response = await fetch('http://localhost:8080/login', {
                method: 'POST',
                headers: {
                    'Connection': 'keep-alive',
                },
                credentials: "include",
                body: customer
            });

            if (response.ok) {
                const data = response.json();
                console.log('Login successful:', data);
                sessionStorage.setItem("user", true);
                await cachUserInfo();
                navigate('/profil');

                if (success) {
                    console.log('Login successful:', success);
                    sessionStorage.setItem("user", true);
                    await cachUserInfo();
                    //window.location.reload();
                    navigate('/profil');
                }

            } else {
                console.error('Error registering customer:', response.statusText);
            }
        } catch (error) {
            console.error('Error:', error);
        }

    }

    const cachUserInfo = async () => {
        try {
            const response = await fetch('http://localhost:8080/customer/info', {
                method: 'GET',
                headers: {
                    'Connection': 'keep-alive',
                },
                 credentials: 'include',
            });


            if (response.ok) {
                const data = await response.json();
                console.log('Info successful received:', data);

                sessionStorage.setItem('userInfo', JSON.stringify(data));

                console.log('First Name:', data.firstName);
                console.log('Last Name:', data.lastName);

            } else {
                console.error('Error getting customer info:', response.statusText);
            }
        }
        catch(error)
        {
        console.error('Error:', error);
        }
    };

    return (
        <div className="login">
            <form onSubmit={handleSubmit}>
                <div className="form-group">
                    <label htmlFor="username"></label>
                    <input onChange={handleChange} value={formData.username} type="text" id="username" name="username" placeholder="Uživatelské jméno:" />
                </div>
                <div className="form-group">
                    <label htmlFor="password"></label>
                    <input onChange={handleChange} value={formData.password} type="password" id="password" name="password" placeholder="Heslo:" />
                </div>
                <button id="login" type="submit">Přihlásit se</button>
            </form>
        </div>
    );
};

export default Login;
