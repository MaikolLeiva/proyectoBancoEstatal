import React, { useState, useEffect } from "react";
import "./ticket.component.css";
import { useHistory } from "react-router-dom";
function VerCasos() {
    const [data, setData] = useState([])
    const [isLoaded, setIsLoaded] = useState(false);
    const [error, setError] = useState(null);
    const [q, setQ] = useState("")
    const history = useHistory();
    useEffect(() => {
        fetch("http://localhost:8080/tickets?status=open", {
            method: 'GET',
            headers: {
                'Authorization': localStorage.getItem('authorization'),
                "Content-Type": 'application/json',
                "Accept": 'application/json',
            }
        }).then(res => res.json())
            .then(
                (result) => {
                    setIsLoaded(true);
                    setData(result);
                },
                (error) => {
                    setIsLoaded(true);
                    setError(error);
                }
            )
    }, [])
    console.warn(data)
    function verCaso() {
        history.push("/home")
    }
    function tomarCaso() {
        history.push("/home")
    }
    //history.push("/home")
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
                    {data.map((data1, index) => (
                        <tr key={index}>
                            {console.log(data)}
                            <td>{data1.id}</td>
                            <td>{data1.cardType}</td>
                            <td>{data1.comment}</td>
                            <td>{data1.status}</td>
                            <td>
                                <button onClick={verCaso}>Ver</button>
                                <button onClick={tomarCaso}>Tomar</button>
                            </td>
                        </tr>
                    ))
                    }
                </tbody>
            </table>
        </div>
    )
};

export default VerCasos;