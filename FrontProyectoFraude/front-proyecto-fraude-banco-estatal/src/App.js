import React from 'react';
import Login from './Login/Login';
import Register from './Register/Register';
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
            <Route path="/login">
                <Login/>
            </Route>
            <Route path="/register">
                <Register/>
            </Route>
            </BrowserRouter>
        </div>

  );
}

export default App;
