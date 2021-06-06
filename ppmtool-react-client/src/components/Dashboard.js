import React, {Component} from 'react';
import ProjectItem from "./project/ProjectItem";
import Header from "./layout/header";

class Dashboard extends Component {
    render() {
        return (
            <div>
                <Header></Header>
                <h1>Welcome to the Dashboard</h1>
                <ProjectItem></ProjectItem>
                <ProjectItem></ProjectItem>
                <ProjectItem></ProjectItem>
            </div>
        );
    }
}

export default Dashboard;
