import {useNavigate} from 'react-router-dom'

import ListGroup from 'react-bootstrap/ListGroup'
import Stack from 'react-bootstrap/Stack'

import AppBar from '../commons/AppBar'
import Avatar from '../commons/Avatar'
import Error from '../commons/Error'
import Loading from '../commons/Loading'
import NoResults from '../commons/NoResults'

import useFetchUsers from './useFetchUsers'

function UserItem({avatarUrl, username, name, onClick}) {
    return (
        <ListGroup.Item action onClick={() => onClick(username)}>
            <Stack direction='horizontal'>
                <Avatar url={avatarUrl} size={4}/>
                <div className='p-2'>
                    <div className='fw-semibold'>{name}</div>
                    <div className='fw-light'>
                        @{username}
                    </div>
                </div>
            </Stack>
        </ListGroup.Item>
    )
}

function UsersListScreen() {
    const navigate = useNavigate()
    const [loading, users, error] = useFetchUsers()
    return <UsersList
        loading={loading}
        users={users}
        error={error}
        onItemClick={username => navigate(username)}
    />
}

function UsersList({loading, users, error, onItemClick}) {
    const List = ({users, onItemClick}) => (
        users.length === 0 ? <NoResults/> : <ListGroup>
            {
                users.map(user => <UserItem
                    key={user.id}
                    avatarUrl={user.avatarUrl}
                    username={user.username}
                    name={user.name}
                    onClick={onItemClick}
                />)
            }
        </ListGroup>
    )

    const Body = () => (error ? <Error message={error.message}/> : <List users={users} onItemClick={onItemClick}/>)

    return (
        <div>
            <AppBar/>
            {loading ? <Loading/> : <Body/>}
        </div>
    )
}

export {
    UsersList,
    UsersListScreen
}
