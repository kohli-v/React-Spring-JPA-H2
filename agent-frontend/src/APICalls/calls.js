// 4 api calls (For summary look at the bottom):

const retrieveFeatureListAPI = (successCallback, errorCallback) => {
  fetch("http://localhost:8080/retrieveFeatureList")
    .then((res) => res.json())
    .then((res) => {
      successCallback(res);
    })
    .catch((err) => errorCallback(err));
};

const addFeatureAPI = (data, successCallback, errorCallback) => {
  fetch("http://localhost:8080/addFeature", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(data),
  })
    .then((res) => res.json())
    .then((res) => {
      successCallback(res);
    })
    .catch((err) => errorCallback(err));
};

const deleteFeatureAPI = (data, successCallback, errorCallback) => {
  fetch("http://localhost:8080/deleteFeature", {
    method: "DELETE",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(data),
  })
    .then((res) => res.json())
    .then((res) => {
      successCallback(res);
    })
    .catch((err) => errorCallback(err));
};

const enableFeatureAPI = (data, successCallback, errorCallback) => {
  fetch("http://localhost:8080/enableFeature", {
    method: "PATCH",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(data),
  })
    .then((res) => res.json())
    .then((res) => {
      successCallback(res);
    })
    .catch((err) => errorCallback(err));
};

export {
  retrieveFeatureListAPI, //GET
  addFeatureAPI, //POST
  deleteFeatureAPI, //DELETE
  enableFeatureAPI, //PATCH
};
