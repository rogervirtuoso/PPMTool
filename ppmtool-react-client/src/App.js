import './App.css';
import Dashboard from "./components/Dashboard";
import "bootstrap/dist/css/bootstrap.min.css"
import {BrowserRouter as Router, Route} from "react-router-dom";
import Header from "./components/layout/header";
import AddProject from "./components/project/AddProject";
import {Provider} from "react-redux";
import store from "./Store";
import UpdateProject from "./components/project/UpdateProject";
import {ProjectBoard} from "./components/projectBoard/projectBoard";
import AddProjectTask from "./components/projectBoard/projectTask/addProjectTask";

function App() {
    return (
        <Provider store={store}>
            <Router>
                <div className="App">
                    <Header/>
                    <Route exact path={"/dashboard"} component={Dashboard}/>
                    <Route exact path={"/addProject"} component={AddProject}/>
                    <Route exact path={"/updateProject/:id"} component={UpdateProject}/>
                    <Route exact path={"/projectBoard/:id"} component={ProjectBoard}/>
                    <Route exact path={"/addProjectTask/:id"} component={AddProjectTask}/>
                </div>
            </Router>
        </Provider>
    );
}

export default App;
