
import { application } from "express";
import React from "react";
import { useEffect, useState } from "react";
import CompanyLogo from './bancoestatal.jpg';

function Registro(){

    const [rut,setRut] = useState("")
    const [password,setPassword] = useState("")
    const [fullname,setName] = useState("")
    const [addres,setAddres] = useState("")
    const [email,setEmail] = useState("")
    const [account,setaccount] = useState("")
    const [phone_number,setPhoneNumber] = useState("")

    async function singUp() {
        let item={rut,password,fullname,addres,email,account,phone_number}
        console.warn(item)

        let result = await fetch("http://localhost:8080/register",{
            method:'POST',
            body:JSON.stringify(item),
            headers:{
                "Conten-Type":'application/json',
                "Accept":'application/json'
            }
        })
        result = await result.json()
        console.warn("result",result)
    }

    return ( 
        <div className="base-container" ref={this.props.containerRef}>
        <div className="header">Registro</div>
        <div className="content">
            <div className="image">
                <img src={CompanyLogo}/>
            </div>
            <div className="form">
                <div className="form-group">
                    <label htmlFor="usuario">Nombre completo</label>
                    <input type="text" value = {fullname} onChange={(e)=>setName(e.target.value)} name="nombre" placeholder="Nombre completo"/>
                </div>
                <div className="form-group">
                    <label htmlFor="rut">Rut</label>
                    <input type="text" value = {rut} onChange={(e)=>setRut(e.target.value)} name="rut" placeholder="Rut"/>
                </div>
                <div className="form-group">
                    <label htmlFor="numeroCuenta">Numero de cuenta</label>
                    <input type="text" value = {account} onChange={(e)=>setaccount(e.target.value)} name="numeroCuenta" placeholder="Numero de cuenta"/>
                </div>
                <div className="form-group">
                    <label htmlFor="correo">Correo</label>
                    <input type="text" value = {email} onChange={(e)=>setEmail(e.target.value)} name="correo" className="login-input" placeholder="Correo"/>
                </div>
                <div className="form-group">
                    <label htmlFor="direccion">Dirección</label>
                    <input type="text" value = {addres} onChange={(e)=>setAddres(e.target.value)} name="direccion" className="login-input" placeholder="direccion"/>
                </div>
                <div className="form-group">
                    <label htmlFor="phone_number">Número de contacto</label>
                    <input type="text" value = {phone_number} onChange={(e)=>setPhoneNumber(e.target.value)} name="phone_number" className="login-input" placeholder="phone_number"/>
                </div>
                <div className="form-group">
                    <label htmlFor="contraseña">Contraseña</label>
                    <input type="text" value = {password} onChange={(e)=>setPassword(e.target.value)} name="contraseña" placeholder="contraseña"/>
                </div>
            </div>
        </div>
        <div className="footer">
            <button onClick={singUp} type="button" className="btn">
                Registrar
            </button>
        </div>
    </div>
    )

}

export default Registro
