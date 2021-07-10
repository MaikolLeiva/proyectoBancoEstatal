import React from "react";
import CompanyLogo from './bancoestatal.jpg';

export class Registro extends React.Component {

    constructor(props) {
        super(props);
    }

    render() {
        return <div className="base-container" ref={this.props.containerRef}>
            <div className="header">Registro</div>
            <div className="content">
                <div className="image">
                    <img src={CompanyLogo}/>
                </div>
                <div className="form">
                    <div className="form-group">
                        <label htmlFor="usuario">Nombre completo</label>
                        <input type="text" name="nombre" placeholder="Nombre completo"/>
                    </div>
                    <div className="form-group">
                        <label htmlFor="rut">Rut</label>
                        <input type="text" name="rut" placeholder="Rut"/>
                    </div>
                    <div className="form-group">
                        <label htmlFor="numeroCuenta">Numero de cuenta</label>
                        <input type="text" name="numeroCuenta" placeholder="Numero de cuenta"/>
                    </div>
                    <div className="form-group">
                        <label htmlFor="correo">Correo</label>
                        <input type="text" name="correo" className="login-input" placeholder="Correo"/>
                    </div>
                    <div className="form-group">
                        <label htmlFor="contraseña">Contraseña</label>
                        <input type="text" name="contraseña" placeholder="contraseña"/>
                    </div>
                </div>
            </div>
            <div className="footer">
                <button type="button" className="btn">
                    Registrar
                </button>
            </div>
        </div>
    }

}