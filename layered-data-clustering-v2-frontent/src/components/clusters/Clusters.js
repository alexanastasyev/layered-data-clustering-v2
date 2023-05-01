import React from "react";
import Highcharts from 'highcharts'
import HighchartsReact from 'highcharts-react-official'

import "./Clusters.css"

class Clusters extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            clusters: props.clusters,
            showSmall: props.showSmall
        }

    }

    render() {
        const options = {
            chart: {
              type: 'column'
            },
            title: {
                text: 'Размеры кластеров'
            },
            series: [{
                name: 'Cluster size',
                data: this.state.clusters
                    .map(cluster => cluster.length)
                    .filter(size => this.state.showSmall ? true : size > 1),
                dataLabels: {
                    enabled: true
                },
                showInLegend: false
            }]
        }

        return (
            <div>

                <HighchartsReact
                    highcharts={Highcharts}
                    options={options}
                />
                &emsp;

                <table>
                    <tbody>
                        {this.state.clusters
                            .filter(cluster => this.state.showSmall ? true : cluster.length > 1)
                            .map((cluster, clusterIndex) =>
                            <tr key={clusterIndex}>
                                <td className={"cluster-table-item"}>{clusterIndex}</td>
                                <td className={"cluster-table-item"}>
                                    {cluster.map((item, itemIndex) =>
                                        <span key={itemIndex}>{item.name}({item.number}){itemIndex < cluster.length - 1 ? ",\t" : ""}</span>
                                    )}
                                </td>
                            </tr>
                        )}
                    </tbody>
                </table>
            </div>
        )
    }
}

export default Clusters;