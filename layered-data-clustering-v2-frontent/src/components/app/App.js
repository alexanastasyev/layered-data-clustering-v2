import React from "react";
import axios from "axios";

// noinspection ES6CheckImport
import {
    BrowserRouter,
    Routes,
    Route,
    Navigate
} from "react-router-dom"

import "./App.css";

import Table from "../table/Table";
import Nav from "../nav/Nav";
import Loading from "../loading/Loading";
import Layers from "../layers/Layers";

class App extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            data: "",
            layers: [],
            layersLoaded: false
        }

        this.loadData = this.loadData.bind(this);
        this.loadClusters = this.loadClusters.bind(this);
    }

    componentDidMount() {
        this.loadData();
        this.loadClusters();
    }

    loadData() {
        axios.get("http://localhost:8080/data?strategy=" + this.state.strategy)
            .then(response => {
                if (response.status === 200) {
                    this.setState({
                        data: response.data
                    });
                }
            });
    }

    loadClusters() {
        const levels = [0, 0.3, 0.5, 0.6, 0.7, 0.75, 0.8, 1, 2];

        levels.forEach((level, index) => {
            axios.get("http://localhost:8080/clusters?level=" + level)
                .then(response => {
                    if (response.status === 200) {
                        let newLayers = this.state.layers;
                        newLayers[index] = response.data;
                        this.setState({
                            layers: newLayers
                        });
                        if (index === levels.length - 1) {
                            this.setState({
                                layersLoaded: true
                            });
                        }
                    }
                })
                .catch(error => console.log(error));
        });

    }

    render() {
        return (
            <div className="app">
                <BrowserRouter>
                    <Nav/>
                    <Routes>

                        <Route path="/" element={<Navigate to={"/data"}/>} />

                        <Route path="/data" element={
                            this.state.data ?
                                <Table data={this.state.data}/> :
                                <Loading/>
                        }/>

                        <Route path="/clusters" element={
                            this.state.layersLoaded ?
                                <Layers layers={this.state.layers} strategy={this.state.strategy} onStrategyChanged={this.handleStrategyChanged}/> :
                                <Loading/>
                        }/>

                    </Routes>
                </BrowserRouter>
            </div>
        );
    }
}

export default App;
