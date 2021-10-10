import React from "react";
import ReactLoading from 'react-loading';
import './Loader.css';

const Loader = (props) => {
    return (
        <div className="animation-loading-bubbles">
            <ReactLoading className="loader-chat" type="bubbles" color="#E7844C" />
            <p>{props.text}</p>
        </div>
    )
};

export default Loader;