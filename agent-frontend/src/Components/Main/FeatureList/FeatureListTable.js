import React from "react";
import Table from "react-bootstrap/Table";
import "./FeatureListTable.css";

// Only rerender after new data is returned from retrieveFeatureListAPI.
const areEqual = (prevProps, nextProps) => {
  if (prevProps.tableData === nextProps.tableData) {
    return true;
  }
  return false;
};

const FeatureListTable = ({
  tableData,
  selectedRowsIndexes,
  setSelectedRowsIndexes,
}) => {
  // function is called everything checkbox is clicked/unclicked
  // selected rows are added / unselected rows are deletd
  const checkBoxChanged = (e) => {
    let index = parseInt(e.target.getAttribute("checkBoxIndex"));

    if (e.target.checked) {
      selectedRowsIndexes.push(index);
    } else {
      index = selectedRowsIndexes.indexOf(index);
      if (index > -1) {
        selectedRowsIndexes.splice(index, 1);
      }
    }
    setSelectedRowsIndexes(selectedRowsIndexes);
  };

  const FeatureTable = () => (
    <Table striped bordered responsive="sm" className="FeatureTable">
      <thead>
        <tr>
          <th></th>
          <th>Title</th>
          <th>Description</th>
          <th>Client</th>
          <th>Client Priority</th>
          <th>Target Date</th>
          <th>Product Area</th>
          <th>Feature Status</th>
        </tr>
      </thead>
      <tbody>
        {tableData.map(function (item, i = 0) {
          return (
            <tr>
              <td>
                <input
                  type="checkbox"
                  checkBoxIndex={i++}
                  onChange={checkBoxChanged}
                />
              </td>
              <td className="titleData">{item.title}</td>
              <td className="descriptionData">{item.description}</td>
              <td className="clientData">{item.client}</td>
              <td className="clientPriorityData">{item.clientPriority}</td>
              <td className="targetDateData">{item.targetDate}</td>
              <td className="productAreaData">{item.productArea}</td>
              <td className={"featureStatusData" + item.featureStatus}>
                {item.featureStatus}
              </td>
            </tr>
          );
        })}
      </tbody>
    </Table>
  );

  return (
    <>
      <FeatureTable />
    </>
  );
};

export default React.memo(FeatureListTable, areEqual);
