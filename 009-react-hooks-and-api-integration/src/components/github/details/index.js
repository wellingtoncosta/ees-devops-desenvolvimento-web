import {useParams} from 'react-router-dom'

import Card from 'react-bootstrap/Card'
import ListGroup from 'react-bootstrap/ListGroup'
import Spinner from 'react-bootstrap/Spinner'
import Stack from 'react-bootstrap/Stack'

import AppBar from '../AppBar'
import useFetchRepos from "./useFetchRepos"
import useFetchUser from "./useFetchUser"
import Avatar from "../commons/Avatar"

function Loading() {
    return (
        <div className='text-center m-5'>
            <Spinner/>
        </div>
    )
}

function Error({message}) {
    return (
        <div className='text-center text-danger mt-5 fs-3'>
            {message}
        </div>
    )
}

function Info({username, name, repos, followers, following}) {
    return (
        <div className='ms-5 p-2'>
            <div className='fs-1 fw-semibold'>{name}</div>
            <div className='fs-2 fw-light'>
                @{username}
            </div>
            <div className='mt-1'/>
            <div className='fs-5 fw-light'>
                Public repositories: {repos}
            </div>
            <div className='fs-5 fw-light'>
                Followers: {followers}
            </div>
            <div className='fs-5 fw-light'>
                Following: {following}
            </div>
        </div>
    )
}

function RepositoryItem({name, stars, forks, openIssues}) {
    return (
        <ListGroup.Item>
            <Stack direction='horizontal'>
                <div className='p-2'>
                    <div className='fw-semibold'>{name}</div>
                    <div className='fw-light'>
                        Stars: {stars} {' '} Forks: {forks} {' '} Open issues: {openIssues}
                    </div>
                </div>
            </Stack>
        </ListGroup.Item>
    )
}

function Repositories({username}) {
    const [loading, repositories, error] = useFetchRepos(username)

    const RepositoriesList = () => (
        <ListGroup variant="flush">
            {repositories.map(repository =>
                <RepositoryItem
                    name={repository.name}
                    forks={repository.forks}
                    stars={repository.stars}
                    openIssues={repository.openIssues}
                />)
            }
        </ListGroup>
    )

    return (
        loading ? <Loading/> : (error ? <Error message={error.message}/> : <RepositoriesList/>)
    )
}

function Content({user}) {
    return (
        <div>
            <Card className='m-2'>
                <Stack direction='horizontal'>
                    <Avatar url={user.avatarUrl} size={20}/>
                    <Info
                        username={user.username}
                        name={user.name}
                        repos={user.repos}
                        followers={user.followers}
                        following={user.following}
                    />
                </Stack>
            </Card>

            <Card className='m-2'>
                <Card.Header className='fs-3'>Repositories</Card.Header>
                <Repositories username={user.username}/>
            </Card>
        </div>
    )
}

export default function UserDetails() {
    const {username} = useParams()
    const [loading, user, error] = useFetchUser(username)

    return (
        <div>
            <AppBar/>
            {loading ? <Loading/> : (error ? <Error message={error.message}/> : <Content user={{username, ...user}}/>)}
        </div>
    )
}
