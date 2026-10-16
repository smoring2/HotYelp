
import React from 'react';
import { googleLogout } from '@react-oauth/google';

function LogoutHooks() {
    const onLogoutSuccess = (res) => {
        console.log('Logged out Success');
        alert('Logged out Successfully ✌');
    };

    const signOut = () => {
        googleLogout();
        onLogoutSuccess();
    };

    return (
        <button onClick={signOut} className="button">
            <img src="icons/google.svg" alt="google login" className="icon"></img>

            <span className="buttonText">Sign out</span>
        </button>
    );
}

export default LogoutHooks;