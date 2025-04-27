import PropTypes from "prop-types";
import "../css/IncidentsMap.css";



function IncidentsMap({ onSectorClick }) {
    return (
        <div className="map-wrapper">
            <svg
                viewBox="0 0 1000 700"
                className="campus-svg"
                xmlns="http://www.w3.org/2000/svg"
            >
                {/* Sektor A3 */}
                <path
                    d="M 417 339 L 417 375 L 477 375 L 477 339 Z"
                    className="sector"
                    onClick={() => onSectorClick('A1')}
                />

                {/* Sektor A4 */}
                <path
                    d="M 417 463 L 417 499 L 477 499 L 477 463 Z"
                    className="sector"
                    onClick={() => onSectorClick('A2')}
                />

                {/* Sektor B2 */}
                <path
                    d="M 617 317 L 499 317 L 499 283 L 617 284 Z"
                    className="sector"
                    onClick={() => onSectorClick('A3')}
                />

                {/* Sektor B3 */}
                <path
                    d="M 612 440 L 499 440 L 499 407 L 612 407 Z"
                    className="sector"
                    onClick={() => onSectorClick('A4')}
                />

                {/* Sektor C2 */}
                <path
                    d="M 477 196 L 477 283 L 520 283 L 520 196 Z"
                    className="sector"
                    onClick={() => onSectorClick('C2')}
                />

                {/* Sektor C3 */}
                <path
                    d="M 477 317 L 477 407 L 520 407 L 520 317 Z"
                    className="sector"
                    onClick={() => onSectorClick('B2')}
                />

                {/* Sektor C4 */}
                <path
                    d="M 520 499 L 520 440 L 476 440 L 477 499 Z"
                    className="sector"
                    onClick={() => onSectorClick('B3')}
                />

                {/* Sektor D2 */}
                <path
                    d="M 454 283 L 454 317 L 499 317 L 499 284 Z"
                    className="sector"
                    onClick={() => onSectorClick('C1')}
                />

                {/* Sektor D3 */}
                <path
                    d="M 454 407 L 454 440 L 499 440 L 499 407 Z"
                    className="sector"
                    onClick={() => onSectorClick('C2')}
                />


                {/* Sektor E1 */}
                <path
                    d="M 620 402 L 719 300 L 738 317 L 638 420 Z"
                    className="sector"
                    onClick={() => onSectorClick('C3')}
                />
                {/* Sektor F1 */}
                <path
                    d="M 633 565 L 593 525 L 647 469 L 616 439 L 628 429 L 669 468 L 658 479 L 688 509 Z"
                    className="sector"
                    onClick={() => onSectorClick('C3')}
                />
                {/* Sektor G1 */}
                <path
                    d="M 695 503 L 676 485 L 783 378 L 801 395 Z"
                    className="sector"
                    onClick={() => onSectorClick('C3')}
                />
                {/* Sektor H1 */}
                <path
                    d="M 748 327 L 647 429 L 681 463 L 782 361 Z"
                    className="sector"
                    onClick={() => onSectorClick('C3')}
                />
                {/* Sektor B1A */}
                <path
                    d="M 712 525 L 802 435 L 845 482 L 757 571 Z"
                    className="sector"
                    onClick={() => onSectorClick('C3')}
                />
                {/* Sektor B3A */}
                <path
                    d="M 893 344 L 802 435 L 845 482 L 937 389 Z"
                    className="sector"
                    onClick={() => onSectorClick('C3')}
                />
            </svg>
        </div>
    );
}

// PropTypes kontrola
IncidentsMap.propTypes = {
    onSectorClick: PropTypes.func.isRequired,
};

export default IncidentsMap;
