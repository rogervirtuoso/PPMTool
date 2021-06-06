import './App.css';
import Dashboard from "./components/Dashboard";
import "bootstrap/dist/css/bootstrap.min.css"
import {Router} from "react-router-dom";

function App() {
    return (
        <Router>
            <div className="App">
                <Dashboard/>
            </div>
        </Router>
    );
}

export default App;
