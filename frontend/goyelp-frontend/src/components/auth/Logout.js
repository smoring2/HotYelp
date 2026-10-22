import React from 'react';
import { googleLogout } from '@react-oauth/google';

function Logout() {
    const onSuccess = () => {
        console.log('Logout made successfully');
        alert('Logout made successfully ✌');
    };

    const handleLogout = () => {
        googleLogout();
        onSuccess();
    };

    return (
        <div>
            <button onClick={handleLogout}>Logout</button>
        </div>
    );
}

export default Logout;
