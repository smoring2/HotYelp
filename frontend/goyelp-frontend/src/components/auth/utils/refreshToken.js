export const refreshTokenSetup = (res) => {
    // @react-oauth/google returns a credential (ID token) or an access token
    // response instead of the GAPI auth object used by react-google-login,
    // so the token is persisted and a refresh is scheduled from its lifetime.
    const token = res.credential || res.access_token;
    if (token) {
        localStorage.setItem('authToken', token);
    }

    // Timing to renew access token
    const refreshTiming = (res.expires_in || 3600 - 5 * 60) * 1000;

    const refreshToken = () => {
        // Google Identity Services cannot silently reload the token, so the
        // stored token is cleared and the user is prompted to sign in again.
        localStorage.removeItem('authToken');
        console.log('authToken expired, re-authentication required');
    };

    // Setup first refresh timer
    setTimeout(refreshToken, refreshTiming);
};
