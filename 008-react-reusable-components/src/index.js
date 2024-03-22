import React from 'react';
import ReactDOM from 'react-dom/client';
import {
  createBrowserRouter,
  RouterProvider,
} from "react-router-dom";
import App from './App';
import AddProduct from "./AddProduct";
import { products } from './products.js'
import './index.css'

const router = createBrowserRouter([
  {
    path: "/",
    element: <App products={products} />,
  },
  {
    path: "/add",
    element: <AddProduct />
  },
  ]
);

const root = ReactDOM.createRoot(document.getElementById('root'));

root.render(
  <React.StrictMode>
    <RouterProvider router={router} />
  </React.StrictMode>
);
