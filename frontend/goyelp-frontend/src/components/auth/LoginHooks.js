import React from 'react';
import { useGoogleLogin } from '@react-oauth/google';

// refresh token
import { refreshTokenSetup } from './utils/refreshToken';

function LoginHooks() {
    const onSuccess = (res) => {
        console.log('Login Success: tokenResponse:', res);
        alert(
            `Logged in successfully 😍. \n See console for full token response.`
        );
        refreshTokenSetup(res);
    };

    const onError = (res) => {
        console.log('Login failed: res:', res);
        alert(
            `Failed to login. 😢 Please ping this to repo owner github.com/smoring2`
        );
    };

    const signIn = useGoogleLogin({
        onSuccess,
        onError,
        flow: 'implicit',
        // flow: 'auth-code',
        // prompt: 'consent',
    });

    return (
        <button onClick={signIn} className="button">
            <img src="icons/google.svg" alt="google login" className="icon"></img>

            <span className="buttonText">Sign in with Google</span>
        </button>
    );
}

export default LoginHooks;