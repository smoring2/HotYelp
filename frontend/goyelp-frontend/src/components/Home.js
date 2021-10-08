import React, {Component} from 'react';
import '../App.css';
import AppNavbar from './AppNavbar';
import {Button, CardText, Container} from 'reactstrap';
import Login from "./auth/Login";
import Logout from "./auth/Logout";


class Home extends Component {

    constructor(props) {
        super(props);
        this.state = {value: ''};

        this.handleChange = this.handleChange.bind(this);
        this.handleClick = this.handleClick.bind(this);

    }

    handleChange(event) {
        this.setState({value: event.target.value});
    }

    handleClick() {
        const oriAddr = this.state.value
        const encodedAddr = encodeURIComponent(oriAddr)
        console.log("oriAddr: " + oriAddr)
        console.log("encodedAddr: " + encodedAddr)
        this.props.history.push("/businesses?addr=" + encodedAddr);
    }


    render() {
        return (
            <div>
                <AppNavbar/>
                <Container fluid>
                    <CardText>Hottest Yelp Biz Near You!</CardText>
                    <input type="text" placeholder="any place!" value={this.state.value}
                           onChange={this.handleChange}/>
                    <Button onClick={this.handleClick}>Xiu~</Button>
                    {/*<Link to={{pathname: "/comments"}} className="btn btn-primary">Xiu~</Link>*/}
                </Container>
            </div>
        );
    }
}

export default Home;