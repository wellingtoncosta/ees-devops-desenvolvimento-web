import {useState, useEffect} from 'react'
import axios from 'axios'

function mapResponse(response) {
    return response.data.map(user => ({
        id: user.id,
        avatarUrl: user.avatar_url,
        username: user.login,
        name: user.name ? user.name : user.login,
    }))
}

function mapError(error) {
    return {
        message: 'Unable to fetch users.',
        status: error.response.status
    }
}

function fetchUsersCall(onSuccess, onError) {
    axios.get('https://api.github.com/users')
        .then(response => onSuccess(mapResponse(response)))
        .catch(error => onError(mapError(error)))
}

export default function useFetchUsers() {
    const [users, setUsers] = useState([])
    const [loading, setLoading] = useState(false)
    const [error, setError] = useState(null)

    const fetchUsers = () => {
        setLoading(true)

        const onSuccess = (users) => {
            setLoading(false)
            setUsers(users)
            setError(null)
        }

        const onError = (error) => {
            setLoading(false)
            setError(error)
            setUsers([])
        }

        fetchUsersCall(onSuccess, onError)
    }

    useEffect(() => fetchUsers(), [])

    return [loading, users, error]
}
