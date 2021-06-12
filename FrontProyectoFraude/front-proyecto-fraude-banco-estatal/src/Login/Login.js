import React from "react";
import "./login.component.css";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import companyLogo from './bancoestatal.jpg';
const Login = () => {


    return (
      
      <div className="inner-container" class="fadeIn first">
        <div className="header">
          Inicio de sesión
        </div>
        <div className="box">
          <img src={companyLogo} alt="Banco estatal. logo"/>
          <div className="input-group">
            <label htmlFor="username">Username</label>
            <input
              type="text"
              name="username"
              className="login-input"
              placeholder="Username"/>
          </div>

          <div className="input-group">
            <label htmlFor="password">Password</label>
            <input
              type="password"
              name="password"
              className="login-input"
              placeholder="Password"/>
          </div>

          <button
            type="button"
            className="login-btn"
             /*onClick={this
            .submitLogin
            .bind(this)}*/
            >Iniciar Sesión</button>
        </div>
      </div>
        );
};

export default Login;