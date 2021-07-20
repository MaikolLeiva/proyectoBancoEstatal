import React, { useState } from "react";
import "./ticket.component.css";
import CompanyLogo from './bancoestatal.jpg';
import { useHistory } from "react-router-dom";

function LevantarTicket() {
    const [card_type, setCard_type] = useState("")
    const [comment, setComment] = useState("")

    const history = useHistory();
    async function crearTicket() {
        let item = { card_type, comment }
        console.warn(item)
        console.warn(JSON.stringify(item))
        let result = await fetch("http://localhost:8080/tickets", {
            method: 'POST',
            body: JSON.stringify(item),
            headers: {
                'Authorization': localStorage.getItem('authorization'),
                "Content-Type": 'application/json',
                "Accept": 'application/json',
            }
        })
        history.push("/home")
    }
    return (
        <div className="inner-container" className="fadeIn first">
            <div className="box">
                <div className="image">
                    <img src={CompanyLogo} width="300" height="150" />
                </div>

                <div >
                    <p>Seleccione su tipo de tarjeta</p>
                    <input type="radio" name="tipo" value={"DEBIT"} onChange={(e) => setCard_type(e.target.value)} />
                    <label for="debito">Débito</label>
                    <br />
                    <input type="radio" name="tipo" value={"CREDIT"} onChange={(e) => setCard_type(e.target.value)} />
                    <label for="credito">Crédito</label>
                </div>
                <br />
                <div className="input-group" >
                    <label htmlFor="comment">Comentario</label>
                    <input type="text" value={comment} onChange={(e) => setComment(e.target.value)} name="comment" className="login-input" placeholder="Comentario" />
                </div>
                <div className="input-group">
                    <button type="button" onClick={crearTicket} className="login-btn">
                        Continuar
                    </button>
                </div>
            </div>
        </div>
    );
};

export default LevantarTicket;