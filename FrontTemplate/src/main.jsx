import React from 'react'
import ReactDOM from 'react-dom/client'
import { BrowserRouter } from 'react-router-dom';
import { CeremonyProvider } from './context/CeremonyContext.jsx';
import { DateProvider } from './context/DateContext.jsx';
import { TimeProvider } from './context/TimeContext.jsx';
import { StudioProvider } from "./context/StudioContext.jsx";

import App from './pages/App.jsx'
import './CSS/index.css'

ReactDOM.createRoot(document.getElementById('root')).render(
    <React.StrictMode>

        <BrowserRouter>
            <StudioProvider>
                <CeremonyProvider>
                    <DateProvider>
                        <TimeProvider>
                            <App />
                        </TimeProvider>
                    </DateProvider>
                </CeremonyProvider>
            </StudioProvider>
        </BrowserRouter>
    </React.StrictMode>
);
