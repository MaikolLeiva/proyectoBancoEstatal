import React from 'react';
import NavbarWeb from './NavbarWeb';
import Login from './Login/Login';
import Register from './Register/Register';
import LevantarTicket from './Ticket/levantarTicket';
import VerCasos from './Ticket/verCasos';
import Home from  './Home';
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Link,
  BrowserRouter
} from "react-router-dom";

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
      </BrowserRouter>
    </div>

  );
}

export default App;
