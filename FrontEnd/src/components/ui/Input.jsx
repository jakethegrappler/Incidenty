function Input({ type = "text", name, value, onChange, placeholder, accept }) {
    return (
        <input
            className="custom-input"
            type={type}
            name={name}
            value={value}
            onChange={onChange}
            placeholder={placeholder}
            accept={accept}
        />
    );
}
export default Input;
