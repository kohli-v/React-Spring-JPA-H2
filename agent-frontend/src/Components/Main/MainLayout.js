import React, { useState, useEffect } from "react";
import Button from "react-bootstrap/Button";
import "./MainLayout.css";
import AddFeatureModal from "./Modals/AddFeatureModal";
import ErrorModal from "./Modals/ErrorModal";
import FeatureListTable from "./FeatureList/FeatureListTable";
import {
  retrieveFeatureListAPI,
  deleteFeatureAPI,
  enableFeatureAPI,
} from "../../APICalls/calls";

const MainLayout = () => {
  const [listOfFeatures, setListOfFeatures] = useState(null);
  const [selectedRowsIndexes, setSelectedRowsIndexes] = useState([]);
  const [showAddFeatureModal, setShowAddFeatureModal] = useState(false);
  const [errorModal, setErrorModal] = useState({
    show: false,
    heading: null,
    message: null,
  });
  const [error, setError] = useState(null);

  const handleShow = () => {
    setShowAddFeatureModal(!showAddFeatureModal);
  };

  const retrieveFeatureList = () => {
    retrieveFeatureListAPI(
      (res) => {
        setListOfFeatures(res);
      },
      (err) => {
        setError(err);
      }
    );
  };

  useEffect(() => {
    // make API call to retrieve feature list
    retrieveFeatureList();
  }, []);

  const featureActionIsValid = () => {
    let data = {
      valid: false,
      selectedFeatures: [],
    };
    if (selectedRowsIndexes.length === 0) {
      setErrorModal({
        show: true,
        heading: "Error",
        message: "Please select atleast 1 row before actioning feature.",
      });
      data.valid = false;
      return data;
    }
    // get all of the selected feature ids
    selectedRowsIndexes.forEach((i) => {
      data.selectedFeatures.push({
        title: listOfFeatures[i].title,
        client: listOfFeatures[i].client,
      });
    });
    data.valid = true;
    return data;
  };

  // used to delete features
  const deleteFeature = () => {
    let result = featureActionIsValid();
    if (result.valid) {
      deleteFeatureAPI(
        result.selectedFeatures,
        (res) => {
          if (res.result !== "success") {
            setErrorModal({
              show: true,
              heading: "ERROR: Failed to action the following ",
              message: (
                <ul>
                  {res.failedToActionFeatures.map((item) => {
                    return (
                      <li>
                        Feature Title: {item.title} - Client ID: {item.client}
                      </li>
                    );
                  })}
                </ul>
              ),
            });
          }
          retrieveFeatureList();
        },
        (err) => {
          alert("Something went wrong");
        }
      );
    }
    setSelectedRowsIndexes([]);
  };

  // used to enable features
  const enableFeature = () => {
    let result = featureActionIsValid();
    if (result.valid) {
      enableFeatureAPI(
        result.selectedFeatures,
        (res) => {
          if (res.result !== "success") {
            setErrorModal({
              show: true,
              heading: "ERROR: Failed to action the following ",
              message: (
                <ul>
                  {res.failedToActionFeatures.forEach((item) => {
                    return (
                      <li>
                        Feature Title: {item.title} - Client ID: {item.client}
                      </li>
                    );
                  })}
                </ul>
              ),
            });
          }
          retrieveFeatureList();
        },
        (err) => {
          alert("Something went wrong");
        }
      );
    }
    setSelectedRowsIndexes([]);
  };

  return (
    <>
      <ErrorModal errorModal={errorModal} setErrorModal={setErrorModal} />
      <Button variant="primary" onClick={handleShow}>
        Add Feature
      </Button>
      <Button variant="danger" onClick={deleteFeature}>
        Delete Feature
      </Button>
      <br />
      <Button variant="warning" onClick={enableFeature}>
        Enable Feature
      </Button>
      {listOfFeatures && <p>Total Features : {listOfFeatures.length}</p>}

      <AddFeatureModal
        error={error}
        setError={setError}
        show={showAddFeatureModal}
        setShow={setShowAddFeatureModal}
        retrieveFeatureList={retrieveFeatureList}
      />
      {!error && listOfFeatures && (
        <FeatureListTable
          tableData={listOfFeatures}
          selectedRowsIndexes={selectedRowsIndexes}
          setSelectedRowsIndexes={setSelectedRowsIndexes}
        />
      )}

      {error && (
        <p className="errorMessage">
          Failed to fetch feature list. Try refreshing page.
        </p>
      )}
    </>
  );
};

export default MainLayout;
