import React from 'react';
import { useGoogleLogin } from '@react-oauth/google';

function LoginHooks() {
    const login = useGoogleLogin({
        onSuccess: (tokenResponse) => {
            console.log('Login Success:', tokenResponse);
        },
        onError: (error) => {
            console.log('Login failed:', error);
            alert(
                `Failed to login. 😢 Please ping this to repo owner github.com/smoring2`
            );
        },
    });

    return (
        <button onClick={() => login()} className="button">
            <img src="icons/google.svg" alt="google login" className="icon"></img>

            <span className="buttonText">Sign in with Google</span>
        </button>
    );
}

export default LoginHooks;
