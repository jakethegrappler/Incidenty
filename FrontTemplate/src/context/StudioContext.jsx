import React, { createContext, useState } from 'react';

export const StudioContext = createContext();

export const StudioProvider = ({ children }) => {
    const [selectedStudio, setSelectedStudio] = useState(null);

    return (
        <StudioContext.Provider value={{ selectedStudio, setSelectedStudio }}>
            {children}
        </StudioContext.Provider>
    );
};
