import 'bootstrap/dist/css/bootstrap.min.css';

import React from 'react';
import ReactDOM from 'react-dom/client';
import {createBrowserRouter, RouterProvider} from "react-router-dom";

import ClickMe from './components/hooks';
import GithubUsers from './components/github';
import UserDetails from './components/github/details';
import Home from './components';

const router = createBrowserRouter([
        {
            path: "/",
            element: <Home/>,
        },
        {
            path: "/clickme-example",
            element: <ClickMe/>
        },
        {
            path: "/github-users",
            element: <GithubUsers/>,
        },
        {
            path: '/github-users/:username',
            element: <UserDetails/>
        }
    ]
);

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
    <React.StrictMode>
        <RouterProvider router={router}/>
    </React.StrictMode>
);
