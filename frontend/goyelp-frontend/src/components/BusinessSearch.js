import React, {Component} from 'react';
import {Button, ButtonGroup, Container, Table} from 'reactstrap';
import AppNavbar from './AppNavbar';
import {Link} from 'react-router-dom';

class BusinessSearch extends Component {


    constructor(props) {
        super(props);
        this.state = {businesses: [], isLoading: true};
    }

    componentDidMount() {
        this.setState({isLoading: true});
        const query = new URLSearchParams(this.props.location.search);
        const addr = query.get("addr")
        fetch('api/businesses?addr=' + addr)
            .then(response => response.json())
            .then(data => this.setState({businesses: data, isLoading: false}));
    }

    render() {
        const {businesses, isLoading} = this.state;

        if (isLoading) {
            return <p>Loading...</p>;
        }
        console.log(businesses)
        const businessList = businesses.map(business => {
            return <tr key={business.id}>
                <td style={{whiteSpace: 'nowrap'}}>{business.name}</td>
                {/*<td>{business.address}</td>*/}
                <td>{business.rating}</td>
                <td>{business.review_count}</td>
                <td>{business.reviewIncCount}</td>

                {/*<td>{<div>{new Intl.DateTimeFormat('en-US', {*/}
                {/*    year: 'numeric',*/}
                {/*    month: 'long',*/}
                {/*    day: '2-digit'*/}
                {/*}).format(new Date(business.commentDate))}</div>*/}
                {/*}*/}
                {/*</td>*/}
                <td>
                    <ButtonGroup>
                        <Button size="sm" color="primary" tag={Link} to={"/reviews?biz=" + business.id}>Show Reviews</Button>
                        {/*<Button size="sm" color="danger" onClick={() => this.remove(business.id)}>Delete</Button>*/}
                    </ButtonGroup>
                </td>
            </tr>
        });

        return (
            <div>
                <AppNavbar/>
                <Container fluid>
                    <h3>Hot Yelp Biz!</h3>
                    <Table className="mt-4">
                        <thead>
                        <tr>
                            <th width="20%">Name</th>
                            {/*<th width="20%">Address</th>*/}
                            <th width="20%">Rating</th>
                            <th width="20%">Review Count</th>
                            <th width="20%">Review Increase Count</th>

                            <th width="10%">Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                        {businessList}
                        </tbody>
                    </Table>
                </Container>
            </div>
        );
    }
}

export default BusinessSearch;