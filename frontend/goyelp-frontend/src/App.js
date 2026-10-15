import React, {Component} from 'react';
import './App.css';
import Home from './components/Home';
import {BrowserRouter as Router, Route, Routes} from 'react-router';
import {GoogleOAuthProvider} from '@react-oauth/google';
import BusinessSearch from "./components/BusinessSearch";
import ReviewList from "./components/ReviewList";
//
// function App() {
//     return (
//         <div className="App">
//             <h2>The Components way</h2>
//             <Login />
//             <br />
//             <Logout />
//             <h2>The Hooks way</h2>
//             <LoginHooks />
//             <LogoutHooks />
//             <br />
//             If it does helped you feel free to star{' '}
//             <a href="https://github.com/Sivanesh-S/react-google-authentication">
//                 Github Repo
//             </a>{' '}
//             😉
//         </div>
//     );
// }

const clientId =
    '527452840708-cjj4h0je154vs0mtqgboqemtqimaern8.apps.googleusercontent.com';

class App extends Component {
    render() {
        return (<div className="App">
                <GoogleOAuthProvider clientId={clientId}>
                    <Router>
                        <Routes>
                            <Route path='/' element={<Home/>}/>
                            <Route path='/reviews' element={<ReviewList/>}/>
                            {/*<Route path='/review/:id' component={ReviewLookup}/>*/}
                            <Route path='/businesses' element={<BusinessSearch/>}/>

                        </Routes>
                    </Router>
                </GoogleOAuthProvider>
            </div>
        )

    }
}

export default App;