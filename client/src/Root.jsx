import {Outlet} from "react-router-dom";

function Root() {
    return (
        <>
            <header>Here's my header</header>
            <Outlet/>
            <footer>Here's my footer</footer>
        </>
    )
}

export default Root;