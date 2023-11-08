import './App.css'
import {createBrowserRouter, RouterProvider} from "react-router-dom";
import Login from "./Login.jsx";
import Home from "./Home.jsx";
import Root from "./Root.jsx";


const router = createBrowserRouter([
    // any routes I don't want to render inside my header / footer go up here
    {path: "*", Component: Root, children: [
        // any routes I *DO* want inside my header/footer (eg. most of them) go in here
        {path: "login", Component: Login}
        ]},
]);

export default function App() {
    return <RouterProvider router={router}/>;
}


