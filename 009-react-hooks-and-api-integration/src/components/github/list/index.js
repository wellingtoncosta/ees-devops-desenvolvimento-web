import ListGroup from 'react-bootstrap/ListGroup'
import Stack from 'react-bootstrap/Stack'

import {useNavigate} from 'react-router-dom'
import Avatar from "../commons/Avatar"

function UserItem({avatarUrl, username, name}) {
    const navigate = useNavigate()

    return (
        <ListGroup.Item action onClick={() => navigate(`${username}`)}>
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

function UsersList({users}) {
    return (
        <ListGroup>
            {
                users.map(user => <UserItem
                    key={user.id}
                    avatarUrl={user.avatarUrl}
                    username={user.username}
                    name={user.name}
                />)
            }
        </ListGroup>
    )
}

export default UsersList;
