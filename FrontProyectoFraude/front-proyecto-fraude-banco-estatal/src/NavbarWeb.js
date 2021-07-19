import { Navbar, Nav, NavDropdown, Container } from "react-bootstrap";
import { Link, useHistory } from "react-router-dom";
import React, { useState } from "react";
function NavbarWeb() {
    const history = useHistory();
    function logOut() {
        localStorage.clear();
        window.location.reload();
        history.push('/login')
    }
    return (
        <div>
            <Navbar bg="light" expand="lg">
                <Container>
                    <Navbar.Brand href="/home">Banco Estatal</Navbar.Brand>
                    <Navbar.Toggle aria-controls="basic-navbar-nav" />
                    <Navbar.Collapse id="basic-navbar-nav">
                        <Nav className="ml-auto">
                            <Nav.Link href="/home">Inicio</Nav.Link>
                            {localStorage.getItem('authorization') ?
                                <>
                                    {localStorage.getItem('rol') == "ROLE_CLIENT" ?
                                        <>
                                            <NavDropdown title={localStorage.getItem("usuario")} id="basic-nav-dropdown">
                                                <NavDropdown.Item href="/levantarTicket">Ingresar ticket</NavDropdown.Item>
                                                <NavDropdown.Divider />
                                                <NavDropdown.Item onClick={logOut}>Cerrar sesión</NavDropdown.Item>
                                            </NavDropdown>
                                        </>
                                        :
                                        <>
                                            <NavDropdown title={localStorage.getItem("usuario")} id="basic-nav-dropdown">
                                                <NavDropdown.Item href="/verCasos">Ver Casos</NavDropdown.Item>
                                                <NavDropdown.Item href="/home">Ver Casos Tomados</NavDropdown.Item>
                                                <NavDropdown.Item href="/home">Generar PDF</NavDropdown.Item>
                                                <NavDropdown.Divider />
                                                <NavDropdown.Item onClick={logOut}>Cerrar sesión</NavDropdown.Item>
                                            </NavDropdown>
                                        </>
                                    }
                                </>
                                :
                                <>
                                    <Nav.Link href="/login">Iniciar Sesión</Nav.Link>
                                    <Nav.Link href="/register">Registrarse</Nav.Link>
                                </>
                            }
                        </Nav>
                    </Navbar.Collapse>
                </Container>
            </Navbar>
        </div>
    );
}

export default NavbarWeb