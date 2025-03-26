import 'react';
import { Route, Routes } from 'react-router-dom';
import Header from '../components/Header';
import Footer from '../components/Footer';
import Home from "./Home.jsx";
import ReportForm from "./ReportForm.jsx";

function App() {
    return (
        <>
            <Header />
            <Routes>
                <Route path="/home" element={<Home />} />
                <Route path="/report" element={<ReportForm />} />
                {/* Zde můžeš přidat další routy */}
            </Routes>
            <Footer />
        </>
    );
}

export default App;
