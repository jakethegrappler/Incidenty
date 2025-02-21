import React, { useContext } from 'react';
import { CeremonyContext } from '../context/CeremonyContext.jsx';
import { DateContext } from '../context/DateContext.jsx';
import { TimeContext } from '../context/TimeContext.jsx';
import { useNavigate } from 'react-router-dom';
import '../CSS/Reservation.css';
import {StudioContext} from "../context/StudioContext.jsx";

const Reservation = () => {
    const { setCeremonyName } = useContext(CeremonyContext);
    const { selectedDate } = useContext(DateContext);
    const { time } = useContext(TimeContext);
    const { selectedStudio } = useContext(StudioContext);
    const navigate = useNavigate();

    const startDate = new Date(selectedDate);
    startDate.setHours(time, 0, 0);

    const [events, setEvents] = useState([]);

    const handleClick = (name) => {
        setCeremonyName(name);
        navigate('/rezervace-final');
    };


    const criteria ={
        Date: startDate,
        studio: selectedStudio
    }

    const getEvents = async () =>
    {
        try {
            const response = await fetch('http://localhost:8080/event/criteria', {
                method: 'POST',
                headers: {
                    'Connection': 'keep-alive',
                },
                credentials: 'include',
                body:criteria
            });

            if (response.ok) {
                const data = await response.json();
                console.log('Events successful received:', data);
                setEvents(data);
                console.log(events);


            } else {
                console.error('Error getting events:', response.statusText);

            }
        } catch (error) {
            console.error('Error:', error);
        }
    };

    useEffect(() => {
        getEvents();
    }, []);


    return (
        <div className="reservation-container">
            <main>
                <div className="ceremonies">
                    <button className="ceremonyButton" onClick={() => handleClick(null)}>
                        Sauna bez eventu
                    </button>
                    {events.map((event, index) => (
                        <button
                            key={index}
                            className="ceremonyButton"
                            onClick={() => handleClick(event)}
                        >
                            {event.name}
                        </button>
                    ))}
                </div>
            </main>
        </div>
    );
};

export default Reservation;