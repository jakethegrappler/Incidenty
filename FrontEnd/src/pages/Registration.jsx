import { useState } from "react";
import { useNavigate } from "react-router-dom";
import "../css/Registration.css";

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
        role: 'ROLE_STUDENT',
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
        const errors = {};
        if (!formData.firstName) errors.firstName = "Křestní jméno je povinné.";
        if (!formData.lastName) errors.lastName = "Příjmení je povinné.";
        if (!formData.username) errors.username = "Uživatelské jméno je povinné.";
        if (!formData.phoneNumber) {
            errors.phoneNumber = "Telefonní číslo je povinné.";
        } else if (!/^\d+$/.test(formData.phoneNumber)) {
            errors.phoneNumber = "Telefonní číslo může obsahovat pouze číslice.";
        }
        if (!formData.email) {
            errors.email = "E-mail je povinný.";
        } else if (!/\S+@\S+\.\S+/.test(formData.email)) {
            errors.email = "E-mail není platný.";
        }
        if (!formData.password) {
            errors.password = "Heslo je povinné.";
        } else if (formData.password.length < 4) {
            errors.password = "Heslo musí mít alespoň 4 znaky.";
        }
        if (formData.confirmPassword !== formData.password) {
            errors.confirmPassword = "Hesla se neshodují.";
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
        <div className="page-wrapper fade-in">
            <div className="registration-card">
                <h2>Registrace</h2>
                <form onSubmit={handleSubmit} className="registration-form">

                    <input type="text" name="firstName" placeholder="Křestní jméno" onChange={handleChange} />
                    {formErrors.firstName && <div className="error-message">{formErrors.firstName}</div>}

                    <input type="text" name="lastName" placeholder="Příjmení" onChange={handleChange} />
                    {formErrors.lastName && <div className="error-message">{formErrors.lastName}</div>}

                    <input type="text" name="username" placeholder="Uživatelské jméno" onChange={handleChange} />
                    {formErrors.username && <div className="error-message">{formErrors.username}</div>}

                    <input type="text" name="phoneNumber" placeholder="Telefon" onChange={handleChange} />
                    {formErrors.phoneNumber && <div className="error-message">{formErrors.phoneNumber}</div>}

                    <input type="email" name="email" placeholder="E-mail" onChange={handleChange} />
                    {formErrors.email && <div className="error-message">{formErrors.email}</div>}

                    <input type="password" name="password" placeholder="Heslo" onChange={handleChange} />
                    {formErrors.password && <div className="error-message">{formErrors.password}</div>}

                    <input type="password" name="confirmPassword" placeholder="Potvrzení hesla" onChange={handleChange} />
                    {formErrors.confirmPassword && <div className="error-message">{formErrors.confirmPassword}</div>}

                    <select name="role" value={formData.role} onChange={handleChange}>
                        <option value="ROLE_STUDENT">Student</option>
                        <option value="ROLE_EMPLOYEE">Zaměstnanec</option>
                        <option value="ROLE_ADMIN">Admin</option>
                    </select>

                    <button type="submit">Registrovat se</button>

                    {isSubmitted && <div className="success-message">Registrace proběhla úspěšně!</div>}
                </form>
            </div>
        </div>
    );
};

export default Registration;
