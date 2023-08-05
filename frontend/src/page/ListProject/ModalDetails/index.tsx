import { useState } from "react";
import { Button, Modal } from "react-bootstrap";

interface ModalData {
  description: string;
}

export function ModalDetails({ description }: ModalData) {
  const [show, setShow] = useState<boolean>(false);

  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);

  return (
    <>
      <Button variant="primary" onClick={handleShow}>
        Details
      </Button>

      <Modal show={show} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>Description</Modal.Title>
        </Modal.Header>
        <Modal.Body>{description}</Modal.Body>
        <Modal.Footer>
            <Button variant="secondary" size="lg" onClick={handleClose}>
              Close
            </Button>
        </Modal.Footer>
      </Modal>
    </>
  );
}
