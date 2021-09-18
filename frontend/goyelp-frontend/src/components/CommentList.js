import React, {Component} from 'react';
import {Button, ButtonGroup, Container, Table} from 'reactstrap';
import AppNavbar from './AppNavbar';
import {Link} from 'react-router-dom';

class CommentList extends Component {

    constructor(props) {
        super(props);
        this.state = {comments: [], isLoading: true};
        this.remove = this.remove.bind(this);
    }

    componentDidMount() {
        this.setState({isLoading: true});

        fetch('api/comments')
            .then(response => response.json())
            .then(data => this.setState({comments: data, isLoading: false}));
    }

    async remove(id) {
        await fetch(`/api/comment/${id}`, {
            method: 'DELETE',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(() => {
            let updatedComments = [...this.state.comments].filter(i => i.id !== id);
            this.setState({comments: updatedComments});
        });
    }

    render() {
        const {comments, isLoading} = this.state;

        if (isLoading) {
            return <p>Loading...</p>;
        }

        const commentList = comments.map(comment => {
            return <tr key={comment.id}>
                <td style={{whiteSpace: 'nowrap'}}>{comment.user}</td>
                <td>{comment.comment}</td>
                <td>{<div>{new Intl.DateTimeFormat('en-US', {
                    year: 'numeric',
                    month: 'long',
                    day: '2-digit'
                }).format(new Date(comment.commentDate))}</div>
                }
                </td>
                <td>
                    <ButtonGroup>
                        <Button size="sm" color="primary" tag={Link} to={"/comments/" + comment.id}>Edit</Button>
                        <Button size="sm" color="danger" onClick={() => this.remove(comment.id)}>Delete</Button>
                    </ButtonGroup>
                </td>
            </tr>
        });

        return (
            <div>
                <AppNavbar/>
                <Container fluid>
                    <div className="float-right">
                        <Button color="success" tag={Link} to="/comments/new">Add Comment</Button>
                    </div>
                    <h3>My JUG Tour</h3>
                    <Table className="mt-4">
                        <thead>
                        <tr>
                            <th width="20%">Name</th>
                            <th width="20%">Location</th>
                            <th>Events</th>
                            <th width="10%">Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                        {commentList}
                        </tbody>
                    </Table>
                </Container>
            </div>
        );
    }
}

export default CommentList;