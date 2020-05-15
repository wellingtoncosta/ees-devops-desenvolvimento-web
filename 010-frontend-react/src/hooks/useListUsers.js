import { useEffect, useState } from 'react';
import listAllUsers from '../api/listAllUsers';

export default () => {
  const [users, setUsers] = useState([]);
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState(null);

  const fetchUsers = () => {
    setIsLoading(true);

    const onSucces = (response) => {
      setIsLoading(false);
      setUsers(response.data);
    }

    const onError = (error) => {
      console.error(error);
      setIsLoading(false);
      setError({ message: 'Unable to load all users.' });
    }

    listAllUsers(onSucces, onError);
  };

  useEffect(() => fetchUsers(), []);

  return [users, isLoading, error, fetchUsers];
}