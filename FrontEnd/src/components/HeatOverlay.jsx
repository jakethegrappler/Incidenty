import { useEffect, useRef } from "react";
import simpleheat from "simpleheat";
import PropTypes from "prop-types";

const HeatOverlay = ({ points }) => {
    const canvasRef = useRef(null);
    const containerRef = useRef(null);

    useEffect(() => {
        const canvas = canvasRef.current;
        const container = containerRef.current;
        if (!canvas || !container || !points.length) return;

        // Nastav reálnou velikost canvasu podle velikosti wrapperu
        const { width, height } = container.getBoundingClientRect();
        canvas.width = width;
        canvas.height = height;

        // Poměr mezi SVG (1000x700) a reálným rozměrem
        const xRatio = width / 1000;
        const yRatio = height / 700;

        const heat = simpleheat(canvas);
        const formattedPoints = points.map(p => [p.x * xRatio, p.y * yRatio, 0.7]);

        heat.data(formattedPoints);
        heat.radius(25, 15); // uprav radius podle zoomu
        heat.max(3);
        heat.draw(0.7);
    }, [points]);

    return (
        <div ref={containerRef} style={{ position: "absolute", top: 0, left: 0, right: 0, bottom: 0 }}>
            <canvas
                ref={canvasRef}
                style={{
                    position: "absolute",
                    top: 0,
                    left: 0,
                    width: "100%",
                    height: "100%",
                    pointerEvents: "none",
                    zIndex: 2
                }}
            />
        </div>
    );
};

HeatOverlay.propTypes = {
    points: PropTypes.arrayOf(
        PropTypes.shape({
            x: PropTypes.number.isRequired,
            y: PropTypes.number.isRequired,
        })
    ).isRequired,
};

export default HeatOverlay;
