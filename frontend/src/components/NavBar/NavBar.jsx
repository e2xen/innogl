import React from 'react'
import {Link} from 'react-router-dom'

function NavBar() {
    return (
        <div className="flhyper">
            <h2><Link to="/" className="hyper">Home</Link></h2>
            <h2><Link to="/privacy" className="hyper">Privacy</Link></h2>
            <h2><Link to="/contact" className="hyper">Contact</Link></h2>

        </div>
    );
}

export default NavBar;
