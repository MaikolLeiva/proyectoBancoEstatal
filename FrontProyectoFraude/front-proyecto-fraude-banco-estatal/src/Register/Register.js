import React from "react";
import "./register.component.css";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import companyLogo from './bancoestatal.jpg';
const Register = () => {

    return (
      
        <div className="inner-container" class="fadeIn first">
        <div className="header">
          Registro
        </div>
        <div className="box">
            <img src={companyLogo} alt="Banco estatal. logo"/>
          <div className="input-group">
            <label htmlFor="Nombre completo">Nombre completo</label>
            <input
              type="text"
              name="Nombre completo"
              className="login-input"
              placeholder="Nombre completo"/>
          </div>

          <div className="input-group">
            <label htmlFor="Rut">Rut</label>
            <input type="text" name="Rut" className="login-input" placeholder="Rut"/>
          </div>

          <div className="input-group">
            <label htmlFor="Numero de cuenta">Numero de cuenta</label>
            <input type="text" name="Numero de cuenta" className="login-input" placeholder="Numero de cuenta"/>
          </div>

          <div className="input-group">
            <label htmlFor="Correo">Correo</label>
            <input type="text" name="Correo" className="login-input" placeholder="Correo"/>
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
            .submitRegister
            .bind(this)}*/
            >Registrar</button>
        </div>
      </div>
        );
};

export default Register;