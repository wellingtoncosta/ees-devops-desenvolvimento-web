import Spinner from 'react-bootstrap/Spinner';

import AppBar from './AppBar';
import UsersList from './list';
import useFetchUsers from "./details/useFetchUsers";

function Loading() {
    return (
        <div className='text-center mt-5'>
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

function GithubUsers() {
    const [loading, users, error] = useFetchUsers()
    const Content = () => (error ? <Error message={error.message}/> : <UsersList users={users}/>)
    return (
        <div>
            <AppBar/>
            {loading ? <Loading/> : <Content/>}
        </div>
    )
}

export default GithubUsers;
