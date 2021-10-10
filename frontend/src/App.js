import React from "react";
import './App.css';
import Home from "./components/Home/Home";
import Privacy from "./components/Privacy/Privacy";
import FindStranger from "./components/FindStranger/FindStranger";
import {Route} from "react-router-dom";
import NavBar from './components/NavBar/NavBar';
import NNavBar from './components/LogoNavBar/LogoNavBar';
import {useHistory} from 'react-router-dom';

function App() {
    const history = useHistory();
    return (
        <div className="container">
            <div className="circle"></div>
            <div className="f1">
                <div>
                    <NNavBar/>
                </div>
                <div>
                    <NavBar/>
                </div>
                <div>
                    <button className="start" onClick={() => history.push("/chat")}>Start Chatting</button>
                </div>
            </div>
            <Route exact path="/" component={Home}/>
            <Route exact path="/chat" component={FindStranger}/>
            <Route exact path="/privacy" component={Privacy}/>
            <Route path="/contact" component={() => {
                window.location.href = 'https://github.com/e2xen/innogl';
                return null;
            }}/>
        </div>
    );
}

export default App;
