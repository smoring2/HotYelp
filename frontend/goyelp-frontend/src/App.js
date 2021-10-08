import React, {Component} from 'react';
import './App.css';
import Home from './components/Home';
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom';
import Login from "./components/auth/Login";
import Logout from "./components/auth/Logout";
import LoginHooks from "./components/auth/LoginHooks";
import LogoutHooks from "./components/auth/LogoutHooks";
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

class App extends Component {
    render() {
        return (<div className="App">
                <Router>
                    <Switch>
                        <Route path='/' exact={true} component={Home}/>
                        <Route path='/reviews' component={ReviewList}/>
                        {/*<Route path='/review/:id' component={ReviewLookup}/>*/}
                        <Route path='/businesses' component={BusinessSearch}/>

                    </Switch>
                </Router>
            </div>
        )

    }
}

export default App;