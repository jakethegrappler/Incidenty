import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import '../css/Registration.css';

const Registration = () => {
    const navigate = useNavigate();
    const [formData, setFormData] = useState({
        firstName: '',
        lastName: '',
        username: '',
        phoneNumber: '',
        email: '',
        password: '',
        confirmPassword: '',
        role: 'STUDENT',
    });

    const [formErrors, setFormErrors] = useState({});
    const [isSubmitted, setIsSubmitted] = useState(false);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({
            ...formData,
            [name]: value,
        });
    };

    const validateForm = () => {
        let errors = {};

        if (!formData.firstName) errors.firstName = 'Křestní jméno je povinné.';
        if (!formData.lastName) errors.lastName = 'Příjmení je povinné.';
        if (!formData.username) errors.username = 'Uživatelské jméno je povinné.';
        if (!formData.phoneNumber) {
            errors.phoneNumber = 'Telefonní číslo je povinné.';
        } else if (!/^\d+$/.test(formData.phoneNumber)) {
            errors.phoneNumber = 'Telefonní číslo může obsahovat pouze číslice.';
        }
        if (!formData.email) {
            errors.email = 'E-mail je povinný.';
        } else if (!/\S+@\S+\.\S+/.test(formData.email)) {
            errors.email = 'E-mail není platný.';
        }
        if (!formData.password) {
            errors.password = 'Heslo je povinné.';
        } else if (formData.password.length < 6) {
            errors.password = 'Heslo musí mít alespoň 6 znaků.';
        }
        if (formData.confirmPassword !== formData.password) {
            errors.confirmPassword = 'Hesla se neshodují.';
        }

        setFormErrors(errors);
        return Object.keys(errors).length === 0;
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        if (!validateForm()) return;

        const userDto = {
            username: formData.username,
            password: formData.password,
            email: formData.email,
            phoneNumber: formData.phoneNumber,
            firstName: formData.firstName,
            lastName: formData.lastName,
            role: formData.role,
        };

        try {
            const response = await fetch('http://localhost:8080/user/register', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    Accept: 'application/json',
                },
                body: JSON.stringify(userDto),
            });

            if (response.ok) {
                const data = await response.json();
                console.log('Token:', data.token);
                setIsSubmitted(true);
                navigate('/login');
            } else {
                console.error('Registrace selhala:', await response.text());
            }
        } catch (error) {
            console.error('Chyba při odeslání:', error);
        }
    };

    return (
        <div className="registration-container">
            <h2>Registrace</h2>
            <form onSubmit={handleSubmit}>
                <input type="text" name="firstName" placeholder="Křestní jméno" onChange={handleChange} />
                {formErrors.firstName && <p className="error">{formErrors.firstName}</p>}

                <input type="text" name="lastName" placeholder="Příjmení" onChange={handleChange} />
                {formErrors.lastName && <p className="error">{formErrors.lastName}</p>}

                <input type="text" name="username" placeholder="Uživatelské jméno" onChange={handleChange} />
                {formErrors.username && <p className="error">{formErrors.username}</p>}

                <input type="text" name="phoneNumber" placeholder="Telefon" onChange={handleChange} />
                {formErrors.phoneNumber && <p className="error">{formErrors.phoneNumber}</p>}

                <input type="email" name="email" placeholder="E-mail" onChange={handleChange} />
                {formErrors.email && <p className="error">{formErrors.email}</p>}

                <input type="password" name="password" placeholder="Heslo" onChange={handleChange} />
                {formErrors.password && <p className="error">{formErrors.password}</p>}

                <input type="password" name="confirmPassword" placeholder="Potvrzení hesla" onChange={handleChange} />
                {formErrors.confirmPassword && <p className="error">{formErrors.confirmPassword}</p>}

                <select name="role" value={formData.role} onChange={handleChange}>
                    <option value="STUDENT">Student</option>
                    <option value="EMPLOYEE">Zaměstnanec</option>
                    <option value="ADMIN">Admin</option>
                </select>

                <button type="submit">Registrovat se</button>
                {isSubmitted && <p className="success">Registrace proběhla úspěšně!</p>}
            </form>
        </div>
    );
};

export default Registration;
