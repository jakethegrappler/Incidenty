import PropTypes from "prop-types";
import "../css/IncidentsMap.css";
import HeatOverlay from "./HeatOverlay";




const typeColors = {
    "KRADEZ": "#e63946",      // červená
    "NAPADENI": "#f77f00",    // oranžová
    "POZAR": "#fcbf49",       // zlatá
    "URAZ": "#00b4d8",        // tyrkysová
    "VANDALISMUS": "#5e60ce", // fialová
    "HAVARIE": "#000509", // šedá
    "OSTATNI": "#136235"
};


function isToday(dateString) {
    if (!dateString) return false;
    const today = new Date();
    const date = new Date(dateString);
    return (
        date.getFullYear() === today.getFullYear() &&
        date.getMonth() === today.getMonth() &&
        date.getDate() === today.getDate()
    );
}



function getColorForType(type) {
    return typeColors[type] || "#6b7280"; // šedá jako výchozí
}

function IncidentsMap({ onMapClick, onSectorClick, incidents = [], useHeatmap = false, selectedPoint }) {
    const handleClick = (e) => {
        if (!onMapClick) return;

        const svg = e.currentTarget;
        const point = svg.createSVGPoint();
        point.x = e.clientX;
        point.y = e.clientY;

        // Transformace do koordinát ve viewBoxu
        const ctm = svg.getScreenCTM().inverse();
        const svgPoint = point.matrixTransform(ctm);

        const x = Math.round(svgPoint.x);
        const y = Math.round(svgPoint.y);

        let sector = "NEZNÁMÝ";
        if (e.target.tagName === "path" && e.target.dataset.sector) {
            sector = e.target.dataset.sector;
        }

        onMapClick(x, y, sector);
    };


    return (
        <div className="map-wrapper" style={{position: "relative"}}>
            {useHeatmap && <HeatOverlay points={incidents}/>}
            <svg
                viewBox="0 0 1000 700"
                className="campus-svg"
                xmlns="http://www.w3.org/2000/svg"
                onClick={handleClick}
            >
                {/* 🖼️ Podkladová mapa */}
                <image
                    href="/FEL-Dejvice_2.png"
                    x="0"
                    y="0"
                    width="1000"
                    height="700"
                />

                {/* 🔵 Polygony sektorů */}
                <path d="M 417 339 L 417 375 L 477 375 L 477 339 Z" data-sector="A3" className="sector"
                      onClick={() => onSectorClick?.("A3")}/>
                <path d="M 417 463 L 417 499 L 477 499 L 477 463 Z" data-sector="A4" className="sector"
                      onClick={() => onSectorClick?.("A4")}/>
                <path d="M 617 317 L 499 317 L 499 283 L 617 284 Z" data-sector="B2" className="sector"
                      onClick={() => onSectorClick?.("B2")}/>
                <path d="M 612 440 L 499 440 L 499 407 L 612 407 Z" data-sector="B3" className="sector"
                      onClick={() => onSectorClick?.("B3")}/>
                <path d="M 477 196 L 477 283 L 520 283 L 520 196 Z" data-sector="C2" className="sector"
                      onClick={() => onSectorClick?.("C2")}/>
                <path d="M 477 317 L 477 407 L 520 407 L 520 317 Z" data-sector="C3" className="sector"
                      onClick={() => onSectorClick?.("C3")}/>
                <path d="M 520 499 L 520 440 L 476 440 L 477 499 Z" data-sector="C4" className="sector"
                      onClick={() => onSectorClick?.("C4")}/>
                <path d="M 454 283 L 454 317 L 499 317 L 499 284 Z" data-sector="D2" className="sector"
                      onClick={() => onSectorClick?.("D2")}/>
                <path d="M 454 407 L 454 440 L 499 440 L 499 407 Z" data-sector="D3" className="sector"
                      onClick={() => onSectorClick?.("D3")}/>
                <path d="M 620 402 L 719 300 L 738 317 L 638 420 Z" data-sector="E1" className="sector"
                      onClick={() => onSectorClick?.("E1")}/>
                <path d="M 633 565 L 593 525 L 647 469 L 616 439 L 628 429 L 669 468 L 658 479 L 688 509 Z"
                      data-sector="F1" className="sector" onClick={() => onSectorClick?.("F1")}/>
                <path d="M 695 503 L 676 485 L 783 378 L 801 395 Z" data-sector="G1" className="sector"
                      onClick={() => onSectorClick?.("G1")}/>
                <path d="M 748 327 L 647 429 L 681 463 L 782 361 Z" data-sector="H1" className="sector"
                      onClick={() => onSectorClick?.("H1")}/>
                <path d="M 712 525 L 802 435 L 845 482 L 757 571 Z" data-sector="B1A" className="sector"
                      onClick={() => onSectorClick?.("B1A")}/>
                <path d="M 893 344 L 802 435 L 845 482 L 937 389 Z" data-sector="B3A" className="sector"
                      onClick={() => onSectorClick?.("B3A")}/>

                {/* 🔴 Incident tečky */}
                {!useHeatmap && incidents.map((incident, i) => (
                    <circle
                        key={i}
                        cx={incident.x}
                        cy={incident.y}
                        r="5"
                        fill={getColorForType(incident.type)}
                        className={`incident-dot ${isToday(incident.date) ? "pulsing" : ""}`}
                        opacity="0.9"
                    />
                ))}

                {selectedPoint && !useHeatmap && (
                    <circle
                        cx={selectedPoint.x}
                        cy={selectedPoint.y}
                        r="6"
                        fill="#000"
                        stroke="#fff"
                        strokeWidth="2"
                        opacity="0.9"
                    />
                )}

            </svg>
        </div>
    );
}

IncidentsMap.propTypes = {
    onMapClick: PropTypes.func,
    onSectorClick: PropTypes.func,
    incidents: PropTypes.array,
    useHeatmap: PropTypes.bool
};

export default IncidentsMap;
