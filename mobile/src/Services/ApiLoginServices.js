import axios from "axios";

export const handleLogin = async (email, password) => {
    const loginData = { email: String(email), password: String(password) };

    const response = await axios.post(
        'https://kumowind-api-3ris.onrender.com/auth/login',
        loginData,
        { headers: { 'Content-Type': 'application/json' } }
    )

    return response
};