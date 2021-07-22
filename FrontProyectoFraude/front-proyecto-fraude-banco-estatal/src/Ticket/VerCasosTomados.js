import React, { useState, useEffect } from "react";
import "./ticket.component.css";
import { useHistory } from "react-router-dom";
function VerCasosTomados() {
    const [data, setData] = useState([]);
    const [error, setError] = useState(null);
    const history = useHistory();
    useEffect(() => {
        fetch("http://localhost:8080/tickets/pending/", {
            method: 'GET',
            headers: {
                'Authorization': localStorage.getItem('authorization'),
                "Content-Type": 'application/json',
                "Accept": 'application/json',
            }
        }).then(res => res.json())
            .then(
                (result) => {
                    setData(result);
                },
                (error) => {
                    setError(error);
                }
            )
    }, [])
    return (
        <div>
            <table className="table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Tipo tarjeta</th>
                        <th>Comentario</th>
                        <th>Estado</th>
                        <th>Acción</th>
                    </tr>
                </thead>
                <tbody>
                    {data.map((item, index) => (
                        <tr key={index}>
                            <td>{item.id}</td>
                            <td>{item.cardType}</td>
                            <td>{item.comment}</td>
                            <td>{item.status}</td>
                            <td>
                                <button onClick={function () { localStorage.setItem("ticketId", item.id); history.push("/verTicketTomado"); history.push("#" + localStorage.getItem('ticketId')) }}>Ver</button>
                            </td>
                        </tr>
                    ))
                    }
                </tbody>
            </table>
        </div>
    )
};

export default VerCasosTomados;