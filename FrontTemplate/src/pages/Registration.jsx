import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import '../CSS/Registration.css';


const Registration = () => {
    const navigate = useNavigate();
    const [formData, setFormData] = useState({
        firstName: '',
        lastName: '',
        username: '',
        phone: '',
        email: '',
        password: '',
        confirmPassword: '',
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

        if (!formData.firstName) {
            errors.firstName = 'Křestní jméno je povinné.';
        }

        if (!formData.lastName) {
            errors.lastName = 'Příjmení je povinné.';
        }

        if (!formData.username) {
            errors.lastName = 'Username je povinné.';
        }

        if (!formData.phone) {
            errors.phone = 'Telefonní číslo je povinné.';
        } else if (!/^\d+$/.test(formData.phone)) {
            errors.phone = 'Telefonní číslo může obsahovat pouze číslice.';
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

        if (!formData.confirmPassword) {
            errors.confirmPassword = 'Potvrzení hesla je povinné.';
        } else if (formData.confirmPassword !== formData.password) {
            errors.confirmPassword = 'Hesla se neshodují.';
        }

        setFormErrors(errors);
        return Object.keys(errors).length === 0;
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        if (validateForm()) {
            setIsSubmitted(true);
            const customer = {
                username: formData.username,
                password: formData.password,
                firstName: formData.firstName,
                lastName: formData.lastName,
                email: formData.email,
                phoneNumber: formData.phone
            }

            try {
                const response = await fetch('http://localhost:8080/customer/registration', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(customer)
                });

                if (response.ok) {
                    const data = await response.json();
                    console.log('Registration successful:', data);
                } else {
                    console.error('Error registering customer:', response.statusText);
                }
            } catch (error) {
                console.error('Error:', error);
            }

            navigate('/');
        }
    };

    return (
        <>
            <div className="registration-container">
                <h2>Registrace</h2>
                <form onSubmit={handleSubmit}>
                    <div>
                        <input
                            type="text"
                            name="firstName"
                            placeholder="Křestní jméno..."
                            value={formData.firstName}
                            onChange={handleChange}
                        />
                        {formErrors.firstName && <p className="error">{formErrors.firstName}</p>}
                    </div>
                    <div>
                        <input
                            type="text"
                            name="lastName"
                            placeholder="Příjmení..."
                            value={formData.lastName}
                            onChange={handleChange}
                        />
                        {formErrors.lastName && <p className="error">{formErrors.lastName}</p>}
                    </div>
                    <div>
                        <input
                            type="text"
                            name="username"
                            placeholder="Username..."
                            value={formData.username}
                            onChange={handleChange}
                        />
                        {formErrors.username && <p className="error">{formErrors.username}</p>}
                    </div>
                    <div>
                        <input
                            type="text"
                            name="phone"
                            placeholder="Tel. číslo..."
                            value={formData.phone}
                            onChange={handleChange}
                        />
                        {formErrors.phone && <p className="error">{formErrors.phone}</p>}
                    </div>
                    <div>
                        <input
                            type="email"
                            name="email"
                            placeholder="E-mail..."
                            value={formData.email}
                            onChange={handleChange}
                        />
                        {formErrors.email && <p className="error">{formErrors.email}</p>}
                    </div>
                    <div>
                        <input
                            type="password"
                            name="password"
                            placeholder="Heslo..."
                            value={formData.password}
                            onChange={handleChange}
                        />
                        {formErrors.password && <p className="error">{formErrors.password}</p>}
                    </div>
                    <div>
                        <input
                            type="password"
                            name="confirmPassword"
                            placeholder="Potvrzení hesla..."
                            value={formData.confirmPassword}
                            onChange={handleChange}
                        />
                        {formErrors.confirmPassword && <p className="error">{formErrors.confirmPassword}</p>}
                    </div>
                    <button type="submit">Registrovat se</button>
                    {isSubmitted && <p className="success">Registrace proběhla úspěšně!</p>}
                </form>
            </div>
        </>
    );
};

export default Registration;