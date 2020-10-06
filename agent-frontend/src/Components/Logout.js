import React from "react";
import { useAuth0 } from "@auth0/auth0-react";
import Button from 'react-bootstrap/Button'

const Logout = () => {
    const { logout } = useAuth0();

    return <Button variant="warning" onClick={() => logout()}>Log In</Button>
};

export default Logout;