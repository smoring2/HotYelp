import React from 'react';
import { googleLogout } from '@react-oauth/google';

function Logout() {
    const onSuccess = () => {
        googleLogout();
        console.log('Logout made successfully');
        alert('Logout made successfully ✌');
    };

    return (
        <div>
            <button onClick={onSuccess} className="button">
                Logout
            </button>
        </div>
    );
}

export default Logout;