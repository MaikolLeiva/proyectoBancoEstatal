import React, { useState } from "react";
import "./register.component.css";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import CompanyLogo from './bancoestatal.jpg';

function Register() {
  const [rut, setRut] = useState("")
  const [password, setPassword] = useState("")
  const [fullname, setName] = useState("")
  const [address, setAddres] = useState("")
  const [email, setEmail] = useState("")
  const [account, setAccount] = useState("")
  const [phone_number, setPhoneNumber] = useState("")

  async function signUp() {
    let item = { rut, password, fullname, address, email, account, phone_number }
    console.warn(item)
    console.warn(JSON.stringify(item))
    let result=await fetch("http://localhost:8080/register", {
      method: 'POST',
      body: JSON.stringify(item),
      
      headers: {
        "Content-Type": 'application/json',
        "Accept": 'application/json',
      }
    })
  }
  return (
    <div className="inner-container" className="fadeIn first">
      <div className="header">Registro</div>
      <div className="box">
        <div className="image">
          <img src={CompanyLogo} />
        </div>

        <div className="input-group">
          <label htmlFor="usuario">Nombre completo</label>
          <input type="text" value={fullname} onChange={(e) => setName(e.target.value)} name="nombre" className="login-input" placeholder="Nombre completo" />
        </div>
        <div className="input-group">
          <label htmlFor="rut">Rut</label>
          <input type="text" value={rut} onChange={(e) => setRut(e.target.value)} name="rut" className="login-input" placeholder="Rut" />
        </div>
        <div className="input-group">
          <label htmlFor="numeroCuenta">Numero de cuenta</label>
          <input type="text" value={account} onChange={(e) => setAccount(e.target.value)} name="numeroCuenta" className="login-input" placeholder="Numero de cuenta" />
        </div>
        <div className="input-group">
          <label htmlFor="correo">Correo</label>
          <input type="text" value={email} onChange={(e) => setEmail(e.target.value)} name="correo" className="login-input" placeholder="Correo" />
        </div>
        <div className="input-group">
          <label htmlFor="direccion">Dirección</label>
          <input type="text" value={address} onChange={(e) => setAddres(e.target.value)} name="direccion" className="login-input" placeholder="direccion" />
        </div>
        <div className="input-group">
          <label htmlFor="phone_number">Número de contacto</label>
          <input type="text" value={phone_number} onChange={(e) => setPhoneNumber(e.target.value)} name="phone_number" className="login-input" placeholder="Número de contacto" />
          <div className="input-group">
            <label htmlFor="contraseña">Contraseña</label>
            <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} name="contraseña" placeholder="contraseña" />
          </div>
        </div>

        <div className="login-btn">
          <button type="button" onClick={signUp} className="login-btn">
            Registrar
          </button>
        </div>
      </div>
    </div>
  );
};

export default Register;