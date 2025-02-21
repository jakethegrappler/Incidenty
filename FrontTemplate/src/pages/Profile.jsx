import React, { useEffect, useState} from 'react';
import '../CSS/Profile.css';

const Profile = () => {

    const [reservations, setReservations] = useState([]);

    const userInfo = {
        name: JSON.parse(sessionStorage.getItem('userInfo')).firstName + JSON.parse(sessionStorage.getItem('userInfo')).lastName,
        email: JSON.parse(sessionStorage.getItem('userInfo')).email,
        imageUrl: 'https://scontent.fprg5-1.fna.fbcdn.net/v/t39.30808-6/456961265_7904323629676476_5052785268261303713_n.jpg?_nc_cat=104&ccb=1-7&_nc_sid=127cfc&_nc_ohc=Uh2Iq90za-cQ7kNvgFma-qA&_nc_ht=scontent.fprg5-1.fna&oh=00_AYCxCX944s1l-GLO1JpX5R8gLeHAvjYj-zcaN0i3wf7GdQ&oe=66D635BC',
    };
    const getReservation = async () =>
    {
        try {
            const response = await fetch('http://localhost:8080/reservation/customer', {
                method: 'GET',
                headers: {
                    'Connection': 'keep-alive',
                },
                credentials: 'include',
            });


            if (response.ok) {
                console.log(response)
                const data = await response.json();
                console.log('Reservation successful received:', data);
                setReservations(data);
                console.log(reservations)
            } else {
                console.error('Error getting customer reservation:', response.statusText);

            }
        } catch (error) {
            console.error('Error:', error);
        }
    };

    useEffect(() => {
        getReservation();
    }, []);

    return (
        <div className="profile-container">
            <div className="profile-header">
                <img className="profile-image" src={userInfo.imageUrl} alt="Profile" />
                <div className="profile-info">
                    <input
                        type="text"
                        className="profile-input"
                        value={userInfo.name}
                        readOnly
                    />
                    <input
                        type="email"
                        className="profile-input"
                        value={userInfo.email}
                        readOnly
                    />
                </div>
            </div>

            <div className="profile-reservations">
                <h2>Moje rezervace</h2>
                <select className="reservations-select">
                    <option value="" disabled>Select</option>
                    {reservations.map((reservation, index) => (
                        <option key={reservation.id || index} value={reservation.id}>
                            {`Reservation at ${reservation.startTime} for ${reservation.numOfPeople} people`}
                        </option>
                    ))}
                </select>
            </div>
        </div>
    );
};

export default Profile;
