import {Link} from 'react-router-dom';

function Home() {
    return (
        <div style={{textAlign: 'center', marginTop: '2em'}}>
            <Link to='/clickme-example'>Go to ClickMe</Link>
            <br/>
            <br/>
            <br/>
            <Link to='/github-users'>Go to Github Users</Link>
        </div>
    )
}

export default Home;
