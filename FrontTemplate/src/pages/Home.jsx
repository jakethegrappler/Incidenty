import React, {useEffect, useState} from 'react';
import { useNavigate } from 'react-router-dom';
import Kalendar from '../components/Kalendar.jsx';
import '../CSS/App.css';

const Home = () => {
    const navigate = useNavigate();

    let selectedTimeSlot;

    const [studios, setStudios] = useState([]);
    const [selectedStudioId, setSelectedStudioId] = useState(null);

    const handleTimeSlotClick = (timeSlot) => {
        setTime(timeSlot);
    };


    const handleClick = () => {
        const selected = studios.find(studio => studio.id === selectedStudioId);
        setSelectedStudio(selected);
        navigate('/rezervace' );
    };

    const handleStudioChange = (e) => {
        const selectedId = parseInt(e.target.value);
        setSelectedStudioId(selectedId);
    };

    const getStudios = async () =>
    {
        try {
            const response = await fetch('http://localhost:8080/studio', {
                method: 'GET',
                headers: {
                    'Connection': 'keep-alive',
                },
                credentials: 'include',
            });
            if (response.ok) {
                const data = await response.json();
                console.log('Reservation successful received:', data);

                // return data.toString();

                setStudios(data);
                setSelectedStudioId(data[0].id)
                console.log(studios);
            } else {
                console.error('Error getting customer reservation:', response.statusText);

            }
        } catch (error) {
            console.error('Error:', error);
        }
    };



    useEffect(() => {
        getStudios();
    }, []);

    //TODO potrebuju prenest studio do /rezervace a tam
    // ho mit jako Context abych vyhledaval podle nej eventy

    return (
        <main className="mainContent">
            <h2>Výběr saunového centra</h2>

            <select className="centerSelect" value={selectedStudioId || ""} onChange={handleStudioChange}>
                {studios.map((studio, index) => (
                    <option key={studio.id || index} value={studio.id}>
                        {`${studio.name}, ${studio.address}`}
                    </option>
                ))}
            </select>
            <Kalendar/>
            <div className="time-slots">
                <button
                    className={`time-slot ${selectedTimeSlot === '09:00-12:00' ? 'selected' : ''}`}
                    onClick={() => handleTimeSlotClick(9)}
                >
                    09:00-12:00
                </button>
                <button
                    className={`time-slot ${selectedTimeSlot === '12:00-15:00' ? 'selected' : ''}`}
                    onClick={() => handleTimeSlotClick(12)}
                >
                    12:00-15:00
                </button>
                <button
                    className={`time-slot ${selectedTimeSlot === '15:00-18:00' ? 'selected' : ''}`}
                    onClick={() => handleTimeSlotClick(15)}
                >
                    15:00-18:00
                </button>
                <button
                    className={`time-slot ${selectedTimeSlot === '18:00-21:00' ? 'selected' : ''}`}
                    onClick={() => handleTimeSlotClick(18)}
                >
                    18:00-21:00
                </button>
            </div>
            <button className="availability" onClick={handleClick}>
                Volná místa: ??/45 →
            </button>

        </main>
    );
};


export default Home;
