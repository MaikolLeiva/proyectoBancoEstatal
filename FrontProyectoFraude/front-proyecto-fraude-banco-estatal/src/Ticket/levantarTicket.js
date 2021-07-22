import React, { useState } from "react";
import "./ticket.component.css";
import CompanyLogo from './bancoestatal.jpg';
import { useHistory } from "react-router-dom";
import Alert from 'react-bootstrap/Alert'

function LevantarTicket() {
    const [card_type, setCard_type] = useState("")
    const [comment, setComment] = useState("")
    const [errors, setErrors] = useState({})
    const [status, setStatus] = useState("")
    const [revisar, setRevisar] = useState("")
    let status1 = ""
    let formIsValid = true;
    let validacion1 = true;
    const history = useHistory();
    async function crearTicket() {
        validacion1 = validacion();
        if (validacion1) {
            let item = { card_type, comment }
            await fetch("http://localhost:8080/tickets", {
                method: 'POST',
                body: JSON.stringify(item),
                headers: {
                    'Authorization': localStorage.getItem('authorization'),
                    "Content-Type": 'application/json',
                    "Accept": 'application/json',
                }
            }).then((response) => { setStatus(response.status); status1 = response.status });
            setRevisar(false);
            console.log(status);
            if (status == "200" || status1 == "200") {
                history.push("/home")
            }
        }
        
    }
    
    function validacion() {
        let errors1 = {}
        formIsValid = true
        for (var i = 0; i < 2; i++) {
            errors1[i] = undefined
        }
        if (!card_type) {
            formIsValid = false;
            errors1[0] = "No puede estar vacío";
        }
        if (!comment) {
            formIsValid = false;
            errors1[1] = "No puede estar vacío";
        }
        setErrors(errors1);
        return formIsValid
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
                    <label htmlFor="debito">Débito</label>
                    <br />
                    <input type="radio" name="tipo" value={"CREDIT"} onChange={(e) => setCard_type(e.target.value)} />
                    <label htmlFor="credito">Crédito</label>
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
                <br />
                <div className="input-group" >
                    <label htmlFor="comment">Comentario</label>
                    <input type="text" value={comment} onChange={(e) => setComment(e.target.value)} name="comment" className="login-input" placeholder="Comentario" maxLength="300" />
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
                <div className="input-group">
                    <button type="button" onClick={crearTicket} className="login-btn">
                        Continuar
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
                                        {"No se ha logrado realizar su ticket, error: " + status}
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

export default LevantarTicket;