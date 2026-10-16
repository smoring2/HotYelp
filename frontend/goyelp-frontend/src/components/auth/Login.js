import React from 'react';

import { GoogleLogin } from '@react-oauth/google';
// refresh token
import { refreshTokenSetup } from './utils/refreshToken';

function Login() {
    const onSuccess = (res) => {
        console.log('Login Success: credentialResponse:', res);
        // alert(
        //     `Logged in successfully welcome ${res.profileObj.name} 😍. \n See console for full profile object.`
        // );
        refreshTokenSetup(res);
    };

    const onError = (res) => {
        console.log('Login failed: res:', res);
        alert(
            `Failed to login. 😢 Please ping this to repo owner github.com/smoring2`
        );
    };

    return (
        <div>
            <GoogleLogin
                onSuccess={onSuccess}
                onError={onError}
                text="signin"
                auto_select={true}
            />
        </div>
    );
}

export default Login;