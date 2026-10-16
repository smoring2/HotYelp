import React from 'react';
import {useLocation, useNavigate, useParams} from 'react-router';

// react-router v6+ removed the withRouter HOC, so class components receive
// routing data through this equivalent wrapper.
export function withRouter(WrappedComponent) {
    return function ComponentWithRouter(props) {
        const location = useLocation();
        const navigate = useNavigate();
        const params = useParams();
        return <WrappedComponent {...props} location={location} navigate={navigate} params={params}/>;
    };
}

export default withRouter;
