import 'react';
import { Route, Routes } from 'react-router-dom';
import Header from '../components/Header';
import Footer from '../components/Footer';
import Home from "./Home.jsx";
import ReportForm from "./ReportForm.jsx";
import Registration from "./Registration.jsx";
import Login from "./Login.jsx";
import Profile from "./Profile.jsx";
import { AuthProvider } from "../auth/AuthContext.jsx"
function App() {
    return (
        <AuthProvider>
            <Header />
            <Routes>
                <Route path="/home" element={<Home />} />
                <Route path="/report" element={<ReportForm />} />
                <Route path="/registrace" element={<Registration />} />
                <Route path="/login" element={<Login />} />
                <Route path="/profile" element={<Profile />} />

                {/* Zde můžeš přidat další routy */}
            </Routes>
            <Footer />
        </AuthProvider>
    );
}

export default App;
