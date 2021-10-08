import React from 'react';

import { GoogleLogin } from 'react-google-login';
// refresh token
import { refreshTokenSetup } from './utils/refreshToken';

const clientId =
    '527452840708-cjj4h0je154vs0mtqgboqemtqimaern8.apps.googleusercontent.com';

function Login() {
    const onSuccess = (res) => {
        console.log('Login Success: currentUser:', res.profileObj);
        // alert(
        //     `Logged in successfully welcome ${res.profileObj.name} 😍. \n See console for full profile object.`
        // );
        refreshTokenSetup(res);
    };

    const onFailure = (res) => {
        console.log('Login failed: res:', res);
        alert(
            `Failed to login. 😢 Please ping this to repo owner github.com/smoring2`
        );
    };

    return (
        <div>
            <GoogleLogin
                clientId={clientId}
                buttonText="Login"
                onSuccess={onSuccess}
                onFailure={onFailure}
                cookiePolicy={'single_host_origin'}
                style={{ marginTop: '100px' }}
                isSignedIn={true}
            />
        </div>
    );
}

export default Login;