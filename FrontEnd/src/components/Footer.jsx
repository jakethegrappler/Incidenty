// Footer.jsx
import "react";
import "../css/Footer.css";

function Footer() {
    return (
        <footer className="footer">
            <p>&copy; {new Date().getFullYear()} Incidenty App. Všechna práva vyhrazena.</p>
        </footer>
    );
}

export default Footer;