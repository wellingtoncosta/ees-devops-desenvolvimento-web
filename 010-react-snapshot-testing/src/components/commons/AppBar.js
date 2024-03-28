import Navbar from 'react-bootstrap/Navbar';

function AppBar() {
    return (
        <Navbar className='bg-dark'>
            <Navbar.Brand>
                <div className='ms-4 text-white fs-3'>Github Users</div>
            </Navbar.Brand>
        </Navbar>
    )
}

export default AppBar;
