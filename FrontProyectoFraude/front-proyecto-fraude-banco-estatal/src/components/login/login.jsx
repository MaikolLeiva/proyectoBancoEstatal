import React from "react";
import CompanyLogo from './bancoestatal.jpg';

export class Login extends React.Component {

    constructor(props) {
        super(props);
    }


    render() {
        return <div className="base-container" ref={this.props.containerRef}>
            <div className="header">Login</div>
            <div className="content">
                <div className="image">
                    <img src={CompanyLogo} alt="Banco estatal. logo"/>
                </div>
                <div className="form">
                    <div className="form-group">
                        <label htmlFor="rut">Rut</label>
                        <input type="text" name="rut" placeholder="Rut"/>
                    </div>
                    <div className="form-group">
                        <label htmlFor="contraseña">Contraseña</label>
                        <input type="text" name="contraseña" placeholder="contraseña"/>
                    </div>
                </div>
            </div>
            <div className="footer">
                <button type="button" className="btn">
                    Acceder
                </button>
            </div>
        </div>
    }

}