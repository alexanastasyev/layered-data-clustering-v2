import React from "react";
import DataTable from 'react-data-table-component';

import "./Table.css"

// noinspection JSUnresolvedVariable
class Table extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            data: props.data
        }

    }

    render() {
        let columns = [
            {
                name: "№ п/п",
                selector: row => Number(row.entityKey.number),
                sortable: true
            },
            {
                name: "ФИО",
                selector: row => row.entityKey.name,
                sortable: true,
                grow: 3
            }
        ];

        for (let i = 1; i < 32; i++) {
            columns[1 + i] = {
                name: "w" + i,
                selector: row => Number(row.clusteringValues["w" + i]),
                sortable: false
            }
        }

        return (
            <div>
                <DataTable
                    columns={columns}
                    data={this.state.data}
                    pagination={true}
                />
            </div>

        );
    }
}

export default Table;