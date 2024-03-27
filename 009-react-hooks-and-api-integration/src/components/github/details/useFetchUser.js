import {useState, useEffect} from 'react';
import axios from 'axios';

function mapResponse(response) {
    return {
        avatarUrl: response.data.avatar_url,
        name: response.data.name,
        repos: response.data.public_repos,
        followers: response.data.followers,
        following: response.data.following,
    }
}

function mapError(username, error) {
    return {
        message: `Unable to fetch user details for username ${username}.`,
        status: error.response.status
    }
}

function fetchUserCall(username, onSuccess, onError) {
    axios.get(`https://api.github.com/users/${username}`)
        .then(response => onSuccess(mapResponse(response)))
        .catch(error => onError(mapError(username, error)))
}

export default function useFetchUser(username) {
    const [user, setUser] = useState(null);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);

    const fetchUser = (username) => {
        setLoading(true)

        const onSuccess = (user) => {
            setLoading(false)
            setUser(user)
            setError(null)
        }

        const onError = (error) => {
            setLoading(false)
            setError(error)
            setUser(null)
        }

        fetchUserCall(username, onSuccess, onError)
    }

    useEffect(() => fetchUser(username), [username])

    return [loading, user, error]
}
