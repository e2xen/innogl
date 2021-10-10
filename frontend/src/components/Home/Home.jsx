import React, {useMemo} from "react";
import './Home.css';
import languages from './languages.png'
import friends from './friends.png'
import problems from './problems.png'
import {ReactComponent as Logo} from './test.svg';
import {useHistory} from 'react-router-dom';

function Home() {
    document.title = useMemo(() => {
        return 'Home page - Innogl'
    }, []);

    const history = useHistory();
    return (
        <div>
            <section className="firsthome">
                <div className="left">
                    <h2>
                        Discuss what you want to.
                    </h2>
                    <p>
                        We guarantee you full anonymity
                    </p>
                    <button className="learn" onClick={() => window.location.href = 'https://github.com/e2xen/innogl'}> Learn more</button>
                    <button className="doit" onClick={() => history.push("/chat")}> Just do it</button>
                </div>
                <div className="right">
                    <div className="photo">
                        <Logo/>
                    </div>
                </div>
            </section>
            <section>
                <h2 className="worry">Do not worry about anything. We will do it for you</h2>
                <p className="ifyou">If you face a problem
                    just discuss them with strangers they may help you.
                    If you want to make some fun then just do it</p>
                <div className="second">
                    <div className="discuss">
                        <img src={problems} alt="" className="vector"/>
                        <p>Discuss problems</p>
                    </div>
                    <div className="study">
                        <img src={languages} alt="" className="vector"/>
                        <p>Study languages</p>
                    </div>
                    <div className="discuss">
                        <img src={friends} alt="" className="vector"/>
                        <p>Find new friends</p>
                    </div>
                </div>
            </section>
            <footer>
                <h2 className="say">What Clients Say</h2>
                <p className="once">We don't know yet, but once we will...</p>
            </footer>
        </div>
    );
}

export default Home;
