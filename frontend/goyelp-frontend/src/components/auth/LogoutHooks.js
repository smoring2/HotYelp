import React from 'react';
import { googleLogout } from '@react-oauth/google';

function LogoutHooks() {
    const onLogoutSuccess = () => {
        console.log('Logged out Success');
        alert('Logged out Successfully ✌');
    };

    const handleSignOut = () => {
        googleLogout();
        onLogoutSuccess();
    };

    return (
        <button onClick={handleSignOut} className="button">
            <img src="icons/google.svg" alt="google login" className="icon"></img>

            <span className="buttonText">Sign out</span>
        </button>
    );
}

export default LogoutHooks;
