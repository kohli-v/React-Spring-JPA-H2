import React, { useState, useRef } from "react";
import Modal from "react-bootstrap/Modal";
import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";
import { addFeatureAPI } from "../../../APICalls/calls";
import "./AddFeatureModal.css";
const AddFeatureModal = ({
  show,
  setShow,
  error,
  setError,
  retrieveFeatureList,
}) => {
  const formRef = useRef(null);

  const [validated, setValidated] = useState(false);
  const [charactersRemainingInteger, setCharactersRemainingInteger] = useState(
    200
  );

  const title = useRef(null);
  const description = useRef(null);
  const clientId = useRef(null);
  const clientPriority = useRef(null);
  const targetDate = useRef(null);
  const productArea = useRef(null);

  const getFormData = () => {
    const titleData = title.current.value.toString();
    const descriptionData = description.current.value.toString();
    const clientIdData = clientId.current.value.toString();
    const clientPriorityData = clientPriority.current.value;
    const targetDateData = targetDate.current.value.toString();
    const productAreaData = productArea.current.value.toString();

    let validFormData = false;

    if (titleData && descriptionData && clientPriorityData && targetDateData) {
      validFormData = true;
    }
    const data = {
      title: titleData,
      description: descriptionData,
      client: clientIdData,
      clientPriority: clientPriorityData,
      targetDate: targetDateData,
      productArea: productAreaData,
    };
    return {
      formData: data,
      validFormData: validFormData,
    };
  };

  const handleSubmit = () => {
    const data = getFormData();

    // Dont let form submit if it is not valid
    setValidated(true);
    if (formRef.current.checkValidity() === true) {
      console.log(formRef.current);

      addFeatureAPI(
        data.formData,
        (res) => {
          handleShow();
          retrieveFeatureList();
        },
        (err) => {
          setError("Something went wrong");
        }
      );
    }
  };

  const charactersRemaining = () => {
    const descriptionDataLength = description.current.value.toString().length;
    setCharactersRemainingInteger(200 - descriptionDataLength);
  };
  const handleShow = () => {
    setCharactersRemainingInteger(200);
    setValidated(false);
    setShow(!show);
    setError(null);
  };

  return (
    <>
      <Modal show={show} backdrop="static" keyboard={false}>
        <Modal.Header>
          <Modal.Title>Add feature </Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Form noValidate validated={validated} ref={formRef}>
            <Form.Group controlId="featureTitle">
              <Form.Label>Title</Form.Label>
              <Form.Control
                required
                type="text"
                ref={title}
                placeholder="Enter title"
              />
              <Form.Control.Feedback type="invalid">
                Title cannot be blank
              </Form.Control.Feedback>
            </Form.Group>
            <Form.Group controlId="featureDescription">
              <Form.Label>Description</Form.Label>
              <Form.Control
                required
                as="textarea"
                rows="4"
                onChange={charactersRemaining}
                ref={description}
                isInvalid={charactersRemainingInteger < 0}
                // isInvalid={true}
              />
              <Form.Text className="text-muted">
                {charactersRemainingInteger} characters remaining
              </Form.Text>
              <Form.Control.Feedback type="invalid">
                Invalid description length
              </Form.Control.Feedback>
            </Form.Group>
            <Form.Group controlId="featureClientId">
              <Form.Label>Client id</Form.Label>
              <Form.Control as="select" ref={clientId}>
                <option>A</option>
                <option>B</option>
                <option>C</option>
              </Form.Control>
              <Form.Control.Feedback type="invalid">
                Description cannot be blank
              </Form.Control.Feedback>
            </Form.Group>
            <Form.Group controlId="featureClientPriority">
              <Form.Label>Client priority</Form.Label>
              <Form.Control
                required
                type="number"
                placeholder="Enter priority"
                ref={clientPriority}
              />
              <Form.Control.Feedback type="invalid">
                Priority cannot be blank
              </Form.Control.Feedback>
            </Form.Group>
            <Form.Group controlId="featureDate">
              <Form.Label>Target date</Form.Label>
              <Form.Control required type="date" ref={targetDate} />
              <Form.Control.Feedback type="invalid">
                Date cannot be blank
              </Form.Control.Feedback>
            </Form.Group>
            <Form.Group controlId="featureProductArea">
              <Form.Label>Product area</Form.Label>
              <Form.Control as="select" ref={productArea}>
                <option>Policies</option>
                <option>Billing</option>
                <option>Claims</option>
                <option>Reports</option>
              </Form.Control>
            </Form.Group>
          </Form>
        </Modal.Body>
        <Modal.Footer>
          {error && <p className="errorMessageModal">Something went wrong</p>}
          <Button variant="secondary" onClick={handleShow}>
            Cancel
          </Button>
          <Button variant="primary" onClick={handleSubmit}>
            Save
          </Button>
        </Modal.Footer>
      </Modal>
    </>
  );
};

export default AddFeatureModal;
