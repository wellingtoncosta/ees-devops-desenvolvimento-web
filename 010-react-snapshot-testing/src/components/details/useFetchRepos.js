import {useState, useEffect} from 'react';
import axios from 'axios';

function mapResponse(response) {
    return response.data.map(repository => (
        {
            name: repository.name,
            stars: repository.stargazers_count,
            forks: repository.forks_count,
            openIssues: repository.open_issues_count,
        }
    ))
}

function mapError(error) {
    return {
        message: `Unable to fetch repos.`,
        status: error.response.status
    }
}

function fetchReposCall(username, onSuccess, onError) {
    axios.get(`https://api.github.com/users/${username}/repos`)
        .then(response => onSuccess(mapResponse(response)))
        .catch(error => onError(mapError(error)))
}

export default function useFetchRepos(username) {
    const [repositories, setRepositories] = useState([]);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);

    const fetchRepos = (username) => {
        setLoading(true)

        const onSuccess = (repos) => {
            setLoading(false)
            setError(null)
            setRepositories(repos)
        }

        const onError = (error) => {
            setLoading(false)
            setError(error)
            setRepositories([])
        }

        fetchReposCall(username, onSuccess, onError)
    }

    useEffect(() => fetchRepos(username), [username]);

    return [loading, repositories, error]
}
