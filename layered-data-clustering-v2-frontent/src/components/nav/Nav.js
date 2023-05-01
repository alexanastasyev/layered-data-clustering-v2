import React from "react";
import 'bootstrap/dist/css/bootstrap.min.css';
import {Link} from "react-router-dom";

import "./Nav.css";

class Nav extends React.Component {

    render() {
        return(
            <div className={"navigation"}>
                <nav className="navbar navbar-expand-sm navbar-light bg-light">
                    <span className="navbar-brand">НИР</span>
                    <button className="navbar-toggler" type="button" data-toggle="collapse"
                            data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                            aria-expanded="false" aria-label="Toggle navigation">
                        <span className="navbar-toggler-icon"/>
                    </button>
                    <div className="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul className="navbar-nav mr-auto">
                            <li className="nav-item">
                                <Link className="nav-link" to="/data">Данные</Link>
                            </li>
                            <li className="nav-item">
                                <Link className="nav-link" to="/clusters">Кластеры</Link>
                            </li>
                        </ul>
                    </div>
                </nav>
            </div>
        );
    }

}

export default Nav;