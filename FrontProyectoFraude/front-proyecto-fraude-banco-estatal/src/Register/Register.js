import React, { useState } from "react";
import "./register.component.css";
import CompanyLogo from './bancoestatal.jpg';
import { useHistory } from "react-router-dom";
import Alert from 'react-bootstrap/Alert'
function Register() {
  const [rut, setRut] = useState("")
  const [password, setPassword] = useState("")
  const [fullname, setName] = useState("")
  const [address, setAddres] = useState("")
  const [email, setEmail] = useState("")
  const [account, setAccount] = useState("")
  const [phone_number, setPhoneNumber] = useState("")
  const [errors, setErrors] = useState({})
  const [status, setStatus] = useState("")
  const [revisar, setRevisar] = useState("")
  let status1 = ""
  const history = useHistory();
  let formIsValid = true;
  let validacion1 = true;
  async function signUp() {
    validacion1 = validacion();
    if (validacion1) {
      let item = { rut, password, fullname, address, email, account, phone_number }
      let result = await fetch("http://localhost:8080/register", {
        method: 'POST',
        body: JSON.stringify(item),
        headers: {
          "Content-Type": 'application/json',
          "Accept": 'application/json',
        }
      }).then((response) => { setStatus(response.status); status1 = response.status });
      setRevisar(false);
      console.log(status);
      if (status == "200" || status1 == "200") {
        history.push("/login")
        window.location.reload();
      } 
    }
  }
  function validacion() {
    formIsValid = true
    let errors1 = {}
    for (var i = 0; i < 7; i++) {
      errors[i] = undefined
    }
    //rut
    if (!rut) {
      formIsValid = false;
      errors1[0] = "No puede estar vacío";
    } else if (!rut.match(/^[0-9]{7,8}-[0-9k]{1}$/)) {
      errors1[0] = "Formato rut incorrecto";
    }


    //password
    if (!password) {
      formIsValid = false;
      errors1[1] = "No puede estar vacío";
    } else if (!password.match(/^[0-9]{4}$/)) {
      errors1[1] = "Contraseña solo puede ser de 4 dígitos y númerica";
    }
    //fullname
    if (!fullname) {
      formIsValid = false;
      errors1[2] = "No puede estar vacío";
    } else if (typeof fullname !== "undefined") {
      if (!fullname.match(/^[a-zA-Z]{4,}(?: [a-zA-Z]+)?(?: [a-zA-Z]+)?(?: [a-zA-Z]+)?$/)) {
        formIsValid = false;
        errors1[2] = "Solo letras y mínimo 4 letras";
      }
    }
    //address
    if (!address) {
      formIsValid = false;
      errors1[3] = "No puede estar vacío";
    }
    //email
    if (!email) {
      formIsValid = false;
      errors1[4] = "No puede estar vacío";
    } else if (typeof email !== "undefined") {
      let lastAtPos = email.lastIndexOf('@');
      let lastDotPos = email.lastIndexOf('.');

      if (!(lastAtPos < lastDotPos && lastAtPos > 0 && email.indexOf('@@') == -1 && lastDotPos > 2 && (email.length - lastDotPos) > 2)) {
        formIsValid = false;
        errors1[4] = "Formato de correo no válido";
      }
    }
    //account
    if (!account) {
      formIsValid = false;
      errors1[5] = "No puede estar vacío";
    } else if (!account.match(/^[0-9]{8,12}$/)) {
      errors1[5] = "Número de cuenta solo puede ser un número entre 8 y 12 digitos";
    }

    //phone_number
    if (!phone_number) {
      formIsValid = false;
      errors1[6] = "No puede estar vacío";
    } else if (!phone_number.match(/^(\+?56)?(\s?)(0?9)(\s?)[9876543]\d{7}$/)) {
      errors1[6] = "Formato incorrecto";
    }
    for (var i = 0; i < 7; i++) {
      console.log(i + ") " + errors[i])
    }
    setErrors(errors1);
    return formIsValid
  }

  return (
    <div className="inner-container" className="fadeIn first">
      <div className="box">
        <div className="image" >
          <img src={CompanyLogo} width="300" height="150" />
        </div>
        <form>
          <div className="input-group">
            <label htmlFor="usuario">Nombre completo</label>
            <input type="text" value={fullname} onChange={(e) => setName(e.target.value)} name="nombre" className="login-input" placeholder="Nombre completo" required />
            {errors[2] !== undefined ?
              <>
                <Alert variant="danger" data-dismiss="alert">
                  {errors[2]}
                </Alert>
              </>
              :
              <>
              </>
            }
          </div>
          <div className="input-group">
            <label htmlFor="rut">Rut (Formato: "11111111-1")</label>
            <input type="text" value={rut} onChange={(e) => setRut(e.target.value)} name="rut" className="login-input" placeholder="Rut" required />
            {errors[0] !== undefined ?
              <>
                <Alert variant="danger" data-dismiss="alert">
                  {errors[0]}
                </Alert>
              </>
              :
              <>
              </>
            }
          </div>
          <div className="input-group">
            <label htmlFor="numeroCuenta">Numero de cuenta</label>
            <input type="text" value={account} onChange={(e) => setAccount(e.target.value)} name="numeroCuenta" className="login-input" placeholder="Numero de cuenta" required />
            {errors[5] !== undefined ?
              <>
                <Alert variant="danger" data-dismiss="alert">
                  {errors[5]}
                </Alert>
              </>
              :
              <>
              </>
            }
          </div>
          <div className="input-group">
            <label htmlFor="correo">Correo</label>
            <input type="email" value={email} onChange={(e) => setEmail(e.target.value)} name="correo" pattern=".+@globex\.com" className="login-input" placeholder="Correo" required />
            {errors[4] !== undefined ?
              <>
                <Alert variant="danger" data-dismiss="alert">
                  {errors[4]}
                </Alert>
              </>
              :
              <>
              </>
            }
          </div>
          <div className="input-group">
            <label htmlFor="direccion">Dirección</label>
            <input type="text" value={address} onChange={(e) => setAddres(e.target.value)} name="direccion" className="login-input" placeholder="direccion" required />
            {errors[3] !== undefined ?
              <>
                <Alert variant="danger" data-dismiss="alert">
                  {errors[3]}
                </Alert>
              </>
              :
              <>
              </>
            }
          </div>
          <div className="input-group">
            <label htmlFor="phone_number">Número de contacto</label>
            <input type="text" value={phone_number} onChange={(e) => setPhoneNumber(e.target.value)} name="phone_number" className="login-input" placeholder="Número de contacto" required />
            {errors[6] !== undefined ?
              <>
                <Alert variant="danger" data-dismiss="alert">
                  {errors[6]}
                </Alert>
              </>
              :
              <>
              </>
            }
            <div className="input-group">
              <label htmlFor="contraseña">Contraseña</label>
              <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} name="contraseña" className="login-input" placeholder="contraseña" maxLength="4" required />
              {errors[1] !== undefined ?
                <>
                  <Alert variant="danger" data-dismiss="alert">
                    {errors[1]}
                  </Alert>
                </>
                :
                <>
                </>
              }
            </div>
          </div>
        </form>

        <div className="input-group">
          <button type="button" onClick={signUp} className="login-btn">
            Registrar
          </button>
        </div>
        {status == "200" ?
          <>
          </>
          :
          <>
            {
              revisar === false ?
                <>
                  <Alert variant="danger" data-dismiss="alert">
                    {"No se ha logrado registrar su cuenta, error: " + status}
                  </Alert>
                </>
                :
                <>
                </>
            }

          </>
        }
      </div>
    </div>
  );
};

export default Register;