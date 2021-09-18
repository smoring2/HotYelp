import React from 'react';
import { GoogleLogout } from 'react-google-login';

const clientId =
    '527452840708-cjj4h0je154vs0mtqgboqemtqimaern8.apps.googleusercontent.com';

function Logout() {
    const onSuccess = () => {
        console.log('Logout made successfully');
        alert('Logout made successfully ✌');
    };

    return (
        <div>
            <GoogleLogout
                clientId={clientId}
                buttonText="Logout"
                onLogoutSuccess={onSuccess}
            ></GoogleLogout>
        </div>
    );
}

export default Logout;