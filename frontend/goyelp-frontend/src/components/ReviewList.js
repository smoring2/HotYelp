import React, {Component} from 'react';
import {Button, ButtonGroup, Container, Table} from 'reactstrap';
import AppNavbar from './AppNavbar';
import {Link} from 'react-router-dom';

class ReviewList extends Component {

    constructor(props) {
        super(props);
        this.state = {reviews: [], isLoading: true};
    }

    componentDidMount() {
        this.setState({isLoading: true});

        const query = new URLSearchParams(this.props.location.search);
        const bizId = query.get("biz")
        const authToken = localStorage.getItem('authToken');
        fetch('api/reviews?biz=' + bizId, {
            headers: {
                'Authorization': 'Bearer ' + authToken
            }
        })
            .then(response => {
                if (response.status === 401) {
                    alert('Please sign in to view reviews.');
                    this.setState({isLoading: false});
                    return [];
                }
                if (response.status === 429) {
                    alert('Rate limit exceeded. Please try again later.');
                    this.setState({isLoading: false});
                    return [];
                }
                return response.json();
            })
            .then(data => this.setState({reviews: data, isLoading: false}));
    }

    render() {
        const {reviews, isLoading} = this.state;

        if (isLoading) {
            return <p>Loading...</p>;
        }

        const reviewList = reviews.map(review => {
            return <tr key={review.id}>
                <td style={{whiteSpace: 'nowrap'}}>{review.userName}</td>
                <td>{review.rating}</td>
                <td>{review.text}</td>
                <td>{review.time_created}</td>

                {/*<td>*/}
                {/*<ButtonGroup>*/}
                {/*<Button size="sm" color="primary" tag={Link} to={"/comments/" + comment.id}>Edit</Button>*/}
                {/*<Button size="sm" color="danger" onClick={() => this.remove(comment.id)}>Delete</Button>*/}
                {/*</ButtonGroup>*/}
                {/*</td>*/}
            </tr>
        });

        return (
            <div>
                <AppNavbar/>
                <Container fluid>
                    <h3>Newest Reviews</h3>
                    <Table className="mt-4">
                        <thead>
                        <tr>
                            <th width="10%">User Name</th>
                            <th width="10%">Rating</th>
                            <th width="20%">Text</th>
                            <th width="10%">Create Time</th>

                            {/*<th>Events</th>*/}
                            {/*<th width="10%">Actions</th>*/}
                        </tr>
                        </thead>
                        <tbody>
                        {reviewList}
                        </tbody>
                    </Table>
                </Container>
            </div>
        );
    }
}

export default ReviewList;
