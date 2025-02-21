import React, { createContext, useState } from 'react';



export const CeremonyContext = createContext();
export const CeremonyProvider = ({ children }) => {
    const [ceremonyName, setCeremonyName] = useState('');

    return (
        <CeremonyContext.Provider value={{ ceremonyName, setCeremonyName }}>
            {children}
        </CeremonyContext.Provider>
    );
};
