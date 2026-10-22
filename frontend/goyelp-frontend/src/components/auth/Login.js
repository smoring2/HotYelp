import React from 'react';

import { GoogleLogin } from '@react-oauth/google';

function Login() {
    const onSuccess = (credentialResponse) => {
        console.log('Login Success:', credentialResponse);
    };

    const onFailure = () => {
        console.log('Login failed');
        alert(
            `Failed to login. 😢 Please ping this to repo owner github.com/smoring2`
        );
    };

    return (
        <div>
            <GoogleLogin
                onSuccess={onSuccess}
                onError={onFailure}
            />
        </div>
    );
}

export default Login;
