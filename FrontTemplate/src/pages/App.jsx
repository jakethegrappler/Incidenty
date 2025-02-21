import React from 'react';
import { Route, Routes } from 'react-router-dom';
import Header from '../components/Header.jsx';
import Footer from '../components/Footer.jsx';
import Login from '../pages/Login.jsx';
import Registration from '../pages/Registration.jsx';
import Reservation from '../pages/Reservation.jsx';
import Profile from '../pages/Profile.jsx';
import Home from '../pages/Home.jsx';
import ReservationFinal from '../pages/Reservation-final.jsx'
import 'react-calendar/dist/Calendar.css';
import '../CSS/App.css';

function App() {
    return (
        <>
            <Header />
            <Routes>
                <Route path="/" element={<Home />} />
                <Route path="/login" element={<Login />} />
                <Route path="/registrace" element={<Registration />} />
                <Route path="/profil" element={<Profile />} />
                <Route path="/rezervace" element={<Reservation />} />
                <Route path="/rezervace-final" element={<ReservationFinal />} />
            </Routes>
            <Footer />
        </>
    );
}

export default App;
