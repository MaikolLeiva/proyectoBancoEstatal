import React, { useEffect, useState } from "react";
import "./login.component.css";
import { useHistory } from "react-router-dom";
import CompanyLogo from './bancoestatal.jpg';
import axios from "axios";
import jwt_decode from "jwt-decode";
function Login() {
  const [username, setUsername] = useState("")
  const [password, setPassword] = useState("")
  const history = useHistory();
  useEffect(() => {
    if (localStorage.getItem('authorization')) {
      history.push("/home")
    }
  }, [])
  async function logIn() {
    const endpoint = "http://localhost:8080/login";
    let item = { username, password };
    const user_object = {
      username: username,
      password: password
    };
    axios.post(endpoint, user_object).then(res => {
      localStorage.setItem("authorization", res.headers.authorization);
      localStorage.setItem("usuario", username);
      let token = localStorage.getItem('authorization');
      token.replace("bearer ", "");
      let decoded = jwt_decode(token);
      localStorage.setItem("rol", decoded.authorities[0].authority);
      localStorage.setItem("id",decoded.id)
      localStorage.setItem("nombre",decoded.name)
      console.warn(localStorage.getItem("rol"))
      console.warn(decoded)
      window.location.reload();
      history.push("/home")

    });
  };

  return (
    <div className="inner-container" className="fadeIn first">
      <div className="box">
        <div className="image">
          <img src={CompanyLogo} width="300" height="150" />
        </div>
        <div className="input-group">
          <label htmlFor="usuario">rut</label>
          <input type="text" value={username} onChange={(e) => setUsername(e.target.value)} name="username" className="login-input" placeholder="username" />
        </div>
        <div className="input-group">
          <label htmlFor="contraseña">Contraseña</label>
          <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} name="contraseña" className="login-input" placeholder="contraseña" />
        </div>


        <div className="input-group">
          <button type="button" onClick={logIn} className="login-btn">
            Iniciar
          </button>
        </div>
      </div>
    </div>
  );
};

export default Login;