import axios from 'axios';

const listAllUsers = (onSuccess, onError) => {
  axios.get('https://api.github.com/users')
  .then(onSuccess)
  .catch(onError);
};

export default listAllUsers;
