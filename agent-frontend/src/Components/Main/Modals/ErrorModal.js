import React from "react";
import Modal from "react-bootstrap/Modal";

const ErrorModal = ({ errorModal, setErrorModal }) => {
  const handleClose = () => setErrorModal({ show: false, message: null });

  return (
    <>
      <Modal show={errorModal.show} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>{errorModal.heading}</Modal.Title>
        </Modal.Header>
        <Modal.Body>{errorModal.message}</Modal.Body>
      </Modal>
    </>
  );
};

export default ErrorModal;
