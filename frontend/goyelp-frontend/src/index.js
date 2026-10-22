import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import 'bootstrap/dist/css/bootstrap.min.css';
import { GoogleOAuthProvider } from '@react-oauth/google';

const clientId =
    '527452840708-cjj4h0je154vs0mtqgboqemtqimaern8.apps.googleusercontent.com';

ReactDOM.render(
    <React.StrictMode>
        <GoogleOAuthProvider clientId={clientId}>
            <App/>
        </GoogleOAuthProvider>
    </React.StrictMode>,
    document.getElementById('root')
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
