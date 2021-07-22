import React from 'react';
import NavbarWeb from './NavbarWeb';
import Login from './Login/Login';
import Register from './Register/Register';
import LevantarTicket from './Ticket/LevantarTicket';
import VerCasos from './Ticket/VerCasos';
import VerCasosTomados from './Ticket/VerCasosTomados';
import VerTicket from './Ticket/VerTicket';
import VerTicketTomado from './Ticket/verTicketTomado';
import Home from  './Home';
import { Switch, Route, Link, BrowserRouter } from "react-router-dom";

function App() {
  return (

    <div className="App">
      <BrowserRouter>
        <NavbarWeb />
        <Route path="/home">
          <Home />
        </Route>
        <Route path="/login">
          <Login />
        </Route>
        <Route path="/register">
          <Register />
        </Route>
        <Route path="/levantarTicket">
          <LevantarTicket />
        </Route>
        <Route path="/verCasos">
          <VerCasos />
        </Route>
        <Route path="/verTicket">
          <VerTicket />
        </Route>
        <Route path="/VerTicketTomado">
          <VerTicketTomado />
        </Route>
        <Route path="/VerCasosTomados">
          <VerCasosTomados />
        </Route>
      </BrowserRouter>
    </div>
  );
}

export default App;
