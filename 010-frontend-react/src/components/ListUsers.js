import React from 'react';
import useListUsers from '../hooks/useListUsers';

const LoadingMessage = () => {
  return (
    <p>
      Loading data, please wait...
    </p>
  );
}

const Error = (props) => {
  const { message } = props;
  return (
    <h3>
      {message}
    </h3>
  );
}

const UserItem = (props) => {
  const { user } = props;
  return (
    <tr>
      <td>{user.id}</td>
      <td>{user.login}</td>
    </tr>
  );
}

const ListUsersTable = (props) => {
  const { users } = props;
  return (
    <table border="1px">
      <thead>
      <tr>
        <th>#</th>
        <th>Username</th>
      </tr>
      </thead>
      <tbody>
        {
          users.map(user => <UserItem key={user.id} user={user} />)
        }
      </tbody>
    </table>
  );
}

const ListUsers = () => {
  const [users, isLoading, error, fetchUsers] = useListUsers();
  return (
    <div>
      <button onClick={fetchUsers}>Refresh</button>
      <br />
      <br />
      {
        error ? <Error message={error} /> : 
        (isLoading ? <LoadingMessage /> : <ListUsersTable users={users} /> )
      }
    </div>
  );
};

export default ListUsers;
