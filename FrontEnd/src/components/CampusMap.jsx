// import { useState } from "react";
// import "../css/CampusMap.css";
// import campusMapImage from "../assets/fel-campus-map.jpg"; // Uprav cestu podle sebe!
//
// const sectors = [
//     { name: "A3", style: { top: "50%", left: "28%", width: "6%", height: "6%" } },
//     { name: "A4", style: { top: "68%", left: "28%", width: "6%", height: "6%" } },
//     { name: "B1", style: { top: "90%", left: "75%", width: "12%", height: "5%" } },
//     { name: "B2", style: { top: "40%", left: "58%", width: "6%", height: "6%" } },
//     { name: "B3", style: { top: "30%", left: "78%", width: "6%", height: "15%" } },
//     { name: "C2", style: { top: "30%", left: "45%", width: "6%", height: "6%" } },
//     { name: "C3", style: { top: "40%", left: "45%", width: "6%", height: "6%" } },
//     { name: "D2", style: { top: "30%", left: "36%", width: "6%", height: "6%" } },
//     { name: "D3", style: { top: "40%", left: "36%", width: "6%", height: "6%" } },
//     { name: "E1", style: { top: "45%", left: "65%", width: "6%", height: "6%" } },
//     { name: "F1", style: { top: "70%", left: "50%", width: "8%", height: "8%" } },
//     { name: "G1", style: { top: "55%", left: "70%", width: "6%", height: "6%" } },
//     { name: "H1", style: { top: "50%", left: "62%", width: "6%", height: "6%" } },
// ];
//
// const CampusMap = () => {
//     const [selectedSector, setSelectedSector] = useState(null);
//
//     const handleSectorClick = (sector) => {
//         console.log(`Kliknuto na sektor: ${sector}`);
//         setSelectedSector(sector);
//     };
//
//     return (
//         <div className="map-wrapper fade-in">
//             <h2 className="map-title">Mapa kampusu FEL</h2>
//             <div className="map-container">
//                 <img src={campusMapImage} alt="Mapa kampusu" className="map-image" />
//
//                 {sectors.map((sector) => (
//                     <div
//                         key={sector.name}
//                         className="map-sector"
//                         style={sector.style}
//                         onClick={() => handleSectorClick(sector.name)}
//                     >
//                         {sector.name}
//                     </div>
//                 ))}
//             </div>
//
//             {selectedSector && (
//                 <div className="selected-sector">
//                     Vybraný sektor: <strong>{selectedSector}</strong>
//                 </div>
//             )}
//         </div>
//     );
// };
//
// export default CampusMap;
