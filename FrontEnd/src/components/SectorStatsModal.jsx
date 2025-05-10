import { Pie } from "react-chartjs-2";
import { Chart as ChartJS, ArcElement, Tooltip, Legend } from "chart.js";
import PropTypes from "prop-types";
import "../css/SectorStatsModal.css";

ChartJS.register(ArcElement, Tooltip, Legend);

function SectorStatsModal({ sector, stats, onClose }) {
    const total = Object.values(stats).reduce((sum, count) => sum + count, 0);
    const typeColors = {
        "Krádež": "#e63946",
        "Napadení": "#f77f00",
        "Požár": "#fcbf49",
        "Úraz": "#00b4d8",
        "Vandalismus": "#5e60ce",
        "Havárie": "#6c757d"
    };



    // Najdi nejčastější typ
    const topTypeEntry = Object.entries(stats).reduce((a, b) => (a[1] > b[1] ? a : b));
    const topType = topTypeEntry[0];
    const topCount = topTypeEntry[1];

    const chartData = {
        labels: Object.keys(stats),
        datasets: [
            {
                data: Object.values(stats),
                backgroundColor: Object.keys(stats).map(type => typeColors[type] || "#ccc"),
            },
        ],
    };


    return (
        <div className="modal-backdrop">
            <div className="modal-content">
                <h2>📍 Sektor: {sector}</h2>

                <div className="modal-stats-summary">
                    <p><strong>Celkem incidentů:</strong> {total}</p>
                    <p><strong>Nejčastější typ:</strong> {topType} ({topCount}×)</p>
                </div>

                <div className="modal-chart">
                    <Pie data={chartData} />
                </div>

                <button className="modal-close" onClick={onClose}>Zavřít</button>
            </div>
        </div>
    );
}

SectorStatsModal.propTypes = {
    sector: PropTypes.string.isRequired,
    stats: PropTypes.object.isRequired,
    onClose: PropTypes.func.isRequired,
};

export default SectorStatsModal;
