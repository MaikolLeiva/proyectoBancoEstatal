import React, { useState, useEffect } from "react";
import "./ticket.component.css";
import { useHistory } from "react-router-dom";

function VerTicket() {
    const history = useHistory();
    //Informacion de ticket
    const [ticketId, setTicketId] = useState("")
    const [cardType, setCardType] = useState("")
    const [comment, setComment] = useState("")
    const [status, setStatus] = useState("")
    //Informacion de usuario
    const [userId, setUserId] = useState("")
    const [rut, setRut] = useState("")
    const [fullname, setFullname] = useState("")
    const [address, setAddress] = useState("")
    const [email, setEmail] = useState("")
    const [account, setAccount] = useState("")
    const [phoneNumber, setPhoneNumber] = useState("")

    useEffect(() => {
        fetch("http://localhost:8080/tickets/" + localStorage.getItem('ticketId') + "/", {
            method: 'GET',
            headers: {
                'Authorization': localStorage.getItem('authorization'),
                "Content-Type": 'application/json',
                "Accept": 'application/json',
            }
        }).then(res => res.json())
            .then(
                (result) => {
                    setTicketId(result.id);
                    setCardType(result.cardType);
                    setComment(result.comment);
                    setStatus(result.status);

                    setUserId(result.client.id);
                    setRut(result.client.rut);
                    setFullname(result.client.fullname);
                    setAddress(result.client.address);
                    setEmail(result.client.email);
                    setAccount(result.client.account);
                    setPhoneNumber(result.client.phone_number);
                }
            )
    }, [])
    function tomarCaso() {
        fetch("http://localhost:8080/tickets/" + ticketId + "/?status=open", {
            method: 'POST',
            headers: {
                'Authorization': localStorage.getItem('authorization'),
                "Content-Type": 'application/json',
                "Accept": 'application/json',
            }
        })
        history.push("/verCasos")
        window.location.reload();
    }
    function volver() {
        history.push("/verCasos")
    }
    return (
        <div>
            <p>Información de fraude</p>
            <table className="table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Tipo tarjeta</th>
                        <th>Comentario</th>
                        <th>Estado</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>{ticketId}</td>
                        <td>{cardType}</td>
                        <td>{comment}</td>
                        <td>{status}</td>

                    </tr>
                </tbody>
            </table>

            <p>Información de usuario</p>
            <table className="table">
                <thead>
                    <tr>
                        <th>ID cliente</th>
                        <th>RUT</th>
                        <th>Nombre</th>
                        <th>Dirección</th>
                        <th>Correo</th>
                        <th>Número de cuenta</th>
                        <th>Número de contacto</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>

                        <td>{userId}</td>
                        <td>{rut}</td>
                        <td>{fullname}</td>
                        <td>{address}</td>
                        <td>{email}</td>
                        <td>{account}</td>
                        <td>{phoneNumber}</td>

                    </tr>
                </tbody>
            </table>
            <br></br>
            <br></br>
            <center><button onClick={tomarCaso}>Tomar</button><button onClick={volver}>Volver</button></center>
        </div>
    );
};

export default VerTicket;