import React, { useContext, useState } from 'react';
import { CeremonyContext } from '../context/CeremonyContext.jsx';
import { DateContext } from '../context/DateContext.jsx';
import { TimeContext } from '../context/TimeContext.jsx';
import '../CSS/ReservationFinal.css';
import {StudioContext} from "../context/StudioContext.jsx";

const ReservationFinal = () => {
    const { ceremonyName } = useContext(CeremonyContext);
    const { selectedDate } = useContext(DateContext);
    const { time } = useContext(TimeContext);
    const { selectedStudio } = useContext(StudioContext);
    const [numberOfGuests, setNumberOfGuests] = useState(1);

    console.log(selectedStudio);

    const handleReservation = async () => {

        const startDate = new Date(selectedDate);
        startDate.setHours(time, 0, 0);

        const endDate = new Date(startDate);
        endDate.setHours(time + 3, 0, 0);

        console.log(`Reserved for ${numberOfGuests} people on ${selectedDate.toLocaleDateString()} for ${ceremonyName.name}`);


        const reservation = {
            event: ceremonyName,
            end_time: endDate.toISOString(),
            num_of_people: numberOfGuests,
            start_time: startDate.toISOString(),
            state_type: "ATTENDED",
            studio: selectedStudio
        }


        try{
                const response = await fetch('http://localhost:8080/reservation', {
                    method: 'POST',
                    headers: {
                        'Connection': 'keep-alive',
                        'Content-Type': 'application/json',
                    },
                     credentials: 'include',
                    body: JSON.stringify(reservation)
                });

                if (response.ok) {
                    const data = await response.json();
                    console.log('Info successful send:', data);

                } else {
                    console.error('Error getting reservation send:', response.statusText);
                }
            }
            catch(error)
            {
                console.error('Error:', error);
            }
        };

    return (
        <div className="reservation-final-container">
            <main className="reservation-main">
                <button className="ceremony-button">{ceremonyName.name}</button>
                <div className="selected-date">
                    {selectedDate.toLocaleDateString()} v {time.toString()+ ":00 - " + (time + 3).toString() + ":00"}
                </div>

                <div className="guest-selection">
                    <label htmlFor="guest-number">Počet osob:</label>
                    <select
                        id="guest-number"
                        value={numberOfGuests}
                        onChange={(e) => setNumberOfGuests(Number(e.target.value))}
                        className="guest-select"
                    >
                        {[...Array(ceremonyName.freeCapacity)].map((_, index) => (
                            <option key={index + 1} value={index + 1}>
                                {index + 1}
                            </option>
                        ))}
                    </select>
                </div>

                <button className="reserve-button" disabled={sessionStorage.getItem('user') !== 'true'}
                        onClick={handleReservation}>
                    Rezervovat
                </button>
            </main>
        </div>
    );
};

export default ReservationFinal;
