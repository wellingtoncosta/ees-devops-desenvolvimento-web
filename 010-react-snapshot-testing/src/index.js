import 'bootstrap/dist/css/bootstrap.min.css'

import React from 'react'
import ReactDOM from 'react-dom/client'
import {createBrowserRouter, RouterProvider} from 'react-router-dom'

import {UsersListScreen} from './components/list'
import {UserDetailsScreen} from './components/details'

const router = createBrowserRouter([
        {
            path: "/",
            element: <UsersListScreen/>,
        },
        {
            path: '/:username',
            element: <UserDetailsScreen/>
        }
    ]
);

const root = ReactDOM.createRoot(document.getElementById('root'))

root.render(
    <React.StrictMode>
        <RouterProvider router={router}/>
    </React.StrictMode>
)
