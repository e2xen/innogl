import React from 'react'
import {Link} from 'react-router-dom'

function LogoNavBar() {
    return (
        <div>
            <h2><Link to="/" className="title">Innogl</Link></h2>
        </div>
    );
}

export default LogoNavBar;
