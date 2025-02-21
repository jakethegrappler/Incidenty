import React, { useContext } from 'react';
import Calendar from 'react-calendar';
import 'react-calendar/dist/Calendar.css';
import { DateContext } from '../context/DateContext.jsx';

import '../CSS/Kalendar.css';

const Kalendar = () => {
    const {selectedDate, setSelectedDate} = useContext(DateContext);

    return (
        <div className="calendarContainer">
            <Calendar
                onChange={setSelectedDate}

                value={selectedDate}
            />

        </div>
    );
};
export default Kalendar;
